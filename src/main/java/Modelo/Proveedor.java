/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import db.dbconnection;
import java.sql.*;

/**
 *
 * @author rubye
 */
public class Proveedor {
    private String idProveedor;
    private String nombre;
    private String tipoServicio;
    private String nombreContacto;
    private String apPaternoContacto;
    private String apMaternoContacto;
    private String telefono;
    private String direccion;
    private String condicionesPago;
    private double calificacion;
    
    public Proveedor() {}

    public Proveedor(String idProveedor, String nombre, String tipoServicio,
            String telefono, String direccion, String condicionesPago, double calificacion) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.tipoServicio = tipoServicio;
        this.telefono = telefono;
        this.direccion = direccion;
        this.condicionesPago = condicionesPago;
        this.calificacion = calificacion;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getApPaternoContacto() {
        return apPaternoContacto;
    }

    public void setApPaternoContacto(String apPaternoContacto) {
        this.apPaternoContacto = apPaternoContacto;
    }

    public String getApMaternoContacto() {
        return apMaternoContacto;
    }

    public void setApMaternoContacto(String apMaternoContacto) {
        this.apMaternoContacto = apMaternoContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCondicionesPago() {
        return condicionesPago;
    }

    public void setCondicionesPago(String condicionesPago) {
        this.condicionesPago = condicionesPago;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
    
    //CRUD OPERATIONS
    public void Guardar(Proveedor p) throws SQLException{
        Connection con = dbconnection.getConexion();
        
        PreparedStatement RES = con.prepareStatement("insert into proveedores values(?,?,?,?,?,?,?,?)");
        //organizar bien por columnas y nombre de estas
        RES.setString(1, nombre);
        RES.setString(2, tipoServicio);
        RES.setString(3, nombreContacto);
        RES.setString(4, apPaternoContacto);
        RES.setString(5, apMaternoContacto);
        RES.setString(6, telefono);
        RES.setString(7, direccion);
        RES.setString(8, condicionesPago);
        RES.setDouble(9, calificacion);
        
        RES.executeUpdate(); //cuando hace cambios de actualizacion
    }
    
    public ResultSet Mostrar ( ) throws SQLException {
        Connection Con = dbconnection.getConexion();
        PreparedStatement SQL = Con.prepareStatement("SELECT * FROM proveedores");
        return SQL.executeQuery(); //manipular todo desde la vista
    }
    
    public boolean Buscar(String nombre) throws SQLException {
        Connection Con = dbconnection.getConexion();
        PreparedStatement SQL = Con.prepareStatement("SELECT * FROM proveedores WHERE nombre=?");
        SQL.setString(1, nombre); //el 1 hace referencia al signo de pregunta correspondiente
        ResultSet Res;
        Res = SQL.executeQuery(); 
        if (Res.next()){
            //colocar los resultados en pantalla
            nombre = Res.getString("nombre");
            tipoServicio = Res.getString("tipo_servicio");
            nombreContacto = Res.getString("nombre_contacto");
            apPaternoContacto = Res.getString("ap_paterno_contacto");
            apMaternoContacto = Res.getString("ap_materno_contacto");
            telefono = Res.getString("telefono");
            direccion = Res.getString("direccion");
            condicionesPago = Res.getString("condiciones_pago");
            calificacion = Double.parseDouble(Res.getString("calificacion"));
            
            return true;
        }
        else{
             return false;
        }
    }
    
    public boolean Modificar(String nombre) throws SQLException {
        Connection Con = dbconnection.getConexion();
        PreparedStatement SQL = Con.prepareStatement("SELECT * FROM proveedores WHERE nombre=?");
        SQL.setString(1, nombre); //el 1 hace referencia al signo de pregunta correspondiente
        ResultSet Res;
        Res = SQL.executeQuery(); //ejecuta el query que se esta escribiendo
        //obtener cuadros donde este escrito algo que el usuario quiera modificar 
       
        if(Res.next()){
            //Se extraera la informacion de estos contenedores
            //se hara un query a SQL donde haga un update de dichos contenedores
            PreparedStatement updateStatement = Con.prepareStatement("UPDATE proveedores SET nombre= ?, tipoServicio = ?,"
                    + " nombreContacto = ?, apPaternoContacto = ?, apMaternoContacto = ?, telefono = ?, direccion = ?, condicionesPago = ? ,"
                    + " calificacion = ?  WHERE Boleta = ?");
            updateStatement.setString(1, nombre);
            updateStatement.setString(2, tipoServicio);
            updateStatement.setString(3, nombreContacto);
            updateStatement.setString(4, apPaternoContacto);
            updateStatement.setString(5, apMaternoContacto);
            updateStatement.setString(6, telefono);
            updateStatement.setString(7, direccion);
            updateStatement.setString(8, condicionesPago);
            updateStatement.setDouble(9, calificacion);
            
            updateStatement.executeUpdate();
            
            return true;
        }else{
            return false;
        }
    }
    
    public boolean eliminarRegistro(String nombre) throws SQLException{
        Connection Con = dbconnection.getConexion();
        PreparedStatement SQL = Con.prepareStatement("SELECT * FROM proveedores WHERE nombre =?");
        SQL.setString(1, nombre); //el 1 hace referencia al signo de pregunta correspondiente
        ResultSet Res;
        Res = SQL.executeQuery(); //ejecuta el query que se esta escribiendo
        if(Res.next()){
            PreparedStatement deleteProv = Con.prepareStatement("DELETE FROM proveedores WHERE nombre = ?");
            deleteProv.setString(1, nombre);
            deleteProv.executeUpdate();
            return true;
        }else{
            return false;
        }
    }
    
}
