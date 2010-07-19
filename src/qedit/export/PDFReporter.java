package qedit.export;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import qedit.clients.components.Compound;
import qedit.clients.components.QPRFReport;

/**
 *
 * @author Pantelis Sopasakis
 * @author Charalampos Chomenides
 */
public class PDFReporter {

    private static final String NOT_AVAILABLE = "N/A";
    private QPRFReport qprfReport;
    private static final int TEXT_SIZE = 12;
    private static final int HEADING_SIZE = 18;
    private static final Font HEADING_FONT = FontFactory.getFont(FontFactory.TIMES_ROMAN, HEADING_SIZE, Font.BOLD, new BaseColor(java.awt.Color.BLUE));
    private static final Font BOLD_FONT = FontFactory.getFont(FontFactory.TIMES_ROMAN, TEXT_SIZE, Font.BOLD);
    private static final Font NORMAL_FONT = FontFactory.getFont(FontFactory.TIMES_ROMAN, TEXT_SIZE, Font.NORMAL);

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

    public PDFObject createPdf() {
        PDFObject pdf = new PDFObject();

        Paragraph heading = new Paragraph(qprfReport.getMetaInfo().getTitle(),
                HEADING_FONT);
        heading.setAlignment(Element.ALIGN_CENTER);
        pdf.addElement(heading);
        pdf.addElement(new Paragraph(Chunk.NEWLINE));

        Paragraph firstParagraph = new Paragraph();
        firstParagraph.add(new Chunk("The adequacy of a prediction depends on the following conditions: a)", NORMAL_FONT));
        firstParagraph.add(new Chunk("the (Q)SAR model is scientifically valid: ", BOLD_FONT));
        firstParagraph.add(new Chunk("the scientific validity is established according to the "
                + "OECD principles for (Q)SAR validation; b)", NORMAL_FONT));
        firstParagraph.add(new Chunk("the (Q)SAR model is applicable to the query chemical: ", BOLD_FONT));
        firstParagraph.add(new Chunk("a (Q)SAR is applicable if the query chemical falls within the "
                + "defined applicability domain of the model; c)", NORMAL_FONT));
        firstParagraph.add(new Chunk("the (Q)SAR result is reliable: ", BOLD_FONT));
        firstParagraph.add(new Chunk("a valid (Q)SAR that is applied to a chemical falling "
                + "within its applicability domain provides a reliable result; d) ", NORMAL_FONT));
        firstParagraph.add(new Chunk("the (Q)SAR model "
                + "is relevant for the regulatory purpose:", BOLD_FONT));
        firstParagraph.add(new Chunk("the predicted endpoint can be used "
                + "directly or following an extrapolation, possibly in combination with other "
                + "information, for a particular regulatory purpose. ", NORMAL_FONT));
        firstParagraph.setAlignment("JUSTIFY");
        pdf.addElement(firstParagraph);
        pdf.addElement(new Paragraph(Chunk.NEWLINE));
        Paragraph secondParagraph = new Paragraph(new Chunk("A (Q)SAR prediction (model result) may be "
                + "considered adequate if it is reliable and relevant, and depending on the totality of "
                + "information available in a weight-of-evidence assessment (see Section 4 of the QPRF).", NORMAL_FONT));
        secondParagraph.setAlignment("JUSTIFY");
        pdf.addElement(secondParagraph);
        pdf.addElement(new Paragraph(Chunk.NEWLINE));
        /*
         * Section 1: Substance
         */
        Compound substance = qprfReport.getCompound();
        pdf.addElement(new MyParagraph(new Chunk("1. Substance", BOLD_FONT)));
        pdf.addElement(new MyParagraph(new Chunk("1.1. CAS number:", BOLD_FONT)).applyIndent(10));
        pdf.addElement(new MyParagraph(new Chunk(substance.getCasRn() != null && !substance.getCasRn().isEmpty()
                ? substance.getCasRn() : NOT_AVAILABLE, NORMAL_FONT)).applyIndent(20));
        pdf.addElement(new MyParagraph(new Chunk("1.2. EC number:", BOLD_FONT)).applyIndent(10));
        pdf.addElement(new MyParagraph(new Chunk(NOT_AVAILABLE, NORMAL_FONT)).applyIndent(20));
        pdf.addElement(new MyParagraph(new Chunk("1.3. Chemical Name:", BOLD_FONT)).applyIndent(10));
        pdf.addElement(new MyParagraph(new Chunk(substance.getIupacName() != null && !substance.getIupacName().isEmpty()
                ? "    " + substance.getIupacName() : "    " + NOT_AVAILABLE, NORMAL_FONT)).applyIndent(20));
        pdf.addElement(new MyParagraph(new Chunk("1.4. Structural Formula:", BOLD_FONT)).applyIndent(10));
        pdf.addElement(new MyParagraph(new Chunk(NOT_AVAILABLE, NORMAL_FONT)).applyIndent(20));
        pdf.addElement(new MyParagraph(new Chunk("1.5. Structure Codes:", BOLD_FONT)).applyIndent(10));
        try {
            Image image = Image.getInstance(substance.getUserIcon().getImage(), java.awt.Color.CYAN);
            image.scalePercent((float) 50.00);
            pdf.addElement(image);
        } catch (BadElementException ex) {
            Logger.getLogger(PDFReporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFReporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        pdf.addElement(new MyParagraph(new Chunk("a. SMILES:", BOLD_FONT)).applyIndent(20));
        pdf.addElement(new MyParagraph(new Chunk(substance.getSmiles(), NORMAL_FONT)).applyIndent(40));
        pdf.addElement(new MyParagraph(new Chunk("b. InChI:", BOLD_FONT)).applyIndent(20));
        pdf.addElement(new MyParagraph(new Chunk(substance.getInChI(), NORMAL_FONT)).applyIndent(40));
        pdf.addElement(new MyParagraph(new Chunk("d. Stereochemical Features:", BOLD_FONT)).applyIndent(20));
        pdf.addElement(new MyParagraph(new Chunk(qprfReport.getStereoFeatures(), NORMAL_FONT)).applyIndent(40));
        pdf.addElement(new Paragraph(Chunk.NEWLINE));
        /*
         * Section 2: General Info
         */
        pdf.addElement(new MyParagraph(new Chunk("2. General Information", BOLD_FONT)));
        pdf.addElement(new MyParagraph(new Chunk("Date of QPRF Report:", BOLD_FONT)).applyIndent(10));
        pdf.addElement(new MyParagraph(qprfReport.getYear() + ", " + "" + qprfReport.getMonth()
                + " " + qprfReport.getDay(), NORMAL_FONT).applyIndent(20));
        pdf.addElement(new MyParagraph(new Chunk("2.1. QPRF Author and Contact Details:", BOLD_FONT)).applyIndent(10));
        return pdf;
    }

    private static class MyParagraph extends Paragraph {

        public MyParagraph(String string) {
            super(string);
        }

        public MyParagraph(Chunk chunk) {
            super(chunk);
        }

        public MyParagraph() {
            super();
        }

        public MyParagraph(String string, Font font) {
            super(string, font);
        }

        public MyParagraph applyIndent(float ind) {
            this.setIndentationLeft(ind);
            this.setAlignment(Element.ALIGN_JUSTIFIED);
            return this;
        }
    }
}
