package telas;

import java.sql.*;
import dal.ModuloConexao;
import javax.swing.JOptionPane;
import java.util.HashSet;
import static telas.telaprincipal_1.idQuarto;

public class tela_hospede extends javax.swing.JFrame {

    private static void HIDE_ON_CLOSE() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    static String cpfHospede = null;
      
    public tela_hospede() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
//    private void atualizarIdHospede(){
//        
//        String sql = "SELECT id_hosp from tbl_hospede WHERE cpf_hosp=?";
//        
//        try {
//            
//            pst = conexao.prepareStatement(sql);
//        
//            String cpf = txtCPF.getText();
//            pst.setString(1, cpf);
//            
//            rs = pst.executeQuery();
//            
//            if (rs.next()) {
//                
//                idHospede = Integer.parseInt(rs.getString("id_hosp"));
//                
//            }
//        } catch (Exception e) {
//            
//            System.out.println(e);
//        }
        
//    }
    
    public static String getCpfHospede() {
        
        return cpfHospede;
    }
    
    private void adicionar() {
    // Verificar se todos os campos estão preenchidos
    if (txtNome.getText().isEmpty() || txtEmail.getText().isEmpty() || txtDDD.getText().isEmpty() || txtCelular.getText().isEmpty() || txtUF.getText().isEmpty() || txtCidade.getText().isEmpty() || txtEnd.getText().isEmpty() || txtCPF.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!");
        return; // Sai do método sem realizar a inserção
    }
    
    
    
    // Validar CPF
    if (!validarCPF(txtCPF.getText())) {
        JOptionPane.showMessageDialog(null, "CPF inválido! Por favor, insira um CPF válido.");
        return; // Sai do método sem realizar a inserção
    }
    
    // Verifica se a UF inserida é válida
    if (!validarUF(txtUF.getText())) {
        JOptionPane.showMessageDialog(null, "UF inválida");
        return; // Sai do método se a UF for inválida
    }
    
    // Verifica se o número de celular tem até 9 dígitos
    if (!validarCelular(txtCelular.getText())) {
        JOptionPane.showMessageDialog(null, "Número de celular inválido");
        return; // Sai do método se o número de celular for inválido
    }

    // Se todos os campos estiverem preenchidos e o CPF for válido, prossegue com a inserção no banco de dados
    String sql = "INSERT INTO tbl_hospede (cpf_hosp, nome_hosp , email_hosp , ddd_hosp , celular_hosp , uf_hosp , cid_hosp , end_hosp) VALUES (?,?,?,?,?,?,?,?)";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtCPF.getText());
        pst.setString(2, txtNome.getText());
        pst.setString(3, txtEmail.getText());
        pst.setString(4, txtDDD.getText());
        pst.setString(5, txtCelular.getText());
        pst.setString(6, txtUF.getText());
        pst.setString(7, txtCidade.getText());
        pst.setString(8, txtEnd.getText());
  
        
        int adicionado = pst.executeUpdate();
        
        if (adicionado > 0) {
            JOptionPane.showMessageDialog(null, "USUÁRIO CADASTRADO!");
            
        }    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
    
    private void alterar(){
    String sql = "UPDATE tbl_hospede SET cpf_hosp=? ,nome_hosp=? , email_hosp=? , ddd_hosp=? , celular_hosp=? , uf_hosp=? , cid_hosp=? , end_hosp=?";

    try {
        pst.setString(1, txtCPF.getText());
        pst.setString(2, txtNome.getText());
        pst.setString(3, txtEmail.getText());
        pst.setString(4, txtDDD.getText());
        pst.setString(5, txtCelular.getText());
        pst.setString(6, txtUF.getText());
        pst.setString(7, txtCidade.getText());
        pst.setString(8, txtEnd.getText());


        int adicionado = pst.executeUpdate();

        if(adicionado > 0) {
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
            // Limpa o formulário após a atualização
           

        } else {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar o usuário. Verifique os dados e tente novamente.");
        }
    } catch(Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar o usuário: " + e.getMessage());
    }
}
    
