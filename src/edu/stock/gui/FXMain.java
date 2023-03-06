/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author maidi
 */
public class FXMain extends Application {
    
    private double x=0;
    private double y=0;
    
    @Override
    public void start(Stage primaryStage) {
     
        try {
            Parent root = FXMLLoader.load(getClass().getResource("tableViewFile2.fxml"));
            
            Scene scene = new Scene(root);
            root.setOnMousePressed((MouseEvent event)->{
                 x= event.getSceneX();
                 y=event.getSceneY();
            });
            
            root.setOnMouseDragged((MouseEvent event)->{
                primaryStage.setX(event.getScreenX()-x);
                primaryStage.setY(event.getScreenY()-y);
            });
            
            //scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setTitle("Ajouter Produit");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());            
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
