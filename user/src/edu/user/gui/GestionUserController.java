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

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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


//import javafx.scene.image.Image ;//
/**
 * FXML Controller class
 *
 * @author chebi
 */
public class GestionUserController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private ComboBox<String> boxrole2;
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
    private TableColumn<User, String> DateDeConColumn;
    
    @FXML
    private TableColumn<User, String> TypeColumn;
    @FXML
    private TableColumn<User, String> RoleColumn;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TextField gcontract;
    
    @FXML
    private ListView<Agent> listView ;
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

        listView.setItems(agents);
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
      ObservableList<String> listrole = FXCollections.observableArrayList("","Stock","Reclamation","Gestion de Reservation","Films et events","Cinemas et salles","Gestion de Parkings");
        boxrole2.setValue("");
        boxrole2.setItems(listrole);
        //Table
        table_affiche();
        table.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
        if (newValue != null) {
      
        this.gnom.setText(newValue.getNom_user());
        this.gprenom.setText(newValue.getPrenom_user());
        this.gcin.setText(newValue.getCin_user());
        this.gemail.setText(newValue.getEmail_user());
        this.boxrole2.setItems(listrole);
        this.gmdp.setText(newValue.getMdp_user());
        this.gsalaire.setText(newValue.getNom_user());
         this.gcontract.setText(newValue.getNom_user());
        }   
        });
        //List
        list_affiche();
              listView.setCellFactory(param -> new ListCell<Agent>() {
    @Override
    protected void updateItem(Agent agent, boolean empty) {
        super.updateItem(agent, empty);
        if (empty || agent == null) {
            setText(null);
        } else {
            setText(agent.getNom_user() + " " + agent.getPrenom_user()+" "+agent.getCin_user()+" "+agent.getSalaire());
        /* // création d'une image pour chaque cellule../edu.user.gui/KitsunePrev.png
                   ImageView imageView = new ImageView(new Image(getClass().getResource("KitsunePrev.png").toExternalForm()));



                    // personnalisation de l'image
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);

                    // ajout de l'image à la cellule
                    setGraphic(imageView); */
          ImageView imageView = new ImageView(new Image(getClass().getResource("KitsunePrev.png").toExternalForm()));

                        // personnalisation de l'image
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
                        VBox vbox = new VBox(imageView, new Label(agent.getType_A() + " " + agent.getPrenom_user()));
                        vbox.setAlignment(Pos.CENTER);
                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
                        setGraphic(vbox);
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
 
           
    public void table_affiche(){
        Connection cnx = ConnectionToDB.getInstance().getConnection();
        ObservableList<User> users = FXCollections.observableArrayList();
        PreparedStatement stmt = null;
	ResultSet rst = null;
    
        try {
           String req = "SELECT * FROM User WHERE role_user= ?";
           stmt = cnx.prepareStatement(req);
	    stmt.setString(1, "Agent");
	    rst = stmt.executeQuery();
            
            {
                while(rst.next()){
                Agent u = new Agent();
                
                u.setNom_user(rst.getString("nom_user"));
                u.setPrenom_user(rst.getString("prenom_user"));
                u.setCin_user(rst.getString("cin_user"));
                u.setEmail_user(rst.getString("email_user"));
                u.setDate_contract(rst.getString("date_contract"));
                u.setMdp_user(rst.getString("mdp_user"));
                u.setSalaire(rst.getString("Salaire"));
                u.setType_A(rst.getString("Type_A"));
                
                users.add(u);}
            }   
            
            NomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("nom_user"));
            PrenomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("prenom_user"));
            CinColumn.setCellValueFactory(new PropertyValueFactory<User,String>("cin_user"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email_user"));
            MDPColumn.setCellValueFactory(new PropertyValueFactory<User,String>("mdp_user"));
            SalaireColumn.setCellValueFactory(new PropertyValueFactory<User,String>("Salaire"));
            DateDeConColumn.setCellValueFactory(new PropertyValueFactory<User,String>("date_contract"));
            RoleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("role_user"));
           TypeColumn.setCellValueFactory(new PropertyValueFactory<User,String>("type_A"));
            table.setItems(users);
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
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
    

    @FXML
    private void getSelected(MouseEvent event) {
        User clicked = listView.getSelectionModel().getSelectedItem();
        gcin.setText(String.valueOf(clicked.getCin_user()));
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
    private void AjouterAgent(ActionEvent event) {
        String nom,prenom,email,role,mdp;
        String cin;
        String salaire , datecontrac ;
        
        nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        role = (String) boxrole2.getSelectionModel().getSelectedItem();
        cin = gcin.getText();
        mdp = gmdp.getText();
        salaire = gsalaire.getText();
        datecontrac = gcontract.getText();
        
        //Contôle de saisie
        if (nom==null || prenom ==null || email==null || role==null || cin==null || mdp==null|| gsalaire==null|| datecontrac==null)      
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
            User user1 = new Agent (nom,prenom,cin,email,mdp,salaire,role,datecontrac);
             UserCRUD uc = new UserCRUD();
           if (uc.VerifCin(user1.getCin_user())!=0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("USER ALREADY EXISTS");
            alert.show(); 
            }
            
            
            else {
           
            uc.ajouterUserAgent(user1);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully created your account. Please check your e-mail box to get your ID ");
            alert.show();
            table_affiche();
            }}
    }


 @FXML
private void ModifierAgent(ActionEvent event) {
/*int ID=0;
        String nom,prenom,email,role,id;
        String cin,mdp;
        
   
        nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        role = (String) boxrole2.getSelectionModel().getSelectedItem();
        cin = gcin.getText();
        mdp = gmdp.getText();
        ID =Integer.parseInt(id);
        //Contôle de saisie
        if (nom==null || prenom ==null || email==null || role==null || cin==null || mdp==null)      
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
        
            User user1 = new User (ID,nom,prenom,cin,email,role,mdp);
            if (rechercheUser(user1)==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setHeaderText("Attention !!");
                alert.setContentText("USER DOES NOT EXISTS");
                alert.show(); 
            }
            
            else {
                UserCRUD uc = new UserCRUD();
                uc.modifierUserAgent(user1);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Update Done !!");
                alert.setContentText("You have successfully updated an account");
                alert.show();
                table_affiche();
            }
        }*/
    }

    @FXML
    private void SupprimerAgent(ActionEvent event) {
        /*int ID=0;
        String nom,prenom,email,role;
        String cin,mdp;
        
        
        
        nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        role = (String) boxrole2.getSelectionModel().getSelectedItem();
        cin = gcin.getText();
        mdp = gmdp.getText();
        
        
        User user2 = new User (nom,prenom,cin,email,role,mdp);
            if (rechercheUser(user2)==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setHeaderText("Attention !!");
                alert.setContentText("USER DOES NOT EXISTS");
                alert.show(); 
            }
            else {
                UserCRUD uc = new UserCRUD();
                uc.supprimerUser(user2);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Removal Done !!");
                alert.setContentText("You have successfully deleted an account ");
                alert.show();
                table_affiche();
            }*/
    }
    }   