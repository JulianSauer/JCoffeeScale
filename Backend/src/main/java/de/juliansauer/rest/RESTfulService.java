package de.juliansauer.rest;

import de.juliansauer.Main;
import de.juliansauer.coffee.CoffeeScale;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class RESTfulService {

    @GET
    @Path("levels")
    @Produces(MediaType.TEXT_PLAIN)
    public String getLevel() {

        JSONObject json = new JSONObject();
        for (CoffeeScale scale : Main.getScales())
            json.put(scale.getUID(), scale.getCurrentLevel());

        return json.toString();
    }

    @GET
    @Path("refills")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCansWaitingForRefill() {

        JSONObject json = new JSONObject();
        for (CoffeeScale scale : Main.getScales())
            if (scale.isWaitingForNewCan())
                json.put(scale.getUID(), scale.getCurrentWeight());

        return json.toString();
    }

    @GET
    @Path("scales")
    @Produces(MediaType.TEXT_PLAIN)
    public String getScales() {

        JSONObject json = new JSONObject();
        for (CoffeeScale scale : Main.getScales()) {
            JSONObject[] values = new JSONObject[3];
            values[0] = new JSONObject().put("level", scale.getCurrentLevel());
            values[1] = new JSONObject().put("weight", scale.getCurrentWeight());
            values[2] = new JSONObject().put("refill", scale.isWaitingForNewCan());
            json.put(scale.getUID(), values);
        }

        return json.toString();
    }

}
