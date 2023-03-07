/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Evenements;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import service.EvenementService;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowEvenementController implements Initializable {

    @FXML
    private ListView<Evenements> listView;
   
    ObservableList<Evenements> data;
    
    public static int idE ;
    
    EvenementService ds = new EvenementService();

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            EvenementService cs = new EvenementService();
            data = (ObservableList<Evenements>) cs.getAllEvenement();   
            listView.setItems(data);
            listView.setCellFactory((ListView<Evenements> param) -> new ListViewEvenement());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    




    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void detail(ActionEvent event) {
        
        
            ObservableList<Evenements> e;
            e = listView.getSelectionModel().getSelectedItems();

            
          for (Evenements m : e) 
          idE=m.getId();
        
      Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) listView.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        } 
        }
     
        
        
    




    }

    

