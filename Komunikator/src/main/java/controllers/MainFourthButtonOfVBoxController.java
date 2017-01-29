package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Main;
import sun.util.locale.provider.AvailableLanguageTags;
import transferData.Sender;
import transferDataContainers.EditedUserData;
import transferDataContainers.User;
import utils.FxmlUtils;
import validators.AgeValidator;
import validators.EmailValidator;
import validators.StringsValidator;

public class MainFourthButtonOfVBoxController {

	private FXMLLoader mainFXMLLoader;

	@FXML
	TextField usernameTextField;
	@FXML
	TextField firstNameTextField;
	@FXML
	TextField lastNameTextField;
	@FXML
	TextField emailTextField;
	@FXML
	TextField ageTextField;
	@FXML
	TextField countryTextField;
	@FXML
	TextField cityTextField;
	@FXML
	TextField genderTextField;
	@FXML
	private ImageView userProfileImage;

	@FXML
	private Button editButton;
	@FXML
	Label profileNameLabel;

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

	public void createFxmlControllers() {

	}

	ObservableList<String> options = FXCollections.observableArrayList("Male", "Female", "Other");

	@FXML
	ComboBox<String> genderComboBox;

	@FXML
	Button loadAvatarButton;

	private EmailValidator emailValidator;

	private AgeValidator ageValidator;

	private Parent mainFourthButtonOfVBoxControllerRoot;

	private StringsValidator firstNameValidator;

	private StringsValidator lastNameValidator;

	private StringsValidator countryNameValidator;

	private StringsValidator cityNameValidator;

	public Parent getMainFourthButtonOfVBoxControllerRoot() {
		return mainFourthButtonOfVBoxControllerRoot;
	}

	public void editButtonOnAction(ActionEvent event) {
		changeEditable();
		loadAvatarButton.setVisible(true);
		usernameTextField.setDisable(true);
		genderTextField.setTranslateY(100);
		genderTextField.setVisible(false);
		genderComboBox.setTranslateY(-40);
		genderComboBox.setVisible(true);
		genderComboBox.setItems(options);
		genderComboBox.setValue(genderTextField.getText());
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

	public void initialize() {

	}

	public Button getEditButton() {
		return editButton;
	}

	@FXML
	public void applyButtonOnAction() {

		if (isInputValid()) {
			changeEditable();
			updateData();
			loadAvatarButton.setVisible(false);
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

	public void init() {
		boolean state = true;
		firstNameTextField.setEditable(!state);
		lastNameTextField.setEditable(!state);
		emailTextField.setEditable(!state);
		ageTextField.setEditable(!state);
		countryTextField.setEditable(!state);
		cityTextField.setEditable(!state);
		genderTextField.setEditable(!state);
		loadAvatarButton.setVisible(false);
		usernameTextField.setDisable(false);
		editButton.setDisable(false);
		getApplyButton().setDisable(true);
		genderTextField.setTranslateY(0);
		genderTextField.setVisible(true);
		genderComboBox.setTranslateY(40);
		genderComboBox.setVisible(false);
	}

	@FXML
	public void loadAvatarButtonOnAction() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		fileChooser.setTitle("Select your profile image");
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			try {
				BufferedImage bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				userProfileImage.setImage(image);
			} catch (IOException ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void updateData() {
		Sender sender = new Sender(mainFXMLLoader.<MainController> getController().getOut());
		User loggedUserData = mainFXMLLoader.<MainController> getController().getLoggedUserData();
		loggedUserData.setFirstName(firstNameTextField.getText());
		loggedUserData.setAge(Integer.valueOf(ageTextField.getText()));
		loggedUserData.setCity(cityTextField.getText());
		loggedUserData.setCountry(countryTextField.getText());
		loggedUserData.seteMail(emailTextField.getText());
		loggedUserData.setLastName(lastNameTextField.getText());
		loggedUserData.setGender(genderComboBox.getValue());
		EditedUserData editedUserData = new EditedUserData(loggedUserData);
		try {
			sender.send(editedUserData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isInputValid() {
		String errorMessage = "";
		emailValidator = new EmailValidator(emailTextField.getText());
		ageValidator = new AgeValidator(ageTextField.getText());
		firstNameValidator = new StringsValidator();
		lastNameValidator = new StringsValidator();
		countryNameValidator = new StringsValidator();
		cityNameValidator = new StringsValidator();

		if (firstNameValidator.isAlpha(firstNameTextField.getText()) == false
				|| firstNameTextField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}

		if (lastNameValidator.isAlpha(lastNameTextField.getText()) == false
				|| lastNameTextField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}

		if (emailTextField.getText() == null || emailValidator.isValid() == false) {
			errorMessage += "No valid email!\n";
		}

		if (ageTextField.getText() == null || ageValidator.isValid() == false
				|| Integer.valueOf(ageTextField.getText()) > 150) {
			errorMessage += "No valid age!\n";
		}

		if (countryNameValidator.isAlpha(countryTextField.getText()) == false
				|| countryTextField.getText().length() == 0) {
			errorMessage += "No valid country!\n";
		}

		if (cityNameValidator.isAlpha(cityTextField.getText()) == false || cityTextField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}

		if (genderComboBox.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "No valid gender!\n";
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
	}

	public void setMainFourthButtonOfVBoxControllerRoot(Parent main) {
		this.mainFourthButtonOfVBoxControllerRoot = main;
	}

	public void setLoggedUserData() {
		MainFourthButtonOfVBoxController controller = mainFXMLLoader.<MainController> getController()
				.getMainFourthFxmlLoader().<MainFourthButtonOfVBoxController> getController();
		User loggedUserData = mainFXMLLoader.<MainController> getController().getLoggedUserData();
		controller.setAgeTextField(loggedUserData.getAge());
		controller.setCityTextField(loggedUserData.getCity());
		controller.setCountryTextField(loggedUserData.getCountry());
		controller.setEmailTextField(loggedUserData.geteMail());
		controller.setFirstNameTextField(loggedUserData.getFirstName());
		controller.setLastNameTextField(loggedUserData.getLastName());
		controller.setGenderTextField(loggedUserData.getGender());
		controller.setUsernameTextField(loggedUserData.getUserName());
		controller.setYourProfileNameLabelText();
		controller.setUserProfileImage(new Image(Main.class.getResourceAsStream("../images/Onion-300x300.png")));
		controller.init();
	}

	public void setFriendData(User friend) {
		MainFourthButtonOfVBoxController controller = mainFXMLLoader.<MainController> getController()
				.getMainFourthFxmlLoader().<MainFourthButtonOfVBoxController> getController();
		MainController mainController = mainFXMLLoader.<MainController> getController();
		controller.setAgeTextField(friend.getAge());
		controller.setCityTextField(friend.getCity());
		controller.setCountryTextField(friend.getCountry());
		controller.setEmailTextField(friend.geteMail());
		controller.setFirstNameTextField(friend.getFirstName());
		controller.setLastNameTextField(friend.getLastName());
		controller.setGenderTextField(friend.getGender());
		controller.setUsernameTextField(friend.getUserName());
		controller.setProfileNameLabelText(mainController.getShowProfileUserName());
		controller.setUserProfileImage(new Image(Main.class.getResourceAsStream("../images/icon.png")));
		controller.init();
		controller.getEditButton().setDisable(true);
	}

}
