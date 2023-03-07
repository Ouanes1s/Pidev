/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.TacheReservation;

import finalintegration2.Models.Offre;
import finalintegration2.Services.ServiceOffre;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    @FXML
    private ImageView backkey;

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
    
}

    @FXML
    private void backtoaffichoffr(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("AfficherOffre.fxml"));
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
