package fr.sdv.builder;

import org.junit.Test;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class ProduitBuilderTest {

    private static final Logger LOG = Logger.getLogger(ProduitBuilderTest.class.getName());

    // =============================================
    // CAS PASSANTS
    // =============================================

    @Test
    public void testBuilderNominal() {
        LOG.info("── CAS PASSANT : construction nominale d'un produit ──");

        Produit produit = new ProduitBuilder()
                .setNom("Pomme")
                .setPrix(1.5)
                .setCategorie(Categorie.ALIMENTAIRE)
                .build();

        assertEquals("Pomme", produit.getNom());
        assertEquals(1.5, produit.getPrix(), 0.0001);
        assertEquals(Categorie.ALIMENTAIRE, produit.getCategorie());

        LOG.info("✓ Produit créé : nom='" + produit.getNom()
                + "', prix=" + produit.getPrix()
                + ", catégorie=" + produit.getCategorie());
    }

    @Test
    public void testBuilderFluent() {
        LOG.info("── CAS PASSANT : chaînage fluent dans un ordre différent ──");

        Produit produit = new ProduitBuilder()
                .setNom("Chemise")
                .setCategorie(Categorie.VETEMENT)
                .setPrix(29.99)
                .build();

        assertEquals("Chemise", produit.getNom());
        assertEquals(Categorie.VETEMENT, produit.getCategorie());
        assertEquals(29.99, produit.getPrix(), 0.0001);

        LOG.info("✓ Chaînage fonctionne quel que soit l'ordre des setters");
    }

    @Test
    public void testBuilderPrixZero() {
        LOG.info("── CAS PASSANT : prix à 0 (valeur limite) ──");

        Produit produit = new ProduitBuilder()
                .setNom("Échantillon gratuit")
                .setPrix(0.0)
                .setCategorie(Categorie.LOISIR)
                .build();

        assertEquals(0.0, produit.getPrix(), 0.0001);

        LOG.info("✓ Prix à 0 accepté : " + produit.getPrix());
    }

    // =============================================
    // CAS NON PASSANTS
    // =============================================

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderPrixNegatif() {
        LOG.info("── CAS NON PASSANT : prix négatif → IllegalArgumentException ──");

        new ProduitBuilder()
                .setNom("Test")
                .setPrix(-5.0);

        LOG.info("✓ Exception levée comme attendue");
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderNomObligatoire() {
        LOG.info("── CAS NON PASSANT : nom null → IllegalStateException ──");

        new ProduitBuilder()
                .setPrix(2.0)
                .setCategorie(Categorie.LOISIR)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderNomVide() {
        LOG.info("── CAS NON PASSANT : nom vide (blank) → IllegalStateException ──");

        new ProduitBuilder()
                .setNom("   ")
                .setPrix(2.0)
                .setCategorie(Categorie.LOISIR)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderCategorieObligatoire() {
        LOG.info("── CAS NON PASSANT : catégorie null → IllegalStateException ──");

        new ProduitBuilder()
                .setNom("Test")
                .setPrix(2.0)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderRienConfigure() {
        LOG.info("── CAS NON PASSANT : aucun setter appelé → IllegalStateException ──");

        new ProduitBuilder().build();
    }
}
