/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EnterUriDialog.java
 *
 * Created on Jun 5, 2010, 5:55:23 AM
 */
package qedit;

import java.awt.Color;
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
        boolean requireCredentialsCheckBoxState = requireCredentials.getState();
        userNameVariable.setEnabled(requireCredentialsCheckBoxState);
        userPasswordValue.setEnabled(requireCredentialsCheckBoxState);
        provideCredentialsHint.setEnabled(requireCredentialsCheckBoxState);
    }

    @Action
    public void clearFieldsButtonAction(){
        datasetUriValue.setText("");
        modelUriValue.setText("");
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

        remoteLocationsPanel = new javax.swing.JPanel();
        datasetUriLabel = new javax.swing.JLabel();
        datasetUriValue = new javax.swing.JTextField();
        modelUriLabel = new javax.swing.JLabel();
        datasetUriExplanation = new javax.swing.JLabel();
        modelUriExplanation = new javax.swing.JLabel();
        modelUriValue = new javax.swing.JTextField();
        credentialsPanel = new javax.swing.JPanel();
        requireCredentials = new java.awt.Checkbox();
        userPasswordValue = new javax.swing.JPasswordField();
        userNameVariable = new javax.swing.JTextField();
        provideCredentialsHint = new javax.swing.JLabel();
        passwordQuality = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        cancellationButton = new javax.swing.JButton();
        clearFieldsButton = new javax.swing.JButton();
        loadFromRemoteButton = new javax.swing.JButton();
        enterUriHint = new javax.swing.JLabel();
        infoIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(EnterUriDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        remoteLocationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("remoteLocationsPanel.border.title"))); // NOI18N
        remoteLocationsPanel.setName("remoteLocationsPanel"); // NOI18N

        datasetUriLabel.setFont(resourceMap.getFont("datasetUriLabel.font")); // NOI18N
        datasetUriLabel.setText(resourceMap.getString("datasetUriLabel.text")); // NOI18N
        datasetUriLabel.setToolTipText(resourceMap.getString("datasetUriLabel.toolTipText")); // NOI18N
        datasetUriLabel.setName("datasetUriLabel"); // NOI18N

        datasetUriValue.setText(resourceMap.getString("datasetUriValue.text")); // NOI18N
        datasetUriValue.setToolTipText(resourceMap.getString("datasetUriValue.toolTipText")); // NOI18N
        datasetUriValue.setName("datasetUriValue"); // NOI18N
        datasetUriValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datasetUriValueActionPerformed(evt);
            }
        });

        modelUriLabel.setFont(resourceMap.getFont("modelUriLabel.font")); // NOI18N
        modelUriLabel.setText(resourceMap.getString("modelUriLabel.text")); // NOI18N
        modelUriLabel.setToolTipText(resourceMap.getString("modelUriLabel.toolTipText")); // NOI18N
        modelUriLabel.setName("modelUriLabel"); // NOI18N

        datasetUriExplanation.setText(resourceMap.getString("datasetUriExplanation.text")); // NOI18N
        datasetUriExplanation.setName("datasetUriExplanation"); // NOI18N

        modelUriExplanation.setText(resourceMap.getString("modelUriExplanation.text")); // NOI18N
        modelUriExplanation.setName("modelUriExplanation"); // NOI18N

        modelUriValue.setText(resourceMap.getString("modelUriValue.text")); // NOI18N
        modelUriValue.setToolTipText(resourceMap.getString("modelUriValue.toolTipText")); // NOI18N
        modelUriValue.setName("modelUriValue"); // NOI18N

        javax.swing.GroupLayout remoteLocationsPanelLayout = new javax.swing.GroupLayout(remoteLocationsPanel);
        remoteLocationsPanel.setLayout(remoteLocationsPanelLayout);
        remoteLocationsPanelLayout.setHorizontalGroup(
            remoteLocationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remoteLocationsPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(remoteLocationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(modelUriLabel)
                    .addComponent(datasetUriLabel))
                .addGap(38, 38, 38)
                .addGroup(remoteLocationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datasetUriExplanation)
                    .addComponent(datasetUriValue, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addComponent(modelUriValue, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addComponent(modelUriExplanation, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
                .addGap(90, 90, 90))
        );
        remoteLocationsPanelLayout.setVerticalGroup(
            remoteLocationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remoteLocationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(remoteLocationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datasetUriValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datasetUriLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datasetUriExplanation)
                .addGap(29, 29, 29)
                .addGroup(remoteLocationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelUriLabel)
                    .addComponent(modelUriValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modelUriExplanation)
                .addGap(137, 137, 137))
        );

        credentialsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("credentialsPanel.border.title"))); // NOI18N
        credentialsPanel.setName("credentialsPanel"); // NOI18N

        requireCredentials.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        requireCredentials.setLabel(resourceMap.getString("requireCredentials.label")); // NOI18N
        requireCredentials.setName("requireCredentials"); // NOI18N
        requireCredentials.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                requireCredentialsItemStateChanged(evt);
            }
        });

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
        provideCredentialsHint.setName("provideCredentialsHint"); // NOI18N
        provideCredentialsHint.setEnabled(false);

        passwordQuality.setText(resourceMap.getString("passwordQuality.text")); // NOI18N
        passwordQuality.setToolTipText(resourceMap.getString("passwordQuality.toolTipText")); // NOI18N
        passwordQuality.setName("passwordQuality"); // NOI18N

        javax.swing.GroupLayout credentialsPanelLayout = new javax.swing.GroupLayout(credentialsPanel);
        credentialsPanel.setLayout(credentialsPanelLayout);
        credentialsPanelLayout.setHorizontalGroup(
            credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(credentialsPanelLayout.createSequentialGroup()
                    .addGap(97, 97, 97)
                    .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(provideCredentialsHint, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(credentialsPanelLayout.createSequentialGroup()
                            .addComponent(userNameVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(userPasswordValue, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(passwordQuality))
                        .addComponent(requireCredentials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(416, 416, 416)))
        );
        credentialsPanelLayout.setVerticalGroup(
            credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 126, Short.MAX_VALUE)
            .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(credentialsPanelLayout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(requireCredentials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(credentialsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(userNameVariable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userPasswordValue)
                        .addComponent(passwordQuality))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(provideCredentialsHint, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(41, 41, 41)))
        );

        buttonsPanel.setName("buttonsPanel"); // NOI18N

        cancellationButton.setMnemonic('C');
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
            .addGap(0, 313, Short.MAX_VALUE)
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
            .addGap(0, 76, Short.MAX_VALUE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(credentialsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(remoteLocationsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(infoIcon)
                        .addGap(18, 18, 18)
                        .addComponent(enterUriHint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(remoteLocationsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(credentialsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterUriHint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoIcon))
                .addGap(31, 31, 31)
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
        int passLength = userPasswordValue.getPassword().length;
        if (passLength == 0) {
            passwordQuality.setText("");
        } else if (passLength >= 1 && passLength <= 4) {
            userPasswordValue.setForeground(Color.CYAN);
            passwordQuality.setText("LOW");
        } else if (passLength >= 5 && passLength <= 8) {
            userPasswordValue.setForeground(Color.GREEN);
            passwordQuality.setText("MEDIUM");
        } else if (passLength >= 9 & passLength <= 12) {
            userPasswordValue.setForeground(Color.ORANGE);
            passwordQuality.setText("GOOD");
        } else if (passLength >= 13) {
            userPasswordValue.setForeground(Color.RED);
            passwordQuality.setText("EXCELENT");
        }
    }//GEN-LAST:event_userPasswordValueCaretUpdate

    private void requireCredentialsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_requireCredentialsItemStateChanged
        enableDiableCredentials();
}//GEN-LAST:event_requireCredentialsItemStateChanged

    private void datasetUriValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datasetUriValueActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_datasetUriValueActionPerformed

    private void loadFromRemoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFromRemoteButtonActionPerformed
        // TODO implement this method
        closeDialog();
    }//GEN-LAST:event_loadFromRemoteButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton cancellationButton;
    private javax.swing.JButton clearFieldsButton;
    private javax.swing.JPanel credentialsPanel;
    private javax.swing.JLabel datasetUriExplanation;
    private javax.swing.JLabel datasetUriLabel;
    private javax.swing.JTextField datasetUriValue;
    private javax.swing.JLabel enterUriHint;
    private javax.swing.JLabel infoIcon;
    private javax.swing.JButton loadFromRemoteButton;
    private javax.swing.JLabel modelUriExplanation;
    private javax.swing.JLabel modelUriLabel;
    private javax.swing.JTextField modelUriValue;
    private javax.swing.JLabel passwordQuality;
    private javax.swing.JLabel provideCredentialsHint;
    private javax.swing.JPanel remoteLocationsPanel;
    private java.awt.Checkbox requireCredentials;
    private javax.swing.JTextField userNameVariable;
    private javax.swing.JPasswordField userPasswordValue;
    // End of variables declaration//GEN-END:variables
}
