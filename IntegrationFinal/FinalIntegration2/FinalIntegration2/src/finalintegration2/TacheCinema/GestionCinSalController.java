/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.TacheCinema;

import finalintegration2.Models.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hayth
 */
public class GestionCinSalController implements Initializable {

    @FXML
    private Button butGC;
    @FXML
    private Button butGS;
    @FXML
    private Label usernameLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String nom = Session.getNom();
    String cin = Session.getCin();
    usernameLabel.setText("Welcome Back, "+"Nom: " + nom + " | CIN: " + cin);
    }    

    @FXML
    private void nav(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Cinemaa.fxml"));
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    }

    @FXML
    private void nav2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Salle.fxml"));
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    }
    
}
