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
public class Ingrediente {
    private String nombre;
    private String idIngrediente;
    private String tipo;
    private String unidadMedida;
    private int stockActual;
    private double costoUnitario;
    private String nivelReorden;
    private String proveedoresIdProveedor;
    private String materiaPrimaInventarioIdInventario;

    public Ingrediente( String idIngrediente,String nombre, String tipo, String unidadMedida, 
                        int stockActual, double costoUnitario, String nivelReorden, 
                        String proveedoresIdProveedor, String materiaPrimaInventarioIdInventario) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.tipo = tipo;
        this.unidadMedida = unidadMedida;
        this.stockActual = stockActual;
        this.costoUnitario = costoUnitario;
        this.nivelReorden = nivelReorden;
        this.proveedoresIdProveedor = proveedoresIdProveedor;
        this.materiaPrimaInventarioIdInventario = materiaPrimaInventarioIdInventario;
    }
    
    public Ingrediente(){
        
    }

    public String getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(String idIngrediente) {
        this.idIngrediente = idIngrediente;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public String getNivelReorden() {
        return nivelReorden;
    }

    public void setNivelReorden(String nivelReorden) {
        this.nivelReorden = nivelReorden;
    }

    public String getProveedoresIdProveedor() {
        return proveedoresIdProveedor;
    }

    public void setProveedoresIdProveedor(String proveedoresIdProveedor) {
        this.proveedoresIdProveedor = proveedoresIdProveedor;
    }

    public String getMateriaPrimaInventarioIdInventario() {
        return materiaPrimaInventarioIdInventario;
    }

    public void setMateriaPrimaInventarioIdInventario(String materiaPrimaInventarioIdInventario) {
        this.materiaPrimaInventarioIdInventario = materiaPrimaInventarioIdInventario;
    }
    
    // CRUD OPERATIONS
public void GuardarIngrediente() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO ingredientes VALUES (?,?,?,?,?,?,?,?,?)"
    );
    
    RES.setString(1, idIngrediente);
    RES.setString(2, nombre);
    RES.setString(3, tipo);
    RES.setString(4, unidadMedida);
    RES.setInt(5, stockActual);
    RES.setDouble(6, costoUnitario);
    RES.setString(7, nivelReorden);
    RES.setString(8, proveedoresIdProveedor);
    RES.setString(9, materiaPrimaInventarioIdInventario);

    RES.executeUpdate();
}

public boolean BuscarIngrediente(String nombre) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM ingredientes WHERE nombre = ?"
    );
    SQL.setString(1, nombre);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        this.nombre = Res.getString("nombre");
        tipo = Res.getString("tipo");
        unidadMedida = Res.getString("unidad_medida");
        stockActual = Res.getInt("stock_actual");
        costoUnitario = Res.getDouble("costo_unitario");
        nivelReorden = Res.getString("nivel_reorden");
        proveedoresIdProveedor = Res.getString("Proveedores_id_Proveedor");
        materiaPrimaInventarioIdInventario = Res.getString("Materia_Prima_Inventario_id_inventario");
        return true;
    } else {
        return false;
    }
}



public ResultSet MostrarIngredientes() throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM ingredientes"
    );
    return SQL.executeQuery();
}

public boolean ModificarIngrediente(String Nombre) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM ingredientes WHERE nombre = ?"
    );
    SQL.setString(1, Nombre);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement update = con.prepareStatement(
            "UPDATE ingredientes SET nombre=?, tipo=?, unidad_medida=?, stock_actual=?, "
          + "costo_unitario=?, nivel_reorden=?, Proveedores_id_Proveedor=?, "
          + "Materia_Prima_Inventario_id_inventario=? WHERE nombre = ?"
        );

        update.setString(1, nombre);
        update.setString(2, tipo);
        update.setString(3, unidadMedida);
        update.setInt(4, stockActual);
        update.setDouble(5, costoUnitario);
        update.setString(6, nivelReorden);
        update.setString(7, proveedoresIdProveedor);
        update.setString(8, materiaPrimaInventarioIdInventario);
        update.setString(9, Nombre);

        update.executeUpdate();
        return true;
    } else {
        return false;
    }
}

public boolean EliminarIngrediente(String nombre) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM ingredientes WHERE nombre = ?"
    );
    SQL.setString(1, nombre);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement delete = con.prepareStatement(
            "DELETE FROM ingredientes WHERE nombre = ?"
        );
        delete.setString(1, nombre);
        delete.executeUpdate();
        return true;
    } else {
        return false;
    }
}


}
