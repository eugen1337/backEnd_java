package Infrustructure.endpoint.controllers;

import Infrustructure.endpoint.IController;
import Infrustructure.endpoint.IControllerFactory;

public class TaskControllerFactory implements IControllerFactory {
    @Override
    public IController createInstance() {
        return new TaskController();
    }

    @Override
    public String getPath() {
        return "/tasks";
    }
}
