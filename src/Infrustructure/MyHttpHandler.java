package Infrustructure;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParam = null;

        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParam = handleGetRequest(httpExchange);
            System.out.println("Get query");
            System.out.println(requestParam);
        } else if ("POST".equals(httpExchange)) {
            requestParam = handlePostRequest(httpExchange);
            System.out.println("Post query");
        }
        handleResponse(httpExchange, requestParam);
    }

    private void handleResponse(HttpExchange httpExchange, String requestParams)  throws  IOException {
        //entry point
        HttpController controller = new HttpController();
        String path = httpExchange.getRequestURI().toString().split("\\?")[0];
        System.out.println(path);
        String htmlResponse = controller.execute(path, requestParams);
        System.out.print("htmlResponse = ");
        System.out.println(htmlResponse);

        httpExchange.sendResponseHeaders(htmlResponse == null ? 404 : 200 , htmlResponse.length());

        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
        System.out.println("htmlResponse is sent");
    }

    private String handlePostRequest(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI()
                .getQuery();
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.
                getRequestURI()
                .getQuery();
    }
}
