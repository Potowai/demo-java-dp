package fr.sdv.strategy;

public class StrategyFactory {

    public static Strategy getInstance(TypeTri type) {
        switch (type) {
            case BULLE:
                return new BulleSort();
            case INSERTION:
                return new InsertionSort();
            case SELECTION:
                return new SelectionSort();
            default:
                throw new IllegalArgumentException("Type de tri inconnu : " + type);
        }
    }
}
