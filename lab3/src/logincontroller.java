package application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;

import java.sql.*;

public class logincontroller implements Initializable {
	
//	String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	String dburl = "jdbc:sqlserver://localhost:1433;DatabaseName=Library";
//	String username = "user1";
//	String userpwd = "user1";
//	Connection con;
//	Statement st;
	
	@FXML
	private Button readerlogin;
	@FXML
	private Button adminlogin;
	@FXML
	private TextField numbertext;
	@FXML
	private TextField passwordtext;

	@Override 
	public void initialize(URL location,ResourceBundle resources)
	{
//		try
//		{
//			Class.forName(drivername);
//			con = DriverManager.getConnection(dburl,username,userpwd);
//			st = con.createStatement();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
	}
		
	public void readerlogin()
	{
		//System.out.print("enter readerlogin function");
		int number;
		String num;
		String password;
		String SQL;
		ResultSet SQLresult=null;
		//System.out.print("enter get number textfield\n");
		
		
		num = numbertext.getText().trim();
		//System.out.println("num:"+num);
			
		if(num.isEmpty())
		{
			//System.out.println("number text null");
			JOptionPane.showMessageDialog(null,"input number","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		number = Integer.parseInt(num);
		
		password = passwordtext.getText().trim();
		if(password.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"input password","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		SQL = "SELECT * FROM READER WHERE RID =" + number +"AND RPWD ='"+password+"'";
		try 
		{
			SQLresult = Main.st.executeQuery(SQL);
			if(SQLresult.next())//正常登录
			{
					//Main.setnumber(number);
					///////
				// set reader ui
				//Main.settitle("readerlogin");
				Main.number = number;
				
				Parent readerparent = FXMLLoader.load(getClass().getResource("readerfxml.fxml"));
				Scene  s = new Scene(readerparent);
				
				SQL = "SELECT RNAME FROM READER WHERE RID = " + number +";";
				SQLresult = Main.st.executeQuery(SQL);
				SQLresult.next();
				String name = SQLresult.getString("RNAME");
				Main.settitle(name);
				//System.out.print(Main.number);
				Main.setscene(s);
					///////////
				//////////////
				///////////
			}
			else
			{
				JOptionPane.showMessageDialog(null,"number or password error","error",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		
	}
	
	public void adminlogin()
	{
		int number;
		String num;
		String password;
		String  SQL;
		ResultSet rs=null;
		//System.out.print("enter adminlogin function");
		
		num = numbertext.getText();
			
		if(num.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"input number","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		else
		{
			number = Integer.parseInt(num);
		}
		
		password = passwordtext.getText();
		if(password.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"input password","tip",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		SQL = "SELECT * FROM ADMIN WHERE AID=" + number +"AND APWD ='"+password+"'";
		//System.out.print("leave adminlogin function");
		try
		{
			rs = Main.st.executeQuery(SQL);
			if(rs.next())
			{
				/*
				 * set admin ui\
				 * */
				Main.number = number;
				Parent adminparent = FXMLLoader.load(getClass().getResource("adminfxml.fxml"));
				Scene s = new Scene(adminparent);
				//System.out.print(Main.number);
				SQL = "SELECT ANAME FROM ADMIN WHERE AID = " + number+";";
				rs = Main.st.executeQuery(SQL);
				rs.next();
				String name = rs.getString("ANAME");
				Main.settitle(name);
				Main.setscene(s);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"number or passwprd error","error",JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
