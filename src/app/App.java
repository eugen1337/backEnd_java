package app;

import app.services.RegisterService;

import java.util.Map;

public class App implements IApp {
    private IDataBase db;
    private Map<String, IService> serviceMap;
    public App(){
        serviceMap = RegisterService.registerServices();
    };

    public App(IDataBase db) {
        setStorage(db);
        serviceMap = RegisterService.registerServices();
    }

    public String route(String path, UserData params) {
        IService bc = serviceMap.get(path);
        System.out.println(path);
        String response = bc.execute(params);
        return response;
    }

    public String execute(String path, DTO values) {
        return router.route("/calc", values);
    }

    @Override
    public void setStorage(IDataBase db) {
        this.db = db;
    }

    /*
    @Override
    public boolean login(Map<String, String> values) {
        return router.route("/login", values) == "ok";
    }

    @Override
    public String calc(Map<String, String> values) {
        return router.route("/calc", values);
    }
*/

}
