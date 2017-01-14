package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;

public class ChattingController {

	@FXML TextField chatTF;
	@FXML Button sendMessageButton;

	@FXML public void chatTFonKeyReleased(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER))
		{
        System.out.println("Wcisnieto ENTER! Robimy clear");
        chatTF.clear();
        
		}
      }

	@FXML public void sendMessageButtonOnAction() {

        chatTF.clear();

	}

	
}
