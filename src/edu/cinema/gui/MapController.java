/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinema.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


/**
 * FXML Controller class
 *
 * @author hayth
 */
public class MapController implements Initializable {

    @FXML
    private WebView web;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        WebEngine webEngine = web.getEngine();
        webEngine.load("https://www.google.com/maps/place/esprit+ecole+d'ingénieurs/@36.899229,10.1874583,17z/data=!3m1!4b1!4m6!3m5!1s0x12e2cb75abbb1733:0x557a99cdf6c13b7b!8m2!3d36.899229!4d10.1896523!16s%2Fg%2F11dybgg6rl");
    }    

   
}
