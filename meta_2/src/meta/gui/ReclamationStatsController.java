/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meta.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Marwa
 */
public class ReclamationStatsController implements Initializable {
    @FXML
    private PieChart piechartrev;
    @FXML
    private Button Retour2;
    
    ObservableList< PieChart.Data> piechartdata;
    ArrayList<String> p = new ArrayList<String>();
    ArrayList<Integer> c = new ArrayList<Integer>();
    Connection cnx = MyDB.getInstance().getCnx();

    public void loadData() {
        String query = "select COUNT(*), reclamation.type_reclamation, reclamation.categorie_reclamation FROM reclamation GROUP BY reclamation.type_reclamation"; //ORDER BY asc
        piechartdata = FXCollections.observableArrayList();
        cnx = MyDB.getInstance().getCnx();
        try {
            ResultSet rs = cnx.createStatement().executeQuery(query);
            while (rs.next()) {
                piechartdata.add(new PieChart.Data(rs.getString("type_reclamation"),rs.getInt("count(*)")));
                p.add(rs.getString("type_reclamation"));
                c.add(rs.getInt("count(*)"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        piechartrev.setData(piechartdata);
    }    
    
    @FXML
    private Button goHomeBtn;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    FXMLLoader fxmlLoader;

    @FXML
    void switchToMainFront(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(GestionGererReclamationController.class.getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To MyCinema");
        stage.setScene(scene);
        stage.show();
    }
    public void Retour2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GestionGererReclamation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bienvenue encore une fois");
        stage.show();
        
    }
}
