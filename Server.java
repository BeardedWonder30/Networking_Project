import java.net.*;
import java.lang.*;
import java.util.*;



import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
 
public class Server {
    public static void main(String[] args) throws IOException {
         
        /*if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }*/
         
        int portNumber = 8000; //Integer.parseInt(args[0]);
         
        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);//Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();     
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            String valid = "Please enter a valid input";
            while ((inputLine = in.readLine()) != null) {
            	if(inputLine.equals("1")) {
            		String[] date = new String[] {"/bin/bash", "-c", "date", "with", "args"};
					Process proc = new ProcessBuilder(date).start();
					BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
					String send= "";
                    while((send = reader.readLine()) != null) {
                            out.println(send + "\n");
                    }
            	}
            	
            	else if(inputLine.equals("2")) {
					String[] uptime  = new String[] {"/bin/bash", "-c", "uptime", "with", "args"};
					Process proc = new ProcessBuilder(uptime).start();
					BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
					String send= "";
                    while((send = reader.readLine()) != null) {
                            out.println(send + "\n");
                    }
            	}
            	
            	else if(inputLine.equals("3")) {
            		String[] memory  = new String[] {"/bin/bash", "-c", "free", "with", "args"};
					Process proc = new ProcessBuilder(memory).start();

					BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
					String send = "";
					while((send == reader.readLine() != null) {
						while(send =! null){
							out.println(send);
						}
						
					}
					
				}
                    
            	
            	
            	else if(inputLine.equals("4")) {
            		String[] netStat  = new String[] {"/bin/bash", "-c", "netStat", "with", "args"};
					Process proc = new ProcessBuilder(netStat).start();
					BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
					String send= "";
                    while((send = reader.readLine()) != null) {
                            out.println(send + "\n");
                    }
            	}
            	
            	else if(inputLine.equals("5")) {
            		String[] currentUser  = new String[] {"/bin/bash", "-c", "user", "with", "args"};
					Process proc = new ProcessBuilder(currentUser).start();
					BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
					String send= "";
                    while((send = reader.readLine()) != null) {
                            out.println(send + "\n");
                    }
            	}
            	
            	else if(inputLine.equals("6")) {
            		String[] processes  = new String[] {"/bin/bash", "-c", "ps", "with", "args"};
					Process proc = new ProcessBuilder(processes).start();
					BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
					String send= "";
                    while((send = reader.readLine()) != null) {
                            out.println(send + "\n");
                    }
            	}
            	
            	else if(inputLine.equals("7")) {
            		out.println("Disconnected");
            	break;
            	}
            	
            	else {
            		out.println(valid);
            	}
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
