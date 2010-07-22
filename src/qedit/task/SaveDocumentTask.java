package qedit.task;

import com.thoughtworks.xstream.XStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import qedit.QEditApp;
import qedit.QEditView;
import qedit.ReportInternalFrame;
import qedit.Session;
import qedit.SessionHistory;
import qedit.clients.components.QPRFReport;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class SaveDocumentTask extends AbstractTask {

    public static final String preferenceSeperator = ";";
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JFileChooser saveFileChooserWindow;
    private ReportInternalFrame rif;

    public SaveDocumentTask() {
        super();
    }

    public javax.swing.JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public void setDesktopPane(javax.swing.JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    public JFileChooser getSaveFileChooserWindow() {
        return saveFileChooserWindow;
    }

    public void setSaveFileChooserWindow(JFileChooser saveFileChooserWindow) {
        this.saveFileChooserWindow = saveFileChooserWindow;
    }

    public ReportInternalFrame getRif() {
        return rif;
    }

    public void setRif(ReportInternalFrame rif) {
        this.rif = rif;
    }

    @Override
    protected Object doInBackground() throws Exception {

        java.io.File selectedFile = rif.getRelatedFile() == null
                ? saveFileChooserWindow.getSelectedFile() : rif.getRelatedFile();

        if (selectedFile == null) {
            QEditApp.getView().getStatusLabel().setText("Info: Report is not saved");
            return null;
        }

        String filePath = selectedFile.getAbsolutePath();
        if (!filePath.contains(".report")) {
            filePath += ".report";
        }

        java.io.File f = new java.io.File(filePath);
        rif.updateReport();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject((QPRFReport) rif.getQprfreport());

        rif.setTitle(selectedFile.getName().replaceAll(".report", ""));
        QEditApp.getView().getStatusLabel().setText("Document Saved");


        Preferences prefs = Preferences.userRoot();
        String sessionHistoryFromPrefsXML = prefs.get("qedit_session_hitory", null);
        SessionHistory sh = null;

        if (sessionHistoryFromPrefsXML == null) {
            sh = new SessionHistory();
        } else {
            sh = (SessionHistory) new XStream().fromXML(sessionHistoryFromPrefsXML);
        }
        Session session = new Session(filePath, rif.getQprfreport().getCompound().getUri(), rif.getQprfreport().getModel().getUri());
        session.setName(rif.getTitle());
        sh.push(session);
        prefs.put("qedit_session_hitory", new XStream().toXML(sh));
        QEditView.setSessionHistory(sh);
        QEditApp.getView().refreshSessionsFromPreferences();
        return null;
    }
}
