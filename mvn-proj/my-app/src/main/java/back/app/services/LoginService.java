package back.app.services;

import java.util.Map;

public class LoginService implements IService {
    private String path;
    public LoginService() {
        path = "/login";
        System.out.println("Login is ready");
    };
    @Override
    public String execute(Map<String, String> params) {
        String response = "";

        for (String key : params.keySet()) {
            response += params.get(key);
        }
        System.out.println("login + pass = ");
        System.out.println(response);

        response = "ok";
        return response;
    }

    @Override
    public String getPath() {
        return path;
    }
}
