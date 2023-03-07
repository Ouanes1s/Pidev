/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalintegration2.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author chebi
 */
public class ConnectionToDB {

    String url = "jdbc:mysql://localhost:3306/pidevmetax";
    String user = "root";
    String pwd = "";
    
    private static ConnectionToDB instance;
    private Connection conn;
    
    private ConnectionToDB(){
        try { 
            conn = DriverManager.getConnection(url,user,pwd);
            System.out.println("Connexion etablie !! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static ConnectionToDB getInstance(){
        if (instance==null)
            instance = new ConnectionToDB ();
        return instance;
    }
    public Connection getConnection(){
        return conn;
    
    }
    
}

    
    

