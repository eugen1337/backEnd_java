package Infrustructure.endpoint.controllers;

import Infrustructure.Builder;
import Infrustructure.endpoint.IController;
import Infrustructure.endpoint.Method;
import Infrustructure.endpoint.Request;
import Infrustructure.endpoint.Response;
import app.IApp;

import java.util.HashMap;

public class LoginController implements IController {
    @Override
    public boolean isSupport(String path, Method method) {
        //need
        return true;
    }

    @Override
    public Response service(Request request) {
        IApp app = Builder.buildApp();
        String resp;

        switch (request.method) {
            case POST:
                resp = app.register(request.body);
                break;
            default:
                resp = app.login(request.params);
                break;
        }

        Response response  = new Response();
        response.code = 200;
        response.headers = new HashMap<>();
        response.headers.put("Content-Type","text/plain; charset=UTF-8");
        response.body = resp;

        return response;
    }
}
