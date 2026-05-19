package fr.sdv.builder;

public class Produit {

    private final String nom;
    private final double prix;
    private final Categorie categorie;

    Produit(String nom, double prix, Categorie categorie) {
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }
}
