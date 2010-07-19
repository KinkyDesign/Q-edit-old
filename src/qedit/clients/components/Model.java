package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;
import java.util.ArrayList;
import qedit.clients.ontol.DCMetaInfo;
import qedit.clients.ontol.impl.DCMetaInfoImpl;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class Model extends AbstractComponent {

    private String uri;
    private String dataset;
    private Algorithm algorithm;
    private Feature predictedFeature;
    private Feature dependentFeature;
    private ArrayList<Feature> independentFeatures;
    private ArrayList<Parameter> parameters;
    private String qmrfReportUri;
    private DCMetaInfo qmrfReportMeta = new DCMetaInfoImpl();
    private DCMetaInfo meta = new DCMetaInfoImpl();
    private String year;
    private String month;
    private String day;

    public Model() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public DCMetaInfo getQmrfReportMeta() {
        return qmrfReportMeta;
    }

    public void setQmrfReportMeta(DCMetaInfo qmrfReportMeta) {
        this.qmrfReportMeta = qmrfReportMeta;
    }

    public String getQmrfReportUri() {
        return qmrfReportUri;
    }

    public void setQmrfReportUri(String qmrfReportUri) {
        this.qmrfReportUri = qmrfReportUri;
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
