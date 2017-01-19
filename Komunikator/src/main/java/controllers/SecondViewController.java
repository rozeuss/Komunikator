package controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SecondViewController{

    @FXML private Label secondInfoLbl;
	private FXMLLoader firstFXML;
	private FXMLLoader secondFXML;
	@FXML Button sendMessageButton;
	@FXML TextField chatTextField;



    
    public void setFirstFXML(FXMLLoader first){
    	this.firstFXML = first;
    }

	public void setSecondFXML(FXMLLoader loader) {
		// TODO Auto-generated method stub
		this.secondFXML = loader;
		System.out.println("loader"+loader);
		
	}

	@FXML public void chatTFonKeyReleased(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER))
		{
        System.out.println("Wcisnieto ENTER! Robimy clear");
        chatTextField.clear();
        
		}
      }

	@FXML public void sendMessageButtonOnAction() {

        chatTextField.clear();

	}


	
}