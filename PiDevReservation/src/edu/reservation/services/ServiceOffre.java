/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.services;
 
import edu.reservation.entities.Offre;
import edu.reservation.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
/**
 *
 * @author Amine Khalfaoui
 */
public class ServiceOffre implements IServices<Offre> {

    Connection cnx = DataSource.getInstance().getCnx();
    
    
    @Override
    public void ajouter (Offre r) {
        try {
            String req = "INSERT INTO `offre`( `nomfilm_offr`,`contenu_offr`, `datedebut_offr`, `datefin_offr`, `code_offr` ) VALUES (?,?,?,?,? )";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, r.getNomfilm_offr());
            ps.setString(2, r.getContenu_offr());
            ps.setString(3, r.getDatedebut_offr());
             ps.setString(4, r.getDatefin_offr());
             ps.setString(5, r.getCode_offr());
  
                
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }

  
    public void supprimer(int id_offr) {
        try {
            String req = "DELETE FROM `offre` WHERE id_offr = " + id_offr;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Offre r) {
  try {
            String req = "UPDATE `offre` SET  `id_film` = '" + r.getNomfilm_offr() + "', `contenu_offr` = '" + r.getContenu_offr() + "', `datedebut_offr` = '" + r.getDatedebut_offr() + "', `datefin_offr` = '" + r.getDatefin_offr() + "', `code_offr` = '" + r.getCode_offr() +   "' WHERE `offre`.`id_offr` = " + r.getId_offr();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Offre> Afficher() {
    List<Offre> list = new ArrayList<>();
        try {
            String req = "Select * from offre";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Offre r = new Offre(rs.getInt("id_offr"), rs.getString("nomfilm_offr"), rs.getString("contenu_offr"), rs.getString("datedebut_offr"), rs.getString("datefin_offr"), rs.getString("code_offr") );
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;    
    }

    @Override
    public Offre getOneById(int id) {
 
        Offre r = null;
        try {
            String req = "Select * from offre";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (id==rs.getInt("id_offr")){
       r = new Offre (rs.getInt("id_offr"), rs.getString("nomfilm_offr"), rs.getString("contenu_offr"),rs.getString("datedebut_offr"),
                        rs.getString("datefin_offr"),rs.getString("code_offr") );            }
        }} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return r;
       }
    
    
    
 

    @Override
    public int VerifTitre(String titre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 

    @Override
    public int VerifOffr(String code, String nom, String prenom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Offre> trierOffreParDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Offre r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
    
    
//
//    public class JoinTablesExample {
//    public static void main(String[] args) {
//    String url = "jdbc:mysql://localhost:3306/mydatabase";
//    String user = "username";
//    String password = "password";
//
//    try (Connection conn = DriverManager.getConnection(url, user, password);
//         Statement stmt = conn.createStatement();
//         ResultSet rs = stmt.executeQuery("SELECT * FROM table1 JOIN table2 ON table1.id = table2.id")) {
//
//      while (rs.next()) {
//        // Process the data here
//      }
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }
 


 
  

     
 
  

 
 
