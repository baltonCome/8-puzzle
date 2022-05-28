package problemaPuzzle;

import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author
 *Tela principal do game
 */
public class MainView extends javax.swing.JFrame {

    private String game;
    private GameState currentGame;
    private int searchType;
    private String manualValue;

    public MainView() {
        initComponents();
        setLocationRelativeTo(null);
        jBtnGo.setEnabled(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBtn00 = new javax.swing.JButton();
        jBtn01 = new javax.swing.JButton();
        jBtn02 = new javax.swing.JButton();
        jBtn12 = new javax.swing.JButton();
        jBtn11 = new javax.swing.JButton();
        jBtn10 = new javax.swing.JButton();
        jBtn20 = new javax.swing.JButton();
        jBtn21 = new javax.swing.JButton();
        jBtn22 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBtnMan = new javax.swing.JButton();
        jBtnGo = new javax.swing.JButton();
        jBtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBtn00.setText("1");

        jBtn01.setText("2");

        jBtn02.setText("3");

        jBtn12.setText("4");

        jBtn11.setText("0");

        jBtn10.setText("5");

        jBtn20.setText("6");

        jBtn21.setText("7");

        jBtn22.setText("8");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("8-Puzzle");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Estado Inicial:");

        jBtnMan.setText("Inserir estado inicial");
        jBtnMan.addActionListener(this::jBtnManActionPerformed);

        jBtnGo.setText("Iniciar");
        jBtnGo.addActionListener(this::jBtnGoActionPerformed);

        jBtnExit.setText("Sair");
        jBtnExit.addActionListener(this::jBtnExitActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtn10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtn00)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn01)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn02)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnMan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jBtnGo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtn20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtn22))
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtn00)
                    .addComponent(jBtn01)
                    .addComponent(jBtn02))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtn10)
                    .addComponent(jBtn11)
                    .addComponent(jBtn12)
                    .addComponent(jBtnMan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtn20)
                    .addComponent(jBtn21)
                    .addComponent(jBtn22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnExit)
                    .addComponent(jBtnGo))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jBtnManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnManActionPerformed
        ManualEntry entry = new ManualEntry(this); // Gera um game a partir  de uma entrada manual de dados
        entry.setVisible(true);
    }//GEN-LAST:event_jBtnManActionPerformed

    public void fillManually() {
        int k = 0;
        Integer[][] manualGame = new Integer[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                manualGame[i][j] = Integer.parseInt(manualValue.substring(k, k + 1));
                k++;
            }
        }

        jBtn00.setText(manualGame[0][0].toString());
        jBtn01.setText(manualGame[0][1].toString());
        jBtn02.setText(manualGame[0][2].toString());
        jBtn10.setText(manualGame[1][0].toString());
        jBtn11.setText(manualGame[1][1].toString());
        jBtn12.setText(manualGame[1][2].toString());
        jBtn20.setText(manualGame[2][0].toString());
        jBtn21.setText(manualGame[2][1].toString());
        jBtn22.setText(manualGame[2][2].toString());

        this.currentGame = new GameState(manualGame);
        jBtnGo.setEnabled(true);

    }

    private void jBtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jBtnExitActionPerformed

    private void jBtnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGoActionPerformed
        if (currentGame.hasSolution(currentGame.getGame())) {
            Search search = new Search();
            ArrayList<GameState> path = search.search(currentGame);
            currentGame.resetGeneratedStates();
            new GameView(currentGame, true, path);

        } else {
            JOptionPane.showMessageDialog(null, "Não existe solução para este estado. Insira outro estado.");
            jBtnGo.setEnabled(false);
        }

    }//GEN-LAST:event_jBtnGoActionPerformed

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtn00;
    private javax.swing.JButton jBtn01;
    private javax.swing.JButton jBtn02;
    private javax.swing.JButton jBtn10;
    private javax.swing.JButton jBtn11;
    private javax.swing.JButton jBtn12;
    private javax.swing.JButton jBtn20;
    private javax.swing.JButton jBtn21;
    private javax.swing.JButton jBtn22;
    private javax.swing.JButton jBtnExit;
    private javax.swing.JButton jBtnGo;
    private javax.swing.JButton jBtnMan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    /**
     * @param manualValue the manualValue to set
     */
    public void setManualValue(String manualValue) {
        this.manualValue = manualValue;
    }
}
