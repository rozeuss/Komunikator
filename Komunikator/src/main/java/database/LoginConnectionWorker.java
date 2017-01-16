package database;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import controllers.*;
import transferData.*;
import transferDataContainers.Confirmation;
import transferDataContainers.UserDataRequest;

public class LoginConnectionWorker implements Runnable {
	
	private Socket socket;
	private Receiver receiver;
	private Sender sender;
	private Object dataObject;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private LoginController loginController;
	private UserDataRequest userDataRequest;
	
	public LoginConnectionWorker(Socket socket, ObjectOutputStream out, ObjectInputStream in, Object dataObject, LoginController loginController) throws IOException{
		this.socket = socket;
		this.out = out;
		this.in = in;
		this.dataObject = dataObject;
		this.receiver = new Receiver();
		this.sender = new Sender(out);
		this.loginController = loginController;
		this.userDataRequest = new UserDataRequest(loginController.getUser());
	}

	@Override
	public void run() {
		boolean isResponseReceived = false;
		Object response = null;
		
		try {
			sender.send(dataObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(!isResponseReceived) {
			try {
				response = receiver.read(in);
				if(response != null){
					isResponseReceived = true;
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (EOFException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
			if(response instanceof Confirmation){
				loginController.setLoginStatus((Confirmation)response);
				System.out.println(((Confirmation) response).getMessage());
				try {
					sender.send(userDataRequest);
				} catch (IOException e) {
					e.printStackTrace();
				}
				loginController.checkLoginStatus();
			}
		
	}

}
