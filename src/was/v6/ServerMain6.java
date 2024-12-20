package was.v6;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.reflection.ReflectionServlet;
import was.v5.servlet.HomeServlet;

import java.io.IOException;
import java.util.List;

public class ServerMain6 {

    private static final int port = 12345;

    public static void main(String[] args) throws IOException {
        ServletManager servletManager = new ServletManager();
        servletManager.add("/", new HomeServlet());
        servletManager.add("/favicon.ico", new DiscardServlet());

        List<Object> controllers = List.of(new SiteControllerV6(), new SearchControllerV6());
        servletManager.setDefaultServlet(new ReflectionServlet(controllers));

        HttpServer server = new HttpServer(port, servletManager);
        server.start();
    }
}
