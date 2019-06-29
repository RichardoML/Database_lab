package application;

import javafx.beans.property.SimpleStringProperty;

public class readerinfo {
	 private final SimpleStringProperty readerid; 
	 private final SimpleStringProperty readername; 
	 private final SimpleStringProperty sex;
	 private final SimpleStringProperty pwd;

	 public readerinfo(String rid,String rname,String s,String pswd)
	 {
		 readerid = new SimpleStringProperty(rid);
		 readername = new SimpleStringProperty(rname);
		 sex = new SimpleStringProperty(s);
		 pwd = new SimpleStringProperty(pswd);
	 }
	 public readerinfo(String rid,String rname,String s)
	 {
		 readerid = new SimpleStringProperty(rid);
		 readername = new SimpleStringProperty(rname);
		 sex = new SimpleStringProperty(s);
		 pwd = new SimpleStringProperty("");
	 }
	 
	 public void setReaderid(String rid) {readerid.set(rid);}
	 public void setReadername(String rname) {readername.set(rname);}
	 public void setSex(String s) {sex.set(s);}
	 public void setPwd(String pswd) {pwd.set(pswd);}
	 
	 public String getReaderid() {return readerid.get();}
	 public String getReadername() {return readername.get();}
	 public String getSex() {return sex.get();}
	 public String getPwd() {return pwd.get();}
}
