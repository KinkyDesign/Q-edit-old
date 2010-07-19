package qedit.task;

import javax.swing.JFileChooser;
import qedit.QEditApp;
import qedit.ReportInternalFrame;
import qedit.export.PDFReporter;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class ExportAsPDFTask extends AbstractTask {

    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JFileChooser saveFileChooserWindow;
    private ReportInternalFrame rif;

    public ExportAsPDFTask() {
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
    protected void succeeded(Object result) {
        super.succeeded(result);
        QEditApp.getView().getStatusLabel().setText("PDF creation is successful!");
    }

    @Override
    protected Object doInBackground() throws Exception {

        java.io.File selectedFile = saveFileChooserWindow.getSelectedFile();

        if (selectedFile == null) {
            QEditApp.getView().getStatusLabel().setText("Info: Report is not saved");
            return null;
        }

        String filePath = selectedFile.getAbsolutePath();
        if (!filePath.contains(".pdf")) {
            filePath += ".pdf";
        }
        java.io.File f = new java.io.File(filePath);
        rif.updateReport();
        PDFReporter reporter = new PDFReporter(rif.getQprfreport());
        java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
        reporter.createPdf().publish(fos);
        fos.close();
        return null;
    }
}
