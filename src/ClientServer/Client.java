import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
	InetAddress hostName;
	int portNumber;
	long initialTime;
	long finalTime;
	long responseTime;
	int selection;
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
			while (in.readLine() != null) {
			serverResponse = in.readLine();
			}
			//Final time after socket reply
			finalTime = System.currentTimeMillis();
			//Elapsed Response time
			responseTime = finalTime - initialTime;
			//5. Close Socket
			out.flush();
			echoSocket.close();
		}
		catch (Exception e) {
			System.out.println("Can't open socket at address: " + hostName + " and port: " + portNumber); 
			e.printStackTrace();
			System.exit(-1);
		}
		////////////////////////////////////////////
		//////beginning of oracle source code///////
		////////////////////////////////////////////
	}
}

//Workstations
//	cisvm-wkstZerind-108
//	cisvm-wkstZerind-109

//IP's
//	192.168.101.108
//	192.168.101.109

//https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
//1. Open socket.
//2. Open an input stream and output stream to the socket.
//3. Read from and write to the stream according to the server's protocol.
//4. Close the streams
//5. Close the socket.