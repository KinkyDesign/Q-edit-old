package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDFS;
import qedit.clients.ontol.DCMetaInfo;
import qedit.clients.ontol.collections.OTObjectProperties;
import qedit.clients.ontol.impl.DCMetaInfoImpl;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class DCMetaInfoSpider extends Tarantula {

    public DCMetaInfoSpider(OntModel model, String uri) {
        super();
        this.model = model;
        resource = model.getResource(uri);
    }

    public DCMetaInfoSpider(Resource resource, OntModel model) {
        super(resource, model);
    }

    @Override
    public DCMetaInfo parse() {
        DCMetaInfo dcmeta = new DCMetaInfoImpl();
        dcmeta.setTitle(retrieveProp(DC.title));
        dcmeta.setCreator(retrieveProp(DC.creator));
        dcmeta.setDescription(retrieveProp(DC.description));
        dcmeta.setPublisher(retrieveProp(DC.publisher));
        dcmeta.setComment(retrieveProp(RDFS.comment));
        dcmeta.setVersionInfo(retrieveProp(OWL.versionInfo));
        dcmeta.setHasSource(retrieveProp(OTObjectProperties.hasSource().asObjectProperty(model)));
        dcmeta.getContributors().addAll(retrieveProps(DC.contributor));
        dcmeta.setSameAs(retrieveProp(OWL.sameAs));
        dcmeta.setSeeAlso(retrieveProp(RDFS.seeAlso));
        dcmeta.setIdentifier(retrieveProp(DC.identifier));
        //TODO: audiences
        return dcmeta;
    }
}
