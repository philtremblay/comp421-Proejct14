package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import database.DBImplementation;
import variables.Values;

public class Views implements ActionListener, ItemListener {
	// variables
	private String item = Values.TABLES[0];
	private String itemStatus = Values.TABLES[0];
	private ArrayList<JCheckBox> listItem = new ArrayList<JCheckBox>();
	String itemRadio;

	// UI variables
	protected JFrame frame;
	JPanel container = new JPanel();
	protected JLabel choice;
	protected JComboBox list, listStatus;
	protected JTextField lTextField = new JTextField();
	protected JButton buttonAdd = new JButton("EXECUTE");
	protected JButton buttonStatus = new JButton("EXECUTE");
	protected JButton buttonSave = new JButton("SAVE");
	protected JButton buttonCompute = new JButton("COMPUTE TOTAL PRICE");
	protected JButton buttonFind = new JButton("FIND SHOES AND CLOTHES");
	protected JButton buttonQuit = new JButton("QUIT");

	JTextArea textArea;

	JTextField addressText, phoneText, emailText, nameText, usernameText;

	public Views() {
		// create the frame
		createFrame();
		// set panel layout
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		// panel for adding values to frame
		addValuesToTableUI();
		// get user info based on status of order
		InfoByOrderStatusUI();
		modifyAccountUI();
		chooseByEquipmentUI();
		BuyItemUI();
		// output textarea
		displayOutputUI();
		quit();
		frame.add(container);
		JScrollPane scrollContainer = new JScrollPane(container);
		frame.add(scrollContainer);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	// create and initialize the frame
	private void createFrame() {
		frame = new JFrame("GROUP 14");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DBImplementation.closeDBAndQuit();
			}
		});
	}

	// this will be used by many Jpanel
	private void setLayout(JPanel pan, BorderLayout pLayout, String title) {
		pLayout.setVgap(20);// set gap
		pan.setLayout(pLayout);

		// border of the panel
		TitledBorder border = BorderFactory.createTitledBorder("Left");
		border.setTitleJustification(TitledBorder.CENTER);
		border.setTitle(title);
		border.setTitlePosition(TitledBorder.TOP);
		pan.setBorder(new CompoundBorder(border, new EmptyBorder(15, 10, 30, 10)));// set
																					// the
																					// border
																					// of
																					// the
																					// panel
	}

	// add a new value to a specific
	private void addValuesToTableUI() {
		JPanel addValuesPanel = new JPanel();
		BorderLayout pLayout = new BorderLayout();
		setLayout(addValuesPanel, pLayout, "ADDING TUPLES");
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

		lTextField.setMargin(new Insets(10, 10, 10, 10));
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
		addValuesPanel.add(innerPanel, pLayout.PAGE_END);
		addValuesPanel.setVisible(true);

		// button
		buttonAdd.addActionListener(this);
		buttonAdd.setEnabled(false);
		container.add(addValuesPanel);
	}

	// UI to find information about order based on the status
	private void InfoByOrderStatusUI() {
		JPanel statusPanel = new JPanel();
		BorderLayout statusLayout = new BorderLayout();
		setLayout(statusPanel, statusLayout, "INFO ABOUT ORDER BASED ON STATUS");
		// create combo box
		listStatus = new JComboBox(Values.STATUS);
		listStatus.addActionListener(this);
		// create a label and add it to the Panel
		JLabel addingValuesLabel = new JLabel(
				"<html>Choose the status of Order " + "for which you want to have informations: </html>");
		addingValuesLabel.setFont(new Font("Serif", Font.BOLD, 18));
		addingValuesLabel.setLabelFor(listStatus);
		statusPanel.add(addingValuesLabel, statusLayout.NORTH);
		statusPanel.add(listStatus);

		// button
		buttonStatus.addActionListener(this);
		buttonStatus.setEnabled(false);
		statusPanel.add(buttonStatus, statusLayout.SOUTH);
		container.add(statusPanel);
	}

	// display output to the box
	private void displayOutputUI() {
		JPanel textAreaPanel = new JPanel();
		BorderLayout layout = new BorderLayout();
		setLayout(textAreaPanel, layout, "OUTPUT");
		textArea = new JTextArea(10, 8);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textAreaPanel.add(textArea);
		container.add(textAreaPanel);
	}

	// modify account
	private void modifyAccountUI() {

		JPanel accountPanel = new JPanel();
		BorderLayout AccLayout = new BorderLayout();
		setLayout(accountPanel, AccLayout, "ACCOUNT MODIFICATION");

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

		// textfield and label for name
		JPanel namePanel = new JPanel(new BorderLayout());
		nameText = new JTextField();
		JLabel nameLabel = new JLabel("Please enter your name to have access to account: ");
		nameLabel.setFont(new Font("Serif", Font.BOLD, 18));
		nameLabel.setLabelFor(nameText);
		namePanel.add(nameLabel, BorderLayout.WEST);
		namePanel.add(nameText);
		wrapper.add(namePanel);

		// textfield and label for username
		JPanel usernamePanel = new JPanel(new BorderLayout());
		usernameText = new JTextField();
		JLabel usernameLabel = new JLabel("Enter the new username: ");
		usernameLabel.setFont(new Font("Serif", Font.BOLD, 18));
		usernameLabel.setLabelFor(usernameText);
		usernamePanel.add(usernameLabel, BorderLayout.WEST);
		usernamePanel.add(usernameText);
		wrapper.add(usernamePanel);

		// textfield and label for email
		JPanel emailPanel = new JPanel(new BorderLayout());
		emailText = new JTextField();
		JLabel emailLabel = new JLabel("Enter the new email: ");
		emailLabel.setFont(new Font("Serif", Font.BOLD, 18));
		emailLabel.setLabelFor(emailText);
		emailPanel.add(emailLabel, BorderLayout.WEST);
		emailPanel.add(emailText);
		wrapper.add(emailPanel);

		// textfield and label for Phone number
		JPanel phonePanel = new JPanel(new BorderLayout());
		phoneText = new JTextField();
		JLabel phoneLabel = new JLabel("Enter the new phone number: ");
		phoneLabel.setFont(new Font("Serif", Font.BOLD, 18));
		phoneLabel.setLabelFor(phoneText);
		phonePanel.add(phoneLabel, BorderLayout.WEST);
		phonePanel.add(phoneText);
		wrapper.add(phonePanel);

		// textfield and label for address
		JPanel addressPanel = new JPanel(new BorderLayout());
		addressText = new JTextField();
		JLabel addressLabel = new JLabel("Enter the new address: ");
		addressLabel.setFont(new Font("Serif", Font.BOLD, 18));
		addressLabel.setLabelFor(addressText);
		addressPanel.add(addressLabel, BorderLayout.WEST);
		addressPanel.add(addressText);
		wrapper.add(addressPanel);

		// button
		buttonSave.addActionListener(this);
		accountPanel.add(wrapper);
		accountPanel.add(buttonSave, BorderLayout.SOUTH);

		container.add(accountPanel);
	}

	private void chooseByEquipmentUI() {
		buttonFind.setEnabled(false);
		JPanel itemPanel = new JPanel();
		BorderLayout itemLayout = new BorderLayout();
		setLayout(itemPanel, itemLayout, "DISPLAY ITEMS BY SPORT EQUIPMENT");

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

		JLabel label = new JLabel(
				"Choose a sport equipment below to display all the " + "corresponding items in the text area ");
		label.setFont(new Font("Serif", Font.BOLD, 18));
		wrapper.add(label);

		JRadioButton buttonradio1 = new JRadioButton(Values.TABLES[1]);
		JRadioButton buttonradio2 = new JRadioButton(Values.TABLES[5]);
		JRadioButton buttonradio3 = new JRadioButton(Values.TABLES[10]);
		ButtonGroup group = new ButtonGroup();
		group.add(buttonradio1);
		group.add(buttonradio2);
		group.add(buttonradio3);



		buttonradio1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				buttonFind.setEnabled(true);
				itemRadio = Values.TABLES[1];
			}
		});

		buttonradio2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				buttonFind.setEnabled(true);
				itemRadio = Values.TABLES[5];
			}
		});
		buttonradio3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				buttonFind.setEnabled(true);
				itemRadio = Values.TABLES[10];
			}
		});

		wrapper.add(buttonradio1);
		wrapper.add(buttonradio2);
		wrapper.add(buttonradio3);
		itemPanel.add(wrapper);

		buttonFind.addActionListener(this);
		itemPanel.add(buttonFind, BorderLayout.SOUTH);
		container.add(itemPanel);

	}

	// choose items to buy
	private void BuyItemUI() {
		JPanel itemPanel = new JPanel();
		BorderLayout itemLayout = new BorderLayout();
		setLayout(itemPanel, itemLayout, "CHECK ITEM TO BUY");

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
		JCheckBox check1 = new JCheckBox(Values.ITEM[0]);
		JCheckBox check2 = new JCheckBox(Values.ITEM[1]);
		JCheckBox check3 = new JCheckBox(Values.ITEM[2]);
		JCheckBox check4 = new JCheckBox(Values.ITEM[3]);
		JCheckBox check5 = new JCheckBox(Values.ITEM[4]);
		JCheckBox check6 = new JCheckBox(Values.ITEM[5]);

		check1.addItemListener(this);
		check2.addItemListener(this);
		check3.addItemListener(this);
		check4.addItemListener(this);
		check5.addItemListener(this);
		check6.addItemListener(this);

		wrapper.add(check1);
		wrapper.add(check2);
		wrapper.add(check3);
		wrapper.add(check4);
		wrapper.add(check5);
		wrapper.add(check6);
		itemPanel.add(wrapper);

		buttonCompute.addActionListener(this);
		itemPanel.add(buttonCompute, BorderLayout.SOUTH);
		container.add(itemPanel);
	}

	private void quit() {
		buttonQuit.addActionListener(this);
		container.add(buttonQuit);
	}

	/***********************************************************************************
	 * This section is more about the logic of the UI instead of the UI itself
	 * Example: handle button click
	 ********************************************************************************************/

	// perform action on click or on select
	@Override
	public void actionPerformed(ActionEvent e) {
		// action listener for the JCombo box
		if (e.getSource() == list)
			performActionOnList();
		// when clicking on the button from the
		else if (e.getSource() == buttonAdd)
			performActionButtonAdd();
		else if (e.getSource() == listStatus)
			performActionOnListStatus();
		else if (e.getSource() == buttonStatus)
			performActionButtonStatus();
		else if (e.getSource() == buttonSave)
			performActionButtonSave();
		else if (e.getSource() == buttonCompute)
			performActionButtonCompute();
		else if (e.getSource() == this.buttonFind)
			performActionButtonFind();
		else if (e.getSource() == buttonQuit)
			performActionQuit();
	}

	// action to perform when choosing an element from the list of Tables to add
	// tuples
	private void performActionOnList() {
		item = list.getSelectedItem().toString();
		if (!item.equals(Values.TABLES[0])) {
			lTextField.enable(true);
			buttonAdd.setEnabled(true);
			String[] arrList = Values.formattedAttributesTables(item);
			lTextField.setText(Arrays.toString(arrList).replace("]", "").replace("[", " "));
		} else {
			lTextField.setText(null);
			lTextField.enable(false);
			buttonAdd.setEnabled(false);
		}
	}

	// action to perform when clicking on Execute button for adding a tuple
	private void performActionButtonAdd() {
		String[] arrAttr = Values.attributesTables(item);
		String query = "INSERT INTO " + item + " ( " + Arrays.toString(arrAttr).replace("]", "").replace("[", " ")
				+ ") VALUES (  " + lTextField.getText() + " );";
		DBImplementation.addTuple(query);
	}

	// action to perform when clicking on Execute button to display info about
	// user's order based on order status
	private void performActionButtonStatus() {
		String query = "SELECT *" + "  FROM (" + "SELECT * FROM \n("
				+ "SELECT c.order_status, c.totalprice, p.pid FROM CustOrder AS c, PaymentInfo AS p \n"
				+ " WHERE c.order_status = " + itemStatus + " AND c.numberoncc = p.numberOnCC) Temp) Temp2 \n"
				+ "INNER JOIN Customer ON Temp2.pid = Customer.pid;";
		textArea.setText(DBImplementation.executeQueryStatus(query));
	}

	// action to perform when choosing an element from the list of Status
	private void performActionOnListStatus() {
		itemStatus = listStatus.getSelectedItem().toString();
		if (!itemStatus.equals(Values.STATUS[0]))
			buttonStatus.setEnabled(true);
		else
			buttonStatus.setEnabled(false);
	}

	private void performActionButtonSave() {
		if (nameText.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "You did not provide your name for identification");
			return;
		} else if (!DBImplementation.isNameExist("'" + nameText.getText() + "'")) {
			JOptionPane.showMessageDialog(null,
					"Name " + nameText.getText() + " does not exist in table Customer from the database");
		} else
			continueModification("'" + nameText.getText() + "'");
	}

	// apply the modification in the database and reset the text view when the
	// update is successful
	private void continueModification(String name) {
		DBImplementation.executeModification(name, usernameText.getText(), phoneText.getText(), emailText.getText(),
				addressText.getText());
		nameText.setText(null);
		usernameText.setText(null);
		phoneText.setText(null);
		emailText.setText(null);
		addressText.setText(null);
	}

	// when clicking on button to compute the total price
	private void performActionButtonCompute() {
		if (listItem.isEmpty()) {
			textArea.setText(null);
			JOptionPane.showMessageDialog(null, " You have not selected any item");
			return;
		}
		String value = "";
		int n, i;
		for (i = 0, n = listItem.size(); i < n - 1; i++)
			value += "'" + listItem.get(i).getText() + "', ";
		value += "'" + listItem.get(n - 1).getText() + "'";

		textArea.setText(DBImplementation.computePrice(value));
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {// checkbox has been
														// selected
			listItem.add((JCheckBox) e.getSource());
		} else {
			listItem.remove(e.getItem());
		}
	}

	private void performActionButtonFind() {
		textArea.setText(DBImplementation.displayItemsSport(itemRadio));

	}

	// quit
	private void performActionQuit() {
		DBImplementation.closeDBAndQuit();
	}

}
