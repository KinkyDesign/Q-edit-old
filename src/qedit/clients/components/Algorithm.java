package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import qedit.clients.ontol.DCMetaInfo;
import qedit.clients.ontol.OntologicalClass;
import qedit.clients.ontol.impl.DCMetaInfoImpl;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class Algorithm extends AbstractComponent{

    private String uri;
    private DCMetaInfo meta = new DCMetaInfoImpl();
    private ArrayList<Parameter> parameters = new ArrayList<Parameter>();
    private Set<OntologicalClass> ontologies;
    
    @Override
    public Individual asIndividual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DCMetaInfo getMeta() {
        return meta;
    }

    public void setMeta(DCMetaInfo meta) {
        this.meta = meta;
    }

    public Set<OntologicalClass> getOntologies() {
        return ontologies;
    }

    public void setOntologies(Set<OntologicalClass> ontologies) {
        this.ontologies = ontologies;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    

}