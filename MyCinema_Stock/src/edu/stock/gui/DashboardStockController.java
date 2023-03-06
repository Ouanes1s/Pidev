/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.gui;

import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import edu.stock.entites.Categorie;
import edu.stock.entites.Produit;
import edu.stock.services.ServiceCategorie;
import edu.stock.services.ServiceProduit;
import edu.stock.utils.ConnexionBD;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
    private TextField ajt_tfCode;    
    @FXML
    private TextField aj_tfQuantite;
    @FXML
    private ComboBox<Categorie> ajt_cbCategorie;
    @FXML
    private TextField ajt_tfPrix;    
    @FXML
    private RadioButton ajt_rbOui;
    @FXML
    private RadioButton ajt_rbNon;
    @FXML
    private TextArea ajt_taDesc;
    @FXML
    private Button btnAjouter;
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
    private Button btnStock;
    @FXML
    private Button btnOffres;
    @FXML
    private Button btnAvis;
    @FXML
    private AnchorPane anchorDashboard;
    @FXML
    private AnchorPane anchoerAfficherLesProduits;
    @FXML
    private TableView<Produit> Aff_tabView;
    @FXML
    private TableColumn<Produit, String> Aff_tv_ColNom;
    @FXML
    private TableColumn<Produit, String> Aff_tv_ColImg;
    @FXML
    private TableColumn<Produit, Integer> Aff_tv_ColCat;
    @FXML
    private TableColumn<Produit, Integer> Aff_tv_ColQuantite;
    @FXML
    private TableColumn<Produit, Float> Aff_tv_ColPrix;
    @FXML
    private TableColumn<Produit, String> Aff_tv_ColDesc;
    @FXML
    private TableColumn<Produit, Integer> Aff_tv_ColEtat;
    @FXML
    private TableColumn<Produit, String> Aff_tv_ColCode;
    @FXML
    private TableColumn<Produit, String> Aff_tv_ColAction;
    
    @FXML
    private Button Aff_btn_AjouterProd;
    @FXML
    private AnchorPane anchorAjouterProduit;
    @FXML
    private ImageView ajt_img;
    @FXML
    private Button btnAjouterCategorie;
    @FXML
    private Button btnModifCategorie;
    @FXML
    private Button btnSuppCategorie;
    @FXML
    private TextField tfAjtCategorie;
    @FXML
    private TextField tfrechercherCategorie;
    @FXML
    private ListView<Categorie> ListeCategorie;
    @FXML
    private AnchorPane anchorCategorie;





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
    

    public ObservableList<Produit> ListeProduits = FXCollections.observableArrayList();
    private Image img;
    private String path;
    public ObservableList<Categorie> ListeCategories = FXCollections.observableArrayList();


    @FXML
    public void switchForm(ActionEvent event){
        if(event.getSource() == btnDashboard){
            anchorDashboard.setVisible(true);
            anchorAjouterProduit.setVisible(false);
            anchoerAfficherLesProduits.setVisible(false);
            anchorCategorie.setVisible(false);
            //lbeki lkolhom false
            
            btnDashboard.setStyle("-fx-background-color:linear-gradient(to bottom right, #530D68,#741292)");
            btnCategorie.setStyle("-fx-background-color:transparent");
            btnStock.setStyle("-fx-background-color:transparent");
            btnOffres.setStyle("-fx-background-color:transparent");
            btnAvis.setStyle("-fx-background-color:transparent");
        
        }else if (event.getSource()== btnCategorie){
            anchorCategorie.setVisible(true);
            anchorDashboard.setVisible(false);
            anchorAjouterProduit.setVisible(false);
            anchoerAfficherLesProduits.setVisible(false);
            
            ServiceCategorie sc = new ServiceCategorie();
            sc.afficherTous().forEach(e->{ListeCategories.add(e);});
            ListeCategorie.setItems(ListeCategories);
            
        }else if (event.getSource()== btnStock){
            anchoerAfficherLesProduits.setVisible(true);
            anchorDashboard.setVisible(false);
            anchorAjouterProduit.setVisible(false);
            anchorCategorie.setVisible(false);
          
            
            Aff_tv_ColNom.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
            Aff_tv_ColImg.setCellValueFactory(new PropertyValueFactory<>("imgProd"));
            Aff_tv_ColCat.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
            Aff_tv_ColQuantite.setCellValueFactory(new PropertyValueFactory<>("quantiteProd"));
            Aff_tv_ColPrix.setCellValueFactory(new PropertyValueFactory<>("prixProd"));
            Aff_tv_ColDesc.setCellValueFactory(new PropertyValueFactory<>("descriptionProd"));
            Aff_tv_ColEtat.setCellValueFactory(new PropertyValueFactory<>("etatProd"));
            Aff_tv_ColCode.setCellValueFactory(new PropertyValueFactory<>("codeProd"));
           // Aff_tv_ColAction.setCellValueFactory(new PropertyValueFactory<>("action"));
            ServiceProduit sp = new ServiceProduit();
            sp.afficherTous().forEach(e->{ListeProduits.add(e);});
            Aff_tabView.setItems(ListeProduits);
            
            
        }else if (event.getSource()== btnOffres){
            //anchorOffres.setVisible(true);
            anchorDashboard.setVisible(false);
            anchorAjouterProduit.setVisible(false);
            anchoerAfficherLesProduits.setVisible(false);
            anchorCategorie.setVisible(false);
            
        }else if (event.getSource()== btnAvis){
            //anchorAvis.setVisible(true);
            anchorDashboard.setVisible(false);
            anchorAjouterProduit.setVisible(false);
            anchoerAfficherLesProduits.setVisible(false);
            anchorCategorie.setVisible(false);
            
        }else if (event.getSource()== Aff_btn_AjouterProd){
            anchorAjouterProduit.setVisible(true);
            anchorDashboard.setVisible(false);
            anchoerAfficherLesProduits.setVisible(false);
            anchorCategorie.setVisible(false);          
            //lbeki false
        }
    }
    
 //***************************Ajouter Produit**********************************************//   
    @FXML
    public void insertImg(){      
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image 1file","*jpg","*png"));
        
        File file = open.showOpenDialog(Anchorwindow.getScene().getWindow());
        if(file != null){
            path = file.getAbsolutePath();
            img= new Image(file.toURI().toString(),134,154,false,true);
            ajt_img.setImage(img);
        }   
    }

    @FXML
    private void enregistrerAjtProd(ActionEvent event) {
        //Image imageProd = ajt_img.getImage();
        String nomProd = ajt_tfNom.getText();
        String codeProd = ajt_tfCode.getText();
        //int idcatProd = ajt_cbCategorie.getSelectionModel().getSelectedItem().getIdCategorie();
        int quantiteProd = Integer.parseInt(aj_tfQuantite.getText());
        Float prixProd = Float.parseFloat(ajt_tfPrix.getText());
        //int etatProd = Integer.parseInt(ajt_rbOui.getText()); 
        String descriptionProd = ajt_taDesc.getText();
        
        Produit p = new Produit(codeProd,nomProd, quantiteProd, prixProd,descriptionProd);
        ServiceProduit sp = new ServiceProduit();
        sp.ajouter(p); 
                
        /*        
        Alert alert;
        if( ajt_cbCategorie.getSelectionModel().getSelectedItem() == null
                    || ajt_tfNom.getText().isEmpty() 
                    || ajt_tfCode.getText().isEmpty() 
                    || aj_tfQuantite.getText().isEmpty()
                    ||ajt_tfPrix.getText().isEmpty()
                    ||ajt_rbOui.getText().isEmpty()
                    ||ajt_taDesc.getText().isEmpty()  )
            {
            alert= new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs");
            alert.showAndWait();
            }else {
                Produit p = new Produit(codeProd,nomProd, quantiteProd, prixProd,etatProd,descriptionProd,idcatProd);
                ServiceProduit sp = new ServiceProduit();
                sp.ajouter(p);            
            }
            
        */    
    }
    
 //***************************CRUD Categorie*********************************************//   
    
    @FXML
    private void AjouterCategorie(ActionEvent event) {
        String nomCategorie= tfAjtCategorie.getText();
        
        Categorie c = new Categorie(nomCategorie);
        ServiceCategorie sc = new ServiceCategorie();
        sc.ajouter(c); 
        
    }

    
    @FXML
    private void ModifierCategorie(ActionEvent event) {
    
    }

    @FXML
    private void supprimerCategorie(ActionEvent event) {
    }

    @FXML
    private void rechercherCategorie(ActionEvent event) {
    }
    
    
    
    
    
    
    
    
    
    

}
    
    
    
    

