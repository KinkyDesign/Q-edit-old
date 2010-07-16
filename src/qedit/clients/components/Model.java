package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;
import java.util.ArrayList;
import qedit.clients.ontol.DCMetaInfo;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class Model extends AbstractComponent{

    private String uri;
    private String dataset;
    private Algorithm algorithm;
    private Feature predictedFeature;
    private Feature dependentFeature;
    private ArrayList<Feature> independentFeatures;
    private ArrayList<Parameter> parameters;
    private DCMetaInfo meta;

    public Model() {
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public Feature getDependentFeature() {
        return dependentFeature;
    }

    public void setDependentFeature(Feature dependentFeature) {
        this.dependentFeature = dependentFeature;
    }

    public ArrayList<Feature> getIndependentFeatures() {
        return independentFeatures;
    }

    public void setIndependentFeatures(ArrayList<Feature> independentFeatures) {
        this.independentFeatures = independentFeatures;
    }

    public DCMetaInfo getMeta() {
        return meta;
    }

    public void setMeta(DCMetaInfo meta) {
        this.meta = meta;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Feature getPredictedFeature() {
        return predictedFeature;
    }

    public void setPredictedFeature(Feature predictedFeature) {
        this.predictedFeature = predictedFeature;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public Individual asIndividual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}