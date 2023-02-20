/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.tests;

 import edu.reservation.entities.Reservation;
import edu.reservation.services.ServiceReservation;
 import edu.reservation.entities.Offre;
import edu.reservation.services.ServiceOffre;
 import edu.reservation.entities.Blog;
import edu.reservation.services.ServiceBlog;
import edu.reservation.utils.DataSource;
 /*
 * @author Amine Khalfaoui
 */
public class MainRes {
    public static void main(String[] args) {
        
        Reservation r2 = new Reservation ( "amine", "khal","amine.khalfaoui@esprit.tn","null", 111, "17/01/2023", "6666 "  );
        ServiceReservation sr = new ServiceReservation();
//        
//        Reservation r2 = new Reservation ( "ouanes", "chebil","premium", 1, 1, "17/01/2000"  );
////        sr.modifierReservation(r2);
         sr.ajouter (r2);
////        sr.supprimer (1);
//        Reservation r = new Reservation (  );
//       
//        r = sr.getOneById(9);
//        System.out.println(r.toString()); 
//        Offre r2 = new Offre ( 3, "moitie du prix", "10/10/2023","15/11/2023",  "12356"  );
//        ServiceOffre so = new ServiceOffre();
//         so.ajouter (r2);
//         Blog r1 = new Blog ( "amine", 5,"a555555" );
//        ServiceBlog sb = new ServiceBlog();
//         sb.ajouter (r1);
////    
} 
    }
