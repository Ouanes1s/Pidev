/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_user.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author chebi
 */
public class AuthentificationController implements Initializable {

    @FXML
    private TextField authemail;
    @FXML
    private Button connecter;
    @FXML
    private PasswordField authmdp;
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void authentification(ActionEvent event) {
    }

    @FXML
    private void mdp_oublie(MouseEvent event) {
    }

    @FXML
    private void back_tologin(MouseEvent event) {
    }
    
}
