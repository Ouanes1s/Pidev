/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Evenements;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class EvenementService {
    
    private final Connection cnx;

    private static EvenementService instance;
    
        public EvenementService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static EvenementService getInstance()
    {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance; 
    }
    
   public void addEvenement(Evenements q)throws SQLDataException, SQLException{
        
         
 String query ="INSERT INTO `evenement`( `lieu`, `titre`, `nbreplaces`,`DateDebut`,`DateFin`,`image`,nbreparticipants,id_cat) VALUES (?,?,?,?,?,?,0,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getLieu());
                st.setString(2,q.getTitre());
                st.setInt(3,q.getNbreplaces());
                st.setDate(4, (Date) q.getDateDebut());
                st.setDate(5, (Date) q.getDateFin());
                st.setString(6,q.getImage());
                st.setInt(7, q.getId_cat());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                

    }

    public boolean ModifierEvenenment(Evenements e) throws SQLDataException {

               
                String query = "UPDATE `evenement` SET `lieu`=?,`nbreplaces`=?,`nbreparticipants`=?,`DateDebut`=?,`DateFin`=?,`titre`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,e.getLieu());
                st.setInt(2,e.getNbreplaces());
                st.setInt(3,e.getNbreparticipants());
                st.setDate(4, (Date) e.getDateDebut());
                st.setDate(5, (Date) e.getDateFin());
                st.setString(6,e.getTitre());
                st.setInt(7,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Evenements> getAllEvenement() throws SQLDataException {

        
        List<Evenements> list =new ArrayList<Evenements>();
        int count =0;
        
        String requete="select * from evenement";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Evenements e = new Evenements();
                e.setId(rs.getInt("id"));
                e.setLieu(rs.getString("lieu"));
                e.setTitre(rs.getString("titre"));
                e.setNbreplaces(rs.getInt("nbreplaces"));
                e.setNbreparticipants(rs.getInt("nbreparticipants"));
                e.setDateDebut(rs.getDate("DateDebut"));
                e.setDateFin(rs.getDate("DateFin"));
                  e.setImage(rs.getString("image"));
                
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public Evenements get_Evenment(int i) {
        Evenements e = new Evenements();
        String requete = "select * from evenement";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                e.setId(rs.getInt("id"));
                e.setLieu(rs.getString("lieu"));
                e.setTitre(rs.getString("titre"));
                e.setNbreplaces(rs.getInt("nbreplaces"));
                e.setNbreparticipants(rs.getInt("nbreparticipants"));
                e.setDateDebut(rs.getDate("DateDebut"));
                e.setDateFin(rs.getDate("DateFin"));
                e.setImage(rs.getString("image"));
                

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
    
    
    
        public ObservableList<Evenements> serach(String cas) throws SQLException {
        ObservableList<Evenements> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `evenement` where  titre LIKE '%" + cas + "%'or  lieu LIKE '%" + cas + "%' or  DateDebut LIKE '%" + cas + "%' or  DateFin LIKE '%" + cas + "%' or nbreplaces LIKE '%" + cas + "%' or  nbreparticipants LIKE '%" + cas + "%' ";
       try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                     Evenements e = new Evenements();
                e.setId(rs.getInt("id"));
                e.setLieu(rs.getString("lieu"));
                e.setTitre(rs.getString("titre"));
                e.setNbreplaces(rs.getInt("nbreplaces"));
                e.setNbreparticipants(rs.getInt("nbreparticipants"));
                e.setDateDebut(rs.getDate("DateDebut"));
                e.setDateFin(rs.getDate("DateFin"));
                  e.setImage(rs.getString("image"));
                
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
    
    
             public int Affichertaille() throws SQLException {
        int nbr = 0;        
        String requete = "SELECT COUNT(*) as nbr FROM evenement"       ;
                try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              nbr=rs.getInt("nbr");
            }
                   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
                        System.out.println("le nombre est : "+nbr);   
        return nbr;

    }
    
    
    

    public boolean deleteEvenement(int idEvenement) throws SQLDataException {

        
        
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM `evenement` WHERE `id` ="+idEvenement;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<Evenements> afficheEvenement(String titre) throws SQLDataException
    {

       List<Evenements> list=new ArrayList<Evenements>();
           try {
               String req="SELECT * FROM `evenement` where `titre`='"+titre+"'";
               Statement st;
                 st = cnx.createStatement();
                 ResultSet rs=st.executeQuery(req);
               
                while(rs.next())
                       {
                           Evenements e= new Evenements();
                           e.setId(rs.getInt("id"));
                e.setLieu(rs.getString("lieu"));
                e.setTitre(rs.getString("titre"));
                e.setNbreplaces(rs.getInt("nbreplaces"));
                e.setNbreparticipants(rs.getInt("nbreparticipants"));
                e.setDateDebut(rs.getDate("DateDebut"));
                e.setDateFin(rs.getDate("DateFin"));
               
                           list.add(e);
            
                       }    
           } catch (SQLException ex) {
               Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
           }
          return list;
    }

    public boolean ModifierEvenenmentPlace(Evenements e) throws SQLDataException {
        String query = "UPDATE `evenement` SET `nbreparticipants`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
            
                st.setInt(2,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    

       public List<Integer> getAllEvent() throws SQLDataException {

        
        List<Integer> list =new ArrayList<Integer>();
        int count =0;
        
        String requete="select id from evenement";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Evenements e = new Evenements();
                e.setId(rs.getInt(1));
               
                  int i = e.getId();
                
                list.add(i);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{

               return list;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
    public ObservableList<String> get_List_Evement_titre() {
        ObservableList<String> list = FXCollections.observableArrayList();

        String requete = "SELECT * FROM `evenement` ";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("titre"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}