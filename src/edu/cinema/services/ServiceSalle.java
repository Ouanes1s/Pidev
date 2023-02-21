/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinema.services;

import edu.cinema.entities.Salle;
import edu.cinema.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ServiceSalle implements IService<Salle> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Salle p) {
        try {
            String req = "INSERT INTO `salle` (`nom_salle`, `adresse`,`num_places`) VALUES ('" + p.getNom_salle() + "', '" + p.getAdresse() +"','"+p.getNum_places()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Salle created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouter2(Salle p) {
        try {
            String req = "INSERT INTO `salle` (`nom`, `adresse`,`num_places) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, p.getNom_salle());
            ps.setString(1, p.getAdresse());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `salle` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Salle deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierSalle(Salle u,int i) {
        try{
        String req = "UPDATE salle SET nom_salle=?,adresse=?,num_places=? WHERE id="+i;
        PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, u.getNom_salle());
            pst.setString(2, u.getAdresse());
            pst.setString(3, u.getNum_places());
            pst.executeUpdate();
            System.out.println("Salle was updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifier(Salle p) {
        try {
            String req = "UPDATE `salle` SET `nom_salle` = '" + p.getNom_salle() + "', `Adresse` = '" + p.getAdresse()+ "' WHERE `cinema`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Salle updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Salle> afficherSalle() {
        List <Salle> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `salle`";
            Statement st;
            st =cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Salle c = new Salle ( rs.getString("nom_Salle"),rs.getString("adresse"),
                        rs.getString("num_places"));
                list.add(c);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Salle> getAll() {
        List<Salle> list = new ArrayList<>();
        try {
            String req = "Select * from salle";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Salle p = new Salle(rs.getString(1), rs.getString("nom"), rs.getString(3));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

     @Override
    public Salle getOneById(int id) {
        Salle c = null;
        try {
            String req = "Select * from salle";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (id==rs.getInt("id")){
       c = new Salle (rs.getInt("id"), rs.getString("nom_Salle"),rs.getString("adresse"),rs.getString("num_places"));            }
        }} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }
   

}
