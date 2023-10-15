package app;

import Infrustructure.User;

import java.util.Map;

public interface IApp {
    String login(String login, String password);

    String register(User user);

    String calc(int a, int b);

    String getTasks(String login);

    String makeTask(String body);
}
