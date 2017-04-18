package de.juliansauer;

import com.tinkerforge.BrickletLoadCell;

public class CoffeeWeightReachedListener implements BrickletLoadCell.WeightReachedListener {

    private int emptyCounter;
    private final int emptyCounterMax;

    private boolean waitingForNewCan;

    /**
     * @param emptyCounterMax Defines how many cycles the listener waits before sending a notification that the can is
     *                        empty.
     */
    public CoffeeWeightReachedListener(int emptyCounterMax) {
        this.emptyCounterMax = emptyCounterMax;
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

    public void resetCounter() {
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
