package controllers;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sun.util.locale.provider.AvailableLanguageTags;
import transferData.Sender;
import transferDataContainers.EditedUserData;
import transferDataContainers.User;
import utils.FxmlUtils;
import validators.AgeValidator;
import validators.EmailValidator;

public class MainFourthButtonOfVBoxController {

	
	
	
	MainController mainController;
	
	private FXMLLoader mainFXMLLoader;
	
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



	@FXML
	private Button applyButton;

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
	

/*	Scene SCENA;

	@FXML
	public void editButtonOnAction(ActionEvent event) {	
	if(flag == true){
			flag = false;
			System.out.println("2 PERSON EDIT" + personEditDialogFxmlLoader.getController().toString());
			
			Stage stage = new Stage();
			//Scene scene = new Scene(personEditDialogFxmlLoader.getRoot());
			Scene scene = new Scene(personEditDialogRoot);
			this.SCENA = scene;
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
			this.KURDEBELE = scene.getRoot();
			System.out.println(scene.getRoot());
			
			PersonEditDialogController controller = personEditDialogFxmlLoader.<PersonEditDialogController>getController();
			controller.setCountryField(countryTextField.getText());
			controller.setAgeField(ageTextField.getText());
			controller.setCityField(cityTextField.getText());
			controller.setFirstNameField(firstNameTextField.getText());
			controller.setLastNameField(lastNameTextField.getText());
			controller.setEmailField(emailTextField.getText());
			controller.setMyImageView(userProfileImage.getImage());
	}
	else
	{
		System.out.println("Tutaj kurdebele? " + this.KURDEBELE);
		System.out.println(personEditDialogFxmlLoader.getRoot().toString());	
		Stage stage = new Stage();
		SCENA.getWindow().isShowing();
	}
	*/		
/*			//fxmlLoader.setRoot(null);
			//fxmlLoader.setController(null);
			if(personEditDialogFxmlLoader.getRoot() == null) {
				root = (Parent)personEditDialogFxmlLoader.load();
			//	System.out.println(root);
			//	newRoot = root;
				System.out.println("1 PERSON EDIT" + personEditDialogFxmlLoader.getController().toString());
				Stage stage = new Stage();
				Scene scene = new Scene(root);
	//			System.out.println(root);
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
				System.out.println("2 PERSON EDIT" + personEditDialogFxmlLoader.getController().toString());
				
				Stage stage = new Stage();
				//Scene scene = new Scene(personEditDialogFxmlLoader.getRoot());
				Scene scene = new Scene(personEditDialogRoot);
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
				this.KURDEBELE = scene.getRoot();
				System.out.println(scene.getRoot());
				
				PersonEditDialogController controller = personEditDialogFxmlLoader.<PersonEditDialogController>getController();
				controller.setCountryField(countryTextField.getText());
				controller.setAgeField(ageTextField.getText());
				controller.setCityField(cityTextField.getText());
				controller.setFirstNameField(firstNameTextField.getText());
				controller.setLastNameField(lastNameTextField.getText());
				controller.setEmailField(emailTextField.getText());
				controller.setMyImageView(userProfileImage.getImage());

			}
		
			*/
	//	} catch (IOException e1) {
	//		e1.printStackTrace();
	//	}
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
		
	}	*/
	
	public void createFxmlControllers(){
	
	}
	/*
	 * 
	 * 			mainThirdFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_THIRD_BUTTON_OF_V_BOX_FXML)); 
			try {
				this.mainThirdFxmlRoot = (Parent)mainThirdFxmlLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mainThirdButtonOfVBoxController = mainThirdFxmlLoader.<MainThirdButtonOfVBoxController>getController();
			mainThirdButtonOfVBoxController.setMainThirdButtonOfVBoxControllerRoot(mainThirdFxmlRoot);
	 * 

	 * 

	 */
	
	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Male",
		        "Female",
		        "Other"
		    );


	@FXML ComboBox<String> genderComboBox;

	@FXML Button loadAvatarButton;

	private EmailValidator emailValidator;

	private AgeValidator ageValidator;

	private Parent mainFourthButtonOfVBoxControllerRoot;
	
	 public void editButtonOnAction(ActionEvent event){
		 System.out.println("A TEN SIE ZGADZ" + this.mainFXMLLoader);
		 System.out.println("A MOZE KONTROLERL" + this.mainFXMLLoader.<MainController>getController().getLoggedUserData().getFirstName());
		 changeEditable();
		 usernameTextField.setDisable(true);
		 genderTextField.setTranslateY(100);
		 genderTextField.setVisible(false);
		 
		 genderComboBox.setTranslateY(-40);
		 genderComboBox.setVisible(true);
		 genderComboBox.setItems(options);
		 genderComboBox.setValue(genderTextField.getText());;
		 editButton.setDisable(true);
		 getApplyButton().setDisable(false);
		
	}
		
		public void changeEditable() {
			boolean state = firstNameTextField.isEditable();
			
			firstNameTextField.setEditable(!state);
			lastNameTextField.setEditable(!state);
			emailTextField.setEditable(!state);
			ageTextField.setEditable(!state);
			countryTextField.setEditable(!state);
			cityTextField.setEditable(!state);
			genderTextField.setEditable(!state);
		}
	 
	
	public MainFourthButtonOfVBoxController() {
		createFxmlControllers();
	}

	
	public void initialize(){

	}


	public Button getEditButton() {
		return editButton;
	}

	@FXML public void applyButtonOnAction() {

		if(isInputValid()){
		 changeEditable();
		 
		 updateData();
		 usernameTextField.setDisable(false);
		 editButton.setDisable(false);
		 getApplyButton().setDisable(true);
		 
		 genderTextField.setTranslateY(0);
		 genderTextField.setVisible(true);
		 genderTextField.setText(genderComboBox.getSelectionModel().getSelectedItem().toString());
		 
		 genderComboBox.setTranslateY(40);
		 genderComboBox.setVisible(false);

		}
	}

	@FXML public void loadAvatarButtonOnAction() {}
	
	public void updateData(){
	//	mainFXMLLoader
		//Sender sender = new Sender();
		User loggedUserData = mainFXMLLoader.<MainController>getController().getLoggedUserData();
		loggedUserData.setFirstName(firstNameTextField.getText());
		loggedUserData.setAge(Integer.valueOf(ageTextField.getText()));
		loggedUserData.setCity(cityTextField.getText());
		loggedUserData.setCountry(countryTextField.getText());
		loggedUserData.seteMail(emailTextField.getText());
		loggedUserData.setLastName(lastNameTextField.getText());
		loggedUserData.setGender(genderComboBox.getValue());
		
		//new EditedUser(loggedUserData)
	}

	   private boolean isInputValid() {
	        String errorMessage = "";
	        emailValidator = new EmailValidator(emailTextField.getText());
	        ageValidator = new AgeValidator(ageTextField.getText());

	        if (firstNameTextField.getText() == null || firstNameTextField.getText().length() == 0) {
	            errorMessage += "No valid first name!\n"; 
	        }
	        
	        if (lastNameTextField.getText() == null || lastNameTextField.getText().length() == 0) {
	            errorMessage += "No valid last name!\n"; 
	        }
	        
	        if (emailTextField.getText() == null || emailValidator.isValid() == false){
	        	errorMessage += "No valid email!\n"; 
	        }   
	      
	        if(ageTextField.getText() == null || ageValidator.isValid() == false || Integer.valueOf(ageTextField.getText())>150) {
	        	errorMessage += "No valid age!\n";
	        }

	        if (countryTextField.getText() == null || countryTextField.getText().length() == 0) {
	            errorMessage += "No valid country!\n"; 
	        }
	        
	        if (cityTextField.getText() == null || cityTextField.getText().length() == 0) {
	            errorMessage += "No valid city!\n"; 
	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {

	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Invalid Fields");
	            alert.setHeaderText("Please correct invalid fields");
	            alert.setContentText(errorMessage);
	            
	            alert.showAndWait();
	            
	            return false;
	        }
	    }

	public Button getApplyButton() {
		return applyButton;
	}




	public void setMainFXMLLoader(FXMLLoader mainFXMLLoader) {
		this.mainFXMLLoader = mainFXMLLoader;
		System.out.println("OTRZYMANY " + mainFXMLLoader);
	}

	public void setMainFourthButtonOfVBoxControllerRoot(Parent main) {
		this.mainFourthButtonOfVBoxControllerRoot=main;
	}

	

}
