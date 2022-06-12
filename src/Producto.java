

/**
 *
 * @author DavidHZ
 */
public class Producto {
    
    // ATRIBUTOS
    private String nombre;
    private double precio;
    private int cantidad;
    
    // METODO
   public Producto(String nombre,double precio,int cantidad){
       
       this.nombre = nombre; //THIS SE ESTA DIFERENCIANDO EL ATRIBUTO NOMBRE Y EL OBJETO (this.nombre)
       this.precio = precio; // Y EL PARAMETRO DEL CONSTRUCTOR LLAMANDO IGUALMENTE "nombre"
       this .cantidad = cantidad;
       
       
   }
   
   public String getNombre(){
       return nombre;
       
   }
   
   public int getCantidad(){
       return cantidad;
   }
   
   public double  getPrecio(){
       return precio;
   }
   
   public void setNombre (String nombre)
   {
       this.nombre = nombre;
   }
   
   public void setPrecio (String precio)
   {
       this.precio = precio;
       
   }
   
   public void setCantidad (String cantidad)
   {
       this.cantidad = cantidad;
   }
   
   
}
   
   
   
   
   
   
   
   
   
       
       
    
}
    
}
