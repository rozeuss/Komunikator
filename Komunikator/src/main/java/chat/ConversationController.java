/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.ObjectOutputStream;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import transferData.Sender;


/**
 *
 * @author ADMIN
 */
public class ConversationController implements Initializable {
    
    @FXML TextField chatTF;
    @FXML TextArea  messagesArea;
    @FXML Button    sendMessageButton;
    
    private ConversationLogicThread logicThread;
    private Parent conversationFxmlRoot;
    private int clientID;
    private Sender sender;
    private ObjectOutputStream out;   

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }
    
    @Override 
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void createLogicThread(){
        logicThread = new ConversationLogicThread(sender,out);
        Thread thread = new Thread(logicThread);
        thread.start();
    }
    @FXML public void sendMessageButtonOnAction() {
        messagesArea.appendText(chatTF.getText());
        /* TODO sending messages */
    }
    
    public void updateMessagesArea(String message){
        messagesArea.appendText(message);
    }
    
   
    
}
