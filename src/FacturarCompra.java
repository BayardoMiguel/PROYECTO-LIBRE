
/**
 *
 * @author DavidHZ
 */





import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FacturarCompra extends javax.swing.JFrame {

    public static int banderaCompra;
    public static int banderaProveedorCompra;
    
    
    Double subTotal,Grantotal,SubTotalModificar;
    
    
    public FacturarCompra() {
        initComponents();
        setCargarFecha();
        setCargarNumeroFactura();
        limpiar();
        bloquear();
        jTextFieldCodigoBarras.setEnabled(false);
        jButtonBuscarProducto.setEnabled(false);
        jTextFieldIdProveedor.requestFocus();
        subTotal = 0.0;
        Grantotal = 0.0;
        banderaCompra = 0;
        banderaProveedorCompra = 0;
        
      
    }
    
    
    public void eliminarTodo(){
DefaultTableModel model = (DefaultTableModel) jTableCompras.getModel();
int numfilas = jTableCompras.getRowCount();
if(numfilas>=1){
for(int i= numfilas-1; i>=0; i--){
model.removeRow(i);
}
Grantotal = 0.0;
jTextFieldGranTotal.setText(Double.toString(Grantotal));
}else{
JOptionPane.showMessageDialog(null,"No hay productos en la factura");
}
bloquear();
}
    public void adicionar(){
    DefaultTableModel model = (DefaultTableModel) jTableCompras.getModel();
if(!jTextFieldDescripcionProducto.getText().equals("")){
model.addRow(new Object[]{jTextFieldCodigoBarras.getText(),
jTextFieldIdProducto.getText(),jTextFieldDescripcionProducto.getText(),
jTextFieldClaveProducto.getText(),jTextFieldCantidad.getText(), jTextFieldValorUnitario.getText(),
jTextFieldTotalVenta.getText()
} );
subTotal = Double.parseDouble(jTextFieldTotalVenta.getText());
Grantotal = Grantotal+subTotal;
jTextFieldGranTotal.setText(Double.toString(Grantotal));
}else{
JOptionPane.showMessageDialog(null,"Seleccione Productos para adicionarlos a la factura");
}

    }
    
    public void setGuardar(){
DefaultTableModel model = (DefaultTableModel) jTableCompras.getModel();
conectar conectar1 = new conectar();
Connection connection1 = conectar1.conexion();
String validProducto,valCantidad, valUni, valTotal;
String sql = "";
String sql2 = "";
String sql3 = "";
//---------------inicia ventas
sql = "INSERT INTO compras (idCompra, FechaCompra, MontoCompra,proveedores_idproveedores) VALUES (?,?,?,?)";
try {
    PreparedStatement pst = connection1.prepareStatement(sql);
    pst.setString(1, jTextFieldNumeroFactura.getText());
    pst.setString(2, jTextFieldFecha.getText() );
    pst.setString(3, jTextFieldGranTotal.getText());
    pst.setString(4, jTextFieldIdProveedor.getText());
int n = pst.executeUpdate();
/*if(n>0)
JOptionPane.showMessageDialog(null, "registro exitoso ventas");*/
} catch (SQLException ex) {
Logger.getLogger(FacturarCompra.class.getName()).log(Level.SEVERE, null, ex);
}
//------------------termina ventas
//---------------inicia facturas
sql = "INSERT INTO facturascompra (idFacturasC, FechaFacturaC, MontoFacturaC,Compras_idCompra,proveedores_idProveedor) VALUES (?,?,?,?,?)";
try {
PreparedStatement pst = connection1.prepareStatement(sql);
pst.setString(1, jTextFieldNumeroFactura.getText());
pst.setString(2, jTextFieldFecha.getText() );
pst.setString(3, jTextFieldGranTotal.getText());
pst.setString(4, jTextFieldNumeroFactura.getText());
pst.setString(5, jTextFieldIdProveedor.getText());
int n = pst.executeUpdate();
/* if(n>0)
JOptionPane.showMessageDialog(null, "registro exitoso Facturas");*/
} catch (SQLException ex) {
Logger.getLogger(FacturarCompra.class.getName()).log(Level.SEVERE, null, ex);
}
//------------------termina facturas
//-------------------inicio detalleCompra
int col = jTableCompras.getRowCount();
for(int i=0; i<col; i++){
validProducto = model.getValueAt( i, 1).toString();
valCantidad = model.getValueAt(i , 4).toString();
valUni = model.getValueAt(i , 5).toString();
valTotal = model.getValueAt(i , 6).toString();
sql = "INSERT INTO detallecompras (compras_idCompras,productos_idProductos,CantidadProducto,ValorUnitario, subtotal) VALUES (?,?,?,?,?)";
try {
PreparedStatement pst = connection1.prepareStatement(sql);
pst.setString(1, jTextFieldNumeroFactura.getText());
pst.setString(2, validProducto);
pst.setString(3, valCantidad);
pst.setString(4, valUni);
pst.setString(5, valTotal);
int n = pst.executeUpdate();
/* if(n>0)
JOptionPane.showMessageDialog(null, "registro exitoso detalle");*/
} catch (SQLException ex) {
Logger.getLogger(FacturarCompra.class.getName()).log(Level.SEVERE, null, ex);
}
}
//..............
int numfilas = jTableCompras.getRowCount();
if(numfilas>=1){
for(int i= numfilas-1; i>=0; i--){
model.removeRow(i);
}
}
Grantotal = 0.0;
jTextFieldGranTotal.setText("");
setCargarNumeroFactura();
jTextFieldIdProveedor.setText("");
jTextFieldProveedor.setText("");
bloquear();
JOptionPane.showMessageDialog(null,"Registros guardados con éxito");
jTextFieldIdProveedor.requestFocus();
jTextFieldCodigoBarras.setEnabled(false);
}
    public void debloquear(){
// jButtonBuscarProducto.setEnabled(true);
jTextFieldCodigoBarras.setEnabled(true);
jTextFieldIdProducto.setEnabled(true);
jTextFieldDescripcionProducto.setEnabled(true);
jTextFieldValorUnitario.setEnabled(true);
jTextFieldClaveProducto.setEnabled(true);
jTextFieldCantidad.setEnabled(true);
jTextFieldTotalVenta.setEnabled(true);
jButtonAdicionar.setEnabled(true);

}
public void bloquear(){
// jButtonBuscarProducto.setEnabled(false);
jTextFieldCodigoBarras.setEnabled(true);
jTextFieldIdProducto.setEnabled(false);
jTextFieldDescripcionProducto.setEnabled(false);
jTextFieldValorUnitario.setEnabled(false);
jTextFieldClaveProducto.setEnabled(false);
jTextFieldCantidad.setEnabled(false);
jTextFieldTotalVenta.setEnabled(false);
jButtonAdicionar.setEnabled(false);
}
public void limpiar(){
//jButtonNuevo.setEnabled(true);
jTextFieldCodigoBarras.setText("");
jTextFieldIdProducto.setText("");
jTextFieldDescripcionProducto.setText("");
jTextFieldValorUnitario.setText("");
jTextFieldCantidad.setText("");
jTextFieldClaveProducto.setText("");
jTextFieldTotalVenta.setText("");
}
public void setCargarNumeroFactura(){
int maxNumFactura=0;
conectar cn = new conectar();
Connection con = cn.conexion();
String sql = "SELECT MAX(idFacturasC) FROM facturascompra";
try {
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(sql);
while(rs.next()){
if(rs.getString(1)!= null)
maxNumFactura = Integer.valueOf(rs.getString(1).toString());
else
maxNumFactura = 0;
}
maxNumFactura = maxNumFactura+1;
jTextFieldNumeroFactura.setText(String.valueOf(maxNumFactura));
} catch (SQLException ex) {
JOptionPane.showConfirmDialog(null, ex);
}
}
    public void setCargarFecha(){
Date now = new Date(System.currentTimeMillis());
SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
// SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
jTextFieldFecha.setText(date.format(now));
//System.out.println(hour.format(now));
//System.out.println(now);
}
    
    public void setBuscarProveedores (){
boolean encontro = false;
int valorIdProveedor = Integer.parseInt(jTextFieldIdProveedor.getText());
conectar conectar1 = new conectar();
Connection Connection1 = conectar1.conexion();
String sql = "SELECT * FROM proveedores WHERE idProveedor = '"+valorIdProveedor+"'";
try {
Statement st = Connection1.createStatement();
ResultSet rs = st.executeQuery(sql);
while (rs.next()){
encontro = true;
jTextFieldProveedor.setText(rs.getString(2)+" "+rs.getString(3));
jTextFieldCodigoBarras.setEnabled(true);
jButtonBuscarProducto.setEnabled(true);
jTextFieldCodigoBarras.requestFocus();
}
if (encontro== false){
//Metodo de selección de confirmación
int x = JOptionPane.showConfirmDialog(null, "El proveedor con id "+ valorIdProveedor+" no está registrado, desea registrarlo");
if (x == JOptionPane.YES_OPTION){
banderaProveedorCompra = 1;
NuevoProveedorCompraVenta.valor = jTextFieldIdProveedor.getText();
NuevoProveedorCompraVenta nc1 = new NuevoProveedorCompraVenta();
nc1.setVisible(true);
dispose();
}
else if(x == JOptionPane.NO_OPTION)
JOptionPane.showMessageDialog(null, "No es posible facturar si no es proveedor del sistema");
jTextFieldIdProveedor.requestFocus();
}
} catch (SQLException ex) {
JOptionPane.showConfirmDialog(null, ex);
}
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonEliminarArticulo = new javax.swing.JButton();
        jButtonEliminarTodos = new javax.swing.JButton();
        jButtonRegresar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCodigoBarras = new javax.swing.JTextField();
        jTextFieldIdProducto = new javax.swing.JTextField();
        jTextFieldValorUnitario = new javax.swing.JTextField();
        jTextFieldTotalVenta = new javax.swing.JTextField();
        jTextFieldCantidad = new javax.swing.JTextField();
        jTextFieldClaveProducto = new javax.swing.JTextField();
        jTextFieldDescripcionProducto = new javax.swing.JTextField();
        jButtonBuscarProducto = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldIdProveedor = new javax.swing.JTextField();
        jTextFieldProveedor = new javax.swing.JTextField();
        jTextFieldFecha = new javax.swing.JTextField();
        jTextFieldGranTotal = new javax.swing.JTextField();
        jTextFieldNumeroFactura = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCompras = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jButtonAdicionar.setText("+");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonEliminarArticulo.setText("Eliminar Articulo");
        jButtonEliminarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarArticuloActionPerformed(evt);
            }
        });

        jButtonEliminarTodos.setText("Eliminar Todos");
        jButtonEliminarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarTodosActionPerformed(evt);
            }
        });

        jButtonRegresar.setText("Regresar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonEliminarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonEliminarArticulo))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButtonAdicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEliminarArticulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEliminarTodos)
                .addGap(18, 18, 18)
                .addComponent(jButtonRegresar)
                .addGap(85, 85, 85))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel6.setText("Cod Barras");

        jLabel7.setText("Total");

        jLabel8.setText("id Producto");

        jLabel9.setText("Descripcion");

        jLabel10.setText("Cantidad");

        jLabel11.setText("Valor/Unitario");

        jLabel12.setText("Clave Producto");

        jTextFieldCodigoBarras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoBarrasFocusLost(evt);
            }
        });
        jTextFieldCodigoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoBarrasActionPerformed(evt);
            }
        });

        jTextFieldValorUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldValorUnitarioFocusLost(evt);
            }
        });

        jButtonBuscarProducto.setText("Buscar");
        jButtonBuscarProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButtonBuscarProductoFocusLost(evt);
            }
        });
        jButtonBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel6)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonBuscarProducto)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextFieldDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7))))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonBuscarProducto)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setText("id Proveedor");

        jLabel2.setText("Nombre");

        jLabel3.setText("Fecha");

        jLabel4.setText("Total");

        jLabel5.setText("FActura de compraN");

        jTextFieldIdProveedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIdProveedorFocusLost(evt);
            }
        });
        jTextFieldIdProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdProveedorActionPerformed(evt);
            }
        });

        jTextFieldProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProveedorActionPerformed(evt);
            }
        });

        jTextFieldFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFechaActionPerformed(evt);
            }
        });

        jTextFieldGranTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGranTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldProveedor)
                    .addComponent(jTextFieldIdProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)))
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldGranTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldGranTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84))
        );

        jTableCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "id Producto ", "Descripciòn", "Clave PRoducto", "Cantidad", "Valor/Unitario", "Total"
            }
        ));
        jTableCompras.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jTableComprasMouseWheelMoved(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCompras);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/img2.png"))); // NOI18N
        jLabel13.setText("jLabel13");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 19, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(641, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIdProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdProveedorActionPerformed

    private void jTextFieldCodigoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoBarrasActionPerformed

    private void jTextFieldIdProveedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIdProveedorFocusLost
        setBuscarProveedores();
        if (jTextFieldProveedor.getText().equals("")){
            jTextFieldIdProveedor.requestFocus();
        }else {
            setBuscarProveedores();
        }
        
        
        
        
    }//GEN-LAST:event_jTextFieldIdProveedorFocusLost

    private void jButtonBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarProductoActionPerformed
