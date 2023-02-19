/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.user.services;
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
 * @author Ameni
 */
public class EnvoyerEmail {
    
    
        public static void envoyer(String destinataire, String code,String name) throws MessagingException {
            
            String username = "mohamedouanes.chebil@esprit.tn";
            String password ="213JMT5601";
            System.out.println("Entrain d'envoyer un email de Récuperation de MDP !! ");
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
            
            Message message = prepareMessage(session,username,destinataire,code,name);
            Transport.send(message);
            System.out.println("Message envoyé !!");
}
        
    

    private static Message prepareMessage(Session session, String username,String destinataire, String code,String name) throws MessagingException {
        
        try { 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(destinataire));
            message.setSubject("Mot de passe oublié");
            message.setText("Mr "+name+" ,     Votre mot de passe est le suivant: "+code+"\nVotre compte n'est pas accessible sans ce Mot De passe");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(EnvoyerEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    //Etape 4 : Tester la méthode
    
}
/**
 *
 * @author chebi
 */

