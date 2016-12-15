package utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FxmlUtils {

	
	public static Pane fxmlLoader (String fxmlPath){
		FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
		try {
			return loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	

}
