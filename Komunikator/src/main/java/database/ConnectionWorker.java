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
import transferDataContainers.LoginStatus;

public class ConnectionWorker implements Runnable {
	
	Socket socket;
	Receiver receiver;
	Sender sender;
	Object dataObject;
	ObjectOutputStream out;
	ObjectInputStream in;
	Object controller;
	
	public ConnectionWorker(Socket socket, ObjectOutputStream out, ObjectInputStream in, Object dataObject, Object controller) throws IOException{
		this.socket = socket;
		this.out = out;
		this.in = in;
		this.dataObject = dataObject;
		this.controller = controller;
		this.receiver = new Receiver();
		this.sender = new Sender(out);
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
		
		if (controller instanceof LoginController) {
			if(response instanceof LoginStatus){
				((LoginController) controller).setLoginStatus((LoginStatus)response);
				System.out.println(((LoginStatus) response).getMessage());
				//((LoginController) controller).checkLoginStatus();
			}
		}
		
	}

}
