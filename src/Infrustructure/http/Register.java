package Infrustructure.http;

import Infrustructure.endpoint.Factories;
import Infrustructure.endpoint.IController;
import Infrustructure.endpoint.IControllerFactory;

import java.util.HashMap;
import java.util.Map;

public class Register {
    public static Map<String, IControllerFactory> registerControllerFactories() {
        Map<String, IControllerFactory> controllerFactoriesMap = new HashMap<>();
        IControllerFactory[] factories = Factories.getFactories();

        for (IControllerFactory icf : factories) {
            controllerFactoriesMap.put(icf.getPath(), icf);
        }

        return controllerFactoriesMap;
    }
}
