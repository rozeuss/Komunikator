/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import controllers.ChattingController;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import main.Main;
import transferData.Receiver;
import transferData.Sender;
import transferDataContainers.Message;


public class ConversationLogicThread implements Runnable {
    
    private Message receivedMessageHolder;
    private Message sentMessageHolder;
    
    private String[] messageContentHolder;
    private String messageContent;
    private StringBuilder messageBuilder;
    private Sender messageSender;
    private Receiver messageReceiver;
    private ObjectOutputStream out;
    private boolean isStopped;
    
    private FXMLLoader conversationFxmlLoader;
    
    private ConversationController conversationController; 
    
    private static final String FXML_CONVERSATION_FXML = "/fxml/Conversation.fxml";
    
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    
    public ConversationLogicThread(Sender sender, ObjectOutputStream out){
        messageBuilder = new StringBuilder();
        isStopped = false;
        messageSender = sender;
        this.out = out;
        conversationFxmlLoader  = new FXMLLoader(getClass().getResource(FXML_CONVERSATION_FXML)); 
        conversationController = conversationFxmlLoader.<ConversationController>getController();  
        receivedMessageHolder = null;
        sentMessageHolder = null;
    }
    
    @Override
    public void run() {
        while(!isStopped){
            
            if(receivedMessageHolder != null){
                messageContentHolder = receivedMessageHolder.toString().split(" ");
                messageContent = messageBuilder.append(messageContentHolder[0]).append(" ").append(messageContentHolder[5]).append(" ").append(messageContentHolder[7]).append("\n").toString();
                conversationController.updateMessagesArea(messageContent);
                
                /*save to file !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
                
                receivedMessageHolder = null;
                messageContent = null;
            
            }
            if(sentMessageHolder != null){
                messageContentHolder = sentMessageHolder.toString().split(" ");
                messageContent = messageBuilder.append(messageContentHolder[0]).append(" ").append(messageContentHolder[5]).append(" ").append(messageContentHolder[7]).append("\n").toString();
                conversationController.updateMessagesArea(messageContent);
                try {
                    messageSender.send(sentMessageHolder);
                } catch (IOException ex) {
                    Logger.getLogger(ConversationLogicThread.class.getName()).log(Level.SEVERE, "COULDNT SEND A MESSAGE");
                }
                
                /* save to file !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
                
                sentMessageHolder = null;
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

    public void setReceivedMessageHolder(Message receivedMessageHolder) {
        this.receivedMessageHolder = receivedMessageHolder;
    }

    public void setSentMessageHolder(Message sentMessageHolder) {
        this.sentMessageHolder = sentMessageHolder;
    }

}
