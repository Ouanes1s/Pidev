/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;

import edu.reservation.entities.Offre;
import edu.reservation.services.ServiceOffre;
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
public class ModifierOffreController implements Initializable {

    @FXML
    private TableView<Offre> table;
    @FXML
    private TableColumn<Offre, String> IDColumn;
    @FXML
    private TableColumn<Offre, String> NomColumn;
    @FXML
    private TableColumn<Offre, String> ContenuColumn;
    @FXML
    private TableColumn<Offre, String> DatedebColumn;
    @FXML
    private TableColumn<Offre, String> DateFinColumn;
    @FXML
    private TableColumn<Offre, String> CodeColumn;
    @FXML
    private TextField gid;
    @FXML
    private TextField gnomevnmt;
    @FXML
    private TextField gcontenu;
    @FXML
    private TextField gdatedeb;
    @FXML
    private TextField gdatefin;
    @FXML
    private TextField gcode;
    @FXML
    private Button btnModifier;

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
            this.gid.setText(Integer.toString(newValue.getId_offr()));
      
        this.gnomevnmt.setText(newValue.getNomfilm_offr());
        this.gcontenu.setText(newValue.getContenu_offr());
        this.gdatedeb.setText(newValue.getDatedebut_offr());
         this.gdatefin.setText(newValue.getDatefin_offr());
        this.gcode.setText(newValue.getCode_offr());

     
         
        }   
        });
    }    
    
     public void table_affiche(){
        Connection cnx = DataSource.getInstance().getCnx();
        ObservableList<Offre> offrs = FXCollections.observableArrayList();
        PreparedStatement stmt = null;
	ResultSet rst = null;
    
        try {
           String req = "SELECT * FROM Offre ";
           stmt = cnx.prepareStatement(req);
	   
	    rst = stmt.executeQuery();
            
            {
                while(rst.next()){
                Offre r = new Offre();
                r.setId_offr(rst.getInt("id_offr"));
                
                r.setNomfilm_offr(rst.getString("nomfilm_offr"));
                
                r.setContenu_offr(rst.getString("contenu_offr"));
                r.setDatedebut_offr(rst.getString("datedebut_offr"));
                r.setDatefin_offr(rst.getString("datefin_offr"));
                r.setCode_offr(rst.getString("code_offr"));
               
                
                
                offrs.add(r);}
            }   
            
            IDColumn.setCellValueFactory(new PropertyValueFactory<Offre,String>("id_offr"));
            NomColumn.setCellValueFactory(new PropertyValueFactory<Offre,String>("nomfilm_offr"));
           
            ContenuColumn.setCellValueFactory(new PropertyValueFactory<Offre,String>("contenu_offr"));
           DatedebColumn.setCellValueFactory(new PropertyValueFactory<Offre,String>("datedebut_offr"));
            DateFinColumn.setCellValueFactory(new PropertyValueFactory<Offre,String>("datefin_offr"));
           CodeColumn.setCellValueFactory(new PropertyValueFactory<Offre,String>("code_offr"));
          
          
            table.setItems(offrs);
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    
    }    

    @FXML
    private void getSelected(MouseEvent event) {
        
         Offre clicked = table.getSelectionModel().getSelectedItem();
        gcode.setText(String.valueOf(clicked.getCode_offr()));
    }

    @FXML
    private void Modifier(ActionEvent event) {
         int ID = Integer.parseInt(gid.getText());
        String nomevnmt,contenu,datedeb, datefin, code;
        
        
     
        nomevnmt = gnomevnmt.getText();
        contenu = gcontenu.getText();
        datedeb = gdatedeb.getText();
        datefin = gdatefin.getText();
        code = gcode.getText();
        
        //Contôle de saisie
         //Contôle de saisie
         if (nomevnmt==null || contenu ==null || datedeb==null || datefin==null || code==null )      
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
               
             Offre r = new Offre ( ID,  nomevnmt ,  contenu ,  datedeb, datefin, code);
            
            ServiceOffre so = new ServiceOffre();
           
            
            
            
           
           
            so.modifier(r);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully updated an offer. ");
            alert.show();
            table_affiche();
            
        }
    }
    }
    

