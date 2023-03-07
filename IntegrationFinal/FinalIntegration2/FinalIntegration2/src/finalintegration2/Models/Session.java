/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.Models;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author chebi
 */
public class Session {
    private static String nom;
    private static String prenom;
    private static String cin;
    private static String role;
    private static String typeA;
    private static int id ;
   
   

  
    public static void initialize(String n, String c,String r,String t,String p) {
        nom = n;
        cin = c;
        role=r;
        typeA=t;
        prenom=p;
        
    }
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Session other = (Session) obj;
       
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        if (!Objects.equals(this.typeA, other.typeA)) {
            return false;
        }
        return true;
    }

    public static String getNom() {
        return nom;
    }

    public static String getCin() {
        return cin;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        Session.role = role;
    }

    public static String getTypeA() {
        return typeA;
    }

    public static void setTypeA(String typeA) {
        Session.typeA = typeA;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        Session.prenom = prenom;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Session.id = id;
    }

   
    
}
