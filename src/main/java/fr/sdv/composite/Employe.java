package fr.sdv.composite;

public class Employe implements IElement {

    private String nom;
    private double salaire;

    public Employe(String nom, double salaire) {
        this.nom = nom;
        this.salaire = salaire;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public double calculerSalaire() {
        return salaire;
    }
}
