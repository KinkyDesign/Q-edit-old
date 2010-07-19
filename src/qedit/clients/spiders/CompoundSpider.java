package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import qedit.QEditApp;
import qedit.clients.ClientException;
import qedit.clients.GetClient;
import qedit.clients.Media;
import qedit.clients.ClientConstants;
import qedit.clients.components.Compound;
import qedit.clients.ontol.collections.OTClasses;
import qedit.clients.ontol.collections.OTDatatypeProperties;
import qedit.clients.ontol.collections.OTFeatures;
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
public class CompoundSpider extends Tarantula<Compound> {

    /**
     * Method according to which compounds are searched (e.g. by URI)
     */
    public enum LookupMethod {

        /**
         * Lookup method where the user provides
         * the URI of the compound
         */
        ByUri,
        /**
         * Get a compound from a local resource
         */
        FromFile,
        /**
         * Autodetect the search keyword
         */
        AutoDetect;
    }
    private static final String namesToken = "%s/all";// <--    ?feature_uris[]
    private String uri;
    private boolean doRetrieveSmiles = false;
    private LookupMethod lookupMethod;

    public boolean isDoRetrieveSmiles() {
        return doRetrieveSmiles;
    }

    /**
     * If set to <code>true</code>, the SMILES string will be retrieved from the
     * remote service at {@link QEditApp#getCasToSmilesService() } but this will
     * take more time. Note that this will not be applied if the SMILES is already
     * included in the RDF representation received from the STD compound service;
     * Setting to true will just force the retrieval of SMILES.
     * @param doRetrieveSmiles
     *      Whether the SMILES string should be retrieved from the remote.
     */
    public void setDoRetrieveSmiles(boolean doRetrieveSmiles) {
        this.doRetrieveSmiles = doRetrieveSmiles;
    }

    /**
     * Construct a new instance of CompoundSpider. CompoundSpider will load all
     * information concerning the identification of a chemical (like its CAS Registration
     * Number, its EINECS and other related infp) from a remote serive either explicitly
     * provided (with its URI) or using some lookup service. This constructor will
     * download the Ontological Model of the compound identified by the provided keyword,
     * or will throw an exception if the keyword does not identify any compounds on
     * the online database.
     * @param lookup
     *      The Lookup method (Look up by URI or by other keyword)
     * @param keyword
     *      The keyword
     * @throws ClientException
     *      An exception thrown in case the keyword does not identify any compounds
     *      or if some communication error happens. Might be thrown because {@link
     *      QEditApp#getCasToSmilesService() } returns an invalid service, or the
     *      service has encountered some error.
     */
    public CompoundSpider(LookupMethod lookup, String keyword) throws ClientException {
        this.lookupMethod = lookup;
        if (lookup.equals(LookupMethod.ByUri)) {
            uri = keyword; // Directly provide the URI
        } else if (lookup.equals(LookupMethod.FromFile)) {
            uri = "file://" + keyword;
        } else if (lookup.equals(LookupMethod.AutoDetect)) {
            uri = String.format(ClientConstants.getCompoundLookupService(), keyword);
            uri = String.format(namesToken, uri);
        }
        GetClient client = new GetClient();
        try {
            client.setUri(uri);
        } catch (URISyntaxException ex) {
            Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
        }
        client.setMediaType(Media.rdf_xml);
        model = client.getOntModel();
    }

    public CompoundSpider(String anyKeyword) throws ClientException {
        this(LookupMethod.AutoDetect, anyKeyword);
    }

    public CompoundSpider(java.net.URI uri) throws ClientException {
        this(LookupMethod.ByUri, uri.toString());
    }

    public CompoundSpider(java.io.File file) throws ClientException {
        this(LookupMethod.FromFile, file.getAbsolutePath());
    }

