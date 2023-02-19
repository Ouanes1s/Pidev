
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MARWA
 */
public class MyDB {

    String url = "jdbc:mysql://localhost:3306/pidevmetax";
    String username = "root";
    String password = "";

    Connection cnx;
    
  
    private static MyDB instance;

    private MyDB() {
        try {
            cnx = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

    
    
    
    
}
