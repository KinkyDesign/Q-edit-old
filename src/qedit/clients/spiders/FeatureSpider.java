package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import java.net.URISyntaxException;
import qedit.clients.ClientException;
import qedit.clients.GetClient;
import qedit.clients.Media;
import qedit.clients.components.Feature;
import qedit.clients.ontol.OntologicalClass;
import qedit.clients.ontol.collections.OTDatatypeProperties;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class FeatureSpider extends Tarantula<Feature> {

    String uri;

    public FeatureSpider(String uri) throws ClientException {
        super();
        this.uri = uri;
        GetClient client = new GetClient();
        client.setMediaType(Media.rdf_xml);
        try {
            client.setUri(uri);
        } catch (URISyntaxException ex) {
            throw new ClientException(ex);
        }
        model = client.getOntModel();
        resource = model.getResource(uri);
        model.write(System.out);
    }

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

        feature.setOntologies(getFeatureTypes(resource));

        Statement unitsStatement = resource.getProperty(
                OTDatatypeProperties.units().asDatatypeProperty(model));
        if (unitsStatement != null) {
            feature.setUnits(unitsStatement.getString());
        }



        return feature;
    }

    public static void main(String... args) throws ClientException {

        FeatureSpider fSpider = new FeatureSpider("http://apps.ideaconsult.net:8080/ambit2/feature/22202");
        Feature f = fSpider.parse();
        fSpider.model.write(System.out);
       System.out.println(f.getMeta().getTitle());

    }
}
