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
public class Solicita {
    private String perfumesId;
    private String pedidosId;
    private int subtotal;
    private int precioUnitario;
    private int cantidad;

        public Solicita(String perfumesId, String pedidosId, int subtotal, int precioUnitario, int cantidad) {
    this.perfumesId = perfumesId;
    this.pedidosId = pedidosId;
    this.subtotal = subtotal;
    this.precioUnitario = precioUnitario;
    this.cantidad = cantidad;
}
        public Solicita() {
}

    public String getPerfumesId() {
        return perfumesId;
    }

    public void setPerfumesId(String perfumesId) {
        this.perfumesId = perfumesId;
    }

    public String getPedidosId() {
        return pedidosId;
    }

    public void setPedidosId(String pedidosId) {
        this.pedidosId = pedidosId;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

        public void Guardar(Solicita s) throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO solicita VALUES (?,?,?,?,?)"
    );

    RES.setString(1, perfumesId);
    RES.setString(2, pedidosId);
    RES.setInt(3, subtotal);
    RES.setInt(4, precioUnitario);
    RES.setInt(5, cantidad);

    RES.executeUpdate();
}

public ResultSet Mostrar() throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM solicita"
    );
    return SQL.executeQuery();
}

public boolean Buscar(String perfumesId, String pedidosId) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM solicita WHERE Perfumes_id_Perfumes = ? AND Pedidos_id_Pedido = ?"
    );

    SQL.setString(1, perfumesId);
    SQL.setString(2, pedidosId);

    ResultSet Res;
    Res = SQL.executeQuery();

    if (Res.next()) {
        this.perfumesId = Res.getString("Perfumes_id_Perfumes");
        this.pedidosId = Res.getString("Pedidos_id_Pedido");
        subtotal = Res.getInt("subtotal");
        precioUnitario = Res.getInt("precio_unitario");
        cantidad = Res.getInt("cantidad");
        return true;
    } else {
        return false;
    }
}

public boolean Modificar(String perfumesId, String pedidosId) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM solicita WHERE Perfumes_id_Perfumes = ? AND Pedidos_id_Pedido = ?"
    );

    SQL.setString(1, perfumesId);
    SQL.setString(2, pedidosId);

    ResultSet Res;
    Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement updateStatement = Con.prepareStatement(
            "UPDATE solicita SET subtotal = ?, precio_unitario = ?, cantidad = ? "
          + "WHERE Perfumes_id_Perfumes = ? AND Pedidos_id_Pedido = ?"
        );

        updateStatement.setInt(1, subtotal);
        updateStatement.setInt(2, precioUnitario);
        updateStatement.setInt(3, cantidad);
        updateStatement.setString(4, perfumesId);
        updateStatement.setString(5, pedidosId);

        updateStatement.executeUpdate();
        return true;
    } else {
        return false;
    }
}

public boolean eliminarRegistro(String perfumesId, String pedidosId) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM solicita WHERE Perfumes_id_Perfumes = ? AND Pedidos_id_Pedido = ?"
    );

    SQL.setString(1, perfumesId);
    SQL.setString(2, pedidosId);

    ResultSet Res;
    Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement delete = Con.prepareStatement(
            "DELETE FROM solicita WHERE Perfumes_id_Perfumes = ? AND Pedidos_id_Pedido = ?"
        );
        delete.setString(1, perfumesId);
        delete.setString(2, pedidosId);
        delete.executeUpdate();
        return true;
    } else {
        return false;
    }
}


}
