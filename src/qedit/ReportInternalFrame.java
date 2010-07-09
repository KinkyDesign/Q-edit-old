/*
 * ReportInternalFrame.java
 *
 * Created on Jun 7, 2010, 9:47:54 AM
 */
package qedit;

import java.awt.CardLayout;
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
import qedit.hints.AdequacyHint;
import qedit.hints.QPRFCommentsHint;
import qedit.hints.RegulInterpretationHint;
import qedit.hints.RegulatoryPurposeHint;

/**
 *
 * @author Sopasakis Pantelis
 */
public class ReportInternalFrame extends javax.swing.JInternalFrame {

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
    public void analoguesRowSelectionAction(){
        int row = analoguesTable.getSelectedRow();
        System.out.println(row);
        DefaultTableModel model = (DefaultTableModel)analoguesTable.getModel();
        CardLayout cl = (CardLayout)(analoguesImageCards.getLayout());
        cl.show(analoguesImageCards, (String) model.getValueAt(row, 0));


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

    public JList getCompoundSynonymsList() {
        return compoundSynonymsList;
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
        basicContainerPanel = new javax.swing.JPanel();
        basicTabbedPanel = new javax.swing.JTabbedPane();
        compoundPanel = new javax.swing.JPanel();
        compoundInfoPanel = new javax.swing.JPanel();
        compoundSynonymsLabel = new javax.swing.JLabel();
        compoundNamesScrollable = new javax.swing.JScrollPane();
        compoundSynonymsList = new javax.swing.JList();
        compoundURILabel = new javax.swing.JLabel();
        compoundUriValue = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        casRnLabel = new javax.swing.JLabel();
        casRnValue = new javax.swing.JLabel();
        copyClipboardCompoundURI = new javax.swing.JButton();
        compoundNameHint = new javax.swing.JLabel();
        compoundsToolbar = new javax.swing.JToolBar();
        smilesButton = new javax.swing.JButton();
        inChIButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        cmlCompoundButton = new javax.swing.JButton();
        rdfCompoundButton = new javax.swing.JButton();
        compoundUriHint = new javax.swing.JLabel();
        addCompoundSynonym = new javax.swing.JButton();
        removeCompoundSynonym = new javax.swing.JButton();
        clearAllCompoundSynonyms = new javax.swing.JButton();
        structureImagePanel = new javax.swing.JPanel();
        structureImageToolbar = new javax.swing.JToolBar();
        loadStructureImgButton = new javax.swing.JButton();
        clearStructureImgButton = new javax.swing.JButton();
        structureImage = new javax.swing.JLabel();
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
        modelPanel = new javax.swing.JPanel();
        modelInfoPanel = new javax.swing.JPanel();
        modelUriLabel = new javax.swing.JLabel();
        modelUriValue = new javax.swing.JTextField();
        copyModelUriButton = new javax.swing.JButton();
        trainingDatasetLabel = new javax.swing.JLabel();
        datasetValue = new javax.swing.JTextField();
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
        structAnalToolbarSeparator = new javax.swing.JToolBar.Separator();
        removeStructuralAnalogue = new javax.swing.JButton();
        clearAllStructuralAnalogues = new javax.swing.JButton();
        structuralAnaloguesSeparator = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        analoguesCommentsLabel = new javax.swing.JLabel();
        analoguesPanel = new javax.swing.JPanel();
        analoguesTableScrollable = new javax.swing.JScrollPane();
        analoguesTable = new javax.swing.JTable();
        analoguesCommentsScrollable = new javax.swing.JScrollPane();
        analoguesCommentsTextArea = new javax.swing.JTextArea();
        analoguesImageCards = new javax.swing.JPanel();
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
        qprfReport = new javax.swing.JPanel();
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
        adequacyPanel = new javax.swing.JPanel();
        adequacyHintLabel = new javax.swing.JLabel();
        adequacyInfoPanel = new javax.swing.JPanel();
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

        compoundSynonymsLabel.setFont(resourceMap.getFont("compoundSynonymsLabel.font")); // NOI18N
        compoundSynonymsLabel.setText(resourceMap.getString("compoundSynonymsLabel.text")); // NOI18N
        compoundSynonymsLabel.setName("compoundSynonymsLabel"); // NOI18N

        compoundNamesScrollable.setName("compoundNamesScrollable"); // NOI18N

        compoundSynonymsList.setModel(new javax.swing.DefaultListModel() //{
            //    String[] strings = { "Benzen", "HexaCarbonoCycloSomething", "Benzol", "Benzosomething", "SomeOtherName" };
            //    public int getSize() { return strings.length; }
            //    public Object getElementAt(int i) { return strings[i]; }
            //}
    );
    compoundSynonymsList.setName("compoundSynonymsList"); // NOI18N
    compoundNamesScrollable.setViewportView(compoundSynonymsList);

    compoundURILabel.setFont(resourceMap.getFont("compoundURILabel.font")); // NOI18N
    compoundURILabel.setIcon(resourceMap.getIcon("compoundURILabel.icon")); // NOI18N
    compoundURILabel.setText(resourceMap.getString("compoundURILabel.text")); // NOI18N
    compoundURILabel.setName("compoundURILabel"); // NOI18N

    compoundUriValue.setForeground(resourceMap.getColor("compoundUriValue.foreground")); // NOI18N
    compoundUriValue.setText(resourceMap.getString("compoundUriValue.text")); // NOI18N
    compoundUriValue.setName("compoundUriValue"); // NOI18N

    jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jSeparator1.setName("jSeparator1"); // NOI18N

    casRnLabel.setText(resourceMap.getString("casRnLabel.text")); // NOI18N
    casRnLabel.setName("casRnLabel"); // NOI18N

    casRnValue.setText(resourceMap.getString("casRnValue.text")); // NOI18N
    casRnValue.setName("casRnValue"); // NOI18N

    copyClipboardCompoundURI.setIcon(resourceMap.getIcon("copyClipboardCompoundURI.icon")); // NOI18N
    copyClipboardCompoundURI.setText(resourceMap.getString("copyClipboardCompoundURI.text")); // NOI18N
    copyClipboardCompoundURI.setToolTipText(resourceMap.getString("copyClipboardCompoundURI.toolTipText")); // NOI18N
    copyClipboardCompoundURI.setName("copyClipboardCompoundURI"); // NOI18N

    compoundNameHint.setIcon(resourceMap.getIcon("compoundNameHint.icon")); // NOI18N
    compoundNameHint.setText(resourceMap.getString("compoundNameHint.text")); // NOI18N
    compoundNameHint.setName("compoundNameHint"); // NOI18N

    compoundsToolbar.setFloatable(false);
    compoundsToolbar.setRollover(true);
    compoundsToolbar.setName("compoundsToolbar"); // NOI18N

    smilesButton.setIcon(resourceMap.getIcon("smilesButton.icon")); // NOI18N
    smilesButton.setText(resourceMap.getString("smilesButton.text")); // NOI18N
    smilesButton.setToolTipText(resourceMap.getString("smilesButton.toolTipText")); // NOI18N
    smilesButton.setFocusable(false);
    smilesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    smilesButton.setName("smilesButton"); // NOI18N
    smilesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    compoundsToolbar.add(smilesButton);

    inChIButton.setIcon(resourceMap.getIcon("inChIButton.icon")); // NOI18N
    inChIButton.setText(resourceMap.getString("inChIButton.text")); // NOI18N
    inChIButton.setToolTipText(resourceMap.getString("inChIButton.toolTipText")); // NOI18N
    inChIButton.setFocusable(false);
    inChIButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    inChIButton.setName("inChIButton"); // NOI18N
    inChIButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    compoundsToolbar.add(inChIButton);

    jSeparator2.setName("jSeparator2"); // NOI18N
    compoundsToolbar.add(jSeparator2);

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

    compoundUriHint.setIcon(resourceMap.getIcon("compoundUriHint.icon")); // NOI18N
    compoundUriHint.setText(resourceMap.getString("compoundUriHint.text")); // NOI18N
    compoundUriHint.setName("compoundUriHint"); // NOI18N

    addCompoundSynonym.setIcon(resourceMap.getIcon("addCompoundSynonym.icon")); // NOI18N
    addCompoundSynonym.setText(resourceMap.getString("addCompoundSynonym.text")); // NOI18N
    addCompoundSynonym.setName("addCompoundSynonym"); // NOI18N
    addCompoundSynonym.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addCompoundSynonymActionPerformed(evt);
        }
    });

    removeCompoundSynonym.setIcon(resourceMap.getIcon("removeCompoundSynonym.icon")); // NOI18N
    removeCompoundSynonym.setText(resourceMap.getString("removeCompoundSynonym.text")); // NOI18N
    removeCompoundSynonym.setName("removeCompoundSynonym"); // NOI18N
    removeCompoundSynonym.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            removeCompoundSynonymActionPerformed(evt);
        }
    });

    clearAllCompoundSynonyms.setIcon(resourceMap.getIcon("clearAllCompoundSynonyms.icon")); // NOI18N
    clearAllCompoundSynonyms.setText(resourceMap.getString("clearAllCompoundSynonyms.text")); // NOI18N
    clearAllCompoundSynonyms.setName("clearAllCompoundSynonyms"); // NOI18N
    clearAllCompoundSynonyms.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearAllCompoundSynonymsActionPerformed(evt);
        }
    });

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

    structureImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    structureImage.setText(resourceMap.getString("structureImage.text")); // NOI18N
    structureImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    structureImage.setName("structureImage"); // NOI18N

    javax.swing.GroupLayout structureImagePanelLayout = new javax.swing.GroupLayout(structureImagePanel);
    structureImagePanel.setLayout(structureImagePanelLayout);
    structureImagePanelLayout.setHorizontalGroup(
        structureImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(structureImagePanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(structureImage, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addContainerGap())
        .addComponent(structureImageToolbar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
    );
    structureImagePanelLayout.setVerticalGroup(
        structureImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(structureImagePanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(structureImageToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(structureImage, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
            .addContainerGap())
    );

    structureImage.setIcon(new ImageIcon());

    javax.swing.GroupLayout compoundInfoPanelLayout = new javax.swing.GroupLayout(compoundInfoPanel);
    compoundInfoPanel.setLayout(compoundInfoPanelLayout);
    compoundInfoPanelLayout.setHorizontalGroup(
        compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundInfoPanelLayout.createSequentialGroup()
            .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                            .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                                    .addComponent(compoundSynonymsLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(compoundNameHint)
                                    .addGap(164, 164, 164))
                                .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                                    .addComponent(compoundNamesScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(clearAllCompoundSynonyms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addCompoundSynonym, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(removeCompoundSynonym, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                            .addComponent(compoundURILabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(compoundUriHint))
                        .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                            .addComponent(compoundUriValue, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(copyClipboardCompoundURI))
                        .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                            .addComponent(casRnLabel)
                            .addGap(36, 36, 36)
                            .addComponent(casRnValue)))
                    .addGap(18, 18, 18)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(structureImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(compoundsToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE))
            .addContainerGap())
    );
    compoundInfoPanelLayout.setVerticalGroup(
        compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundInfoPanelLayout.createSequentialGroup()
            .addComponent(compoundsToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(structureImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, compoundInfoPanelLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(compoundSynonymsLabel)
                        .addComponent(compoundNameHint))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                            .addComponent(addCompoundSynonym)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(removeCompoundSynonym)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(clearAllCompoundSynonyms))
                        .addComponent(compoundNamesScrollable, 0, 0, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                            .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(compoundURILabel)
                                .addComponent(compoundUriHint))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(compoundUriValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(copyClipboardCompoundURI))
                    .addGap(10, 10, 10)
                    .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(casRnLabel)
                        .addComponent(casRnValue))))
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
            .addComponent(descriptorsScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
            .addContainerGap())
    );

    stereoChemFeaturesDiscussionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("stereoChemFeaturesDiscussionPanel.border.title"))); // NOI18N
    stereoChemFeaturesDiscussionPanel.setName("stereoChemFeaturesDiscussionPanel"); // NOI18N

    stereoChemHintLabel.setText(resourceMap.getString("stereoChemHintLabel.text")); // NOI18N
    stereoChemHintLabel.setName("stereoChemHintLabel"); // NOI18N

    stereoChemScollPane.setName("stereoChemScollPane"); // NOI18N

    stereoChemDiscussionTextArea.setColumns(20);
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
                .addComponent(stereoChemScollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addGroup(stereoChemFeaturesDiscussionPanelLayout.createSequentialGroup()
                    .addComponent(stereoChemHintLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
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
            .addComponent(stereoChemScollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
            .addContainerGap())
    );

    javax.swing.GroupLayout compoundPanelLayout = new javax.swing.GroupLayout(compoundPanel);
    compoundPanel.setLayout(compoundPanelLayout);
    compoundPanelLayout.setHorizontalGroup(
        compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(compoundInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(compoundPanelLayout.createSequentialGroup()
                    .addComponent(descriptorsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(stereoChemFeaturesDiscussionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(180, 180, 180))
    );
    compoundPanelLayout.setVerticalGroup(
        compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(compoundPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(compoundInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stereoChemFeaturesDiscussionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(descriptorsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(33, 33, 33))
    );

    basicTabbedPanel.addTab(resourceMap.getString("compoundPanel.TabConstraints.tabTitle"), compoundPanel); // NOI18N

    modelPanel.setName("modelPanel"); // NOI18N

    modelInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("modelInfoPanel.border.title"))); // NOI18N
    modelInfoPanel.setName("modelInfoPanel"); // NOI18N

    modelUriLabel.setFont(resourceMap.getFont("modelUriLabel.font")); // NOI18N
    modelUriLabel.setIcon(resourceMap.getIcon("modelUriLabel.icon")); // NOI18N
    modelUriLabel.setText(resourceMap.getString("modelUriLabel.text")); // NOI18N
    modelUriLabel.setName("modelUriLabel"); // NOI18N

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

    datasetValue.setForeground(resourceMap.getColor("datasetValue.foreground")); // NOI18N
    datasetValue.setText(resourceMap.getString("datasetValue.text")); // NOI18N
    datasetValue.setName("datasetValue"); // NOI18N

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
    modelInfoToolButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            modelInfoToolButtonMouseClicked(evt);
        }
    });
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

    qmrfReportHeader.setFont(resourceMap.getFont("qmrfReportHeader.font")); // NOI18N
    qmrfReportHeader.setText(resourceMap.getString("qmrfReportHeader.text")); // NOI18N
    qmrfReportHeader.setName("qmrfReportHeader"); // NOI18N

    qmrfReportLabel.setText(resourceMap.getString("qmrfReportLabel.text")); // NOI18N
    qmrfReportLabel.setName("qmrfReportLabel"); // NOI18N

    qmrfReportTextField.setText(resourceMap.getString("qmrfReportTextField.text")); // NOI18N
    qmrfReportTextField.setName("qmrfReportTextField"); // NOI18N

    modelVersionHeader.setFont(resourceMap.getFont("modelVersionHeader.font")); // NOI18N
    modelVersionHeader.setText(resourceMap.getString("modelVersionHeader.text")); // NOI18N
    modelVersionHeader.setName("modelVersionHeader"); // NOI18N

    modelVersionLabel.setText(resourceMap.getString("modelVersionLabel.text")); // NOI18N
    modelVersionLabel.setName("modelVersionLabel"); // NOI18N

    modelVersionScrollPane.setName("modelVersionScrollPane"); // NOI18N

    modelVersionInfoTextArea.setColumns(20);
    modelVersionInfoTextArea.setRows(5);
    modelVersionInfoTextArea.setName("modelVersionInfoTextArea"); // NOI18N
    modelVersionScrollPane.setViewportView(modelVersionInfoTextArea);

    qmrfReportDiscussionScrollable.setName("qmrfReportDiscussionScrollable"); // NOI18N

    qmrfReportDiscussionTextArea.setColumns(20);
    qmrfReportDiscussionTextArea.setRows(5);
    qmrfReportDiscussionTextArea.setName("qmrfReportDiscussionTextArea"); // NOI18N
    qmrfReportDiscussionScrollable.setViewportView(qmrfReportDiscussionTextArea);

    qmrfReportDiscussionLabel.setText(resourceMap.getString("qmrfReportDiscussionLabel.text")); // NOI18N
    qmrfReportDiscussionLabel.setName("qmrfReportDiscussionLabel"); // NOI18N

    modelDatePanel.setName("modelDatePanel"); // NOI18N

    modelDateLabel.setText(resourceMap.getString("modelDateLabel.text")); // NOI18N
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

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getActionMap(ReportInternalFrame.class, this);
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
            .addContainerGap()
            .addGroup(modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(modelDateLabel)
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
                .addComponent(modelCurrentDate))
            .addContainerGap(71, Short.MAX_VALUE))
    );
    modelDatePanelLayout.setVerticalGroup(
        modelDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(modelDatePanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(modelDateLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                                            .addGap(86, 86, 86)
                                            .addComponent(algorithmNameLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(algorithmNameValue, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, modelInfoPanelLayout.createSequentialGroup()
                                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(qmrfReportLabel)
                                                .addComponent(predFeatureNameLabel)
                                                .addComponent(algorithmUriLabel)
                                                .addComponent(predFeatureResourceLabel)
                                                .addComponent(qmrfReportDiscussionLabel))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(predFeatureUriValue, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                                .addComponent(predictedFeatureNameValue, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                                .addComponent(algorithmUriValue, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modelInfoPanelLayout.createSequentialGroup()
                                                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(qmrfReportDiscussionScrollable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                                                        .addComponent(qmrfReportTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, modelInfoPanelLayout.createSequentialGroup()
                                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(trainingDatasetLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(modelUriLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addGap(18, 18, 18)
                                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(modelUriValue, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                                                .addComponent(datasetValue, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))))
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
                    .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(modelInfoPanelLayout.createSequentialGroup()
                            .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(modelVersionLabel)
                                .addComponent(modelVersionHeader)
                                .addComponent(modelVersionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(13, 13, 13))
                        .addComponent(modelDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(modelToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(datasetValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(modelDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())))
    );

    javax.swing.GroupLayout modelPanelLayout = new javax.swing.GroupLayout(modelPanel);
    modelPanel.setLayout(modelPanelLayout);
    modelPanelLayout.setHorizontalGroup(
        modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(modelPanelLayout.createSequentialGroup()
            .addComponent(modelInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(58, Short.MAX_VALUE))
    );
    modelPanelLayout.setVerticalGroup(
        modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(modelPanelLayout.createSequentialGroup()
            .addComponent(modelInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(13, Short.MAX_VALUE))
    );

    basicTabbedPanel.addTab(resourceMap.getString("modelPanel.TabConstraints.tabTitle"), modelPanel); // NOI18N

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
            .addContainerGap(240, Short.MAX_VALUE))
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

    metabolicDomainText.setColumns(20);
    metabolicDomainText.setRows(5);
    metabolicDomainText.setName("metabolicDomainText"); // NOI18N
    jScrollPane1.setViewportView(metabolicDomainText);

    domainCardPanel.add(jScrollPane1, "Metabolic Domain");

    jScrollPane2.setName("jScrollPane2"); // NOI18N

    structuralFragmentDomainText.setColumns(20);
    structuralFragmentDomainText.setRows(5);
    structuralFragmentDomainText.setName("structuralFragmentDomainText"); // NOI18N
    jScrollPane2.setViewportView(structuralFragmentDomainText);

    domainCardPanel.add(jScrollPane2, "Structural Fragment Domain");

    jScrollPane3.setName("jScrollPane3"); // NOI18N

    descriptorDomainText.setColumns(20);
    descriptorDomainText.setRows(5);
    descriptorDomainText.setName("descriptorDomainText"); // NOI18N
    jScrollPane3.setViewportView(descriptorDomainText);

    domainCardPanel.add(jScrollPane3, "Descriptor Domain");

    jScrollPane4.setName("jScrollPane4"); // NOI18N

    mechanismDomainText.setColumns(20);
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
            .addContainerGap(267, Short.MAX_VALUE))
    );
    addDomainsDiscussionPanelLayout.setVerticalGroup(
        addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(addDomainsDiscussionPanelLayout.createSequentialGroup()
            .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(domainCardPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
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

    structAnalToolbarSeparator.setName("structAnalToolbarSeparator"); // NOI18N
    structuralAnaloguesToolbar.add(structAnalToolbarSeparator);

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

    structuralAnaloguesSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);
    structuralAnaloguesSeparator.setName("structuralAnaloguesSeparator"); // NOI18N

    jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
    jLabel9.setName("jLabel9"); // NOI18N

    jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
    jSeparator6.setName("jSeparator6"); // NOI18N

    analoguesCommentsLabel.setText(resourceMap.getString("analoguesCommentsLabel.text")); // NOI18N
    analoguesCommentsLabel.setName("analoguesCommentsLabel"); // NOI18N

    analoguesPanel.setName("analoguesPanel"); // NOI18N

    analoguesTableScrollable.setName("analoguesTableScrollable"); // NOI18N

    analoguesTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Compound Name", "SMILES", "CAS-RN"
        }
    ));
    analoguesTable.setAutoCreateRowSorter(true);
    analoguesTable.setName("analoguesTable"); // NOI18N
    analoguesTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    analoguesTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            analoguesTableMouseClicked(evt);
        }
    });
    analoguesTableScrollable.setViewportView(analoguesTable);
    analoguesTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("analoguesTable.columnModel.title0")); // NOI18N
    analoguesTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("analoguesTable.columnModel.title1")); // NOI18N
    analoguesTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("analoguesTable.columnModel.title2")); // NOI18N

    javax.swing.GroupLayout analoguesPanelLayout = new javax.swing.GroupLayout(analoguesPanel);
    analoguesPanel.setLayout(analoguesPanelLayout);
    analoguesPanelLayout.setHorizontalGroup(
        analoguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 453, Short.MAX_VALUE)
        .addGroup(analoguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analoguesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(analoguesTableScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                .addContainerGap()))
    );
    analoguesPanelLayout.setVerticalGroup(
        analoguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 239, Short.MAX_VALUE)
        .addGroup(analoguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analoguesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(analoguesTableScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap()))
    );

    analoguesCommentsScrollable.setName("analoguesCommentsScrollable"); // NOI18N

    analoguesCommentsTextArea.setColumns(20);
    analoguesCommentsTextArea.setRows(5);
    analoguesCommentsTextArea.setName("analoguesCommentsTextArea"); // NOI18N
    analoguesCommentsScrollable.setViewportView(analoguesCommentsTextArea);

    analoguesImageCards.setName("analoguesImageCards"); // NOI18N
    analoguesImageCards.setLayout(new java.awt.CardLayout());

    javax.swing.GroupLayout structuralAnaloguesPanelLayout = new javax.swing.GroupLayout(structuralAnaloguesPanel);
    structuralAnaloguesPanel.setLayout(structuralAnaloguesPanelLayout);
    structuralAnaloguesPanelLayout.setHorizontalGroup(
        structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(structuralAnaloguesPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(analoguesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(structuralAnaloguesSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel9)
                .addComponent(analoguesImageCards, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(analoguesCommentsLabel)
                .addComponent(analoguesCommentsScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
            .addContainerGap())
        .addComponent(structuralAnaloguesToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
    );
    structuralAnaloguesPanelLayout.setVerticalGroup(
        structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(structuralAnaloguesPanelLayout.createSequentialGroup()
            .addComponent(structuralAnaloguesToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(analoguesPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(structuralAnaloguesSeparator, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, structuralAnaloguesPanelLayout.createSequentialGroup()
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(analoguesImageCards, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, structuralAnaloguesPanelLayout.createSequentialGroup()
                    .addComponent(analoguesCommentsLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(analoguesCommentsScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)))
            .addContainerGap())
    );

    javax.swing.GroupLayout applicabilityDomainPanelLayout = new javax.swing.GroupLayout(applicabilityDomainPanel);
    applicabilityDomainPanel.setLayout(applicabilityDomainPanelLayout);
    applicabilityDomainPanelLayout.setHorizontalGroup(
        applicabilityDomainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(applicabilityDomainPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(applicabilityDomainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(structuralAnaloguesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(appDomainInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addDomainsDiscussionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    applicabilityDomainPanelLayout.setVerticalGroup(
        applicabilityDomainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(applicabilityDomainPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(appDomainInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(structuralAnaloguesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(addDomainsDiscussionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(73, Short.MAX_VALUE))
    );

    basicTabbedPanel.addTab(resourceMap.getString("applicabilityDomainPanel.TabConstraints.tabTitle"), applicabilityDomainPanel); // NOI18N

    predictionPanel.setName("predictionPanel"); // NOI18N

    predictionInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("predictionInfoPanel.border.title"))); // NOI18N
    predictionInfoPanel.setName("predictionInfoPanel"); // NOI18N

    predictionValueLabel.setText(resourceMap.getString("predictionValueLabel.text")); // NOI18N
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
    predictionUncertaintyLabel.setName("predictionUncertaintyLabel"); // NOI18N

    predictionUncertaintyScrollable.setName("predictionUncertaintyScrollable"); // NOI18N

    predictionUncertaintyTextArea.setColumns(20);
    predictionUncertaintyTextArea.setRows(5);
    predictionUncertaintyTextArea.setName("predictionUncertaintyTextArea"); // NOI18N
    predictionUncertaintyScrollable.setViewportView(predictionUncertaintyTextArea);

    predictionCommentLabel.setLabelFor(predictionCommentTextArea);
    predictionCommentLabel.setText(resourceMap.getString("predictionCommentLabel.text")); // NOI18N
    predictionCommentLabel.setName("predictionCommentLabel"); // NOI18N

    predictionCommentScrollable.setName("predictionCommentScrollable"); // NOI18N

    predictionCommentTextArea.setColumns(20);
    predictionCommentTextArea.setRows(5);
    predictionCommentTextArea.setName("predictionCommentTextArea"); // NOI18N
    predictionCommentScrollable.setViewportView(predictionCommentTextArea);

    experimentalValueLabel.setText(resourceMap.getString("experimentalValueLabel.text")); // NOI18N
    experimentalValueLabel.setName("experimentalValueLabel"); // NOI18N

    exprerimentalValueTextField.setForeground(resourceMap.getColor("exprerimentalValueTextField.foreground")); // NOI18N
    exprerimentalValueTextField.setText(resourceMap.getString("exprerimentalValueTextField.text")); // NOI18N
    exprerimentalValueTextField.setName("exprerimentalValueTextField"); // NOI18N

    experimentalValueUnitsTextField.setText(resourceMap.getString("experimentalValueUnitsTextField.text")); // NOI18N
    experimentalValueUnitsTextField.setToolTipText(resourceMap.getString("experimentalValueUnitsTextField.toolTipText")); // NOI18N
    experimentalValueUnitsTextField.setName("experimentalValueUnitsTextField"); // NOI18N

    javax.swing.GroupLayout predictionInfoPanelLayout = new javax.swing.GroupLayout(predictionInfoPanel);
    predictionInfoPanel.setLayout(predictionInfoPanelLayout);
    predictionInfoPanelLayout.setHorizontalGroup(
        predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(predictionInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(predictionCommentScrollable, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, predictionInfoPanelLayout.createSequentialGroup()
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
                            .addComponent(predictionUnitsTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))))
                .addComponent(predictionCommentLabel))
            .addGap(70, 70, 70)
            .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(predictionUncertaintyLabel)
                .addComponent(predictionUncertaintyScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(113, Short.MAX_VALUE))
    );
    predictionInfoPanelLayout.setVerticalGroup(
        predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(predictionInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(predictionValueLabel)
                .addComponent(predictionValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(predictionUnitsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(predictionUncertaintyScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addComponent(predictionCommentScrollable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout predictionPanelLayout = new javax.swing.GroupLayout(predictionPanel);
    predictionPanel.setLayout(predictionPanelLayout);
    predictionPanelLayout.setHorizontalGroup(
        predictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(predictionPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(predictionInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    predictionPanelLayout.setVerticalGroup(
        predictionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(predictionPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(predictionInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(358, Short.MAX_VALUE))
    );

    basicTabbedPanel.addTab(resourceMap.getString("predictionPanel.TabConstraints.tabTitle"), predictionPanel); // NOI18N

    qprfReport.setName("qprfReport"); // NOI18N

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
    authorsTable.setCellSelectionEnabled(true);
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
        .addComponent(authorsTableScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
        .addComponent(authorsToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
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
            .addContainerGap(631, Short.MAX_VALUE))
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

    javax.swing.GroupLayout generalInfoPanelLayout = new javax.swing.GroupLayout(generalInfoPanel);
    generalInfoPanel.setLayout(generalInfoPanelLayout);
    generalInfoPanelLayout.setHorizontalGroup(
        generalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(generalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(datePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(authorsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    generalInfoPanelLayout.setVerticalGroup(
        generalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(generalInfoPanelLayout.createSequentialGroup()
            .addGap(27, 27, 27)
            .addComponent(authorsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(30, 30, 30)
            .addComponent(datePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(243, Short.MAX_VALUE))
    );

    qprfInfoTabbedSubPanel.addTab(resourceMap.getString("generalInfoPanel.TabConstraints.tabTitle"), generalInfoPanel); // NOI18N

    adequacyPanel.setName("adequacyPanel"); // NOI18N

    adequacyHintLabel.setIcon(resourceMap.getIcon("adequacyHintLabel.icon")); // NOI18N
    adequacyHintLabel.setText(resourceMap.getString("adequacyHintLabel.text")); // NOI18N
    adequacyHintLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    adequacyHintLabel.setName("adequacyHintLabel"); // NOI18N
    adequacyHintLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            adequacyHintLabelMouseClicked(evt);
        }
    });

    adequacyInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("adequacyInfoPanel.border.title"))); // NOI18N
    adequacyInfoPanel.setName("adequacyInfoPanel"); // NOI18N

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
    regulatoryPurposeTextArea.setRows(5);
    regulatoryPurposeTextArea.setName("regulatoryPurposeTextArea"); // NOI18N
    regulatoryPurposeScrollable.setViewportView(regulatoryPurposeTextArea);

    regulInterprScrollable.setName("regulInterprScrollable"); // NOI18N

    regulInterprTextArea.setColumns(20);
    regulInterprTextArea.setRows(5);
    regulInterprTextArea.setName("regulInterprTextArea"); // NOI18N
    regulInterprScrollable.setViewportView(regulInterprTextArea);

    qprfOutcomeScrollable.setName("qprfOutcomeScrollable"); // NOI18N

    qprfOutcomeTextArea.setColumns(20);
    qprfOutcomeTextArea.setRows(5);
    qprfOutcomeTextArea.setName("qprfOutcomeTextArea"); // NOI18N
    qprfOutcomeScrollable.setViewportView(qprfOutcomeTextArea);

    qprfReportConclusionScollable.setName("qprfReportConclusionScollable"); // NOI18N

    qprfReportConclusionTextArea.setColumns(20);
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

    javax.swing.GroupLayout adequacyInfoPanelLayout = new javax.swing.GroupLayout(adequacyInfoPanel);
    adequacyInfoPanel.setLayout(adequacyInfoPanelLayout);
    adequacyInfoPanelLayout.setHorizontalGroup(
        adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(adequacyInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(adequacyInfoPanelLayout.createSequentialGroup()
                    .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(adequacyInfoPanelLayout.createSequentialGroup()
                            .addComponent(regulatoryPurposeLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(regulatoryPurposeHintLabel)
                            .addGap(267, 267, 267))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adequacyInfoPanelLayout.createSequentialGroup()
                            .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(qprfOutcomeScrollable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, adequacyInfoPanelLayout.createSequentialGroup()
                                    .addComponent(outcomeLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(outcomeHintLabel))
                                .addComponent(regulatoryPurposeScrollable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                            .addGap(27, 27, 27)))
                    .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(qprfReportConclusionScollable, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                        .addGroup(adequacyInfoPanelLayout.createSequentialGroup()
                            .addComponent(conclusionLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(conclusionHintLabel))
                        .addGroup(adequacyInfoPanelLayout.createSequentialGroup()
                            .addComponent(regulatoryInterprLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(regulatoryInterprHintLabel))
                        .addComponent(regulInterprScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)))
                .addGroup(adequacyInfoPanelLayout.createSequentialGroup()
                    .addComponent(adequacyInfoIndicatorLabel)
                    .addGap(18, 18, 18)
                    .addComponent(adequacyBottomMessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)))
            .addContainerGap())
    );
    adequacyInfoPanelLayout.setVerticalGroup(
        adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adequacyInfoPanelLayout.createSequentialGroup()
            .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(regulatoryPurposeHintLabel)
                .addComponent(regulatoryInterprHintLabel)
                .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regulatoryInterprLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regulatoryPurposeLabel)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(regulatoryPurposeScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addComponent(regulInterprScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(outcomeLabel)
                .addComponent(conclusionLabel)
                .addComponent(outcomeHintLabel)
                .addComponent(conclusionHintLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(qprfReportConclusionScollable)
                .addComponent(qprfOutcomeScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
            .addGap(30, 30, 30)
            .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(adequacyInfoIndicatorLabel)
                .addComponent(adequacyBottomMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    javax.swing.GroupLayout adequacyPanelLayout = new javax.swing.GroupLayout(adequacyPanel);
    adequacyPanel.setLayout(adequacyPanelLayout);
    adequacyPanelLayout.setHorizontalGroup(
        adequacyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adequacyPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(adequacyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(adequacyInfoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adequacyHintLabel))
            .addContainerGap())
    );
    adequacyPanelLayout.setVerticalGroup(
        adequacyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(adequacyPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(adequacyHintLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(adequacyInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(47, Short.MAX_VALUE))
    );

    qprfInfoTabbedSubPanel.addTab(resourceMap.getString("adequacyPanel.TabConstraints.tabTitle"), adequacyPanel); // NOI18N

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
    qprfReportDescriptionTextArea.setRows(5);
    qprfReportDescriptionTextArea.setName("qprfReportDescriptionTextArea"); // NOI18N
    qprfReportDescriptionScollable.setViewportView(qprfReportDescriptionTextArea);

    qprfReportCommentsScrollable.setName("qprfReportCommentsScrollable"); // NOI18N

    qprfReportCommentsTextArea.setColumns(20);
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
                .addComponent(qprfReportTitleScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
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
                .addComponent(qprfReportDescriptionScollable, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                .addComponent(qprfReportCommentsScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE))
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

    javax.swing.GroupLayout otherInfoPanelLayout = new javax.swing.GroupLayout(otherInfoPanel);
    otherInfoPanel.setLayout(otherInfoPanelLayout);
    otherInfoPanelLayout.setHorizontalGroup(
        otherInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(otherInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(metaInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    otherInfoPanelLayout.setVerticalGroup(
        otherInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(otherInfoPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(metaInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(210, Short.MAX_VALUE))
    );

    qprfInfoTabbedSubPanel.addTab("Other", otherInfoPanel);

    javax.swing.GroupLayout qprfReportLayout = new javax.swing.GroupLayout(qprfReport);
    qprfReport.setLayout(qprfReportLayout);
    qprfReportLayout.setHorizontalGroup(
        qprfReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(qprfInfoTabbedSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
    );
    qprfReportLayout.setVerticalGroup(
        qprfReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(qprfInfoTabbedSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
    );

    basicTabbedPanel.addTab(resourceMap.getString("qprfReport.TabConstraints.tabTitle"), qprfReport); // NOI18N

    javax.swing.GroupLayout basicContainerPanelLayout = new javax.swing.GroupLayout(basicContainerPanel);
    basicContainerPanel.setLayout(basicContainerPanelLayout);
    basicContainerPanelLayout.setHorizontalGroup(
        basicContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, basicContainerPanelLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(basicTabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1064, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(176, 176, 176))
    );
    basicContainerPanelLayout.setVerticalGroup(
        basicContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(basicTabbedPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(basicContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(basicContainerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void adequacyHintLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adequacyHintLabelMouseClicked
        if (MouseEvent.BUTTON1 == evt.getButton()) { // left click
            AdequacyHint hint = new AdequacyHint(QEditApp.getView().getFrame(), true);
            hint.setBounds(new Rectangle(evt.getLocationOnScreen(), new Dimension(hint.getWidth(), hint.getHeight())));
            hint.setVisible(true);
        }
    }//GEN-LAST:event_adequacyHintLabelMouseClicked

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
    }//GEN-LAST:event_authorsWizardButtonActionPerformed

    private void clearAllRows(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }

    public JPanel getAnaloguesImageCards() {
        return analoguesImageCards;
    }

    public JTable getAnaloguesTable() {
        return analoguesTable;
    }

    private void clearAllAuthorsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllAuthorsButtonActionPerformed
        clearAllRows(authorsTable);
    }//GEN-LAST:event_clearAllAuthorsButtonActionPerformed

    private void clearAllDescriptorsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllDescriptorsButtonActionPerformed
        clearAllRows(descriptorsTable);
    }//GEN-LAST:event_clearAllDescriptorsButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        desriptorWizard = new DescriptorValueWizard(QEditApp.getView().getFrame());
        desriptorWizard.setDescriptorsTable(descriptorsTable);
        desriptorWizard.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void compoundWizardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundWizardButtonActionPerformed
        JFrame jframe = QEditApp.getView().getFrame();
        structuralAnalogueWizard = new StructuralAnalogueWizard_Step1(jframe,this);
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
        clearAllRows(analoguesTable);
    }//GEN-LAST:event_clearAllStructuralAnaloguesActionPerformed

    private void removeStructuralAnalogueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeStructuralAnalogueActionPerformed
        removeSelectedRows(analoguesTable);
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

    private void modelInfoToolButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modelInfoToolButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_modelInfoToolButtonMouseClicked

    private void modelInfoToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelInfoToolButtonActionPerformed
        ModelInfoDialog modelInfoDialog = new ModelInfoDialog(QEditApp.getApplication().getMainFrame());
        modelInfoDialog.setVisible(true);
        modelInfoDialog.getTrainingAlgorithmField().setText(algorithmUriValue.getText());
        modelInfoDialog.getTrainingDatasetField().setText(datasetValue.getText());
        modelInfoDialog.getPredictionFeatureField().setText(predFeatureUriValue.getText());
        modelInfoDialog.getModelUriField().setText(modelUriValue.getText());

    }//GEN-LAST:event_modelInfoToolButtonActionPerformed

    private void algorithmInfoToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmInfoToolButtonActionPerformed
        AlgorithmInfoDialog algorithmInfoDialog = new AlgorithmInfoDialog(QEditApp.getApplication().getMainFrame());
        algorithmInfoDialog.setVisible(true);
        algorithmInfoDialog.getAlgorithmURIField().setText(algorithmUriValue.getText());
        algorithmInfoDialog.getTitleField().setText("some title");
    }//GEN-LAST:event_algorithmInfoToolButtonActionPerformed

    private void predictedFeatureToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predictedFeatureToolButtonActionPerformed
        FeatureInfoDialogue featureInfoDialog = new FeatureInfoDialogue(QEditApp.getApplication().getMainFrame());
        featureInfoDialog.setVisible(true);
    }//GEN-LAST:event_predictedFeatureToolButtonActionPerformed

    private void dependentFeatureToolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dependentFeatureToolButtonActionPerformed
        FeatureInfoDialogue featureInfoDialog = new FeatureInfoDialogue(QEditApp.getApplication().getMainFrame());
        featureInfoDialog.setVisible(true);
    }//GEN-LAST:event_dependentFeatureToolButtonActionPerformed

    private void addCompoundSynonymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCompoundSynonymActionPerformed
        AddSynonymDialog addSynonymDialog = new AddSynonymDialog(QEditApp.getApplication().getMainFrame(), this);
        addSynonymDialog.setVisible(true);
    }//GEN-LAST:event_addCompoundSynonymActionPerformed

    private void clearAllCompoundSynonymsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllCompoundSynonymsActionPerformed
        getCompoundSynonymsList().setModel(new DefaultListModel());
    }//GEN-LAST:event_clearAllCompoundSynonymsActionPerformed

    private void removeCompoundSynonymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeCompoundSynonymActionPerformed
        int index = getCompoundSynonymsList().getSelectedIndex();
        if (index > -1) {
            ((DefaultListModel) getCompoundSynonymsList().getModel()).remove(index);
        }
    }//GEN-LAST:event_removeCompoundSynonymActionPerformed

    private void clearStructureImgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearStructureImgButtonActionPerformed
        structureImage.setIcon(new ImageIcon());
        structureImage.setText("No Image");
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
        CardLayout cl = (CardLayout)(domainCardPanel.getLayout());
        cl.show(domainCardPanel, (String)evt.getItem());
        System.out.println((String)evt.getItem());
    }//GEN-LAST:event_domainChooserComboBoxItemStateChanged

    private void analoguesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_analoguesTableMouseClicked
        analoguesRowSelectionAction();
    }//GEN-LAST:event_analoguesTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCompoundSynonym;
    private javax.swing.JButton addDescriptorValueButton;
    private javax.swing.JLabel addDomLinkLabel;
    private javax.swing.JLabel addDomainResultLabel;
    private javax.swing.JPanel addDomainsDiscussionPanel;
    private javax.swing.JLabel adequacyBottomMessageLabel;
    private javax.swing.JLabel adequacyHintLabel;
    private javax.swing.JLabel adequacyInfoIndicatorLabel;
    private javax.swing.JPanel adequacyInfoPanel;
    private javax.swing.JPanel adequacyPanel;
    private javax.swing.JButton algorithmDetailsButton;
    private javax.swing.JButton algorithmInfoToolButton;
    private javax.swing.JLabel algorithmNameLabel;
    private javax.swing.JTextField algorithmNameValue;
    private javax.swing.JLabel algorithmUriLabel;
    private javax.swing.JTextField algorithmUriValue;
    private javax.swing.JLabel analoguesCommentsLabel;
    private javax.swing.JScrollPane analoguesCommentsScrollable;
    private javax.swing.JTextArea analoguesCommentsTextArea;
    private javax.swing.JPanel analoguesImageCards;
    private javax.swing.JPanel analoguesPanel;
    private javax.swing.JTable analoguesTable;
    private javax.swing.JScrollPane analoguesTableScrollable;
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
    private javax.swing.JLabel casRnLabel;
    private javax.swing.JLabel casRnValue;
    private javax.swing.JLabel chooseDomainLabel;
    private javax.swing.JButton clearAllAuthorsButton;
    private javax.swing.JButton clearAllCompoundSynonyms;
    private javax.swing.JButton clearAllDescriptorsButton;
    private javax.swing.JButton clearAllStructuralAnalogues;
    private javax.swing.JButton clearStructureImgButton;
    private javax.swing.JButton cmlCompoundButton;
    private javax.swing.JPanel compoundInfoPanel;
    private javax.swing.JLabel compoundNameHint;
    private javax.swing.JScrollPane compoundNamesScrollable;
    private javax.swing.JPanel compoundPanel;
    private javax.swing.JLabel compoundSynonymsLabel;
    private javax.swing.JList compoundSynonymsList;
    private javax.swing.JLabel compoundURILabel;
    private javax.swing.JLabel compoundUriHint;
    private javax.swing.JTextField compoundUriValue;
    private javax.swing.JButton compoundWizardButton;
    private javax.swing.JToolBar compoundsToolbar;
    private javax.swing.JLabel conclusionHintLabel;
    private javax.swing.JLabel conclusionLabel;
    private javax.swing.JButton copyAlgorithmName;
    private javax.swing.JButton copyAlgorithmUriButton;
    private javax.swing.JButton copyClipboardCompoundURI;
    private javax.swing.JButton copyDatasetButton;
    private javax.swing.JMenuItem copyDescriptorMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JButton copyModelUriButton;
    private javax.swing.JButton copyPredFeatureNameButton;
    private javax.swing.JButton copyPredFeatureUriButton;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JButton datasetDetailsButton;
    private javax.swing.JTextField datasetValue;
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
    private javax.swing.JLabel experimentalValueLabel;
    private javax.swing.JTextField experimentalValueUnitsTextField;
    private javax.swing.JTextField exprerimentalValueTextField;
    private javax.swing.JPanel generalInfoPanel;
    private javax.swing.JButton inChIButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
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
    private javax.swing.JPanel qprfReport;
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
    private javax.swing.JButton smilesButton;
    private javax.swing.JTextArea stereoChemDiscussionTextArea;
    private javax.swing.JPanel stereoChemFeaturesDiscussionPanel;
    private javax.swing.JLabel stereoChemHintLabel;
    private javax.swing.JLabel stereoChemHintLamp;
    private javax.swing.JScrollPane stereoChemScollPane;
    private javax.swing.JToolBar.Separator structAnalToolbarSeparator;
    private javax.swing.JPanel structuralAnaloguesPanel;
    private javax.swing.JSeparator structuralAnaloguesSeparator;
    private javax.swing.JToolBar structuralAnaloguesToolbar;
    private javax.swing.JTextArea structuralFragmentDomainText;
    private javax.swing.JLabel structureImage;
    private javax.swing.JPanel structureImagePanel;
    private javax.swing.JToolBar structureImageToolbar;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel trainingAlgorithmHeader;
    private javax.swing.JLabel trainingDatasetLabel;
    private javax.swing.JCheckBox useCurrentDateForReport;
    // End of variables declaration//GEN-END:variables
    private AuthorWizard authorsWizard;
    private DescriptorValueWizard desriptorWizard;
    private StructuralAnalogueWizard_Step1 structuralAnalogueWizard;
    private DescriptorMetaDataDialog descriptorMetaDialog;

}


