package de.juliansauer.coffee;

import com.tinkerforge.BrickletLoadCell;

/**
 * Sends a message if an empty can is on the scale for a few ticks.
 */
public class CoffeeWeightReachedListener implements BrickletLoadCell.WeightReachedListener {

    private int emptyCounter;
    private final int emptyCounterMax;

    private boolean waitingForNewCan;

    /**
     * @param ticks Defines how many cycles the listener waits before sending a notification that the can is empty.
     */
    protected CoffeeWeightReachedListener(int ticks) {
        this.emptyCounterMax = ticks;
        resetCounter();
    }

    public void weightReached(int weight) {

        if (!waitingForNewCan)
            emptyCounter--;
        if (emptyCounter <= 0) {
            System.out.println("THE CAN IS EMPTY :(");
            resetCounter(true);
        }

    }

    protected void resetCounter() {
        emptyCounter = emptyCounterMax;
        waitingForNewCan = false;
    }

    /**
     * Resets the counter. Prevents multiple notifications by stopping the counter until the empty can is removed.
     *
     * @param waitingForNewCan Set to true after one notification was send
     */
    private void resetCounter(boolean waitingForNewCan) {
        emptyCounter = emptyCounterMax;
        this.waitingForNewCan = waitingForNewCan;
    }

}
