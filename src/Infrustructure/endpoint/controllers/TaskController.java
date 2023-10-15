package Infrustructure.endpoint.controllers;

import Infrustructure.endpoint.Request;
import jakarta.ws.rs.core.Response;

public class TaskController {

    public Response service(Request request) {

/*        IApp app = Builder.buildApp();
        String resp;

        switch (request.method) {
            case POST:
                resp = app.makeTask(request.body);
                break;
            default:
                resp = app.getTasks(request.params.get("login"));
                break;
        }

        Response response = new Response();
        response.code = 200;
        response.headers = new HashMap<>();
        response.headers.put("Content-Type", "text/plain; charset=UTF-8");
        response.body = resp;*/
        String resultJSON = "";
        return Response.ok(resultJSON).build();
    }
}
