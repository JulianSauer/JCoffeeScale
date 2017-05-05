package de.juliansauer.coffee;

import com.tinkerforge.*;

import java.io.IOException;

import static de.juliansauer.coffee.CoffeeLevel.EMPTY;

public class CoffeeScale {

    private final String UID;
    private final String HOST;
    private final int PORT;

    private IPConnection connection;
    private BrickletLoadCell loadCell;

    private CoffeeWeightListener weightListener;
    private CoffeeWeightReachedListener weightReachedListener;

    /**
     * @param uid  uid of the load cell bricklet
     * @param host ip of the scale
     * @param port port of the scale
     */
    public CoffeeScale(String uid, String host, int port, int ticks) {
        UID = uid;
        HOST = host;
        PORT = port;
        initializeConnection();
        configureLoadCell(1000, EMPTY);
        startListening(ticks);
    }

    public void closeConnection() {
        System.out.println("Disconnecting " + UID);
        try {
            connection.disconnect();
        } catch (NotConnectedException e) {
            e.printStackTrace();
        }
    }

    public CoffeeLevel getCurrentLevel() {
        return weightListener.getCurrentLevel();
    }

    public int getCurrentWeight() {
        return weightListener.getCurrentWeight();
    }

    public boolean isWaitingForNewCan() {
        return weightReachedListener.isWaitingForNewCan();
    }

    public String getUID() {
        return UID;
    }

    private void initializeConnection() {
        connection = new IPConnection();
        loadCell = new BrickletLoadCell(UID, connection);

        try {
            connection.connect(HOST, PORT);
        } catch (IOException | AlreadyConnectedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configures the LoadCell of the scale for listening.
     *
     * @param period      Interval in milliseconds in which listeners are called
     * @param coffeeLevel Calls listener when weight is between the values of this level
     */
    private void configureLoadCell(int period, CoffeeLevel coffeeLevel) {
        try {
            loadCell.setDebouncePeriod(period);
            loadCell.setWeightCallbackThreshold('i', coffeeLevel.getMinWeight(), coffeeLevel.getMaxWeight());
            loadCell.setWeightCallbackPeriod(period);
        } catch (TimeoutException | NotConnectedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a message after a certain number of ticks if the weight is between the configured interval.
     *
     * @param ticks Number of ticks before a message is send
     */
    private void startListening(int ticks) {
        weightReachedListener = new CoffeeWeightReachedListener(ticks);
        weightListener = new CoffeeWeightListener(weightReachedListener);
        loadCell.addWeightReachedListener(weightReachedListener);
        loadCell.addWeightListener(weightListener);
    }

}
