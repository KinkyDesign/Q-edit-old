
package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Resource;
import qedit.clients.components.Feature;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class FeatureSpider extends Tarantula<Feature>{

    String uri;

    public FeatureSpider(Resource resource, OntModel model) {
        super(resource, model);
        uri = resource.getURI();
    }

    public FeatureSpider(OntModel model, String uri) {
        super();
        this.model = model;
        this.uri = uri;
        this.resource = model.getResource(uri);
    }



    @Override
    public Feature parse() {
        Feature feature = new Feature();
        feature.setMeta(new DCMetaInfoSpider(resource, model).parse());
        feature.setUri(uri);
        
        return feature;
    }

}
