/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.user.test;

import edu.user.entities.Agent;
import edu.user.entities.User;
import edu.user.services.UserCRUD;
import edu.user.utils.ConnectionToDB;

/**
 *
 * @author chebi
 */
public class Test {
    public static void main(String[] args) {
     
        
        User u1=new Agent ("1500", "Agent1", "2Ans", "Ouanes", "Chebil", "1234567", "Mohamedouanes.chebil@esprit.tn" ,"542365");
       // User u2=new User (8,"Khalfaoui","Amine","12345678","Amine.Khalfaoui@esprit.tn","Utilisateur","Jesuisunemotdepasse123");
        User u = new User();
        int i = 0;
        UserCRUD uc = new UserCRUD();
        //i=uc.VerifCin(1234567);
        System.out.println(i);
        uc.ajouterUserAgent(u1);
        //uc.supprimerUserCin(u1);
        //uc.modifierUser(u2);
        //System.out.println(uc.afficherUser());*//

        u=uc.getOneByCin("1234567");
        System.out.println(u.toString());
       
        
         
        
        
      
    }
    
}
    

