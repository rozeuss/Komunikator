package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import main.Main;

public class ChattingController {
        
    
	@FXML TabPane   conversationsPane;
              
        
        private Parent chattingControllerRoot;
        private FXMLLoader conversationFxmlLoader;
        private String FXML_CONVERSATION_FXML = "/fxml/Conversation.fxml";
        private static final Logger LOGGER = Logger.getLogger(Main.class.getName());        
	@FXML public void chatTFonKeyReleased(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER))
		{
                System.out.println("Wcisnieto ENTER! Robimy clear");
                //chatTF.clear();
        
		}
      }

	       
        public void openConversationTab(int clientID, String name, String surname)
        {
            
            /*TODO search for already opened tab*/
            
            Tab tab = new Tab();
            try 
            {
                tab.setContent(FXMLLoader.load(getClass().getResource("/fxml/Conversation.fxml")));
            } catch (IOException ex) 
            {
                Logger.getLogger(ChattingController.class.getName()).log(Level.SEVERE, ex + " SETTING TAB CONTENT FAILED");
            }
            tab.setText(clientID + " " + name + " " + surname);
            
            conversationsPane.getTabs().add(tab);
            //conversationsPane.getSelectionModel().select(clientID);
            
            conversationFxmlLoader  = new FXMLLoader(getClass().getResource(FXML_CONVERSATION_FXML));
            System.out.println("////////////////////////////////////////////////////////////"+conversationFxmlLoader.getClass());          
        }
        
        public void setChattingControllerRoot(Parent chattingControllerRoot) 
        {
            this.chattingControllerRoot = chattingControllerRoot;
        }

	
}      
