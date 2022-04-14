package busandticket;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

public class AddDate {
    void addDate() {
        Date date = new Date();

        int d = date.getDate();
        int m = date.getMonth() + 1;
        int y = date.getYear() + 1900;
        FileWriter w = null;
        BufferedReader r = null;
        try {
            r = new BufferedReader(new FileReader("..\\Bus_Ticket_Booking\\Database\\Date Data.txt"));
            StringTokenizer str = new StringTokenizer(r.readLine());
            while (str.hasMoreTokens()) {
                int dd = Integer.parseInt(str.nextToken());
                int mm = Integer.parseInt(str.nextToken());
                int yy = Integer.parseInt(str.nextToken());
                if (dd == d && mm == m && yy == y)
                    break;
                String s = setDate(dd) + setMonth(mm) + yy;
                deleteFile(s + ".txt");
                if (str.hasMoreTokens())
                    str = new StringTokenizer(r.readLine());
            }
            w = new FileWriter("..\\Bus_Ticket_Booking\\Database\\Date Data.txt");
            String s = setDate(d) + setMonth(m) + y;
            for (int i = 1; i <= 15; i++) {
                createNewFile(s + ".txt");
                String s1, s2, s3;
                s1 = s.substring(0, 2);
                s2 = s.substring(2, 4);
                s3 = s.substring(4);
                d = Integer.parseInt(s1);
                m = Integer.parseInt(s2);
                y = Integer.parseInt(s3);
                w.write(s1 + " " + s2 + " " + s3 + "\n");
                w.flush();
                s = nextDate(d, m, y);

            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found\n" + e);
        } catch (IOException e) {
            System.out.println("Some I/O Error Occur \n" + e);
        } finally {
            try {
                r.close();
                w.close();
            } catch (NullPointerException e) {
                System.out.println("NUll Pointer Exception \n" + e);
            } catch (IOException e) {
                System.out.println("Some I/O Error Occur \n" + e);
            }
        }
    }

    private void createNewFile(String s) throws IOException {
        File f = new File("..\\Bus_Ticket_Booking\\Database\\SeatBooked\\" + s);
        if (f.createNewFile()) {
            BufferedReader r = null;
            BufferedWriter w = null;
            try {
                r = new BufferedReader(
                        new FileReader("..\\Bus_Ticket_Booking\\Database\\SeatDataOfDifferentRoute.txt"));
                w = new BufferedWriter(new FileWriter(f));
                for (int i = 1; i <= 60; i++) {
                    w.write(r.readLine());
                    w.write("\n");
                }
                w.flush();
            } catch (FileNotFoundException e) {
                System.err.println("File Not Found \n" + e);
            } catch (IOException e) {
                System.err.println("Some I/O Error Occur \n" + e);
            } finally {
                r.close();
                w.close();
            }
        } else {
            return;
        }
    }

    private void deleteFile(String s) {
        File f = new File("..\\Bus_Ticket_Booking\\Database\\SeatBooked\\" + s);
        f.delete();
    }

    private String nextDate(int dd, int mm, int yy) {
        if (dd == 31 && mm == 12)
            return "01" + "01" + (yy + 1);
        else if (dd == 29 && mm == 2 && (yy % 4) == 0)
            return "01" + "03" + yy;
        else if (dd == 28 && mm == 2 && (yy % 4) == 0)
            return "29" + "02" + yy;
        else {
            if (dd == 28 && mm == 2)
                return "01" + "03" + yy;
            else if (dd == 30 && (mm == 4 || mm == 6 || mm == 9 || mm == 11))
                return "01" + setMonth(mm + 1) + yy;
            else if (dd == 31)
                return "01" + setMonth(mm + 1) + yy;
            else
                return setDate(dd + 1) + setMonth(mm) + yy;
        }
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
