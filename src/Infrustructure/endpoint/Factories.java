package Infrustructure.endpoint;

import Infrustructure.endpoint.controllers.CalcControllerFactory;
import Infrustructure.endpoint.controllers.LoginControllerFactory;
import Infrustructure.endpoint.controllers.TaskControllerFactory;

public class Factories {
    public static IControllerFactory[] getFactories() {
        return new IControllerFactory[] {
                new CalcControllerFactory(),
                new LoginControllerFactory(),
                new TaskControllerFactory()
        };
    }
}
