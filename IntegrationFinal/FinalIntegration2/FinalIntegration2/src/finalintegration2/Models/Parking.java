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
public class Parking implements Comparable<Parking>{
    private int idParking;
    private String nomParking;
    private String logoParking;
    private int capaciteParking;
    private int takenPParking;
    private float prixParking;
    

    public Parking() {
    }

    public Parking(String nomParking, String logoParking, int capaciteParking, int takenPParking, float prixParking) {
        this.nomParking = nomParking;
        this.logoParking = logoParking;
        this.capaciteParking = capaciteParking;
        this.takenPParking = takenPParking;
        this.prixParking = prixParking;
    }

    public Parking(int idParking, String nomParking, String logoParking, int capaciteParking, int takenPParking, float prixParking) {
        this.idParking = idParking;
        this.nomParking = nomParking;
        this.logoParking = logoParking;
        this.capaciteParking = capaciteParking;
        this.takenPParking = takenPParking;
        this.prixParking = prixParking;
    }

    

    public int getIdParking() {
        return idParking;
    }

    public void setIdParking(int idParking) {
        this.idParking = idParking;
    }

    public String getNomParking() {
        return nomParking;
    }

    public void setNomParking(String nomParking) {
        this.nomParking = nomParking;
    }

    public String getLogoParking() {
        return logoParking;
    }

    public void setLogoParking(String logoParking) {
        this.logoParking = logoParking;
    }

    public int getCapaciteParking() {
        return capaciteParking;
    }

    public void setCapaciteParking(int capaciteParking) {
        this.capaciteParking = capaciteParking;
    }

    public int getTakenPParking() {
        return takenPParking;
    }

    public void setTakenPParking(int takenPParking) {
        this.takenPParking = takenPParking;
    }

    public float getPrixParking() {
        return prixParking;
    }

    public void setPrixParking(float prixParking) {
        this.prixParking = prixParking;
    }

    @Override
    public String toString() {
        return "Parking{" + "idParking=" + idParking + ", nomParking=" + nomParking + ", logoParking=" + logoParking + ", capaciteParking=" + capaciteParking + ", takenPParking=" + takenPParking + ", prixParking=" + prixParking + '}';
    }

    @Override
    public int compareTo(Parking o) {
         return Integer.compare((int)this.prixParking, (int)o.prixParking);
    }

}
