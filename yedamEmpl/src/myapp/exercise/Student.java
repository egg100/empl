package myapp.exercise;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
	SimpleStringProperty name;
	SimpleIntegerProperty lang;
	SimpleIntegerProperty math;
	SimpleIntegerProperty eng;
	
	public Student() {
		name = new SimpleStringProperty();
		lang = new SimpleIntegerProperty();
		math = new SimpleIntegerProperty();
		eng = new SimpleIntegerProperty();
	}
	
	public String getName() {
		return this.name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public SimpleStringProperty nameProgerty() {
		return name;
	}
	public int getLang() {
		return this.lang.get();
	}
	public void setLang(int lang) {
		this.lang.set(lang);
	}
	public SimpleIntegerProperty langProgerty() {
		return lang;
	}
	public int getMath() {
		return this.math.get();
	}
	public void setMath(int math) {
		this.math.set(math);
	}
	public SimpleIntegerProperty mathProgerty() {
		return math;
	}
	public int getEng() {
		return this.eng.get();
	}
	public void setEng(int eng) {
		this.eng.set(eng);
	}
	public SimpleIntegerProperty engProgerty() {
		return eng;
	}
}
