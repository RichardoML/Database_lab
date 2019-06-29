package application;

import javafx.beans.property.SimpleStringProperty;

public class pulishinfo 
{
	  private final SimpleStringProperty readerid; 
	  private final SimpleStringProperty readername; 
	  private final SimpleStringProperty bookid;
	  private final SimpleStringProperty bookname;
	  private final SimpleStringProperty overtime;
	  private final SimpleStringProperty overday;
	  private final SimpleStringProperty broken;
	  private final SimpleStringProperty fine;
	  
	  public pulishinfo(String rid,String rname,String bid,String bname,String otime,String oday,String brk,String fn)
	  {
		  readerid = new SimpleStringProperty(rid);
		  readername = new SimpleStringProperty(rname);
		  bookid = new SimpleStringProperty(bid);
		  bookname = new SimpleStringProperty(bname);
		  overtime = new SimpleStringProperty(otime);
		  overday =new SimpleStringProperty(oday);
		  broken = new SimpleStringProperty(brk);
		  fine = new SimpleStringProperty(fn);
	  }
	  
	  public pulishinfo(String bid,String bname,String otime,String oday,String brk,int fn)
	  {
		  readerid = new SimpleStringProperty("");
		  readername = new SimpleStringProperty("");
		  bookid = new SimpleStringProperty(bid);
		  bookname = new SimpleStringProperty(bname);
		  overtime = new SimpleStringProperty(otime);
		  overday =new SimpleStringProperty(oday);
		  broken = new SimpleStringProperty(brk);
		  fine = new SimpleStringProperty(fn+"");
	  }
	  
	  public void setReaderid(String rid) {readerid.set(rid);}
	  public String getReaderid() {return readerid.get();}
	  
	  public void setReadername(String rname) {readername.set(rname);}
	  public String getReadername() {return readername.get();}
	  
	  public void setBookid(String bid) {bookid.set(bid);}
	  public String getBookid() {return bookid.get();}
	  
	  public void setBookname(String bname) {bookname.set(bname);}
	  public String getBookname() {return bookname.get();}
	  
	  public void setOvertime(String otime) {overtime.set(otime);}
	  public String getOvertime() {return overtime.get();}
	  
	  public void setOverday(String oday) {overday.set(oday);}
	  public String getOverday() {return overday.get();}
	  
	  public void setBroken(String brk) {broken.set(brk);}
	  public String getBroken() {return broken.get();}
	  
	  public void setFine(String fn) {fine.set(fn);}
	  public String getFine() {return fine.get();}
	  
	  public String toString()
	  {
		  return readername.get()+bookname.get();
	  }

}
