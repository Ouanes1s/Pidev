/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stock.services;

import java.util.List;

/**
 *
 * @author maidi
 */
public interface ServiceStock <T>{
    public void ajouter(T p);
    public void supprimer(int Id_Produit); //=(T p)
    public void modifier(T p);
    public List<T> getAll();
    public T getOneById(int Id_Produit);
}
