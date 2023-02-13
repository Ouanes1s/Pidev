package edu.stock.tests;

import edu.stock.entities.Fournisseur;
import edu.stock.entities.Produit;
import edu.stock.services.ServiceFournisseur;
import edu.stock.services.ServiceProduit;
import edu.stock.utils.DataSource;

/**
 *
 * @author maidi
 */
public class Main {
    public static void main(String[] args) {
        DataSource.getInstance();
    /*
        Produit p1 = new Produit("P88", "PopCorn Sucré","Pop_Corn",145,80);
        Produit p2 = new Produit("P88", "PopCorn Sucré","Pop_Corn",145,80);
        Produit p3 = new Produit("P88", "PopCorn Sucré","Pop_Corn",145,80);
        
        ServiceProduit sp = new ServiceProduit();
        sp.ajouter(p1);
        sp.ajouter(p2);
        
        sp.supprimer(2);
    */
    
        Fournisseur f1 = new Fournisseur("karim","tunis",14554);
        ServiceFournisseur sf = new ServiceFournisseur();
        sf.ajouter(f1);
        
        sf.supprimer(1);
        System.out.println(sf.getOneById(1));
        
        
        
    }
}
