package finalintegration2.TacheReclamation;

import finalintegration2.Models.Parking;
import finalintegration2.Models.ReservationParking;
import finalintegration2.Models.Session;
import finalintegration2.Models.User;
import finalintegration2.Services.ParkingService;
import finalintegration2.Services.ReservationParkingService;
import finalintegration2.Utils.ConnectionToDB;
//import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class GestionReservationEvController implements Initializable {
   Connection cnx = ConnectionToDB.getInstance().getConnection();


    @FXML
    public PieChart piechartrev;

    @FXML
    private GridPane resEvGridPane;

    @FXML
    private ScrollPane resEvScrollPane;
    @FXML
    private Button Retour;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FXMLLoader fxmlLoader;

    ReservationParking r = new ReservationParking();
    ReservationParkingService reps = new ReservationParkingService();
    Parking pr = new Parking();
    ParkingService ps = new ParkingService();
    User user = new User();
    

    private static GestionReservationEvController instance;

    public GestionReservationEvController (){
        instance = this;
    }

    public static GestionReservationEvController getInstance(){
        return instance;
    }


    public void switchToMainFront(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1087, 649);
        stage.setTitle("Page accueil");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        piechartrev.setData(piechartdata);
        appendReservationEvLayout();
        
    }
    public void Retour(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GestionParkings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   
    }
    @FXML //non utilis??
    //public void pdfResEv(ActionEvent event) throws FileNotFoundException {
     /*   Utilisateur u = new Utilisateur();
        UtilisateurService us = new UtilisateurService();
        u = (Utilisateur) UserSession.INSTANCE.get("user");
        if (u.getRankUtilisateur() == 1)
            revs.pdfReservationEvAdmin(u.getIdUtilisateur());
        else
            revs.pdfReservationEv(u.getIdUtilisateur());
    }*/

    ObservableList< PieChart.Data> piechartdata;
    ArrayList<String> p = new ArrayList<String>();
    ArrayList<Integer> c = new ArrayList<Integer>();

    public void loadData() {
        String query = "select COUNT(*) as count,p.nom_parking From parking p,reservationparking rev WHERE p.id_parking=rev.id_parking GROUP BY rev.id_parking"; //ORDER BY asc
        piechartdata = FXCollections.observableArrayList();
        cnx = ConnectionToDB.getInstance().getConnection();
        try {
            ResultSet rs = cnx.createStatement().executeQuery(query);
            while (rs.next()) {
                piechartdata.add(new PieChart.Data(rs.getString("nom_parking"),rs.getInt("count")));
                p.add(rs.getString("e.nom_parking"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Parking> mesParkingsReserves;

    public void appendReservationEvLayout(){
        int columns=0;
        int row =1;
        String cin = Session.getCin();
        try{
            mesParkingsReserves = reps.recupererUserResevrations(cin);
            for (int i =0; i< mesParkingsReserves.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.btnResParking.setVisible(false);
                cardController.btnModifierParking.setVisible(false);
                cardController.btnSuppParking.setVisible(false);
                //user = us.getUser(1); //session
                String nom = Session.getNom();
        String role = Session.getRole();
        String type = Session.getTypeA();
                if(type.equals("Gestion de Parkings")) cardController.btnBouderParking.setVisible(false);
                cardController.setDataParking(mesParkingsReserves.get(i));
                if (columns == 2){
                    columns =0;
                    ++row;
                }
                resEvGridPane.add(cardBox,++columns,row);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void clearReservationEvLayout(){
        resEvGridPane.getChildren().clear();
    }

}
