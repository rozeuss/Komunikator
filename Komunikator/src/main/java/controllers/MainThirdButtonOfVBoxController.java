package controllers;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import database.LoginConnectionWorker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import transferData.Sender;
import transferDataContainers.Confirmation;
import transferDataContainers.LoginCredentials;
import transferDataContainers.User;

public class MainThirdButtonOfVBoxController {
	
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Sender sender;
	private Parent mainThirdButtonOfVBoxControllerRoot;
	
	@FXML
	private TextArea searcherTA;
	
	public MainThirdButtonOfVBoxController(){
	}
		
	@FXML
	private void sendInvitationButtonOnAction(ActionEvent event) throws Exception {		
	}

	@FXML
	private void searcherTAOnKeyTyped(KeyEvent event) throws Exception {
		String str = "";
		str = searcherTA.getText();
		User user = new User(str);
		sender.send(user);
	}
	
	@FXML
	private void usersTVOnContextMenuRequest(ActionEvent event) throws Exception {		
	}
	
	public void setSocket(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
		this.socket = socket;
		this.in = in;
		this.out = out;
	}
	
	public void createSender(){
		this.sender = new Sender(out);
	}
	
	public void setMainThirdButtonOfVBoxControllerRoot(Parent mainThirdButtonOfVBoxControllerRoot) {
		this.mainThirdButtonOfVBoxControllerRoot = mainThirdButtonOfVBoxControllerRoot;
	}
}
