/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.entities;
 
import java.util.Comparator;


/**
 *
 * @author Amine Khalfaoui
 */
public class Reservation implements Comparator<Reservation> {
     
    private int id_res ;
    private String nom_res , prenom_res, email_res;
    private String typeticket_res ;
    private String nom_evnmt ;
    private String code_offr ;
     
    private String date_res ;
    

    public Reservation() {
    }

//    public Reservation(int id_res, String nom_res, String prenom_res, String email_res, String typeticket_res, String nom_evnmt,String date_res,  String code_offr) {
//        this.id_res = id_res;
//        this.nom_res = nom_res;
//        this.prenom_res = prenom_res;
//        this.email_res = email_res;
//        this.typeticket_res = typeticket_res;
//        this.nom_evnmt = nom_evnmt;
//        this.code_offr = code_offr;
//        this.date_res = date_res;
//    }

    public Reservation(String nom_res, String prenom_res, String email_res, String typeticket_res, String nom_evnmt, String code_offr, String date_res) {
        this.nom_res = nom_res;
        this.prenom_res = prenom_res;
        this.email_res = email_res;
        this.typeticket_res = typeticket_res;
        this.nom_evnmt = nom_evnmt;
        this.code_offr = code_offr;
        this.date_res = date_res;
    }

    

    public String getNom_evnmt() {
        return nom_evnmt;
    }

    public void setNom_evnmt(String nom_evnmt) {
        this.nom_evnmt = nom_evnmt;
    }

   

    

    public String getEmail_res() {
        return email_res;
    }

    public void setEmail_res(String email_res) {
        this.email_res = email_res;
    }

    public String getTypeticket_res() {
        return typeticket_res;
    }

    public void setTypeticket_res(String typeticket_res) {
        this.typeticket_res = typeticket_res;
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
        return "Reservation{" + "id_res=" + id_res + ", nom_res=" + nom_res + ", prenom_res=" + prenom_res + ", email_res=" + email_res + ", typeticket_res=" + typeticket_res + ", nom_evnmt=" + nom_evnmt + ", code_offr=" + code_offr + ", date_res=" + date_res + '}';
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

   

    @Override
    public int compare(Reservation r1, Reservation r2) {
        return r1.getEmail_res().compareTo(r2.getEmail_res());       }
    

    
}
