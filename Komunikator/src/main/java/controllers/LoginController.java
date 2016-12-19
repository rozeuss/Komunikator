package controllers;


import utils.FxmlUtils;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import javafx.scene.text.Text;
import tasks.CredentialsHandlerTrigger;

public class LoginController implements Initializable {
        private static final ExecutorService taskExecutor = Executors.newFixedThreadPool(1);
             
             
	private static final String FXML_NEW_ACCOUNT_FXML = "/fxml/NewAccount.fxml";

	private static final String FXML_SPLASH_FXML = "/fxml/Splash.fxml";

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
        
        private CredentialsHandlerTrigger credentialsHandlerTrigger;
	@FXML
	private Label messageLabel;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button loginButton;

	@FXML
	private Button exitButton;

	@FXML
	private Label incorrectCredentialsLabel;

	@FXML
	private Button newAccountButton;

	@FXML
	private Text forgotPasswordLabel;

	@FXML
	private void loginButtonOnAction(ActionEvent event) throws Exception {
                        
                credentialsHandlerTrigger.SetUsername(txtUsername.getText());
                credentialsHandlerTrigger.SetPassword(txtPassword.getText());
                try {
                        incorrectCredentialsLabel.setVisible(false);
                        taskExecutor.execute(credentialsHandlerTrigger);
                        taskExecutor.shutdown();
		} catch (IllegalArgumentException e) {
			incorrectCredentialsLabel.setVisible(true);
		}

		if (txtUsername.getText().equals("test") && txtPassword.getText().equals("test")) {

			((Node) (event.getSource())).getScene().getWindow().hide();
			messageLabel.setText("Welcome: " + txtUsername.getText());
		/*	Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource(FXML_SPLASH_FXML));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			Pane borderPane = FxmlUtils.fxmlLoader(FXML_SPLASH_FXML);
			Stage stage = new Stage();
			Scene scene = new Scene(borderPane);
			stage.setScene(scene);
			stage.setResizable(true);
			stage.initStyle(StageStyle.TRANSPARENT);
			// scene.setFill(Color.TRANSPARENT);
			// stage.setTitle("Main Frame");
			// stage.setResizable(false);
			// stage.initStyle(StageStyle.UNDECORATED);
			
			stage.show();

		} else {
			messageLabel.setText("Username or Password invalid!");

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		incorrectCredentialsLabel.setVisible(false);

	}

	@FXML
	public void exitButtonOnAction() {
		Platform.exit();
		System.exit(0);
	}

	@FXML
	public void newAccountButtonOnAction(ActionEvent event) {

	/*	Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource(FXML_NEW_ACCOUNT_FXML));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		// Stage stage = new Stage();
		Pane borderPane = FxmlUtils.fxmlLoader(FXML_NEW_ACCOUNT_FXML);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		Scene scene = new Scene(borderPane);

		stage.setScene(scene);
		stage.setTitle("New Account");
		stage.setResizable(false);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.show();

	}

	public LoginController() {
		LOGGER.log(Level.FINE, "LOG IN Controller created");
                credentialsHandlerTrigger = new CredentialsHandlerTrigger();
	}

	@FXML
	public void forgotPasswordLabelOnMouseEntered() {

		System.out.println("to se przypomnij xD\nlogin:test\nhaslo:test");

	}

}
