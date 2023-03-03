/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.reservation.services;
import java.util.List;

/**
 *
 * @author Amine Khalfaoui
 */
 
 public interface IServices <T>{
    public void ajouter (T r);
    public void supprimer (int id );
    public void modifier (T r);
    public List<T> Afficher();
    public T getOneById(int id );
    public int VerifTitre(String titre) ;
    public int VerifOffr(String code, String nom, String prenom) ;
    public List<T> trierOffreParDate();
    
}
