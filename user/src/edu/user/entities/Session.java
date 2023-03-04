/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.user.entities;

/**
 *
 * @author chebi
 */
public class Session {
    private static String nom;
    private static String cin;

    public static void initialize(String n, String c) {
        nom = n;
        cin = c;
    }

    public static String getNom() {
        return nom;
    }

    public static String getCin() {
        return cin;
    }
}
