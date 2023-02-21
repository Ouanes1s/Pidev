/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.tests;

import edu.workshopjdbc3a48.entities.Cinema;
import edu.workshopjdbc3a48.services.ServiceCinema;
import edu.workshopjdbc3a48.utils.DataSource;

/**
 *
 * @author abdelazizmezri
 */
public class MainClass {
    
    public static void main(String[] args) {
        
        Cinema p1 = new Cinema(3,"pathe","tunis","5");
        Cinema p2 = new Cinema("pathe", "sfax","10");
        Cinema p3 = new Cinema("colise", "tunis","20");
        Cinema p4 = new Cinema("rio", "sousse","11");
        Cinema c=new Cinema();
        ServiceCinema sc = new ServiceCinema();
        
        sc.ajouter(p2);
        sc.supprimer(8);
        sc.modifierCinema(p4,3);
        System.out.println(sc.afficherCinema());
        c=sc.getOneById(5);
        System.out.println(c.toString());
        
        
        /*sp.ajouter(p1);
        sp.ajouter(p2);
        sp.ajouter2(p3);
        sp.ajouter2(p4);*/
        
        
        
    }
    
}
