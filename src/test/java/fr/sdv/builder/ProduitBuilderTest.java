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
        LOG.info("-- CAS PASSANT : construction nominale d'un produit --");

        Produit produit = new ProduitBuilder()
                .setNom("Pomme")
                .setPrix(1.5)
                .setCategorie(Categorie.ALIMENTAIRE)
                .build();

        assertEquals("Pomme", produit.getNom());
        assertEquals(1.5, produit.getPrix(), 0.0001);
        assertEquals(Categorie.ALIMENTAIRE, produit.getCategorie());

        LOG.info("OK Produit cree : nom='" + produit.getNom()
                + "', prix=" + produit.getPrix()
                + ", categorie=" + produit.getCategorie());
    }

    @Test
    public void testBuilderFluent() {
        LOG.info("-- CAS PASSANT : chainage fluent dans un ordre different --");

        Produit produit = new ProduitBuilder()
                .setNom("Chemise")
                .setCategorie(Categorie.VETEMENT)
                .setPrix(29.99)
                .build();

        assertEquals("Chemise", produit.getNom());
        assertEquals(Categorie.VETEMENT, produit.getCategorie());
        assertEquals(29.99, produit.getPrix(), 0.0001);

        LOG.info("OK Chainage fonctionne quel que soit l'ordre des setters");
    }

    @Test
    public void testBuilderPrixZero() {
        LOG.info("-- CAS PASSANT : prix a 0 (valeur limite) --");

        Produit produit = new ProduitBuilder()
                .setNom("Echantillon gratuit")
                .setPrix(0.0)
                .setCategorie(Categorie.LOISIR)
                .build();

        assertEquals(0.0, produit.getPrix(), 0.0001);

        LOG.info("OK Prix a 0 accepte : " + produit.getPrix());
    }

    // =============================================
    // CAS NON PASSANTS
    // =============================================

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderPrixNegatif() {
        LOG.info("-- CAS NON PASSANT : prix negatif -> IllegalArgumentException --");

        new ProduitBuilder()
                .setNom("Test")
                .setPrix(-5.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderNomObligatoire() {
        LOG.info("-- CAS NON PASSANT : nom null -> IllegalStateException --");

        new ProduitBuilder()
                .setPrix(2.0)
                .setCategorie(Categorie.LOISIR)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderNomVide() {
        LOG.info("-- CAS NON PASSANT : nom vide (blank) -> IllegalStateException --");

        new ProduitBuilder()
                .setNom("   ")
                .setPrix(2.0)
                .setCategorie(Categorie.LOISIR)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderCategorieObligatoire() {
        LOG.info("-- CAS NON PASSANT : categorie null -> IllegalStateException --");

        new ProduitBuilder()
                .setNom("Test")
                .setPrix(2.0)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderRienConfigure() {
        LOG.info("-- CAS NON PASSANT : aucun setter appele -> IllegalStateException --");

        new ProduitBuilder().build();
    }
}
