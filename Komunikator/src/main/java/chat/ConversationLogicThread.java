/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import controllers.ChattingController;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import main.Main;
import transferData.Receiver;
import transferData.Sender;
import transferDataContainers.Message;


public class ConversationLogicThread implements Runnable {
    
    private Message messageHolder;
    
    private String[] messageContentHolder;
    private String messageContent;
    private StringBuilder messageBuilder;
    
    private Sender messageSender;
    private Receiver messageReceiver;
    
    private boolean isStopped;
    
    private FXMLLoader conversationFxmlLoader;
    
    private ConversationController conversationController; 
    
    private static final String FXML_CONVERSATION_FXML = "/fxml/Conversation.fxml";
    
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    
    public ConversationLogicThread(){
        messageBuilder = new StringBuilder();
        isStopped = false;
        
        conversationFxmlLoader  = new FXMLLoader(getClass().getResource(FXML_CONVERSATION_FXML)); 
        conversationController = conversationFxmlLoader.<ConversationController>getController();  
        
    }
    
    @Override
    public void run() {
        while(!isStopped){
            
            if(messageHolder != null){
                messageContentHolder = messageHolder.toString().split(" ");
                messageContent = messageBuilder.append(messageContentHolder[0]).append(" ").append(messageContentHolder[5]).append(" ").append(messageContentHolder[7]).append("\n").toString();
                conversationController.updateMessagesArea(messageContent);
                messageHolder = null;
                messageContent = null;
            
            }
            
            
            
        }
        
        
    }

    public String getMessageContent() {
        return messageContent;
    }
    
    
    public boolean IsStopped() {
        return isStopped;
    }

    public void setIsStopped(boolean isStopped) {
        this.isStopped = isStopped;
    }
    
    public Message getMessageHolder() {
        return messageHolder;
    }

    public void setMessageHolder(Message messageHolder) {
        this.messageHolder = messageHolder;
    }
}
