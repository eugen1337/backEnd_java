package Infrustructure.endpoint.controllers;

import Infrustructure.endpoint.*;

public class LoginControllerFactory implements IControllerFactory {
    @Override
    public IController createInstance() {
        return new LoginController() {};
    }
    @Override
    public String getPath() {
        return "/login";
    }
}
