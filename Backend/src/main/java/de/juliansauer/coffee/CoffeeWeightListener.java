package de.juliansauer.coffee;

import com.tinkerforge.BrickletLoadCell;

/**
 * Resets the counter if a non-empty can is put on the scale.
 */
public class CoffeeWeightListener implements BrickletLoadCell.WeightListener {

    private CoffeeWeightReachedListener reachedListener;

    private CoffeeLevel currentLevel;

    protected CoffeeWeightListener(CoffeeWeightReachedListener reachedListener) {
        this.reachedListener = reachedListener;
        currentLevel = CoffeeLevel.NO_CAN;
    }

    public void weight(int weight) {
        currentLevel = CoffeeLevel.getLevelFor(weight);
        if (currentLevel != CoffeeLevel.EMPTY)
            reachedListener.resetCounter();

        if (currentLevel == CoffeeLevel.OVERLOADED)
            System.out.println("The scale is only build for less than 5kg. Please be careful!");
    }

    public CoffeeLevel getCurrentLevel() {
        return currentLevel;
    }

}
