/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Parking;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.MyDB;

/**
 *
 * @author MarwaTriaa
 */
public class ReclamationService implements IService<Reclamation>{
    Connection cnx = MyDB.getInstance().getCnx();
    
    @Override
    public void ajouter(Reclamation reclamation) throws SQLException {
        if(checkExistence(reclamation) == 0 && checkEmpty(reclamation)){
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
        } else if(checkExistence(reclamation) != 0)
            System.out.println("Reclamation Already Exists!!");
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
    
    public void filterReclamations(String target) throws SQLException{
        ReclamationService rs = new ReclamationService();
        rs.recuperer().stream().filter(
                (r)
                        -> r.getCategorieReclamation().toLowerCase().contains(target.toLowerCase())
                        || r.getMessageReclamation().toLowerCase().contains(target.toLowerCase())
                        || r.getReponseReclamation().toLowerCase().contains(target.toLowerCase())
                        || r.getTypeReclamation().toLowerCase().contains(target.toLowerCase())
                        || r.getDateOccReclamation().toString().contains(target)
        ).forEach(System.out::println);
    }
    
    //TRI
    public List<Reclamation> sortReclamations() throws SQLException {
        ReclamationService rs = new ReclamationService();
        List<Reclamation> sortedReclamations = rs.recuperer().stream()
                                             .sorted()
                                             .collect(Collectors.toList());
        return sortedReclamations;
    }
    
    //controle de saisie
    public int checkExistence(Reclamation reclamation) throws SQLException{ //pour le mm message 
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from RECLAMATION WHERE message_reclamation= '" +
                reclamation.getMessageReclamation()+ "'"); //on compte nbr enregistrement
        int size = 0;
        rs.next();
        size = rs.getInt(1);
        return size; //si 0 aucun enregistrement
    }
    //controle de saisie 
    public boolean checkEmpty(Reclamation reclamation) throws SQLException{
        if (reclamation.getCategorieReclamation() == null || reclamation.getDateOccReclamation() == null
            || reclamation.getMessageReclamation() == null || reclamation.getTypeReclamation() == null
                || reclamation.getImportanceReclamation() == 0){
            System.out.println("L'un des champs obligatoires à remplir est vide!!"); //message d'erreur
            return false;
        }
        System.out.println("Tous les champs obligatoires sont remplis !!");
        return true;
    }
    
    
     public boolean SendMail()
    {
        String password = "fyfwedvsxlbmyylo"; //code secu
        // Ur email password : pexwoavpodyhtkcn //mdp de pidev
        String from,to,host,sub,content;
        from = "pidevmailing@gmail.com";
        to = "marwa.triaa@esprit.tn";
        host="localhost";
        sub="Réclamation envoyée";
        content="Votre réclamation a bien été envoyée. Elle est en cours de traitement. Ce Message est automatique.";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session=Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(from,password);
        }
        }
        );
        try {
            MimeMessage m =new MimeMessage(session);
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(sub);
            m.setText(content);
            Transport.send(m);
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

}
