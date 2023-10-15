package app;

import Infrustructure.User;
import app.services.CalcService;
import app.services.IService;
import domain.ISum;
import domain.SumFactory;

import java.util.HashMap;
import java.util.Map;

public class App implements IApp, IDataBaseUsing {
    private IDataBase db;
    private Map<String, Task> tasks = new HashMap<>();
    private static int count = 0;
    App() {

    }
    @Override
    public String login(String login, String password) {
        if(db.isRegistredUser(login, password))
            return "ok";
        else
            return null;
    }
    @Override
    public String register(User user) {
        if(db.addUser(user.getLogin(), user.getPassword()))
            return "ok";
        else
            return null;
    }

    @Override
    public String calc(int a, int b) {
        /*IService calcServ = new CalcService();
        return calcServ.execute(a, b);*/
        ISum model = SumFactory.createCalculator();
        return String.valueOf(model.sum(a, b));
    }
    /*@Override
    public int createTask(String username, int value1, int value2)
    {
        return storage.createTask(username, value1, value2);
    }*/

    @Override
    public String getTasks(String login) {
        StringBuilder result = new StringBuilder();
        String[] tasks = db.getTasks(login);
        if (tasks == null ) return null;
        for (String task : tasks) {
            result.append(task);
        }
        System.out.println(result);
        return result.toString();
    }

    @Override
    public String makeTask(String body) {
        body = body.replace('"', ' ');
        body = body.trim();
        System.out.println(body);
        String[] params = body.split("&");
        int id = db.createTask(params[0], Integer.valueOf(params[1]), Integer.valueOf(params[2]));
        System.out.println("Integer.toString(id)");
        System.out.println(id);
        return Integer.toString(id);
    }

    @Override
    public void useDB(IDataBase db) {
        this.db = db;
    }
}
