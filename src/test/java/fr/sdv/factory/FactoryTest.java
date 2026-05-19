package fr.sdv.factory;

import org.junit.Test;
import static org.junit.Assert.*;

public class FactoryTest {

    @Test
    public void testCreateIngredient() {
        Element e = ElementFactory.create(TypeElement.INGREDIENT, "Sucre", 10.0, "g");
        assertTrue(e instanceof Ingredient);
        assertEquals("Sucre", e.getNom());
        assertEquals(10.0, e.getValeur(), 0.0001);
        assertEquals("g", e.getUnite());
    }

    @Test
    public void testCreateAllergene() {
        Element e = ElementFactory.create(TypeElement.ALLERGENE, "Gluten", 5.0, "mg");
        assertTrue(e instanceof Allergene);
        assertEquals("Gluten", e.getNom());
        assertEquals(5.0, e.getValeur(), 0.0001);
        assertEquals("mg", e.getUnite());
    }

    @Test
    public void testCreateAdditif() {
        Element e = ElementFactory.create(TypeElement.ADDITIF, "E951", 2.5, "mg");
        assertTrue(e instanceof Additif);
        assertEquals("E951", e.getNom());
        assertEquals(2.5, e.getValeur(), 0.0001);
        assertEquals("mg", e.getUnite());
    }
}
