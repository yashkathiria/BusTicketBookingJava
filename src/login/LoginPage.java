package login;


import java.util.*;

public class LoginPage {

	public void printbigsymbol() {
		System.out.println(
				"     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                                       ***********************");
		System.out.println("                                       ***********************");
		System.out.println("                                       ***                 ***");
		System.out.println("                                       * *                 * *");
		System.out.println("                                       **                   **");
		System.out.println("                                       *                     *");
		System.out.println("                                       *                     *");
		System.out.println("                                       *                     *");
		System.out.println("                                       ***********************");
		System.out.println("                                       ****               ****");
		System.out.println("                                       ***********************");
		System.out.println("                                       *****             *****");
		System.out.println("                                       *****             *****");
		System.out.println(
				"     ---------------------------------------------------------------------------------------------");
		System.out.println(
				"   ______                _______ _       _                    ______              _     _             \r\n"
						+ "   (____  \\              (_______|_)     | |            _     (____  \\            | |   (_)            \r\n"
						+ "    ____)  )_   _  ___       _    _  ____| |  _ _____ _| |_    ____)  ) ___   ___ | |  _ _ ____   ____ \r\n"
						+ "   |  __  (| | | |/___)     | |  | |/ ___) |_/ ) ___ (_   _)  |  __  ( / _ \\ / _ \\| |_/ ) |  _ \\ / _  |\r\n"
						+ "   | |__)  ) |_| |___ |     | |  | ( (___|  _ (| ____| | |_   | |__)  ) |_| | |_| |  _ (| | | | ( (_| |\r\n"
						+ "   |______/|____/(___/      |_|  |_|\\____)_| \\_)_____)  \\__)  |______/ \\___/ \\___/|_| \\_)_|_| |_|\\___ |\r\n"
						+ "                                                                                                (_____|");

		System.out.println("\n\n");

	}

	public int loginsignup(Scanner s) {
	
		// Scanner s = new Scanner(System.in);
		// BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		// DataInputStream bre=new DataInputStream(System.in);
		System.out.println("               _    ____ ____ _ _  _                ____ _ ____ _  _    _  _ ___  \r\n"
				+ "               |    |  | | __ | |\\ |                [__  | | __ |\\ |    |  | |__] \r\n"
				+ "               |___ |__| |__] | | \\|                ___] | |__] | \\|    |__| |    \r\n"
				+ "                                                                                  ");

		int choice = -1;
			boolean success=false;
			while (!success) {
				System.out.println("\t------------------------------------------------------------------------------");
				System.out.println("\t\t\t1. LOGIN (as a existing User)");
				System.out.println("\t\t\t2. SIGN UP (as a new User)");
				System.out.println("\t\t\t3. EXIT");
				System.out.println("\t------------------------------------------------------------------------------");
				try {
					System.out.println("Enter Choice: ");
					// s.nextLine();
					// s.next();
					choice = s.nextInt();
					// s.nextLine();
					success = true;
				} catch (Exception e) {
					System.out.println("Enter valid Choice!");
					s.nextLine();
				}
		}
		return choice;
	}

}
