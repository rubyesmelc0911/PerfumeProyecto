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
public class LotesProduccion {
    private String idLote;
    private String fechaInicio;
    private String fechaFin;
    private int cantidadProducida;
    private double costoTotal;
    private String controlCalidad;
    private String observaciones;
    private String empleadosIdEmpleado;
    private String perfumesIdPerfumes;

    public LotesProduccion(String idLote, String fechaInicio, String fechaFin, int cantidadProducida, 
                          double costoTotal, String controlCalidad, String observaciones, 
                          String empleadosIdEmpleado, String perfumesIdPerfumes) {
        this.idLote = idLote;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadProducida = cantidadProducida;
        this.costoTotal = costoTotal;
        this.controlCalidad = controlCalidad;
        this.observaciones = observaciones;
        this.empleadosIdEmpleado = empleadosIdEmpleado;
        this.perfumesIdPerfumes = perfumesIdPerfumes;
    }
    LotesProduccion(){
        
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(int cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getControlCalidad() {
        return controlCalidad;
    }

    public void setControlCalidad(String controlCalidad) {
        this.controlCalidad = controlCalidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEmpleadosIdEmpleado() {
        return empleadosIdEmpleado;
    }

    public void setEmpleadosIdEmpleado(String empleadosIdEmpleado) {
        this.empleadosIdEmpleado = empleadosIdEmpleado;
    }

    public String getPerfumesIdPerfumes() {
        return perfumesIdPerfumes;
    }

    public void setPerfumesIdPerfumes(String perfumesIdPerfumes) {
        this.perfumesIdPerfumes = perfumesIdPerfumes;
    }
    
    
    public void GuardarLoteProduccion() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO lotes_produccion VALUES (?,?,?,?,?,?,?,?)"
    );

    RES.setString(1, idLote);
    RES.setDate(2, Date.valueOf(fechaInicio));
    RES.setDate(3, Date.valueOf(fechaFin));
    RES.setInt(4, cantidadProducida);
    RES.setDouble(5, costoTotal);
    RES.setString(6, controlCalidad);
    RES.setString(7, observaciones);
    RES.setString(8, empleadosIdEmpleado);
    RES.setString(9, perfumesIdPerfumes);

    RES.executeUpdate();
}

    
    public boolean ModificarLoteProduccion(String idLote) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM lotes_produccion WHERE id_lote = ?"
    );
    SQL.setString(1, idLote);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement update = con.prepareStatement(
            "UPDATE lotes_produccion SET fecha_inicio=?, fecha_fin=?, cantidad_producida=?, "
          + "costo_total=?, control_calidad=?, observaciones=?, Empleados_id_Empleado=?, "
          + "Perfumes_id_Perfumes=? WHERE id_lote=?"
        );

        update.setDate(1, Date.valueOf(fechaInicio));
        update.setDate(2, Date.valueOf(fechaFin));
        update.setInt(3, cantidadProducida);
        update.setDouble(4, costoTotal);
        update.setString(5, controlCalidad);
        update.setString(6, observaciones);
        update.setString(7, empleadosIdEmpleado);
        update.setString(8, perfumesIdPerfumes);
        update.setString(9, idLote);

        update.executeUpdate();
        return true;
    } else {
        return false;
    }
}

    public boolean EliminarLoteProduccion(String idLote) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM lotes_produccion WHERE id_lote = ?"
    );
    SQL.setString(1, idLote);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        PreparedStatement delete = con.prepareStatement(
            "DELETE FROM lotes_produccion WHERE id_lote = ?"
        );
        delete.setString(1, idLote);
        delete.executeUpdate();
        return true;
    } else {
        return false;
    }
}
    
    public boolean BuscarLoteProduccion(String idLote) throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT * FROM lotes_produccion WHERE id_lote = ?"
    );
    SQL.setString(1, idLote);

    ResultSet Res = SQL.executeQuery();

    if (Res.next()) {
        this.idLote = Res.getString("id_lote");
        this.fechaInicio = String.valueOf(Res.getDate("fecha_inicio"));
        this.fechaFin = String.valueOf(Res.getDate("fecha_fin"));
        this.cantidadProducida = Res.getInt("cantidad_producida");
        this.costoTotal = Res.getDouble("costo_total");
        this.controlCalidad = Res.getString("control_calidad");
        this.observaciones = Res.getString("observaciones");
        this.empleadosIdEmpleado = Res.getString("Empleados_id_Empleado");
        this.perfumesIdPerfumes = Res.getString("Perfumes_id_Perfumes");
        return true;
    } else {
        return false;
    }
}


    public ResultSet MostrarLotesProduccion() throws SQLException {
    Connection con = dbconnection.getConexion();
    PreparedStatement SQL = con.prepareStatement(
        "SELECT id_lote, fecha_inicio, fecha_fin, cantidad_producida, "
      + "costo_total, control_calidad, observaciones, "
      + "Empleados_id_Empleado, Perfumes_id_Perfumes "
      + "FROM lotes_produccion"
    );

    return SQL.executeQuery();
}

    
    
}
