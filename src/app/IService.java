package app;

import java.util.Map;

public interface IService {
    public String execute(Map<String, String> params);
    public String getPath();
}
