package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.sql.*;


public class Main extends Application 
{
	
	//to connect database
	static final String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String dburl = "jdbc:sqlserver://localhost:1433;DatabaseName=Library";
	static final String username = "user1";
	static final String userpwd = "user1";
	static Connection con;
	static Statement st;
	static int number;
//	
	static Stage primarystage = null;
	private static Scene loginscene = null;
//	private static Scene readerscene = null;
//	private static Scene adminscene = null;
	private Parent loginparent = null;
//	private Parent readerparent = null;
//	private Parent adminparent = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
				try 
				{
					Class.forName(drivername);
					con = DriverManager.getConnection(dburl,username,userpwd);
					st= con.createStatement();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				primaryStage.setTitle("Library--acm1601江易星");
				primarystage = primaryStage;
				//BorderPane root = new BorderPane();
				//Parent loginui = FXMLLoader.load(getClass().getResource("loginfxml.fxml"));
				loginparent = FXMLLoader.load(getClass().getResource("loginfxml.fxml"));
				
				
				loginscene = new Scene(loginparent);
	//			readerscene = new Scene(readerparent);
	//			adminscene = new Scene(adminparent);
	//			Parent readerui = FXMLLoader.load(getClass().getResource("readerfxml.fxml"));
				//Scene scene = new Scene(loginui,600,400);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				//primaryStage.setScene(scene);
				setloginui();
				primarystage.show();
			} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void setprimarystage(Stage stage)
	{
		this.primarystage = stage;
	}
	public static Stage getprimarystage()
	{
		return primarystage;
	}
	public static void setloginui()
	{
		primarystage.setScene(loginscene);
	}
//	public static void setreaderui()
//	{
//		primarystage.setScene(readerscene);
//	}
//	public static void setadminui()
//	{
//		primarystage.setScene(adminscene);
//	}
	public static void settitle(String title)
	{
		primarystage.setTitle(title);
	}
	public static void setscene(Scene scene)
	{
		primarystage.setScene(scene);
	}
	
	public static void main(String[] args) {
//		String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//		String dburl = "jdbc:sqlserver://localhost:1433;DatabaseName=Library";
//		String username = "user1";
//		String userpwd = "user1";
//		String sql = "SELECT * FROM READER WHERE RID = 1 AND RPWD LIKE '1' escape '\'";
//		try
//		{
//			Class.forName(drivername);
//			con = DriverManager.getConnection(dburl,username,userpwd);
//			st = con.createStatement();
//			st.executeQuery(sql);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		launch(args);
	}
}
