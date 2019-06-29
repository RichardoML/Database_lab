package application;

import javafx.beans.property.SimpleStringProperty;

public class bookinfo 
{
	private final SimpleStringProperty bookid;
	private final SimpleStringProperty bookname;
	private final SimpleStringProperty author;
	private final SimpleStringProperty press;
	private final SimpleStringProperty bookclass;
	private final SimpleStringProperty location;
	private final SimpleStringProperty free;
	
	public bookinfo(String bid,String bname,String bauthor,String bpress,String bclass,String blocation,String bfree)
	{
		bookid = new SimpleStringProperty(bid);
		bookname = new SimpleStringProperty(bname);
		author = new SimpleStringProperty(bauthor);
		press = new SimpleStringProperty(bpress);
		bookclass = new SimpleStringProperty(bclass);
		location = new SimpleStringProperty(blocation);
		free = new SimpleStringProperty(bfree);
	}
	public bookinfo(String bid,String bname,String bauthor,String bpress,String bclass,String blocation)
	{
		bookid = new SimpleStringProperty(bid);
		bookname = new SimpleStringProperty(bname);
		author = new SimpleStringProperty(bauthor);
		press = new SimpleStringProperty(bpress);
		bookclass = new SimpleStringProperty(bclass);
		location = new SimpleStringProperty(blocation);
		free = new SimpleStringProperty("");
	}
	//注意这里一定要 setBookid这样子写，，不能都写成小写，，不然会出现，表格看不到文字的情况
	public void setBoookid(String bname)
	{
		bookid.set(bname);
	}
	public String getBookid()
	{
		return bookid.get();
	}
	
	public void setBoookname(String bname)
	{
		bookname.set(bname);
	}
	public String getBookname()
	{
		return bookname.get();
	}
	
	public void setAuthor(String bauthor)
	{
		author.set(bauthor);
	}
	public String getAuthor()
	{
		return author.get();
	}
	
	public void setPress(String bpress)
	{
		press.set(bpress);
	}
	public String getPress()
	{
		return press.get();
	}
	
	public void setBookclass(String bclass)
	{
		bookclass.set(bclass);
	}
	public String getBookclass()
	{
		return bookclass.get();
	}
	
	public void setLocation(String blocation)
	{
		location.set(blocation);
	}
	public String getLocation()
	{
		return location.get();
	}
	
	public void setFree(String bfree)
	{
		free.set(bfree);
	}
	public String getFree()
	{
		return free.get();
	}
	
	  public String toString()
	  {
		  return bookid.get()+bookname.get();
	  }
}
