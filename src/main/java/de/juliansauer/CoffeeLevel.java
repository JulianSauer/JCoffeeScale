package de.juliansauer;

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

    public static CoffeeLevel getLevelFor(int weight) {
        for (CoffeeLevel fillStatus : CoffeeLevel.values()) {
            if (weight > fillStatus.minWeight && weight < fillStatus.maxWeight)
                return fillStatus;
        }
        return NO_CAN;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

}