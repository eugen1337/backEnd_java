package app;

import app.services.IService;
import app.services.RegisterService;

import java.util.Map;

public class RouteService {
    private Map<String, IService> serviceMap;
    public RouteService() {
        System.out.println("routeService()");
        serviceMap = RegisterService.registerServices();
    }

    public String route(String path, Map<String, String> params) {
        IService bc = serviceMap.get(path);
        System.out.println(path);
        String response = bc.execute(params);
        return response;
    }
}
