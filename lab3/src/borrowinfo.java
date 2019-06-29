package application;

import javafx.beans.property.SimpleStringProperty;

public class borrowinfo 
{
	private final SimpleStringProperty readerid; 
    private final SimpleStringProperty readername; 
    private final SimpleStringProperty bookid;
    private final SimpleStringProperty bookname;
    private final SimpleStringProperty starttime;
    private final SimpleStringProperty endtime;
    private final SimpleStringProperty breturn; 

    public borrowinfo(String rid,String rname,String bid,String bname,String starttime,String endtime,String breturn)
    {
    	readerid = new SimpleStringProperty(rid);
    	readername = new SimpleStringProperty(rname);
    	bookid = new SimpleStringProperty(bid);
    	bookname = new SimpleStringProperty(bname);
    	this.starttime = new SimpleStringProperty(starttime);
    	this.endtime = new SimpleStringProperty(endtime);
    	this.breturn =new SimpleStringProperty(breturn);
    }
    public borrowinfo(String bname,String starttime,String endtime,String breturn)
    {
    	readerid = new SimpleStringProperty("");
    	readername = new SimpleStringProperty("");
    	bookid = new SimpleStringProperty("");
    	bookname = new SimpleStringProperty(bname);
    	this.starttime = new SimpleStringProperty(starttime);
    	this.endtime = new SimpleStringProperty(endtime);
    	this.breturn =new SimpleStringProperty(breturn);
    }
    public borrowinfo(String bid,String bname,String starttime,String endtime,String breturn)
    {
    	readerid = new SimpleStringProperty("");
    	readername = new SimpleStringProperty("");
    	bookid = new SimpleStringProperty(bid);
    	bookname = new SimpleStringProperty(bname);
    	this.starttime = new SimpleStringProperty(starttime);
    	this.endtime = new SimpleStringProperty(endtime);
    	this.breturn =new SimpleStringProperty(breturn);
    }
    public borrowinfo(String rid,String bid,String bname,String endtime,String starttime,String breturn)
    {
    	readerid = new SimpleStringProperty(rid);
    	readername = new SimpleStringProperty("");
    	bookid = new SimpleStringProperty(bid);
    	bookname = new SimpleStringProperty(bname);
    	this.starttime = new SimpleStringProperty(starttime);
    	this.endtime = new SimpleStringProperty(endtime);
    	this.breturn =new SimpleStringProperty(breturn);
    }
    public String getReaderid() {return readerid.get();}
    public void setReaderid(String rid) {readerid.set(rid);}
    
    public String getReadername() {return readername.get();}
    public void setReadername(String rname) {readername.set(rname);}
    
    public String getBookid() {return bookid.get();}
    public void setBookid(String bid) {bookid.set(bid);}
    
    public String getBookname() {return bookname.get();}
    public void setBookname(String bname) {bookname.set(bname);}
    
    public String getStarttime() {return starttime.get();}
    public void setStartime(String stime) {starttime.set(stime);}
    
    public String getEndtime() {return endtime.get();}
    public void setEndtime(String etime) {endtime.set(etime);}
    
    public String getBreturn() {return breturn.get();}
    public void setBreturn(String brt) {breturn.set(brt);} 
    
    public String toString()
    {
    	return readerid.get()+readername.get()+bookid.get()+bookname.get()+starttime.get()+endtime.get()+breturn.get();
    }
}
