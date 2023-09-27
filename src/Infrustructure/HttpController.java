package Infrustructure;

import app.IApp;
import app.ITestService;
import app.RouteService;

import java.util.HashMap;
import java.util.Map;

public class HttpController {
    public String execute(String path, String requestParams) {
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

        ITestService testService = Builder.buildTestService();

        return testService.calc(params);
    }

}
