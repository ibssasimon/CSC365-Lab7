import java.sql.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

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
        int kids;
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
        kids = scan.nextInt();
        System.out.println("Number of adults?: ");
        adults = scan.nextInt();
        String rate = "NULL";
        scan.close();


        /*
        * QUESTIONS:
        * 1. Do I have to generate my own CODE(11) in order to insert into reservations?
        * 2. How do I efficiently query for bedType, RoomCode, without writing a TON of try/catch Statements?
        * 3. Bit confused on structure of Result Set. Is this all strings concatenated, or is iterating over a list and producing a single element?
        * */
        // select statement to see if there are any current reservations in that roomcode
        try {
            Connection conn = establishConnection();

            // build Statement using StringBuilder here
            StringBuilder sb = new StringBuilder();
            sb.append("select Room from lab7_reservations WHERE Room =");
            sb.append(roomCode);
            sb.append(" and (CheckIn >= ");
            sb.append(begin);
            sb.append(" and CheckOut <= ");
            sb.append(end);
            sb.append(");");

            ArrayList<String> allBookedRooms = new ArrayList<String>();
            // execute SQL
            try (Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(sb.toString());
                // build unique CODE(11)
                int CODE = generateCode();
                try (Statement randStat = conn.createStatement()) {
                    ResultSet codes = randStat.executeQuery("SELECT CODE from lab7_reservations;");
                    while(codes.next()) {
                        String code = codes.getString("CODE");
                        if (code.contains(Character.toString(CODE))) {
                            CODE = generateCode();
                        }
                    }

                } catch(SQLException e) {
                    e.printStackTrace();
                }

                // empty set, no rooms at all are occupied in that date frame
                if(!rs.next()) {
                    try (Statement st1 = conn.createStatement()) {

                        StringBuilder sb1 = r2InsertIntoTable(CODE, roomCode, begin, end, rate, last, adults, kids);
                        st1.execute(sb1.toString());
                        updateUserSuccess(first, last, roomCode, "bedType", begin, end, adults, kids);
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    while (rs.next()) {
                        // non empty set, check to see if roomCode exists in table. If not exist, add res
                        // analyze results
                        String room = rs.getString("Room");
                        allBookedRooms.add(room);
                        if(room.equals(roomCode)) {
                            updateUserFailure();
                        }
                    }
                    if(!allBookedRooms.contains(roomCode)) {
                        StringBuilder sb1 = r2InsertIntoTable(CODE, roomCode, begin, end, rate, last, adults, kids);
                        try (Statement st1 = conn.createStatement()) {
                            st1.executeQuery(sb1.toString());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
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

    private int generateCode() {
        Random r = new Random();
        int CODE = r.nextInt(99999);
        return CODE;
    }
    private void updateUserSuccess(String first, String last, String RoomCode, String bedType, String begin, String end, int adults, int kids) {
        System.out.println("Succcessfully added reservation with following info: ");
        System.out.println("First Name: " + first);
        System.out.println("LastName: " + last);
        System.out.println("RoomCode: " + RoomCode);
        System.out.println("Bed Type: " + bedType);
        System.out.println("Begin Date: " + begin);
        System.out.println("End Date: " + end);
        System.out.println("Number of Adults: " + adults);
        System.out.println("Number of Kids: " + kids);
    }
    private void updateUserFailure() {
        System.out.println("Could not add reservation (room occupied). Please try another set of options.");
    }

    private StringBuilder r2InsertIntoTable(int CODE, String roomCode, String begin, String end, String rate, String last, int adults, int kids) {
        StringBuilder sb1 = new StringBuilder("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, Lastname, Adults, Kids) VALUES ");
        sb1.append("(");
        sb1.append(CODE);
        sb1.append(",");
        sb1.append(roomCode);
        sb1.append(",");
        sb1.append(begin);
        sb1.append(",");
        sb1.append(end);
        sb1.append(",");
        sb1.append(rate);
        sb1.append(",");
        sb1.append(last);
        sb1.append(",");
        sb1.append(adults);
        sb1.append(",");
        sb1.append(kids);
        sb1.append(");");
        return sb1;
    }
}