banderaCompra = 1;
ConsultarProductoCompraVenta obj = new ConsultarProductoCompraVenta();
obj.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBuscarProductoActionPerformed

    private void jTableComprasMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jTableComprasMouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableComprasMouseWheelMoved

    private void jButtonBuscarProductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonBuscarProductoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBuscarProductoFocusLost

    private void jTextFieldCodigoBarrasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBarrasFocusLost
     if (jTextFieldCodigoBarras.getText().equals("")) {
} else {
boolean igual = false;//El producto no existe
String registro [] = new String[1];
String valorCodigoBarras = jTextFieldCodigoBarras.getText();
String sql = "SELECT * FROM productos WHERE CodigoBarras like '"+valorCodigoBarras+"'";
conectar conectar1 = new conectar();
Connection cn = conectar1.conexion();
try {
Statement st = cn.createStatement();
ResultSet rs = st.executeQuery(sql);
while (rs.next()){
igual = true;
registro[0]= rs.getString("CodigoBarras");
jTextFieldIdProducto.setText(rs.getString("idProductos"));
jTextFieldDescripcionProducto.setText(rs.getString("DescripcionProducto"));
jTextFieldClaveProducto.setText(rs.getString("ClaveProducto"));
jTextFieldCantidad.requestFocus();
debloquear();
}
if (igual == false){
//JOptionPane.showMessageDialog(null,"¿Producto no existe desea registrarlo en el inventario?");
int x = JOptionPane.showConfirmDialog(null, "El producto no existe, desea registrarlo");
if (x == JOptionPane.YES_OPTION){
NuevoProductoComprar obj = new NuevoProductoComprar();
obj.setVisible(true);
}
else if(x == JOptionPane.NO_OPTION){
JOptionPane.showMessageDialog(null, "No es posible facturar si el producto no está en el sistema");
limpiar();
bloquear();
jTextFieldCodigoBarras.requestFocus();
}
}
} catch (SQLException ex) {
Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
}
}
    }//GEN-LAST:event_jTextFieldCodigoBarrasFocusLost

    private void jTextFieldValorUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldValorUnitarioFocusLost
