package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tasks.FileAppender;
import transferData.Sender;
import transferDataContainers.EndOfFriendship;
import transferDataContainers.FoundedUsers;
import transferDataContainers.Friends;
import transferDataContainers.Invitation;
import transferDataContainers.Message;
import transferDataContainers.NewFriend;
import transferDataContainers.OverdueInvitations;
import transferDataContainers.UnreadMessages;
import transferDataContainers.User;

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

	public ObjectOutputStream getOut() {
		return out;
	}

	private ObjectInputStream in;

	public ObjectInputStream getIn() {
		return in;
	}

	private FXMLLoader mainThirdFxmlLoader;
	private FXMLLoader mainFirstFxmlLoader;
	private FXMLLoader mainFourthFxmlLoader;
	private FXMLLoader chattingFxmlLoader;
	private FXMLLoader loginFxmlLoader;
	private String showProfileUserName;
	private ArrayList<Invitation> invitations;
	private Parent mainThirdFxmlRoot;
	private Parent mainFirstFxmlRoot;
	private Parent mainSecondFxmlRoot;
	private Parent chattingFxmlRoot;
	private FXMLLoader mainSecondFxmlLoader;
	private Sender sender;
	private FileAppender fileAppender;



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

	
	
	
	public String getShowProfileUserName() {
		return showProfileUserName;
	}

	
	@FXML
	private void tableViewOnMouseClicked(){

	}

	public void setCenter(FXMLLoader fxmlLoader) {
		Parent root = null;
		try {
			if (fxmlLoader.getRoot() == null) {
				root = (Parent) fxmlLoader.load();
				borderPane.setCenter(root);
			} else {
				borderPane.setCenter(fxmlLoader.getRoot());
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private ArrayList<SecondViewController> secondViewsControllers = new ArrayList<SecondViewController>();
	private boolean isTabOpened;
	private ObservableList<String> openedTabsUsernames = FXCollections.observableArrayList();

	private void createTabDynamically(String firstName, String lastName, String userName) {
		if (!openedTabsUsernames.contains(userName)) {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/secondView.fxml"));
			SecondViewController secondView = new SecondViewController();
			secondViewsControllers.add(secondView);

			ChattingController chattingController = getChattingFxmlLoader().<ChattingController>getController();
			setCenter(getChattingFxmlLoader());
			Tab myDynamicTab = chattingController.getMyDynamicTab();
			try {
				openedTabsUsernames.add(userName);
				secondView.setUsername(userName);
				secondView.setSecondFXML(loader);
				secondView.setChattingFXMLLoader(getChattingFxmlLoader());
				secondView.setMainFxmlLoader(mainFxmlLoader);				
				loader.setController(secondView);
				Parent parent = loader.load();
				myDynamicTab = new Tab("" + firstName + " " + lastName + " " + "[" + userName + "]");
				myDynamicTab.setContent(parent);
				myDynamicTab.setOnClosed(new EventHandler<Event>() {
					@Override
					public void handle(Event arg0) {
						openedTabsUsernames.remove(userName);
					}
				});
				chattingController.getTabPane().getTabs().add(myDynamicTab);
				chattingController.getTabPane().getSelectionModel().select(myDynamicTab);
				for(Message message : unreadMessages){
					String senderName = message.getSender();
					String text = message.getTextContent();
					if(secondView.getUsername().equals(senderName)){
						secondView.addMessageToConversationTextArea(text, senderName,LocalDateTime.now());
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			chattingController.getTabPane().getSelectionModel().select(openedTabsUsernames.indexOf(userName));
	}

	@FXML
	private void initialize() {
		mainRightVBoxController.setMainController(this);


		
	}
	
	public void updateFriendsList(){
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
					if (event.getClickCount() == 2 && (!row.isEmpty())) {
						User rowData = row.getItem();
						setCenter(getChattingFxmlLoader());
						createTabDynamically(row.getItem().getFirstName(), row.getItem().getLastName(),
								row.getItem().getUserName());
					}
				});

				chatItem.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						setCenter(getChattingFxmlLoader());
						createTabDynamically(row.getItem().getFirstName(), row.getItem().getLastName(),
								row.getItem().getUserName());

					}

				});

				profileItem.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						MainFourthButtonOfVBoxController controller = mainFourthFxmlLoader
								.<MainFourthButtonOfVBoxController>getController();
						showProfileUserName = row.getItem().getFirstName() + " " + row.getItem().getLastName();
						setCenter(getMainFourthFxmlLoader());
						controller.setProfileNameLabelText(showProfileUserName);
						// mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController().setMainFXMLLoader(mainFxmlLoader);
						if (controller.getApplyButton().isDisabled() == false)
							controller.changeEditable();
						for (User u : friendsData) {
							if (u.getUserName().equals(row.getItem().getUserName())) {
								User item = row.getItem();
								controller.setFriendData(item);
							}
						}
					}
				});

				deleteFriendItem.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						Optional<ButtonType> result = DialogsUtils.confirmationDialog("Deleting friend", "Do you really want to kill your friendship?");
						if (result.get() == ButtonType.OK) {
						int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
						String selectedName = personTable.getSelectionModel().getSelectedItem().getFirstName();
						EndOfFriendship endOfFriendship = new EndOfFriendship(loggedUserData, row.getItem());
						try {
							sender.send(endOfFriendship);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						personTable.getItems().remove(selectedIndex);
						}
					}
	            	
	            });

				rowMenu.getItems().addAll(chatItem, profileItem, deleteFriendItem);
				row.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(row.itemProperty())).then(rowMenu)
						.otherwise((ContextMenu) null));
				return row;
			}
		});

	}

	@FXML
	public void menuItemCloseOnAction() {

		Optional<ButtonType> result = DialogsUtils.confirmationDialog("Exit", "Attention! It's dangerous!");
		if (result.get() == ButtonType.OK) {
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

			try {
				sender.send("quit");
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
		// setCenter(FXML_MAIN_FOURTH_BUTTON_OF_V_BOX_FXML);
		setCenter(getMainFourthFxmlLoader());
		getMainFourthFxmlLoader().<MainFourthButtonOfVBoxController>getController().setLoggedUserData();
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
	private MainFirstButtonOfVBoxController mainFirstButtonOfVBoxController;
	private ChattingController chattingController;

	public void createFxmlControllers(FXMLLoader loginFxmlLoader) {
		this.sender = new Sender(out);
		this.loginFxmlLoader = loginFxmlLoader;

		mainFirstFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_FIRST_BUTTON_OF_V_BOX_FXML));
		try {
			this.mainFirstFxmlRoot = (Parent) mainFirstFxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainFirstButtonOfVBoxController = mainFirstFxmlLoader.<MainFirstButtonOfVBoxController>getController();
		mainFirstButtonOfVBoxController.setMainFirstButtonOfVBoxControllerRoot(mainFirstFxmlRoot);
		mainFirstButtonOfVBoxController.createSender(out);
		mainFirstButtonOfVBoxController.setMainViewController(this);

		mainSecondFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_SECOND_BUTTON_OF_V_BOX_FXML));
		try {
			this.mainSecondFxmlRoot = (Parent) mainSecondFxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mainThirdFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_THIRD_BUTTON_OF_V_BOX_FXML));
		try {
			this.mainThirdFxmlRoot = (Parent) mainThirdFxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainThirdButtonOfVBoxController = mainThirdFxmlLoader.<MainThirdButtonOfVBoxController>getController();
		mainThirdButtonOfVBoxController.setMainThirdButtonOfVBoxControllerRoot(mainThirdFxmlRoot);

		mainThirdButtonOfVBoxController.setSocket(socket, out, in);
		mainThirdButtonOfVBoxController.createSender();
		mainThirdButtonOfVBoxController.setMainController(this);

		mainFourthFxmlLoader = new FXMLLoader(getClass().getResource(FXML_MAIN_FOURTH_BUTTON_OF_V_BOX_FXML));

		try {
			this.mainFourthFxmlRoot = (Parent) mainFourthFxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainFourthButtonOfVBoxController = mainFourthFxmlLoader.<MainFourthButtonOfVBoxController>getController();
		mainFourthButtonOfVBoxController.setMainFourthButtonOfVBoxControllerRoot(mainFourthFxmlRoot);

		chattingFxmlLoader = new FXMLLoader(getClass().getResource(FXML_CHATTING_FXML));
		try {
			this.chattingFxmlRoot = (Parent) chattingFxmlLoader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		chattingController = chattingFxmlLoader.<ChattingController>getController();
		chattingController.setSocket(socket, out, in);
		chattingController.createSender();
	}

	private User loggedUserData;
	private Friends friendsLoggedUserData;

	private ObservableList<User> friendsData = FXCollections.observableArrayList();

	private ArrayList<Message> unreadMessages = new ArrayList<Message>();

	public ObservableList<User> getFriendsData() {
		return friendsData;
	}

	public void setUser(User dataObject) {
		this.loggedUserData = dataObject;
		mainThirdButtonOfVBoxController.setLoggedUser(loggedUserData);
		mainFirstButtonOfVBoxController.setLoggedUser(loggedUserData);
		this.setLoggedUserData(dataObject);
		welcomeLabel.setText("Welcome, " + dataObject.getUserName() + " :-)");

	}

	public void addFriends(Friends dataObject) {
		this.friendsLoggedUserData = dataObject;

		if (dataObject.getFriends().size() != 0) {
			ArrayList<User> friendList = dataObject.getFriends();
			for (User user : friendList) {
				friendsData.add(user);
			}
		}
		updateFriendsList();
		
		
		ObservableList<String> friendsNames = FXCollections.observableArrayList();
		for(User friend : friendsData)
		{
			friendsNames.add(friend.getUserName());
		}
		getMainSecondFxmlLoader().<MainSecondButtonOfVBoxController>getController().getListView().setItems(friendsNames);
	}

	public Parent getMainControllerRoot() {
		return mainControllerRoot;
	}

	public void setMainControllerRoot(Parent mainControllerRoot) {
		this.mainControllerRoot = mainControllerRoot;
	}

	public void addInvitations(OverdueInvitations dataObject) {
		this.invitations = dataObject.getInvitations();
		mainFirstButtonOfVBoxController.setInvitationList(invitations);
	}

	/**
	 * @return the loggedUserData
	 */
	public User getLoggedUserData() {
		return loggedUserData;
	}

	/**
	 * @param loggedUserData
	 *            the loggedUserData to set
	 */
	public void setLoggedUserData(User loggedUserData) {
		this.loggedUserData = loggedUserData;
	}

	public FXMLLoader getMainFxmlLoader() {
		return mainFxmlLoader;
	}

	public void setMainFxmlLoader(FXMLLoader mainFxmlLoader) {
		this.mainFxmlLoader = mainFxmlLoader;
	}

	public void setFoundedUsers(FoundedUsers dataObject) {
		mainThirdButtonOfVBoxController.setFoundedUsers(dataObject);
	}


	public void setUnreadMessages(UnreadMessages dataObject) {
		this.unreadMessages  = dataObject.getUnreadMessages();
	}

	private Message message;

	public Message getMessage() {
		return message;
	}

	private StringProperty messageTEXT;

	public void setMessage(Message dataObject) {
		// TODO Auto-generated method stub
		// this.secondView.addMessageToConversationTextArea(dataObject.getTextContent(),
		// dataObject.getSender());
		SecondViewController secondViewController = null;
		for (SecondViewController controller : this.secondViewsControllers) {
			if (dataObject.getSender().equals(controller.getUsername())) {
				secondViewController = controller;
			}
		}
		if(secondViewController == null) this.unreadMessages.add(dataObject);
		else secondViewController.addMessageToConversationTextArea(dataObject.getTextContent(), dataObject.getSender(), LocalDateTime.now());
		message = dataObject;


	}

	public void setNewInvitation(Invitation dataObject) {
		this.mainFirstButtonOfVBoxController.addNewInvitation(dataObject);
	}

	public void setNewFriend(NewFriend friend) {
		
		User newFriend = friend.getFriend();
		friendsData.add(newFriend);
		updateFriendsList();
	}
}
