package app;

public interface IDataBase {
    boolean isRegistredUser(String login, String password);;
    boolean addUser(String login, String password);

    String[] getTasks(String login);

    int createTask(String login, int value1, int value2);

    boolean modifyTask(int id, int result, String status);
}
