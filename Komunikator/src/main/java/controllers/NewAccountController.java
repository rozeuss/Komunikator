package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

public class NewAccountController {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName() );
    
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

	@FXML 
	private Button cancelButton;

	@FXML public void cancelButtonOnAction(ActionEvent event) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Stage stage = new Stage();
		
		
        Stage stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();

		Scene scene = new Scene(parent);
		
		
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.setResizable(false);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
	
	
	   public NewAccountController(){
	       LOGGER.log(Level.FINE, "SIGN UP Controller created");
	    }

}




