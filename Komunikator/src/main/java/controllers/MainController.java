package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;

import javax.sql.RowSetMetaData;

import database.DataConnectionWorker;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import main.Main;
import main.Person;
import sun.awt.AppContext;
import transferDataContainers.User;
import transferDataContainers.UserData;
import utils.DialogsUtils;
import utils.FxmlUtils;

public class MainController {
	
	@FXML
	private TableView<User> groupTable;
	
	

   @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
   
    private Parent mainControllerRoot;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private FXMLLoader mainFirstFxmlLoader;
	private FXMLLoader mainSecondFxmlLoader;
	private FXMLLoader mainThirdFxmlLoader;
	private FXMLLoader mainFourthFxmlLoader;
	private FXMLLoader chattingFxmlLoader;
	private String showProfileUserName;

	
	public String getShowProfileUserName() {
		return showProfileUserName;
	}


	private static final String FXML_LOGIN_FXML = "/fxml/Login.fxml";

	private static final String FXML_MAIN_FOURTH_BUTTON_OF_V_BOX_FXML = "/fxml/MainFourthButtonOfVBox.fxml";
	
	private static final String FXML_MAIN_FIRST_BUTTON_OF_V_BOX_FXML = "/fxml/MainFirstButtonOfVBox.fxml";

	private static final String FXML_MAIN_SECOND_BUTTON_OF_V_BOX_FXML = "/fxml/MainSecondButtonOfVBox.fxml";
	
	private static final String FXML_MAIN_THIRD_BUTTON_OF_V_BOX_FXML = "/fxml/MainThirdButtonOfVBox.fxml";
	
	private static final String FXML_CHATTING_FXML = "/fxml/Chatting.fxml";

	private static final String FXML_OPTIONS_FXML = "/fxml/Options.fxml";

	@FXML
	private MainRightVBoxController mainRightVBoxController;

	@FXML
	private BorderPane borderPane;

	@FXML
	private Label welcomeLabel;

	@FXML
	MenuItem menuItemClose;

	@FXML
	MenuItem menuItemOptions;

	@FXML
	MenuItem menuItemProfile;

	@FXML
	MenuItem menuItemSignOut;

	@FXML
	ToggleGroup styleGroup;

	@FXML
	CheckMenuItem menuItemAlwaysOnTop;

