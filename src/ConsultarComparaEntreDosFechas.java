
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario1
 */
public class ConsultarComparaEntreDosFechas extends javax.swing.JFrame {
    String fechaDesde;
    String fechaHasta;
    DefaultTableModel model;     
    public ConsultarComparaEntreDosFechas() {
        initComponents();
        setResizable(false);
        setLocation(0,0);
        setTitle("Consulta de Compras en un periodo"); 
        fechaDesde = "";
        fechaHasta = "";
        getContentPane().setBackground(Color.white);
        jTextFieldHasta.setVisible(false);
        jTextFieldDesde.setVisible(false);
    }
    public void setConsultarCompras(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
       // SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
        jTextFieldDesde.setText(date.format(jDateChooserDesde.getDate()));
        fechaDesde = jTextFieldDesde.getText();
         SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
         jTextFieldHasta.setText(date2.format(jDateChooserHasta.getDate()));
         fechaHasta = jTextFieldHasta.getText();
         //Validar fecha
         int validarfecha=(date.format(jDateChooserDesde.getDate())).compareTo((date2.format(jDateChooserHasta.getDate())));
           if (validarfecha == 1){
               JOptionPane.showMessageDialog(null,"Error en Fecha, establecer un periodo válido para la búsqueda");
           }
           else{
               String [] encabezados = {"id_Producto","Ref", "Cód", "Descripción", "Cantidad"};
               String [] registros = new String[5];
               String sql = "SELECT productos.idProductos AS id, productos.ClaveProducto AS Ref, productos.CodigoBarras AS Cod, productos.DescripcionProducto AS Descripcion, SUM(detallecompras.CantidadProducto) AS Cantidad FROM detallecompras INNER JOIN productos ON detallecompras.productos_idProductos = productos.idProductos INNER JOIN compras ON detallecompras.compras_idCompras = compras.idCompra AND compras.FechaCompra BETWEEN '"+fechaDesde+"' AND '"+fechaHasta+"'GROUP BY Productos_idProductos";
               model = new DefaultTableModel(null,encabezados);
               conectar conectar1 = new conectar();
               Connection Connection1 = conectar1.conexion();
               try {   
                    Statement st = Connection1.createStatement();
                    ResultSet rs1 = st.executeQuery(sql);           
                    while(rs1.next()){
                        registros [0] = rs1.getString("id");
                        registros [1] = rs1.getString("Ref");
                        registros [2] = rs1.getString("Cod");
                        registros [3] = rs1.getString("Descripcion");
                        registros [4] = rs1.getString("Cantidad");
                    model.addRow(registros);
                }
                jTableInventario1.setModel(model);
                } catch (SQLException ex) {
                    JOptionPane.showConfirmDialog(null, ex);
                }
       }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldDesde = new javax.swing.JTextField();
        jTextFieldHasta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableInventario1 = new javax.swing.JTable();
        jButtonBuscar = new javax.swing.JButton();
        jButtonVerReporte = new javax.swing.JButton();
        jDateChooserDesde = new com.toedter.calendar.JDateChooser();
        jDateChooserHasta = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Desde");

        jLabel2.setText("Hasta");

        jTableInventario1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableInventario1);

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonVerReporte.setText("Ver Reporte");
        jButtonVerReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(139, 139, 139)
                                .addComponent(jLabel2)
                                .addGap(130, 130, 130))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jDateChooserDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooserHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)))
                        .addComponent(jButtonBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonVerReporte))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonBuscar)
                        .addComponent(jButtonVerReporte)))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        setConsultarCompras();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonVerReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerReporteActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonVerReporteActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultarComparaEntreDosFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarComparaEntreDosFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarComparaEntreDosFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarComparaEntreDosFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarComparaEntreDosFechas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonVerReporte;
    private com.toedter.calendar.JDateChooser jDateChooserDesde;
    private com.toedter.calendar.JDateChooser jDateChooserHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableInventario1;
    private javax.swing.JTextField jTextFieldDesde;
    private javax.swing.JTextField jTextFieldHasta;
    // End of variables declaration//GEN-END:variables
}