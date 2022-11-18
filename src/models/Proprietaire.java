package models;

public class Proprietaire {
    private int cin;
    private String nom;
    private String addresse;

    public Proprietaire(int cin, String nom, String addresse) {
        this.cin = cin;
        this.nom = nom;
        this.addresse = addresse;
    }

    public Proprietaire() {
    }

    public Proprietaire(String nom) {
        this.nom = nom;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    @Override
    public String toString() {
        return "Proprietaire{" +
                "cin=" + cin +
                ", nom='" + nom + '\'' +
                ", addresse='" + addresse + '\'' +
                '}';
    }
}
