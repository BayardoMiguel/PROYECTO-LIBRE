
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author ricardo
 */
public class Proveedor {
    int IdProveedor;
    String Nombre;
    String Apellido;
    String Celular;
    String Correo;
     
    public Proveedor (){
    IdProveedor = 0;
    Nombre = "";
    Apellido = "";
    Celular = "";
    Correo = "";
            
    }
            
    public void setIdProveedor (int valorIdProveedor){
        IdProveedor = valorIdProveedor;
    }
    public void setNombre(String valorNombre){
        Nombre = valorNombre;
    }
    public void setApellido(String valorApellido){
        Apellido = valorApellido;
    }
    public void setCorreo(String valorCorreo){
        Correo = valorCorreo;
    }
    public void setCelular(String valorCelular){
        Celular = valorCelular;
    }
    public int getIdProveedor(){
        return IdProveedor;
    }
    public String getNombre(){
        return Nombre;
    }
    public String getApellido(){
        return Apellido;
    }
    public String getCorreo(){
        return Correo;
    }
    public String getCelular(){
        return Celular;
    }
    public void setInsertarProveedor(int valIdProveedor, String valNombre, String valApellido, String valCorreo, String valCelular) throws SQLException{
        conectar conectar1 = new conectar();
        Connection connection1 = conectar1.conexion();
        String sql = "";
        sql = "INSERT INTO proveedores (idProveedor, NombreProveedor, ApellidoProveedor, CorreoProveedor, CelularProveedor) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = connection1.prepareStatement(sql);
                pst.setString(1, Integer.toString(valIdProveedor));
                pst.setString(2, valNombre);
                pst.setString(3, valApellido);
                pst.setString(4, valCorreo);
                pst.setString(5, valCelular);
                int n = pst.executeUpdate();
                pst.close();
        if (n>0)
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
        }
        catch (SQLException ex) {
            Logger.getLogger(NuevoProveedorCompraVenta.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void setActualizarProveedor(int valorId, String valorNombre,String valorApellido, String valorCorreo, String valorCelular){
        conectar cn = new conectar();
        Connection con = cn.conexion(); 
       
        String sql = "UPDATE proveedores "+
                "SET NombreProveedor = ?,"+
                "ApellidoProveedor = ?, "+
                "CorreoProveedor = ?, "+
                "CelularProveedor = ? "+
                "WHERE idProveedor = ?";
              
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, valorNombre);
            pst.setString(2, valorApellido);
            pst.setString(3, valorCorreo);
            pst.setString(4, valorCelular);
            pst.setString(5, Integer.toString(valorId));
            int n = pst.executeUpdate();
            pst.close();
            if(n > 0)
            JOptionPane.showMessageDialog(null, "Actualización Exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setEliminarProveedor(int valorId){
        conectar cn = new conectar();
        Connection con = cn.conexion();
        String sql = "DELETE FROM Proveedores WHERE idProveedor = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(valorId));
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(EliminarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
