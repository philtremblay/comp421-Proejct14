import javax.swing.*;
import java.sql.*;

public class Main {
	
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	
	
	private final String DB_USERNAME = "group14"; //needs to be updated
	private final String DB_PASSWORD = "[lephant22]";

	
	public static void main(String[] args)
	{
		JFrame lFrame = new JFrame("Group 14");
		lFrame.setVisible(true);
	}
	
	public void connectToDB()
	{
		Class.forName("org.postgresql.Driver");
	}

}
