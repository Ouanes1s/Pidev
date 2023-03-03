/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;

import edu.reservation.entities.Blog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.reservation.entities.Reservation;
import edu.reservation.services.ServiceBlog;
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
public class AjouterBlogController implements Initializable {

    @FXML
    private TextField txttitre;
    @FXML
    private TextField txtcontenu;
    @FXML
    private TextField txtemail;
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
        String titre,email,contenu;
        
         titre = txttitre.getText();
        email = txtemail.getText();
        contenu = txtcontenu.getText();
        
        
        
        if (titre==null || email ==null || contenu==null )      
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
        }

       
        
        
        else{
        
        Blog r = new Blog (   titre ,  email ,  contenu );
        ServiceBlog sb = new ServiceBlog();
    sb.ajouter(r);
    }
    }  
}