double cantidad = Double.parseDouble(jTextFieldCantidad.getText());
double valorUnitario = Double.parseDouble(jTextFieldValorUnitario.getText());
double total = cantidad*valorUnitario;
jButtonAdicionar.requestFocus();
jTextFieldTotalVenta.setText(Double.toString(total));        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValorUnitarioFocusLost

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
adicionar();
limpiar();
bloquear();
jTextFieldCodigoBarras.requestFocus();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonEliminarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarArticuloActionPerformed

DefaultTableModel model = (DefaultTableModel) jTableCompras.getModel();
if (jTableCompras.getSelectedRow()==-1){
if (jTableCompras.getRowCount()==0){
JOptionPane.showMessageDialog(null, "Ingrese productos a la factura");
}else{
JOptionPane.showMessageDialog(null, "Seleccione un artículo para eliminar de la tabla");
}
}else{
//Actualizar la caja de texto GranTotal
//Grantotal = Grantotal - (Double.parseDouble(jTextFieldTotalVenta.getText()));
Grantotal = Grantotal - Double.parseDouble(model.getValueAt(jTableCompras.getSelectedRow(), 6).toString());
jTextFieldGranTotal.setText(Double.toString(Grantotal));
model.removeRow(jTableCompras.getSelectedRow());
}
bloquear();
limpiar();
jTextFieldCodigoBarras.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEliminarArticuloActionPerformed

    private void jButtonEliminarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarTodosActionPerformed

    eliminarTodo();
    jTextFieldCodigoBarras.requestFocus();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonEliminarTodosActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
if (jTableCompras.getRowCount()==0) {
    JOptionPane.showMessageDialog(null,"Ingrese productos a la factura");
    jTextFieldCodigoBarras.requestFocus();
    } else {
    setGuardar();
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jTextFieldProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldProveedorActionPerformed

    private void jTextFieldFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFechaActionPerformed

    private void jTextFieldGranTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGranTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGranTotalActionPerformed

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
            java.util.logging.Logger.getLogger(FacturarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturarCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonBuscarProducto;
    private javax.swing.JButton jButtonEliminarArticulo;
    private javax.swing.JButton jButtonEliminarTodos;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCompras;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldClaveProducto;
    public static javax.swing.JTextField jTextFieldCodigoBarras;
    private javax.swing.JTextField jTextFieldDescripcionProducto;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldGranTotal;
    private javax.swing.JTextField jTextFieldIdProducto;
    public static javax.swing.JTextField jTextFieldIdProveedor;
    private javax.swing.JTextField jTextFieldNumeroFactura;
    private javax.swing.JTextField jTextFieldProveedor;
    private javax.swing.JTextField jTextFieldTotalVenta;
    private javax.swing.JTextField jTextFieldValorUnitario;
    // End of variables declaration//GEN-END:variables
}
