package fr.sdv.factory;

import java.util.logging.Logger;

public class ElementFactory {

    private static final Logger LOG = Logger.getLogger(ElementFactory.class.getName());

    public static Element create(TypeElement type, String nom, double valeur, String unite) {
        if (type == null) {
            LOG.severe("Echec creation : type null");
            throw new IllegalArgumentException("Le type d'élément est obligatoire");
        }
        LOG.info("Creation element : type=" + type + ", nom=" + nom + ", valeur=" + valeur + ", unite=" + unite);
        Element element;
        switch (type) {
            case INGREDIENT:
                element = new Ingredient(nom, valeur, unite);
                break;
            case ALLERGENE:
                element = new Allergene(nom, valeur, unite);
                break;
            case ADDITIF:
                element = new Additif(nom, valeur, unite);
                break;
            default:
                LOG.severe("Echec creation : type inconnu " + type);
                throw new IllegalArgumentException("Type inconnu : " + type);
        }
        LOG.info("Element cree : " + element.getClass().getSimpleName()
                + " nom=" + element.getNom());
        return element;
    }
}