	@FXML
	private void tableViewOnMouseClicked(){
		System.out.println("elo");
	}
	
	
	
	
	
	
	
	

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	public void setCenter(FXMLLoader fxmlLoader) {
		Parent root = null;
		try {
			//fxmlLoader.setRoot(null);
			//fxmlLoader.setController(null);
			if(fxmlLoader.getRoot() == null) {
				root = (Parent)fxmlLoader.load();
				System.out.println("1 " + fxmlLoader.getController().toString());
				borderPane.setCenter(root);
			}
			else {
				System.out.println("2 " + fxmlLoader.getController().toString());
				borderPane.setCenter(fxmlLoader.getRoot());
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	private void initialize() {
		mainRightVBoxController.setMainController(this);
		
	//	userData.add(loggedUserData.getFriends().get(0));
	    //	System.out.println("\n\ndodano " + loggedUserData.getFriends().get(0) + "\n\n");
		

		 personTable.setItems(this.getPersonData());
		 firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	      lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	      
	      
	      personTable.setRowFactory(new Callback<TableView<Person>, TableRow<Person>>() {
	          @Override
	          public TableRow<Person> call(TableView<Person> tableView) {
	            final TableRow<Person> row = new TableRow<>();
	            final ContextMenu rowMenu = new ContextMenu();
	            ContextMenu tableMenu = tableView.getContextMenu();
	            if (tableMenu != null) {
	              rowMenu.getItems().addAll(tableMenu.getItems());
	              rowMenu.getItems().add(new SeparatorMenuItem());
	            }
	            MenuItem chatItem = new MenuItem("Chat");
	            MenuItem profileItem = new MenuItem("Show profile");
	            MenuItem deleteFriendItem = new MenuItem("Delete friend");
	            
	            
	            
	            row.setOnMouseClicked(event -> {
	                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	                    Person rowData = row.getItem();
	                    System.out.println("Klikasz ziomka: " + rowData.getFirstName() + " " + rowData.getLastName());
						//setCenter(FXML_CHATTING_FXML);
	                    setCenter(getChattingFxmlLoader());

	                }
	            });
	            
	            
	            chatItem.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
						System.out.println("elodwazero");
						//setCenter(FXML_CHATTING_FXML);
						
						setCenter(getChattingFxmlLoader());
					}
	            	
	            });
	            
	            
	            profileItem.setOnAction(new EventHandler<ActionEvent>(){

	    					@Override
	    					public void handle(ActionEvent event) {

	    						System.out.println("elodwazero");
	    						//setCenter(FXML_MAIN_FOURTH_BUTTON_OF_V_BOX_FXML);

	    					
	    						showProfileUserName = row.getItem().getFirstName() + " " + row.getItem().getLastName();
	    						System.out.println(showProfileUserName);
	    						setCenter(getMainFourthFxmlLoader());
	    						//setCenter(FXML_MAIN_FOURTH_BUTTON_OF_V_BOX_FXML);
	    						mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setProfileNameLabelText(showProfileUserName);
	    						mainFourthFxmlLoader.
	    						<MainFourthButtonOfVBoxController>getController().
	    						setFirstAndLastName(row.getItem().getFirstName(), row.getItem().getLastName());
	    						
	    						setFriendData(0);
	    					}
	    	            	
	    	            });
	            
	        //    deleteFriendItem.setOnAction(event -> personTable.getItems().remove(cell.getItem()));

	            
	            deleteFriendItem.setOnAction(new EventHandler<ActionEvent>(){

	    					@Override
	    					public void handle(ActionEvent event) {
	    						//System.out.println(personTable.getSelectionModel().getSelectedItem().toString());
	    						Optional<ButtonType> result = DialogsUtils.confirmationDialog("Deleting friend", "Do you really want to kill your friendship?");
	    						if (result.get() == ButtonType.OK) {
	    						int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
	    						String selectedName = personTable.getSelectionModel().getSelectedItem().getFirstName();
								personTable.getItems().remove(selectedIndex);
	    						System.out.println("Usunieto poprawnie uzytkownika: " + selectedName);
	    						}
	    					}
	    	            	
	    	            });
	            
	            
	            rowMenu.getItems().addAll(chatItem, profileItem, deleteFriendItem);
	            row.contextMenuProperty().bind(
	                Bindings.when(Bindings.isNotNull(row.itemProperty()))
	                .then(rowMenu)
	                .otherwise((ContextMenu) null));
	            return row;
	          }
	        });
	      
	      
	      


	}

	@FXML
	public void menuItemCloseOnAction() {
		Optional<ButtonType> result = DialogsUtils.confirmationDialog("Exit", "Attention! It's dangerous!");
		if (result.get() == ButtonType.OK) {
			Platform.exit();
			System.exit(0);
		}
	}

	@FXML
	public void menuItemOptionsOnAction() {

		/*
		 * Parent parent = null; try { parent =
		 * FXMLLoader.load(getClass().getResource(FXML_OPTIONS_FXML)); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		Pane borderPane = FxmlUtils.fxmlLoader(FXML_OPTIONS_FXML);
		Stage stage = new Stage();
		Scene scene = new Scene(borderPane);
		stage.setHeight(800);
		stage.setWidth(1000);
		stage.setScene(scene);
		stage.setTitle("Options");
		stage.setMinHeight(575);
		stage.setMinWidth(300);
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}

	

	@FXML
	public void menuItemProfileOnAction() {
		//setCenter(FXML_MAIN_FOURTH_BUTTON_OF_V_BOX_FXML);
		setCenter(getMainFourthFxmlLoader());
		setLoggedUserData();
	}

	@FXML
	public void menuItemSignOutOnAction() {

		Optional<ButtonType> result = DialogsUtils.confirmationDialog("Sign Out", "Are you sure?");
		if (result.get() == ButtonType.OK) {
			Pane loginPane = FxmlUtils.fxmlLoader(FXML_LOGIN_FXML);
			Stage stage = new Stage();
			Scene scene = new Scene(loginPane);
			stage.setHeight(486);
			stage.setWidth(586);
			stage.centerOnScreen();
			stage.setScene(scene);
			stage.setTitle("Options");
			stage.setMinHeight(575);
			stage.setMinWidth(300);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			borderPane.getScene().getWindow().hide();
		}
	}

	@FXML
	public void menuItemCaspianOnAction() {
		Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
	}

	@FXML
	public void menuItemModenaOnAction() {
		Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);

	}

	@FXML
	public void menuItemAlwaysOnTopOnAction(ActionEvent actionEvent) {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
		stage.setAlwaysOnTop(value);
	}

	@FXML
	public void menuItemAboutOnAction() {
		DialogsUtils.dialogAboutApplication();
	}

	
	 private ObservableList<Person> personData = FXCollections.observableArrayList();
	 


	    /**
	     * Constructor
	     */
	    public MainController() {
	    	
	    //	userData.add(loggedUserData.getFriends().get(0));
	    //	System.out.println("\n\ndodano " + loggedUserData.getFriends().get(0) + "\n\n");
	        // Add some sample data
	        personData.add(new Person("Hans", "Muster"));
	        personData.add(new Person("Ruth", "Mueller"));


	        
	        createFxmlControllers();
	    }


	    public ObservableList<Person> getPersonData() {
	        return personData;
	    }
	    
		public void setSocket(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
			this.socket = socket;
			this.in = in;
			this.out = out;
		}
		
		public FXMLLoader getMainFirstFxmlLoader() {
			return mainFirstFxmlLoader;
		}
		
		public FXMLLoader getMainSecondFxmlLoader() {
			return mainSecondFxmlLoader;
		}
		
		public FXMLLoader getMainThirdFxmlLoader() {
			return mainThirdFxmlLoader;
		}
		
		public FXMLLoader getMainFourthFxmlLoader() {
			return mainFourthFxmlLoader;
		}
		
		
		public FXMLLoader getChattingFxmlLoader() {
			return chattingFxmlLoader;
		}

		public void createFxmlControllers(){
			mainFirstFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_FIRST_BUTTON_OF_V_BOX_FXML)); 
			MainFirstButtonOfVBoxController mainFirstButtonOfVBoxController = mainFirstFxmlLoader.<MainFirstButtonOfVBoxController>getController();
			
			mainSecondFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_SECOND_BUTTON_OF_V_BOX_FXML)); 
			MainSecondButtonOfVBoxController mainSecondButtonOfVBoxController = mainSecondFxmlLoader.<MainSecondButtonOfVBoxController>getController();
			
			mainThirdFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_THIRD_BUTTON_OF_V_BOX_FXML)); 
			MainThirdButtonOfVBoxController mainThirdButtonOfVBoxController = mainThirdFxmlLoader.<MainThirdButtonOfVBoxController>getController();
			
			mainFourthFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_FOURTH_BUTTON_OF_V_BOX_FXML)); 
			MainFourthButtonOfVBoxController mainFourthButtonOfVBoxController = mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController();

			chattingFxmlLoader  = new FXMLLoader(getClass().getResource(FXML_CHATTING_FXML)); 
			ChattingController chattingController = chattingFxmlLoader.<ChattingController>getController();

		}

		UserData loggedUserData;
		 private ObservableList<User> friendsData = FXCollections.observableArrayList();
		 
		public ObservableList<User> getFriendsData() {
			return friendsData;
		}


		public void setUserData(UserData dataObject) {
			this.loggedUserData = dataObject;
			System.out.println("\nSetUserData function");

		    welcomeLabel.setText("Welcome, " + dataObject.getUser().getUserName() + " :-)");

		    System.out.println( "Info o uzytkowniku " + 
		    dataObject.getUser().getAge()+
		    dataObject.getUser().getCity()+
		    dataObject.getUser().getCountry()+
		    dataObject.getUser().geteMail()+
		    dataObject.getUser().getLastName()+
		    dataObject.getUser().getFirstName()+
		    dataObject.getUser().getGender()
		    );
		   System.out.println("To powinno byc Karolina: " + dataObject.getFriends().get(0).getFirstName());
		    
		   friendsData.add(dataObject.getFriends().get(0));
		   System.out.println(friendsData.get(0).getFirstName());
		//   groupTable.setItems(this.friendsData);
		    
		}

	//	personTable.setItems(this.getPersonData());
		

		public Parent getMainControllerRoot() {
			return mainControllerRoot;
		}
		

		
		public void setMainControllerRoot(Parent mainControllerRoot) {
			this.mainControllerRoot = mainControllerRoot;
		}

		public void setLoggedUserData(){
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setAgeTextField(loggedUserData.getUser().getAge());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setCityTextField(loggedUserData.getUser().getCity());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setCountryTextField(loggedUserData.getUser().getCountry());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setEmailTextField(loggedUserData.getUser().geteMail());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setFirstNameTextField(loggedUserData.getUser().getFirstName());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setLastNameTextField(loggedUserData.getUser().getLastName());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setGenderTextField(loggedUserData.getUser().getGender());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setUsernameTextField(loggedUserData.getUser().getUserName());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setYourProfileNameLabelText();
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setUserProfileImage(new Image(Main.class.getResourceAsStream( "../images/Onion-300x300.png" )));
		}
		
		public void setFriendData(int friendIndex){
			
			
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setAgeTextField(9200);
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setCityTextField("");
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setCountryTextField("");
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setEmailTextField("");
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setFirstNameTextField("");
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setLastNameTextField("");
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setGenderTextField("");
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setUsernameTextField("");
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setProfileNameLabelText(showProfileUserName);
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setUserProfileImage(new Image(Main.class.getResourceAsStream( "../images/icon.png" )));
			
			// ELO tutaj nizej poprawne jak bedzie serwer dzialal
		/*	
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setAgeTextField(loggedUserData.getFriends().get(friendIndex).getAge());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setCityTextField(loggedUserData.getFriends().get(friendIndex).getCity());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setCountryTextField(loggedUserData.getFriends().get(friendIndex).getCountry());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setEmailTextField(loggedUserData.getFriends().get(friendIndex).geteMail());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setFirstNameTextField(loggedUserData.getFriends().get(friendIndex).getFirstName());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setLastNameTextField(loggedUserData.getFriends().get(friendIndex).getLastName());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setGenderTextField(loggedUserData.getFriends().get(friendIndex).getGender());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setUsernameTextField(loggedUserData.getFriends().get(friendIndex).getUserName());
			mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setProfileNameLabelText(showProfileUserName);
		*/
		}
}
