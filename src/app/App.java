package app;

import app.services.CalcService;
import app.services.IService;

import java.util.HashMap;
import java.util.Map;

public class App implements IApp, IDataBaseUsing {
    private IDataBase db;
    private Map<String, Task> tasks = new HashMap<>();
    private static int count = 0;
    App() {

    }
    @Override
    public String login(Map<String, String> params) {
        System.out.println("params = ");
        System.out.println(params);
        if(db.isRegistredUser(params.get("login"), params.get("password")))
            return "ok";
        else
            return "null";
    }
    @Override
    public String register(String body) {
        body = body.replace('"', ' ');
        body = body.trim();
        System.out.println(body);
        if(db.addUser(body.split("&")[0], body.split("&")[1]))
            return "ok";
        else
            return "null";
    }

    @Override
    public String calc(Map<String, String> params) {
        IService calcServ = new CalcService();
        return calcServ.execute(params);
    }

    @Override
    public String getTasks(Map<String, String> params) {
        /*for (task : tasks) {

        }
        else*/ return "null";
    }

    @Override
    public String makeTask(String body) {
        body = body.replace('"', ' ');
        body = body.trim();
        System.out.println(body);
        String[] params = body.split("&");
        tasks.put(Integer.toString(count), new Task(params[0], params[1], params[2]));
        count += 1;
        System.out.println("Integer.toString(count)");
        System.out.println(Integer.toString(count));
        return Integer.toString(count);
    }

    @Override
    public void useDB(IDataBase db) {
        this.db = db;
    }
}
