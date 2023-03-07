/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.TacheReservation;

import finalintegration2.Models.Blog;


import finalintegration2.Services.ServiceBlog;
import finalintegration2.Utils.ConnectionToDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amine Khalfaoui
 */
public class ModifierBlogController implements Initializable {

    @FXML
    private TableView<Blog> table;
    @FXML
    private TableColumn<Blog, String> TitreColumn;
    @FXML
    private TableColumn<Blog, String> EmailColumn;
    @FXML
    private TableColumn<Blog, String> ContenuColumn;
    @FXML
    private TextField gtitre;
    @FXML
    private TextField gemail;
    @FXML
    private TextField gcontenu;
    @FXML
    private Button btnModifier;
    @FXML
    private TableColumn<Blog, String> IDColumn;
    @FXML
    private TextField gid;
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
        
    
        //Table
        table_affiche();
        table.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
        if (newValue != null) {
            this.gid.setText(Integer.toString(newValue.getId_blg()));
      
        this.gtitre.setText(newValue.getTitre_blg());
        this.gemail.setText(newValue.getEmail_blg());
        this.gcontenu.setText(newValue.getContenu_blg());
     
         
        }   
        });
    }  
    
    public void table_affiche(){
        Connection cnx = ConnectionToDB.getInstance().getConnection();
        ObservableList<Blog> blgs = FXCollections.observableArrayList();
        PreparedStatement stmt = null;
	ResultSet rst = null;
    
        try {
           String req = "SELECT * FROM Blog ";
           stmt = cnx.prepareStatement(req);
	   
	    rst = stmt.executeQuery();
            
            {
                while(rst.next()){
                Blog r = new Blog();
                r.setId_blg(rst.getInt("id_blg"));
                
                r.setTitre_blg(rst.getString("titre_blg"));
                r.setEmail_blg(rst.getString("email_blg"));
                r.setContenu_blg(rst.getString("contenu_blg"));
               
                
                
                blgs.add(r);}
            }   
            
            IDColumn.setCellValueFactory(new PropertyValueFactory<Blog,String>("id_blg"));
            TitreColumn.setCellValueFactory(new PropertyValueFactory<Blog,String>("titre_blg"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<Blog,String>("email_blg"));
            ContenuColumn.setCellValueFactory(new PropertyValueFactory<Blog,String>("contenu_blg"));
          
          
            table.setItems(blgs);
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    
    }    


    @FXML
    private void getSelected(MouseEvent event) {
        
         Blog clicked = table.getSelectionModel().getSelectedItem();
        gtitre.setText(String.valueOf(clicked.getTitre_blg()));
    }

    @FXML
    private void Modifier(ActionEvent event) {
        int ID = Integer.parseInt(gid.getText());
        String titre,email,contenu;
        
        
     
        titre = gtitre.getText();
        email = gemail.getText();
        contenu = gcontenu.getText();
        
        //Contôle de saisie
         //Contôle de saisie
         if (titre==null || contenu ==null || contenu==null )      
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
//        }else if (codeoffr.length()!=8 ){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Failed");
//            alert.setHeaderText("Attention !!");
//            alert.setContentText("Enter a valid CODE");
//            alert.show();

       
        
        }
        else{
               
             Blog r = new Blog ( ID,  titre ,  email ,  contenu );
            
//           Reservation r1 = new Reservation (  r.getId_res(), nom ,  prenom ,  email ,  typetick ,   nomevnmt, codeoffr, dateres  );
            ServiceBlog sb = new ServiceBlog();
           
            
            
            
           
           
            sb.modifier(r);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully updated a reservation. ");
            alert.show();
            table_affiche();
            
        }
    }

    @FXML
    private void backtoaffichblg(MouseEvent event) {
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
    

