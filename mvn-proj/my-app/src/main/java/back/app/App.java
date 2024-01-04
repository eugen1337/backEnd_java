package back.app;

import back.Infrustructure.Task;
import back.Infrustructure.User;
import back.domain.ISum;
import back.domain.SumFactory;

public class App implements IApp, IDataBaseUsing, ITokenManagerUsing {
    public App() {
        System.out.println("create app");
    }

    public IDataBase db;
    public ITokenManager tm;

    @Override
    public String login(String login, String password) {
        System.out.println(tm == null);
        String token = tm.generateToken(login, password);
        System.out.println(token);
        if (db.isRegistredUser(login, password))
            return token;
        else
            return null;
    }

    @Override
    public String register(User user) {
        String token = tm.generateToken(user.getLogin(), user.getPassword());
        if (db.addUser(user.getLogin(), user.getPassword()))
            return token;
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
        if (tasks == null)
            return null;
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
    public void useDB(IDataBase ldb) {
        db = ldb;
    }

    @Override
    public void useTM(ITokenManager ltm) {
        tm = ltm;
    }
}
