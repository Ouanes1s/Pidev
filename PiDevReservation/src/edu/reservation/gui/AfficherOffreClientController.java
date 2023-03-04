/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;

import edu.reservation.services.ServiceOffre;
import edu.reservation.entities.Offre;
import edu.reservation.utils.DataSource;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    @FXML
    private ImageView backkey;
    
    
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
                    rst.getString("datefin_offr"),
                    rst.getString("code_offr")
                   
                   
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
    protected void updateItem(Offre offre, boolean empty) {
        super.updateItem(offre, empty);
        if (empty || offre == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(offre.getNomfilm_offr() + "|| " + offre.getContenu_offr()+" ||  From  "+offre.getDatedebut_offr()+"  Until "+offre.getDatefin_offr()+"|| Save This Code:  "+offre.getCode_offr()+"|| ");
             
            }    
    
    }
              });
    }

    @FXML
    private void backtoajoutres(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("AjouterReservation.fxml"));
             Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
        }
    }

              
    
    
              
              
