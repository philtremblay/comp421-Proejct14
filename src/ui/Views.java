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
import javax.swing.border.TitledBorder;

import database.DBImplementation;
import variables.Values;


public class Views  implements ActionListener {
	private final int WIDTH = 900;
	private final int HEIGHT = 800;
	private String item = Values.TABLES[0];
	private String lst;


	protected JFrame frame;
	protected JPanel addValuesPanel;
	protected JLabel addingValuesLabel;
	protected JLabel choice;
	protected JComboBox list;
	protected JTextField lTextField = new JTextField();
	protected JButton buttonAdd = new JButton("EXECUTE");

	public Views()
	{
		createFrame();
		addValuesLabel();
		addToFrame();
		frame.pack();
		//frame.setFocusable(true);
		frame.setVisible(true);

	}


	// create and initialize the frame	
	private void createFrame()
	{
		frame = new JFrame("GROUP 14");
		//frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	DBImplementation.closeDB();
            }
        });
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

	}

	// add a new value to a specific 
	private void addValuesLabel()
	{

		// the panel for adding attributes
		addValuesPanel = new JPanel();

		BorderLayout pLayout = new BorderLayout();
		pLayout.setVgap(20);// set gap
		addValuesPanel.setLayout(pLayout);

		// border of the panel
		TitledBorder border = BorderFactory.createTitledBorder("Left");
		//addValuesPanel.setPreferredSize(new Dimension(WIDTH/2, 100));
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitle("ADDING TUPLES");
		border.setTitlePosition(TitledBorder.TOP);
		addValuesPanel.setBorder(border);// set the border of the panel

		// create combo box
		list = new JComboBox(Values.TABLES);
		list.addActionListener(this);
		// create a label and add it to the Panel
		addingValuesLabel = new JLabel("Add a new tuple in Table: ");
		addingValuesLabel.setFont(new Font("Serif", Font.BOLD, 18));
		addingValuesLabel.setLabelFor(list);

		// create a label and add it to the Panel
		choice = new JLabel("<html>Please enter the tuple for the choosing table."
				+ " If you do not want to specify an attribute, enter NULL<br>"
				+ "Example: Paul, 4, NULL, 1994</html>");
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
	}
	
	
	// add the outerPanel to the frame
	private void addToFrame()
	{
		frame.add(addValuesPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// action listener for the JCombo boc
		if(e.getSource() == list){
			item = list.getSelectedItem().toString();
			if(!item.equals(Values.TABLES[0])){
				lTextField.enable(true);
				buttonAdd.setEnabled(true);
				String [] arrList =  UILogic.formattedAttributesTables(item);
				lTextField.setText(Arrays.toString(arrList).replace("]", "").replace("[", " "));
			}
			else
			{
				lTextField.setText(null);
				lTextField.enable(false);
				buttonAdd.setEnabled(false);
			}
		}
		
		else if(e.getSource() == buttonAdd)
		{
			System.out.println("HERE");
			String [] arrAttr =  UILogic.attributesTables(item);
			String query = "INSERT INTO " + item + " ( " + Arrays.toString(arrAttr).replace("]", "").
					replace("[", " ") + ") VALUES (  " + lTextField.getText() + " );";
			DBImplementation.addTuple(query);
		}

	}



}
