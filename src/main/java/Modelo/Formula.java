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
    
    
    
}
