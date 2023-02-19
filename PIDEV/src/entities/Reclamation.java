/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author MARWA
 */
public class Reclamation {
    private int idReclamation;
    private Date dateOccReclamation;
    private String categorieReclamation;
    private String typeReclamation;
    private String messageReclamation;
    private boolean etatReclamation; 
    private int importanceReclamation;
    private String reponseReclamation;
    private int userId;

    public Reclamation() {
    }
    
   
    public Reclamation(Date dateOccReclamation, String categorieReclamation, String typeReclamation, String messageReclamation, boolean etatReclamation, int importanceReclamation, String reponseReclamation, int userId) {
        this.dateOccReclamation = dateOccReclamation;
        this.categorieReclamation = categorieReclamation;
        this.typeReclamation = typeReclamation;
        this.messageReclamation = messageReclamation;
        this.etatReclamation = etatReclamation;
        this.importanceReclamation = importanceReclamation;
        this.reponseReclamation = reponseReclamation;
        this.userId = userId;
    }

    public Reclamation(int idReclamation, Date dateOccReclamation, String categorieReclamation, String typeReclamation, String messageReclamation, boolean etatReclamation, int importanceReclamation, String reponseReclamation, int userId) {
        this.idReclamation = idReclamation;
        this.dateOccReclamation = dateOccReclamation;
        this.categorieReclamation = categorieReclamation;
        this.typeReclamation = typeReclamation;
        this.messageReclamation = messageReclamation;
        this.etatReclamation = etatReclamation;
        this.importanceReclamation = importanceReclamation;
        this.reponseReclamation = reponseReclamation;
        this.userId = userId;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public Date getDateOccReclamation() {
        return dateOccReclamation;
    }

    public void setDateOccReclamation(Date dateOccReclamation) {
        this.dateOccReclamation = dateOccReclamation;
    }

    public String getCategorieReclamation() {
        return categorieReclamation;
    }

    public void setCategorieReclamation(String categorieReclamation) {
        this.categorieReclamation = categorieReclamation;
    }

    public String getTypeReclamation() {
        return typeReclamation;
    }

    public void setTypeReclamation(String typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public String getMessageReclamation() {
        return messageReclamation;
    }

    public void setMessageReclamation(String messageReclamation) {
        this.messageReclamation = messageReclamation;
    }

    public boolean isEtatReclamation() {
        return etatReclamation;
    }

    public void setEtatReclamation(boolean etatReclamation) {
        this.etatReclamation = etatReclamation;
    }

    public int getImportanceReclamation() {
        return importanceReclamation;
    }

    public void setImportanceReclamation(int importanceReclamation) {
        this.importanceReclamation = importanceReclamation;
    }

    public String getReponseReclamation() {
        return reponseReclamation;
    }

    public void setReponseReclamation(String reponseReclamation) {
        this.reponseReclamation = reponseReclamation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", dateOccReclamation=" + dateOccReclamation + ", categorieReclamation=" + categorieReclamation + ", typeReclamation=" + typeReclamation + ", messageReclamation=" + messageReclamation + ", etatReclamation=" + etatReclamation + ", importanceReclamation=" + importanceReclamation + ", reponseReclamation=" + reponseReclamation + ", userId=" + userId + '}';
    }

    
}
