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
public class MateriaPrima {
    
    private String inventarioIdInventario;
    
    public MateriaPrima(String invIdInv){
        this.inventarioIdInventario = invIdInv;
    }
    public MateriaPrima(){
        
    }

    public String getInventarioIdInventario() {
        return inventarioIdInventario;
    }

    public void setInventarioIdInventario(String inventarioIdInventario) {
        this.inventarioIdInventario = inventarioIdInventario;
    }
    
    public void GuardarMateria() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO materia_prima VALUES (?)"
    );

    RES.setString(1, inventarioIdInventario);

    RES.executeUpdate();
}
    
    public void EliminarMateria() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "DELETE FROM materia_prima WHERE Inventario_id_inventario = ?"
    );
    RES.setString(1, inventarioIdInventario);
 

    RES.executeUpdate();
}
    
    
    public void MostrarMateria() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "SELECT * FROM materia_prima"
    );
    
    RES.executeQuery();
}
    
    
    public void BuscarMateria() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "SELECT * FROM Contiene WHERE inventarioIdInventario = ? "
    );
    RES.setString(1, inventarioIdInventario);

    RES.executeQuery();
}
    
    
}
