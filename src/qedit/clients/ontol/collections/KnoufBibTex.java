package qedit.clients.ontol.collections;

import com.hp.hpl.jena.vocabulary.OWL;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import qedit.clients.ontol.Cause;
import qedit.clients.ontol.OntologicalClass;
import qedit.clients.ontol.YaqpException;
import qedit.clients.ontol.impl.OntologicalClassImpl;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class KnoufBibTex {

    private KnoufBibTex() {
    }
    public static final String NS = "http://purl.oclc.org/NET/nknouf/ns/bibtex#";
    private static OntologicalClass ms_Thing;
    private static OntologicalClass ms_Entry;
    private static OntologicalClass ms_Article;
    private static OntologicalClass ms_Book;
    private static OntologicalClass ms_Conference;
    private static OntologicalClass ms_Phdthesis;
    private static Map<String, Method> ms_methodCache;

    private synchronized static void initMethodCache() {
        if (ms_methodCache == null) {
            ms_methodCache = new HashMap<String, Method>();
            for (Method method : KnoufBibTex.class.getDeclaredMethods()) {
                if (OntologicalClass.class.equals(method.getReturnType()) && method.getParameterTypes().length == 0) {
                    ms_methodCache.put(method.getName(), method);
                }
            }
        }
    }

    public static OntologicalClass forName(String name) throws YaqpException {
        initMethodCache();
        try {
            Method method = ms_methodCache.get(name);
            if (method == null) {
                throw new YaqpException(Cause.KnoufBibTexClassNotFound, "BibTeX class : '" + name
                        + "' not found in the cache");
            }
            OntologicalClass oc = (OntologicalClass) method.invoke(null);
            return oc;
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static OntologicalClass Thing() {
        if (ms_Thing == null) {
            OntologicalClass clazz = new OntologicalClassImpl("Thing");
            clazz.setNameSpace(OWL.NS);
            clazz.getMetaInfo().setComment("All classes subclass of owl:Thing");
            ms_Thing = clazz;
        }
        return ms_Thing;
    }

    public static OntologicalClass Entry() {
        if (ms_Entry == null) {
            OntologicalClass clazz = new OntologicalClassImpl("Entry");
            clazz.getSuperClasses().add(Thing());
            clazz.setNameSpace(NS);
            clazz.getMetaInfo().setComment("Generic bibtex entry");
            clazz.getMetaInfo().setSeeAlso("http://zeitkunst.org/bibtex/0.1/");
            ms_Entry = clazz;
        }
        return ms_Entry;
    }

    public static OntologicalClass Article() {
        if (ms_Article == null) {
            OntologicalClass clazz = new OntologicalClassImpl("Article");
            clazz.getSuperClasses().add(Entry());
            clazz.setNameSpace(NS);
            clazz.getMetaInfo().setComment("An article from a journal or magazine.");
            ms_Article = clazz;
        }
        return ms_Article;
    }

    public static OntologicalClass Book() {
        if (ms_Book == null) {
            OntologicalClass clazz = new OntologicalClassImpl("Book");
            clazz.getSuperClasses().add(Entry());
            clazz.setNameSpace(NS);
            clazz.getMetaInfo().setComment("A book with an explicit publisher.");
            ms_Book = clazz;
        }
        return ms_Book;
    }

    public static OntologicalClass Conference() {
        if (ms_Conference == null) {
            OntologicalClass clazz = new OntologicalClassImpl("Conference");
            clazz.getSuperClasses().add(Entry());
            clazz.setNameSpace(NS);
            clazz.getMetaInfo().setComment("The same as INPROCEEDINGS, included for Scribe compatibility.");
            ms_Conference = clazz;
        }
        return ms_Conference;
    }

    public static OntologicalClass Phdthesis() {
        if (ms_Phdthesis == null) {
            OntologicalClass clazz = new OntologicalClassImpl("Phdthesis");
            clazz.getSuperClasses().add(Entry());
            clazz.setNameSpace(NS);
            clazz.getMetaInfo().setComment("A PhD Thesis.");
            ms_Phdthesis = clazz;
        }
        return ms_Phdthesis;
    }
}
