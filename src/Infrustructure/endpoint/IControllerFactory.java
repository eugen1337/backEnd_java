package Infrustructure.endpoint;

public interface IControllerFactory {
    IController createInstance();

    String getPath();
}
