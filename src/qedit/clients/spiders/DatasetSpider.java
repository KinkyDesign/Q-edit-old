
package qedit.clients.spiders;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import qedit.clients.ClientException;
import qedit.clients.GetClient;
import qedit.clients.Media;
import qedit.clients.components.Compound;
import qedit.clients.components.Dataset;
import qedit.clients.components.Feature;
import qedit.clients.components.FeatureValue;
import qedit.clients.ontol.collections.OTDatatypeProperties;
import qedit.clients.ontol.collections.OTObjectProperties;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class DatasetSpider extends Tarantula<Dataset>{

    String datasetUri;
    String compoundUri;

    public DatasetSpider(String datasetUri, String compoundUri) throws ClientException {
        super();
        this.datasetUri = datasetUri;
        this.compoundUri = compoundUri;
        GetClient client =  new GetClient();
        client.setMediaType(Media.rdf_xml);
        try {
            client.setUri(datasetUri);
        } catch (URISyntaxException ex) {
            throw new ClientException(ex);
        }
        model = client.getOntModel();
        resource = model.getResource(datasetUri);
    }

    public DatasetSpider(Resource resource, OntModel model, String compoundUri) {
        super(resource, model);
        this.datasetUri = resource.getURI();
        this.compoundUri = compoundUri;
    }

    @Override
    public Dataset parse() throws ClientException {
        Dataset dataset = new Dataset();
        Compound compound = new Compound();
        System.out.println(compoundUri);
        CompoundSpider cSpider = new CompoundSpider(compoundUri);
        compound = cSpider.parse();

        dataset.setUri(datasetUri);
        dataset.setCompoundUri(compoundUri);
        dataset.setMeta(new DCMetaInfoSpider(resource, model).parse());

        StmtIterator entryIt = model.listStatements(
                new SimpleSelector(resource, OTObjectProperties.dataEntry().asObjectProperty(model),
                (RDFNode)null));

        Resource entryResource = null;
        while(entryIt.hasNext()){
            Resource entry = entryIt.nextStatement().getObject().as(Resource.class);
            StmtIterator compoundIt = model.listStatements(
                    new SimpleSelector(entry, 
                    OTObjectProperties.compound().asObjectProperty(model),
                    (RDFNode)null));
            if(compoundIt.hasNext()){
                Statement compStmt = compoundIt.nextStatement();
                if(compound.getConformers().contains(compStmt.getObject().as(Resource.class).getURI())){
                    entryResource = entry;
                }
                if(compStmt.getObject().as(Resource.class).getURI().equals(compoundUri)){
                    entryResource = entry;
                    break;
                }
            }
        }

        if (entryResource==null){
            return dataset;
        }

        StmtIterator valuesIt = model.listStatements(
                new SimpleSelector(entryResource,
                OTObjectProperties.values().asObjectProperty(model),
                (RDFNode)null));

        Map<Feature,FeatureValue> features = new HashMap<Feature, FeatureValue>();
        while(valuesIt.hasNext()){
            Resource valueResource = valuesIt.nextStatement().getObject().as(Resource.class);

            Resource featureResource = valueResource.getProperty(OTObjectProperties.feature().asObjectProperty(model)).getResource();
            FeatureSpider fSpider = new FeatureSpider(featureResource, model);
            Feature feature = fSpider.parse();

            Literal value = valueResource.getProperty(
                    OTDatatypeProperties.value().asDatatypeProperty(model)
                    ).getObject().as(Literal.class);

            FeatureValue fValue;
            if(value.getDatatype().equals(XSDDatatype.XSDdouble)){
                 fValue = new FeatureValue<Double>(value.getDouble(),XSDDatatype.XSDdouble);
            }else if(value.getDatatype().equals(XSDDatatype.XSDinteger)){
                fValue = new FeatureValue<Integer>(value.getInt(),XSDDatatype.XSDinteger);
            }else if(value.getDatatype().equals(XSDDatatype.XSDfloat)){
                fValue = new FeatureValue<Float>(value.getFloat(),XSDDatatype.XSDfloat);
            }else{
                fValue = new FeatureValue<String>(value.getString(),XSDDatatype.XSDstring);
            }
            features.put(feature, fValue);
        }
        dataset.setFeatures(features);

        return dataset;
    }

    public static void main(String... args) throws ClientException{
        DatasetSpider dSpider = new DatasetSpider("http://ambit.uni-plovdiv.bg:8080/ambit2/dataset/6",
                "http://ambit.uni-plovdiv.bg:8080/ambit2/compound/8829/conformer/8829");
        Dataset d = dSpider.parse();

        for(Feature feature : d.getFeatures().keySet()){
            System.out.println("Feature:"+feature.getUri());
            System.out.println("   Value:"+d.getFeatures().get(feature).getValue());
            System.out.println("   Type:"+d.getFeatures().get(feature).getType());
            System.out.println("   Units:"+feature.getUnits()+"\n");            
        }

    }

}
