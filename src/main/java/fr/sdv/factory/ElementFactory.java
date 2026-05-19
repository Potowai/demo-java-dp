package fr.sdv.factory;

public class ElementFactory {

    public static Element create(TypeElement type, String nom, double valeur, String unite) {
        switch (type) {
            case INGREDIENT:
                return new Ingredient(nom, valeur, unite);
            case ALLERGENE:
                return new Allergene(nom, valeur, unite);
            case ADDITIF:
                return new Additif(nom, valeur, unite);
            default:
                throw new IllegalArgumentException("Type inconnu : " + type);
        }
    }
}