   private void consultar() {
    String sql = "SELECT * FROM tbl_hospede WHERE cpf_hosp=?";
    
    try {
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtCPF.getText());
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            // Se encontrou um registro com o CPF fornecido, preenche os campos com os dados
            txtNome.setText(rs.getString("nome_hosp"));
            txtEmail.setText(rs.getString("email_hosp"));
            txtDDD.setText(rs.getString("ddd_hosp"));
            txtCelular.setText(rs.getString("celular_hosp"));
            txtUF.setText(rs.getString("uf_hosp"));
            txtCidade.setText(rs.getString("cid_hosp"));
            txtEnd.setText(rs.getString("end_hosp"));
            txtCPF.setText(rs.getString("cpf_hosp"));
        } else {
            // Se não encontrou registro com o CPF fornecido, exibe uma mensagem
            JOptionPane.showMessageDialog(null, "Usuário não cadastrado.");
            // E limpa os campos do formulário
            txtNome.setText(null);
            txtEmail.setText(null);
            txtDDD.setText(null);
            txtCelular.setText(null);
            txtUF.setText(null);
            txtCidade.setText(null);
            txtEnd.setText(null);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao consultar usuário: " + e.getMessage());
    }
}
   
       private void pegarQuarto() {
        
        String input = JOptionPane.showInputDialog("Digite o número do quarto:\n 1 - Executivo\n 2 - Família\n 3 - Premium");
        
        int id = Integer.parseInt(input);
        
        idQuarto = id;
    }
    
    public static int pegarIDQuarto() {
                
        return idQuarto;
    }

    
    private boolean validarCelular(String celular) {
    // Verifica se o número de celular tem até 9 dígitos
    return celular.length() <= 9;
}
    
    private boolean validarUF(String uf) {
    // Inicializa o conjunto de UFs brasileiras manualmente
    HashSet<String> ufsBrasileiras = new HashSet<>();
    ufsBrasileiras.add("AC");
    ufsBrasileiras.add("AL");
    ufsBrasileiras.add("AP");
    ufsBrasileiras.add("AM");
    ufsBrasileiras.add("BA");
    ufsBrasileiras.add("CE");
    ufsBrasileiras.add("DF");
    ufsBrasileiras.add("ES");
    ufsBrasileiras.add("GO");
    ufsBrasileiras.add("MA");
    ufsBrasileiras.add("MT");
    ufsBrasileiras.add("MS");
    ufsBrasileiras.add("MG");
    ufsBrasileiras.add("PA");
    ufsBrasileiras.add("PB");
    ufsBrasileiras.add("PR");
    ufsBrasileiras.add("PE");
    ufsBrasileiras.add("PI");
    ufsBrasileiras.add("RJ");
    ufsBrasileiras.add("RN");
    ufsBrasileiras.add("RS");
    ufsBrasileiras.add("RO");
    ufsBrasileiras.add("RR");
    ufsBrasileiras.add("SC");
    ufsBrasileiras.add("SP");
    ufsBrasileiras.add("SE");
    ufsBrasileiras.add("TO");
 
    return ufsBrasileiras.contains(uf.toUpperCase());

}
    
    private boolean validarCPF(String cpf) {
    // Remove caracteres não numéricos do CPF
    cpf = cpf.replaceAll("[^0-9]", "");
    
    // Verifica se o CPF tem 11 dígitos
    if (cpf.length() != 11) {
        return false;
    }
    
    // Verifica se todos os dígitos são iguais, o que é inválido para um CPF válido
    int num1 = Character.getNumericValue(cpf.charAt(0));
    int num2 = Character.getNumericValue(cpf.charAt(1));
    int num3 = Character.getNumericValue(cpf.charAt(2));
    int num4 = Character.getNumericValue(cpf.charAt(3));
    int num5 = Character.getNumericValue(cpf.charAt(4));
    int num6 = Character.getNumericValue(cpf.charAt(5));
    int num7 = Character.getNumericValue(cpf.charAt(6));
    int num8 = Character.getNumericValue(cpf.charAt(7));
    int num9 = Character.getNumericValue(cpf.charAt(8));
    int num10 = Character.getNumericValue(cpf.charAt(9));
    int num11 = Character.getNumericValue(cpf.charAt(10));
    
    if (num1 == num2 && num2 == num3 && num3 == num4 && num4 == num5 &&
        num5 == num6 && num6 == num7 && num7 == num8 && num8 == num9 &&
        num9 == num10 && num10 == num11) {
        return false; // CPF inválido
    }
    
    // Calcula a soma dos produtos dos dígitos pelos pesos
    int soma1 = num1 * 10 + num2 * 9 + num3 * 8 + num4 * 7 + num5 * 6 + num6 * 5 + num7 * 4 + num8 * 3 + num9 * 2;
    int soma2 = num1 * 11 + num2 * 10 + num3 * 9 + num4 * 8 + num5 * 7 + num6 * 6 + num7 * 5 + num8 * 4 + num9 * 3 + num10 * 2;
    
    // Calcula os restos
    int resto1 = (soma1 * 10) % 11;
    int resto2 = (soma2 * 10) % 11;
    
    // Verifica se os restos correspondem aos dígitos verificadores
    if (resto1 == num10 && resto2 == num11) {
        return true; // CPF válido
    } else {
        return false; // CPF inválido
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloUf = new javax.swing.JLabel();
        txtUF = new javax.swing.JTextField();
        tituloCid = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tituloEnd = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtEnd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tituloCpf = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        txtDDD = new javax.swing.JTextField();
        btnAdicionar1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnProsseguir = new javax.swing.JButton();
        AtualizarInfo = new javax.swing.JButton();
        VizualizarInfo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Hospede");

        tituloUf.setText("UF:");

        txtUF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUFActionPerformed(evt);
            }
        });

        tituloCid.setText("Cidade:");

        jLabel3.setText("Nome:");

        tituloEnd.setText("Endereço:");

        jLabel4.setText("Email:");

        tituloCpf.setText("CPF:");

        jLabel7.setText("DDD:");

        txtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFActionPerformed(evt);
            }
        });

        txtDDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDDDActionPerformed(evt);
            }
        });

        btnAdicionar1.setText("Adicionar Hospede");
        btnAdicionar1.setPreferredSize(new java.awt.Dimension(90, 30));
        btnAdicionar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionar1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Celular:");

        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });

        jLabel1.setText("Informações do Hospede");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/voltar.png"))); // NOI18N
        jButton2.setText("Voltar");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnProsseguir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/prosseguir.png"))); // NOI18N
        btnProsseguir.setText("Prosseguir");
        btnProsseguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsseguirActionPerformed(evt);
            }
        });

        AtualizarInfo.setText("Atualizar Informações");
        AtualizarInfo.setPreferredSize(new java.awt.Dimension(90, 30));
        AtualizarInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtualizarInfoActionPerformed(evt);
            }
        });

        VizualizarInfo.setText("Verificar possivel cadastro");
        VizualizarInfo.setPreferredSize(new java.awt.Dimension(90, 30));
        VizualizarInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VizualizarInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(177, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(txtNome)
                                .addComponent(txtEmail)
                                .addComponent(tituloEnd)
                                .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AtualizarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tituloCpf)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAdicionar1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(tituloUf)
                                        .addComponent(txtUF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(25, 25, 25)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCelular)
                                        .addComponent(jLabel5)
                                        .addComponent(tituloCid)
                                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(134, 134, 134))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnProsseguir)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(VizualizarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(238, 238, 238))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(269, 269, 269))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tituloCid)
                                    .addComponent(tituloUf))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tituloEnd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tituloCpf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AtualizarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(VizualizarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(btnProsseguir)
                .addGap(29, 29, 29))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUFActionPerformed

    private void txtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFActionPerformed

    private void txtDDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDDDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDDDActionPerformed

    private void btnAdicionar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionar1ActionPerformed
                                               
        adicionar();
        


    }//GEN-LAST:event_btnAdicionar1ActionPerformed

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       // Fecha a tela atual
        dispose();

    // Abre a tela anterior
   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnProsseguirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsseguirActionPerformed
        pegarQuarto();
//        atualizarIdHospede();
        cpfHospede = txtCPF.getText();
        telaquartos tela_hospede = new telaquartos();
        tela_hospede.setVisible(true);
        this.dispose(); // Fecha a TelaSecundaria após abrir a TelaPrincipal

//         telapagamento pagamento = new telapagamento();
//         pagamento.setVisible(true);
    }//GEN-LAST:event_btnProsseguirActionPerformed

    private void AtualizarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtualizarInfoActionPerformed
        alterar();
    }//GEN-LAST:event_AtualizarInfoActionPerformed

    private void VizualizarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VizualizarInfoActionPerformed
        consultar();
    }//GEN-LAST:event_VizualizarInfoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tela_hospede.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tela_hospede.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tela_hospede.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tela_hospede.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tela_hospede().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AtualizarInfo;
    private javax.swing.JButton VizualizarInfo;
    private javax.swing.JButton btnAdicionar1;
    private javax.swing.JButton btnProsseguir;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel tituloCid;
    private javax.swing.JLabel tituloCpf;
    private javax.swing.JLabel tituloEnd;
    private javax.swing.JLabel tituloUf;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtDDD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtUF;
    // End of variables declaration//GEN-END:variables
}
