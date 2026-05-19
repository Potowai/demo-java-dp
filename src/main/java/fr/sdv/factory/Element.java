package fr.sdv.factory;

public abstract class Element {

    protected String nom;
    protected double valeur;
    protected String unite;

    public Element(String nom, double valeur, String unite) {
        this.nom = nom;
        this.valeur = valeur;
        this.unite = unite;
    }

    public String getNom() {
        return nom;
    }

    public double getValeur() {
        return valeur;
    }

    public String getUnite() {
        return unite;
    }
}
