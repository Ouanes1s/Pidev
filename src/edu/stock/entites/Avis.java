/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.entites;

/**
 *
 * @author maidi
 */
public class Avis {
    private int idAvis;
    private int valeur;
    private int id_user;

    public Avis() {
    }

    public Avis(int idAvis, int valeur, int idUser) {
        this.idAvis = idAvis;
        this.valeur = valeur;
        this.id_user = idUser;
    }

    public Avis(int valeur, int idUser) {
        this.valeur = valeur;
        this.id_user = idUser;
    }

    public Avis(int valeur) {
        this.valeur = valeur;
    }
    

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int idUser) {
        this.id_user = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idAvis;
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
        final Avis other = (Avis) obj;
        if (this.idAvis != other.idAvis) {
            return false;
        }
        if (this.valeur != other.valeur) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avi:" + "valeur=" + valeur + ", idUser=" + id_user + "\n";
    }
    
}
