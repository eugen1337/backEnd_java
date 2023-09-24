package app;

public class Services {
    public IService[] getServices() {
        return new IService[] {new CalcService(), new LoginService()};
    }
}
