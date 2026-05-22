package fr.sdv.state;

public interface EtatCommande {

    void ajouterProduit(Produit p, Commande commande);

    void payer(Commande commande);

    void livrer(String adresse, Commande commande);

    void annuler(Commande commande);
}
