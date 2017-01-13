package controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

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
