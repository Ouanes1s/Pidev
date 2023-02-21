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
public class Offre {
    private int idOffre;
    private int poucentage;
    private int idProd;

    public Offre() {
    }

    public Offre(int idOffre, int poucentage, int idProd) {
        this.idOffre = idOffre;
        this.poucentage = poucentage;
        this.idProd = idProd;
    }

    public Offre(int poucentage, int idProd) {
        this.poucentage = poucentage;
        this.idProd = idProd;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getPoucentage() {
        return poucentage;
    }

    public void setPoucentage(int poucentage) {
        this.poucentage = poucentage;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.idOffre;
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
        if (this.idOffre != other.idOffre) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offre:" + "poucentage=" + poucentage + ", idProd=" + idProd + "\n";
    }
       
}
