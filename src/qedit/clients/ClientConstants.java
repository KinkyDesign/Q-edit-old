package qedit.clients;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class ClientConstants {

    /*
     * --- Image Services ---
     */
    public static final String AMBIT_CDK_IMAGE_SERVICE = "http://ambit.uni-plovdiv.bg:8080/ambit2/depict/cdk?search=%s";
    public static final String AMBIT_DAYLIGHT_IMAGE_SERVICE = "http://ambit.uni-plovdiv.bg:8080/ambit2/depict/daylight?search=%s";
    public static final String AMBIT_CACTVS_IMAGE_SERVICE = "http://ambit.uni-plovdiv.bg:8080/ambit2/depict/cactvs?search=%s";
    public static final String IDEA_CDK_IMAGE_SERVICE = "http://apps.ideaconsult.net:8080/ambit2/depict/cdk?search=%s";
    public static final String IDEA_DAYLIGHT_IMAGE_SERVICE = "http://apps.ideaconsult.net:8080/ambit2/depict/daylight?search=%s";
    public static final String IDEA_CACTVS_IMAGE_SERVICE = "http://apps.ideaconsult.net:8080/ambit2/depict/cactvs?search=%s";
    public static final String ACCEPT_IMAGE_URL_PARAMETER = "%s?accept-header=image/png";
    /*
     * --- Compound Services ---
     */
    public static final String AMBIT_SMILES_SERVICE = "http://ambit.uni-plovdiv.bg:8080/ambit2/query/csls/%s/smiles";
    public static final String AMBIT_COMPOUND_LOOKUP_SERVICE = "http://apps.ideaconsult.net:8080/ambit2/query/compound/%s";
    /*
     * --- Similarity Search ---
     */
    public static final String AMBIT_SIMILARITY = "http://ambit.uni-plovdiv.bg:8080/ambit2/query/similarity?search=%s&threshold=%s";
    /**
     * --- Configurable Parameters ---
     */
    private static String imageService = AMBIT_CACTVS_IMAGE_SERVICE;
    private static String casToSmilesService = AMBIT_SMILES_SERVICE;
    private static String compoundLookupService = AMBIT_COMPOUND_LOOKUP_SERVICE;

    public static String getCompoundLookupService() {
        return compoundLookupService;
    }

    public static void setCompoundLookupService(String smilesToUriService) {
        ClientConstants.compoundLookupService = smilesToUriService;
    }

    public static void setCasToSmilesService(String casToSmilesService) {
        ClientConstants.casToSmilesService = casToSmilesService;
    }

    public static String getCasToSmilesService() {
        return casToSmilesService;
    }

    public static String getImageService() {
        return imageService;
    }

    public static void setImageService(String imageService) {
        ClientConstants.imageService = imageService;
    }
}
