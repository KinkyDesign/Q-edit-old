
package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntModel;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import qedit.clients.ClientException;
import qedit.clients.GetClient;
import qedit.clients.Media;
import qedit.clients.components.Algorithm;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class AlgorithmSpider {

    private String uri;
    private OntModel model;

    public AlgorithmSpider(String uri) throws ClientException {
        this.uri = uri;
        GetClient client =  new GetClient();
        client.setMediaType(Media.rdf_xml);
        try {
            client.setUri(uri);
        } catch (URISyntaxException ex) {
            throw new ClientException(ex);
        }
        model = client.getOntModel();
    }

    public Algorithm parse(){
        Algorithm algorithm = new Algorithm();
        DCMetaInfoSpider metaSpider = new DCMetaInfoSpider(model, uri);
        algorithm.setMeta(metaSpider.parse());
        

        return algorithm;

    }


    public static void main(String... args) throws ClientException{
        AlgorithmSpider spider = new AlgorithmSpider("http://opentox.ntua.gr:3000/algorithm/mlr");
        System.out.println(spider.parse().getMeta().getTitle());
    }



}
