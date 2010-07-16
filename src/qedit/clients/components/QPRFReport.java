package qedit.clients.components;

import qedit.clients.ontol.DCMetaInfo;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class QPRFReport {

    /**
     * Meta-information about the report (report title, description, comments, etc)
     */
    private DCMetaInfo metaInfo;
    /**
     * The compound for which the report is made for.
     */
    private Compound compound;
    /**
     * The model used to make the prediction for which this report is about
     */
    private Model model;

    public QPRFReport() {
    }

    public Compound getCompound() {
        return compound;
    }

    public void setCompound(Compound compound) {
        this.compound = compound;
    }

    public DCMetaInfo getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(DCMetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
