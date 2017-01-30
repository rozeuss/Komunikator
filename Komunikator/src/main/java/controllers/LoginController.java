package controllers;

import utils.FxmlUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DataConnectionWorker;
import database.LoginConnectionWorker;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import security.PasswordHasher;
import tasks.LogInCredentialsHandlerTrigger;
import transferDataContainers.*;

public class LoginController implements Initializable {
	private static final ExecutorService taskExecutor = Executors.newFixedThreadPool(5);

	private LogInCredentialsHandlerTrigger credentialsHandlerTrigger;

	private static final String FXML_NEW_ACCOUNT_FXML = "/fxml/NewAccount.fxml";

	private static final String FXML_SPLASH_FXML = "/fxml/Splash.fxml";

	private static final String FXML_MAIN_FXML = "/fxml/Main.fxml";

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	private Socket socket;

	public Socket getSocket() {
		return socket;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Confirmation loginConfirmation;
	private ActionEvent loginButtonActionEvent;
	private MainController mainController;
	private SplashController splashController;
	private DataConnectionWorker dataConnectionWorker;

	public DataConnectionWorker getDataConnectionWorker() {
		return dataConnectionWorker;
	}

	private User user;
	private Parent mainControllerRoot;

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

	private FXMLLoader loginFXMLLoader;

	FXMLLoader mainFxmlLoader;

	@FXML
	private void loginButtonOnAction(ActionEvent event) throws Exception {
		loginConfirmation = new Confirmation();
		this.loginButtonActionEvent = event;
		try {
			Socket socket = new Socket("127.0.0.1", 1056);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			this.setSocket(socket, out, in);
			this.setSettingsOfMainController();
		} catch (Exception e) {
			e.printStackTrace();
		}
                String pw_hash = PasswordHasher.hashpw(txtPassword.getText(), PasswordHasher.gensalt());
                LoginCredentials loginCredentials = new LoginCredentials(txtUsername.getText(), txtPassword.getText());
                System.out.println(pw_hash);
                pw_hash = null;
		user = new User(txtUsername.getText());
		LoginConnectionWorker loginConnectionWorker = new LoginConnectionWorker(socket, out, in, loginCredentials, this);
		Thread thread = new Thread(loginConnectionWorker);
		thread.run();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		incorrectCredentialsLabel.setVisible(false);
	}

	@FXML
	public void exitButtonOnAction() {
		Platform.exit();
		System.exit(0);
	}

	@FXML
	public void newAccountButtonOnAction(ActionEvent event) {
		Pane borderPane = FxmlUtils.fxmlLoader(FXML_NEW_ACCOUNT_FXML);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(borderPane);
		stage.setScene(scene);
		stage.setTitle("New Account");
		stage.setResizable(false);
		stage.show();

	}

	public LoginController() {
		LOGGER.log(Level.FINE, "LOG IN Controller created");
		credentialsHandlerTrigger = new LogInCredentialsHandlerTrigger();
		this.mainFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_FXML));
		try {
			this.mainControllerRoot = (Parent) mainFxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainController = mainFxmlLoader.<MainController> getController();
		mainController.setMainControllerRoot(mainControllerRoot);
		mainController.setMainFxmlLoader(mainFxmlLoader);

	}

	public void setSettingsOfMainController() {
		mainController.setSocket(socket, out, in);
		mainController.createFxmlControllers(this.loginFXMLLoader);
	}

	public void setSocket(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
		this.socket = socket;
		this.in = in;
		this.out = out;
	}

	public void setLoginStatus(Confirmation loginStatus) {
		this.loginConfirmation = loginStatus;
	}

	public void setSplashScene() {
		((Node) (loginButtonActionEvent.getSource())).getScene().getWindow().hide();
		messageLabel.setText("Welcome: " + txtUsername.getText());

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_SPLASH_FXML));
		Parent root;
		try {
			root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			scene.setFill(Color.TRANSPARENT);
			root.setStyle("-fx-background-color: transparent;");
			stage.setResizable(true);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		splashController = fxmlLoader.<SplashController> getController();

		try {
			splashController.setMainController(mainController);
			splashController.startSplashScreen();
			createAndRunDataConnectionWorker();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showInvalidCredentialsLabel() {
		messageLabel.setText(loginConfirmation.getMessage());
	}

	public void checkLoginStatus() {
		if (loginConfirmation.isConfirmed() == true) {
			setSplashScene();
		} else {
			showInvalidCredentialsLabel();
		}

	}

	@FXML
	public void txtPasswordOnKeyReleased(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			loginButton.fire();
			txtUsername.clear();
			txtPassword.clear();

		}
	}

	public void createAndRunDataConnectionWorker() {
		try {
			dataConnectionWorker = new DataConnectionWorker(socket, out, in, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread thread = new Thread(dataConnectionWorker);
		thread.start();
	}

	public MainController getMainController() {
		return mainController;
	}

	public SplashController getSplashController() {
		return splashController;
	}

	public User getUser() {
		return user;
	}

	public void setLoginFXMLLoader(FXMLLoader fxmlLoader) {
		this.loginFXMLLoader = fxmlLoader;

	}

	public FXMLLoader getLoginFXMLLoader() {
		return loginFXMLLoader;
	}
}
