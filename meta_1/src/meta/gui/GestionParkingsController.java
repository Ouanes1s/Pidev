/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meta.gui;

import entities.Parking;
import entities.User;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ParkingService;
import services.ReservationParkingService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Marwa
 */
public class GestionParkingsController implements Initializable {
    
    @FXML
    private ImageView cnctedUser;

    @FXML
    private Label cnctedUserName;

    @FXML
    private AnchorPane parkingsAnchor;

    @FXML
     TextField nomParking;

    @FXML
     TextField capaciteParking;

    @FXML
     TextField takenParking;

    @FXML
     TextField prixParking;

    @FXML
    private Button imageParkingBtn;

    @FXML
     TextField tfImageParking;

    @FXML
    private Button btnAjouterparking;

    @FXML
    private Button reserverBtn;

    @FXML
    private ScrollPane parkingScrolPane;

    @FXML
    private GridPane parkingGridPane;

    @FXML
    private TextField searchtf;

    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    
    Parking parking = new Parking();
    ParkingService ps = new ParkingService();
    UserService us = new UserService();
    User user = new User();
    
    private List<Parking> mesParkings;

    public ObservableList data = FXCollections.observableArrayList();

    private static GestionParkingsController instance;
    

    public GestionParkingsController (){
        instance = this;
    }

    public static GestionParkingsController getInstance(){
        return instance;
    }

    public void switchToMainFront(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1087, 649);
        stage.setTitle("Page acceuil");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        try {
            parkingScrolPane.setStyle("-fx-background-color:transparent;");
           // setBtnStyles();
            appendParkingLayout();
            user = us.getUser(1);
                if(user.getRoleUser().equals("client")) {
                    reserverBtn.setVisible(false);
                    nomParking.setVisible(false);
                    capaciteParking.setVisible(false);
                    takenParking.setVisible(false);
                    imageParkingBtn.setVisible(false);
                    btnAjouterparking.setVisible(false);
                    prixParking.setVisible(false);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouterparking)
            ajouterParking();
    }

