/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.user.gui;

import edu.user.utils.ConnectionToDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chebi
 */
public class AuthentificationController implements Initializable {

    @FXML
    private TextField authemail;
    @FXML
    private Button connecter;
    @FXML
    private PasswordField authmdp;
    @FXML
    private ImageView back;
   Stage stage;
    /**
     * Initializes the controller class.
     */
   private static int idConnecte;
    private static String specialiteConnecte;
    public static int getIdConnecter(){
        return idConnecte;
    }
    public static String getSpecialiteConnecter(){
        return specialiteConnecte;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    Connection cnx = ConnectionToDB.getInstance().getConnection();
 public String role_selection(String email){
       String role="";
        Connection cnx = ConnectionToDB.getInstance().getConnection();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    
                    try {
                        /*User u=new User(rs.getString("role_user"));*/
                        pst = cnx.prepareStatement("SELECT type_A FROM user WHERE email_user=? ");
                        /*User u = new User (rs.getInt("id_user"), rs.getString("nom_user"),rs.getString("prenom_user"),
                        rs.getString("cin_user"),rs.getString("email_user"),rs.getString("role_user"),rs.getString("mdp_user"));*/
                        pst.setString(1, email);
                        
                        role=rs.getString("type_A"); 
                      
                        
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
         return role; 
    }
 public boolean verifierEmailBd(String email) {
	PreparedStatement stmt = null;
	ResultSet rst = null;
	try {
	    String sql = "SELECT * FROM User WHERE email_user=?";
	    stmt = cnx.prepareStatement(sql);
	    stmt.setString(1, email);
	    rst = stmt.executeQuery();
	    if (rst.next()) {
		return true;
	    }
	} catch (SQLException ex) {
	    System.out.println(ex.getMessage());
	}
	return false;

    }
 
 public String mdptaker(String email) {
	PreparedStatement stmt = null;
	ResultSet rst = null;
	try {
	    String sql = "SELECT mdp_user FROM User WHERE email_user=?";
	    stmt = cnx.prepareStatement(sql);
	    stmt.setString(1, email);
	    rst = stmt.executeQuery();
	    while (rst.next()) {
		return rst.getString("mdp_user");
	    }
	} catch (SQLException ex) {
	    System.out.println(ex.getMessage());
	}
        return null;
	}
    @FXML
    private void authentification(ActionEvent event) throws IOException, SQLException {
      int id=0;
       Connection cnx = ConnectionToDB.getInstance().getConnection();
       PreparedStatement ps = null;
       ResultSet rs = null;
                  /* System.out.println(authemail.getText()+authmdp.getText());*/
                        ps = cnx.prepareStatement("SELECT * FROM user WHERE email_user='"+authemail.getText()+"' AND mdp_user ='"+authmdp.getText()+"'");
                        
                        rs = ps.executeQuery();
           
                        if (verifierEmailBd(authemail.getText())==true && mdptaker(authemail.getText()).equals(authmdp.getText()) ){
                        while (rs.next()) {
                           //id = rs.getInt("id_user");
                            specialiteConnecte = rs.getString("type_A");
                            idConnecte=id;
                            if (rs.getString("role_user").equals("Administrateur")){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeAdmin.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Gestion User");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("role_user").equals("Membre")){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgotMDP.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome Membre");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("type_A").equals("Stock")){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionMembre.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome Agent de Stock");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("type_A").equals("Gestion de Reservation")){
                               
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeReservations.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome Agent de gestion de reservation");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("type_A").equals("Reclamation")){
                               
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionUser.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome agent reclamation");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("type_A").equals("Films et events")){
                               
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeFilms.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome agent de gestion de film et evenements");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("type_A").equals("Cinemas et salles")){
                               
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeCinemas.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome agent de gestion de Cinemas et salles");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("type_A").equals("Gestion de Parkings")){
                               
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeParkings.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome agent de gestion de Parkings");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Welcome");
                            alert.setHeaderText("WELCOME TO Mycinema");
                            alert.setContentText("You're connected");
                            alert.show();
                            
                            
                        
                        }}
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Failed");
                            alert.setHeaderText("Attention !!");
                            alert.setContentText("Can not connect to Mycinema. Vos données sont erronés.");
                            alert.show();
                        }
 
     } 

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthentificationController other = (AuthentificationController) obj;
        if (!Objects.equals(this.authemail, other.authemail)) {
            return false;
        }
        if (!Objects.equals(this.authmdp, other.authmdp)) {
            return false;
        }
        return true;
    }
    

    @FXML
     public void mdp_oublie(MouseEvent event) {
    try {
                     Parent root = FXMLLoader.load(getClass().getResource("ForgotMdp.fxml"));
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

    @FXML
    private void back_tologin(MouseEvent event) {
        
     try {
                     Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
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
    

