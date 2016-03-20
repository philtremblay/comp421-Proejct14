package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.sql.Statement;

public class DBImplementation {
	private static Statement stmt = null;
	private static ResultSet rs = null;

	public DBImplementation() {
		connectToDB();
	}

	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static Connection conn = null;

	// DB Credentials
//	private static final String DB_USERNAME = "cs421g14"; //needs to be updated
//	private static final String DB_PASSWORD = "[lephant22]";
	private static final String DB_USERNAME = "sfosti"; //needs to be updated
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
	
	// close the database
	public static void closeDB()
	{
		try {
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
			conn.close();
			JOptionPane.showMessageDialog(null,"Database closed successfully");
			System.exit(0);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Database closed With error: " + e.getSQLState());

		}
	}
	
	// add a tuple to a specific table
	public static void addTuple(String sql)
	{
		try {
			stmt = conn.createStatement();
	        stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Query executed successfully");
		} catch (SQLException e) {
			//System.out.println(e);
			JOptionPane.showMessageDialog(null,"Cannot execute: " + sql +  "\n" + e.getMessage());
			System.exit(0);
		}		
	}
	
	// execute query to print the information about a user who did an order Based on the order status
	public static String executeQueryStatus(String sql)
	{
		String str = "";
		try {
			stmt = conn.createStatement();
	        rs = stmt.executeQuery(sql);
	        int i = 1;
	        
	        while( rs.next()){
	        	 str += (i + "- ORDER STATUS: " + rs.getString("order_status") + ", TOTAL PRICE: " 
	        			+ rs.getString("totalprice") + ", NAME: " + rs.getString("name") + ", EMAIL: " 
	        			+rs.getString("email") + ", PHONE: " + rs.getString("phone") + ", ADDRESS: " +
	        			rs.getString("address") +"\n\n");
	        	i++;
	        }
		} catch (SQLException e) {
			//System.out.println(e);
			JOptionPane.showMessageDialog(null,"Cannot execute: " + sql +  "\n" + e.getMessage());
			System.exit(0);
		}	
		return str;
		
	}
	
	
}
