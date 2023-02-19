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

/**
 *
 * @author chebi
 */

    public class UserCRUD implements InterfaceUser <User> {
     Connection cnx = ConnectionToDB.getInstance().getConnection();

     @Override
    public void ajouterUserAdmin(User U1) {
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
        }
    }
    
    @Override
    public void ajouterUserAgent(User u) {
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

         }
    
    @Override
    public void ajouterUserMembre(User u) {
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
                User u= new Membre(rs.getInt("id_user"), rs.getString("nom_user"),rs.getString("prenom_user"),
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
    public User getOneByCin(int cin) {
        User u = null;
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (cin==rs.getInt("cin_user")){
            u = new User (rs.getInt("id_user"), rs.getString("nom_user"),rs.getString("prenom_user"),rs.getString("cin_user"),rs.getString("email_user"),rs.getString("mdp_user"));}
                
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;

        
    }

    

    }

    

    

