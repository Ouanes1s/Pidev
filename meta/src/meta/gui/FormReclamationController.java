package meta.gui;

import entities.Reclamation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import services.ReclamationService;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

public class FormReclamationController {

    @FXML
    private Button BackToMain;

    @FXML
    private TextField descriptionReclamation;

    @FXML
    private Button GetData;

    @FXML
    private Circle ImgUser;

    @FXML
    private TextField emailUser;

    @FXML
    private TextField nomUser;

    @FXML
    private TextField prenomUser;
    
     @FXML
    private ComboBox<String> categorieCombo;

    @FXML
    private ComboBox<String> typeCombo;

    @FXML
    private ComboBox<Integer> importanceCombo;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    FXMLLoader fxmlLoader;

    
    Reclamation reclamation = new Reclamation();
    ReclamationService rs = new ReclamationService();
    public void initialize() throws SQLException {
        ObservableList<String> listCategories = FXCollections.observableArrayList("Categorie 1", "Categorie 2", "Categorie 3");
        ObservableList<String> listTypes = FXCollections.observableArrayList("Type 1", "Type 2", "Type 3");
        ObservableList<Integer> listImportance = FXCollections.observableArrayList(12,3,4,5);
        categorieCombo.setItems(listCategories);
        typeCombo.setItems(listTypes);
        importanceCombo.setItems(listImportance);
    }
    
    @FXML
    void BackToMain(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(GestionGererReclamationController.class.getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Metax Space");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void SubmitRec(ActionEvent event) throws SQLException, IOException {
       reclamation.setCategorieReclamation(categorieCombo.getSelectionModel().getSelectedItem());
       reclamation.setImportanceReclamation(importanceCombo.getSelectionModel().getSelectedItem());
       reclamation.setTypeReclamation(typeCombo.getSelectionModel().getSelectedItem());
       reclamation.setMessageReclamation(descriptionReclamation.getText());
       rs.ajouter(reclamation);
        fxmlLoader = new FXMLLoader(GestionGererReclamationController.class.getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Metax Space");
        stage.setScene(scene);
        stage.show();
    }

}
