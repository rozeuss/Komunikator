package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SplashController implements Initializable{

	
	@FXML
	private AnchorPane rootAnchorPane;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		new SplashScreen().start();
		
	}
	
	
	class SplashScreen extends Thread{
		public void run(){
			try {
				Thread.sleep(3000);
				
				Platform.runLater(new Runnable(){

					@Override
					public void run() {
						Parent parent = null;
						try {
							parent = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Stage stage = new Stage();
						Scene scene = new Scene(parent);
						stage.setScene(scene);
						stage.setTitle("Main Frame");
						stage.setResizable(false);
					//	stage.initStyle(StageStyle.UNDECORATED);
						stage.show();
						
						rootAnchorPane.getScene().getWindow().hide();
					}
					
					
					
				});

			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
