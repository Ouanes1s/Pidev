/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.CommentEvenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.CommentService;

/**
 *
 * @author 21627
 */
public class CommentItemController implements Initializable {
    
      @FXML
    private HBox Hbox;
    @FXML
    private Text UserName;
    @FXML
    private Text reponse;
    
    @FXML
    private Button supprimer;
    @FXML
    private Button Update;

    public static CommentEvenement idcomment ;
    /**
     * Initializes the controller class.
     */
    
    
    
    public CommentItemController(){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/CommentItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    
    
    
    
    }
            
            
    @Override        
    public void initialize(URL url, ResourceBundle rb) {
        
        
      
        
        
       
        // TODO
    }    
    
    
         public void setInfo(CommentEvenement string ) throws SQLException, Exception
    { 
                idcomment = string ;
        CommentService rs = new CommentService();
        
        System.out.println("eeeeeaymen : "+string.toString());

        // System.out.println("vuuuuuuuuuuuuuuuuuuuuuuuuu"+idcomment.getId());
    CommentEvenement c = rs.finCommentById(idcomment.getIdComment());
    
 

      /*  
        if (rs.propre_comment(Session.getCurrentSession(), c.getIdComment())==0)
        {
            
           supprimer.setVisible(false);
           Update.setVisible(false);

        }
        */
          
        
        
        reponse.setText(string.getComment());
      
        UserName.setText("Hiba");
       
       // VoteService vs = new  VoteService();
  
        //int v = vs.NumLike(string.getId_reponse());
        //int v1 = vs.NumdeLike(string.getId_reponse());
    
        //nbr_like.setText(String.valueOf(v));
        //nbr_deslike.setText(String.valueOf(v1));
        //if (v1>2){
        
        //Oki.setVisible(true);
        }
   
  @FXML
    private void DeleteQuestion(ActionEvent event) throws SQLDataException, IOException {
        
         CommentService q = new CommentService();
         
         System.out.println("helooooooooooooooooooooo"+idcomment.getIdComment());
         q.deleteComment(idcomment.getIdComment());
         Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowDetailsEvent.fxml"));
            Stage myWindow =  (Stage) supprimer.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void UpdateReponse(ActionEvent event) {
        
      
         Parent root ;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/UpdateComment.fxml"));
            Stage myWindow =  (Stage) supprimer.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Modifier Reponse");
            myWindow.show();
            
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
   
     
    
    public HBox getBox()
    {
        return Hbox;
    }

    
    
}



