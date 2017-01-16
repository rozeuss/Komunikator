package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.FxmlUtils;

public class MainFourthButtonOfVBoxController {

	private MainController mainController;
	@FXML TextField usernameTextField;
	@FXML TextField firstNameTextField;
	@FXML TextField lastNameTextField;
	@FXML TextField emailTextField;
	@FXML TextField ageTextField;
	@FXML TextField countryTextField;
	@FXML TextField cityTextField;
	@FXML TextField genderTextField;
	@FXML
	private ImageView userProfileImage;
	@FXML
	private Button editButton;
	@FXML Label profileNameLabel;
	
	
	public void setProfileNameLabelText(String profileNameLabelText) {
		profileNameLabel.setText(profileNameLabelText + "'s profile");
	}
	
	public void setYourProfileNameLabelText() {
		profileNameLabel.setText("Your profile");
	}
	
	public void setFirstAndLastName(String firstName, String lastName){
		firstNameTextField.setText(firstName);
		lastNameTextField.setText(lastName);
	}



	// Event Listener on Button[#editButton].onAction
	@FXML
	public void editButtonOnAction(ActionEvent event) {
		Pane borderPane = FxmlUtils.fxmlLoader("/fxml/PersonEditDialog.fxml");
		Stage stage = new Stage();
		Scene scene = new Scene(borderPane);
		stage.setHeight(600);
		stage.setWidth(450);
		stage.setScene(scene);
		stage.setTitle("Edit profile");
		stage.setMinHeight(600);
		stage.setMinWidth(450);
		stage.getIcons().add(
				   new Image(
				      SplashController.class.getResourceAsStream( "../images/icon.png" ))); 
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
	
	

	
	public MainFourthButtonOfVBoxController() {
		
	}




	public void initialize(){
		showPersonDetails();
	}
	
	
	

	
	public void showPersonDetails(){
		usernameTextField.setText("nickname");
		firstNameTextField.setText("first");
		lastNameTextField.setText("last");
		
	}
}
