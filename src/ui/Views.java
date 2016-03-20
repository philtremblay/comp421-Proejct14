package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import database.DBImplementation;
import variables.Values;


public class Views  implements ActionListener {
	// variables
	private String item = Values.TABLES[0];
	private String itemStatus = Values.TABLES[0];

	// UI variables
	protected JFrame frame;
	JPanel container = new JPanel();
	protected JLabel choice;
	protected JComboBox list, listStatus;
	protected JTextField lTextField = new JTextField();
	protected JButton buttonAdd = new JButton("EXECUTE");
	protected JButton buttonStatus = new JButton("EXECUTE");
	JTextArea textArea;

	

	public Views()
	{
		//create the frame
		createFrame();
		// set panel layout
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		JScrollPane scrollContainer = new JScrollPane(container); 
		scrollContainer.setAutoscrolls(true);
		
		// panel for adding values to frame
		addValuesToTableUI();
		// get user info based on status of order
		InfoByOrderStatusUI();
		
		// output textarea
		displayOutputUI();
		frame.add(container);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	// create and initialize the frame	
	private void createFrame()
	{
		frame = new JFrame("GROUP 14");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	DBImplementation.closeDB();
            }
        });
	}
	
	// this will be used by many Jpanel
	private void setLayout(JPanel pan, BorderLayout pLayout, String title){
		pLayout.setVgap(20);// set gap
		pan.setLayout(pLayout);

		// border of the panel
		TitledBorder border = BorderFactory.createTitledBorder("Left");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitle(title);
		border.setTitlePosition(TitledBorder.TOP);
		pan.setBorder(new CompoundBorder(border, new EmptyBorder(15,10,30,10)));// set the border of the panel
	}

	// add a new value to a specific 
	private void addValuesToTableUI()
	{
		JPanel addValuesPanel = new JPanel();
		BorderLayout pLayout = new BorderLayout();
		setLayout(addValuesPanel,pLayout, "ADDING TUPLES");
		// create combo box
		list = new JComboBox(Values.TABLES);
		list.addActionListener(this);
		// create a label and add it to the Panel
		JLabel addingValuesLabel = new JLabel("Add a new tuple in Table: ");
		addingValuesLabel.setFont(new Font("Serif", Font.BOLD, 18));
		addingValuesLabel.setLabelFor(list);

		// create a label and add it to the Panel
		choice = new JLabel("<html>Please enter the tuple for the choosing table."
				+ " If you do not want to specify an attribute, enter NULL<br>"
				+ "Example: 'Paul', '2020 wall Street USA', 4, NULL, NULL, '1994'</html>");
		addingValuesLabel.setFont(new Font("Serif", Font.BOLD, 18));

		lTextField.setMargin(new Insets(10,10,10,10));
		// Inner JPanel
		BorderLayout innerLayout = new BorderLayout();
		JPanel innerPanel = new JPanel(innerLayout);
		innerLayout.setVgap(5);
		innerPanel.add(choice, innerLayout.NORTH);
		innerPanel.add(lTextField, innerLayout.CENTER);
		innerPanel.add(buttonAdd, innerLayout.SOUTH);
		lTextField.enable(false);
		innerLayout.setVgap(15);

		// add to the panel
		addValuesPanel.add(addingValuesLabel, pLayout.WEST);
		addValuesPanel.add(list);
		addValuesPanel.add(innerPanel,pLayout.PAGE_END);
		addValuesPanel.setVisible(true);

		//button
		buttonAdd.addActionListener(this);
		buttonAdd.setEnabled(false);		
		container.add(addValuesPanel);
	}
	// UI to find information about order based on the status
	private void InfoByOrderStatusUI()
	{
		JPanel statusPanel = new JPanel();
		BorderLayout statusLayout = new BorderLayout();
		setLayout(statusPanel,statusLayout, "INFO ABOUT ORDER BASED ON STATUS");
		// create combo box
		listStatus = new JComboBox(Values.STATUS);
		listStatus.addActionListener(this);
		// create a label and add it to the Panel
		JLabel addingValuesLabel = new JLabel("<html>Choose the status of Order "
				+ "for which you want to have informations: </html>");
		addingValuesLabel.setFont(new Font("Serif", Font.BOLD, 18));
		addingValuesLabel.setLabelFor(listStatus);
		statusPanel.add(addingValuesLabel, statusLayout.NORTH);
		statusPanel.add(listStatus);

		// button
		buttonStatus.addActionListener(this);
		buttonStatus.setEnabled(false);
		statusPanel.add(buttonStatus,statusLayout.SOUTH);
		container.add(statusPanel);
	}

	// display output to the box
	private void displayOutputUI()
	{
		JPanel textAreaPanel = new JPanel();
		BorderLayout layout = new BorderLayout();
		setLayout(textAreaPanel,layout, "OUTPUT");
		textArea = new JTextArea(200, 50);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textAreaPanel.add(textArea);
		container.add(textAreaPanel);
	
	}
	
	
/***********************************************************************************
 * This section is more about the logic of the UI instead of the UI itself
 * Example: handle button click
	********************************************************************************************/	
	
	
	// perform action on click or on select
		@Override
		public void actionPerformed(ActionEvent e) {
			// action listener for the JCombo box
			if(e.getSource() == list)
				performActionOnList();
			// when clicking on the button from the
			else if(e.getSource() == buttonAdd)
				performActionButtonAdd();
			else if(e.getSource() == listStatus)
				performActionOnListStatus();
			else if(e.getSource() == buttonStatus)
				performActionButtonStatus();
		}
	
	// action to perform when choosing an element from the list of Tables to add tuples
	private void performActionOnList()
	{
		item = list.getSelectedItem().toString();
		if(!item.equals(Values.TABLES[0])){
			lTextField.enable(true);
			buttonAdd.setEnabled(true);
			String [] arrList =  Values.formattedAttributesTables(item);
			lTextField.setText(Arrays.toString(arrList).replace("]", "").replace("[", " "));
		}
		else
		{
			lTextField.setText(null);
			lTextField.enable(false);
			buttonAdd.setEnabled(false);
		}
	}
	
	// action to perform when clicking on Execute button for adding a tuple
		private void performActionButtonAdd()
		{
			String [] arrAttr =  Values.attributesTables(item);
			String query = "INSERT INTO " + item + " ( " + Arrays.toString(arrAttr).replace("]", "").
					replace("[", " ") + ") VALUES (  " + lTextField.getText() + " );";
			DBImplementation.addTuple(query);
		}
		
		// action to perform when clicking on Execute button to display info about user's order based on order status
		private void performActionButtonStatus(){
			String query = "SELECT *"
					+ "  FROM (" +
					"SELECT * FROM \n(" + 
							"SELECT c.order_status, c.totalprice, p.pid FROM CustOrder AS c, PaymentInfo AS p \n"
							+ " WHERE c.order_status = " + itemStatus + " AND c.numberoncc = p.numberOnCC) Temp) Temp2 \n"+ 
			"INNER JOIN Customer ON Temp2.pid = Customer.pid;";
			textArea.setText(DBImplementation.executeQueryStatus(query));
		}
		
		// action to perform when choosing an element from the list of Status
		private void performActionOnListStatus()
		{
			itemStatus = listStatus.getSelectedItem().toString();
			if(!itemStatus.equals(Values.STATUS[0]))
				buttonStatus.setEnabled(true);
			else 
				buttonStatus.setEnabled(false);
		}
}
