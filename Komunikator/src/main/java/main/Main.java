package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import controllers.LoginController;
import controllers.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import transferData.Sender;
import utils.FxmlUtils;

public class Main extends Application {

	private static final String FXML_LOGIN_FXML = "/fxml/Login.fxml";
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	FXMLLoader fxmlLoader;
	
	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		fxmlLoader = new FXMLLoader(getClass().getResource(FXML_LOGIN_FXML)); 
		Parent root = (Parent)fxmlLoader.load();
		LoginController loginController = fxmlLoader.<LoginController>getController();
<<<<<<< HEAD
		
		try{
			//Socket socket = new Socket("192.168.1.102", 1056);
			Socket socket = new Socket("127.0.0.1", 1056);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			loginController.setSocket(socket, out, in);	
			System.out.println("login  " +  in.getClass());
			System.out.println(out.getClass());
			System.out.println(socket.getClass());
			loginController.setSettingsOfMainController();
		}catch(Exception e){
			e.printStackTrace();
		}
		
=======

		System.out.println("Wys³any fxmlLoader: " + fxmlLoader);
		loginController.setLoginFXMLLoader(fxmlLoader);
>>>>>>> NewWorkingBranch
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Yo! - Login");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(
				   new Image(
				      Main.class.getResourceAsStream( "../images/icon.png" ))); 
		primaryStage.show();
		LOGGER.log(Level.FINE, "Main App window created and shown");

	}
	
	@Override
	public void stop(){

	    System.out.println("Stage is closing");
	    System.out.println("AAAAAAAAAAAA " +fxmlLoader.<LoginController>getController().getSocket());
	    
	    if(fxmlLoader.<LoginController>getController().getSocket()!=null){
			Sender sender = new Sender(fxmlLoader.<LoginController>getController().getOut());
			try {
				
				sender.send("quit");
				fxmlLoader.<LoginController>getController().getDataConnectionWorker().close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	/*    try {
			fxmlLoader.<LoginController>getController().getIn().close();
	    	System.out.println("dziala");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			fxmlLoader.<LoginController>getController().getOut().close();
	    	System.out.println("znow dziala");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			fxmlLoader.<LoginController>getController().getSocket().close();
			System.out.println("tez dziala");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    }
	    */
	    try {
			if(fxmlLoader.<LoginController>getController().getIn() != null)
				fxmlLoader.<LoginController>getController().getIn().close();
			if(fxmlLoader.<LoginController>getController().getOut() != null)
				fxmlLoader.<LoginController>getController().getOut().close();
			if(fxmlLoader.<LoginController>getController().getSocket() != null)
				fxmlLoader.<LoginController>getController().getSocket().close();
		} catch (IOException e) {
	
		}
	    
	    
		Platform.exit();
		System.exit(0);
		
		
		
		
	}
	
	

	}
}
