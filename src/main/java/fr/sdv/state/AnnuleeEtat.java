package fr.sdv.state;

public class AnnuleeEtat implements EtatCommande {

    @Override
    public void ajouterProduit(Produit p, Commande commande) {
        throw new IllegalStateException("Impossible d'ajouter un produit a une commande annulee");
    }

    @Override
    public void payer(Commande commande) {
        throw new IllegalStateException("Impossible de payer une commande annulee");
    }

    @Override
    public void livrer(String adresse, Commande commande) {
        throw new IllegalStateException("Impossible de livrer une commande annulee");
    }

    @Override
    public void annuler(Commande commande) {
        throw new IllegalStateException("La commande a deja ete annulee");
    }
}
