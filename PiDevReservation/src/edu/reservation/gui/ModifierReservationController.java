/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;


import edu.reservation.entities.Reservation;

import edu.reservation.services.ServiceReservation;
import edu.reservation.utils.DataSource;
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
public class ModifierReservationController implements Initializable {

    @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation, String> NomColumn;
    @FXML
    private TableColumn<Reservation, String> PrenomColumn;
    @FXML
    private TableColumn<Reservation, String> EmailColumn;
    @FXML
    private TableColumn<Reservation, String> TypeColumn;
    @FXML
    private TableColumn<Reservation, String> NomEvnmtColumn;
    @FXML
    private TableColumn<Reservation, String> CodeColumn;
    @FXML
    private TableColumn<Reservation, String> DateResColumn;
    @FXML
    private ComboBox<String> boxtypetick;
    @FXML
    private TextField gnom;
    @FXML
    private TextField gprenom;
    @FXML
    private TextField gemail;
    @FXML
    private TextField gnomevnmt;
    @FXML
    private TextField gcode;
    @FXML
    private TextField gdateres;
    @FXML
    private Button btnModifier;
    @FXML
    private TextField gid;
    @FXML
    private TableColumn<Reservation, String> IDColumn;
    @FXML
    private ImageView backkey;
    @FXML
    private ImageView homekey;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<String> listtype = FXCollections.observableArrayList("","Balcony","First Row","Middle Row","Back Seats");
        boxtypetick.setValue("");
        boxtypetick.setItems(listtype);
        //Table
        table_affiche();
        table.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
        if (newValue != null) {
         this.gid.setText(Integer.toString(newValue.getId_res()));
        this.gnom.setText(newValue.getNom_res());
        this.gprenom.setText(newValue.getPrenom_res());
        this.gemail.setText(newValue.getEmail_res());
        this.gnomevnmt.setText(newValue.getNom_evnmt());
        this.boxtypetick.setItems(listtype);
        this.gcode.setText(newValue.getCode_offr());
        this.gdateres.setText(newValue.getDate_res());
         
        }   
        });
    } 
    public void table_affiche(){
        Connection cnx = DataSource.getInstance().getCnx();
        ObservableList<Reservation> res = FXCollections.observableArrayList();
        PreparedStatement stmt = null;
	ResultSet rst = null;
    
        try {
           String req = "SELECT * FROM Reservation ";
           stmt = cnx.prepareStatement(req);
	   
	    rst = stmt.executeQuery();
            
            {
                while(rst.next()){
                Reservation r = new Reservation();
                r.setId_res(rst.getInt("id_res"));
                r.setNom_res(rst.getString("nom_res"));
                r.setPrenom_res(rst.getString("prenom_res"));
                r.setEmail_res(rst.getString("email_res"));
                r.setNom_evnmt(rst.getString("nom_evnmt"));
                r.setCode_offr(rst.getString("code_offr"));
                r.setDate_res(rst.getString("date_res"));
                r.setTypeticket_res(rst.getString("typeticket_res"));
                
                
                res.add(r);}
            }   
            IDColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("id_res"));
            NomColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("nom_res"));
            PrenomColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("prenom_res"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("email_res"));
            TypeColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("typeticket_res"));
            NomEvnmtColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("nom_evnmt"));
            CodeColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("code_offr"));
            DateResColumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("date_res"));
          
            table.setItems(res);
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    
    }    

    @FXML
    private void getSelected(MouseEvent event) {
        Reservation clicked = table.getSelectionModel().getSelectedItem();
        gnom.setText(String.valueOf(clicked.getNom_res()));

    }

    @FXML
    private void Modifier(ActionEvent event) {
         int ID = Integer.parseInt(gid.getText());
        String nom,prenom,email,typetick,dateres,codeoffr, nomevnmt;
        
        
     nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        typetick = (String) boxtypetick.getSelectionModel().getSelectedItem();
        nomevnmt = gnomevnmt.getText();
        codeoffr = gcode.getText();
        
        dateres = gdateres.getText();
        //Contôle de saisie
         //Contôle de saisie
         if (nom==null || prenom ==null || email==null || dateres==null || typetick==null || codeoffr==null || nomevnmt==null)      
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
               
             Reservation r = new Reservation ( ID,  nom ,  prenom ,  email ,  typetick ,   nomevnmt, codeoffr, dateres  );
            
//           Reservation r1 = new Reservation (  r.getId_res(), nom ,  prenom ,  email ,  typetick ,   nomevnmt, codeoffr, dateres  );
            ServiceReservation sr = new ServiceReservation();
           
            
            
            
           
           
            sr.modifier(r);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully updated a reservation. ");
            alert.show();
            table_affiche();
            
        }
    }

    @FXML
    private void backtoaffichres(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("AfficherReservation.fxml"));
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
    private void todashboard(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("DashboardResAgent.fxml"));
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
    

