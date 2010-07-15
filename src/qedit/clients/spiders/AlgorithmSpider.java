
package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import qedit.clients.ClientException;
import qedit.clients.GetClient;
import qedit.clients.Media;
import qedit.clients.components.Algorithm;
import qedit.clients.ontol.OntologicalClass;
import qedit.clients.ontol.collections.OTObjectProperties;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class AlgorithmSpider extends Tarantula<Algorithm>{

    private String uri;

    public AlgorithmSpider(String uri) throws ClientException {
        super();
        this.uri = uri;
        GetClient client =  new GetClient();
        client.setMediaType(Media.rdf_xml);
        try {
            client.setUri(uri);
        } catch (URISyntaxException ex) {
            throw new ClientException(ex);
        }
        model = client.getOntModel();
        resource = model.getResource(uri);
    }

    public Algorithm parse(){
        Algorithm algorithm = new Algorithm();

        algorithm.setOntologies(getOTATypes(resource));

        DCMetaInfoSpider metaSpider = new DCMetaInfoSpider(model, uri);
        algorithm.setMeta(metaSpider.parse());

        StmtIterator itParam = model.listStatements(
                new SimpleSelector(resource,
                OTObjectProperties.parameters().asObjectProperty(model),
                (RDFNode)null));

        while(itParam.hasNext()){
            ParameterSpider paramSpider = new ParameterSpider(model, itParam.nextStatement().getObject().as(Resource.class));
            algorithm.getParameters().add(paramSpider.parse());
        }

        return algorithm;

    }


    public static void main(String... args) throws ClientException{
        AlgorithmSpider spider = new AlgorithmSpider("http://opentox.ntua.gr:3000/algorithm/filter");
        Algorithm a = spider.parse();
        System.out.println(a.getMeta().getTitle());
        System.out.println(a.getParameters().isEmpty());
        for(OntologicalClass oc : a.getOntologies()){
            System.out.println(oc.getName());
        }
    }



}
