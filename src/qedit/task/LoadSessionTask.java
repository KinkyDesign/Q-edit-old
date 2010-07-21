package qedit.task;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import qedit.QEditView;
import qedit.clients.components.QPRFReport;

/**
 *
 * @author Charalampos Chomenides
 * @author Pantelis Sopasakis
 */
public class LoadSessionTask extends AbstractTask {

    private String fileName;

    public LoadSessionTask() {
        super();
    }

    public LoadSessionTask(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected Object doInBackground() throws Exception {
        final QPRFReport report = (QPRFReport) new com.thoughtworks.xstream.XStream().fromXML(
                new java.io.FileInputStream(fileName));
        setProgress(15);

        qedit.ReportInternalFrame nd = new qedit.ReportInternalFrame();
        setProgress(40);
        nd.setQprfreport(report);
        nd.synchronizeFieldsWRTReport();
        nd.setVisible(true);
        qedit.QEditApp.getView().getDesktopPane().add(nd);

        nd.revalidate();
            nd.setLocation(new java.awt.Point(40 + 10 * QEditView.getNumOpenDocuments(), 40 + 10 * QEditView.getNumOpenDocuments()));
            setProgress(60);
            nd.setTitle(fileName.replaceAll(".xml", ""));
            setProgress(70);
            QEditView.increaseNumOpenDocuments();
            setProgress(80);
            try {
                nd.setSelected(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(OpenDocumentTask.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
           //     desktopPane.setCursor(java.awt.Cursor.getDefaultCursor());
            }
            setProgress(90);
            qedit.QEditApp.getView().getStatusLabel().setText("Report loaded successfully");
            setProgress(100);

        return null;
    }
}
