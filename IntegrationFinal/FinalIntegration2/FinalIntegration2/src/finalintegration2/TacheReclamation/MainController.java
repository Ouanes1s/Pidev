package finalintegration2.TacheReclamation;

//import entities.User;
import finalintegration2.Models.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
//import services.UserService;

public class MainController implements Initializable{
    @FXML
    private VBox userCardLayout;

    @FXML
    private Label LbLogUser;

    @FXML
    private Button logout;

    @FXML
    private Circle idimg;

    @FXML
    private Button adminCard3;

    @FXML
    private Circle adminCard33;

    @FXML
    private ImageView adminCard333;

    @FXML
    private Label adminCard3333;
    
    @FXML
    private Circle adminCard331;

    @FXML
    private Label adminCard33331;

    @FXML
    private ImageView adminCard3331;

    @FXML
    private Button Reclamation;
    
    @FXML
    private Button gestionParking;
    
    @FXML
    private Button nosParkings;
    
    @FXML
    private Circle nosParkingCircle;

    @FXML
    private Label nosParkingTitle;

    @FXML
    private ImageView nomParkingImg;

    private Stage stage;
    private Scene scene;
    private Parent root;
    FXMLLoader fxmlLoader;
    
//     UserService us = new UserService();
 //    User user = new User();
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        String nom = Session.getNom();
        String cin = Session.getCin();
        String role = Session.getRole();
        String type = Session.getTypeA();
        //user = us.getUser(1);
        if (type.equals("Reclamation")) {
            Reclamation.setVisible(false);
            nosParkings.setVisible(false);
            nomParkingImg.setVisible(false);
            nosParkingCircle.setVisible(false);
            nosParkingTitle.setVisible(false);
        }
        else if(role.equals("Membre")){
            adminCard3.setVisible(false);
            adminCard33.setVisible(false);
            adminCard333.setVisible(false);
            adminCard3333.setVisible(false);
            gestionParking.setVisible(false);
            adminCard331.setVisible(false);
            adminCard3331.setVisible(false);
            adminCard33331.setVisible(false);
        }
    }

    @FXML
    void Reclamation(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("GestionGererReclamation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Reclamation Space");
        stage.setScene(scene);
        stage.show();
        /*root = FXMLLoader.load(getClass().getResource("GestionGererReclamation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }
    

    @FXML
    void createReclamation(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("FormReclamation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(),570, 616);
        stage.setTitle("Welcome To Create Reclamation Space");
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    void switchToParkings(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("GestionParkings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Parkings Space");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void switchToNosParkings(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("GestionParkings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Parkings Space");
        stage.setScene(scene);
        stage.show();
    }

   
    
    

   
}
