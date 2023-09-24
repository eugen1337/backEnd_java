package app;

import java.util.Map;

public class LoginService implements IService {
    private String path;
    public LoginService() {
        path = "login";
        System.out.println("Login is ready");
    };
    @Override
    public String execute(Map<String, String> params) {
        String response = "";

        for (String key : params.keySet()) {
            response += params.get(key);
        }

        return response;
    }

    @Override
    public String getPath() {
        return path;
    }
}
