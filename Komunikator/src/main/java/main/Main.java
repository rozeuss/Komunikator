package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.setResizable(false);
		// primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		LOGGER.log(Level.FINE, "Main App window created and shown");

	}

}
