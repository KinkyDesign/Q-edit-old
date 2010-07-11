package qedit.clients;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class GetClient {

    private String mediaType;
    private java.net.URI uri;
    private java.net.HttpURLConnection con;
    private static int bufferSize = 4194304;
    private static String casToSmilesService = "http://ambit.uni-plovdiv.bg:8080/ambit2/query/csls/%s/smiles";

    public GetClient() {
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public java.net.URI getUri() {
        return uri;
    }

    public void setUri(java.net.URI uri) {
        this.uri = uri;
    }

    public void setUri(String uri) throws java.net.URISyntaxException {
        this.uri = new java.net.URI(uri);
    }

    private java.net.HttpURLConnection initializeConnection(final java.net.URI uri) throws ClientException {
        try {
            java.net.HttpURLConnection.setFollowRedirects(true);
            java.net.URL dataset_url = uri.toURL();
            con = (java.net.HttpURLConnection) dataset_url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            if (mediaType != null) {
                con.setRequestProperty("Accept", mediaType);
            }
            return con;
        } catch (final Exception ex) {
            throw new ClientException(ex);
        }
    }

    public java.io.InputStream getRemoteStream() throws ClientException, java.io.IOException {
        if (con == null) {
            con = initializeConnection(uri);
        }
        if (con == null) {
            throw new ClientException("Comminucation Error with the remote");
        }
        if (con.getResponseCode() == 200) {
            return new java.io.BufferedInputStream(con.getInputStream(), bufferSize);
        } else {
            return new java.io.BufferedInputStream(con.getErrorStream(), bufferSize);
        }
    }

    public int getResponseCode() throws ClientException, java.io.IOException {
        if (con == null) {
            con = initializeConnection(uri);
        }
        return con.getResponseCode();
    }

    public String getRemoteMessage() throws ClientException, java.io.IOException {
        if (con == null) {
            con = initializeConnection(uri);
        }
        java.io.InputStream is = getRemoteStream();
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(is));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return new String(sb);
    }

    public static String smilesFromCasRn(String casRn) throws ClientException, java.io.IOException {
        GetClient getter = new GetClient();
        try {
            getter.setUri(String.format(casToSmilesService, casRn));
            return getter.getRemoteMessage();
        } catch (java.net.URISyntaxException ex) {
            throw new RuntimeException("Unexpected bad-uri!");
        }
    }
}

