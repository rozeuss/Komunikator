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
				System.out.println("DataConnectionWorker - przed czytaniem obiektu");
				
				dataObject = receiver.read(in);
				
				if(dataObject instanceof OverdueInvitations){
					mainController.addInvitations((OverdueInvitations)dataObject);
					OverdueInvitations dom = (OverdueInvitations)dataObject;
					System.out.println("dom  " + dom.getClass());
					System.out.println("iwitycaje  SOM NIESTETY PUSTE :("+dom.getInvitations());
					ArrayList<Invitation> inv = dom.getInvitations();
					System.out.println("Odbieram paczke zaproszen ");
					System.out.println("size listy : W DATACONN " + inv.size());
					splashController.getSplashScreen().setIsDataLoaded(true);
				}
				else if(dataObject instanceof User){
					mainController.setUser((User)dataObject);
				}
				else if(dataObject instanceof Friends){
					mainController.addFriends((Friends)dataObject);
				}
				else if(dataObject instanceof UnreadMessages){

				} else if(dataObject instanceof FoundedUsers){	
					mainController.setFoundedUsers((FoundedUsers)dataObject);
				}
				else if(dataObject instanceof InvitationConfirmation){
					System.out.println("Odbieram invitation confirmation ");	
				}
				else if(dataObject instanceof Invitation){
					System.out.println("Odbieram invitation ");	
				}
				else if(dataObject instanceof NewFriend){
					
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
}