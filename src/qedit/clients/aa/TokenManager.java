package qedit.clients.aa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;


/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class TokenManager {

    private String credentialsFolder = Configuration.getStringProperty("aa.credentialsFolder");
    private String credentialsFile = String.format(credentialsFolder, ".YaqpCredentials.properties");
    private static final String yaqpEncoding = Configuration.getStringProperty("yaqp.urlEncoding");
    private static final String userNameParam = Configuration.getStringProperty("aa.usernameParam");
    private static final String passwordParam = Configuration.getStringProperty("aa.userPasswordParam");
    private String username;
    private String password;
    private URL url;

    public TokenManager() {
        YaqpSSLConfiguration.initializeSSLConnection();
        InputStream inStr = null;
        try {
            Properties props = new Properties();
            inStr = new FileInputStream(credentialsFile);
            props.load(inStr);
            username = props.getProperty(userNameParam);
            password = props.getProperty(passwordParam);
            /* Decrypt password and username */
            username = CryptoUtil.CRYPTO.decrypt(username);
            password = CryptoUtil.CRYPTO.decrypt(password);
            YaqpSSLConfiguration.initializeSSLConnection();
        } catch (IOException ex) {
            YaqpLogger.LOG.log(new Warning(TokenManager.class, "IOException while trying to read credentials from file!", ex));
        } finally {
            if (inStr != null) {
                try {
                    inStr.close();
                } catch (IOException ex) {
                    String message = "Input Stream from the credentials file : '" + credentialsFile + "' "
                            + "could not close";
                    YaqpLogger.LOG.log(new ScrewedUp(TokenManager.class, message, ex));
                    throw new RuntimeException(message, ex);
                }
            }
        }

    }

    public TokenManager(String username, String password) {
        this.username = username;
        this.password = password;
        YaqpSSLConfiguration.initializeSSLConnection();
    }

    public void setCredentialsFile(String credentialsFile) {
        this.credentialsFile = String.format(credentialsFolder, credentialsFile);
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

    public AuthenticationToken acquireToken() throws YaqpException {
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
                throw new YaqpException(Cause.AuthenticationFailed, "Authentication Failed. "
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
                throw new YaqpException(Cause.UnknownCauseOfException, "Received status code 200 and an empty token!?");
            }
            tokenId = tokenId.replaceAll("token.id=", "");
        } catch (IOException ex) {
            throw new YaqpException(Cause.ConnectionException, "Exception while trying to "
                    + "get token from " + url.toString(), ex);
        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException ex) {
                    throw new YaqpException(Cause.StreamCouldNotClose, ex);
                }
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException ex) {
                    throw new YaqpException(Cause.StreamCouldNotClose, ex);
                }
            }
        }
        AuthenticationToken t0ken = new AuthenticationToken(tokenId);
        return t0ken;
    }
}

