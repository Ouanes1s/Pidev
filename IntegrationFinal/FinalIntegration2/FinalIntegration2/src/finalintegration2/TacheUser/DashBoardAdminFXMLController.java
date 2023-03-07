/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.TacheUser;

import finalintegration2.Models.Agent;
import finalintegration2.Utils.ConnectionToDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.application.*;
/**
 * FXML Controller class
 *
 * @author chebi
 */
public class DashBoardAdminFXMLController implements Initializable {

    @FXML
    private TilePane tilePane;
    @FXML
    private ListView<Agent> listView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            list_affiche();
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardAdminFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }



  /* public void list_affiche(){
    Connection cnx = ConnectionToDB.getInstance().getConnection();
    ObservableList<Agent> agents = FXCollections.observableArrayList();
    PreparedStatement stmt = null;
    ResultSet rst = null;

    try {
        String req = "SELECT * FROM User WHERE role_user= ?";
        stmt = cnx.prepareStatement(req);
        stmt.setString(1, "Agent");
        rst = stmt.executeQuery();

        while(rst.next()){
            Agent agent = new Agent(
                    rst.getString("nom_user"),
                    rst.getString("prenom_user"),
                    rst.getString("cin_user"),
                    rst.getString("email_user"),
                    rst.getString("date_contract"),
                    rst.getString("mdp_user"),
                    rst.getString("Salaire"),
                    rst.getString("Type_A")
            );
            agents.add(agent);
        }

        listView.setItems(agents);

        // personnalisation de l'affichage des cellules
        listView.setCellFactory((ListView<Agent> param) -> new ListCell<Agent>() {
            @Override
            protected void updateItem(Agent agent, boolean empty) {
                super.updateItem(agent, empty);
                if (empty || agent == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(agent.getNom_user() + " " + agent.getPrenom_user());

                    // création d'une image pour chaque cellule
                  ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("../images/not_sending_video_frames_16px.png")));

                    // personnalisation de l'image
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);

                    // ajout de l'image à la cellule
                    setGraphic(imageView);
                }
            }
        });

        // personnalisation de l'affichage en mosaïque
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(10, 10, 10, 10));
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        tilePane.setPrefColumns(3);

        tilePane.getChildren().addAll(listView);
        Scene scene = new Scene(tilePane, 800, 600);

        Stage stage = (Stage) listView.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    catch (SQLException ex){
        System.err.println(ex.getMessage());
    }
}*/


  /* public void list_affiche() throws SQLException{
    Connection cnx = ConnectionToDB.getInstance().getConnection();
    ObservableList<Agent> agents = FXCollections.observableArrayList();
    PreparedStatement stmt = null;
    ResultSet rst = null;

 
        String req = "SELECT * FROM User WHERE role_user= ?";
        stmt = cnx.prepareStatement(req);
        stmt.setString(1, "Agent");
        rst = stmt.executeQuery();

        while(rst.next()){
            Agent agent = new Agent(
                    rst.getString("nom_user"),
                    rst.getString("prenom_user"),
                    rst.getString("cin_user"),
                    rst.getString("email_user"),
                    rst.getString("date_contract"),
                    rst.getString("mdp_user"),
                    rst.getString("Salaire"),
                    rst.getString("Type_A")
            );
            agents.add(agent);
        }

        // création de la disposition TilePane
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(10));
        tilePane.setHgap(10);
        tilePane.setVgap(10);

        // personnalisation de l'affichage des cellules
        for (Agent agent : agents) {
            // création d'une image pour chaque agent
             ImageView imageView = new ImageView(new Image(getClass().getResource("KitsunePrev.png").toExternalForm()));

            // personnalisation de l'image
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);

            // création d'un conteneur pour l'image et le nom de l'agent
            VBox vBox = new VBox(imageView, new Label(agent.getNom_user() + " " + agent.getPrenom_user()));
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(5);

            // ajout du conteneur à la disposition TilePane
            tilePane.getChildren().add(vBox);
        }


       Stage stage = new Stage();
   
    // add controls to the stage...
        Scene scene = new Scene(tilePane);
       

    // add controls to the stage...
        stage.setScene(scene);
        stage.show();}
*/
    
   public void list_affiche() throws SQLException{
    Connection cnx = ConnectionToDB.getInstance().getConnection();
    ObservableList<Agent> agents = FXCollections.observableArrayList();
    PreparedStatement stmt = null;
    ResultSet rst = null;

    String req = "SELECT * FROM User WHERE role_user= ?";
    stmt = cnx.prepareStatement(req);
    stmt.setString(1, "Agent");
    rst = stmt.executeQuery();

    while(rst.next()){
        Agent agent = new Agent(
            rst.getString("nom_user"),
            rst.getString("prenom_user"),
            rst.getString("cin_user"),
            rst.getString("email_user"),
            rst.getString("date_contract"),
            rst.getString("mdp_user"),
            rst.getString("Salaire"),
            rst.getString("Type_A")
        );
        agents.add(agent);
    }

    // personnalisation de l'affichage des cellules
    listView.setCellFactory(new Callback<ListView<Agent>, ListCell<Agent>>() {
        @Override
        public ListCell<Agent> call(ListView<Agent> param) {
            return new ListCell<Agent>() {
                @Override
                protected void updateItem(Agent agent, boolean empty) {
                    super.updateItem(agent, empty);
                    if (empty || agent == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(agent.getNom_user() + " " + agent.getPrenom_user());

                        // création d'une image pour chaque cellule
                      ImageView imageView = new ImageView(new Image(getClass().getResource("KitsunePrev.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(agent.getNom_user() + " " + agent.getPrenom_user()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);
                    }
                }
            };
        }
    });

    // ajout des agents à la liste
    listView.setItems(agents);
}
 
    }
    


   
    


     
 
