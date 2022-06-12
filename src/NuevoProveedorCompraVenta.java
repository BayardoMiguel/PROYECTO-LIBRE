

import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricardo
 */
public class NuevoProveedorCompraVenta extends javax.swing.JFrame {
public static String valor; 

    /**
     * Creates new form GestionarProveedores
     */
    public NuevoProveedorCompraVenta() {
        initComponents();
        setResizable(false);
        setLocation(0,0);
        setTitle("Nuevo Proveedor"); 
        getContentPane().setBackground(Color.white);
        jTextFieldIdProveedor.setText(valor);
        jTextFieldNombre.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldApellido = new javax.swing.JTextField();
        jButtonGuardar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldIdProveedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldCorreo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldCelular = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1367, 740));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nombre");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(200, 250, 60, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Apellido");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(200, 280, 60, 20);
        getContentPane().add(jTextFieldNombre);
        jTextFieldNombre.setBounds(280, 240, 270, 30);

        jTextFieldApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldApellidoActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldApellido);
        jTextFieldApellido.setBounds(280, 270, 270, 30);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        jButtonGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonGuardarKeyPressed(evt);
            }
        });
        getContentPane().add(jButtonGuardar);
        jButtonGuardar.setBounds(280, 370, 270, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nit o C.C:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(190, 210, 60, 20);

        jTextFieldIdProveedor.setEditable(false);
        getContentPane().add(jTextFieldIdProveedor);
        jTextFieldIdProveedor.setBounds(280, 210, 270, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Correo");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(210, 310, 50, 20);
        getContentPane().add(jTextFieldCorreo);
        jTextFieldCorreo.setBounds(280, 300, 270, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Celular");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(210, 340, 50, 20);
        getContentPane().add(jTextFieldCelular);
        jTextFieldCelular.setBounds(280, 330, 270, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Lateral 2.jpeg"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(180, 60, 400, 610);

        jPanel4.setBackground(new java.awt.Color(168, 37, 104));
        jPanel4.setLayout(null);
        getContentPane().add(jPanel4);
        jPanel4.setBounds(-10, 0, 180, 690);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Lateral 1.jpeg"))); // NOI18N
        getContentPane().add(jLabel13);
        jLabel13.setBounds(0, -10, 590, 720);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Proveedores 3.jpeg"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(320, 0, 1240, 900);

        jMenu1.setText("Salir");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Regresar");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        try {
            // TODO add your handling code here:
            Proveedor Proveedores1 = new Proveedor();
            Proveedores1 .setIdProveedor(Integer.parseInt(jTextFieldIdProveedor.getText()));
            Proveedores1.setNombre(jTextFieldNombre.getText());
            Proveedores1.setApellido(jTextFieldApellido.getText());
            Proveedores1.setCorreo(jTextFieldCorreo.getText());
            Proveedores1.setCelular(jTextFieldCelular.getText());
            Proveedores1.setInsertarProveedor(Proveedores1.getIdProveedor(), Proveedores1.getNombre(),
            Proveedores1.getApellido(),Proveedores1.getCorreo(),Proveedores1.getCelular());
        } catch (SQLException ex) {
            Logger.getLogger(NuevoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacturarCompra obj = new FacturarCompra();
        FacturarCompra.jTextFieldIdProveedor.setText(jTextFieldIdProveedor.getText());
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        // TODO add your handling code here:
         int x = JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?");
                    if (x == JOptionPane.YES_OPTION){
                        System.exit(0);
                    }
//                    else if(x == JOptionPane.NO_OPTION){
//                    }
    }//GEN-LAST:event_jMenu1MousePressed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jTextFieldApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldApellidoActionPerformed

    private void jButtonGuardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonGuardarKeyPressed
         try {
            // TODO add your handling code here:
            Proveedor Proveedores1 = new Proveedor();
            Proveedores1 .setIdProveedor(Integer.parseInt(jTextFieldIdProveedor.getText()));
            Proveedores1.setNombre(jTextFieldNombre.getText());
            Proveedores1.setApellido(jTextFieldApellido.getText());
            Proveedores1.setCorreo(jTextFieldCorreo.getText());
            Proveedores1.setCelular(jTextFieldCelular.getText());
            Proveedores1.setInsertarProveedor(Proveedores1.getIdProveedor(), Proveedores1.getNombre(),
            Proveedores1.getApellido(),Proveedores1.getCorreo(),Proveedores1.getCelular());
        } catch (SQLException ex) {
            Logger.getLogger(NuevoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacturarCompra obj = new FacturarCompra();
        FacturarCompra.jTextFieldIdProveedor.setText(jTextFieldIdProveedor.getText());
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarKeyPressed

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
            java.util.logging.Logger.getLogger(NuevoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoProveedorCompraVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldCelular;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldIdProveedor;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}