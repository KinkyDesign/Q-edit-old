/*
 * AddAlgorithmType.java
 *
 * Created on Jul 11, 2010, 8:02:11 PM
 */
package qedit;

import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author chung
 */
public class AddAlgorithmType extends javax.swing.JDialog {

    private java.util.Set rightSet = new java.util.HashSet();
    private javax.swing.JList typesListParent;

    public JList getTypesListParent() {
        return typesListParent;
    }

    public void setTypesListParent(JList typesListParent) {
        this.typesListParent = typesListParent;
    }
    

    public Set getRightSet() {
        return rightSet;
    }

    /** Creates new form AddAlgorithmType */
    public AddAlgorithmType(java.awt.Frame parent) {
        super(parent);
        initComponents();
    }

    /** Creates new form AddAlgorithmType */
    public AddAlgorithmType(java.awt.Frame parent, boolean modal) {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        leftList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        rightList = new javax.swing.JList();
        jToolBar1 = new javax.swing.JToolBar();
        leftToRightButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        infoAboutAlgorithmType = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(qedit.QEditApp.class).getContext().getResourceMap(AddAlgorithmType.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        leftList.setModel(new javax.swing.AbstractListModel() {

            private String[] getStringValues(){
                String[] strings = new String[qedit.AlgorithmTypes.values().length];
                int flag = 0;
                qedit.AlgorithmTypes[] types = qedit.AlgorithmTypes.values();
                for (qedit.AlgorithmTypes algType : types){
                    strings[flag] = algType.toString();
                    flag++;
                }
                return strings;
            }

            public int getSize() { return getStringValues().length; }
            public Object getElementAt(int i) { return getStringValues()[i]; }
        });
        leftList.setName("leftList"); // NOI18N
        leftList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                leftListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(leftList);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        rightList.setModel(new javax.swing.DefaultListModel());
        rightList.setName("rightList"); // NOI18N
        jScrollPane2.setViewportView(rightList);

        jToolBar1.setFloatable(false);
        jToolBar1.setOrientation(1);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        leftToRightButton.setIcon(resourceMap.getIcon("leftToRightButton.icon")); // NOI18N
        leftToRightButton.setText(resourceMap.getString("leftToRightButton.text")); // NOI18N
        leftToRightButton.setToolTipText(resourceMap.getString("leftToRightButton.toolTipText")); // NOI18N
        leftToRightButton.setFocusable(false);
        leftToRightButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        leftToRightButton.setName("leftToRightButton"); // NOI18N
        leftToRightButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        leftToRightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftToRightButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(leftToRightButton);

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setToolTipText(resourceMap.getString("jButton1.toolTipText")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton3.setIcon(resourceMap.getIcon("jButton3.icon")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setToolTipText(resourceMap.getString("jButton3.toolTipText")); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        infoAboutAlgorithmType.setText(resourceMap.getString("infoAboutAlgorithmType.text")); // NOI18N
        infoAboutAlgorithmType.setName("infoAboutAlgorithmType"); // NOI18N

        okButton.setText(resourceMap.getString("okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoAboutAlgorithmType, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(312, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoAboutAlgorithmType, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(okButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void leftListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_leftListValueChanged
        infoAboutAlgorithmType.setText(AlgorithmTypes.values()[leftList.getSelectedIndex()].getDescription());
    }//GEN-LAST:event_leftListValueChanged

    private void leftToRightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftToRightButtonActionPerformed
        int[] selectedIndices = leftList.getSelectedIndices();
        for (int si : selectedIndices) {
            AlgorithmTypes type = AlgorithmTypes.values()[si];
            if (!rightSet.contains(type)) {
                rightSet.add(AlgorithmTypes.values()[si]);
                ((DefaultListModel) rightList.getModel()).addElement(AlgorithmTypes.values()[si].toString());
            }
        }
    }//GEN-LAST:event_leftToRightButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        for (Object o : ((DefaultListModel)rightList.getModel()).toArray()){
            ((DefaultListModel)typesListParent.getModel()).addElement(o.toString());
        }
        setVisible(false);
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AddAlgorithmType dialog = new AddAlgorithmType(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel infoAboutAlgorithmType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JList leftList;
    private javax.swing.JButton leftToRightButton;
    private javax.swing.JButton okButton;
    private javax.swing.JList rightList;
    // End of variables declaration//GEN-END:variables
}
