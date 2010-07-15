package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.DC_10;
import com.hp.hpl.jena.vocabulary.DC_11;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDFS;
import java.util.ArrayList;
import qedit.clients.ontol.DCMetaInfo;
import qedit.clients.ontol.collections.OTDatatypeProperties;
import qedit.clients.ontol.collections.OTObjectProperties;
import qedit.clients.ontol.impl.DCMetaInfoImpl;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class DCMetaInfoSpider {

    private Resource resource;
    private OntModel model;

    public DCMetaInfoSpider(OntModel model, String uri) {
        this.model = model;
        resource = model.getResource(uri);
    }

    public DCMetaInfoSpider(Resource node, OntModel model) {
        this.resource = node;
        this.model = model;
    }

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

    private String retrieveProp(Property prop) {
        if (prop.equals(OTObjectProperties.hasSource().asObjectProperty(model))) {
            StmtIterator it = model.listStatements(new SimpleSelector(resource, prop, (RDFNode) null));
            if (it.hasNext()) {
                try {
                    return (it.nextStatement().getObject().as(Resource.class).getURI());
                } finally {
                    it.close();
                }
            }

        } else {
            StmtIterator it = model.listStatements(new SimpleSelector(resource, prop, (Literal) null));
            if (it.hasNext()) {
                try {
                    return (it.nextStatement().getObject().as(Literal.class).getString());
                } finally {
                    it.close();
                }
            }
        }
        return null;
    }

    private ArrayList<String> retrieveProps(Property prop) {
        ArrayList<String> props = new ArrayList<String>();

        StmtIterator it = model.listStatements(new SimpleSelector(resource, prop, (Literal) null));
        while (it.hasNext()) {
            try {
                props.add(it.nextStatement().getObject().as(Literal.class).getString());
            } finally {
                it.close();
            }
        }
        return props;
    }
}
