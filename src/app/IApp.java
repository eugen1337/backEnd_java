package app;

import java.util.Map;

public interface IApp {
    String login(Map<String, String> params);

    String register(String body);

    String calc(Map<String, String> params);

    String getTasks(Map<String, String> params);

    String makeTask(String body);
}
