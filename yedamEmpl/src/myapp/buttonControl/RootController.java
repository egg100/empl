package myapp.buttonControl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RootController implements Initializable {
	@FXML
	Button btn1, btn2, btn3;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		btn1.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent arg0) {
//				handleBtn1Action(arg0);
//			}
//		});
//		
//		btn2.setOnAction(event -> handleBtn2Action(event));
//		btn3.setOnAction(event -> handleBtn3Action(event));
	}
	
	public void handleBtn1Action(ActionEvent e) {
		System.out.println("버튼1 클릭.");
	}
	
	public void handleBtn2Action(ActionEvent e) {
		System.out.println("버튼2 클릭.");
	}
	
	public void handleBtn3Action(ActionEvent e) {
		Platform.exit();
	}

}
