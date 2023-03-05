/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinema.gui;

import edu.cinema.entities.Salle;
import edu.cinema.services.ServiceCinema;
import edu.cinema.services.ServiceSalle;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hayth
 */
public class SalleController implements Initializable {

    @FXML
    private TextField tfnom_cinema;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfnum;
    @FXML
    private ListView<Salle> CinemaView;
    @FXML
    private Button refresh;
    @FXML
    private TextField searchField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Salle c = new Salle();
        ServiceSalle sc=new ServiceSalle();
        List<Salle> data=sc.agetAll();
        ObservableList<Salle> observableData = FXCollections.observableArrayList(data);
        CinemaView.setItems(observableData);
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
         String nom_salle =tfnom_cinema.getText();
        String adresse=tfadresse.getText();
        String num_places=tfnum.getText();
        Salle c = new Salle (nom_salle,adresse,num_places);
        ServiceSalle sc = new ServiceSalle();
        sc.ajouter2(c);
        refreshs();
    }

    @FXML
    @SuppressWarnings("empty-statement")
    private void supprimer(ActionEvent event) {
        if (!CinemaView.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer la salle du " + CinemaView.getSelectionModel().getSelectedItem().getNom_salle()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    ServiceSalle r=new ServiceSalle();
    r.supprimer(CinemaView.getSelectionModel().getSelectedItem().getId());
    };
    refreshs();
    }
    }

    @FXML
    private void refresh(ActionEvent event) {
         Salle c = new Salle();
        ServiceSalle sc=new ServiceSalle();
        List<Salle> data=sc.agetAll();
        ObservableList<Salle> observableData = FXCollections.observableArrayList(data);
        CinemaView.setItems(observableData);
    }

    @FXML
    private void modifier(ActionEvent event) {
        String nom_cinema =tfnom_cinema.getText();
        String adresse=tfadresse.getText();
        String num=tfnum.getText();
        Salle c = new Salle (nom_cinema,adresse,num);
        ServiceSalle sc = new ServiceSalle();
        c=CinemaView.getSelectionModel().getSelectedItem();
        c.setId(CinemaView.getSelectionModel().getSelectedItem().getId());
        c.setNom_Salle(nom_cinema);
        c.setAdresse(adresse);
        c.setNum(num);
        String n=tfnom_cinema.getText();
        String ad=tfadresse.getText();
        String nu =  tfnum.getText();
        
                
    Salle p=new Salle(n,ad,nu);
        System.out.println(p.toString());
    sc.modifier(c);
    refreshs();

    Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
    alert.setContentText("Reservation ajouter");
    alert.showAndWait();
    }
    private void refreshs() {
         Salle c = new Salle();
        ServiceSalle sc=new ServiceSalle();
        List<Salle> data=sc.agetAll();
        ObservableList<Salle> observableData = FXCollections.observableArrayList(data);
        CinemaView.setItems(observableData); 
        
    }

    @FXML
    private void search(ActionEvent event) {
        Salle c = new Salle();
        ServiceSalle sc=new ServiceSalle();        
        String searchText = searchField.getText();
    List<Salle> searchResults = sc.searchByName(searchText, CinemaView.getItems());
    ObservableList<Salle> observableData = FXCollections.observableArrayList(searchResults);
    CinemaView.setItems(observableData);
        System.out.println(searchResults);
    }
    public void sortByName() {
    Salle c = new Salle();
        ServiceCinema sc=new ServiceCinema();
    List<Salle> Salles = CinemaView.getItems();
    List<Salle> sortedCinemas = Salles.stream()
        .sorted(Comparator.comparing(Salle::getNom_salle))
        .collect(Collectors.toList());
    CinemaView.setItems(FXCollections.observableArrayList(sortedCinemas));
}

    @FXML
    private void sort(ActionEvent event) {
        sortByName();
    }
    
}
