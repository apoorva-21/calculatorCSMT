package serverEnd;

import java.io.*;
import java.net.*;
//import java.util.Scanner;
public class Server {
//	private static String hostName = "127.0.0.1";
	private int portNo;
	private int clientCounter;
	private ServerSocket ss;
	
	public Server(int portNo) throws IOException
	{
		this.portNo = portNo;
		this.ss = new ServerSocket(portNo);
		this.clientCounter = 0;
	}
	
	public Socket awaitRequests() throws IOException
	{
		Socket clientConnection = this.ss.accept();
		this.clientCounter ++;
		return clientConnection;
	}
	
	public void handleRequests()
	{
		try 
		{	
			Socket clientConnection = awaitRequests();
			ServerThread serverThread = new ServerThread(clientConnection, clientCounter); //instantiate a new Server Thread to serve the newly connected client
			serverThread.start();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Server server = new Server(8080);
		System.out.println("Server up and running on port:\t" + server.portNo);

		while(true)//be ever ready to handle client requests
			server.handleRequests();
	}	
}
