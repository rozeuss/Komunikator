package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import transferData.Sender;
import transferDataContainers.Invitation;
import transferDataContainers.InvitationConfirmation;
import transferDataContainers.User;

public class MainFirstButtonOfVBoxController {

	private ObservableList<String> observableInvitationsSenders = FXCollections.observableArrayList ();
	private ArrayList<Invitation> invitationList = new ArrayList<Invitation>();
	private Sender sender;
	private Parent mainFirstFxmlRoot;
	@FXML
	private ListView <String> listView;
	private User loggedUser;

	public void initialize(){
 
	}

	public void setInvitationList(ArrayList<Invitation> invitationList){
		this.invitationList = invitationList;
		System.out.println("jestem tu");
		ArrayList<String> invitationsSenders = new ArrayList<String>();
		//invitationsSenders.add("Kasia");
		//invitationsSenders.add("Basia");
		
		for(Invitation invitation: invitationList){
			invitationsSenders.add(invitation.getSender().getUserName() + " sent you a friend request");
			System.out.println("klasa user name " + invitation.getSender().getUserName().getClass());
			System.out.println("Wyswietlamy senderow " + invitation.getSender().getUserName()); 
		}
		observableInvitationsSenders.addAll(invitationsSenders);
		listView.setItems(observableInvitationsSenders);

		//listView.getSelectionModel().select(0); // TUTAJ TRZA DODAC TEGO DISABLE 

	        listView.setCellFactory(lv -> {ListCell<String> cell = new ListCell<>();
	        ContextMenu contextMenu = new ContextMenu();
	        MenuItem editItem = new MenuItem();
	        editItem.textProperty().bind(Bindings.format("Accept \"%s\"", cell.itemProperty()));
	        editItem.setOnAction(event -> {
	        String item = cell.getItem();
	        InvitationConfirmation invitationConfirmation = new InvitationConfirmation(new User(cell.getItem()), loggedUser, true);
	        try {
				sender.send(invitationConfirmation);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        });
	        
	        MenuItem deleteItem = new MenuItem();
	        
	        deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
	        deleteItem.setOnAction(event -> {
		        listView.getItems().remove(cell.getItem());
		        System.out.println("klikniete delete lus " + cell.getItem().getClass() + "user class" + loggedUser.getUserName());
		        String userName = (String)cell.getItem();
		        String logdUser = (String)loggedUser.getUserName();
		        InvitationConfirmation invitationConfirmation = new InvitationConfirmation(new User(userName), new User(logdUser), false);
		        System.out.println("Invitation = " + invitationConfirmation);
		        try {
					sender.send(invitationConfirmation);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        });
	        contextMenu.getItems().addAll(editItem, deleteItem);
	        cell.textProperty().bind(cell.itemProperty());
	        cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
	                if (isNowEmpty) {
	                    cell.setContextMenu(null);
	                } else {
	                    cell.setContextMenu(contextMenu);
	                }
	            });
	            return cell ;
	        }); 
	}
	
	public void createSender(ObjectOutputStream out){
		this.sender = new Sender(out);
	}

	public void setMainFirstButtonOfVBoxControllerRoot(Parent mainFirstFxmlRoot) {
		this.mainFirstFxmlRoot =mainFirstFxmlRoot;	
	}
	
	public void setLoggedUser(User user){
		this.loggedUser = user;
	}

}
