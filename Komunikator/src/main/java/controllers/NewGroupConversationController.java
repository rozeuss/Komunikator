package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class NewGroupConversationController {
	
	@FXML
	private ListView<String> friendListListView;
	
	@FXML
	private ListView<String> selectedFriendsListView;
	

	ObservableList<String> items =FXCollections.observableArrayList (
		    "Single", "Double", "Suite", "Family App");


	@FXML Button newGroupButton;

	@FXML Button cancelButton;

	 public void initialize() {
	        friendListListView.setItems(items);
	        friendListListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	        friendListListView.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
	        	selectedFriendsListView.setItems(friendListListView.getSelectionModel().getSelectedItems());
	        });
	    }





	@FXML public void newGroupButtonOnAction() {
		
		
     	ObservableList<String> selectedItems = friendListListView.getSelectionModel().getSelectedItems();

        for(String s : selectedItems){
            System.out.println("selected item " + s);
	}
}





	@FXML public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
	}
	
}