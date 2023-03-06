/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.services;

import edu.stock.entites.Produit_Avis;
import edu.stock.utils.ConnexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maidi
 */
public class ServiceProdAvis {
    
    Connection cnx= ConnexionBD.getInstance().getCnx();
    
    public List<Produit_Avis> afficherTous() {
        List<Produit_Avis> list = new ArrayList<>();
        try {
            String req = "Select * from avis";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Produit_Avis a = new Produit_Avis(rs.getInt(1), rs.getInt(2));
                list.add(a);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list; 
    }

//metier nbr avis par produit


}
