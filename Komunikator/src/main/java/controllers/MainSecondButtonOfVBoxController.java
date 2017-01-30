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


            MenuItem editItem = new MenuItem();
            editItem.textProperty().bind(Bindings.format("Edit \"%s\"", cell.itemProperty()));
            editItem.setOnAction(event -> {
               String item = cell.getItem();
                
            });
            MenuItem deleteItem = new MenuItem();
            deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
            deleteItem.setOnAction(event -> getListView().getItems().remove(cell.getItem()));
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



	@FXML Button newGroupButton;
	
	private void avoidBlankSpacesOnListView() throws IOException
    {
    String item = getListView().getSelectionModel().getSelectedItem().toString();
    String itemUserName = getListView().getSelectionModel().getSelectedItem();
        if(item != tempItem)
        {
        	archiveTextArea.clear();
        	groupNameLabel.setText("Archive of " + getListView().getSelectionModel().getSelectedItem());
        	
        	fileReader.setFile("./src/main/resources/conversations/".concat(itemUserName).concat(".txt"), 100);

        	String archiveContent;
        	while((archiveContent=fileReader.readLine())!=null){
        	archiveTextArea.appendText(archiveContent+"\n");
        	}

        }
        tempItem = item;    
}
	
	
	FileReader fileReader = new FileReader();
	
	
	@FXML public void listViewOnMouseClicked(MouseEvent arg0) throws IOException {
	    avoidBlankSpacesOnListView();
	}





	@FXML public void newGroupButtonOnAction() {
		
		Pane borderPane = FxmlUtils.fxmlLoader("/fxml/NewGroupConversation.fxml");
		Stage stage = new Stage();
		Scene scene = new Scene(borderPane);
		stage.setHeight(600);
		stage.setWidth(420);
		stage.setScene(scene);
		stage.setTitle("New group");
		stage.setResizable(false);
		stage.getIcons().add(
				   new Image(
				      this.getClass().getResourceAsStream( "../images/icon.png" ))); 
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		
		
	}

	private FXMLLoader mainFXMLLoader;

	public void setMainFXMLLoader(FXMLLoader mainFxmlLoader) {
		// TODO Auto-generated method stub
		this.mainFXMLLoader = mainFxmlLoader;
		System.out.println("Z secondButton: + " + mainFXMLLoader);
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
