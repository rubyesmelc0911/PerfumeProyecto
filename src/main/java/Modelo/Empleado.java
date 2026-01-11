/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import db.dbconnection;


/**
 *
 * @author rubye
 */
public class Empleado {
 
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String puesto;
    private String correo;
    private String telefono;
    private String usuario;
    private String contrasena;
    private String activo;

    public Empleado( String nombre, String apPaterno, String apMaterno, 
                    String puesto, String correo, String telefono, String usuario, 
                    String contrasena, String activo) {
        
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.puesto = puesto;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
    // CRUD OPERATIONS
public void GuardarEmpleado() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO empleados VALUES (?,?,?,?,?,?,?,?,?)"
    );
    
    RES.setString(1, nombre);
    RES.setString(2, apPaterno);
    RES.setString(3, apMaterno);
    RES.setString(4, puesto);
    RES.setString(5, correo);
    RES.setString(6, telefono);
    RES.setString(7, usuario);
    RES.setString(8, contrasena);
    RES.setString(9, activo);

    RES.executeUpdate();
}

public boolean BuscarEmpleado(String usuario) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM empleados WHERE usuario = ?"
    );
    SQL.setString(1, usuario);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        nombre = Res.getString("nombre");
        apPaterno = Res.getString("ap_paterno");
        apMaterno = Res.getString("ap_materno");
        puesto = Res.getString("puesto");
        correo = Res.getString("correo");
        telefono = Res.getString("telefono");
        contrasena = Res.getString("contraseña");
        activo = Res.getString("activo");
        return true;
    } else {
        return false;
    }
}

public boolean ModificarEmpleado(String usuario) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "UPDATE empleados SET nombre=?, ap_paterno=?, ap_materno=?, puesto=?, correo=?, telefono=?, contraseña=?, activo=? WHERE usuario=?"
    );

    SQL.setString(1, nombre);
    SQL.setString(2, apPaterno);
    SQL.setString(3, apMaterno);
    SQL.setString(4, puesto);
    SQL.setString(5, correo);
    SQL.setString(6, telefono);
    SQL.setString(7, contrasena);
    SQL.setString(8, activo);
    SQL.setString(9, usuario);

    return SQL.executeUpdate() > 0;
}


public boolean EliminarEmpleado(String usuario) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "DELETE FROM empleados WHERE usuario = ?"
    );
    SQL.setString(1, usuario);
    return SQL.executeUpdate() > 0;
}


public ResultSet MostrarEmpleados() throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement("SELECT * FROM empleados");
    return SQL.executeQuery();
}

    
    
    
}
