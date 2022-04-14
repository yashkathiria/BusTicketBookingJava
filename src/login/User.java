package login;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class User {
	private String username;
	private String password;
	private String name;
	private String dob;

	// Getters and Setters of UserData (Except Password)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void login(Scanner s) throws FileNotFoundException {
		
		File f = new File("..\\Bus_Ticket_Booking\\Database\\Userdata\\maindata.txt");
		Scanner sc = new Scanner(f);
		List<String> data = new LinkedList<>();
		while (sc.hasNext()) {
			data.add(sc.nextLine());
		}

		int attempt = 2;
		String userdetail = "";
		boolean success = false;
		a: while (attempt < 5 && !success) {
			
			System.out.println("Enter Username (Mobile Number): ");
			if(attempt==2)
			s.nextLine();
			userdetail = s.nextLine();
			userdetail=userdetail.concat(" ");
			System.out.println("Enter Password: ");
			userdetail += s.nextLine();

			// System.out.println(userdetail);

			Iterator<String> itr = data.iterator();
			while (itr.hasNext()) {
				if (itr.next().compareTo(userdetail) == 0) {
					success = true;
					break a;
				}
			}
			attempt++;
			System.out.println("Username or Password Incorrect.\nPlease Enter valid Username and Password!\n");
		}
		if (success) {
			getUser(userdetail);
		} else {
			System.out.println("Please Try After Sometime");
		
		}
		sc.close();
	}

	private void getUser(String userdetail) {
		username = userdetail.split(" ")[0];
		password = userdetail.split(" ")[1];
		File f = new File("..\\Bus_Ticket_Booking\\Database\\Userdata\\" + username + "\\info.txt");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("User info File missing");
			System.exit(0);
		}
		String[] info;
		info = s.nextLine().split(" ");
		name = info[2] + " " + info[3];
		dob = info[4];

	}

	public void signup(Scanner s) throws IOException {
		File f = new File("..\\Bus_Ticket_Booking\\Database\\Userdata\\maindata.txt");
		Scanner sc = new Scanner(f);
		List<String> data = new LinkedList<>();

		while (sc.hasNext()) {
			data.add(sc.nextLine().split(" ")[0]);
		}
		sc.close();
	
		boolean success;
		success = false;
		while (!success) {
			System.out.println("Enter your Mobile Number (It will be your Username): ");
			if (!s.hasNextLong()) {
				//s.nextLine();
				System.out.println("Enter Valid Mobile Number");

			} else {
				
				s.nextLine();
				
				username = s.nextLine();
				if (username.length() != 10) {
					System.out.println("Enter Valid Mobile Number");

				} else {
					Iterator<String> itr = data.iterator();
					while (itr.hasNext()) {
						if (itr.next().equals(username)) {
							System.out.println("Username Already Taken!");
							success = false;
							break;
						}
						success = true;
					}
				}
			}

		}

		success = false;
		while (!success) {
			System.out.println("Enter Password: ");
			password = s.nextLine();
			if (password.isEmpty() || password.contains(" ")) {
				System.out.println("Space is not allowed in Password or Password cannot be Empty");
				continue;
			} else if (password.length() <= 5) {
				System.out.println("Password should be Minimum 6 character long");
				continue;
			} else {
				success = true;
			}
		}

		success = false;
		while (!success) {
			System.out.println("Enter First Name: ");
			name = s.nextLine();
			if (name.isEmpty() || name.contains(" ")) {
				System.out.println("Space is not allowed in First Name or First Name cannot be Empty");
				continue;
			} else {
				success = true;
				name += " ";
			}
		}

		success = false;
		while (!success) {
			System.out.println("Enter Last Name: ");
			String temp = s.nextLine();
			if (temp.isEmpty() || temp.contains(" ")) {
				System.out.println("Space is not allowed in Last Name or Last Name cannot be Empty");
				continue;
			} else {
				success = true;
				name += temp;
			}
		}

		success = false;
		while (!success) {
			System.out.println("Enter your Date of Birth (Format : MM/DD/YYYY): ");
			dob = s.nextLine();
		//	sc.nextLine();
			if (isValidDate(dob)) {
				success = true;
			} else {
				System.out.println("Please Enter Valid Date of Birth (Format : MM/DD/YYYY)");
			}
		}

		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));

		File f1 = new File("..\\Bus_Ticket_Booking\\Database\\Userdata\\" + username);
		if (!f1.mkdir()) {
			System.out.println("NewUser Directory not Created");
			System.exit(0);
		}
		f1 = new File("..\\Bus_Ticket_Booking\\Database\\Userdata\\" + username + "\\info.txt");
		f1.createNewFile();
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(f1));
		bw.write(username + " " + password);
		bw.newLine();
		bw.close();
		bw1.write(username + " " + password + " " + name + " " + dob);
		bw1.newLine();
		bw1.close();

	}

	public boolean isValidDate(String dateStr) {
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}
