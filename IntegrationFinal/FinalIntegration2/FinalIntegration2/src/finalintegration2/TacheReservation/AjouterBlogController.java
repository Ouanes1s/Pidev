/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.TacheReservation;

import finalintegration2.Models.Blog;

import finalintegration2.Services.Rss;
import finalintegration2.Services.ServiceBlog;
import finalintegration2.Utils.ConnectionToDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private ImageView backkey;
    @FXML
    private Button affichblgkey;
  
    @FXML
    private Label txtRSS;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> rssFeeds = new ArrayList<>();        
         
         rssFeeds.add("https://www.imdb.com/news/ni63983161");
         rssFeeds.add("https://rss.app/feeds/tcUbBqXKZsw6YYNd.xml");
        
 StringBuilder builder = new StringBuilder();
        for (String feed : rssFeeds) {
            builder.append(Rss.readRSS(feed)).append("\n\n");
        }
        txtRSS.setText(builder.toString());
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
        if (VerifTitre(r.getTitre_blg())!=0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Be Creative!");
            alert.setContentText("This Title Already exists");
            alert.show(); 
            }
            
            
            else {
    sb.ajouter(r);
    }
    }  
}
        Connection cnx = ConnectionToDB.getInstance().getConnection();

     public int VerifTitre(String titre) {
        Blog r = null;
         int nb = 0;
        try {
            String req = "Select * from blog";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (titre.equals(rs.getString("titre_blg"))){
           nb=1;}
                
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;

        
    }

    @FXML
    private void backtodash(MouseEvent event) {
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

    @FXML
    private void toaffichblg(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("AfficherBlogClient.fxml"));
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

