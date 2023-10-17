package app;

import Infrustructure.Task;
import Infrustructure.User;
import domain.ISum;
import domain.SumFactory;

import java.util.HashMap;
import java.util.Map;

public class App implements IApp, IDataBaseUsing {
    private IDataBase db;
/*    private Map<String, Task> tasks = new HashMap<>();*/
    App() {}
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
        ISum model = SumFactory.createCalculator();
        return String.valueOf(model.sum(a, b));
    }

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
    public int createTask(Task task) {
        int id = db.createTask(task.login, task.value1, task.value2);
        System.out.println(id);
        return id;
    }

    public boolean deleteTask(int id) {
        return db.deleteTask(id);
    }

    @Override
    public void useDB(IDataBase db) {
        this.db = db;
    }
}
