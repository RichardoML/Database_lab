package application;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.regex.Pattern;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.sql.*;

public class readercontroller implements Initializable 
{
//	static final String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	static final String dburl = "jdbc:sqlserver://localhost:1433;DatabaseName=Library";
//	static final String username = "user1";
//	static final String userpwd = "user1";
//	private Connection con=null;
//	private Statement st=null;
//	
	static ResultSet rs = null;
	static String SQL=null;
	
	
	
	/*
	 * 书籍查询部分
	 * */
	@FXML
	private TextField bookquerybooknametextfield;
	@FXML
	private TextField bookqueryauthortextfield;
	@FXML
	private TextField bookqueryclasstextfield;
	@FXML
	private TextField bookquerypresstextfield;
	@FXML
	private RadioButton bookqueryfreeradiobutton;
	@FXML
	private Button bookqueryquerybutton;
	@FXML
	private TableView<bookinfo> bookquerytable;
	@FXML
	private TableColumn<?,?> bookquerycolumnbookid,bookquerycolumnbookname;
	@FXML
	private TableColumn<?,?> bookquerycolumnauthor,bookquerycolumnpress;
	@FXML
	private TableColumn<?,?> bookquerycolumnclass,bookquerycolumnlocation,bookquerycolumnfree;
	
	private ObservableList<bookinfo> bookquerydata = FXCollections.observableArrayList();
	//private ToggleGroup group;
	
	String author;
	String press;
	String bclass;
	String blocation;
	String bfree;
	String bname;
	String stime;
	String etime;
	String ireturn;
	String readerid;
	String readername;
	String sex;
	String pwd;
	String bid;
	String otime;
	String oday;
	String brk;
	String fn;
	/*
	 * 借书记录查询部分
	 * */
	@FXML
	private TableView<borrowinfo> borrowquerytable;
	@FXML
	private TableColumn<?,?> borrowquerycolumnbookname,borrowquerycolumnstarttime;
	@FXML
	private TableColumn<?,?> borrowquerycolumnendtime,borrowquerycolumnreturn;
	

	
	private ObservableList<borrowinfo> borrowinfotable = FXCollections.observableArrayList();
	/*
	 * 基本信息查询
	 * */
	@FXML
	private TextField infoqueryreaderidtextfield;
	@FXML
	private TextField infoqueryreadernametextfield;
	@FXML
	private TextField infoquerysextextfield;
	@FXML
	private TextField infoquerypasswordtextfield;
	

	/*
	 * 续借部分
	 * */
	@FXML
	private TableView<borrowinfo> renewtable;
	@FXML
	private TableColumn<?,?> renewcolumnbookid,renewcolumnbookname;
	@FXML
	private TableColumn<?,?> renewcolumnstarttime,renewcolumnendtime;
	@FXML
	private Button renewbutton;
	
	private ObservableList<borrowinfo> renewdata = FXCollections.observableArrayList();
	
	/*
	 * 罚单部分
	 * */
	@FXML
	private TableView<pulishinfo> pulishtableview;
	@FXML
	private TableColumn<?,?> pulishcolumnbookid,pulishcolumnbookname;
	@FXML
	private TableColumn<?,?> pulishcolumnovertime,pulishcolumnoverday;
	@FXML
	private TableColumn<?,?> pulishcolumnbroken,pulishcolumnfine;

	private ObservableList<pulishinfo> pulishinfotable = FXCollections.observableArrayList();
	
