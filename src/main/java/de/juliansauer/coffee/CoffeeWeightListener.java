package de.juliansauer.coffee;

import com.tinkerforge.BrickletLoadCell;

/**
 * Resets the counter if a non-empty can is put on the scale.
 */
public class CoffeeWeightListener implements BrickletLoadCell.WeightListener {

    private CoffeeWeightReachedListener reachedListener;

    protected CoffeeWeightListener(CoffeeWeightReachedListener reachedListener) {
        this.reachedListener = reachedListener;
    }

    public void weight(int weight) {
        CoffeeLevel currentLevel = CoffeeLevel.getLevelFor(weight);
        if (currentLevel != CoffeeLevel.EMPTY)
            reachedListener.resetCounter();

        if (currentLevel == CoffeeLevel.OVERLOADED)
            System.out.println("The scale is only build for less than 5kg. Please be careful!");
    }

}
