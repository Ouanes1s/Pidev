/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.TacheUser;

import finalintegration2.Models.Session;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chebi
 */
public class WelcomeAdminController implements Initializable {

    @FXML
    private Button signup;
    @FXML
    private Button login;
     Parent root;
    
    private Stage stage;
    private Stage scene;
    @FXML
    private ImageView signout;
    @FXML
    private Label usernameLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      setUserInfo();  
        
    }    
public void setUserInfo() {
    String nom = Session.getNom();
    String cin = Session.getCin();
    usernameLabel.setText("Welcome Back, "+"Nom: " + nom + " | CIN: " + cin);
}

    @FXML
    private void signup(ActionEvent event) {
        
        try {
             root = FXMLLoader.load(getClass().getResource("AgentSettings.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void login(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("MembreSettings.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void signoutclick(MouseEvent event) {
        
     try {
                     Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
         Stage Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
    }
    
}