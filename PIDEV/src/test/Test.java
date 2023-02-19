/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Parking;
import entities.Reclamation;
import entities.ReservationParking;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import services.ParkingService;
import services.ReclamationService;
import services.ReservationParkingService;
import utils.MyDB;

/**
 *
 * @author MARWA
 */
public class Test {
    
    
    public static void main(String[] args) throws SQLException {
       
        Connection myDb = MyDB.getInstance().getCnx();
        
        ReclamationService rs = new ReclamationService();
        ParkingService ps = new ParkingService();
        ReservationParkingService rps = new ReservationParkingService();
        
        Reclamation reclamation = new Reclamation(5, Date.valueOf("2022-04-17"),
                "categoryReclamation", "typeReclamation", "messageReclamationUpdated", true, 5,
                "ReponseReclamation",1);
        /*rs.ajouter(reclamation);
        rs.supprimer(reclamation);
        rs.recuperer().forEach(System.out::println);
        System.out.println("************************************");
        rs.modifier(reclamation);
        rs.recuperer().forEach(System.out::println);*/
        
        
        Parking parking = new Parking(1, "nomParkingUpdated", "logoParkingUpdated", 250, 2, 15);
        /*ps.ajouter(parking);
        ps.supprimer(parking);
        ps.modifier(parking);
        ps.recuperer().forEach(System.out::println);*/
        
        
        ReservationParking reservationP = new ReservationParking(1, 3,5,3);
        /*rps.ajouter(reservationP);
        rps.recuperer().forEach(System.out::println);
        rps.modifier(reservationP);
        rps.supprimer(reservationP);*/
        
    }
    
}
