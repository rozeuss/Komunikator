package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

import javax.sql.RowSetMetaData;

import database.DataConnectionWorker;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import sun.awt.AppContext;
import transferDataContainers.FoundedUsers;
import transferDataContainers.Friends;
import transferDataContainers.Invitation;
import transferDataContainers.OverdueInvitations;
import transferDataContainers.User;
import transferDataContainers.UserData;
import utils.DialogsUtils;
import utils.FxmlUtils;

public class MainController {
	@FXML
	private TableView<User> groupTable;
   @FXML
    private TableView<User> personTable;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
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
<<<<<<< HEAD
	private FXMLLoader personEditDialogFxmlLoader;
=======
	
	private FXMLLoader loginFxmlLoader;



>>>>>>> NewWorkingBranch
	private String showProfileUserName;
	private ArrayList<Invitation> invitations;
	private Parent mainThirdFxmlRoot;

	
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
		
		 personTable.setItems(this.getFriendsData());
		 firstNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFirstName()));
	     lastNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLastName())); 
	     personTable.setRowFactory(new Callback<TableView<User>, TableRow<User>>() {
	         
	    	 @Override  
	          public TableRow<User> call(TableView<User> tableView) {
	            final TableRow<User> row = new TableRow<>();
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
	                    User rowData = row.getItem();
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
	    						
	    						showProfileUserName = row.getItem().getFirstName() + " " + row.getItem().getLastName();
	    						setCenter(getMainFourthFxmlLoader());
	    						MainFourthButtonOfVBoxController controller = mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController();
	    						controller.setProfileNameLabelText(showProfileUserName);
	    						System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " + mainFxmlLoader);
	    						mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setMainFXMLLoader(mainFxmlLoader);
	    						for (User u : friendsData){
	    							if(u.getUserName().equals(row.getItem().getUserName()))
	    							{
	    								User item = row.getItem();
	    								setFriendData(item);
	    							}

	    							
	    						}
	    						

	    						
	    						
	    						
	    		
	    			
	    					
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
			System.out.println("Kontroler tutaj " + loginFxmlLoader.<LoginController>getController());
			try {
				loginFxmlLoader.<LoginController>getController().getIn().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				loginFxmlLoader.<LoginController>getController().getOut().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				loginFxmlLoader.<LoginController>getController().getSocket().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

	


	    public MainController() {
	    }



	    
		public void setSocket(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
			this.socket = socket;
			this.out = out;
			this.in = in;
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
		

		private MainThirdButtonOfVBoxController mainThirdButtonOfVBoxController;



		private FXMLLoader mainFxmlLoader;



		private Parent mainFourthFxmlRoot;



		private MainFourthButtonOfVBoxController mainFourthButtonOfVBoxController;
		
		public void createFxmlControllers(FXMLLoader loginFxmlLoader){
	//		mainFxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml")); 
			System.out.println("Otrzymany loginFXMLLoader " + loginFxmlLoader);
			this.loginFxmlLoader = loginFxmlLoader;
			System.out.println("Controller logina: " + this.loginFxmlLoader.<LoginController>getController());
			
			
			mainFirstFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_FIRST_BUTTON_OF_V_BOX_FXML)); 
			MainFirstButtonOfVBoxController mainFirstButtonOfVBoxController = mainFirstFxmlLoader.<MainFirstButtonOfVBoxController>getController();
			
			mainSecondFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_SECOND_BUTTON_OF_V_BOX_FXML)); 
			MainSecondButtonOfVBoxController mainSecondButtonOfVBoxController = mainSecondFxmlLoader.<MainSecondButtonOfVBoxController>getController();
			
			mainThirdFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_THIRD_BUTTON_OF_V_BOX_FXML)); 
			try {
				this.mainThirdFxmlRoot = (Parent)mainThirdFxmlLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mainThirdButtonOfVBoxController = mainThirdFxmlLoader.<MainThirdButtonOfVBoxController>getController();
			mainThirdButtonOfVBoxController.setMainThirdButtonOfVBoxControllerRoot(mainThirdFxmlRoot);

			System.out.println("mainThirdButtonOfVBoxController " +  in.getClass());
			System.out.println(out.getClass());
			System.out.println(socket.getClass());
			mainThirdButtonOfVBoxController.setSocket(socket, out, in);
			mainThirdButtonOfVBoxController.createSender();
			
			mainFourthFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_FOURTH_BUTTON_OF_V_BOX_FXML)); 
