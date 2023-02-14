package edu.stock.services;

import edu.stock.entities.Produit;
import edu.stock.utils.DataSource;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author maidi
 */
public class ServiceProduit implements ServiceStock<Produit>{

    Connection cnx=DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Produit p) {
        try {
            String req ="INSERT INTO `produit`(`Code_Article`, `Nom_Article`, `Catégorie_Article`, `Quantité_Article`, `Prix_Vente`, `Etat_Article`) "
                + "VALUES ('" + p.getCode_Article() + "','" + p.getNom_Article() + "','" + p.getCatégorie_Article() + "','" + p.getQuantité_Article() + "'"
                + ",'" + p.getPrix_Vente() + "','" + p.getEtat_Article() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Product created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        
    }

    @Override
    public void supprimer(int Id_Produit) {
        try {
            String req = "DELETE FROM `produit` WHERE Id_Produit = " + Id_Produit;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Product deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Produit p) {
        try {
            String req="UPDATE `produit` SET `Code_Article`='" + p.getCode_Article() + "',`Nom_Article`='" + p.getNom_Article() + "',`Catégorie_Article`='" + p.getCatégorie_Article() + "',`Quantité_Article`='" + p.getQuantité_Article() + "',`Prix_Vente`='" + p.getPrix_Vente() + "',`Etat_Article`='" + p.getEtat_Article() + "' "
                    + "WHERE `produit`.`Id_Produit` = " + p.getId_Produit();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Product updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Produit p = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Produit getOneById(int Id_Produit) {
        Produit p = null;
        try {
            String req = "Select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7));                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }
    
    
}
