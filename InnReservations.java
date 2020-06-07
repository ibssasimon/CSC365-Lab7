import java.util.Scanner;

public class InnReservations {

  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    int selection = 0;

    while(selection != -1) {
      System.out.println("Hello! We are Simon and Louise and we are the Inn Managers. Please refer to the options:");
      System.out.println("1: Rooms and Rates");
      System.out.println("2: Make a Reservation");
      System.out.println("3: Change Reservation");
      System.out.println("4: Cancel Reservation");
      System.out.println("5: Inn Revenue");
      System.out.println("6: Exit");
      System.out.print("Please Choose an Option: ");

      selection = reader.nextInt();
      reader.nextLine();

      //Switch statement for different options
      switch(selection) {
        case 1:
            System.out.println("Proceeding to Rooms and Rates...");
            FR1();
            break;
        case 2:
            System.out.println("Proceeding to Make a Reservation...");
            FR2();
            break;
        case 3:
            System.out.println("Proceeding to Change Reservation...");
            FR3();
            break;
        case 4:
            System.out.println("Proceeding to Cancel Reservation...");
            FR4();
            break;
        case 5:
            System.out.println("Proceeding to Inn Revenue...");
            FR5();
            break;
        case 6:
            System.out.println("Thanks! Please come again.");
            selection = -1;
            break;
      }
    }
  }

  private static void FR5() {
    System.out.println("TODO");
  }

  private static void FR4() {
    System.out.println("TODO");
  }

  private static void FR3() {
    System.out.println("TODO");
  }

  private static void FR2() {
    System.out.println("TODO");
  }

  private static void FR1() {
      System.out.println("TODO");
  }
}