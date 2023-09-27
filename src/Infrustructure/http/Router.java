package Infrustructure.http;

import Infrustructure.endpoint.*;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private static Map<String, IControllerFactory> controllerFactories;

    public static void registerControllers() {
        controllerFactories = Register.registerControllerFactories();
    }

    public static Response route(Request request) {
        IController controller = controllerFactories.get(request.path).createInstance();

        Response response = null;
        if (controller.isSupport(request.path, request.method) && controller != null) {
            response = controller.service(request);
        } else {
            response = new Response();
            response.code = 400;
            response.headers = new HashMap<>();
            response.body = "No implementation for path or method";
        }
        return response;
    }
}
