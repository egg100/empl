package myapp.viewPackage;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import common.BoardVO;
import common.InputBoardVO;
import common.InputDAO;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class BoardController implements Initializable {
	@FXML TableView<BoardVO> tableView;
	@FXML TextField boardNo, title;
	@FXML TextArea contents;
	@FXML ComboBox<String> publicity;
	@FXML DatePicker exitDate;
	@FXML Button updateBtn, deleteBtn, addBtn, prevBtn, nextBtn;
	
	private Stage primaryStage;
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		deleteBtn.setOnAction(e -> deleteBtnAction(e));
		addBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				addBtnAction(arg0);
			}
			
		});
		
		ObservableList<BoardVO> list = InputDAO.boardList();
		
		tableView.setPrefWidth(550); // prefWidth="30"
		tableView.setLayoutX(30);
		
		TableColumn<BoardVO, Integer> tcBoardNo = 
				(TableColumn<BoardVO, Integer>) tableView.getColumns().get(0);
		tcBoardNo.setCellValueFactory(
				new PropertyValueFactory<BoardVO, Integer>("boardNo"));
		tcBoardNo.setPrefWidth(60);
		TableColumn<BoardVO, String> tcTitle =
				(TableColumn<BoardVO, String>) tableView.getColumns().get(1);
		tcTitle.setCellValueFactory(
				new Callback<CellDataFeatures<BoardVO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BoardVO, String> param) {
						return param.getValue().titleProperty();
					}
		});
		tcTitle.setPrefWidth(150);
		TableColumn<BoardVO, String> tcPub = new TableColumn<BoardVO, String>("공개");
		tcPub.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("publicity"));
		tcPub.setPrefWidth(45);
		tableView.getColumns().add(tcPub);
		
		TableColumn<BoardVO, String> tcExitDate = new TableColumn<BoardVO, String>("종료일자");
		tcExitDate.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("exitDate"));
		tcExitDate.setPrefWidth(75);
		tableView.getColumns().add(tcExitDate);
		
		TableColumn<BoardVO, String> tcContents = new TableColumn<BoardVO, String>("내용");
		tcContents.setCellValueFactory(new PropertyValueFactory<BoardVO, String>("contents"));
		tcContents.setPrefWidth(200);
		tableView.getColumns().add(tcContents);
		
		tableView.setItems(list);
		
		tableView.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<BoardVO>() {

					@Override
					public void changed(ObservableValue<? extends BoardVO> arg0, BoardVO oldVal, BoardVO newVal) {
						if(newVal != null) {
							boardNo.setText(String.valueOf(newVal.getBoardNo()));
							title.setText(newVal.getTitle());
							publicity.setValue(newVal.getPublicity());
							contents.setText(newVal.getContents());
							exitDate.setValue(LocalDate.parse(newVal.getExitDate()));
						}
					}
				}
		);
	}// initialize method end
	
	public void prevBtnAction(ActionEvent e) {
		int index = tableView.getSelectionModel().getSelectedIndex() - 1;
		tableView.getSelectionModel().select(index);
		tableView.scrollTo(index);
	}
	
	public void nextBtnAction(ActionEvent e) {
		int index = tableView.getSelectionModel().getSelectedIndex() + 1;
		tableView.getSelectionModel().select(index);
		tableView.scrollTo(index);
	}

	public void updateBtnAction(ActionEvent e) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(boardNo.getText()));
		vo.setPublicity(publicity.getValue());
		vo.setExitDate(exitDate.getValue().toString());
		vo.setContents(contents.getText());
		InputDAO.updateBoard(vo);
		tableView.setItems(InputDAO.boardList());
	}
	
	public void deleteBtnAction(ActionEvent e) {
		InputDAO.deleteBoard(Integer.parseInt(boardNo.getText()));
		boardNo.setText("");
		title.setText("");
		publicity.setValue("");
		contents.setText("");
		exitDate.setValue(null);
		tableView.setItems(InputDAO.boardList());
	}
	
	public void exitBtnAction(ActionEvent e) {
		Platform.exit();
	}
	
	public void addBtnAction(ActionEvent e) {
		Stage stage = new Stage(StageStyle.DECORATED); 
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		
		try {
			AnchorPane ap = FXMLLoader.load(getClass().getResource("BoardAdd.fxml"));
			Scene scene = new Scene(ap);
			stage.setScene(scene);
			stage.show();
			
			Button btnReg = (Button) ap.lookup("#btnReg");
			btnReg.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					TextField txtTitle = (TextField) ap.lookup("#txtTitle");
					PasswordField txtPasswd = (PasswordField) ap.lookup("#txtPassword");
					ComboBox<String> comboPublic = (ComboBox<String>) ap.lookup("#comboPublic");
					DatePicker dateExit = (DatePicker) ap.lookup("#dateExit");
					TextArea txtContent = (TextArea) ap.lookup("#txtContent");
					
					
					InputBoardVO vo = new InputBoardVO();
					vo.setTitle(txtTitle.getText());
					vo.setPasswd(txtPasswd.getText());
					vo.setPublicity(comboPublic.getValue().toString());
					vo.setExitDate(dateExit.getValue().toString());
					vo.setContents(txtContent.getText());
					InputDAO.insertBoard(vo);
					tableView.setItems(InputDAO.boardList());
					stage.close();
				}
			});
			
			Button btnCancel = (Button) ap.lookup("#btnCancel");
			btnCancel.setOnAction(a -> stage.close());
			
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
