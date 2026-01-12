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
public class ProductoTerminado {
    private String inventarioId;

    public ProductoTerminado(String inventarioId) {
    this.inventarioId = inventarioId;
}
    
    public ProductoTerminado() {
}

    public String getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(String inventarioId) {
        this.inventarioId = inventarioId;
    }

    
    public void Guardar(ProductoTerminado p) throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO producto_terminado VALUES (?)"
    );

    RES.setString(1, inventarioId);

    RES.executeUpdate();
}

    public ResultSet Mostrar() throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM producto_terminado"
    );
    return SQL.executeQuery();
}

    public boolean Buscar(String inventarioId) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM producto_terminado WHERE Inventario_id_inventario = ?"
    );
    SQL.setString(1, inventarioId);

    ResultSet Res;
    Res = SQL.executeQuery();

    if (Res.next()) {
        inventarioId = Res.getString("Inventario_id_inventario");
        return true;
    } else {
        return false;
    }
}

public boolean Modificar(String inventarioId) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM producto_terminado WHERE Inventario_id_inventario = ?"
    );
    SQL.setString(1, inventarioId);

    ResultSet Res;
    Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement updateStatement = Con.prepareStatement(
            "UPDATE producto_terminado SET Inventario_id_inventario = ? "
          + "WHERE Inventario_id_inventario = ?"
        );

        updateStatement.setString(1, inventarioId);
        updateStatement.setString(2, inventarioId);

        updateStatement.executeUpdate();
        return true;
    } else {
        return false;
    }
}

public boolean eliminarRegistro(String inventarioId) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM producto_terminado WHERE Inventario_id_inventario = ?"
    );
    SQL.setString(1, inventarioId);

    ResultSet Res;
    Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement delete = Con.prepareStatement(
            "DELETE FROM producto_terminado WHERE Inventario_id_inventario = ?"
        );
        delete.setString(1, inventarioId);
        delete.executeUpdate();
        return true;
    } else {
        return false;
    }
}


}