<<<<<<< HEAD
=======
			try {
				this.mainFourthFxmlRoot = (Parent)mainFourthFxmlLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mainFourthButtonOfVBoxController = mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController();
			mainFourthButtonOfVBoxController.setMainFourthButtonOfVBoxControllerRoot(mainFourthFxmlRoot);
			
			MainFourthButtonOfVBoxController mainFourthButtonOfVBoxController = mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController();
			
>>>>>>> NewWorkingBranch
			
			chattingFxmlLoader  = new FXMLLoader(getClass().getResource(FXML_CHATTING_FXML)); 
			ChattingController chattingController = chattingFxmlLoader.<ChattingController>getController();
			

		}

		private User loggedUserData;
<<<<<<< HEAD
		private Friends friendsLoggedUserData;
=======
		Friends friendsLoggedUserData;
>>>>>>> NewWorkingBranch
		
		 private ObservableList<User> friendsData = FXCollections.observableArrayList();
		 
		public ObservableList<User> getFriendsData() {
			return friendsData;
		}


		public void setUser(User dataObject) {
<<<<<<< HEAD
			this.loggedUserData = dataObject;
			mainThirdButtonOfVBoxController.setLoggedUser(loggedUserData);
=======
			this.setLoggedUserData(dataObject);
>>>>>>> NewWorkingBranch

		    welcomeLabel.setText("Welcome, " + dataObject.getUserName() + " :-)");

		    System.out.println( "Info o uzytkowniku " + 
		    dataObject.getAge()+
		    dataObject.getCity()+
		    dataObject.getCountry()+
		    dataObject.geteMail()+
		    dataObject.getLastName()+
		    dataObject.getFirstName()+
		    dataObject.getGender()
		    );
		}
	

		public void addFriends(Friends dataObject) {
			this.friendsLoggedUserData = dataObject;
		
			if(dataObject.getFriends().size() != 0){
				System.out.println("To powinno byc Karolina: " +
						dataObject.getFriends().get(0).getFirstName()
						);
					   ArrayList<User> friendList = dataObject.getFriends();  
					   for(User user: friendList){
						   friendsData.add(user);
					   }
			}
	}
		
		
		public Parent getMainControllerRoot() {
			return mainControllerRoot;
		}
		

		
		public void setMainControllerRoot(Parent mainControllerRoot) {
			this.mainControllerRoot = mainControllerRoot;
		}


		public void addInvitations(OverdueInvitations dataObject) {
			this.invitations = dataObject.getInvitations();
		}

		public void setLoggedUserData(){

			MainFourthButtonOfVBoxController controller = mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController();
			controller.setAgeTextField(loggedUserData.getAge());
			controller.setCityTextField(loggedUserData.getCity());
			controller.setCountryTextField(loggedUserData.getCountry());
			controller.setEmailTextField(loggedUserData.geteMail());
			controller.setFirstNameTextField(loggedUserData.getFirstName());
			controller.setLastNameTextField(loggedUserData.getLastName());
			controller.setGenderTextField(loggedUserData.getGender());
			controller.setUsernameTextField(loggedUserData.getUserName());
			controller.setYourProfileNameLabelText();
			controller.setUserProfileImage(new Image(Main.class.getResourceAsStream( "../images/Onion-300x300.png" )));
			controller.getEditButton().setDisable(false);
		}
		
		public void setFriendData(User friend){
			MainFourthButtonOfVBoxController controller = mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController();
			controller.setAgeTextField(friend.getAge());
			controller.setCityTextField(friend.getCity());
			controller.setCountryTextField(friend.getCountry());
			controller.setEmailTextField(friend.geteMail());
			controller.setFirstNameTextField(friend.getFirstName());
			controller.setLastNameTextField(friend.getLastName());
			controller.setGenderTextField(friend.getGender());
			controller.setUsernameTextField(friend.getUserName());
			controller.setProfileNameLabelText(showProfileUserName);
			controller.setUserProfileImage(new Image(Main.class.getResourceAsStream( "../images/icon.png" )));
			controller.getEditButton().setDisable(true);
			controller.getApplyButton().setDisable(true);
		}





		/**
		 * @return the loggedUserData
		 */
		public User getLoggedUserData() {
			return loggedUserData;
		}





		/**
		 * @param loggedUserData the loggedUserData to set
		 */
		public void setLoggedUserData(User loggedUserData) {
			this.loggedUserData = loggedUserData;
		}





		/**
		 * @return the mainFxmlLoader
		 */
		public FXMLLoader getMainFxmlLoader() {
			return mainFxmlLoader;
		}





		/**
		 * @param mainFxmlLoader the mainFxmlLoader to set
		 */
		public void setMainFxmlLoader(FXMLLoader mainFxmlLoader) {
			this.mainFxmlLoader = mainFxmlLoader;
	
		}

		public void setFoundedUsers(FoundedUsers dataObject) {
			mainThirdButtonOfVBoxController.setFoundedUsers(dataObject);	
		}
}
