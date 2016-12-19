package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Person;
import utils.FxmlUtils;
import javafx.scene.control.TextArea;

public class MainFourthButtonOfVBoxController {
	@FXML
	private ImageView userProfileImage;
	@FXML
	 TextField userUserName;
	@FXML
	private TextField userFirstName;
	@FXML
	private TextField userLastName;
	@FXML
	private TextField userUserName3;
	@FXML
	private TextField userUserName4;
	@FXML
	private Label userAbout;
	@FXML
	private Button editButton;
	@FXML TextField userEmail;
	@FXML TextField userAge;
	@FXML TextField userFrom;
	@FXML TextArea aboutMeTextArea;

	// Event Listener on Button[#editButton].onAction
	@FXML
	public void editButtonOnAction(ActionEvent event) {
		Pane borderPane = FxmlUtils.fxmlLoader("/fxml/PersonEditDialog.fxml");
		Stage stage = new Stage();
		Scene scene = new Scene(borderPane);
		stage.setHeight(800);
		stage.setWidth(1000);
		stage.setScene(scene);
		stage.setTitle("Options");
		stage.setMinHeight(575);
		stage.setMinWidth(300);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
	
	public void initialize(){
		//aboutMeTextArea.setWrapText(true);
		showPersonDetails();
	}
	
	
	
	private MainController mainController;
	
	
	public void showPersonDetails(){
		userUserName.setText("nickname");
		userFirstName.setText("first");
		userLastName.setText("last");
		
	}
}
