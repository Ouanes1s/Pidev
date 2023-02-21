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
public class Produit_Avis {
    private int idProd_Avis;
    private int idProd;
    private int idAvis;

    public Produit_Avis() {
    }

    public Produit_Avis(int idProd_Avis, int idProd, int idAvis) {
        this.idProd_Avis = idProd_Avis;
        this.idProd = idProd;
        this.idAvis = idAvis;
    }

    public Produit_Avis(int idProd, int idAvis) {
        this.idProd = idProd;
        this.idAvis = idAvis;
    }

    public int getIdProd_Avis() {
        return idProd_Avis;
    }
/*
    public void setIdProd_Avis(int idProd_Avis) {
        this.idProd_Avis = idProd_Avis;
    }
*/
    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idProd_Avis;
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
        final Produit_Avis other = (Produit_Avis) obj;
        if (this.idProd_Avis != other.idProd_Avis) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit_Avis:" + "idProd=" + idProd + ", idAvis=" + idAvis + "\n";
    }
    
}
