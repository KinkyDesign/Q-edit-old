
package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;
import java.util.Map;
import qedit.clients.ontol.DCMetaInfo;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class Dataset extends AbstractComponent{

    String datasetUri;
    String compoundUri;
    Map<Feature, FeatureValue> features;
    DCMetaInfo meta;

    public Dataset() {
    }

    public DCMetaInfo getMeta() {
        return meta;
    }

    public void setMeta(DCMetaInfo meta) {
        this.meta = meta;
    }

    public String getCompoundUri() {
        return compoundUri;
    }

    public void setCompoundUri(String compoundUri) {
        this.compoundUri = compoundUri;
    }

    public Map<Feature, FeatureValue> getFeatures() {
        return features;
    }

    public void setFeatures(Map<Feature, FeatureValue> features) {
        this.features = features;
    }

    public String getUri() {
        return datasetUri;
    }

    public void setUri(String uri) {
        this.datasetUri = uri;
    }



    @Override
    public Individual asIndividual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
