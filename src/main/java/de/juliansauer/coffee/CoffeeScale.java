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

    /**
     * @param uid  uid of the load cell bricklet
     * @param host ip of the scale
     * @param port port of the scale
     */
    public CoffeeScale(String uid, String host, int port) {
        UID = uid;
        HOST = host;
        PORT = port;
        initializeConnection();
        configureLoadCell(1000, EMPTY);
    }

    /**
     * Uses a default value of ticks.
     */
    public void startListening() {
        startListening(5);
    }

    /**
     * Sends a message after a certain number of ticks if the weight is between the configured interval.
     *
     * @param ticks Number of ticks before a message is send
     */
    public void startListening(int ticks) {
        CoffeeWeightReachedListener weightReachedListener = new CoffeeWeightReachedListener(ticks);
        CoffeeWeightListener currentWeightListener = new CoffeeWeightListener(weightReachedListener);
        loadCell.addWeightReachedListener(weightReachedListener);
        loadCell.addWeightListener(currentWeightListener);
    }

    public void closeConnection() {
        System.out.println("Disconnecting " + UID);
        try {
            connection.disconnect();
        } catch (NotConnectedException e) {
            e.printStackTrace();
        }
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

}
