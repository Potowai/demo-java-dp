package fr.sdv.state;

import org.junit.Test;
import static org.junit.Assert.*;

public class CommandeTest {

    @Test
    public void testCreationEtPaiement() {
        Commande cmd = new Commande();
        cmd.ajouterProduit(new Produit("Pomme", 1.5));
        cmd.ajouterProduit(new Produit("Poire", 2.0));
        assertEquals(2, cmd.getProduits().size());
        assertEquals(1.0, cmd.calculerTotal(), 0.0001);

        cmd.payer();
        cmd.livrer("10 rue de Paris");
        assertEquals("10 rue de Paris", cmd.getAdresseLivraison());
    }

    @Test(expected = IllegalStateException.class)
    public void testLivrerSansPaiement() {
        Commande cmd = new Commande();
        cmd.ajouterProduit(new Produit("Pomme", 1.5));
        cmd.livrer("10 rue de Paris");
    }

    @Test(expected = IllegalStateException.class)
    public void testAjoutProduitApresPaiement() {
        Commande cmd = new Commande();
        cmd.ajouterProduit(new Produit("Pomme", 1.5));
        cmd.payer();
        cmd.ajouterProduit(new Produit("Poire", 2.0));
    }

    @Test
    public void testAnnulerDepuisCreation() {
        Commande cmd = new Commande();
        cmd.ajouterProduit(new Produit("Pomme", 1.5));
        cmd.annuler();
    }

    @Test
    public void testAnnulerDepuisPaiement() {
        Commande cmd = new Commande();
        cmd.ajouterProduit(new Produit("Pomme", 1.5));
        cmd.payer();
        cmd.annuler();
    }

    @Test(expected = IllegalStateException.class)
    public void testAnnulerDepuisLivraison() {
        Commande cmd = new Commande();
        cmd.ajouterProduit(new Produit("Pomme", 1.5));
        cmd.payer();
        cmd.livrer("10 rue de Paris");
        cmd.annuler();
    }

    @Test(expected = IllegalStateException.class)
    public void testPayerDeuxFois() {
        Commande cmd = new Commande();
        cmd.ajouterProduit(new Produit("Pomme", 1.5));
        cmd.payer();
        cmd.payer();
    }
}
