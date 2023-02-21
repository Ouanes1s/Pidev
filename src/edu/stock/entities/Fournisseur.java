/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.entities;

/**
 *
 * @author maidi
 */
public class Fournisseur {
    private int Id_Fournisseur;
    private String Nom_Fournisseur;
    private String Adresse_Fournisseur;
    private int Téléphone;

    public Fournisseur() {
    }

    public Fournisseur(String Nom_Fournisseur, String Adresse_Fournisseur, int Téléphone) {
        this.Nom_Fournisseur = Nom_Fournisseur;
        this.Adresse_Fournisseur = Adresse_Fournisseur;
        this.Téléphone = Téléphone;
    }

    public Fournisseur(int Id_Fournisseur, String Nom_Fournisseur, String Adresse_Fournisseur, int Téléphone) {
        this.Id_Fournisseur = Id_Fournisseur;
        this.Nom_Fournisseur = Nom_Fournisseur;
        this.Adresse_Fournisseur = Adresse_Fournisseur;
        this.Téléphone = Téléphone;
    }

    public int getId_Fournisseur() {
        return Id_Fournisseur;
    }

    public String getNom_Fournisseur() {
        return Nom_Fournisseur;
    }

    public void setNom_Fournisseur(String Nom_Fournisseur) {
        this.Nom_Fournisseur = Nom_Fournisseur;
    }

    public String getAdresse_Fournisseur() {
        return Adresse_Fournisseur;
    }

    public void setAdresse_Fournisseur(String Adresse_Fournisseur) {
        this.Adresse_Fournisseur = Adresse_Fournisseur;
    }

    public int getTéléphone() {
        return Téléphone;
    }

    public void setTéléphone(int Téléphone) {
        this.Téléphone = Téléphone;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "Nom_Fournisseur=" + Nom_Fournisseur + ", Adresse_Fournisseur=" + Adresse_Fournisseur + ", Téléphone=" + Téléphone + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.Id_Fournisseur;
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
        final Fournisseur other = (Fournisseur) obj;
        if (this.Id_Fournisseur != other.Id_Fournisseur) {
            return false;
        }
        return true;
    }
    
}
