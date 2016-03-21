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
	private static final String DB_USERNAME = "cs421g14"; //needs to be
	private static final String DB_PASSWORD = "[lephant22]";
	
	private static final String DB_URL = "jdbc:postgresql://comp421.cs.mcgill.ca/cs421";

	// connect to DB
	public static void connectToDB() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			JOptionPane.showMessageDialog(null, "CONNECTED SUCCESSFULLY");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to connect to the database");
			System.exit(0);
		}
	}

	// close the database
	public static void closeDB() {
		try {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "Database closed successfully");
			System.exit(0);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Database closed With error: " + e.getSQLState());

		}
	}

	// add a tuple to a specific table
	public static void addTuple(String sql) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Query executed successfully");
		} catch (SQLException e) {
			// System.out.println(e);
			JOptionPane.showMessageDialog(null, "Cannot execute: " + sql + "\n" + e.getMessage());
			System.exit(0);
		}
	}

	// execute query to print the information about a user who did an order
	// Based on the order status
	public static String executeQueryStatus(String sql) {
		String str = "";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int i = 1;

			while (rs.next()) {
				str += (i + "- ORDER STATUS: " + rs.getString("order_status") + ", TOTAL PRICE: "
						+ rs.getString("totalprice") + ", NAME: " + rs.getString("name") + ", EMAIL: "
						+ rs.getString("email") + ", PHONE: " + rs.getString("phone") + ", ADDRESS: "
						+ rs.getString("address") + "\n\n");
				i++;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Cannot execute: " + sql + "\n" + e.getMessage());
			System.exit(0);
		}
		return str;

	}

	// check if a given name exists before trying to update the values
	public static boolean isNameExist(String name) {
		String sql = "SELECT name FROM Customer WHERE name = " + name;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (!rs.isBeforeFirst())
				return false;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Cannot execute: " + sql + "\n" + e.getMessage());
			System.exit(0);
		}
		return true;

	}

	// execute an update for account modification
	public static void executeModification(String name, String usernameText, String phoneText, String emailText,
			String addressText) {
		try {
			stmt = conn.createStatement();
			if (!usernameText.equals(""))
				stmt.addBatch("UPDATE Customer SET username = '" + usernameText + "' WHERE name = " + name + ";");
			if (!phoneText.equals(""))
				stmt.addBatch("UPDATE Customer SET phone = '" + phoneText + "' WHERE name = " + name + ";");
			if (!emailText.equals(""))
				stmt.addBatch("UPDATE Customer SET email = '" + emailText + "' WHERE name = " + name + ";");
			if (!addressText.equals(""))
				stmt.addBatch("UPDATE Customer SET address = '" + addressText + "' WHERE name = " + name + ";");
			stmt.executeBatch();
			JOptionPane.showMessageDialog(null,"The modification has been saved!");

		} catch (SQLException e) {
			// System.out.println(e);
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(0);
		}
	}
	
	// compute the total price of the selected items
	public static String computePrice(String list) {
		String sql1 = "SELECT description, price FROM SportEquipment WHERE description IN ( " + list + " );";

		String sql = "SELECT SUM(price::numeric) AS total FROM SportEquipment WHERE description IN ( " + list + " );";
		String price = "";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next()){
				price += rs.getString("description") + ": " + rs.getString("price") + "\n";
			}
			
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				price += "\n\nTotal price: $ " + rs.getString("total");
			}
			
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Cannot execute: " + sql + "\n" + e.getMessage());
			System.exit(0);
		}
		return price;
	}

}
