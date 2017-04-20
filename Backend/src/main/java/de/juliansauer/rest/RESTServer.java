package de.juliansauer.rest;

import de.juliansauer.Main;
import de.juliansauer.coffee.CoffeeScale;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class RESTServer extends Thread {

    private Server server;

    public RESTServer(int port) {
        ResourceConfig config = new ResourceConfig();
        config.packages("de.juliansauer.rest");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");
    }

    @Override
    public void run() {

        try {
            server.start();
            server.join();
        } catch (InterruptedException e) {
            System.out.println("RESTServer interrupted.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Stops Jetty and closes CoffeeScale connections.
     */
    @Override
    public void interrupt() {

        super.interrupt();

        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        server.destroy();
        for (CoffeeScale scale : Main.getScales())
            scale.closeConnection();

    }

}
