package controllers;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import transferData.Sender;
import javafx.scene.control.Button;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;



public class ChattingController implements Initializable {

	private FXMLLoader mainFXMLLoader;
	private FXMLLoader chattingFXMLloader;
	private Sender sender;

	public Sender getSender() {
		return sender;
	}

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	@FXML
	private TabPane tabPane;
	@FXML
	TextField chatTextField;
	@FXML
	Button sendMessageButton;
	@FXML
	private Tab myDynamicTab;

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	@FXML public void chatTFonKeyReleased(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER))
		{
        System.out.println("Wcisnieto ENTER! Robimy clear");
        chatTextField.clear();

        
		}
      }

	       
        public void openConversationTab(int clientID, String name, String surname){
            Tab tab = new Tab();
            try {
                tab.setContent(FXMLLoader.load(getClass().getResource("/fxml/Conversation.fxml")));
            } catch (IOException ex) {
                Logger.getLogger(ChattingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tab.setText(clientID + " " + name + " " + surname);
            
            
        }

	public Tab getMyDynamicTab() {
		return myDynamicTab;
	}

	public void setMyDynamicTab(Tab myDynamicTab) {
		this.myDynamicTab = myDynamicTab;
	}

	public FXMLLoader getMainFXMLLoader() {
		return mainFXMLLoader;
	}

	public void setMainFXMLLoader(FXMLLoader mainFXMLLoader) {
		this.mainFXMLLoader = mainFXMLLoader;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public FXMLLoader getChattingFXMLloader() {
		return chattingFXMLloader;
	}

	public void setChattingFXMLloader(FXMLLoader chattingFXMLloader) {
		this.chattingFXMLloader = chattingFXMLloader;
	}

	public void setSocket(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
		this.socket = socket;
		this.in = in;
		this.out = out;
	}

	public void createSender() {
		this.sender = new Sender(out);
	}

}
