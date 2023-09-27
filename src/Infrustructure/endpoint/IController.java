package Infrustructure.endpoint;

public interface IController {
    boolean isSupport(String path, Method method);
    Response service(Request request);
}
