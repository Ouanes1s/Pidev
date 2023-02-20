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
public class Blog {
    
    private int id_blg ;
    private String titre_blg;
    private int email_user ;
    private String contenu_blg ;

    public Blog(int id_blg, String titre_blg, int email_user, String contenu_blg) {
        this.id_blg = id_blg;
        this.titre_blg = titre_blg;
        this.email_user = email_user;
        this.contenu_blg = contenu_blg;
    }

    public Blog(String titre_blg, int email_user, String contenu_blg) {
        this.titre_blg = titre_blg;
        this.email_user = email_user;
        this.contenu_blg = contenu_blg;
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

    public int getEmail_user() {
        return email_user;
    }

    public void setId_user(int email_user) {
        this.email_user = email_user;
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
        hash = 23 * hash + this.email_user;
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
        if (this.email_user != other.email_user) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Blog{" + "id_blg=" + id_blg + ", titre_blg=" + titre_blg + ", email_user=" + email_user + ", contenu_blg=" + contenu_blg + '}';
    }
    
    
    
}
