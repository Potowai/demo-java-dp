package fr.sdv.builder;

import java.util.logging.Logger;

public class ProduitBuilder {

    private static final Logger LOG = Logger.getLogger(ProduitBuilder.class.getName());

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
            LOG.severe("Echec build : nom obligatoire");
            throw new IllegalStateException("Le nom est obligatoire");
        }
        if (categorie == null) {
            LOG.severe("Echec build : categorie obligatoire");
            throw new IllegalStateException("La catégorie est obligatoire");
        }
        Produit produit = new Produit(nom, prix, categorie);
        LOG.info("Produit construit : " + produit.getNom()
                + ", prix=" + produit.getPrix()
                + ", categorie=" + produit.getCategorie());
        return produit;
    }
}
