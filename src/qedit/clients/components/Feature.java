

package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;
import qedit.clients.ontol.DCMetaInfo;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class Feature extends AbstractComponent{

    private String uri;
    private DCMetaInfo meta;

    public Feature() {
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
