package app;

import java.util.Map;

public interface IApp {
    String login(int variant);
    String calc(Map<String, String> params);
}
