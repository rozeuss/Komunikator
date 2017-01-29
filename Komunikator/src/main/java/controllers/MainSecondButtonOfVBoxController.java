package controllers;

import java.io.File;
import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import transferDataContainers.User;
import utils.FxmlUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/** JEZELI LISTA KONWERSACJI BEDZIE PUSTA TO MOZE WYWALIC BLAD GDY UZYTKOWNIK
 * BEDZIE CHCIAL JAKAS ZAZNACZYC, MOZNA NP ZROBIC disable dla listy jezeli uzytkownik
 * nie ma zadnych konwersacji **/

public class MainSecondButtonOfVBoxController {

	@FXML SplitPane splitPane;

	ObservableList<User> items = FXCollections.observableArrayList();
        
        File folder = new File("C:\\Users\\ADMIN\\Desktop\\ProjektZD\\Komunikator\\Komunikator\\src\\main\\resources\\conversations");
        File[] listOfFiles = folder.listFiles();
        
	@FXML
	private ListView<User> listView;
	
	public void initialize(){
                    getListView().setCellFactory(lv -> {
                    ListCell<User> cell = new ListCell<>();
                    
		    
	            ContextMenu contextMenu = new ContextMenu();


	            MenuItem editItem = new MenuItem();
                    // WYSWIETLANIE NAZWY :(
                    cell.textProperty().bind(cell.itemProperty().asString());
                    
                    //cell.textProperty().setValue(listOfFiles[0].getName());
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
                   
        if(item != tempItem)
        {
             
        }
        tempItem = item;    
        
    }
	
	
	
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
		stage.show();
		
		
	}

	private FXMLLoader mainFXMLLoader;

	public void setMainFXMLLoader(FXMLLoader mainFxmlLoader) {
		// TODO Auto-generated method stub
		this.mainFXMLLoader = mainFxmlLoader;
                
	}

        public ListView<User> getListView() {
		return listView;
	}



	/**
	 * @param listView the listView to set
	 */
	public void setListView(ListView<User> listView) {
		this.listView = listView;
	}
	
}
