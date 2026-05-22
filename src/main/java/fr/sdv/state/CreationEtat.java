package fr.sdv.state;

public class CreationEtat implements EtatCommande {

    @Override
    public void ajouterProduit(Produit p, Commande commande) {
        commande.addProduit(p);
    }

    @Override
    public void payer(Commande commande) {
        commande.setEtat(new PaiementEtat());
    }

    @Override
    public void livrer(String adresse, Commande commande) {
        throw new IllegalStateException("Impossible de livrer une commande en cours de creation");
    }

    @Override
    public void annuler(Commande commande) {
        commande.setEtat(new AnnuleeEtat());
    }
}
