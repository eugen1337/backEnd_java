package app;

public class App implements IApp {
    private IDataBase db;
    private RouteService router;

    public App() {
        router = new RouteService();
    }

    public App(IDataBase db) {
        router = new RouteService();
        setStorage(db);
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

    public String execute(String path, DTO values) {
        return router.route("/calc", values);
    }

    @Override
    public void setStorage(IDataBase db) {
        this.db = db;
    }

}
