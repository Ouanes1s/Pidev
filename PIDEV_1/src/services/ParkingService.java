/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Parking;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import utils.MyDB;

/**
 *
 * @author MarwaTriaa
 */
public class ParkingService implements IService<Parking>{
    Connection cnx = MyDB.getInstance().getCnx();
    
    @Override
    public void ajouter(Parking parking) throws SQLException {
        if (checkExistence(parking) == 0 && checkEmpty(parking)) {
            String query = "INSERT INTO PARKING(nom_parking,logo_parking,capacite_parking,"
                + "takenP_parking,prix_parking)" +
                " VALUES(?,?,?,?,?)";
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1,parking.getNomParking());
            ste.setString(2, parking.getLogoParking());
            ste.setInt(3,parking.getCapaciteParking());
            ste.setInt(4,parking.getTakenPParking());
            ste.setFloat(5,parking.getPrixParking());
            ste.executeUpdate();
            System.out.println("Parking Added Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        } else if(checkExistence(parking) != 0)
            System.out.println("Parking Already Exists!!");
    }

    @Override
    public void modifier(Parking parking) throws SQLException {
        String query = "UPDATE PARKING SET nom_parking= '" + parking.getNomParking()+ "', logo_parking= '" +
                parking.getLogoParking()+ "', capacite_parking= '" + parking.getCapaciteParking()+ "', takenP_parking= '" +
                parking.getTakenPParking()+ "', prix_parking= '" + parking.getPrixParking()+ "'" +
                " WHERE id_parking= " + parking.getIdParking()+ "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Parking Updated Successfully ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Parking parking) throws SQLException {
        String query = "DELETE FROM PARKING WHERE id_parking = " + parking.getIdParking() + "";
        try{
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Parking Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Parking> recuperer() throws SQLException {
        List<Parking> listeParkings = new ArrayList<>();
        String query = "SELECT * FROM PARKING";
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
  
    
   //Recherche
    
    public void filterParkings(String target) throws SQLException{
        
        String output = target.replaceAll("[^0-9]", "");
        recuperer().stream().filter(
                (p)
                        -> p.getNomParking().toLowerCase().contains(target.toLowerCase())
                        || p.getCapaciteParking()==(Integer.valueOf(output))
                        || p.getPrixParking() == (Integer.valueOf(output))
        ).forEach(System.out::println);
    }
    
    //TRI
    public List<Parking> sortParkings() throws SQLException {
        List<Parking> sortedParkings = recuperer().stream()
                                             .sorted()
                                             .collect(Collectors.toList());
        return sortedParkings;
    }
    
    public int checkExistence(Parking parking) throws SQLException{
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from PARKING WHERE nom_parking= '" +
                parking.getNomParking() + "'");
        int size = 0;
        rs.next();
        size = rs.getInt(1);
        return size;
    }
    
    public boolean checkEmpty(Parking parking) throws SQLException{
        if (parking.getCapaciteParking() == 0 || parking.getLogoParking() == null
            || parking.getNomParking() == null || parking.getPrixParking() == 0
                || parking.getCapaciteParking() < parking.getTakenPParking()){
            System.out.println("U Have Smthg Wrong!!");
            return false;
        }
        System.out.println("All Is Good !!");
        return true;
    }
}
