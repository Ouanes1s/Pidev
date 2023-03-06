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
public class Produit {
    private int idProd;
    private String codeProd;
    private String nomProd;   
    private int quantiteProd;
    private float prixProd;
    private int etatProd;   
    private String descriptionProd;
    private String imgProd;
    private int idCategorie;

    public Produit() {
    }

    public Produit(int idProd, String codeProd, String nomProd, int quantiteProd, float prixProd, int etatProd, String descriptionProd, String imgProd, int idCategorie) {
        this.idProd = idProd;
        this.codeProd = codeProd;
        this.nomProd = nomProd;
        this.quantiteProd = quantiteProd;
        this.prixProd = prixProd;
        this.etatProd = etatProd;
        this.descriptionProd = descriptionProd;
        this.imgProd = imgProd;
        this.idCategorie = idCategorie;
    }

    public Produit(String codeProd, String nomProd, int quantiteProd, float prixProd, int etatProd, String descriptionProd, String imgProd, int idCategorie) {
        this.codeProd = codeProd;
        this.nomProd = nomProd;
        this.quantiteProd = quantiteProd;
        this.prixProd = prixProd;
        this.etatProd = etatProd;
        this.descriptionProd = descriptionProd;
        this.imgProd = imgProd;
        this.idCategorie = idCategorie;
    }

    public Produit(String codeProd, String nomProd, int quantiteProd, float prixProd, String descriptionProd) {
        this.codeProd = codeProd;
        this.nomProd = nomProd;
        this.quantiteProd = quantiteProd;
        this.prixProd = prixProd;
        this.descriptionProd = descriptionProd;
    }


    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getCodeProd() {
        return codeProd;
    }

    public void setCodeProd(String codeProd) {
        this.codeProd = codeProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public int getQuantiteProd() {
        return quantiteProd;
    }

    public void setQuantiteProd(int quantiteProd) {
        this.quantiteProd = quantiteProd;
    }

    public float getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(float prixProd) {
        this.prixProd = prixProd;
    }

    public int getEtatProd() {
        return etatProd;
    }

    public void setEtatProd(int etatProd) {
        this.etatProd = etatProd;
    }

    public String getDescriptionProd() {
        return descriptionProd;
    }

    public void setDescriptionProd(String descriptionProd) {
        this.descriptionProd = descriptionProd;
    }

    public String getImgProd() {
        return imgProd;
    }

    public void setImgProd(String imgProd) {
        this.imgProd = imgProd;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idProd;
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
        final Produit other = (Produit) obj;
        if (this.idProd != other.idProd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        /*Discount dis = new Discount();
        Category cat = new Category();
        CategoryService cs = new CategoryService();
        if(dis.getPercent()==0)*/
        return "Produit:" + "codeProd=" + codeProd + ", nomProd=" + nomProd + ", quantiteProd=" + quantiteProd + ", prixProd=" + prixProd + ", etatProd=" + etatProd + ", descriptionProd=" + descriptionProd + ", imgProd=" + imgProd + ", idCategorie=" + idCategorie + "\n";
    }
    
    
}
