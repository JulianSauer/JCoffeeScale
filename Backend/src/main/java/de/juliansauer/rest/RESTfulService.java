package de.juliansauer.rest;

import de.juliansauer.Main;
import de.juliansauer.coffee.CoffeeScale;
import org.json.JSONObject;

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

        JSONObject json = new JSONObject();
        for (CoffeeScale scale : Main.getScales())
            json.put(scale.getUID(), scale.getCurrentLevel());

        return json.toString();
    }

    @GET
    @Path("Refill")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCansWaitingForRefill() {

        JSONObject json = new JSONObject();
        for (CoffeeScale scale : Main.getScales())
            if (scale.isWaitingForNewCan())
                json.put(scale.getUID(), scale.getCurrentWeight());

        return json.toString();
    }

}
