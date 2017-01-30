package controllers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
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
	private MainController mainController;

	public void initialize(){
 
	}

	public void setInvitationList(ArrayList<Invitation> invitationList){
		
		if(invitationsSenders != null){
			observableInvitationsSenders = FXCollections.observableArrayList ();
		}
		
		this.invitationList = invitationList;
		this.invitationsSenders = new ArrayList<String>();
		
		for(Invitation invitation: invitationList){
			if(!this.mainController.getFriendsData().contains(invitation.getReceiver())){
				if(!invitationsSenders.contains(invitation.getSender().getUserName()))
					invitationsSenders.add(invitation.getSender().getUserName());
			}
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
	        String userName = cell.getItem();
	        InvitationConfirmation invitationConfirmation = new InvitationConfirmation(new User(cell.getItem()), loggedUser, true);
	        System.out.println("wysylam invitation confirmation Sender(1) : " +  userName + " receiver(2) " + loggedUser.getUserName());
	        listView.getItems().remove(userName);   
	        try {
				sender.send(invitationConfirmation);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        removeInvitationFromInvitationList(userName);
	        });
	        
	        MenuItem deleteItem = new MenuItem();
	        
	        deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
	        deleteItem.setOnAction(event -> {
	        	
	        	String userName = (String)cell.getItem();
		        listView.getItems().remove(cell.getItem());    
		        String logdUser = (String)loggedUser.getUserName();
		        InvitationConfirmation invitationConfirmation = new InvitationConfirmation(new User(userName), new User(logdUser), false);
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
		
		for(Invitation invitation: tmpInvitationList){
			if(invitation.getSender().getUserName() == userName)
				InvitationToRemove.add(invitation);
		}
		invitationList.removeAll(InvitationToRemove);
	}
	
	public void setMainViewController(MainController mainController){
		this.mainController = mainController;
	}

}
