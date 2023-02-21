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
public interface IService<T> {
    
    public void ajouter(T t);
    public List<T> afficherTous();
    public T rechercherUnParId(int id);    
    public void modifier(T t);
    public void supprimer(int id);

}
