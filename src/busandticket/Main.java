package busandticket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import login.*;

public class Main {

	public static void main(String[] args) throws IOException {
		new AddDate().addDate();
		boolean flag1 = true;
		Scanner s = new Scanner(System.in);
		User user;
		while (flag1) {
			LoginPage lp = new LoginPage();
			user = new User();
			
			boolean flag = true;
			lp.printbigsymbol();
			int choice=lp.loginsignup(s);
			switch (choice) {
			case 1:
				try {
					user.login(s);
					if(user.getUsername()==null) {
						continue;
					}
				} catch (FileNotFoundException e) {
					System.out.println("File was not Found. Check maindata file in Userdata");
				}
				break;
			case 2:
				try {
					user.signup(s);
				} catch (IOException e) {
					System.out.println("Some File Error Occured in Signing Up");
				}
				break;
			case 3:System.exit(0);
			default:
				continue;
			}
			while (flag) {
				int ch = 0;
				System.out.println(
						"\t***********************************************************************************************");
				System.out.println("\t\tWelcome " + user.getName() + " to the Bus Ticket Booking Window");
				System.out.println(
						"\t***********************************************************************************************");
				System.out.println("\t\t1. Book a Bus Ticket");
				System.out.println("\t\t2. Print your Bus Ticket");
			//	System.out.println("\t\t3. Cancel your Bus Ticket");
			//	System.out.println("\t\t4. View your Past Tickets");
				System.out.println("\t\t0. Log Out");
				System.out.println(
						"\t***********************************************************************************************");

				boolean success = false;

				while (!success) {

					try {
						System.out.println("Enter Choice: ");
						// s.nextLine();
						// s.next();
						ch = s.nextInt();
						// s.nextLine();
						success = true;
					} catch (Exception e) {
						System.out.println("Enter valid Choice!");
						s.nextLine();

					}

				}

				switch (ch) {

				case 1: Bus b=new Bus();
					b.ticketBook();
					Ticket tb=new Ticket(b,user.getUsername());
					try {
					tb.generateTicket(s, user);
					}catch(NullPointerException e) {
					//	System.out.println("Enter Asked Data in Valid Format");
					}
					break;
				case 2:
					System.out.println("Enter Your Ticket Number : ");
					s.nextLine();
					String tnum = s.nextLine();
					System.out.println(tnum);
					Ticket t = new Ticket(tnum, user.getUsername());
					t.printTicket(user.getUsername());
					// System.out.println();
					break;
//				case 3:
//					System.out.println("Enter Your Ticket Number : ");
//					s.nextLine();
//					String tnum1 = s.nextLine();
//					System.out.println(tnum1);
//					Ticket.cancelTicket(user.getUsername(),tnum1);
//					// System.out.println();
//					break;
				case 4:
					break;	
				case 0:
					flag = false;
					break;
				default:
					System.out.println("Enter Valid Choice");
				}

			}
			
		}
	}

}
