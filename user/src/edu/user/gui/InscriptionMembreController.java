/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.user.gui;

import edu.user.entities.Membre;
import edu.user.services.UserCRUD;
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
 * @author chebi
 */
public class InscriptionMembreController implements Initializable {
    @FXML
    private TextField txt_date;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtcin;
    @FXML
    private TextField txtemail;
    @FXML
    private Button confirmer;
    @FXML
    private TextField txtmdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterMembre(ActionEvent event) {
         String nom,prenom,email,date;
        String cin,mdp;
        
        nom = txtnom.getText();
        prenom = txtprenom.getText();
        email = txtemail.getText();
        date = txt_date.getText();
        cin = txtcin.getText();
        mdp = txtmdp.getText();
        
        //Contôle de saisie
        if (nom==null || prenom ==null || email==null || date==null || cin==null || mdp==null)      
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
        }else if (cin.length()!=8 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();

        }else if (cin.matches("[0-9]*")==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();
        }else if (mdp.length()<8){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid password");
            alert.show();
        }
        else{
            //Methode Ajouter    
            Membre user1 = new Membre (nom,prenom,cin,email,mdp,date);
            UserCRUD uc = new UserCRUD();
            
            //controle d'.existance
            if (uc.VerifCin(user1.getCin_user())!=0 || uc.verifierEmailBd(user1.getEmail_user())==true){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("USER ALREADY EXISTS. CIN or Email is taken");
            alert.show(); 
            }
            
            else {
            
            uc.ajouterUserMembre(user1);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully created your account. Please check your e-mail box to get your ID ");
            alert.show();
            
            /*try {
                     Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
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
                }*/
            
            }
    }
    
    }}