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
            String req = "INSERT INTO `offre`( `id_film`,`contenu_offr`, `datedebut_offr`, `datefin_offr`, `code_offr` ) VALUES (?,?,?,?,? )";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, r.getId_film());
            ps.setString(2, r.getContenu_offr());
            ps.setString(3, r.getDatedebut_offr());
             ps.setString(4, r.getDatefin_offr());
             ps.setString(5, r.getCode_offr());
  
                
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }

    @Override
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
            String req = "UPDATE `offre` SET  `id_film` = '" + r.getId_film() + "', `contenu_offr` = '" + r.getContenu_offr() + "', `datedebut_offr` = '" + r.getDatedebut_offr() + "', `datefin_offr` = '" + r.getDatefin_offr() + "', `code_offr` = '" + r.getCode_offr() +   "' WHERE `offre`.`id_offr` = " + r.getId_offr();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List<Offre> getAll() {
    List<Offre> list = new ArrayList<>();
        try {
            String req = "Select * from offre";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Offre r = new Offre(rs.getInt("id_offr"), rs.getInt("id_film"), rs.getString("contenu_offr"), rs.getString("datedebut_offr"), rs.getString("datefin_offr"), rs.getString("code_offr") );
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
       r = new Offre (rs.getInt("id_offr"), rs.getInt("id_film"), rs.getString("contenu_offr"),rs.getString("datedebut_offr"),
                        rs.getString("datefin_offr"),rs.getString("code_offr") );            }
        }} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return r;
       }
  

     

 
 
}
