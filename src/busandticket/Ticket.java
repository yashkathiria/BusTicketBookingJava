package busandticket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import login.User;

import java.util.LinkedList;

public class Ticket {
	private Bus bus;
	public String ticketNumber; // Format : "MMDDYYYY"+"RouteNumber"+"SeatNumber"
	private String username;

	public Ticket(Bus bus, String username) {
		this.bus = bus;
		this.username = username;
		
	}

	public Ticket(String ticketNumber, String username) {
		this.ticketNumber = ticketNumber;
		this.username = username;
	}

	public void generateTicket(Scanner s,User user) {
		setTicketNumber();
		File f = new File("..\\Bus_Ticket_Booking\\Database\\Userdata\\" + username + "\\" + ticketNumber + ".txt");
		try {
			f.createNewFile();
		} catch (IOException e) {
			System.out.println("Input Output Error Occured while Generating Ticket");
			return;
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
			bw.write(ticketNumber+" "+bus.route+" "+bus.date+" "+bus.source+" "+bus.dst+" "+bus.time+" ");
			bw.write(user.getName());
		for(int i=0;i<bus.totalSeat;i++)
			bw.write(" "+bus.seat[i]);
		bw.close();
		} catch (IOException e) {
			System.out.println("Some Input Error occured while accessing Ticket");
			return;
		}
		System.out.println("\t\t************************************************************");
		System.out.println("\t\tYour Ticket Number is : "+ticketNumber);
		System.out.println("\t\t************************************************************");
		System.out.println("\t\tYour Ticket is Booked Successfully");
		System.out.println("\t\tKeep this Ticket Number :"+ticketNumber+" for Future Reference");
		System.out.println("\t\tShow the ticket to Conductor and pay the required Money");
		System.out.println("\t\t************************************************************");

	}

	private void setTicketNumber() {
		ticketNumber=bus.date+bus.route+bus.seat[0];
	}

	public void printTicket(String username) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File("..\\Bus_Ticket_Booking\\Database\\Userdata\\" + username + "\\" + ticketNumber + ".txt")));

			try {
				String[] data = br.readLine().split(" ");
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("\t\tName : " + data[6] + " " + data[7]+"\t\t\tDate (DDMMYYYY): "+data[2]);
				System.out.println("\t\tFrom : "+data[3]+"\tTo : "+data[4]+"\tTime of Departure (24 Hour Format): "+data[5]);
				System.out.print("\t\tSeats booked : ");
				for(int i=8;i<data.length-1;i++) {
					System.out.print(data[i]+", ");
				}
				System.out.println(data[data.length-1]);
				System.out.println(
						"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				br.close();
			} catch (IOException e) {
				System.out.println("Unable to fetch Ticket Data");
				return;
			}

		} catch (FileNotFoundException e) {
			System.out.println("Ticket Not Found. Enter Valid Ticket Number.");
			return;
		}
		
	}

	public static void cancelTicket(String username,String tnum) {
		BufferedReader br;
		try {
			
			br = new BufferedReader(new FileReader(new File("..\\Bus_Ticket_Booking\\Database\\Userdata\\" + username + "\\" + tnum + ".txt")));
			

			try {
				String[] data = br.readLine().split(" ");
				String date=data[2];
				String route=data[1];
				String[] seats=new String[data.length-8];
				for(int i=8,j=0;i<data.length;i++,j++)
					seats[j]=data[i];
				br.close();
				clearSeats(date,route,seats);
				new File("..\\Bus_Ticket_Booking\\Database\\Userdata\\" + username + "\\" + tnum + ".txt").delete();
			} catch (IOException e) {
				System.out.println("Unable to fetch Ticket Data");
				return;
			}

		} catch (FileNotFoundException e) {
			System.out.println("Ticket Not Found");
			return;
		}
	}
	
	private static void clearSeats(String date,String route,String[] seats) {
		try {
			BufferedReader br=new BufferedReader(new FileReader(new File("..\\Bus_Ticket_Booking\\Database\\SeatBooked\\"+date+".txt")));
			List<String> data=new LinkedList<>();
			String temp;
			while((temp=br.readLine())!="") {
				data.add(temp);
			}
			Iterator<String> itr=data.iterator();
			boolean flag=false;
			while(itr.hasNext()) {
				String line=itr.next();
				String[] info=line.split(" ");
				char[] newline =line.toCharArray();
				if(info[0].equals(route)) {
					flag=true;
					for(String ss:seats) {
						int snum=Integer.parseInt(ss);
						newline[5+(snum-1)*2]='0';	
						
					}
					data.remove(line);
					data.add(newline.toString());
				}
				if(flag) {
					break;
				}
			}
			br.close();
			
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File("..\\Bus_Ticket_Booking\\Database\\SeatBooked\\"+date+".txt")));
			Iterator<String> itr1=data.iterator();
			while(itr1.hasNext()) {
				bw.write(itr1.next());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Input Error occured while Cancelling Seats");
			return;
		}
	}
}
