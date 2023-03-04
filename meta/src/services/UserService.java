/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.MyDB;

/**
 *
 * @author ProtocolBlood
 */
public class UserService {
    
        Connection cnx = MyDB.getInstance().getCnx();
        public User getUser(int idUser) throws SQLException {
            String query = "SELECT * FROM USER where id_user = '" + idUser + "' ";
            User user = new User();
            try{
                Statement ste = cnx.createStatement();
                ResultSet rs = ste.executeQuery(query);
                while (rs.next()){
                    user.setIdUser(rs.getInt("id_user"));
                    user.setCinUser(rs.getString("cin_user"));
                    user.setPrenomUser(rs.getString("prenom_user"));
                    user.setNomUser(rs.getString("nom_user"));
                    user.setEmailUser(rs.getString("email_user"));
                    user.setRoleUser(rs.getString("role_user")); //
                    user.setMdpUser(rs.getString("mdp_user"));
                    user.setPrenomUser(rs.getString("Date_inscri"));
                    user.setSalaire(rs.getString("Salaire"));
                    user.setType_A(rs.getString("Type_A"));
                    user.setDateContract(rs.getString("date_contrat"));
                }
            }
            catch (SQLException e){
                e.getMessage();
            }
            return user;
    }
    
   
}
