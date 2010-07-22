package qedit;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class Session {

    private String name;
    private String file;
    private String compoundUri;
    private String modelUri;

    public Session() {
    }

    public Session(Session other){
        this(other.file,other.compoundUri,other.modelUri);
    }

    public Session(String file, String compoundUri, String modelUri) {
        this.file = file;
        this.compoundUri = compoundUri;
        this.modelUri = modelUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompoundUri() {
        return compoundUri;
    }

    public void setCompoundUri(String compoundUri) {
        this.compoundUri = compoundUri;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getModelUri() {
        return modelUri;
    }

    public void setModelUri(String modelUri) {
        this.modelUri = modelUri;
    }



    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.file != null ? this.file.hashCode() : 0);
        hash = 53 * hash + (this.compoundUri != null ? this.compoundUri.hashCode() : 0);
        hash = 53 * hash + (this.modelUri != null ? this.modelUri.hashCode() : 0);
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Session other = (Session) obj;
        if ((this.file == null) ? (other.file != null) : !this.file.equals(other.file)) {
            return false;
        }
        if ((this.compoundUri == null) ? (other.compoundUri != null) : !this.compoundUri.equals(other.compoundUri)) {
            return false;
        }
        if ((this.modelUri == null) ? (other.modelUri != null) : !this.modelUri.equals(other.modelUri)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }




}
