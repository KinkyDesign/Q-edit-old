package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Resource;
import java.util.Set;
import qedit.clients.ontol.OntologicalClass;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class OntTypesSpider extends Tarantula<Set<OntologicalClass>> {

    public OntTypesSpider(Resource resource, OntModel model) {
        super(resource, model);
    }

    @Override
    public Set<OntologicalClass> parse() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
