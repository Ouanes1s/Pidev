package edu.stock.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.stock.entites.Offre;
import edu.stock.utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableViewsFile2 implements Initializable {


    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Offre formation = null ;
/********************//***********************/
    //  int index = -1;

/********************//***********************/

    ObservableList<Offre> offreList = FXCollections.observableArrayList();
    @FXML
    private Button addFileB;

    @FXML
    private TableView<Offre> TableOffre;

    @FXML
    private TableColumn<Offre, String> idCol;

    @FXML
    private TableColumn<Offre, String> titleCol;

    @FXML
    private TableColumn<Offre, String> DisableCol;

    @FXML
    private TableColumn<Offre, String> editCol;
    @FXML
    private TextField filterfield;

    @FXML
    private Button buttonHD;
    @FXML
    private Button back_button;


    @FXML
    void Print(MouseEvent event) {

    }

    @FXML
    void addFile(ActionEvent event) throws Exception {
        /*Parent root  = FXMLLoader.load(getClass().getResource("../gui/productUI.fxml"));

        Stage window =(Stage) addFileB.getScene().getWindow();
        window.setScene(new Scene(root, 880, 541));*/

    }

    public void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }



    @FXML
    public void getAddView(MouseEvent mouseEvent)throws Exception {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("ADDoffre.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void helpdeslk(ActionEvent event) throws Exception {


       /* Parent root  = FXMLLoader.load(getClass().getResource("../gui/ImageProductTable.fxml"));

        Stage window =(Stage) addFileB.getScene().getWindow();
        window.setScene(new Scene(root, 880, 541));*/
    }
    @FXML
    void back_action(ActionEvent event) throws Exception {


        Parent root  = FXMLLoader.load(getClass().getResource("../gui/ProductsView.fxml"));

        Stage window =(Stage) back_button.getScene().getWindow();
        window.setScene(new Scene(root, 1140, 590));
    }

    @FXML
    void print(MouseEvent event) {

    }

    @FXML
    void refresh() {
        try {
            offreList.clear();




            query = "SELECT * FROM `offre`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                offreList.add(new Offre(
                        resultSet.getInt(1),//idOffre
                        resultSet.getInt(2),//pourcentage
                        resultSet.getInt(3)//idProduit
                ));
                TableOffre.setItems(offreList);
            }


        } catch (SQLException ex) {
            Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private void loadDate() {

        connection = DatabaseConnection.getInstance().getConnection();
        refresh();

        //name of attributes from entity
        idCol.setCellValueFactory(new PropertyValueFactory<>("idOffre"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("poucentage"));
       // DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        DisableCol.setCellValueFactory(new PropertyValueFactory<>("idProd"));




        /*this*/ Callback<TableColumn<Offre, String>, TableCell<Offre, String>> cellFoctory = (TableColumn<Offre, String> param) -> {
            // make cell containing buttons
            final TableCell<Offre, String> cell = new TableCell<Offre, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                formation = TableOffre.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `offre` WHERE idOffre  ="+formation.getIdOffre();
                                connection = DatabaseConnection.getInstance().getConnection();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refresh();

                            } catch (SQLException ex) {
                                Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
                            }





                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            formation = TableOffre.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("ADDoffre.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableViewsFile2.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            ADDoffreController addProductController = loader.getController();
                            addProductController.setUpdate(true); //addcon
                            addProductController.setTextField(formation.getIdOffre(),formation.getPoucentage(),
                                    //formation.getDescription(),
                                    formation.getIdProd());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();




                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editCol.setCellFactory(cellFoctory);
        TableOffre.setItems(offreList);

    }



    @FXML
    void search(KeyEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadDate();
    }
}
