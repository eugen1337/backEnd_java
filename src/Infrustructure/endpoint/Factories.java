package Infrustructure.endpoint;

import Infrustructure.endpoint.controllers.CalcControllerFactory;
import Infrustructure.endpoint.controllers.LoginControllerFactory;

public class Factories {
    public static IControllerFactory[] getFactories() {
        return new IControllerFactory[] {
                new CalcControllerFactory(),
                new LoginControllerFactory()
        };
    }
}