    /**
     * Parses the downloaded Ontological Model into an instance of {@link Compound }.
     * The compound contains information about the chemical's CAS-RN, IUPAC Name,
     * EINECS, SMILES (except if you have disabled it) and Chemical Name.
     * @return
     *      The parsed compound
     * @throws ClientException
     *      In case some communication error is encountered while the method tries to
     *      retireve the SMILES string from the remote service. In case you have disabled
     *      SMILES retrieval, this exception will not be thrown.
     */
    @Override
    public Compound parse() throws ClientException {
        Compound compound = new Compound(uri);
        StmtIterator it = model.listStatements(
                new SimpleSelector(null,
                OTObjectProperties.dataEntry().asObjectProperty(model), (RDFNode) null));
        StmtIterator it2 = null, it3 = null, itCompound = null;

        itCompound = model.listStatements(
                new SimpleSelector(null,
                RDF.type,
                OTClasses.Compound().inModel(model)));

        if (itCompound.hasNext()) {
            Statement stmt = itCompound.nextStatement();
            DCMetaInfoSpider metaSpider = new DCMetaInfoSpider(stmt.getSubject(), model);
            compound.setMeta(metaSpider.parse());
        }

        if (itCompound != null) {
            itCompound.close();
        }

        if (it.hasNext()) {
            Resource dataEntryNode = it.nextStatement().getObject().as(Resource.class);

            if (lookupMethod.equals(LookupMethod.AutoDetect)) {
                StmtIterator compoundResourceIter =
                        model.listStatements(new SimpleSelector(dataEntryNode, OTObjectProperties.compound().asObjectProperty(model), (RDFNode) null));

                if (compoundResourceIter.hasNext()) {
                    compound.setUri(compoundResourceIter.nextStatement().getObject().as(Resource.class).getURI());
                }
            }

            it2 = model.listStatements(
                    new SimpleSelector(dataEntryNode, OTObjectProperties.values().asObjectProperty(model), (RDFNode) null));

            while (it2.hasNext()) {
                Resource valuesResource = it2.nextStatement().getObject().as(Resource.class);
                it3 = model.listStatements(
                        new SimpleSelector(valuesResource, OTObjectProperties.feature().asObjectProperty(model), (RDFNode) null));
                if (it3.hasNext()) {
                    Resource featureNode = it3.nextStatement().getObject().as(Resource.class);
                    String featureSameAsURI = featureNode.getProperty(model.getProperty(
                            OWL.sameAs.getURI())).getObject().as(Resource.class).getURI();
                    String val = valuesResource.getProperty(OTDatatypeProperties.value().
                            asDatatypeProperty(model)).getObject().as(Literal.class).getString();
                    if (featureSameAsURI.equals(OTFeatures.IUPACName().getUri())) {
                        compound.setIupacName(val);
                    } else if (featureSameAsURI.equals(OTFeatures.EINECS().getUri())) {
                        compound.setEINECS(val);
                    } else if (featureSameAsURI.equals(OTFeatures.ChemicalName().getUri())) {
                        compound.setChemicalName(val);
                    } else if (featureSameAsURI.equals(OTFeatures.CASRN().getUri())) {
                        compound.setCasRn(val);
                    } else if (featureSameAsURI.equals(OTFeatures.SMILES().getUri())) {
                        compound.setSmiles(val);
                    } else if (featureSameAsURI.equals(OTFeatures.InChI_std().getUri())) {
                        compound.setInChI(val);
                    } else if (featureSameAsURI.equals(OTFeatures.InChIKey_std().getUri())) {
                        compound.setInChIKey(val);
                    } else if (featureSameAsURI.equals(OTFeatures.REACHRegistrationDate().getUri())) {
                        compound.setREACHRegistrationDate(val);
                    }
                }
            }
        }
        if (it != null) {
            it.close();
        }
        if (it2 != null) {
            it2.close();
        }
        if (it3 != null) {
            it3.close();
        }
        // GET SMILES:
        if (compound.getCasRn() != null && doRetrieveSmiles) {
            GetClient gc = new GetClient();
            try {
                gc.setUri(String.format(ClientConstants.getCasToSmilesService(), compound.getCasRn()));
                gc.setMediaType("text/plain");
                compound.setSmiles(gc.getRemoteMessage());
            } catch (IOException ex) {
                Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex) {
                Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // GET CONFORMERS:
        if (uri.contains("conformer")) {
            compound.getConformers().add(uri);
        } else {
            String conformerUri = uri.endsWith("/")?uri+"conformer/":uri+"/conformer/";
            GetClient client = new GetClient();
            try {
                client.setUri(conformerUri);
            } catch (URISyntaxException ex) {
                Logger.getLogger(CompoundSpider.class.getName()).log(Level.SEVERE, null, ex);
            }
            client.setMediaType(Media.rdf_xml);
            OntModel conformerModel = client.getOntModel();
            Resource conformerResource = conformerModel.getResource(conformerUri);
            StmtIterator conformerIt = conformerModel.listStatements(
                    new SimpleSelector(conformerResource,
                    OTObjectProperties.dataEntry().asObjectProperty(conformerModel),
                    (RDFNode)null)
                    );
            while(conformerIt.hasNext()){
                String cUri = conformerIt.nextStatement()
                        .getResource().getProperty(
                        OTObjectProperties.compound().asObjectProperty(conformerModel)
                        ).getResource()
                        .getURI();
                compound.getConformers().add(cUri);
//               if(cUri.contains("conformer")){
//                   compound.getConformers().add(cUri);
//               }
            }
        }
        for(String s : compound.getConformers()){
            System.out.println(s);
        }
        return compound;
    }

    /**
     * Outputs:
     * <pre>
     * IUPAC Name    : 1-ethyl-2-methylbenzene
     * Chemical Name : 2-ethyltoluene
     * CAS-RN        : 611-14-3
     * SMILES String : CCc1ccccc1C
     * EINCES        : 210-255-1
     * </pre>
     * @param args - Not used
     * @throws ClientException
     */
    public static void main(String... args) throws ClientException, FileNotFoundException {
        CompoundSpider spider = new CompoundSpider(LookupMethod.AutoDetect, "Phenol");
        System.out.println(spider.parse());
        spider.close();

    }
}
