package controllers;
import java.io.IOException;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.Main;
import transferData.Sender;
import transferDataContainers.Message;
import javafx.scene.control.TextArea;

public class SecondViewController implements Initializable{

    @FXML private Label secondInfoLbl;
	private static FXMLLoader chattingFXMLLoader;
	private FXMLLoader secondFXML;
	@FXML Button sendMessageButton;
	@FXML TextField chatTextField;
	@FXML TextArea conversationTextArea;
	private Sender sender;
	private Message message;
	private String username;
    
	private FXMLLoader mainFxmlLoader;
	
    public void setMainFxmlLoader(FXMLLoader mainFxmlLoader) {
		this.mainFxmlLoader = mainFxmlLoader;
	}


	public void setChattingFXMLLoader(FXMLLoader chattingFXMLLoader){
    	SecondViewController.chattingFXMLLoader = chattingFXMLLoader;
    }


    public void addMessageToConversationTextArea(String text, String sender){
    	//conversationTextArea.setStyle("-fx-text-fill: blue;");
    	/*conversationTextArea.appendText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime())
    			+ " " + chattingFXMLLoader.<ChattingController>getController().getMainFXMLLoader().<MainController>getController().getLoggedUserData()
    			.getUserName() + ":" + " " +text  + "\n");*/
    	conversationTextArea.appendText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime())
		+ " " + sender + ":" + " " +text  + "\n");
    }

	public void setSecondFXML(FXMLLoader loader) {
		this.secondFXML = loader;		
	}

	@FXML public void chatTFonKeyReleased(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER))
		{
        sendMessageButtonOnAction();
		}
      }

	@FXML public void sendMessageButtonOnAction() {
		/*message = new Message(chattingFXMLLoader.<ChattingController>getController().
				getMainFXMLLoader().<MainController>getController().getLoggedUserData().getUserName(),
				chattingFXMLLoader.<ChattingController>getController().
				getMainFXMLLoader().<MainController>getController().getLoggedUserData().getUserName(),
				chatTextField.getText(),
				LocalDateTime.now()	
				);*/
		
		message = new Message(chattingFXMLLoader.<ChattingController>getController().
		getMainFXMLLoader().<MainController>getController().getLoggedUserData().getUserName(),
		this.username,
		chatTextField.getText(),
		LocalDateTime.now()	
		);
		
		System.out.println(chattingFXMLLoader.<ChattingController>getController().getSender());
		try {
			chattingFXMLLoader.<ChattingController>getController().getSender().send(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMessageToConversationTextArea(chatTextField.getText(), chattingFXMLLoader.<ChattingController>getController().
				getMainFXMLLoader().<MainController>getController().getLoggedUserData().getUserName());

		/*conversationTextArea.appendText(chattingFXMLLoader.<ChattingController>getController()
				.getMainFXMLLoader()
				.<MainController>getController()
				.getMessage()
				.getTextContent()+"\n");*/

        chatTextField.clear();
        System.out.println(username);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
 

	
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}
	
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}




	

	
}