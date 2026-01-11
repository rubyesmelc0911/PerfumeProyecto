/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;
/**
 *
 * @author rubye
 */
public class dbconnection {
    public static Connection getConexion() {
        Connection cn = null;
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("db.properties")); //busca el archivo llamado db.properties en el root del proyecto donde esta el pom.xml
            
            // This line is the "magic" that uses the driver you just installed
            cn = DriverManager.getConnection(
                props.getProperty("db.url"), 
                props.getProperty("db.user"), 
                props.getProperty("db.password")
            );
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return cn;
    }
    
}
