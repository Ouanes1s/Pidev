package meta.gui;

import entities.Reclamation;
import services.ReclamationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GestionGererReclamationController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    FXMLLoader fxmlLoader;
    @FXML
    private GridPane GridRec;

    @FXML
    private Label MessageUpdate;

    @FXML
    private ScrollPane ScrollRec;

    @FXML
    private HBox hboxRec;

    @FXML
    private ImageView imgArea;

    @FXML
    void switchToMainFront(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(GestionGererReclamationController.class.getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 993, 616);
        stage.setTitle("Welcome To Metax Space");
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws SQLException {
       ScrollRec.setStyle("-fx-background: #9BA3EB; -fx-border-color: #ffffff;");
       // GridRec.setStyle("-fx-background: #9BA3EB; -fx-border-color: #ffffff;");
        //hboxRec.setStyle("-fx-background: #ff000; -fx-border-color: #131313;");
        ShowRec();
    }
    void ShowRec() throws SQLException {

        ReclamationService rs = new ReclamationService();
        List<Reclamation> mesReclamations = rs.recuperer();
        System.out.println(mesReclamations);
        try {
            int columns = 0;
            int row = 1;
            for (Reclamation mesReclamation : mesReclamations) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ReclamationCard.fxml"));
                VBox cardBox = fxmlLoader.load();
                ReclamationCardController reclamationCardController = ReclamationCardController.getInstance();
                reclamationCardController.setReclamation(mesReclamation);
                if (columns == 2) {
                    columns = 0;
                    ++row;
                }
                GridRec.add(cardBox, ++columns, row);
                GridPane.setMargin(cardBox, new Insets(10));
                GridRec.setBackground(new Background(new BackgroundFill(Color.rgb(19, 19, 19), new CornerRadii(0), new Insets(0))));

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
