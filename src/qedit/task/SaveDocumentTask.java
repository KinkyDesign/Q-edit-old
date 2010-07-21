package qedit.task;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import qedit.QEditApp;
import qedit.QEditView;
import qedit.ReportInternalFrame;

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

        java.io.File selectedFile = saveFileChooserWindow.getSelectedFile();

        if (selectedFile == null) {
            QEditApp.getView().getStatusLabel().setText("Info: Report is not saved");
            return null;
        }
        String filePath = selectedFile.getAbsolutePath();
        if (!filePath.contains(".xml")) {
            filePath += ".xml";
        }
        java.io.File f = new java.io.File(filePath);
        rif.updateReport();
        try {
            com.thoughtworks.xstream.XStream xs = new com.thoughtworks.xstream.XStream();
            xs.toXML(rif.getQprfreport(), new java.io.FileOutputStream(f));
        } catch (java.io.IOException ex) {
            Logger.getLogger(QEditView.class.getName()).log(Level.SEVERE, null, ex);
        }
        rif.setTitle(selectedFile.getName().replaceAll(".xml", ""));
        QEditApp.getView().getStatusLabel().setText("Document Saved");

        Preferences prefs = Preferences.userRoot();
        int recyclePosition = prefs.getInt("recyclePosition", 0);
        prefs.put("qedit-report" + recyclePosition,                
                 rif.getTitle() + preferenceSeperator
                +"Model URI: "+ rif.getQprfreport().getModel().getUri() + preferenceSeperator
                +"Compound URI: "+ rif.getQprfreport().getCompound().getUri() + preferenceSeperator
                +"Filepath: "+ filePath + preferenceSeperator);
        prefs.putInt("recyclePosition", recyclePosition < 4 ? recyclePosition + 1 : 0);

        QEditApp.getView().refreshSessions();
        return null;
    }
}
