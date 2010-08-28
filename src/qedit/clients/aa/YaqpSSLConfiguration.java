package qedit.clients.aa;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class YaqpSSLConfiguration {

    /*
     * IMPORTANT: You have to add the certificate from opensso.in-silico.ch
     * to your $JAVA_HOME. To do this, run the main method in InstallCert (this
     * is provided by Sun). This will produce a file (ignore the exception messages).
     * Put this file in
     */

    private static boolean isSslInitialized = false;
    private static final String openSsoHost = Configuration.getProperties().getProperty("aa.ssoHost");

    public static void initializeSSLConnection(){
        if (!isSslInitialized){
            initSsl();
        }
    }


    private static void initSsl(){
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        try {
            sc.init(null, null, new SecureRandom());
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HostnameVerifier hv = new HostnameVerifier() {

            @Override
            public boolean verify(String urlHostName, SSLSession session) {
                /* This is to avoid spoofing */
                return (urlHostName.equals(session.getPeerHost())
                        && urlHostName.equals(openSsoHost));
            }
        };

        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        isSslInitialized = true;
    }

}