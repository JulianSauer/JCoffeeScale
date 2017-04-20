package de.juliansauer.coffee;

/**
 * Defines the weights of different coffee levels.
 */
public enum CoffeeLevel {

    NO_CAN(-Integer.MAX_VALUE, 10),
    EMPTY(NO_CAN.maxWeight, 300),
    FULL(EMPTY.maxWeight, 5000),
    OVERLOADED(FULL.maxWeight, Integer.MAX_VALUE);

    private final int minWeight;
    private final int maxWeight;

    CoffeeLevel(int minWeight, int maxWeight) {
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    protected static CoffeeLevel getLevelFor(int weight) {
        for (CoffeeLevel fillStatus : CoffeeLevel.values()) {
            if (weight > fillStatus.minWeight && weight < fillStatus.maxWeight)
                return fillStatus;
        }
        return NO_CAN;
    }

    protected int getMinWeight() {
        return minWeight;
    }

    protected int getMaxWeight() {
        return maxWeight;
    }

}