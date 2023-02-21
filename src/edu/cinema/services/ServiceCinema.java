/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinema.services;

import edu.cinema.entities.Cinema;
import edu.cinema.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ServiceCinema implements IService<Cinema> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Cinema p) {
        try {
            String req = "INSERT INTO `cinema` (`nom_cinema`, `adresse`,`num`) VALUES ('" + p.getNom_cinema() + "', '" + p.getAdresse() +"','"+p.getNum()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Cinema created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouter2(Cinema p) throws SQLException {
        if(checkExistence(p) == 0 && checkEmpty(p)){
            
        try{
            String query = "INSERT INTO `cinema` (`nom_cinema`, `adresse`,`num`) VALUES ('" + p.getNom_cinema() + "', '" + p.getAdresse() +"','"+p.getNum()+ "')";
           Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("Cinema created !");;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        } else if(checkExistence(p) != 0)
            System.out.println("Cinema exists!!");
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `cinema` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Cinema deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierCinema(Cinema u,int i) {
        try{
        String req = "UPDATE cinema SET nom_cinema=?,adresse=?,num=? WHERE id="+i;
        PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, u.getNom_cinema());
            pst.setString(2, u.getAdresse());
            pst.setString(3, u.getNum());
            pst.executeUpdate();
            System.out.println("Cinema was updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifier(Cinema p) {
        try {
            String req = "UPDATE `cinema` SET `nom_cinema` = '" + p.getNom_cinema() + "', `Adresse` = '" + p.getAdresse()+ "' WHERE `cinema`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Cinema updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Cinema> afficherCinema() {
        List <Cinema> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `cinema`";
            Statement st;
            st =cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Cinema c = new Cinema ( rs.getString("nom_Cinema"),rs.getString("adresse"),
                        rs.getString("num"));
                list.add(c);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Cinema> getAll() {
        List<Cinema> list = new ArrayList<>();
        try {
            String req = "Select * from cinema";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cinema p = new Cinema(rs.getString(1), rs.getString("nom"), rs.getString(3));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

     @Override
    public Cinema getOneById(int id) {
        Cinema c = null;
        try {
            String req = "Select * from cinema";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (id==rs.getInt("id")){
       c = new Cinema (rs.getInt("id"), rs.getString("nom_Cinema"),rs.getString("adresse"),rs.getString("num"));            }
        }} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }
     public int checkExistence(Cinema c) throws SQLException{
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from cinema WHERE nom_cinema= '" +
                c.getNom_cinema()+ "'");
        int size = 0;
        rs.next();
        size = rs.getInt(1);
        return size;
    }
    //controle de saisie 
    public boolean checkEmpty(Cinema c) throws SQLException{
        if (c.getNom_cinema() == null || c.getAdresse() ==null
            || c.getNum() == null ){
            System.out.println("U Have Smthg Wrong!!");
            return false;
        }
        System.out.println("All Is Good !!");
        return true;
    }
    //public void Recherche(String target) throws SQLException{
      //  ServiceCinema sc = new ServiceCinema();
        //sc.afficherCinema().stream().filter(
          //      (Cinema r)
            //            -> r.getNom_cinema().toLowerCase().contains(target.toLowerCase())
              //          || r.getAdresse().toLowerCase().contains(target.toLowerCase())
                //        || r.getNum().toLowerCase().contains(target.toLowerCase())
                        
       // ).forEach(System.out::println);
    //}
    
    public List<Cinema> sortCinema() throws SQLException {
        ServiceCinema sc = new ServiceCinema();
            List<Cinema> sortedCinema= sc.afficherCinema().stream()
                                             .sorted()
                                             .collect(Collectors.toList());
        return sortedCinema;
    }

}
