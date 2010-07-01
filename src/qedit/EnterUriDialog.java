/*
 * EnterUriDialog.java
 *
 * Created on Jun 5, 2010, 5:55:23 AM
 */
package qedit;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.beans.PropertyVetoException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jdesktop.application.Action;

/**
 *
 * @author chung
 */
public class EnterUriDialog extends javax.swing.JDialog {

    public EnterUriDialog(java.awt.Frame parent) {
        super(parent);
        initComponents();
    }

    /** Creates new form EnterUriDialog */
    public EnterUriDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @Action
    public void closeDialog() {
        dispose();
    }

    @Action
    public void enableDiableCredentials() {
        boolean requireCredentialsCheckBoxState = requireCredentials.isSelected();
        userNameVariable.setEnabled(requireCredentialsCheckBoxState);
        userPasswordValue.setEnabled(requireCredentialsCheckBoxState);
        provideCredentialsHint.setEnabled(requireCredentialsCheckBoxState);
    }

    @Action
    public void clearFieldsButtonAction() {
        userNameVariable.setText("");
        userPasswordValue.setText("");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonsPanel = new javax.swing.JPanel();
        cancellationButton = new javax.swing.JButton();
        clearFieldsButton = new javax.swing.JButton();
        loadFromRemoteButton = new javax.swing.JButton();
        enterUriHint = new javax.swing.JLabel();
        infoIcon = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        provideLabel = new javax.swing.JLabel();
        searchMethodSelection = new javax.swing.JComboBox();
        compoundResource = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        DefaultMutableTreeNode rootOfTree = null;
        DefaultMutableTreeNode model = null;
        DefaultMutableTreeNode modelInfo = null;
        rootOfTree = new DefaultMutableTreeNode("QSAR Models");

        model = new DefaultMutableTreeNode("Model 1");
        modelInfo = new DefaultMutableTreeNode("URI : http://someserver.com:8081/model/1");
        model.add(modelInfo);
        modelInfo = new DefaultMutableTreeNode("Name : model 1");
        model.add(modelInfo);
        rootOfTree.add(model);

        model = new DefaultMutableTreeNode("Model 2");
        modelInfo = new DefaultMutableTreeNode("URI : http://someserver.com:8081/model/2");
        model.add(modelInfo);
        modelInfo = new DefaultMutableTreeNode("Name : model 2");
        model.add(modelInfo);
        rootOfTree.add(model);
        jTree1 = new javax.swing.JTree(rootOfTree);
        jPanel3 = new javax.swing.JPanel();
        credentialsPanel = new javax.swing.JPanel();
        userPasswordValue = new javax.swing.JPasswordField();
        userNameVariable = new javax.swing.JTextField();
        provideCredentialsHint = new javax.swing.JLabel();
        passwordQuality = new javax.swing.JLabel();
        requireCredentials = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(EnterUriDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        buttonsPanel.setName("buttonsPanel"); // NOI18N

        cancellationButton.setText(resourceMap.getString("cancellationButton.text")); // NOI18N
        cancellationButton.setToolTipText(resourceMap.getString("cancellationButton.toolTipText")); // NOI18N
        cancellationButton.setName("cancellationButton"); // NOI18N
        cancellationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellationButtonActionPerformed(evt);
            }
        });

        clearFieldsButton.setText(resourceMap.getString("clearFieldsButton.text")); // NOI18N
        clearFieldsButton.setName("clearFieldsButton"); // NOI18N
        clearFieldsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFieldsButtonActionPerformed(evt);
            }
        });

        loadFromRemoteButton.setText(resourceMap.getString("loadFromRemoteButton.text")); // NOI18N
        loadFromRemoteButton.setToolTipText(resourceMap.getString("loadFromRemoteButton.toolTipText")); // NOI18N
        loadFromRemoteButton.setName("loadFromRemoteButton"); // NOI18N
        loadFromRemoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFromRemoteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
            .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(buttonsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(loadFromRemoteButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(clearFieldsButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(cancellationButton)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
            .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(buttonsPanelLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(loadFromRemoteButton)
                        .addComponent(clearFieldsButton)
                        .addComponent(cancellationButton))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        enterUriHint.setText(resourceMap.getString("enterUriHint.text")); // NOI18N
        enterUriHint.setAutoscrolls(true);
        enterUriHint.setName("enterUriHint"); // NOI18N

        infoIcon.setIcon(resourceMap.getIcon("infoIcon.icon")); // NOI18N
        infoIcon.setText(resourceMap.getString("infoIcon.text")); // NOI18N
        infoIcon.setName("infoIcon"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        provideLabel.setText(resourceMap.getString("provideLabel.text")); // NOI18N
        provideLabel.setName("provideLabel"); // NOI18N

        searchMethodSelection.setModel(new javax.swing.DefaultComboBoxModel(searchBy().keySet().toArray()));
        searchMethodSelection.setName("searchMethodSelection"); // NOI18N
        searchMethodSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMethodSelectionActionPerformed(evt);
            }
        });

        compoundResource.setText(resourceMap.getString("compoundResource.text")); // NOI18N
        compoundResource.setName("compoundResource"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(provideLabel)
                    .addComponent(jLabel1))
                .addGap(78, 78, 78)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchMethodSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compoundResource, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchMethodSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(provideLabel)
                    .addComponent(compoundResource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel5.border.title"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jRadioButton1.setSelected(true);
        jRadioButton1.setText(resourceMap.getString("jRadioButton1.text")); // NOI18N
        jRadioButton1.setName("jRadioButton1"); // NOI18N
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });

        jRadioButton2.setText(resourceMap.getString("jRadioButton2.text")); // NOI18N
        jRadioButton2.setName("jRadioButton2"); // NOI18N
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setEnabled(false);
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField2.setForeground(resourceMap.getColor("jTextField2.foreground")); // NOI18N
        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setEnabled(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTree1.setName("jTree1"); // NOI18N
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(461, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        credentialsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("credentialsPanel.border.title"))); // NOI18N
        credentialsPanel.setName("credentialsPanel"); // NOI18N

        userPasswordValue.setForeground(resourceMap.getColor("userPasswordValue.foreground")); // NOI18N
        userPasswordValue.setText(resourceMap.getString("userPasswordValue.text")); // NOI18N
        userPasswordValue.setToolTipText(resourceMap.getString("userPasswordValue.toolTipText")); // NOI18N
        userPasswordValue.setName("userPasswordValue"); // NOI18N
        userPasswordValue.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                userPasswordValueCaretUpdate(evt);
            }
        });
        //// Initially disabled text field:
        userPasswordValue.setEnabled(false);

        userNameVariable.setText(resourceMap.getString("userNameVariable.text")); // NOI18N
        userNameVariable.setToolTipText(resourceMap.getString("userNameVariable.toolTipText")); // NOI18N
        userNameVariable.setName("userNameVariable"); // NOI18N
        userNameVariable.setEnabled(false);

        provideCredentialsHint.setText(resourceMap.getString("provideCredentialsHint.text")); // NOI18N
        provideCredentialsHint.setToolTipText(resourceMap.getString("provideCredentialsHint.toolTipText")); // NOI18N
        provideCredentialsHint.setName("provideCredentialsHint"); // NOI18N
        provideCredentialsHint.setEnabled(false);

        passwordQuality.setText(resourceMap.getString("passwordQuality.text")); // NOI18N
        passwordQuality.setToolTipText(resourceMap.getString("passwordQuality.toolTipText")); // NOI18N
        passwordQuality.setName("passwordQuality"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getActionMap(EnterUriDialog.class, this);
        requireCredentials.setAction(actionMap.get("enableDiableCredentials")); // NOI18N
        requireCredentials.setText(resourceMap.getString("requireCredentials.text")); // NOI18N
        requireCredentials.setToolTipText(resourceMap.getString("requireCredentials.toolTipText")); // NOI18N
        requireCredentials.setName("requireCredentials"); // NOI18N

        javax.swing.GroupLayout credentialsPanelLayout = new javax.swing.GroupLayout(credentialsPanel);
        credentialsPanel.setLayout(credentialsPanelLayout);
        credentialsPanelLayout.setHorizontalGroup(
            credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(credentialsPanelLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(requireCredentials)
                .addGap(52, 52, 52)
                .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(provideCredentialsHint, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(credentialsPanelLayout.createSequentialGroup()
                        .addComponent(userNameVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userPasswordValue, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(passwordQuality, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        credentialsPanelLayout.setVerticalGroup(
            credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(credentialsPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, credentialsPanelLayout.createSequentialGroup()
                        .addComponent(requireCredentials)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, credentialsPanelLayout.createSequentialGroup()
                        .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordQuality, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(userNameVariable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(userPasswordValue)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(provideCredentialsHint, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(198, 198, 198))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(credentialsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(credentialsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoIcon)
                .addGap(18, 18, 18)
                .addComponent(enterUriHint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(361, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(664, Short.MAX_VALUE)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoIcon)
                    .addComponent(enterUriHint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearFieldsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFieldsButtonActionPerformed
        clearFieldsButtonAction();
}//GEN-LAST:event_clearFieldsButtonActionPerformed

    private void cancellationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellationButtonActionPerformed
        closeDialog();
}//GEN-LAST:event_cancellationButtonActionPerformed

    private void userPasswordValueCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_userPasswordValueCaretUpdate
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(EnterUriDialog.class);
        int passLength = userPasswordValue.getPassword().length;
        if (passLength == 0) {
            passwordQuality.setText("");
        } else if (passLength >= 1 && passLength <= 4) {
            userPasswordValue.setForeground(Color.CYAN);
            passwordQuality.setText(resourceMap.getString("passwordLabel.low"));
        } else if (passLength >= 5 && passLength <= 8) {
            userPasswordValue.setForeground(Color.GREEN);
            passwordQuality.setText(resourceMap.getString("passwordLabel.medium"));
        } else if (passLength >= 9 & passLength <= 12) {
            userPasswordValue.setForeground(Color.ORANGE);
            passwordQuality.setText(resourceMap.getString("passwordLabel.good"));
        } else if (passLength >= 13) {
            userPasswordValue.setForeground(Color.RED);
            passwordQuality.setText(resourceMap.getString("passwordLabel.great"));
        }
    }//GEN-LAST:event_userPasswordValueCaretUpdate

    private void loadFromRemoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFromRemoteButtonActionPerformed

        ReportInternalFrame nd = new ReportInternalFrame();
        nd.setVisible(true);
        QEditApp.getView().getDesktopPane().add(nd);
        nd.revalidate();
        nd.repaint();
        nd.setLocation(new Point(40 + 10 * QEditView.getNumOpenDocuments(), 40 + 10 * QEditView.getNumOpenDocuments()));
        nd.setTitle("Document " + (QEditView.getNumOpenDocuments() + 1));
        nd.setName(nd.getTitle());
        QEditView.increaseNumOpenDocuments();
        try {
            nd.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(EnterUriDialog.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeDialog();
        }

    }//GEN-LAST:event_loadFromRemoteButtonActionPerformed

    private void searchMethodSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMethodSelectionActionPerformed
        String selected = searchMethodSelection.getSelectedItem().toString();
        provideLabel.setText(searchBy().get(selected));
        if (selected.equals("By URI")) {
            compoundResource.setText("http://");
            compoundResource.setForeground(Color.blue);
        } else {
            compoundResource.setText(null);
            compoundResource.setForeground(Color.black);
        }
    }//GEN-LAST:event_searchMethodSelectionActionPerformed

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        boolean selected = false;
        if (evt.getStateChange()==ItemEvent.SELECTED){
            selected = true;
        }
        jLabel3.setEnabled(selected);
        jTextField2.setEnabled(selected);
        jRadioButton1.setSelected(!selected);
        jTree1.setEnabled(!selected);
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        boolean selected = false;
        if (evt.getStateChange()==ItemEvent.SELECTED){
            selected = true;
        }
        jLabel3.setEnabled(!selected);
        jTextField2.setEnabled(!selected);
        jRadioButton2.setSelected(!selected);
        jTree1.setEnabled(selected);
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private static Map<String, String> searchBy() {
        if (searchMap == null) {
            searchMap = new LinkedHashMap<String, String>();
            searchMap.put("By Name", "Provide Any Name :");
            searchMap.put("By InChI", "Provide InChI :");
            searchMap.put("By CAS-RN", "Provide the CAS-RN :");
            searchMap.put("By URI", "Provide the URI :");
            searchMap.put("By SMILES", "Provide the SMILES code :");
        }
        return searchMap;
    }
    private static Map<String, String> searchMap;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancellationButton;
    private javax.swing.JButton clearFieldsButton;
    private javax.swing.JTextField compoundResource;
    private javax.swing.JPanel credentialsPanel;
    private javax.swing.JLabel enterUriHint;
    private javax.swing.JLabel infoIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTree jTree1;
    private javax.swing.JButton loadFromRemoteButton;
    private javax.swing.JLabel passwordQuality;
    private javax.swing.JLabel provideCredentialsHint;
    private javax.swing.JLabel provideLabel;
    private javax.swing.JCheckBox requireCredentials;
    private javax.swing.JComboBox searchMethodSelection;
    private javax.swing.JTextField userNameVariable;
    private javax.swing.JPasswordField userPasswordValue;
    // End of variables declaration//GEN-END:variables
}
