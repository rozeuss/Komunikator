package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.FxmlUtils;

public class SplashController implements Initializable{

	
	@FXML
	private AnchorPane rootAnchorPane;
	
	private Socket socket;
	
	private ObjectOutputStream out;
	
	private ObjectInputStream in;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		new SplashScreen().start();
		
	}
	
	
	class SplashScreen extends Thread{
		private static final String FXML_MAIN_FXML = "/fxml/Main.fxml";

	public void run(){
		try {
			Thread.sleep(100);
			Platform.runLater(new Runnable(){
					@Override
					public void run() {	
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_FXML)); 
						Parent root;
						try {
							root = (Parent)fxmlLoader.load();
							Scene scene = new Scene(root);
							Stage stage = new Stage();
							stage.setScene(scene);
							stage.setHeight(800);
							stage.setWidth(1000);
							stage.setTitle("Yo!");
							stage.setMinHeight(575);
							stage.setMinWidth(400);
							stage.getIcons().add(
							new Image(SplashController.class.getResourceAsStream( "../images/icon.png" ))); 
							stage.show();
							rootAnchorPane.getScene().getWindow().hide();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						MainController mainController = fxmlLoader.<MainController>getController();
						try{
							mainController.setSocket(socket, out, in);	
						}catch(Exception e){
							e.printStackTrace();
						}
						mainController.createFxmlControllers();
					}
				});
				} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
	
	public void setSocket(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
		this.socket = socket;
		this.in = in;
		this.out = out;
	}
}
