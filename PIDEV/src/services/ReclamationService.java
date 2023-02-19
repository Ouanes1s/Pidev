/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author MARWA
 */
public class ReclamationService implements IService<Reclamation>{
    Connection cnx = MyDB.getInstance().getCnx();
    
    @Override
    public void ajouter(Reclamation reclamation) throws SQLException {
        String query = "INSERT INTO RECLAMATION(date_reclamation,categorie_reclamation,type_reclamation,"
                + "message_reclamation,etat_reclamation," +
                 "importance_reclamation,reponse_reclamation,id_user) VALUES(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setDate(1,reclamation.getDateOccReclamation());
            ste.setString(2, reclamation.getCategorieReclamation());
            ste.setString(3,reclamation.getTypeReclamation());
            ste.setString(4,reclamation.getMessageReclamation());
            ste.setBoolean(5,reclamation.isEtatReclamation());
            ste.setInt(6, reclamation.getImportanceReclamation());
            ste.setString(7, reclamation.getReponseReclamation());
            ste.setInt(8, reclamation.getUserId());
            ste.executeUpdate();
            System.out.println("Reclamation Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation reclamation) throws SQLException {
       String query = "UPDATE RECLAMATION SET date_reclamation= '" + reclamation.getDateOccReclamation()+ "', categorie_reclamation= '" +
                reclamation.getCategorieReclamation()+ "', type_reclamation= '" + reclamation.getTypeReclamation()+ "', message_reclamation= '" +
                reclamation.getMessageReclamation()+ "', etat_reclamation= '" + (reclamation.isEtatReclamation() ? 1 : 0)+ "', importance_reclamation= '" +
                reclamation.getImportanceReclamation()+ "', reponse_reclamation='" + reclamation.getReponseReclamation() 
                + "' WHERE id_reclamation= " + reclamation.getIdReclamation()+ "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Reclamation Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Reclamation reclamation) throws SQLException {
        String query = "DELETE FROM RECLAMATION WHERE id_reclamation = " + reclamation.getIdReclamation() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Reclamation Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Reclamation> recuperer() throws SQLException {
        List<Reclamation> listeReclamations = new ArrayList<>();
        String query = "SELECT * FROM RECLAMATION";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Reclamation reclamation = new Reclamation();
                reclamation.setIdReclamation(rs.getInt("id_reclamation"));
                reclamation.setDateOccReclamation(rs.getDate("date_reclamation"));
                reclamation.setCategorieReclamation(rs.getString("categorie_reclamation"));
                reclamation.setTypeReclamation(rs.getString("type_reclamation"));
                reclamation.setMessageReclamation(rs.getString("message_reclamation"));
                reclamation.setEtatReclamation(rs.getBoolean("etat_reclamation"));
                reclamation.setImportanceReclamation(rs.getInt("importance_reclamation"));
                reclamation.setReponseReclamation(rs.getString("reponse_reclamation"));
                reclamation.setUserId(rs.getInt("id_user"));
                listeReclamations.add(reclamation);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listeReclamations;
    }

}
