/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MARWA
 */
public class ReservationParking {
    private int idReservationParking;
    private int idParking;
    private int nbHours;
    private int idUser;

    public ReservationParking() {
    }

    public ReservationParking(int idParking, int nbHours, int idUser) {
        this.idParking = idParking;
        this.nbHours = nbHours;
        this.idUser = idUser;
    }

    public ReservationParking(int idReservationParking, int idParking, int nbHours, int idUser) {
        this.idReservationParking = idReservationParking;
        this.idParking = idParking;
        this.nbHours = nbHours;
        this.idUser = idUser;
    }

    public int getIdReservationParking() {
        return idReservationParking;
    }

    public void setIdReservationParking(int idReservationParking) {
        this.idReservationParking = idReservationParking;
    }

    public int getIdParking() {
        return idParking;
    }

    public void setIdParking(int idParking) {
        this.idParking = idParking;
    }

    public int getNbHours() {
        return nbHours;
    }

    public void setNbHours(int nbHours) {
        this.nbHours = nbHours;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "ReservationParking{" + "idReservationParking=" + idReservationParking + ", idParking=" + idParking + ", NbHours=" + nbHours + ", idUser=" + idUser + '}';
    }
    
    
}
