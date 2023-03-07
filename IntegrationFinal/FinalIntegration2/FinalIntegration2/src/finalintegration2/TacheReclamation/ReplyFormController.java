package finalintegration2.TacheReclamation;

import finalintegration2.Models.Session;
import finalintegration2.Models.User;
import finalintegration2.Services.ReclamationService;
import finalintegration2.Services.UserCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public class ReplyFormController {

    @FXML
    private Button BackToMain;

    @FXML
    private Circle ImgUser;

    @FXML
    private TextField ReplyAdmin;

    @FXML
    private Button ReplyWithEmail;

    @FXML
    private TextField adminLog;

    @FXML
    void BackToMain(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

    }
    public void initialize() throws SQLException {
        // Session user.getnom
        String nom = Session.getNom();
        String prenom = Session.getPrenom();
        adminLog.setText(nom+" "+prenom);
//        String path= us.LoadIMG(u);
//        File f = new File(path);
//        Image img= new Image(f.getAbsolutePath());
//        ImgUser.setFill(new ImagePattern(img));
    }
    @FXML
    void ReplyWithEmail(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        UserCRUD us=new UserCRUD();
        User user = us.getOneByCin(Session.getCin());
        String Email = user.getEmail_user(); //user.getemail
        String body=ReplyAdmin.getText();
        if (rs.ContactUserReclam(Email,body)){
            stage.close();
        }
    }
}
