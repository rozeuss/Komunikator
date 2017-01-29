package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javafx.application.Platform;
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
	private ArrayList<String> invitationsSenders = new ArrayList<String>();
	private Sender sender;
	private Parent mainFirstFxmlRoot;
	@FXML
	private ListView <String> listView;
	private User loggedUser;

	public void initialize(){
 
	}

	public void setInvitationList(ArrayList<Invitation> invitationList){
		
		for(String sender: invitationsSenders){
			System.out.println("wyswietlam sendrow :  " + sender);
		}
		
		if(invitationsSenders != null){
			//observableInvitationsSenders.removeAll(invitationsSenders);
			observableInvitationsSenders = FXCollections.observableArrayList ();
			System.out.println("usunelam observableInvitationsSenders");
		}
		
		this.invitationList = invitationList;
		this.invitationsSenders = new ArrayList<String>();
		
		for(Invitation invitation: invitationList){
			invitationsSenders.add(invitation.getSender().getUserName());
		}
		
		observableInvitationsSenders.addAll(invitationsSenders);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				listView.setItems(observableInvitationsSenders);
			}
		});
		
		
		if(observableInvitationsSenders!=null){
	        listView.setCellFactory(lv -> {ListCell<String> cell = new ListCell<>();
	        ContextMenu contextMenu = new ContextMenu();
	        MenuItem editItem = new MenuItem();
	        editItem.textProperty().bind(Bindings.format("Accept \"%s\"", cell.itemProperty()));
	        editItem.setOnAction(event -> {
	        String item = cell.getItem();
	        InvitationConfirmation invitationConfirmation = new InvitationConfirmation(new User(cell.getItem()), loggedUser, true);
	        
	        System.out.println("Invitation receiver name = " + loggedUser);
	        System.out.println("Invitation sender name = " + cell.getItem());
	        System.out.println("Invitation receiver = " + invitationConfirmation.getReceiver());
	        System.out.println("Invitation loged user " + invitationConfirmation.getSender());
	        System.out.println("Invitation confirmation " + invitationConfirmation.isConfirmed());
	        System.out.println("Invitation = " + invitationConfirmation);
	        
	        try {
				sender.send(invitationConfirmation);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        });
	        
	        MenuItem deleteItem = new MenuItem();
	        
	        deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
	        deleteItem.setOnAction(event -> {
	        	
	        	String userName = (String)cell.getItem();
		        listView.getItems().remove(cell.getItem());    
		        String logdUser = (String)loggedUser.getUserName();
		        InvitationConfirmation invitationConfirmation = new InvitationConfirmation(new User(userName), new User(logdUser), false);
		        System.out.println("Invitation receiver name = " + logdUser);
		        System.out.println("Invitation sender name = " + userName);
		        System.out.println("Invitation receiver = " + invitationConfirmation.getReceiver());
		        System.out.println("Invitation loged user " + invitationConfirmation.getSender());
		        System.out.println("Invitation confirmation " + invitationConfirmation.isConfirmed());
		        System.out.println("Invitation = " + invitationConfirmation);
		        try {
					sender.send(invitationConfirmation);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
		        removeInvitationFromInvitationList(userName);
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

	public void addNewInvitation(Invitation dataObject) {
		this.invitationList.add(dataObject);	
		
		for(Invitation invitation: invitationList){
			System.out.println("Jestem tu w add New invitation" + invitation.getSender().getUserName());
		}
		setInvitationList(invitationList);
	}
	
	public void removeInvitationFromInvitationList(String userName){
		ArrayList<Invitation> tmpInvitationList = new ArrayList<Invitation>();
		ArrayList<Invitation> InvitationToRemove = new ArrayList<Invitation>();
		tmpInvitationList = this.invitationList;
		
		for(Invitation invitation: invitationList){
			System.out.println("przed " + invitation.getSender().getUserName());
		}
		
		for(Invitation invitation: tmpInvitationList){
			if(invitation.getSender().getUserName() == userName)
				InvitationToRemove.add(invitation);
		}
		invitationList.removeAll(InvitationToRemove);
		
		for(Invitation invitation: invitationList){
			System.out.println("po " + invitation.getSender().getUserName());
		}
	}

}
