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

}