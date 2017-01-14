package main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.FxmlUtils;

public class Main extends Application {

	private static final String FXML_LOGIN_FXML = "/fxml/Login.fxml";
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_LOGIN_FXML)); 
		Parent root = (Parent)fxmlLoader.load();   
		//Parent root = FXMLLoader.load(getClass().getResource(FXML_LOGIN_FXML));
		LoginController loginController = fxmlLoader.<LoginController>getController();
		
		try{
			Socket socket = new Socket("127.0.0.1", 1056);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			loginController.setSocket(socket, out, in);	
		}catch(Exception e){
			e.printStackTrace();
		}

		
		
		//Pane borderPane = FxmlUtils.fxmlLoader(FXML_LOGIN_FXML);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.setResizable(false);
		// primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(
				   new Image(
				      Main.class.getResourceAsStream( "../images/icon.png" ))); 
		primaryStage.show();
		LOGGER.log(Level.FINE, "Main App window created and shown");

	}

}
