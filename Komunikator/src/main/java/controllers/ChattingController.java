package controllers;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
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

	/**
	 * @return the tabPane
	 */
	public TabPane getTabPane() {
		return tabPane;
	}

	/**
	 * @param tabPane the tabPane to set
	 */
	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	@FXML TextField chatTextField;
	@FXML Button sendMessageButton;
	
	@FXML
	private Tab myDynamicTab;

	@FXML public void chatTFonKeyReleased(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER))
		{
        System.out.println("Wcisnieto ENTER! Robimy clear");
        chatTextField.clear();

        
		}
      }

	       
        public void openConversationTab(int clientID, String name, String surname){
            
            /*TODO search for already opened tab*/
            
            
            Tab tab = new Tab();
            try {
                tab.setContent(FXMLLoader.load(getClass().getResource("/fxml/Conversation.fxml")));
            } catch (IOException ex) {
                Logger.getLogger(ChattingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tab.setText(clientID + " " + name + " " + surname);
            
            
        }


	/**
	 * @return the myDynamicTab
	 */
	public Tab getMyDynamicTab() {
		return myDynamicTab;
	}

	/**
	 * @param myDynamicTab the myDynamicTab to set
	 */
	public void setMyDynamicTab(Tab myDynamicTab) {
		this.myDynamicTab = myDynamicTab;
	}


	/**
	 * @return the mainFXMLLoader
	 */
	public FXMLLoader getMainFXMLLoader() {
		return mainFXMLLoader;
	}

	/**
	 * @param mainFXMLLoader the mainFXMLLoader to set
	 */
	public void setMainFXMLLoader(FXMLLoader mainFXMLLoader) {
		this.mainFXMLLoader = mainFXMLLoader;
		System.out.println("Z CHATTING CONTROLER mainloader" + mainFXMLLoader);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	
	
	/**
	 * @return the chattingFXMLloader
	 */
	public FXMLLoader getChattingFXMLloader() {
		return chattingFXMLloader;
	}

	/**
	 * @param chattingFXMLloader the chattingFXMLloader to set
	 */
	public void setChattingFXMLloader(FXMLLoader chattingFXMLloader) {
		this.chattingFXMLloader = chattingFXMLloader;
		System.out.println("CHATTING FXML LODAER " + chattingFXMLloader);
	}


	public void setSocket(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
		this.socket = socket;
		this.in = in;
		this.out = out;
	}
	
	public void createSender(){
		this.sender = new Sender(out);
	}
	
}
