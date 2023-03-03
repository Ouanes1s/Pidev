/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.reservation.entities.Reservation;
import edu.reservation.services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amine Khalfaoui
 */
public class AjouterReservationController implements Initializable {
 @FXML
    private TextField txt_dateres;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtnomevnmt;
    @FXML
    private TextField txtemail;
    @FXML
    private Button confirmer;
    @FXML
    private TextField txtcodeoffr;
    @FXML
    private TextField txttypeticket;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     @FXML
    private void ajouter (ActionEvent event) {
         String nom,prenom,email,date, codeoffr;
        String typeticket,nomevnmt;
        
        nom = txtnom.getText();
        prenom = txtprenom.getText();
        email = txtemail.getText();
        date = txt_dateres.getText();
        typeticket = txttypeticket.getText();
        codeoffr = txtcodeoffr.getText();
        nomevnmt = txtnomevnmt.getText();
        
        
         if (nom==null || prenom ==null || email==null || date==null || typeticket==null || codeoffr==null || nomevnmt==null)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        }else if (email.matches("(.*)@(.*)")==false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid email address");
            alert.show();
        }else if (codeoffr.length()!=8 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CODE");
            alert.show();

       
        
        }
        else{
        
        Reservation r = new Reservation (   nom ,  prenom ,  email ,  typeticket ,   nomevnmt, date, codeoffr  );
        ServiceReservation sr = new ServiceReservation();
    sr.ajouter(r);
    }
    }
}
