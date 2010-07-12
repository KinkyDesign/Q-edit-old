package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class Compound extends AbstractComponent {

    private String iupacName;
    private String smiles;
    private String generatedMW;
    private String inChI;
    private String casRn;
    private String molFormula;
    private String ecName;
    private String uri;

    public Compound() {
    }

    public Compound(String uri) {
        this.uri = uri;
    }

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    // DO NOT MODIFY!
    // <editor-fold defaultstate="collapsed" desc="Getters and Settters">
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCasRn() {
        return casRn;
    }

    public void setCasRn(String casRn) {
        this.casRn = casRn;
    }

    public String getGeneratedMW() {
        return generatedMW;
    }

    public void setGeneratedMW(String generatedMW) {
        this.generatedMW = generatedMW;
    }

    public String getInChI() {
        return inChI;
    }

    public void setInChI(String inChI) {
        this.inChI = inChI;
    }

    public String getIupacName() {
        return iupacName;
    }

    public void setIupacName(String iupacName) {
        this.iupacName = iupacName;
    }

    public String getMolFormula() {
        return molFormula;
    }

    public void setMolFormula(String molFormula) {
        this.molFormula = molFormula;
    }

    public String getSmiles() {
        return smiles;
    }

    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }
    // </editor-fold>
   

    @Override
    public Individual asIndividual() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (casRn!=null){
            builder.append("CAS-RN        : ");
            builder.append(casRn);
            builder.append("\n");
        }
        if (getIupacName()!=null){
            builder.append("IUPAC Name    : ");
            builder.append(getIupacName());
            builder.append("\n");
        }
        if (getSmiles()!=null){
            builder.append("SMILES String : ");
            builder.append(getSmiles());
            builder.append("\n");
        }
        if (getMolFormula()!=null){
            builder.append("Mol. Formula  : ");
            builder.append(getMolFormula());
            builder.append("\n");
        }
        if (getGeneratedMW()!=null){
            builder.append("Mol. Weight   : ");
            builder.append(getGeneratedMW());
            builder.append("\n");
        }
        if (getInChI()!=null){
            builder.append("InChI Code    : ");
            builder.append(getInChI());
            builder.append("\n");
        }
        return new String(builder);
    }

}
