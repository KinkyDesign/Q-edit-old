package qedit.export;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class PDFObject {

    private ArrayList<Element> elements = new ArrayList<Element>();
    private String subject = "OpenTox Entity Representation";
    private String pdfAuthor = "OpenTox NTUA WebServices";
    private String pdfCreator = "OpenTox NTUA Serivces";
    private String pdfTitle = "OpenTox Entity Representation";
    private String pdfKeywords = "";

    public PDFObject() {
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void setPdfAuthor(final String pdfAuthor) {
        this.pdfAuthor = pdfAuthor;
    }

    public void setPdfCreator(final String pdfCreator) {
        this.pdfCreator = pdfCreator;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public void setPdfTitle(final String pdfTitle) {
        this.pdfTitle = pdfTitle;
    }

    public void setPdfKeywords(String pdfKeywords) {
        this.pdfKeywords = pdfKeywords;
    }

    public void publish(OutputStream stream) throws Exception {
        if (stream == null) {
            throw new NullPointerException("Cannot public pdf to a null output stream");
        }
        try {
            /*
             * Initialize the document...
             */
            Document doc = new Document();
            try {
                PdfWriter.getInstance(doc, stream);
            } catch (ClassCastException ex) {
                throw new ClassCastException("The stream you provided is not a valid output stream");
            }
            doc.open();
            /*
             * Meta-information about the document...
             */
            doc.addAuthor(pdfAuthor);
            doc.addCreationDate();
            doc.addProducer();
            doc.addSubject(subject);
            doc.addCreator(pdfCreator);
            doc.addTitle(pdfTitle);
            doc.addKeywords(pdfKeywords);
            doc.addHeader("License", "GNU GPL v3");

            doc.add(new Paragraph("\n\n\n"));
            for (Element e : elements) {
                doc.add(e);
            }
            doc.close();
        } catch (DocumentException ex) {
            String message = "Error while generating PDF representation.";
        }

    }
}
