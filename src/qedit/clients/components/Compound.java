package qedit.clients.components;

import com.hp.hpl.jena.ontology.Individual;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import qedit.ReportInternalFrame;
import qedit.clients.ClientConstants;
import qedit.clients.ClientException;
import qedit.clients.GetClient;
import qedit.clients.Media;
import qedit.clients.ontol.DCMetaInfo;
import qedit.clients.spiders.CompoundSpider;

/**
 * A Chemical Compound.
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class Compound extends AbstractComponent {

    private static final String CHEMICAL_NAMES_SEPARATOR = ";";
    private static final String SMILES_SEPARATOR = ",";
    private String iupacName;
    private String inChI;
    private String inChIKey;
    private String REACHRegistrationDate;
    private String chemicalName;
    private String smiles;
    private String casRn;
    private String einecs;
    private String uri;
    private DCMetaInfo meta;
    private ImageIcon userIcon = null;
    private FeatureValue<String> experimentalValue;
    private FeatureValue<String> predictedValue;
    private java.util.List<String> synonyms = new java.util.ArrayList<String>();
    private Set<String> conformers = new HashSet<String>();
    private List<Compound> structuralAnalogues = new ArrayList<Compound>();

    public Compound() {
    }

    public Compound(String uri) {
        this.uri = uri;
    }

    public String getEinecs() {
        return einecs;
    }

    public void setEinecs(String einecs) {
        this.einecs = einecs;
    }

    public ImageIcon getUserIcon() {
        if (userIcon == null) {
            ImageIcon icon = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(ReportInternalFrame.class).getImageIcon("structureImage.icon");
            return icon;
        } else {
        }
        return userIcon;
    }

    public void setUserIcon(ImageIcon userIcon) {
        this.userIcon = userIcon;
    }

    // DO NOT MODIFY!
    // <editor-fold defaultstate="collapsed" desc="Getters and Settters">
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
        this.smiles = smiles.split(SMILES_SEPARATOR)[0];
    }

    public DCMetaInfo getMeta() {
        return meta;
    }

    public void setMeta(DCMetaInfo meta) {
        this.meta = meta;
    }

    // </editor-fold>
    public java.util.List<String> getSynonyms() {
        if (synonyms == null || synonyms.isEmpty()) {
            if (chemicalName == null) {
                return new java.util.ArrayList<String>();
            }
            synonyms = java.util.Arrays.asList(chemicalName.split(CHEMICAL_NAMES_SEPARATOR));
        }
        return synonyms;
    }

    public Set<String> getConformers() {
        return conformers;
    }

    public void setConformers(Set<String> conformers) {
        this.conformers = conformers;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public javax.swing.ImageIcon getDepiction() throws ClientException {
        try {
            if (smiles != null) {
                String smilesUrlEncoded = java.net.URLEncoder.encode(getSmiles(), "UTF-8");
                String url = String.format(ClientConstants.getImageService(), smilesUrlEncoded);
                try {
                    return new javax.swing.ImageIcon(new URL(url));
                } catch (MalformedURLException ex) {
                    throw new ClientException("Depiction not possible", ex);
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Compound.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        return null;
    }

    public javax.swing.ImageIcon getImage() throws ClientException {
        try {
            if (getUri() != null) {
                java.net.URL url = new java.net.URL(String.format(ClientConstants.ACCEPT_IMAGE_URL_PARAMETER, getUri()));
                return new javax.swing.ImageIcon(url);
            } else {
            }
        } catch (java.net.MalformedURLException ex) {
            throw new ClientException("No Image Available", ex);
        }
        return null;
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

    public List<Compound> updateSimilar(double similarity) throws ClientException {
        structuralAnalogues.clear();
        try {
            String similarityUri = String.format(ClientConstants.AMBIT_SIMILARITY, URLEncoder.encode(getSmiles(), "UTF-8"), Double.toString(similarity));
            GetClient client = new GetClient();
            client.setUri(similarityUri);
            client.setMediaType(Media.uriList);
            try {
                List<String> similarCompounds = client.getUriList();
                for (String compUri : similarCompounds) {
                    CompoundSpider cSpider = new CompoundSpider(compUri);
                    structuralAnalogues.add(cSpider.parse());
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } catch (URISyntaxException ex) {
            Logger.getLogger(Compound.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        return structuralAnalogues;
    }

    public List<Compound> getStructuralAnalogues() {
        return structuralAnalogues;
    }

    public void setStructuralAnalogues(List<Compound> structuralAnalogues) {
        this.structuralAnalogues = structuralAnalogues;
    }

    public boolean addStructuralAnalogue(Compound e) {
        return structuralAnalogues.add(e);
    }

    public FeatureValue<String> getExperimentalValue() {
        return experimentalValue;
    }

    public void setExperimentalValue(FeatureValue<String> experimentalValue) {
        this.experimentalValue = experimentalValue;
    }

    public FeatureValue<String> getPredictedValue() {
        return predictedValue;
    }

    public void setPredictedValue(FeatureValue<String> predictedValue) {
        this.predictedValue = predictedValue;
    }

    public boolean isImageAvailable() {
        return userIcon != null;
    }

}

