/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.user.gui;

import edu.user.entities.Agent;
import edu.user.entities.User;
import edu.user.services.UserCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chebi
 */
public class AjouterAgentController implements Initializable {

    @FXML
    private ComboBox<String> boxrole2;
    @FXML
    private TextField gnom;
    @FXML
    private TextField gprenom;
    @FXML
    private TextField gcin;
    @FXML
    private TextField gemail;
    @FXML
    private TextField gmdp;
    @FXML
    private TextField gsalaire;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField gcontract;
    @FXML
    private ImageView back;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listrole = FXCollections.observableArrayList("","Stock","Reclamation","Gestion de Reservation","Films et events","Cinemas et salles","Gestion de Parkings");
        boxrole2.setValue("");
        boxrole2.setItems(listrole);
        // TODO
    }    


    @FXML
    private void AjouterAgent(ActionEvent event) {
        String nom,prenom,email,role,mdp;
        String cin;
        String salaire , datecontrac ;
        
        nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        role = (String) boxrole2.getSelectionModel().getSelectedItem();
        cin = gcin.getText();
        mdp = gmdp.getText();
        salaire = gsalaire.getText();
        datecontrac = gcontract.getText();
        
        //Contôle de saisie
        if (nom==null || prenom ==null || email==null || role==null || cin==null || mdp==null|| gsalaire==null|| datecontrac==null)      
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
        }else{
            //Methode Ajouter    
            User user1 = new Agent (nom,prenom,cin,email,mdp,salaire,role,datecontrac);
             UserCRUD uc = new UserCRUD();
           
           if (uc.VerifCin(user1.getCin_user())!=0 || uc.verifierEmailBd(user1.getEmail_user())==true){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("USER ALREADY EXISTS. CIN or Email is taken");
            alert.show(); 
            }
            
            
            else {
           
            uc.ajouterUserAgent(user1);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully created your account. Please check your e-mail box to get your ID ");
            alert.show();
            
            }} 
        
    }

    @FXML
    private void back_tologin(MouseEvent event) {
        
         try {
                     Parent root = FXMLLoader.load(getClass().getResource("AgentSettings.fxml"));
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

    

