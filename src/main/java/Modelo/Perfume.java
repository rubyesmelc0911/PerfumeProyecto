/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import db.dbconnection;
import java.time.LocalDate;


/**
 *
 * @author rubye
 */
public class Perfume {
    private String idPerfumes;
    private String nombre;
    private String familiaOlfativa;
    private String tipoProducto;
    private String fechaCreacion;
    private String perfumista;
    private String descripcion;
    private double precioVenta;
    private double costoProduccion;
    private String estado;
    private String productoTerminadoInventarioId;
    
    public Perfume(){
        
    }

    public Perfume(String idPerfumes, String nombre, String familiaOlfativa, String tipoProducto, 
                   String fechaCreacion, String perfumista, String descripcion, double precioVenta, 
                   double costoProduccion, String estado, String productoTerminadoInventarioId) {
        this.idPerfumes = idPerfumes;
        this.nombre = nombre;
        this.familiaOlfativa = familiaOlfativa;
        this.tipoProducto = tipoProducto;
        this.fechaCreacion = fechaCreacion;
        this.perfumista = perfumista;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.costoProduccion = costoProduccion;
        this.estado = estado;
        this.productoTerminadoInventarioId = productoTerminadoInventarioId;
    }
    
    public void GuardarPerfume() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO perfumes VALUES (?,?,?,?,?,?,?,?,?,?,?)"
    );
    RES.setString(1, idPerfumes);
    RES.setString(2, nombre);
    RES.setString(3, familiaOlfativa);
    RES.setString(4, tipoProducto);
    RES.setDate(5,Date.valueOf(fechaCreacion));
    RES.setString(6, perfumista);
    RES.setString(7, descripcion);
    RES.setDouble(8, precioVenta);
    RES.setDouble(9, costoProduccion);
    RES.setString(10, estado);
    RES.setString(11, productoTerminadoInventarioId);

    RES.executeUpdate();
}

    public ResultSet MostrarPerfumes() throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement("SELECT * FROM perfumes");
    return SQL.executeQuery();
}

    
    
    public boolean BuscarPerfume(String nombre) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM perfumes WHERE nombre = ?"
    );
    SQL.setString(1, nombre);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        familiaOlfativa = Res.getString("familia_olfativa");
        tipoProducto = Res.getString("tipo_producto");
        perfumista = Res.getString("perfumista");
        descripcion = Res.getString("descripcion");
        precioVenta = Res.getDouble("precio_venta");
        costoProduccion = Res.getDouble("costo_produccion");
        estado = Res.getString("estado");
        return true;
    } else {
        return false;
    }
}

    
    public boolean ModificarPerfume(String nombre) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "UPDATE perfumes SET familia_olfativa=?, tipo_producto=?, perfumista=?, descripcion=?, precio_venta=?, costo_produccion=?, estado=? WHERE nombre=?"
    );

    SQL.setString(1, familiaOlfativa);
    SQL.setString(2, tipoProducto);
    SQL.setString(3, perfumista);
    SQL.setString(4, descripcion);
    SQL.setDouble(5, precioVenta);
    SQL.setDouble(6, costoProduccion);
    SQL.setString(7, estado);
    SQL.setString(8, nombre);

    return SQL.executeUpdate() > 0;
}

    
    public boolean EliminarPerfume(String nombre) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "DELETE FROM perfumes WHERE nombre = ?"
    );
    SQL.setString(1, nombre);
    return SQL.executeUpdate() > 0;
}

}
