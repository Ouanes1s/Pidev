/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.user.entities;

import java.util.Objects;

/**
 *
 * @author chebi
 */
public class Agent extends User {
    private String Salaire ;
      private String type_A;
    
    private String  date_contract;

    public Agent(String Salaire, String type_E, String date_contract) {
        this.Salaire = Salaire;
        this.type_A = type_E;
        this.date_contract = date_contract;
    }

    public Agent(String Salaire, String type_E, String date_contract, int id_user, String nom_user, String prenom_user, String cin_user, String email_user, String mdp_user) {
        super(id_user, nom_user, prenom_user, cin_user, email_user, mdp_user);
        this.Salaire = Salaire;
        this.type_A = type_E;
        this.date_contract = date_contract;
    }

    public Agent(String Salaire, String type_E, String date_contract, String nom_user, String prenom_user, String cin_user, String email_user, String mdp_user) {
        super(nom_user, prenom_user, cin_user, email_user, mdp_user);
        this.Salaire = Salaire;
        this.type_A = type_E;
        this.date_contract = date_contract;
    }

    public Agent() {
    }

    public String getSalaire() {
        return Salaire;
    }

    public String getType_A() {
        return type_A;
    }

    public String getDate_contract() {
        return date_contract;
    }

    public void setSalaire(String Salaire) {
        this.Salaire = Salaire;
    }

    public void setType_A(String type_E) {
        this.type_A = type_E;
    }

    public void setDate_contract(String date_contract) {
        this.date_contract = date_contract;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.Salaire);
        hash = 17 * hash + Objects.hashCode(this.type_A);
        hash = 17 * hash + Objects.hashCode(this.date_contract);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agent other = (Agent) obj;
        if (!Objects.equals(this.Salaire, other.Salaire)) {
            return false;
        }
        if (!Objects.equals(this.type_A, other.type_A)) {
            return false;
        }
        if (!Objects.equals(this.date_contract, other.date_contract)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agent{" + "Salaire=" + Salaire + ", type_A=" + type_A + ", date_contract=" + date_contract + '}';
    }
    

    
    
    
}
