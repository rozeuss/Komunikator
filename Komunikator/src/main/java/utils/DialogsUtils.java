package utils;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogsUtils {

	
	public static void dialogAboutApplication(){
		Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
		informationAlert.setTitle("About");
		informationAlert.setHeaderText("About app.");
		informationAlert.setContentText("This is a app. True a art.");
		informationAlert.showAndWait();
	}
	
	public static Optional<ButtonType> confirmationDialog(String titleString){
		Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmationAlert.setTitle(titleString);
		confirmationAlert.setHeaderText("Are you sure?");
		Optional<ButtonType> result = confirmationAlert.showAndWait();
		return result;
	}
}
