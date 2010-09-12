package qedit.clients.aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class TokenManager {

    private static final String yaqpEncoding = "UTF-8";
    private static final String userNameParam = "username";
    private static final String passwordParam = "password";
    private String username;
    private String password;
    private URL url;

    public TokenManager(String username, String password) {
        try {
            url = new URL("https://opensso.in-silico.ch/opensso/identity/authenticate?uri=service=openldap");
        } catch (MalformedURLException ex) {
            Logger.getLogger(TokenManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        YaqpSSLConfiguration.initializeSSLConnection();
        this.username = username;
        this.password = password;        
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOpenSsoUrl(URL url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AuthenticationToken acquireToken() throws Exception {
        String tokenId = null;
        HttpsURLConnection conn = null;
        OutputStreamWriter wr = null;
        BufferedReader rd = null;

        try {
            String data = URLEncoder.encode(userNameParam, yaqpEncoding)
                    + "=" + URLEncoder.encode(username, yaqpEncoding);
            data += "&" + URLEncoder.encode(passwordParam, yaqpEncoding)
                    + "=" + URLEncoder.encode(password, yaqpEncoding);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            if (200 != conn.getResponseCode()) {
                throw new Exception("Authentication Failed. "
                        + "Status code receienced : " + conn.getResponseCode());
            }
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                tokenId = line;
                break;
            }
            wr.close();
            rd.close();
            if (tokenId == null) {
                throw new Exception("Received status code 200 and an empty token!?");
            }
            tokenId = tokenId.replaceAll("token.id=", "");
        } catch (IOException ex) {
            throw new Exception("Exception while trying to "
                    + "get token from " + url.toString(), ex);
        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (final IOException ex) {
                    throw ex;
                }
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (final IOException ex) {
                    throw ex;
                }
            }
        }
        AuthenticationToken t0ken = new AuthenticationToken(tokenId);
        return t0ken;
    }

    public static void main(String... args) throws Exception{
        TokenManager tm = new TokenManager("Sopasakis", "xxx");
        System.out.println(tm.acquireToken().getTokenUrlEncoded());
    }
}

