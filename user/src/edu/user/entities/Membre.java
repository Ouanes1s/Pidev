package edu.user.entities;


import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chebi
 */
public class Membre extends User{
private String Date_inscri ;
    public Membre() {
    }

    public Membre(int id_user, String nom_user, String prenom_user, String cin_user, String email_user, String mdp_user,String Date_inscri) {
        super(id_user, nom_user, prenom_user, cin_user, email_user, mdp_user);
        this.Date_inscri = Date_inscri ;
    }

    public Membre(String nom_user, String prenom_user, String cin_user, String email_user, String mdp_user, String Date_inscri) {
        super(nom_user, prenom_user, cin_user, email_user, mdp_user);
        this.Date_inscri = Date_inscri ;
    }

    public Membre(int aInt, String string, String string0, String string1, String string2, String string3, String string4, String string5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Membre(int aInt, String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6, String string7) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Membre(int aInt, String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getDate_inscri() {
        return Date_inscri;
    }

    public void setDate_inscri(String Date_inscri) {
        this.Date_inscri = Date_inscri;
    }

    @Override
    public String toString() {
        return "Membre{"+ "nom_user=" + getNom_user() + ", prenom_user=" + getPrenom_user() + ", cin_user=" + getCin_user() + ", email_user=" + getEmail_user() + ", mdp_user=" + getMdp_user() + '}'+ "Date_inscri=" + Date_inscri + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.Date_inscri);
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
        final Membre other = (Membre) obj;
        if (!Objects.equals(this.Date_inscri, other.Date_inscri)) {
            return false;
        }
        return true;
    }
}

    

