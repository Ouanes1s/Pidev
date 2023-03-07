/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class Evenements {
    
    private int id ,nbreplaces,nbreparticipants,id_cat ;
    private String lieu ,titre , image ;
    private Date DateDebut , DateFin ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbreplaces() {
        return nbreplaces;
    }

    public void setNbreplaces(int nbreplaces) {
        this.nbreplaces = nbreplaces;
    }

    public int getNbreparticipants() {
        return nbreparticipants;
    }

    public void setNbreparticipants(int nbreparticipants) {
        this.nbreparticipants = nbreparticipants;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    @Override
    public String toString() {
        return "Evenements{" + "id=" + id + ", nbreplaces=" + nbreplaces + ", nbreparticipants=" + nbreparticipants + ", lieu=" + lieu + ", titre=" + titre + ", image=" + image + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + '}';
    }

    public Evenements() {
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }
    
    
    
    
    
}
