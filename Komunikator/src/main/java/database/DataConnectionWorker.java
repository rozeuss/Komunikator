package database;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	
	
	public DataConnectionWorker(Socket socket, ObjectOutputStream out, ObjectInputStream in, LoginController loginController) throws IOException {
		this.socket = socket;
		this.out = out;
		this.in = in;
		this.loginController = loginController;
		this.mainController = loginController.getMainController();
		this.splashController = loginController.getSplashController();
		this.receiver = new Receiver();
		//dataObject = new Object();
	}

	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("DataConnectionWorker - przed czytaniem obiektu");
				dataObject = receiver.read(in);
				
				if(dataObject instanceof UserData){
					mainController.setUserData((UserData)dataObject);
					splashController.getSplashScreen().setIsDataLoaded(true);
				}
			} 
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}