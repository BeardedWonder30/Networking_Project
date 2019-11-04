//Group 5:
//Bailey, Garrett
//Buffkin, David
//Matarese, Domninic
//Simpson, Charles

//Workstations
//cisvm-wkstZerind-108 Server
//cisvm-wkstZerind-109 Client

//IP's
//192.168.101.108 Server
//192.168.101.109 Client

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//Verify command line argument offers exactly two args, one for IP and one for Port
		if (args.length != 2) {
			System.out.println("Incorrect command line argument. Please enter Server IP and Port only.");
			System.exit(1);
		}
		//Declare IP
		InetAddress serverIP;
		//Verification of IP address
		try {
			serverIP = InetAddress.getByName(args[0]);}
		catch (UnknownHostException e) {
			System.out.println("Server IP not valid");
			return;
		}
		//Declare Port
		int port;
		//Verification of Port number
		try {
			port = Integer.parseInt(args[1]);}
		//handles non-int inputs
		catch(InputMismatchException e) {
			System.out.println("Port number not valid");
			return;
		}

		//Check port number validity
		while (port <= 0 || port > 65535) {
			System.out.print("Invalid port number! Enter a port between 0 and 65536: ");
			try {port = input.nextInt();}
			//handles non-int inputs
			catch(java.util.InputMismatchException e) {
				System.out.println("Port out of bounds. Enter a port between 0 and 65536");
				return;
			}
		}
		//Initializes number of Clients to be selected by user.
		int numOfclients;
		System.out.print("Enter the number of clients to run: ");
		//Only accepts Int for # of Clients
		while(!input.hasNextInt()) {
			System.out.println("Invalid number of clients");
			input.next();
			System.out.print("Enter the number of clients to run: ");
		}
		numOfclients = input.nextInt();
		//Number of clients cannot be negative or 0. Exit program.
		if (numOfclients < 1) {
			System.out.println("Number of clients must be at least 1");
			return;
		}
		//Initializes new ClientTool with IP/Port from command line and # of Clients from User
		ClientTool ct = new ClientTool(serverIP, port, numOfclients);

		//Initialize quitLoop to false unless user selects 7 - Quit
		boolean quitLoop = false;
		////////////////////////////////////
		//while executes until quitLoop true
		////////////////////////////////////
		while (quitLoop == false) {
			//Display Menu
			printMenu();
			//Prompt the user for a command
			System.out.print("Please select a number from the above list: ");
			//Takes user-selected choice from client. Closes program if not int
			int choice;
			try {choice = input.nextInt();
			ct.setSelection(choice);}
			catch(InputMismatchException e){
				System.out.println("Not a number. Have a nice day.");
				return;
			}
			//Test user input for command validity
			//If user command is invalid, inform the user and redisplay the menu
			while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7 ) {
				printMenu();
				System.out.print("Invalid selection. Please select a number from the above list: ");
				try{choice = input.nextInt();
				ct.setSelection(choice);}
				catch(InputMismatchException e){
					System.out.println("Not a number. Have a nice day");
					System.exit(1);
				}
			}
			//ends do-while loop
			if (choice == 7) {
				quitLoop = true;
				System.out.println("Menu Closed. Have a nice day.");
				System.exit(1);
			}
			//Generates response information for client via Threading # of clients to run
			ct.simulateClients();
			//Prints the final appended Server Response
			System.out.print(ct.getServerResponse());
			System.out.flush();
			//Prints the final average server response time
			System.out.println("\nMean server response time (ms): " + ct.getMeanResponseTime());
			//////////////////
			//End of 'quitLoop'
			//////////////////
		} 
	}
	//Client Menu
	public static void printMenu() {
		System.out.println("1. Host current Date and Time");
		System.out.println("2. Host uptime");
		System.out.println("3. Host memory use");
		System.out.println("4. Host Netstat");
		System.out.println("5. Host current users");
		System.out.println("6. Host running processes");
		System.out.println("7. Quit");
	}
}
