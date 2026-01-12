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
public class Pedido {
    
    private String idPedido;
    private String fechaPedido;
    private String fechaEntrega;
    private String estado;
    private double total;
    private String clientesIdCliente;
    private String facturasIdFacturas;

    public Pedido(String idPedido, String fechaPedido, String fechaEntrega, 
                  String estado, double total, String clientesIdCliente, String facturasIdFacturas) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.total = total;
        this.clientesIdCliente = clientesIdCliente;
        this.facturasIdFacturas = facturasIdFacturas;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }


    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getClientesIdCliente() {
        return clientesIdCliente;
    }

    public void setClientesIdCliente(String clientesIdCliente) {
        this.clientesIdCliente = clientesIdCliente;
    }

    public String getFacturasIdFacturas() {
        return facturasIdFacturas;
    }

    public void setFacturasIdFacturas(String facturasIdFacturas) {
        this.facturasIdFacturas = facturasIdFacturas;
    }
    
    public ResultSet MostrarPedidos() throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement("SELECT * FROM pedidos");
    return SQL.executeQuery();
    
    
}
    
    public boolean BuscarPedido(String idPedido) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM pedidos WHERE id_Pedido = ?"
    );
    SQL.setString(1, idPedido);

    ResultSet Res = SQL.executeQuery();
    return Res.next();
}
    
    // CRUD OPERATIONS
public void GuardarPedido() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO pedidos VALUES (?,?,?,?,?,?,?)"
    );

   
    RES.setDate(1, Date.valueOf(fechaPedido));
    RES.setDate(2, Date.valueOf(fechaEntrega));
    RES.setString(3, estado);
    RES.setDouble(4, total);
    RES.setString(5, clientesIdCliente);
    RES.setString(6, facturasIdFacturas);

    RES.executeUpdate();
}

public boolean ModificarPedido(String idPedido) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM pedidos WHERE id_Pedido = ?"
    );
    SQL.setString(1, idPedido);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement update = con.prepareStatement(
            "UPDATE pedidos SET fecha_pedido=?, fecha_entrega=?, estado=?, total=?, "
          + "Clientes_id_Cliente=?, Facturas_id_Facturas=? WHERE id_Pedido=?"
        );

        update.setDate(1, Date.valueOf(fechaPedido));
        update.setDate(2, Date.valueOf(fechaEntrega));
        update.setString(3, estado);
        update.setDouble(4, total);
        update.setString(5, clientesIdCliente);
        update.setString(6, facturasIdFacturas);
        update.setString(7, idPedido);

        update.executeUpdate();
        return true;
    } else {
        return false;
    }
}

public boolean EliminarPedido(String idPedido) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM pedidos WHERE id_Pedido = ?"
    );
    SQL.setString(1, idPedido);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement delete = con.prepareStatement(
            "DELETE FROM pedidos WHERE id_Pedido = ?"
        );
        delete.setString(1, idPedido);
        delete.executeUpdate();
        return true;
    } else {
        return false;
    }
}



    
}
