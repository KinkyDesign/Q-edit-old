package qedit.clients.components;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class AdequacyInfo implements java.io.Serializable{

    private String regulatoryPurpose;
    private String regulatoryInterpretation;
    private String outcome;
    private String conclusion;

    public AdequacyInfo() {
    }

    public boolean isEmpty(){
        return (regulatoryPurpose==null||(regulatoryPurpose!=null&& regulatoryPurpose.isEmpty()))&&
                (regulatoryInterpretation==null||(regulatoryInterpretation!=null&& regulatoryInterpretation.isEmpty()))&&
                (outcome==null||(outcome!=null&& outcome.isEmpty()))&&
                (conclusion==null||(conclusion!=null&& conclusion.isEmpty()));
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getRegulatoryInterpretation() {
        return regulatoryInterpretation;
    }

    public void setRegulatoryInterpretation(String regulatoryInterpretation) {
        this.regulatoryInterpretation = regulatoryInterpretation;
    }

    public String getRegulatoryPurpose() {
        return regulatoryPurpose;
    }

    public void setRegulatoryPurpose(String regulatoryPurpose) {
        this.regulatoryPurpose = regulatoryPurpose;
    }

    


}