package app.services;

import java.util.HashMap;
import java.util.Map;

public class RegisterService {
    public static Map<String, IService> registerServices() {
        Map<String, IService> serviceMap = new HashMap<>();
        Services servs = new Services();
        for (IService is :  servs.getServices()) {
            serviceMap.put(is.getPath(), is);
        }
        return serviceMap;
    }
}
