import Infrustructure.http.MyHttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

//test url = http://localhost:8001/calc?a=30&b=40

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            String rootContext = "/";
            server.createContext(rootContext, new MyHttpHandler());

            server.setExecutor(null);
            server.start();
        } catch (Exception ex) {
            System.out.println("Error while starting server");
        }

        System.out.println("Server started!");
    }
}



