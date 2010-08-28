package qedit.clients.aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;


/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class AuthenticationToken {

    private String token;
    private long creationStamp = -1;
    private static final long tokenLifeTime = 2 * 3600 * 1000L; // 2hrs
    private static final String aaService = "https://opensso.in-silico.ch/opensso/identity/%s";
    private static final String tokenValidationUrl = String.format(aaService, "isTokenValid");
    private static final String logoutUrl = String.format(aaService, "logout");
    private static final String userInfoUrl = String.format(aaService, "attributes");
    private static final String subjectIdHeader = "subjectid";
    private static final String tokenIdHeader = "tokenid";
    private static final String encoding = "UTF-8";

    public AuthenticationToken() {
        YaqpSSLConfiguration.initializeSSLConnection();
    }

    public AuthenticationToken(String token) {
        this.creationStamp = System.currentTimeMillis();
        this.token = token;
    }

    public enum TokenStatus {

        ACTIVE,
        STALE,
        ROTTEN;
    }

    public TokenStatus getStatus() {
        if (creationStamp == -1) {
            return TokenStatus.ROTTEN;
        } else if ((System.currentTimeMillis() - creationStamp) < tokenLifeTime) {
            return TokenStatus.ACTIVE;
        } else {
            return TokenStatus.STALE;
        }
    }

    public String getToken() {
        return token;
    }

    public String getTokenUrlEncoded() {
        try {
            return URLEncoder.encode(token, encoding);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Bad encoding UTF-8 ?");
        }
    }

    public long getCreationStamp() {
        return creationStamp;
    }

    public Date getCreationDate() {
        return new Date(creationStamp);
    }

    public void setToken(String token) {
        this.creationStamp = System.currentTimeMillis();
        this.token = token;
    }

    public void invalidate()  throws Exception {
        HttpURLConnection conn = null;
        OutputStreamWriter wr = null;
        BufferedReader rd = null;
        try {
            URL url = new URL(logoutUrl);
            String data = URLEncoder.encode(subjectIdHeader, encoding) + "=" + getTokenUrlEncoded();
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                break;
            }
            wr.close();
            rd.close();

        } catch (IOException ex) {
            String message = "IOException while trying to invalidate a token";
            throw new Exception(message, ex);
        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException ex) {
                    String message = "IOException while trying to invalidate a token: The "
                            + "outputStreamWriter seems to be unable to post data to the remote.";
                    throw new Exception(message, ex);
                }
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException ex) {
                    String message = "IOException while trying "
                            + "to invalidate a token: BufferedReader (from the remote stream) could not close.";
                    throw new Exception(message, ex);
                }
            }
        }
    }

    public boolean validate() throws Exception {
        HttpURLConnection conn = null;
        OutputStreamWriter wr = null;
        BufferedReader rd = null;

        try {
            URL url = new URL(tokenValidationUrl);
            String data = URLEncoder.encode(tokenIdHeader, encoding) + "=" + getTokenUrlEncoded();
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            if (401 == conn.getResponseCode()) {
                return false;
            }

            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                break;
            }
            wr.close();
            rd.close();
            if (line == null) {
                return false;
            } else {
                line = line.replaceAll("boolean=", "");
                if (line != null && line.equals("true")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            String message = "IOException while trying to validate a token";
            throw new Exception(message, ex);
        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException ex) {
                    String message = "IOException while trying to validate a token: The "
                            + "outputStreamWriter seems to be unable to post data to the remote.";
                    throw new Exception(message, ex);
                }
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException ex) {
                    String message = "IOException while trying "
                            + "to validate a token: BufferedReader (from the remote stream) could not close.";
                    throw new Exception(message, ex);
                }
            }
        }
        return false;
    }



    @Override
    public String toString() {
        String s = "-- Token : start --\n";
        s += "token id  : " + token + "\n";
        s += "timestamp : " + getCreationDate() + "\n";
        s += "status    : " + getStatus() + "\n";
        s += "-- Token : end --";
        return s;
    }


}
