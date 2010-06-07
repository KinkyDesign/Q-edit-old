/*
 * QEditView.java
 */
package qedit;

import java.awt.Point;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The application's main frame.
 */
public class QEditView extends FrameView {

    public QEditView(SingleFrameApplication app) {
        super(app);

        initComponents();
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

    }

    @Action
    public void showAboutBox() {

        if (aboutBox == null) {
            JFrame mainFrame = QEditApp.getApplication().getMainFrame();
            aboutBox = new QEditAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        QEditApp.getApplication().show(aboutBox);
    }

    @Action
    public void openFileAction() {
        localFileChooserWindow = new JFileChooser();
        localFileChooserWindow.setAcceptAllFileFilterUsed(true);
        localFileChooserWindow.showOpenDialog(mainPanel);
    }

    @Action
    public void enterUriDialogBox() {
        if (enterUriBox == null) {
            JFrame mainFrame = QEditApp.getApplication().getMainFrame();
            enterUriBox = new EnterUriDialog(mainFrame);
            enterUriBox.setLocationRelativeTo(mainFrame);
        }

        statusMessageLabel.setText("Opening Remote Location");
        QEditApp.getApplication().show(enterUriBox);
    }

    @Action
    public void statisticsDialogBox() {
        if (statisticsBox == null) {
            JFrame mainFrame = QEditApp.getApplication().getMainFrame();
            statisticsBox = new StatisticsDialog(mainFrame);
            statisticsBox.setLocationRelativeTo(mainFrame);
        }
        QEditApp.getApplication().show(statisticsBox);
    }

    @Action
    public void saveDialogBox() {
        saveFileChooserWindow = new JFileChooser();
        saveFileChooserWindow.setFileFilter(new FileNameExtensionFilter("rdf files", "rdf"));
        saveFileChooserWindow.showSaveDialog(mainPanel);
    }

    @Action
    public void createNewReport() {
        ReportInternalFrame nd = new ReportInternalFrame();
        nd.setVisible(true);
        desktopPane.add(nd);
        nd.revalidate();
        nd.repaint();
        nd.setLocation(new Point(40 + 10 * numOpenInternalFrames, 40 + 10 * numOpenInternalFrames));
        nd.setTitle("Document "+(numOpenInternalFrames+1));
        nd.setName(nd.getTitle());
        numOpenInternalFrames++;
        try {
            nd.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(QEditView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public javax.swing.JLabel getStatusLabel() {
        return statusMessageLabel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        mainSplitPane = new javax.swing.JSplitPane();
        leftSplittedPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        rightSplittedPanel = new javax.swing.JPanel();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        newReport = new javax.swing.JMenuItem();
        openFileMenuItem = new javax.swing.JMenuItem();
        openUrlMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        firstFileMenuSeparatorItem = new javax.swing.JPopupMenu.Separator();
        qprfOptionsMenuItem = new javax.swing.JMenuItem();
        secondFileMenuSeparatorItem = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        reportMenu = new javax.swing.JMenu();
        pdfReportMenuItem = new javax.swing.JMenuItem();
        wordReportMenuItem = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        toolsOptionsMenuItem = new javax.swing.JMenuItem();
        statisticsMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        helpItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        localFileChooserWindow = new javax.swing.JFileChooser();
        saveFileChooserWindow = new javax.swing.JFileChooser();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        basicToolbar = new javax.swing.JToolBar();
        newReportButton = new javax.swing.JButton();
        downloadButton = new javax.swing.JButton();
        openLocalResourceButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        exportDocButton = new javax.swing.JButton();
        exportPdfButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        aboutToolButton = new javax.swing.JButton();
        ejectButton = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(QEditView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(704, 410));

        mainSplitPane.setBackground(resourceMap.getColor("mainSplitPane.background")); // NOI18N
        mainSplitPane.setBorder(new javax.swing.border.MatteBorder(null));
        mainSplitPane.setResizeWeight(0.1);
        mainSplitPane.setInheritsPopupMenu(true);
        mainSplitPane.setName("mainSplitPane"); // NOI18N
        mainSplitPane.setOpaque(true);

        leftSplittedPanel.setName("leftSplittedPanel"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Recent Sessions");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Dataset");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("http://someserver.com/ds/1");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("http://someserver.com/ds/2");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Models");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("http://someserver.com/model/a");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("http://someserver.com/model/b");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setName("jTree1"); // NOI18N
        jScrollPane2.setViewportView(jTree1);

        javax.swing.GroupLayout leftSplittedPanelLayout = new javax.swing.GroupLayout(leftSplittedPanel);
        leftSplittedPanel.setLayout(leftSplittedPanelLayout);
        leftSplittedPanelLayout.setHorizontalGroup(
            leftSplittedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );
        leftSplittedPanelLayout.setVerticalGroup(
            leftSplittedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
        );

        mainSplitPane.setLeftComponent(leftSplittedPanel);

        rightSplittedPanel.setName("rightSplittedPanel"); // NOI18N

        desktopPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        desktopPane.setName("desktopPane"); // NOI18N

        javax.swing.GroupLayout rightSplittedPanelLayout = new javax.swing.GroupLayout(rightSplittedPanel);
        rightSplittedPanel.setLayout(rightSplittedPanelLayout);
        rightSplittedPanelLayout.setHorizontalGroup(
            rightSplittedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
        );
        rightSplittedPanelLayout.setVerticalGroup(
            rightSplittedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
        );

        mainSplitPane.setRightComponent(rightSplittedPanel);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setMnemonic('F');
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        newReport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newReport.setIcon(resourceMap.getIcon("newReport.icon")); // NOI18N
        newReport.setText(resourceMap.getString("newReport.text")); // NOI18N
        newReport.setToolTipText(resourceMap.getString("newReport.toolTipText")); // NOI18N
        newReport.setName("newReport"); // NOI18N
        newReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newReportActionPerformed(evt);
            }
        });
        fileMenu.add(newReport);

        openFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openFileMenuItem.setIcon(resourceMap.getIcon("openFileMenuItem.icon")); // NOI18N
        openFileMenuItem.setMnemonic('O');
        openFileMenuItem.setText(resourceMap.getString("openFileMenuItem.text")); // NOI18N
        openFileMenuItem.setToolTipText(resourceMap.getString("openFileMenuItem.toolTipText")); // NOI18N
        openFileMenuItem.setName("openFileMenuItem"); // NOI18N
        openFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openFileMenuItem);

        openUrlMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        openUrlMenuItem.setIcon(resourceMap.getIcon("openUrlMenuItem.icon")); // NOI18N
        openUrlMenuItem.setText(resourceMap.getString("openUrlMenuItem.text")); // NOI18N
        openUrlMenuItem.setToolTipText(resourceMap.getString("openUrlMenuItem.toolTipText")); // NOI18N
        openUrlMenuItem.setName("openUrlMenuItem"); // NOI18N
        openUrlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openUrlMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openUrlMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setIcon(resourceMap.getIcon("saveMenuItem.icon")); // NOI18N
        saveMenuItem.setMnemonic('S');
        saveMenuItem.setText(resourceMap.getString("saveMenuItem.text")); // NOI18N
        saveMenuItem.setToolTipText(resourceMap.getString("saveMenuItem.toolTipText")); // NOI18N
        saveMenuItem.setName("saveMenuItem"); // NOI18N
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        firstFileMenuSeparatorItem.setName("firstFileMenuSeparatorItem"); // NOI18N
        fileMenu.add(firstFileMenuSeparatorItem);

        qprfOptionsMenuItem.setMnemonic('E');
        qprfOptionsMenuItem.setText(resourceMap.getString("qprfOptionsMenuItem.text")); // NOI18N
        qprfOptionsMenuItem.setToolTipText(resourceMap.getString("qprfOptionsMenuItem.toolTipText")); // NOI18N
        qprfOptionsMenuItem.setName("qprfOptionsMenuItem"); // NOI18N
        fileMenu.add(qprfOptionsMenuItem);

        secondFileMenuSeparatorItem.setName("secondFileMenuSeparatorItem"); // NOI18N
        fileMenu.add(secondFileMenuSeparatorItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getActionMap(QEditView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        reportMenu.setMnemonic('R');
        reportMenu.setText(resourceMap.getString("reportMenu.text")); // NOI18N
        reportMenu.setName("reportMenu"); // NOI18N

        pdfReportMenuItem.setIcon(resourceMap.getIcon("pdfReportMenuItem.icon")); // NOI18N
        pdfReportMenuItem.setMnemonic('P');
        pdfReportMenuItem.setText(resourceMap.getString("pdfReportMenuItem.text")); // NOI18N
        pdfReportMenuItem.setToolTipText(resourceMap.getString("pdfReportMenuItem.toolTipText")); // NOI18N
        pdfReportMenuItem.setName("pdfReportMenuItem"); // NOI18N
        reportMenu.add(pdfReportMenuItem);

        wordReportMenuItem.setIcon(resourceMap.getIcon("wordReportMenuItem.icon")); // NOI18N
        wordReportMenuItem.setMnemonic('D');
        wordReportMenuItem.setText(resourceMap.getString("wordReportMenuItem.text")); // NOI18N
        wordReportMenuItem.setName("wordReportMenuItem"); // NOI18N
        reportMenu.add(wordReportMenuItem);

        menuBar.add(reportMenu);

        toolsMenu.setMnemonic('T');
        toolsMenu.setText(resourceMap.getString("toolsMenu.text")); // NOI18N
        toolsMenu.setName("toolsMenu"); // NOI18N

        toolsOptionsMenuItem.setIcon(resourceMap.getIcon("toolsOptionsMenuItem.icon")); // NOI18N
        toolsOptionsMenuItem.setMnemonic('O');
        toolsOptionsMenuItem.setText(resourceMap.getString("toolsOptionsMenuItem.text")); // NOI18N
        toolsOptionsMenuItem.setName("toolsOptionsMenuItem"); // NOI18N
        toolsMenu.add(toolsOptionsMenuItem);

        statisticsMenuItem.setAction(actionMap.get("statisticsDialogBox")); // NOI18N
        statisticsMenuItem.setIcon(resourceMap.getIcon("statisticsMenuItem.icon")); // NOI18N
        statisticsMenuItem.setText(resourceMap.getString("statisticsMenuItem.text")); // NOI18N
        statisticsMenuItem.setName("statisticsMenuItem"); // NOI18N
        toolsMenu.add(statisticsMenuItem);

        menuBar.add(toolsMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        helpItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        helpItem.setIcon(resourceMap.getIcon("helpItem.icon")); // NOI18N
        helpItem.setMnemonic('H');
        helpItem.setText(resourceMap.getString("helpItem.text")); // NOI18N
        helpItem.setToolTipText(resourceMap.getString("helpItem.toolTipText")); // NOI18N
        helpItem.setName("helpItem"); // NOI18N
        helpItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpItem);

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        localFileChooserWindow.setDialogTitle(resourceMap.getString("localFileChooserWindow.dialogTitle")); // NOI18N
        localFileChooserWindow.setName("localFileChooserWindow"); // NOI18N

        saveFileChooserWindow.setName("saveFileChooserWindow"); // NOI18N

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setText(resourceMap.getString("statusMessageLabel.text")); // NOI18N
        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 568, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        basicToolbar.setRollover(true);
        basicToolbar.setName("basicToolbar"); // NOI18N

        newReportButton.setAction(actionMap.get("createNewReport")); // NOI18N
        newReportButton.setIcon(resourceMap.getIcon("newReportButton.icon")); // NOI18N
        newReportButton.setText(resourceMap.getString("newReportButton.text")); // NOI18N
        newReportButton.setFocusable(false);
        newReportButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newReportButton.setName("newReportButton"); // NOI18N
        newReportButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        basicToolbar.add(newReportButton);

        downloadButton.setAction(actionMap.get("enterUriDialogBox")); // NOI18N
        downloadButton.setIcon(resourceMap.getIcon("downloadButton.icon")); // NOI18N
        downloadButton.setText(resourceMap.getString("downloadButton.text")); // NOI18N
        downloadButton.setToolTipText(resourceMap.getString("downloadButton.toolTipText")); // NOI18N
        downloadButton.setFocusable(false);
        downloadButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        downloadButton.setName("downloadButton"); // NOI18N
        downloadButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        basicToolbar.add(downloadButton);

        openLocalResourceButton.setAction(actionMap.get("openFileAction")); // NOI18N
        openLocalResourceButton.setIcon(resourceMap.getIcon("openLocalResourceButton.icon")); // NOI18N
        openLocalResourceButton.setText(resourceMap.getString("openLocalResourceButton.text")); // NOI18N
        openLocalResourceButton.setFocusable(false);
        openLocalResourceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openLocalResourceButton.setName("openLocalResourceButton"); // NOI18N
        openLocalResourceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        basicToolbar.add(openLocalResourceButton);

        saveButton.setAction(actionMap.get("saveDialogBox")); // NOI18N
        saveButton.setIcon(resourceMap.getIcon("saveButton.icon")); // NOI18N
        saveButton.setText(resourceMap.getString("saveButton.text")); // NOI18N
        saveButton.setToolTipText(resourceMap.getString("saveButton.toolTipText")); // NOI18N
        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setName("saveButton"); // NOI18N
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        basicToolbar.add(saveButton);

        jSeparator2.setName("jSeparator2"); // NOI18N
        basicToolbar.add(jSeparator2);

        exportDocButton.setIcon(resourceMap.getIcon("exportDocButton.icon")); // NOI18N
        exportDocButton.setText(resourceMap.getString("exportDocButton.text")); // NOI18N
        exportDocButton.setToolTipText(resourceMap.getString("exportDocButton.toolTipText")); // NOI18N
        exportDocButton.setFocusable(false);
        exportDocButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportDocButton.setName("exportDocButton"); // NOI18N
        exportDocButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        basicToolbar.add(exportDocButton);

        exportPdfButton.setIcon(resourceMap.getIcon("exportPdfButton.icon")); // NOI18N
        exportPdfButton.setText(resourceMap.getString("exportPdfButton.text")); // NOI18N
        exportPdfButton.setFocusable(false);
        exportPdfButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportPdfButton.setName("exportPdfButton"); // NOI18N
        exportPdfButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        exportPdfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPdfButtonActionPerformed(evt);
            }
        });
        basicToolbar.add(exportPdfButton);

