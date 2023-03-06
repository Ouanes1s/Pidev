/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.services;

import edu.stock.entites.Categorie;
import edu.stock.entites.Produit;
import java.sql.Connection;
import edu.stock.utils.ConnexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maidi
 */
public class ServiceCategorie implements IService <Categorie>{
    
    Connection cnx=ConnexionBD.getInstance().getCnx();
    
    @Override
    public void ajouter(Categorie c) {
        if((this.exist(c.getNomCategorie()))){
            System.out.println("Cette catégorie existe déjà !");
        }else{
            try {
                String req = "INSERT INTO `categorie` (`nom`) VALUES (?)";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, c.getNomCategorie());
                ps.executeUpdate();
                System.out.println("Catégorie crée avec succès !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public List<Categorie> afficherTous() {
        List<Categorie> list = new ArrayList<>();
        try {
            String req = "Select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt(1), rs.getString(2));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list; 
    }
 
    @Override
    public Categorie rechercherUnParId(int idCategorie) {
        Categorie c = null;
        try {
            String req = "Select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                c = new Categorie(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }
    
    public Categorie rechercher(String nom) {
        Categorie c = new Categorie();
        String sql = "SELECT * FROM categorie WHERE nom LIKE ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, c.getNomCategorie());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Categorie(rs.getInt("idCategorie"), rs.getString("nom"));
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
    
    @Override
    public void modifier(Categorie c) {
        try {
            String req = "UPDATE `categorie` SET `nom` = '" + c.getNomCategorie()+ "' WHERE `categorie`.`id` = " + c.getIdCategorie();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Catégorie modiifiée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override 
    public void supprimer(int idCategorie) {
        Categorie c=new Categorie();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();       
        try {
            String req = "DELETE FROM `categorie` WHERE id = " + idCategorie;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Catégorie supprimée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      
    }
    
    
    ///metier verification de l'existance d'une categorie par son nom
    public boolean exist(String nom) {
        try {
            String req = "Select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (nom.equals(rs.getString("nom"))){
                    return true;
                }        
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    //metier tri par nom
    public List<Categorie> TriParNom() {
        List<Categorie> Categories = this.afficherTous();
        Collections.sort(Categories, new Comparator<Categorie>() {
            @Override
            public int compare(Categorie o1, Categorie o2) {
            return o1.getNomCategorie().compareTo(o2.getNomCategorie());
            }
        });
        return Categories;
    }
    
    
    /*
   //metier Calculer le nombre de produits par categorie
    public int NbrCategorie(int idCategorie) {
        
        ArrayList<Categorie> listProdParCat = new ArrayList<>();
        try {
            String req = "select * from categorie where id="+ idCategorie;
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Produit p=new  Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
                listProdParCat.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (listProdParCat.isEmpty()) {
            System.out.println("Il y a aucun produit ");
        }
        return listProdParCat.size();
    }
    */



}
