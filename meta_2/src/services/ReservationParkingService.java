/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Parking;
import entities.ReservationParking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author MarwaTriaa
 */
public class ReservationParkingService implements IService<ReservationParking>{
    Connection cnx = MyDB.getInstance().getCnx();
 
    @Override
    public void ajouter(ReservationParking reservationP) throws SQLException {
         String query = "INSERT INTO RESERVATIONPARKING(id_parking," +
                 "nb_hours,id_user) VALUES(?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,reservationP.getIdParking());
            ste.setInt(2, reservationP.getNbHours());
            ste.setInt(3,reservationP.getIdUser());
            ste.executeUpdate();
            System.out.println("Reservation Parking Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(ReservationParking reservationP) throws SQLException {
        String query = "UPDATE RESERVATIONPARKING SET id_parking= '" + reservationP.getIdParking()+ "', nb_hours= '" +
                reservationP.getNbHours()+ "', id_user= '" + reservationP.getIdUser()+ "'" 
                + " WHERE id_reservation= " + reservationP.getIdReservationParking()+ "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Reservation Parking Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(ReservationParking reservationP) throws SQLException {
        String query = "DELETE FROM RESERVATIONPARKING WHERE id_reservation = " + reservationP.getIdReservationParking()+ "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Reservation Parking Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<ReservationParking> recuperer() throws SQLException {
        List<ReservationParking> listeReservationsP = new ArrayList<>();
        String query = "SELECT * FROM reservationparking";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                ReservationParking reservationP = new ReservationParking();
                reservationP.setIdReservationParking(rs.getInt("id_reservation"));
                reservationP.setIdParking(rs.getInt("id_parking"));
                reservationP.setNbHours(rs.getInt("nb_hours"));
                reservationP.setIdUser(rs.getInt("id_user"));
                listeReservationsP.add(reservationP);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listeReservationsP;
    }
    
    public ReservationParking getReservationParking(int idParking) throws SQLException {
            String query = "SELECT * FROM reservationparking where id_parking = " + idParking;
            ReservationParking reservationParking = new ReservationParking();
            try{
                Statement ste = cnx.createStatement();
                ResultSet rs = ste.executeQuery(query);
                 
                while (rs.next()){
                    reservationParking.setIdReservationParking(rs.getInt("id_reservation"));
                    reservationParking.setIdParking(rs.getInt("id_parking"));
                    reservationParking.setIdUser(rs.getInt("id_user"));
                    reservationParking.setNbHours(rs.getInt("nb_hours"));
                }
            }
            catch (SQLException e){
                e.getMessage();
            }
            return reservationParking;
    }
    
     public List<Parking> recupererUserResevrations(int idUser) throws SQLException {
        List<Parking> listeParkings = new ArrayList<>();
        String query = "SELECT DISTINCT nom_parking,logo_parking,capacite_parking,takenP_parking,prix_parking,reservationparking.id_parking,"
                + "reservationparking.id_user from parking INNER JOIN reservationparking ON (parking.id_parking = reservationparking.id_parking"
                + " AND reservationparking.id_user =" + idUser + ")";
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Parking parking = new Parking();
                parking.setIdParking(rs.getInt("id_parking"));
                parking.setNomParking(rs.getString("nom_parking"));
                parking.setLogoParking(rs.getString("logo_parking"));
                parking.setCapaciteParking(rs.getInt("capacite_parking"));
                parking.setTakenPParking(rs.getInt("takenP_parking"));
                parking.setPrixParking(rs.getFloat("prix_parking"));
                listeParkings.add(parking);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return listeParkings;
    }
    
    
    
    
}
