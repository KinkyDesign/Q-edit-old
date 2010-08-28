package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.net.URISyntaxException;
import java.util.ArrayList;
import qedit.QEditView;
import qedit.clients.ClientException;
import qedit.clients.GetClient;
import qedit.clients.Media;
import qedit.clients.components.Feature;
import qedit.clients.components.Model;
import qedit.clients.components.Parameter;
import qedit.clients.ontol.collections.OTObjectProperties;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class ModelSpider extends Tarantula<Model> {

    String uri;

    public ModelSpider(Resource resource, OntModel model) {
        super(resource, model);
        this.uri = resource.getURI();
    }

    public ModelSpider(String uri) throws ClientException {
        super();
        this.uri = uri;
        GetClient client = new GetClient();
        client.setMediaType(Media.rdf_xml);
        try {
            if (QEditView.getToken()!=null){
                uri += "?tokenid="+QEditView.getToken().getTokenUrlEncoded();
            }
            client.setUri(uri);
        } catch (URISyntaxException ex) {
            throw new ClientException(ex);
        }
        model = client.getOntModel();
        resource = model.getResource(uri);
    }

    @Override
    public Model parse() throws ClientException {
        Model m = new Model();

        m.setUri(uri);
        m.setMeta(new DCMetaInfoSpider(resource, model).parse());

        StmtIterator itDataset = model.listStatements(
                new SimpleSelector(resource,
                OTObjectProperties.trainingDataset().asObjectProperty(model),
                (RDFNode) null));
        if (itDataset.hasNext()) {
            m.setDataset(itDataset.nextStatement().getObject().as(Resource.class).getURI());
        }

        StmtIterator itFeature = model.listStatements(
                new SimpleSelector(resource,
                OTObjectProperties.predictedVariables().asObjectProperty(model),
                (RDFNode) null));

        if (itFeature.hasNext()) {
            FeatureSpider fspider = new FeatureSpider(model,
                    itFeature.nextStatement().getObject().as(Resource.class).getURI());
            m.setPredictedFeature(fspider.parse());
        }

        itFeature = model.listStatements(
                new SimpleSelector(resource,
                OTObjectProperties.dependentVariables().asObjectProperty(model),
                (RDFNode) null));
        if (itFeature.hasNext()) {
            FeatureSpider fspider = new FeatureSpider(model,
                    itFeature.nextStatement().getObject().as(Resource.class).getURI());
            m.setDependentFeature(fspider.parse());
        }

        itFeature = model.listStatements(
                new SimpleSelector(resource,
                OTObjectProperties.independentVariables().asObjectProperty(model),
                (RDFNode) null));
        ArrayList<Feature> indepFeatures = new ArrayList<Feature>();
        while (itFeature.hasNext()) {
            FeatureSpider fspider = new FeatureSpider(model,
                    itFeature.nextStatement().getObject().as(Resource.class).getURI());
            indepFeatures.add(fspider.parse());
        }
        m.setIndependentFeatures(indepFeatures);

        StmtIterator itAlgorithm = model.listStatements(
                new SimpleSelector(resource,
                OTObjectProperties.algorithm().asObjectProperty(model),
                (RDFNode) null));
        if (itAlgorithm.hasNext()) {
            AlgorithmSpider aspider = new AlgorithmSpider(
                    itAlgorithm.nextStatement().getObject().as(Resource.class).getURI(), QEditView.getToken());
            m.setAlgorithm(aspider.parse());
        }

        StmtIterator itParam = model.listStatements(
                new SimpleSelector(resource,
                OTObjectProperties.parameters().asObjectProperty(model),
                (RDFNode) null));

        ArrayList<Parameter> parameters = new ArrayList<Parameter>();
        while (itParam.hasNext()) {
            ParameterSpider paramSpider = new ParameterSpider(model, itParam.nextStatement().getObject().as(Resource.class));
            parameters.add(paramSpider.parse());
        }
        m.setParameters(parameters);
        getFeatureMetaFromRemote(m.getDependentFeature());
        getFeatureMetaFromRemote(m.getPredictedFeature());
        for (Feature indFeat : m.getIndependentFeatures()) {
            getFeatureMetaFromRemote(indFeat);
        }
        return m;
    }

    private void getFeatureMetaFromRemote(Feature feature) throws ClientException {
        String featTitle = feature.getMeta().getTitle();
        if (featTitle == null
                || (featTitle != null && featTitle.isEmpty())) {
            FeatureSpider featureSpider = new FeatureSpider(feature.getUri());
            Feature featureFromRemote = featureSpider.parse();
            feature.setMeta(featureFromRemote.getMeta());
        }

        featTitle = feature.getMeta().getTitle();
        if (featTitle == null
                || (featTitle != null && featTitle.isEmpty())) {
            feature.getMeta().setTitle(feature.getUri());
        }
    }

    public static void main(String... args) throws ClientException {
        ModelSpider mSpider = new ModelSpider("http://opentox.ntua.gr:3000/model/f9a97443-6baf-4361-a55c-b08cf12c3e39");
        Model m = mSpider.parse();
        System.out.println(m.getParameters().get(0).getValue());
        System.out.println(m.getAlgorithm().getMeta().getTitle());
        System.out.println(m.getDataset());
        System.out.println(m.getPredictedFeature().getUri());
    }
}
