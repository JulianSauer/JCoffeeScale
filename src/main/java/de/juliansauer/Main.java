package de.juliansauer;

import de.juliansauer.coffee.CoffeeScale;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        CoffeeScale scale = new CoffeeScale("B12", "localhost", 4223);
        scale.startListening();

        System.out.println("Press enter to exit");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scale.closeConnection();

    }

}
