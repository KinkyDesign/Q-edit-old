
package qedit.clients.components;

import com.hp.hpl.jena.datatypes.RDFDatatype;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class FeatureValue<T> {

    private T value;
    private RDFDatatype type;

    public FeatureValue(T value, RDFDatatype type) {
        this.value = value;
        this.type = type;
    }

    public RDFDatatype getType() {
        return type;
    }

    public void setType(RDFDatatype type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }



}
