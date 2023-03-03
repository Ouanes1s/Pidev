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
public class Blog implements Comparator<Blog> {
    
    private int id_blg ;
    private String titre_blg;
    private String email_blg ;
    private String contenu_blg ;

//    public Blog(int id_blg, String titre_blg, int email_user, String contenu_blg) {
//        this.id_blg = id_blg;
//        this.titre_blg = titre_blg;
//        this.email_user = email_user;
//        this.contenu_blg = contenu_blg;
//    }

    public Blog(String titre_blg, String email_blg, String contenu_blg) {
        this.titre_blg = titre_blg;
        this.email_blg = email_blg;
        this.contenu_blg = contenu_blg;
    }

    public Blog() {
     }

    public int getId_blg() {
        return id_blg;
    }

    public void setId_blg(int id_blg) {
        this.id_blg = id_blg;
    }

    public String getTitre_blg() {
        return titre_blg;
    }

    public void setTitre_blg(String titre_blg) {
        this.titre_blg = titre_blg;
    }

    public String getEmail_blg() {
        return email_blg;
    }

    public void setId_user(String email_blg) {
        this.email_blg = email_blg;
    }

    public String getContenu_blg() {
        return contenu_blg;
    }

    public void setContenu_blg(String contenu_blg) {
        this.contenu_blg = contenu_blg;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id_blg;
        
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
        final Blog other = (Blog) obj;
        if (this.id_blg != other.id_blg) {
            return false;
        }
      
        return true;
    }

    @Override
    public String toString() {
        return "Blog{" + "id_blg=" + id_blg + ", titre_blg=" + titre_blg + ", email_blg=" + email_blg + ", contenu_blg=" + contenu_blg + '}';
    }

    @Override
    public int compare(Blog o1, Blog o2) {
        return o1.getTitre_blg().compareTo(o2.getTitre_blg());    }
    
    
    
}
