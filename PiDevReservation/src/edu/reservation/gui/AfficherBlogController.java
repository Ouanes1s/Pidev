/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.gui;
import edu.reservation.entities.Blog;

import edu.reservation.services.ServiceBlog;
import edu.reservation.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
;
import javafx.scene.control.TextField;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList ;
import javafx.collections.transformation.SortedList;

/**
 * FXML Controller class
 *
 * @author Amine Khalfaoui
 */
public class AfficherBlogController implements Initializable {

    @FXML
    private ListView<Blog> listView;
    @FXML
    private TextField searchField;

    /**
     * Initializes the controller class.
     */
    public void list_affiche(){
    Connection cnx = DataSource.getInstance().getCnx();
    ObservableList<Blog> blogs = FXCollections.observableArrayList();
    PreparedStatement stmt = null;
    ResultSet rst = null;

    try {
        String req = "SELECT * FROM Blog ";
        stmt = cnx.prepareStatement(req);
        
        rst = stmt.executeQuery();

        while(rst.next()){
            Blog blog = new Blog(
                    rst.getString("titre_blg"),
                    rst.getString("email_blg"),
                    rst.getString("contenu_blg")
                   
                   
            ) ;
            blogs.add(blog);
        }

        listView.setItems(blogs);
        
        
        FilteredList<Blog> filteredBlogs = new FilteredList<>(blogs, p -> true);
        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredBlogs.setPredicate(blog -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (blog.getTitre_blg().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (blog.getContenu_blg().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                
                } else if (blog.getEmail_blg().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            }); 
            SortedList<Blog> sortedBlogs;
            sortedBlogs = new SortedList<>(filteredBlogs, Comparator.comparing(Blog::getTitre_blg));
        listView.setItems(sortedBlogs);
       
        });

      
    }
    catch (SQLException ex){
        System.err.println(ex.getMessage());
    }
}
  
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list_affiche();
              listView.setCellFactory(param -> new ListCell<Blog>() {
    @Override
    protected void updateItem(Blog blog, boolean empty) {
        super.updateItem(blog, empty);
        if (empty || blog == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(blog.getTitre_blg() + " " + blog.getEmail_blg()+" "+blog.getContenu_blg()+" ");
             

            // ajout de l'image à la cellule
//                    setGraphic(imageView); */
//          ImageView imageView = new ImageView(new Image(getClass().getResource("KitsunePrev.png").toExternalForm()));

                        // personnalisation de l'image
//                        imageView.setFitHeight(50);
//                        imageView.setFitWidth(50);

                        // création d'un conteneur pour l'image et le nom de l'agent
//                        VBox vbox = new VBox(imageView, new Label(agent.getType_A() + " " + agent.getPrenom_user()));
//                        vbox.setAlignment(Pos.CENTER);
//                        vbox.setSpacing(5);

                        // ajout du conteneur à la cellule
//                        setGraphic(vbox);
        }
    }
    
}
              );
              
              
              listView.setOnMouseClicked(event -> {
    if (event.getClickCount() == 3) {
        Blog blog = listView.getSelectionModel().getSelectedItem();
        if (blog != null) {
            supprimer(blog);
            listView.getItems().remove(blog);
        }
    }
});
    
                }
    

     public void supprimer(Blog blog) {
    Connection cnx = DataSource.getInstance().getCnx();
    PreparedStatement stmt = null;
    
    try {
        String req = "DELETE FROM Blog WHERE titre_blg = ?";
        stmt = cnx.prepareStatement(req);
        stmt.setString(1, blog.getTitre_blg());
        stmt.executeUpdate();
    }
    catch (SQLException ex){
        System.err.println(ex.getMessage());
    }
    finally {
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException ex){
                System.err.println(ex.getMessage());
            }
        }
    }
}

        
    }    
    
