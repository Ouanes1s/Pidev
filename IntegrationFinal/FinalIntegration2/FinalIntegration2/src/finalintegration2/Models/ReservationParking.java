/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintegration2.Models;

/**
 *
 * @author MarwaTriaa
 */
public class ReservationParking {
    private int idReservationParking;
    private int idParking;
    private int nbHours;
    private String cin;

    public ReservationParking() {
    }

    public ReservationParking(int idParking, int nbHours, String cin) {
        this.idParking = idParking;
        this.nbHours = nbHours;
        this.cin = cin;
    }

    public ReservationParking(int idReservationParking, int idParking, int nbHours,String cin) {
        this.idReservationParking = idReservationParking;
        this.idParking = idParking;
        this.nbHours = nbHours;
        this.cin = cin;
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


    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "ReservationParking{" + "idReservationParking=" + idReservationParking + ", idParking=" + idParking + ", NbHours=" + nbHours + ", cin=" + cin + '}';
    }
    
    
}
