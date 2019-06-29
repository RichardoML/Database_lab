package application;

import java.util.regex.Pattern;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//import application.readercontroller.bookquerylistener;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.sql.*;
import java.util.regex.Matcher
;public class admincontroller implements Initializable{

	//新书录入部分
	@FXML 
	private TextField bookinnumtextfield;
	@FXML
	private TextField bookinbooknametextfield;
	@FXML
	private TextField bookinauthortextfield;
	@FXML
	private TextField bookinpresstextfield;
	@FXML
	private TextField bookinclasstextfield;
	@FXML
	private TextField bookinpricetextfield;
	@FXML
	private TextField bookinlocationtextfield;
	@FXML
	private Button  bookininbutton;
	
	//旧书下架
	@FXML
	private TextField bookdownbookidtextfield;
	@FXML
	private TextField bookdownbooknametextfield;
	@FXML
	private TextField bookdownauthortextfield;
	@FXML
	private TextField bookdownclasstextfield;
	@FXML
	private TextField bookdownpresstextfield;
	
	@FXML
	private TableView<bookinfo> bookdowntableview;
	
	@FXML
	private TableColumn<?,?> bookdowncolumnbookid,bookdowncolumnbookname;
	@FXML
	private TableColumn<?,?> bookdowncolumnauthor,bookdowncolumnpress;
	@FXML
	private TableColumn<?,?>  bookdowncolumnclass,bookdowncolumnlocation;
	
	@FXML
	private Button bookdowndownbutton;
	@FXML
	private Button bookdownquerybutton;
	
	private ObservableList<bookinfo> bookdowndata = FXCollections.observableArrayList();
	//书籍查询
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
	private TableView<bookinfo> bookquerytableview;
	
	@FXML
	private TableColumn<?,?> bookquerycolumnbookid,bookquerycolumnbookname;
	@FXML
	private TableColumn<?,?> bookquerycolumnauthor,bookquerycolumnpress;
	@FXML
	private TableColumn<?,?> bookquerycolumnclass,bookquerycolumnlocation,bookquerycolumnfree;
	
	private ObservableList<bookinfo> bookquerydata = FXCollections.observableArrayList();
	//录入读者
	@FXML
	private TextField readerinreaderidtextfield;
	@FXML
	private TextField readerinreadernametextfield;
	@FXML
	private ComboBox<String> readerinsexcombobox;
	@FXML
	private TextField readerinpasswordtextfield;
	@FXML
	private Button  readerininbutton;
	
	//注销读者
	@FXML
	private TextField readeroutreaderidtextfield;
	@FXML
	private TextField readeroutreadernametextfield;
	@FXML
	private TableView<readerinfo> readerouttableview;
	@FXML
	private TableColumn<?,?> readeroutcolumnreaderid,readeroutcolumnreadername,readeroutcolumnsex;
	@FXML
	private Button readeroutoutbutton;
	@FXML
	private Button readeroutquerybutton;
	
	private ObservableList<readerinfo> readerdowndata = FXCollections.observableArrayList();
	//修改读者信息
	@FXML
	private TextField readermodifyreaderidtextfield;
	@FXML
	private TextField readermodifyreadernametextfield;
	@FXML
	private ComboBox<String> readermodifysexcombobox;
	@FXML
	private TextField readermodifypasswordtextfield;
	@FXML
	private Button readermodifymodifybutton;
	@FXML
	private Button readermodifyquerybutton;
	//查询读者信息
	@FXML
	private TextField readerqueryreaderidtextfield;
	@FXML
	private TextField readerqueryreadernametextfield;
	@FXML
	private TextField readerquerysextextfield;
	@FXML
	private TextField readerquerypasswordtextfield;
	@FXML
	private Button readerqueryquerybutton;
	@FXML
	private Button readerqueryclearbutton;
	
	//借还书管理
	@FXML
	private TextField borrowreturnreaderidtextfield;
	@FXML
	private TextField borrowreturnbookidtextfield;
	@FXML
	private TextField borrowreturnoverdaytextfield;
	@FXML
	private TextField borrowreturnovertimetextfield;
	
	@FXML
	private ComboBox<String> borrowreturnbrokencombobox;
	
	@FXML
	private Button borrowreturnborrowbutton;
	@FXML
	private Button borrowreturnreturnbutton;
	
	@FXML
	private TableView<borrowinfo>  borrowreturntableview;
	
	@FXML
	private TableColumn<?,?> borrowreturncolumnreaderid,borrowreturncolumnbookid;
	@FXML
	private TableColumn<?,?> borrowreturncolumnbookname,borrowreturncolumnendtime;
	
	private ObservableList<borrowinfo> borrowreturndata = FXCollections.observableArrayList();
	
	//罚单信息管理
	@FXML
	private TextField pulishreaderidtextfield;
	@FXML
	private TextField pulishfinetextfield;
	
	@FXML
	private TableView<pulishinfo> pulishtableview;
	
	@FXML
	private TableColumn<?,?> pulishcolumnreaderid,pulishcolumnreadername;
	@FXML
	private TableColumn<?,?> pulishcolumnbookid,pulishcolumnbookname;
	@FXML
	private TableColumn<?,?> pulishcolumnovertime,pulishcolumnoverday;
	@FXML
	private TableColumn<?,?> pulishcolumnbroken,pulishcolumnfine;
	
	@FXML
	private Button pulishfinebutton;
	
	private ObservableList<pulishinfo> pulishdata = FXCollections.observableArrayList();
	
	private ResultSet rs = null;
	private String SQL=null;
	
	String rdid;
	String rdname;
	String rdsex;
	String rdpwd;
	String bkid;
	String bkname;
	String bkauthor;
	String bkclass;
	String bkpress;
	String bklocation;
	String bknum;
	String bkprice;
	String bkfree;
	String etime;
	String otime;
	String oday;
	String brk;
	String fn;
	int allfine = 0;
	
	@Override 
	public void initialize(URL location,ResourceBundle resources)
	{
		try{	//书籍下架的初始化
				SQL = "SELECT * \r\n" + 
						"FROM BOOK\r\n" + 
						"WHERE BFREE = '是' AND \r\n" + 
						"BUNSHELVE = '否'";
				rs = Main.st.executeQuery(SQL);
				while(rs.next())
				{
					bkid = rs.getString(1);
					bkname = rs.getString(2);
					bkauthor = rs.getString(3);
					bkpress = rs.getString(4);
					bkclass = rs.getString(5);
					bklocation = rs.getString(7);
					bookinfo newbook = new bookinfo(bkid,bkname,bkauthor,bkpress,bkclass,bklocation);
					bookdowndata.add(newbook);
				}
				bookdowncolumnbookid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
				bookdowncolumnbookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
				bookdowncolumnauthor.setCellValueFactory(new PropertyValueFactory<>("author"));
				bookdowncolumnpress.setCellValueFactory(new PropertyValueFactory<>("press"));
				bookdowncolumnclass.setCellValueFactory(new PropertyValueFactory<>("bookclass"));
				bookdowncolumnlocation.setCellValueFactory(new PropertyValueFactory<>("location"));
				bookdowntableview.setItems(bookdowndata);
				bookdowntableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				//书籍查询的初始化
				SQL = "SELECT * FROM BOOK\r\n" + 
						"WHERE BUNSHELVE = '否'";
				rs = Main.st.executeQuery(SQL);
				while(rs.next())
				{
					bkid = rs.getString(1);
					bkname = rs.getString(2);
					bkauthor = rs.getString(3);
					bkpress = rs.getString(4);
					bkclass = rs.getString(5);
					bklocation = rs.getString(7);
					bkfree = rs.getString(8);
					bookinfo newbook = new bookinfo(bkid,bkname,bkauthor,bkpress,bkclass,bklocation,bkfree);
					bookquerydata.add(newbook);
				}
				bookquerycolumnbookid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
				bookquerycolumnbookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
				bookquerycolumnauthor.setCellValueFactory(new PropertyValueFactory<>("author"));
				bookquerycolumnpress.setCellValueFactory(new PropertyValueFactory<>("press"));
				bookquerycolumnclass.setCellValueFactory(new PropertyValueFactory<>("bookclass"));
				bookquerycolumnlocation.setCellValueFactory(new PropertyValueFactory<>("location"));
				bookquerycolumnfree.setCellValueFactory(new PropertyValueFactory<>("free"));
				bookquerytableview.setItems(bookquerydata);
				//罚单信息的初始化
				SQL = "SELECT READER.RID, READER.RNAME,BOOK.BID,\r\n" + 
						"BOOK.BNAME,PULISH.OVERTIME,PULISH.[DAY],\r\n" + 
						"PULISH.BROKEN,BOOK.BPRICE\r\n" + 
						"FROM BOOK,BORROW,READER,PULISH \r\n" + 
						"WHERE PULISH.BID = BORROW.BID AND\r\n" + 
						"BORROW.RID = READER.RID AND \r\n" + 
						"BORROW.BOOKID = BOOK.BID AND \r\n"+
						"PULISH.PAID = '否'";
				rs = Main.st.executeQuery(SQL);
				allfine = 0;
				while(rs.next())
				{
					rdid = rs.getString(1);
					rdname = rs.getString(2);
					bkid = rs.getString(3);
					bkname = rs.getString(4);
					otime = rs.getString(5);
					oday = rs.getString(6);
					brk = rs.getString(7);
					fn = rs.getString(8);
					int bkprice = Integer.parseInt(fn);
					if(brk.equals("否"))
					{
						bkprice = 0;
					}
					if(otime.equals("是"))
					{
						bkprice = bkprice + Integer.parseInt(oday);
					}
					allfine = allfine + bkprice;
					fn = bkprice+"";
					pulishinfo newpulish = new pulishinfo(rdid,rdname,bkid,bkname,otime,oday,brk,fn);
					pulishdata.add(newpulish);
				}
				pulishfinetextfield.setText(allfine+"元");
				pulishcolumnreaderid.setCellValueFactory(new PropertyValueFactory<>("readerid"));
				pulishcolumnreadername.setCellValueFactory(new PropertyValueFactory<>("readername"));
				pulishcolumnbookid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
				pulishcolumnbookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
				pulishcolumnovertime.setCellValueFactory(new PropertyValueFactory<>("overtime"));
				pulishcolumnoverday.setCellValueFactory(new PropertyValueFactory<>("overday"));
				pulishcolumnbroken.setCellValueFactory(new PropertyValueFactory<>("broken"));
				pulishcolumnfine.setCellValueFactory(new PropertyValueFactory<>("fine"));
				pulishtableview.setItems(pulishdata);
				pulishtableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				//借还书的初始化:表格中不需要任何提前的信息
				borrowreturnbrokencombobox.getItems().addAll("是","否");
				borrowreturntableview.setItems(borrowreturndata);
				borrowreturncolumnreaderid.setCellValueFactory(new PropertyValueFactory<>("readerid"));
				borrowreturncolumnbookid.setCellValueFactory(new PropertyValueFactory<>("bookid"));
				borrowreturncolumnbookname.setCellValueFactory(new PropertyValueFactory<>("bookname"));
				borrowreturncolumnendtime.setCellValueFactory(new PropertyValueFactory<>("endtime"));
				//录入读者的初始化
				readerinsexcombobox.getItems().addAll("男","女");
				readerinsexcombobox.getSelectionModel().select(0);
				//修改读者的初始化
				readermodifysexcombobox.getItems().addAll("男","女");
				//注销读者的初始化
				SQL = "SELECT *\r\n" + 
						"FROM READER \r\n";
				rs = Main.st.executeQuery(SQL);
				while(rs.next())
				{
					rdid = rs.getString(1);
					rdname = rs.getString(2);
					rdsex = rs.getString(4);
					readerinfo areader = new readerinfo(rdid,rdname,rdsex);
					readerdowndata.add(areader);
				}
				readerouttableview.setItems(readerdowndata);
				readeroutcolumnreaderid.setCellValueFactory(new PropertyValueFactory<>("readerid"));
				readeroutcolumnreadername.setCellValueFactory(new PropertyValueFactory<>("readername")); 
				readeroutcolumnsex.setCellValueFactory(new PropertyValueFactory<>("sex"));
				readerouttableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				//旧书下架的动态处理，动态改变表格中的显示
//				bookdownbookidtextfield.textProperty().addListener(new bookdownlistener<String>());
//				bookdownbooknametextfield.textProperty().addListener(new bookdownlistener<String>());
//				bookdownauthortextfield.textProperty().addListener(new bookdownlistener<String>());
//				bookdownclasstextfield.textProperty().addListener(new bookdownlistener<String>());
//				bookdownpresstextfield.textProperty().addListener(new bookdownlistener<String>());
//				//注销读者的动态处理，输入变化，表格也变化
				readeroutreaderidtextfield.textProperty().addListener(new readeroutlistener<String>());
				//readeroutreadernametextfield.textProperty().addListener(new readeroutlistener<String>());
				//书籍查询的动态处理，根据文本框中的输入改变来自动改变表格中的内容
//				 bookquerybooknametextfield.textProperty().addListener(new bookquerylistener<String>());
//				 bookqueryclasstextfield.textProperty().addListener(new bookquerylistener<String>());
//				 bookquerypresstextfield.textProperty().addListener(new bookquerylistener<String>());
//				 bookqueryauthortextfield.textProperty().addListener(new bookquerylistener<String>());
//			
				 //罚单的动态处理，读者ID变化，动态改变表格内容
				 pulishreaderidtextfield.textProperty().addListener(new pulishlistener<String>());
				 //借还书的动态处理，根据输入的reader id来改变表格中的内容
				 borrowreturnreaderidtextfield.textProperty().addListener(new borrowreturnlistener<String>());
				 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void bookin()
	{
		bknum = bookinnumtextfield.getText().trim();	
		if(bknum==null || bknum.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"输入书籍数量","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		int bkn = 0;
		try
		{
			bkn = Integer.parseInt(bknum); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"书籍数量请输入数字","tip",JOptionPane.ERROR_MESSAGE);
			return;
		}
		bkname = bookinbooknametextfield.getText().trim();
		if(bkname==null || bkname.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"输入书籍名称","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		bkauthor = bookinauthortextfield.getText().trim();
		if(bkauthor==null || bkauthor.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"输入书籍作者","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		bkpress = bookinpresstextfield.getText().trim();
		if(bkpress==null || bkpress.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"输入出版社","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		bkclass = bookinclasstextfield.getText().trim();
		bkprice = bookinpricetextfield.getText().trim();
		if(bkprice==null || bkprice.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"输入价格","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		bklocation = bookinlocationtextfield.getText().trim();
		if(bklocation==null || bklocation.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"输入馆藏地址","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		int i=0;
		int insertednum = 0;
		for(i=0;i<bkn;i++)
		{
			SQL = "BEGIN TRANSACTION \r\n" + 
					"DECLARE @PK INT;\r\n" + 
					"SET @PK = (SELECT MAX(BID)+1 FROM BOOK);\r\n" + 
					"INSERT INTO BOOK \r\n" + 
					"VALUES (@PK,'"+bkname+"','"+bkauthor+"','"+bkpress+"','"+bkclass+"',"+bkprice+",'"+bklocation+"','是','否')\r\n"+
					"COMMIT";
			try
			{
				 insertednum = insertednum + Main.st.executeUpdate(SQL);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"插入未全部成功，插入书籍数量："+insertednum,"tip",JOptionPane.PLAIN_MESSAGE);
			}
			
		}
		JOptionPane.showMessageDialog(null,"插入成功，插入书籍数量："+insertednum+bkname,"tip",JOptionPane.PLAIN_MESSAGE);
		bookinnumtextfield.setText("");
		bookinbooknametextfield.setText("");
		bookinauthortextfield.setText("");
		bookinpresstextfield.setText("");
		bookinclasstextfield.setText("");
		bookinpricetextfield.setText("");
		bookinlocationtextfield.setText("");
	}
	
	
	public void bookdownquery()
	{
		bkid = bookdownbookidtextfield.getText().trim();
		bkname = bookdownbooknametextfield.getText().trim();
		bkauthor = bookdownauthortextfield.getText().trim();
		bkclass = bookdownclasstextfield.getText().trim();
		bkpress = bookdownpresstextfield.getText().trim();
		
		if(bkid!=null && !bkid.isEmpty()) {bkid = "AND BID LIKE '%"+bkid+"%' ESCAPE '\\'\r\n";}
		else {bkid="";}
		if(bkname!=null && !bkname.isEmpty()) {bkname = "AND BNAME LIKE '%"+bkname+"%' ESCAPE '\\'\r\n";}
		else {bkname="";}
		if(bkauthor!=null && !bkauthor.isEmpty()) {bkauthor = "AND BAUTHOR LIKE '%"+bkauthor+"%' ESCAPE '\\'\r\n";}
		else {bkauthor="";}
		if(bkclass!=null && !bkclass.isEmpty()) {bkclass = "AND BTYPE LIKE '%"+bkclass+"%' ESCAPE '\\'\r\n";}
		else {bkclass="";}
		if(bkpress!=null && !bkpress.isEmpty()) {bkpress = "AND BPRESS LIKE '%"+bkpress+"%' ESCAPE '\\'\r\n";}
		else {bkpress="";}
		
		SQL = "SELECT * \r\n" + 
				"FROM BOOK\r\n" + 
				"WHERE BFREE = '是' AND \r\n" + 
				"BUNSHELVE = '否'\r\n"+bkid+bkname+bkauthor+bkclass+bkpress;
		try
		{
			System.out.print(SQL);
			rs = Main.st.executeQuery(SQL);
			bookdowndata.clear();
			while(rs.next())
			{
				bkid = rs.getString(1);
				bkname = rs.getString(2);
				bkauthor = rs.getString(3);
				bkpress = rs.getString(4);
				bkclass = rs.getString(5);
				bklocation = rs.getString(7);
				bookinfo newbook = new bookinfo(bkid,bkname,bkauthor,bkpress,bkclass,bklocation);
				bookdowndata.add(newbook);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
//	class bookdownlistener<T> implements ChangeListener<T>
//	{
//		@Override
//		public void changed(ObservableValue <? extends T> a, T old, T n)
//		{
//			bookdownquery();
//		}
//	}
//	

	public void bookdown()
	{
		ObservableList<bookinfo> selectedslots = FXCollections.observableArrayList();
		selectedslots = bookdowntableview.getSelectionModel().getSelectedItems();
		bookinfo slot;
		
		int i=0;
		int bokid;
		int index=0;
		int size = selectedslots.size();
		System.out.println("size:"+size);
		//int[] indexs = new int[size];
		String downbooks ="下架书籍：";
		for(i=0;i<size;i++)
		{
			slot = selectedslots.get(i);
			System.out.println("slot:"+slot.toString());
			bkid = slot.getBookid();
			bokid = Integer.parseInt(bkid);
			SQL = "UPDATE BOOK\r\n" + 
					"SET BUNSHELVE = '是'\r\n" + 
					"WHERE BID = "+bokid;
			System.out.println("SQL:"+SQL);
			try
			{
				Main.st.executeUpdate(SQL);
				downbooks = downbooks +" "+ bkid;
				System.out.println("downbooks:"+downbooks);
				index = bookdowndata.indexOf(slot);
				//bookdowndata.remove(index);
				System.out.println("selected size"+selectedslots.size());
				//indexs[i] = index;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"未全部下架成功，"+downbooks,"tip",JOptionPane.WARNING_MESSAGE);
				bookdownquery();
				return;
			}
		}
		JOptionPane.showMessageDialog(null,downbooks,"tip",JOptionPane.PLAIN_MESSAGE);
		bookdownquery();
	}
	
	public void bookquery()
	{
		bkname = bookquerybooknametextfield.getText();
		bkauthor = bookqueryauthortextfield.getText();
		bkclass = bookqueryclasstextfield.getText();
		bkpress = bookquerypresstextfield.getText();

		if(bkname!=null && !bkname.isEmpty()) {bkname = "AND BNAME LIKE '%"+bkname+"%' ESCAPE '\\'\r\n";}
		else {bkname="";}
		if(bkauthor!=null && !bkauthor.isEmpty()) {bkauthor = "AND BAUTHOR LIKE '%"+bkauthor+"%' ESCAPE '\\'\r\n";}
		else {bkauthor="";}
		if(bkclass!=null && !bkclass.isEmpty()) {bkclass = "AND BTYPE LIKE '%"+bkclass+"%' ESCAPE '\\'\r\n";}
		else {bkclass="";}
		if(bkpress!=null && !bkpress.isEmpty()) {bkpress = "AND BPRESS LIKE '%"+bkpress+"%' ESCAPE '\\'\r\n";}
		else {bkpress="";}
		if(bookqueryfreeradiobutton.isSelected()) {bkfree = "AND BFREE = '是'";}
		else {bkfree = "";}
		
		SQL = "SELECT * FROM BOOK\r\n" + 
				"WHERE BUNSHELVE = '否'\r\n"+bkname+bkauthor+bkclass+bkpress+bkfree;
		try
		{
			rs= Main.st.executeQuery(SQL);
			bookquerydata.clear();
			while(rs.next())
			{
				bkid = rs.getString(1);
				bkname = rs.getString(2);
				bkauthor = rs.getString(3);
				bkpress = rs.getString(4);
				bkclass = rs.getString(5);
				bklocation = rs.getString(7);
				bkfree = rs.getString(8);
				bookinfo newbook = new bookinfo(bkid,bkname,bkauthor,bkpress,bkclass,bklocation,bkfree);
				bookquerydata.add(newbook);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
//	class bookquerylistener<T> implements ChangeListener<T>
//	{
//		@Override
//		public void changed(ObservableValue <? extends T> a, T old, T n)
//		{
//			bookquery();
//		}
//	}
//	
	public void pwdconsistent()
	{
		
	}
	
	public void readerin()
	{
		try
		{
			rdid = readerinreaderidtextfield.getText().trim();
			int newid = Integer.parseInt(rdid);
			rdname = readerinreadernametextfield.getText().trim();
			String pwd = readerinpasswordtextfield.getText();	
			int sel = readerinsexcombobox.getSelectionModel().getSelectedIndex();
			String sex=null;
			if(sel==0)
			{
				sex = "男";
			}
			else
			{
				if(sel == 1)
				{
					sex ="女";
				}
			}
			if(rdid ==null|rdid.isEmpty()) 
			{
				JOptionPane.showMessageDialog(null,"请输入读者ID","tip：",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(rdname==null|| rdname.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"请输入读者姓名","tip：",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwd==null || pwd.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"请输入密码","tip：",JOptionPane.WARNING_MESSAGE);
				return;
			}
			SQL = "INSERT INTO READER \r\n" + 
					"VALUES ("+newid+",'"+rdname+"','"+pwd+"','"+sex+"');";
			Main.st.executeUpdate(SQL);
			JOptionPane.showMessageDialog(null,"录入成功："+rdname+" "+rdid,"tip：",JOptionPane.PLAIN_MESSAGE);
			readerinreaderidtextfield.setText("");
			readerinreadernametextfield.setText("");
			readerinpasswordtextfield.setText("");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	public void readeroutquery()
	{
		readerdowndata.clear();
		rdid = readeroutreaderidtextfield.getText().trim();
		rdname = readeroutreadernametextfield.getText().trim();
		if(rdid!=null && !rdid.isEmpty()) {rdid = "RID LIKE  '%"+rdid+"%' ESCAPE '\\'";}
		else {rdid = "";}
		if(rdname!=null && !rdname.isEmpty()) {rdname = " RNAME LIKE '%"+rdname+"%' ESCAPE '\\'";}
		else {rdname = "";}
		
		try
		{
			SQL = "SELECT *\r\n" + 
					"FROM READER \r\n";
			if(rdid.equals(""))
			{
				if(rdname.equals("")) {}
				else
				{
					SQL = SQL + "WHERE "+rdname;
				}
			}
			else
			{
				SQL = SQL +"WHERE "+rdid;
				if(rdname.equals("")) {}
				else
				{
					SQL = SQL + "\r\n AND"+rdname;
				}
			}
			rs = Main.st.executeQuery(SQL);
			readerdowndata.clear();
			while(rs.next())
			{
				rdid = rs.getString(1);
				rdname = rs.getString(2);
				rdsex = rs.getString(4);
				readerinfo newreader = new readerinfo(rdid,rdname,rdsex);
				readerdowndata.add(newreader);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	class readeroutlistener<T> implements ChangeListener<T>
	{
		 @Override
		  public void changed(ObservableValue <? extends T> a, T old, T n) 
		 {
			 readeroutquery();
		 }
	}
	
	
	public void readerout()
	{
		ObservableList<readerinfo> selectedslots = FXCollections.observableArrayList();
		selectedslots = readerouttableview.getSelectionModel().getSelectedItems();
		readerinfo slot;
		
		System.out.println(selectedslots.size());
		int i=0;
		int outnum = 0;
		for(i=0;i<selectedslots.size();i++)
		{
			slot = selectedslots.get(i);
			rdid = slot.getReaderid();
			System.out.println(rdid);
			//注销时不能有借书没还或者罚款没交
			try
			{
				SQL = "SELECT *\r\n" + 
						"FROM BORROW \r\n" + 
						"WHERE [RETURN] = '否'\r\n" + 
						"AND RID = "+rdid;
				ResultSet booksborrowed = Main.st.executeQuery(SQL);
				if(booksborrowed.next())
				{
					JOptionPane.showMessageDialog(null,rdid+"号读者有书未还，不可注销","tip：",JOptionPane.WARNING_MESSAGE);
					return;
				}
				SQL = "SELECT *\r\n" + 
						"FROM PULISH,BORROW\r\n" + 
						"WHERE PULISH.BID = BORROW.BID\r\n" + 
						"AND PAID = '否'\r\n" + 
						"AND BORROW.RID ="+rdid;
				ResultSet pulishitems = Main.st.executeQuery(SQL);
				if(pulishitems.next())
				{
					JOptionPane.showMessageDialog(null,rdid+"号读者有罚款未交，不可注销","tip：",JOptionPane.WARNING_MESSAGE);
					return;
				}
				SQL = "DELETE FROM READER\r\n" + 
						"WHERE RID ="+rdid;
				outnum += Main.st.executeUpdate(SQL);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"未能全部注销,成功数"+outnum,"tip：",JOptionPane.WARNING_MESSAGE);
				e.printStackTrace();
				return;
			}
		}
		JOptionPane.showMessageDialog(null,"全部注销成功,成功数"+outnum,"tip：",JOptionPane.WARNING_MESSAGE);
		readeroutquery();
	}
	
	public void readermodifyquery()
	{
		rdid = readermodifyreaderidtextfield.getText().trim();
		if(rdid==null || rdid.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"输入读者ID","tip：",JOptionPane.WARNING_MESSAGE);
			return;
		}
		SQL = "SELECT * FROM READER \r\n" + 
				"WHERE RID = "+rdid;
		try
		{
			rs = Main.st.executeQuery(SQL);
			rs.next();
			rdname = rs.getString(2);
			rdpwd = rs.getString(3);
			rdsex = rs.getString(4);
			if(rdsex.equals("男"))
			{
				readermodifysexcombobox.getSelectionModel().select(0);
			}
			if(rdsex.equals("女"))
			{
				readermodifysexcombobox.getSelectionModel().select(1);
			}
			readermodifyreadernametextfield.setText(rdname);
			readermodifypasswordtextfield.setText(rdpwd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void readermodify()
	{
		rdid = readermodifyreaderidtextfield.getText().trim();
		rdname = readermodifyreadernametextfield.getText().trim();
		int sel = readermodifysexcombobox.getSelectionModel().getSelectedIndex();
		rdpwd = readermodifypasswordtextfield.getText().trim();
		if(rdid==null||rdid.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"请输入读者ID","tip：",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(sel==0) {rdsex = "男";}
		if(sel==1) {rdsex = "女";}
		
		rdname = "RNAME = '"+rdname+"',\r\n";
		rdpwd = "RPWD = '"+rdpwd+"'\r\n";
		rdsex = "RSEX = '"+rdsex+"',\r\n";
		SQL = "UPDATE READER\r\n" + 
				"SET "+rdname+rdsex+rdpwd+"WHERE RID = "+rdid;
		System.out.println(SQL);
		try
		{
			Main.st.executeUpdate(SQL);
			JOptionPane.showMessageDialog(null,"更新成功","tip",JOptionPane.PLAIN_MESSAGE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void readerquery()
	{
		rdid = readerqueryreaderidtextfield.getText().trim();
		if(rdid == null || rdid.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"输入账号","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		int num=0;
		try
		{
			num = Integer.parseInt(rdid);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"账号格式不正确","tip",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			return;
		}
		SQL = "SELECT * FROM READER\r\n" + 
				"WHERE RID  = "+num;
		try
		{
			rs = Main.st.executeQuery(SQL);
			if(!rs.next())//没有返回结果
			{
				JOptionPane.showMessageDialog(null,"不存在该账户的用户","tip",JOptionPane.WARNING_MESSAGE);
				return;
			}
			else
			{
				readerqueryreadernametextfield.setText(rs.getString(2));
				readerquerysextextfield.setText(rs.getString(4));
				readerquerypasswordtextfield.setText(rs.getString(3));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	public void clearreaderquery()
	{
		readerqueryreaderidtextfield.clear();
		readerquerysextextfield.clear();
		readerquerypasswordtextfield.clear();
		readerqueryreadernametextfield.clear();
	}
	//借还书部分
	public void borrowbook()
	{
		rdid = borrowreturnreaderidtextfield.getText();
		if(rdid==null|| rdid.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"输入读者ID","tip：",JOptionPane.WARNING_MESSAGE);
			return;
		}
		bkid = borrowreturnbookidtextfield.getText();
		if(bkid==null || bkid.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"输入书籍ID","tip：",JOptionPane.WARNING_MESSAGE);
			return;
		}
		try
		{
			SQL = "SELECT * \r\n" + 
					"FROM BOOK\r\n" + 
					"WHERE  BFREE = '是'\r\n" + 
					"AND BUNSHELVE = '否'\r\n" + 
					"AND BID = '"+bkid+"';";
			ResultSet brbook = Main.st.executeQuery(SQL);//想要借的书的信息，检查数是否可以借
			if(!brbook.next())//没有返回结果
			{
				JOptionPane.showMessageDialog(null,"该书不能被借，可能该书不空闲或者已下架，或者书籍编号不正确","tip：",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			SQL = "SELECT COUNT(*)\r\n" + 
					"FROM BORROW \r\n" + 
					"WHERE [RETURN] = '否'\r\n" + 
					"AND RID = '"+rdid+"';";
			ResultSet borrowednum = Main.st.executeQuery(SQL);
			borrowednum.next();
			int borrowedbknum = Integer.parseInt(borrowednum.getString(1));
			if(borrowedbknum >= 5 )//每个人最多借五本书
			{
				JOptionPane.showMessageDialog(null,"借书数量达到上限","tip：",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
			Date nowdate = new Date();
			SQL = "SELECT *\r\n" + 
					"FROM BORROW \r\n" + 
					"WHERE [RETURN]='否'\r\n" + 
					"AND RID = '"+ rdid +"'\r\n" + 
					"AND ENDTIME < '"+dateformatter.format(nowdate)+"';";
			System.out.println(SQL);
			ResultSet overtimebooks = Main.st.executeQuery(SQL);
			if(overtimebooks.next())//有值说明有书超时未还
			{
				JOptionPane.showMessageDialog(null,"该读者有书超时未还，不能借书","tip：",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			SQL="SELECT *\r\n" + 
					"FROM PULISH,BORROW\r\n" + 
					"WHERE PULISH.BID = BORROW.BID\r\n" + 
					"AND PULISH.PAID = '否'\r\n" + 
					"AND BORROW.RID = '"+rdid+"';";
			ResultSet unpaidpulish = Main.st.executeQuery(SQL);
			if(unpaidpulish.next())
			{
				JOptionPane.showMessageDialog(null,"该读者有罚款未交，不能借书","tip：",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			//所有条件都满足，可以借书。登记借书信息
			Calendar c =Calendar.getInstance(); 
			c.setTime(nowdate);
			c.add(Calendar.DAY_OF_MONTH, 7);
			Date sevendayslater = c.getTime();
			SQL ="BEGIN TRANSACTION \r\n" + 
					"DECLARE @PK INT;\r\n" + 
					"SET @PK  = (SELECT MAX(BID)+1 FROM BORROW );\r\n" + 
					"INSERT INTO BORROW \r\n" + 
					"VALUES(@PK,"+rdid+","+bkid+",'"+dateformatter.format(nowdate)+"','"+dateformatter.format(sevendayslater)+"','否');\r\n" + 
					"COMMIT";
			Main.st.executeUpdate(SQL);//先借书表中插入一条记录
			
			SQL = "UPDATE BOOK\r\n" + 
					"SET BFREE = '否'\r\n" + 
					"WHERE BID  = "+bkid;
			
			Main.st.executeUpdate(SQL);
			JOptionPane.showMessageDialog(null,"借书成功","tip：",JOptionPane.PLAIN_MESSAGE);
			queryborrow();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
	}
	
	public void choosereturn()
	{
		ObservableList<borrowinfo> selectedslots = FXCollections.observableArrayList();
		selectedslots = borrowreturntableview.getSelectionModel().getSelectedItems();
		borrowinfo slot = selectedslots.get(0);
		rdid = slot.getReaderid();
		bkid = slot.getBookid();
		bkname = slot.getBookname();
		etime = slot.getEndtime();
		
		borrowreturnbookidtextfield.setText(bkid);
		
		Date nowdate;
		Date shouldreturntime;
		SimpleDateFormat datefm = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			nowdate = datefm.parse(datefm.format((new Date())));
			shouldreturntime = datefm.parse(etime);
			if(nowdate.after(shouldreturntime))
			{
				borrowreturnovertimetextfield.setText("是");
				borrowreturnoverdaytextfield.setVisible(true);
				LocalDate date1 = LocalDate.parse(datefm.format(nowdate));
				LocalDate date2 = LocalDate.parse(datefm.format(shouldreturntime));
				long days = Math.abs(ChronoUnit.DAYS.between(date1,date2));
				borrowreturnoverdaytextfield.setText(days+"");
			}
			else
			{
				borrowreturnovertimetextfield.setText("否");
				borrowreturnoverdaytextfield.setVisible(false);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	public void returnbook()
	{
		try
		{
			rdid = borrowreturnreaderidtextfield.getText();
			
			ObservableList<borrowinfo> selectedslots = FXCollections.observableArrayList();
			selectedslots = borrowreturntableview.getSelectionModel().getSelectedItems();
			borrowinfo slot = selectedslots.get(0);
			bkid = slot.getBookid();
			
			otime = borrowreturnovertimetextfield.getText();
			oday = borrowreturnoverdaytextfield.getText();
			
			int sel = borrowreturnbrokencombobox.getSelectionModel().getSelectedIndex();
			
			int readid;
			int bokid = Integer.parseInt(bkid);
			String hit="还书成功\r\n";
			int ovday = otime.equals("是")?Integer.parseInt(oday):0;

			if(sel==0 || otime.equals("是"))//书本有损坏或者是超时
			{
				String brkn = sel == 0?"是":"否";
				if(sel==0) hit = hit +"书籍损坏，打印罚单。\r\n";
				if(otime.equals("是")) hit = hit +"书籍超时。";
				SQL = "BEGIN TRANSACTION \r\n" + 
						"DECLARE @BORROWID INT;\r\n" + 
						"SET @BORROWID =\r\n" + 
						" (SELECT BID \r\n" + 
						"FROM BORROW \r\n" + 
						"WHERE RID = "+rdid+"\r\n" + 
						"AND BOOKID = "+bkid+"\r\n" + 
						"AND [RETURN]='否');\r\n" + 
						"INSERT INTO PULISH \r\n" + 
						"VALUES(@BORROWID,'"+otime+"',"+ovday+",'"+brkn+"','否');\r\n" + 
						"COMMIT ";
				System.out.println(SQL);
				Main.st.executeUpdate(SQL);
				System.out.println(otime);
				if(sel==0)//书籍损坏，则对应的书籍需要标记下架
				{
					SQL = "UPDATE BOOK\r\n" + 
							"SET BUNSHELVE = '是'\r\n" + 
							"WHERE BID = "+bokid;
					Main.st.executeUpdate(SQL);
					System.out.println(SQL);
				}
			}
			SQL = "UPDATE BOOK\r\n" + 
					"SET BFREE = '是'\r\n" + 
					"WHERE BID = "+bokid;
			Main.st.executeUpdate(SQL);
			SQL = "UPDATE BORROW \r\n" + 
					"SET [RETURN] = '是'\r\n" + 
					"WHERE BID = \r\n" + 
					"(\r\n" + 
					"SELECT BID\r\n" + 
					"FROM BORROW \r\n" + 
					"WHERE RID = "+rdid+"\r\n" + 
					"AND BOOKID ="+bkid+" \r\n" + 
					"AND [RETURN] = '否'\r\n" + 
					" )";
			Main.st.executeUpdate(SQL);
			System.out.println(SQL);

			JOptionPane.showMessageDialog(null,hit,"tip：",JOptionPane.PLAIN_MESSAGE);
			queryborrow();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void queryborrow()
	{
		borrowreturndata.clear();
		rdid = borrowreturnreaderidtextfield.getText().trim();
		if(rdid==null || rdid.isEmpty())
		{
			borrowreturndata.clear();
			return;
		}
		else
		{
			SQL = "SELECT BORROW.RID,BORROW.BOOKID,BOOK.BNAME,BORROW.ENDTIME\r\n" + 
					"FROM  BORROW,BOOK\r\n" + 
					"WHERE BORROW.BOOKID = BOOK.BID \r\n" + 
					" AND BORROW.[RETURN]='否'\r\n" + 
					"AND BORROW.RID LIKE '%"+rdid+"%' ESCAPE '\\'";
			try
			{
				rs = Main.st.executeQuery(SQL);
				while(rs.next())
				{
					rdid = rs.getString(1);
					bkid = rs.getString(2);
					bkname = rs.getString(3);
					etime = rs.getString(4);
					borrowinfo newborrow = new borrowinfo(rdid,bkid,bkname,etime,"",""); 
					borrowreturndata.add(newborrow);
				}	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	
	class borrowreturnlistener<T> implements ChangeListener<T>
	{
		 @Override
		  public void changed(ObservableValue <? extends T> a, T old, T n) 
		 {
			 queryborrow();
		 }
	} 
	
	
	public void finequery()
	{
		rdid = pulishreaderidtextfield.getText().trim();
		if(rdid!=null && !rdid.isEmpty()) {rdid = "AND READER.RID ="+rdid;}
		else {rdid = "";}
		
		SQL = "SELECT READER.RID, READER.RNAME,BOOK.BID,\r\n" + 
				"BOOK.BNAME,PULISH.OVERTIME,PULISH.[DAY],\r\n" + 
				"PULISH.BROKEN,BOOK.BPRICE\r\n" + 
				"FROM BOOK,BORROW,READER,PULISH \r\n" + 
				"WHERE PULISH.BID = BORROW.BID AND\r\n" + 
				"BORROW.RID = READER.RID AND \r\n" + 
				"BORROW.BOOKID = BOOK.BID \r\n"+
				"AND PULISH.PAID = '否'"+rdid;
		try 
		{
			rs = Main.st.executeQuery(SQL);
			pulishdata.clear();
			allfine = 0;
			while(rs.next())
			{
				rdid = rs.getString(1);
				rdname = rs.getString(2);
				bkid = rs.getString(3);
				bkname = rs.getString(4);
				otime = rs.getString(5);
				oday = rs.getString(6);
				brk = rs.getString(7);
				fn = rs.getString(8);
				int bkprice = Integer.parseInt(fn);
				if(brk.equals("否"))
				{
					bkprice = 0;
				}
				if(otime.equals("是"))
				{
					bkprice = bkprice + Integer.parseInt(oday);
				}
				allfine = allfine + bkprice;
				fn = bkprice+"";
				pulishinfo newpulish = new pulishinfo(rdid,rdname,bkid,bkname,otime,oday,brk,fn);
				pulishdata.add(newpulish);
			}
			pulishfinetextfield.setText(allfine+"元");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	class pulishlistener<T> implements ChangeListener<T>
	{
		@Override
		public void changed(ObservableValue <? extends T> a, T old, T n)
		{
			finequery();
		}
	}
	public void handlefine()
	{
		ObservableList<pulishinfo> selectedslots = FXCollections.observableArrayList();
		selectedslots = pulishtableview.getSelectionModel().getSelectedItems();
		pulishinfo slot;
		
		int i=0;
		int index;
		int handlenum=0;
		int size = selectedslots.size();
		System.out.print("size:"+selectedslots.size());
		for(i=0;i<size;i++)
		{
			System.out.println("i:"+i);
			slot = selectedslots.get(i);
			System.out.print("slot"+slot.toString());
			bkid = slot.getBookid();
			rdid = slot.getReaderid();
			System.out.print("bkid"+bkid+"rdid"+rdid);
			bkid = "AND BORROW.BOOKID = "+bkid;
			rdid = "AND BORROW.RID =" + rdid;
			
			SQL = "UPDATE PULISH\r\n" + 
					"SET PAID = '是'\r\n" + 
					"where PULISH.BID =\r\n" + 
					"(\r\n" + 
					"	SELECT PULISH.BID\r\n" + 
					"	FROM PULISH,BORROW\r\n" + 
					"	WHERE PULISH.BID = BORROW.BID\r\n" + 
					bkid+"\r\n"+rdid+"\r\n"+
					")";
			try
			{
				 handlenum += Main.st.executeUpdate(SQL);
				 System.out.println("handlenum"+handlenum);
				 index = pulishdata.indexOf(slot);
				 //pulishdata.remove(index);
				 System.out.print("size:"+selectedslots.size());
			}
			catch(Exception e)
			{
				finequery();
				e.printStackTrace();
			}
			
		}
		JOptionPane.showMessageDialog(null,"已完成处理个数："+handlenum,"tip",JOptionPane.PLAIN_MESSAGE);
		finequery();
	}
	
}
