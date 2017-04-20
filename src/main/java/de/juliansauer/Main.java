package de.juliansauer;

import com.tinkerforge.*;
import de.juliansauer.coffee.CoffeeWeightListener;
import de.juliansauer.coffee.CoffeeWeightReachedListener;

import java.io.IOException;

import static de.juliansauer.coffee.CoffeeLevel.EMPTY;

public class Main {

    private static final String HOST = "localhost";
    private static final int PORT = 4223;
    private static final String UID = "B12";

    public static void main(String[] args) throws IOException, AlreadyConnectedException, TimeoutException, NotConnectedException, InterruptedException {

        IPConnection connection = new IPConnection();
        BrickletLoadCell loadCell = new BrickletLoadCell(UID, connection);

        connection.connect(HOST, PORT);

        int period = 1000; // Calls listeners every 1s
        loadCell.setDebouncePeriod(period);
        loadCell.setWeightCallbackThreshold('i', EMPTY.getMinWeight(), EMPTY.getMaxWeight()); // Calls weightReachedListener when weight is in between these values
        loadCell.setWeightCallbackPeriod(period);

        CoffeeWeightReachedListener weightReachedListener = new CoffeeWeightReachedListener(5);
        CoffeeWeightListener currentWeightListener = new CoffeeWeightListener(weightReachedListener);
        loadCell.addWeightReachedListener(weightReachedListener);
        loadCell.addWeightListener(currentWeightListener);

        System.out.println("Press enter to exit");
        System.in.read();
        connection.disconnect();

    }

}
