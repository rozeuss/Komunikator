package controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tasks.SplashScreen;
import utils.FxmlUtils;

public class SplashController implements Initializable {

	@FXML
	private AnchorPane rootAnchorPane;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private MainController mainController;
	private SplashScreen splashScreen;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		splashScreen = new SplashScreen(this);
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public SplashScreen getSplashScreen() {
		return this.splashScreen;
	}
	
	public void startSplashScreen() {
		Thread t = new Thread(splashScreen);
		t.start();
	}
	
	public synchronized void launchMainView() {
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	Parent root = mainController.getMainControllerRoot();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				//scene.setFill(Color.TRANSPARENT);
				//root.setStyle("-fx-background-color: transparent;");
				stage.setResizable(true);
				//stage.initStyle(StageStyle.TRANSPARENT);
				stage.show();
				rootAnchorPane.getScene().getWindow().hide();
		    }
		});
		
	}
}
