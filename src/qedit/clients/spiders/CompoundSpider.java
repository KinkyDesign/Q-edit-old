package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import qedit.QEditApp;
import qedit.clients.ClientException;
import qedit.clients.GetClient;
import qedit.clients.Media;
import qedit.clients.components.Compound;
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
    private static final String featureUriToken = "?feature_uris%5B%5D=";
    public static final String mfFeature = "http://ambit.uni-plovdiv.bg:8080/ambit2/feature/1";
    public static final String chemicalNameFeature = "http://ambit.uni-plovdiv.bg:8080/ambit2/feature/2";
    public static final String smilesFeature = "http://ambit.uni-plovdiv.bg:8080/ambit2/feature/11";
    public static final String ecNameFeature = "http://ambit.uni-plovdiv.bg:8080/ambit2/feature/5";
    public static final String casRnFeature = "http://ambit.uni-plovdiv.bg:8080/ambit2/feature/6";
    public static final String mwFeature = "http://ambit.uni-plovdiv.bg:8080/ambit2/feature/8";
    public static final String iupacNameFeature = "http://ambit.uni-plovdiv.bg:8080/ambit2/feature/9";
    public static final String inChINameFeature = "http://ambit.uni-plovdiv.bg:8080/ambit2/feature/13";
    private OntModel om;
    private String uri;

    public CompoundSpider(LookupMethod lookup, String keyword) {
        if (lookup.equals(LookupMethod.Uri)) {
            this.uri = keyword;
        } else {
            try {
                GetClient client = new GetClient();
                client.setMediaType("text/uri-list");
                client.setUri(String.format(QEditApp.getSmilesToUriService(), keyword));
                uri = client.getUriList().get(0);
            } catch (ClientException ex) {
                Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String getProperty(String query) throws ClientException {
        GetClient client = new GetClient();
        try {
            client.setUri(uri + featureUriToken + query);
        } catch (URISyntaxException ex) {
            Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
        }
        client.setMediaType(Media.rdf_xml);
        om = client.getOntModel();
        StmtIterator it = om.listStatements(new SimpleSelector(null, OTObjectProperties.feature().asObjectProperty(om), om.getResource(query)));
        if (it.hasNext()) {
            Resource subj = it.nextStatement().getSubject();
            it = om.listStatements(new SimpleSelector(subj, OTDatatypeProperties.value().asDatatypeProperty(om), (Literal) null));
            if (it.hasNext()) {
                return it.nextStatement().getObject().as(Literal.class).getString();
            }
        }
        return null;
    }

    public String getCasRn() throws ClientException {
        return getProperty(casRnFeature);
    }

    public String getEcName() throws ClientException {
        return getProperty(ecNameFeature);
    }

    public String getInChI() throws ClientException {
        return getProperty(inChINameFeature);
    }

    public String getIUPACName() throws ClientException {
        return getProperty(iupacNameFeature);
    }

    public String getMolecularFormula() throws ClientException {
        return getProperty(mfFeature);
    }

    public String getMolecularWeight() throws ClientException {
        return getProperty(mwFeature);
    }

    public String getSmiles() throws ClientException {
        return getProperty(smilesFeature);
    }

    public Compound parseCompound() throws ClientException {
        Compound compound = new Compound(uri);
        CompoundSpider spider = new CompoundSpider(LookupMethod.Uri, uri);
        compound.setCasRn(spider.getCasRn());
        compound.setGeneratedMW(spider.getMolecularWeight());
        compound.setInChI(spider.getInChI());
        compound.setIupacName(spider.getIUPACName());
        compound.setMolFormula(spider.getMolecularFormula());
        compound.setGeneratedMW(spider.getMolecularWeight());
        compound.setEcName(spider.getEcName());
        compound.setSmiles(spider.getSmiles());
        return compound;
    }

    public static void main(String... args) throws ClientException {
        CompoundSpider p = new CompoundSpider(LookupMethod.AutoDetect, "dopamine");
        Compound compound = p.parseCompound();
        System.out.println(compound);
    }
}
