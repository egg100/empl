package myapp.exercise;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
		TableColumn<Student, String> tcLang =
				(TableColumn<Student, String>) tableView.getColumns().get(1);
		tcLang.setCellValueFactory(new PropertyValueFactory<Student, String>("lang"));
		TableColumn<Student, String> tcMath =
				(TableColumn<Student, String>) tableView.getColumns().get(2);
		tcMath.setCellValueFactory(new PropertyValueFactory<Student, String>("math"));
		TableColumn<Student, String> tcEng =
				(TableColumn<Student, String>) tableView.getColumns().get(3);
		tcEng.setCellValueFactory(new PropertyValueFactory<Student, String>("eng"));
		
		
		tableView.setItems(list);
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
					if(txtName.getText() != "") {
						std.setName(txtName.getText());
					} else {
						std.setName("무명");
					}
					if(txtLang.getText() != "" && isDouble(txtLang.getText())) {
						std.setLang(Integer.parseInt(txtLang.getText()));
					} else {
						std.setLang(0);
					}
					if(txtMath.getText() != "" && isDouble(txtMath.getText())) {
						std.setMath(Integer.parseInt(txtMath.getText()));
					} else {
						std.setMath(0);
					}
					if(txtEng.getText() != "" && isDouble(txtEng.getText())) {
						std.setEng(Integer.parseInt(txtEng.getText()));
					} else {
						std.setEng(0);
					}
					list.add(std);
					stage.close();
				}
			});
			
			Button btnCancel = (Button) bp.lookup("#btnCancel");
			btnCancel.setOnAction(a -> stage.close());
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void handleBtnChartAction(ActionEvent e) {
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		try {
			BorderPane ch = FXMLLoader.load(getClass().getResource("BarChart.fxml"));
			Scene scene = new Scene(ch);
			stage.setScene(scene);
			stage.show();
			
			BarChart<String, Integer> barChart = (BarChart<String, Integer>) ch.lookup("#barChart");
			CategoryAxis category = (CategoryAxis) ch.lookup("#category");
			
			XYChart.Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
			Stream<Student> stream = list.stream();
			ObservableList<XYChart.Data<String, Integer>> data1 = stream.map(t -> new XYChart.Data<String, Integer>(t.getName(), t.getLang()))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
			series1.setData(data1);
			series1.setName("국어");
			
			XYChart.Series<String, Integer> series2 = new XYChart.Series<String, Integer>();
			stream = list.stream();
			ObservableList<XYChart.Data<String, Integer>> data2 = stream.map(t -> new XYChart.Data<String, Integer>(t.getName(), t.getMath()))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
			series2.setData(data2);
			series2.setName("수학");
			
			XYChart.Series<String, Integer> series3 = new XYChart.Series<String, Integer>();
			stream = list.stream();
			ObservableList<XYChart.Data<String, Integer>> data3 = stream.map(t -> new XYChart.Data<String, Integer>(t.getName(), t.getEng()))
				.collect(Collectors.toCollection(FXCollections::observableArrayList));
			series3.setData(data3);
			series3.setName("영어");
			barChart.getData().add(series1);
			barChart.getData().add(series2);
			barChart.getData().add(series3);
			
			ObservableList<String> categoryList = FXCollections.observableArrayList();
			for(Student c : list) {
				categoryList.add(c.getName());
			}
			category.setCategories(categoryList);
			
			Button btnCancel = (Button) ch.lookup("#btnCancel");
			btnCancel.setOnAction(a -> stage.close());
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
