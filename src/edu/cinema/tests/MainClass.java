/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinema.tests;

import edu.cinema.entities.Cinema;
import edu.cinema.entities.Salle;
import edu.cinema.services.ServiceCinema;
import edu.cinema.services.ServiceSalle;
import edu.cinema.utils.DataSource;
import java.sql.SQLException;

/**
 *
 * @author abdelazizmezri
 */
public class MainClass {
    
    public static void main(String[] args) throws SQLException {
        
        Cinema p1 = new Cinema(3,"pathe","tunis","5");
        Cinema p2 = new Cinema("pathe", "sfax","10");
        Cinema p3 = new Cinema("colise", "tunis","20");
        Cinema p4 = new Cinema("rio", "sousse","11");
        Salle s1=new Salle("test","manaarch","51");
        Salle s2=new Salle("bbs","aaq","12");
        Cinema c=new Cinema();
        ServiceCinema sc = new ServiceCinema();
        ServiceSalle ss=new ServiceSalle();
         String target = "test";
//        sc.Recherche(target);
  //      sc.sortCinema().forEach(System.out::println);
        //ss.ajouter(s2);
        System.out.println(ss.afficherSalle());
         sc.modifierCinema(p2, 1);
        System.out.println(sc.afficherCinema());
       
        //ss.modifierSalle(s1, 2);
        
        /*sc.ajouter2(p2);
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
