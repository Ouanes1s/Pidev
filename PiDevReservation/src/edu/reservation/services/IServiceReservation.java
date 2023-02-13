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
 
 public interface IServiceReservation <T>{
    public void ajouterReservation(T r);
    public void supprimerReservation(int id_res);
    public void modifierReservation(T r);
    public List<T> getAll();
    public T getOneById(int id_res);
    
}
