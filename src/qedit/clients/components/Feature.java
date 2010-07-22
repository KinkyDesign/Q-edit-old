

package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;
import java.util.Set;
import qedit.clients.ontol.DCMetaInfo;
import qedit.clients.ontol.OntologicalClass;
import qedit.clients.ontol.impl.DCMetaInfoImpl;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class Feature extends AbstractComponent{

    private String uri;
    private DCMetaInfo meta = new DCMetaInfoImpl();
    private Set<OntologicalClass> ontologies;
    private String units;

    public Feature() {
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Set<OntologicalClass> getOntologies() {
        return ontologies;
    }

    public void setOntologies(Set<OntologicalClass> ontologies) {
        this.ontologies = ontologies;
    }

    public DCMetaInfo getMeta() {
        return meta;
    }

    public void setMeta(DCMetaInfo meta) {
        this.meta = meta;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public Individual asIndividual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
