package de.juliansauer;

import de.juliansauer.coffee.CoffeeScale;
import de.juliansauer.rest.RESTServer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<CoffeeScale> scales;

    public static void main(String[] args) {

        CoffeeScale scale = new CoffeeScale("B12", "localhost", 4223);
        scale.startListening();

        scales = new ArrayList<>();
        scales.add(scale);

        RESTServer restServer = new RESTServer(2222);
        restServer.startRestServer();

    }

    public static List<CoffeeScale> getScales() {
        return scales;
    }

}
