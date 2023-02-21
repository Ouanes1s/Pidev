/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.tests;

import edu.stock.entites.Avis;
import edu.stock.entites.Categorie;
import edu.stock.entites.Offre;
import edu.stock.entites.Produit;
import edu.stock.services.ServiceAvis;
import edu.stock.services.ServiceCategorie;
import edu.stock.services.ServiceOffre;
import edu.stock.services.ServiceProduit;
import edu.stock.utils.ConnexionBD;

/**
 *
 * @author maidi
 */
public class MainClass {
    public static void main(String[] args) {
        
        ConnexionBD.getInstance();

/******************************* test categorie ********************************/

        Categorie c1 = new Categorie("boisson");
        Categorie c2 = new Categorie("popCorn");
        Categorie c3 = new Categorie("chocolat");
        ServiceCategorie sc = new ServiceCategorie();
        //sc.ajouter(c1);
        //sc.ajouter(c2);
        //sc.ajouter(c3);
        Categorie c4 = new Categorie("biscuit");
        //sc.ajouter(c4);
        Categorie c5 = new Categorie("zeyda");
        //sc.ajouter(c5);
        Categorie c6 = new Categorie("kkk");
        //sc.ajouter(c6);
        //sc.supprimer(6);
        Categorie c7 = new Categorie("hhh");
        //sc.supprimerVerif(c6);
        //System.out.println(sc.afficherTous()); 
        //System.out.println(sc.TriParNom());
        //System.out.println(sc.rechercherUnParId(1));
        
        
/******************************* test Produit ********************************/      
      
        Produit p1 = new Produit("P88", "PopCorn Sucré",245,12,1,"poCorn Bnin mosh normal","image",2);
        Produit p2 = new Produit("B78", "Coca Cola",877,13,1,"Gazouza Bnina mosh normal","image",1);
        Produit p3 = new Produit("C88", "Chocolat",334,9,1,"chocolat Bnina mosh normal","image",3); 
        ServiceProduit sp = new ServiceProduit();
        //sp.ajouter(p1);
        //sp.ajouter(p2);
        //sp.ajouter(p3);
        //sp.ajouter(p3);
        Produit p4 = new Produit("Bis88", "biscuit",555,7,1,"biscuit Bnin mosh normal","image",3);         
        //sp.ajouter(p4);
        //sp.supprimer(6);
        //System.out.println(sp.afficherTous());
        //System.out.println(sp.rechercherUnParId(77));
        //System.out.println(sp.afficherProduitsParCategorie(3));
        //System.out.println(sp.nbrProdCategorie(3));
        //Produit p5 = new Produit("lab5", "biscuit",555,7,1,"biscuit Bnin mosh normal","image",3); 
        //sp.modifier(p5);
        
/******************************* test offre ********************************/      
        
        Offre o1 = new Offre(10,1);
        Offre o2 = new Offre(20,1);
        Offre o3 = new Offre(30,1);
        Offre o4 = new Offre(40,1);
        ServiceOffre sof = new ServiceOffre();
        //sof.ajouter(o4);
        //System.out.println(sof.afficherTous());
        //System.out.println(sof.rechercherUnParId(1));
        //sof.modifier(o4);
        //System.out.println(sof.rechercherUnParId(2));
        Offre o5 = new Offre(90,1);
        //sof.modifier(o5);
        //sof.ajouter(o5);
        //sof.supprimer(5);
        //System.out.println(sof.TriParPourcentage());
        //System.out.println();
        //sof.calculOffre(2, 10);
        sof.SendMail();
 
/******************************* test offre ********************************/ 


        Avis av =new Avis(2,1);
        ServiceAvis sAv =new ServiceAvis();
        //sAv.ajouter(av);
        
        
    
    }
}
