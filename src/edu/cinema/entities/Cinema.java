/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinema.entities;

public class Cinema {
    private int id;
    private String nom_cinema, adresse;
    private String num;

    public Cinema() {
    }
    
    public Cinema(String nom_cinema, String adresse,String num) {
        this.nom_cinema = nom_cinema;
        this.adresse = adresse;
        this.num=num;
    }

    public Cinema(int id, String nom_cinema, String adresse,String num) {
        this.id = id;
        this.nom_cinema = nom_cinema;
        this.adresse = adresse;
        this.num=num;
    }

    public int getId() {
        return id;
    }

    public String getNom_cinema() {
        return nom_cinema;
    }

    public String getAdresse() {
        return adresse;
    }
    public String getNum(){
    return num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_cinema(String nom_cinema) {
        this.nom_cinema = nom_cinema;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setNum(String num){
    this.num=num;
    }

    @Override
    public String toString() {
        return "Cinema{" + "nom_cinema=" + nom_cinema + ", adresse=" + adresse+", num="+ num+ '}';
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
        final Cinema other = (Cinema) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
