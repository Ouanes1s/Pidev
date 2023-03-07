/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.TacheCinema;
import finalintegration2.Models.Cinema;
import finalintegration2.Services.ServiceCinema;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hayth
 */
public class CinemaaController implements Initializable {

    @FXML
    private TextField tfnom_cinema;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfnum;
    @FXML
    private ListView<Cinema> CinemaView;
    @FXML
    private Button refresh;
    @FXML
    private TextField searchField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Cinema c = new Cinema();
        ServiceCinema sc=new ServiceCinema();
        List<Cinema> data=sc.agetAll();
        ObservableList<Cinema> observableData = FXCollections.observableArrayList(data);
        CinemaView.setItems(observableData);
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        String nom_cinema =tfnom_cinema.getText();
        String adresse=tfadresse.getText();
        String num=tfnum.getText();
        if (nom_cinema==null || adresse ==null || num==null )      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        }else if (nom_cinema.matches("[a..z]*")==true)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid name");
            alert.show();
        }else if (adresse.matches("[a..z]")==true ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid adresse");
            alert.show();
        }else if(num.matches("[0..9]*")==true){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid nombre");
            alert.show();}
        else{
        Cinema c = new Cinema (nom_cinema,adresse,num);
        ServiceCinema sc = new ServiceCinema();
        sc.ajouter2(c);
        refreshs();}
   }

    @FXML
    @SuppressWarnings("empty-statement")
    private void supprimer(ActionEvent event) {
        if (!CinemaView.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer la reservation du " + CinemaView.getSelectionModel().getSelectedItem().getId()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    ServiceCinema r=new ServiceCinema();
    r.supprimer(CinemaView.getSelectionModel().getSelectedItem().getId());
    refreshs();
    };
    
    }
    }
        
    @FXML
    //private void Liste_Voyage(MouseEvent event) {
      //   try {
        //       ReserverVoyage Res = TableVoyage.getSelectionModel().getSelectedItem();
          //     Dest.setText(Res.getNom_voyage());
            //   Age.setText(String.valueOf(Res.getAge()));
              // String c =  Res.getNom_voyage();
               //VoyageCombox.setValue(c);
               //String B =  Res.getTravel_Class();
               //Travel_ComboBox.setValue(B);
           //} catch (Exception e) {
             //  System.out.println(e.getMessage());
               
          // }
        //ServiceCinema sc=new ServiceCinema();
        //String id=tfnom_cinema_sup.getText();
        //int i=Integer.parseInt(id);
       //sc.supprimer(i);
        
        
    //}

   // @FXML
    private void refresh(ActionEvent event) {
      Cinema c = new Cinema();
        ServiceCinema sc=new ServiceCinema();
        List<Cinema> data=sc.getAll();
        ObservableList<Cinema> observableData = FXCollections.observableArrayList(data);
        CinemaView.setItems(observableData);
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         String nom_cinema =tfnom_cinema.getText();
        String adresse=tfadresse.getText();
        String num=tfnum.getText();
        Cinema c = new Cinema (nom_cinema,adresse,num);
        ServiceCinema sc = new ServiceCinema();
        c=CinemaView.getSelectionModel().getSelectedItem();
        c.setId(CinemaView.getSelectionModel().getSelectedItem().getId());
        c.setNom_cinema(nom_cinema);
        c.setAdresse(adresse);
        c.setNum(num);
        String n=tfnom_cinema.getText();
        String ad=tfadresse.getText();
        String nu =  tfnum.getText();
        
                
        //ReserverVoyage re = new ReserverVoyage(IdVoy,Tra,date,A);
    //System.out.println(re.toString());
    Cinema p=new Cinema(n,ad,nu);
        System.out.println(p.toString());
    sc.modifier(c);
    refreshs();

    Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
    alert.setContentText("Reservation ajouter");
    alert.showAndWait();
        
    }
     private void refreshs() {
        Cinema c = new Cinema();
        ServiceCinema sc=new ServiceCinema();
        List<Cinema> data=sc.getAll();
        ObservableList<Cinema> observableData = FXCollections.observableArrayList(data);
        CinemaView.setItems(observableData);     
    }

    @FXML
    private void search(ActionEvent event) {
 Cinema c = new Cinema();
        ServiceCinema sc=new ServiceCinema();        
        String searchText = searchField.getText();
    List<Cinema> searchResults = sc.searchByName(searchText, CinemaView.getItems());
    ObservableList<Cinema> observableData = FXCollections.observableArrayList(searchResults);
    CinemaView.setItems(observableData);
        System.out.println(searchResults);
    //CinemaView.setItems(FXCollections.observableArrayList(searchResults));
    }
public void sortByName() {
    Cinema c = new Cinema();
        ServiceCinema sc=new ServiceCinema();
    List<Cinema> cinemas = CinemaView.getItems();
    List<Cinema> sortedCinemas = cinemas.stream()
        .sorted(Comparator.comparing(Cinema::getNom_cinema))
        .collect(Collectors.toList());
    CinemaView.setItems(FXCollections.observableArrayList(sortedCinemas));
}
    

    @FXML
    private void sort(ActionEvent event) {
        sortByName();
    }
   
    
}
