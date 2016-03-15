import javax.swing.*;
import java.sql.*;

public class Main {
	
	private static final String JDBC_DRIVER = "org.postgresql.Driver";  
	private static Connection conn = null;

	
	
	//DB Credentials
	private static final String DB_USERNAME = "cs421g14"; //needs to be updated
	private static final String DB_PASSWORD = "[lephant22]";
	private static final String DB_URL = "jdbc:postgresql://132.206.51.39/cs421";
	
	//GUI Elements
	private static JFrame aFrame;

	
	public static void main(String[] args)
	{
		createFrame();
		connectToDB();
	}
	
	public static JFrame createFrame()
	{
		JFrame lFrame = new JFrame("Group 14");
		lFrame.setSize(512, 512);
		lFrame.setVisible(true);
		return lFrame;
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
	
	public static void executeQuery(Connection pConnection, String pQuery)
	{
		assert(pConnection != null);
		
		try {
			Statement lStatement = pConnection.createStatement();
			ResultSet lResultSet = lStatement.executeQuery(pQuery);
			while(lResultSet.next())
			{
				System.out.println("Column 1 returned");
				System.out.println(lResultSet.getString(1));
			}
			lResultSet.close();
			lStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
