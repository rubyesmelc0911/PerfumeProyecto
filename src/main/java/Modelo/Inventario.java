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
public class Inventario {
    private String idInventario;
    private double cantidad;
    private String ubicacion;
    private String fechaActualizacion;

    public Inventario(String idInventario, double cantidad, String ubicacion, String fechaActualizacion) {
        this.idInventario = idInventario;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.fechaActualizacion = fechaActualizacion;
    }
    
    public Inventario(){
        
    }

    public String getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    // CRUD OPERATIONS
public void GuardarInventario() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO inventario VALUES (?,?,?,?)"
    );

    RES.setString(1, idInventario);
    RES.setDouble(2, cantidad);
    RES.setString(3, ubicacion);
    RES.setDate(4, Date.valueOf(fechaActualizacion));

    RES.executeUpdate();
}

public ResultSet MostrarInventario() throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM inventario"
    );
    return SQL.executeQuery();
}

public boolean ModificarInventario(String idInventario) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM inventario WHERE id_inventario = ?"
    );
    SQL.setString(1, idInventario);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement update = con.prepareStatement(
            "UPDATE inventario SET cantidad=?, ubicacion=?, fecha_actualizacion=? WHERE id_inventario=?"
        );

        update.setDouble(1, cantidad);
        update.setString(2, ubicacion);
        update.setDate(3, Date.valueOf(fechaActualizacion));
        update.setString(4, idInventario);

        update.executeUpdate();
        return true;
    } else {
        return false;
    }
}

public boolean BuscarInventario(String idInventario) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM inventario WHERE id_inventario = ?"
    );
    SQL.setString(1, idInventario);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        idInventario = Res.getString("id_inventario");
        cantidad = Res.getDouble("cantidad");
        ubicacion = Res.getString("ubicacion");
        fechaActualizacion = String.valueOf(Res.getDate("fecha_actualizacion"));
        return true;
    } else {
        return false;
    }
}

public boolean EliminarInventario(String idInventario) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM inventario WHERE id_inventario = ?"
    );
    SQL.setString(1, idInventario);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement delete = con.prepareStatement(
            "DELETE FROM inventario WHERE id_inventario = ?"
        );
        delete.setString(1, idInventario);
        delete.executeUpdate();
        return true;
    } else {
        return false;
    }
}


    
    
}
