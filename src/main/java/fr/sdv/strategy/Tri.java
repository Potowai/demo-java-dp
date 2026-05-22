package fr.sdv.strategy;

public class Tri {

    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void exec(Integer[] arr) {
        if (strategy == null) {
            throw new IllegalStateException("Aucune strategie de tri definie");
        }
        strategy.trier(arr);
    }
}
