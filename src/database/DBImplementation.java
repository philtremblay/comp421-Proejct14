package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.sql.Statement;

public class DBImplementation {
	private static Statement stmt = null;

	public DBImplementation() {
		connectToDB();
	}

	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static Connection conn = null;

	// DB Credentials
	private static final String DB_USERNAME = "sfosti"; // needs to be updated
	private static final String DB_PASSWORD = "dNT8ap6M";
	private static final String DB_URL = "jdbc:postgresql://comp421.cs.mcgill.ca/cs421";

	// connect to DB
	public static void connectToDB()
	{
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
			JOptionPane.showMessageDialog(null,"CONNECTED SUCCESSFULLY");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Unable to connect to the database");
			System.exit(0);		
		}
	}
	
	public static void closeDB()
	{
		try {
			conn.close();
			JOptionPane.showMessageDialog(null,"Database closed successfully");
			System.exit(0);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Database closed With error: " + e);

		}
	}
	
	public static void addTuple(String sql)
	{
		try {
			stmt = conn.createStatement();
	        stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Query executed successfully");
		} catch (SQLException e) {
			//System.out.println(e);
			JOptionPane.showMessageDialog(null,"Cannot execute: " + sql +  "\n" + e.getMessage() );
		}		
	}
	
	
}
