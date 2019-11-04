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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
	public static void main(String[] args) throws IOException {
		//Verify Server only has one args, the Port #
		if (args.length != 1) {
			System.err.println("Usage: java Server <port number>");
			System.exit(1);
		}
		//Initializes the port number in command line argument
		int portNumber = Integer.parseInt(args[0]);
		//Initialize server socket on port #
		//ServerSocket serverSocket = new ServerSocket(portNumber);
		//While-loop will always be true so the Server is always accepting requests
		//This functions to keep the server host 'always running'
		boolean listening = true;
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
			while(listening){
				new theThread(serverSocket.accept()).start();
			}
        	
		}		
			
	}
}