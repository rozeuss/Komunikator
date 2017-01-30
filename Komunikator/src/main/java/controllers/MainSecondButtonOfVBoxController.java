package controllers;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tasks.FileReader;
import transferDataContainers.User;
import utils.FxmlUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MainSecondButtonOfVBoxController {

	@FXML SplitPane splitPane;

	ObservableList<User> items =FXCollections.observableArrayList();

	@FXML
	private ListView<String> listView;
	
	public void initialize(){
		
		updateFriendsViewList();

	        
	}

	
	
	public void updateFriendsViewList(){
		
        getListView().setCellFactory(lv -> {

            ListCell<String> cell = new ListCell<>();

            ContextMenu contextMenu = new ContextMenu();




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



	@FXML Button newGroupButton;
	
	private void avoidBlankSpacesOnListView() throws IOException {
		String item = getListView().getSelectionModel().getSelectedItem().toString();
		String itemUserName = getListView().getSelectionModel().getSelectedItem();
		if (item != tempItem) {
			archiveTextArea.clear();
			groupNameLabel.setText("Archive of " + getListView().getSelectionModel().getSelectedItem());

			boolean isFileExist = fileReader
					.setFile("./src/main/resources/conversations/".concat(itemUserName).concat(".txt"), 100);
			if (isFileExist) {
				String archiveContent;
				while ((archiveContent = fileReader.readLine()) != null) {
					archiveTextArea.appendText(archiveContent + "\n");
				}
			}

		}
		tempItem = item;
	}

	FileReader fileReader = new FileReader();
	
	
	@FXML public void listViewOnMouseClicked(MouseEvent arg0) throws IOException {
	    avoidBlankSpacesOnListView();
	}






	private FXMLLoader mainFXMLLoader;

	public void setMainFXMLLoader(FXMLLoader mainFxmlLoader) {
		// TODO Auto-generated method stub
		this.mainFXMLLoader = mainFxmlLoader;
	}



	/**
	 * @return the listView
	 */
	public ListView<String> getListView() {
		return listView;
	}



    @FXML
    private Label groupNameLabel;

	@FXML TextArea archiveTextArea;
	/**
	 * @param listView the listView to set
	 */
	public void setListView(ListView<String> listView) {
		this.listView = listView;
	}
	
}
