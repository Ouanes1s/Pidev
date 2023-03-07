package finalintegration2.TacheReclamation;

import finalintegration2.Models.Parking;
import finalintegration2.Models.ReservationParking;
import finalintegration2.Models.Session;
import finalintegration2.Models.User;
import finalintegration2.Services.ParkingService;

//import org.controlsfx.control.Notifications;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import finalintegration2.Services.ReservationParkingService;

public class CardController implements Initializable {
  
      @FXML
    private HBox BoxParking;

    @FXML
    private ImageView ImageParking;

    @FXML
    private Label NomParking;

    @FXML
    private Label PrixParking;

    @FXML
     Button btnResParking;

    @FXML
     Button btnBouderParking;

    @FXML
     Button btnModifierParking;

    @FXML
     Button btnSuppParking;

    private static CardController instance;

    public CardController (){
        instance = this;
    }

    public static CardController getInstance(){
        return instance;
    }
   
    ReservationParkingService reps = new ReservationParkingService();
    ParkingService ps = new ParkingService();
    //MODIFIER
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Object ==> Connected User
        String nom = Session.getNom();
        String cin = Session.getCin();
        String role = Session.getRole();
        String type = Session.getTypeA();
        //user = us.getUser(cin); //id 1
        if(type.equals("Gestion de Parkings")) btnResParking.setVisible(false);
    }

    public void setDataParking(Parking parking){
        NomParking.setText(parking.getNomParking());
        PrixParking.setText(String.valueOf(parking.getPrixParking()) + " TND");
        String pathImageP = parking.getLogoParking();
        File f = new File(pathImageP);
        Image image = new Image("file:" + f.getAbsolutePath());
        ImageParking.setImage(image);
    }

    
    

    @FXML
    private void ajouterReservationParking(){
        String cin = Session.getCin();
        try{
            Parking myP = ps.getParking(NomParking.getText());
            ReservationParking rp = new ReservationParking();
            rp.setIdParking(myP.getIdParking());
            rp.setNbHours(3);
            rp.setCin(cin);
            reps.ajouter(rp);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success !");
            alert.setContentText("La réservation du parking a été ajoutée");
            alert.show();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void supprimerParking() throws SQLException {
       
          ParkingService ps = new ParkingService();
            Parking myP = ps.getParking(NomParking.getText());
            ps.supprimer(myP);
           //revs.clearReservationTable(myEv.getIdEvenement());
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success !");
            alert.setContentText("Le parking a été supprimé avec succès");
            alert.show();
            /*Notifications.create()
    .title("Suppression")
   .text("Le parking a été supprimé avec succès.")
    .showInformation();*/
            GestionParkingsController.getInstance().clearInputsData();
            GestionParkingsController.getInstance().clearParkingLayout();
            GestionParkingsController.getInstance().appendParkingLayout();
       
    }

    @FXML
    public void fillData(MouseEvent event) throws SQLException{
         ParkingService ps = new ParkingService();
          Parking myP = ps.getParking(NomParking.getText());
  
        GestionParkingsController.getInstance().fillInputsWithData(myP);
    }

    @FXML
    public void resetBg(MouseEvent event){
        /* myEv = revs.getEventForReservation(TitreEvenement.getText());
        BoxEvenement.setStyle("-fx-background-color: #fff");
        //GestionEvenementController.getInstance().fillInputsWithData(myEv);*/
    }

    @FXML
    public void modifierParking() throws SQLException {
    
        ParkingService ps = new ParkingService();
        Parking myP = ps.getParking(NomParking.getText());
        myP.setCapaciteParking(Integer.parseInt(GestionParkingsController.getInstance().capaciteParking.getText()));
        myP.setNomParking(GestionParkingsController.getInstance().nomParking.getText());
        myP.setPrixParking(Float.valueOf(GestionParkingsController.getInstance().prixParking.getText()));
        myP.setLogoParking(GestionParkingsController.getInstance().tfImageParking.getText().
                replaceAll("\\\\","\\\\\\\\"));
        myP.setTakenPParking(Integer.parseInt(GestionParkingsController.getInstance().takenParking.getText()));
        ps.modifier(myP);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success !");
        alert.setContentText("The Parking Has Been Updated");
        alert.show();
        GestionParkingsController.getInstance().clearInputsData();
        GestionParkingsController.getInstance().clearParkingLayout();
        GestionParkingsController.getInstance().appendParkingLayout();
        
    }

    @FXML
    private void suppprimerReservationParking(){
        try{
            ParkingService ps = new ParkingService();
            Parking myP = ps.getParking(NomParking.getText());
            ReservationParking myRP = reps.getReservationParking(myP.getIdParking());
            reps.supprimer(myRP);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success !");
            alert.setContentText("Reservation Has Been Deleted");
            alert.show();
            GestionReservationEvController.getInstance().clearReservationEvLayout();
            GestionReservationEvController.getInstance().appendReservationEvLayout();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

   
}
