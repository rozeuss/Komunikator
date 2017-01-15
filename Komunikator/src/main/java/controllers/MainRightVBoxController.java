package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class MainRightVBoxController {
	@FXML
	private Button firstButtonVBox;
	@FXML
	private ToggleGroup toggleGroup;

	private MainController mainController;
	
	@FXML
	VBox mainRightVBox;
	@FXML
	Button secondButtonVBox;
	@FXML
	Button thirdButtonVBox;
	@FXML
	Button fourthButtonVBox;

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	@FXML
	public void firstButtonVBoxOnAction(ActionEvent event) {

		mainController.setCenter(mainController.getMainFirstFxmlLoader());
	}

	@FXML
	public void secondButtonVBoxOnAction() {
		//TODO podwójne wyœwietlanie
		//mainController.setCenter(mainController.getMainSecondFxmlLoader());
		mainController.setCenter(mainController.getMainSecondFxmlLoader());
	}

	@FXML
	public void thirdButtonVBoxOnAction() {
		//mainController.setCenter("/fxml/MainThirdButtonOfVBox.fxml");
		mainController.setCenter(mainController.getMainThirdFxmlLoader());

	}

	@FXML
	public void fourthButtonVBoxOnAction() {

		mainController.setCenter(mainController.getMainFourthFxmlLoader());

	}

}
