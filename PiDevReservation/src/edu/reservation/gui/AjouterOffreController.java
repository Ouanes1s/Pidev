/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;

import edu.reservation.entities.Offre;
import edu.reservation.entities.Reservation;
import edu.reservation.services.ServiceOffre;
import edu.reservation.services.ServiceReservation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Amine Khalfaoui
 */
public class AjouterOffreController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtcontenu;
    @FXML
    private TextField txt_datedeb;
    @FXML
    private TextField txt_datefin;
    @FXML
    private TextField txtcode;
    @FXML
    private Button confirmer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
         String nomfilm,contenuoffr,datedebut,datefin , code;
          
        nomfilm = txtnom.getText();
        contenuoffr = txtcontenu.getText();
        datedebut = txt_datedeb.getText();
        datefin = txt_datefin.getText();
        code = txtcode.getText();
        
        if (nomfilm==null || contenuoffr ==null || datedebut==null || datefin==null || code==null )      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        
        }else if (code.length()!=8 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CODE");
            alert.show();

       
        
        }
        else{
        
        
        Offre r = new Offre (   nomfilm ,  contenuoffr ,  datedebut ,  datefin ,   code);
        ServiceOffre so = new ServiceOffre();
    so.ajouter(r);
    }
    
} }
