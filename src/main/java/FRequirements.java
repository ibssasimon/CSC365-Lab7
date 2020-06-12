package src.main.java;

import java.sql.*;
import java.time.Instant;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.List;

public class FRequirements {
    private final String JDBC_URL = "jdbc:h2:~/csc365_lab7";
    private final String JDBC_USER = "";
    private final String JDBC_PASSWORD = "";
    private Connection establishConnection() throws SQLException {
        // Create user name, password, url
        // establish to DB
        Connection conn;
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            return conn;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    // TODO (ibssasimon): FR5
    public void FR5() {

        /*
        FR5 QUERY
           with rev as (
    select Room, round(sum(
    case when month(Checkout) = 1 then datediff(Checkout, CheckIn) * rate else 0 end),0) as January,
    round(sum(case when month(Checkout) = 2 then datediff(Checkout, CheckIn) * rate else 0 end),0) as February,
    round(sum(case when month(Checkout) = 3 then datediff(Checkout, CheckIn) * rate else 0 end),0) as March,
    round(sum(case when month(Checkout) = 4 then datediff(Checkout, CheckIn) * rate else 0 end),0) as April,
    round(sum(case when month(Checkout) = 5 then datediff(Checkout, CheckIn) * rate else 0 end),0) as May,
    round(sum(case when month(Checkout) = 6 then datediff(Checkout, CheckIn) * rate else 0 end),0) as June,
    round(sum(case when month(Checkout) = 7 then datediff(Checkout, CheckIn) * rate else 0 end),0) as July,
    round(sum(case when month(Checkout) = 8 then datediff(Checkout, CheckIn) * rate else 0 end),0) as August,
    round(sum(case when month(Checkout) = 9 then datediff(Checkout, CheckIn) * rate else 0 end),0) as September,
    round(sum(case when month(Checkout) = 10 then datediff(Checkout, CheckIn) * rate else 0 end),0) as October,
    round(sum(case when month(Checkout) = 11 then datediff(Checkout, CheckIn) * rate else 0 end),0) as November,
    round(sum(case when month(Checkout) = 12 then datediff(Checkout, CheckIn) * rate else 0 end),0) as December,
    round(sum(datediff(Checkout, Checkin) * rate),0) as Annual
    from reservations
    group by Room
)
select Room, January, February, March, April, May, June, July, August, September, October, November, December, Annual from rev
union
select 'Total', sum(January), sum(February), sum(March), sum(April), sum(May), sum(June), sum(July), sum(August),
sum(September), sum(October), sum(November), sum(December), sum(Annual) from rev;

         */

        System.out.println("Month-By-Month overview of revenue: ");
        try {
            Connection conn = establishConnection();

            // build sql query using static String object
            String q = "with rev as (select Room, round(sum(case when month(Checkout) = 1 then datediff(Checkout, CheckIn) * rate else 0 end),0) as January,round(sum(case when month(Checkout) = 2 then datediff(Checkout, CheckIn) * rate else 0 end),0) as February,round(sum(case when month(Checkout) = 3 then datediff(Checkout, CheckIn) * rate else 0 end),0) as March, round(sum(case when month(Checkout) = 4 then datediff(Checkout, CheckIn) * rate else 0 end),0) as April, round(sum(case when month(Checkout) = 5 then datediff(Checkout, CheckIn) * rate else 0 end),0) as May, round(sum(case when month(Checkout) = 6 then datediff(Checkout, CheckIn) * rate else 0 end),0) as June, round(sum(case when month(Checkout) = 7 then datediff(Checkout, CheckIn) * rate else 0 end),0) as July, round(sum(case when month(Checkout) = 8 then datediff(Checkout, CheckIn) * rate else 0 end),0) as August, round(sum(case when month(Checkout) = 9 then datediff(Checkout, CheckIn) * rate else 0 end),0) as September, round(sum(case when month(Checkout) = 10 then datediff(Checkout, CheckIn) * rate else 0 end),0) as October,round(sum(case when month(Checkout) = 11 then datediff(Checkout, CheckIn) * rate else 0 end),0) as November, round(sum(case when month(Checkout) = 12 then datediff(Checkout, CheckIn) * rate else 0 end),0) as December, round(sum(datediff(Checkout, Checkin) * rate),0) as Annual from lab7_reservations group by Room ) select Room, January, February, March, April, May, June, July, August, September, October, November, December, Annual from rev union select 'Total', sum(January), sum(February), sum(March), sum(April), sum(May), sum(June), sum(July), sum(August),sum(September), sum(October), sum(November), sum(December), sum(Annual) from rev;";



            // execute SQL
            try (Statement st = conn.createStatement()) {
                ResultSet res = st.executeQuery(q);
                while (res.next()) {
                    // analyze results
                    String room = res.getString("Room");
                    String january = res.getString("January");
                    String february = res.getString("February");
                    String march = res.getString("March");
                    String april = res.getString("April");
                    String may = res.getString("May");
                    String june = res.getString("June");
                    String july = res.getString("July");
                    String august = res.getString("August");
                    String september = res.getString("September");
                    String october = res.getString("October");
                    String november = res.getString("November");
                    String december = res.getString("December");
                    String annual = res.getString("Annual");



                    System.out.format("%s %s %s %s %s %s %s %s %s %s %s %s %s %s", room, january, february, march, april, may, june, july, august, september, october, november, december, annual);


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
            final Connection conn = establishConnection();

            Scanner reader = new Scanner(System.in);

            System.out.println("Enter your reservation code to cancel: ");
            int code = reader.nextInt();
            String confirmCancel = reader.nextLine();

            if(confirmCancel.equals("yes") || confirmCancel.equals("YES") || confirmCancel.equals("Yes")) {
                PreparedStatement stmt = conn.prepareStatement(
                        "DELETE FROM lab7_reservations " +
                                "WHERE Code = ?"
                );
                int res = stmt.executeUpdate();
                if(res >= 1) {
                    System.out.println("Reservation cancelled");
                    return;
                }
                else {
                    System.out.println("No reservation code found. Please try again.");
                    return;
                }
            }


            // check results using ResultSet, NOTE: iterate using rs.next()
        } catch (final SQLException e) {
            e.printStackTrace();
        }

    }

    // TODO(louiseibuna): FR3
    public void FR3() {
        try {
            final Connection conn = establishConnection();
            final Scanner reader = new Scanner(System.in);
            System.out.println("Enter reservation code: ");
            final String rsvpCode = reader.nextLine();

            if(rsvpCode == null || rsvpCode.length() == 0) {
                System.out.println("RSVP code required! Please try again.");
                return;
            }
            //Insert information to fields
            System.out.println("Fill out new information for the following fields. If you wish to not make a change on a field, hit Enter.");

            System.out.print("First Name: ");
            final String firstName = reader.nextLine();

            System.out.print("Last Name: ");
            final String lastName = reader.nextLine();

            System.out.print("Begin date (yyyy-mm-dd): ");
            final String beginDate = reader.nextLine();

            System.out.print("End date (yyyy-mm-dd): ");
            final String endDate = reader.nextLine();

            System.out.print("Number of children (-1 for no change): ");
            final Integer numChildren = Integer.valueOf(reader.nextLine());

            System.out.print("Number of adults (-1 for no change): ");
            final Integer numAdults = Integer.valueOf(reader.nextLine());

            final List<Object> parameters = new ArrayList<Object>();

            final StringBuilder sb = new StringBuilder("UPDATE lab7_reservations SET ");
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

            if(!"".equalsIgnoreCase(beginDate)) {
                sb.append("? ");
                parameters.add(beginDate);
            }
            else {
                sb.append("dates.checkin ");
            }
            sb.append("< others.checkout) and (");

            if(!"".equalsIgnoreCase(endDate)) {
                sb.append("? ");
                parameters.add(endDate);
            }
            else {
                sb.append("dates.checkout ");
            }
            sb.append("> others.checkin) and dates.room = others.room ");
            sb.append("and ");

            if(!"".equalsIgnoreCase(beginDate)) {
                sb.append("? ");
                parameters.add(beginDate);
            }
            else {
                sb.append("dates.checkin ");
            }
            sb.append("< ");

            if(!"".equalsIgnoreCase(endDate)) {
                sb.append("? );");
                parameters.add(endDate);
            }
            else {
                sb.append("dates.checkout); ");
            }

            if(param_count == 0) return;
            try (PreparedStatement pstmt = conn.prepareStatement(sb.toString())) {
                int i = 1;
                for (final Object p : parameters) {
                    pstmt.setObject(i++, p);
                }

                final int res = pstmt.executeUpdate();

            }
        } catch (final SQLException e) {
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
        String bedType = "";
        double rate = 0.0;
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
        scan.close();


        /*
        * QUESTIONS:
        *
        * */
        // select statement to see if there are any current reservations in that roomcode
        try {
            Connection conn = establishConnection();

            // build Statement using StringBuilder here

            // find all reservations under user inputted roomcode
            StringBuilder sb = new StringBuilder();
            sb.append("select Room from lab7_reservations WHERE Room ='");
            sb.append(roomCode);
            sb.append("'");
            sb.append(" and (CheckIn >= ");
            sb.append("'");
            sb.append(begin);
            sb.append("'");
            sb.append(" and CheckOut <= ");
            sb.append("'");
            sb.append(end);
            sb.append("'");
            sb.append(");");

            ArrayList<String> allBookedRooms = new ArrayList<String>();
            // execute SQL
            try (Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(sb.toString());

                // build unique CODE(11)
                int CODE = generateCode();
                try (Statement randStat = conn.createStatement()) {
                    ResultSet codes = randStat.executeQuery("SELECT max(CODE) from lab7_reservations;");
                    while(codes.next()) {
                        String code = codes.getString("CODE");
                        CODE = Integer.parseInt(code);
                        CODE += 1;
                    }

                } catch(SQLException e) {
                    e.printStackTrace();
                }
                // end of building unique CODE(11)



                // empty set, no rooms at all are occupied in that date frame
                if(!rs.next()) {
                    try (Statement st1 = conn.createStatement()) {
                        // find bedType, Rate
                        StringBuilder temp = new StringBuilder("SELECT basePrice, bedType from lab7_reservations inner join lab7_rooms on lab7_reservations.Room = lab7_rooms.RoomCode and lab7_reservations.Room = '");
                        temp.append(roomCode);
                        temp.append("'");
                        temp.append(";");
                        ResultSet ratesAndBedTypes = st1.executeQuery(temp.toString());
                        while(ratesAndBedTypes.next()) {
                            String r = ratesAndBedTypes.getString("basePrice");
                            rate = Float.parseFloat(r);

                            String bt = ratesAndBedTypes.getString("bedType");
                            bedType = bt;
                        }

                        StringBuilder sb1 = r2InsertIntoTable(CODE, roomCode, begin, end, rate, last, adults, kids);
                        st1.execute(sb1.toString());


                        updateUserSuccess(first, last, roomCode, bedType, begin, end, adults, kids);
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
                            st1.execute(sb1.toString());
                            updateUserSuccess(first, last, roomCode, bedType, begin, end, adults, kids);
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
            final Connection conn = establishConnection();

            //Spaces in between sb.append indicate sub blocks
            final StringBuilder sb = new StringBuilder("WITH popularRooms AS (");
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
                        final String RoomCode = rs.getString("roomcode");
                        final String RoomName = rs.getString("roomname");
                        final int Beds = rs.getInt("beds");
                        final String bedType = rs.getString("bedType");
                        final int maxOcc = rs.getInt("maxOcc");
                        final float basePrice = rs.getFloat("basePrice");
                        final String decor = rs.getString("decor");
                        final String popularRooms = rs.getString("popularRooms");
                        final String NextAvailableDate = rs.getString("NextAvailableDate");
                        final String LengthStay = rs.getString("LengthStay");
                        final String Outtie = rs.getString("Outtie");

                        System.out.format("%5s |%30s |%4d |%8s |%3d |%10.2f |%20s |%4.2f |%15s |%15s |%15s%n",
                                RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor, popularRooms, NextAvailableDate, LengthStay, Outtie);
                    }

                }
            }
        } catch (final SQLException e) {
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
        double totalCost = 0;


        try {
            Connection conn = establishConnection();
            StringBuilder sb = new StringBuilder("SELECT Room, CheckIn, Checkout, Rate from lab7_reservations where Room = ");
            sb.append(RoomCode);
            sb.append("and FirstName = '");
            sb.append(first);
            sb.append("' and LastName = '");
            sb.append(last);
            sb.append("';");
            try(Statement st = conn.createStatement()) {

                ResultSet rs = st.executeQuery(sb.toString());
                R2Response r2Response = null;
                while(rs.next()) {
                    String room = rs.getString("Room");
                    String checkin = rs.getString("CheckIn");
                    String checkout = rs.getString("Checkout");
                    String r = rs.getString("Rate");
                    float rate = Float.parseFloat(r);

                    r2Response =  new R2Response(room, checkin, checkout, rate);


                }
                totalCost = r2Response.computeTotalStay();
                System.out.println("Total cost of stay: " + totalCost);
            }catch (SQLException e) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateUserFailure() {
        System.out.println("Could not add reservation (room occupied). Please try another set of options.");
    }

    private StringBuilder r2InsertIntoTable(int CODE, String roomCode, String begin, String end, double rate, String last, int adults, int kids) {
        StringBuilder sb1 = new StringBuilder("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, Adults, Kids) VALUES ");
        sb1.append("(");
        sb1.append(CODE);
        sb1.append(",");
        sb1.append("'");
        sb1.append(roomCode);
        sb1.append("'");
        sb1.append(",");
        sb1.append("'");
        sb1.append(begin);
        sb1.append("'");
        sb1.append(",");
        sb1.append("'");
        sb1.append(end);
        sb1.append("'");
        sb1.append(",");
        sb1.append(rate);
        sb1.append(",");
        sb1.append("'");
        sb1.append(last);
        sb1.append("'");
        sb1.append(",");
        sb1.append(adults);
        sb1.append(",");
        sb1.append(kids);
        sb1.append(");");
        return sb1;
    }


    public void setupTables() {
        // Send SQL DDL and DML to DB
        try {
            Connection c = establishConnection();

            try (Statement st = c.createStatement()) {
                // DDL

                st.execute("drop table if exists lab7_rooms;");
                st.execute("create table lab7_rooms (RoomCode char(5) PRIMARY KEY,\nRoomName varchar(30),\nBeds int(11),\nbedType varchar(8),\nmaxOcc int(11),\nbasePrice float,\ndecor varchar(20),\nPRIMARY KEY(RoomCode),\nUNIQUE(RoomName)\n);");
                st.execute("INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('AOB', 'Abscond or bolster', 2, 'Queen', 4, 175, 'traditional');");
                st.execute("INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('CAS', 'Convoke and sanguine', 2, 'King', 4, 175, 'traditional');");
                st.execute("INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('FNA', 'Frugal not apropos', 2, 'King', 4, 250, 'traditional');");
                st.execute("INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('HBB', 'Harbinger but bequest', 1, 'Queen', 2, 100, 'modern');");
                st.execute("INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('IBD', 'Immutable before decorum', 2, 'Queen', 4, 150, 'rustic');");





                st.execute("drop table if exists lab7_reservations;");
                st.execute("create table lab7_reservations (\nCODE int(11),\nRoom char(5),\nCheckIn DATE,\nCheckOut DATE,\nRate float,\nLastName varchar(15),\nFirstName varchar(15),\nAdults int(11),\nKids int(11),\n PRIMARY KEY(CODE),\n FOREIGN KEY(Room) references lab7_rooms(RoomCode)\n);");
                st.execute("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, FirstName, Adults, Kids) VALUES(10105, 'HBB', '2010-10-23', '2010-10-25', 100, 'SELBIG', 'CONRAD', 1, 0);");
                st.execute("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, FirstName, Adults, Kids) VALUES(10183, 'IBD', '2010-09-19', '2010-09-20', 150, 'GABLER', 'DOLLIE', 2, 0);");
                st.execute("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, FirstName, Adults, Kids) VALUES(10489, 'AOB', '2010-02-02', '2010-02-05', 218.75, 'CARISTO', 'MARKITA', 2, 1);");
                st.execute("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, FirstName, Adults, Kids) VALUES(10574, 'FNA', '2010-11-26', '2010-12-03', 287.5, 'SWEAZY', 'ROY', 2, 1);");
                st.execute("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, FirstName, Adults, Kids) VALUES(10990, 'CAS', '2010-09-21', '2010-09-27', 175, 'TRACHSEL', 'DAMIEN', 1, 3);");





                // DML

                // need to manually put in these datapoints



            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    private class R2Response {
        public String room;
        public String checkin;
        public String checkout;
        double rate;
        public R2Response(String room, String checkin, String checkout, float rate) {
            this.room = room;
            this.checkin = checkin;
            this.checkout = checkout;
            this.rate = rate;
        }
        public float computeTotalStay() {
            float sum = 0;
            LocalDate checkInDate = LocalDate.parse(checkin);
            LocalDate checkOutDate = LocalDate.parse(checkout);
            ZoneId z = ZoneId.of( "America/Montreal" );


            for(LocalDate date = checkInDate; date.isBefore(checkOutDate); date = date.plusDays(1)) {
                Calendar c = Calendar.getInstance();
                ZonedDateTime zoned_time = date.atStartOfDay(z);
                Instant i = zoned_time.toInstant();

                java.util.Date d = java.util.Date.from(i);
                c.setTime(d);

                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

                if(dayOfWeek >= 2 && dayOfWeek <=6) {
                    // weekday case
                    sum += (rate);
                } else if (dayOfWeek == 0 || dayOfWeek == 7) {
                    // weekend case
                    sum += (rate * 1.10);
                }
            }
            return sum;
        }
        public String getRoom() {
            return room;
        }
    }

    private boolean execute_query_r3(Connection conn, StringBuilder sb, List<Object> params, String resCode) throws SQLException {

        try (PreparedStatement pstmt = conn.prepareStatement(sb.toString())) {
            int i = 1;
            for (final Object p : params) {
                pstmt.setObject(i++, p);
            }

            // Try finding any matching room
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Checking for conflicting dates");
                while (rs.next())
                    if (!resCode.equals(rs.getString("code")))
                        return false;
                return true;
            }
        }
    }
}
