
//import com.sun.glass.ui.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
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
public class Clientes {
    int IdCliente;
    String Nombre;
    String Apellido;
    String Celular;
    String Correo;
     
    public Clientes (){
    IdCliente = 0;
    Nombre = "";
    Apellido = "";
    Celular = "";
    Correo = "";
            
    }
            
    public void setIdCliente (int valorIdCLiente){
        IdCliente = valorIdCLiente;//valorIdCliente recibe dato de c.c.
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
    public int getIdCliente(){
        return IdCliente;
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
    public void setInsertarClientes(int valIdCliente, String valNombre, String valApellido, String valCorreo, String valCelular) throws SQLException{
        conectar conectar1 = new conectar();
        Connection connection1 = conectar1.conexion();
        String sql = "";
        sql = "INSERT INTO clientes (idclientes, NombreCliente, ApellidoCliente, CorreoCliente, CelularCliente) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = connection1.prepareStatement(sql);
                pst.setString(1, Integer.toString(valIdCliente));
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
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void setActualizarCliente(int valorId, String valorNombre,String valorApellido, String valorCorreo, String valorCelular){
        conectar cn = new conectar();
        Connection con = cn.conexion(); 
       
        String sql = "UPDATE clientes "+
                "SET NombreCliente = ?,"+
                "ApellidoCliente = ?, "+
                "CorreoCliente = ?, "+
                "CelularCliente = ? "+
                "WHERE idclientes = ?";
       //String sql = "UPDATE clientes SET NombreCliente=?,ApellidoCliente=?,CorreoCliente=?,CelularCliente=? WHERE idclientes=?";
        
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
            Logger.getLogger(ActualizarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setEliminarCliente(int valorId){
        conectar cn = new conectar();
        Connection con = cn.conexion();
        String sql = "DELETE FROM clientes WHERE idclientes = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Integer.toString(valorId));
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(EliminarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
