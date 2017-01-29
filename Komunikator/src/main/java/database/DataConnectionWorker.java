package database;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import controllers.LoginController;
import controllers.MainController;
import controllers.SplashController;
import transferData.Receiver;
import transferData.Sender;
import transferDataContainers.*;

public class DataConnectionWorker implements Runnable {

	private Socket socket;
	private Receiver receiver;
	private Object dataObject;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private LoginController loginController;
	private MainController mainController;
	private SplashController splashController;
	private boolean isRunning = true;

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public DataConnectionWorker(Socket socket, ObjectOutputStream out, ObjectInputStream in, LoginController loginController) throws IOException {
		this.socket = socket;
		this.out = out;
		this.in = in;
		this.loginController = loginController;
		this.mainController = loginController.getMainController();
		this.splashController = loginController.getSplashController();
		this.receiver = new Receiver();   
	}

	@Override
	public void run() {
		
		while(isRunning) {
			try {			
				dataObject = receiver.read(in);
				
				if(dataObject instanceof OverdueInvitations){
					mainController.addInvitations((OverdueInvitations)dataObject);
					splashController.getSplashScreen().setIsDataLoaded(true);
				}
				else if(dataObject instanceof User){
					mainController.setUser((User)dataObject);
				}
				else if(dataObject instanceof Friends){
					mainController.addFriends((Friends)dataObject);
				}
				else if(dataObject instanceof UnreadMessages){
					mainController.setUnreadMessages((UnreadMessages)dataObject);

				} else if(dataObject instanceof FoundedUsers){	
					mainController.setFoundedUsers((FoundedUsers)dataObject);
				}
				else if(dataObject instanceof Invitation){
					mainController.setNewInvitation((Invitation)dataObject);
				}
				else if(dataObject instanceof NewFriend){
					
					System.out.println("dostalam nowego przyjaciela");
					NewFriend friend = (NewFriend)dataObject;
					System.out.println(friend.getFriend().getUserName());
					
				}
				else if(dataObject instanceof Message){
					mainController.setMessage((Message)dataObject);
				}
			} 
			catch(SocketException se)
			{
				System.out.println("CONNECTION LOST");
			}
			catch (ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
			}

		}
	}

	public void close() {
		this.setRunning(false);
		try {
			this.in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}