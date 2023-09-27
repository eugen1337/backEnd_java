package Infrustructure.endpoint.controllers;

import Infrustructure.endpoint.IController;
import Infrustructure.endpoint.IControllerFactory;

public class CalcControllerFactory implements IControllerFactory {
    @Override
    public IController createInstance() {
        return new CalcController();
    }

    @Override
    public String getPath() {
        return "/calc";
    }
}
