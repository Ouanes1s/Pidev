/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.user.gui;

import edu.user.services.EnvoyerEmail;
import edu.user.utils.ConnectionToDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author chebi
 */
public class ForgotMDPController implements Initializable {

    @FXML
    private TextField pwdemail;
    private TextField pwdcode;
    @FXML
    private Button codebutton;
    @FXML
    private Button resetbutton;
    @FXML
    private ImageView back;
int test;
    Parent root;
    private Stage stage;
    private Stage scene;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    Connection cnx = ConnectionToDB.getInstance().getConnection();   
 public boolean verifierEmailBd(String email) {
	PreparedStatement stmt = null;
	ResultSet rst = null;
	try {
	    String sql = "SELECT * FROM User WHERE email_user=?";
	    stmt = cnx.prepareStatement(sql);
	    stmt.setString(1, email);
	    rst = stmt.executeQuery();
	    if (rst.next()) {
		return true;
	    }
	} catch (SQLException ex) {
	    System.out.println(ex.getMessage());
	}
	return false;

    }
    @FXML
    private void sendcode(ActionEvent event) throws MessagingException {
        if (verifierEmailBd(pwdemail.getText())==true){
            pwdcode.setDisable(false);
            resetbutton.setDisable(false);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Next Step");
            alert.setHeaderText("Votre MDP est envoyé via email");
            alert.setContentText("Check Ton email pour recevoir ton MDP puis clique sur l'authentification bouton");
            alert.show();
            
            
           
            EnvoyerEmail e = new EnvoyerEmail();
         
                e.envoyer(pwdemail.getText(),mdptaker(pwdemail.getText()),nametaker(pwdemail.getText()));
        
        } else 
        {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Next Step");
            alert.setHeaderText("Email non valide");
            alert.setContentText("L'email que vous avez choisis n'est pas enregistré veuillez vous inscrire ");
            alert.show();
        }
        
        

    }
     public String mdptaker(String email) {
	PreparedStatement stmt = null;
	ResultSet rst = null;
	try {
	    String sql = "SELECT mdp_user FROM User WHERE email_user=?";
	    stmt = cnx.prepareStatement(sql);
	    stmt.setString(1, email);
	    rst = stmt.executeQuery();
	    while (rst.next()) {
		return rst.getString("mdp_user");
	    }
	} catch (SQLException ex) {
	    System.out.println(ex.getMessage());
	}
        return null;
	}
public String nametaker(String email) {
	PreparedStatement stmt = null;
	ResultSet rst = null;
	try {
	    String sql = "SELECT nom_user FROM User WHERE email_user=?";
	    stmt = cnx.prepareStatement(sql);
	    stmt.setString(1, email);
	    rst = stmt.executeQuery();
	    while (rst.next()) {
		return rst.getString("nom_user");
	    }
	} catch (SQLException ex) {
	    System.out.println(ex.getMessage());
	}
        return null;
	}
    @FXML
    private void reset_onclick(ActionEvent event) {
         try {
                     Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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

   
    

    @FXML
  private void back_tologin(MouseEvent event) {

    
    
 try {
                     Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
        }}
