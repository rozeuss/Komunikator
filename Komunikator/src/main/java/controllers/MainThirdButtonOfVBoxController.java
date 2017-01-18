package controllers;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import database.LoginConnectionWorker;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import transferData.Sender;
import transferDataContainers.Confirmation;
import transferDataContainers.FoundedUsers;
import transferDataContainers.Invitation;
import transferDataContainers.LoginCredentials;
import transferDataContainers.User;

public class MainThirdButtonOfVBoxController {
	
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Sender sender;
	private Parent mainThirdButtonOfVBoxControllerRoot;
	private ArrayList <User> foundedUsersList;
	@FXML
	private Button sendInvitationButton;
	private ObservableList<User> FoundUsers = FXCollections.observableArrayList();
	@FXML
	private TableColumn userNameTC;
	@FXML 
	private TableColumn firstNameTC;
	@FXML 
	private TableColumn lastNameTC;
	@FXML 
	private TableColumn ageTC;
	@FXML 
	private TableColumn cityTC;
	@FXML 
	private TableColumn countryTC;
	@FXML 
	private TableColumn genderTC;
	@FXML
	private TableView<User> UsersTV;
	@FXML
	private TextArea searcherTA;
	private User receiverUser;
	private User loggedUser;
	
	public MainThirdButtonOfVBoxController(){
	}
		
	@FXML
	private void sendInvitationButtonOnAction(MouseEvent event) throws Exception {	
		sender.setOut(out);
		System.out.println("Ja : " + loggedUser.getUserName());
		System.out.println("Ktos : " + receiverUser.getUserName());
		Invitation invitation = new Invitation(new User(loggedUser.getUserName()), new User(receiverUser.getUserName()));
		System.out.println("invitaion : " + invitation.getClass());
		System.out.println("out : " + out.getClass());
		sender.send(invitation);
		//sendInvitationButton.setDisable(true);
	}
	
	@FXML
	private void usersTVOnMouseClicked(MouseEvent event) throws Exception {
		System.out.println("jestem w mouse clicked ");
		System.out.println("to co row " + UsersTV.getSelectionModel().selectedItemProperty().get().getUserName());
		receiverUser =  UsersTV.getSelectionModel().selectedItemProperty().get();
		sendInvitationButton.setDisable(false);	
	}

	@FXML
	private void searcherTAOnKeyReleased(KeyEvent event) throws Exception {
		System.out.println("wysylam usera aby uzyskac founded userow");
		String str = "";
		if(!searcherTA.getText().equals("")){
			str = searcherTA.getText();
			User user = new User(str);
			sender.send(user);
		}
		else{
			if(foundedUsersList != null)
				FoundUsers.removeAll(foundedUsersList);
		}		
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

	public void showFoundedUsers() {
		
		for(User user: foundedUsersList){
				setUsersComulnValues(user);	
		}

	}
	public void setFoundedUsers(FoundedUsers dataObject){
		if(foundedUsersList != null)
		FoundUsers.removeAll(foundedUsersList);
		foundedUsersList = dataObject.getFoundedUsers();
		FoundUsers.addAll(foundedUsersList);
		UsersTV.setItems(FoundUsers);
		showFoundedUsers();
	}
	
	public void setUsersComulnValues(User user){
		userNameTC.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
		firstNameTC.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		lastNameTC.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		ageTC.setCellValueFactory(new PropertyValueFactory<User, String>("age"));
		cityTC.setCellValueFactory(new PropertyValueFactory<User, String>("city"));
		countryTC.setCellValueFactory(new PropertyValueFactory<User, String>("country"));
		genderTC.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
	}
	
	public void setLoggedUser(User loggedUser){
		this.loggedUser = loggedUser;
	}

}
