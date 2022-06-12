
import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Facturar extends javax.swing.JFrame {
    public static int banderaVenta;
    public static String valor; 
    public static int banderaProveedorVenta;
    Double subTotal,Grantotal,SubTotalModificar;
    
    public Facturar() {
        initComponents();
        getContentPane().setBackground(Color.white);
        setTitle("Factura de Ventas");
        setResizable(false);
        setLocationRelativeTo(null);
        limpiar();
        bloquear();
        jTextFieldCodigoBarras.setEnabled(false);
        jButtonBuscarProducto.setEnabled(false);
        //setCargarClientes();
        setCargarFecha();
        setCargarNumeroFactura();
        jTextFieldIdCliente.requestFocus();
        subTotal = 0.0;
        Grantotal = 0.0;
        banderaVenta = 0;
        banderaProveedorVenta = 0;
    }
    /* public void setCargarClientes (){
       conectar conectar1 = new conectar();
       Connection Connection1 = conectar1.conexion();
       String sql = "SELECT * FROM clientes ORDER BY  idclientes ASC ";      
        try {
                Statement st = Connection1.createStatement();
                ResultSet rs = st.executeQuery(sql);
                jComboBoxClientes.removeAllItems();
                    while (rs.next()){
                        jComboBoxClientes.addItem((rs.getString(1))+" - "+rs.getString(2)+" "+rs.getString(3));
                    }
            } catch (SQLException ex) {
              JOptionPane.showConfirmDialog(null, ex);
            }      
     }*/
    public void debloquear(){
//jButtonNuevo.setEnabled(false);
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
    
     public void setBuscarClientes (){
       boolean encontro = false;
       int valorIdCliente = Integer.parseInt(jTextFieldIdCliente.getText());
       conectar conectar1 = new conectar();
       Connection Connection1 = conectar1.conexion();
      //String sql = "SELECT * FROM clientes WHERE idclientes like '"+valorIdCliente+"'";      
       String sql = "SELECT * FROM clientes WHERE idclientes = '"+valorIdCliente+"'";      
        try {
                Statement st = Connection1.createStatement();
                ResultSet rs = st.executeQuery(sql);
                    while (rs.next()){
                        encontro = true;
                        jTextFieldCliente.setText(rs.getString(2)+" "+rs.getString(3));
                        jTextFieldCodigoBarras.setEnabled(true);
                        jButtonBuscarProducto.setEnabled(true);
                        jTextFieldCodigoBarras.requestFocus();
                    }
                    if (encontro== false){
                          //Metodo de selección de confirmación
                    int x = JOptionPane.showConfirmDialog(null, "El Cliente con id "+valorIdCliente+" no está registrado, desea registrarlo");
                    if (x == JOptionPane.YES_OPTION){
                        NuevoClienteVenta.valor = jTextFieldIdCliente.getText();
                        NuevoClienteVenta nc1 = new NuevoClienteVenta();
                        nc1.setVisible(true);
                        dispose();
                    }
                    else if(x == JOptionPane.NO_OPTION)
                        JOptionPane.showMessageDialog(null, "No es posible facturar si no es cliente del sistema");
                        jTextFieldIdCliente.requestFocus();
        
                    }
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
      public void setCargarNumeroFactura(){
        int maxNumFactura=0;
        conectar cn = new conectar();
        Connection con = cn.conexion();
        String sql = "SELECT MAX(idFacturas) FROM facturas";
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
      

   public void setGuardar(){
       DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
       conectar conectar1 = new conectar();
       Connection connection1 = conectar1.conexion();
        String validProducto,valCantidad, valUni, valTotal;
            String sql = "";
            String sql2 = "";
            String sql3 = "";
            //---------------inicia ventas
            sql = "INSERT INTO ventas (idVentas, FechaVenta, MontoVenta,clientes_idclientes) VALUES (?,?,?,?)";
                try {
                    PreparedStatement pst = connection1.prepareStatement(sql);
                    pst.setString(1, jTextFieldNumeroFactura.getText());
                    pst.setString(2, jTextFieldFecha.getText() );
                    pst.setString(3, jTextFieldGranTotal.getText());
                    pst.setString(4, jTextFieldIdCliente.getText());
                    
                    int n = pst.executeUpdate();
                    /*if(n>0)
                    JOptionPane.showMessageDialog(null, "registro exitoso ventas");*/
                } catch (SQLException ex) {
                    Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
                }
            //------------------termina ventas
                //---------------inicia facturas
            sql = "INSERT INTO facturas (idFacturas, FechaFactura, MontoFactura,Ventas_idVentas,clientes_idclientes) VALUES (?,?,?,?,?)";
                try {
                    PreparedStatement pst = connection1.prepareStatement(sql);
                    pst.setString(1, jTextFieldNumeroFactura.getText());
                    pst.setString(2, jTextFieldFecha.getText() );
                    pst.setString(3, jTextFieldGranTotal.getText());
                    pst.setString(4, jTextFieldNumeroFactura.getText());
                     pst.setString(5, jTextFieldIdCliente.getText());
                    
                    int n = pst.executeUpdate();
                   /* if(n>0)
                    JOptionPane.showMessageDialog(null, "registro exitoso Facturas");*/
                } catch (SQLException ex) {
                    Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
                }
            //------------------termina facturas
            //-------------------inicio detalleventas
            int col = jTableVentas.getRowCount();
            for(int i=0; i<col; i++){
                    
                    validProducto = model.getValueAt( i, 1).toString();
                    valCantidad = model.getValueAt(i , 4).toString();
                    valUni = model.getValueAt(i , 5).toString();
                    valTotal = model.getValueAt(i , 6).toString();
                sql = "INSERT INTO detalleventas (Ventas_idVentas,Productos_idProductos,CantidadProducto,ValorUnitario, subTotal) VALUES (?,?,?,?,?)";
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
                    Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
                 
         //..............
         int numfilas = jTableVentas.getRowCount();
         if(numfilas>=1){
            for(int i= numfilas-1; i>=0; i--){
                 model.removeRow(i);
             }        
          }
        Grantotal = 0.0;
        jTextFieldGranTotal.setText("");
        setCargarNumeroFactura();
        jTextFieldIdCliente.setText("");
        jTextFieldCliente.setText("");
        bloquear();
        JOptionPane.showMessageDialog(null,"Registros guardados con éxito");
        jTextFieldIdCliente.requestFocus();
        jTextFieldCodigoBarras.setEnabled(false);
   }
   public void adicionar(){
        DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
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
   public void eliminarTodo(){
   DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
      int numfilas = jTableVentas.getRowCount();
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVentas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldDescripcionProducto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCodigoBarras = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jTextFieldIdProducto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldValorUnitario = new javax.swing.JTextField();
        jTextFieldClaveProducto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldTotalVenta = new javax.swing.JTextField();
        jButtonBuscarProducto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldIdCliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldGranTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNumeroFactura = new javax.swing.JTextField();
        jTextFieldFecha = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonEliminarArticulo = new javax.swing.JButton();
        jButtonEliminarTodos = new javax.swing.JButton();
        jButtonRegresar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuRegresar = new javax.swing.JMenu();
        jMenuSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1367, 740));
        setMinimumSize(new java.awt.Dimension(1367, 740));
        setPreferredSize(new java.awt.Dimension(1367, 740));
        getContentPane().setLayout(null);

        jTableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod", "Id Producto", "Descripción", "Clave", "Cantidad", "Valor/Unitario", "ValorTotal"
            }
        ));
        jTableVentas.setGridColor(new java.awt.Color(255, 255, 255));
        jTableVentas.setOpaque(false);
        jTableVentas.setSelectionBackground(new java.awt.Color(255, 153, 153));
        jTableVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableVentas);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(190, 250, 913, 325);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jLabel6.setText("Total");

        jLabel1.setText("Descripción");

        jTextFieldDescripcionProducto.setEditable(false);

        jLabel3.setText("Cod Barras");

        jTextFieldCodigoBarras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoBarrasFocusLost(evt);
            }
        });

        jLabel10.setText("Cantidad");

        jTextFieldIdProducto.setEditable(false);

        jLabel8.setText("idProducto");

        jTextFieldValorUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldValorUnitarioFocusLost(evt);
            }
        });

        jTextFieldClaveProducto.setEditable(false);

        jLabel4.setText("Valor/unitario");

        jLabel5.setText("Clave Producto");

        jTextFieldTotalVenta.setEditable(false);
        jTextFieldTotalVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTotalVentaFocusGained(evt);
            }
        });

        jButtonBuscarProducto.setText("Buscar");
        jButtonBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonBuscarProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel8))
                    .addComponent(jTextFieldIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(97, 97, 97)
                        .addComponent(jLabel5)
                        .addGap(29, 29, 29)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldClaveProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscarProducto))
                .addGap(12, 12, 12))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(190, 170, 913, 68);

        jPanel2.setBackground(new java.awt.Color(168, 37, 104));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(168, 37, 104)));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nombre");

        jTextFieldCliente.setEditable(false);
        jTextFieldCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldClienteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Id Cliente");

        jTextFieldIdCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIdClienteFocusLost(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TOTAL");

        jTextFieldGranTotal.setEditable(false);
        jTextFieldGranTotal.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldGranTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldGranTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Factrua de venta N°");

        jTextFieldNumeroFactura.setEditable(false);
        jTextFieldNumeroFactura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldFecha.setEditable(false);
        jTextFieldFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fecha");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jTextFieldGranTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addGap(41, 41, 41)
                        .addComponent(jTextFieldNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFieldIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jTextFieldFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                            .addComponent(jTextFieldNumeroFactura))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldGranTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addGap(0, 25, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(190, 20, 1096, 130);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        jButtonAdicionar.setBackground(new java.awt.Color(204, 0, 0));
        jButtonAdicionar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdicionar.setText("+");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });
        jButtonAdicionar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonAdicionarKeyPressed(evt);
            }
        });

        jButtonGuardar.setBackground(new java.awt.Color(168, 37, 104));
        jButtonGuardar.setForeground(new java.awt.Color(255, 255, 255));
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
        jButtonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAdicionar)
                .addGap(49, 49, 49)
                .addComponent(jButtonGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEliminarArticulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEliminarTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRegresar)
                .addContainerGap(189, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(1110, 170, 174, 401);

        jPanel4.setBackground(new java.awt.Color(168, 37, 104));
        jPanel4.setLayout(null);

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Gastos");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel14);
        jLabel14.setBounds(30, 260, 70, 20);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Consultas");
        jPanel4.add(jLabel15);
        jLabel15.setBounds(20, 300, 100, 20);

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Clientes");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel16);
        jLabel16.setBounds(30, 200, 50, 20);

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Productos");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel17);
        jLabel17.setBounds(30, 220, 70, 20);

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Proveedores");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel18);
        jLabel18.setBounds(30, 240, 70, 20);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Gestionar");
        jPanel4.add(jLabel19);
        jLabel19.setBounds(20, 180, 80, 20);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Facturación");
        jPanel4.add(jLabel20);
        jLabel20.setBounds(20, 100, 110, 20);

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Factura de compras");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel21);
        jLabel21.setBounds(30, 320, 110, 20);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Compras por producto");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel22);
        jLabel22.setBounds(30, 340, 130, 20);

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Factura de Ventas");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel23);
        jLabel23.setBounds(30, 360, 120, 20);

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Inventario de Productos");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel24);
        jLabel24.setBounds(30, 420, 130, 20);

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Ventas por Producto");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel25);
        jLabel25.setBounds(30, 380, 130, 20);

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Compras");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel26);
        jLabel26.setBounds(30, 120, 100, 20);

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Ventas");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel27);
        jLabel27.setBounds(30, 140, 100, 20);

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Valor del Inventario");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel28);
        jLabel28.setBounds(30, 400, 130, 20);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 170, 690);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Lateral 1.jpeg"))); // NOI18N
        getContentPane().add(jLabel13);
        jLabel13.setBounds(120, 0, 1360, 700);

        jMenuRegresar.setText("Regresar");
        jMenuRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuRegresarMousePressed(evt);
            }
        });
        jMenuBar1.add(jMenuRegresar);

        jMenuSalir.setText("Salir");
        jMenuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuSalirMousePressed(evt);
            }
        });
        jMenuBar1.add(jMenuSalir);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegresarActionPerformed
        // TODO add your handling code here:
        Inicio inicio1 = new Inicio();
        inicio1.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonRegresarActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        // TODO add your handling code here:
        adicionar();
        limpiar();
        bloquear();
        jTextFieldCodigoBarras.requestFocus();
        
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonAdicionarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonAdicionarKeyPressed
        // TODO add your handling code here:
        adicionar();
        limpiar();
        bloquear();
        jTextFieldCodigoBarras.requestFocus();
    }//GEN-LAST:event_jButtonAdicionarKeyPressed

    private void jTableVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVentasMouseClicked
        // TODO add your handling code here:
           //debloquear();
           jButtonAdicionar.setEnabled(false);
           DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();   
