package qedit.clients.ontol.impl;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Property;
import java.util.ArrayList;
import java.util.Collection;
import qedit.clients.ontol.DCMetaInfo;
import qedit.clients.ontol.OTProperty;
import qedit.clients.ontol.OntologicalClass;
import qedit.clients.ontol.collections.OTClasses;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public abstract class OTPropertyImpl implements OTProperty {

    private DCMetaInfo metaInfo = new DCMetaInfoImpl();
    private Collection<OntologicalClass> domain = new ArrayList<OntologicalClass>();
    private Collection<OTProperty> superProperties = new ArrayList<OTProperty>();
    private String ns = OTClasses.NS;
    private String name;

    public OTPropertyImpl() {
    }

    public OTPropertyImpl(String name) {
        this.name = name;
    }
    

    @Override
    public DCMetaInfo getMetaInfo() {
        return metaInfo;
    }

    @Override
    public void setMetaInfo(DCMetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    @Override
    public Collection<qedit.clients.ontol.OntologicalClass> getDomain() {
        return domain;
    }

    @Override
    public void setDomain(Collection<OntologicalClass> domain) {
        this.domain = domain;
    }

    @Override
    public Collection<OTProperty> getSuperProperties() {
        return superProperties;
    }

    @Override
    public void setSuperProperties(Collection<OTProperty> superProperties) {
        this.superProperties = superProperties;
    }

    @Override
    public String getNameSpace() {
        return ns;
    }

    @Override
    public void setNameSpace(String ns) {
        this.ns = ns;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUri() {
        return getNameSpace()+getName();
    }

    @Override
    public Property getProperty(OntModel model) {
        return model.getProperty(getUri());
    }
    

    

}
