/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.user.services;

import edu.user.entities.User;
import java.util.List;

/**
 *
 * @author chebi
 */


    /**
     *
     * @param <user>
     */
    public interface InterfaceUser <user> {
    public void ajouterUserAgent(User u);
    public void ajouterUserMembre(User u);
    public void ajouterUserAdmin(User U1);
    
    public void modifierUserAdmin (User u);
    public void modifierUserAgent(User u);
    public void modifierUserMembre(User u);
    

    public void supprimerUser (user u);
    
    public List<user> afficherUserMembre();
    public List<user> afficherUserAgent();
    
    public user getOneByCin(String cin);
    public int VerifCin(String cin);
      public boolean verifierEmailBd(String email);
  
}

