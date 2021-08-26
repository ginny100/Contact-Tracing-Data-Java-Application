package contactTracingGUIFrontEnd;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import contactTracingDataBackEnd.Collection;
import contactTracingDataBackEnd.Person;
import javax.swing.event.ChangeEvent;
import javax.swing.border.BevelBorder;

public class MainPanel extends JPanel 
{
	//------------//
	// Dimensions //
	//------------//
	private final int WIDTH = 1080, HEIGHT = 1650;
	
	//------------//
	// All Panels //
	//------------//
	private JPanel controlPanel;
	private JPanel viewPanel;
	private JPanel addPanel;
	private JPanel editPanel;
	private JPanel removePanel;
	
	//------------------//
	// Get the Data set //
	//------------------//
	private Collection myData;
	
	//----------------//
	// All Components //
	//----------------//
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private final ButtonGroup buttonGroup_4 = new ButtonGroup();
	private JTextArea textArea;
	private JTextField addFirstName;
	private JTextField addLastName;
	private JTextField addId;
	private JTextField addPhoneNumber;
	private JTextField addEmail;
	private JTextField editFirstName;
	private JTextField editLastName;
	private JTextField editPhoneNumber;
	private JTextField editEmail;
	private JRadioButton editInfected;
	private JRadioButton editUnknown;
	private JRadioButton editGood;
	private JCheckBox editInstructor;
	private JCheckBox editStudent;
	private JComboBox idComboBox_1;
	private JComboBox idComboBox_2;
	private JComboBox idComboBox_3;
	private JComboBox contactsComboBox_1;
	private JComboBox contactsComboBox_2;
	private JButton addSave;
	private JButton editSave;
	private JButton DELETE; 
	
	//-----------------------//
	// Patient's information //
	//-----------------------//
	private String id;
	private String firstName;
	private String lastName;
	private String status;
	private String role;
	private String number;
	private String email;
	private ArrayList<String> contacts;
	//private Person p;
	private Person contact;
	
