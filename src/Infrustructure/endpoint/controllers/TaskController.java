package Infrustructure.endpoint.controllers;

import Infrustructure.Builder;
import Infrustructure.endpoint.IController;
import Infrustructure.endpoint.Method;
import Infrustructure.endpoint.Request;
import Infrustructure.endpoint.Response;
import app.IApp;

import java.util.HashMap;

public class TaskController implements IController {
    @Override
    public boolean isSupport(String path, Method method) {
        boolean flag = false;

        if (path.equals("/tasks")) {
            if (method == Method.GET || method == Method.POST ) {
                flag = true;
            }
        }
        System.out.println(method);

        return flag;
    }

    @Override
    public Response service(Request request) {

        IApp app = Builder.buildApp();
        String resp;

        switch (request.method) {
            case POST:
                resp = app.makeTask(request.body);
                break;
            default:
                resp = app.getTasks(request.params);
                break;
        }

        Response response = new Response();
        response.code = 200;
        response.headers = new HashMap<>();
        response.headers.put("Content-Type", "text/plain; charset=UTF-8");
        response.body = resp;

        return response;
    }
}
