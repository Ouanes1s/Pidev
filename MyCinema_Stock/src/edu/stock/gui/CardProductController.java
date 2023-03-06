/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.gui;

import edu.stock.entites.Produit;
import edu.stock.services.ServiceProduit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author maidi
 */
public class CardProductController implements Initializable {

    @FXML
    private ImageView imgCard;
    @FXML
    private Label tfCardNom;
    @FXML
    private Label tfCardPrix;
    @FXML
    private Label tfCardOffre;

    public ObservableList<Produit> ListeProduits = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Produit p){
        Image img = new Image(getClass().getResourceAsStream(p.getImgProd()));
        imgCard.setImage(img);
        tfCardNom.setText(p.getNomProd());
        tfCardPrix.setText(String.valueOf(p.getPrixProd()));
        
        ServiceProduit sp = new ServiceProduit();
        sp.afficherTous().forEach(e->{ListeProduits.add(e);});
        //Aff_tabView.setItems(ListeProduits);
        
    }    
}
