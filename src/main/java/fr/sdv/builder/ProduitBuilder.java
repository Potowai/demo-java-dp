package fr.sdv.builder;

public class ProduitBuilder {

    private String nom;
    private double prix;
    private Categorie categorie;

    public ProduitBuilder setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public ProduitBuilder setPrix(double prix) {
        if (prix < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        this.prix = prix;
        return this;
    }

    public ProduitBuilder setCategorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public Produit build() {
        if (nom == null || nom.isBlank()) {
            throw new IllegalStateException("Le nom est obligatoire");
        }
        if (categorie == null) {
            throw new IllegalStateException("La catégorie est obligatoire");
        }
        return new Produit(nom, prix, categorie);
    }
}
