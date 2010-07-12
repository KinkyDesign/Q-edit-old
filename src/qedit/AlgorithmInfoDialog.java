/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AlgorithmInfoDialog.java
 *
 * Created on 1 Ιουλ 2010, 7:43:10 μμ
 */
package qedit;

import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author hampos
 */
public class AlgorithmInfoDialog extends javax.swing.JDialog {

    /** Creates new form AlgorithmInfoDialog */
    public AlgorithmInfoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public AlgorithmInfoDialog(Frame owner) {
        super(owner);
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
        jPanel2 = new javax.swing.JPanel();
        algorithmURILabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        typesLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        commentLabel = new javax.swing.JLabel();
        publisherLabel = new javax.swing.JLabel();
        algorithmURIField = new javax.swing.JTextField();
        titleField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        typesList = new javax.swing.JList();
        algorithmDescriptionScrollable = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        contributionLabel = new javax.swing.JLabel();
        commentField = new javax.swing.JTextField();
        publisherField = new javax.swing.JTextField();
        contributionField = new javax.swing.JTextField();
        algorithmTypesToolbar = new javax.swing.JToolBar();
        addAlgorithmType = new javax.swing.JButton();
        removeSelectedAlgorithmType = new javax.swing.JButton();
        clearAllalgorithmTypes = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(AlgorithmInfoDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        algorithmURILabel.setIcon(resourceMap.getIcon("algorithmURILabel.icon")); // NOI18N
        algorithmURILabel.setText(resourceMap.getString("algorithmURILabel.text")); // NOI18N
        algorithmURILabel.setName("algorithmURILabel"); // NOI18N

        titleLabel.setText(resourceMap.getString("titleLabel.text")); // NOI18N
        titleLabel.setName("titleLabel"); // NOI18N

        typesLabel.setText(resourceMap.getString("typesLabel.text")); // NOI18N
        typesLabel.setName("typesLabel"); // NOI18N

        descriptionLabel.setText(resourceMap.getString("descriptionLabel.text")); // NOI18N
        descriptionLabel.setName("descriptionLabel"); // NOI18N

        commentLabel.setText(resourceMap.getString("commentLabel.text")); // NOI18N
        commentLabel.setName("commentLabel"); // NOI18N

        publisherLabel.setText(resourceMap.getString("publisherLabel.text")); // NOI18N
        publisherLabel.setName("publisherLabel"); // NOI18N

        algorithmURIField.setText(resourceMap.getString("algorithmURIField.text")); // NOI18N
        algorithmURIField.setName("algorithmURIField"); // NOI18N
        algorithmURIField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithmURIFieldActionPerformed(evt);
            }
        });

        titleField.setText(resourceMap.getString("titleField.text")); // NOI18N
        titleField.setName("titleField"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        typesList.setModel(new javax.swing.DefaultListModel());
        typesList.setName("typesList"); // NOI18N
        jScrollPane2.setViewportView(typesList);

        algorithmDescriptionScrollable.setName("algorithmDescriptionScrollable"); // NOI18N

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setName("descriptionTextArea"); // NOI18N
        algorithmDescriptionScrollable.setViewportView(descriptionTextArea);

        contributionLabel.setText(resourceMap.getString("contributionLabel.text")); // NOI18N
        contributionLabel.setName("contributionLabel"); // NOI18N

        commentField.setText(resourceMap.getString("commentField.text")); // NOI18N
        commentField.setName("commentField"); // NOI18N

        publisherField.setText(resourceMap.getString("publisherField.text")); // NOI18N
        publisherField.setName("publisherField"); // NOI18N

        contributionField.setText(resourceMap.getString("contributionField.text")); // NOI18N
        contributionField.setName("contributionField"); // NOI18N

        algorithmTypesToolbar.setFloatable(false);
        algorithmTypesToolbar.setRollover(true);
        algorithmTypesToolbar.setName("algorithmTypesToolbar"); // NOI18N

        addAlgorithmType.setIcon(resourceMap.getIcon("addAlgorithmType.icon")); // NOI18N
        addAlgorithmType.setText(resourceMap.getString("addAlgorithmType.text")); // NOI18N
        addAlgorithmType.setToolTipText(resourceMap.getString("addAlgorithmType.toolTipText")); // NOI18N
        addAlgorithmType.setFocusable(false);
        addAlgorithmType.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addAlgorithmType.setName("addAlgorithmType"); // NOI18N
        addAlgorithmType.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addAlgorithmType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAlgorithmTypeActionPerformed(evt);
            }
        });
        algorithmTypesToolbar.add(addAlgorithmType);

        removeSelectedAlgorithmType.setIcon(resourceMap.getIcon("removeSelectedAlgorithmType.icon")); // NOI18N
        removeSelectedAlgorithmType.setText(resourceMap.getString("removeSelectedAlgorithmType.text")); // NOI18N
        removeSelectedAlgorithmType.setToolTipText(resourceMap.getString("removeSelectedAlgorithmType.toolTipText")); // NOI18N
        removeSelectedAlgorithmType.setFocusable(false);
        removeSelectedAlgorithmType.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeSelectedAlgorithmType.setName("removeSelectedAlgorithmType"); // NOI18N
        removeSelectedAlgorithmType.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        algorithmTypesToolbar.add(removeSelectedAlgorithmType);

        clearAllalgorithmTypes.setIcon(resourceMap.getIcon("clearAllalgorithmTypes.icon")); // NOI18N
        clearAllalgorithmTypes.setText(resourceMap.getString("clearAllalgorithmTypes.text")); // NOI18N
        clearAllalgorithmTypes.setToolTipText(resourceMap.getString("clearAllalgorithmTypes.toolTipText")); // NOI18N
        clearAllalgorithmTypes.setFocusable(false);
        clearAllalgorithmTypes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clearAllalgorithmTypes.setName("clearAllalgorithmTypes"); // NOI18N
        clearAllalgorithmTypes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        algorithmTypesToolbar.add(clearAllalgorithmTypes);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(56, 56, 56)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(descriptionLabel)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(typesLabel)
                            .add(algorithmURILabel)
                            .add(titleLabel))
                        .add(jPanel2Layout.createSequentialGroup()
                            .add(45, 45, 45)
                            .add(commentLabel))
                        .add(jPanel2Layout.createSequentialGroup()
                            .add(31, 31, 31)
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(contributionLabel)
                                .add(publisherLabel)))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, algorithmTypesToolbar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, contributionField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, publisherField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .add(commentField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .add(algorithmDescriptionScrollable, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .add(titleField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .add(algorithmURIField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(algorithmURILabel)
                    .add(algorithmURIField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(titleField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(titleLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(typesLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(algorithmTypesToolbar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(algorithmDescriptionScrollable, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(descriptionLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(commentField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(commentLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(publisherField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(publisherLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(contributionLabel)
                    .add(contributionField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        closeButton.setIcon(resourceMap.getIcon("closeButton.icon")); // NOI18N
        closeButton.setText(resourceMap.getString("closeButton.text")); // NOI18N
        closeButton.setName("closeButton"); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(closeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 119, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(closeButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
}//GEN-LAST:event_closeButtonActionPerformed

    private void algorithmURIFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmURIFieldActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_algorithmURIFieldActionPerformed

    private void addAlgorithmTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAlgorithmTypeActionPerformed
        if (addAlgorithmTypeDialog == null) {
            JFrame jframe = QEditApp.getView().getFrame();
            addAlgorithmTypeDialog = new AddAlgorithmType(jframe);
            addAlgorithmTypeDialog.setTypesListParent(typesList);
        }
        addAlgorithmTypeDialog.setVisible(true);
    }//GEN-LAST:event_addAlgorithmTypeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AlgorithmInfoDialog dialog = new AlgorithmInfoDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public JTextField getAlgorithmURIField() {
        return algorithmURIField;
    }

    public JTextField getCommentField() {
        return commentField;
    }

    public JTextField getContributionField() {
        return contributionField;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public JTextField getPublisherField() {
        return publisherField;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JList getTypesList() {
        return typesList;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAlgorithmType;
    private javax.swing.JScrollPane algorithmDescriptionScrollable;
    private javax.swing.JToolBar algorithmTypesToolbar;
    private javax.swing.JTextField algorithmURIField;
    private javax.swing.JLabel algorithmURILabel;
    private javax.swing.JButton clearAllalgorithmTypes;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField commentField;
    private javax.swing.JLabel commentLabel;
    private javax.swing.JTextField contributionField;
    private javax.swing.JLabel contributionLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField publisherField;
    private javax.swing.JLabel publisherLabel;
    private javax.swing.JButton removeSelectedAlgorithmType;
    private javax.swing.JTextField titleField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel typesLabel;
    private javax.swing.JList typesList;
    // End of variables declaration//GEN-END:variables
    private AddAlgorithmType addAlgorithmTypeDialog;
}
