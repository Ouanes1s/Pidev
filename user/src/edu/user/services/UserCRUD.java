/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.user.services;
import static com.oracle.nio.BufferSecrets.instance;
import edu.user.entities.Agent;
import edu.user.entities.Membre;
import edu.user.entities.User;
import edu.user.utils.ConnectionToDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.U;
import javax.mail.MessagingException;
import static javax.swing.UIManager.getString;

/**
 *
 * @author chebi
 */

    public class UserCRUD implements InterfaceUser <User> {
     Connection cnx = ConnectionToDB.getInstance().getConnection();

     @Override
    public void ajouterUserAdmin(User U1) {
       
              if (VerifCin(U1.getCin_user())!=0) {
                System.out.println("Admin deja ajouté ");
                EnvoyerEmail e = new EnvoyerEmail();
            try {
                e.envoyer(U1.getEmail_user(),U1.getMdp_user(),U1.getNom_user());
            } catch (MessagingException ex) {
                Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
           else{
               
    

             try{   
                
            String req ="INSERT INTO user (nom_user, prenom_user, cin_user, email_user, role_user, mdp_user) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            
            
            
            pst.setString(1, U1.getNom_user());
            pst.setString(2, U1.getPrenom_user());
            pst.setString(3, U1.getCin_user());
            pst.setString(4, U1.getEmail_user());
            pst.setString(5, "Admin");
            pst.setString(6, U1.getMdp_user());
            
            
            pst.executeUpdate();
            System.out.println("An agent was added succesfully!!!!!");
            
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }}
    }
    
    @Override
    public void ajouterUserAgent(User u) {
        if (VerifCin(u.getCin_user())!=0) {
                System.out.println("Agent deja ajouté ");
               EnvoyerEmail e = new EnvoyerEmail();
            try {
                e.envoyer(u.getEmail_user(),u.getMdp_user(),u.getNom_user());
            } catch (MessagingException ex) {
                Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
           else{
               
        try{
              
                   
            if (u instanceof Agent ){
                 Agent U1=(Agent) u;
            String req ="INSERT INTO user (nom_user, prenom_user, cin_user, email_user, role_user, mdp_user,salaire,type_A,date_contract) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            
            
            
            pst.setString(1, U1.getNom_user());
            pst.setString(2, U1.getPrenom_user());
            pst.setString(3, U1.getCin_user());
            pst.setString(4, U1.getEmail_user());
            pst.setString(5, "Agent");
            pst.setString(6, U1.getMdp_user());
            pst.setString(7, U1.getSalaire());
            pst.setString(8, U1.getType_A());
            pst.setString(9, U1.getDate_contract());
            
            
            
            
            pst.executeUpdate();
            System.out.println("An agent was added succesfully!!!!!");}
            
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

         }}
    
    @Override
    public void ajouterUserMembre(User u) {
        /*if (VerifCin(u.getCin_user())!=0) {
                System.out.println("Membre deja ajouté ");
                EnvoyerEmail e = new EnvoyerEmail();
            try {
                e.envoyer(u.getEmail_user(),u.getMdp_user(),u.getNom_user());
            } catch (MessagingException ex) {
                Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
           else{*/
               
        try{
              
                   
            if (u instanceof Membre ){
                 Membre U1=(Membre) u;
            String req ="INSERT INTO user (nom_user, prenom_user, cin_user, email_user, role_user, mdp_user,Date_inscri) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            
            
            
            pst.setString(1, U1.getNom_user());
            pst.setString(2, U1.getPrenom_user());
            pst.setString(3, U1.getCin_user());
            pst.setString(4, U1.getEmail_user());
            pst.setString(5, "Membre");
            pst.setString(6, U1.getMdp_user());
            pst.setString(7, U1.getDate_inscri());
            
            
            
            pst.executeUpdate();
            System.out.println("A member was added succesfully!!!!!");}
            
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

         }
    

    @Override
    public void modifierUserAgent(User u) {
        try{
            if (u instanceof Agent ){
                 Agent U1=(Agent) u;
        String req = "UPDATE user SET nom_user=?,prenom_user=?,cin_user=?,email_user=?,role_user=?,mdp_user=?,salaire=?,type_A=?,date_contract=? WHERE id_user=?";
        PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, U1.getNom_user());
            pst.setString(2, U1.getPrenom_user());
            pst.setString(3, U1.getCin_user());
            pst.setString(4, U1.getEmail_user());
            pst.setString(5, "Agent");
            pst.setString(6, U1.getMdp_user());
            pst.setString(7, U1.getSalaire());
            pst.setString(8, U1.getType_A());
            pst.setString(9, U1.getDate_contract());
            pst.setInt(10, U1.getId_user());
            
            pst.executeUpdate();
            System.out.println("An agent was updated successfully!");}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifierUserMembre(User u) {
        try{
            if (u instanceof Membre ){
                 Membre U1=(Membre) u;
        String req = "UPDATE user SET nom_user=?,prenom_user=?,cin_user=?,email_user=?,role_user=?,mdp_user=?,Date_inscri=? WHERE id_user=?";
        PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, U1.getNom_user());
            pst.setString(2, U1.getPrenom_user());
            pst.setString(3, U1.getCin_user());
            pst.setString(4, U1.getEmail_user());
            pst.setString(5, "Membre");
            pst.setString(6, U1.getMdp_user());
            pst.setString(7, U1.getDate_inscri());
            pst.setInt(8, U1.getId_user());
            
            pst.executeUpdate();
            System.out.println("A member was updated successfully!");}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifierUserAdmin (User U1){
    try{
            
        String req = "UPDATE user SET nom_user=?,prenom_user=?,cin_user=?,email_user=?,role_user=?,mdp_user=? WHERE id_user=?";
        PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, U1.getNom_user());
            pst.setString(2, U1.getPrenom_user());
            pst.setString(3, U1.getCin_user());
            pst.setString(4, U1.getEmail_user());
            pst.setString(5, "Admin");
            pst.setString(6, U1.getMdp_user());
            pst.setInt(7, U1.getId_user());
            
            pst.executeUpdate();
            System.out.println("A member was updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
     public void supprimerUser(User u) {
        try{
           
            String req = "DELETE FROM user WHERE id_user=?" ;
            Statement st;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, u.getId_user());
            pst.executeUpdate();
            System.out.println("A user was deleted successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void supprimerUserCin(User u) {
        try{
           
            String req = "DELETE FROM user WHERE cin_user=?" ;
            Statement st;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, u.getCin_user());
            pst.executeUpdate();
            System.out.println("A user was deleted successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    @Override
    public List<User> afficherUserMembre() {
        List <User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user`";
            Statement st;
            st =cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                if (rs.getString("role_user").equals("Membre")){
                User u= new Membre(rs.getInt("id_user"), rs.getString("nom_user"),rs.getString("prenom_user"),
                        rs.getString("cin_user"),rs.getString("email_user"),rs.getString("mdp_user"),rs.getString("Date_inscri")); 
                
                list.add(u);}
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public void majmdp (User U1, String mdp){
    try{
            
        String req = "UPDATE user SET mdp_user=? WHERE id_user=?";
        PreparedStatement pst = cnx.prepareStatement(req);
            
          
            pst.setString(1, mdp);
            pst.setInt(2, U1.getId_user());
            
            pst.executeUpdate();
            System.out.println("Your Password was updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Override
    public List<User> afficherUserAgent() {
        List <User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user`";
            Statement st;
            st =cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                if (rs.getString("role_user").equals("Agent")){
                User u= new Agent( rs.getString("nom_user"),rs.getString("prenom_user"),
                        rs.getString("cin_user"),rs.getString("email_user"),rs.getString("mdp_user"),rs.getString("salaire"),rs.getString("type_A"),rs.getString("date_contract")); 
                
               
                list.add(u);}
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

   

     @Override
    public User getOneByCin(String cin) {
        User u = null;
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (cin.equals(rs.getString("cin_user"))){
            u = new User (rs.getInt("id_user"), rs.getString("nom_user"),rs.getString("prenom_user"),rs.getString("cin_user"),rs.getString("email_user"),rs.getString("mdp_user"));}
                
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;

        
    }

    /**
     *
     * @param cin
     * @return
     */
    @Override
    public int VerifCin(String cin) {
        User u = null;
         int nb = 0;
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (cin.equals(rs.getString("cin_user"))){
           nb=1;}
                
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;

        
    }
     @Override
    public boolean verifierEmailBd(String email) {
	PreparedStatement stmt = null;
	ResultSet rst = null;
	try {
	    String sql = "SELECT * FROM User WHERE email_user=?";
	    stmt = cnx.prepareStatement(sql);
	    stmt.setString(1, email);
	    rst = stmt.executeQuery();
	    if (rst.next()) {
		return true;
	    }
	} catch (SQLException ex) {
	    System.out.println(ex.getMessage());
	}
	return false;

    }
     @Override
    public boolean verifiermdp(String mdp) {
	PreparedStatement stmt = null;
	ResultSet rst = null;
	try {
	    String sql = "SELECT * FROM User WHERE mdp_user=?";
	    stmt = cnx.prepareStatement(sql);
	    stmt.setString(1, mdp);
	    rst = stmt.executeQuery();
	    if (rst.next()) {
		return true;
	    }
	} catch (SQLException ex) {
	    System.out.println(ex.getMessage());
	}
	return false;

    }

    /**
     *
     * @param email
     * @param mdp
     * @return
     * @throws MessagingException
     * @throws SQLException
     */
     @Override
     public void Authentification(String email , String mdp)  {
    String s= "";
    PreparedStatement stmt = null;
	ResultSet rst = null;
    if (verifierEmailBd(email)==false) {
        System.out.println("Email n'est pas reconnu. Veuillez vous inscrire!!!");
    }
    else {
       if (verifiermdp(mdp)==false) {
        System.out.println("Votre Mdp est erroné . Un Email de verification vous sera envoyé pour changer de mot de passe!");
        try {
	    String sql = "SELECT * FROM User WHERE email_user=?";
	    stmt = cnx.prepareStatement(sql);
	    stmt.setString(1, email);
	    rst = stmt.executeQuery();
            if (rst.next()) {
	  s=rst.getString("nom_user");
		EnvoyerEmail e = new EnvoyerEmail();
            try {
                e.envoyer(email,rst.getString("mdp_user"),s);
            } catch (MessagingException ex) {
                Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }}
	    
	} catch (SQLException ex) {
	    System.out.println(ex.getMessage());
	}}
        else { 
           try {
               String sq1 = "SELECT * FROM User WHERE email_user=?";
               stmt = cnx.prepareStatement(sq1);
               stmt.setString(1, email);
               rst = stmt.executeQuery();
               if (rst.next()) {
               if ("Agent".equals(rst.getString("role_user"))){
                   System.out.println("Bonjour Mr "+rst.getString("nom_user")+"Vous etes  Agent de "+ rst.getString("type_A") );
               }
               if ("Membre".equals(rst.getString("role_user"))){
                   System.out.println("Bonjour Mr "+rst.getString("nom_user")+" Membre Depuis "+ rst.getString("Date_inscri") );
               }
               if ("Admin".equals(rst.getString("role_user"))){
                   System.out.println("Bonjour Mr "+rst.getString("nom_user")+" Admin de l'app " );
               }}
           } catch (SQLException ex) {
               Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
           }
      
       }}
    
     }}
    
        
        

    
    
    

    

