package finalintegration2.TacheReclamation;;

import finalintegration2.Models.Reclamation;
import finalintegration2.Models.Session;
import finalintegration2.Services.ReclamationService;
import finalintegration2.Utils.ConnectionToDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ReclamationCardController {

    @FXML
    private Circle ImageUser;
    @FXML
    private Label NomUser;

    @FXML
    private Button dropRec;

    @FXML
    private Label prenomUser;

    @FXML
    private Button replyRec;
    @FXML
    private Label idRec;
    @FXML
    private Label sujetRec;
    private static ReclamationCardController instance;
    public ReclamationCardController (){
        instance = this;
    }
    public static ReclamationCardController getInstance(){
        return instance;
    }
    @FXML
    private VBox vboxUser;


    public void initialize() throws SQLException {

    }
    public void setReclamation(Reclamation reclamation) throws SQLException {

        sujetRec.setText(reclamation.getMessageReclamation());
        String nom = Session.getNom();
        String prenom = Session.getPrenom();
        NomUser.setText(nom);
        prenomUser.setText(prenom);
        
//        String path= us.LoadIMG(userReclamation);
//        File f = new File(path);
//        Image img= new Image(f.getAbsolutePath());
       // ImageUser.setFill(new ImagePattern(img));
        idRec.setText(String.valueOf(reclamation.getIdReclamation()));
     //   idRec.setVisible(false);
    }
    @FXML
    void CloseRec(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        if (rs.CloseRec(idRec.getText()))
        {
            vboxUser.setVisible(false);
        }
    }

    @FXML
    void replyRec(ActionEvent event) throws SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ReplyForm.fxml"));
        try {
            AnchorPane anchor1 = fxmlLoader.load();
            ReclamationService rs = new ReclamationService();
            Connection cnx = ConnectionToDB.getInstance().getConnection();
            String query = "UPDATE RECLAMATION SET etat_reclamation='1' where id_reclamation= '" + Integer.parseInt(idRec.getText()) + "' ";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            vboxUser.setVisible(false);
            Stage stage = new Stage();
            stage.setUserData(idRec.getText());
            Scene s = new Scene (anchor1);
            stage.setScene(s);
            stage.setTitle("Reply");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
