/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maidi
 */
public class DashboardStockController implements Initializable {

    @FXML
    private Button Ajt_btn_import;
    @FXML
    private TextField ajt_tfNom;
    @FXML
    private TextField aj_tfQuantite;
    @FXML
    private ComboBox<?> ajt_cbCategorie;
    @FXML
    private RadioButton ajt_rbOui;
    @FXML
    private RadioButton ajt_rbNon;
    @FXML
    private TextArea ajt_taDesc;
    @FXML
    private Button btnAjouter;
    @FXML
    private TableView<?> Ajt_tabView;
    @FXML
    private TableColumn<?, ?> ajt_tv_ColNom;
    @FXML
    private TableColumn<?, ?> ajt_tv_Cat;
    @FXML
    private TableColumn<?, ?> ajt_tv_ColQuantite;
    @FXML
    private TableColumn<?, ?> ajt_tv_ColPrix;
    @FXML
    private TableColumn<?, ?> ajt_tv_ColDesc;
    @FXML
    private TableColumn<?, ?> ajt_tv_ColEtat;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnReduce;
    @FXML
    private AnchorPane Anchorwindow;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnCategorie;
    @FXML
    private Button btnProduits;
    @FXML
    private Button btnOffres;
    @FXML
    private Button btnAvis;
    @FXML
    private AnchorPane anchorDashboard;
    @FXML
    private AnchorPane anchorAjouterProd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CloseWindow(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void ReduceWindow(ActionEvent event) {
        Stage stage = (Stage)Anchorwindow.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    public void switchForm(ActionEvent event){
        if(event.getSource() == btnDashboard){
            anchorDashboard.setVisible(true);
            anchorAjouterProd.setVisible(false);
            //lbeki lkolhom false
            
            btnDashboard.setStyle("-fx-background-color:linear-gradient(to bottom right, #530D68,#741292)");
            btnCategorie.setStyle("-fx-background-color:transparent");
            btnProduits.setStyle("-fx-background-color:transparent");
            btnOffres.setStyle("-fx-background-color:transparent");
            btnAvis.setStyle("-fx-background-color:transparent");
        }else if (event.getSource()== btnCategorie){
          //  anchorCategorie.setVisible(true);
            anchorDashboard.setVisible(false);
            anchorAjouterProd.setVisible(false);
        }else if (event.getSource()== btnProduits){
            anchorAjouterProd.setVisible(true);
            anchorDashboard.setVisible(false);
            
        }else if (event.getSource()== btnOffres){
            //anchorOffres.setVisible(true);
            anchorAjouterProd.setVisible(false);
            anchorDashboard.setVisible(false);
            
        }else if (event.getSource()== btnAvis){
            //anchorAvis.setVisible(true);
            anchorAjouterProd.setVisible(false);
            anchorDashboard.setVisible(false);
            
        }
    }
    
    
    
    
    
    
}
