package controllers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import transferDataContainers.User;

public class MainFirstButtonOfVBoxController {

	ObservableList<String> items =FXCollections.observableArrayList (
		    "Single", "Double", "Suite", "Family App");

	@FXML ListView<String> listView;
	
	public void initialize(){
		listView.setItems(items);
		listView.getItems().addAll("One", "Two", "Three");
		listView.getSelectionModel().select(0); // TUTAJ TRZA DODAC TEGO DISABLE 

	        listView.setCellFactory(lv -> {

	            ListCell<String> cell = new ListCell<>();

	            ContextMenu contextMenu = new ContextMenu();


	            MenuItem editItem = new MenuItem();
	            editItem.textProperty().bind(Bindings.format("Accept \"%s\"", cell.itemProperty()));
	            editItem.setOnAction(event -> {
	                String item = cell.getItem();
	                // code to edit item...
	            });
	            MenuItem deleteItem = new MenuItem();
	            deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
	            deleteItem.setOnAction(event -> listView.getItems().remove(cell.getItem()));
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

/*public class MainFirstButtonOfVBoxController {

private ObservableList<User> FoundUsers = FXCollections.observableArrayList ();

@FXML
private ListView <User> listView;

public void initialize(){
	ArrayList<User> foundedUsersList = new ArrayList<User>();
	foundedUsersList.add(new User("Ewelina"));
	foundedUsersList.add(new User("Kamil"));
	FoundUsers.addAll(foundedUsersList);
	listView.setItems(FoundUsers);
	
	//listView.setItems(items);
	//listView.getItems().addAll("One", "Two", "Three");
	//listView.getSelectionModel().select(0); // TUTAJ TRZA DODAC TEGO DISABLE 

        listView.setCellFactory(lv -> {

            ListCell<User> cell = new ListCell<>();

            ContextMenu contextMenu = new ContextMenu();


            MenuItem editItem = new MenuItem();
            editItem.textProperty().bind(Bindings.format("Accept \"%s\"", cell.itemProperty()));
            editItem.setOnAction(event -> {
                User item = cell.getItem();
                // code to edit item...
            });
            MenuItem deleteItem = new MenuItem();
            deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
            deleteItem.setOnAction(event -> listView.getItems().remove(cell.getItem()));
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
}*/
