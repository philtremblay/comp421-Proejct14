package main;

import database.DBImplementation;
import ui.Views;

public class Main {
	
	public static void main(String [] args)
	{
		DBImplementation db = new DBImplementation();
		//db.connectToDB();
		Views v = new Views();
	}

}
