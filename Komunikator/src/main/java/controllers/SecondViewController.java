package controllers;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import transferData.Sender;
import transferDataContainers.Message;
import javafx.scene.control.TextArea;
import tasks.FileAppender;

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
    private FileAppender fileAppender;
    private FXMLLoader mainFxmlLoader;
    private Thread fileThread;
    
    public void setMainFxmlLoader(FXMLLoader mainFxmlLoader) {
		this.mainFxmlLoader = mainFxmlLoader;
	}


	public void setChattingFXMLLoader(FXMLLoader chattingFXMLLoader){
    	SecondViewController.chattingFXMLLoader = chattingFXMLLoader;
    }


    public void addMessageToConversationTextArea(String text, String sender){
                try {
                    fileAppender.setFile("./src/main/resources/conversations/".concat(username).concat(".txt"));
                } catch (IOException ex) {
                    Logger.getLogger(SecondViewController.class.getName()).log(Level.SEVERE, null, ex + "\n >>>COULDNT SET A FILE PATH");
                }
                fileAppender.setMessageContent(
                        sender
                        .concat(" \"")
                        .concat(chatTextField.getText())
                        .concat("\" ")
                        .concat(LocalDateTime.now().toString()));
                //fileThread = new Thread(fileAppender);
                //fileThread.start();
                
                
                
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
            message = new Message(chattingFXMLLoader.<ChattingController>getController().
		getMainFXMLLoader().<MainController>getController().getLoggedUserData().getUserName(),
		this.username,
		chatTextField.getText(),
		LocalDateTime.now()	
		);
		try {
			chattingFXMLLoader.<ChattingController>getController().getSender().send(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMessageToConversationTextArea(chatTextField.getText(), chattingFXMLLoader.<ChattingController>getController().
				getMainFXMLLoader().<MainController>getController().getLoggedUserData().getUserName());
                chatTextField.clear();
        }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
            fileAppender = new FileAppender();
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