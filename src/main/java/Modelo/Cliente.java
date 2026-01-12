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
public class Cliente {
    private String idCliente;
    private String rfc;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String tipo;
    private String correo;
    private String telefono;
    private String direccionEnvio;
    private String fechaRegistro;

    public Cliente(String idCliente, String rfc, String nombre, String apPaterno, 
                   String apMaterno, String tipo, String correo, String telefono, 
                   String direccionEnvio, String fechaRegistro) {
        this.idCliente = idCliente;
        this.rfc = rfc;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.tipo = tipo;
        this.correo = correo;
        this.telefono = telefono;
        this.direccionEnvio = direccionEnvio;
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
    public void GuardarCliente() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO clientes VALUES (?,?,?,?,?,?,?,?,?,?)"
    );

   RES.setString(1, idCliente);
    RES.setString(2, rfc);
    RES.setString(3, nombre);
    RES.setString(4, apPaterno);
    RES.setString(5, apMaterno);
    RES.setString(6, tipo);
    RES.setString(7, correo);
    RES.setString(8, telefono);
    RES.setString(9, direccionEnvio);
    RES.setDate(10, Date.valueOf(fechaRegistro));

    RES.executeUpdate();
}
    
    public boolean BuscarCliente(String rfc) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM clientes WHERE rfc = ?"
    );
    SQL.setString(1, rfc);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        nombre = Res.getString("nombre");
        apPaterno = Res.getString("ap_paterno");
        apMaterno = Res.getString("ap_materno");
        correo = Res.getString("correo");
        telefono = Res.getString("telefono");
        direccionEnvio = Res.getString("direccion_envio");
        return true;
    } else {
        return false;
    }
}
    
    public boolean ModificarCliente(String rfc) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "UPDATE clientes SET nombre=?, ap_paterno=?, ap_materno=?, correo=?, telefono=?, direccion_envio=? WHERE rfc=?"
    );

    SQL.setString(1, nombre);
    SQL.setString(2, apPaterno);
    SQL.setString(3, apMaterno);
    SQL.setString(4, correo);
    SQL.setString(5, telefono);
    SQL.setString(6, direccionEnvio);
    SQL.setString(7, rfc);

    return SQL.executeUpdate() > 0;
}


    
    public ResultSet MostrarClientes() throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement("SELECT * FROM clientes");
    return SQL.executeQuery();
}


    public boolean EliminarCliente(String rfc) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "DELETE FROM clientes WHERE rfc = ?"
    );
    SQL.setString(1, rfc);
    return SQL.executeUpdate() > 0;
}

}
