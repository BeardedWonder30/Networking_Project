import java.net.*;
import java.io.*;

public class theThread extends Thread {
    private Socket Socket = null;

        public theThread(Socket Socket) {
            super("theThread");
            this.Socket = Socket;
        }
        public void run(){
            //while (true) {
                ////////////////////////////////////////////
                //////beginning of oracle source code///////
                ////////////////////////////////////////////
                try (
                        
                        PrintWriter out = new PrintWriter(Socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
                    ) {
                    String inputLine;
                    //Initialize string for invalid input !(1-6)
                    String valid = "Please enter a valid input";
                    //Iterates while-loop whenever there is a request from a Client
                    while ((inputLine = in.readLine()) != null) {
                        //Selection 1 Host current Date and Time
                        if(inputLine.equals("1")) {
                            String[] date = new String[] {"/bin/bash", "-c", "date", "with", "args"};
                            Process proc = new ProcessBuilder(date).start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                            String send= "";
                            while((send = reader.readLine()) != null) {
                                out.println(send);
                            }
                            //endFlag signifies to client to exit while-loop
                            out.println("endFlag");
                        }
                        //Selection 2 Host uptime
                        else if(inputLine.equals("2")) {
                            String[] uptime  = new String[] {"/bin/bash", "-c", "uptime", "with", "args"};
                            Process proc = new ProcessBuilder(uptime).start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                            String send= "";
                            while((send = reader.readLine()) != null) {
                                out.println(send);
                            }
                            //endFlag signifies to client to exit while-loop
                            out.println("endFlag");		
                        }
                        //Selection 3 Host memory use
                        else if(inputLine.equals("3")) {
                            String[] memory  = new String[] {"/bin/bash", "-c", "free", "with", "args"};
                            Process proc = new ProcessBuilder(memory).start();
    
                            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                            String send = "";
                            while((send = reader.readLine()) != null) {
                                out.println(send);
                            }
                            //endFlag signifies to client to exit while-loop
                            out.println("endFlag");
                        }
                        //Selection 4 Host Netstat
                        else if(inputLine.equals("4")) {
                            String[] netStat  = new String[] {"/bin/bash", "-c", "netstat", "with", "args"};
                            Process proc = new ProcessBuilder(netStat).start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                            String send= "";
                            while((send = reader.readLine()) != null) {
                                out.println(send);
                            }
                            //endFlag signifies to client to exit while-loop
                            out.println("endFlag");
                        }
                        //Selection 5 Host current users
                        else if(inputLine.equals("5")) {
                            String[] currentUser  = new String[] {"/bin/bash", "-c", "users", "with", "args"};
                            Process proc = new ProcessBuilder(currentUser).start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                            String send= "";
                            while((send = reader.readLine()) != null) {
                                out.println(send);
                            }
                            //endFlag signifies to client to exit while-loop
                            out.println("endFlag");
                        }
                        //Selection 6 Host running processes
                        else if(inputLine.equals("6")) {
                            String[] processes  = new String[] {"/bin/bash", "-c", "ps -e", "with", "args"}; Process proc = new ProcessBuilder(processes).start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                            String send= "";
                            while((send = reader.readLine()) != null) {
                                out.println(send);
                            }
                            //endFlag signifies to client to exit while-loop
                            out.println("endFlag");
                        }
                        //Verification for invalid input !(1-6)
                        else {
                            out.println(valid);
                        }
                    }
                    Socket.close();
                }
                
               catch (IOException e) {
                    System.out.println("Failed");
                    /*System.out.println("Exception caught when trying to listen on port "
                            + portNumber + " or listening for a connection");
                    System.out.println(e.getMessage());*/
                }
                ////////////////////////////////////////////
                //////end of oracle source code/////////////
                ////////////////////////////////////////////
       // }
                
    }
}