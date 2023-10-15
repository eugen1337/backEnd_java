package Infrustructure.endpoint.controllers;

import Infrustructure.endpoint.*;

public class LoginControllerFactory{
    public LoginController createInstance() {
        return new LoginController() {};
    }
    public String getPath() {
        return "/login";
    }
}
