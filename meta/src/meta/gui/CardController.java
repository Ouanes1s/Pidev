package meta.gui;

import entities.Parking;
import entities.ReservationParking;
import services.ParkingService;

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
import services.ReservationParkingService;

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
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            
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
        try{
            Parking myP = ps.getParking(NomParking.getText());
            ReservationParking rp = new ReservationParking();
            rp.setIdParking(myP.getIdParking());
            rp.setNbHours(3);
            rp.setIdUser(1);
            reps.ajouter(rp);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success !");
            alert.setContentText("The Reseravtion Has Been Added");
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
            alert.setContentText("The Parking Has Been Deleted");
            alert.show();
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
