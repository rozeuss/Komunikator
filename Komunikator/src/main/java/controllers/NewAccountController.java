package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;
import utils.FxmlUtils;

public class NewAccountController implements Initializable{
    private static final String FXML_LOGIN_FXML = "/fxml/Login.fxml";
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName() );
    private Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    private Matcher emailPatternMatcher;
    
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    
    
    
	StringProperty nameProperty = new SimpleStringProperty();

	// widocznosc/ niewidocznosc label OK imie
	BooleanProperty nameOkProperty = new SimpleBooleanProperty(false);

	public BooleanProperty getNameOkProperty() {
		return nameOkProperty;
	}
	
	public BooleanProperty getPasswordOkProperty() {
		return passwordOkProperty;
	}


	// textfield naziwsko
	StringProperty passwordProperty = new SimpleStringProperty();

	// wylaczenie/wlaczenie pola nazwisko
	BooleanProperty disablePasswordProperty = new SimpleBooleanProperty(true);

	// widocznosc/ niewidocznosc label OK naziwsko
	BooleanProperty passwordOkProperty = new SimpleBooleanProperty(false);

	// textfield - rok urodzenia
	IntegerProperty yearProperty = new SimpleIntegerProperty();

	// przyjmuje wartosc checkBox
	BooleanProperty confirmProperty = new SimpleBooleanProperty(false);

	// obliczony wiek
	StringProperty ageProperty = new SimpleStringProperty();

	// wlacz/wylacz przycisk
	BooleanProperty buttonProperty = new SimpleBooleanProperty(false);

	// aktualny rok
	IntegerProperty actualYearProperty = new SimpleIntegerProperty(LocalDate.now().getYear());
    
	@FXML 
	private Button cancelButton;

	@FXML Text welldefinedUsername;

	@FXML Button createAccountButton;

	@FXML Text welldefinedPassword;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		usernameTextField.textProperty().bindBidirectional(nameProperty);
		passwordTextField.textProperty().bindBidirectional(passwordProperty);

		
		//createAccountButton.disableProperty().bind(disablePasswordProperty);

		welldefinedUsername.visibleProperty().bind(getNameOkProperty());
		welldefinedPassword.visibleProperty().bind(getPasswordOkProperty());

		createAccountButton.disableProperty().bind(getPasswordOkProperty().not());
	}


	@FXML public void cancelButtonOnAction(ActionEvent event) {
            
            
            
	/*	Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource(FXML_LOGIN_FXML));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Pane borderPane = FxmlUtils.fxmlLoader(FXML_LOGIN_FXML);
		
        Stage stage  = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(borderPane);
		stage.setScene(scene);
		stage.setTitle("Yo! - Login");
		stage.setResizable(false);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
	
	
	   public NewAccountController(){
	       LOGGER.log(Level.FINE, "SIGN UP Controller created");
	       nameOkProperty.bind(nameProperty.isNotEmpty());
	       passwordOkProperty.bind(passwordProperty.isNotEmpty());
	    }




	@FXML public void createAccountButtonOnAction() {}


	


}




