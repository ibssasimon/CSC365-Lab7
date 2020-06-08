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
    // TODO (ibssasimon): FR5
    public void FR5() {
        try {
            Connection conn = establishConnection();

            // build sql query using StringBuilder here
            StringBuilder sb = new StringBuilder();



            // Starting transaction step -- con.setAutoCommit(false)
            conn.setAutoCommit(false);

            // execute SQL
            try (Statement st = conn.createStatement()) {
                ResultSet res = st.executeQuery(sb.toString());
                while (res.next()) {
                    // analyze results
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // conn.commit()
            conn.commit();
            // check results using ResultSet, NOTE: iterate using rs.next()

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO(louiseibuna): FR4
    public void FR4() {
        try {
            Connection conn = establishConnection();

            // build Statement using StringBuilder here

            // con.setAutoCommit(false)

            // execute SQL
            // conn.commit()


            // check results using ResultSet, NOTE: iterate using rs.next()
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // TODO(louiseibuna): FR3
    public void FR3() {
        try {
            Connection conn = establishConnection();

            // build Statement using StringBuilder here

            // con.setAutoCommit(false)

            // execute SQL
            // conn.commit()


            // check results using ResultSet, NOTE: iterate using rs.next()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO(ibssasimon): FR2
    public void FR2() {
        try {
            Connection conn = establishConnection();

            // build Statement using StringBuilder here
            StringBuilder sb = new StringBuilder();

            // Start transaction step -- con.setAutoCommit(false)
            conn.setAutoCommit(false);

            // execute SQL
            try (Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(sb.toString());

                while (rs.next()) {
                    // analyze results
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // conn.commit()


            // check results using ResultSet, NOTE: iterate using rs.next()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO(louiseibuna): FR1
    public void FR1() {
        try {
            Connection conn = establishConnection();

            // build Statement using StringBuilder here

            // con.setAutoCommit(false)

            // execute SQL
            // conn.commit()


            // check results using ResultSet, NOTE: iterate using rs.next()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