    private void ajouterParking(){
        try{
            if(inputsControl()){
                parking.setCapaciteParking(Integer.parseInt(capaciteParking.getText()));
                parking.setNomParking(nomParking.getText());
                parking.setTakenPParking(Integer.parseInt(takenParking.getText()));
                parking.setPrixParking(Float.valueOf(prixParking.getText()));
                parking.setLogoParking(tfImageParking.getText());
                ps.ajouter(parking);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Parking ajouté avec succés!");
                alert.show();
                clearInputsData();
                clearParkingLayout();
                appendParkingLayout();
            }
                
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void importImageEv(ActionEvent event) throws IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\xampp\\htdocs\\PideversImgUploaded\\Event\\"+"Event"+x+".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            tfImageParking.setText(DBPath);
            int b=0;
            while(b!=-1){
                b=bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
        } else  {
            System.out.println("error");
        }
    }

    public void appendParkingLayout(){
            int columns=0;
            int row =1;
            try{          
                    mesParkings = ps.recuperer();
                    ReservationParkingService reps = new ReservationParkingService();
                    List<Parking> mesParkingReserves = reps.recupererUserResevrations(1);
                for (int i =0; i< mesParkings.size(); ++i) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                    HBox cardBox = fxmlLoader.load();
                    CardController cardController = fxmlLoader.getController();
                    user = us.getUser(1);
                    if(user.getRoleUser().equals("GReclamation")) {
                        cardController.btnBouderParking.setVisible(false);
                        cardController.btnResParking.setVisible(false);
                    }
                     else if (user.getRoleUser().equals("client")) {
                        cardController.btnModifierParking.setVisible(false);
                        cardController.btnSuppParking.setVisible(false);
                        cardController.btnBouderParking.setVisible(false);
                    }
                    if(!mesParkingReserves.contains(i)) cardController.btnBouderParking.setVisible(false);
                    cardController.setDataParking(mesParkings.get(i));
                    if (columns == 2){
                        columns =0;
                        ++row;
                    }
                    parkingGridPane.add(cardBox,++columns,row);
                }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void clearParkingLayout(){
        parkingGridPane.getChildren().clear();
    }

    public void openReservationParkingBullet(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("GestionResevationParking.fxml"));
        try {
            AnchorPane anchor = fxmlLoader.load();
            Stage stage = new Stage();
            Scene s = new Scene (anchor);
            stage.setScene(s);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Reservations Parking");
            stage.show();
            
            Stage stage2 = (Stage) reserverBtn.getScene().getWindow();
    
            // fermer la fenêtre
            stage2.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean inputsControl(){
        if (nomParking.getText().trim().isEmpty() ||
                capaciteParking.getText().trim().isEmpty() ||
                takenParking.getText().trim().isEmpty() ||
                prixParking.getText().trim().isEmpty() ||
                tfImageParking.getText().trim().isEmpty()
        ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Veuillez Remplir Les Champs");
            alert.show();
            return false;
        }
        return true;
    }

    @FXML
    private void filterev(KeyEvent event) throws SQLException {
       data.clear();
        data.addAll(ps.recuperer().stream().filter(
                (e)
                        -> e.getNomParking().toLowerCase().contains(searchtf.getText().toLowerCase())
        ).collect(Collectors.toList()));
    }

    public void fillInputsWithData( Parking ev ){
        tfImageParking.setText(ev.getLogoParking());
        capaciteParking.setText(String.valueOf(ev.getCapaciteParking()));
        nomParking.setText(ev.getNomParking());
        prixParking.setText(String.valueOf(ev.getPrixParking()));
        takenParking.setText(String.valueOf(ev.getTakenPParking()));
    }

    public void clearInputsData(){
        nomParking.setText("");
        tfImageParking.setText("");
        prixParking.setText("");
        capaciteParking.setText("");
        takenParking.setText("");
    }

    public void setBtnStyles(){
        btnAjouterparking.setTextFill(Color.WHITE);
        reserverBtn.setTextFill(Color.WHITE);
        nomParking.setStyle("-fx-text-inner-color: #000; -fx-border-color: #fff; -fx-border-radius: 10; ");
        prixParking.setStyle("-fx-text-inner-color: #000; -fx-border-color: #fff; -fx-border-radius: 10; ");
        takenParking.setStyle("-fx-text-inner-color: #000; -fx-border-color: #fff; -fx-border-radius: 10; ");
        capaciteParking.setStyle("-fx-text-inner-color: #fff; -fx-border-color: #fff; -fx-border-radius: 10; ");
    }

    public void clearData() {
        parkingGridPane.getChildren().clear();
    }

    public boolean removeNodeByRowColumnIndex(final int row, final int column, GridPane parGridPane) {
        ObservableList<Node> childrens = parkingGridPane.getChildren();
        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                parkingGridPane.getChildren().remove(node);
                return true;
            }
        }
        return false;
    }

    Parking evSearch = new Parking();
    public void showUsersByLog() throws SQLException{
        ObservableList<Parking> listEventsForSearch = FXCollections.observableList(ps.recuperer());
        try{
            listEventsForSearch.clear();
            listEventsForSearch.addAll(ps.recuperer().stream().filter((e)
                               -> e.getNomParking().toLowerCase().contains(searchtf.getText().toLowerCase())
            ).collect(Collectors.toList()));
            int columns = 0;
            int row = 1;
            for (int i = 0; i < listEventsForSearch.size(); ++i) {
                FXMLLoader fxmlLoader =new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = (CardController) fxmlLoader.getController();
                user = us.getUser(1);
                if(user.getRoleUser().equals("GReclamation")) {
                    cardController.btnBouderParking.setVisible(false);
                    cardController.btnResParking.setVisible(false);
                }
                else if (user.getRoleUser().equals("client")) {
                cardController.btnModifierParking.setVisible(false);
                cardController.btnSuppParking.setVisible(false);
                cardController.btnBouderParking.setVisible(false);
                }
                cardController.setDataParking(listEventsForSearch.get(i));
                if (columns == 2) {
                    columns = 0;
                    ++row;
                }
               parkingGridPane.add(cardBox, ++columns, row);
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void FindEvent() {
        searchtf.setOnKeyReleased(keyEvent -> {
                    //clearData();
                    parkingGridPane.getChildren().clear();
            try {
                showUsersByLog();
            } catch (SQLException ex) {
                Logger.getLogger(GestionParkingsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
  
}
