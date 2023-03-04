/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.user.gui;

import edu.user.entities.Membre;
import edu.user.services.UserCRUD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jdk.nashorn.api.scripting.JSObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * FXML Controller class
 *
 * @author chebi
 */
public class InscriptionMembreController implements Initializable {
    @FXML
    private TextField txt_date;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtcin;
    @FXML
    private TextField txtemail;
    @FXML
    private Button confirmer;
    @FXML
    private TextField txtmdp;
    @FXML
    private ImageView signin;
    @FXML
    private WebView captchaWebView;
    @FXML
    private TextField captchaResponseX;
    private String captchaResponse;
    private final String siteKey = "6Lek1NAkAAAAAHVyGISMpvhMzJtmA2aBUlYVDh7k";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         captchaWebView.getEngine().load("https://www.google.com/recaptcha/api/fallback?k=" + siteKey);
        captchaWebView.getEngine().setJavaScriptEnabled(true);
        captchaWebView.getEngine().getLoadWorker().stateProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED) {
                        JSObject window = (JSObject) captchaWebView.getEngine().executeScript("window");
                        window.setMember("java", new JavaBridge());
                    }
                });
    }    
    public class JavaBridge {

        public void setCaptchaResponse(String response) {
            captchaResponse = response;
        }

        public void recaptchaCallback(String response) {
            setCaptchaResponse(response);
        }

        public String getCaptchaResponse() {
            return captchaResponse;
        }
    }
    //Méthode pour vérifier si le captcha a été rempli correctement
    public boolean verifyCaptcha() {
        boolean success = false;
        captchaResponse = captchaResponseX.getText();
        try {
            URL url = new URL("https://www.google.com/recaptcha/api/siteverify");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String secretKey = "6Lek1NAkAAAAADtFlwrmQA2uhItC39NW5wxb3C6c";
            String postParams = "secret=" + secretKey + "&response=" + captchaResponse;
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(postParams.getBytes());
            os.flush();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
                success = jsonObject.get("success").getAsBoolean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    //

    @FXML
    private void ajouterMembre(ActionEvent event) {
         String nom,prenom,email,date;
        String cin,mdp;
        
        nom = txtnom.getText();
        prenom = txtprenom.getText();
        email = txtemail.getText();
        date = txt_date.getText();
        cin = txtcin.getText();
        mdp = txtmdp.getText();
        
        //Contôle de saisie
             
       if (nom==null || prenom ==null || email==null || date==null || cin==null || mdp==null)      
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
        }else if (mdp.length()<8){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid password");
            alert.show();
        }
        
        
        else{
            //Methode Ajouter    
            Membre user1 = new Membre (nom,prenom,cin,email,mdp,date);
            UserCRUD uc = new UserCRUD();
            
            //controle d'.existance
            if (uc.VerifCin(user1.getCin_user())!=0 || uc.verifierEmailBd(user1.getEmail_user())==true){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("USER ALREADY EXISTS. CIN or Email is taken");
            alert.show(); 
            }
            else if(verifyCaptcha()==false){Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("WWrong captcha");
            alert.show();}
            else {
            
            uc.ajouterUserMembre(user1);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully created your account. ");
            alert.show();
            
            /*try {
                     Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
                Stage Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
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
                }*/
            
            }
    }
    
    }

    @FXML
    private void signinclick(MouseEvent event) {
        
     try {
                     Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
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