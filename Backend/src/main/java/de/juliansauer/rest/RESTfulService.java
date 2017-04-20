package de.juliansauer.rest;

import de.juliansauer.Main;
import de.juliansauer.coffee.CoffeeScale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("jcs")
public class RESTfulService {

    @GET
    @Path("CoffeeLevel")
    @Produces(MediaType.TEXT_PLAIN)
    public String getLevel() {
        String levels = "Levels: ";
        for (CoffeeScale scale : Main.getScales())
            levels += scale.getCurrentLevel().name() + " ";
        return levels;
    }

}
