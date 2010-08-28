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
    private String considerationsOnAnalogues;
    private String year;
    private String month;
    private String day;
    private String metabolicDomain;
    private String structuralDomain;
    private String descriptorDomain;
    private String mechanismDomain;
    private Dataset descriptors;
    private String passphrase;

    public QPRFReport() {
    }

    public String getDescriptorDomain() {
        return descriptorDomain;
    }

    public void setDescriptorDomain(String descriptorDomain) {
        this.descriptorDomain = descriptorDomain;
    }

    public String getMechanismDomain() {
        return mechanismDomain;
    }

    public void setMechanismDomain(String mechanismDomain) {
        this.mechanismDomain = mechanismDomain;
    }

    public String getMetabolicDomain() {
        return metabolicDomain;
    }

    public void setMetabolicDomain(String metabolicDomain) {
        this.metabolicDomain = metabolicDomain;
    }

    public String getStructuralDomain() {
        return structuralDomain;
    }

    public void setStructuralDomain(String structuralDomain) {
        this.structuralDomain = structuralDomain;
    }

    public String getConsiderationsOnAnalogues() {
        return considerationsOnAnalogues;
    }

    public void setConsiderationsOnAnalogues(String considerationsOnAnalogues) {
        this.considerationsOnAnalogues = considerationsOnAnalogues;
    }

    public Dataset getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(Dataset descriptors) {
        this.descriptors = descriptors;
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

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    @Override
    public Individual asIndividual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
