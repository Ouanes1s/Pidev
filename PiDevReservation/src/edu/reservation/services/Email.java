/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.services;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Amine Khalfaoui
 */
public class Email {
            public static void envoyer(String destinataire, String date,String name) throws MessagingException {
            
            String username = "amine.khalfaoui@esprit.tn";
            String password ="JaMiLa23919011";
            System.out.println("Envoie de la reservation ar mail en cours !! ");
            // Etape 1 : Création de la session
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port","587");
            
            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);}
            });
            
            Message message = prepareMessage(session,username,destinataire,date,name);
            Transport.send(message);
            System.out.println("Message envoyé !!");
}
        
    

    private static Message prepareMessage(Session session, String username,String destinataire, String date,String name) throws MessagingException {
        
        try { 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(destinataire));
            message.setSubject("Confirmation Reservation");
            message.setText("Bonjour Mr/Mme "+name+" ,     Votre reservation est confirme pour le :"+date+"\nVous trouverez ci-joint votre ticket!");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger( Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
