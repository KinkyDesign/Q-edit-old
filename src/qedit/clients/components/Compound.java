package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;

/**
 * A Chemical Compound.
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class Compound extends AbstractComponent {

    private static final String CHEMICAL_NAMES_SEPARATOR = ";";

    private String iupacName;
    private String inChI;
    private String inChIKey;
    private String REACHRegistrationDate;
    private String chemicalName;
    private String smiles;
    private String casRn;
    private String einecs;
    private String uri;

    public Compound() {
    }

    public Compound(String uri) {
        this.uri = uri;
    }

    public String getREACHRegistrationDate() {
        return REACHRegistrationDate;
    }

    public void setREACHRegistrationDate(String REACHRegistrationDate) {
        this.REACHRegistrationDate = REACHRegistrationDate;
    }

    public String getInChIKey() {
        return inChIKey;
    }

    public void setInChIKey(String inChIKey) {
        this.inChIKey = inChIKey;
    }

    
    public String getEINECS() {
        return einecs;
    }

    public void setEINECS(String ecName) {
        this.einecs = ecName;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
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

    public String getSmiles() {
        return smiles;
    }

    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }
    // </editor-fold>

    public java.util.List<String> getSynonyms() {
        if (chemicalName == null) {
            return new java.util.ArrayList<String>();
        }
        java.util.List<String> list = java.util.Arrays.asList(chemicalName.split(CHEMICAL_NAMES_SEPARATOR));
        return list;
    }

    @Override
    public Individual asIndividual() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (getIupacName() != null) {
            builder.append("IUPAC Name    : ");
            builder.append(getIupacName());
            builder.append("\n");
        }
        // Display the Chemical Name only when it is different from the IUPAC Name
        if (getChemicalName() != null && !getChemicalName().equals(getIupacName())) {
            builder.append("Chemical Names... \n");
            for (String synonym : getSynonyms()) {                
                builder.append("     - ");
                builder.append(synonym.trim());
                builder.append("\n");
            }
        }
        if (casRn != null) {
            builder.append("CAS-RN        : ");
            builder.append(casRn);
            builder.append("\n");
        }
        if (getSmiles() != null) {
            builder.append("SMILES String : ");
            builder.append(getSmiles());
            builder.append("\n");
        }

        if (getEINECS() != null) {
            builder.append("EINCES        : ");
            builder.append(getEINECS());
            builder.append("\n");
        }
        if (getInChI() != null) {
            builder.append("InChI Code    : ");
            builder.append(getInChI());
            builder.append("\n");
        }
        if (getInChIKey() != null) {
            builder.append("InChI Key     : ");
            builder.append(getInChIKey());
            builder.append("\n");
        }
        if (getREACHRegistrationDate() != null) {
            builder.append("REACH Reg. Date : ");
            builder.append(getREACHRegistrationDate());
            builder.append("\n");
        }
        return new String(builder);
    }
}
