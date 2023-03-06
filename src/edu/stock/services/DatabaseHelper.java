
package edu.stock.services;
import edu.stock.entites.*;
import edu.stock.resources.*;
import edu.stock.utils.DatabaseConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHelper {

   Connection cnx;

    public static boolean insertNewProduct(Produit products, ObservableList<Produit> listProducts) {
        try {
            String sql = "INSERT INTO produit (barcode, productName, purchasePrice, etat, quantite, descriptionProduct, imageProduct) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, products.getBarcode());
            preparedStatement.setString(2, products.getProductName());
            preparedStatement.setDouble(3, products.getPurchasePrice());
            preparedStatement.setInt(4, products.getEtat());
            preparedStatement.setDouble(5, products.getQuantite());
            preparedStatement.setString(6, products.getDescriptionProduct());
            preparedStatement.setBlob(7, products.getProductImage());
            //preparedStatement.setString(8,null);
            preparedStatement.execute();
            listProducts.add(products);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteProduct(TableView<Produit> tbl, ObservableList<Produit> listProducts) {
        try {
            String sql = "DELETE FROM produit WHERE id = ?";
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, tbl.getSelectionModel().getSelectedItem().getId());
            preparedStatement.execute();
            listProducts.remove(tbl.getSelectionModel().getSelectedIndex());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateProduct(Produit products) {
        try {
            String sql = "UPDATE produit SET barcode = ?, productName = ?, purchasePrice = ?, "
                    + "etat = ?, quantite = ?, descriptionProduct = ?, imageProduct = ? "
                    + "WHERE  id = ?";
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, products.getBarcode());
            preparedStatement.setString(2, products.getProductName());
            preparedStatement.setDouble(3, products.getPurchasePrice());
            preparedStatement.setInt(4, products.getEtat());
            preparedStatement.setDouble(5, products.getQuantite());
            preparedStatement.setString(6, products.getDescriptionProduct());
            preparedStatement.setBlob(7, products.getProductImage());
            preparedStatement.setInt(8, products.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateProductIfFileIsNull(Produit products) {
        try {
            String sql = "UPDATE produit SET barcode = ?, productName = ?, purchasePrice = ?, "
                    + "etat = ?, quantite = ?, descriptionProduct = ? "
                    + "WHERE  id = ?";
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, products.getBarcode());
            preparedStatement.setString(2, products.getProductName());
            preparedStatement.setDouble(3, products.getPurchasePrice());
            preparedStatement.setInt(4, products.getEtat());
            preparedStatement.setDouble(5, products.getQuantite());
            preparedStatement.setString(6, products.getDescriptionProduct());
            preparedStatement.setInt(7, products.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static int checkIfProductExists(String barcode) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM produit WHERE barcode = ?";
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, barcode);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static Image getProductImage(int id) {
        Image image = null;
        try {
            String sql = "SELECT imageProduct FROM produit WHERE id = ?";
            PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                InputStream img = rs.getBinaryStream("imageProduct");
                if (img != null) {
                    image = new Image(img);
                } else {
                    image = new Image(Constants.NO_IMAGE_AVAILABLE);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            image = new Image(Constants.NO_IMAGE_AVAILABLE);
        }
        return image;
    }

    public List<Produit> read() throws SQLException {

        List<Produit> ls = new ArrayList<Produit>();
        Statement st =  cnx.createStatement();
        String req = "select * from produit order by id";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            int id = rs.getInt("id");
            String barcode = rs.getString("barcode");
            String productName = rs.getString("productName");
            double purchasePrice = rs.getDouble("purchasePrice");
            int etat = rs.getInt("etat");
            double quantite = rs.getDouble("quantite");
            String descriptionProduct = rs.getString("descriptionProduct");
            InputStream image = null;

            //   LocalDate born = rs.getDate("born").toLocalDate();
            Produit p = new Produit(id,barcode,productName,purchasePrice,etat,quantite,descriptionProduct,image);
            ls.add(p);
        }


        return ls;

    }


}
