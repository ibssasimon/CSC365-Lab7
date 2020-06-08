import java.sql.*;
import java.util.Scanner;

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



            // execute SQL
            try (Statement st = conn.createStatement()) {
                ResultSet res = st.executeQuery(sb.toString());
                while (res.next()) {
                    // analyze results
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


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
        // Gather user input for FR2
        String first;
        String last;
        String roomCode;
        String begin;
        String end;
        int children;
        int adults;
        System.out.println("Please fill out the information below to complete your reservation request.");
        Scanner scan = new Scanner(System.in);
        System.out.println("First Name?: ");
        first = scan.nextLine();
        System.out.println("Last Name?: ");
        last = scan.nextLine();
        System.out.println("Room Code?: ");
        roomCode = scan.nextLine();
        System.out.println("Begin date? (YYYY-MM-DD): ");
        begin = scan.nextLine();
        System.out.println("End date? (YYYY-MM-DD): ");
        end = scan.nextLine();
        System.out.println("Number of children?: ");
        children = scan.nextInt();
        System.out.println("Number of adults?: ");
        adults = scan.nextInt();

        scan.close();

        // select statement to see if there are any current reservations in that roomcode
        try {
            Connection conn = establishConnection();

            // build Statement using StringBuilder here
            StringBuilder sb = new StringBuilder();
            sb.append("select * from lab7_reservations WHERE Room =");
            sb.append(roomCode);


            // execute SQL
            try (Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(sb.toString());

                while (rs.next()) {
                    // analyze results
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

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
