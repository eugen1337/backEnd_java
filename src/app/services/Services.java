package app.services;

import app.IService;
import app.services.CalcService;
import app.services.LoginService;

public class Services {
    public IService[] getInstanseServices() {
        return new IService[] {new CalcService(), new LoginService()};
    }
}
