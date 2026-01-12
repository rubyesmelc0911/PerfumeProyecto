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
public class Factura {
    private String folio;
    private String fechaEmision;
    private double montoTotal;
    private double impuestos;
    private String metodoPago;
    private String estado;

    public Factura(String folio, String fechaEmision, double montoTotal, 
                   double impuestos, String metodoPago, String estado) {
        this.folio = folio;
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
        this.impuestos = impuestos;
        this.metodoPago = metodoPago;
        this.estado = estado;
    }

   
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void GuardarFactura() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO facturas VALUES (?,?,?,?,?,?)"
    );
    RES.setString(1, folio);
    RES.setDate(2, Date.valueOf(fechaEmision));
    RES.setDouble(3, montoTotal);
    RES.setDouble(4, impuestos);
    RES.setString(5, metodoPago);
    RES.setString(6, estado);

    RES.executeUpdate();
}

    
    public boolean BuscarFactura(String folio) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM facturas WHERE folio = ?"
    );
    SQL.setString(1, folio);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        fechaEmision = String.valueOf(Res.getDate("fecha_emision"));
        montoTotal = Res.getDouble("monto_total");
        impuestos = Res.getDouble("impuestos");
        metodoPago = Res.getString("metodo_pago");
        estado = Res.getString("estado");
        return true;
    } else {
        return false;
    }
}

   

    public boolean ModificarFactura(String folio) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "UPDATE facturas SET monto_total=?, impuestos=?, metodo_pago=?, estado=? WHERE folio=?"
    );

    SQL.setDouble(1, montoTotal);
    SQL.setDouble(2, impuestos);
    SQL.setString(3, metodoPago);
    SQL.setString(4, estado);
    SQL.setString(5, folio);

    return SQL.executeUpdate() > 0;
}

    
    
    public ResultSet MostrarFacturas() throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement("SELECT * FROM facturas");
    return SQL.executeQuery();
}
    
    public boolean EliminarFactura(String folio) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "DELETE FROM facturas WHERE folio = ?"
    );
    SQL.setString(1, folio);
    return SQL.executeUpdate() > 0;
}


}
