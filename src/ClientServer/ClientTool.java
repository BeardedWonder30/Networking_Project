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
public class ClientTool {
	//Declare clientsArray array of Clients
	public Client[] clientsArray;
	public ClientTool(InetAddress hostName, int portNumber, int numOfclients) {
		//Increase the number of clientsArray as follows: 1, 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100.
		//Declares an array of type Client
		clientsArray = new Client[numOfclients];
		//For each Client, initializes the hostName and portNumber to the same IP/Port 
		for (int i = 0; i < numOfclients; i++) {
			clientsArray[i] = new Client(hostName, portNumber);
		}
	}
	//Client Threading
	public void simulateClients() {
		//Declare 'threadsArray' an array of threadsArray of array length equivalent to # of clientsArray selected by user
		Thread[] threadsArray = new Thread[clientsArray.length];
		//For each Thread object in array 'threadsArray', assigns each Client object from array 'clientsArray' to it
		for (int i = 0; i < threadsArray.length; i++) {
			threadsArray[i] = new Thread(clientsArray[i]);
		}
		//For each Thread object in array 'threadsArray', start the Thread
		for (Thread thread : threadsArray) {
			thread.start();
		}
		//For each Thread object in array 'threadsArray', will put each thread on wait until the previous thread has been executed
		for (Thread thread : threadsArray) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.out.println("Threading Error");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	//serverResponse Getter from clientsArray[]
	public String getServerResponse() {
		//first client as the response
		return clientsArray[0].getServerResponse();
	}
	//Getter - Average response time for each Client's request when multiple clients involved
	public long getMeanResponseTime() {
		long responseTime = 0;
		//For each Client in 'clientsArray', adds it's response time to the total
		for(Client client: clientsArray) {
			responseTime += client.getResponseTime();
		}
		//Divides the total by the number of clients which yields the average
		return responseTime / clientsArray.length;
	}
	
	//Selection setter
	public void setSelection(int selectedRequest){
		//Sets the selection to the same selection for every Client in 'clientsArray'
		for (Client client: clientsArray) {
			client.setSelection(selectedRequest);
		}
	}
}