package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;
import qedit.clients.ontol.DCMetaInfo;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class Parameter extends AbstractComponent{

    @Override
    public Individual asIndividual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public enum ParameterScope{
        OPTIONAL,
        MANDATORY,
        INTERNAL;
    }
    private String value;
    private ParameterScope scope;
    private DCMetaInfo meta;

    public DCMetaInfo getMeta() {
        return meta;
    }

    public void setMeta(DCMetaInfo meta) {
        this.meta = meta;
    }

    public ParameterScope getScope() {
        return scope;
    }

    public void setScope(ParameterScope scope) {
        this.scope = scope;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
}