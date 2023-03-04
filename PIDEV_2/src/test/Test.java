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
 * @author Marwa
 */
public class Test {
    
    
    public static void main(String[] args) throws SQLException {
       
        Connection myDb = MyDB.getInstance().getCnx();
        ReclamationService rs = new ReclamationService();
        ParkingService ps = new ParkingService();
        ReservationParkingService rps = new ReservationParkingService();
        
        Reclamation reclamation = new Reclamation(1, Date.valueOf("2023-05-29"),
                "categoryReclamation1", "typeReclamation1", "messageReclamationUpdated", false, 8,
                "reponseReclamation1",2);
        /*rs.ajouter(reclamation);
        rs.supprimer(reclamation);
        rs.recuperer().forEach(System.out::println);
        System.out.println("************************************");
        rs.modifier(reclamation);
        rs.recuperer().forEach(System.out::println);
        String target = "2022-04-17";
        rs.filterReclamations(target);
        rs.sortReclamations().forEach(System.out::println);
        */
        
        rs.SendMail("null");

        Parking parking = new Parking(2, "nomParking2", "logoParking2", 300, 0, 10);
       // Parking parking2 = new Parking( "nomParking3", "logoParking2", 300, 350, 10);
        /*ps.ajouter(parking);
        /*ps.supprimer(parking);
        ps.modifier(parking);
        ps.recuperer().forEach(System.out::println);
        ps.filterParkings("10");*/
        
        
        ReservationParking reservationP = new ReservationParking(1, 3,5,3);
        /*rps.ajouter(reservationP);
        rps.recuperer().forEach(System.out::println);
        rps.modifier(reservationP);
        rps.supprimer(reservationP);*/
        
    }
    
}
