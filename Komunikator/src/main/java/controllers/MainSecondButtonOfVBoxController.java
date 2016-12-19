package controllers;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

/** JEZELI LISTA KONWERSACJI BEDZIE PUSTA TO MOZE WYWALIC BLAD GDY UZYTKOWNIK
 * BEDZIE CHCIAL JAKAS ZAZNACZYC, MOZNA NP ZROBIC disable dla listy jezeli uzytkownik
 * nie ma zadnych konwersacji **/

public class MainSecondButtonOfVBoxController {

	@FXML SplitPane splitPane;

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
	            editItem.textProperty().bind(Bindings.format("Edit \"%s\"", cell.itemProperty()));
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

	String tempItem = "avoidBlankSpacesOnListView";

	@FXML Button newConversationButton;
	
	private void avoidBlankSpacesOnListView() throws IOException
    {
    String item = listView.getSelectionModel().getSelectedItem().toString();

        if(item != tempItem)
        {
           //click, do something
        	System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
        }
        tempItem = item;    
}
	
	
	
	@FXML public void listViewOnMouseClicked(MouseEvent arg0) throws IOException {
		
	  //  System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());
	    avoidBlankSpacesOnListView();

	}



	@FXML public void newConversationButtonOnAction() {}
	
}
