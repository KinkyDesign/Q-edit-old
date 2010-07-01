/*
 * ReportInternalFrame.java
 *
 * Created on Jun 7, 2010, 9:47:54 AM
 */
package qedit;

import qedit.hints.QPRFDescriptionHint;
import java.awt.Dimension;
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
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.application.Action;
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
        QEditApp.getView().getStatusLabel().setText("Report info retrieved from remote resource");
    }   

    @Action
    public void addLineAuthors() {
    }

    @Action
    public void enableDisableDate() {
        boolean enablement = !jCheckBox1.isSelected();
        jComboBox1.setEnabled(enablement);
        jComboBox2.setEnabled(enablement);
        jComboBox3.setEnabled(enablement);
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
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
        compoundImageLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextArea12 = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        modelPanel = new javax.swing.JPanel();
        modelInfoPanel = new javax.swing.JPanel();
        modelUriLabel = new javax.swing.JLabel();
        modelUriValue = new javax.swing.JTextField();
        copyModelUriButton = new javax.swing.JButton();
        trainingDatasetLabel = new javax.swing.JLabel();
        datasetValue = new javax.swing.JTextField();
        copyDatasetButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea11 = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextArea13 = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        applicabilityDomainPanel = new javax.swing.JPanel();
        appDomainInfoPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        appDomainAlgorithmNameValue = new javax.swing.JTextField();
        appDomainURIValue = new javax.swing.JTextField();
        addDomainsDiscussionPanel = new javax.swing.JPanel();
        appDomainsLabel = new javax.swing.JLabel();
        appDomainsScrollable = new javax.swing.JScrollPane();
        appDomainsTextArea = new javax.swing.JTextArea();
        addDomainResultLabel = new javax.swing.JLabel();
        applicabilityDomainValue = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea8 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea9 = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea10 = new javax.swing.JTextArea();
        structuralAnaloguesPanel = new javax.swing.JPanel();
        structuralAnaloguesToolbar = new javax.swing.JToolBar();
        compoundWizardButton = new javax.swing.JButton();
        structAnalToolbarSeparator = new javax.swing.JToolBar.Separator();
        removeStructuralAnalogue = new javax.swing.JButton();
        clearAllStructuralAnalogues = new javax.swing.JButton();
        structuralAnaloguesSeparator = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        compoundsTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        predictionPanel = new javax.swing.JPanel();
        predictionInfoPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextArea14 = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextArea15 = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
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
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
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
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();

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

        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane17.setName("jScrollPane17"); // NOI18N

        basicTabbedPanel.setName("basicTabbedPanel"); // NOI18N

        compoundPanel.setName("compoundPanel"); // NOI18N

        compoundInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("compoundInfoPanel.border.title"))); // NOI18N
        compoundInfoPanel.setName("compoundInfoPanel"); // NOI18N

        compoundSynonymsLabel.setFont(resourceMap.getFont("compoundSynonymsLabel.font")); // NOI18N
        compoundSynonymsLabel.setText(resourceMap.getString("compoundSynonymsLabel.text")); // NOI18N
        compoundSynonymsLabel.setName("compoundSynonymsLabel"); // NOI18N

        compoundNamesScrollable.setName("compoundNamesScrollable"); // NOI18N

        compoundSynonymsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Benzen", "HexaCarbonoCycloSomething", "Benzol", "Benzosomething", "SomeOtherName" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        compoundSynonymsList.setName("compoundSynonymsList"); // NOI18N
        compoundNamesScrollable.setViewportView(compoundSynonymsList);

        compoundURILabel.setFont(resourceMap.getFont("compoundURILabel.font")); // NOI18N
        compoundURILabel.setIcon(resourceMap.getIcon("compoundURILabel.icon")); // NOI18N
        compoundURILabel.setText(resourceMap.getString("compoundURILabel.text")); // NOI18N
        compoundURILabel.setName("compoundURILabel"); // NOI18N

        compoundUriValue.setEditable(false);
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

        compoundImageLabel.setText(resourceMap.getString("compoundImageLabel.text")); // NOI18N
        compoundImageLabel.setName("compoundImageLabel"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N

        jButton4.setIcon(resourceMap.getIcon("jButton4.icon")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        javax.swing.GroupLayout compoundInfoPanelLayout = new javax.swing.GroupLayout(compoundInfoPanel);
        compoundInfoPanel.setLayout(compoundInfoPanelLayout);
        compoundInfoPanelLayout.setHorizontalGroup(
            compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, compoundInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                        .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                                .addComponent(compoundSynonymsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compoundNameHint)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addComponent(compoundNamesScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                        .addComponent(compoundURILabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(compoundUriHint))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, compoundInfoPanelLayout.createSequentialGroup()
                        .addComponent(compoundUriValue)
                        .addGap(18, 18, 18)
                        .addComponent(copyClipboardCompoundURI))
                    .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                        .addComponent(casRnLabel)
                        .addGap(36, 36, 36)
                        .addComponent(casRnValue)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
                .addComponent(compoundImageLabel)
                .addGap(422, 422, 422))
            .addComponent(compoundsToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1417, Short.MAX_VALUE)
        );
        compoundInfoPanelLayout.setVerticalGroup(
            compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                .addComponent(compoundsToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(compoundSynonymsLabel)
                            .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(compoundNameHint)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addComponent(compoundNamesScrollable, 0, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(compoundURILabel)
                            .addComponent(compoundUriHint))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                                .addComponent(compoundUriValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addGroup(compoundInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(casRnLabel)
                                    .addComponent(casRnValue)))
                            .addComponent(copyClipboardCompoundURI))
                        .addGap(42, 42, 42))
                    .addGroup(compoundInfoPanelLayout.createSequentialGroup()
                        .addComponent(compoundImageLabel)
                        .addContainerGap())))
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
            .addComponent(descriptorsTableToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
            .addComponent(descriptorsScrollable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        descriptorsPanelLayout.setVerticalGroup(
            descriptorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descriptorsPanelLayout.createSequentialGroup()
                .addComponent(descriptorsTableToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptorsScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jScrollPane13.setName("jScrollPane13"); // NOI18N

        jTextArea12.setColumns(20);
        jTextArea12.setRows(5);
        jTextArea12.setName("jTextArea12"); // NOI18N
        jScrollPane13.setViewportView(jTextArea12);

        jLabel21.setIcon(resourceMap.getIcon("jLabel21.icon")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.setName("jLabel21"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout compoundPanelLayout = new javax.swing.GroupLayout(compoundPanel);
        compoundPanel.setLayout(compoundPanelLayout);
        compoundPanelLayout.setHorizontalGroup(
            compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(compoundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, compoundPanelLayout.createSequentialGroup()
                        .addComponent(descriptorsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(compoundInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        compoundPanelLayout.setVerticalGroup(
            compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(compoundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(compoundInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(compoundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descriptorsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        algorithmNameLabel.setText(resourceMap.getString("algorithmNameLabel.text")); // NOI18N
        algorithmNameLabel.setName("algorithmNameLabel"); // NOI18N

        modelUriDetailsButton.setIcon(resourceMap.getIcon("modelUriDetailsButton.icon")); // NOI18N
        modelUriDetailsButton.setText(resourceMap.getString("modelUriDetailsButton.text")); // NOI18N
        modelUriDetailsButton.setToolTipText(resourceMap.getString("modelUriDetailsButton.toolTipText")); // NOI18N
        modelUriDetailsButton.setName("modelUriDetailsButton"); // NOI18N

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
        modelToolbar.add(predictedFeatureToolButton);

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jButton5.setIcon(resourceMap.getIcon("jButton5.icon")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator9.setName("jSeparator9"); // NOI18N

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jScrollPane12.setName("jScrollPane12"); // NOI18N

        jTextArea11.setColumns(20);
        jTextArea11.setRows(5);
        jTextArea11.setName("jTextArea11"); // NOI18N
        jScrollPane12.setViewportView(jTextArea11);

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jScrollPane14.setName("jScrollPane14"); // NOI18N

        jTextArea13.setColumns(20);
        jTextArea13.setRows(5);
        jTextArea13.setName("jTextArea13"); // NOI18N
        jScrollPane14.setViewportView(jTextArea13);

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        javax.swing.GroupLayout modelInfoPanelLayout = new javax.swing.GroupLayout(modelInfoPanel);
        modelInfoPanel.setLayout(modelInfoPanelLayout);
        modelInfoPanelLayout.setHorizontalGroup(
            modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(modelToolbar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1417, Short.MAX_VALUE)
            .addGroup(modelInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(predictedFeatureHeadline)
                    .addComponent(jLabel13)
                    .addComponent(jLabel7)
                    .addGroup(modelInfoPanelLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(modelUriLabel)
                            .addComponent(algorithmNameLabel)
                            .addComponent(trainingDatasetLabel)
                            .addComponent(algorithmUriLabel)
                            .addComponent(predFeatureNameLabel)
                            .addComponent(predFeatureResourceLabel)
                            .addComponent(jLabel8)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane14)
                            .addComponent(jTextField1)
                            .addComponent(predFeatureUriValue)
                            .addComponent(predictedFeatureNameValue)
                            .addComponent(algorithmUriValue)
                            .addComponent(algorithmNameValue)
                            .addComponent(datasetValue)
                            .addComponent(modelUriValue, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                .addComponent(copyAlgorithmUriButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(algorithmDetailsButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5))
                            .addComponent(copyAlgorithmName)
                            .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(copyDatasetButton)
                                    .addComponent(copyModelUriButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(modelUriDetailsButton)
                                    .addComponent(datasetDetailsButton)))
                            .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(copyPredFeatureUriButton)
                                    .addComponent(copyPredFeatureNameButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(predFeatureDetailsButton)))))
                .addGap(59, 59, 59)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
                .addContainerGap())
        );
        modelInfoPanelLayout.setVerticalGroup(
            modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modelInfoPanelLayout.createSequentialGroup()
                .addComponent(modelToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modelInfoPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                        .addComponent(copyModelUriButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(copyDatasetButton))
                                    .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(27, 27, 27)
                                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(modelUriLabel)
                                            .addComponent(modelUriValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(datasetValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(trainingDatasetLabel)))
                                    .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                        .addComponent(modelUriDetailsButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(datasetDetailsButton)))
                                .addGap(37, 37, 37)
                                .addComponent(jLabel14)
                                .addGap(19, 19, 19)
                                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(algorithmNameLabel)
                                            .addComponent(algorithmNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(algorithmUriLabel)
                                            .addComponent(algorithmUriValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)
                                        .addComponent(predictedFeatureHeadline))
                                    .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                        .addComponent(copyAlgorithmName)
                                        .addGap(18, 18, 18)
                                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(algorithmDetailsButton)
                                            .addComponent(copyAlgorithmUriButton)
                                            .addComponent(jButton5))))
                                .addGap(18, 18, 18)
                                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(predFeatureNameLabel)
                                            .addComponent(predictedFeatureNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(predFeatureResourceLabel)
                                            .addComponent(predFeatureUriValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                        .addComponent(copyPredFeatureNameButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(copyPredFeatureUriButton))
                                    .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(predFeatureDetailsButton)))
                                .addGap(37, 37, 37)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(modelInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18)))
                        .addGap(18, 18, 18)
                        .addGroup(modelInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout modelPanelLayout = new javax.swing.GroupLayout(modelPanel);
        modelPanel.setLayout(modelPanelLayout);
        modelPanelLayout.setHorizontalGroup(
            modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modelInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        modelPanelLayout.setVerticalGroup(
            modelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modelInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        basicTabbedPanel.addTab(resourceMap.getString("modelPanel.TabConstraints.tabTitle"), modelPanel); // NOI18N

        applicabilityDomainPanel.setName("applicabilityDomainPanel"); // NOI18N

        appDomainInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("appDomainInfoPanel.border.title"))); // NOI18N
        appDomainInfoPanel.setName("appDomainInfoPanel"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

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
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(appDomainInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(appDomainURIValue)
                    .addComponent(appDomainAlgorithmNameValue, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
                .addContainerGap(629, Short.MAX_VALUE))
        );
        appDomainInfoPanelLayout.setVerticalGroup(
            appDomainInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appDomainInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(appDomainInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(appDomainAlgorithmNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(appDomainInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(appDomainURIValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        addDomainsDiscussionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("addDomainsDiscussionPanel.border.title"))); // NOI18N
        addDomainsDiscussionPanel.setName("addDomainsDiscussionPanel"); // NOI18N

        appDomainsLabel.setText(resourceMap.getString("appDomainsLabel.text")); // NOI18N
        appDomainsLabel.setName("appDomainsLabel"); // NOI18N

        appDomainsScrollable.setName("appDomainsScrollable"); // NOI18N

        appDomainsTextArea.setColumns(20);
        appDomainsTextArea.setRows(5);
        appDomainsTextArea.setName("appDomainsTextArea"); // NOI18N
        appDomainsScrollable.setViewportView(appDomainsTextArea);

        addDomainResultLabel.setText(resourceMap.getString("addDomainResultLabel.text")); // NOI18N
        addDomainResultLabel.setName("addDomainResultLabel"); // NOI18N

        applicabilityDomainValue.setIcon(resourceMap.getIcon("applicabilityDomainValue.icon")); // NOI18N
        applicabilityDomainValue.setText(resourceMap.getString("applicabilityDomainValue.text")); // NOI18N
        applicabilityDomainValue.setToolTipText(resourceMap.getString("applicabilityDomainValue.toolTipText")); // NOI18N
        applicabilityDomainValue.setName("applicabilityDomainValue"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jTextArea8.setColumns(20);
        jTextArea8.setRows(5);
        jTextArea8.setName("jTextArea8"); // NOI18N
        jScrollPane5.setViewportView(jTextArea8);

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        jTextArea9.setColumns(20);
        jTextArea9.setRows(5);
        jTextArea9.setName("jTextArea9"); // NOI18N
        jScrollPane6.setViewportView(jTextArea9);

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jScrollPane10.setName("jScrollPane10"); // NOI18N

        jTextArea10.setColumns(20);
        jTextArea10.setRows(5);
        jTextArea10.setName("jTextArea10"); // NOI18N
        jScrollPane10.setViewportView(jTextArea10);

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
                        .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(addDomainsDiscussionPanelLayout.createSequentialGroup()
                                .addComponent(appDomainsLabel)
                                .addGap(160, 160, 160))
                            .addGroup(addDomainsDiscussionPanelLayout.createSequentialGroup()
                                .addComponent(appDomainsScrollable, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(addDomainsDiscussionPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(97, 97, 97))
                            .addGroup(addDomainsDiscussionPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                            .addComponent(jLabel15))))
                .addContainerGap())
        );
        addDomainsDiscussionPanelLayout.setVerticalGroup(
            addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addDomainsDiscussionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addDomainResultLabel)
                    .addComponent(applicabilityDomainValue))
                .addGap(18, 18, 18)
                .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appDomainsLabel)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addDomainsDiscussionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(appDomainsScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
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

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane11.setName("jScrollPane11"); // NOI18N

        compoundsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Compound Name", "SMILES", "CAS-RN"
            }
        ));
        compoundsTable.setAutoCreateRowSorter(true);
        compoundsTable.setCellSelectionEnabled(true);
        compoundsTable.setName("compoundsTable"); // NOI18N
        jScrollPane11.setViewportView(compoundsTable);
        compoundsTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("compoundsTable.columnModel.title0")); // NOI18N
        compoundsTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("compoundsTable.columnModel.title1")); // NOI18N
        compoundsTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("compoundsTable.columnModel.title2")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE)))
        );

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane4.setViewportView(jTextArea1);

        javax.swing.GroupLayout structuralAnaloguesPanelLayout = new javax.swing.GroupLayout(structuralAnaloguesPanel);
        structuralAnaloguesPanel.setLayout(structuralAnaloguesPanelLayout);
        structuralAnaloguesPanelLayout.setHorizontalGroup(
            structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(structuralAnaloguesToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1417, Short.MAX_VALUE)
            .addGroup(structuralAnaloguesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(structuralAnaloguesSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(117, 117, 117)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );
        structuralAnaloguesPanelLayout.setVerticalGroup(
            structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(structuralAnaloguesPanelLayout.createSequentialGroup()
                .addComponent(structuralAnaloguesToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(structuralAnaloguesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(structuralAnaloguesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(structuralAnaloguesSeparator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
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
                    .addComponent(addDomainsDiscussionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(appDomainInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        applicabilityDomainPanelLayout.setVerticalGroup(
            applicabilityDomainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applicabilityDomainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appDomainInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(structuralAnaloguesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addDomainsDiscussionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(282, Short.MAX_VALUE))
        );

        basicTabbedPanel.addTab(resourceMap.getString("applicabilityDomainPanel.TabConstraints.tabTitle"), applicabilityDomainPanel); // NOI18N

        predictionPanel.setName("predictionPanel"); // NOI18N

        predictionInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("predictionInfoPanel.border.title"))); // NOI18N
        predictionInfoPanel.setName("predictionInfoPanel"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField3.setForeground(resourceMap.getColor("jTextField3.foreground")); // NOI18N
        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jScrollPane15.setName("jScrollPane15"); // NOI18N

        jTextArea14.setColumns(20);
        jTextArea14.setRows(5);
        jTextArea14.setName("jTextArea14"); // NOI18N
        jScrollPane15.setViewportView(jTextArea14);

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        jScrollPane16.setName("jScrollPane16"); // NOI18N

        jTextArea15.setColumns(20);
        jTextArea15.setRows(5);
        jTextArea15.setName("jTextArea15"); // NOI18N
        jScrollPane16.setViewportView(jTextArea15);

        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jTextField4.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N

        jTextField5.setForeground(resourceMap.getColor("jTextField4.foreground")); // NOI18N
        jTextField5.setText(resourceMap.getString("jTextField5.text")); // NOI18N
        jTextField5.setName("jTextField5"); // NOI18N

        javax.swing.GroupLayout predictionInfoPanelLayout = new javax.swing.GroupLayout(predictionInfoPanel);
        predictionInfoPanel.setLayout(predictionInfoPanelLayout);
        predictionInfoPanelLayout.setHorizontalGroup(
            predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(predictionInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(predictionInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addContainerGap(1100, Short.MAX_VALUE))
                    .addGroup(predictionInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addContainerGap(1184, Short.MAX_VALUE))
                    .addGroup(predictionInfoPanelLayout.createSequentialGroup()
                        .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField4)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                        .addContainerGap(955, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, predictionInfoPanelLayout.createSequentialGroup()
                        .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                        .addGap(997, 997, 997))))
        );
        predictionInfoPanelLayout.setVerticalGroup(
            predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(predictionInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(predictionInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(588, Short.MAX_VALUE))
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
                .addComponent(predictionInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            .addComponent(authorsTableScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 1396, Short.MAX_VALUE)
            .addComponent(authorsToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 1396, Short.MAX_VALUE)
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
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(years));
        jComboBox1.setName("jComboBox1"); // NOI18N

        String[] months = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(months));
        jComboBox2.setName("jComboBox2"); // NOI18N
        jComboBox2.setSelectedItem(months[java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)]);

        java.util.ArrayList<String> listOfDays = new java.util.ArrayList<String>();
        for (int i=1;i<=31;i++){
            listOfDays.add(Integer.toString(i));
        }
        Object[] selectableDays = listOfDays.toArray();
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(selectableDays));
        jComboBox3.setSelectedIndex(java.util.Calendar.getInstance().get(java.util.Calendar.DATE)-1);
        jComboBox3.setName("jComboBox3"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getActionMap(ReportInternalFrame.class, this);
        jCheckBox1.setAction(actionMap.get("enableDisableDate")); // NOI18N
        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(resourceMap.getString("jCheckBox1.toolTipText")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        javax.swing.GroupLayout datePanelLayout = new javax.swing.GroupLayout(datePanel);
        datePanel.setLayout(datePanelLayout);
        datePanelLayout.setHorizontalGroup(
            datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addGap(77, 77, 77)
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(999, Short.MAX_VALUE))
        );
        datePanelLayout.setVerticalGroup(
            datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datePanelLayout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, datePanelLayout.createSequentialGroup()
                        .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addContainerGap(606, Short.MAX_VALUE))
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

        conclusionLabel.setLabelFor(jTextArea4);
        conclusionLabel.setText(resourceMap.getString("conclusionLabel.text")); // NOI18N
        conclusionLabel.setName("conclusionLabel"); // NOI18N

        regulatoryPurposeScrollable.setName("regulatoryPurposeScrollable"); // NOI18N

        regulatoryPurposeTextArea.setColumns(20);
        regulatoryPurposeTextArea.setRows(5);
        regulatoryPurposeTextArea.setName("regulatoryPurposeTextArea"); // NOI18N
        regulatoryPurposeScrollable.setViewportView(regulatoryPurposeTextArea);

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setName("jTextArea2"); // NOI18N
        jScrollPane7.setViewportView(jTextArea2);

        jScrollPane8.setName("jScrollPane8"); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setName("jTextArea3"); // NOI18N
        jScrollPane8.setViewportView(jTextArea3);

        jScrollPane9.setName("jScrollPane9"); // NOI18N

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setName("jTextArea4"); // NOI18N
        jScrollPane9.setViewportView(jTextArea4);

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
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, adequacyInfoPanelLayout.createSequentialGroup()
                                        .addComponent(outcomeLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(outcomeHintLabel))
                                    .addComponent(regulatoryPurposeScrollable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
                                .addGap(27, 27, 27)))
                        .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                            .addGroup(adequacyInfoPanelLayout.createSequentialGroup()
                                .addComponent(conclusionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(conclusionHintLabel))
                            .addGroup(adequacyInfoPanelLayout.createSequentialGroup()
                                .addComponent(regulatoryInterprLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regulatoryInterprHintLabel))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)))
                    .addGroup(adequacyInfoPanelLayout.createSequentialGroup()
                        .addComponent(adequacyInfoIndicatorLabel)
                        .addGap(18, 18, 18)
                        .addComponent(adequacyBottomMessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 1322, Short.MAX_VALUE)))
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
                    .addComponent(regulatoryPurposeScrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outcomeLabel)
                    .addComponent(conclusionLabel)
                    .addComponent(outcomeHintLabel)
                    .addComponent(conclusionHintLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adequacyInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane9)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
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
                .addComponent(adequacyInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea5.setColumns(20);
        jTextArea5.setEditable(false);
        jTextArea5.setRows(5);
        jTextArea5.setText(resourceMap.getString("jTextArea5.text")); // NOI18N
        jTextArea5.setName("jTextArea5"); // NOI18N
        jScrollPane1.setViewportView(jTextArea5);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jTextArea6.setName("jTextArea6"); // NOI18N
        jScrollPane2.setViewportView(jTextArea6);

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jTextArea7.setName("jTextArea7"); // NOI18N
        jScrollPane3.setViewportView(jTextArea7);

        javax.swing.GroupLayout metaInfoPanelLayout = new javax.swing.GroupLayout(metaInfoPanel);
        metaInfoPanel.setLayout(metaInfoPanelLayout);
        metaInfoPanelLayout.setHorizontalGroup(
            metaInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(metaInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(metaInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1356, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1356, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1356, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(metaInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(qprfDescriptionLabel)
                    .addComponent(qprfDescriptionHintLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(metaInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qprfCommentsLabel)
                    .addComponent(qprfCommentsHintLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(554, Short.MAX_VALUE))
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
                .addComponent(metaInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        qprfInfoTabbedSubPanel.addTab("Other", otherInfoPanel);

        javax.swing.GroupLayout qprfReportLayout = new javax.swing.GroupLayout(qprfReport);
        qprfReport.setLayout(qprfReportLayout);
        qprfReportLayout.setHorizontalGroup(
            qprfReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qprfInfoTabbedSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1469, Short.MAX_VALUE)
        );
        qprfReportLayout.setVerticalGroup(
            qprfReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qprfInfoTabbedSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
        );

        basicTabbedPanel.addTab(resourceMap.getString("qprfReport.TabConstraints.tabTitle"), qprfReport); // NOI18N

        jScrollPane17.setViewportView(basicTabbedPanel);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void removeSelectedRows(JTable table){
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
        int dialog_x = (frameWidth-dialogWidht)/2;
        int dialog_y = (frameHeight - dialogHeight)/2;
        authorsWizard.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        authorsWizard.setAuthorsTable(authorsTable);
        authorsWizard.setVisible(true);
    }//GEN-LAST:event_authorsWizardButtonActionPerformed

    private void clearAllRows(JTable table){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
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
        structuralAnalogueWizard = new StructuralAnalogueWizard_Step1(jframe);
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = structuralAnalogueWizard.getWidth();
        int dialogHeight = structuralAnalogueWizard.getHeight();
        int dialog_x = (frameWidth-dialogWidht)/2;
        int dialog_y = (frameHeight - dialogHeight)/2;
        structuralAnalogueWizard.setBounds(dialog_x, dialog_y, dialogWidht, dialogHeight);
        structuralAnalogueWizard.setVisible(true);
    }//GEN-LAST:event_compoundWizardButtonActionPerformed

    private void clearAllStructuralAnaloguesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllStructuralAnaloguesActionPerformed
        clearAllRows(compoundsTable);
    }//GEN-LAST:event_clearAllStructuralAnaloguesActionPerformed

    private void removeStructuralAnalogueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeStructuralAnalogueActionPerformed
        removeSelectedRows(compoundsTable);
    }//GEN-LAST:event_removeStructuralAnalogueActionPerformed

    private void descriptorMetaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptorMetaMenuItemActionPerformed
        JFrame jframe = QEditApp.getView().getFrame();
        descriptorMetaDialog = new DescriptorMetaDataDialog(jframe);
        int frameWidth = jframe.getWidth();
        int frameHeight = jframe.getHeight();
        int dialogWidht = descriptorMetaDialog.getWidth();
        int dialogHeight = descriptorMetaDialog.getHeight();
        int dialog_x = (frameWidth-dialogWidht)/2;
        int dialog_y = (frameHeight - dialogHeight)/2;        
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDescriptorValueButton;
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
    private javax.swing.JTextField appDomainAlgorithmNameValue;
    private javax.swing.JPanel appDomainInfoPanel;
    private javax.swing.JTextField appDomainURIValue;
    private javax.swing.JLabel appDomainsLabel;
    private javax.swing.JScrollPane appDomainsScrollable;
    private javax.swing.JTextArea appDomainsTextArea;
    private javax.swing.JButton appendRowButton;
    private javax.swing.JPanel applicabilityDomainPanel;
    private javax.swing.JLabel applicabilityDomainValue;
    private javax.swing.JPanel authorsPanel;
    private javax.swing.JTable authorsTable;
    private javax.swing.JPopupMenu authorsTablePopupMenu;
    private javax.swing.JScrollPane authorsTableScrollable;
    private javax.swing.JToolBar authorsToolbar;
    private javax.swing.JButton authorsWizardButton;
    private javax.swing.JTabbedPane basicTabbedPanel;
    private javax.swing.JLabel casRnLabel;
    private javax.swing.JLabel casRnValue;
    private javax.swing.JButton clearAllAuthorsButton;
    private javax.swing.JButton clearAllDescriptorsButton;
    private javax.swing.JButton clearAllStructuralAnalogues;
    private javax.swing.JButton cmlCompoundButton;
    private javax.swing.JLabel compoundImageLabel;
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
    private javax.swing.JTable compoundsTable;
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
    private javax.swing.JMenuItem descriptorMetaMenuItem;
    private javax.swing.JPanel descriptorsPanel;
    private javax.swing.JPopupMenu descriptorsPopupMenu;
    private javax.swing.JScrollPane descriptorsScrollable;
    private javax.swing.JTable descriptorsTable;
    private javax.swing.JToolBar descriptorsTableToolbar;
    private javax.swing.JPanel generalInfoPanel;
    private javax.swing.JButton inChIButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea10;
    private javax.swing.JTextArea jTextArea11;
    private javax.swing.JTextArea jTextArea12;
    private javax.swing.JTextArea jTextArea13;
    private javax.swing.JTextArea jTextArea14;
    private javax.swing.JTextArea jTextArea15;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextArea jTextArea9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JPanel metaInfoPanel;
    private javax.swing.JPanel modelInfoPanel;
    private javax.swing.JButton modelInfoToolButton;
    private javax.swing.JPanel modelPanel;
    private javax.swing.JToolBar modelToolbar;
    private javax.swing.JButton modelUriDetailsButton;
    private javax.swing.JLabel modelUriLabel;
    private javax.swing.JTextField modelUriValue;
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
    private javax.swing.JPanel predictionInfoPanel;
    private javax.swing.JPanel predictionPanel;
    private javax.swing.JLabel qprfCommentsHintLabel;
    private javax.swing.JLabel qprfCommentsLabel;
    private javax.swing.JLabel qprfDescriptionHintLabel;
    private javax.swing.JLabel qprfDescriptionLabel;
    private javax.swing.JTabbedPane qprfInfoTabbedSubPanel;
    private javax.swing.JPanel qprfReport;
    private javax.swing.JLabel qprfTitleHintLabel;
    private javax.swing.JButton rdfCompoundButton;
    private javax.swing.JLabel regulatoryInterprHintLabel;
    private javax.swing.JLabel regulatoryInterprLabel;
    private javax.swing.JLabel regulatoryPurposeHintLabel;
    private javax.swing.JLabel regulatoryPurposeLabel;
    private javax.swing.JScrollPane regulatoryPurposeScrollable;
    private javax.swing.JTextArea regulatoryPurposeTextArea;
    private javax.swing.JMenuItem removeAuthorMenuItem;
    private javax.swing.JMenuItem removeDescriptorLineMenuItem;
    private javax.swing.JButton removeDescriptorValueButton;
    private javax.swing.JButton removeSelectedRowButton;
    private javax.swing.JButton removeStructuralAnalogue;
    private javax.swing.JButton smilesButton;
    private javax.swing.JToolBar.Separator structAnalToolbarSeparator;
    private javax.swing.JPanel structuralAnaloguesPanel;
    private javax.swing.JSeparator structuralAnaloguesSeparator;
    private javax.swing.JToolBar structuralAnaloguesToolbar;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel trainingDatasetLabel;
    // End of variables declaration//GEN-END:variables
    private AuthorWizard authorsWizard;
    private DescriptorValueWizard desriptorWizard;
    private StructuralAnalogueWizard_Step1 structuralAnalogueWizard;
    private DescriptorMetaDataDialog descriptorMetaDialog;
}

