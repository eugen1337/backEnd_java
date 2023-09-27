package app.services;

import app.IService;

import java.util.HashMap;
import java.util.Map;

public class RegisterService {
    public static Map<String, IService> registerServices() {
        Map<String, IService> serviceMap = new HashMap<>();
        Services serves = new Services();
        for (IService is :  serves.getInstanseServices()) {
            serviceMap.put(is.getPath(), is);
        }
        return serviceMap;
    }
}
