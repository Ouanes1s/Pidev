/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author ProtocolBlood
 */


public class User {
    private int idUser;
    private String prenomUser;
    private String cinUser;
    private String emailUser;
    private String roleUser;
    private String mdpUser;
    private String DateInscri;
    private String nomUser;
    private String salaire;
    private String Type_A;
    private String dateContract;

    public User() {
    }

    public User(String prenomUser, String cinUser, String emailUser, String roleUser, String mdpUser, String DateInscri, String nomUser, String salaire, String Type_A, String dateContract) {
        this.prenomUser = prenomUser;
        this.cinUser = cinUser;
        this.emailUser = emailUser;
        this.roleUser = roleUser;
        this.mdpUser = mdpUser;
        this.DateInscri = DateInscri;
        this.nomUser = nomUser;
        this.salaire = salaire;
        this.Type_A = Type_A;
        this.dateContract = dateContract;
    }

    public User(int idUser, String prenomUser, String cinUser, String emailUser, String roleUser, String mdpUser, String DateInscri, String nomUser, String salaire, String Type_A, String dateContract) {
        this.idUser = idUser;
        this.prenomUser = prenomUser;
        this.cinUser = cinUser;
        this.emailUser = emailUser;
        this.roleUser = roleUser;
        this.mdpUser = mdpUser;
        this.DateInscri = DateInscri;
        this.nomUser = nomUser;
        this.salaire = salaire;
        this.Type_A = Type_A;
        this.dateContract = dateContract;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getCinUser() {
        return cinUser;
    }

    public void setCinUser(String cinUser) {
        this.cinUser = cinUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

    public String getMdpUser() {
        return mdpUser;
    }

    public void setMdpUser(String mdpUser) {
        this.mdpUser = mdpUser;
    }

    public String getDateInscri() {
        return DateInscri;
    }

    public void setDateInscri(String DateInscri) {
        this.DateInscri = DateInscri;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public String getType_A() {
        return Type_A;
    }

    public void setType_A(String Type_A) {
        this.Type_A = Type_A;
    }

    public String getDateContract() {
        return dateContract;
    }

    public void setDateContract(String dateContract) {
        this.dateContract = dateContract;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", prenomUser=" + prenomUser + ", cinUser=" + cinUser + ", emailUser=" + emailUser + ", roleUser=" + roleUser + ", mdpUser=" + mdpUser + ", DateInscri=" + DateInscri + ", nomUser=" + nomUser + ", salaire=" + salaire + ", Type_A=" + Type_A + ", dateContract=" + dateContract + '}';
    }

   
}
