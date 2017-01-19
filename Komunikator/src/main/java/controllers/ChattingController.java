package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;

public class ChattingController {

	@FXML
	private TabPane tabPane;

	/**
	 * @return the tabPane
	 */
	public TabPane getTabPane() {
		return tabPane;
	}

	/**
	 * @param tabPane the tabPane to set
	 */
	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	@FXML TextField chatTextField;
	@FXML Button sendMessageButton;
	
	@FXML
	private Tab myDynamicTab;

	@FXML public void chatTFonKeyReleased(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER))
		{
        System.out.println("Wcisnieto ENTER! Robimy clear");
        chatTextField.clear();
        
		}
      }

	@FXML public void sendMessageButtonOnAction() {

        chatTextField.clear();

	}

	/**
	 * @return the myDynamicTab
	 */
	public Tab getMyDynamicTab() {
		return myDynamicTab;
	}

	/**
	 * @param myDynamicTab the myDynamicTab to set
	 */
	public void setMyDynamicTab(Tab myDynamicTab) {
		this.myDynamicTab = myDynamicTab;
	}



	
}
