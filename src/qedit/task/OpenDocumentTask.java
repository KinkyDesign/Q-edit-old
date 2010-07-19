package qedit.task;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import qedit.QEditView;
import qedit.clients.components.QPRFReport;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class OpenDocumentTask extends AbstractTask {

    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JFileChooser localFileChooserWindow;

     public OpenDocumentTask() {
        super();
    }

    public javax.swing.JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public void setDesktopPane(javax.swing.JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    public JFileChooser getLocalFileChooserWindow() {
        return localFileChooserWindow;
    }

    public void setLocalFileChooserWindow(JFileChooser localFileChooserWindow) {
        this.localFileChooserWindow = localFileChooserWindow;
    }
    

    @Override
    protected Object doInBackground() throws Exception {
        try {
            setProgress(5);
            qedit.QEditApp.getView().getStatusLabel().setText("Reading Report from XML file...");
            final QPRFReport report = (QPRFReport) new com.thoughtworks.xstream.XStream().fromXML(
                    new java.io.FileInputStream(localFileChooserWindow.getSelectedFile()));
            setProgress(15);
            desktopPane.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
            setProgress(20);
            qedit.QEditApp.getView().getStatusLabel().setText("Loading Report... Please Wait!");
            setProgress(30);
            qedit.ReportInternalFrame nd = new qedit.ReportInternalFrame();
            setProgress(40);
            nd.setQprfreport(report);
            nd.synchronizeFieldsWRTReport();
            nd.setVisible(true);
            qedit.QEditApp.getView().getDesktopPane().add(nd);
            setProgress(50);
            nd.revalidate();
            nd.setLocation(new java.awt.Point(40 + 10 * QEditView.getNumOpenDocuments(), 40 + 10 * QEditView.getNumOpenDocuments()));
            setProgress(60);
            nd.setTitle(localFileChooserWindow.getSelectedFile().getName().replaceAll(".xml", ""));
            setProgress(70);
            QEditView.increaseNumOpenDocuments();
            setProgress(80);
            try {
                nd.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(OpenDocumentTask.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                desktopPane.setCursor(java.awt.Cursor.getDefaultCursor());
            }
            setProgress(90);
            qedit.QEditApp.getView().getStatusLabel().setText("Report loaded successfully");
            setProgress(100);
            return new Object();
        } catch (Exception exx) {
            desktopPane.setCursor(java.awt.Cursor.getDefaultCursor());
            qedit.QEditApp.getView().getStatusLabel().setText("Report could not be loaded!!!");
            throw exx;
        }
    }
}
