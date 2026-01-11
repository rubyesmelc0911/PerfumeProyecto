/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
import db.dbconnection;
/**
 *
 * @author rubye
 */
public class Main {
    public static void main(String[] args) {
    // 1. Try to get the connection from your Conexion class
    java.sql.Connection testConn = dbconnection.getConexion();

    if (testConn != null) {
        System.out.println("✅ Connection Successful!");
        System.out.println("Testing query on 'Ingredientes' table...");
        try {
            testConn.close();
        } catch (java.sql.SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
            System.out.println("Tip: Make sure you ran your script in Workbench to create the tables first!");
        }
    } else {
        System.out.println("❌ Connection Failed.");
        System.out.println("Check: \n1. Is MySQL/MariaDB running? \n2. Is db.properties in the ROOT folder? \n3. Is the password/user correct?");
    }
}
    
}
