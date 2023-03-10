/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.Services;
import finalintegration2.Models.Offre;
import finalintegration2.Models.Reservation;
import finalintegration2.Utils.ConnectionToDB;
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

    Connection cnx = ConnectionToDB.getInstance().getConnection();

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
                
                   
         }
        else{   
              Email e = new  Email();
            try {
                e.envoyer(r.getEmail_res(),r.getDate_res(),r.getPrenom_res());
            } catch (MessagingException ex) {
                Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
         } 
        try {
            String req = "INSERT INTO `reservation`( `nom_res`, `prenom_res`,`email_res`, `typeticket_res`, `nom_evnmt`, `date_res`, `code_offr`) VALUES (?,?,?,?,?,?,? )";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, r.getPrenom_res());
            ps.setString(1, r.getNom_res());
            ps.setString(3, r.getEmail_res());
             ps.setString(4, r.getTypeTicket_res());
             ps.setString(5, r.getNom_evnmt());
             ps.setString(6, r.getDate_res());
             ps.setString(7, r.getCode_offr());
                
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
        }
    }

    

   
    public void supprimer (Reservation r) {
        try {
            String req = "DELETE FROM `reservation` WHERE id_res =? " ;
             Statement st;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, r.getId_res());
            System.out.println("Reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
//    public void modifier (Reservation r) {
//        try{
//            if (r instanceof Reservation ){
//                 Reservation R1 =(Reservation) r;
//        String req = "UPDATE reservation SET  prenom_res=?,email_res=?,typeticket_res=?,nom_evnmt=?,code_offr=?, date_res=? WHERE nom_res=?";
//        PreparedStatement pst = cnx.prepareStatement(req);
//            
//            pst.setString(1, R1.getNom_res());
//            pst.setString(2, R1.getPrenom_res());
//         
//            pst.setString(3, R1.getEmail_res());
//            pst.setString(4, R1.getTypeTicket_res());
//            pst.setString(5, R1.getNom_evnmt());
//            pst.setString(6, R1.getCode_offr());
//            pst.setString(7, R1.getDate_res());
////           pst.setInt(8, R1.getId_res());
//            
//            pst.executeUpdate();
//            System.out.println("A Reservation was updated successfully!");}
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    
    public void modifier(Reservation r) {
    try {
        Reservation R1 = (Reservation) r;
        String req = "UPDATE reservation SET nom_res=? , prenom_res=?, email_res=?, typeticket_res=?, nom_evnmt=?, code_offr=?, date_res=? WHERE id_res=?";
        PreparedStatement pst = cnx.prepareStatement(req);
         pst.setString(1, R1.getNom_res());

        pst.setString(2, R1.getPrenom_res());
        pst.setString(3, R1.getEmail_res());
        pst.setString(4, R1.getTypeTicket_res());
        pst.setString(5, R1.getNom_evnmt());
        pst.setString(6, R1.getCode_offr());
        pst.setString(7, R1.getDate_res());
        pst.setInt(8, R1.getId_res());

        pst.executeUpdate();
        System.out.println("A Reservation was updated successfully!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    public List<Reservation> Afficher() {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reservation r = new Reservation( rs.getString("nom_res"),  rs.getString("prenom_res"), rs.getString("email_res"), rs.getString("typeticket_res"), rs.getString("nom_evnmt"), rs.getString("date_res"), rs.getString("code_offr"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }

//    @Override
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
    
//    public Reservation getOneById(int id) {
//        Reservation r = null;
//        try {
//            String req = "Select * from reservation";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//                if (id==rs.getInt("id_res")){
//       r = new Reservation (rs.getInt("id_res"), rs.getString("nom_res"),rs.getString("prenom_res"),rs.getString("email_res"),
//                        rs.getString("typeticket_res"),rs.getString("nom_evnmt"),rs.getString("date_res"),rs.getString("code_offr"));            
//                }
//        }} catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return r;
//    }

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

    @Override
    public Reservation getOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    }
    
 
