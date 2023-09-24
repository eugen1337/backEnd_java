import Infrustructure.MyHttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);

        server.createContext("/", new MyHttpHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server started!");
    }
}



