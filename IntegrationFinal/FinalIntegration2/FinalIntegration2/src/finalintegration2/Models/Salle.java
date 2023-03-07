/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.Models;

public class Salle {
    private int id;
    private String nom_salle, adresse;
    private String num_places;

    public Salle() {
    }
    
    public Salle(String nom_salle, String adresse,String num_places) {
        this.nom_salle = nom_salle;
        this.adresse = adresse;
        this.num_places=num_places;
    }

    public Salle(int id, String nom_salle, String adresse,String num_places) {
        this.id = id;
        this.nom_salle = nom_salle;
        this.adresse = adresse;
        this.num_places=num_places;
    }

    public int getId() {
        return id;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public String getAdresse() {
        return adresse;
    }
    public String getNum_places(){
    return num_places;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_Salle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
public void setNum(String num_places) {
        this.num_places = num_places;
    }
    @Override
    public String toString() {
        return "Salle{" + "nom_salle=" + nom_salle + ", adresse=" + adresse+", num_places="+ num_places+ '}';
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
        final Salle other = (Salle) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}