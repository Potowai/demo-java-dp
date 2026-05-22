package fr.sdv.factory;

import org.junit.Test;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class FactoryTest {

    private static final Logger LOG = Logger.getLogger(FactoryTest.class.getName());

    // =============================================
    // CAS PASSANTS
    // =============================================

    @Test
    public void testCreateIngredient() {
        LOG.info("-- CAS PASSANT : creation d'un Ingredient --");

        Element e = ElementFactory.create(TypeElement.INGREDIENT, "Sucre", 10.0, "g");

        assertTrue("Devrait etre une instance de Ingredient", e instanceof Ingredient);
        assertEquals("Sucre", e.getNom());
        assertEquals(10.0, e.getValeur(), 0.0001);
        assertEquals("g", e.getUnite());

        LOG.info("OK Type=INGREDIENT, nom='" + e.getNom()
                + "', valeur=" + e.getValeur()
                + ", unite='" + e.getUnite() + "'");
    }

    @Test
    public void testCreateAllergene() {
        LOG.info("-- CAS PASSANT : creation d'un Allergene --");

        Element e = ElementFactory.create(TypeElement.ALLERGENE, "Gluten", 5.0, "mg");

        assertTrue("Devrait etre une instance de Allergene", e instanceof Allergene);
        assertEquals("Gluten", e.getNom());
        assertEquals(5.0, e.getValeur(), 0.0001);
        assertEquals("mg", e.getUnite());

        LOG.info("OK Type=ALLERGENE, nom='" + e.getNom()
                + "', valeur=" + e.getValeur()
                + ", unite='" + e.getUnite() + "'");
    }

    @Test
    public void testCreateAdditif() {
        LOG.info("-- CAS PASSANT : creation d'un Additif --");

        Element e = ElementFactory.create(TypeElement.ADDITIF, "E951", 2.5, "mg");

        assertTrue("Devrait etre une instance de Additif", e instanceof Additif);
        assertEquals("E951", e.getNom());
        assertEquals(2.5, e.getValeur(), 0.0001);
        assertEquals("mg", e.getUnite());

        LOG.info("OK Type=ADDITIF, nom='" + e.getNom()
                + "', valeur=" + e.getValeur()
                + ", unite='" + e.getUnite() + "'");
    }

    @Test
    public void testCreateValeurZero() {
        LOG.info("-- CAS PASSANT : creation avec valeur=0 (valeur limite) --");

        Element e = ElementFactory.create(TypeElement.INGREDIENT, "Eau", 0.0, "L");

        assertTrue(e instanceof Ingredient);
        assertEquals("Eau", e.getNom());
        assertEquals(0.0, e.getValeur(), 0.0001);
        assertEquals("L", e.getUnite());

        LOG.info("OK Valeur a 0 acceptee");
    }

    // =============================================
    // CAS NON PASSANTS
    // =============================================

    @Test(expected = IllegalArgumentException.class)
    public void testTypeNull() {
        LOG.info("-- CAS NON PASSANT : type null -> IllegalArgumentException --");

        ElementFactory.create(null, "Test", 1.0, "g");
    }
}
