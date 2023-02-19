/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.entities;
 

/**
 *
 * @author Amine Khalfaoui
 */
public class Reservation {
     
    private int id_res ;
    private String nom_res , prenom_res;
    private String typeticket_res ;
    private int id_film ;
    private String code_offr ;
     
    private String date_res ;
    

    public Reservation() {
    }
    
    public Reservation(String nom_res, String prenom_res, String typeticket_res, int id_film,  String date_res, String code_offr) {
        this.nom_res = nom_res;
        this.prenom_res = prenom_res;
        this.typeticket_res = typeticket_res;
        this.id_film = id_film;
        this.code_offr = code_offr;
        this.date_res = date_res;
        
    }

    public Reservation(int id_res, String nom_res, String prenom_res, String typeticket_res, int id_film,   String date_res, String code_offr ) {
        this.id_res = id_res;
        this.nom_res = nom_res;
        this.prenom_res = prenom_res;
        this.typeticket_res = typeticket_res;
        this.id_film = id_film;
        this.code_offr = code_offr;
         
         
        this.date_res = date_res;

    }

    public int getId_res() {
        return id_res;
    }

    public String getNom_res() {
        return nom_res;
    }

    public String getPrenom_res() {
        return prenom_res;
    }
    
    public String getTypeTicket_res() {
        return typeticket_res;
    }
     
    public int getId_film() {
        return id_film;
    }
    
   
     public String getDate_res() {
        return date_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public void setNom_res(String nom_res) {
        this.nom_res = nom_res;
    }

    public void setPrenom_res(String prenom_res) {
        this.prenom_res = prenom_res;
    }
    
    public void setTypeTicket_res(String typeticket_res) {
        this.typeticket_res = typeticket_res;
    }
    
    public void getId_film(int id_film) {
        this.id_film = id_film;
    }
    
    
    
    public void getDate_res(String date_res) {
        this.date_res = date_res;
    }

    public String getCode_offr() {
        return code_offr;
    }

    public void setCode_offr(String code_offr) {
        this.code_offr = code_offr;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_res=" + id_res + ", nom_res=" + nom_res + ", prenom_res=" + prenom_res + ", typeticket_res=" + typeticket_res + ", id_film=" + id_film + ", code_offr=" + code_offr + ", date_res=" + date_res + '}';
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
        final Reservation other = (Reservation) obj;
        if (this.id_res != other.id_res ) {
            return false;
        }
        return true;
    }
    

    
}
