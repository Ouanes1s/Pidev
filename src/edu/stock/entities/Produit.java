package edu.stock.entities;

/**
 *
 * @author maidi
 */
public class Produit {

    private int Id_Produit;
    private String Code_Article;
    private String Nom_Article;
    private String Catégorie_Article;
    private int Quantité_Article;
    private int Prix_Vente;
    private String Etat_Article;

    public Produit() {
    }

    public Produit(String Code_Article, String Nom_Article, String Catégorie_Article, int Quantité_Article, int Prix_Vente) {
        this.Code_Article = Code_Article;
        this.Nom_Article = Nom_Article;
        this.Catégorie_Article = Catégorie_Article;
        this.Quantité_Article = Quantité_Article;
        this.Prix_Vente = Prix_Vente;
    }
        
    public Produit(int Id_Produit, String Code_Article, String Nom_Article, String Catégorie_Article, int Quantité_Article, int Prix_Vente, String Etat_Article) {
        this.Id_Produit = Id_Produit;
        this.Code_Article = Code_Article;
        this.Nom_Article = Nom_Article;
        this.Catégorie_Article = Catégorie_Article;
        this.Quantité_Article = Quantité_Article;
        this.Prix_Vente = Prix_Vente;
        this.Etat_Article = Etat_Article;
    }

    public int getId_Produit() {
        return Id_Produit;
    }

    public String getCode_Article() {
        return Code_Article;
    }

    public void setCode_Article(String Code_Article) {
        this.Code_Article = Code_Article;
    }

    public String getNom_Article() {
        return Nom_Article;
    }

    public void setNom_Article(String Nom_Article) {
        this.Nom_Article = Nom_Article;
    }

    public String getCatégorie_Article() {
        return Catégorie_Article;
    }

    public void setCatégorie_Article(String Catégorie_Article) {
        this.Catégorie_Article = Catégorie_Article;
    }

    public int getQuantité_Article() {
        return Quantité_Article;
    }

    public void setQuantité_Article(int Quantité_Article) {
        this.Quantité_Article = Quantité_Article;
    }

    public int getPrix_Vente() {
        return Prix_Vente;
    }

    public void setPrix_Vente(int Prix_Vente) {
        this.Prix_Vente = Prix_Vente;
    }

    public String getEtat_Article() {
        return Etat_Article;
    }

    public void setEtat_Article(String Etat_Article) {
        this.Etat_Article = Etat_Article;
    }

    @Override
    public String toString() {
        return "Produit{" + "Code_Article=" + Code_Article + ", Nom_Article=" + Nom_Article + ", Catégorie_Article="
                + Catégorie_Article + ", Quantité_Article=" + Quantité_Article + ", Prix_Vente=" + Prix_Vente
                + ", Etat_Article=" + Etat_Article + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.Id_Produit;
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
        if (this.Id_Produit != other.Id_Produit) {
            return false;
        }
        return true;
    }

}
