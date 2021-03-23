package myapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import myapp.viewPackage.BoardController;

public class FxmlApp2 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("viewPackage/Board.fxml"));
		Parent root = loader.load();
		
		BoardController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		 
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
