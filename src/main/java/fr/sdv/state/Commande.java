package fr.sdv.state;

import java.util.ArrayList;
import java.util.List;

public class Commande {

    private EtatCommande etat;
    private List<Produit> produits = new ArrayList<>();
    private String adresseLivraison;

    public Commande() {
        this.etat = new CreationEtat();
    }

    public void setEtat(EtatCommande etat) {
        this.etat = etat;
    }

    public void ajouterProduit(Produit p) {
        etat.ajouterProduit(p, this);
    }

    void addProduit(Produit p) {
        this.produits.add(p);
    }

    public void payer() {
        etat.payer(this);
    }

    void setAdresseLivraison(String adresse) {
        this.adresseLivraison = adresse;
    }

    public void livrer(String adresse) {
        etat.livrer(adresse, this);
    }

    public void annuler() {
        etat.annuler(this);
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public double calculerTotal() {
        return produits.size() * 0.5;
    }
}
