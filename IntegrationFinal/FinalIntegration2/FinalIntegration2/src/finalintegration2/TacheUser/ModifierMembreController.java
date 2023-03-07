/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.TacheUser;

import finalintegration2.Models.Membre;
import finalintegration2.Models.User;
import finalintegration2.Services.UserCRUD;
import finalintegration2.Utils.ConnectionToDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chebi
 */
public class ModifierMembreController implements Initializable {

     @FXML
    private TableView<Membre> table;
    @FXML
    private TextField gnom;
    @FXML
    private TextField gprenom;
    @FXML
    private TextField gcin;
    @FXML
    private TextField gemail;
    @FXML
    private TextField gmdp;
    @FXML
    private TextField gsalaire;
    @FXML
    private TableColumn<User, String> NomColumn;
    @FXML
    private TableColumn<User, String> PrenomColumn;
    @FXML
    private TableColumn<User, String> CinColumn;
    @FXML
    private TableColumn<User, String> EmailColumn;
    @FXML
    private TableColumn<User, String> MDPColumn;
    @FXML
    private TableColumn<User, String> SalaireColumn;
   
    
  
    @FXML
    private Button btnModifier;
    
    
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    table_affiche();
        table.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
        if (newValue != null) {
      
        this.gnom.setText(newValue.getNom_user());
        this.gprenom.setText(newValue.getPrenom_user());
        this.gcin.setText(newValue.getCin_user());
        this.gemail.setText(newValue.getEmail_user());
       
        this.gmdp.setText(newValue.getMdp_user());
        this.gsalaire.setText(newValue.getDate_inscri());
         
        }   
        });
    }    
         
    public void table_affiche(){
        Connection cnx = ConnectionToDB.getInstance().getConnection();
        ObservableList<Membre> users = FXCollections.observableArrayList();
        PreparedStatement stmt = null;
	ResultSet rst = null;
    
        try {
           String req = "SELECT * FROM User WHERE role_user= ?";
           stmt = cnx.prepareStatement(req);
	    stmt.setString(1, "Membre");
	    rst = stmt.executeQuery();
            
            {
                while(rst.next()){
               Membre u = new Membre();
                
                u.setNom_user(rst.getString("nom_user"));
                u.setPrenom_user(rst.getString("prenom_user"));
                u.setCin_user(rst.getString("cin_user"));
                u.setEmail_user(rst.getString("email_user"));
               
                u.setMdp_user(rst.getString("mdp_user"));
                u.setDate_inscri(rst.getString("Date_inscri"));
                
                
                users.add(u);}
            }   
            
            NomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("nom_user"));
            PrenomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("prenom_user"));
            CinColumn.setCellValueFactory(new PropertyValueFactory<User,String>("cin_user"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email_user"));
            MDPColumn.setCellValueFactory(new PropertyValueFactory<User,String>("mdp_user"));
            SalaireColumn.setCellValueFactory(new PropertyValueFactory<User,String>("Date_inscri"));
           
            table.setItems(users);
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
    @FXML
    private void getSelected(MouseEvent event) {
        User clicked = table.getSelectionModel().getSelectedItem();
        gcin.setText(String.valueOf(clicked.getCin_user()));
    }



 @FXML
private void ModifierAgent(ActionEvent event) {
int ID=0;
        String nom,prenom,email,salaire;
        String cin,mdp;
        
     nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        
        cin = gcin.getText();
        mdp = gmdp.getText();
        salaire = gsalaire.getText();
     
        //Contôle de saisie
         //Contôle de saisie
        if (nom==null || prenom ==null || email==null  || cin==null || mdp==null|| gsalaire==null)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        }else if (email.matches("(.*)@(.*)")==false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid email address");
            alert.show();
        }else if (cin.length()!=8 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();

        }else if (cin.matches("[0-9]*")==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();
        }else{
            //Methode Ajouter    
            User user1 = new Membre (nom,prenom,cin,email,mdp,salaire);
             UserCRUD uc = new UserCRUD();
           
           
            
            
            
           
           
            uc.modifierUserMembre(user1);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully updated an account. ");
            alert.show();
            table_affiche();
            
        }
    }

    @FXML
    private void back_tologin(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("MembreSettings.fxml"));
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
