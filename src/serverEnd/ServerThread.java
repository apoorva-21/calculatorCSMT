package serverEnd;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import calculatorLogic.BusinessLogic;

public class ServerThread extends Thread {
	private Socket currentThreadSocket;
	private String currentClientID;
	private ArrayList <String> clientHistory = new ArrayList<String>();
	private DataInputStream dIn;
	private DataOutputStream dOut;
	
	public ServerThread(Socket socket,int count) throws IOException
	{
		this.currentThreadSocket = socket;
		this.currentClientID = "Client"+ count;
		this.dOut = new DataOutputStream(this.currentThreadSocket.getOutputStream());
		this.dIn = new DataInputStream(this.currentThreadSocket.getInputStream());
	}
	
	public void printClientHistory() throws IOException
	{
		if(clientHistory.size() > 0)
		{
			dOut.writeUTF(currentClientID +", your calculation history :");
			
			for(int i = 0; i < clientHistory.size(); i++)
				dOut.writeUTF(clientHistory.get(i));
			
			dOut.writeUTF("fin");
			
			dOut.writeUTF(currentClientID +", you have been disconnected!");
		}
	}
	
	public void handleClientIO() throws IOException
	{
		dOut.flush();
		String clientInput = "";
		BusinessLogic solver = new BusinessLogic();
		while(!clientInput.equals("exit")){
			if((clientInput = dIn.readUTF()) != null)
			{
				if(clientInput.equals("exit"))
					break;
				String result = solver.solve(clientInput);
				clientHistory.add(clientInput + " = " + result);
				dOut.writeUTF("Result = " + result);
			}
		}
	}
	
	public void releaseResources() throws IOException
	{
		dIn.close();
		dOut.close();
		currentThreadSocket.close();
	}
	
	public void run()
	{
		try
		{
			//create streams for data flow between client-server
			handleClientIO();
			printClientHistory();
			releaseResources();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
