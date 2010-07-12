package qedit.clients.ontol.impl;

import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.impl.OntModelImpl;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.vocabulary.DC;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class SimpleOntModelImpl extends OntModelImpl {

    public SimpleOntModelImpl() {
        super(OntModelSpec.OWL_DL_MEM);
        Map<String , String> map = new HashMap<String, String>();
        map.put("ota", "http://www.opentox.org/algorithmTypes.owl/#");
        map.put("ot", qedit.clients.ontol.collections.OTClasses.NS);
        map.put("dc", DC.NS);
        map.put("bibtex", qedit.clients.ontol.collections.KnoufBibTex.NS);
        map.put("yaqp","http://opentox.ntua.gr:3000");
        setNsPrefixes(map);
    }

    public SimpleOntModelImpl(OntModelSpec spec) {
        super(spec);
    }

    public SimpleOntModelImpl(OntModelSpec spec, Model model) {
        super(spec, model);
    }



}