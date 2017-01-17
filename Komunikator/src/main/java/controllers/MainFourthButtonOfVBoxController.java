package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sun.util.locale.provider.AvailableLanguageTags;
import utils.FxmlUtils;

public class MainFourthButtonOfVBoxController {

	MainController mainController;
	
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
	
	PersonEditDialogController personEditDialogController;
	private static final String FXML_PERSON_EDIT_DIALOG_FXML = "/fxml/PersonEditDialog.fxml";
	
	private FXMLLoader personEditDialogFxmlLoader;

	public void setAgeTextField(int ageTextFieldText) {
		this.ageTextField.setText(String.valueOf(ageTextFieldText));
	}

	public void setUsernameTextField(String usernameTextField) {
		this.usernameTextField.setText(usernameTextField);
	}

	public void setFirstNameTextField(String firstNameTextField) {
		this.firstNameTextField.setText(firstNameTextField);
	}

	public void setLastNameTextField(String lastNameTextField) {
		this.lastNameTextField.setText(lastNameTextField);
	}

	public void setEmailTextField(String emailTextField) {
		this.emailTextField.setText(emailTextField);
	}

	public void setCountryTextField(String countryTextField) {
		this.countryTextField.setText(countryTextField);
	}

	public void setCityTextField(String cityTextField) {
		this.cityTextField.setText(cityTextField);
	}

	public void setGenderTextField(String genderTextField) {
		this.genderTextField.setText(genderTextField);
	}

	public void setUserProfileImage(Image userProfileImage) {
		this.userProfileImage.setImage(userProfileImage);
	}

	public void setProfileNameLabelText(String profileNameLabelText) {
		profileNameLabel.setText(profileNameLabelText + "'s profile");
	}
	
	public void setYourProfileNameLabelText() {
		profileNameLabel.setText("Your profile");
	}
	


	// Event Listener on Button[#editButton].onAction
	// 	/** PRZESTARZALE */
	@FXML
	public void editButtonOnAction(ActionEvent event) {
		

//		System.out.println("1 " + fxmlLoader.getController().toString());
		//Pane borderPane = FxmlUtils.fxmlLoader("/fxml/PersonEditDialog.fxml");
		
		Parent root = null;
		try {
			//fxmlLoader.setRoot(null);
			//fxmlLoader.setController(null);
			if(personEditDialogFxmlLoader.getRoot() == null) {
				root = (Parent)personEditDialogFxmlLoader.load();
				System.out.println("1 " + personEditDialogFxmlLoader.getController().toString());
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setHeight(600);
				stage.setWidth(450);
				stage.setTitle("Edit profile");
				stage.setMinHeight(600);
				stage.setMinWidth(450);
				stage.getIcons().add(
						   new Image(
						      SplashController.class.getResourceAsStream( "../images/icon.png" ))); 
				stage.setScene(scene);
				stage.show();
	
				PersonEditDialogController controller = personEditDialogFxmlLoader.<PersonEditDialogController>getController();
				controller.setCountryField(countryTextField.getText());
				controller.setAgeField(ageTextField.getText());
				controller.setCityField(cityTextField.getText());
				controller.setFirstNameField(firstNameTextField.getText());
				controller.setLastNameField(lastNameTextField.getText());
				controller.setEmailField(emailTextField.getText());
				controller.setMyImageView(userProfileImage.getImage());
	
			}
			else {
				System.out.println("2 " + personEditDialogFxmlLoader.getController().toString());
				Stage stage = new Stage();
				Scene scene = new Scene(personEditDialogFxmlLoader.getRoot());
				stage.setScene(scene);
				stage.show();

			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	/*	
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
		System.out.println("Person edit controller: " + personEditDialogFxmlLoader.toString());
		stage.show();
		
		*/
	}
	public void createFxmlControllers(){
		personEditDialogFxmlLoader = new FXMLLoader(getClass().getResource(FXML_PERSON_EDIT_DIALOG_FXML)); 
		PersonEditDialogController personEditDialog = personEditDialogFxmlLoader.<PersonEditDialogController>getController();
	}
	/*public void editButtonOnAction(ActionEvent event){
		boolean state = engine.isEditable();
		
		engine.setEditable(!state);
		ip.setEditable(!state);
		port.setEditable(!state);
		schema.setEditable(!state);
		user.setEditable(!state);
		password.setEditable(!state);
	}*/

	
	public MainFourthButtonOfVBoxController() {
		createFxmlControllers();
	}

	
	public void initialize(){

	}
	
	
	

}
