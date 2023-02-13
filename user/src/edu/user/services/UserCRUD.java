/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.user.services;
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

/**
 *
 * @author chebi
 */

    public class UserCRUD implements InterfaceUser <User> {
     Connection cnx = ConnectionToDB.getInstance().getConnection();

    @Override
    public void ajouterUser(User u) {
        try{
            String req ="INSERT INTO user (nom_user, prenom_user, cin_user, email_user, role_user, mdp_user) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, u.getNom_user());
            pst.setString(2, u.getPrenom_user());
            pst.setString(3, u.getCin_user());
            pst.setString(4, u.getEmail_user());
            pst.setString(5, u.getRole_user());
            pst.setString(6, u.getMdp_user());
            
            
            pst.executeUpdate();
            System.out.println("A user was added succesfully!!!!!");
            
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierUser(User u) {
        try{
        String req = "UPDATE user SET nom_user=?,prenom_user=?,cin_user=?,email_user=?,role_user=?,mdp_user=? WHERE id_user=?";
        PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, u.getNom_user());
            pst.setString(2, u.getPrenom_user());
            pst.setString(3, u.getCin_user());
            pst.setString(4, u.getEmail_user());
            pst.setString(5, u.getRole_user());
            pst.setString(6, u.getMdp_user());
            pst.setInt(7, u.getId_user());
            
            pst.executeUpdate();
            System.out.println("A user was updated successfully!");
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
   
 public List<User> afficherUser() {
        List <User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user`";
            Statement st;
            st =cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                User u = new User (rs.getInt("id_user"), rs.getString("nom_user"),rs.getString("prenom_user"),
                        rs.getString("cin_user"),rs.getString("email_user"),rs.getString("role_user"),rs.getString("mdp_user"));
                list.add(u);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

   

    @Override
    public User getOneById(int id) {
        User u = null;
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (id==rs.getInt("id_user")){
       u = new User (rs.getInt("id_user"), rs.getString("nom_user"),rs.getString("prenom_user"),
                        rs.getString("cin_user"),rs.getString("email_user"),rs.getString("role_user"),rs.getString("mdp_user"));            }
        }} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return u;
    }

    }

    

    

