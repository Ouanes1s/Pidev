/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.TacheCinema;

import finalintegration2.Models.Cinema;
import finalintegration2.Services.ServiceCinema;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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

/**
 * FXML Controller class
 *
 * @author hayth
 */
public class CinemaUserController implements Initializable {

    @FXML
    private ListView<Cinema> CinemaView;
    @FXML
    private Button refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Cinema c = new Cinema();
        ServiceCinema sc=new ServiceCinema();
        List<Cinema> data=sc.agetAll();
        ObservableList<Cinema> observableData = FXCollections.observableArrayList(data);
        CinemaView.setItems(observableData);
    }    

    @FXML
    private void refresh(ActionEvent event) {
        Cinema c = new Cinema();
        ServiceCinema sc=new ServiceCinema();
        List<Cinema> data=sc.getAll();
        ObservableList<Cinema> observableData = FXCollections.observableArrayList(data);
        CinemaView.setItems(observableData);
    }

    @FXML
    private void map(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Map.fxml"));
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    }
    
}
