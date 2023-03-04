/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meta.gui;

import entities.Parking;
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

/**
 * FXML Controller class
 *
 * @author ProtocolBlood
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
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        try {
            parkingScrolPane.setStyle("-fx-background-color:transparent;");
           // setBtnStyles();
            appendParkingLayout();
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
                parking.setCapaciteParking(Integer.parseInt(capaciteParking.getText()));
                parking.setNomParking(nomParking.getText());
                parking.setTakenPParking(Integer.parseInt(takenParking.getText()));
                parking.setPrixParking(Float.valueOf(prixParking.getText()));
                parking.setLogoParking(tfImageParking.getText());
                ps.ajouter(parking);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Parking Ajoutée Avec Succés!");
                alert.show();
                //clearInputsData();
                clearParkingLayout();
                appendParkingLayout();
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean inputsControl(){
       /* if (tfTitreEvenement.getText().trim().isEmpty() ||
                tfLieuEvenement.getText().trim().isEmpty() ||
                tfTypeEvenement.getText().trim().isEmpty() ||
                tfDescriptionEvenement.getText().trim().isEmpty() ||
                datePickerEvenement.getValue().equals(null)
        ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Veuillez Remplir Les Champs");
            alert.show();
            return false;
        }*/
        return true;
    }

    @FXML
    private void filterev(KeyEvent event) throws SQLException {
      /*  data.clear();
        data.addAll(es.afficher().stream().filter(
                (e)
                        -> e.getTitreEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
                        || e.getLieuEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
                        || e.getTypeEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
                        || e.getDescriptionEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
        ).collect(Collectors.toList()));*/
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
        for (int x = 0; x < parkingGridPane.getRowConstraints().size(); x++) {
            for (int i = 0; i < parkingGridPane.getRowConstraints().size(); i++) {
                {
                    removeNodeByRowColumnIndex(i, x, parkingGridPane);
                }
            }
        }
    }

    public boolean removeNodeByRowColumnIndex(final int row, final int column, GridPane eventGridPane) {
        ObservableList<Node> childrens = eventGridPane.getChildren();
        for (Node node : childrens) {
            if (eventGridPane.getRowIndex(node) == row && eventGridPane.getColumnIndex(node) == column) {
                eventGridPane.getChildren().remove(node);
                return true;
            }
        }
        return false;
    }

   /* Evenement evSearch = new Evenement();
    public void showUsersByLog(){
        ObservableList<Evenement> listEventsForSearch = revs.afficher();
        try{
            listEventsForSearch.clear();
            listEventsForSearch.addAll(es.afficher().stream().filter((e)
                            -> e.getTitreEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
                            || e.getLieuEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
                            || e.getTypeEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
                            || e.getDescriptionEvenement().toLowerCase().contains(searchtf.getText().toLowerCase())
            ).collect(Collectors.toList()));
            int columns = 0;
            int row = 1;
            for (int i = 0; i < listEventsForSearch.size(); ++i) {
                FXMLLoader fxmlLoader =new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Views/Card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = (CardController) fxmlLoader.getController();
                cardController.setDataEvenement(listEventsForSearch.get(i));
                if (columns == 2) {
                    columns = 0;
                    ++row;
                }
               eventGridPane.add(cardBox, ++columns, row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void FindEvent() {
        searchtf.setOnKeyReleased(keyEvent -> {
                    clearData();
                    showUsersByLog();
        });
    }*/
   
}
