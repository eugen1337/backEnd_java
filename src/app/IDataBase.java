package app;

public interface IDataBase {
    boolean isRegistredUser(String login, String password);;
    boolean addUser(String login, String password);
}
