package Infrustructure;

import app.IApp;
import app.UserData;

import java.util.HashMap;
import java.util.Map;

public class HttpController {
    public String execute(String path, String requestParams) {
        /*//query params to map
        Map<String, String> params = null;

        if (requestParams != null) {
            params = new HashMap<>();
            for (String param : requestParams.split("&")) {
                String[] entry = param.split("=");
                params.put(entry[0], entry[1]);
            }
            System.out.println(params);
        }*/
        UserData userData = new UserData();

        String[] params = requestParams.split("&");
        userData.setA(params[0]);

        else System.out.println("No params");

        //App without DB
        IApp app = Builder.build(null);

        return app.route(path, params);
    }

}
