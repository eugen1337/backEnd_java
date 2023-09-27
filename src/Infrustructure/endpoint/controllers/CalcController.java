package Infrustructure.endpoint.controllers;

import Infrustructure.Builder;
import Infrustructure.endpoint.IController;
import Infrustructure.endpoint.Method;
import Infrustructure.endpoint.Request;
import Infrustructure.endpoint.Response;
import app.IApp;

import java.util.HashMap;

public class CalcController implements IController {
    @Override
    public boolean isSupport(String path, Method method) {
        return true;
    }

    @Override
    public Response service(Request request) {

        IApp app = Builder.buildApp();
        String resp = app.calc(request.params);

        Response response  = new Response();
        response.code = 200;
        response.headers = new HashMap<>();
        response.headers.put("Content-Type","text/plain; charset=UTF-8");
        response.body = resp;
        return response;
    }
}
