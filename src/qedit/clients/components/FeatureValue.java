
package qedit.clients.components;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.thoughtworks.xstream.XStream;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class FeatureValue<T> implements java.io.Serializable{

    private T value;
    private String type;

    public FeatureValue(T value, XSDDatatype type) {
        this.value = value;
        XStream xs = new XStream();
        this.type = xs.toXML(type);
    }

    public RDFDatatype getType() {
        return (RDFDatatype)new XStream().fromXML(type);
    }

    public void setType(XSDDatatype type) {
        XStream xs = new XStream();
        this.type = xs.toXML(type);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }



}
