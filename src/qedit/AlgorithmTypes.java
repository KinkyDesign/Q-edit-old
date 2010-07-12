package qedit;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public enum AlgorithmTypes {

    AlgorithmType("Algorithm Type", "Generic Algorithm Type including any QSAR algorithm"),
    ApplicabilityDomain("Applicability Domain", "Appplicability Domain Algorithm"),
    MSDMTox("MDSM Tox", "MSDM Toxicological"),
    Learning("Learning", "Machine Learning Algorithm"),
    Classification("Classification", "Classification Algorithm"),
    Regression("Regression", "Regression Algorithm");
    
    private String name;
    private String description;

    private AlgorithmTypes(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
