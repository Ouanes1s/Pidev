/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Categorie;
import entity.Evenements;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class AffichierEvenementController implements Initializable {

    @FXML
    private TableView<Evenements> table;
    @FXML
    private TableColumn<Evenements, String> titre;
    @FXML
    private TableColumn<Evenements, String> lieu;
    @FXML
    private TableColumn<Evenements, Integer> nbrplace;
    @FXML
    private TableColumn<Evenements, Date> dated;
    @FXML
    private TableColumn<Evenements, Date> datef;
    @FXML
    private TableColumn<Evenements, Integer> nbrPlace;
    
    private ObservableList<Evenements> UserData = FXCollections.observableArrayList();
    
    EvenementService evenementService = new EvenementService();
    
    public static int ide ;
    @FXML
    private TextField rechercher;
    
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Evenements> listb= new ArrayList<Evenements>();
            
            listb = evenementService.getAllEvenement();
     
            System.out.println("hello"+listb);
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            titre.setCellValueFactory
                      (
                              new PropertyValueFactory<>("titre")
                      );
            lieu.setCellValueFactory
                      (
                              new PropertyValueFactory<>("lieu")
                      );
            nbrPlace.setCellValueFactory
                      (
                              new PropertyValueFactory<>("nbreplaces")
                      );
            nbrplace.setCellValueFactory
                      (
                              new PropertyValueFactory<>("nbreparticipants")
                      );
            dated.setCellValueFactory
                      (
                              new PropertyValueFactory<>("DateDebut")
                      );
            datef.setCellValueFactory
                      (
                              new PropertyValueFactory<>("DateFin")
                      );
            
        } catch (SQLDataException ex) {
            Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
                Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/AddEvenement.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
    }

    @FXML
    private void Modefier(ActionEvent event) {
        
      ide =  table.getSelectionModel().getSelectedItem().getId();     

        
                  Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/ModefierEvenement.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
               }
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException 
{        
        int id =  table.getSelectionModel().getSelectedItem().getId();     
        evenementService.deleteEvenement(id);
        resetTableData();
        
        
        
    }
    
    
    
        
        public void resetTableData() throws SQLDataException
    {
        List<Evenements> lisre = new ArrayList<>();
        lisre = evenementService.getAllEvenement();
        ObservableList<Evenements> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }

    @FXML
    private void rechercher(ActionEvent event) throws SQLDataException {
        
            titre.setCellValueFactory
                      (
                              new PropertyValueFactory<>("titre")
                      );
            lieu.setCellValueFactory
                      (
                              new PropertyValueFactory<>("lieu")
                      );
            nbrPlace.setCellValueFactory
                      (
                              new PropertyValueFactory<>("nbreplaces")
                      );
            nbrplace.setCellValueFactory
                      (
                              new PropertyValueFactory<>("nbreparticipants")
                      );
            dated.setCellValueFactory
                      (
                              new PropertyValueFactory<>("DateDebut")
                      );
            datef.setCellValueFactory
                      (
                              new PropertyValueFactory<>("DateFin")
                      );
        
                  
            List<Evenements> list = evenementService.getAllEvenement();
            
            //tableview.setItems(observablelist);
            
            FilteredList<Evenements> filtredData= new FilteredList<>(UserData, b ->true);
            rechercher.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super Evenements> Reservation;
                filtredData.setPredicate((Evenements e) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();
                    if(e.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (e.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Evenements> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
        
        
    }
    
        
    }
    

