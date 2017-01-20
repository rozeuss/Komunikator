package controllers;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
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
import javafx.scene.control.TextArea;

public class SecondViewController implements Initializable{

    @FXML private Label secondInfoLbl;
	private static FXMLLoader chattingFXMLLoader;
	private FXMLLoader secondFXML;
	@FXML Button sendMessageButton;
	@FXML TextField chatTextField;
	@FXML TextArea conversationTextArea;



    
    public void setChattingFXMLLoader(FXMLLoader chattingFXMLLoader){
    	SecondViewController.chattingFXMLLoader = chattingFXMLLoader;
    	System.out.println("first " + chattingFXMLLoader);
    }


    public void addMessageToConversationTextArea(String text){
    	conversationTextArea.appendText("\n" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " userName" + " " +text);
    }

	public void setSecondFXML(FXMLLoader loader) {
		// TODO Auto-generated method stub
		this.secondFXML = loader;
		System.out.println("Unikalny loader dla taba "+ loader);
		
	}

	@FXML public void chatTFonKeyReleased(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER))
		{
        addMessageToConversationTextArea(chatTextField.getText());
        chatTextField.clear();
        
		}
      }

	@FXML public void sendMessageButtonOnAction() {
		System.out.println(chattingFXMLLoader);
		System.out.println(chattingFXMLLoader.<ChattingController>getController().getMainFXMLLoader().<MainController>getController().getLoggedUserData()
				.getFirstName());
		chattingFXMLLoader.<ChattingController>getController().getMainFXMLLoader().<MainController>getController().getOut();
		addMessageToConversationTextArea(chatTextField.getText());
        chatTextField.clear();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("SIEMA");
		
	}


	

	
}