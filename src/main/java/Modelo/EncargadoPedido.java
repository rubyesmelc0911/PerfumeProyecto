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
public class EncargadoPedido {
    private String Empleados_id_Empleado  ;

    public EncargadoPedido(String Empleados_id_Empleado ) {
    this.Empleados_id_Empleado  = Empleados_id_Empleado ;
}
    
    public EncargadoPedido() {
}

    public String getEmpleados_id_Empleado() {
        return Empleados_id_Empleado;
    }

    public void setEmpleados_id_Empleado(String Empleados_id_Empleado) {
        this.Empleados_id_Empleado = Empleados_id_Empleado;
    }
    
    public void Guardar(EncargadoPedido e) throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO encargadopedido VALUES (?)"
    );

    RES.setString(1, Empleados_id_Empleado);

    RES.executeUpdate();
}
    
    public ResultSet Mostrar() throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM encargadopedido"
    );
    return SQL.executeQuery();
}

public boolean Buscar(String empleadosId) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM encargadopedido WHERE Empleados_id_Empleado = ?"
    );

    SQL.setString(1, empleadosId);

    ResultSet Res;
    Res = SQL.executeQuery();

    if (Res.next()) {
        this.Empleados_id_Empleado = Res.getString("Empleados_id_Empleado");
        return true;
    } else {
        return false;
    }
}

public boolean Modificar(String empleadosId) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM encargadopedido WHERE Empleados_id_Empleado = ?"
    );

    SQL.setString(1, empleadosId);

    ResultSet Res;
    Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement updateStatement = Con.prepareStatement(
            "UPDATE encargadopedido SET Empleados_id_Empleado = ? "
          + "WHERE Empleados_id_Empleado = ?"
        );

        updateStatement.setString(1, empleadosId);
        updateStatement.setString(2, empleadosId);

        updateStatement.executeUpdate();
        return true;
    } else {
        return false;
    }
}

public boolean eliminarRegistro(String empleadosId) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM encargadopedido WHERE Empleados_id_Empleado = ?"
    );

    SQL.setString(1, empleadosId);

    ResultSet Res;
    Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement delete = Con.prepareStatement(
            "DELETE FROM encargadopedido WHERE Empleados_id_Empleado = ?"
        );
        delete.setString(1, empleadosId);
        delete.executeUpdate();
        return true;
    } else {
        return false;
    }
}

    
}
