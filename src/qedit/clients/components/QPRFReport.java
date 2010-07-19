package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;
import java.util.ArrayList;
import qedit.clients.ontol.DCMetaInfo;
import qedit.clients.ontol.impl.DCMetaInfoImpl;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class QPRFReport extends AbstractComponent {

    private static final long serialVersionUID = 193847284712388L;
    /**
     * Meta-information about the report (report title, description, comments, etc)
     */
    private DCMetaInfo metaInfo = new DCMetaInfoImpl();
    /**
     * The compound for which the report is made for.
     */
    private Compound compound = new Compound();
    private ArrayList<Author> authors;
    /**
     * The model used to make the prediction for which this report is about
     */
    private Model model = new Model();
    private AdequacyInfo adequacyInfo = new AdequacyInfo();
    private String stereoFeatures;
    private String chemBioMechanisms;
    private String year;
    private String month;
    private String day;

    public QPRFReport() {
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getChemBioMechanisms() {
        return chemBioMechanisms;
    }

    public void setChemBioMechanisms(String chemBioMechanisms) {
        this.chemBioMechanisms = chemBioMechanisms;
    }

    public String getStereoFeatures() {
        return stereoFeatures;
    }

    public void setStereoFeatures(String stereoFeatures) {
        this.stereoFeatures = stereoFeatures;
    }

    public AdequacyInfo getAdequacyInfo() {
        return adequacyInfo;
    }

    public void setAdequacyInfo(AdequacyInfo adequacyInfo) {
        this.adequacyInfo = adequacyInfo;
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

    @Override
    public Individual asIndividual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
