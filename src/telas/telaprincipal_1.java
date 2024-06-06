package telas;

import dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.text.*;

public class telaprincipal_1 extends javax.swing.JFrame {
    
    static int idQuarto = 0;
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
 
    public telaprincipal_1() {
       initComponents();
         conexao = ModuloConexao.conector();
    }
    
    private void pegarQuarto() {
        
        String input = JOptionPane.showInputDialog("Digite o número do quarto:\n 1 - Executivo\n 2 - Família\n 3 - Premium");
        
        int id = Integer.parseInt(input);
        
        idQuarto = id;
    }
    
    public static int pegarIDQuarto() {
                
        return idQuarto;
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        MenuReserva = new javax.swing.JMenu();
        IniciarReserva = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        verificarQuartos = new javax.swing.JMenuItem();
        MenuHospede = new javax.swing.JMenu();
        telaHospede = new javax.swing.JMenuItem();
        MenuSair = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setTitle("Tela Inicial");

        MenuReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reservar.png"))); // NOI18N
        MenuReserva.setText("Reserva");
        MenuReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuReservaActionPerformed(evt);
            }
        });

        IniciarReserva.setText("Iniciar Reserva");
        IniciarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarReservaActionPerformed(evt);
            }
        });
        MenuReserva.add(IniciarReserva);

        jMenuBar1.add(MenuReserva);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/quarto.png"))); // NOI18N
        jMenu1.setText("Quartos");

        verificarQuartos.setText("Verificar Quartos");
        verificarQuartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verificarQuartosActionPerformed(evt);
            }
        });
        jMenu1.add(verificarQuartos);

        jMenuBar1.add(jMenu1);

        MenuHospede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/hospedes.png"))); // NOI18N
        MenuHospede.setText("Hospede");
        MenuHospede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuHospedeActionPerformed(evt);
            }
        });

        telaHospede.setText("Hospede");
        MenuHospede.add(telaHospede);

        jMenuBar1.add(MenuHospede);

        MenuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sair.png"))); // NOI18N
        MenuSair.setText("Opção");
        MenuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSairActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MenuSair.add(jMenuItem1);

        jMenuBar1.add(MenuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 704, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSairActionPerformed

    }//GEN-LAST:event_MenuSairActionPerformed

    private void MenuHospedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuHospedeActionPerformed
                  // TODO add your handling code here:
    }//GEN-LAST:event_MenuHospedeActionPerformed

    private void MenuReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuReservaActionPerformed
         
    }//GEN-LAST:event_MenuReservaActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void IniciarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarReservaActionPerformed
        // TODO add your handling code here:
        tela_hospede hospede = new tela_hospede();
        hospede.setVisible(true);
    }//GEN-LAST:event_IniciarReservaActionPerformed

    private void verificarQuartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verificarQuartosActionPerformed
       quartos_disponiveis quartos = new quartos_disponiveis();
       quartos.setVisible(true);
    }//GEN-LAST:event_verificarQuartosActionPerformed
    
    public static void main(String args[]) {

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaprincipal_1().setVisible(true);
            }
        });
    }    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem IniciarReserva;
    private javax.swing.JMenu MenuHospede;
    private javax.swing.JMenu MenuReserva;
    private javax.swing.JMenu MenuSair;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem telaHospede;
    private javax.swing.JMenuItem verificarQuartos;
    // End of variables declaration//GEN-END:variables
}


