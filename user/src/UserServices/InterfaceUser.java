/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserServices;

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
    public void ajouterUser (user u);
    public void modifierUser (user u);
    public void supprimerUser (user u);
    public List<user> afficherUser();
    public user getOneById(int id);
  
}

