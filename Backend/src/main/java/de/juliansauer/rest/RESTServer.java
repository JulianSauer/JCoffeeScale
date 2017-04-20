package de.juliansauer.rest;

import de.juliansauer.Main;
import de.juliansauer.coffee.CoffeeScale;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class RESTServer {

    private Server server;

    public RESTServer(int port) {
        ResourceConfig config = new ResourceConfig();
        config.packages("de.juliansauer.rest");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");
    }

    public void startRestServer() {

        try {
            server.start();
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
            for (CoffeeScale scale : Main.getScales())
                scale.closeConnection();
        }

    }

}
