/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.services;

import edu.stock.entites.Avis;
import edu.stock.entites.Categorie;
import edu.stock.utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author maidi
 */
public class ServiceAvis implements IService <Avis>{

    Connection cnx=ConnexionBD.getInstance().getCnx();
    
    @Override
    public void ajouter(Avis a) {
            try {
                String req = "INSERT INTO `avis` (`valeur`,`id_user`) VALUES (?,?)";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(3, a.getIdUser());
                ps.setInt(2, a.getValeur());
                ps.executeUpdate();
                System.out.println("avis ajouté avec succès !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @Override
    public List<Avis> afficherTous() {
        List<Avis> list = new ArrayList<>();
        try {
            String req = "Select * from avis";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Avis a = new Avis(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                list.add(a);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list; 
    }

    @Override
    public Avis rechercherUnParId(int id_user) {
        Avis a = null;
        try {
            String req = "Select * from avis";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                a = new Avis(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }

    @Override
    public void modifier(Avis a) {
        try {
            String req = "UPDATE `avis` SET `valeur` = '" + a.getValeur()+ "' WHERE `avis`.`id` = " + a.getIdAvis();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int idAvis) {
        try {
            String req = "DELETE FROM `avis` WHERE id = " + idAvis;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Avis supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //metier tri par valeur
    public List<Avis> TriParValeur() {
        List<Avis> LesAvis = this.afficherTous();
        Collections.sort(LesAvis, new Comparator<Avis>() {
            @Override
            public int compare(Avis o1, Avis o2) {
            return Integer.compare(o2.getValeur(),o1.getValeur());
            }
        });
        return LesAvis;
    }
    
    
    //APi
    public boolean SendMail()
    {
        String password = "fyfwedvsxlbmyylo"; //code secu
        // Ur email password : pexwoavpodyhtkcn //mdp de pidev
        String from,to,host,sub,content;
        from = "pidevmailing@gmail.com";
        to = "meryam.saidi@esprit.tn";
        host="localhost";
        sub="Réclamation envoyée";
        content="Votre Avis a bien été envoyée. Ce Message est automatique.";
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
