import java.sql.*;

public class FRequirements {
    private Connection establishConnection() throws SQLException {
        // Create user name, password, url
        String url = System.getenv("HP_JDBC_URL");
        String user = System.getenv("HP_JDBC_USER");
        String pass = System.getenv("HP_JDBC_PW");
        // establish to DB
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void FR5() {
        try {
            Connection conn = establishConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("TODO");
    }

    public void FR4() {
        try {
            Connection conn = establishConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("TODO");

    }

    public void FR3() {
        try {
            Connection conn = establishConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("TODO");
    }

    public void FR2() {
        try {
            Connection conn = establishConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("TODO");
    }

    public void FR1() {
        try {
            Connection conn = establishConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("TODO");
    }
}
