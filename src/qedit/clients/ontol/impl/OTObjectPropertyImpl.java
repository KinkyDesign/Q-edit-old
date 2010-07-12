package qedit.clients.ontol.impl;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.vocabulary.DC;
import java.util.ArrayList;
import java.util.Collection;
import qedit.clients.ontol.DCMetaInfo;
import qedit.clients.ontol.OntologicalClass;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class OTObjectPropertyImpl extends OTPropertyImpl implements qedit.clients.ontol.OTObjectProperty {

    private Collection<OntologicalClass> range = new ArrayList<OntologicalClass>();

    public OTObjectPropertyImpl() {
        super();
    }

    public OTObjectPropertyImpl(String name) {
        super(name);
    }


    @Override
    public Collection<OntologicalClass> getRange() {
        return range;
    }

    @Override
    public void setRange(Collection<OntologicalClass> range) {
        this.range = range;
    }
    
   

    @Override
    public ObjectProperty asObjectProperty(OntModel model) {
        ObjectProperty property = null;
        property = model.getObjectProperty(getUri());
        if (property!=null){
            return property;
        }
        property = model.createObjectProperty(getUri());

        if (getDomain() != null) {
            for (OntologicalClass dom : getDomain()) {
                property.addDomain(dom.inModel(model));
            }
        }

        if (getRange() != null) {
            for (OntologicalClass xsd : getRange()) {
                property.addRange(xsd.inModel(model));
            }
        }

        DCMetaInfo meta = getMetaInfo();
        if (meta != null) {
            String title = meta.getTitle();
            if (title != null) {
                property.addLiteral(model.createAnnotationProperty(DC.title.getURI()),
                        model.createTypedLiteral(title, XSDDatatype.XSDstring));
            }

            String description = meta.getDescription();
            if (description != null) {
                property.addLiteral(model.createAnnotationProperty(DC.description.getURI()),
                        model.createTypedLiteral(description, XSDDatatype.XSDstring));
            }

            String comment = meta.getComment();
            if (comment != null) {
                property.addComment(model.createTypedLiteral(comment, XSDDatatype.XSDstring));
            }

            String identifier = meta.getIdentifier();
            if (identifier!=null){
                property.addLiteral(model.createAnnotationProperty(DC.identifier.getURI()),
                        model.createTypedLiteral(identifier, XSDDatatype.XSDanyURI));
            }
        }
        return property;
    }

    

}