package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public abstract class AbstractComponent {

    public AbstractComponent() {
    }

    public OntModel asOntModel() {
        throw new UnsupportedOperationException();
    }
    
    public abstract Individual asIndividual();
}
