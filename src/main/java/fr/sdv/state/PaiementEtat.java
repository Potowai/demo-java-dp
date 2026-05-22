package fr.sdv.state;

public class PaiementEtat implements EtatCommande {

    @Override
    public void ajouterProduit(Produit p, Commande commande) {
        throw new IllegalStateException("Impossible d'ajouter un produit apres le paiement");
    }

    @Override
    public void payer(Commande commande) {
        throw new IllegalStateException("La commande a deja ete payee");
    }

    @Override
    public void livrer(String adresse, Commande commande) {
        commande.setAdresseLivraison(adresse);
        commande.setEtat(new EnLivraisonEtat());
    }

    @Override
    public void annuler(Commande commande) {
        commande.setEtat(new AnnuleeEtat());
    }
}
