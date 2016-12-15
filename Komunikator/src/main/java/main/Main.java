package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.FxmlUtils;

public class Main extends Application {

	private static final String FXML_LOGIN_FXML = "/fxml/Login.fxml";
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		//Parent root = FXMLLoader.load(getClass().getResource(FXML_LOGIN_FXML));
		Pane borderPane = FxmlUtils.fxmlLoader(FXML_LOGIN_FXML);
		Scene scene = new Scene(borderPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.setResizable(false);
		// primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		LOGGER.log(Level.FINE, "Main App window created and shown");

	}

}
