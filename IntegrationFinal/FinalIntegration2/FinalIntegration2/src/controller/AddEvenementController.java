/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Categorie;
import entity.Evenements;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
import service.CategorieService;
import service.EvenementService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class AddEvenementController implements Initializable {

    @FXML
    private ImageView imv;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private TextField lieu;
    @FXML
    private TextField titre;
    @FXML
    private TextField nbrplace;

        int c;
        int file;
        File pDir;
        File pfile;
        String lien;
    
    
    EvenementService evenementService = new EvenementService();
    
    CategorieService categorieService = new CategorieService();
    
    @FXML
    private ChoiceBox<String> chexbox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
                    UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            }
            return null;
        };
            nbrplace.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        
        
        
        try {
            c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
            pDir = new File("src/gui/img/event" + c + ".jpg");
            lien = "event" + c + ".jpg";
            List<Categorie> l = categorieService.getAllCategories();
            
            for(Categorie c : l ){
            
                chexbox.getItems().addAll(c.getDescription());
            }
                
                
                // TODO
                } catch (SQLDataException ex) {
            Logger.getLogger(AddEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Upload(ActionEvent event) throws MalformedURLException {
        
        
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imv.setImage(image);
    }
    
        
        
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
 
        System.out.println("hhhh"+chexbox.getValue());
       
       if (lieu.getText().equals("") || titre.getText().equals("") || chexbox.getValue().equals("")|| file==0 || nbrplace.equals(0)){
       
        Alert alert = new Alert(Alert.AlertType.ERROR, "v??rifier votre champs", ButtonType.OK);
           alert.showAndWait();
        
       
       }
       else{
                   copier(pfile, pDir);

                          Date dd = new java.sql.Date(new Date(dated.getEditor().getText()).getTime());

        Date df = new java.sql.Date(new Date(datef.getEditor().getText()).getTime());
        Evenements e = new Evenements();
       e.setLieu(lieu.getText());
       e.setImage(lien);
       e.setNbreparticipants(0);
       e.setNbreplaces(Integer.valueOf(nbrplace.getText()));
       e.setTitre(titre.getText());
       e.setDateDebut(dd);
       e.setDateFin(df);
              e.setId_cat(categorieService.getCategorieByDescription(chexbox.getValue()).getId_categori());

       
              evenementService.addEvenement(e);

       
       
       
                  Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/gui/AffichierEvenement.fxml"));
               Stage myWindow = (Stage) titre.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
               }
       }
        
        
        
        
    }
    
    
    
        
             public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // R??sultat OK   
    }
}
