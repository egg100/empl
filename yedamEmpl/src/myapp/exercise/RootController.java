package myapp.exercise;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.BoardVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {
	@FXML TableView<Student> tableView;
	
	private Stage primaryStage;
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	ObservableList<Student> list = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableColumn<Student, String> tcName =
				(TableColumn<Student, String>) tableView.getColumns().get(0);
		tcName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
	}
	
	public void handleBtnAddAction(ActionEvent e) {
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		try {
			BorderPane bp = FXMLLoader.load(getClass().getResource("Form.fxml"));
			Scene scene = new Scene(bp);
			stage.setScene(scene);
			stage.show();
			
			Button btnReg = (Button) bp.lookup("#btnReg");
			btnReg.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					TextField txtName = (TextField) bp.lookup("#txtName");
					TextField txtLang = (TextField) bp.lookup("#txtLang");
					TextField txtMath = (TextField) bp.lookup("#txtMath");
					TextField txtEng = (TextField) bp.lookup("#txtEng");
					
					Student std = new Student();
					std.setName(txtName.getText());
					std.setLang(Integer.parseInt(txtLang.getText()));
					std.setMath(Integer.parseInt(txtMath.getText()));
					std.setEng(Integer.parseInt(txtEng.getText()));
					list.add(std);
					stage.close();
					
				}
				
			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