//           jTextFieldCodigoBarras.setText(model.getValueAt(jTableVentas.getSelectedRow(), 0).toString());
//           jTextFieldIdProducto.setText(model.getValueAt(jTableVentas.getSelectedRow(), 1).toString());
//           jTextFieldDescripcionProducto.setText(model.getValueAt(jTableVentas.getSelectedRow(), 2).toString());
//           jTextFieldClaveProducto.setText(model.getValueAt(jTableVentas.getSelectedRow(), 3).toString());
//           jTextFieldCantidad.setText(model.getValueAt(jTableVentas.getSelectedRow(), 4).toString());
//           jTextFieldValorUnitario.setText(model.getValueAt(jTableVentas.getSelectedRow(), 5).toString());
//           jTextFieldTotalVenta.setText(model.getValueAt(jTableVentas.getSelectedRow(), 6).toString());
           SubTotalModificar = 0.0;
           //SubTotalModificar = Double.parseDouble(jTextFieldTotalVenta.getText());
           SubTotalModificar = Double.parseDouble(model.getValueAt(jTableVentas.getSelectedRow(), 6).toString());
    }//GEN-LAST:event_jTableVentasMouseClicked

    private void jButtonEliminarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarArticuloActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
        if (jTableVentas.getSelectedRow()==-1){
            if (jTableVentas.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "Ingrese productos a la factura");
            }else{
                 JOptionPane.showMessageDialog(null, "Seleccione un artículo para eliminar de la tabla");
            }
        }else{
            //Actualizar la caja de texto GranTotal
            //Grantotal = Grantotal - (Double.parseDouble(jTextFieldTotalVenta.getText()));
            Grantotal = Grantotal - Double.parseDouble(model.getValueAt(jTableVentas.getSelectedRow(), 6).toString());
            jTextFieldGranTotal.setText(Double.toString(Grantotal));
            model.removeRow(jTableVentas.getSelectedRow());
        }
        bloquear();
        limpiar();        jTextFieldCodigoBarras.requestFocus();
    }//GEN-LAST:event_jButtonEliminarArticuloActionPerformed

    private void jButtonEliminarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarTodosActionPerformed

        // TODO add your handling code here:
        
        eliminarTodo();
        jTextFieldCodigoBarras.requestFocus();
        
    }//GEN-LAST:event_jButtonEliminarTodosActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
        if (jTableVentas.getRowCount()==0) {
            JOptionPane.showMessageDialog(null,"Ingrese productos a la factura");
            jTextFieldCodigoBarras.requestFocus();
        } else {
             setGuardar();
        }
       
        
        
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jTextFieldCodigoBarrasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBarrasFocusLost
        // TODO add your handling code here:
        if (jTextFieldCodigoBarras.getText().equals("")) {
            
        } else {
        boolean igual = false;
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
                JOptionPane.showMessageDialog(null,"¿Producto no existe?");
                
                 limpiar();
                 bloquear();
                 jTextFieldCodigoBarras.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
        
    }//GEN-LAST:event_jTextFieldCodigoBarrasFocusLost

    private void jTextFieldTotalVentaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTotalVentaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalVentaFocusGained

    private void jTextFieldValorUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldValorUnitarioFocusLost
        // TODO add your handling code here:
        double cantidad = Double.parseDouble(jTextFieldCantidad.getText());
        double valorUnitario = Double.parseDouble(jTextFieldValorUnitario.getText());
        double total = cantidad*valorUnitario;
        jButtonAdicionar.requestFocus();
        jTextFieldTotalVenta.setText(Double.toString(total));
    }//GEN-LAST:event_jTextFieldValorUnitarioFocusLost

    private void jTextFieldIdClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIdClienteFocusLost
        // TODO add your handling code here:
        if (jTextFieldIdCliente.getText().equals("")){
            jTextFieldIdCliente.requestFocus();
        }else{
            setBuscarClientes();
        }
        
               
    }//GEN-LAST:event_jTextFieldIdClienteFocusLost

    private void jMenuSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSalirMousePressed
        // TODO add your handling code here:
         int x = JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?");
                    if (x == JOptionPane.YES_OPTION){
                        System.exit(0);
                    }
