/*
 * Copyright 2020-2021 LaynezCode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.stock.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.stock.alerts.*;

import edu.stock.animations.Animations;
import edu.stock.entites.Produit;
import edu.stock.mask.RequieredFieldsValidators;
import edu.stock.mask.TextFieldMask;
import edu.stock.notifications.NotificationType;
import edu.stock.notifications.NotificationsBuilder;
import edu.stock.preferences.Preferences;
import edu.stock.resources.Constants;
import edu.stock.util.ContextMenu;
import edu.stock.util.JFXDialogTool;
import edu.stock.utils.DatabaseConnection;
import edu.stock.services.DatabaseHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsController implements Initializable {

    private final ColorAdjust colorAdjust = new ColorAdjust();

    private final long LIMIT = 1000000;

    private final String ALREADY_EXISTS = "There is already a product with this barcode";

    public JFXButton tst;

    private ObservableList<Produit> listProducts;

    private ObservableList<Produit> filterProducts;

    @FXML
    private StackPane stckProducts;

    @FXML
    private AnchorPane rootProducts;

    @FXML
    private AnchorPane containerDeleteProducts;

    @FXML
    private HBox hBoxSearch;

    @FXML
    private TextField txtSearchProduct;

    @FXML
    private TextField txtSearchBarCode;

    @FXML
    private JFXButton btnNewProduct;

    @FXML
    private TableView<Produit> tblProducts;

    @FXML
    private TableColumn<Produit, Integer> colId;

    @FXML
    private TableColumn<Produit, Integer> colBarcode;

    @FXML
    private TableColumn<Produit, String> colName;

    @FXML
    private TableColumn<Produit, String> colDescription;

    @FXML
    private TableColumn<Produit, Double> colPurchasePrice;


    @FXML
    private TableColumn<Produit, Integer> colEtat;

    @FXML
    private TableColumn<Produit, Double> colQuantite;

    @FXML
    private AnchorPane containerAddProduct;

    @FXML
    private JFXTextField txtBarCode;

    @FXML
    private JFXTextField txtNameProduct;

    @FXML
    private JFXTextField txtPurchasePrice;

    @FXML
    private Text textAddProduct;

    @FXML
    private Text textPurchase;

    @FXML
    private Text textEtat;



    @FXML
    private JFXTextArea txtDescriptionProduct;

    @FXML
    private JFXButton btnUpdateProduct;

    @FXML
    private JFXButton btnSaveProduct;

    @FXML
    private JFXButton btnCancelAddProduct;

    @FXML
    private JFXTextField txtEtat;

    @FXML
    private JFXTextField txtQuantite;

    @FXML
    private ImageView imageProduct;

    @FXML
    private Pane paneContainer;

    @FXML
    private HBox imageContainer;

    private JFXDialogTool dialogAddProduct;

    private JFXDialogTool dialogDeleteProduct;

    private static final Stage stage = new Stage();

    private File imageFile;

    private ContextMenu contextMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        setMask();
        animateNodes();
        selectText();
        setValidations();
        validateUser();
        characterLimiter();
        initializeImage();
        setTextIfFieldIsEmpty();
        closeDialogWithTextFields();
        closeDialogWithEscapeKey();
    }

    private void setContextMenu() {
        contextMenu = new ContextMenu(tblProducts);

        contextMenu.setActionEdit(ev -> {
            showDialogEditProduct();
            contextMenu.hide();
        });

        contextMenu.setActionDelete(ev -> {
            showDialogDeleteProduct();
            contextMenu.hide();
        });

        contextMenu.setActionDetails(ev -> {
            showDialogDetailsProduct();
            contextMenu.hide();
        });

        contextMenu.setActionRefresh(ev -> {
            loadData();
            contextMenu.hide();
        });

        contextMenu.show();
    }

    private void initializeImage() {
        imageContainer.hoverProperty().addListener((o, oldV, newV) -> {
            if (newV) {
                colorAdjust.setBrightness(0.25);
                imageProduct.setEffect(colorAdjust);
            } else {
                imageProduct.setEffect(null);
            }
        });

        imageContainer.setPadding(new Insets(5));
        filterProducts = FXCollections.observableArrayList();
        imageProduct.setFitHeight(imageContainer.getPrefHeight() - 10);
        imageProduct.setFitWidth(imageContainer.getPrefWidth() - 10);
    }

    private void animateNodes() {
        Animations.fadeInUp(btnNewProduct);
        Animations.fadeInUp(tblProducts);
        Animations.fadeInUp(hBoxSearch);
    }

    private void setValidations() {
        RequieredFieldsValidators.toJFXTextField(txtBarCode);
        RequieredFieldsValidators.toJFXTextField(txtNameProduct);
        RequieredFieldsValidators.toJFXTextArea(txtDescriptionProduct);
        RequieredFieldsValidators.toJFXTextField(txtPurchasePrice);
    }

    private void setMask() {
        TextFieldMask.onlyDoubleNumbers5Integers(txtPurchasePrice);
        TextFieldMask.onlyNumbers(txtBarCode);
        TextFieldMask.onlyNumbers(txtSearchBarCode);
    }

    private void selectText() {
        TextFieldMask.selectText(txtNameProduct);
        TextFieldMask.selectText(txtBarCode);
        TextFieldMask.selectText(txtPurchasePrice);
        TextFieldMask.selectTextToJFXTextArea(txtDescriptionProduct);
    }

    private void setTextIfFieldIsEmpty() {
        TextFieldMask.setTextIfFieldIsEmpty(txtPurchasePrice);
        TextFieldMask.setTextIfFieldIsEmpty(txtQuantite);
        TextFieldMask.setTextIfFieldIsEmpty(txtEtat);
    }

    private void characterLimiter() {
        TextFieldMask.characterLimit(txtBarCode, 20);
    }

    @FXML
    private void showDialogAddProduct() {
        resetValidation();

        enableEditControls();
        disableTable();
        rootProducts.setEffect(Constants.BOX_BLUR_EFFECT);

        textAddProduct.setText("Ajouter Produit");
        imageContainer.toFront();
        containerAddProduct.setVisible(true);
        btnSaveProduct.setDisable(false);
        btnUpdateProduct.setVisible(true);
        btnSaveProduct.toFront();

        dialogAddProduct = new JFXDialogTool(containerAddProduct, stckProducts);
        dialogAddProduct.show();

        dialogAddProduct.setOnDialogOpened(ev -> {
            txtBarCode.requestFocus();
        });

        dialogAddProduct.setOnDialogClosed(ev -> {
            closeStage();
            tblProducts.setDisable(false);
            rootProducts.setEffect(null);
            containerAddProduct.setVisible(false);
            cleanControls();
        });
    }

    @FXML
    private void closeDialogAddProduct() {
        if (dialogAddProduct != null) {
            dialogAddProduct.close();
        }
    }

    @FXML
    private void showDialogDeleteProduct() {
        if (tblProducts.getSelectionModel().getSelectedItems().isEmpty()) {
            AlertsBuilder.create(AlertType.ERROR, stckProducts, rootProducts, tblProducts, Constants.MESSAGE_NO_RECORD_SELECTED);
            return;
        }

        rootProducts.setEffect(Constants.BOX_BLUR_EFFECT);
        containerDeleteProducts.setVisible(true);
        disableTable();

        dialogDeleteProduct = new JFXDialogTool(containerDeleteProducts, stckProducts);
        dialogDeleteProduct.show();

        dialogDeleteProduct.setOnDialogClosed(ev -> {
            tblProducts.setDisable(false);
            rootProducts.setEffect(null);
            containerDeleteProducts.setVisible(false);
            cleanControls();
        });

    }

    @FXML
    private void hideDialogDeleteProduct() {
        if (dialogDeleteProduct != null) {
            dialogDeleteProduct.close();
        }
    }

    @FXML
    private void showDialogEditProduct() {
        if (tblProducts.getSelectionModel().getSelectedItems().isEmpty()) {
            AlertsBuilder.create(AlertType.ERROR, stckProducts, rootProducts, tblProducts, Constants.MESSAGE_NO_RECORD_SELECTED);
            return;
        }

        showDialogAddProduct();
        btnUpdateProduct.toFront();
        textAddProduct.setText("Modifier Produit");
        selectedRecord();

    }

    @FXML
    private void showDialogDetailsProduct() {
        if (tblProducts.getSelectionModel().getSelectedItems().isEmpty()) {
            AlertsBuilder.create(AlertType.ERROR, stckProducts, rootProducts, tblProducts, Constants.MESSAGE_NO_RECORD_SELECTED);
            return;
        }

        showDialogAddProduct();
        textAddProduct.setText("Afficher Produit");
        selectedRecord();
        paneContainer.toFront();
        btnUpdateProduct.setVisible(false);
        btnSaveProduct.setDisable(true);
        btnSaveProduct.toFront();
        disableEditControls();
    }

    @FXML
    private void loadData() {
        loadTable();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionProduct"));
        colPurchasePrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
    }

    private void loadTable() {
        ArrayList<Produit> list = new ArrayList<>();
        try {
            String sql = "SELECT id, barcode, productName, purchasePrice, etat, quantite, descriptionProduct FROM produit";
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String barcode = resultSet.getString("barcode");
                String productName = resultSet.getString("productName");
                Double purchasePrice = resultSet.getDouble("purchasePrice");
                int etat = resultSet.getInt("etat");
                Double quantite = resultSet.getDouble("quantite");
                String descriptionProduct = resultSet.getString("descriptionProduct");
                list.add(new Produit(id, barcode, productName, purchasePrice, etat, quantite, descriptionProduct));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            AlertsBuilder.create(AlertType.ERROR, stckProducts, rootProducts, tblProducts, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
        }
        listProducts = FXCollections.observableArrayList(list);
        tblProducts.setItems(listProducts);
        tblProducts.setFixedCellSize(30);
    }

    private void selectedRecord() {
        Produit products = tblProducts.getSelectionModel().getSelectedItem();
        txtBarCode.setText(String.valueOf(products.getBarcode()));
        txtNameProduct.setText(products.getProductName());
        txtPurchasePrice.setText(String.valueOf(products.getPurchasePrice()));
        txtEtat.setText(String.valueOf(products.getEtat()));
        txtDescriptionProduct.setText(products.getDescriptionProduct());
        txtQuantite.setText(String.valueOf(products.getQuantite()));
        imageProduct.setImage(getImage(products.getId()));
        expandImage(products.getId(), products.getProductName());
    }

    @FXML
    private void newProduct() {
        String barcode = txtBarCode.getText().trim();
        String productName = txtNameProduct.getText().trim();
        String purchasePrice = txtPurchasePrice.getText().trim();
        String etat = txtEtat.getText().trim();
        String quantite = txtQuantite.getText().trim();
        String description = txtDescriptionProduct.getText().trim();

        if (barcode.isEmpty()) {
            txtBarCode.requestFocus();
            Animations.shake(txtBarCode);
            return;
        }

        if (DatabaseHelper.checkIfProductExists(barcode) != 0) {
            txtBarCode.requestFocus();
            NotificationsBuilder.create(NotificationType.ERROR, ALREADY_EXISTS);
            return;
        }

        if (productName.isEmpty()) {
            txtNameProduct.requestFocus();
            Animations.shake(txtNameProduct);
            return;
        }

        if (purchasePrice.isEmpty()) {
            txtPurchasePrice.requestFocus();
            Animations.shake(txtPurchasePrice);
            return;
        }

        if (etat.isEmpty()) {
            txtEtat.requestFocus();
            Animations.shake(txtEtat);
            return;
        }



        if (quantite.isEmpty()) {
            txtQuantite.requestFocus();
            Animations.shake(txtQuantite);
            return;
        }



        if (description.isEmpty()) {
            txtDescriptionProduct.requestFocus();
            Animations.shake(txtDescriptionProduct);
            return;
        }

        if (imageFile != null && imageFile.length() > LIMIT) {
            Animations.shake(imageContainer);
            NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_IMAGE_LARGE);
            return;
        }

        Produit products = new Produit();
        products.setBarcode(barcode);
        products.setProductName(productName);
        products.setDescriptionProduct(description);
        products.setPurchasePrice(Double.parseDouble(purchasePrice));
        products.setEtat(Integer.valueOf(etat));
        products.setQuantite(Double.parseDouble(quantite));
        products.setProductImage(getInputStream());

        boolean result = DatabaseHelper.insertNewProduct(products, listProducts);
        if (result) {
            loadData();
            cleanControls();
            closeDialogAddProduct();
            AlertsBuilder.create(AlertType.SUCCES, stckProducts, rootProducts, tblProducts, Constants.MESSAGE_ADDED);
        } else {
            NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
        }
    }

    private InputStream getInputStream() {
        InputStream is;
        try {
            if (imageFile != null) {
                is = new FileInputStream(imageFile);
            } else {
                is = ProductsController.class.getResourceAsStream(Constants.NO_IMAGE_AVAILABLE);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            NotificationsBuilder.create(NotificationType.INFORMATION, Constants.MESSAGE_IMAGE_NOT_FOUND);
            is = ProductsController.class.getResourceAsStream(Constants.NO_IMAGE_AVAILABLE);
        }
        return is;
    }

    private Image getImage(int id) {
        Image image = null;
        try {
            String sql = "SELECT imageProduct FROM produit WHERE id = ?";
            PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InputStream img = rs.getBinaryStream("imageProduct");
                if (img != null) {
                    image = new Image(img, 200, 200, true, true);
                } else {
                    image = new Image(Constants.NO_IMAGE_AVAILABLE, 200, 200, true, true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    @FXML
    private void updateProduct() {
        String barcode = txtBarCode.getText().trim();
        String productName = txtNameProduct.getText().trim();
        String purchasePrice = txtPurchasePrice.getText().trim();
        String etat = txtEtat.getText().trim();
        String quantite = txtQuantite.getText().trim();
        String description = txtDescriptionProduct.getText().trim();
        String barcodeFromTable = tblProducts.getSelectionModel().getSelectedItem().getBarcode();

        if (barcode.isEmpty()) {
            txtBarCode.requestFocus();
            Animations.shake(txtBarCode);
            return;
        }

        if (DatabaseHelper.checkIfProductExists(barcode) != 0 && !barcodeFromTable.equals(barcode)) {
            txtBarCode.requestFocus();
            Animations.shake(txtBarCode);
            NotificationsBuilder.create(NotificationType.ERROR, ALREADY_EXISTS);
            return;
        }

        if (productName.isEmpty()) {
            txtNameProduct.requestFocus();
            Animations.shake(txtNameProduct);
            return;
        }

        if (purchasePrice.isEmpty()) {
            txtPurchasePrice.requestFocus();
            Animations.shake(txtPurchasePrice);
            return;
        }

        if (etat.isEmpty()) {
            txtEtat.requestFocus();
            Animations.shake(txtEtat);
            return;
        }



        if (quantite.isEmpty()) {
            txtQuantite.requestFocus();
            Animations.shake(txtQuantite);
            return;
        }



        if (description.isEmpty()) {
            txtDescriptionProduct.requestFocus();
            Animations.shake(txtDescriptionProduct);
            return;
        }

        if (imageFile != null && imageFile.length() > LIMIT) {
            Animations.shake(imageContainer);
            NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_IMAGE_LARGE);
            return;
        }

        Produit products = tblProducts.getSelectionModel().getSelectedItem();
        products.setId(products.getId());
        products.setBarcode(barcode);
        products.setProductName(productName);
        products.setDescriptionProduct(description);
        products.setPurchasePrice(Double.parseDouble(purchasePrice));
        products.setEtat(Integer.valueOf(etat));
        products.setQuantite(Double.parseDouble(quantite));
        products.setProductImage(getInputStream());

        if (imageFile != null) {
            boolean result = DatabaseHelper.updateProduct(products);
            if (result) {
                closeDialogAddProduct();
                loadData();
                cleanControls();
                AlertsBuilder.create(AlertType.SUCCES, stckProducts, rootProducts, tblProducts, Constants.MESSAGE_UPDATED);
            } else {
                NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
            }
        } else {
            boolean result = DatabaseHelper.updateProductIfFileIsNull(products);
            if (result) {
                closeDialogAddProduct();
                loadData();
                cleanControls();
                AlertsBuilder.create(AlertType.SUCCES, stckProducts, rootProducts, tblProducts, Constants.MESSAGE_UPDATED);
            } else {
                NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
            }
        }
    }

    @FXML
    private void deleteProducts() {
        boolean result = DatabaseHelper.deleteProduct(tblProducts, listProducts);
        if (result) {
            loadData();
            cleanControls();
            hideDialogDeleteProduct();
            AlertsBuilder.create(AlertType.SUCCES, stckProducts, rootProducts, tblProducts, Constants.MESSAGE_DELETED);
        } else {
            NotificationsBuilder.create(NotificationType.ERROR, Constants.MESSAGE_ERROR_CONNECTION_MYSQL);
        }
    }

    private void cleanControls() {
        imageFile = null;
        txtPurchasePrice.clear();
        txtQuantite.clear();
        txtBarCode.clear();
        txtNameProduct.clear();
        txtEtat.clear();
        txtDescriptionProduct.clear();
        try {
        imageProduct.setImage(new Image(Constants.NO_IMAGE_AVAILABLE));
        }catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void disableEditControls() {
        txtBarCode.setEditable(false);
        txtDescriptionProduct.setEditable(false);
        txtNameProduct.setEditable(false);
        txtPurchasePrice.setEditable(false);
        txtEtat.setEditable(false);
        txtQuantite.setEditable(false);
    }

    private void enableEditControls() {
        txtBarCode.setEditable(true);
        txtNameProduct.setEditable(true);
        txtQuantite.setEditable(true);
        txtEtat.setEditable(true);
        txtPurchasePrice.setEditable(true);
        txtDescriptionProduct.setEditable(true);
    }

    private void disableTable() {
        tblProducts.setDisable(true);
    }

    private void resetValidation() {
        txtBarCode.resetValidation();
        txtEtat.resetValidation();
        txtQuantite.resetValidation();
        txtNameProduct.resetValidation();
        txtPurchasePrice.resetValidation();
        txtDescriptionProduct.resetValidation();
    }

    private void validateUser() {
        setContextMenu();
       // if (DatabaseHelper.getUserType().equals("Administrator")) {
            deleteUserDeleteKey();
            
            colEtat.setVisible(true);
            colPurchasePrice.setVisible(true);
            btnNewProduct.setDisable(false);
            txtPurchasePrice.setVisible(true);
            txtEtat.setVisible(true);
            textPurchase.setVisible(false);
            textEtat.setVisible(false);
            setEnableMenuItem();
      //  } else {
         /*   colPorcentage.setVisible(false);
            colPurchasePrice.setVisible(false);
            btnNewProduct.setDisable(true);
            txtPurchasePrice.setVisible(false);
            textPurchase.setVisible(true);
            textPorcentage.setVisible(true);
            txtPorcentage.setVisible(false);
            setDisableMenuItem();
        }*/
    }

    private void setDisableMenuItem() {
        contextMenu.getEditButton().setDisable(true);
        contextMenu.getDeleteButton().setDisable(true);
    }

    private void setEnableMenuItem() {
        contextMenu.getEditButton().setDisable(false);
        contextMenu.getDeleteButton().setDisable(false);
    }

    private void closeDialogWithEscapeKey() {
        rootProducts.setOnKeyReleased(ev -> {
            if (ev.getCode().equals(KeyCode.ESCAPE)) {
                closeDialogAddProduct();
            }

            if (ev.getCode().equals(KeyCode.ESCAPE)) {
                hideDialogDeleteProduct();
            }

            if (ev.getCode().equals(KeyCode.ESCAPE)) {
                tblProducts.setDisable(false);
                rootProducts.setEffect(null);
                AlertsBuilder.close();
            }

        });
    }

    private void closeDialogWithTextFields() {
        txtBarCode.setOnKeyReleased(ev -> {
            if (ev.getCode().equals(KeyCode.ESCAPE)) {
                closeDialogAddProduct();
            }
        });

        txtNameProduct.setOnKeyReleased(ev -> {
            if (ev.getCode().equals(KeyCode.ESCAPE)) {
                closeDialogAddProduct();
            }
        });

        txtPurchasePrice.setOnKeyReleased(ev -> {
            if (ev.getCode().equals(KeyCode.ESCAPE)) {
                closeDialogAddProduct();
            }
        });


        txtDescriptionProduct.setOnKeyReleased(ev -> {
            if (ev.getCode().equals(KeyCode.ESCAPE)) {
                closeDialogAddProduct();
            }
        });

        txtEtat.setOnKeyReleased(ev -> {
            if (ev.getCode().equals(KeyCode.ESCAPE)) {
                closeDialogAddProduct();
                tblProducts.setDisable(false);
            }
        });

        txtQuantite.setOnKeyReleased(ev -> {
            if (ev.getCode().equals(KeyCode.ESCAPE)) {
                closeDialogAddProduct();
                tblProducts.setDisable(false);
            }
        });
    }

    public static void closeStage() {
        if (stage != null) {
            stage.hide();
        }
    }

    private void deleteUserDeleteKey() {
        rootProducts.setOnKeyPressed(ev -> {
            if (ev.getCode().equals(KeyCode.DELETE)) {
                if (tblProducts.isDisable()) {
                    return;
                }

                if (tblProducts.getSelectionModel().getSelectedItems().isEmpty()) {
                    AlertsBuilder.create(AlertType.ERROR, stckProducts, rootProducts, tblProducts, Constants.MESSAGE_NO_RECORD_SELECTED);
                    return;
                }

                deleteProducts();
            }
        });
    }

    @FXML
    private void filterNameProduct() {
        String filterName = txtSearchProduct.getText().trim();
        if (filterName.isEmpty()) {
            tblProducts.setItems(listProducts);
        } else {
            filterProducts.clear();
            for (Produit p : listProducts) {
                if (p.getProductName().toLowerCase().contains(filterName.toLowerCase())) {
                    filterProducts.add(p);
                }
            }
            tblProducts.setItems(filterProducts);
        }
    }

    @FXML
    private void filterCodeBar() {
        String filterCodeBar = txtSearchBarCode.getText().trim();
        if (filterCodeBar.isEmpty()) {
            tblProducts.setItems(listProducts);
        } else {
            filterProducts.clear();
            for (Produit p : listProducts) {
                if (p.getBarcode().toLowerCase().contains(filterCodeBar.toLowerCase())) {
                    filterProducts.add(p);
                }
            }
            tblProducts.setItems(filterProducts);
        }
    }


    @FXML
    private void showFileChooser() {
        imageFile = getImageFromFileChooser(getStage());
        if (imageFile != null) {
            Image image = new Image(imageFile.toURI().toString(), 200, 200, true, true);
            imageProduct.setImage(image);
            setInitialDirectory();
        }
    }

    private Stage getStage() {
        return (Stage) btnCancelAddProduct.getScene().getWindow();
    }

    private void setInitialDirectory() {
        Preferences preferences = Preferences.getPreferences();
        preferences.setInitialPathFileChooserProductsController(imageFile.getParent());
        Preferences.writePreferencesToFile(preferences);
    }

    private File getImageFromFileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterImages = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().addAll(extFilterImages);
        fileChooser.setInitialDirectory(getInitialDirectoy());
        fileChooser.setTitle("choissisez");

        File selectedImage = fileChooser.showOpenDialog(stage);
        return selectedImage;
    }

    private File getInitialDirectoy() {
        Preferences preferences = Preferences.getPreferences();
        File initPath = new File(preferences.getInitialPathFileChooserProductsController());
        if (!initPath.exists()) {
            preferences.setInitialPathFileChooserProductsController(System.getProperty("user.home"));
            Preferences.writePreferencesToFile(preferences);
            initPath = new File(preferences.getInitialPathFileChooserProductsController());
        }
        return initPath;
    }

    private void expandImage(int id, String title) {
        paneContainer.hoverProperty().addListener((o, oldV, newV) -> {
            if (newV) {
                colorAdjust.setBrightness(0.25);
                imageProduct.setEffect(colorAdjust);
            } else {
                imageProduct.setEffect(null);
            }
        });

        paneContainer.setOnMouseClicked(ev -> {
            final Image image = DatabaseHelper.getProductImage(id);
            final ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(550);

            final BorderPane boderPane = new BorderPane(imageView);
            boderPane.setStyle("-fx-background-color: white");
            boderPane.setCenter(imageView);

            final ScrollPane root = new ScrollPane(boderPane);
            root.setStyle("-fx-background-color: white");
            root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            //root.getStylesheets().add(Constants.LIGHT_THEME);
            root.getStyleClass().add("scroll-bar");

            root.setFitToHeight(true);
            root.setFitToWidth(true);

            stage.getIcons().add(new Image(Constants.STAGE_ICON));
            stage.setScene(new Scene(root, 550, 555));
            stage.setTitle(title);
            stage.show();
        });
    }


}
