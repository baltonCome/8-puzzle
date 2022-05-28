package problemaPuzzle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 *
 * @author
 *Insercao manual do array
 */
public class ManualEntry extends javax.swing.JFrame {

    private MainView viewLocal = new MainView();

    public ManualEntry(MainView view) {
        initComponents();
        viewLocal = view;
        setLocationRelativeTo(null);
    }

    public ManualEntry() {
        initComponents();
        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInsert = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtValue = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnInsert.setText("Inserir");
        btnInsert.addActionListener(this::btnInsertActionPerformed);

        jLabel1.setText("Digite os números do quebra-cabeça:");

        txtValue.setText("012345678");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnInsert)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInsert)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        boolean repetido = false;

        if (txtValue.getText().length() != 9) {
            JOptionPane.showMessageDialog(null, "Formato incorreto. Insira uma série de 9 caracteres.");
        } else {
            Pattern p = Pattern.compile("[^0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(txtValue.getText());
            boolean b = m.find();

            if (b) {
                JOptionPane.showMessageDialog(null, "Insira apenas caracteres númericos.");
            } else {

                for (int i = 0; i < txtValue.getText().length(); i++) {
                    for (int j = i + 1; j < txtValue.getText().length(); j++) {
                        if (txtValue.getText().charAt(i) == txtValue.getText().charAt(j)) {
                            repetido = true;
                        }
                    }
                }

                if (repetido) {
                    JOptionPane.showMessageDialog(null, "Não insira numeros repetidos.");
                } else {
                    viewLocal.setManualValue(txtValue.getText());
                    viewLocal.fillManually();
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ManualEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ManualEntry().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtValue;
    // End of variables declaration//GEN-END:variables
}
