/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DescriptorValueWizard.java
 *
 * Created on Jun 10, 2010, 4:33:39 PM
 */

package qedit;

import java.awt.event.ItemEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chung
 */
public class DescriptorValueWizard extends javax.swing.JDialog {

    private JTable descriptorsTable;

    public DescriptorValueWizard(java.awt.Frame parent) {
        super(parent);
        initComponents();
    }

    /** Creates new form DescriptorValueWizard */
    public DescriptorValueWizard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        newDescrRadioButton = new javax.swing.JRadioButton();
        existDescrRadioButton = new javax.swing.JRadioButton();
        descrTitleLabel = new javax.swing.JLabel();
        descriptorTitle = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        featValueLabel = new javax.swing.JLabel();
        featureValueUserProvided = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        unitsFeatLabel = new javax.swing.JLabel();
        featValueUnitsUserProvided = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        advancedDescriptorMetaLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(DescriptorValueWizard.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        newDescrRadioButton.setSelected(true);
        newDescrRadioButton.setText(resourceMap.getString("newDescrRadioButton.text")); // NOI18N
        newDescrRadioButton.setName("newDescrRadioButton"); // NOI18N
        newDescrRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                newDescrRadioButtonItemStateChanged(evt);
            }
        });

        existDescrRadioButton.setText(resourceMap.getString("existDescrRadioButton.text")); // NOI18N
        existDescrRadioButton.setName("existDescrRadioButton"); // NOI18N
        existDescrRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                existDescrRadioButtonItemStateChanged(evt);
            }
        });

        descrTitleLabel.setText(resourceMap.getString("descrTitleLabel.text")); // NOI18N
        descrTitleLabel.setName("descrTitleLabel"); // NOI18N

        descriptorTitle.setText(resourceMap.getString("descriptorTitle.text")); // NOI18N
        descriptorTitle.setName("descriptorTitle"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setEnabled(false);
        jLabel4.setName("jLabel4"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "By Name (Title)", "By URI"}));
        jComboBox1.setEnabled(false);
        jComboBox1.setName("jComboBox1"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setEnabled(false);
        jLabel5.setName("jLabel5"); // NOI18N

        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setEnabled(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        featValueLabel.setText(resourceMap.getString("featValueLabel.text")); // NOI18N
        featValueLabel.setName("featValueLabel"); // NOI18N

        featureValueUserProvided.setText(resourceMap.getString("featureValueUserProvided.text")); // NOI18N
        featureValueUserProvided.setName("featureValueUserProvided"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jSeparator2.setName("jSeparator2"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        unitsFeatLabel.setText(resourceMap.getString("unitsFeatLabel.text")); // NOI18N
        unitsFeatLabel.setName("unitsFeatLabel"); // NOI18N

        featValueUnitsUserProvided.setText(resourceMap.getString("featValueUnitsUserProvided.text")); // NOI18N
        featValueUnitsUserProvided.setName("featValueUnitsUserProvided"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newDescrRadioButton, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(descrTitleLabel)
                                .addGap(18, 18, 18)
                                .addComponent(descriptorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(existDescrRadioButton)))
                .addGap(98, 98, 98))
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(570, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(featValueLabel)
                .addGap(18, 18, 18)
                .addComponent(featureValueUserProvided, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(574, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(unitsFeatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(featValueUnitsUserProvided, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newDescrRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descrTitleLabel)
                    .addComponent(descriptorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(existDescrRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(featureValueUserProvided, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(featValueLabel))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unitsFeatLabel)
                    .addComponent(featValueUnitsUserProvided, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        okButton.setIcon(resourceMap.getIcon("okButton.icon")); // NOI18N
        okButton.setText(resourceMap.getString("okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        advancedDescriptorMetaLabel.setIcon(resourceMap.getIcon("advancedDescriptorMetaLabel.icon")); // NOI18N
        advancedDescriptorMetaLabel.setText(resourceMap.getString("advancedDescriptorMetaLabel.text")); // NOI18N
        advancedDescriptorMetaLabel.setToolTipText(resourceMap.getString("advancedDescriptorMetaLabel.toolTipText")); // NOI18N
        advancedDescriptorMetaLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        advancedDescriptorMetaLabel.setName("advancedDescriptorMetaLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(447, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(advancedDescriptorMetaLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(okButton)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(advancedDescriptorMetaLabel)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JTable getDescriptorsTable() {
        return descriptorsTable;
    }

    public void setDescriptorsTable(JTable descriptorsTable) {
        this.descriptorsTable = descriptorsTable;
    }


    private void close(){
        setVisible(false);
        dispose();
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        DefaultTableModel descritporsTableModel = (DefaultTableModel) descriptorsTable.getModel();
        String[] values = new String[descritporsTableModel.getColumnCount()];
        values[0] = descriptorTitle.getText();
        values[1] = featureValueUserProvided.getText();
        values[2] = featValueUnitsUserProvided.getText();
        descritporsTableModel.addRow(values);
        close();
    }//GEN-LAST:event_okButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void newDescrRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_newDescrRadioButtonItemStateChanged
        boolean isButton1Selected =  (ItemEvent.SELECTED==evt.getStateChange());
        descrTitleLabel.setEnabled(isButton1Selected);
        descriptorTitle.setEnabled(isButton1Selected);
        existDescrRadioButton.setSelected(!isButton1Selected);
        jLabel4.setEnabled(!isButton1Selected);
        jLabel5.setEnabled(!isButton1Selected);
        jComboBox1.setEnabled(!isButton1Selected);
        jTextField2.setEnabled(!isButton1Selected);
    }//GEN-LAST:event_newDescrRadioButtonItemStateChanged

    private void existDescrRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_existDescrRadioButtonItemStateChanged
        boolean isButton2Selected =  (ItemEvent.SELECTED==evt.getStateChange());
        descrTitleLabel.setEnabled(!isButton2Selected);
        descriptorTitle.setEnabled(!isButton2Selected);
        newDescrRadioButton.setSelected(!isButton2Selected);
        jLabel4.setEnabled(isButton2Selected);
        jLabel5.setEnabled(isButton2Selected);
        jComboBox1.setEnabled(isButton2Selected);
        jTextField2.setEnabled(isButton2Selected);
    }//GEN-LAST:event_existDescrRadioButtonItemStateChanged
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel advancedDescriptorMetaLabel;
    private javax.swing.JLabel descrTitleLabel;
    private javax.swing.JTextField descriptorTitle;
    private javax.swing.JRadioButton existDescrRadioButton;
    private javax.swing.JLabel featValueLabel;
    private javax.swing.JTextField featValueUnitsUserProvided;
    private javax.swing.JTextField featureValueUserProvided;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JRadioButton newDescrRadioButton;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel unitsFeatLabel;
    // End of variables declaration//GEN-END:variables

}
