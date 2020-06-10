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
            String q = "with rev as (\nselect Room, round(sum(\ncase when month(Checkout) = 1 then datediff(Checkout, CheckIn) * rate else 0 end),0) as January,\nround(sum(case when month(Checkout) = 2 then datediff(Checkout, CheckIn) * rate else 0 end),0) as February,\nround(sum(case when month(Checkout) = 3 then datediff(Checkout, CheckIn) * rate else 0 end),0) as March,\n round(sum(case when month(Checkout) = 4 then datediff(Checkout, CheckIn) * rate else 0 end),0) as April,\n round(sum(case when month(Checkout) = 5 then datediff(Checkout, CheckIn) * rate else 0 end),0) as May,\n round(sum(case when month(Checkout) = 6 then datediff(Checkout, CheckIn) * rate else 0 end),0) as June,\n round(sum(case when month(Checkout) = 7 then datediff(Checkout, CheckIn) * rate else 0 end),0) as July,\n round(sum(case when month(Checkout) = 8 then datediff(Checkout, CheckIn) * rate else 0 end),0) as August,\n round(sum(case when month(Checkout) = 9 then datediff(Checkout, CheckIn) * rate else 0 end),0) as September,\n round(sum(case when month(Checkout) = 10 then datediff(Checkout, CheckIn) * rate else 0 end),0) as October,\n round(sum(case when month(Checkout) = 11 then datediff(Checkout, CheckIn) * rate else 0 end),0) as November,\n round(sum(case when month(Checkout) = 12 then datediff(Checkout, CheckIn) * rate else 0 end),0) as December,\n round(sum(datediff(Checkout, Checkin) * rate),0) as Annual\n from reservations\n group by Room\n )\n select Room, January, February, March, April, May, June, July, August, September, October, November, December, Annual from rev\n union\n select 'Total', sum(January), sum(February), sum(March), sum(April), sum(May), sum(June), sum(July), sum(August),\n sum(September), sum(October), sum(November), sum(December), sum(Annual) from rev;";



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
                        StringBuilder temp = new StringBuilder("SELECT basePrice, bedType from reservations inner join rooms on reservations.Room = rooms.RoomCode and reservations.Room = '");
                        temp.append(roomCode);
                        temp.append("'");
                        temp.append(");");
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
        int totalCost = 0;


        try {
            Connection conn = establishConnection();

            try(Statement st = conn.createStatement()) {
                st.executeQuery("");
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


    public void setupTables() {
        // Send SQL DDL and DML to DB
        try {
            Connection c = establishConnection();

            try (Statement st = c.createStatement()) {
                // DDL
                st.execute("drop table if exists lab7_rooms;");
                st.execute("drop table if exists lab7_reservations;");
                st.execute("create table if not exists lab7_rooms (char(5) RoomCode,\nvarchar(30) RoomName,\nint(11) Beds,\nvarchar(8) bedType,\nint(11) maxOcc,\nfloat basePrice,\nvarchar(20) decor,\nPRIMARY KEY(RoomCode),\nUNIQUE(RoomName)\n);");
                st.execute("create table if not exists lab_7reservations(\nint(11) CODE,\nchar(5) Room,\nDATE CheckIn,\nDATE CheckOut,\n float Rate,\nvarchar(15) LastName,\n varchar(15) FirstName,\nint(11) Adults,\nint(11) Kids,\n PRIMARY KEY(CODE),\n FOREIGN KEY(Room) references lab7_rooms(RoomCode)\n);");


                // DML

                // need to manually put in these datapoints
                st.execute("INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('AOB', 'Abscond or bolster', 2, 'Queen', 4, 175, 'traditional');");
                st.execute("INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('CAS', 'Convoke and sanguine', 2, 'King', 4, 175, 'traditional');");
                st.execute("INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('FNA', 'Frugal not apropos', 2, 'King', 4, 250, 'traditional');");
                st.execute("INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('HBB', 'Harbinger but bequest', 1, 'Queen', 2, 100, 'modern');");
                st.execute("INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('IBD', 'Immutable before decorum', 2, 'Queen', 4, 150, 'rustic');");


                st.execute("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, FirstName, Adults, Kids) VALUES(10105, 'HBB', '2010-10-23', '2010-10-25', 100, 'SELBIG', 'CONRAD', 1, 0);");
                st.execute("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, FirstName, Adults, Kids) VALUES(10183, 'IBD', '2010-09-19', '2010-09-20', 150, 'GABLER', 'DOLLIE', 2, 0);");
                st.execute("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, FirstName, Adults, Kids) VALUES(10489, 'AOB', '2010-02-02', '2010-02-05', 218.75, 'CARISTO', 'MARKITA', 2, 1);");
                st.execute("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, FirstName, Adults, Kids) VALUES(10574, 'FNA', '2010-11-26', '2010-12-03', 287.5, 'SWEAZY', 'ROY', 2, 1);");
                st.execute("INSERT INTO lab7_reservations (CODE, Room, CheckIn, CheckOut, Rate, LastName, FirstName, Adults, Kids) VALUES(10990, 'CAS', '2010-09-21', '2010-09-27', 175, 'TRACHSEL', 'DAMIEN', 1, 3);");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
