package back.Infrustructure.storage;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

public class PostgreDB implements IDataBase {
    private final String url = "jdbc:postgresql://localhost:5432/test";
    private final String db_login = "postgres";
    private final String db_password = "1-5qwerty";

    private DataSource ds;

    private Connection getConnectionJDBC() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, db_login, db_password);
            return conn;
        } catch (Exception e) {
            throw new Exception("Exception in genConn()" + e.getMessage());
        }
    }

    private Connection getConnectionPool() throws Exception {
        try {
            InitialContext initialContext = new InitialContext();
            ds = (DataSource) initialContext.lookup("jdbc/local_pg_test");
            Connection conn = ds.getConnection();
            return conn;
        } catch (Exception e) {
            throw new Exception("Exception in genConn()" + e.getMessage());
        }
    }

    @Override
    public boolean isRegistredUser(String login, String password) {
        try {
            Connection conn = getConnectionPool();
            try {
                PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
                st.setString(1, login);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();

                boolean isRegistredUser = rs.next();

                rs.close();
                st.close();
                return isRegistredUser;
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error while JDBC operating: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addUser(String login, String password) {
        try {
            Connection conn = getConnectionJDBC();
            try {
                PreparedStatement st = conn.prepareStatement("INSERT INTO users (login, password) VALUES (?, ?)");
                st.setString(1, login);
                st.setString(2, password);
                st.executeUpdate();
                st.close();
                return true;
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error while JDBC operating: " + e.getMessage());
        }
        return false;
    }

    @Override
    public String[] getTasks(String login) {
        try {
            Connection conn = getConnectionJDBC();
            try {
                PreparedStatement st = conn
                        .prepareStatement(
                                "SELECT * FROM tasks WHERE login = ?");

                st.setString(1, login);

                ResultSet rs = st.executeQuery();

                String resultString = "{\"docs\": [";

                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    Integer value1 = rs.getInt("value1");
                    Integer value2 = rs.getInt("value2");
                    String result = rs.getString("result");
                    String status = rs.getString("status");
                    resultString += "{\"id\":" + id + ", \"value1\":" + value1 + ", \"value2\":" + value2
                            + ", \"result\":"
                            + result + ", \"status\":\"" + status + "\"},";

                }
                resultString = StringUtils.chop(resultString);
                resultString += "]}";

                System.out.println(resultString);
                st.close();

                String[] res_Strings = new String[1];
                res_Strings[0] = resultString;

                return res_Strings;
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error while JDBC operating: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int createTask(String login, int value1, int value2) {
        try {
            Connection conn = getConnectionJDBC();
            try {
                int id = new BigDecimal(new Date().getTime() / 100 % 1000000000).intValueExact();

                PreparedStatement st = conn
                        .prepareStatement(
                                "INSERT INTO tasks (id, value1, value2, result, status, login) VALUES (?, ?, ?, ?, ?, ?)");

                st.setInt(1, id);
                st.setInt(2, value1);
                st.setInt(3, value2);
                st.setString(4, "null");
                st.setString(5, "not started");
                st.setString(6, login);

                st.executeUpdate();
                st.close();
                return id;
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error while createTask: " + e.getMessage());
        }
        return 404;
    }

    @Override
    public boolean modifyTask(int id, int result, String status) {
        try {
            Connection conn = getConnectionJDBC();
            try {
                PreparedStatement st = conn
                        .prepareStatement(
                                "UPDATE tasks SET result = ?, status = ? WHERE id = ?");

                st.setInt(1, result);
                st.setString(2, status);
                st.setInt(3, id);

                st.executeUpdate();
                st.close();

                return true;
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error while JDBC operating: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteTask(int id) {
        try {
            Connection conn = getConnectionJDBC();
            try {
                PreparedStatement st = conn
                        .prepareStatement(
                                "DELETE FROM tasks WHERE id = ?");

                st.setInt(1, id);

                st.executeUpdate();
                st.close();
                return true;
            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error while JDBC operating: " + e.getMessage());
        }
        return false;
    }

}
