package Infrustructure.http;

import Infrustructure.endpoint.Method;
import Infrustructure.endpoint.Request;
import Infrustructure.endpoint.Response;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Router.registerControllers();

        OutputStream responseStream = httpExchange.getResponseBody();
        Headers responseHeaders = httpExchange.getResponseHeaders();
        String responseBody = "";

        try {
            InputStream requestBodyStream = httpExchange.getRequestBody();
            String requestBody = new String(requestBodyStream.readAllBytes());
            Headers requestHeaders = httpExchange.getRequestHeaders();
            URI requestURI = httpExchange.getRequestURI();

            Request request = new Request();

            Method method = Method.valueOf(httpExchange.getRequestMethod().toUpperCase());
            request.method = method;
            request.body = requestBody;
            request.path = httpExchange.getRequestURI().getPath();
            String query = requestURI.getQuery();

            if (query != null) {
                request.params = handleParams(query);
            }
            else {
                System.out.println("query = 0");
            }

            request.headers = new HashMap<>();
            if (requestHeaders != null) {
                if (requestHeaders.isEmpty() == false) {
                    for (String key : requestHeaders.keySet()) {
                        request.headers.put(key, requestHeaders.getFirst(key));
                    }
                }
            }

            System.out.println("request =" );
            System.out.println(request);
            Response response = Router.route(request);

            if (response.headers != null && response.headers.isEmpty() != true) {
                    for (String key : response.headers.keySet()) {
                        responseHeaders.set(key, response.headers.get(key));
                    }
            }
            responseBody = response.body;
            httpExchange.sendResponseHeaders(response.code, responseBody.getBytes().length);
        } catch (Exception ex) {
            responseBody = "Fatal error: " + ex.getMessage();
            httpExchange.sendResponseHeaders(500, responseBody.getBytes().length);
        }

        responseStream.write(responseBody.getBytes());
        responseStream.flush();
        responseStream.close();
    }

    private Map<String, String> handleParams(String requestParams) {
        //query params to map
        Map<String, String> params = null;
        params = new HashMap<String, String>();
        for (String param : requestParams.split("&")) {
            String[] entry = param.split("=");
            params.put(entry[0], entry[1]);
        }
        System.out.println(params);

        return params;
    }


    private void handleResponse(HttpExchange httpExchange, String requestParams) throws IOException {
        //entry point
        /*HttpController controller = new HttpController();
        String path = httpExchange.getRequestURI().toString().split("\\?")[0];
        System.out.println(path);
        String response = controller.execute(path, requestParams);
        System.out.print("htmlResponse = ");
        System.out.println(response);

        httpExchange.sendResponseHeaders(response == null ? 404 : 200, response.length());

        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
        System.out.println("htmlResponse is sent");*/
    }

}
