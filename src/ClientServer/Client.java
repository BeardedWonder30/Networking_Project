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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
public class Client implements Runnable {
	//Host IP
	InetAddress hostName;
	int portNumber;
	long initialTime;
	long finalTime;
	long responseTime;
	//Client selection
	int selection;
	//Response from server to be printed to client
	String serverResponse;
	//Client constructor
	public Client(InetAddress host, int port) {
		this.hostName = host;
		this.portNumber = port;
	}
	//Selection setter method
	public void setSelection(int number) {
		selection = number;
	}
	//Response getter method
	public String getServerResponse() {
		return serverResponse;
	}
	//Response Time getter method
	public long getResponseTime() {
		return responseTime;
	}
	//Override Runnable
	@Override
	public void run() {
		////////////////////////////////////////////
		//////beginning of oracle source code///////
		////////////////////////////////////////////
		try (
				//1. Open Socket
				Socket echoSocket = new Socket(hostName, portNumber);
				//2. Open and input stream and output stream to the socket
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				) 
		{
			//Initial time prior to socket request
			initialTime = System.currentTimeMillis();
			//3. Read from and write to the stream according to the server's protocol.
			//Write selection to the stream
			out.println(selection);
			
			//Read from response from the stream
			//Temp response for each readLine()
			String tempResponse;
			serverResponse = "";
			//Loop to read every readLine() from buffer unless it is null. 
			//Prevents case where socket ends up preemptively closing for some reason prior to the endFlag
			while ((tempResponse = in.readLine()) != null) {
				//When readLine() encounters "endFlag" at the end of the response, the loop breaks
				if (tempResponse.equals("endFlag")) {
					break;
				}
				//Append tempResponse to the serverResponse for every readLine()
				serverResponse += tempResponse;
				serverResponse += "\n";
			}

			//Final time after socket reply
			finalTime = System.currentTimeMillis();
			//Calculate Elapsed Response time
			responseTime = finalTime - initialTime;
			//5. Close Socket
			out.flush();
			echoSocket.close();
		}
		catch (Exception e) {
			System.out.println("Can't open socket at address: " + hostName + " and port: " + portNumber); 
			e.printStackTrace();
		}
		////////////////////////////////////////////
		//////end of oracle source code/////////////
		////////////////////////////////////////////
	}
}
//https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
//1. Open socket.
//2. Open an input stream and output stream to the socket.
//3. Read from and write to the stream according to the server's protocol.
//4. Close the streams
//5. Close the socket.
