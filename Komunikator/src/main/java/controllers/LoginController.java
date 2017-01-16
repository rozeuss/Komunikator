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

import database.LoginConnectionWorker;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.Main;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.scene.text.Text;
import tasks.LogInCredentialsHandlerTrigger;
import transferDataContainers.*;

public class LoginController implements Initializable {
    private static final ExecutorService taskExecutor = Executors.newFixedThreadPool(5);
    
    private LogInCredentialsHandlerTrigger credentialsHandlerTrigger;

    private static final String FXML_NEW_ACCOUNT_FXML = "/fxml/NewAccount.fxml";

	private static final String FXML_SPLASH_FXML = "/fxml/Splash.fxml";

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
        
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Confirmation loginConfirmation;
	private ActionEvent loginButtonActionEvent;
	
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
	private void loginButtonOnAction(ActionEvent event) throws Exception {
		loginConfirmation =  new Confirmation();
		/*credentialsHandlerTrigger.SetUsername(txtUsername.getText());
		credentialsHandlerTrigger.SetPassword(txtPassword.getText());
		try {
		    incorrectCredentialsLabel.setVisible(false);
		    taskExecutor.execute(credentialsHandlerTrigger);
		    taskExecutor.shutdown();
		   taskExecutor.awaitTermination(1, TimeUnit.SECONDS);
		} catch (IllegalArgumentException e) {
		    incorrectCredentialsLabel.setVisible(true);
		} catch (NullPointerException nlp) {
		            LOGGER.log(Level.WARNING, "NULL POINTER EXCEPTION IN LOG IN EXECUTOR");  
		}*/
		this.loginButtonActionEvent = event;
		
		LoginCredentials loginCredentials = new LoginCredentials(txtUsername.getText(), txtPassword.getText());
		
		LoginConnectionWorker loginConnectionWorker = new LoginConnectionWorker(socket, out, in, loginCredentials, this);

		Thread thread = new Thread(loginConnectionWorker);
		thread.run();

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
	}


	public void setSocket(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
		this.socket = socket;
		this.in = in;
		this.out = out;
	}
	
	public void setLoginStatus(Confirmation loginStatus) {
		this.loginConfirmation = loginStatus;
	}
	
	public void setSplashScene(){
		
		((Node) (loginButtonActionEvent.getSource())).getScene().getWindow().hide();
		messageLabel.setText("Welcome: " + txtUsername.getText());

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_SPLASH_FXML)); 
		Parent root;
		try {
			root = (Parent)fxmlLoader.load();
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
		SplashController splashLoginController = fxmlLoader.<SplashController>getController();
		
		try{
		splashLoginController.setSocket(socket, out, in);	
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void showInvalidCredentialsLabel(){	
		messageLabel.setText(loginConfirmation.getMessage());
		
		System.out.println(loginConfirmation.getMessage());
	}

	public void checkLoginStatus() {
		if(loginConfirmation.isConfirmed() == true){
			System.out.println("potweirdzono status");
			setSplashScene();
		}
		else{
			showInvalidCredentialsLabel();
			System.out.println("zly status ");
		}
			
	}

	@FXML public void txtPasswordOnKeyReleased( KeyEvent e)
	{
		if(e.getCode().equals(KeyCode.ENTER))
		{
		loginButton.fire();
        System.out.println("Wcisnieto ENTER! Robimy clear");
        txtUsername.clear();
        txtPassword.clear();
		}
	}

}
