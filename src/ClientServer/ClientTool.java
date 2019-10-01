import java.net.InetAddress;
import java.util.stream.Stream;

public class ClientTool {
	public Client[] clients;
	public ClientTool(InetAddress hostName, int portNumber, int numOfClients) {
		//Increase the number of clients as follows: 1, 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100.
		clients = new Client[numOfClients];
		for (int i = 0; i < numOfClients; i++) {
			clients[i] = new Client(hostName, portNumber);
		}
	}
	//threading method
	public void simulateClients() {
		//initialize threads
		Thread[] threads = new Thread[clients.length];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(clients[i]);
		}
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.out.println("Threading Error");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	//Getter from clients[]
	public Stream<String> getServerResponse() {
		//first client as the response
		return clients[0].getServerResponse();
	}
	//
	public long getMeanResponseTime() {
		long responseTime = 0;

		for(Client client: clients) {
			responseTime += client.getResponseTime();
		}

		return responseTime / clients.length;
	}
	
	//Selection setter
	public void setSelection(int selectedRequest){
		for (Client client: clients) {
			client.setSelection(selectedRequest);
		}
	}



}


