/*
 * ReportInternalFrame.java
 *
 * Created on Jun 7, 2010, 9:47:54 AM
 */
package qedit;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JPanel;
import qedit.hints.QPRFDescriptionHint;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.application.Action;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.Task;
import org.jdesktop.application.TaskMonitor;
import org.jdesktop.application.TaskService;
import qedit.clients.ClientException;
import qedit.clients.components.Author;
import qedit.clients.components.Compound;
import qedit.clients.components.Dataset;
import qedit.clients.components.Feature;
import qedit.clients.components.FeatureValue;
import qedit.clients.components.Model;
import qedit.clients.components.QPRFReport;
import qedit.clients.spiders.CompoundSpider;
import qedit.clients.spiders.DatasetSpider;
import qedit.clients.spiders.ModelSpider;
import qedit.hints.AdequacyHint;
import qedit.hints.QPRFCommentsHint;
import qedit.hints.RegulInterpretationHint;
import qedit.hints.RegulatoryPurposeHint;
import qedit.task.AbstractTask;

/**
 *
 * @author Sopasakis Pantelis
 */
public class ReportInternalFrame extends javax.swing.JInternalFrame {

    private java.io.File relatedFile;

    public File getRelatedFile() {
        return relatedFile;
    }

    public void setRelatedFile(File relatedFile) {
        this.relatedFile = relatedFile;
    }

    public QPRFReport getQprfreport() {
        return qprfreport;
    }

    public void setQprfreport(QPRFReport qprfreport) {
        this.qprfreport = qprfreport;
    }

    /** Creates new form ReportInternalFrame */
    public ReportInternalFrame() {
        initComponents();
        revalidate();
        repaint();

    }

    @Action
    public void addLineAuthors() {
    }

    @Action
    public void enableDisableDate() {
        boolean enablement = !useCurrentDateForReport.isSelected();
        qprfYearCombo.setEnabled(enablement);
        qprfMonthCombo.setEnabled(enablement);
        qprfDayCombo.setEnabled(enablement);
    }

    @Action
    public void enableDisableModelDate() {
        boolean enablement = !modelCurrentDate.isSelected();
        modelDayCombo.setEnabled(enablement);
        modelMonthCombo.setEnabled(enablement);
        modelYearCombo.setEnabled(enablement);
    }

    @Action
    public void analoguesRowSelectionAction() {
//        int row = analoguesTable.getSelectedRow();
//        DefaultTableModel model = (DefaultTableModel) analoguesTable.getModel();
//        CardLayout cl = (CardLayout) (analoguesImageCards.getLayout());
//        cl.show(analoguesImageCards, (String) model.getValueAt(row, 0));
    }

    @Action
    public void copy() {
        int selectedRowIndex = authorsTable.getSelectedRow();
        int selectedColumnIndex = authorsTable.getSelectedColumn();
        if ((selectedColumnIndex + 1) * (1 + selectedRowIndex) == 0) {
            return;// no cell is selected!
        }
        Object value = authorsTable.getValueAt(selectedRowIndex, selectedColumnIndex);
        String selection;
        if (value != null) {
            selection = value.toString();
        } else {
            selection = null;
        }
        StringSelection data = new StringSelection(selection);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(data, data);
    }

    @Action
    public void delete() {
        int selectedRowIndex = authorsTable.getSelectedRow();
        int selectedColumnIndex = authorsTable.getSelectedColumn();
        if ((selectedColumnIndex + 1) * (1 + selectedRowIndex) == 0) {
            return;// no cell is selected!
        }
        authorsTable.setValueAt(null, selectedRowIndex, selectedColumnIndex);
    }

    @Action
    public void synchronizeAnaloguesWRTReport() {
        List<Compound> analogues = qprfreport.getCompound().getStructuralAnalogues();
        DefaultTableModel analoguesData = (DefaultTableModel) structuralAnaloguesTable.getModel();
        for (Compound c : analogues) {
            System.out.println(c.getChemicalName() + "*");
            analoguesData.addRow(new Object[]{c.getChemicalName().split(";")[0], c.getExperimentalValue()!=null?c.getExperimentalValue().getValue():""});
        }
        structuralAnaloguesTable.setModel(analoguesData);
    }

    @Action
    public void synchronizeCompoundFieldsWRTReport() {
        /*
         * Synchronize compound information:
         */
        stereoChemDiscussionTextArea.setText(qprfreport.getStereoFeatures());
        structureImage.setIcon(qprfreport.getCompound().getUserIcon());
        structureImage.setText("");
        DefaultListModel compoundSynonymsSyncronized = new DefaultListModel();
        for (String synonym : qprfreport.getCompound().getSynonyms()) {
            compoundSynonymsSyncronized.addElement(synonym);
        }
        compoundSynonymsList.setModel(compoundSynonymsSyncronized);
    }

    @Action
    public void synchronizeModelFieldsWRTReport() {
        /**
         * Synchronize MODEL information
         */
        modelVersionInfoTextArea.setText(qprfreport.getModel().getMeta().getVersionInfo());
        modelUriValue.setText(qprfreport.getModel().getUri());
        trainingDatasetValueTextField.setText(qprfreport.getModel().getDataset());
        qmrfReportDiscussionTextArea.setText(qprfreport.getModel().getQmrfReportMeta().getComment());

        if (qprfreport.getModel().getAlgorithm() != null) {
            algorithmUriValue.setText(qprfreport.getModel().getAlgorithm().getUri());
            if (qprfreport.getModel().getAlgorithm().getMeta() != null) {
                algorithmNameValue.setText(qprfreport.getModel().getAlgorithm().getMeta().getTitle());
            }
        }

        if (qprfreport.getModel().getPredictedFeature() != null) {
            if (qprfreport.getModel().getPredictedFeature().getMeta() != null) {
                String predFeatureName = qprfreport.getModel().getPredictedFeature().getMeta().getTitle();
                predictedFeatureNameValue.setText(predFeatureName);
            }
            predFeatureUriValue.setText(qprfreport.getModel().getPredictedFeature().getUri());
        }
    }

    @Action
    public void synchronizeDesccriptorsWRTReport() {
        Dataset dataset = qprfreport.getDescriptors();
        if (dataset != null) {
            Iterator<Entry<Feature, FeatureValue>> datasetValues = dataset.getFeatures().entrySet().iterator();
            DefaultTableModel descriptorsContent = (DefaultTableModel) descriptorsTable.getModel();
            String descriptorName;
            while (datasetValues.hasNext()) {
                Entry<Feature, FeatureValue> datasetEntry = datasetValues.next();
                descriptorName = datasetEntry.getKey().getMeta().getTitle() != null
                        ? datasetEntry.getKey().getMeta().getTitle() : datasetEntry.getKey().getUri();
                descriptorsContent.addRow(new String[]{
                            descriptorName,
                            datasetEntry.getValue().getValue().toString(),
                            datasetEntry.getKey().getUnits()
                        });
            }
            datasetUriValueTextField.setText(qprfreport.getDescriptors().getUri());
        }
    }

    @Action
    public void synchronizeDomainsWRTReport() {
        structuralFragmentDomainText.setText(qprfreport.getStructuralDomain());
        descriptorDomainText.setText(qprfreport.getDescriptorDomain());
        metabolicDomainText.setText(qprfreport.getMetabolicDomain());
        mechanismDomainText.setText(qprfreport.getMechanismDomain());
    }

    @Action
    public void synchronizeFieldsWRTReport() {
        synchronizeCompoundFieldsWRTReport();
        synchronizeModelFieldsWRTReport();
        /* Synchronize adequacy information */
        regulatoryPurposeTextArea.setText(qprfreport.getAdequacyInfo().getRegulatoryPurpose());
        regulInterprTextArea.setText(qprfreport.getAdequacyInfo().getRegulatoryInterpretation());
        qprfOutcomeTextArea.setText(qprfreport.getAdequacyInfo().getOutcome());
        qprfReportConclusionTextArea.setText(qprfreport.getAdequacyInfo().getConclusion());
        /* Synchronize General Info */
        qprfReportDescriptionTextArea.setText(qprfreport.getMetaInfo().getDescription());
        qprfReportCommentsTextArea.setText(qprfreport.getMetaInfo().getComment());
        clearAllRows(authorsTable);
        DefaultTableModel authorsData = (DefaultTableModel) authorsTable.getModel();
        for (Author author : qprfreport.getAuthors()) {
            authorsData.addRow(
                    new String[]{
                        author.getName() != null ? author.getName() : "",
                        author.getAffiliation() != null ? author.getAffiliation() : "",
                        author.getContact() != null ? author.getContact() : "",
                        author.getUrl() != null ? author.getUrl() : "",
                        author.getEmail() != null ? author.getEmail() : "",
                        author.getAddress() != null ? author.getAddress() : ""
                    });
        }
        analoguesCommentsTextArea.setText(qprfreport.getConsiderationsOnAnalogues());
        chemBioMechanismsTextArea.setText(qprfreport.getChemBioMechanisms());
        qmrfReportTextField.setText(qprfreport.getModel().getQmrfReportUri());
        predictionValueTextField.setText(qprfreport.getCompound().getPredictedValue().getValue());
        exprerimentalValueTextField.setText(qprfreport.getCompound().getExperimentalValue().getValue());
        synchronizeDesccriptorsWRTReport();
        synchronizeAnaloguesWRTReport();
        synchronizeDomainsWRTReport();

    }

    @Action
    public void updateReport() {
        qprfreport.setChemBioMechanisms(chemBioMechanismsTextArea.getText());
        qprfreport.setStereoFeatures(stereoChemDiscussionTextArea.getText());
        qprfreport.getAdequacyInfo().setRegulatoryPurpose(regulatoryPurposeTextArea.getText());
        qprfreport.getAdequacyInfo().setRegulatoryInterpretation(regulInterprTextArea.getText());
        qprfreport.getAdequacyInfo().setConclusion(qprfReportConclusionTextArea.getText());
        qprfreport.getAdequacyInfo().setOutcome(qprfOutcomeTextArea.getText());
        qprfreport.getMetaInfo().setTitle(qprfReportTitleTextArea.getText());
        qprfreport.getMetaInfo().setDescription(qprfReportDescriptionTextArea.getText());
        qprfreport.getMetaInfo().setComment(qprfReportCommentsTextArea.getText());
        qprfreport.getModel().setUri(modelUriValue.getText());
        qprfreport.getModel().setDataset(trainingDatasetValueTextField.getText());
        if (modelInfoDialog != null) {
            qprfreport.getModel().getMeta().setTitle(modelInfoDialog.getModelTitleTextField().getText());
        }
        qprfreport.getModel().setQmrfReportUri(qmrfReportTextField.getText());
        qprfreport.getModel().getQmrfReportMeta().setComment(qmrfReportDiscussionTextArea.getText());
        qprfreport.getModel().setYear(modelYearCombo.getSelectedItem().toString());
        qprfreport.getModel().setMonth(modelMonthCombo.getSelectedItem().toString());
        qprfreport.getModel().setDay(modelDayCombo.getSelectedItem().toString());
        qprfreport.getModel().getMeta().setVersionInfo(modelVersionInfoTextArea.getText());
        qprfreport.setYear(qprfYearCombo.getSelectedItem().toString());
        qprfreport.setMonth(qprfMonthCombo.getSelectedItem().toString());
        qprfreport.setDay(qprfDayCombo.getSelectedItem().toString());

        DefaultListModel synonyms = ((DefaultListModel) compoundSynonymsList.getModel());
        java.util.List<String> synonymsList = new java.util.ArrayList<String>();
        for (int synIndex = 0; synIndex < synonyms.size(); synIndex++) {
            synonymsList.add(synonyms.get(synIndex).toString());
        }
        qprfreport.getCompound().setSynonyms(synonymsList);
        qprfreport.getCompound().setUserIcon((ImageIcon) structureImage.getIcon());

        DefaultTableModel authorsData = (DefaultTableModel) authorsTable.getModel();
        Author currentAuthor;
        int numAuthors = authorsData.getRowCount();
        qprfreport.setAuthors(new ArrayList<Author>(numAuthors));
        for (int authorIndx = 0; authorIndx < numAuthors; authorIndx++) {
            currentAuthor = new Author();

            String tempString = "";
            int index = 0;
            tempString = authorsData.getValueAt(authorIndx, index) != null
                    ? authorsData.getValueAt(authorIndx, index++).toString() : "";
            currentAuthor.setName(tempString);
            tempString = authorsData.getValueAt(authorIndx, index) != null
                    ? authorsData.getValueAt(authorIndx, index++).toString() : "";
            currentAuthor.setAffiliation(tempString);
            tempString = authorsData.getValueAt(authorIndx, index) != null
                    ? authorsData.getValueAt(authorIndx, index++).toString() : "";
            currentAuthor.setContact(tempString);
            tempString = authorsData.getValueAt(authorIndx, index) != null
                    ? authorsData.getValueAt(authorIndx, index++).toString() : "";
            currentAuthor.setUrl(tempString);
            tempString = authorsData.getValueAt(authorIndx, index) != null
                    ? authorsData.getValueAt(authorIndx, index++).toString() : "";
            currentAuthor.setEmail(tempString);
            tempString = authorsData.getValueAt(authorIndx, index) != null
                    ? authorsData.getValueAt(authorIndx, index++).toString() : "";
            currentAuthor.setAddress(tempString);
            qprfreport.getAuthors().add(currentAuthor);
        }
        qprfreport.setConsiderationsOnAnalogues(analoguesCommentsTextArea.getText());
        qprfreport.setStructuralDomain(structuralFragmentDomainText.getText());
        qprfreport.setMechanismDomain(mechanismDomainText.getText());
        qprfreport.setMetabolicDomain(metabolicDomainText.getText());
        qprfreport.setDescriptorDomain(descriptorDomainText.getText());

        /* Update experimental values of structural analogues */
        DefaultTableModel structuralAnaloguesData = (DefaultTableModel) structuralAnaloguesTable.getModel();
        for (int sa = 0; sa < structuralAnaloguesTable.getRowCount(); sa++) {
            qprfreport.getCompound().getStructuralAnalogues().get(sa).setExperimentalValue(
                    new FeatureValue<String>(
                    structuralAnaloguesData.getValueAt(sa, 1).toString(),
                    XSDDatatype.XSDstring));
        }
        qprfreport.getCompound().setPredictedValue(new FeatureValue<String>(predictionValueTextField.getText(), XSDDatatype.XSDstring));
        qprfreport.getCompound().setExperimentalValue(new FeatureValue<String>(exprerimentalValueTextField.getText(), XSDDatatype.XSDstring));
        qprfreport.getCompound().getMeta().setComment(predictionCommentTextArea.getText());

    }

    private void clearCompound() {
        clearAllRows(descriptorsTable);
        structureImage.setText("");
        structureImage.setIcon(new Compound().getUserIcon());
        compoundSynonymsList.setModel(new DefaultListModel());
        basicContainerPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

    }

    private Compound refreshCompoundObject() throws ClientException {
        qedit.clients.components.Compound compound = null;
        QEditApp.getView().getStatusLabel().setText("Downloading Compound from remote server...");

        String keyWord = anyKeywordValueTextField.getText();

        if (keyWord == null || keyWord.isEmpty()) {
            return null;
        }
        CompoundSpider spider = new CompoundSpider(keyWord);
        compound = spider.parse();

        ImageIcon icon = null;
        icon = compound.getDepiction();
        compound.setUserIcon(icon);

        return compound;
    }

