package edu.stock.services;

import edu.stock.entities.Fournisseur;
import edu.stock.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maidi
 */
public class ServiceFournisseur implements ServiceStock<Fournisseur>{

    Connection cnx=DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Fournisseur f) {
        try {
            String req = "INSERT INTO `fournisseur` (`Nom_Fournisseur`, `Adresse_Fournisseur`, `Téléphone`) VALUES ('" + f.getNom_Fournisseur() + "', '" + f.getAdresse_Fournisseur() + "','" + f.getTéléphone() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("supplier created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int Id_Fournisseur) {
        try {
            String req = "DELETE FROM `fournisseur` WHERE Id_Fournisseur = " + Id_Fournisseur;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("supplier deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Fournisseur f) {
        try {
            String req = "INSERT INTO `fournisseur` (`Nom_Fournisseur`, `Adresse_Fournisseur`, `Téléphone`) VALUES ('" + f.getNom_Fournisseur() + "', '" + f.getAdresse_Fournisseur() + "','" + f.getTéléphone() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Supplier updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Fournisseur> getAll() {
        List<Fournisseur> list = new ArrayList<>();
        try {
            String req = "Select * from fournisseur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Fournisseur f = new Fournisseur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Fournisseur getOneById(int Id_Fournisseur) {
        Fournisseur f = null;
        try {
            String req = "Select * from fournisseur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                f = new Fournisseur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return f;
    }
    
}
