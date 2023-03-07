/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import static controller.ShowEvenementController.idE;
import entity.BadWords;
import entity.CommentEvenement;
import entity.Evenements;
import entity.Vote;
import finalintegration2.Models.Session;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import service.CommentService;
import service.EvenementService;
import service.ServiceVote;


/**
 *
 * @author 21627
 */
public class ShowDetailsEventController implements Initializable {
    
   @FXML
    private ListView<CommentEvenement> listViewQR;
    ObservableList<CommentEvenement> data;
    int i,i2 ;
    public static int idC ;

    @FXML
    private Text question;
    
    @FXML
    private Button type_vote_oui;
    @FXML
    private Button type_vote_non;
    @FXML
    private Button addComment;
    @FXML
    private Button back;
    @FXML
    private ImageView img_ev;
    @FXML
    private TextField commentText;
    @FXML
    private Label nbrLike;
    @FXML
    private Label nbrdeslike;
    @FXML
    private Text question1;
 
    
     EvenementService es = new EvenementService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
          CommentService cs = new CommentService();

          ServiceVote v = new ServiceVote();
         int lik;
        try {
            lik = v.NumLike(ShowEvenementController.idE);
            
            int des =  v.NumdeLike(idE);
            nbrLike.setText(String.valueOf(des));
           nbrdeslike.setText(String.valueOf(lik));
        } catch (SQLException ex) {
            Logger.getLogger(ShowDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }


        
         
         
         System.err.println("okiiiiiiiiiiii"+ es.get_Evenment(idE).getImage());
         
         question.setText(es.get_Evenment(idE).getTitre());
         String ImageUrl ="/gui/img/";
        Image image = new Image(ImageUrl + es.get_Evenment(idE).getImage());
       img_ev.setImage(image);
        try {
            data = cs.getAllCommentByEvent(es.get_Evenment(idE));
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowDetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("ya aymen"+data);
         //supprimer.setVisible(false);
        // Question quest = new Question();
         //quest.setId_client(LoginController.session.getId());

     /*    if (quest.getId_client()==idU){
       supprimer.setVisible(true);
    }*/
         
     

      listViewQR.setItems(data);
       listViewQR.setCellFactory((ListView<CommentEvenement> param) -> new ListViewCommentEvent());
    }

        
        
        
        // TODO
   /*    
    private void DeleteQuestion(ActionEvent event) throws SQLDataException {
        
    CommentService q = new CommentService();
    ObservableList<CommentEvenement> e;
        e = listViewQR.getSelectionModel().getSelectedItems();
        
         for (CommentEvenement m : e) {
            idC=m.getIdComment();
              
        }
         System.out.println("controller.ShowDetailsEventController.DeleteQuestion(ddddddddd)"+i);
        q.deleteComment(idC);
         Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) question.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    
    }
    private void UpdateReponse(ActionEvent event) {
        
           ObservableList<CommentEvenement> c;
        c = listViewQR.getSelectionModel().getSelectedItems();
        
      
        for (CommentEvenement m : c) {
            idC=m.getIdComment();
            
              
        }
        
         Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/UpdateComment.fxml"));
            Stage myWindow =  (Stage) question.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Modifier Reponse");
            myWindow.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }   */

    @FXML
    private void Like(ActionEvent event) throws SQLException, Exception {
        
        ServiceVote sv  = new ServiceVote();
        if (sv.user_vote(Session.getId(),idE)== null)
        {
            Vote v = new Vote ();
        v.setId_client(Session.getId());
        v.setType_vote(1);
        v.setId_evenement(idE);
       sv.addVote(v);
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("votre vote est bien enregistrer").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        } 
        }
        else
        {
      Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Vous avez déja voter sur cette Evenement").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();         
        }
       
        
    }

    @FXML
    private void DesLike(ActionEvent event) throws SQLException, Exception {
        
        
        ServiceVote sv  = new ServiceVote();
  if (sv.user_vote(Session.getId(),idE)== null)
  {
       Vote v = new Vote ();
        v.setId_client(Session.getId());
        v.setType_vote(2);
        v.setId_evenement(idE);
        sv.addVote(v);
        
         Notifications notificationBuilder = Notifications.create()
                .title("Done").text("votre vote est bien enregistrer").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        }
  }
  else
  {
      Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Vous avez déja voter sur cette Evenement").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();        
  }
       
        
    }


    @FXML
    private void AddComment(ActionEvent event) throws SQLDataException, Exception {
           
        CommentEvenement e = new CommentEvenement();
        CommentService cs = new CommentService();
        e.setId(cs.findUserById(Session.getId()));
        e.setIdEvt(es.get_Evenment(idE));

        BadWords.loadConfigs();
      
            {
                
                if (BadWords.filterText(commentText.getText())) {
            
       Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("cette application n'autorise pas ces termes").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
       notificationBuilder.darkStyle();
       notificationBuilder.show();
                       
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        }

                  
                } 
                else
                
                {
                    
                          
         Notifications notificationBuilder = Notifications.create()
               .title("Succes").text("Votre Commentaire est publier").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
                   notificationBuilder.darkStyle();
                   notificationBuilder.show();
                    e.setIdEvt(es.get_Evenment(idE));
                   e.setComment(commentText.getText());
                   e.setId(cs.findUserById(Session.getId()));
                    cs.addComment(e);
                   
             
            
           Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) commentText.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("comment");
            myWindow.show();
             
        } catch (IOException ex) {
            Logger.getLogger(CommentItemController.class.getName()).log(Level.SEVERE,null,ex);
           
        }
        
    
            }
    
    }}
      /*    Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) Update.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Ajouter Comment");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
    } */
 
    @FXML
    private void back(ActionEvent event) {
         try {
           Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowEvenement.fxml"));
            Stage myWindow =  (Stage) addComment.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Ajouter Comment");
            myWindow.show();
           
        } catch (IOException ex) {
            Logger.getLogger(ShowDetailsEventController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void Pdf(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException {

        TelechargerPdf(es.get_Evenment(idE));
        
    }
    
    
             
        void TelechargerPdf( Evenements py) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        String file_name="C:\\Users\\21627\\OneDrive\\Bureau\\pdf\\mypdf.pdf";
        Document document = new Document();
        
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        
        Paragraph p =new Paragraph("L'evenement:"+py.getTitre()+" lieu:"+py.getLieu());
        Paragraph p1 =new Paragraph("Date début Début:"+py.getDateDebut()+" Date fin:"+py.getDateFin());
        Paragraph p2 =new Paragraph("Date début Début:"+py.getDateDebut()+" Date fin:"+py.getDateFin());
        
            com.itextpdf.text.Image img =com.itextpdf.text.Image.getInstance("C:\\Users\\21627\\OneDrive\\Bureau\\Hiba\\EvenementProject\\src\\gui\\img\\"+py.getImage());

        document.add(p);
        document.add(p1);
         document.add(p2);
          document.add(img);
        
        document.close();
        }
    
    
   

}



