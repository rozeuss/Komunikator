package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;
import transferData.Sender;
import transferDataContainers.Confirmation;
import transferDataContainers.LoginCredentials;
import transferDataContainers.RegistrationInformation;
import utils.FxmlUtils;
import validators.AgeValidator;
import validators.EmailValidator;
import validators.StringsValidator;
import javafx.scene.control.ComboBox;

public class NewAccountController implements Initializable {
	private static final String FXML_LOGIN_FXML = "/fxml/Login.fxml";
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	@FXML
	private TextField usernameTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private TextField ageTextField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private Button cancelButton;
	@FXML
	Text welldefinedUsername;
	@FXML
	Button createAccountButton;
	@FXML
	Text welldefinedPassword;
	@FXML
	TextField emailTextField;
	@FXML
	TextField countryTextField;
	@FXML
	TextField cityTextField;
	@FXML
	ComboBox<String> genderComboBox;
	ObservableList<String> options = FXCollections.observableArrayList("Male", "Female", "Other");
	private EmailValidator emailValidator;
	private AgeValidator ageValidator;
	private StringsValidator firstNameValidator;
	private StringsValidator lastNameValidator;
	private StringsValidator countryNameValidator;
	private StringsValidator cityNameValidator;
	private StringsValidator passwordValidator;
	private StringsValidator usernameValidator;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		genderComboBox.setItems(options);
		genderComboBox.setValue(options.get(0));
		BooleanBinding booleanBinding = usernameTextField.textProperty().isEqualTo("")
				.or(passwordTextField.textProperty().isEqualTo("")
						.or(firstNameTextField.textProperty().isEqualTo("").or(lastNameTextField.textProperty()
								.isEqualTo("").or(ageTextField.textProperty().isEqualTo("")
										.or(emailTextField.textProperty().isEqualTo("").or(cityTextField.textProperty()
												.isEqualTo("").or(countryTextField.textProperty().isEqualTo(""))))))));
		createAccountButton.disableProperty().bind(booleanBinding);
	}

	@FXML
	public void cancelButtonOnAction(ActionEvent event) {
		Pane borderPane = FxmlUtils.fxmlLoader(FXML_LOGIN_FXML);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(borderPane);
		stage.setScene(scene);
		stage.setTitle("Yo! - Login");
		stage.setResizable(false);
		stage.show();
	}

	private boolean isInputValid() {
		String errorMessage = "";
		emailValidator = new EmailValidator(emailTextField.getText());
		ageValidator = new AgeValidator(ageTextField.getText());
		firstNameValidator = new StringsValidator();
		lastNameValidator = new StringsValidator();
		countryNameValidator = new StringsValidator();
		cityNameValidator = new StringsValidator();
		passwordValidator = new StringsValidator();
		usernameValidator = new StringsValidator();

		if (usernameValidator.isValidUsername(usernameTextField.getText()) == false) {
			errorMessage += "No valid username!\n";
		}

		if (passwordValidator.isValidPass(passwordTextField.getText()) == false) {
			errorMessage += "No valid password!\n";
		}

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

	public NewAccountController() {
		LOGGER.log(Level.FINE, "SIGN UP Controller created");
	}

	@FXML
	public void createAccountButtonOnAction(ActionEvent event) {
		if (isInputValid()) {
			Thread t = new Thread() {
				public void run() {
					Socket socket = null;
					ObjectOutputStream out = null;
					ObjectInputStream in = null;

					try {
						socket = new Socket("172.25.169.205", 1056);
						out = new ObjectOutputStream(socket.getOutputStream());
						in = new ObjectInputStream(socket.getInputStream());
					} catch (Exception e) {
						e.printStackTrace();
					}

					Sender sender = new Sender(out);

					try {
						sender.send(new RegistrationInformation(usernameTextField.getText(),
								passwordTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(),
								emailTextField.getText(), Integer.valueOf(ageTextField.getText()), "Male",
								countryTextField.getText(), cityTextField.getText()));
					} catch (IOException e) {
						e.printStackTrace();
					}

					try {
						Confirmation confirmation = (Confirmation) in.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			};
			t.start();

		}
	}
}
