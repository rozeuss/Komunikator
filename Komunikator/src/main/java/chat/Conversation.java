/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import transferData.Receiver;
import transferData.Sender;
import transferDataContainers.Message;

/**
 *
 * @author ADMIN
 */
public class Conversation extends Tab implements Runnable {
    
    private Sender messageSender;
    private Receiver messageReceiver;
    
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private AnchorPane root;
    private String messageContent;
    private final StringBuilder messageBuilder;
    private String[] messageContentHolder;
    private ObservableList<String> messages = FXCollections.observableArrayList();
    private Message messageHolder;

    public Message getMessageHolder() {
        return messageHolder;
    }

    public void setMessageHolder(Message messageHolder) {
        this.messageHolder = messageHolder;
    }
    public Conversation(ObjectOutputStream out, ObjectInputStream in){
        this.out = out;
        this.in  = in;
        messageHolder = null;
        messageBuilder = new StringBuilder();
    }
    
    @Override
    public void run() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Chatting.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(Conversation.class.getName()).log(Level.SEVERE, null, ex);
        }

        while(true){
            if(messageHolder != null){
                messageContentHolder = messageHolder.toString().split(" ");
                messageContent = messageBuilder.append(messageContentHolder[0]).append(messageContentHolder[5]).append(messageContentHolder[7]).toString();
                messages.add(messageContent);
                messageHolder = null;
                messageContent = null;
                
            }
            
            
        }
    }
    
}
