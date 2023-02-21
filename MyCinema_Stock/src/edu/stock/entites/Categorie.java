/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.entites;

import java.util.Objects;

/**
 *
 * @author maidi
 */
public class Categorie {
    private int idCategorie;
    private String NomCategorie; 

    public Categorie() {
    }

    public Categorie(int idCategorie, String NomCategorie) {
        this.idCategorie = idCategorie;
        this.NomCategorie = NomCategorie;
    }

    public Categorie(String NomCategorie) {
        this.NomCategorie = NomCategorie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }
/*
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
*/
    public String getNomCategorie() {
        return NomCategorie;
    }

    public void setNomCategorie(String NomCategorie) {
        this.NomCategorie = NomCategorie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.idCategorie;
        return hash;
    }
/*
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
        final Categorie other = (Categorie) obj;
        if (this.idCategorie != other.idCategorie) {
            return false;
        }
        return true;
    }
*/

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
        final Categorie other = (Categorie) obj;
        if (this.idCategorie != other.idCategorie) {
            return false;
        }
        if (!Objects.equals(this.NomCategorie, other.NomCategorie)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Categorie:" + "NomCategorie=" + NomCategorie + "\n";
    }
    
    
}
