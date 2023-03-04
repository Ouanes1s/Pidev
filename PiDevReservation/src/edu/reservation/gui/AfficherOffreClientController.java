/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;

import edu.reservation.services.ServiceOffre;
import edu.reservation.entities.Offre;
import edu.reservation.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Amine Khalfaoui
 */
public class AfficherOffreClientController implements Initializable {
 @FXML
    private ListView<Offre> listView;
    @FXML
    private TextField searchField;
    
    
     public void list_affiche(){
    Connection cnx = DataSource.getInstance().getCnx();
    ObservableList<Offre> offres = FXCollections.observableArrayList();
    PreparedStatement stmt = null;
    ResultSet rst = null;

    try {
        String req = "SELECT * FROM Offre ";
        stmt = cnx.prepareStatement(req);
        
        rst = stmt.executeQuery();

        while(rst.next()){
            Offre offre = new Offre(
                    rst.getString("nomfilm_offr"),
                    rst.getString("contenu_offr"),
                    rst.getString("datedebut_offr"),
                    rst.getString("datedebut_offr"),
                    rst.getString("datedebut_offr")
                   
                   
            ) ;
            offres.add(offre);
        }

        listView.setItems(offres);
        
        
        FilteredList<Offre> filteredOffres = new FilteredList<>(offres, p -> true);
        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredOffres.setPredicate(offre -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (offre.getNomfilm_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (offre.getContenu_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                
                } else if (offre.getDatedebut_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (offre.getDatefin_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (offre.getCode_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;    
                }
                return false;
            }); 
            SortedList<Offre> sortedOffres;
            sortedOffres = new SortedList<>(filteredOffres, Comparator.comparing(Offre::getCode_offr));
        listView.setItems(sortedOffres);
       
        });

      
    }
    catch (SQLException ex){
        System.err.println(ex.getMessage());
    }
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    
        // TODO
        
        list_affiche();
              listView.setCellFactory(param -> new ListCell<Offre>() {
    @Override
    protected void updateItem(Offre blog, boolean empty) {
        super.updateItem(blog, empty);
        if (empty || blog == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(blog.getNomfilm_offr() + "|| " + blog.getContenu_offr()+" || "+blog.getDatedebut_offr()+"|| "+blog.getDatefin_offr()+"|| "+blog.getCode_offr()+"|| ");
             
            }    
    
    }
              });
    }
}
              
    
    
              
              
