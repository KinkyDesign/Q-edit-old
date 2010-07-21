/*
 * QEditApp.java
 */
package qedit;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class QEditApp extends SingleFrameApplication {

    public static qedit.SplashScreen splash;
    private static QEditView theView;
    private static int magnificationMode = java.awt.Image.SCALE_DEFAULT;

    public static void setMagnificationMode(int magnificationMode) {
        QEditApp.magnificationMode = magnificationMode;
    }

    public static int getMagnificationMode() {
        return magnificationMode;
    }

    public static QEditView getView() {
        return theView;
    }

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        theView = new QEditView(this);
        show(theView);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of QEditApp
     */
    public static QEditApp getApplication() {
        return Application.getInstance(QEditApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) throws BackingStoreException {
//        Preferences prefs = Preferences.userRoot();
//        prefs.clear();
        
        splash = new qedit.SplashScreen("resources/splash.png", null, 5000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(QEditApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch(QEditApp.class, args);
    }
}