        jSeparator1.setName("jSeparator1"); // NOI18N
        basicToolbar.add(jSeparator1);

        aboutToolButton.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutToolButton.setIcon(resourceMap.getIcon("aboutToolButton.icon")); // NOI18N
        aboutToolButton.setText(resourceMap.getString("aboutToolButton.text")); // NOI18N
        aboutToolButton.setFocusable(false);
        aboutToolButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aboutToolButton.setName("aboutToolButton"); // NOI18N
        aboutToolButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        basicToolbar.add(aboutToolButton);

        ejectButton.setAction(actionMap.get("quit")); // NOI18N
        ejectButton.setIcon(resourceMap.getIcon("ejectButton.icon")); // NOI18N
        ejectButton.setText(resourceMap.getString("ejectButton.text")); // NOI18N
        ejectButton.setFocusable(false);
        ejectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ejectButton.setName("ejectButton"); // NOI18N
        ejectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        basicToolbar.add(ejectButton);

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
        setToolBar(basicToolbar);
    }// </editor-fold>//GEN-END:initComponents

    private void openFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileMenuItemActionPerformed
        openFileAction();
    }//GEN-LAST:event_openFileMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        saveDialogBox();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void openUrlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openUrlMenuItemActionPerformed
        enterUriDialogBox();
    }//GEN-LAST:event_openUrlMenuItemActionPerformed

    private void helpItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpItemActionPerformed

    private void exportPdfButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportPdfButtonActionPerformed
    }//GEN-LAST:event_exportPdfButtonActionPerformed

    private void newReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newReportActionPerformed
        createNewReport();
    }//GEN-LAST:event_newReportActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutToolButton;
    private javax.swing.JToolBar basicToolbar;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton downloadButton;
    private javax.swing.JButton ejectButton;
    private javax.swing.JButton exportDocButton;
    private javax.swing.JButton exportPdfButton;
    private javax.swing.JPopupMenu.Separator firstFileMenuSeparatorItem;
    private javax.swing.JMenuItem helpItem;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JTree jTree1;
    private javax.swing.JPanel leftSplittedPanel;
    private javax.swing.JFileChooser localFileChooserWindow;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newReport;
    private javax.swing.JButton newReportButton;
    private javax.swing.JMenuItem openFileMenuItem;
    private javax.swing.JButton openLocalResourceButton;
    private javax.swing.JMenuItem openUrlMenuItem;
    private javax.swing.JMenuItem pdfReportMenuItem;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JMenuItem qprfOptionsMenuItem;
    private javax.swing.JMenu reportMenu;
    private javax.swing.JPanel rightSplittedPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JFileChooser saveFileChooserWindow;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JPopupMenu.Separator secondFileMenuSeparatorItem;
    private javax.swing.JMenuItem statisticsMenuItem;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JMenuItem toolsOptionsMenuItem;
    private javax.swing.JMenuItem wordReportMenuItem;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
    private JDialog enterUriBox;
    private JDialog statisticsBox;
    private static int numOpenInternalFrames = 0;
}
