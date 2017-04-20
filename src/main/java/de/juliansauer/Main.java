package de.juliansauer;

import com.tinkerforge.AlreadyConnectedException;
import com.tinkerforge.NotConnectedException;
import com.tinkerforge.TimeoutException;
import de.juliansauer.coffee.CoffeeScale;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, AlreadyConnectedException, TimeoutException, NotConnectedException, InterruptedException {

        CoffeeScale scale = new CoffeeScale("B12", "localhost", 4223);
        scale.startListening();

        System.out.println("Press enter to exit");
        System.in.read();
        scale.closeConnection();

    }

}
