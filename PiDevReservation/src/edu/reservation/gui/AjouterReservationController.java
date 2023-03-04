/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;

import edu.reservation.entities.Offre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.reservation.entities.Reservation;
import edu.reservation.services.ServiceReservation;
import edu.reservation.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private ComboBox<String> boxtypetick;
    @FXML
    private Button affichoffr;
    @FXML
    private ImageView backkey;
    @FXML
    private ImageView home;
       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
               //combobox
      ObservableList<String> listtype = FXCollections.observableArrayList("","Balcony", "First row","Middle Row","BackSeats");
        boxtypetick.setValue("");
        boxtypetick.setItems(listtype);
    }  
     @FXML
    private void ajouter (ActionEvent event) {
         String nom,prenom,email,date, codeoffr;
        String typeticket,nomevnmt;
        
        nom = txtnom.getText();
        prenom = txtprenom.getText();
        email = txtemail.getText();
        date = txt_dateres.getText();
        typeticket = (String) boxtypetick.getSelectionModel().getSelectedItem();
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
        
        Reservation r = new Reservation (   nom ,  prenom ,  email ,  typeticket ,   nomevnmt, codeoffr ,date  );
        ServiceReservation sr = new ServiceReservation();
        if (VerifOffr(r.getCode_offr(),r.getNom_res(),r.getPrenom_res() )!=0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("YOU ALREADY USED THIS OFFER");
            alert.show(); 
            }
            
            
            else {
    sr.ajouter(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully a reservation. Please check your e-mail box to get your Confirmation ");
            alert.show();
    }
    }
    }

 Connection cnx = DataSource.getInstance().getCnx();
    public int VerifOffr(String code, String nom, String prenom ) {
        Offre r = null;
         int nb = 0;
        try {
            String req = "Select * from reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (code.equals(rs.getString("code_offr"))&& nom.equals(rs.getString("nom_res"))&& prenom.equals(rs.getString("prenom_res")) ){
           nb=1;}
                
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;    }

    @FXML
    private void toaffichoffr(MouseEvent event) {
        
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("AfficherOffreClient.fxml"));
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


    @FXML
    private void backtodashb(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("DashboardReservation.fxml"));
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
