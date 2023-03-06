/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.services;

import edu.stock.entites.Offre;
import edu.stock.entites.Produit;
import edu.stock.utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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
public class ServiceOffre implements IService<Offre> {

    Connection cnx=ConnexionBD.getInstance().getCnx();
    
    @Override
    public void ajouter(Offre o) {
        if((this.exist(o.getPoucentage()))){
            System.out.println("cette offre existe déjà !");
        }else{
        try {
            //String req = "INSERT INTO `categorie` (`nom`) VALUES (?)";
            String requete = "INSERT INTO offre (idOffre,pourcentage,idProduit) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,o.getIdOffre());
            pst.setInt(2,o.getPoucentage());
            pst.setInt(3, o.getIdProd());
            pst.executeUpdate();
            System.out.println("Offre ajoutée avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
    }
    
    
    //metier verification de l'existance d'une offre par son pourcent (int)
    public boolean exist(int pourcent) {
        try {
            String req = "Select * from offre";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (pourcent==(rs.getInt(2))){
                    return true;
                }        
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    
    @Override
    public List<Offre> afficherTous() {
        List<Offre> offres = new ArrayList<>();
        String req = "Select * from offre";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
             offres.add(new Offre( rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }

    
    @Override
    public Offre rechercherUnParId(int idOffre) {
        try {
            String req = "SELECT * FROM offre WHERE idOffre = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1,idOffre); //ds #1 cherche idProd passé en para
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Offre o = new Offre();
                o.setIdOffre(rs.getInt(1));
                o.setPoucentage(rs.getInt(2));
                o.setIdProd(rs.getInt(3));
                return o;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        System.out.println("Cette offre n'existe pas !");
        return null;
    }
    

    @Override
    public void modifier(Offre o) {
        if(!(this.exist(o.getPoucentage()))){
            System.out.println("cette offre n'existe pas !");
        }else{        
        try { 
            String requete = "UPDATE `offre` SET pourcentage=?, idProduit=? WHERE idOffre= ?";;
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3,o.getIdProd());
            pst.setInt(1, o.getIdOffre());
            pst.setInt(2, o.getPoucentage());           
            pst.executeUpdate();
            System.out.println("Offre modifiée avec succés \n ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    }

    
    @Override
    public void supprimer(int idOffre) {
       if(!(this.existId(idOffre))){
            System.out.println("cette offre n'existe pas !");
        }else{     
        try {
            String req = "DELETE FROM `offre` WHERE idOffre = " + idOffre;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }    

    
    //metier verification de l'existance d'une offre par son id (int)
    public boolean existId(int id) {
        try {
            String req = "Select * from offre";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (id==(rs.getInt(1))){
                    return true;
                }        
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }    
    
    
    //metier tri offre par pourcentage le plus haut
    public List<Offre> TriParPourcentage() {
        List<Offre> Offres = this.afficherTous();
        Collections.sort(Offres, (Offre o1, Offre o2) -> Float.compare(o1.getPoucentage(),o2.getPoucentage()) 
        );
        Collections.reverse(Offres);
        return Offres;
    }

 /*   
    //metier calcul offre    
    public float calculOffre(int idProduit, int Pourcentage) {
        Offre o=new Offre();
        Produit p=new Produit();
        try {
            String req = "SELECT * FROM produit WEHERE idProduit=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idProduit);
            ResultSet rs = pst.executeQuery();
           // pst.setInt(2, rs.getInt(10));
            //p.setIdProd(rs.getInt(1));
            p.setPrixProd(rs.getInt(5));
            p.setIdOffre(rs.getInt(10));
            
            String req2 = "SELECT `pourcentage` From  offre WHERE idOffre = "+p.getIdOffre();
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            ResultSet rs2 = pst2.executeQuery();
            o.setPoucentage(rs2.getInt(2));

           // cnx.close();
                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }         
        float valeur = p.getPrixProd() * (o.getPoucentage() / 100);
        return p.getPrixProd() - valeur;
    }
    
 */ 
    //APi
    public boolean SendMail()
    {
        String password = "fyfwedvsxlbmyylo"; //code secu
        // Ur email password : pexwoavpodyhtkcn //mdp de pidev
        String from,to,host,sub,content;
        from = "pidevmailing@gmail.com";
        to = "meryam.saidi@esprit.tn";
        host="localhost";
        sub="Offre envoyée";
        content="Vous beneficiez d'une offre sur le produit. Ce Message est automatique.";
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
    
    
    
    
    
    
    /*  
    @Override
    public void supprimer(int idOffre) {
        if((this.existid(idOffre))){
            System.out.println("cette offre n'existe pas !");
        }else{          
        try {
         String requete = "DELETE FROM offre WHERE idOffre=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,idOffre);
            pst.executeUpdate();
            System.out.println("Offre supprimée avec succés  ! \n");
        } 
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    } 
    */
    
}
