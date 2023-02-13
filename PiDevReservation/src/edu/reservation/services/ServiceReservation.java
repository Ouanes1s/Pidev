/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.services;
import edu.reservation.entities.Reservation;
import edu.reservation.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amine Khalfaoui
 */
public class ServiceReservation implements IServiceReservation<Reservation> {

    Connection cnx = DataSource.getInstance().getCnx();

//    @Override
//    public void ajouterReservation(Reservation r) {
//        try {
//            String req = "INSERT INTO `reservation`( `nom_res`, `prenom_res`, `typeticket_res`, `numfilm_res`, `idproduit_res`, `date_res`) VALUES ('" + r.getNom_res() + "', '" + r.getPrenom_res() + "''" + r.getTypeTicket_res() + "', '" + r.getNumFilm_res() + "''" + r.getIdProduit_res() + "', '" + r.getDate_res() + "')";
//            PreparedStatement st = cnx.prepareStatement();
//            st.executeUpdate(req);
//            System.out.println("Reservation created !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    public void ajouterReservation(Reservation r) {
        try {
            String req = "INSERT INTO `reservation`( `nom_res`, `prenom_res`, `typeticket_res`, `numfilm_res`, `idproduit_res`, `date_res`) VALUES (?,?,?,?,?,? )";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, r.getPrenom_res());
            ps.setString(1, r.getNom_res());
             ps.setString(3, r.getTypeTicket_res());
             ps.setInt(4, r.getNumFilm_res());
             ps.setInt(5, r.getIdProduit_res());
             ps.setString(6, r.getDate_res());
                
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }

    

    @Override
    public void supprimerReservation(int id_res) {
        try {
            String req = "DELETE FROM `reservation` WHERE id = " + id_res;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierReservation(Reservation r) {
        try {
            String req = "UPDATE `reservation` SET `nom_res` = '" + r.getNom_res() + "', `prenom_res` = '" + r.getPrenom_res() + "', `typeticket_res` = '" + r.getTypeTicket_res() + "', `numfilm_res` = '" + r.getNumFilm_res() + "', `idproduit_res` = '" + r.getIdProduit_res() + "', `date_res` = '" + r.getDate_res() + "' WHERE `personne`.`id` = " + r.getId_res();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reservation r = new Reservation(rs.getInt("id_res"), rs.getString("nom_res"), rs.getString("prenom_res"), rs.getString("typeticket_res"), rs.getInt("numfilm_res"), rs.getInt("idproduit_res"), rs.getString("date_res"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
//    public Reservation getOneById(int id_res) {
//        Reservation r = null;
//        try {
//            String req = "Select * from reservation";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//                r = new Reservation(rs.getInt("id_res"), rs.getString("nom_res"), rs.getString("prenom_res"), rs.getString("typeticket_res"), rs.getInt("numfilm_res"), rs.getInt("idproduit_res"), rs.getString("date_res"));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return r;
//    }
    
    public Reservation getOneById(int id) {
        Reservation r = null;
        try {
            String req = "Select * from reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (id==rs.getInt("id_res")){
       r = new Reservation (rs.getInt("id_res"), rs.getString("nom_res"),rs.getString("prenom_res"),
                        rs.getString("typeticket_res"),rs.getInt("numfilm_res"),rs.getInt("idproduit_res"),rs.getString("date_res"));            }
        }} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return r;
    }

    }
    
 
