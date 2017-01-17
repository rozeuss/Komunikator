package controllers;

import chat.Conversation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import main.Main;
import transferDataContainers.User;

public class ChattingController {
        
    
	@FXML TabPane   conversationsPane;
              
        private ObservableList<Conversation> tabs = FXCollections.observableArrayList();
        private String[] tabText;
        private Parent chattingControllerRoot;
        private FXMLLoader conversationFxmlLoader;
        private boolean isExisting;
        private String FXML_CONVERSATION_FXML = "/fxml/Conversation.fxml";
        private static final Logger LOGGER = Logger.getLogger(Main.class.getName());        
	@FXML public void chatTFonKeyReleased(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER))
		{
                System.out.println("Wcisnieto ENTER! Robimy clear");
                //chatTF.clear();
        
		}
      }

	       
        public void openConversationTab(String clientID, String name, String surname)
        {
            isExisting = false;
            for(Conversation tab : tabs){
               if(clientID == tab.getClientID()) 
               {
                   isExisting = true;
                   conversationsPane.getSelectionModel().select(tab);
                   
               }
            }
            /*TODO search for already opened tab*/
            
            if(!isExisting){
                Conversation tab = new Conversation(clientID);
            try 
            {
                tab.setContent(FXMLLoader.load(getClass().getResource("/fxml/Conversation.fxml")));
            } catch (IOException ex) 
            {
                Logger.getLogger(ChattingController.class.getName()).log(Level.SEVERE, ex + " SETTING TAB CONTENT FAILED");
            }
            tab.setText("[ "+clientID+" ]" + " " + name + " " + surname);
            tabs.add(tab);
            conversationsPane.getTabs().add(tab);
            conversationsPane.getSelectionModel().select(tab);
            conversationFxmlLoader  = new FXMLLoader(getClass().getResource(FXML_CONVERSATION_FXML));
            System.out.println("////////////////////////////////////////////////////////////"+conversationFxmlLoader.getClass()); 
            }
            else
            {
                
            }
            
        }
        
        public void closeConversationTab(String clientID){
            for(Conversation tab : tabs){
               if(clientID == tab.getClientID()) 
               {
                   conversationsPane.getTabs().remove(tab);
                   tabs.remove(tab);
               }
            }
            
            
        }
        public void setChattingControllerRoot(Parent chattingControllerRoot) 
        {
            this.chattingControllerRoot = chattingControllerRoot;
        }

	
}      
