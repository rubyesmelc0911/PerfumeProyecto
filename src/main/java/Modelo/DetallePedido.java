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
public class DetallePedido {
    private String ingredientesIdIngredientes;
    private String formulasIdFormula;
    private String nota;
    private double proporcion;

    public DetallePedido(String ingredientesIdIngredientes, String formulasIdFormula, 
                    String nota, double proporcion) {
        this.ingredientesIdIngredientes = ingredientesIdIngredientes;
        this.formulasIdFormula = formulasIdFormula;
        this.nota = nota;
        this.proporcion = proporcion;
    }

    public String getIngredientesIdIngredientes() {
        return ingredientesIdIngredientes;
    }

    public void setIngredientesIdIngredientes(String ingredientesIdIngredientes) {
        this.ingredientesIdIngredientes = ingredientesIdIngredientes;
    }

    public String getFormulasIdFormula() {
        return formulasIdFormula;
    }

    public void setFormulasIdFormula(String formulasIdFormula) {
        this.formulasIdFormula = formulasIdFormula;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public double getProporcion() {
        return proporcion;
    }

    public void setProporcion(double proporcion) {
        this.proporcion = proporcion;
    }
    
    public void Guardar() throws SQLException {
    Connection con = dbconnection.getConexion();

    PreparedStatement RES = con.prepareStatement(
        "INSERT INTO Contiene VALUES (?,?,?,?)"
    );

    RES.setString(1, ingredientesIdIngredientes);
    RES.setString(2, formulasIdFormula);
    RES.setString(3, nota);
    RES.setDouble(4, proporcion);

    RES.executeUpdate();
}
    
    
    
    
    
}
