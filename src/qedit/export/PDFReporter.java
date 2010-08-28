package qedit.export;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import qedit.clients.components.AdequacyInfo;
import qedit.clients.components.Author;
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
    private static final Font ITALICS_FONT = FontFactory.getFont(FontFactory.TIMES_ROMAN, TEXT_SIZE, Font.ITALIC);

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
        if (substance != null) {
            pdf.addElement(new MyParagraph(new Chunk("1. Substance", BOLD_FONT)));
            pdf.addElement(new MyParagraph(new Chunk("1.1. CAS number:", BOLD_FONT)).applyIndent(10));
            pdf.addElement(new MyParagraph(new Chunk(substance.getCasRn() != null && !substance.getCasRn().isEmpty()
                    ? substance.getCasRn() : NOT_AVAILABLE, NORMAL_FONT)).applyIndent(20));
            pdf.addElement(new MyParagraph(new Chunk("1.2. EC number:", BOLD_FONT)).applyIndent(10));
            pdf.addElement(new MyParagraph(new Chunk(substance.getEinecs() != null
                    ? substance.getEinecs().isEmpty() ? NOT_AVAILABLE : substance.getEinecs() : NOT_AVAILABLE, NORMAL_FONT)).applyIndent(20));
            pdf.addElement(new MyParagraph(new Chunk("1.3. Chemical Name:", BOLD_FONT)).applyIndent(10));
            pdf.addElement(new MyParagraph(new Chunk(substance.getIupacName() != null && !substance.getIupacName().isEmpty()
                    ? substance.getIupacName() : NOT_AVAILABLE, NORMAL_FONT)).applyIndent(20));
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
            pdf.addElement(new MyParagraph(new Chunk(substance.getSmiles() != null ? substance.getSmiles() : NOT_AVAILABLE, NORMAL_FONT)).applyIndent(40));
            pdf.addElement(new MyParagraph(new Chunk("b. InChI:", BOLD_FONT)).applyIndent(20));
            pdf.addElement(new MyParagraph(new Chunk(substance.getInChI() != null ? substance.getInChI() : NOT_AVAILABLE, NORMAL_FONT)).applyIndent(40));
            if (qprfReport.getStereoFeatures() != null) {
                if (!qprfReport.getStereoFeatures().isEmpty()) {
                    pdf.addElement(new MyParagraph(new Chunk("d. Stereochemical Features:", BOLD_FONT)).applyIndent(20));
                    pdf.addElement(new MyParagraph(new Chunk(qprfReport.getStereoFeatures(), NORMAL_FONT)).applyIndent(40));
                }
            }
            pdf.addElement(new Paragraph(Chunk.NEWLINE));
            pdf.addElement(new Paragraph(Chunk.NEWLINE));
        }
        /*
         * Section 2: General Info
         */
        pdf.addElement(new MyParagraph(new Chunk("2. General Information", BOLD_FONT)));
        pdf.addElement(new MyParagraph(new Chunk("Date of QPRF Report:", BOLD_FONT)).applyIndent(10));
        pdf.addElement(new MyParagraph(qprfReport.getYear() + ", " + "" + qprfReport.getMonth()
                + " " + qprfReport.getDay(), NORMAL_FONT).applyIndent(20));
        pdf.addElement(new MyParagraph(new Chunk("2.1. QPRF Author and Contact Details:", BOLD_FONT)).applyIndent(10));
        // Authors:
        for (Author author : qprfReport.getAuthors()) {
            if (author != null) {
                String authorString = author.getName();
                if (author.getAffiliation() != null && !author.getAffiliation().isEmpty()) {
                    authorString += " (" + author.getAffiliation() + ")";
                }

                if (author.getEmail() != null && !author.getEmail().isEmpty()) {
                    authorString += ", e-mail: " + author.getEmail();
                }
                if (author.getUrl() != null && !author.getUrl().isEmpty()) {
                    authorString += ", URL: " + author.getUrl();
                }
                if (author.getContact() != null && !author.getContact().isEmpty()) {
                    authorString += ", Contact info: " + author.getContact();
                }

                pdf.addElement(new MyParagraph(new Chunk(authorString, NORMAL_FONT)).applyIndent(20));
            }
        }
        pdf.addElement(new Paragraph(Chunk.NEWLINE));
        pdf.addElement(new Paragraph(Chunk.NEWLINE));

        /*
         * Section 3: Prediction
         */
        if (qprfReport.getModel() != null) {
            pdf.addElement(new MyParagraph(new Chunk("3. Prediction", BOLD_FONT)));
            pdf.addElement(new MyParagraph(new Chunk("3.1. EndPoint (OECD Principle 1)", BOLD_FONT)).applyIndent(10));
            pdf.addElement(new MyParagraph(new Chunk("a. Endpoint", BOLD_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk("b. Dependent Variable:", BOLD_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk(qprfReport.getModel().getDependentFeature().getMeta().getTitle() != null
                    ? qprfReport.getModel().getDependentFeature().getMeta().getTitle() : NOT_AVAILABLE,
                    NORMAL_FONT)).applyIndent(30));
            pdf.addElement(new Paragraph(Chunk.NEWLINE));
            pdf.addElement(new MyParagraph(new Chunk("3.2. Algorithm (OECD Principle 2)", BOLD_FONT)).applyIndent(10));
            pdf.addElement(new MyParagraph(new Chunk("a. Model or Submodel Name", BOLD_FONT)).applyIndent(30));

            pdf.addElement(new MyParagraph(new Chunk(qprfReport.getModel().getMeta().getTitle() != null && !qprfReport.getModel().getMeta().getTitle().isEmpty()
                    ? qprfReport.getModel().getMeta().getTitle() : qprfReport.getModel().getUri(), NORMAL_FONT)).applyIndent(30));

            pdf.addElement(new MyParagraph(new Chunk("b. Model Version", BOLD_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk("Date : " + qprfReport.getModel().getYear() + ", "
                    + qprfReport.getModel().getMonth() + " " + qprfReport.getModel().getDay(), NORMAL_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk("Version Info : " + qprfReport.getModel().getMeta().getVersionInfo(), NORMAL_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk("c. Reference to QMRF", BOLD_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk(qprfReport.getModel().getQmrfReportMeta().getComment(), NORMAL_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk("d. Predicted Value (model result)", BOLD_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk(qprfReport.getCompound().getPredictedValue().getValue(), NORMAL_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk("d. Predicted Value (comments)", BOLD_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk(qprfReport.getCompound().getMeta().getComment(), NORMAL_FONT)).applyIndent(30));
            pdf.addElement(new Paragraph(Chunk.NEWLINE));

            pdf.addElement(new MyParagraph(new Chunk("3.3. Applicability Domain (OECD Principle 3)", BOLD_FONT)).applyIndent(10));
            pdf.addElement(new MyParagraph(new Chunk("a. Domains", BOLD_FONT)).applyIndent(30));
            pdf.addElement(new MyParagraph(new Chunk("i. descriptor domain", ITALICS_FONT)).applyIndent(40));
            pdf.addElement(new MyParagraph(new Chunk(qprfReport.getDescriptorDomain(), NORMAL_FONT)).applyIndent(40));
            pdf.addElement(new Paragraph(Chunk.NEWLINE));
            pdf.addElement(new MyParagraph(new Chunk("ii. structural fragment domain", ITALICS_FONT)).applyIndent(40));
            pdf.addElement(new MyParagraph(new Chunk(qprfReport.getStructuralDomain(), NORMAL_FONT)).applyIndent(40));
            pdf.addElement(new Paragraph(Chunk.NEWLINE));
            pdf.addElement(new MyParagraph(new Chunk("iii. mechanism domain", ITALICS_FONT)).applyIndent(40));
            pdf.addElement(new MyParagraph(new Chunk(qprfReport.getMechanismDomain(), NORMAL_FONT)).applyIndent(40));
            pdf.addElement(new Paragraph(Chunk.NEWLINE));
            pdf.addElement(new MyParagraph(new Chunk("iv. metabolic domain, if relevant", ITALICS_FONT)).applyIndent(40));
            pdf.addElement(new MyParagraph(new Chunk(qprfReport.getMetabolicDomain(), NORMAL_FONT)).applyIndent(40));

            pdf.addElement(new Paragraph(Chunk.NEWLINE));
            pdf.addElement(new MyParagraph(new Chunk("b. Structural Analogues", BOLD_FONT)).applyIndent(30));
            pdf.addElement(new Paragraph(Chunk.NEWLINE));
            PdfPTable structuralAnalogues = new PdfPTable(4);
            structuralAnalogues.setHorizontalAlignment(Element.ALIGN_CENTER);
            try {
                structuralAnalogues.setWidths(new int[]{100, 250, 100, 100});
            } catch (DocumentException ex) {
                Logger.getLogger(PDFReporter.class.getName()).log(Level.SEVERE, null, ex);
            }
            structuralAnalogues.addCell(new PdfPCell(new Phrase("CAS", BOLD_FONT)));
            structuralAnalogues.addCell(new PdfPCell(new Phrase("Structure", BOLD_FONT)));
            structuralAnalogues.addCell(new PdfPCell(new Phrase("SMILES", BOLD_FONT)));
            structuralAnalogues.addCell(new PdfPCell(new Phrase("Exp. Value", BOLD_FONT)));
            List<Compound> analogues = qprfReport.getCompound().getStructuralAnalogues();

            for (Compound anal : analogues) {
                structuralAnalogues.addCell(new PdfPCell(new Phrase(anal.getCasRn(), NORMAL_FONT)));

                if (anal.isImageAvailable()) {
                    try {
                        Image my = Image.getInstance(anal.getUserIcon().getImage(), java.awt.Color.BLACK);
                        my.scaleAbsolute((int) (anal.getUserIcon().getIconWidth()), (int) (anal.getUserIcon().getIconHeight()));
                        PdfPCell imageCell = new PdfPCell(my, true);
                        imageCell.setPadding(1);
                        structuralAnalogues.addCell(imageCell);
                    } catch (BadElementException ex) {
                        Logger.getLogger(PDFReporter.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(PDFReporter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {// No Image available
                    PdfPCell noImage = new PdfPCell(new Phrase("No Image", NORMAL_FONT));
                    noImage.setHorizontalAlignment(Element.ALIGN_CENTER);
                    structuralAnalogues.addCell(noImage);
                }

                structuralAnalogues.addCell(new PdfPCell(new Phrase(anal.getSmiles(), NORMAL_FONT)));
                structuralAnalogues.addCell(new PdfPCell(new Phrase(anal.getExperimentalValue() != null
                        ? anal.getExperimentalValue().getValue() : "", NORMAL_FONT)));
            }

            pdf.addElement(structuralAnalogues);
        }

        pdf.addElement(new Paragraph(Chunk.NEWLINE));
        pdf.addElement(new Paragraph(Chunk.NEWLINE));
        /*
         * Section 4: Adequacy Information
         */
        AdequacyInfo adequacy = qprfReport.getAdequacyInfo();
        if (!adequacy.isEmpty()) {
            pdf.addElement(new MyParagraph(new Chunk("4. Adequacy", BOLD_FONT)));
            pdf.addElement(new MyParagraph(new Chunk("The information provided in this section might be useful, "
                    + "depending on the reporting needs and formats of the regulatory framework of interest.",
                    NORMAL_FONT)).justify());
            pdf.addElement(new MyParagraph(new Chunk("This information aims to facilitate "
                    + "considerations about the adequacy of the (Q)SAR prediction (result) estimate. "
                    + "A (Q)SAR prediction may or may not be considered adequate (“fit-for-purpose”),"
                    + " depending on whether the prediction is sufficiently reliable and relevant in relation "
                    + "to the particular regulatory purpose. The adequacy of the prediction also depends on the "
                    + "availability of other information, and is determined in a weight-of-evidence assessment.",
                    NORMAL_FONT)).justify());
            pdf.addElement(new Paragraph(Chunk.NEWLINE));
            pdf.addElement(new MyParagraph(new Chunk("4.1. Regulatory Purpose:", BOLD_FONT)).applyIndent(10));
            pdf.addElement(new MyParagraph(new Chunk(adequacy.getRegulatoryPurpose(), NORMAL_FONT)).applyIndent(20));
            pdf.addElement(new MyParagraph(new Chunk("4.2. Approach for regulatory interpretation of the model result:",
                    BOLD_FONT)).applyIndent(10));
            pdf.addElement(new MyParagraph(new Chunk(adequacy.getRegulatoryInterpretation(), NORMAL_FONT)).applyIndent(20));
            pdf.addElement(new MyParagraph(new Chunk("4.3. Outcome:", BOLD_FONT)).applyIndent(10));
            pdf.addElement(new MyParagraph(new Chunk(adequacy.getOutcome(), NORMAL_FONT)).applyIndent(20));
            pdf.addElement(new MyParagraph(new Chunk("4.4. Conclusion:", BOLD_FONT)).applyIndent(10));
            pdf.addElement(new MyParagraph(new Chunk(adequacy.getConclusion(), NORMAL_FONT)).applyIndent(20));
        }
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

        public MyParagraph justify() {
            this.setAlignment(Element.ALIGN_JUSTIFIED);
            return this;
        }

        public MyParagraph applyIndent(float ind) {
            this.setIndentationLeft(ind);
            this.setAlignment(Element.ALIGN_JUSTIFIED);
            return this;
        }
    }
}
