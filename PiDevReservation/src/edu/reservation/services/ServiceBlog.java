/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.services;
import edu.reservation.entities.Blog;
import edu.reservation.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amine Khalfaoui
 */
public class ServiceBlog implements IServices<Blog> {
    
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Blog r) {
                if (VerifTitre(r.getTitre_blg())!=0) {
                System.out.println("Titre deja existe "); }
        try {
            String req = "INSERT INTO `blog`( `titre_blg`, `email_user`, `contenu_blg`  ) VALUES (?,?,? )";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, r.getTitre_blg());
            ps.setInt(2, r.getEmail_user());
             ps.setString(3, r.getContenu_blg());
             
  
                
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }

    @Override
    public void supprimer(int id_blg) {
        try {
            String req = "DELETE FROM `blog` WHERE id_blg = " + id_blg;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Blog r) {
        try {
            String req = "UPDATE `blog` SET `titre_blg` = '" + r.getTitre_blg() + "', `email_user` = '" + r.getEmail_user() + "', `contenu_blg` = '" + r.getContenu_blg() +    "' WHERE `blog`.`id_blg` = " + r.getId_blg();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Blog updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }     

    @Override
    public List<Blog> getAll() {
    List<Blog> list = new ArrayList<>();
        try {
            String req = "Select * from blog";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Blog r = new Blog(rs.getInt("id_blg"), rs.getString("titre_blg"), rs.getInt("id_user"), rs.getString("contenu_blg")  );
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;      }

    @Override
    public Blog getOneById(int id) {
        Blog r = null;
        try {
            String req = "Select * from blog";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (id==rs.getInt("id_blg")){
            r = new Blog (rs.getInt("id_blg"), rs.getString("titre_blg"),rs.getInt("id_user"),
                        rs.getString("contenu_blg")  );            
                }
           }
           } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return r;
 
       }  
    
     @Override
    public int VerifTitre(String titre) {
        Blog r = null;
         int nb = 0;
        try {
            String req = "Select * from blog";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (titre.equals(rs.getString("titre_blg"))){
           nb=1;}
                
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;

        
    }

    public int VerifOffr(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int VerifOffr(String code, String nom, String prenom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Blog> trierOffreParDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
 }

 

 
    
 
