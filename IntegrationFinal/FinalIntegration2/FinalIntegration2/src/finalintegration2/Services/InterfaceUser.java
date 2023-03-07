/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalintegration2.Services;

import finalintegration2.Models.User;
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
    
    public void majmdp (User U1, String mdp);

    public void supprimerUser (user u);
     public void supprimerUserCin(User u);
    
    public List<user> afficherUserMembre();
    public List<user> afficherUserAgent();
    
    public user getOneByCin(String cin);
    public int VerifCin(String cin);
      public boolean verifierEmailBd(String email);
      public boolean verifiermdp(String mdp);
  public void Authentification(String email , String mdp);
}

