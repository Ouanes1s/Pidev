/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;


import edu.reservation.entities.Reservation;
import edu.reservation.services.ServiceReservation;
import edu.reservation.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList ;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Amine Khalfaoui
 */
public class AfficherReservationController implements Initializable {

    @FXML
    private ListView<Reservation> listView;
    @FXML
    private TextField searchField;
    @FXML
    private ImageView backkey;
    @FXML
    private Button modres;
    @FXML
    private Label countLabel;

    /**
     * Initializes the controller class.
     */
    
    public void list_affiche(){
    Connection cnx = DataSource.getInstance().getCnx();
    ObservableList<Reservation> res = FXCollections.observableArrayList();
    PreparedStatement stmt = null;
    ResultSet rst = null;

    try {
        String req = "SELECT * FROM Reservation ";
        stmt = cnx.prepareStatement(req);
        
        rst = stmt.executeQuery();

        while(rst.next()){
            Reservation reservation = new Reservation(
                    rst.getString("nom_res"),
                    rst.getString("prenom_res"),
                    rst.getString("email_res"),
                    rst.getString("typeticket_res"),
                    rst.getString("nom_evnmt"),
                    rst.getString("code_offr"),
                    rst.getString("date_res")
                   
                   
            ) ;
            res.add(reservation);
        }
           countLabel.setText("Nombre de réservations est de : " + res.size());
        listView.setItems(res);
        
        
        FilteredList<Reservation> filteredRes = new FilteredList<>(res, p -> true);
        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredRes.setPredicate(reservation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (reservation.getNom_res().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (reservation.getPrenom_res().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                
                } else if (reservation.getEmail_res().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (reservation.getTypeticket_res().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (reservation.getNom_evnmt().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (reservation.getCode_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (reservation.getDate_res().toLowerCase().contains(lowerCaseFilter)) {
                    return true;    
                }
                return false;
            }); 
            SortedList<Reservation> sortedRes;
            sortedRes = new SortedList<>(filteredRes, Comparator.comparing(Reservation::getEmail_res));
        listView.setItems(sortedRes);
       
        });

      
    }
    catch (SQLException ex){
        System.err.println(ex.getMessage());
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         list_affiche();
              listView.setCellFactory(param -> new ListCell<Reservation>() {
    @Override
    protected void updateItem(Reservation reservation, boolean empty) {
        super.updateItem(reservation, empty);
        if (empty || reservation == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(reservation.getNom_res() + "|| " + reservation.getPrenom_res()+" || "+reservation.getEmail_res()+" || "+reservation.getTypeticket_res()+" || "+reservation.getNom_evnmt()+" || "+reservation.getCode_offr()+" || "+reservation.getDate_res()+" ");
             
                if (reservation.getTypeticket_res().equals("Balcony")) {
          ImageView imageView = new ImageView(new Image(getClass().getResource("star-7207.png").toExternalForm()));
           imageView.setFitHeight(50);
                        imageView.setFitWidth(50);
                        VBox vbox = new VBox(imageView, new Label(reservation.getTypeticket_res() + " " + reservation.getPrenom_res()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);
                        
                        setGraphic(vbox);}
        if (reservation.getTypeticket_res().equals("Middle Row")) {
          ImageView imageView = new ImageView(new Image(getClass().getResource("star7204.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(reservation.getTypeticket_res() + " " + reservation.getPrenom_res()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);}
        if (reservation.getTypeticket_res().equals("Backseats")) {
          ImageView imageView = new ImageView(new Image(getClass().getResource("star-7206.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(reservation.getTypeticket_res() + " " + reservation.getPrenom_res()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);}
        if (reservation.getTypeticket_res().equals("First row")) {
          ImageView imageView = new ImageView(new Image(getClass().getResource("5-stars-7236.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(reservation.getTypeticket_res() + " " + reservation.getPrenom_res()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);}
      
            // ajout de l'image à la cellule
//                    setGraphic(imageView); */
//          ImageView imageView = new ImageView(new Image(getClass().getResource("KitsunePrev.png").toExternalForm()));

                        // personnalisation de l'image
//                        imageView.setFitHeight(50);
//                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
//                        VBox vbox = new VBox(imageView, new Label(agent.getType_A() + " " + agent.getPrenom_user()));
//                        vbox.setAlignment(Pos.CENTER);
//                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
//                        setGraphic(vbox);
        }
    }
    
}
              );
              
              
              listView.setOnMouseClicked(event -> {
    if (event.getClickCount() == 3) {
        Reservation reservation = listView.getSelectionModel().getSelectedItem();
        if (reservation != null) {
            supprimer(reservation);
            listView.getItems().remove(reservation);
        }
    }
});
    
                }
    

     public void supprimer(Reservation reservation) {
    Connection cnx = DataSource.getInstance().getCnx();
    PreparedStatement stmt = null;
    
    try {
        String req = "DELETE FROM Reservation WHERE id_res = ?";
        stmt = cnx.prepareStatement(req);
        stmt.setInt(1, reservation.getId_res());
        stmt.executeUpdate();
    }
    catch (SQLException ex){
        System.err.println(ex.getMessage());
    }
    finally {
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException ex){
                System.err.println(ex.getMessage());
            }
        }
    }
}

    @FXML
    private void backtogesres(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("DashboardResAgent.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
    }

    @FXML
    private void tomodres(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("ModifierReservation.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
    }

    }    
    

