package app;

import java.util.Map;

public interface ITestService {
    String login(int variant);
    String calc(Map<String, String> params);
    //String tasks();
}
