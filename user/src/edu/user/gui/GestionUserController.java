/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.user.gui;

import edu.user.entities.User;
import edu.user.entities.Agent;
import edu.user.services.UserCRUD;
import edu.user.utils.ConnectionToDB;
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

//import javafx.scene.image.Image ;//
/**
 * FXML Controller class
 *
 * @author chebi
 */
public class GestionUserController implements Initializable {

   
    
    @FXML
    private ListView<Agent> listView ;
    @FXML
    private TextField searchField;
    @FXML
    private ImageView back;
    @FXML
    private Label countLabel;
    /**
     * Initializes the controller class.
     */
    public void list_affiche(){
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
                    rst.getString("mdp_user"),
                    
                   
                    rst.getString("Salaire"),
                    rst.getString("Type_A"),
                    rst.getString("date_contract")
            ) ;
            agents.add(agent);
        }
 countLabel.setText("Nombre d'agents actifs est de : " + agents.size());
        listView.setItems(agents);
          // Créer un champ de recherche
      // Ajout de la recherche intelligente
        FilteredList<Agent> filteredAgents = new FilteredList<>(agents, p -> true);
        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredAgents.setPredicate(agent -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (agent.getNom_user().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (agent.getPrenom_user().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (agent.getCin_user().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (agent.getEmail_user().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            }); 
            SortedList<Agent> sortedAgents;
            sortedAgents = new SortedList<>(filteredAgents, Comparator.comparing(Agent::getNom_user));
        listView.setItems(sortedAgents);
       
        });

      
    }
    catch (SQLException ex){
        System.err.println(ex.getMessage());
    }
}
  

   


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     // TODO
        //combobox
      
        //List
        list_affiche();
              listView.setCellFactory(param -> new ListCell<Agent>() {
    @Override
    protected void updateItem(Agent agent, boolean empty) {
        super.updateItem(agent, empty);
        if (empty || agent == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(agent.getNom_user() + " " + agent.getPrenom_user()+" "+agent.getCin_user()+" "+agent.getSalaire()+" "+agent.getEmail_user());
        /* // création d'une image pour chaque cellule../edu.user.gui/KitsunePrev.png
                   ImageView imageView = new ImageView(new Image(getClass().getResource("KitsunePrev.png").toExternalForm()));



                    // personnalisation de l'image
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);

                    // ajout de l'image à la cellule
                    setGraphic(imageView); */
        if (agent.getType_A().equals("Stock")) {
          ImageView imageView = new ImageView(new Image(getClass().getResource("truck-2007.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(agent.getType_A() + " " + agent.getPrenom_user()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);}
        if (agent.getType_A().equals("Gestion de Reservation")) {
          ImageView imageView = new ImageView(new Image(getClass().getResource("hand-click-and-reservation-sign-outline-16344.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(agent.getType_A() + " " + agent.getPrenom_user()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);}
        if (agent.getType_A().equals("Reclamation")) {
          ImageView imageView = new ImageView(new Image(getClass().getResource("list-6235.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(agent.getType_A() + " " + agent.getPrenom_user()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);}
        if (agent.getType_A().equals("Films et events")) {
          ImageView imageView = new ImageView(new Image(getClass().getResource("1080p-hd-quality-outline-sign-16927.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(agent.getType_A() + " " + agent.getPrenom_user()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);}
        if (agent.getType_A().equals("Cinemas et salles")) {
          ImageView imageView = new ImageView(new Image(getClass().getResource("video-camera-2805.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(agent.getType_A() + " " + agent.getPrenom_user()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);}
        if (agent.getType_A().equals("Gestion de Parkings")) {
          ImageView imageView = new ImageView(new Image(getClass().getResource("parking-1645.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(agent.getType_A() + " " + agent.getPrenom_user()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);}
        }
        
    }
    
}
              );
              /*   // personnalisation de l'affichage des cellules
        listView.setCellFactory((ListView<Agent> param) -> new ListCell<Agent>() {
            @Override
            protected void updateItem(Agent agent, boolean empty) {
                super.updateItem(agent, empty);
                if (empty || agent == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(agent.getNom_user() + " " + agent.getPrenom_user());

                    // création d'une image pour chaque cellule../edu.user.gui/KitsunePrev.png
                   ImageView imageView = new ImageView(new Image("../edu.user.gui/KitsunePrev.png"));


                    // personnalisation de l'image
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);

                    // ajout de l'image à la cellule
                    setGraphic(imageView);
                }
            }
        });*/
              listView.setOnMouseClicked(event -> {
    if (event.getClickCount() == 2) {
        Agent agent = listView.getSelectionModel().getSelectedItem();
        if (agent != null) {
            supprimerAgent(agent);
            listView.getItems().remove(agent);
        }
    }
});
    
                }
    

     public void supprimerAgent(Agent agent) {
    Connection cnx = ConnectionToDB.getInstance().getConnection();
    PreparedStatement stmt = null;
    
    try {
        String req = "DELETE FROM User WHERE cin_user = ?";
        stmt = cnx.prepareStatement(req);
        stmt.setString(1, agent.getCin_user());
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
 
           
    
    
    public Integer rechercheUser(User u){
                    Integer exist = 0;
                    Connection cnx = ConnectionToDB.getInstance().getConnection();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    try {
                        pst = cnx.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE nom_user=?,prenom_user=?,cin_user=?,email_user=?");
                        pst.setString(1, u.getNom_user());
                        pst.setString(2, u.getPrenom_user());
                        pst.setString(3, u.getCin_user());
                        pst.setString(4, u.getEmail_user());
                        rs = pst.executeQuery();
                        if (rs.next()) {
                             exist=rs.getInt("count"); 
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    return exist;
    }
    public Integer rechercheID(User u){
                    Integer exist = 0;
                    Connection cnx = ConnectionToDB.getInstance().getConnection();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    try {
                        pst = cnx.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE id_user=? ");
                        pst.setInt(1, u.getId_user());
                      
                        rs = pst.executeQuery();
                        if (rs.next()) {
                             exist=rs.getInt("count"); 
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    return exist;
    }
    

 
  /*  private void back_tologin(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
                        Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
        
    }*/

    @FXML
    private void backtoagent(MouseEvent event) {
     try {
                     Parent root = FXMLLoader.load(getClass().getResource("AgentSettings.fxml"));
         Stage Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
    }
    
}

    
    


   