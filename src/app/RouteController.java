package app;

import java.util.HashMap;
import java.util.Map;

public class RouteController {
    private static Map<String, IBackController> backControllerMap = new HashMap<>();

    public static String route(String path, Map<String, String> params) {
        IBackController bc = backControllerMap.get(path);
        System.out.println(path);
        String response = bc.execute(params);
        return response;
    }

    public static void registerController(String path, IBackController bc) {
        backControllerMap.put(path, bc);
    }
}
