package edu.stock.controllers;

import edu.stock.entites.Produit;
import edu.stock.services.DatabaseHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserPanel implements Initializable {
    public FontAwesomeIconView adminPage;
    @FXML
    private JFXTextField txtfieldObjet;

    @FXML
    private Button buttonMusic;
    @FXML
    private JFXTextField adresse_txt;
    @FXML
    private JFXTextArea description_txt;
    @FXML
    private Button play;
    @FXML
    private Button goToHome;

    @FXML
    private HBox hbox,hboxfirst ;


    public void btnHome(ActionEvent actionEvent) {
    }

    public void mailfonction(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Produit> ls=new ArrayList<Produit>();
        List<Produit> lss=new ArrayList<>();
        DatabaseHelper s=new DatabaseHelper();
        //ServiceProduit ss=new ServiceProduit();
        try {
            ls= s.read();
            lss=s.read();



            for (int i = 0; i < ls.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();

            }
            for(int j=0;j<lss.size();j++){
                FXMLLoader fxmlLoader = new FXMLLoader();

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
