package app;

import java.util.Map;

public class LoginController implements IBackController {
    @Override
    public String execute(Map<String, String> params) {
        String response = "";

        for (String key : params.keySet()) {
            response += params.get(key);
        }

        return response;
    }
}
