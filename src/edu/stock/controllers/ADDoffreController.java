package edu.stock.controllers;

import edu.stock.entites.*;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.stock.utils.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ADDoffreController implements Initializable {

    @FXML
    private JFXTextField title_txt; //idoffre

    @FXML
    private JFXTextArea description_txt; //idprod

    @FXML
    private JFXTextField qte_txt;

    @FXML
    private JFXTextField price_txt;

    @FXML
    private JFXTextField categ_txt;

    @FXML
    private JFXTextField dis_txt; // pourcentage

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Offre off = null;
    private boolean update;
    int offreId;


    @FXML
    public void AddButton(MouseEvent mouseEvent) {
        connection = DatabaseConnection.getInstance().getConnection();
        String title = title_txt.getText();
        String description=description_txt.getText();
      /*  String qte=qte_txt.getText();
        String price=price_txt.getText();
        String Category_id=categ_txt.getText();*/
        String disable=dis_txt.getText();






        if (title.isEmpty() || description.isEmpty()
                /*|| qte.isEmpty()||price.isEmpty()
                ||Category_id.isEmpty()*/
                ||disable.isEmpty()   ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            ClearButton();

        }
    }


    public void ClearButton() {
        title_txt.setText(null);
        description_txt.setText(null);
      /*  qte_txt.setText(null);
        price_txt.setText(null);
        categ_txt.setText(null);*/
        dis_txt.setText(null);
    }
    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, title_txt.getText());
            preparedStatement.setString(2, description_txt.getText());
            preparedStatement.setString(3, dis_txt.getText());




            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ADDoffreController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    private void getQuery() {



        if (update == false) {

            query = "INSERT INTO `offre`( `idOffre`, `pourcentage`, `idProduit`) VALUES (?,?,?)";

        }else{
            query = "UPDATE `offre` SET "
                    + "`pourcentage`=?,"
                    +"`idProduit`= ? WHERE idOffre = '"+offreId+"'";
        }

    }


    void setUpdate(boolean b) {
        this.update = b;

    }
    void setTextField(int id, int title, int disable) {

        offreId = id;
        title_txt.setText(String.valueOf(id));//idoffre
        description_txt.setText(String.valueOf(disable));//idproduit

        dis_txt.setText(String.valueOf(title));//pourcentage

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
