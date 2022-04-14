package busandticket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Bus {
    String route, source, dst, date;
    int time, totalSeat;
    int seat[];
    Scanner scan = new Scanner(System.in);

    void ticketBook() {
        System.out.println(
                "\t*********************************************************************************************************");
        System.out.println(
                "\t\tOur Service covers cities : Ahmedabad, Surat, Baroda, Bhavnagar, Rajkot, Navsari \n\t\tShortly,We will Extend Our Services For Other Cities too.");
        System.out.println(
                "\t*********************************************************************************************************");

        for (;;) {
            System.out.println("Enter Starting Point or Press 0 to go back to Main Menu: ");
            // ......................take starting point.........................
            source = scan.next();
            if (source.equals("0"))
                return;
            while (!source.equalsIgnoreCase("ahmedabad") && !source.equalsIgnoreCase("surat")
                    && !source.equalsIgnoreCase("baroda") && !source.equalsIgnoreCase("navsari")
                    && !source.equalsIgnoreCase("bhavnagar") && !source.equalsIgnoreCase("rajkot")) {
                System.out.println("By Mistake You Entered Wrong City \nEnter it Again or  Press 0 to go back: ");
                source = scan.next();
                if (source.equals("0"))
                    return;
            }

            // ........................take destination............................
            System.out.println("Enter Destination or Press 0 to go back to Main Menu: ");
            dst = scan.next();
            if (dst.equals("0"))
                return;
            while (!dst.equalsIgnoreCase("ahmedabad") && !dst.equalsIgnoreCase("surat")
                    && !dst.equalsIgnoreCase("baroda") && !dst.equalsIgnoreCase("navsari")
                    && !dst.equalsIgnoreCase("bhavnagar") && !dst.equalsIgnoreCase("rajkot")) {
                System.out.println(
                        "By Mistake You Entered Wrong City \nEnter it Again or Press 0 to go back to Main Menu: ");
                dst = scan.next();
                if (dst.equals("0"))
                    return;
            }
            if (source.equalsIgnoreCase(dst)) {
                System.out.println("Starting Point and Destination cannot be same!");
            } else
                break;
        }
        // ........................take date............................
        System.out.println("\t**************** NOTE: We Only Book for next 15 days .*****************");
        int d = 0, m = 0, y = 0;
        for (;;) {
            System.out.println("Enter Date(DD MM YYYY) or Press 0 to go back to Main Menu: ");
            try {
                d = scan.nextInt();
                if (d == 0)
                    return;
                m = scan.nextInt();
                y = scan.nextInt();
                if (!isValidDate(m + "/" + d + "/" + y)) {
                    System.out.println("Date is Not Valid!");
                    continue;
                }
                String temp = setDate(d) + setMonth(m) + y + ".txt";
                if (!new File("..\\Bus_Ticket_Booking\\Database\\SeatBooked\\" + temp).exists()) {
                    System.out.println(
                            "Sorry we unable to Book Ticket For the Date provided. \nRemember We Book in Advance for next 15 days only.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Date Month Year Must Be An Integer");
                scan.nextLine();
            }
        }
        date = setDate(d) + setMonth(m) + y;
        // .........................take time..........................
        System.out.println(
                "\t************ NOTE: Our Service is for Below Provided Times only. Routes with New Time will be added Shortly.*************");
        for (;;) {
            try {
                System.out.println("Enter\n1.Departure Time : 9 a.m.\n2.Departure Time : 9 p.m.:");
                System.out.println("or Press 0 to go back to Main Menu:");
                time = scan.nextInt();
                if (time == 0)
                    return;
                if (time != 1 && time != 2) {
                    System.out.println("Your Input should be either 1 or 2.");
                    continue;
                } else
                    break;
            } catch (InputMismatchException e) {
                System.out.println("Your Input should be either 1 or 2");
                scan.nextLine();
            }
        }
        if (time == 1)
            time = 9;
        else
            time = 10;

        // ..................making of route..........................
        {
            if (source.equalsIgnoreCase("ahmedabad"))
                route = "1";
            else if (source.equalsIgnoreCase("surat"))
                route = "2";
            else if (source.equalsIgnoreCase("baroda"))
                route = "3";
            else if (source.equalsIgnoreCase("navsari"))
                route = "4";
            else if (source.equalsIgnoreCase("rajkot"))
                route = "5";
            else
                route = "6";
            if (dst.equalsIgnoreCase("ahmedabad"))
                route = route + "01";
            else if (dst.equalsIgnoreCase("surat"))
                route = route + "02";
            else if (dst.equalsIgnoreCase("baroda"))
                route = route + "03";
            else if (dst.equalsIgnoreCase("navsari"))
                route = route + "04";
            else if (dst.equalsIgnoreCase("rajkot"))
                route = route + "05";
            else
                route = route + "06";
            if (time == 9)
                route = route + "D";
            else
                route = route + "N";
        }

        // ..............................Data Read From Date of Booking
        // File.................
        BufferedReader r = null;
        List<String> file = null;
        try {
            r = new BufferedReader(new FileReader("..\\Bus_Ticket_Booking\\Database\\SeatBooked\\" + date + ".txt"));
            file = new ArrayList<>(60);
            for (int i = 0; i < 60; i++) {
                file.add(i, r.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found\n" + e);
        } catch (IOException e) {
            System.out.println("Some I/O Error Occur\n" + e);
        } finally {
            try {
                r.close();
            } catch (IOException e) {
                System.out.println("Some I/O Error Occur\n" + e);
            }
        }

        // ..........................SeatNumber Format....................
        System.out.println("Bus SeatNumber Format: " + "\n\t1  2\t3  4  5\n" + "\t6  7\t8  9  10\n"
                + "\t11 12\t13 14 15\n" + "\t16 17\t18 19 20\n" + "\t21 22\t23 24 25\n" + "\t26 27\t28 29 30\n"
                + "\t31 32\t33 34 35\n" + "\t36 37\t38 39 40\n" + "\t41 42\t43 44 45\n" + "\t46 47\t48 49 50\n");
        int index = 0;
        for (int i = 0; i < 60; i++) {

            if (file.get(i).equals(route)) {
                index = i;
                break;
            }
        }

        // ........................showing available and not available
        // seat......................
        String replace[];
        replace = file.remove(index).split(" ");
        System.out.println("Here 0 represent Seat is Available and 1 represent Seat is already Booked :");
        for (int i = 0; i < 10; i++) {
            System.out.println("\t" + replace[5 * i + 1] + " " + replace[5 * i + 2] + "\t" + replace[5 * i + 3] + " "
                    + replace[5 * i + 4] + " " + replace[5 * i + 5]);
        }

        // ........................booking of seat..............................
        for (;;) {
            try {
                System.out.println("Enter TotalSeat :");
                System.out.println("Press 0 to go back to Main Menu:");
                totalSeat = scan.nextInt();
                if (totalSeat == 0)
                    return;
                if (totalSeat > 50 || totalSeat < 0) {
                    System.out.println("Total Seat capacity of Bus : 50");
                    continue;
                } else
                    break;
            } catch (InputMismatchException e) {
                System.out.println("Enter Valid Seat Number");
                scan.nextLine();
            }
        }
        System.out.println("Enter " + totalSeat + " Seat No:");
        seat = new int[totalSeat];
        for (int i = 0; i < totalSeat; i++) {

            for (;;) {
                try {
                    seat[i] = scan.nextInt();
                    if (seat[i] == 0)
                        return;
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Seat Number Must be an Integer");
                    System.out.println("Enter Again or Press 0 to go back to Main Menu:");
                    scan.nextLine();
                }
            }
            if (seat[i] > 50 || seat[i] < 0) {
                System.out.println("Seat Number must be between 1 to 50.");
                System.out.println("Enter Again or Press 0 to go back to Main Menu:");
                i--;
                continue;
            }
            if (replace[seat[i]].equals("1")) {
                System.out.println("The Seat " + seat[i] + " is already Booked.");
                System.out.println("Enter Again or Press 0 to go back to Main Menu:");
                i--;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (seat[j] == seat[i]) {
                    System.out.println("The Seat " + seat[i] + " is already Booked.");
                    System.out.println("Enter Again or Press 0 to go back to Main Menu:");
                    i--;
                    break;
                }
            }

        }
        for (int i = 0; i < totalSeat; i++) {
            replace[seat[i]] = "1";
        }

        // ........................replace data in file............................
        String temp = replace[0];
        for (int i = 1; i < 51; i++) {
            temp = temp + " " + replace[i];
        }
        file.add(index, temp);
        BufferedWriter w = null;
        try {
            w = new BufferedWriter(
                    new FileWriter("..\\Bus_Ticket_Booking\\Database\\SeatBooked\\" + date + ".txt", false));
            for (int i = 0; i < 60; i++) {
                w.write(file.get(i));
                w.write("\n");
            }
            w.flush();
        } catch (IOException e) {
            System.out.println("Some I/O Error Occur \n" + e);
        } finally {
            try {
                w.close();
            } catch (IOException e) {
                System.out.println("Some I/O Error Occur \n" + e);
            }
        }

    }

    boolean isValidDate(String s) {
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(s);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private String setDate(int dd) {
        if (dd == 1 || dd == 2 || dd == 3 || dd == 4 || dd == 5 || dd == 6 || dd == 7 || dd == 8 || dd == 9)
            return "0" + dd;
        return dd + "";
    }

    private String setMonth(int mm) {
        if (mm == 10 || mm == 11 || mm == 12)
            return mm + "";
        return "0" + mm;
    }
}
