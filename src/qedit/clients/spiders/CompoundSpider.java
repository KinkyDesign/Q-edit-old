package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import qedit.QEditApp;
import qedit.clients.ClientException;
import qedit.clients.GetClient;
import qedit.clients.Media;
import qedit.clients.components.Compound;
import qedit.clients.ontol.collections.OTClasses;
import qedit.clients.ontol.collections.OTDatatypeProperties;
import qedit.clients.ontol.collections.OTObjectProperties;

/**
 * Parses a compound that is identified by some URI. Provide the compound's URI
 * or directly it's Ontological Model (OntoModel) and ask the parser for various
 * information that might be ontained in this model. If these information are not
 * found, the parser returns null.
 * 
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class CompoundSpider {

    public enum LookupMethod {

        Uri,
        AutoDetect;
    }
    private static final String namesToken = "%s/names";// <--    ?feature_uris[]
    private OntModel om;
    private String uri;
    public static final String chemicalName = OTClasses.NS + "ChemicalName";
    public static final String einecs = OTClasses.NS + "EINECS";
    public static final String iupacName = OTClasses.NS + "IUPACName";
    public static final String casRn = OTClasses.NS + "CASRN";

    public CompoundSpider(LookupMethod lookup, String keyword) throws ClientException {
        if (lookup.equals(LookupMethod.Uri)) {
            this.uri = keyword;
        } else {
            uri = String.format(QEditApp.getSmilesToUriService(), keyword);
        }
        GetClient client = new GetClient();
        try {
            uri = String.format(namesToken, uri);
            client.setUri(uri);
        } catch (URISyntaxException ex) {
            Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
        }
        client.setMediaType(Media.rdf_xml);
        om = client.getOntModel();
    }

    private Compound parse() throws ClientException {
        Compound compound = new Compound(uri);
        StmtIterator it = om.listStatements(new SimpleSelector(om.getResource(uri), OTObjectProperties.dataEntry().asObjectProperty(om), (RDFNode) null));

        if (it.hasNext()) {
            Resource dataEntryNode = it.nextStatement().getObject().as(Resource.class);
            StmtIterator it2 = om.listStatements(new SimpleSelector(dataEntryNode, OTObjectProperties.values().asObjectProperty(om), (RDFNode) null));
            while (it2.hasNext()) {
                Resource valuesResource = it2.nextStatement().getObject().as(Resource.class);
                StmtIterator it3 = om.listStatements(new SimpleSelector(valuesResource, OTObjectProperties.feature().asObjectProperty(om), (RDFNode) null));
                if (it3.hasNext()) {
                    Resource featureNode = it3.nextStatement().getObject().as(Resource.class);
                    String featureSameAsURI = featureNode.getProperty(om.getProperty(OWL.sameAs.getURI())).getObject().as(Resource.class).getURI();
                    String val = valuesResource.getProperty(OTDatatypeProperties.value().asDatatypeProperty(om)).getObject().as(Literal.class).getString();
                    if (featureSameAsURI.equals(iupacName)) {
                        compound.setIupacName(val);
                    } else if (featureSameAsURI.equals(einecs)) {
                        compound.setEINECS(val);
                    } else if (featureSameAsURI.equals(chemicalName)) {
                        compound.setChemicalName(val);
                    } else if (featureSameAsURI.equals(casRn)) {
                        compound.setCasRn(val);
                    }
                }
            }
        }
        // GET SMILES:
        if (compound.getCasRn() != null) {
            GetClient gc = new GetClient();
            try {
                gc.setUri(String.format(QEditApp.getCasToSmilesService(), compound.getCasRn()));
                gc.setMediaType("text/plain");
                compound.setSmiles(gc.getRemoteMessage());
            } catch (IOException ex) {
                Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return compound;
    }

    public static void main(String... args) throws ClientException {
        CompoundSpider spider = new CompoundSpider(LookupMethod.AutoDetect, "dopamine");
        System.out.println(spider.parse());
    }
}
