package Infrustructure;

import app.CalcService;
import app.RouteService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParam = "Default value";

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

    private void handleResponse(HttpExchange httpExchange, String requestParams)  throws  IOException {
        OutputStream outputStream = httpExchange.getResponseBody();

        //query params to map
        Map<String, String> params = null;

        if (requestParams != null) {
            params = new HashMap<String, String>();
            for (String param : requestParams.split("&")) {
                String[] entry = param.split("=");
                params.put(entry[0], entry[1]);
            }
            System.out.println(params);
        }
        else System.out.println("No params");

        RouteService routeServ = new RouteService();
        String htmlResponse = routeServ.route("calc", params);

        httpExchange.sendResponseHeaders(htmlResponse == null ? 404 : 200 , htmlResponse.length());

        System.out.println("resp is " + htmlResponse);
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();

        System.out.println("Resp is sent");
    }
}