	//---------------------//
	// Default Constructor //
	//---------------------//
	public MainPanel()
	{
		super(new BorderLayout());
	    
		myData = new Collection("./data.txt");
		System.out.println(myData);
		
		//--------------------------------------------//
	    // Set up the iterator and make a list of ids //
	  	//--------------------------------------------//
	    Iterator<String> iter = myData.getIterator();
    	ArrayList<String> ids = new ArrayList<String>();
    	while (iter.hasNext())
    	{
    		ids.add(iter.next());
    	}
		
	    //---------------------------//
		// Sets up the control panel //
		//---------------------------//
	    controlPanel = new JPanel();
	    add(controlPanel, BorderLayout.NORTH);
	    controlPanel.setPreferredSize (new Dimension(WIDTH, HEIGHT));
	    controlPanel.setBackground (Color.PINK);
	    controlPanel.setLayout(null);
	
	    //------------------------//
	    // Sets up the view panel //
	    //------------------------//
    	viewPanel = new JPanel();
    	add(viewPanel, BorderLayout.EAST);
    	
    	//---------------------------------//
	    // Sets up the view panel's button //
	    //---------------------------------//
	    JButton btnView = new JButton("View");
	    btnView.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	      	{
	    		controlPanel.setVisible(false);
	        	add(viewPanel, BorderLayout.EAST);
	        	viewPanel.setPreferredSize (new Dimension(WIDTH, HEIGHT));
	        	viewPanel.setBackground (Color.LIGHT_GRAY);
	        	viewPanel.setVisible(true);
	      	}
	    });
	    btnView.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    btnView.setBackground(Color.GRAY);
	    btnView.setBounds(30, 104, 200, 53);
	    
	    //-------------------------------------------//
	  	// Sets up the exit button of the view panel //
	  	//-------------------------------------------//
	    JButton btnExit_1 = new JButton("BACK");
    	btnExit_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	btnExit_1.addActionListener(new ActionListener() 
    	{
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		controlPanel.setVisible(true);
	      		viewPanel.setVisible(false);
	    	}
	    });
    	viewPanel.setLayout(new BorderLayout(0, 0));
    	viewPanel.add(btnExit_1, BorderLayout.SOUTH);
	    controlPanel.add(btnView);
	    
	    //-----------------------------------//
	    // Sets up the view panel's contents //
	    //-----------------------------------//
    	JTextArea txtrViewPatientsInformation = new JTextArea();
    	txtrViewPatientsInformation.setEditable(false);
    	txtrViewPatientsInformation.setLineWrap(true);
    	txtrViewPatientsInformation.setFont(new Font("Times New Roman", Font.BOLD, 40));
    	txtrViewPatientsInformation.setText("View a patient's information");
    	txtrViewPatientsInformation.setBounds(30, 443, 229, 149);
    	viewPanel.add(txtrViewPatientsInformation, BorderLayout.NORTH);
    	
    	JTextArea viewColumn = new JTextArea();
    	viewColumn.setFont(new Font("Monospaced", Font.PLAIN, 20));
    	viewColumn.setTabSize(20);
    	viewPanel.add(viewColumn, BorderLayout.CENTER);
    	viewColumn.setEditable(false);
    	viewColumn.setText("\r\nThe ID drop down list contains all the patients' IDs. Click on each ID to see the \r\npatient's information.");
	    
    	//-----------------------//
    	// Sets up the add panel //
    	//-----------------------//
	    addPanel = new JPanel();
	    add(addPanel, BorderLayout.EAST);
	    
	    //--------------------------------//
    	// Sets up the add panel's button //
    	//--------------------------------//
	    JButton btnAdd = new JButton("Add");
	    btnAdd.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	      	{
	    		controlPanel.setVisible(false);
	    		add(addPanel, BorderLayout.EAST);
	        	addPanel.setPreferredSize (new Dimension(WIDTH, HEIGHT));
	        	addPanel.setBackground (Color.YELLOW);
	      		addPanel.setVisible(true);
	      	}
	    });
	    btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    btnAdd.setBackground(Color.GRAY);
	    btnAdd.setBounds(358, 104, 200, 53);
    	
    	//------------------------------------------//
	  	// Sets up the exit button of the add panel //
	  	//------------------------------------------//
    	JButton btnExit_2 = new JButton("BACK");
    	btnExit_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	btnExit_2.addActionListener(new ActionListener() 
    	{
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		controlPanel.setVisible(true);
	      		viewPanel.setVisible(false);
	      		addPanel.setVisible(false);
	      		editPanel.setVisible(false);
	      		removePanel.setVisible(false);
	    	}
	    });
    	addPanel.setLayout(new BorderLayout(0, 0));
    	addPanel.add(btnExit_2, BorderLayout.SOUTH);
    	
    	//----------------------------------//
	    // Sets up the add panel's contents //
	    //----------------------------------//
    	JTextArea txtrAddPatientsInformation = new JTextArea();
    	txtrAddPatientsInformation.setLineWrap(true);
    	txtrAddPatientsInformation.setFont(new Font("Times New Roman", Font.BOLD, 40));
    	txtrAddPatientsInformation.setEditable(false);
    	txtrAddPatientsInformation.setText("Add a new patient");
    	txtrAddPatientsInformation.setBounds(30, 568, 5, 22);
    	addPanel.add(txtrAddPatientsInformation, BorderLayout.NORTH);
    	
    	JTextArea addColumn = new JTextArea();
    	addColumn.setTabSize(20);
    	addColumn.setFont(new Font("Monospaced", Font.PLAIN, 20));
    	addColumn.setEditable(false);
    	addPanel.add(addColumn, BorderLayout.CENTER);
    	addColumn.setText("\r\nThis column allows you to add new patient to the data set. Each time you enter new \r\ninformation in each of these following fields, be sure to hit \"Enter.\" If you want to add\r\nnew contacts to that new patient, select an ID from the Contacts box, then click \"ADD.\"\r\nYou can add as many contacts as you want. Finally, click \"SAVE,\" a new patient will be \r\nadded to the data set and will also show up in other ID drop down lists.");
    	
    	//--------------------------//
	  	// Sets up the Add ID field //
	  	//--------------------------//
    	addId = new JTextField();
    	addId.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			id = addId.getText();
    		}
    	});
    	addId.setBounds(358, 168, 200, 36);
    	GridBagConstraints gbc_addId = new GridBagConstraints();
    	gbc_addId.anchor = GridBagConstraints.SOUTHWEST;
    	gbc_addId.insets = new Insets(0, 0, 5, 0);
    	gbc_addId.gridx = 1;
    	gbc_addId.gridy = 1;
    	controlPanel.add(addId, gbc_addId);
    	addId.setColumns(10);
    	
    	//----------------------------------//
	  	// Sets up the Add First Name field //
	  	//----------------------------------//
    	addFirstName = new JTextField();
    	addFirstName.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			firstName = addFirstName.getText();
    		}
    	});
    	addFirstName.setBounds(358, 215, 200, 30);
    	GridBagConstraints gbc_addFirstName = new GridBagConstraints();
    	gbc_addFirstName.anchor = GridBagConstraints.WEST;
    	gbc_addFirstName.fill = GridBagConstraints.VERTICAL;
    	gbc_addFirstName.insets = new Insets(0, 0, 0, 5);
    	gbc_addFirstName.gridx = 0;
    	gbc_addFirstName.gridy = 2;
    	controlPanel.add(addFirstName, gbc_addFirstName);
    	addFirstName.setColumns(10);
    	
    	//---------------------------------//
	  	// Sets up the Add Last Name field //
	  	//---------------------------------//
    	addLastName = new JTextField();
    	addLastName.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			lastName = addLastName.getText();
    		}
    	});
    	addLastName.setBounds(358, 244, 200, 30);
    	GridBagConstraints gbc_addLastName = new GridBagConstraints();
    	gbc_addLastName.anchor = GridBagConstraints.WEST;
    	gbc_addLastName.fill = GridBagConstraints.VERTICAL;
    	gbc_addLastName.gridx = 1;
    	gbc_addLastName.gridy = 2;
    	controlPanel.add(addLastName, gbc_addLastName);
    	addLastName.setColumns(10);
    	controlPanel.add(btnAdd);
	
    	//------------------------//
    	// Sets up the edit panel //
    	//------------------------//
	    editPanel = new JPanel();
	    add(editPanel, BorderLayout.EAST);
	    
	    //---------------------------------//
    	// Sets up the edit panel's button //
    	//---------------------------------//
	    JButton btnEdit = new JButton("Edit");
	    btnEdit.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	      	{
	    		controlPanel.setVisible(false);
	    		add(editPanel, BorderLayout.EAST);
	        	editPanel.setPreferredSize (new Dimension(WIDTH, HEIGHT));
	        	editPanel.setBackground (Color.GREEN);
	      		editPanel.setVisible(true);
	      	}
	    });
	    btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    btnEdit.setBackground(Color.GRAY);
	    btnEdit.setBounds(723, 104, 200, 53);
	    
	    //-------------------------------------------//
	  	// Sets up the exit button of the edit panel //
	  	//-------------------------------------------//
    	JButton btnExit_3 = new JButton("BACK");
    	btnExit_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	btnExit_3.addActionListener(new ActionListener() 
    	{
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		controlPanel.setVisible(true);
	      		viewPanel.setVisible(false);
	      		addPanel.setVisible(false);
	      		editPanel.setVisible(false);
	      		removePanel.setVisible(false);
	    	}
	    });
    	editPanel.setLayout(new BorderLayout(0, 0));
    	editPanel.add(btnExit_3, BorderLayout.SOUTH);
    	controlPanel.add(btnEdit);
    	
    	//-----------------------------------//
	    // Sets up the edit panel's contents //
	    //-----------------------------------//
    	JTextArea txtrEditPatientsInformation = new JTextArea();
    	txtrEditPatientsInformation.setFont(new Font("Times New Roman", Font.BOLD, 40));
    	txtrEditPatientsInformation.setLineWrap(true);
    	txtrEditPatientsInformation.setEditable(false);
    	editPanel.add(txtrEditPatientsInformation, BorderLayout.NORTH);
    	txtrEditPatientsInformation.setText("Edit a patient's information");
    	
    	JTextArea editCoulumn = new JTextArea();
    	editCoulumn.setTabSize(20);
    	editCoulumn.setFont(new Font("Monospaced", Font.PLAIN, 20));
    	editCoulumn.setEditable(false);
    	editPanel.add(editCoulumn, BorderLayout.CENTER);
    	editCoulumn.setText("\r\nSelect an ID from the drop down box and all the information of that patient will be \r\ndisplayed except for the list of his/her contacts. Then you can edit all these fields. \r\nEach time you edit each of these fields, be sure to hit \"Enter.\" When adding new contacts \r\nto that patient, select an ID from the Contacts box, then click \"ADD.\" You can add as \r\nmany contacts as you want. Finally, click \"SAVE,\" that patient with modified information\r\nwill show up in other ID drop down lists and the data set.");
	
	    //--------------------------//
	    // Sets up the remove panel //
	    //--------------------------//
	    removePanel = new JPanel();
	    add(removePanel, BorderLayout.EAST);
    	
	    //-----------------------------------//
	    // Sets up the remove panel's button //
	    //-----------------------------------//
	    JButton btnRemove = new JButton("Remove");
	    btnRemove.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	      	{
	    		controlPanel.setVisible(false);
	    		add(removePanel, BorderLayout.EAST);
	    		removePanel.setPreferredSize (new Dimension(WIDTH, HEIGHT));
	    		removePanel.setBackground (Color.RED);
	    		removePanel.setVisible(true);
	      	}
	    });
	    btnRemove.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    btnRemove.setBackground(Color.GRAY);
	    btnRemove.setBounds(1047, 104, 200, 53);
	    
	    //---------------------------------------------//
	  	// Sets up the exit button of the remove panel //
	  	//---------------------------------------------//
    	JButton btnExit_4 = new JButton("BACK");
    	btnExit_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	btnExit_4.addActionListener(new ActionListener() 
    	{
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		controlPanel.setVisible(true);
	      		viewPanel.setVisible(false);
	      		addPanel.setVisible(false);
	      		editPanel.setVisible(false);
	      		removePanel.setVisible(false);
	    	}
	    });
    	removePanel.setLayout(new BorderLayout(0, 0));
    	removePanel.add(btnExit_4, BorderLayout.SOUTH);
    	
    	//------------------------------------//
	  	// Sets up the remove panel's content //
	  	//------------------------------------//
    	JTextArea txtrRemovePatientsInformation = new JTextArea();
    	txtrRemovePatientsInformation.setLineWrap(true);
    	txtrRemovePatientsInformation.setFont(new Font("Times New Roman", Font.BOLD, 40));
    	txtrRemovePatientsInformation.setEditable(false);
    	removePanel.add(txtrRemovePatientsInformation, BorderLayout.NORTH);
    	txtrRemovePatientsInformation.setText("Remove an existing patient");
    	
    	JTextArea removeColumn = new JTextArea();
    	removeColumn.setFont(new Font("Monospaced", Font.PLAIN, 20));
    	removeColumn.setEditable(false);
    	removeColumn.setTabSize(20);
    	removePanel.add(removeColumn, BorderLayout.CENTER);
    	removeColumn.setText("\r\nChoose an ID from the drop down list, and click on \"DELETE,\" the patient who has that \r\nID will be removed from the data set and other ID drop down lists.");
    	controlPanel.add(btnRemove);
    	
    	//---------------------------------//
	  	// Sets up the View drop down list //
	  	//---------------------------------//
    	idComboBox_1 = new JComboBox();
    	idComboBox_1.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    	   	{
    			String id = idComboBox_1.getSelectedItem().toString();
    	   		Person p = myData.findPerson(id);
    			//p = myData.findPerson(id);
    	   		String i = "ID: " + p.getId() + "\n";
    	   		i += "First Name: " + p.getFirstName() + "\n";
    	   		i += "Last Name: " + p.getLastName() + "\n";
    	   		i += "Status: " + p.getStatus() + "\n";
    	   		i += "Role: " + p.getRole() + "\n";
    	   		i += "Number: " + p.getNumber() + "\n";
    	   		i += "Email: " + p.getEmail() + "\n";
    	   		i += "Contacts: " + "\n" + p.getContacts() + "\n";
    	   		textArea.setText(i);
    	   	}
    	});
    	idComboBox_1.setModel(new DefaultComboBoxModel(ids.toArray()));
    	idComboBox_1.setBounds(30, 168, 200, 36);
    	controlPanel.add(idComboBox_1);
    	
    	//------------------------------//
	  	// Sets up the Add Status field //
	  	//------------------------------//
    	
    	//-----------Infected-----------//
    	JRadioButton addInfected = new JRadioButton("infected");
    	addInfected.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			status = "infected";
    		}
    	});
    	addInfected.setSelected(true);
    	buttonGroup_1.add(addInfected);
    	addInfected.setBounds(358, 281, 200, 30);
    	controlPanel.add(addInfected);
    	//-----------Unknown-----------//
    	JRadioButton addUnknown = new JRadioButton("unknown");
    	addUnknown.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			status = "unknown";
    		}
    	});
    	addUnknown.setSelected(true);
    	buttonGroup_1.add(addUnknown);
    	addUnknown.setBounds(358, 310, 200, 30);
    	controlPanel.add(addUnknown);
    	//-------------Good-------------//
    	JRadioButton addGood = new JRadioButton("good");
    	addGood.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			status = "good";
    		}
    	});
    	addGood.setSelected(true);
    	buttonGroup_1.add(addGood);
    	addGood.setBounds(358, 339, 200, 30);
    	controlPanel.add(addGood);
    	
    	//-----------------------------------//
	  	// Sets up the control panel's title //
	  	//-----------------------------------//
    	JLabel lblContactTracingData = new JLabel("   Contact Tracing Data");
    	lblContactTracingData.setFont(new Font(".VnStamp", Font.BOLD, 25));
    	lblContactTracingData.setBounds(358, 11, 565, 61);
    	controlPanel.add(lblContactTracingData);
    	
    	//------------------------------------//
	  	// Sets up the Add Phone Number field //
	  	//------------------------------------//
    	addPhoneNumber = new JTextField();
    	addPhoneNumber.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			number = addPhoneNumber.getText();
    		}
    	});
    	addPhoneNumber.setBounds(358, 443, 200, 31);
    	controlPanel.add(addPhoneNumber);
    	addPhoneNumber.setColumns(10);
    	
    	//-----------------------------//
	  	// Sets up the Add Email field //
	  	//-----------------------------//
    	addEmail = new JTextField();
    	addEmail.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			email = addEmail.getText();
    		}
    	});
    	addEmail.setBounds(358, 485, 200, 31);
    	controlPanel.add(addEmail);
    	addEmail.setColumns(10);
    	
    	//---------------------------------//
	  	// Sets up the Edit drop down list //
	  	//---------------------------------//
    	idComboBox_2 = new JComboBox();
    	idComboBox_2.addActionListener(new ActionListener() 
	   	{
	   		public void actionPerformed(ActionEvent e) 
	   		{
	   			String id = idComboBox_2.getSelectedItem().toString();
	   			//p = myData.findPerson(id);
	   			Person p = myData.findPerson(id);
	   			
	   			editFirstName.setText(p.getFirstName().toString());
	   			editLastName.setText(p.getLastName().toString());
	   			editPhoneNumber.setText(p.getNumber().toString());
	   			editEmail.setText(p.getEmail().toString());
	   			
	   			if (p.getStatus().equals("infected"))
	   			{
	   				editInfected.setSelected(true);
	   			}
	   			else if (p.getStatus().equals("unknown"))
	   			{
	   				editUnknown.setSelected(true);
	   			}
	   			else
	   			{
	   				editGood.setSelected(true);
	   			}
	   			
	   			if (p.getRole().equals("instructor"))
	   			{
	   				editInstructor.setSelected(true);
	   			}
	   			else
	   			{
	   				editStudent.setSelected(true);
	   			}
	   			
	   			Iterator<String> iter = myData.getIterator();
    		    ArrayList<String> ids = new ArrayList<String>();
    		    while (iter.hasNext()) 
    		    {
    		    	ids.add(iter.next());
    		    }
    		    contactsComboBox_2.setModel(new DefaultComboBoxModel(ids.toArray()));
	   		}
	   	});
    	idComboBox_2.setModel(new DefaultComboBoxModel(ids.toArray()));
    	idComboBox_2.setBounds(723, 168, 200, 36);
    	controlPanel.add(idComboBox_2);
    	
    	contactsComboBox_2 = new JComboBox();
    	contactsComboBox_2.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			String id = contactsComboBox_2.getSelectedItem().toString();
                contact = myData.findPerson(id);
    		}
    	});
    	contactsComboBox_2.setModel(new DefaultComboBoxModel(ids.toArray()));
    	contactsComboBox_2.setBounds(723, 524, 200, 34);
    	controlPanel.add(contactsComboBox_2);
    	
    	contactsComboBox_1 = new JComboBox();
    	contactsComboBox_1.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			String id = contactsComboBox_1.getSelectedItem().toString();
                contact = myData.findPerson(id);
                //contact_id = contact.getId();
    		}
    	});
    	contactsComboBox_1.setModel(new DefaultComboBoxModel(ids.toArray()));
    	contactsComboBox_1.setBounds(358, 527, 200, 31);
    	controlPanel.add(contactsComboBox_1);
    	
    	//------------------------------------------------//
	  	// Patient's information field in the View column //
	  	//------------------------------------------------//
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBounds(30, 229, 200, 178);
    	controlPanel.add(scrollPane);
    	
    	textArea = new JTextArea();
    	textArea.setEditable(false);
    	scrollPane.setViewportView(textArea);
    	textArea.setBackground(Color.WHITE);
    	
    	//----------------------------//
	  	// Label of the Add ID field //
	  	//----------------------------//
    	JLabel lblId = new JLabel("ID");
    	lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId.setBounds(262, 166, 96, 36);
    	controlPanel.add(lblId);
    	
    	//-----------------------------------//
	  	// Label of the Add First Name field //
	  	//-----------------------------------//
    	JLabel lblFirstName = new JLabel("First Name");
    	lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblFirstName.setBounds(262, 215, 96, 30);
    	controlPanel.add(lblFirstName);
    	
    	//----------------------------------//
	  	// Label of the Add Last Name field //
	  	//----------------------------------//
    	JLabel lblLastName = new JLabel("Last Name");
    	lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblLastName.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblLastName.setBounds(262, 244, 96, 30);
    	controlPanel.add(lblLastName);
    	
    	//-------------------------------//
	  	// Label of the Add Status field //
	  	//-------------------------------//
    	JLabel lblId_2_1 = new JLabel("Status");
    	lblId_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2_1.setBounds(262, 281, 96, 30);
    	controlPanel.add(lblId_2_1);
    	
    	//-----------------------------//
	  	// Label of the Add Role field //
	  	//-----------------------------//
    	JLabel lblId_2_2 = new JLabel("Role");
    	lblId_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2_2.setBounds(262, 377, 96, 30);
    	controlPanel.add(lblId_2_2);
    	
    	//-------------------------------------//
	  	// Label of the Add Phone Number field //
	  	//-------------------------------------//
    	JLabel lblId_2_3 = new JLabel("Phone");
    	lblId_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2_3.setBounds(262, 444, 96, 30);
    	controlPanel.add(lblId_2_3);
    	
    	//-------------------------------//
	  	// Label of the Add Email field //
	  	//-------------------------------//
    	JLabel lblId_2_4 = new JLabel("Email");
    	lblId_2_4.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2_4.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2_4.setBounds(262, 486, 96, 30);
    	controlPanel.add(lblId_2_4);
    	
    	//---------------------------------//
	  	// Label of the Add Contacts field //
	  	//---------------------------------//
    	JLabel lblId_2_5 = new JLabel("Contacts");
    	lblId_2_5.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2_5.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2_5.setBounds(262, 528, 96, 30);
    	controlPanel.add(lblId_2_5);
    	
    	//----------------------------//
	  	// Label of the Edit ID field //
	  	//----------------------------//
    	JLabel lblId_1 = new JLabel("ID");
    	lblId_1.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_1.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_1.setBounds(625, 166, 96, 36);
    	controlPanel.add(lblId_1);
    	
    	//----------------------------------//
	  	// Label of the Edit Contacts field //
	  	//----------------------------------//
    	JLabel lblId_2_5_1 = new JLabel("Contacts");
    	lblId_2_5_1.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2_5_1.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2_5_1.setBounds(625, 524, 96, 30);
    	controlPanel.add(lblId_2_5_1);
    	
    	//-------------------------------//
	  	// Remove column's drop down box //
	  	//-------------------------------//
    	idComboBox_3 = new JComboBox();
    	idComboBox_3.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			String id = idComboBox_3.getSelectedItem().toString();
	   			Person p = myData.findPerson(id);
	   			//p = myData.findPerson(id);
    		}
    	});
    	idComboBox_3.setModel(new DefaultComboBoxModel(ids.toArray()));
    	idComboBox_3.setBounds(1047, 168, 200, 36);
    	controlPanel.add(idComboBox_3);
    	
    	//----------------------------//
	  	// Sets up the Add Role field //
	  	//----------------------------//
    	
    	//---------Student---------//
    	JCheckBox addStudent = new JCheckBox("student");
    	addStudent.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			role = "student";
    		}
    	});
    	buttonGroup_2.add(addStudent);
    	addStudent.setBounds(358, 406, 200, 30);
    	controlPanel.add(addStudent);
    	//---------Instructor---------//
    	JCheckBox addInstructor = new JCheckBox("instructor");
    	addInstructor.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			role = "instructor";
    		}
    	});
    	buttonGroup_2.add(addInstructor);
    	addInstructor.setBounds(358, 379, 200, 30);
    	controlPanel.add(addInstructor);
    	
    	//------------------------------------//
	  	// Label of the Edit First Name field //
	  	//------------------------------------//
    	JLabel lblFirstName_1 = new JLabel("First Name");
    	lblFirstName_1.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblFirstName_1.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblFirstName_1.setBounds(625, 215, 96, 30);
    	controlPanel.add(lblFirstName_1);
    	
    	//-----------------------------------//
	  	// Label of the Edit Last Name field //
	  	//-----------------------------------//
    	JLabel lblLastName_1 = new JLabel("Last Name");
    	lblLastName_1.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblLastName_1.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblLastName_1.setBounds(625, 244, 96, 30);
    	controlPanel.add(lblLastName_1);
    	
    	//--------------------------------//
	  	// Label of the Edit Status field //
	  	//--------------------------------//
    	JLabel lblId_2_1_1 = new JLabel("Status");
    	lblId_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2_1_1.setBounds(625, 281, 96, 30);
    	controlPanel.add(lblId_2_1_1);
    	
    	//------------------------------//
	  	// Label of the Edit Role field //
	  	//------------------------------//
    	JLabel lblId_2_2_1 = new JLabel("Role");
    	lblId_2_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2_2_1.setBounds(625, 377, 96, 30);
    	controlPanel.add(lblId_2_2_1);
    	
    	//--------------------------------------//
	  	// Label of the Edit Phone Number field //
	  	//--------------------------------------//
    	JLabel lblId_2_3_1 = new JLabel("Phone");
    	lblId_2_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2_3_1.setBounds(625, 443, 96, 30);
    	controlPanel.add(lblId_2_3_1);
    	
    	//-------------------------------//
	  	// Label of the Edit Email field //
	  	//-------------------------------//
    	JLabel lblId_2_4_1 = new JLabel("Email");
    	lblId_2_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2_4_1.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2_4_1.setBounds(625, 486, 96, 30);
    	controlPanel.add(lblId_2_4_1);
    	
    	//-----------------------------------//
	  	// Sets up the Edit First Name field //
	  	//-----------------------------------//
    	editFirstName = new JTextField();
    	editFirstName.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			firstName = editFirstName.getText();
    		}
    	});
    	editFirstName.setColumns(10);
    	editFirstName.setBounds(723, 215, 200, 30);
    	controlPanel.add(editFirstName);
    	
    	//----------------------------------//
	  	// Sets up the Edit Last Name field //
	  	//----------------------------------//
    	editLastName = new JTextField();
    	editLastName.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			lastName = editLastName.getText();
    		}
    	});
    	editLastName.setColumns(10);
    	editLastName.setBounds(723, 244, 200, 30);
    	controlPanel.add(editLastName);
    	
    	//---------------------------------//
	  	// "SAVE" button of the Add column //
	  	//---------------------------------//
    	addSave = new JButton("SAVE");
    	addSave.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			Person p = new Person(id, firstName, lastName, status, role, number, email);
    			//p = new Person(id, firstName, lastName, status, role, number, email);
    			myData.add(p);
    			
    			for (String obj: contacts) 
    			{
    				Person p1 = myData.findPerson(obj);
    				myData.addContact(p, p1);
    			}
    			
    			myData.addContact(p, contact);
    			Iterator<String> iter = myData.getIterator();
    	    	ArrayList<String> ids = new ArrayList<String>();
    	    	while (iter.hasNext())
    	    	{
    	    		ids.add(iter.next());
    	    	}
    	    	idComboBox_1.setModel(new DefaultComboBoxModel(ids.toArray()));
    	    	idComboBox_2.setModel(new DefaultComboBoxModel(ids.toArray()));//
    	    	idComboBox_3.setModel(new DefaultComboBoxModel(ids.toArray()));//
    			myData.writeFile();
    		}
    	});
    	addSave.setBounds(358, 569, 200, 23);
    	controlPanel.add(addSave);
    	
    	//-------------------------------//
	  	// Sets up the Edit Status field //
	  	//-------------------------------//
    	
    	//-----------Infected-----------//
    	editInfected = new JRadioButton("infected");
    	editInfected.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			status = "infected";
    		}
    	});
    	buttonGroup_3.add(editInfected);
    	editInfected.setSelected(true);
    	editInfected.setBounds(723, 281, 200, 30);
    	controlPanel.add(editInfected);
    	//-----------Unknown-----------//
    	editUnknown = new JRadioButton("unknown");
    	editUnknown.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			status = "unknown";
    		}
    	});
    	buttonGroup_3.add(editUnknown);
    	editUnknown.setSelected(true);
    	editUnknown.setBounds(723, 310, 200, 30);
    	controlPanel.add(editUnknown);
    	//-----------Good-----------//
    	editGood = new JRadioButton("good");
    	editGood.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			status = "good";
    		}
    	});
    	buttonGroup_3.add(editGood);
    	editGood.setSelected(true);
    	editGood.setBounds(723, 339, 200, 30);
    	controlPanel.add(editGood);
    	
    	//-----------------------------//
	  	// Sets up the Edit Role field //
	  	//-----------------------------//
    	
    	//---------Instructor---------//
    	editInstructor = new JCheckBox("instructor");
    	editInstructor.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			role = "instructor";
    		}
    	});
    	buttonGroup_4.add(editInstructor);
    	editInstructor.setBounds(723, 383, 200, 30);
    	controlPanel.add(editInstructor);
    	//---------Student---------//
    	editStudent = new JCheckBox("student");
    	editStudent.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			role = "student";
    		}
    	});
    	buttonGroup_4.add(editStudent);
    	editStudent.setBounds(723, 410, 200, 30);
    	controlPanel.add(editStudent);
    	
    	editPhoneNumber = new JTextField();
    	editPhoneNumber.setColumns(10);
    	editPhoneNumber.setBounds(723, 448, 200, 31);
    	controlPanel.add(editPhoneNumber);
    	
    	editEmail = new JTextField();
    	editEmail.setColumns(10);
    	editEmail.setBounds(723, 485, 200, 31);
    	controlPanel.add(editEmail);
    	
    	//--------------------------------------------//
	  	// Label of the Remove column's drop down box //
	  	//--------------------------------------------//
    	JLabel lblId_1_1 = new JLabel("ID");
    	lblId_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_1_1.setBounds(951, 168, 96, 36);
    	controlPanel.add(lblId_1_1);
    	
    	//----------------------------------//
	  	// "SAVE" button of the Edit column //
	  	//----------------------------------//
    	editSave = new JButton("SAVE");
    	editSave.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			//Person p = new Person();//
    			//p = myData.findPerson(idComboBox_2.getSelectedItem().toString());
    			Person p = myData.findPerson(idComboBox_2.getSelectedItem().toString());
    			
    			//-----------------------------------//
    		  	// Preload the patient's information //
    		  	//-----------------------------------//
    			p.setFirstName(editFirstName.getText());
    			p.setLastName(editLastName.getText());
    			p.setNumber(editPhoneNumber.getText());
    			p.setEmail(editEmail.getText());
    			
    			if (editInfected.isSelected())
    			{
    				p.setStatus("infected");
    			}
    			else if (editUnknown.isSelected())
    			{
    				p.setStatus("unknown");
    			}
    			else
    			{
    				p.setStatus("good");
    			}
    			
    			if (editInstructor.isSelected())
    			{
    				p.setRole("instructor");
    			}
    			else
    			{
    				p.setRole("student");
    			}
    			
    			for (String obj: contacts)
    			{
    				Person p1 = myData.findPerson(obj);
    				myData.addContact(p, p1);
    			}
    			
    			myData.addContact(p, contact);
    			Iterator<String> iter = myData.getIterator();
    			ArrayList<String> ids = new ArrayList<String>();
    			
    			while (iter.hasNext())
    			{
    				ids.add(iter.next());
    			}
    			idComboBox_1.setModel(new DefaultComboBoxModel(ids.toArray()));
    			idComboBox_2.setModel(new DefaultComboBoxModel(ids.toArray()));
    			idComboBox_3.setModel(new DefaultComboBoxModel(ids.toArray()));
    			myData.writeFile();
    		}
    	});
    	editSave.setBounds(723, 569, 200, 23);
    	controlPanel.add(editSave);
    	
    	//------------------------------------------//
	  	// Label of the View column's drop down box //
	  	//------------------------------------------//
    	JLabel lblId_2 = new JLabel("ID");
    	lblId_2.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblId_2.setFont(new Font("Tahoma", Font.BOLD, 15));
    	lblId_2.setBounds(5, 168, 25, 36);
    	controlPanel.add(lblId_2);
    	
    	//--------------------------------------//
	  	// "DELETE" button of the Remove column //
	  	//--------------------------------------//
    	DELETE = new JButton("DELETE");
    	DELETE.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			String id = idComboBox_3.getSelectedItem().toString();
	   			Person p = myData.findPerson(id);
    			//p = myData.findPerson(id);
	   			myData.remove(id);
	   			ids.remove(id);//
	   			//myData.writeFile();
	   			
	   			idComboBox_1.setModel(new DefaultComboBoxModel(ids.toArray()));
	   	    	idComboBox_2.setModel(new DefaultComboBoxModel(ids.toArray()));
	   	    	idComboBox_3.setModel(new DefaultComboBoxModel(ids.toArray()));
	   	    	
	   	    	//myData.remove(id);
	   			myData.writeFile();
    		}
    	});
    	/*idComboBox_1.setModel(new DefaultComboBoxModel(ids.toArray()));
    	idComboBox_2.setModel(new DefaultComboBoxModel(ids.toArray()));
    	idComboBox_3.setModel(new DefaultComboBoxModel(ids.toArray()));*/
    	DELETE.setBounds(1047, 215, 200, 23);
    	controlPanel.add(DELETE);
    	
    	//--------------------------------//
	  	// "ADD" button of the Add column //
	  	//--------------------------------//
    	JButton btnAdd_1 = new JButton("ADD");
    	contacts = new ArrayList<String>();
    	btnAdd_1.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			contacts.add(contactsComboBox_1.getSelectedItem().toString());
    		}
    	});
    	btnAdd_1.setBounds(561, 530, 89, 23);
    	controlPanel.add(btnAdd_1);
    	
    	//--------------------------------//
	  	// "ADD" button of the Edit column //
	  	//--------------------------------//
    	JButton btnAdd_2 = new JButton("ADD");
    	contacts = new ArrayList<String>();
    	btnAdd_2.addActionListener(new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e) 
    		{
    			contacts.add(contactsComboBox_2.getSelectedItem().toString());
    		}
    	});
    	btnAdd_2.setBounds(925, 530, 89, 23);
    	controlPanel.add(btnAdd_2);
	 }
}