//                    else if(x == JOptionPane.NO_OPTION){
//                    }
    }//GEN-LAST:event_jMenuSalirMousePressed

    private void jMenuRegresarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuRegresarMousePressed
        // TODO add your handling code here:
        Inicio ini = new Inicio();
        ini.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuRegresarMousePressed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        GestionarGastos obj = new GestionarGastos();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        GestionarClientes obj = new GestionarClientes();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        GestionarProducto obj = new GestionarProducto();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        GestionarProveedor obj = new GestionarProveedor();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        ConsultarCompras obj = new ConsultarCompras();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        ConsultaComprasEntreDosFechas obj = new ConsultaComprasEntreDosFechas();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        ConsultarVentas obj = new ConsultarVentas();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        ConsultaInventario2 obj = new ConsultaInventario2();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        ConsultaVentasEntreDosFechas obj = new ConsultaVentasEntreDosFechas();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel25MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        FacturarCompra obj = new FacturarCompra();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        Facturar obj = new Facturar();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
         ConsultaValorInventario obj = new ConsultaValorInventario();
        obj.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jButtonBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarProductoActionPerformed
        banderaVenta = 1;
        ConsultarProductoCompraVenta obj = new ConsultarProductoCompraVenta();
        obj.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBuscarProductoActionPerformed

    private void jTextFieldClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldClienteActionPerformed

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
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturar().setVisible(true);
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
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuRegresar;
    private javax.swing.JMenu jMenuSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableVentas;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldClaveProducto;
    private javax.swing.JTextField jTextFieldCliente;
    public static javax.swing.JTextField jTextFieldCodigoBarras;
    private javax.swing.JTextField jTextFieldDescripcionProducto;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldGranTotal;
    public static javax.swing.JTextField jTextFieldIdCliente;
    private javax.swing.JTextField jTextFieldIdProducto;
    private javax.swing.JTextField jTextFieldNumeroFactura;
    private javax.swing.JTextField jTextFieldTotalVenta;
    private javax.swing.JTextField jTextFieldValorUnitario;
    // End of variables declaration//GEN-END:variables
}
