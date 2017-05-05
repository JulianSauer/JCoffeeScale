package de.juliansauer;

import de.juliansauer.coffee.CoffeeScale;
import de.juliansauer.rest.RESTServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<CoffeeScale> scales;

    public static void main(String[] args) {
        addScales();
        startRestThread();
    }

    public static List<CoffeeScale> getScales() {
        return scales;
    }

    private static void addScales() {
        scales = new ArrayList<>();
        CoffeeScale scale = new CoffeeScale("B12", "localhost", 4223, 5);
        scales.add(scale);
    }

    private static void startRestThread() {
        Thread restThread = new RESTServer(2222);
        restThread.start();

        System.out.println("Press enter to exit");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        restThread.interrupt();
    }

}
