import javax.swing.*;
import java.sql.*;

public class Main {
	
	private static final String JDBC_DRIVER = "org.postgresql.Driver";  
	private static Connection conn = null;

	
	
	private static final String DB_USERNAME = "cs421g14"; //needs to be updated
	private static final String DB_PASSWORD = "[lephant22]";
	private static final String DB_URL = "jdbc:postgresql://132.206.51.39/cs421";

	
	public static void main(String[] args)
	{
		JFrame lFrame = new JFrame("Group 14");
		lFrame.setVisible(true);
		connectToDB();
	}
	
	public static void connectToDB()
	{
		try{
			Class.forName(JDBC_DRIVER);
		}catch(ClassNotFoundException e)
		{
			System.out.println("LOL");
		}
		
		try{
			conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
			System.out.println("Connected");
		}catch(Exception e)
		{
			System.out.println("No connection");
		}
	}

}
