package back.app;

import back.Infrustructure.Task;
import back.Infrustructure.User;

import java.util.Map;

public interface IApp {
    String login(String login, String password);

    String register(User user);

    String calc(int id, int a, int b);

    String getTasks(String login);

    int createTask(Task task);

    boolean deleteTask(int id);

    boolean validateToken(String token);

    Map<String, String> getUserInfo(String token);
}
