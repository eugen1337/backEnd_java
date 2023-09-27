package app.services;

public class Services {
    public IService[] getServices() {
        return new IService[] {new CalcService(), new LoginService()};
    }
}
