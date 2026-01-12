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
public class Formula {
    private String idFormula;
    private String version;
    private String fechaRegistro;
    private String observaciones;
    private String perfumesIdPerfumes;

    public Formula(String idFormula, String version, String fechaRegistro, 
                   String observaciones, String perfumesIdPerfumes) {
        this.idFormula = idFormula;
        this.version = version;
        this.fechaRegistro = fechaRegistro;
        this.observaciones = observaciones;
        this.perfumesIdPerfumes = perfumesIdPerfumes;
    }

    public String getIdFormula() {
        return idFormula;
    }

    public void setIdFormula(String idFormula) {
        this.idFormula = idFormula;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPerfumesIdPerfumes() {
        return perfumesIdPerfumes;
    }

    public void setPerfumesIdPerfumes(String perfumesIdPerfumes) {
        this.perfumesIdPerfumes = perfumesIdPerfumes;
    }
    
    
    //CRUD OPERATIONS
public void Guardar(Formula f) throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO formulas VALUES (?,?,?,?,?)"
    );

    RES.setString(1, idFormula);
    RES.setString(2, version);
    RES.setString(3, fechaRegistro);
    RES.setString(4, observaciones);
    RES.setString(5, perfumesIdPerfumes);

    RES.executeUpdate();
}
public ResultSet Mostrar() throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM formulas"
    );
    return SQL.executeQuery();
}
public boolean Buscar(String idFormula) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM formulas WHERE id_Formula = ?"
    );

    SQL.setString(1, idFormula);
    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        this.idFormula = Res.getString("id_Formula");
        version = Res.getString("version");
        fechaRegistro = Res.getString("fecha_registro");
        observaciones = Res.getString("observaciones");
        perfumesIdPerfumes = Res.getString("Perfumes_id_Perfumes");
        return true;
    } else {
        return false;
    }
}

public boolean Modificar(String idFormula) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM formulas WHERE id_Formula = ?"
    );

    SQL.setString(1, idFormula);
    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement updateStatement = Con.prepareStatement(
            "UPDATE formulas SET version = ?, fecha_registro = ?, observaciones = ?, "
          + "Perfumes_id_Perfumes = ? WHERE id_Formula = ?"
        );

        updateStatement.setString(1, version);
        updateStatement.setString(2, fechaRegistro);
        updateStatement.setString(3, observaciones);
        updateStatement.setString(4, perfumesIdPerfumes);
        updateStatement.setString(5, idFormula);

        updateStatement.executeUpdate();
        return true;
    } else {
        return false;
    }
}

public boolean eliminarRegistro(String idFormula) throws SQLException {
    Connection Con = dbconnection.getConexion();
    PreparedStatement SQL = Con.prepareStatement(
        "SELECT * FROM formulas WHERE id_Formula = ?"
    );

    SQL.setString(1, idFormula);
    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement delete = Con.prepareStatement(
            "DELETE FROM formulas WHERE id_Formula = ?"
        );
        delete.setString(1, idFormula);
        delete.executeUpdate();
        return true;
    } else {
        return false;
    }
}

    
    
}
