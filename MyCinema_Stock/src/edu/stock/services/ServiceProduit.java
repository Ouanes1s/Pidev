/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.services;

import edu.stock.entites.Produit;
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

/**
 *
 * @author maidi
 */
public class ServiceProduit implements IService<Produit> {

    Connection cnx=ConnexionBD.getInstance().getCnx();
    
    @Override
    public void ajouter(Produit p) {
        if((this.exist(p.getCodeProd()))){
            System.out.println("ce produit existe déjà !");
        }else{
        try {
            String req ="INSERT INTO `produit`(`code`, `nom`, `quantite`, `prix`, `etat`, `description`, `image`, `idCategorie`, `idOffre`) "
                + "VALUES ('" + p.getCodeProd() + "','" + p.getNomProd() + "','" + p.getQuantiteProd() + "','" + p.getPrixProd() + "'"
                + ",'" + p.getEtatProd() + "','" + p.getDescriptionProd() + "','" + p.getImgProd() + "','" + p.getIdCategorie() + "','" + p.getIdOffre() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Prduit ajouté avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
        }
    }

    
    //metier verification de l'existance d'une categorie par son code (string)
    public boolean exist(String code) {
        try {
            String req = "Select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (code.equals(rs.getString(2))){
                    return true;
                }        
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    
    //metier verification de l'existance d'une categorie par son id
    public boolean exist(int id) {
        try {
            String req = "Select * from produit";
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
   
    
    @Override
    public List<Produit> afficherTous() {
        List<Produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
 
    
    public ArrayList<Produit> afficherProduitsParCategorie(int idCategorie) {
        ArrayList<Produit> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM produit WHERE idCategorie ="+idCategorie;
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
              list.add(new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));             
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (list.isEmpty()) {
            System.out.println("Cette categorie est vide");
        }
        return list;
    }
     
    
    @Override
    public Produit rechercherUnParId(int idProd) {
        try {
            String req = "SELECT * FROM produit WHERE idProduit = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1,idProd); //ds #1 cherche idProd passé en para
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Produit p = new Produit();
                p.setIdProd(rs.getInt(1));
                p.setCodeProd(rs.getString(2));
                p.setNomProd(rs.getString(3));
                p.setQuantiteProd(rs.getInt(4));
                p.setPrixProd(rs.getFloat(5));
                p.setEtatProd(rs.getInt(6));
                p.setDescriptionProd(rs.getString(7));
                p.setImgProd(rs.getString(8));
                p.setIdCategorie(rs.getInt(9));
                p.setIdOffre(rs.getInt(10));
                return p;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        System.out.println("Ce produit n'existe pas !");
        return null;        
    }
    
    
    @Override
    public void modifier(Produit p) {
       if(!(this.exist(p.getCodeProd()))){
            System.out.println("ce produit n'existe pas !");
        }else{ 
        try {
            String req="UPDATE `produit` SET `code`='" + p.getCodeProd() + "',`nom`='" + p.getNomProd() + "',`quantite`='" + p.getQuantiteProd() + "',`prix`='" + p.getPrixProd() + "',`etat`='" + p.getEtatProd() + "',`description`='" + p.getDescriptionProd() + "',`image`='" + p.getImgProd() + "',`idCategorie`='" + p.getIdCategorie() +"',`idOffre`='" + p.getIdOffre() + "' "
                    + "WHERE `produit`.`idProduit` = " + p.getIdProd();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit mis à jour avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    }
    
    
    @Override
    public void supprimer(int idProd) {
       if(!(this.exist(idProd))){
            System.out.println("ce produit n'existe pas !");
        }else{     
        try {
            String req = "DELETE FROM `produit` WHERE idProduit = " + idProd;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
    
    //metier nb de produits par categorie
    public String nbrProdCategorie(int idCategorie){
        return "Le nombre de produits par cette catégorie = " + (this.afficherProduitsParCategorie(idCategorie)).size();
    }
    
    
    //metier tri produit par nom
    public List<Produit> TriParNom() {
        List<Produit> Produits = this.afficherTous();
        Collections.sort(Produits, new Comparator<Produit>() {
            @Override
            public int compare(Produit o1, Produit o2) {
            return o1.getNomProd().compareTo(o2.getNomProd());
            }
        });
        return Produits;
    }
    
    
    //metier tri produit par nom si egaus alors par plus bas prix
    public List<Produit> TriParNomEtPrix() {
        List<Produit> Produits = this.afficherTous();
        Collections.sort(Produits, new Comparator<Produit>() {
            @Override
            public int compare(Produit o1, Produit o2) {
            int sorted=o1.getNomProd().compareTo(o2.getNomProd());
            if(sorted==0){
                sorted=Float.compare(o1.getPrixProd(),o2.getPrixProd());
            }
            return sorted;
            }
        });
        return Produits;
    }    

    
    //metier tri produit par le prix le plus haut
    public List<Produit> TriParPrix() {
        List<Produit> Produits = this.afficherTous();
        Collections.sort(Produits, (Produit o1, Produit o2) -> Float.compare(o1.getPrixProd(),o2.getPrixProd()) 
        );
        Collections.reverse(Produits);
        return Produits;
    }
 
    
    /*
     @Override     
    public Produit rechercherUnParId(int idProd) {
        Produit p = null;
        try {
            String req = "Select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10));                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return p;
    }    
    */   //dima trajaa ekher produit
    
       
}
