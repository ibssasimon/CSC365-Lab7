import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

            // build Statement using StringBuilder here

            // con.setAutoCommit(false)

            // execute SQL

            // conn.commit()
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
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter reservation code: ");
            String rsvpCode = reader.nextLine();

            if(rsvpCode == null || rsvpCode.length() == 0) {
                System.out.println("RSVP code required! Please try again.");
                return;
            }
            //Insert information to fields
            System.out.println("Fill out new information for the following fields. If you wish to not make a change on a field, hit Enter.");
            
            System.out.print("First Name: ");
            String firstName = reader.nextLine();

            System.out.print("Last Name: ");
            String lastName = reader.nextLine();

            System.out.print("Begin date (yyyy-mm-dd): ");
            String beginDate = reader.nextLine();

            System.out.print("End date (yyyy-mm-dd): ");
            String endDate = reader.nextLine();

            System.out.print("Number of children (-1 for no change): ");
            Integer numChildren = Integer.valueOf(reader.nextLine());

            System.out.print("Number of adults (-1 for no change): ");
            Integer numAdults = Integer.valueOf(reader.nextLine());

            List<Object> parameters = new ArrayList<Object>();

            StringBuilder sb = new StringBuilder("UPDATE lab7_reservations SET ");
            int param_count = 0;

            if(!"".equalsIgnoreCase(firstName)) {
                sb.append("FirstName = ? ");
                param_count++;
                parameters.add(firstName);
            }

            if(!"".equalsIgnoreCase(lastName)) {
                if(param_count > 0) {
                    sb.append(", ");
                }
                sb.append("lastName = ? ");
                param_count++;
                parameters.add(lastName);
            }

            if(!"".equalsIgnoreCase(beginDate)) {
                if(param_count > 0) {
                    sb.append(", ");
                }
                sb.append("startDate = ? ");
                param_count++;
                parameters.add(beginDate);
            }

            if(!"".equalsIgnoreCase(endDate)) {
                if(param_count > 0) {
                    sb.append(", ");
                }
                sb.append("endDate = ? ");
                param_count++;
                parameters.add(endDate);
            }

            if(numChildren >= 0) {
                if(param_count > 0) {
                    sb.append(", ");
                }
                sb.append("Number of Kids = ? ");
                param_count++;
                parameters.add(numChildren);
            }

            if(numAdults > 0) {
                if(param_count > 0) {
                    sb.append(", ");
                }
                sb.append("Number of Adults = ? ");
                param_count++;
                parameters.add(numAdults);
            }

            sb.append("WHERE CODE = ? AND ");
            sb.append("NOT EXISTS (SELECT * FROM");
            sb.append("(SELECT * FROM lab7_reservations as r1 ");
            sb.append("WHERE CODE = ?) dates ");
            sb.append("INNER JOIN ");
            sb.append("(SELEECT * from lab7_reservations as r2 ");
            sb.append("WHERE CODE != ?) OTHERS ");
            sb.append("ON (");
            parameters.add(rsvpCode);
            parameters.add(rsvpCode);
            parameters.add(rsvpCode);
            

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

            // con.setAutoCommit(false)

            // execute SQL
            // conn.commit()


            // check results using ResultSet, NOTE: iterate using rs.next()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO(louiseibuna): FR1
    public void FR1() throws SQLException {
        try {
            Connection conn = establishConnection();

            //Spaces in between sb.append indicate sub blocks
            StringBuilder sb = new StringBuilder("WITH popularRooms AS (");
            sb.append("SELECT room, ROUND(SUM(DATEDIFF(CheckOut, CheckIn))/180, 2) p");
            sb.append("FROM lab7_reservations");
            sb.append("GROUP BY Room), ");

            sb.append("NextAvailableDate AS (");
            sb.append("SELECT room, MIN(CheckOut) AS Next");
            sb.append("FROM lab7_reservations");
            sb.append("WHERE CheckOut >= CURDATE()");
            sb.append("GROUP BY Room),");

            sb.append("LengthStays AS (");
            sb.append("SELECT room, code, CheckIn, CheckOut, DATEDIFF(CheckOut, CheckIn) AS Length, ");
            sb.append("MAX(CheckOut) OVER (PARTITION BY Room) AS LATEST");
            sb.append("FROM lab7_reservations");
            sb.append("WHERE CheckOut < CURDATE()");

            sb.append("MostRecentRoom AS (");
            sb.append("SELECT Room, Length, CheckOut");
            sb.append("FROM LengthStays");
            sb.append("WHERE CheckOut = Latest");

            sb.append("SELECT r.*, p.p AS popularRooms, n.NextAvailableDate AS NextAvail, l.LengthStays AS Length, l.CheckOut AS Outtie");
            sb.append("FROM Rooms");
            sb.append("INNER JOIN popularRooms p ON r.RoomCode = p.Room");
            sb.append("INNER JOIN NextAvailableDate n ON r.RoomCode = n.Room");
            sb.append("INNER JOIN MostRecentRoom l ON RoomCode = l.Room");
            sb.append("ORDER BY p.p DESC;");

            // execute SQL
            try(PreparedStatement pst = conn.prepareStatement(sb.toString())) {
                try(ResultSet rs = pst.executeQuery()) {
                    System.out.format("%6s |%30s |%4s |%8s |%3s |%10s |%20s |%10s |%15s |%15s |%10s",
                                        "roomcode", "roomname", "beds", "bedType", "maxOcc", "basePrice", "decor", "popularRooms", "nextAvail", "LengthStay", "Outtie");
                    System.out.println("------" + "-----------------------" + "---------" + "-----" + "------" + "-----" + "---------" + "------" + "------" + "-------" + "------");
                    while(rs.next()) {
                        String RoomCode = rs.getString("roomcode");
                        String RoomName = rs.getString("roomname");
                        int Beds = rs.getInt("beds");
                        String bedType = rs.getString("bedType");
                        int maxOcc = rs.getInt("maxOcc");
                        float basePrice = rs.getFloat("basePrice");
                        String decor = rs.getString("decor");
                        String popularRooms = rs.getString("popularRooms");
                        String NextAvailableDate = rs.getString("NextAvailableDate");
                        String LengthStay = rs.getString("LengthStay");
                        String Outtie = rs.getString("Outtie");

                        System.out.format("%5s |%30s |%4d |%8s |%3d |%10.2f |%20s |%4.2f |%15s |%15s |%15s%n",
                            RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor, popularRooms, NextAvailableDate, LengthStay, Outtie);
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
