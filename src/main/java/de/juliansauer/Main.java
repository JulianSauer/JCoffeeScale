package de.juliansauer;

import com.tinkerforge.*;

import java.io.IOException;

public class Main {

    private static final String HOST = "localhost";
    private static final int PORT = 4223;
    private static final String UID = "B12";

    public static void main(String[] args) throws IOException, AlreadyConnectedException, TimeoutException, NotConnectedException, InterruptedException {

        IPConnection connection = new IPConnection();
        BrickletLoadCell loadCell = new BrickletLoadCell(UID, connection);

        connection.connect(HOST, PORT);

        int weight = loadCell.getWeight();
        System.out.println("Weight: " + weight + " g");

        connection.disconnect();

    }

}