    private void refreshCompoundFields(Compound compound) {
        QEditApp.getView().getStatusLabel().setText("Setting structure image...");
        structureImage.setIcon(compound.getUserIcon());
        QEditApp.getView().getStatusLabel().setText("Setting compound synonyms...");
        getCompoundSynonymsList().setModel(new DefaultListModel());
        if (!compound.getSynonyms().isEmpty()) {
            for (String synonym : compound.getSynonyms()) {
                ((DefaultListModel) compoundSynonymsList.getModel()).addElement(synonym.trim());
            }
        } else {
            QEditApp.getView().getStatusLabel().setText("Failed to load synonyms...");
        }

        try {
            QEditApp.getView().getStatusLabel().setText("Loading descriptor values from dataset...");
            String compoundUri = compound.getUri();
            String datasetUri = datasetUriValueTextField.getText();
            if (datasetUri != null && !datasetUri.isEmpty()) {
                DatasetSpider dsSpider = new DatasetSpider(datasetUri, compoundUri);
                Dataset dataset = dsSpider.parse();
                qprfreport.setDescriptors(dataset);// Descriptors updated!
                Iterator<Entry<Feature, FeatureValue>> datasetValues = dataset.getFeatures().entrySet().iterator();
                DefaultTableModel descriptorsContent = (DefaultTableModel) descriptorsTable.getModel();
                String descriptorName;
                while (datasetValues.hasNext()) {
                    Entry<Feature, FeatureValue> datasetEntry = datasetValues.next();
                    descriptorName = datasetEntry.getKey().getMeta().getTitle() != null
                            ? datasetEntry.getKey().getMeta().getTitle() : datasetEntry.getKey().getUri();
                    descriptorsContent.addRow(new String[]{
                                descriptorName,
                                datasetEntry.getValue().getValue().toString(),
                                datasetEntry.getKey().getUnits()
                            });
                }
            }
        } catch (ClientException ex) {
            QEditApp.getView().getStatusLabel().setText("Dataset could not be loaded...");
            Logger.getLogger(ReportInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * According to the user input, updates the object {@link ReportInternalFrame#qprfreport }
     */
    @Action
    public void updateCompoundFromUserInput() {
        Task downloadImageTask = new Task(QEditApp.getApplication()) {

            @Override
            protected void finished() {
                basicContainerPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                super.finished();
            }

            @Override
            protected void cancelled() {
                super.cancelled();
                basicContainerPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                QEditApp.getView().getStatusLabel().setText("Compound Loading Cancelled!");
            }

            @Override
            protected Object doInBackground() throws Exception {
                setProgress(0);
                clearCompound();
                qprfreport.setCompound(new Compound());
                Compound compound = refreshCompoundObject();
                setProgress(30);
                if (compound == null) {
                    QEditApp.getView().getStatusLabel().setText("No Compound could be loaded...");
                    setProgress(100);
                    return null;
                }
                refreshCompoundFields(compound);
                setProgress(100);
                QEditApp.getView().getStatusLabel().setText("Loaded Compound information...");
                basicContainerPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                qprfreport.setCompound(compound);
                return new Object();
            }

            @Override
            protected void failed(Throwable cause) {
                super.failed(cause);
                if (cause instanceof ClientException) {
                    QEditApp.getView().getStatusLabel().setText("No Compound could be loaded...");
                }
            }
        };

        ApplicationContext appC = QEditApp.getInstance().getContext();
        TaskMonitor taskMonitor = appC.getTaskMonitor();
        TaskService taskService = appC.getTaskService();
        taskService.execute(downloadImageTask);
        taskMonitor.setForegroundTask(downloadImageTask);

    }

    @Action
    public void deleteImage() {
        structureImage.setIcon(new Compound().getUserIcon());
    }

    @Action
    public void addCompoundSynonym() {
        JFrame jframe = QEditApp.getView().getFrame();
        AddSynonymDialog addSynonymDialog = new AddSynonymDialog(jframe, this);
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = addSynonymDialog.getWidth();
        int dialogHeight = addSynonymDialog.getHeight();
        int dialog_x = (frameWidth - dialogWidht) / 2;
        int dialog_y = (frameHeight - dialogHeight) / 2;
        addSynonymDialog.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        addSynonymDialog.setVisible(true);
    }

    @Action
    public void compoundDetails() {
        JFrame jframe = QEditApp.getView().getFrame();
        compoundDetailsDialog = new CompoundDetailsDialog(jframe, qprfreport.getCompound());
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = compoundDetailsDialog.getWidth();
        int dialogHeight = compoundDetailsDialog.getHeight();
        int dialog_x = (frameWidth - dialogWidht) / 2;
        int dialog_y = (frameHeight - dialogHeight) / 2;
        compoundDetailsDialog.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        compoundDetailsDialog.setVisible(true);
        compoundDetailsDialog.setRif(this);
    }

    @Action
    public void addFeatureWizard() {
        JFrame jframe = QEditApp.getView().getFrame();
        desriptorWizard = new DescriptorValueWizard(jframe);
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = desriptorWizard.getWidth();
        int dialogHeight = desriptorWizard.getHeight();
        int dialog_x = (frameWidth - dialogWidht) / 2;
        int dialog_y = (frameHeight - dialogHeight) / 2;
        desriptorWizard.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        desriptorWizard.setDescriptorsTable(descriptorsTable);
        desriptorWizard.setVisible(true);
    }

    @Action
    public void addAuthorWizard() {
        JFrame jframe = QEditApp.getView().getFrame();
        authorsWizard = new AuthorWizard(jframe);
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = authorsWizard.getWidth();
        int dialogHeight = authorsWizard.getHeight();
        int dialog_x = (frameWidth - dialogWidht) / 2;
        int dialog_y = (frameHeight - dialogHeight) / 2;
        authorsWizard.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        authorsWizard.setAuthorsTable(authorsTable);
        authorsWizard.setVisible(true);
    }

    @Action
    public void updateCompoundPanel() {
        Compound compound = qprfreport.getCompound();

        structureImage.setIcon(compound.getUserIcon());

    }

    @Action
    public void downloadModelInfoAction() {
        modelUriValue.setBackground(new Color(228, 237, 237));

        if (modelUriValue.getText() == null || modelUriValue.getText().isEmpty()) {
            QEditApp.getView().getStatusLabel().setText("You have to provide a model URI first");
            modelUriValue.setBackground(Color.yellow);
        }

        AbstractTask task = new AbstractTask() {

            @Override
            protected void failed(Throwable cause) {
                super.failed(cause);
                QEditApp.getView().getStatusLabel().setText("Could not load model info from " + modelUriValue.getText());
            }

            @Override
            protected Object doInBackground() throws Exception {
                ModelSpider mSpider = new ModelSpider(modelUriValue.getText());
                Model modelFromRemote = mSpider.parse();
                qprfreport.setModel(modelFromRemote);
                if (modelFromRemote != null) {
                    synchronizeModelFieldsWRTReport();
                }
                return null;
            }
        };
        task.runInBackground();
    }

    public JList getCompoundSynonymsList() {
        return compoundSynonymsList;
    }

    private void toggleDOAValue() {
        ImageIcon icon = !doaValue
                ? org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(ReportInternalFrame.class).getImageIcon("applicabilityDomainValue.icon")
                : org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(StructuralAnalogueWizard_Step1.class).getImageIcon("cancelButton.icon");
        applicabilityDomainValue.setIcon(icon);
        doaValue = !doaValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        authorsTablePopupMenu = new javax.swing.JPopupMenu();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        cutMenuItem = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        removeAuthorMenuItem = new javax.swing.JMenuItem();
        descriptorsPopupMenu = new javax.swing.JPopupMenu();
        descriptorMetaMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        copyDescriptorMenuItem = new javax.swing.JMenuItem();
        removeDescriptorLineMenuItem = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        basicContainerPanel = new javax.swing.JPanel();
        basicTabbedPanel = new javax.swing.JTabbedPane();
        compoundPanel = new javax.swing.JPanel();
        compoundInfoPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        compoundsToolbar = new javax.swing.JToolBar();
        cmlCompoundButton = new javax.swing.JButton();
        rdfCompoundButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        showCompoundDetailsButton = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        updateAllCompoundInfoButton = new javax.swing.JButton();
        structureImagePanel = new javax.swing.JPanel();
        structureImageToolbar = new javax.swing.JToolBar();
        loadStructureImgButton = new javax.swing.JButton();
        clearStructureImgButton = new javax.swing.JButton();
        showMagnifiedImage = new javax.swing.JButton();
        structureImage = new javax.swing.JLabel();
        compoundDataInputPanel = new javax.swing.JPanel();
        compoundIdentifierPanel = new javax.swing.JPanel();
        compoundURILabel = new javax.swing.JLabel();
        datasetUriValueTextField = new javax.swing.JTextField();
        compoundUriHint = new javax.swing.JLabel();
        compoundIndentifiersLabel = new javax.swing.JLabel();
        compoundIdentifiersCards = new javax.swing.JPanel();
        anyKeywordValueTextField = new javax.swing.JTextField();
        compoundSynonymsPanel = new javax.swing.JPanel();
        compoundNamesScrollable = new javax.swing.JScrollPane();
        compoundSynonymsList = new javax.swing.JList();
        removeCompoundSynonym = new javax.swing.JButton();
        addCompoundSynonym = new javax.swing.JButton();
        compoundNameHint = new javax.swing.JLabel();
        compoundSynonymsLabel = new javax.swing.JLabel();
        clearAllCompoundSynonyms = new javax.swing.JButton();
        descriptorsPanel = new javax.swing.JPanel();
        descriptorsScrollable = new javax.swing.JScrollPane();
        descriptorsTable = new javax.swing.JTable();
        descriptorsTableToolbar = new javax.swing.JToolBar();
        addDescriptorValueButton = new javax.swing.JButton();
        removeDescriptorValueButton = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        clearAllDescriptorsButton = new javax.swing.JButton();
        stereoChemFeaturesDiscussionPanel = new javax.swing.JPanel();
        stereoChemHintLabel = new javax.swing.JLabel();
        stereoChemScollPane = new javax.swing.JScrollPane();
        stereoChemDiscussionTextArea = new javax.swing.JTextArea();
        stereoChemHintLamp = new javax.swing.JLabel();
        reportGeneralInfoPanel = new javax.swing.JPanel();
        qprfInfoTabbedSubPanel = new javax.swing.JTabbedPane();
        generalInfoPanel = new javax.swing.JPanel();
        authorsPanel = new javax.swing.JPanel();
        authorsTableScrollable = new javax.swing.JScrollPane();
        authorsTable = new javax.swing.JTable(){
            @Override
            protected boolean processKeyBinding(KeyStroke ks, KeyEvent e,
                int condition, boolean pressed) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_CONTEXT_MENU) {
                    return false;
                }
                return super.processKeyBinding(ks, e, condition, pressed);
            }
        }
        ;
        authorsToolbar = new javax.swing.JToolBar();
        appendRowButton = new javax.swing.JButton();
        removeSelectedRowButton = new javax.swing.JButton();
        moveUpRowButton = new javax.swing.JButton();
        moveDownRowButton = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        authorsWizardButton = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        clearAllAuthorsButton = new javax.swing.JButton();
        datePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        qprfYearCombo = new javax.swing.JComboBox();
        qprfMonthCombo = new javax.swing.JComboBox();
        qprfDayCombo = new javax.swing.JComboBox();
        useCurrentDateForReport = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        otherInfoPanel = new javax.swing.JPanel();
        metaInfoPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        qprfDescriptionLabel = new javax.swing.JLabel();
        qprfCommentsLabel = new javax.swing.JLabel();
        qprfDescriptionHintLabel = new javax.swing.JLabel();
        qprfCommentsHintLabel = new javax.swing.JLabel();
        qprfTitleHintLabel = new javax.swing.JLabel();
        qprfReportTitleScrollable = new javax.swing.JScrollPane();
        qprfReportTitleTextArea = new javax.swing.JTextArea();
        qprfReportDescriptionScollable = new javax.swing.JScrollPane();
        qprfReportDescriptionTextArea = new javax.swing.JTextArea();
        qprfReportCommentsScrollable = new javax.swing.JScrollPane();
        qprfReportCommentsTextArea = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        reportPredictionPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        modelPanel = new javax.swing.JPanel();
        modelInfoPanel = new javax.swing.JPanel();
        modelUriLabel = new javax.swing.JLabel();
        modelUriValue = new javax.swing.JTextField();
        copyModelUriButton = new javax.swing.JButton();
        trainingDatasetLabel = new javax.swing.JLabel();
        trainingDatasetValueTextField = new javax.swing.JTextField();
        copyDatasetButton = new javax.swing.JButton();
        modelAndTrainingDSHeader = new javax.swing.JLabel();
        trainingAlgorithmHeader = new javax.swing.JLabel();
        algorithmNameLabel = new javax.swing.JLabel();
        modelUriDetailsButton = new javax.swing.JButton();
        datasetDetailsButton = new javax.swing.JButton();
        algorithmNameValue = new javax.swing.JTextField();
        copyAlgorithmName = new javax.swing.JButton();
        algorithmUriLabel = new javax.swing.JLabel();
        algorithmUriValue = new javax.swing.JTextField();
        copyAlgorithmUriButton = new javax.swing.JButton();
        algorithmDetailsButton = new javax.swing.JButton();
        predictedFeatureHeadline = new javax.swing.JLabel();
        predFeatureNameLabel = new javax.swing.JLabel();
        predictedFeatureNameValue = new javax.swing.JTextField();
        copyPredFeatureNameButton = new javax.swing.JButton();
        predFeatureResourceLabel = new javax.swing.JLabel();
        predFeatureUriValue = new javax.swing.JTextField();
        copyPredFeatureUriButton = new javax.swing.JButton();
        predFeatureDetailsButton = new javax.swing.JButton();
        modelToolbar = new javax.swing.JToolBar();
        modelInfoToolButton = new javax.swing.JButton();
        algorithmInfoToolButton = new javax.swing.JButton();
        predictedFeatureToolButton = new javax.swing.JButton();
        dependentFeatureToolButton = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        downloadModelInfoButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        qmrfReportHeader = new javax.swing.JLabel();
        qmrfReportLabel = new javax.swing.JLabel();
        qmrfReportTextField = new javax.swing.JTextField();
        modelVersionHeader = new javax.swing.JLabel();
        modelVersionLabel = new javax.swing.JLabel();
        modelVersionScrollPane = new javax.swing.JScrollPane();
        modelVersionInfoTextArea = new javax.swing.JTextArea();
        qmrfReportDiscussionScrollable = new javax.swing.JScrollPane();
        qmrfReportDiscussionTextArea = new javax.swing.JTextArea();
        qmrfReportDiscussionLabel = new javax.swing.JLabel();
        modelDatePanel = new javax.swing.JPanel();
        modelDateLabel = new javax.swing.JLabel();
        modelYearLabel = new javax.swing.JLabel();
        modelYearCombo = new javax.swing.JComboBox();
        modelMonthLabel = new javax.swing.JLabel();
        modelMonthCombo = new javax.swing.JComboBox();
        modelDayLabel = new javax.swing.JLabel();
        modelDayCombo = new javax.swing.JComboBox();
        modelCurrentDate = new javax.swing.JCheckBox();
        predictionPanel = new javax.swing.JPanel();
        predictionInfoPanel = new javax.swing.JPanel();
        predictionValueLabel = new javax.swing.JLabel();
        predictionValueTextField = new javax.swing.JTextField();
        predictionUnitsTextField = new javax.swing.JTextField();
        predictionUncertaintyLabel = new javax.swing.JLabel();
        predictionUncertaintyScrollable = new javax.swing.JScrollPane();
        predictionUncertaintyTextArea = new javax.swing.JTextArea();
        predictionCommentLabel = new javax.swing.JLabel();
        predictionCommentScrollable = new javax.swing.JScrollPane();
        predictionCommentTextArea = new javax.swing.JTextArea();
        experimentalValueLabel = new javax.swing.JLabel();
        exprerimentalValueTextField = new javax.swing.JTextField();
        experimentalValueUnitsTextField = new javax.swing.JTextField();
        chemBioMechanismsLabel = new javax.swing.JLabel();
        chemBioMechanismsScrollable = new javax.swing.JScrollPane();
        chemBioMechanismsTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        applicabilityDomainPanel = new javax.swing.JPanel();
        appDomainInfoPanel = new javax.swing.JPanel();
        appDomAlgorithmNameLabel = new javax.swing.JLabel();
        addDomLinkLabel = new javax.swing.JLabel();
        appDomainAlgorithmNameValue = new javax.swing.JTextField();
        appDomainURIValue = new javax.swing.JTextField();
        addDomainsDiscussionPanel = new javax.swing.JPanel();
        addDomainResultLabel = new javax.swing.JLabel();
        applicabilityDomainValue = new javax.swing.JLabel();
        domainChooserComboBox = new javax.swing.JComboBox();
        chooseDomainLabel = new javax.swing.JLabel();
        domainCardPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        metabolicDomainText = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        structuralFragmentDomainText = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        descriptorDomainText = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        mechanismDomainText = new javax.swing.JTextArea();
        structuralAnaloguesPanel = new javax.swing.JPanel();
        structuralAnaloguesToolbar = new javax.swing.JToolBar();
        compoundWizardButton = new javax.swing.JButton();
        removeStructuralAnalogue = new javax.swing.JButton();
        clearAllStructuralAnalogues = new javax.swing.JButton();
        structAnalToolbarSeparator = new javax.swing.JToolBar.Separator();
        jLabel4 = new javax.swing.JLabel();
        similarityLevel = new javax.swing.JTextField();
        acquireListAnaloguesButton = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        analogueInfo = new javax.swing.JButton();
        structuralAnaloguesSeparator = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        analoguesCommentsLabel = new javax.swing.JLabel();
        analoguesPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        structuralAnaloguesTable = new javax.swing.JTable();
        analoguesCommentsScrollable = new javax.swing.JScrollPane();
        analoguesCommentsTextArea = new javax.swing.JTextArea();
        analoguesImagePanel = new javax.swing.JPanel();
        analogueImageLabel = new javax.swing.JLabel();
        adequacyInfoPanel = new javax.swing.JPanel();
        adequacyInfoSubPanel = new javax.swing.JPanel();
        regulatoryPurposeLabel = new javax.swing.JLabel();
        regulatoryPurposeHintLabel = new javax.swing.JLabel();
        regulatoryInterprLabel = new javax.swing.JLabel();
        outcomeLabel = new javax.swing.JLabel();
        conclusionLabel = new javax.swing.JLabel();
        regulatoryPurposeScrollable = new javax.swing.JScrollPane();
        regulatoryPurposeTextArea = new javax.swing.JTextArea();
        regulInterprScrollable = new javax.swing.JScrollPane();
        regulInterprTextArea = new javax.swing.JTextArea();
        qprfOutcomeScrollable = new javax.swing.JScrollPane();
        qprfOutcomeTextArea = new javax.swing.JTextArea();
        qprfReportConclusionScollable = new javax.swing.JScrollPane();
        qprfReportConclusionTextArea = new javax.swing.JTextArea();
        regulatoryInterprHintLabel = new javax.swing.JLabel();
        outcomeHintLabel = new javax.swing.JLabel();
        conclusionHintLabel = new javax.swing.JLabel();
        adequacyInfoIndicatorLabel = new javax.swing.JLabel();
        adequacyBottomMessageLabel = new javax.swing.JLabel();
        adequacyHintLabel = new javax.swing.JLabel();

        authorsTablePopupMenu.setName("authorsTablePopupMenu"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(ReportInternalFrame.class);
        copyMenuItem.setIcon(resourceMap.getIcon("copyMenuItem.icon")); // NOI18N
        copyMenuItem.setMnemonic('C');
        copyMenuItem.setText(resourceMap.getString("copyMenuItem.text")); // NOI18N
        copyMenuItem.setName("copyMenuItem"); // NOI18N
        copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuItemActionPerformed(evt);
            }
        });
        authorsTablePopupMenu.add(copyMenuItem);

        pasteMenuItem.setIcon(resourceMap.getIcon("pasteMenuItem.icon")); // NOI18N
        pasteMenuItem.setMnemonic('P');
        pasteMenuItem.setText(resourceMap.getString("pasteMenuItem.text")); // NOI18N
        pasteMenuItem.setName("pasteMenuItem"); // NOI18N
        pasteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteMenuItemActionPerformed(evt);
            }
        });
        authorsTablePopupMenu.add(pasteMenuItem);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable clipData = clipboard.getContents(clipboard);
        Object trasferableData = null;
        try {
            trasferableData = clipData.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException ex){
        } catch (IOException ex){
        }
        if (clipData==null || trasferableData==null) {
            pasteMenuItem.setEnabled(false);
        }

        deleteMenuItem.setIcon(resourceMap.getIcon("deleteMenuItem.icon")); // NOI18N
        deleteMenuItem.setText(resourceMap.getString("deleteMenuItem.text")); // NOI18N
        deleteMenuItem.setName("deleteMenuItem"); // NOI18N
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuItemActionPerformed(evt);
            }
        });
        authorsTablePopupMenu.add(deleteMenuItem);

        cutMenuItem.setIcon(resourceMap.getIcon("cutMenuItem.icon")); // NOI18N
        cutMenuItem.setText(resourceMap.getString("cutMenuItem.text")); // NOI18N
        cutMenuItem.setName("cutMenuItem"); // NOI18N
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        authorsTablePopupMenu.add(cutMenuItem);

        jSeparator10.setName("jSeparator10"); // NOI18N
        authorsTablePopupMenu.add(jSeparator10);

        removeAuthorMenuItem.setIcon(resourceMap.getIcon("removeAuthorMenuItem.icon")); // NOI18N
        removeAuthorMenuItem.setText(resourceMap.getString("removeAuthorMenuItem.text")); // NOI18N
        removeAuthorMenuItem.setName("removeAuthorMenuItem"); // NOI18N
        removeAuthorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAuthorMenuItemActionPerformed(evt);
            }
        });
        authorsTablePopupMenu.add(removeAuthorMenuItem);

        descriptorsPopupMenu.setName("descriptorsPopupMenu"); // NOI18N

        descriptorMetaMenuItem.setIcon(resourceMap.getIcon("descriptorMetaMenuItem.icon")); // NOI18N
        descriptorMetaMenuItem.setText(resourceMap.getString("descriptorMetaMenuItem.text")); // NOI18N
        descriptorMetaMenuItem.setName("descriptorMetaMenuItem"); // NOI18N
        descriptorMetaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptorMetaMenuItemActionPerformed(evt);
            }
        });
        descriptorsPopupMenu.add(descriptorMetaMenuItem);

        jSeparator3.setName("jSeparator3"); // NOI18N
        descriptorsPopupMenu.add(jSeparator3);

        copyDescriptorMenuItem.setIcon(resourceMap.getIcon("copyDescriptorMenuItem.icon")); // NOI18N
        copyDescriptorMenuItem.setText(resourceMap.getString("copyDescriptorMenuItem.text")); // NOI18N
        copyDescriptorMenuItem.setName("copyDescriptorMenuItem"); // NOI18N
        descriptorsPopupMenu.add(copyDescriptorMenuItem);

        removeDescriptorLineMenuItem.setIcon(resourceMap.getIcon("removeDescriptorLineMenuItem.icon")); // NOI18N
        removeDescriptorLineMenuItem.setText(resourceMap.getString("removeDescriptorLineMenuItem.text")); // NOI18N
        removeDescriptorLineMenuItem.setName("removeDescriptorLineMenuItem"); // NOI18N
        removeDescriptorLineMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDescriptorLineMenuItemActionPerformed(evt);
            }
        });
        descriptorsPopupMenu.add(removeDescriptorLineMenuItem);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N

        basicContainerPanel.setName("basicContainerPanel"); // NOI18N

        basicTabbedPanel.setName("basicTabbedPanel"); // NOI18N

        compoundPanel.setName("compoundPanel"); // NOI18N

        compoundInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("compoundInfoPanel.border.title"))); // NOI18N
        compoundInfoPanel.setName("compoundInfoPanel"); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N

        compoundsToolbar.setFloatable(false);
        compoundsToolbar.setRollover(true);
        compoundsToolbar.setName("compoundsToolbar"); // NOI18N

        cmlCompoundButton.setIcon(resourceMap.getIcon("cmlCompoundButton.icon")); // NOI18N
        cmlCompoundButton.setText(resourceMap.getString("cmlCompoundButton.text")); // NOI18N
        cmlCompoundButton.setToolTipText(resourceMap.getString("cmlCompoundButton.toolTipText")); // NOI18N
        cmlCompoundButton.setFocusable(false);
        cmlCompoundButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmlCompoundButton.setName("cmlCompoundButton"); // NOI18N
        cmlCompoundButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        compoundsToolbar.add(cmlCompoundButton);

        rdfCompoundButton.setIcon(resourceMap.getIcon("rdfCompoundButton.icon")); // NOI18N
        rdfCompoundButton.setText(resourceMap.getString("rdfCompoundButton.text")); // NOI18N
        rdfCompoundButton.setToolTipText(resourceMap.getString("rdfCompoundButton.toolTipText")); // NOI18N
        rdfCompoundButton.setFocusable(false);
        rdfCompoundButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rdfCompoundButton.setName("rdfCompoundButton"); // NOI18N
        rdfCompoundButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        compoundsToolbar.add(rdfCompoundButton);

        jSeparator2.setName("jSeparator2"); // NOI18N
        compoundsToolbar.add(jSeparator2);

        showCompoundDetailsButton.setIcon(resourceMap.getIcon("showCompoundDetailsButton.icon")); // NOI18N
        showCompoundDetailsButton.setText(resourceMap.getString("showCompoundDetailsButton.text")); // NOI18N
        showCompoundDetailsButton.setFocusable(false);
        showCompoundDetailsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        showCompoundDetailsButton.setName("showCompoundDetailsButton"); // NOI18N
        showCompoundDetailsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        showCompoundDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCompoundDetailsButtonActionPerformed(evt);
            }
        });
        compoundsToolbar.add(showCompoundDetailsButton);

        jSeparator11.setName("jSeparator11"); // NOI18N
        compoundsToolbar.add(jSeparator11);

        updateAllCompoundInfoButton.setIcon(resourceMap.getIcon("updateAllCompoundInfoButton.icon")); // NOI18N
        updateAllCompoundInfoButton.setText(resourceMap.getString("updateAllCompoundInfoButton.text")); // NOI18N
        updateAllCompoundInfoButton.setFocusable(false);
        updateAllCompoundInfoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateAllCompoundInfoButton.setName("updateAllCompoundInfoButton"); // NOI18N
        updateAllCompoundInfoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateAllCompoundInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAllCompoundInfoButtonActionPerformed(evt);
            }
        });
        compoundsToolbar.add(updateAllCompoundInfoButton);

        structureImagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("structureImagePanel.border.title"))); // NOI18N
        structureImagePanel.setName("structureImagePanel"); // NOI18N

        structureImageToolbar.setFloatable(false);
        structureImageToolbar.setRollover(true);
        structureImageToolbar.setName("structureImageToolbar"); // NOI18N

        loadStructureImgButton.setIcon(resourceMap.getIcon("loadStructureImgButton.icon")); // NOI18N
        loadStructureImgButton.setText(resourceMap.getString("loadStructureImgButton.text")); // NOI18N
        loadStructureImgButton.setToolTipText(resourceMap.getString("loadStructureImgButton.toolTipText")); // NOI18N
        loadStructureImgButton.setName("loadStructureImgButton"); // NOI18N
        loadStructureImgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadStructureImgButtonActionPerformed(evt);
            }
        });
        structureImageToolbar.add(loadStructureImgButton);

        clearStructureImgButton.setIcon(resourceMap.getIcon("clearStructureImgButton.icon")); // NOI18N
        clearStructureImgButton.setText(resourceMap.getString("clearStructureImgButton.text")); // NOI18N
        clearStructureImgButton.setToolTipText(resourceMap.getString("clearStructureImgButton.toolTipText")); // NOI18N
        clearStructureImgButton.setName("clearStructureImgButton"); // NOI18N
        clearStructureImgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearStructureImgButtonActionPerformed(evt);
            }
        });
        structureImageToolbar.add(clearStructureImgButton);

        showMagnifiedImage.setIcon(resourceMap.getIcon("showMagnifiedImage.icon")); // NOI18N
        showMagnifiedImage.setText(resourceMap.getString("showMagnifiedImage.text")); // NOI18N
        showMagnifiedImage.setToolTipText(resourceMap.getString("showMagnifiedImage.toolTipText")); // NOI18N
        showMagnifiedImage.setFocusable(false);
        showMagnifiedImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        showMagnifiedImage.setName("showMagnifiedImage"); // NOI18N
        showMagnifiedImage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        showMagnifiedImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMagnifiedImageActionPerformed(evt);
            }
        });
        structureImageToolbar.add(showMagnifiedImage);

        structureImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        structureImage.setIcon(resourceMap.getIcon("structureImage.icon")); // NOI18N
        structureImage.setText(resourceMap.getString("structureImage.text")); // NOI18N
        structureImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        structureImage.setName("structureImage"); // NOI18N

        javax.swing.GroupLayout structureImagePanelLayout = new javax.swing.GroupLayout(structureImagePanel);
        structureImagePanel.setLayout(structureImagePanelLayout);
        structureImagePanelLayout.setHorizontalGroup(
            structureImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(structureImagePanelLayout.createSequentialGroup()
                .addGroup(structureImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(structureImagePanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(structureImage, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(structureImageToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addContainerGap())
        );
        structureImagePanelLayout.setVerticalGroup(
            structureImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(structureImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(structureImageToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(structureImage, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        compoundDataInputPanel.setName("compoundDataInputPanel"); // NOI18N

        compoundIdentifierPanel.setName("compoundIdentifierPanel"); // NOI18N

        compoundURILabel.setFont(resourceMap.getFont("compoundURILabel.font")); // NOI18N
        compoundURILabel.setIcon(resourceMap.getIcon("compoundURILabel.icon")); // NOI18N
        compoundURILabel.setText(resourceMap.getString("compoundURILabel.text")); // NOI18N
        compoundURILabel.setName("compoundURILabel"); // NOI18N

        datasetUriValueTextField.setBackground(resourceMap.getColor("datasetUriValueTextField.background")); // NOI18N
        datasetUriValueTextField.setForeground(resourceMap.getColor("datasetUriValueTextField.foreground")); // NOI18N
        datasetUriValueTextField.setText(resourceMap.getString("datasetUriValueTextField.text")); // NOI18N
        datasetUriValueTextField.setName("datasetUriValueTextField"); // NOI18N

        compoundUriHint.setIcon(resourceMap.getIcon("compoundUriHint.icon")); // NOI18N
        compoundUriHint.setText(resourceMap.getString("compoundUriHint.text")); // NOI18N
        compoundUriHint.setName("compoundUriHint"); // NOI18N

        compoundIndentifiersLabel.setText(resourceMap.getString("compoundIndentifiersLabel.text")); // NOI18N
        compoundIndentifiersLabel.setName("compoundIndentifiersLabel"); // NOI18N

        compoundIdentifiersCards.setName("compoundIdentifiersCards"); // NOI18N
        compoundIdentifiersCards.setLayout(new java.awt.CardLayout());

        anyKeywordValueTextField.setBackground(resourceMap.getColor("anyKeywordValueTextField.background")); // NOI18N
        anyKeywordValueTextField.setText(resourceMap.getString("anyKeywordValueTextField.text")); // NOI18N
        anyKeywordValueTextField.setName("anyKeywordValueTextField"); // NOI18N
        compoundIdentifiersCards.add(anyKeywordValueTextField, "Any Keyword");

        javax.swing.GroupLayout compoundIdentifierPanelLayout = new javax.swing.GroupLayout(compoundIdentifierPanel);
        compoundIdentifierPanel.setLayout(compoundIdentifierPanelLayout);
        compoundIdentifierPanelLayout.setHorizontalGroup(
            compoundIdentifierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(compoundIdentifierPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(compoundIdentifierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(datasetUriValueTextField)
                    .addComponent(compoundIdentifiersCards, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, compoundIdentifierPanelLayout.createSequentialGroup()
                        .addComponent(compoundURILabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compoundUriHint))
                    .addComponent(compoundIndentifiersLabel, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(142, 142, 142))
        );
        compoundIdentifierPanelLayout.setVerticalGroup(
            compoundIdentifierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(compoundIdentifierPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(compoundIndentifiersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compoundIdentifiersCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(compoundIdentifierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(compoundURILabel)
                    .addComponent(compoundUriHint))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datasetUriValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        compoundSynonymsPanel.setName("compoundSynonymsPanel"); // NOI18N

        compoundNamesScrollable.setName("compoundNamesScrollable"); // NOI18N

        compoundSynonymsList.setModel(new javax.swing.DefaultListModel() //{
            //    String[] strings = { "Benzen", "HexaCarbonoCycloSomething", "Benzol", "Benzosomething", "SomeOtherName" };
            //    public int getSize() { return strings.length; }
            //    public Object getElementAt(int i) { return strings[i]; }
            //}
    );
    compoundSynonymsList.setName("compoundSynonymsList"); // NOI18N
    compoundNamesScrollable.setViewportView(compoundSynonymsList);

    removeCompoundSynonym.setIcon(resourceMap.getIcon("removeCompoundSynonym.icon")); // NOI18N
    removeCompoundSynonym.setText(resourceMap.getString("removeCompoundSynonym.text")); // NOI18N
    removeCompoundSynonym.setName("removeCompoundSynonym"); // NOI18N
    removeCompoundSynonym.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeCompoundSynonymActionPerformed(evt);
        }
    });

    addCompoundSynonym.setIcon(resourceMap.getIcon("addCompoundSynonym.icon")); // NOI18N
    addCompoundSynonym.setText(resourceMap.getString("addCompoundSynonym.text")); // NOI18N
    addCompoundSynonym.setName("addCompoundSynonym"); // NOI18N
    addCompoundSynonym.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addCompoundSynonymActionPerformed(evt);
        }
    });

    compoundNameHint.setIcon(resourceMap.getIcon("compoundNameHint.icon")); // NOI18N
    compoundNameHint.setText(resourceMap.getString("compoundNameHint.text")); // NOI18N
    compoundNameHint.setName("compoundNameHint"); // NOI18N

    compoundSynonymsLabel.setFont(resourceMap.getFont("compoundSynonymsLabel.font")); // NOI18N
    compoundSynonymsLabel.setText(resourceMap.getString("compoundSynonymsLabel.text")); // NOI18N
    compoundSynonymsLabel.setName("compoundSynonymsLabel"); // NOI18N

    clearAllCompoundSynonyms.setIcon(resourceMap.getIcon("clearAllCompoundSynonyms.icon")); // NOI18N
    clearAllCompoundSynonyms.setText(resourceMap.getString("clearAllCompoundSynonyms.text")); // NOI18N
    clearAllCompoundSynonyms.setName("clearAllCompoundSynonyms"); // NOI18N
    clearAllCompoundSynonyms.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearAllCompoundSynonymsActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout compoundSynonymsPanelLayout = new javax.swing.GroupLayout(compoundSynonymsPanel);
    compoundSynonymsPanel.setLayout(compoundSynonymsPanelLayout);
    compoundSynonymsPanelLayout.setHorizontalGroup(
        compoundSynonymsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundSynonymsPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(compoundSynonymsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(compoundSynonymsPanelLayout.createSequentialGroup()
                    .addComponent(compoundSynonymsLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(compoundNameHint))
                .addComponent(compoundNamesScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(compoundSynonymsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(clearAllCompoundSynonyms, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addComponent(removeCompoundSynonym, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addComponent(addCompoundSynonym, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
            .addContainerGap())
    );
    compoundSynonymsPanelLayout.setVerticalGroup(
        compoundSynonymsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundSynonymsPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(compoundSynonymsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(compoundSynonymsLabel)
                .addComponent(compoundNameHint))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(compoundSynonymsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(compoundSynonymsPanelLayout.createSequentialGroup()
                    .addComponent(addCompoundSynonym)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(removeCompoundSynonym)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(clearAllCompoundSynonyms, 0, 0, Short.MAX_VALUE))
                .addComponent(compoundNamesScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    javax.swing.GroupLayout compoundDataInputPanelLayout = new javax.swing.GroupLayout(compoundDataInputPanel);
    compoundDataInputPanel.setLayout(compoundDataInputPanelLayout);
    compoundDataInputPanelLayout.setHorizontalGroup(
        compoundDataInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundDataInputPanelLayout.createSequentialGroup()
            .addGroup(compoundDataInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(compoundSynonymsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(compoundIdentifierPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
            .addContainerGap())
    );
    compoundDataInputPanelLayout.setVerticalGroup(
        compoundDataInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, compoundDataInputPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(compoundIdentifierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
            .addComponent(compoundSynonymsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    javax.swing.GroupLayout compoundInfoPanelLayout = new javax.swing.GroupLayout(compoundInfoPanel);
    compoundInfoPanel.setLayout(compoundInfoPanelLayout);
    compoundInfoPanelLayout.setHorizontalGroup(
        compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundInfoPanelLayout.createSequentialGroup()
            .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, compoundInfoPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(compoundDataInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(structureImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(compoundsToolbar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(30, 30, 30)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(395, 395, 395))
    );
    compoundInfoPanelLayout.setVerticalGroup(
        compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundInfoPanelLayout.createSequentialGroup()
            .addComponent(compoundsToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(compoundDataInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(structureImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    descriptorsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Descriptors"));
    descriptorsPanel.setName("descriptorsPanel"); // NOI18N

    descriptorsScrollable.setName("descriptorsScrollable"); // NOI18N

    descriptorsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Descriptor", "Value", "Units"
        }
    ));
    descriptorsTable.setAutoCreateRowSorter(true);
    descriptorsTable.setCellSelectionEnabled(true);
    descriptorsTable.setName("descriptorsTable"); // NOI18N
    descriptorsTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            descriptorsTableMouseClicked(evt);
        }
    });
    descriptorsScrollable.setViewportView(descriptorsTable);
    descriptorsTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("descriptorsTable.columnModel.title0")); // NOI18N
    descriptorsTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("descriptorsTable.columnModel.title1")); // NOI18N
    descriptorsTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("descriptorsTable.columnModel.title2")); // NOI18N

    descriptorsTableToolbar.setFloatable(false);
    descriptorsTableToolbar.setRollover(true);
    descriptorsTableToolbar.setName("descriptorsTableToolbar"); // NOI18N

    addDescriptorValueButton.setIcon(resourceMap.getIcon("addDescriptorValueButton.icon")); // NOI18N
    addDescriptorValueButton.setText(resourceMap.getString("addDescriptorValueButton.text")); // NOI18N
    addDescriptorValueButton.setToolTipText(resourceMap.getString("addDescriptorValueButton.toolTipText")); // NOI18N
    addDescriptorValueButton.setFocusable(false);
    addDescriptorValueButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    addDescriptorValueButton.setName("addDescriptorValueButton"); // NOI18N
    addDescriptorValueButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    addDescriptorValueButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addDescriptorValueButtonActionPerformed(evt);
        }
    });
    descriptorsTableToolbar.add(addDescriptorValueButton);

    removeDescriptorValueButton.setIcon(resourceMap.getIcon("removeDescriptorValueButton.icon")); // NOI18N
    removeDescriptorValueButton.setText(resourceMap.getString("removeDescriptorValueButton.text")); // NOI18N
    removeDescriptorValueButton.setFocusable(false);
    removeDescriptorValueButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    removeDescriptorValueButton.setName("removeDescriptorValueButton"); // NOI18N
    removeDescriptorValueButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    removeDescriptorValueButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeDescriptorValueButtonActionPerformed(evt);
        }
    });
    descriptorsTableToolbar.add(removeDescriptorValueButton);

    jSeparator5.setName("jSeparator5"); // NOI18N
    descriptorsTableToolbar.add(jSeparator5);

    jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
    jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
    jButton2.setFocusable(false);
    jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton2.setName("jButton2"); // NOI18N
    jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });
    descriptorsTableToolbar.add(jButton2);

    jSeparator8.setName("jSeparator8"); // NOI18N
    descriptorsTableToolbar.add(jSeparator8);

    clearAllDescriptorsButton.setIcon(resourceMap.getIcon("clearAllDescriptorsButton.icon")); // NOI18N
    clearAllDescriptorsButton.setText(resourceMap.getString("clearAllDescriptorsButton.text")); // NOI18N
    clearAllDescriptorsButton.setFocusable(false);
    clearAllDescriptorsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    clearAllDescriptorsButton.setName("clearAllDescriptorsButton"); // NOI18N
    clearAllDescriptorsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    clearAllDescriptorsButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearAllDescriptorsButtonActionPerformed(evt);
        }
    });
    descriptorsTableToolbar.add(clearAllDescriptorsButton);

    javax.swing.GroupLayout descriptorsPanelLayout = new javax.swing.GroupLayout(descriptorsPanel);
    descriptorsPanel.setLayout(descriptorsPanelLayout);
    descriptorsPanelLayout.setHorizontalGroup(
        descriptorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(descriptorsTableToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
        .addComponent(descriptorsScrollable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
    );
    descriptorsPanelLayout.setVerticalGroup(
        descriptorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(descriptorsPanelLayout.createSequentialGroup()
            .addComponent(descriptorsTableToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(descriptorsScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addContainerGap())
    );

    stereoChemFeaturesDiscussionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("stereoChemFeaturesDiscussionPanel.border.title"))); // NOI18N
    stereoChemFeaturesDiscussionPanel.setName("stereoChemFeaturesDiscussionPanel"); // NOI18N

    stereoChemHintLabel.setText(resourceMap.getString("stereoChemHintLabel.text")); // NOI18N
    stereoChemHintLabel.setName("stereoChemHintLabel"); // NOI18N

    stereoChemScollPane.setName("stereoChemScollPane"); // NOI18N

    stereoChemDiscussionTextArea.setColumns(20);
    stereoChemDiscussionTextArea.setLineWrap(true);
    stereoChemDiscussionTextArea.setRows(5);
    stereoChemDiscussionTextArea.setName("stereoChemDiscussionTextArea"); // NOI18N
    stereoChemScollPane.setViewportView(stereoChemDiscussionTextArea);

    stereoChemHintLamp.setIcon(resourceMap.getIcon("stereoChemHintLamp.icon")); // NOI18N
    stereoChemHintLamp.setText(resourceMap.getString("stereoChemHintLamp.text")); // NOI18N
    stereoChemHintLamp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    stereoChemHintLamp.setName("stereoChemHintLamp"); // NOI18N

    javax.swing.GroupLayout stereoChemFeaturesDiscussionPanelLayout = new javax.swing.GroupLayout(stereoChemFeaturesDiscussionPanel);
    stereoChemFeaturesDiscussionPanel.setLayout(stereoChemFeaturesDiscussionPanelLayout);
    stereoChemFeaturesDiscussionPanelLayout.setHorizontalGroup(
        stereoChemFeaturesDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(stereoChemFeaturesDiscussionPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(stereoChemFeaturesDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stereoChemScollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addGroup(stereoChemFeaturesDiscussionPanelLayout.createSequentialGroup()
                    .addComponent(stereoChemHintLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                    .addComponent(stereoChemHintLamp)))
            .addContainerGap())
    );
    stereoChemFeaturesDiscussionPanelLayout.setVerticalGroup(
        stereoChemFeaturesDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(stereoChemFeaturesDiscussionPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(stereoChemFeaturesDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(stereoChemHintLamp)
                .addComponent(stereoChemHintLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(stereoChemScollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
            .addContainerGap())
    );

    javax.swing.GroupLayout compoundPanelLayout = new javax.swing.GroupLayout(compoundPanel);
    compoundPanel.setLayout(compoundPanelLayout);
    compoundPanelLayout.setHorizontalGroup(
        compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(compoundInfoPanel, javax.swing.GroupLayout.Alignment.LEADING, 0, 1041, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, compoundPanelLayout.createSequentialGroup()
                    .addComponent(descriptorsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(stereoChemFeaturesDiscussionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addContainerGap(14, Short.MAX_VALUE))
    );
    compoundPanelLayout.setVerticalGroup(
        compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(compoundInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(stereoChemFeaturesDiscussionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(descriptorsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );

    basicTabbedPanel.addTab(resourceMap.getString("compoundPanel.TabConstraints.tabTitle"), compoundPanel); // NOI18N

    reportGeneralInfoPanel.setName("reportGeneralInfoPanel"); // NOI18N

    qprfInfoTabbedSubPanel.setToolTipText(null);
    qprfInfoTabbedSubPanel.setName("qprfInfoTabbedSubPanel"); // NOI18N

    generalInfoPanel.setName("generalInfoPanel"); // NOI18N

    authorsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("authorsPanel.border.title"))); // NOI18N
    authorsPanel.setName("authorsPanel"); // NOI18N

    authorsTableScrollable.setName("authorsTableScrollable"); // NOI18N

    authorsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Name", "Affiliation", "Contact", "URL", "e-mail", "Address"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }
    });
    authorsTable.setAutoCreateRowSorter(true);
    authorsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    authorsTable.setName("authorsTable"); // NOI18N
    authorsTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            authorsTableMouseClicked(evt);
        }
    });
    authorsTable.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            authorsTableKeyPressed(evt);
        }
    });
    authorsTableScrollable.setViewportView(authorsTable);
    authorsTable.getColumnModel().getColumn(0).setPreferredWidth(350);
    authorsTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("authorsTable.columnModel.title0")); // NOI18N
    authorsTable.getColumnModel().getColumn(1).setPreferredWidth(200);
    authorsTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("authorsTable.columnModel.title1")); // NOI18N
    authorsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
    authorsTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("authorsTable.columnModel.title2")); // NOI18N
    authorsTable.getColumnModel().getColumn(3).setPreferredWidth(200);
    authorsTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("authorsTable.columnModel.title3")); // NOI18N
    authorsTable.getColumnModel().getColumn(4).setPreferredWidth(150);
    authorsTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("authorsTable.columnModel.title4")); // NOI18N
    authorsTable.getColumnModel().getColumn(5).setPreferredWidth(250);
    authorsTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("authorsTable.columnModel.title5")); // NOI18N

    authorsToolbar.setFloatable(false);
    authorsToolbar.setRollover(true);
    authorsToolbar.setName("authorsToolbar"); // NOI18N

    appendRowButton.setIcon(resourceMap.getIcon("appendRowButton.icon")); // NOI18N
    appendRowButton.setText(resourceMap.getString("appendRowButton.text")); // NOI18N
    appendRowButton.setToolTipText(resourceMap.getString("appendRowButton.toolTipText")); // NOI18N
    appendRowButton.setFocusable(false);
    appendRowButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    appendRowButton.setName("appendRowButton"); // NOI18N
    appendRowButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    appendRowButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            appendRowButtonActionPerformed(evt);
        }
    });
    authorsToolbar.add(appendRowButton);

    removeSelectedRowButton.setIcon(resourceMap.getIcon("removeSelectedRowButton.icon")); // NOI18N
    removeSelectedRowButton.setText(resourceMap.getString("removeSelectedRowButton.text")); // NOI18N
    removeSelectedRowButton.setToolTipText(resourceMap.getString("removeSelectedRowButton.toolTipText")); // NOI18N
    removeSelectedRowButton.setFocusable(false);
    removeSelectedRowButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    removeSelectedRowButton.setName("removeSelectedRowButton"); // NOI18N
    removeSelectedRowButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    removeSelectedRowButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeSelectedRowButtonActionPerformed(evt);
        }
    });
    authorsToolbar.add(removeSelectedRowButton);

    moveUpRowButton.setIcon(resourceMap.getIcon("moveUpRowButton.icon")); // NOI18N
    moveUpRowButton.setText(resourceMap.getString("moveUpRowButton.text")); // NOI18N
    moveUpRowButton.setToolTipText(resourceMap.getString("moveUpRowButton.toolTipText")); // NOI18N
    moveUpRowButton.setFocusable(false);
    moveUpRowButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    moveUpRowButton.setName("moveUpRowButton"); // NOI18N
    moveUpRowButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    moveUpRowButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            moveUpRowButtonActionPerformed(evt);
        }
    });
    authorsToolbar.add(moveUpRowButton);

    moveDownRowButton.setIcon(resourceMap.getIcon("moveDownRowButton.icon")); // NOI18N
    moveDownRowButton.setText(resourceMap.getString("moveDownRowButton.text")); // NOI18N
    moveDownRowButton.setToolTipText(resourceMap.getString("moveDownRowButton.toolTipText")); // NOI18N
    moveDownRowButton.setFocusable(false);
    moveDownRowButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    moveDownRowButton.setName("moveDownRowButton"); // NOI18N
    moveDownRowButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    moveDownRowButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            moveDownRowButtonActionPerformed(evt);
        }
    });
    authorsToolbar.add(moveDownRowButton);

    jSeparator4.setName("jSeparator4"); // NOI18N
    authorsToolbar.add(jSeparator4);

    authorsWizardButton.setIcon(resourceMap.getIcon("authorsWizardButton.icon")); // NOI18N
    authorsWizardButton.setText(resourceMap.getString("authorsWizardButton.text")); // NOI18N
    authorsWizardButton.setToolTipText(resourceMap.getString("authorsWizardButton.toolTipText")); // NOI18N
    authorsWizardButton.setFocusable(false);
    authorsWizardButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    authorsWizardButton.setName("authorsWizardButton"); // NOI18N
    authorsWizardButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    authorsWizardButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            authorsWizardButtonActionPerformed(evt);
        }
    });
    authorsToolbar.add(authorsWizardButton);

    jSeparator7.setName("jSeparator7"); // NOI18N
    authorsToolbar.add(jSeparator7);

    clearAllAuthorsButton.setIcon(resourceMap.getIcon("clearAllAuthorsButton.icon")); // NOI18N
    clearAllAuthorsButton.setText(resourceMap.getString("clearAllAuthorsButton.text")); // NOI18N
    clearAllAuthorsButton.setToolTipText(resourceMap.getString("clearAllAuthorsButton.toolTipText")); // NOI18N
    clearAllAuthorsButton.setFocusable(false);
    clearAllAuthorsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    clearAllAuthorsButton.setName("clearAllAuthorsButton"); // NOI18N
    clearAllAuthorsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    clearAllAuthorsButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearAllAuthorsButtonActionPerformed(evt);
        }
    });
    authorsToolbar.add(clearAllAuthorsButton);

    javax.swing.GroupLayout authorsPanelLayout = new javax.swing.GroupLayout(authorsPanel);
    authorsPanel.setLayout(authorsPanelLayout);
    authorsPanelLayout.setHorizontalGroup(
        authorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(authorsTableScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
        .addComponent(authorsToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
    );
    authorsPanelLayout.setVerticalGroup(
        authorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(authorsPanelLayout.createSequentialGroup()
            .addComponent(authorsToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(1, 1, 1)
            .addComponent(authorsTableScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
    );

    datePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("datePanel.border.title"))); // NOI18N
    datePanel.setName("datePanel"); // NOI18N

    jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
    jLabel1.setName("jLabel1"); // NOI18N

    jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
    jLabel2.setName("jLabel2"); // NOI18N

    jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
    jLabel3.setName("jLabel3"); // NOI18N

    java.util.Calendar calendar = java.util.Calendar.getInstance();
    int currentYear = calendar.get(java.util.Calendar.YEAR);
    int yearsLister = 30;
    String[] years = new String[yearsLister];
    for (int i=0 ; i < yearsLister ; i++){
        years[i]=Integer.toString(currentYear - i);
    }
    qprfYearCombo.setModel(new javax.swing.DefaultComboBoxModel(years));
    qprfYearCombo.setName("qprfYearCombo"); // NOI18N

    String[] months = {"January", "February",
        "March", "April", "May", "June", "July",
        "August", "September", "October", "November",
        "December"};
    qprfMonthCombo.setModel(new javax.swing.DefaultComboBoxModel(months));
    qprfMonthCombo.setName("qprfMonthCombo"); // NOI18N
    qprfMonthCombo.setSelectedItem(months[java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)]);

    java.util.ArrayList<String> listOfDays = new java.util.ArrayList<String>();
    for (int i=1;i<=31;i++){
        listOfDays.add(Integer.toString(i));
    }
    Object[] selectableDays = listOfDays.toArray();
    qprfDayCombo.setModel(new javax.swing.DefaultComboBoxModel(selectableDays));
    qprfDayCombo.setSelectedIndex(java.util.Calendar.getInstance().get(java.util.Calendar.DATE)-1);
    qprfDayCombo.setName("qprfDayCombo"); // NOI18N

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getActionMap(ReportInternalFrame.class, this);
    useCurrentDateForReport.setAction(actionMap.get("enableDisableDate")); // NOI18N
    useCurrentDateForReport.setText(resourceMap.getString("useCurrentDateForReport.text")); // NOI18N
    useCurrentDateForReport.setToolTipText(resourceMap.getString("useCurrentDateForReport.toolTipText")); // NOI18N
    useCurrentDateForReport.setName("useCurrentDateForReport"); // NOI18N

    javax.swing.GroupLayout datePanelLayout = new javax.swing.GroupLayout(datePanel);
    datePanel.setLayout(datePanelLayout);
    datePanelLayout.setHorizontalGroup(
        datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(datePanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(useCurrentDateForReport)
            .addGap(77, 77, 77)
            .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel3)
                .addComponent(jLabel2)
                .addComponent(jLabel1))
            .addGap(18, 18, 18)
            .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(qprfDayCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(qprfMonthCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(qprfYearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(638, Short.MAX_VALUE))
    );
    datePanelLayout.setVerticalGroup(
        datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(datePanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datePanelLayout.createSequentialGroup()
                    .addComponent(useCurrentDateForReport)
                    .addGap(69, 69, 69))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datePanelLayout.createSequentialGroup()
                    .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(qprfYearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(qprfMonthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(qprfDayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(22, Short.MAX_VALUE))
    );

    jLabel6.setIcon(resourceMap.getIcon("jLabel6.icon")); // NOI18N
    jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
    jLabel6.setName("jLabel6"); // NOI18N

    javax.swing.GroupLayout generalInfoPanelLayout = new javax.swing.GroupLayout(generalInfoPanel);
    generalInfoPanel.setLayout(generalInfoPanelLayout);
    generalInfoPanelLayout.setHorizontalGroup(
        generalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(generalInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(generalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(datePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(authorsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );
    generalInfoPanelLayout.setVerticalGroup(
        generalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(generalInfoPanelLayout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(authorsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(datePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(253, Short.MAX_VALUE))
    );

    qprfInfoTabbedSubPanel.addTab(resourceMap.getString("generalInfoPanel.TabConstraints.tabTitle"), generalInfoPanel); // NOI18N

    otherInfoPanel.setName("otherInfoPanel"); // NOI18N

    metaInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("metaInfoPanel.border.title"))); // NOI18N
    metaInfoPanel.setName("metaInfoPanel"); // NOI18N

    titleLabel.setFont(resourceMap.getFont("titleLabel.font")); // NOI18N
    titleLabel.setText(resourceMap.getString("titleLabel.text")); // NOI18N
    titleLabel.setToolTipText(resourceMap.getString("titleLabel.toolTipText")); // NOI18N
    titleLabel.setName("titleLabel"); // NOI18N

    qprfDescriptionLabel.setFont(resourceMap.getFont("qprfDescriptionLabel.font")); // NOI18N
    qprfDescriptionLabel.setText(resourceMap.getString("qprfDescriptionLabel.text")); // NOI18N
    qprfDescriptionLabel.setToolTipText(resourceMap.getString("qprfDescriptionLabel.toolTipText")); // NOI18N
    qprfDescriptionLabel.setName("qprfDescriptionLabel"); // NOI18N

    qprfCommentsLabel.setFont(resourceMap.getFont("qprfCommentsLabel.font")); // NOI18N
    qprfCommentsLabel.setText(resourceMap.getString("qprfCommentsLabel.text")); // NOI18N
    qprfCommentsLabel.setToolTipText(resourceMap.getString("qprfCommentsLabel.toolTipText")); // NOI18N
    qprfCommentsLabel.setName("qprfCommentsLabel"); // NOI18N

    qprfDescriptionHintLabel.setIcon(resourceMap.getIcon("qprfDescriptionHintLabel.icon")); // NOI18N
    qprfDescriptionHintLabel.setText(resourceMap.getString("qprfDescriptionHintLabel.text")); // NOI18N
    qprfDescriptionHintLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    qprfDescriptionHintLabel.setName("qprfDescriptionHintLabel"); // NOI18N
    qprfDescriptionHintLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            qprfDescriptionHintLabelMouseClicked(evt);
        }
    });

    qprfCommentsHintLabel.setIcon(resourceMap.getIcon("qprfCommentsHintLabel.icon")); // NOI18N
    qprfCommentsHintLabel.setText(resourceMap.getString("qprfCommentsHintLabel.text")); // NOI18N
    qprfCommentsHintLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    qprfCommentsHintLabel.setName("qprfCommentsHintLabel"); // NOI18N
    qprfCommentsHintLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            qprfCommentsHintLabelMouseClicked(evt);
        }
    });

    qprfTitleHintLabel.setIcon(resourceMap.getIcon("qprfTitleHintLabel.icon")); // NOI18N
    qprfTitleHintLabel.setText(resourceMap.getString("qprfTitleHintLabel.text")); // NOI18N
    qprfTitleHintLabel.setName("qprfTitleHintLabel"); // NOI18N

    qprfReportTitleScrollable.setName("qprfReportTitleScrollable"); // NOI18N

    qprfReportTitleTextArea.setColumns(20);
    qprfReportTitleTextArea.setEditable(false);
    qprfReportTitleTextArea.setRows(5);
    qprfReportTitleTextArea.setText(resourceMap.getString("qprfReportTitleTextArea.text")); // NOI18N
    qprfReportTitleTextArea.setName("qprfReportTitleTextArea"); // NOI18N
    qprfReportTitleScrollable.setViewportView(qprfReportTitleTextArea);

    qprfReportDescriptionScollable.setName("qprfReportDescriptionScollable"); // NOI18N

    qprfReportDescriptionTextArea.setColumns(20);
    qprfReportDescriptionTextArea.setLineWrap(true);
    qprfReportDescriptionTextArea.setRows(5);
    qprfReportDescriptionTextArea.setName("qprfReportDescriptionTextArea"); // NOI18N
    qprfReportDescriptionScollable.setViewportView(qprfReportDescriptionTextArea);

    qprfReportCommentsScrollable.setName("qprfReportCommentsScrollable"); // NOI18N

    qprfReportCommentsTextArea.setColumns(20);
    qprfReportCommentsTextArea.setLineWrap(true);
    qprfReportCommentsTextArea.setRows(5);
    qprfReportCommentsTextArea.setName("qprfReportCommentsTextArea"); // NOI18N
    qprfReportCommentsScrollable.setViewportView(qprfReportCommentsTextArea);

    javax.swing.GroupLayout metaInfoPanelLayout = new javax.swing.GroupLayout(metaInfoPanel);
    metaInfoPanel.setLayout(metaInfoPanelLayout);
    metaInfoPanelLayout.setHorizontalGroup(
        metaInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(metaInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(metaInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(qprfReportTitleScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
                .addGroup(metaInfoPanelLayout.createSequentialGroup()
                    .addComponent(titleLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(qprfTitleHintLabel))
                .addGroup(metaInfoPanelLayout.createSequentialGroup()
                    .addComponent(qprfDescriptionLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(qprfDescriptionHintLabel))
                .addGroup(metaInfoPanelLayout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(qprfCommentsLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(qprfCommentsHintLabel))
                .addComponent(qprfReportDescriptionScollable, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
                .addComponent(qprfReportCommentsScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE))
            .addContainerGap())
    );
    metaInfoPanelLayout.setVerticalGroup(
        metaInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(metaInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(metaInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(titleLabel)
                .addComponent(qprfTitleHintLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(qprfReportTitleScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(metaInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(qprfDescriptionLabel)
                .addComponent(qprfDescriptionHintLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(qprfReportDescriptionScollable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(metaInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(qprfCommentsLabel)
                .addComponent(qprfCommentsHintLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(qprfReportCommentsScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabel7.setIcon(resourceMap.getIcon("jLabel7.icon")); // NOI18N
    jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
    jLabel7.setName("jLabel7"); // NOI18N

    javax.swing.GroupLayout otherInfoPanelLayout = new javax.swing.GroupLayout(otherInfoPanel);
    otherInfoPanel.setLayout(otherInfoPanelLayout);
    otherInfoPanelLayout.setHorizontalGroup(
        otherInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(otherInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(otherInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(metaInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7))
            .addContainerGap())
    );
    otherInfoPanelLayout.setVerticalGroup(
        otherInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(otherInfoPanelLayout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(metaInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(186, Short.MAX_VALUE))
    );

    qprfInfoTabbedSubPanel.addTab("Other", otherInfoPanel);

    javax.swing.GroupLayout reportGeneralInfoPanelLayout = new javax.swing.GroupLayout(reportGeneralInfoPanel);
    reportGeneralInfoPanel.setLayout(reportGeneralInfoPanelLayout);
    reportGeneralInfoPanelLayout.setHorizontalGroup(
        reportGeneralInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(qprfInfoTabbedSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
    );
    reportGeneralInfoPanelLayout.setVerticalGroup(
        reportGeneralInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(qprfInfoTabbedSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
    );

    basicTabbedPanel.addTab(resourceMap.getString("reportGeneralInfoPanel.TabConstraints.tabTitle"), reportGeneralInfoPanel); // NOI18N

    reportPredictionPanel.setName("reportPredictionPanel"); // NOI18N

    jTabbedPane1.setName("jTabbedPane1"); // NOI18N

    modelPanel.setName("modelPanel"); // NOI18N

    modelInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("modelInfoPanel.border.title"))); // NOI18N
    modelInfoPanel.setName("modelInfoPanel"); // NOI18N

    modelUriLabel.setFont(resourceMap.getFont("modelUriLabel.font")); // NOI18N
    modelUriLabel.setIcon(resourceMap.getIcon("modelUriLabel.icon")); // NOI18N
    modelUriLabel.setText(resourceMap.getString("modelUriLabel.text")); // NOI18N
    modelUriLabel.setName("modelUriLabel"); // NOI18N

    modelUriValue.setBackground(resourceMap.getColor("modelUriValue.background")); // NOI18N
    modelUriValue.setForeground(resourceMap.getColor("modelUriValue.foreground")); // NOI18N
    modelUriValue.setText(resourceMap.getString("modelUriValue.text")); // NOI18N
    modelUriValue.setName("modelUriValue"); // NOI18N

    copyModelUriButton.setIcon(resourceMap.getIcon("copyModelUriButton.icon")); // NOI18N
    copyModelUriButton.setText(resourceMap.getString("copyModelUriButton.text")); // NOI18N
    copyModelUriButton.setToolTipText(resourceMap.getString("copyModelUriButton.toolTipText")); // NOI18N
    copyModelUriButton.setName("copyModelUriButton"); // NOI18N

    trainingDatasetLabel.setIcon(resourceMap.getIcon("trainingDatasetLabel.icon")); // NOI18N
    trainingDatasetLabel.setText(resourceMap.getString("trainingDatasetLabel.text")); // NOI18N
    trainingDatasetLabel.setName("trainingDatasetLabel"); // NOI18N

    trainingDatasetValueTextField.setForeground(resourceMap.getColor("trainingDatasetValueTextField.foreground")); // NOI18N
    trainingDatasetValueTextField.setText(resourceMap.getString("trainingDatasetValueTextField.text")); // NOI18N
    trainingDatasetValueTextField.setName("trainingDatasetValueTextField"); // NOI18N

    copyDatasetButton.setIcon(resourceMap.getIcon("copyDatasetButton.icon")); // NOI18N
    copyDatasetButton.setText(resourceMap.getString("copyDatasetButton.text")); // NOI18N
    copyDatasetButton.setToolTipText(resourceMap.getString("copyDatasetButton.toolTipText")); // NOI18N
    copyDatasetButton.setName("copyDatasetButton"); // NOI18N

    modelAndTrainingDSHeader.setFont(resourceMap.getFont("modelAndTrainingDSHeader.font")); // NOI18N
    modelAndTrainingDSHeader.setText(resourceMap.getString("modelAndTrainingDSHeader.text")); // NOI18N
    modelAndTrainingDSHeader.setName("modelAndTrainingDSHeader"); // NOI18N

    trainingAlgorithmHeader.setFont(resourceMap.getFont("trainingAlgorithmHeader.font")); // NOI18N
    trainingAlgorithmHeader.setText(resourceMap.getString("trainingAlgorithmHeader.text")); // NOI18N
    trainingAlgorithmHeader.setName("trainingAlgorithmHeader"); // NOI18N

    algorithmNameLabel.setText(resourceMap.getString("algorithmNameLabel.text")); // NOI18N
    algorithmNameLabel.setName("algorithmNameLabel"); // NOI18N

    modelUriDetailsButton.setIcon(resourceMap.getIcon("modelUriDetailsButton.icon")); // NOI18N
    modelUriDetailsButton.setText(resourceMap.getString("modelUriDetailsButton.text")); // NOI18N
    modelUriDetailsButton.setToolTipText(resourceMap.getString("modelUriDetailsButton.toolTipText")); // NOI18N
    modelUriDetailsButton.setName("modelUriDetailsButton"); // NOI18N
    modelUriDetailsButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            modelUriDetailsButtonActionPerformed(evt);
        }
    });

    datasetDetailsButton.setIcon(resourceMap.getIcon("datasetDetailsButton.icon")); // NOI18N
    datasetDetailsButton.setText(resourceMap.getString("datasetDetailsButton.text")); // NOI18N
    datasetDetailsButton.setToolTipText(resourceMap.getString("datasetDetailsButton.toolTipText")); // NOI18N
    datasetDetailsButton.setName("datasetDetailsButton"); // NOI18N

    algorithmNameValue.setText(resourceMap.getString("algorithmNameValue.text")); // NOI18N
    algorithmNameValue.setName("algorithmNameValue"); // NOI18N

    copyAlgorithmName.setIcon(resourceMap.getIcon("copyAlgorithmName.icon")); // NOI18N
    copyAlgorithmName.setText(resourceMap.getString("copyAlgorithmName.text")); // NOI18N
    copyAlgorithmName.setToolTipText(resourceMap.getString("copyAlgorithmName.toolTipText")); // NOI18N
    copyAlgorithmName.setName("copyAlgorithmName"); // NOI18N

    algorithmUriLabel.setIcon(resourceMap.getIcon("algorithmUriLabel.icon")); // NOI18N
    algorithmUriLabel.setText(resourceMap.getString("algorithmUriLabel.text")); // NOI18N
    algorithmUriLabel.setName("algorithmUriLabel"); // NOI18N

    algorithmUriValue.setForeground(resourceMap.getColor("algorithmUriValue.foreground")); // NOI18N
    algorithmUriValue.setText(resourceMap.getString("algorithmUriValue.text")); // NOI18N
    algorithmUriValue.setName("algorithmUriValue"); // NOI18N

    copyAlgorithmUriButton.setIcon(resourceMap.getIcon("copyAlgorithmUriButton.icon")); // NOI18N
    copyAlgorithmUriButton.setText(resourceMap.getString("copyAlgorithmUriButton.text")); // NOI18N
    copyAlgorithmUriButton.setToolTipText(resourceMap.getString("copyAlgorithmUriButton.toolTipText")); // NOI18N
    copyAlgorithmUriButton.setName("copyAlgorithmUriButton"); // NOI18N

    algorithmDetailsButton.setIcon(resourceMap.getIcon("algorithmDetailsButton.icon")); // NOI18N
    algorithmDetailsButton.setText(resourceMap.getString("algorithmDetailsButton.text")); // NOI18N
    algorithmDetailsButton.setToolTipText(resourceMap.getString("algorithmDetailsButton.toolTipText")); // NOI18N
    algorithmDetailsButton.setName("algorithmDetailsButton"); // NOI18N
    algorithmDetailsButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            algorithmDetailsButtonActionPerformed(evt);
        }
    });

    predictedFeatureHeadline.setFont(resourceMap.getFont("predictedFeatureHeadline.font")); // NOI18N
    predictedFeatureHeadline.setText(resourceMap.getString("predictedFeatureHeadline.text")); // NOI18N
    predictedFeatureHeadline.setName("predictedFeatureHeadline"); // NOI18N

    predFeatureNameLabel.setText(resourceMap.getString("predFeatureNameLabel.text")); // NOI18N
    predFeatureNameLabel.setName("predFeatureNameLabel"); // NOI18N

    predictedFeatureNameValue.setText(resourceMap.getString("predictedFeatureNameValue.text")); // NOI18N
    predictedFeatureNameValue.setToolTipText(resourceMap.getString("predictedFeatureNameValue.toolTipText")); // NOI18N
    predictedFeatureNameValue.setName("predictedFeatureNameValue"); // NOI18N

    copyPredFeatureNameButton.setIcon(resourceMap.getIcon("copyPredFeatureNameButton.icon")); // NOI18N
    copyPredFeatureNameButton.setText(resourceMap.getString("copyPredFeatureNameButton.text")); // NOI18N
    copyPredFeatureNameButton.setName("copyPredFeatureNameButton"); // NOI18N

    predFeatureResourceLabel.setIcon(resourceMap.getIcon("predFeatureResourceLabel.icon")); // NOI18N
    predFeatureResourceLabel.setText(resourceMap.getString("predFeatureResourceLabel.text")); // NOI18N
    predFeatureResourceLabel.setName("predFeatureResourceLabel"); // NOI18N

    predFeatureUriValue.setForeground(resourceMap.getColor("predFeatureUriValue.foreground")); // NOI18N
    predFeatureUriValue.setText(resourceMap.getString("predFeatureUriValue.text")); // NOI18N
    predFeatureUriValue.setName("predFeatureUriValue"); // NOI18N

    copyPredFeatureUriButton.setIcon(resourceMap.getIcon("copyPredFeatureUriButton.icon")); // NOI18N
    copyPredFeatureUriButton.setText(resourceMap.getString("copyPredFeatureUriButton.text")); // NOI18N
    copyPredFeatureUriButton.setToolTipText(resourceMap.getString("copyPredFeatureUriButton.toolTipText")); // NOI18N
    copyPredFeatureUriButton.setName("copyPredFeatureUriButton"); // NOI18N

    predFeatureDetailsButton.setIcon(resourceMap.getIcon("predFeatureDetailsButton.icon")); // NOI18N
    predFeatureDetailsButton.setText(resourceMap.getString("predFeatureDetailsButton.text")); // NOI18N
    predFeatureDetailsButton.setToolTipText(resourceMap.getString("predFeatureDetailsButton.toolTipText")); // NOI18N
    predFeatureDetailsButton.setName("predFeatureDetailsButton"); // NOI18N
    predFeatureDetailsButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            predFeatureDetailsButtonActionPerformed(evt);
        }
    });

    modelToolbar.setFloatable(false);
    modelToolbar.setRollover(true);
    modelToolbar.setName("modelToolbar"); // NOI18N

    modelInfoToolButton.setIcon(resourceMap.getIcon("modelInfoToolButton.icon")); // NOI18N
    modelInfoToolButton.setText(resourceMap.getString("modelInfoToolButton.text")); // NOI18N
    modelInfoToolButton.setFocusable(false);
    modelInfoToolButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    modelInfoToolButton.setName("modelInfoToolButton"); // NOI18N
    modelInfoToolButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    modelInfoToolButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            modelInfoToolButtonActionPerformed(evt);
        }
    });
    modelToolbar.add(modelInfoToolButton);

    algorithmInfoToolButton.setIcon(resourceMap.getIcon("algorithmInfoToolButton.icon")); // NOI18N
    algorithmInfoToolButton.setText(resourceMap.getString("algorithmInfoToolButton.text")); // NOI18N
    algorithmInfoToolButton.setFocusable(false);
    algorithmInfoToolButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    algorithmInfoToolButton.setName("algorithmInfoToolButton"); // NOI18N
    algorithmInfoToolButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    algorithmInfoToolButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            algorithmInfoToolButtonActionPerformed(evt);
        }
    });
    modelToolbar.add(algorithmInfoToolButton);

    predictedFeatureToolButton.setIcon(resourceMap.getIcon("predictedFeatureToolButton.icon")); // NOI18N
    predictedFeatureToolButton.setText(resourceMap.getString("predictedFeatureToolButton.text")); // NOI18N
    predictedFeatureToolButton.setFocusable(false);
    predictedFeatureToolButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    predictedFeatureToolButton.setName("predictedFeatureToolButton"); // NOI18N
    predictedFeatureToolButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    predictedFeatureToolButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            predictedFeatureToolButtonActionPerformed(evt);
        }
    });
    modelToolbar.add(predictedFeatureToolButton);

    dependentFeatureToolButton.setIcon(resourceMap.getIcon("dependentFeatureToolButton.icon")); // NOI18N
    dependentFeatureToolButton.setText(resourceMap.getString("dependentFeatureToolButton.text")); // NOI18N
    dependentFeatureToolButton.setFocusable(false);
    dependentFeatureToolButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    dependentFeatureToolButton.setName("dependentFeatureToolButton"); // NOI18N
    dependentFeatureToolButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    dependentFeatureToolButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            dependentFeatureToolButtonActionPerformed(evt);
        }
    });
    modelToolbar.add(dependentFeatureToolButton);

    jSeparator9.setName("jSeparator9"); // NOI18N
    modelToolbar.add(jSeparator9);

    downloadModelInfoButton.setIcon(resourceMap.getIcon("downloadModelInfoButton.icon")); // NOI18N
    downloadModelInfoButton.setText(resourceMap.getString("downloadModelInfoButton.text")); // NOI18N
    downloadModelInfoButton.setFocusable(false);
    downloadModelInfoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    downloadModelInfoButton.setName("downloadModelInfoButton"); // NOI18N
    downloadModelInfoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    downloadModelInfoButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            downloadModelInfoButtonActionPerformed(evt);
        }
    });
    modelToolbar.add(downloadModelInfoButton);

    jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
    jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
    jButton3.setFocusable(false);
    jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButton3.setName("jButton3"); // NOI18N
    jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    modelToolbar.add(jButton3);

    qmrfReportHeader.setFont(resourceMap.getFont("qmrfReportHeader.font")); // NOI18N
    qmrfReportHeader.setText(resourceMap.getString("qmrfReportHeader.text")); // NOI18N
    qmrfReportHeader.setName("qmrfReportHeader"); // NOI18N

    qmrfReportLabel.setText(resourceMap.getString("qmrfReportLabel.text")); // NOI18N
    qmrfReportLabel.setToolTipText(resourceMap.getString("qmrfReportLabel.toolTipText")); // NOI18N
    qmrfReportLabel.setName("qmrfReportLabel"); // NOI18N

    qmrfReportTextField.setText(resourceMap.getString("qmrfReportTextField.text")); // NOI18N
    qmrfReportTextField.setName("qmrfReportTextField"); // NOI18N

    modelVersionHeader.setFont(resourceMap.getFont("modelVersionHeader.font")); // NOI18N
    modelVersionHeader.setText(resourceMap.getString("modelVersionHeader.text")); // NOI18N
    modelVersionHeader.setName("modelVersionHeader"); // NOI18N

    modelVersionLabel.setText(resourceMap.getString("modelVersionLabel.text")); // NOI18N
    modelVersionLabel.setToolTipText(resourceMap.getString("modelVersionLabel.toolTipText")); // NOI18N
    modelVersionLabel.setName("modelVersionLabel"); // NOI18N

    modelVersionScrollPane.setName("modelVersionScrollPane"); // NOI18N

    modelVersionInfoTextArea.setColumns(20);
    modelVersionInfoTextArea.setLineWrap(true);
    modelVersionInfoTextArea.setRows(5);
    modelVersionInfoTextArea.setName("modelVersionInfoTextArea"); // NOI18N
    modelVersionScrollPane.setViewportView(modelVersionInfoTextArea);

    qmrfReportDiscussionScrollable.setName("qmrfReportDiscussionScrollable"); // NOI18N

    qmrfReportDiscussionTextArea.setColumns(20);
    qmrfReportDiscussionTextArea.setLineWrap(true);
    qmrfReportDiscussionTextArea.setRows(5);
    qmrfReportDiscussionTextArea.setName("qmrfReportDiscussionTextArea"); // NOI18N
    qmrfReportDiscussionScrollable.setViewportView(qmrfReportDiscussionTextArea);

    qmrfReportDiscussionLabel.setText(resourceMap.getString("qmrfReportDiscussionLabel.text")); // NOI18N
    qmrfReportDiscussionLabel.setToolTipText(resourceMap.getString("qmrfReportDiscussionLabel.toolTipText")); // NOI18N
    qmrfReportDiscussionLabel.setName("qmrfReportDiscussionLabel"); // NOI18N

    modelDatePanel.setName("modelDatePanel"); // NOI18N

    modelDateLabel.setText(resourceMap.getString("modelDateLabel.text")); // NOI18N
    modelDateLabel.setToolTipText(resourceMap.getString("modelDateLabel.toolTipText")); // NOI18N
    modelDateLabel.setName("modelDateLabel"); // NOI18N

    modelYearLabel.setText(resourceMap.getString("modelYearLabel.text")); // NOI18N
    modelYearLabel.setName("modelYearLabel"); // NOI18N

    java.util.Calendar mcalendar = java.util.Calendar.getInstance();
    int mcurrentYear = mcalendar.get(java.util.Calendar.YEAR);
    int myearsLister = 30;
    String[] myears = new String[myearsLister];
    for (int i=0 ; i < myearsLister ; i++){
        myears[i]=Integer.toString(mcurrentYear - i);
    }
    modelYearCombo.setModel(new javax.swing.DefaultComboBoxModel(myears));
    modelYearCombo.setName("modelYearCombo"); // NOI18N

    modelMonthLabel.setText(resourceMap.getString("modelMonthLabel.text")); // NOI18N
    modelMonthLabel.setName("modelMonthLabel"); // NOI18N

    String[] mmonths = {"January", "February",
        "March", "April", "May", "June", "July",
        "August", "September", "October", "November",
        "December"};
    modelMonthCombo.setModel(new javax.swing.DefaultComboBoxModel(mmonths));
    modelMonthCombo.setName("modelMonthCombo"); // NOI18N
    qprfMonthCombo.setSelectedItem(mmonths[java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)]);

    modelDayLabel.setText(resourceMap.getString("modelDayLabel.text")); // NOI18N
    modelDayLabel.setName("modelDayLabel"); // NOI18N

    java.util.ArrayList<String> mlistOfDays = new java.util.ArrayList<String>();
    for (int i=1;i<=31;i++){
        mlistOfDays.add(Integer.toString(i));
    }
    Object[] mselectableDays = mlistOfDays.toArray();
    modelDayCombo.setModel(new javax.swing.DefaultComboBoxModel(mselectableDays));
    modelDayCombo.setName("modelDayCombo"); // NOI18N

    modelCurrentDate.setAction(actionMap.get("enableDisableDate")); // NOI18N
    modelCurrentDate.setText(resourceMap.getString("modelCurrentDate.text")); // NOI18N
    modelCurrentDate.setToolTipText(resourceMap.getString("modelCurrentDate.toolTipText")); // NOI18N
    modelCurrentDate.setName("modelCurrentDate"); // NOI18N
    modelCurrentDate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            modelCurrentDateActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout modelDatePanelLayout = new javax.swing.GroupLayout(modelDatePanel);
    modelDatePanel.setLayout(modelDatePanelLayout);
    modelDatePanelLayout.setHorizontalGroup(
        modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(modelDatePanelLayout.createSequentialGroup()
            .addGroup(modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(modelDatePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(modelDatePanelLayout.createSequentialGroup()
                            .addGroup(modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(modelDayLabel)
                                .addComponent(modelMonthLabel)
                                .addComponent(modelYearLabel))
                            .addGap(18, 18, 18)
                            .addGroup(modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(modelDayCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(modelMonthCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(modelYearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(modelCurrentDate)))
                .addComponent(modelDateLabel))
            .addContainerGap(82, Short.MAX_VALUE))
    );
    modelDatePanelLayout.setVerticalGroup(
        modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(modelDatePanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(modelDateLabel)
            .addGap(12, 12, 12)
            .addComponent(modelCurrentDate)
            .addGap(26, 26, 26)
            .addGroup(modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(modelYearLabel)
                .addComponent(modelYearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(modelMonthLabel)
                .addComponent(modelMonthCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(modelDayLabel)
                .addComponent(modelDayCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(168, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout modelInfoPanelLayout = new javax.swing.GroupLayout(modelInfoPanel);
    modelInfoPanel.setLayout(modelInfoPanelLayout);
    modelInfoPanelLayout.setHorizontalGroup(
        modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(modelInfoPanelLayout.createSequentialGroup()
            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                    .addComponent(modelAndTrainingDSHeader)
                                    .addGap(434, 434, 434))
                                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, modelInfoPanelLayout.createSequentialGroup()
                                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(qmrfReportLabel)
                                                .addComponent(predFeatureNameLabel)
                                                .addComponent(algorithmUriLabel)
                                                .addComponent(predFeatureResourceLabel)
                                                .addComponent(qmrfReportDiscussionLabel))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(predFeatureUriValue, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                                .addComponent(predictedFeatureNameValue, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                                .addComponent(algorithmUriValue, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modelInfoPanelLayout.createSequentialGroup()
                                                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(qmrfReportTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                                                        .addComponent(qmrfReportDiscussionScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, modelInfoPanelLayout.createSequentialGroup()
                                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(trainingDatasetLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(modelUriLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                                    .addGap(86, 86, 86)
                                                    .addComponent(algorithmNameLabel)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(algorithmNameValue, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                                .addComponent(modelUriValue, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                                .addComponent(trainingDatasetValueTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))))
                                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                                    .addComponent(copyModelUriButton)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(modelUriDetailsButton))
                                                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                                    .addComponent(copyDatasetButton)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(datasetDetailsButton))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modelInfoPanelLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(copyAlgorithmName)
                                                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                                    .addComponent(copyAlgorithmUriButton)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(algorithmDetailsButton))
                                                .addComponent(copyPredFeatureNameButton)
                                                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                                    .addComponent(copyPredFeatureUriButton)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(predFeatureDetailsButton)))
                                            .addGap(42, 42, 42)))))
                            .addGap(12, 12, 12))
                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                            .addComponent(trainingAlgorithmHeader)
                            .addGap(545, 545, 545))
                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                            .addComponent(predictedFeatureHeadline)
                            .addGap(564, 564, 564))
                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                            .addComponent(qmrfReportHeader)
                            .addGap(540, 540, 540)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(modelVersionLabel)
                        .addComponent(modelVersionHeader)
                        .addComponent(modelDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modelVersionScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(11, 11, 11))
                .addComponent(modelToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE))
            .addContainerGap())
    );
    modelInfoPanelLayout.setVerticalGroup(
        modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(modelInfoPanelLayout.createSequentialGroup()
            .addComponent(modelToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                    .addComponent(modelAndTrainingDSHeader)
                    .addGap(27, 27, 27)
                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(copyModelUriButton)
                                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(modelUriLabel)
                                    .addComponent(modelUriValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(trainingDatasetLabel)
                                    .addComponent(trainingDatasetValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(copyDatasetButton)))
                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                            .addComponent(modelUriDetailsButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datasetDetailsButton)))
                    .addGap(18, 18, 18)
                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                    .addComponent(trainingAlgorithmHeader)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(algorithmNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(algorithmNameLabel)))
                                .addComponent(copyAlgorithmName))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(copyAlgorithmUriButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(algorithmUriLabel)
                                    .addComponent(algorithmUriValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(algorithmDetailsButton))
                    .addGap(18, 18, 18)
                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                            .addComponent(predictedFeatureHeadline)
                            .addGap(18, 18, 18)
                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(predFeatureNameLabel)
                                .addComponent(predictedFeatureNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(copyPredFeatureNameButton))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(predFeatureDetailsButton)
                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(predFeatureResourceLabel)
                                .addComponent(predFeatureUriValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(qmrfReportHeader)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(qmrfReportTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(qmrfReportLabel)))
                        .addComponent(copyPredFeatureUriButton))
                    .addGap(12, 12, 12)
                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(qmrfReportDiscussionLabel)
                        .addComponent(qmrfReportDiscussionScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(27, 27, 27))
                .addGroup(modelInfoPanelLayout.createSequentialGroup()
                    .addComponent(modelVersionHeader)
                    .addGap(18, 18, 18)
                    .addComponent(modelVersionLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(modelVersionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(modelDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addContainerGap())
    );

    javax.swing.GroupLayout modelPanelLayout = new javax.swing.GroupLayout(modelPanel);
    modelPanel.setLayout(modelPanelLayout);
    modelPanelLayout.setHorizontalGroup(
        modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(modelPanelLayout.createSequentialGroup()
            .addComponent(modelInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    modelPanelLayout.setVerticalGroup(
        modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(modelPanelLayout.createSequentialGroup()
            .addComponent(modelInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab(resourceMap.getString("modelPanel.TabConstraints.tabTitle"), modelPanel); // NOI18N

    predictionPanel.setName("predictionPanel"); // NOI18N

    predictionInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("predictionInfoPanel.border.title"))); // NOI18N
    predictionInfoPanel.setName("predictionInfoPanel"); // NOI18N

    predictionValueLabel.setText(resourceMap.getString("predictionValueLabel.text")); // NOI18N
    predictionValueLabel.setToolTipText(resourceMap.getString("predictionValueLabel.toolTipText")); // NOI18N
    predictionValueLabel.setName("predictionValueLabel"); // NOI18N

    predictionValueTextField.setForeground(resourceMap.getColor("predictionValueTextField.foreground")); // NOI18N
    predictionValueTextField.setText(resourceMap.getString("predictionValueTextField.text")); // NOI18N
    predictionValueTextField.setName("predictionValueTextField"); // NOI18N

    predictionUnitsTextField.setForeground(resourceMap.getColor("predictionUnitsTextField.foreground")); // NOI18N
    predictionUnitsTextField.setText(resourceMap.getString("predictionUnitsTextField.text")); // NOI18N
    predictionUnitsTextField.setToolTipText(resourceMap.getString("predictionUnitsTextField.toolTipText")); // NOI18N
    predictionUnitsTextField.setName("predictionUnitsTextField"); // NOI18N

    predictionUncertaintyLabel.setLabelFor(predictionUncertaintyTextArea);
    predictionUncertaintyLabel.setText(resourceMap.getString("predictionUncertaintyLabel.text")); // NOI18N
    predictionUncertaintyLabel.setToolTipText(resourceMap.getString("predictionUncertaintyLabel.toolTipText")); // NOI18N
    predictionUncertaintyLabel.setName("predictionUncertaintyLabel"); // NOI18N

    predictionUncertaintyScrollable.setName("predictionUncertaintyScrollable"); // NOI18N

    predictionUncertaintyTextArea.setColumns(20);
    predictionUncertaintyTextArea.setLineWrap(true);
    predictionUncertaintyTextArea.setRows(5);
    predictionUncertaintyTextArea.setName("predictionUncertaintyTextArea"); // NOI18N
    predictionUncertaintyScrollable.setViewportView(predictionUncertaintyTextArea);

    predictionCommentLabel.setLabelFor(predictionCommentTextArea);
    predictionCommentLabel.setText(resourceMap.getString("predictionCommentLabel.text")); // NOI18N
    predictionCommentLabel.setToolTipText(resourceMap.getString("predictionCommentLabel.toolTipText")); // NOI18N
    predictionCommentLabel.setName("predictionCommentLabel"); // NOI18N

    predictionCommentScrollable.setName("predictionCommentScrollable"); // NOI18N

    predictionCommentTextArea.setColumns(20);
    predictionCommentTextArea.setLineWrap(true);
    predictionCommentTextArea.setRows(5);
    predictionCommentTextArea.setName("predictionCommentTextArea"); // NOI18N
    predictionCommentScrollable.setViewportView(predictionCommentTextArea);

    experimentalValueLabel.setText(resourceMap.getString("experimentalValueLabel.text")); // NOI18N
    experimentalValueLabel.setToolTipText(resourceMap.getString("experimentalValueLabel.toolTipText")); // NOI18N
    experimentalValueLabel.setName("experimentalValueLabel"); // NOI18N

    exprerimentalValueTextField.setForeground(resourceMap.getColor("exprerimentalValueTextField.foreground")); // NOI18N
    exprerimentalValueTextField.setText(resourceMap.getString("exprerimentalValueTextField.text")); // NOI18N
    exprerimentalValueTextField.setName("exprerimentalValueTextField"); // NOI18N

    experimentalValueUnitsTextField.setText(resourceMap.getString("experimentalValueUnitsTextField.text")); // NOI18N
    experimentalValueUnitsTextField.setToolTipText(resourceMap.getString("experimentalValueUnitsTextField.toolTipText")); // NOI18N
    experimentalValueUnitsTextField.setName("experimentalValueUnitsTextField"); // NOI18N

    chemBioMechanismsLabel.setText(resourceMap.getString("chemBioMechanismsLabel.text")); // NOI18N
    chemBioMechanismsLabel.setName("chemBioMechanismsLabel"); // NOI18N

    chemBioMechanismsScrollable.setName("chemBioMechanismsScrollable"); // NOI18N

    chemBioMechanismsTextArea.setColumns(20);
    chemBioMechanismsTextArea.setLineWrap(true);
    chemBioMechanismsTextArea.setRows(5);
    chemBioMechanismsTextArea.setName("chemBioMechanismsTextArea"); // NOI18N
    chemBioMechanismsScrollable.setViewportView(chemBioMechanismsTextArea);

    jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
    jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
    jButton1.setName("jButton1"); // NOI18N

    javax.swing.GroupLayout predictionInfoPanelLayout = new javax.swing.GroupLayout(predictionInfoPanel);
    predictionInfoPanel.setLayout(predictionInfoPanelLayout);
    predictionInfoPanelLayout.setHorizontalGroup(
        predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(predictionInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(predictionInfoPanelLayout.createSequentialGroup()
                    .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(experimentalValueLabel)
                        .addComponent(predictionValueLabel))
                    .addGap(18, 18, 18)
                    .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(exprerimentalValueTextField)
                        .addComponent(predictionValueTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(experimentalValueUnitsTextField)
                        .addComponent(predictionUnitsTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(jButton1))
                .addGroup(predictionInfoPanelLayout.createSequentialGroup()
                    .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(predictionCommentLabel)
                        .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(predictionCommentScrollable, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chemBioMechanismsLabel, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                            .addComponent(chemBioMechanismsScrollable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                    .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(predictionUncertaintyLabel)
                        .addComponent(predictionUncertaintyScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(114, 114, 114))
    );
    predictionInfoPanelLayout.setVerticalGroup(
        predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(predictionInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(predictionValueLabel)
                .addComponent(predictionValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(predictionUnitsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(experimentalValueLabel)
                .addComponent(exprerimentalValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(experimentalValueUnitsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(52, 52, 52)
            .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(predictionCommentLabel)
                .addComponent(predictionUncertaintyLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(predictionUncertaintyScrollable, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(predictionCommentScrollable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addComponent(chemBioMechanismsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(chemBioMechanismsScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    javax.swing.GroupLayout predictionPanelLayout = new javax.swing.GroupLayout(predictionPanel);
    predictionPanel.setLayout(predictionPanelLayout);
    predictionPanelLayout.setHorizontalGroup(
        predictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(predictionPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(predictionInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(35, Short.MAX_VALUE))
    );
    predictionPanelLayout.setVerticalGroup(
        predictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(predictionPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(predictionInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(166, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab(resourceMap.getString("predictionPanel.TabConstraints.tabTitle"), predictionPanel); // NOI18N

    applicabilityDomainPanel.setName("applicabilityDomainPanel"); // NOI18N

    appDomainInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("appDomainInfoPanel.border.title"))); // NOI18N
    appDomainInfoPanel.setName("appDomainInfoPanel"); // NOI18N

    appDomAlgorithmNameLabel.setText(resourceMap.getString("appDomAlgorithmNameLabel.text")); // NOI18N
    appDomAlgorithmNameLabel.setName("appDomAlgorithmNameLabel"); // NOI18N

    addDomLinkLabel.setText(resourceMap.getString("addDomLinkLabel.text")); // NOI18N
    addDomLinkLabel.setName("addDomLinkLabel"); // NOI18N

    appDomainAlgorithmNameValue.setText(resourceMap.getString("appDomainAlgorithmNameValue.text")); // NOI18N
    appDomainAlgorithmNameValue.setName("appDomainAlgorithmNameValue"); // NOI18N

    appDomainURIValue.setForeground(resourceMap.getColor("appDomainURIValue.foreground")); // NOI18N
    appDomainURIValue.setText(resourceMap.getString("appDomainURIValue.text")); // NOI18N
    appDomainURIValue.setName("appDomainURIValue"); // NOI18N

    javax.swing.GroupLayout appDomainInfoPanelLayout = new javax.swing.GroupLayout(appDomainInfoPanel);
    appDomainInfoPanel.setLayout(appDomainInfoPanelLayout);
    appDomainInfoPanelLayout.setHorizontalGroup(
        appDomainInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(appDomainInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(appDomainInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(addDomLinkLabel)
                .addComponent(appDomAlgorithmNameLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(appDomainInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(appDomainURIValue)
                .addComponent(appDomainAlgorithmNameValue, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
            .addContainerGap(242, Short.MAX_VALUE))
    );
    appDomainInfoPanelLayout.setVerticalGroup(
        appDomainInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(appDomainInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(appDomainInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(appDomAlgorithmNameLabel)
                .addComponent(appDomainAlgorithmNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(appDomainInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(addDomLinkLabel)
                .addComponent(appDomainURIValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    addDomainsDiscussionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("addDomainsDiscussionPanel.border.title"))); // NOI18N
    addDomainsDiscussionPanel.setName("addDomainsDiscussionPanel"); // NOI18N

    addDomainResultLabel.setText(resourceMap.getString("addDomainResultLabel.text")); // NOI18N
    addDomainResultLabel.setName("addDomainResultLabel"); // NOI18N

    applicabilityDomainValue.setIcon(resourceMap.getIcon("applicabilityDomainValue.icon")); // NOI18N
    applicabilityDomainValue.setText(resourceMap.getString("applicabilityDomainValue.text")); // NOI18N
    applicabilityDomainValue.setToolTipText(resourceMap.getString("applicabilityDomainValue.toolTipText")); // NOI18N
    applicabilityDomainValue.setName("applicabilityDomainValue"); // NOI18N
    applicabilityDomainValue.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            applicabilityDomainValueMouseClicked(evt);
        }
    });

    domainChooserComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Metabolic Domain", "Structural Fragment Domain", "Descriptor Domain", "Mechanism Domain" }));
    domainChooserComboBox.setName("domainChooserComboBox"); // NOI18N
    domainChooserComboBox.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            domainChooserComboBoxItemStateChanged(evt);
        }
    });

    chooseDomainLabel.setText(resourceMap.getString("chooseDomainLabel.text")); // NOI18N
    chooseDomainLabel.setName("chooseDomainLabel"); // NOI18N

    domainCardPanel.setName("domainCardPanel"); // NOI18N
    domainCardPanel.setLayout(new java.awt.CardLayout());

    jScrollPane1.setName("jScrollPane1"); // NOI18N

    metabolicDomainText.setBackground(resourceMap.getColor("metabolicDomainText.background")); // NOI18N
    metabolicDomainText.setColumns(20);
    metabolicDomainText.setLineWrap(true);
    metabolicDomainText.setRows(5);
    metabolicDomainText.setName("metabolicDomainText"); // NOI18N
    jScrollPane1.setViewportView(metabolicDomainText);

    domainCardPanel.add(jScrollPane1, "Metabolic Domain");

    jScrollPane2.setName("jScrollPane2"); // NOI18N

    structuralFragmentDomainText.setBackground(resourceMap.getColor("structuralFragmentDomainText.background")); // NOI18N
    structuralFragmentDomainText.setColumns(20);
    structuralFragmentDomainText.setLineWrap(true);
    structuralFragmentDomainText.setRows(5);
    structuralFragmentDomainText.setName("structuralFragmentDomainText"); // NOI18N
    jScrollPane2.setViewportView(structuralFragmentDomainText);

    domainCardPanel.add(jScrollPane2, "Structural Fragment Domain");

    jScrollPane3.setName("jScrollPane3"); // NOI18N

    descriptorDomainText.setBackground(resourceMap.getColor("descriptorDomainText.background")); // NOI18N
    descriptorDomainText.setColumns(20);
    descriptorDomainText.setLineWrap(true);
    descriptorDomainText.setRows(5);
    descriptorDomainText.setName("descriptorDomainText"); // NOI18N
    jScrollPane3.setViewportView(descriptorDomainText);

    domainCardPanel.add(jScrollPane3, "Descriptor Domain");

    jScrollPane4.setName("jScrollPane4"); // NOI18N

    mechanismDomainText.setBackground(resourceMap.getColor("mechanismDomainText.background")); // NOI18N
    mechanismDomainText.setColumns(20);
    mechanismDomainText.setLineWrap(true);
    mechanismDomainText.setRows(5);
    mechanismDomainText.setName("mechanismDomainText"); // NOI18N
    jScrollPane4.setViewportView(mechanismDomainText);

    domainCardPanel.add(jScrollPane4, "Mechanism Domain");

    javax.swing.GroupLayout addDomainsDiscussionPanelLayout = new javax.swing.GroupLayout(addDomainsDiscussionPanel);
    addDomainsDiscussionPanel.setLayout(addDomainsDiscussionPanelLayout);
    addDomainsDiscussionPanelLayout.setHorizontalGroup(
        addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(addDomainsDiscussionPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(addDomainsDiscussionPanelLayout.createSequentialGroup()
                    .addComponent(addDomainResultLabel)
                    .addGap(18, 18, 18)
                    .addComponent(applicabilityDomainValue))
                .addGroup(addDomainsDiscussionPanelLayout.createSequentialGroup()
                    .addComponent(chooseDomainLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(domainChooserComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(48, 48, 48)
            .addComponent(domainCardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(229, Short.MAX_VALUE))
    );
    addDomainsDiscussionPanelLayout.setVerticalGroup(
        addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(addDomainsDiscussionPanelLayout.createSequentialGroup()
            .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(domainCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addDomainsDiscussionPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addDomainResultLabel)
                        .addComponent(applicabilityDomainValue))
                    .addGap(18, 18, 18)
                    .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chooseDomainLabel)
                        .addComponent(domainChooserComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap())
    );

    structuralAnaloguesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("structuralAnaloguesPanel.border.title"))); // NOI18N
    structuralAnaloguesPanel.setName("structuralAnaloguesPanel"); // NOI18N

    structuralAnaloguesToolbar.setFloatable(false);
    structuralAnaloguesToolbar.setRollover(true);
    structuralAnaloguesToolbar.setName("structuralAnaloguesToolbar"); // NOI18N

    compoundWizardButton.setIcon(resourceMap.getIcon("compoundWizardButton.icon")); // NOI18N
    compoundWizardButton.setText(resourceMap.getString("compoundWizardButton.text")); // NOI18N
    compoundWizardButton.setFocusable(false);
    compoundWizardButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    compoundWizardButton.setName("compoundWizardButton"); // NOI18N
    compoundWizardButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    compoundWizardButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            compoundWizardButtonActionPerformed(evt);
        }
    });
    structuralAnaloguesToolbar.add(compoundWizardButton);

    removeStructuralAnalogue.setIcon(resourceMap.getIcon("removeStructuralAnalogue.icon")); // NOI18N
    removeStructuralAnalogue.setText(resourceMap.getString("removeStructuralAnalogue.text")); // NOI18N
    removeStructuralAnalogue.setFocusable(false);
    removeStructuralAnalogue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    removeStructuralAnalogue.setName("removeStructuralAnalogue"); // NOI18N
    removeStructuralAnalogue.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    removeStructuralAnalogue.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeStructuralAnalogueActionPerformed(evt);
        }
    });
    structuralAnaloguesToolbar.add(removeStructuralAnalogue);

    clearAllStructuralAnalogues.setIcon(resourceMap.getIcon("clearAllStructuralAnalogues.icon")); // NOI18N
    clearAllStructuralAnalogues.setText(resourceMap.getString("clearAllStructuralAnalogues.text")); // NOI18N
    clearAllStructuralAnalogues.setFocusable(false);
    clearAllStructuralAnalogues.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    clearAllStructuralAnalogues.setName("clearAllStructuralAnalogues"); // NOI18N
    clearAllStructuralAnalogues.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    clearAllStructuralAnalogues.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearAllStructuralAnaloguesActionPerformed(evt);
        }
    });
    structuralAnaloguesToolbar.add(clearAllStructuralAnalogues);

    structAnalToolbarSeparator.setName("structAnalToolbarSeparator"); // NOI18N
    structuralAnaloguesToolbar.add(structAnalToolbarSeparator);

    jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
    jLabel4.setName("jLabel4"); // NOI18N
    structuralAnaloguesToolbar.add(jLabel4);

    similarityLevel.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
    similarityLevel.setText(resourceMap.getString("similarityLevel.text")); // NOI18N
    similarityLevel.setName("similarityLevel"); // NOI18N
    similarityLevel.setPreferredSize(new java.awt.Dimension(100, 27));
    similarityLevel.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            similarityLevelCaretUpdate(evt);
        }
    });
    structuralAnaloguesToolbar.add(similarityLevel);

    acquireListAnaloguesButton.setIcon(resourceMap.getIcon("acquireListAnaloguesButton.icon")); // NOI18N
    acquireListAnaloguesButton.setText(resourceMap.getString("acquireListAnaloguesButton.text")); // NOI18N
    acquireListAnaloguesButton.setFocusable(false);
    acquireListAnaloguesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    acquireListAnaloguesButton.setName("acquireListAnaloguesButton"); // NOI18N
    acquireListAnaloguesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    acquireListAnaloguesButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            acquireListAnaloguesButtonActionPerformed(evt);
        }
    });
    structuralAnaloguesToolbar.add(acquireListAnaloguesButton);

    jSeparator12.setName("jSeparator12"); // NOI18N
    structuralAnaloguesToolbar.add(jSeparator12);

    analogueInfo.setIcon(resourceMap.getIcon("analogueInfo.icon")); // NOI18N
    analogueInfo.setText(resourceMap.getString("analogueInfo.text")); // NOI18N
    analogueInfo.setEnabled(false);
    analogueInfo.setFocusable(false);
    analogueInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    analogueInfo.setName("analogueInfo"); // NOI18N
    analogueInfo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    analogueInfo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            analogueInfoActionPerformed(evt);
        }
    });
    structuralAnaloguesToolbar.add(analogueInfo);

    structuralAnaloguesSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);
    structuralAnaloguesSeparator.setName("structuralAnaloguesSeparator"); // NOI18N

    jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
    jLabel9.setName("jLabel9"); // NOI18N

    jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jSeparator6.setName("jSeparator6"); // NOI18N

    analoguesCommentsLabel.setText(resourceMap.getString("analoguesCommentsLabel.text")); // NOI18N
    analoguesCommentsLabel.setToolTipText(resourceMap.getString("analoguesCommentsLabel.toolTipText")); // NOI18N
    analoguesCommentsLabel.setName("analoguesCommentsLabel"); // NOI18N

    analoguesPanel.setName("analoguesPanel"); // NOI18N

    jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
    jLabel5.setName("jLabel5"); // NOI18N

    jScrollPane5.setName("jScrollPane5"); // NOI18N

    structuralAnaloguesTable.setAutoCreateRowSorter(true);
    structuralAnaloguesTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Chemical Name", "Experimental Value"
        }
    ));
    structuralAnaloguesTable.setName("structuralAnaloguesTable"); // NOI18N
    structuralAnaloguesTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            structuralAnaloguesTableMouseClicked(evt);
        }
    });
    structuralAnaloguesTable.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            structuralAnaloguesTableKeyReleased(evt);
        }
    });
    jScrollPane5.setViewportView(structuralAnaloguesTable);
    structuralAnaloguesTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("structuralAnaloguesTable.columnModel.title0")); // NOI18N
    structuralAnaloguesTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("structuralAnaloguesTable.columnModel.title1")); // NOI18N

    javax.swing.GroupLayout analoguesPanelLayout = new javax.swing.GroupLayout(analoguesPanel);
    analoguesPanel.setLayout(analoguesPanelLayout);
    analoguesPanelLayout.setHorizontalGroup(
        analoguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(analoguesPanelLayout.createSequentialGroup()
            .addComponent(jLabel5)
            .addContainerGap(86, Short.MAX_VALUE))
        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
    );
    analoguesPanelLayout.setVerticalGroup(
        analoguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(analoguesPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
            .addContainerGap())
    );

    analoguesCommentsScrollable.setName("analoguesCommentsScrollable"); // NOI18N

    analoguesCommentsTextArea.setColumns(20);
    analoguesCommentsTextArea.setLineWrap(true);
    analoguesCommentsTextArea.setRows(5);
    analoguesCommentsTextArea.setName("analoguesCommentsTextArea"); // NOI18N
    analoguesCommentsScrollable.setViewportView(analoguesCommentsTextArea);

    analoguesImagePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    analoguesImagePanel.setName("analoguesImagePanel"); // NOI18N

    analogueImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    analogueImageLabel.setText(resourceMap.getString("analogueImageLabel.text")); // NOI18N
    analogueImageLabel.setName("analogueImageLabel"); // NOI18N

    javax.swing.GroupLayout analoguesImagePanelLayout = new javax.swing.GroupLayout(analoguesImagePanel);
    analoguesImagePanel.setLayout(analoguesImagePanelLayout);
    analoguesImagePanelLayout.setHorizontalGroup(
        analoguesImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(analogueImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    analoguesImagePanelLayout.setVerticalGroup(
        analoguesImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(analogueImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    javax.swing.GroupLayout structuralAnaloguesPanelLayout = new javax.swing.GroupLayout(structuralAnaloguesPanel);
    structuralAnaloguesPanel.setLayout(structuralAnaloguesPanelLayout);
    structuralAnaloguesPanelLayout.setHorizontalGroup(
        structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(structuralAnaloguesPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(analoguesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(structuralAnaloguesSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(analoguesImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel9))
            .addGap(18, 18, 18)
            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(analoguesCommentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(analoguesCommentsScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(structuralAnaloguesToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
    );
    structuralAnaloguesPanelLayout.setVerticalGroup(
        structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(structuralAnaloguesPanelLayout.createSequentialGroup()
            .addComponent(structuralAnaloguesToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(structuralAnaloguesSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addComponent(analoguesPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, structuralAnaloguesPanelLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(analoguesImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, structuralAnaloguesPanelLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addGroup(structuralAnaloguesPanelLayout.createSequentialGroup()
                            .addComponent(analoguesCommentsLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                            .addComponent(analoguesCommentsScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addContainerGap())
    );

    javax.swing.GroupLayout applicabilityDomainPanelLayout = new javax.swing.GroupLayout(applicabilityDomainPanel);
    applicabilityDomainPanel.setLayout(applicabilityDomainPanelLayout);
    applicabilityDomainPanelLayout.setHorizontalGroup(
        applicabilityDomainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(applicabilityDomainPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(applicabilityDomainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(structuralAnaloguesPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(appDomainInfoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addDomainsDiscussionPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(28, Short.MAX_VALUE))
    );
    applicabilityDomainPanelLayout.setVerticalGroup(
        applicabilityDomainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(applicabilityDomainPanelLayout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(appDomainInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(structuralAnaloguesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(addDomainsDiscussionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(33, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab(resourceMap.getString("applicabilityDomainPanel.TabConstraints.tabTitle"), applicabilityDomainPanel); // NOI18N

    javax.swing.GroupLayout reportPredictionPanelLayout = new javax.swing.GroupLayout(reportPredictionPanel);
    reportPredictionPanel.setLayout(reportPredictionPanelLayout);
    reportPredictionPanelLayout.setHorizontalGroup(
        reportPredictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(reportPredictionPanelLayout.createSequentialGroup()
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1082, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    reportPredictionPanelLayout.setVerticalGroup(
        reportPredictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
    );

    basicTabbedPanel.addTab(resourceMap.getString("reportPredictionPanel.TabConstraints.tabTitle"), reportPredictionPanel); // NOI18N

    adequacyInfoPanel.setName("adequacyInfoPanel"); // NOI18N

    adequacyInfoSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("adequacyInfoSubPanel.border.title"))); // NOI18N
    adequacyInfoSubPanel.setName("adequacyInfoSubPanel"); // NOI18N

    regulatoryPurposeLabel.setText(resourceMap.getString("regulatoryPurposeLabel.text")); // NOI18N
    regulatoryPurposeLabel.setName("regulatoryPurposeLabel"); // NOI18N

    regulatoryPurposeHintLabel.setIcon(resourceMap.getIcon("regulatoryPurposeHintLabel.icon")); // NOI18N
    regulatoryPurposeHintLabel.setText(resourceMap.getString("regulatoryPurposeHintLabel.text")); // NOI18N
    regulatoryPurposeHintLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    regulatoryPurposeHintLabel.setName("regulatoryPurposeHintLabel"); // NOI18N
    regulatoryPurposeHintLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            regulatoryPurposeHintLabelMouseClicked(evt);
        }
    });

    regulatoryInterprLabel.setText(resourceMap.getString("regulatoryInterprLabel.text")); // NOI18N
    regulatoryInterprLabel.setName("regulatoryInterprLabel"); // NOI18N

    outcomeLabel.setText(resourceMap.getString("outcomeLabel.text")); // NOI18N
    outcomeLabel.setName("outcomeLabel"); // NOI18N

    conclusionLabel.setLabelFor(qprfReportConclusionTextArea);
    conclusionLabel.setText(resourceMap.getString("conclusionLabel.text")); // NOI18N
    conclusionLabel.setName("conclusionLabel"); // NOI18N

    regulatoryPurposeScrollable.setName("regulatoryPurposeScrollable"); // NOI18N

    regulatoryPurposeTextArea.setColumns(20);
    regulatoryPurposeTextArea.setLineWrap(true);
    regulatoryPurposeTextArea.setRows(5);
    regulatoryPurposeTextArea.setName("regulatoryPurposeTextArea"); // NOI18N
    regulatoryPurposeScrollable.setViewportView(regulatoryPurposeTextArea);

    regulInterprScrollable.setName("regulInterprScrollable"); // NOI18N

    regulInterprTextArea.setColumns(20);
    regulInterprTextArea.setLineWrap(true);
    regulInterprTextArea.setRows(5);
    regulInterprTextArea.setName("regulInterprTextArea"); // NOI18N
    regulInterprScrollable.setViewportView(regulInterprTextArea);

    qprfOutcomeScrollable.setName("qprfOutcomeScrollable"); // NOI18N

    qprfOutcomeTextArea.setColumns(20);
    qprfOutcomeTextArea.setLineWrap(true);
    qprfOutcomeTextArea.setRows(5);
    qprfOutcomeTextArea.setName("qprfOutcomeTextArea"); // NOI18N
    qprfOutcomeScrollable.setViewportView(qprfOutcomeTextArea);

    qprfReportConclusionScollable.setName("qprfReportConclusionScollable"); // NOI18N

    qprfReportConclusionTextArea.setColumns(20);
    qprfReportConclusionTextArea.setLineWrap(true);
    qprfReportConclusionTextArea.setRows(5);
    qprfReportConclusionTextArea.setName("qprfReportConclusionTextArea"); // NOI18N
    qprfReportConclusionScollable.setViewportView(qprfReportConclusionTextArea);

    regulatoryInterprHintLabel.setIcon(resourceMap.getIcon("regulatoryInterprHintLabel.icon")); // NOI18N
    regulatoryInterprHintLabel.setText(resourceMap.getString("regulatoryInterprHintLabel.text")); // NOI18N
    regulatoryInterprHintLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    regulatoryInterprHintLabel.setName("regulatoryInterprHintLabel"); // NOI18N
    regulatoryInterprHintLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            regulatoryInterprHintLabelMouseClicked(evt);
        }
    });

    outcomeHintLabel.setIcon(resourceMap.getIcon("outcomeHintLabel.icon")); // NOI18N
    outcomeHintLabel.setText(resourceMap.getString("outcomeHintLabel.text")); // NOI18N
    outcomeHintLabel.setName("outcomeHintLabel"); // NOI18N

    conclusionHintLabel.setIcon(resourceMap.getIcon("conclusionHintLabel.icon")); // NOI18N
    conclusionHintLabel.setLabelFor(conclusionLabel);
    conclusionHintLabel.setText(resourceMap.getString("conclusionHintLabel.text")); // NOI18N
    conclusionHintLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    conclusionHintLabel.setName("conclusionHintLabel"); // NOI18N

    adequacyInfoIndicatorLabel.setIcon(resourceMap.getIcon("adequacyInfoIndicatorLabel.icon")); // NOI18N
    adequacyInfoIndicatorLabel.setText(resourceMap.getString("adequacyInfoIndicatorLabel.text")); // NOI18N
    adequacyInfoIndicatorLabel.setName("adequacyInfoIndicatorLabel"); // NOI18N

    adequacyBottomMessageLabel.setText(resourceMap.getString("adequacyBottomMessageLabel.text")); // NOI18N
    adequacyBottomMessageLabel.setName("adequacyBottomMessageLabel"); // NOI18N

    javax.swing.GroupLayout adequacyInfoSubPanelLayout = new javax.swing.GroupLayout(adequacyInfoSubPanel);
    adequacyInfoSubPanel.setLayout(adequacyInfoSubPanelLayout);
    adequacyInfoSubPanelLayout.setHorizontalGroup(
        adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(adequacyInfoSubPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(adequacyInfoSubPanelLayout.createSequentialGroup()
                    .addGroup(adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(adequacyInfoSubPanelLayout.createSequentialGroup()
                            .addComponent(regulatoryPurposeLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(regulatoryPurposeHintLabel)
                            .addGap(267, 267, 267))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adequacyInfoSubPanelLayout.createSequentialGroup()
                            .addGroup(adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(qprfOutcomeScrollable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, adequacyInfoSubPanelLayout.createSequentialGroup()
                                    .addComponent(outcomeLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(outcomeHintLabel))
                                .addComponent(regulatoryPurposeScrollable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                            .addGap(27, 27, 27)))
                    .addGroup(adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(qprfReportConclusionScollable, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                        .addGroup(adequacyInfoSubPanelLayout.createSequentialGroup()
                            .addComponent(conclusionLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(conclusionHintLabel))
                        .addGroup(adequacyInfoSubPanelLayout.createSequentialGroup()
                            .addComponent(regulatoryInterprLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(regulatoryInterprHintLabel))
                        .addComponent(regulInterprScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)))
                .addGroup(adequacyInfoSubPanelLayout.createSequentialGroup()
                    .addComponent(adequacyInfoIndicatorLabel)
                    .addGap(18, 18, 18)
                    .addComponent(adequacyBottomMessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)))
            .addContainerGap())
    );
    adequacyInfoSubPanelLayout.setVerticalGroup(
        adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adequacyInfoSubPanelLayout.createSequentialGroup()
            .addGroup(adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(regulatoryPurposeHintLabel)
                .addComponent(regulatoryInterprHintLabel)
                .addGroup(adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regulatoryInterprLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regulatoryPurposeLabel)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(regulatoryPurposeScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addComponent(regulInterprScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addGroup(adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(outcomeLabel)
                .addComponent(conclusionLabel)
                .addComponent(outcomeHintLabel)
                .addComponent(conclusionHintLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(qprfReportConclusionScollable)
                .addComponent(qprfOutcomeScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
            .addGap(30, 30, 30)
            .addGroup(adequacyInfoSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(adequacyInfoIndicatorLabel)
                .addComponent(adequacyBottomMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    adequacyHintLabel.setIcon(resourceMap.getIcon("adequacyHintLabel.icon")); // NOI18N
    adequacyHintLabel.setText(resourceMap.getString("adequacyHintLabel.text")); // NOI18N
    adequacyHintLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    adequacyHintLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
    adequacyHintLabel.setName("adequacyHintLabel"); // NOI18N
    adequacyHintLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            adequacyHintLabelMouseClicked(evt);
        }
    });

    javax.swing.GroupLayout adequacyInfoPanelLayout = new javax.swing.GroupLayout(adequacyInfoPanel);
    adequacyInfoPanel.setLayout(adequacyInfoPanelLayout);
    adequacyInfoPanelLayout.setHorizontalGroup(
        adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adequacyInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(adequacyInfoSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addComponent(adequacyHintLabel)
            .addContainerGap())
    );
    adequacyInfoPanelLayout.setVerticalGroup(
        adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(adequacyInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(adequacyInfoSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(adequacyHintLabel))
            .addContainerGap(59, Short.MAX_VALUE))
    );

    basicTabbedPanel.addTab(resourceMap.getString("adequacyInfoPanel.TabConstraints.tabTitle"), adequacyInfoPanel); // NOI18N

    javax.swing.GroupLayout basicContainerPanelLayout = new javax.swing.GroupLayout(basicContainerPanel);
    basicContainerPanel.setLayout(basicContainerPanelLayout);
    basicContainerPanelLayout.setHorizontalGroup(
        basicContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(basicContainerPanelLayout.createSequentialGroup()
            .addComponent(basicTabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1071, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    basicContainerPanelLayout.setVerticalGroup(
        basicContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(basicContainerPanelLayout.createSequentialGroup()
            .addComponent(basicTabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(basicContainerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(basicContainerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pasteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMenuItemActionPerformed
        int selectedRowIndex = authorsTable.getSelectedRow();
        int selectedColumnIndex = authorsTable.getSelectedColumn();
        if ((selectedColumnIndex + 1) * (1 + selectedRowIndex) == 0) {
            return;// no cell is selected!
        }
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable clipData = clipboard.getContents(clipboard);
        if (clipData != null) {
            try {
                if (clipData.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String s = (String) (clipData.getTransferData(
                            DataFlavor.stringFlavor));
                    authorsTable.setValueAt(s, selectedRowIndex, selectedColumnIndex);
                }
            } catch (UnsupportedFlavorException ufe) {
                System.err.println("Flavor unsupported: " + ufe);
            } catch (IOException ioe) {
                System.err.println("Data not available: " + ioe);
            }
        } else {
            System.out.println("No DATA ???");
        }

    }//GEN-LAST:event_pasteMenuItemActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
        copy();
    }//GEN-LAST:event_copyMenuItemActionPerformed

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
        delete();
    }//GEN-LAST:event_deleteMenuItemActionPerformed

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        copy();
        delete();
    }//GEN-LAST:event_cutMenuItemActionPerformed

    private void qprfDescriptionHintLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qprfDescriptionHintLabelMouseClicked
        if (MouseEvent.BUTTON1 == evt.getButton()) { // left click
            QPRFDescriptionHint hint = new QPRFDescriptionHint(QEditApp.getView().getFrame(), true);
            hint.setBounds(new Rectangle(evt.getLocationOnScreen(), new Dimension(hint.getWidth(), hint.getHeight())));
            hint.setVisible(true);
        }
}//GEN-LAST:event_qprfDescriptionHintLabelMouseClicked

    private void moveDownRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownRowButtonActionPerformed
        int nRows = authorsTable.getRowCount();
        int[] selectedRows = authorsTable.getSelectedRows();
        DefaultTableModel authorsTableModel = (DefaultTableModel) authorsTable.getModel();
        int currentRow = -1;
        for (int rowSelectionIndex = selectedRows.length - 1; rowSelectionIndex >= 0; rowSelectionIndex--) {
            currentRow = selectedRows[rowSelectionIndex];
            if (currentRow < nRows - 1) {
                authorsTableModel.moveRow(currentRow, currentRow, currentRow + 1);
                authorsTable.getSelectionModel().removeSelectionInterval(currentRow, currentRow);
                authorsTable.getSelectionModel().addSelectionInterval(currentRow + 1, currentRow + 1);
            }
        }
    }//GEN-LAST:event_moveDownRowButtonActionPerformed

    private void moveUpRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpRowButtonActionPerformed
        int[] selectedRows = authorsTable.getSelectedRows();
        DefaultTableModel authorsTableModel = (DefaultTableModel) authorsTable.getModel();
        for (int rowSelected : selectedRows) {
            if (rowSelected > 0) {
                authorsTableModel.moveRow(rowSelected, rowSelected, rowSelected - 1);
                authorsTable.getSelectionModel().removeSelectionInterval(rowSelected, rowSelected);
                authorsTable.getSelectionModel().addSelectionInterval(rowSelected - 1, rowSelected - 1);
            }
        }
}//GEN-LAST:event_moveUpRowButtonActionPerformed

    private void removeSelectedRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSelectedRowButtonActionPerformed
        removeSelectedRows(authorsTable);
}//GEN-LAST:event_removeSelectedRowButtonActionPerformed

    private void appendRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appendRowButtonActionPerformed
        DefaultTableModel authorsTableModel = (DefaultTableModel) authorsTable.getModel();
        authorsTableModel.addRow(new String[authorsTableModel.getColumnCount()]);
}//GEN-LAST:event_appendRowButtonActionPerformed

    private void authorsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authorsTableMouseClicked
        // React only on right-click
        if (MouseEvent.BUTTON3 != evt.getButton()) {
            return;
        }
        Point p = evt.getPoint();
        int row = authorsTable.rowAtPoint(p);
        int column = authorsTable.columnAtPoint(p);
        authorsTable.changeSelection(row, column, false, false);
        authorsTable.requestFocus();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable clipData = clipboard.getContents(clipboard);
        Object trasferableData = null;
        try {
            trasferableData = clipData.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException ex) {
        } catch (IOException ex) {
        }
        pasteMenuItem.setEnabled(!(clipData == null || trasferableData == null));
        authorsTablePopupMenu.show(authorsTable, evt.getX(), evt.getY());
}//GEN-LAST:event_authorsTableMouseClicked

    private void qprfCommentsHintLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qprfCommentsHintLabelMouseClicked
        if (MouseEvent.BUTTON1 == evt.getButton()) { // left click
            QPRFCommentsHint hint = new QPRFCommentsHint(QEditApp.getView().getFrame(), true);
            hint.setBounds(new Rectangle(evt.getLocationOnScreen(), new Dimension(hint.getWidth(), hint.getHeight())));
            hint.setVisible(true);
        }
    }//GEN-LAST:event_qprfCommentsHintLabelMouseClicked

    private void regulatoryInterprHintLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regulatoryInterprHintLabelMouseClicked
        if (MouseEvent.BUTTON1 == evt.getButton()) { // left click
            RegulInterpretationHint hint = new RegulInterpretationHint(QEditApp.getView().getFrame(), true);
            hint.setBounds(new Rectangle(evt.getLocationOnScreen(), new Dimension(hint.getWidth(), hint.getHeight())));
            hint.setVisible(true);
        }
}//GEN-LAST:event_regulatoryInterprHintLabelMouseClicked

    private void regulatoryPurposeHintLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regulatoryPurposeHintLabelMouseClicked
        if (MouseEvent.BUTTON1 == evt.getButton()) { // left click
            RegulatoryPurposeHint hint = new RegulatoryPurposeHint(QEditApp.getView().getFrame(), true);
            hint.setBounds(new Rectangle(evt.getLocationOnScreen(), new Dimension(hint.getWidth(), hint.getHeight())));
            hint.setVisible(true);
        }
    }//GEN-LAST:event_regulatoryPurposeHintLabelMouseClicked

    private void authorsTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_authorsTableKeyPressed
        if (KeyEvent.VK_CONTEXT_MENU == evt.getKeyCode()) {
            int row = authorsTable.getSelectedRow();
            int column = authorsTable.getSelectedColumn();
            authorsTable.changeSelection(row, column, false, false);
            authorsTable.requestFocus();
            Rectangle cellRect = authorsTable.getCellRect(row, column, true);
            authorsTablePopupMenu.show(authorsTable, cellRect.x, cellRect.y);
        }
    }//GEN-LAST:event_authorsTableKeyPressed

    private void addDescriptorValueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDescriptorValueButtonActionPerformed
        DefaultTableModel descriptorsTableModel = (DefaultTableModel) descriptorsTable.getModel();
        descriptorsTableModel.addRow(new String[descriptorsTableModel.getColumnCount()]);
    }//GEN-LAST:event_addDescriptorValueButtonActionPerformed

    private void removeSelectedRows(JTable table) {
        int[] selectedRows = table.getSelectedRows();
        TableModel tableModel = table.getModel();
        while (selectedRows.length > 0) {
            ((DefaultTableModel) tableModel).removeRow(table.convertRowIndexToModel(selectedRows[0]));
            selectedRows = table.getSelectedRows();
        }
        table.clearSelection();
    }

    private void removeDescriptorValueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDescriptorValueButtonActionPerformed
        removeSelectedRows(descriptorsTable);
    }//GEN-LAST:event_removeDescriptorValueButtonActionPerformed

    private void descriptorsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descriptorsTableMouseClicked
        if (MouseEvent.BUTTON3 != evt.getButton()) {
            return;
        }
        Point p = evt.getPoint();
        int row = descriptorsTable.rowAtPoint(p);
        int column = descriptorsTable.columnAtPoint(p);
        descriptorsTable.changeSelection(row, column, false, false);
        descriptorsTable.requestFocus();
        descriptorsPopupMenu.show(descriptorsTable, evt.getX(), evt.getY());
    }//GEN-LAST:event_descriptorsTableMouseClicked

    private void authorsWizardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorsWizardButtonActionPerformed
        addAuthorWizard();
    }//GEN-LAST:event_authorsWizardButtonActionPerformed

    private void clearAllRows(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }

    public JPanel getAnaloguesImageCards() {
        return analoguesImagePanel;
    }

    private void clearAllAuthorsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllAuthorsButtonActionPerformed
        clearAllRows(authorsTable);
    }//GEN-LAST:event_clearAllAuthorsButtonActionPerformed

    private void clearAllDescriptorsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllDescriptorsButtonActionPerformed
        clearAllRows(descriptorsTable);
    }//GEN-LAST:event_clearAllDescriptorsButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        addFeatureWizard();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void compoundWizardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundWizardButtonActionPerformed
        JFrame jframe = QEditApp.getView().getFrame();
        structuralAnalogueWizard = new StructuralAnalogueWizard_Step1(jframe, this);
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = structuralAnalogueWizard.getWidth();
        int dialogHeight = structuralAnalogueWizard.getHeight();
        int dialog_x = (frameWidth - dialogWidht) / 2;
        int dialog_y = (frameHeight - dialogHeight) / 2;
        structuralAnalogueWizard.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        structuralAnalogueWizard.setVisible(true);
    }//GEN-LAST:event_compoundWizardButtonActionPerformed

    private void clearAllStructuralAnaloguesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllStructuralAnaloguesActionPerformed
    }//GEN-LAST:event_clearAllStructuralAnaloguesActionPerformed

    private void removeStructuralAnalogueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeStructuralAnalogueActionPerformed
    }//GEN-LAST:event_removeStructuralAnalogueActionPerformed

    private void descriptorMetaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptorMetaMenuItemActionPerformed
        JFrame jframe = QEditApp.getView().getFrame();
        descriptorMetaDialog = new DescriptorMetaDataDialog(jframe);
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = descriptorMetaDialog.getWidth();
        int dialogHeight = descriptorMetaDialog.getHeight();
        int dialog_x = (frameWidth - dialogWidht) / 2;
        int dialog_y = (frameHeight - dialogHeight) / 2;
        descriptorMetaDialog.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        descriptorMetaDialog.setVisible(true);
    }//GEN-LAST:event_descriptorMetaMenuItemActionPerformed

    private void removeAuthorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAuthorMenuItemActionPerformed
        removeSelectedRows(authorsTable);
    }//GEN-LAST:event_removeAuthorMenuItemActionPerformed

    private void removeDescriptorLineMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDescriptorLineMenuItemActionPerformed
        removeSelectedRows(descriptorsTable);
    }//GEN-LAST:event_removeDescriptorLineMenuItemActionPerformed

    private void modelInfoToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelInfoToolButtonActionPerformed
        JFrame jframe = QEditApp.getView().getFrame();
        modelInfoDialog = new ModelInfoDialog(QEditApp.getView().getFrame(), qprfreport.getModel());
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = modelInfoDialog.getWidth();
        int dialogHeight = modelInfoDialog.getHeight();
        int dialog_x = (frameWidth - dialogWidht) / 2;
        int dialog_y = (frameHeight - dialogHeight) / 2;
        modelInfoDialog.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        modelInfoDialog.setVisible(true);

    }//GEN-LAST:event_modelInfoToolButtonActionPerformed

    private void algorithmInfoToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmInfoToolButtonActionPerformed
        JFrame jframe = QEditApp.getView().getFrame();

        if (algorithmInfoDialog == null) {
            algorithmInfoDialog = new AlgorithmInfoDialog(jframe, qprfreport.getModel().getAlgorithm());
            int frameWidth = jframe.getWidth();
            int frameHeight = jframe.getHeight();
            int dialogWidht = algorithmInfoDialog.getWidth();
            int dialogHeight = algorithmInfoDialog.getHeight();
            int dialog_x = (frameWidth - dialogWidht) / 2;
            int dialog_y = (frameHeight - dialogHeight) / 2;
            algorithmInfoDialog.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        }
        algorithmInfoDialog.setVisible(true);
        algorithmInfoDialog.getAlgorithmURIField().setText(algorithmUriValue.getText());
    }//GEN-LAST:event_algorithmInfoToolButtonActionPerformed

    private void predictedFeatureToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predictedFeatureToolButtonActionPerformed
        JFrame jframe = QEditApp.getView().getFrame();
        FeatureInfoDialogue featureInfoDialog = new FeatureInfoDialogue(jframe, qprfreport.getModel().getPredictedFeature());
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = featureInfoDialog.getWidth();
        int dialogHeight = featureInfoDialog.getHeight();
        int dialog_x = (frameWidth - dialogWidht) / 2;
        int dialog_y = (frameHeight - dialogHeight) / 2;
        featureInfoDialog.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        featureInfoDialog.setVisible(true);
    }//GEN-LAST:event_predictedFeatureToolButtonActionPerformed

    private void dependentFeatureToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dependentFeatureToolButtonActionPerformed
        JFrame jframe = QEditApp.getView().getFrame();
        FeatureInfoDialogue featureInfoDialog = new FeatureInfoDialogue(jframe, qprfreport.getModel().getDependentFeature());
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = featureInfoDialog.getWidth();
        int dialogHeight = featureInfoDialog.getHeight();
        int dialog_x = (frameWidth - dialogWidht) / 2;
        int dialog_y = (frameHeight - dialogHeight) / 2;
        featureInfoDialog.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        featureInfoDialog.setVisible(true);
    }//GEN-LAST:event_dependentFeatureToolButtonActionPerformed

    private void modelCurrentDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelCurrentDateActionPerformed
        enableDisableModelDate();
    }//GEN-LAST:event_modelCurrentDateActionPerformed

    private void modelUriDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelUriDetailsButtonActionPerformed
        modelInfoToolButtonActionPerformed(evt);
    }//GEN-LAST:event_modelUriDetailsButtonActionPerformed

    private void algorithmDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmDetailsButtonActionPerformed
        algorithmInfoToolButtonActionPerformed(evt);
    }//GEN-LAST:event_algorithmDetailsButtonActionPerformed

    private void predFeatureDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predFeatureDetailsButtonActionPerformed
        predictedFeatureToolButtonActionPerformed(evt);
    }//GEN-LAST:event_predFeatureDetailsButtonActionPerformed

    private void domainChooserComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_domainChooserComboBoxItemStateChanged
        CardLayout cl = (CardLayout) (domainCardPanel.getLayout());
        cl.show(domainCardPanel, (String) evt.getItem());
    }//GEN-LAST:event_domainChooserComboBoxItemStateChanged

    private void clearStructureImgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearStructureImgButtonActionPerformed
        deleteImage();
}//GEN-LAST:event_clearStructureImgButtonActionPerformed

    private void loadStructureImgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadStructureImgButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.showOpenDialog(QEditApp.getApplication().getMainFrame());
        final File selectedFile = fileChooser.getSelectedFile();
        structureImage.setIcon(null);
        structureImage.setText("Loading...");
        Task compImageLoadingTask = new Task(QEditApp.getApplication()) {

            @Override
            protected Object doInBackground() throws Exception {
                if (selectedFile != null) {
                    QEditApp.getView().getDesktopPane().setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
                    setProgress(0);
                    QEditApp.getView().getStatusLabel().setText("Loading Image... " + getProgress() + "%");
                    try {
                        setProgress(10);
                        QEditApp.getView().getStatusLabel().setText("Loading Image... " + getProgress() + "%");
                        ImageIcon ii = new ImageIcon(selectedFile.getCanonicalPath());
                        setProgress(50);
                        QEditApp.getView().getStatusLabel().setText("Loading Image... " + getProgress() + "%");
                        Image image = ii.getImage().getScaledInstance(-1, structureImage.getHeight(), Image.SCALE_SMOOTH);
                        setProgress(70);
                        QEditApp.getView().getStatusLabel().setText("Loading Image... " + getProgress() + "%");
                        if (image.getWidth(null) > structureImage.getWidth()) {
                            image = image.getScaledInstance(structureImage.getWidth(), -1, 0);
                        }
                        setProgress(95);
                        QEditApp.getView().getStatusLabel().setText("Loading Image... " + getProgress() + "%");
                        structureImage.setIcon(new ImageIcon(image));
                        structureImage.setText("");
                        setProgress(100);
                        QEditApp.getView().getStatusLabel().setText("Loading Image... DONE!");
                    } catch (IOException ex) {
                        Logger.getLogger(ReportInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        QEditApp.getView().getDesktopPane().setCursor(java.awt.Cursor.getDefaultCursor());
                    }
                }
                return new Object();
            }
        };
        ApplicationContext appC = QEditApp.getInstance().getContext();
        TaskMonitor taskMonitor = appC.getTaskMonitor();
        TaskService taskService = appC.getTaskService();
        taskService.execute(compImageLoadingTask);
        taskMonitor.setForegroundTask(compImageLoadingTask);
}//GEN-LAST:event_loadStructureImgButtonActionPerformed

    private void clearAllCompoundSynonymsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllCompoundSynonymsActionPerformed
        getCompoundSynonymsList().setModel(new DefaultListModel());
}//GEN-LAST:event_clearAllCompoundSynonymsActionPerformed

    private void removeCompoundSynonymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCompoundSynonymActionPerformed
        int index = getCompoundSynonymsList().getSelectedIndex();
        if (index > -1) {
            ((DefaultListModel) getCompoundSynonymsList().getModel()).remove(index);
        }
}//GEN-LAST:event_removeCompoundSynonymActionPerformed

    private void addCompoundSynonymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCompoundSynonymActionPerformed
        addCompoundSynonym();
}//GEN-LAST:event_addCompoundSynonymActionPerformed

    private void showMagnifiedImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMagnifiedImageActionPerformed
        if (structureImage.getText().isEmpty()) {
            JFrame jframe = QEditApp.getView().getFrame();
            MagnifiedImage magnified = new MagnifiedImage(jframe);
            int frameWidth = jframe.getWidth();
            int frameHeight = jframe.getHeight();
            int dialogWidht = magnified.getWidth();
            int dialogHeight = magnified.getHeight();
            int dialog_x = (frameWidth - dialogWidht) / 2;
            int dialog_y = (frameHeight - dialogHeight) / 2;
            magnified.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
            ImageIcon ic = (ImageIcon) structureImage.getIcon();
            Image im = ic.getImage().getScaledInstance(magnified.getImageLabel().getWidth(),
                    magnified.getImageLabel().getHeight(), QEditApp.getMagnificationMode());
            magnified.setIcon(new ImageIcon(im));
            magnified.setVisible(true);
        }
    }//GEN-LAST:event_showMagnifiedImageActionPerformed

    private void adequacyHintLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adequacyHintLabelMouseClicked
        if (MouseEvent.BUTTON1 == evt.getButton()) { // left click
            AdequacyHint hint = new AdequacyHint(QEditApp.getView().getFrame(), true);
            hint.setBounds(new Rectangle(evt.getLocationOnScreen(), new Dimension(hint.getWidth(), hint.getHeight())));
            hint.setVisible(true);
        }
}//GEN-LAST:event_adequacyHintLabelMouseClicked

    private void updateAllCompoundInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAllCompoundInfoButtonActionPerformed
        updateCompoundFromUserInput();

    }//GEN-LAST:event_updateAllCompoundInfoButtonActionPerformed

    private void showCompoundDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCompoundDetailsButtonActionPerformed
        compoundDetails();
    }//GEN-LAST:event_showCompoundDetailsButtonActionPerformed

    private void downloadModelInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadModelInfoButtonActionPerformed

        downloadModelInfoAction();

    }//GEN-LAST:event_downloadModelInfoButtonActionPerformed

    private void applicabilityDomainValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applicabilityDomainValueMouseClicked
        toggleDOAValue();
    }//GEN-LAST:event_applicabilityDomainValueMouseClicked

    private void acquireListAnaloguesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acquireListAnaloguesButtonActionPerformed
        if (qprfreport.getCompound() == null) {
            QEditApp.getView().getStatusLabel().setText("No compound found! Cannot load structural analogues!");
            return;
        }
        AbstractTask task = new AbstractTask() {

            @Override
            protected Object doInBackground() throws Exception {
                try {
                    analogueInfo.setEnabled(false);
                    compoundWizardButton.setEnabled(false);
                    removeStructuralAnalogue.setEnabled(false);
                    clearAllStructuralAnalogues.setEnabled(false);
                    structuralAnaloguesTable.setModel(new DefaultTableModel(new Object[][]{},
                            new Object[]{"Chemical Name", "Experimental Value"}));
                    analogueImageLabel.setIcon(new ImageIcon());
                    double similarityLevelValue = Double.parseDouble(similarityLevel.getText());
                    try {
                        qprfreport.getCompound().updateSimilar(similarityLevelValue);
                        synchronizeAnaloguesWRTReport();
                    } catch (ClientException ex) {
                        Logger.getLogger(ReportInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NumberFormatException ex) {
                    throw new RuntimeException(ex);
                }
                return null;
            }

            @Override
            protected void finished() {
                super.finished();
                compoundWizardButton.setEnabled(true);
                removeStructuralAnalogue.setEnabled(true);
                clearAllStructuralAnalogues.setEnabled(true);
            }

            @Override
            protected void succeeded(Object result) {
                super.succeeded(result);
                QEditApp.getView().getStatusLabel().setText(qprfreport.getCompound().getStructuralAnalogues().size()
                        + " Structural Analogues Loaded Successfully!");
            }
        };
        task.runInBackground();

    }//GEN-LAST:event_acquireListAnaloguesButtonActionPerformed

    private void similarityLevelCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_similarityLevelCaretUpdate
        try {
            Double similarityLevelValue = Double.parseDouble(similarityLevel.getText());

            if (similarityLevelValue < 0.5) {
                acquireListAnaloguesButton.setEnabled(false);
            } else {
                acquireListAnaloguesButton.setEnabled(true);
            }

        } catch (NumberFormatException ex) {
            acquireListAnaloguesButton.setEnabled(false);
        }

    }//GEN-LAST:event_similarityLevelCaretUpdate

    private void analogueInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analogueInfoActionPerformed

        JFrame jframe = QEditApp.getView().getFrame();
        Compound structuralAnalogueSelectedByOurBelovedUserThankYouForYourChoiseGodBleesYou_IfAny =
                qprfreport.getCompound().getStructuralAnalogues().get(structuralAnaloguesTable.getSelectedRow());
        CompoundDetailsDialog myDialogThatWillBePresentedToTheAboevementionedGreatUser =
                new CompoundDetailsDialog(jframe, structuralAnalogueSelectedByOurBelovedUserThankYouForYourChoiseGodBleesYou_IfAny);
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = myDialogThatWillBePresentedToTheAboevementionedGreatUser.getWidth();
        int dialogHeight = myDialogThatWillBePresentedToTheAboevementionedGreatUser.getHeight();
        int dialog_x = (frameWidth - dialogWidht) / 2;
        int dialog_y = (frameHeight - dialogHeight) / 2;
        myDialogThatWillBePresentedToTheAboevementionedGreatUser.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        myDialogThatWillBePresentedToTheAboevementionedGreatUser.setRif(this);
        myDialogThatWillBePresentedToTheAboevementionedGreatUser.setVisible(true);

    }//GEN-LAST:event_analogueInfoActionPerformed

    private void structuralAnaloguesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_structuralAnaloguesTableMouseClicked
        displayImageStructAnalogue();
    }//GEN-LAST:event_structuralAnaloguesTableMouseClicked

    private void displayImageStructAnalogue() {
        AbstractTask task = new AbstractTask() {

            @Override
            protected Object doInBackground() throws Exception {
                int selectedIndex = structuralAnaloguesTable.getSelectedRow();
                analogueImageLabel.setIcon(new ImageIcon());
                if (selectedIndex != -1) {
                    ImageIcon icon = qprfreport.getCompound().getStructuralAnalogues().get(selectedIndex).getUserIcon();
                    analogueImageLabel.setIcon(icon);
                    analogueInfo.setEnabled(true);
                }
                return null;
            }
        };
        task.runInBackground();
    }

    private void structuralAnaloguesTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_structuralAnaloguesTableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_KP_UP
                || evt.getKeyCode() == KeyEvent.VK_KP_DOWN
                || evt.getKeyCode() == KeyEvent.VK_UP
                || evt.getKeyCode() == KeyEvent.VK_DOWN
                || evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN
                || evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            displayImageStructAnalogue();
        }
    }//GEN-LAST:event_structuralAnaloguesTableKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acquireListAnaloguesButton;
    private javax.swing.JButton addCompoundSynonym;
    private javax.swing.JButton addDescriptorValueButton;
    private javax.swing.JLabel addDomLinkLabel;
    private javax.swing.JLabel addDomainResultLabel;
    private javax.swing.JPanel addDomainsDiscussionPanel;
    private javax.swing.JLabel adequacyBottomMessageLabel;
    private javax.swing.JLabel adequacyHintLabel;
    private javax.swing.JLabel adequacyInfoIndicatorLabel;
    private javax.swing.JPanel adequacyInfoPanel;
    private javax.swing.JPanel adequacyInfoSubPanel;
    private javax.swing.JButton algorithmDetailsButton;
    private javax.swing.JButton algorithmInfoToolButton;
    private javax.swing.JLabel algorithmNameLabel;
    private javax.swing.JTextField algorithmNameValue;
    private javax.swing.JLabel algorithmUriLabel;
    private javax.swing.JTextField algorithmUriValue;
    private javax.swing.JLabel analogueImageLabel;
    private javax.swing.JButton analogueInfo;
    private javax.swing.JLabel analoguesCommentsLabel;
    private javax.swing.JScrollPane analoguesCommentsScrollable;
    private javax.swing.JTextArea analoguesCommentsTextArea;
    private javax.swing.JPanel analoguesImagePanel;
    private javax.swing.JPanel analoguesPanel;
    private javax.swing.JTextField anyKeywordValueTextField;
    private javax.swing.JLabel appDomAlgorithmNameLabel;
    private javax.swing.JTextField appDomainAlgorithmNameValue;
    private javax.swing.JPanel appDomainInfoPanel;
    private javax.swing.JTextField appDomainURIValue;
    private javax.swing.JButton appendRowButton;
    private javax.swing.JPanel applicabilityDomainPanel;
    private javax.swing.JLabel applicabilityDomainValue;
    private javax.swing.JPanel authorsPanel;
    private javax.swing.JTable authorsTable;
    private javax.swing.JPopupMenu authorsTablePopupMenu;
    private javax.swing.JScrollPane authorsTableScrollable;
    private javax.swing.JToolBar authorsToolbar;
    private javax.swing.JButton authorsWizardButton;
    private javax.swing.JPanel basicContainerPanel;
    private javax.swing.JTabbedPane basicTabbedPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel chemBioMechanismsLabel;
    private javax.swing.JScrollPane chemBioMechanismsScrollable;
    private javax.swing.JTextArea chemBioMechanismsTextArea;
    private javax.swing.JLabel chooseDomainLabel;
    private javax.swing.JButton clearAllAuthorsButton;
    private javax.swing.JButton clearAllCompoundSynonyms;
    private javax.swing.JButton clearAllDescriptorsButton;
    private javax.swing.JButton clearAllStructuralAnalogues;
    private javax.swing.JButton clearStructureImgButton;
    private javax.swing.JButton cmlCompoundButton;
    private javax.swing.JPanel compoundDataInputPanel;
    private javax.swing.JPanel compoundIdentifierPanel;
    private javax.swing.JPanel compoundIdentifiersCards;
    private javax.swing.JLabel compoundIndentifiersLabel;
    private javax.swing.JPanel compoundInfoPanel;
    private javax.swing.JLabel compoundNameHint;
    private javax.swing.JScrollPane compoundNamesScrollable;
    private javax.swing.JPanel compoundPanel;
    private javax.swing.JLabel compoundSynonymsLabel;
    private javax.swing.JList compoundSynonymsList;
    private javax.swing.JPanel compoundSynonymsPanel;
    private javax.swing.JLabel compoundURILabel;
    private javax.swing.JLabel compoundUriHint;
    private javax.swing.JButton compoundWizardButton;
    private javax.swing.JToolBar compoundsToolbar;
    private javax.swing.JLabel conclusionHintLabel;
    private javax.swing.JLabel conclusionLabel;
    private javax.swing.JButton copyAlgorithmName;
    private javax.swing.JButton copyAlgorithmUriButton;
    private javax.swing.JButton copyDatasetButton;
    private javax.swing.JMenuItem copyDescriptorMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JButton copyModelUriButton;
    private javax.swing.JButton copyPredFeatureNameButton;
    private javax.swing.JButton copyPredFeatureUriButton;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JButton datasetDetailsButton;
    private javax.swing.JTextField datasetUriValueTextField;
    private javax.swing.JPanel datePanel;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JButton dependentFeatureToolButton;
    private javax.swing.JTextArea descriptorDomainText;
    private javax.swing.JMenuItem descriptorMetaMenuItem;
    private javax.swing.JPanel descriptorsPanel;
    private javax.swing.JPopupMenu descriptorsPopupMenu;
    private javax.swing.JScrollPane descriptorsScrollable;
    private javax.swing.JTable descriptorsTable;
    private javax.swing.JToolBar descriptorsTableToolbar;
    private javax.swing.JPanel domainCardPanel;
    private javax.swing.JComboBox domainChooserComboBox;
    private javax.swing.JButton downloadModelInfoButton;
    private javax.swing.JLabel experimentalValueLabel;
    private javax.swing.JTextField experimentalValueUnitsTextField;
    private javax.swing.JTextField exprerimentalValueTextField;
    private javax.swing.JPanel generalInfoPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton loadStructureImgButton;
    private javax.swing.JTextArea mechanismDomainText;
    private javax.swing.JPanel metaInfoPanel;
    private javax.swing.JTextArea metabolicDomainText;
    private javax.swing.JLabel modelAndTrainingDSHeader;
    private javax.swing.JCheckBox modelCurrentDate;
    private javax.swing.JLabel modelDateLabel;
    private javax.swing.JPanel modelDatePanel;
    private javax.swing.JComboBox modelDayCombo;
    private javax.swing.JLabel modelDayLabel;
    private javax.swing.JPanel modelInfoPanel;
    private javax.swing.JButton modelInfoToolButton;
    private javax.swing.JComboBox modelMonthCombo;
    private javax.swing.JLabel modelMonthLabel;
    private javax.swing.JPanel modelPanel;
    private javax.swing.JToolBar modelToolbar;
    private javax.swing.JButton modelUriDetailsButton;
    private javax.swing.JLabel modelUriLabel;
    private javax.swing.JTextField modelUriValue;
    private javax.swing.JLabel modelVersionHeader;
    private javax.swing.JTextArea modelVersionInfoTextArea;
    private javax.swing.JLabel modelVersionLabel;
    private javax.swing.JScrollPane modelVersionScrollPane;
    private javax.swing.JComboBox modelYearCombo;
    private javax.swing.JLabel modelYearLabel;
    private javax.swing.JButton moveDownRowButton;
    private javax.swing.JButton moveUpRowButton;
    private javax.swing.JPanel otherInfoPanel;
    private javax.swing.JLabel outcomeHintLabel;
    private javax.swing.JLabel outcomeLabel;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JButton predFeatureDetailsButton;
    private javax.swing.JLabel predFeatureNameLabel;
    private javax.swing.JLabel predFeatureResourceLabel;
    private javax.swing.JTextField predFeatureUriValue;
    private javax.swing.JLabel predictedFeatureHeadline;
    private javax.swing.JTextField predictedFeatureNameValue;
    private javax.swing.JButton predictedFeatureToolButton;
    private javax.swing.JLabel predictionCommentLabel;
    private javax.swing.JScrollPane predictionCommentScrollable;
    private javax.swing.JTextArea predictionCommentTextArea;
    private javax.swing.JPanel predictionInfoPanel;
    private javax.swing.JPanel predictionPanel;
    private javax.swing.JLabel predictionUncertaintyLabel;
    private javax.swing.JScrollPane predictionUncertaintyScrollable;
    private javax.swing.JTextArea predictionUncertaintyTextArea;
    private javax.swing.JTextField predictionUnitsTextField;
    private javax.swing.JLabel predictionValueLabel;
    private javax.swing.JTextField predictionValueTextField;
    private javax.swing.JLabel qmrfReportDiscussionLabel;
    private javax.swing.JScrollPane qmrfReportDiscussionScrollable;
    private javax.swing.JTextArea qmrfReportDiscussionTextArea;
    private javax.swing.JLabel qmrfReportHeader;
    private javax.swing.JLabel qmrfReportLabel;
    private javax.swing.JTextField qmrfReportTextField;
    private javax.swing.JLabel qprfCommentsHintLabel;
    private javax.swing.JLabel qprfCommentsLabel;
    private javax.swing.JComboBox qprfDayCombo;
    private javax.swing.JLabel qprfDescriptionHintLabel;
    private javax.swing.JLabel qprfDescriptionLabel;
    private javax.swing.JTabbedPane qprfInfoTabbedSubPanel;
    private javax.swing.JComboBox qprfMonthCombo;
    private javax.swing.JScrollPane qprfOutcomeScrollable;
    private javax.swing.JTextArea qprfOutcomeTextArea;
    private javax.swing.JScrollPane qprfReportCommentsScrollable;
    private javax.swing.JTextArea qprfReportCommentsTextArea;
    private javax.swing.JScrollPane qprfReportConclusionScollable;
    private javax.swing.JTextArea qprfReportConclusionTextArea;
    private javax.swing.JScrollPane qprfReportDescriptionScollable;
    private javax.swing.JTextArea qprfReportDescriptionTextArea;
    private javax.swing.JScrollPane qprfReportTitleScrollable;
    private javax.swing.JTextArea qprfReportTitleTextArea;
    private javax.swing.JLabel qprfTitleHintLabel;
    private javax.swing.JComboBox qprfYearCombo;
    private javax.swing.JButton rdfCompoundButton;
    private javax.swing.JScrollPane regulInterprScrollable;
    private javax.swing.JTextArea regulInterprTextArea;
    private javax.swing.JLabel regulatoryInterprHintLabel;
    private javax.swing.JLabel regulatoryInterprLabel;
    private javax.swing.JLabel regulatoryPurposeHintLabel;
    private javax.swing.JLabel regulatoryPurposeLabel;
    private javax.swing.JScrollPane regulatoryPurposeScrollable;
    private javax.swing.JTextArea regulatoryPurposeTextArea;
    private javax.swing.JMenuItem removeAuthorMenuItem;
    private javax.swing.JButton removeCompoundSynonym;
    private javax.swing.JMenuItem removeDescriptorLineMenuItem;
    private javax.swing.JButton removeDescriptorValueButton;
    private javax.swing.JButton removeSelectedRowButton;
    private javax.swing.JButton removeStructuralAnalogue;
    private javax.swing.JPanel reportGeneralInfoPanel;
    private javax.swing.JPanel reportPredictionPanel;
    private javax.swing.JButton showCompoundDetailsButton;
    private javax.swing.JButton showMagnifiedImage;
    private javax.swing.JTextField similarityLevel;
    private javax.swing.JTextArea stereoChemDiscussionTextArea;
    private javax.swing.JPanel stereoChemFeaturesDiscussionPanel;
    private javax.swing.JLabel stereoChemHintLabel;
    private javax.swing.JLabel stereoChemHintLamp;
    private javax.swing.JScrollPane stereoChemScollPane;
    private javax.swing.JToolBar.Separator structAnalToolbarSeparator;
    private javax.swing.JPanel structuralAnaloguesPanel;
    private javax.swing.JSeparator structuralAnaloguesSeparator;
    private javax.swing.JTable structuralAnaloguesTable;
    private javax.swing.JToolBar structuralAnaloguesToolbar;
    private javax.swing.JTextArea structuralFragmentDomainText;
    private javax.swing.JLabel structureImage;
    private javax.swing.JPanel structureImagePanel;
    private javax.swing.JToolBar structureImageToolbar;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel trainingAlgorithmHeader;
    private javax.swing.JLabel trainingDatasetLabel;
    private javax.swing.JTextField trainingDatasetValueTextField;
    private javax.swing.JButton updateAllCompoundInfoButton;
    private javax.swing.JCheckBox useCurrentDateForReport;
    // End of variables declaration//GEN-END:variables
    private AuthorWizard authorsWizard;
    private DescriptorValueWizard desriptorWizard;
    private StructuralAnalogueWizard_Step1 structuralAnalogueWizard;
    private DescriptorMetaDataDialog descriptorMetaDialog;
    private AlgorithmInfoDialog algorithmInfoDialog;
    private QPRFReport qprfreport = new QPRFReport();
    private ModelInfoDialog modelInfoDialog;
    private CompoundDetailsDialog compoundDetailsDialog;
    private boolean doaValue = true;
}


