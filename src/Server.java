<<<<<<< Updated upstream
import Infrustructure.MyHttpHandler;
=======
/*
>>>>>>> Stashed changes
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) throws IOException {
<<<<<<< Updated upstream
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);

        server.createContext("/", new MyHttpHandler());

        server.setExecutor(null);
        server.start();
=======
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            String rootContext = "/";
>>>>>>> Stashed changes

        System.out.println("Server started!");
    }
}



*/

public class Server {
    public static void main(String[] args) {

    }
}