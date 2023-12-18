package app.services;

public class Services {
    public static IService[] getServices() {
        return new IService[] {new CalcService(), new LoginService()};
    }
}
