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
public class Offre {
    private int id_offr ;
    private String nomfilm_offr ;
    private String contenu_offr;
    private String datedebut_offr , datefin_offr ;
    private String code_offr ;

    public Offre() {
    }

    public Offre(int id_offr, String nomfilm_offr, String contenu_offr, String datedebut_offr, String datefin_offr, String code_offr) {
        this.id_offr = id_offr;
        this.nomfilm_offr = nomfilm_offr;
        this.contenu_offr = contenu_offr;
        this.datedebut_offr = datedebut_offr;
        this.datefin_offr = datefin_offr;
        this.code_offr = code_offr;
    }

    public Offre(String nomfilm_offr, String contenu_offr, String datedebut_offr, String datefin_offr, String code_offr) {
        this.nomfilm_offr = nomfilm_offr;
        this.contenu_offr = contenu_offr;
        this.datedebut_offr = datedebut_offr;
        this.datefin_offr = datefin_offr;
        this.code_offr = code_offr;
    }

    public int getId_offr() {
        return id_offr;
    }

    public void setId_offr(int id_offr) {
        this.id_offr = id_offr;
        
    }

    public String getNomfilm_offr() {
        return nomfilm_offr;
    }

    public void setNomfilm_offr(String nomfilm_offr) {
        this.nomfilm_offr = nomfilm_offr;
    }
    
    

    public String getContenu_offr() {
        return contenu_offr;
    }

    public void setContenu_offr(String contenu_offr) {
        this.contenu_offr = contenu_offr;
    }

    public String getDatedebut_offr() {
        return datedebut_offr;
    }

    public void setDatedebut_offr(String datedebut_offr) {
        this.datedebut_offr = datedebut_offr;
    }

    public String getDatefin_offr() {
        return datefin_offr;
    }

    public void setDatefin_offr(String datefin_offr) {
        this.datefin_offr = datefin_offr;
    }

    public String getCode_offr() {
        return code_offr;
    }

    public void setCode_offr(String code_offr) {
        this.code_offr = code_offr;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id_offr;
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
        final Offre other = (Offre) obj;
        if (this.id_offr != other.id_offr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offre{" + "id_offr=" + id_offr + ", id_film=" + nomfilm_offr + ", contenu_offr=" + contenu_offr + ", datedebut_offr=" + datedebut_offr + ", datefin_offr=" + datefin_offr + ", code_offr=" + code_offr + '}';
    }

     
    
 
    
}


