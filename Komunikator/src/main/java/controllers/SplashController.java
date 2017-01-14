package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.FxmlUtils;

public class SplashController implements Initializable{

	
	@FXML
	private AnchorPane rootAnchorPane;
	
	
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
					/*	Parent parent = null;
						try {
							parent = FXMLLoader.load(getClass().getResource(FXML_MAIN_FXML));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						
						Pane borderPane = FxmlUtils.fxmlLoader(FXML_MAIN_FXML);
						Stage stage = new Stage();
						Scene scene = new Scene(borderPane);
						stage.setHeight(800);
						stage.setWidth(1000);
						stage.setScene(scene);
						stage.setTitle("Yo!");
						stage.setMinHeight(575);
						stage.setMinWidth(400);
						stage.getIcons().add(
								   new Image(
								      SplashController.class.getResourceAsStream( "../images/icon.png" ))); 
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
