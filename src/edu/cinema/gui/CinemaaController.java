/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinema.gui;
import edu.cinema.entities.Cinema;
import edu.cinema.services.ServiceCinema;
import edu.cinema.utils.DataSource;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hayth
 */
public class CinemaaController implements Initializable {

    @FXML
    private TextField tfnom_cinema;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfnom_cinema_sup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        String nom_cinema =tfnom_cinema.getText();
        String adresse=tfadresse.getText();
        String num=tfnum.getText();
        Cinema c = new Cinema (nom_cinema,adresse,num);
        ServiceCinema sc = new ServiceCinema();
        sc.ajouter2(c);
   }
    
}
