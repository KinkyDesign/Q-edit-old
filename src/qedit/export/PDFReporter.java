package qedit.export;

import com.itextpdf.text.Paragraph;
import qedit.clients.components.QPRFReport;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class PDFReporter {

    private QPRFReport qprfReport;

    public PDFReporter() {
    }

    public PDFReporter(QPRFReport qprfReport) {
        this.qprfReport = qprfReport;
    }

    public QPRFReport getQprfReport() {
        return qprfReport;
    }

    public void setQprfReport(QPRFReport qprfReport) {
        this.qprfReport = qprfReport;
    }

    public PDFObject createPdf(){
        PDFObject pdf = new PDFObject();
        pdf.addElement(new Paragraph("Hello!"));
        return pdf;
    }

}