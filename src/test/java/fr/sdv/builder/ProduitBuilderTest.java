package fr.sdv.builder;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProduitBuilderTest {

    @Test
    public void testBuilderNominal() {
        Produit produit = new ProduitBuilder()
                .setNom("Pomme")
                .setPrix(1.5)
                .setCategorie(Categorie.ALIMENTAIRE)
                .build();

        assertEquals("Pomme", produit.getNom());
        assertEquals(1.5, produit.getPrix(), 0.0001);
        assertEquals(Categorie.ALIMENTAIRE, produit.getCategorie());
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderNomObligatoire() {
        new ProduitBuilder()
                .setPrix(2.0)
                .setCategorie(Categorie.LOISIR)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void testBuilderCategorieObligatoire() {
        new ProduitBuilder()
                .setNom("Test")
                .setPrix(2.0)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilderPrixNegatif() {
        new ProduitBuilder()
                .setNom("Test")
                .setPrix(-5.0);
    }

    @Test
    public void testBuilderFluent() {
        Produit produit = new ProduitBuilder()
                .setNom("Chemise")
                .setCategorie(Categorie.VETEMENT)
                .setPrix(29.99)
                .build();

        assertEquals("Chemise", produit.getNom());
        assertEquals(Categorie.VETEMENT, produit.getCategorie());
    }
}
