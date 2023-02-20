/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.services;
import edu.reservation.entities.Offre;
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
import javax.mail.MessagingException;
 

/**
 *
 * @author Amine Khalfaoui
 */
public class ServiceReservation implements IServices<Reservation> {

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

    @Override
    public void ajouter (Reservation r) {
         if (VerifOffr(r.getCode_offr(),r.getNom_res(),r.getPrenom_res() )!=0) {
                System.out.println("Vous avez deja utiliser cette offre  ");
                   Email e = new  Email();
            try {
                e.envoyer(r.getEmail_res(),r.getDate_res(),r.getPrenom_res());
            } catch (MessagingException ex) {
                Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
         } 
         }
        else{        
        try {
            String req = "INSERT INTO `reservation`( `nom_res`, `prenom_res`,`email_res`, `typeticket_res`, `id_film`, `date_res`, `code_offr`) VALUES (?,?,?,?,?,?,? )";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, r.getPrenom_res());
            ps.setString(1, r.getNom_res());
            ps.setString(3, r.getEmail_res());
             ps.setString(4, r.getTypeTicket_res());
             ps.setInt(5, r.getId_film());
             ps.setString(6, r.getDate_res());
             ps.setString(7, r.getCode_offr());
                
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
        }
    }

    

    @Override
    public void supprimer (int id_res) {
        try {
            String req = "DELETE FROM `reservation` WHERE id_res = " + id_res;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier (Reservation r) {
        try {
            String req = "UPDATE `reservation` SET `nom_res` = '" + r.getNom_res() + "', `prenom_res` = '" + r.getPrenom_res() + "', `email_res` = '" + r.getEmail_res() + "', `typeticket_res` = '" + r.getTypeTicket_res() + "', `id_film` = '" + r.getId_film() + "', `date_res` = '" + r.getDate_res() + "', `code_offr` = '" + r.getCode_offr() + "' WHERE `reservation`.`id_res` = " + r.getId_res();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation updated !");
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
                Reservation r = new Reservation(rs.getInt("id_res"), rs.getString("nom_res"),  rs.getString("prenom_res"), rs.getString("email_res"), rs.getString("typeticket_res"), rs.getInt("id_film"), rs.getString("date_res"), rs.getString("code_offr"));
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
       r = new Reservation (rs.getInt("id_res"), rs.getString("nom_res"),rs.getString("prenom_res"),rs.getString("email_res"),
                        rs.getString("typeticket_res"),rs.getInt("id_film"),rs.getString("date_res"),rs.getString("code_offr"));            
                }
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
    public int VerifOffr(String code, String nom, String prenom ) {
        Offre r = null;
         int nb = 0;
        try {
            String req = "Select * from reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (code.equals(rs.getString("code_offr"))&& nom.equals(rs.getString("nom_res"))&& prenom.equals(rs.getString("prenom_res")) ){
           nb=1;}
                
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;    }

    @Override
    public List<Reservation> trierOffreParDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }
    
 
