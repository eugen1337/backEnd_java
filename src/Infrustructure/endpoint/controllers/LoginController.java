package Infrustructure.endpoint.controllers;

import Infrustructure.endpoint.IController;
import Infrustructure.endpoint.Method;
import Infrustructure.endpoint.Request;
import Infrustructure.endpoint.Response;

public class LoginController implements IController {
    @Override
    public boolean isSupport(String path, Method method) {
        return false;
    }

    @Override
    public Response service(Request request) {
        return null;
    }
}
