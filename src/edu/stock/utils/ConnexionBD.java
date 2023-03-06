/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author
 */
public class ConnexionBD {
    private Connection cnx;
    private static ConnexionBD instance;
    private final String USER="root";
    private final String PWD="";
    private final String URL="jdbc:mysql://localhost:3306/stock";  
    
    private ConnexionBD() { //Constructeur
        try {
            cnx=DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connecté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
    public static ConnexionBD getInstance(){
        if(instance == null)
            instance = new ConnexionBD();
        return instance;
    }    
}
