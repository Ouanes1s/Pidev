/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;



import edu.reservation.entities.Offre;
import edu.reservation.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amine Khalfaoui
 */
public class AfficherOffreController implements Initializable {

    @FXML
    private ListView<Offre> listView;
    @FXML
    private TextField searchField;
    @FXML
    private ImageView backkey;
    @FXML
    private Button ajoutoffrkey;
    @FXML
    private Button modoffrkey;
    
    
     public void list_affiche(){
    Connection cnx = DataSource.getInstance().getCnx();
    ObservableList<Offre> offres = FXCollections.observableArrayList();
    PreparedStatement stmt = null;
    ResultSet rst = null;

    try {
        String req = "SELECT * FROM Offre ";
        stmt = cnx.prepareStatement(req);
        
        rst = stmt.executeQuery();

        while(rst.next()){
            Offre offre = new Offre(
                    rst.getString("nomfilm_offr"),
                    rst.getString("contenu_offr"),
                    rst.getString("datedebut_offr"),
                    rst.getString("datefin_offr"),
                    rst.getString("code_offr")
                   
                   
            ) ;
            offres.add(offre);
        }

        listView.setItems(offres);
        
        
        FilteredList<Offre> filteredOffres = new FilteredList<>(offres, p -> true);
        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredOffres.setPredicate(offre -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (offre.getNomfilm_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (offre.getContenu_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                
                } else if (offre.getDatedebut_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (offre.getDatefin_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (offre.getCode_offr().toLowerCase().contains(lowerCaseFilter)) {
                    return true;    
                }
                return false;
            }); 
            SortedList<Offre> sortedOffres;
            sortedOffres = new SortedList<>(filteredOffres, Comparator.comparing(Offre::getCode_offr));
        listView.setItems(sortedOffres);
       
        });

      
    }
    catch (SQLException ex){
        System.err.println(ex.getMessage());
    }
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list_affiche();
              listView.setCellFactory(param -> new ListCell<Offre>() {
    @Override
    protected void updateItem(Offre blog, boolean empty) {
        super.updateItem(blog, empty);
        if (empty || blog == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(blog.getNomfilm_offr() + "|| " + blog.getContenu_offr()+" || "+blog.getDatedebut_offr()+"|| "+blog.getDatefin_offr()+"|| "+blog.getCode_offr()+"|| ");
             

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
        Offre offre = listView.getSelectionModel().getSelectedItem();
        if (offre != null) {
            supprimer(offre);
            listView.getItems().remove(offre);
        }
    }
});
    
                }
    

     public void supprimer(Offre offre) {
    Connection cnx = DataSource.getInstance().getCnx();
    PreparedStatement stmt = null;
    
    try {
        String req = "DELETE FROM Offre WHERE code_offr = ?";
        stmt = cnx.prepareStatement(req);
        stmt.setString(1, offre.getCode_offr());
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
    private void toajoutoffr(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("AjouterOffre.fxml"));
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
    private void tomodoffr(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("ModifierOffre.fxml"));
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
    
    

