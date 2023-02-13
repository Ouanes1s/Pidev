/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.tests;

 import edu.reservation.entities.Reservation;
import edu.reservation.services.ServiceReservation;
import edu.reservation.utils.DataSource;
 /*
 * @author Amine Khalfaoui
 */
public class MainRes {
    public static void main(String[] args) {
        
        Reservation r1 = new Reservation (1,"amine", "Khalfaoui","premium", 1, 1, "17/01/2000"  );
        ServiceReservation sr = new ServiceReservation();
        sr.ajouterReservation(r1);
        
    
}
    }
