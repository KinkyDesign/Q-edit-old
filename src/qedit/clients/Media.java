package qedit.clients;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public enum Media {

    rdf_xml("application/rdf+xml"),
    smiles("chemical/x-daylight-smiles"),
    uriList("text/uri-list");
    private String name;

    private Media() {
    }

    private Media(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name != null ? name : super.toString();
    }
}
