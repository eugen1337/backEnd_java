package Infrustructure.endpoint.controllers;

public class LoginControllerFactory{
    public LoginController createInstance() {
        return new LoginController() {};
    }
    public String getPath() {
        return "/login";
    }
}
