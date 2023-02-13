/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.user.test;

import edu.user.entities.User;
import edu.user.services.UserCRUD;
import edu.user.utils.ConnectionToDB;

/**
 *
 * @author chebi
 */
public class Test {
    public static void main(String[] args) {
     
        
        User u1=new User (5,"Chebil","Ouanes","12345678","mohamedouanes.chebil@esprit.tn","Admin","Jesuisunemotdepasse123");
                User u2=new User (8,"Khalfaoui","Amine","12345678","Amine.Khalfaoui@esprit.tn","Utilisateur","Jesuisunemotdepasse123");
User u = new User();
        UserCRUD uc = new UserCRUD();
        uc.ajouterUser(u1);
        uc.supprimerUser(u1);
        uc.modifierUser(u2);
        System.out.println(uc.afficherUser());
        u=uc.getOneById(6);
        System.out.println(u.toString());
       
        
        
        
        
      
    }
    
}
    

