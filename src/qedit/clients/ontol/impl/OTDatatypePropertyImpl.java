package qedit.clients.ontol.impl;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.vocabulary.DC;
import java.util.ArrayList;
import java.util.Collection;
import qedit.clients.ontol.DCMetaInfo;
import qedit.clients.ontol.OntologicalClass;

public class OTDatatypePropertyImpl
        extends OTPropertyImpl implements qedit.clients.ontol.OTDatatypeProperty {

    private Collection<XSDDatatype> domain = new ArrayList<XSDDatatype>();

    public OTDatatypePropertyImpl() {
        super();
    }

    public OTDatatypePropertyImpl(String name) {
        super(name);
    }

    @Override
    public Collection<XSDDatatype> getRange() {
        return domain;
    }

    @Override
    public void setRange(Collection<XSDDatatype> range) {
        this.domain = range;
    }

    @Override
    public DatatypeProperty asDatatypeProperty(OntModel model) {
        DatatypeProperty property = null;
        property = model.getDatatypeProperty(getUri());
        if (property!=null){
            return property;
        }
        
        property = model.createDatatypeProperty(getUri());
        if (getDomain() != null) {
            for (OntologicalClass dom : getDomain()) {
                property.addDomain(dom.inModel(model));
            }
        }

        if (getRange() != null) {
            for (XSDDatatype xsd : getRange()) {
                property.addRange(model.createResource(xsd.getURI()));
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
