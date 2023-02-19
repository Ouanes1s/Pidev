/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.user.entities;

import java.util.Objects;

/**
 *
 * @author chebi
 */
public class User {

    private int id_user;
    private String nom_user;
    private String prenom_user;
    private String cin_user;
    private String email_user;
    private String mdp_user;

    public User() {
    }
    
    

    public User(int id_user, String nom_user, String prenom_user, String cin_user, String email_user, String mdp_user) {
        this.id_user = id_user;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.cin_user = cin_user;
        this.email_user = email_user;
       
        this.mdp_user = mdp_user;
    }

    public User(String nom_user, String prenom_user, String cin_user, String email_user, String mdp_user) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.cin_user = cin_user;
        this.email_user = email_user;
    
        this.mdp_user = mdp_user;
    }

    public User(int aInt, String string, String string0, String string1, String string2, String string3, String string4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.nom_user, other.nom_user)) {
            return false;
        }
        if (!Objects.equals(this.prenom_user, other.prenom_user)) {
            return false;
        }
        if (!Objects.equals(this.cin_user, other.cin_user)) {
            return false;
        }
        if (!Objects.equals(this.email_user, other.email_user)) {
            return false;
        }
        if (!Objects.equals(this.mdp_user, other.mdp_user)) {
            return false;
        }
        return true;
    }

    

    public int getId_user() {
        return id_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public String getCin_user() {
        return cin_user;
    }

    public String getEmail_user() {
        return email_user;
    }

   
    public String getMdp_user() {
        return mdp_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public void setCin_user(String cin_user) {
        this.cin_user = cin_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }



    public void setMdp_user(String mdp_user) {
        this.mdp_user = mdp_user;
    }

    public String toString() {
        return "User{" + "nom=" + nom_user + ", prenom=" + prenom_user +", role" +'}' ;
    }

  
    

    }


