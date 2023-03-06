/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.gui;

import edu.stock.entites.Categorie;
import edu.stock.services.ServiceCategorie;
import edu.stock.utils.ConnexionBD;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author maidi
 */
public class CategorieProduitController implements Initializable {

    @FXML
    private ListView<Categorie> ListeCategorie;
    @FXML
    private Button btnAjouterCategorie;
    @FXML
    private Button btnModifCategorie;
    @FXML
    private Button btnSuppCategorie;
    @FXML
    private TextField tfrechercherCategorie;
    @FXML
    private Button btnAfficherCategorie;
    @FXML
    private TextField tfNomCat;

    
    public ObservableList<Categorie> ListeCategories = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ServiceCategorie sc = new ServiceCategorie();
            sc.afficherTous().forEach(e->{ListeCategories.add(e);});
            ListeCategorie.setItems(ListeCategories);
    }    

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        String nomCat = tfNomCat.getText();
        if (nomCat==null)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Veuillez entrer un nom");
            alert.show();
        }else{
            Categorie c = new Categorie(nomCat);
            ServiceCategorie sc = new ServiceCategorie();
            sc.ajouter(c);   
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confimation");
            alert.setHeaderText("Succès");
            alert.setContentText("Catégorie créé avec succès");
            alert.show();
        }
    }
    

    @FXML
    private void AfficherCategorie(ActionEvent event) {
            ListeCategories.clear();

            ServiceCategorie sc = new ServiceCategorie();
            sc.afficherTous().forEach(e->{ListeCategories.add(e);});
            ListeCategorie.setItems(ListeCategories);
    }
    @FXML
    private void ModifierCategorie(ActionEvent event) {

    }
    
    @FXML
    private void supprimerCategorie(ActionEvent event) {
    
    Connection cnx = ConnexionBD.getInstance().getCnx();
    PreparedStatement ps = null;
    Categorie cat= new Categorie();        
    Categorie selected = ListeCategorie.getSelectionModel().getSelectedItem();

    if (selected != null) {
        ListeCategorie.getItems().remove(selected);   
        try {
            String req = "DELETE FROM categorie WHERE id = ?";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, selected.getIdCategorie());
            ps.executeUpdate();
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de suppression");
        alert.setHeaderText("Aucune catégorie sélectionnée");
        alert.setContentText("Veuillez sélectionner une catégorie à supprimer.");
        alert.showAndWait();        
    }  
    }
        
        
    @FXML
    private void rechercherCategorie(ActionEvent event) {
        ServiceCategorie sc = new ServiceCategorie();
        ListeCategorie.setItems(ListeCategories);
        tfrechercherCategorie.setOnKeyReleased(e -> {
        String cat = tfrechercherCategorie.getText();
        //List<Categorie> filteredCategories = sc.rechercher(cat);
        ListeCategories.setAll(sc.rechercher(cat));
        });    
    }

/*    
    @FXML
    private void rechercherCategorie(ActionEvent event) {
    // public Integer rechercheUser(Categorie c){
         Categorie c=new Categorie();
                    Integer exist = 0;
                    Connection cnx = ConnexionBD.getInstance().getCnx();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    try {
                        pst = cnx.prepareStatement("SELECT COUNT(*) AS count FROM categorie WHERE nom=?");
                        pst.setString(1, c.getNomCategorie());
                        rs = pst.executeQuery();
                        if (rs.next()) {
                             exist=rs.getInt("count"); 
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                   // return exist;
    }   

 */   





}