	@Override 
	public void initialize(URL location,ResourceBundle resources)
	{
		SQL = "SELECT * FROM READER WHERE RID = "+ Main.number +";";
		System.out.println(Main.number);
		System.out.println(SQL);
		try 
		{
			//实现基本信息查询
			rs = Main.st.executeQuery(SQL);
			rs.next();
			readerid = rs.getString(1);
			readername = rs.getString(2);
			sex = rs.getString(4);
			pwd = rs.getString(3);
			System.out.println(readerid);
			System.out.println(readername);
			System.out.println(sex);
			System.out.println(pwd);
			infoqueryreaderidtextfield.setText(readerid);
			infoqueryreadernametextfield.setText(readername);
			infoquerysextextfield.setText(sex);
			infoquerypasswordtextfield.setText(pwd);
		
			//实现借书记录的查询
			SQL = "SELECT BNAME,STARTTIME,ENDTIME,[RETURN]\r\n "
					+ "FROM BOOK,BORROW\r\n"
					+ "WHERE BOOK.BID = BORROW.BOOKID AND BORROW.RID = "+Main.number +";";
//			SQL = "SELECT BNAME,STARTTIME ,ENDTIME,[RETURN]\r\n" + 
//					"FROM BOOK ,BORROW\r\n" + 
//					"WHERE BOOK.BID = BORROW.BOOKID AND BORROW.RID = "+Main.number+";";
			//这里的换行符\r\n很重要，要是没有的话会报错:SQL 有语法错误。
			rs = Main.st.executeQuery(SQL);
			while(rs.next())
			{
				bname = rs.getString("BNAME");
				stime= rs.getString("STARTTIME");
				etime = rs.getString("ENDTIME");
				ireturn = rs.getString("RETURN");
				//System.out.println(bname + stime +etime+ireturn);
				
				borrowinfo slot = new borrowinfo(bname,stime,etime,ireturn);
				System.out.println(slot);
				borrowinfotable.add(slot);
			}
			
			borrowquerycolumnbookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
			borrowquerycolumnstarttime.setCellValueFactory(new PropertyValueFactory<>("starttime"));
			borrowquerycolumnendtime.setCellValueFactory(new PropertyValueFactory<>("endtime"));
			borrowquerycolumnreturn.setCellValueFactory(new PropertyValueFactory<>("breturn"));
			borrowquerytable.setItems(borrowinfotable);
			//罚单信息处理
			SQL = "SELECT BOOK.BID,BOOK.BNAME,OVERTIME,[DAY],BROKEN,BOOK.BPRICE\r\n" + 
					"FROM BOOK,BORROW,PULISH\r\n" + 
					"WHERE BORROW.BID = PULISH.BID AND \r\n" + 
					"BORROW.BOOKID = BOOK.BID AND \r\n" + 
					"  PAID  = '否' AND BORROW.RID = "+Main.number+";";
			rs = Main.st.executeQuery(SQL);
			while(rs.next())
			{
				bid = rs.getString(1);
				bname = rs.getString(2);
				otime = rs.getString(3);
				oday = rs.getString(4);
				brk = rs.getString(5);
				int price = Integer.parseInt(rs.getString(6));
				if(brk.equals("否"))
				{
					price = 0;
				}
				if(otime.equals("是"))
				{
					price = price + Integer.parseInt(oday);
				}
				pulishinfo slot = new pulishinfo(bid,bname,otime,oday,brk,price);
				pulishinfotable.add(slot);
			}
			pulishcolumnbookid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
			pulishcolumnbookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
			pulishcolumnovertime.setCellValueFactory(new PropertyValueFactory<>("overtime"));
			pulishcolumnoverday.setCellValueFactory(new PropertyValueFactory<>("overday"));
			pulishcolumnbroken.setCellValueFactory(new PropertyValueFactory<>("broken"));
			pulishcolumnfine.setCellValueFactory(new PropertyValueFactory<>("fine"));
			pulishtableview.setItems(pulishinfotable);
		///书籍查询的初始化
			SQL = "SELECT * FROM BOOK\r\n" + 
					"WHERE BUNSHELVE = '否'";
			rs = Main.st.executeQuery(SQL);
			while(rs.next())
			{
				bid = rs.getString(1);
				bname = rs.getString(2);
				author = rs.getString(3);
				press = rs.getString(4);
				bclass = rs.getString(5);
				blocation = rs.getString(7);
				bfree = rs.getString(8);
				bookinfo newbook = new bookinfo(bid,bname,author,press,bclass,blocation,bfree);
				bookquerydata.add(newbook);
			}
			bookquerycolumnbookid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
			bookquerycolumnbookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
			bookquerycolumnauthor.setCellValueFactory(new PropertyValueFactory<>("author"));
			bookquerycolumnpress.setCellValueFactory(new PropertyValueFactory<>("press"));
			bookquerycolumnclass.setCellValueFactory(new PropertyValueFactory<>("bookclass"));
			bookquerycolumnlocation.setCellValueFactory(new PropertyValueFactory<>("location"));
			bookquerycolumnfree.setCellValueFactory(new PropertyValueFactory<>("free"));
			bookquerytable.setItems(bookquerydata);
			//续借部分的初始化
			SQL = "SELECT BOOKID, BNAME,STARTTIME,ENDTIME\r\n" + 
					"FROM BOOK,BORROW\r\n" + 
					"WHERE BOOK.BID = BORROW.BOOKID AND \r\n" + 
					"BORROW.RID = "+Main.number+"AND \r\n"+
					"[RETURN] = '否';";
			rs = Main.st.executeQuery(SQL);
			while(rs.next())
			{
				bid = rs.getString(1);
				bname = rs.getString(2);
				stime = rs.getString(3);
				etime = rs.getString(4);
				borrowinfo newinborrow = new borrowinfo(bid,bname,stime,etime,"");
				renewdata.add(newinborrow);
			}
			renewcolumnbookid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
			renewcolumnbookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
			renewcolumnstarttime.setCellValueFactory(new PropertyValueFactory<>("starttime"));
			renewcolumnendtime.setCellValueFactory(new PropertyValueFactory<>("endtime"));
			renewtable.setItems(renewdata);
			renewtable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			//书籍查询动态处理
//			bookquerybooknametextfield.textProperty().addListener(new bookquerylistener<String>());
//			bookqueryauthortextfield.textProperty().addListener(new bookquerylistener<String>());
//			bookqueryclasstextfield.textProperty().addListener(new bookquerylistener<String>());
//			bookquerypresstextfield.textProperty().addListener(new bookquerylistener<String>());
//			//bookqueryfreeradiobutton.textProperty().addListener(new bookquerylistener<String>());不能实现radiobutton变化就调用函数
			//bookqueryfreeradiobutton.selectedProperty().addListener(new ChangeListener<Toggle>();
//			group = new ToggleGroup();
//			bookqueryfreeradiobutton.setToggleGroup(group);
//			group.selectedToggleProperty().addListener(new  bookquerylistener());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
}
	
	
	/*
	 * 点击借书按钮时调用
	 * */
	public void renewbook()
	{
		ObservableList<borrowinfo> selectedslots = FXCollections.observableArrayList();
		selectedslots = renewtable.getSelectionModel().getSelectedItems();
		borrowinfo slot;
		int i = 0;
		String starttime;
		String endtime;
		String bkname;
		int bkid;
		SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
		Date enddate;
		Date startdate;
		Calendar cld = Calendar.getInstance();
		for(i=0;i<selectedslots.size();i++)
		{
			slot = selectedslots.get(i);
			starttime = slot.getStarttime();
			endtime = slot.getEndtime();
			bkname = slot.getBookname();
			bkid = Integer.parseInt(slot.getBookid());
			try
			{
				startdate = dateformatter.parse(starttime);
				enddate = dateformatter.parse(endtime);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}
			cld.setTime(startdate);
			cld.add(Calendar.DATE,14);
			startdate = cld.getTime();
			if(!enddate.before(startdate))//借书时长14天，说明已经续借过了，不能再次续借
			{
				JOptionPane.showMessageDialog(null,bkname+" 已经续借过，不能再次续借","tip：",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				SQL = "UPDATE BORROW \r\n" + 
						"SET ENDTIME =  DATEADD(day,7,ENDTIME)\r\n" + 
						"WHERE  RID = "+ Main.number +"AND \r\n"+
						"BOOKID = "+bkid+"AND \r\n"
						+"[RETURN] = '否'";
				try
				{
					Main.st.executeUpdate(SQL);//更新数据库
					/*
					 * 需要更新表格中对应的数据
					 * 
					 * 并且弹出一个对话框提示续借成功
					 * */
					int index = renewdata.indexOf(slot);
					String updatedate=null;
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
					try 
					{
						updatedate = fm.format(startdate);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					borrowinfo update = new borrowinfo(bkid+"",bkname,starttime,updatedate,"");
					renewdata.set(index,update);
					JOptionPane.showMessageDialog(null,bkname+"续借成功","tip：",JOptionPane.PLAIN_MESSAGE);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/*借书的输入变化时调用
	 * */
	public  void querybook()
	{
		bname = bookquerybooknametextfield.getText();
		author = bookqueryauthortextfield.getText();
		bclass = bookqueryclasstextfield.getText();
		press = bookquerypresstextfield.getText();
		
		if(bookqueryfreeradiobutton.isSelected()){bfree = "AND BFREE = '是'";}
		else{bfree = "";}
		
		if(bname!=null && !bname.isEmpty()){bname = "AND BNAME LIKE '%"+bname+"%' ESCAPE '\\'";}//注意要是\\ 转义的问题
		else{bname = "";}
		
		if(author!=null && !author.isEmpty()) {author = "AND BAUTHOR LIKE '%"+author+"%' ESCAPE '\\'";}
		else {author = "";}
		
		if(bclass!= null && !bclass.isEmpty()) {bclass = "AND BTYPE LIKE '%"+bclass+"%' ESCAPE '\\'";}
		else {bclass = "";}
		
		if(press!=null && !press.isEmpty()) {press = "AND BPRESS LIKE '%"+press+"%' ESCAPE '\\'";}
		else {press = "";}
		
		SQL = "SELECT BID,BNAME,BAUTHOR,BPRESS,BTYPE,BLOCATION,BFREE\r\n" + 
				"FROM BOOK\r\n" + 
				"WHERE BUNSHELVE = '否'\r\n"+bname+"\r\n"+author+"\r\n"+bclass+"\r\n"+press+"\r\n"+bfree+";";
		try 
		{
			rs = Main.st.executeQuery(SQL);
			System.out.print(SQL);
			bookquerydata.clear();
			while(rs.next())
			{
				bid = rs.getString(1);
				bname = rs.getString(2);
				author = rs.getString(3);
				press = rs.getString(4);
				bclass = rs.getString(5);
				blocation = rs.getString(6);
				bfree = rs.getString(7);
				bookinfo updateone = new bookinfo(bid,bname,author,press,bclass,blocation,bfree);
				bookquerydata.add(updateone);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
//	class bookquerylistener<T> implements ChangeListener<T>
//	{
//		 @Override
//		  public void changed(ObservableValue <? extends T> a, T old, T n) 
//		 {
//			 querybook();
//		 }
//	}
}
