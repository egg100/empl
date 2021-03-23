package common;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BoardVO {
	SimpleIntegerProperty boardNo;
	SimpleStringProperty title;
	SimpleStringProperty passwd;
	SimpleStringProperty publicity;
	SimpleStringProperty exitDate;
	SimpleStringProperty contents;
	
	public BoardVO() {
		boardNo = new SimpleIntegerProperty();
		title = new SimpleStringProperty();
		passwd = new SimpleStringProperty();
		publicity = new SimpleStringProperty();
		exitDate = new SimpleStringProperty();
		contents = new SimpleStringProperty();
	}
	
	// 1)
	public void setBoardNo(int boardNo) {
		this.boardNo.set(boardNo);
	}
	public int getBoardNo() {
		return this.boardNo.get();
	}
	public SimpleIntegerProperty boardNoProperty() {
		return this.boardNo;
	}
	
	// 2)
	public void setTitle(String title) {
		this.title.set(title);
	}
	public String getTitle() {
		return this.title.get();
	}
	public SimpleStringProperty titleProperty() {
		return this.title;
	}
	
	// 3)
	public void setPasswd(String passwd) {
		this.passwd.set(passwd);
	}
	public String getPasswd() {
		return this.passwd.get();
	}
	public SimpleStringProperty passwdProperty() {
		return this.passwd;
	}
	
	// 4)
	public void setPublicity(String publicity) {
		this.publicity.set(publicity);
	}
	public String getPublicity() {
		return this.publicity.get();
	}
	public SimpleStringProperty publicityProperty() {
		return this.publicity;
	}
	
	// 5)
	public void setExitDate(String exitDate) {
		this.exitDate.set(exitDate);
	}
	public String getExitDate() {
		return this.exitDate.get();
	}
	public SimpleStringProperty exitDateProperty() {
		return this.exitDate;
	}
	
	// 6)
	public void setContents(String contents) {
		this.contents.set(contents);
	}
	public String getContents() {
		return this.contents.get();
	}
	public SimpleStringProperty contentsProperty() {
		return this.contents;
	}
}
