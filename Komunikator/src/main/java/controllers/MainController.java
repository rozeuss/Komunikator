package controllers;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class MainController {


	@FXML
	private MainRightVBoxController mainRightVBoxController;
	
	@FXML
	private BorderPane borderPane;

	@FXML 
	private Label welcomeLabel;

	@FXML MenuItem menuItemClose;

	@FXML MenuItem menuItemOptions;

	@FXML MenuItem menuItemProfile;

	@FXML MenuItem menuItemSignOut; 
	
	
	
	public void setCenter(String fxmlPath){
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		borderPane.setCenter(parent);
	}



	@FXML
	private void initialize() {
		mainRightVBoxController.setMainController(this);
		
	}



	@FXML public void menuItemCloseOnAction() {
		
		Platform.exit();

	}



	@FXML public void menuItemOptionsOnAction() {
		
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/Options.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setHeight(800);
		stage.setWidth(1000);
		stage.setScene(scene);
		stage.setTitle("Options");
		stage.setMinHeight(575);
		stage.setMinWidth(300);
	//	stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}



	@FXML public void menuItemProfileOnAction() {
	}



	@FXML public void menuItemSignOutOnAction() {
		
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setHeight(486);
		stage.setWidth(586);
		stage.centerOnScreen();
		stage.setScene(scene);
		stage.setTitle("Options");
		stage.setMinHeight(575);
		stage.setMinWidth(300);
	//	stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		borderPane.getScene().getWindow().hide();
		
	}
	
	
}
