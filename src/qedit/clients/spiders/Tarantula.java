package qedit.clients.spiders;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import qedit.clients.ClientException;
import qedit.clients.ontol.OntologicalClass;
import qedit.clients.ontol.collections.OTAlgorithmTypes;
import qedit.clients.ontol.collections.OTClasses;
import qedit.clients.ontol.collections.OTObjectProperties;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public abstract class Tarantula<Result> implements Closeable {

    protected Resource resource;
    protected OntModel model;

    public Tarantula(Resource resource, OntModel model) {
        this.resource = resource;
        this.model = model;
    }

    public Tarantula() {
    }

    public abstract Result parse() throws ClientException;

    protected String retrieveProp(Property prop) {
//        if (prop.equals(OTObjectProperties.hasSource().asObjectProperty(model))) {
//
//            StmtIterator it = model.listStatements(new SimpleSelector(resource, prop, (RDFNode) null));
//            if (it.hasNext()) {
//                try {
//                    return (it.nextStatement().getObject().as(Resource.class).getURI());
//                } finally {
//                    it.close();
//                }
//            }
//
//        } else if (prop.equals(OWL.sameAs)) {
//
//        } else {
            StmtIterator it = model.listStatements(new SimpleSelector(resource, prop, (RDFNode) null));
            if (it.hasNext()) {
                try {
                    RDFNode node = it.nextStatement().getObject();
                    if(node.isLiteral()){
                        return (node.as(Literal.class).getString());
                    }else if(node.isResource()){
                        return (node.as(Resource.class).getURI());
                    }
                } finally {
                    it.close();
                }
            }
        //}
        return null;
    }

    protected ArrayList<String> retrieveProps(Property prop) {
        ArrayList<String> props = new ArrayList<String>();

        StmtIterator it = model.listStatements(
                new SimpleSelector(resource, prop, (Literal) null));
        while (it.hasNext()) {
            try {
                props.add(it.nextStatement().getObject().as(Literal.class).getString());
            } finally {
                it.close();
            }
        }
        return props;
    }

    protected Set<OntologicalClass> getOTATypes(Resource currentResource) {
        Set<OntologicalClass> ontClasses = new HashSet<OntologicalClass>();

        StmtIterator classIt = model.listStatements(
                new SimpleSelector(currentResource, RDF.type,
                (RDFNode) null));
        Set<OntClass> ontClassSet = new HashSet<OntClass>();
        while (classIt.hasNext()) {
            OntClass tempClass = classIt.nextStatement().getObject().as(OntClass.class);
            if (tempClass.getNameSpace().equals(OTAlgorithmTypes.NS)) {
                ontClassSet.add(tempClass);
                ontClassSet = getSuperTypes(ontClassSet);
            }
        }
        for (OntClass oc : ontClassSet) {
            ontClasses.add(OTAlgorithmTypes.forName(oc.getLocalName()));
        }
        return ontClasses;
    }

    protected Set<OntologicalClass> getFeatureTypes(Resource currentResource) {
        Set<OntologicalClass> ontClasses = new HashSet<OntologicalClass>();

        StmtIterator classIt = model.listStatements(
                new SimpleSelector(currentResource, RDF.type,
                (RDFNode) null));
        Set<OntClass> ontClassSet = new HashSet<OntClass>();
        while (classIt.hasNext()) {
            OntClass tempClass = classIt.nextStatement().getObject().as(OntClass.class);
            if (tempClass.getNameSpace().equals(OTClasses.NS)) {
                ontClassSet.add(tempClass);
                ontClassSet = getSuperTypes(ontClassSet);
            }
        }
        for (OntClass oc : ontClassSet) {
            ontClasses.add(OTClasses.forName(oc.getLocalName()));
        }
        return ontClasses;
    }

    private Set<OntClass> getSuperTypes(Set<OntClass> ontClasses) {
        Set<OntClass> newSet = new HashSet<OntClass>();
        newSet.addAll(ontClasses);
        for (OntClass oc : ontClasses) {
            newSet.addAll(oc.listSuperClasses().toSet());
        }
        if (!ontClasses.equals(newSet)) {
            newSet.addAll(getSuperTypes(newSet));
        }
        return newSet;
    }

    /**
     * Closes the ontological model used by the Spider (if any) and releases all
     * resources it holds. Closing the Spider is considered good practise and prefered
     * comparing to leaving it to the finalizer.
     */
    @Override
    public void close() {
        if (model != null) {
            model.close();
        }
    }

 
}
