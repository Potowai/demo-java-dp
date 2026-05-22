package fr.sdv.state;

public class EnLivraisonEtat implements EtatCommande {

    @Override
    public void ajouterProduit(Produit p, Commande commande) {
        throw new IllegalStateException("Impossible d'ajouter un produit pendant la livraison");
    }

    @Override
    public void payer(Commande commande) {
        throw new IllegalStateException("La commande a deja ete payee");
    }

    @Override
    public void livrer(String adresse, Commande commande) {
        throw new IllegalStateException("La commande est deja en cours de livraison");
    }

    @Override
    public void annuler(Commande commande) {
        throw new IllegalStateException("Impossible d'annuler, la commande est deja en cours de livraison");
    }
}
