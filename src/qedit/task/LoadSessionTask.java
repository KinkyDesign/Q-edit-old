package qedit.task;

import java.beans.PropertyVetoException;
import java.io.IOException;
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

    private java.io.InputStream is = null;
    private java.io.ObjectInputStream ois = null;
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
    protected void finished() {
        super.finished();
        if (is != null) {
            try {
                is.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (ois != null) {
            try {
                ois.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    protected Object doInBackground() throws Exception {
        is = new java.io.FileInputStream(new java.io.File(fileName));
        ois = new java.io.ObjectInputStream(is);
        final QPRFReport report = (QPRFReport) ois.readObject();
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
        nd.setTitle(fileName.replaceAll(".report", ""));
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
