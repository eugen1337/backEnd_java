package back.app;

import back.Infrustructure.Task;
import back.Infrustructure.User;
import back.domain.ISum;
import back.domain.SumFactory;

import java.util.Map;

public class App implements IApp, IDataBaseUsing, ITokenManagerUsing {
    private IDataBase db;
    private ITokenManager tm;

    App() {
    }

    @Override
    public boolean validateToken(String token) {
        Map<String, String> tokenInfo = tm.getTokenInfo(token);
        return db.isRegistredUser(tokenInfo.get("username"), tokenInfo.get("passwd"));
    }

    @Override
    public String login(String login, String password) {
        String token = tm.generateToken(login, password);
        System.out.println("APP login, token = " + token);
        if (db.isRegistredUser(login, password))
            return token;
        else
            return null;
    }

    @Override
    public String register(User user) {
        String token = tm.generateToken(user.getLogin(), user.getPassword());
        System.out.println("APP register, token = " + token);
        if (db.addUser(user.getLogin(), user.getPassword()))
            return token;
        else
            return null;
    }

    @Override
    public String calc(int id, int a, int b) {
        ISum model = SumFactory.createCalculator();
        int res = model.sum(a, b);

        db.modifyTask(id, res, "ready");

        return String.valueOf(res);
    }

    @Override
    public String getTasks(String login) {
        StringBuilder result = new StringBuilder();
        String[] tasks = db.getTasks(login);
        System.out.println("db tasks is error or tasks is empty");
        if (tasks == null)
            return "EMPTY";
        for (String task : tasks) {
            result.append(task);
        }
        return result.toString();
    }

    @Override
    public int createTask(Task task) {
        int id = db.createTask(task.login, task.value1, task.value2);
        return id;
    }

    public boolean deleteTask(int id) {
        return db.deleteTask(id);
    }

    @Override
    public void useDB(IDataBase db) {
        this.db = db;
    }

    @Override
    public void useTM(ITokenManager tm) {
        this.tm = tm;
    }

    @Override
    public Map<String, String> getUserInfo(String token) {
        return tm.getTokenInfo(token);
    }
}
