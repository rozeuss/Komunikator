package controllers;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.DialogsUtils;
import utils.FxmlUtils;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;

public class MainController {

	private static final String FXML_LOGIN_FXML = "/fxml/Login.fxml";

	private static final String FXML_MAIN_FOURTH_BUTTON_OF_V_BOX_FXML = "/fxml/MainFourthButtonOfVBox.fxml";

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

	public void setCenter(String fxmlPath) {
		borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
	}

	@FXML
	private void initialize() {
		mainRightVBoxController.setMainController(this);

	}

	@FXML
	public void menuItemCloseOnAction() {
		Optional<ButtonType> result = DialogsUtils.confirmationDialog("Exit");
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
		setCenter(FXML_MAIN_FOURTH_BUTTON_OF_V_BOX_FXML);
	}

	@FXML
	public void menuItemSignOutOnAction() {

		Optional<ButtonType> result = DialogsUtils.confirmationDialog("Sign Out");
		if (result.get() == ButtonType.OK) {
			/*
			 * Parent parent = null; try { parent =
			 * FXMLLoader.load(getClass().getResource(FXML_LOGIN_FXML)); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			Pane borderPane = FxmlUtils.fxmlLoader(FXML_LOGIN_FXML);
			Stage stage = new Stage();
			Scene scene = new Scene(borderPane);
			stage.setHeight(486);
			stage.setWidth(586);
			stage.centerOnScreen();
			stage.setScene(scene);
			stage.setTitle("Options");
			stage.setMinHeight(575);
			stage.setMinWidth(300);
			// stage.initStyle(StageStyle.UNDECORATED);
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

}
