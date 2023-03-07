/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.Services;

import finalintegration2.Models.Parking;
import finalintegration2.Models.Reclamation;
import finalintegration2.Utils.ConnectionToDB;
import java.sql.Connection;
import java.sql.Date;
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
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import finalintegration2.Models.Session;
/**
 *
 * @author MarwaTriaa
 */
public class ReclamationService implements IService<Reclamation>{
    Connection cnx = ConnectionToDB.getInstance().getConnection();
    
    @Override
    public void ajouter(Reclamation reclamation) throws SQLException {
        String cin = Session.getCin();
        if(checkExistence(reclamation) == 0 ){
            String query = "INSERT INTO RECLAMATION(date_reclamation,categorie_reclamation,type_reclamation,"
                + "message_reclamation,etat_reclamation," +
                 "importance_reclamation,reponse_reclamation,cin) VALUES(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setDate(1,new Date(new java.util.Date().getTime()));
            ste.setString(2, reclamation.getCategorieReclamation());
            ste.setString(3,reclamation.getTypeReclamation());
            ste.setString(4,reclamation.getMessageReclamation());
            ste.setBoolean(5,false);
            ste.setInt(6, reclamation.getImportanceReclamation());
            ste.setString(7, "");
            ste.setString(8, cin);
            ste.executeUpdate();
            System.out.println("Reclamation Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        } else if(checkExistence(reclamation) != 0)
            System.out.println("Parking Already Exists!!");
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
        String query = "SELECT * FROM RECLAMATION where etat_reclamation = '0'";
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
                reclamation.setCin(rs.getString("cin"));
                listeReclamations.add(reclamation);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listeReclamations;
    }
    
    
    public Reclamation getReclamation(int idRec) throws SQLException {
        
        String query = "SELECT * FROM RECLAMATION where id_reclamation = '" + idRec + "' ";
        Reclamation reclamation = new Reclamation();
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                reclamation.setIdReclamation(rs.getInt("id_reclamation"));
                reclamation.setDateOccReclamation(rs.getDate("date_reclamation"));
                reclamation.setCategorieReclamation(rs.getString("categorie_reclamation"));
                reclamation.setTypeReclamation(rs.getString("type_reclamation"));
                reclamation.setMessageReclamation(rs.getString("message_reclamation"));
                reclamation.setEtatReclamation(rs.getBoolean("etat_reclamation"));
                reclamation.setImportanceReclamation(rs.getInt("importance_reclamation"));
                reclamation.setReponseReclamation(rs.getString("reponse_reclamation"));
                reclamation.setCin(rs.getString("cin"));
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return reclamation;
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
    
    public List<Reclamation> sortReclamations() throws SQLException {
        ReclamationService rs = new ReclamationService();
        List<Reclamation> sortedReclamations = rs.recuperer().stream()
                                             .sorted()
                                             .collect(Collectors.toList());
        return sortedReclamations;
    }
    
    public int checkExistence(Reclamation reclamation) throws SQLException{
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from RECLAMATION WHERE message_reclamation= '" +
                reclamation.getMessageReclamation()+ "'");
        int size = 0;
        rs.next();
        size = rs.getInt(1);
        return size;
    }
    
    public boolean checkEmpty(Reclamation reclamation) throws SQLException{
        if (reclamation.getCategorieReclamation() == null || reclamation.getDateOccReclamation() == null
            || reclamation.getMessageReclamation() == null || reclamation.getTypeReclamation() == null
                || reclamation.getImportanceReclamation() == 0){
            System.out.println("U Have Smthg Wrong!!");
            return false;
        }
        System.out.println("All Is Good !!");
        return true;
    }
    
    
     public boolean SendMail(String code)
    {
        String password = "fyfwedvsxlbmyylo";
        // Ur email password : pexwoavpodyhtkcn
        String from,to,host,sub,content;
        from = "pidevmailing@gmail.com";
        to = "zodias176@gmail.com";
        host="localhost";
        sub="This is a test mail!";
        content="Reclamation Bien Envoyée";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        javax.mail.Session session=javax.mail.Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
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
     
      public boolean CloseRec(String idRec){
        String query = "DELETE FROM RECLAMATION WHERE id_reclamation = " + idRec + "";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
      
      
      public boolean ContactUserReclam(String email,String body)
    {
        String nom = Session.getNom();
      String prenom = Session.getPrenom();
        String from,to,host,sub,content;
        from = "pidevmailing@gmail.com";
        String password = "fyfwedvsxlbmyylo";
        to = email;
        host="localhost";
                // Session
            sub="Répondre à votre signalement";
            content="Bonjour Mr/Mme "+nom+" "+prenom+ ". "+body+"";
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            javax.mail.Session session=javax.mail.Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication(from,password);
                        }
                    }
            );
            try {
                MimeMessage m =new MimeMessage(session);
                m. setFrom(from);
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
