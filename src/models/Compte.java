package models;

import java.util.List;

public class Compte {
    public static int c=1;
    private static int rip;
    private Proprietaire proprietaire;
    private double montant;
    private List<String> listOps;
    public Compte() {
        this.rip=c++;
    }

    public Compte(Proprietaire proprietaire, double montant) {
        this.rip = c++;
        this.proprietaire = proprietaire;
        this.montant = montant;
    }

    public Compte(Proprietaire proprietaire) {
        this.rip = c++;
        this.proprietaire = proprietaire;
    }

    public List<String> getListOps() {
        return listOps;
    }

    public void setListOps(List<String> listOps) {
        this.listOps = listOps;
    }

    public int getRip() {
        return rip;
    }

    public void setRip(int rip) {
        this.rip = rip;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "rip=" + rip +
                ", proprietaire=" + proprietaire +
                ", montant=" + montant +
                '}';
    }
}
