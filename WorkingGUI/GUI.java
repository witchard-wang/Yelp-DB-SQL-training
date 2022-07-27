import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Map;

import java.io.PrintWriter;

import java.sql.*;
import java.sql.Connection;

import java.lang.Math;

public class GUI implements ActionListener {
	// is the window
	private JFrame frame;
	// how things fit in the window
	private JPanel panel;

	// declare variables to be put inside panel

	// bottom half
	private JTextArea output;
	private JButton queryButton;
	private JScrollPane scrollOutput, scrollFrame;

	// "I want to find" Section
	private JLabel label1;
	private JRadioButton rbBusinessN, rbState, rbCity, rbRating, rbStreet;
	private ArrayList<JRadioButton> attributes;
	private ButtonGroup groupA;

	// "From the list of" Section
	private JLabel label2;
	private JRadioButton rbRes, rbSer, rbRet, rbHos, rbEnt;
	private ArrayList<JRadioButton> tables;

	// "Where" Section
	private JLabel label3, label4, label5;
	private JComboBox cbRating1, cbRating2, cbState;

	// "NEW QUERIES" Section
	private JTextField q1res1, q1res2, q1city1, q1city2, q4city, q5res, q5city;
	private JRadioButton rbQ1, rbQ3, rbQ4, rbQ5, rbNA;
	private ButtonGroup groupB;
	private JComboBox q1state1, q1state2, q3state, q5state, q5ratinga, q5ratingb;
	private JLabel lbq1city1, lbq1state1, lbq1res1, lbq1res2, lbq1city2, lbq1state2, lbq3state, lbq4city, lbq5res,
			lbq5city, lbq5state, lbq5rating;
	private JLabel lbQuestion1, lbQuestion3a, lbQuestion3b, lbQuestion4a, lbQuestion4b, lbQuestion5a, lbQuestion5b;

	// Declare variables for JDBC Connection
	private DBConnect conn;

	/**
	 * GUI Constructor it makes the frame, panels, and buttons
	 */
	public GUI() {
		// set up connection
		conn = new DBConnect();

		// initialize the windows
		panel = new JPanel();
		frame = new JFrame();
		frame.setTitle("The Boys' Business Search");

		panel.setLayout(null);

		// set the variables inside the panel and add it inside

		// "I want to find" Section
		label1 = new JLabel("I want to find:");
		label1.setBounds(10, 20, 80, 25);
		panel.add(label1);

		rbBusinessN = new JRadioButton("Business Names");
		rbBusinessN.setBounds(96, 21, 130, 23);
		panel.add(rbBusinessN);

		rbRating = new JRadioButton("Rating");
		rbRating.setBounds(228, 21, 67, 23);
		panel.add(rbRating);

		rbCity = new JRadioButton("City");
		rbCity.setBounds(297, 21, 61, 23);
		panel.add(rbCity);

		rbState = new JRadioButton("State");
		rbState.setBounds(360, 21, 76, 23);
		panel.add(rbState);

		rbStreet = new JRadioButton("Street");
		rbStreet.setBounds(438, 21, 80, 23);
		panel.add(rbStreet);

		attributes = new ArrayList<JRadioButton>();
		attributes.add(rbBusinessN);
		attributes.add(rbState);
		attributes.add(rbCity);
		attributes.add(rbRating);
		attributes.add(rbStreet);

		// "From the list of" Section
		label2 = new JLabel("From the list of:");
		label2.setBounds(10, 60, 80, 25);
		panel.add(label2);

		rbRes = new JRadioButton("Restaurant");
		rbRes.setBounds(96, 61, 99, 23);
		panel.add(rbRes);

		rbSer = new JRadioButton("Service");
		rbSer.setBounds(197, 61, 85, 23);
		panel.add(rbSer);

		rbRet = new JRadioButton("Retail");
		rbRet.setBounds(284, 61, 85, 23);
		panel.add(rbRet);

		rbHos = new JRadioButton("Hospitality");
		rbHos.setBounds(371, 61, 85, 23);
		panel.add(rbHos);

		rbEnt = new JRadioButton("Entertainment");
		rbEnt.setBounds(458, 61, 124, 23);
		panel.add(rbEnt);

		tables = new ArrayList<JRadioButton>();
		tables.add(rbRes);
		tables.add(rbSer);
		tables.add(rbRet);
		tables.add(rbHos);
		tables.add(rbEnt);

		groupA = new ButtonGroup();
		groupA.add(rbRes);
		groupA.add(rbSer);
		groupA.add(rbRet);
		groupA.add(rbHos);
		groupA.add(rbEnt);

		// "Where" Section
		label3 = new JLabel("Where:");
		label3.setBounds(10, 101, 80, 25);
		panel.add(label3);

		label4 = new JLabel("Rating");
		label4.setBounds(96, 101, 80, 25);
		panel.add(label4);

		cbRating1 = new JComboBox();
		cbRating1.setModel(new DefaultComboBoxModel(new String[] { "N/A", "=", ">", ">=", "<", "<=" }));
		cbRating1.setMaximumRowCount(6);
		cbRating1.setBounds(175, 102, 51, 22);
		panel.add(cbRating1);

		cbRating2 = new JComboBox();
		cbRating2.setModel(
				new DefaultComboBoxModel(new String[] { "N/A", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5" }));
		cbRating2.setMaximumRowCount(10);
		cbRating2.setBounds(231, 102, 51, 22);
		panel.add(cbRating2);

		label5 = new JLabel("State/Province      = ");
		label5.setBounds(96, 137, 130, 25);
		panel.add(label5);

		cbState = new JComboBox();
		cbState.setModel(new DefaultComboBoxModel(new String[] { "N/A", "AB", "AK", "AL", "AR", "AZ", "BC", "CA", "CO",
				"CT", "DOW", "DUR", "FL", "GA", "HI", "HPL", "IL", "MB", "MI", "MO", "NC", "NE", "NV", "NY", "OH", "ON",
				"OR", "PA", "QC", "SC", "TX", "UT", "VA", "VT", "WA", "WI", "XWY", "YT" }));
		cbState.setMaximumRowCount(38);
		cbState.setBounds(218, 138, 51, 22);
		panel.add(cbState);

		// bottom half with textboxes and button
		queryButton = new JButton("Search");
		queryButton.setBounds(600, 181, 80, 25);
		queryButton.addActionListener(this);
		panel.add(queryButton);

		output = new JTextArea(" Results Printed Here\n -----------------------------");
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		output.setOpaque(true);
		output.setBackground(Color.WHITE);
		Border outputBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
		output.setBorder(outputBorder);

		// set up scroll for output
		scrollOutput = new JScrollPane(output);
		scrollOutput.setBounds(100, 180, 500, 200);
		panel.add(scrollOutput);

		// NEW Queries Phase 4
		// --------------------------------------------------------------------------------------------------------------------
		// Question 1
		rbQ1 = new JRadioButton("Question 1");
		rbQ1.setBounds(10, 433, 109, 23);
		panel.add(rbQ1);

		lbQuestion1 = new JLabel(
				"Given 2 restaurants, find the shortest chain of patrons that gave a review of 3 stars (of 5) or better.");
		lbQuestion1.setBounds(149, 437, 560, 14);
		panel.add(lbQuestion1);

		lbq1res1 = new JLabel("Restaurant 1");
		lbq1res1.setBounds(149, 465, 77, 14);
		panel.add(lbq1res1);

		lbq1city1 = new JLabel("City 1");
		lbq1city1.setBounds(149, 492, 46, 14);
		panel.add(lbq1city1);

		lbq1state1 = new JLabel("State 1");
		lbq1state1.setBounds(149, 524, 46, 14);
		panel.add(lbq1state1);

		lbq1res2 = new JLabel("Restaurant 2");
		lbq1res2.setBounds(419, 465, 75, 14);
		panel.add(lbq1res2);

		lbq1city2 = new JLabel("City 2");
		lbq1city2.setBounds(419, 492, 46, 14);
		panel.add(lbq1city2);

		lbq1state2 = new JLabel("State 2");
		lbq1state2.setBounds(419, 524, 46, 14);
		panel.add(lbq1state2);

		q1res1 = new JTextField();
		q1res1.setBounds(245, 462, 164, 20);
		panel.add(q1res1);
		q1res1.setColumns(10);

		q1res2 = new JTextField();
		q1res2.setColumns(10);
		q1res2.setBounds(516, 462, 164, 20);
		panel.add(q1res2);

		q1city1 = new JTextField();
		q1city1.setColumns(10);
		q1city1.setBounds(245, 489, 164, 20);
		panel.add(q1city1);

		q1city2 = new JTextField();
		q1city2.setColumns(10);
		q1city2.setBounds(516, 489, 164, 20);
		panel.add(q1city2);

		q1state2 = new JComboBox();
		q1state2.setModel(new DefaultComboBoxModel(new String[] { "N/A", "AB", "AK", "AL", "AR", "AZ", "BC", "CA", "CO",
				"CT", "DOW", "DUR", "FL", "GA", "HI", "HPL", "IL", "MB", "MI", "MO", "NC", "NE", "NV", "NY", "OH", "ON",
				"OR", "PA", "QC", "SC", "TX", "UT", "VA", "VT", "WA", "WI", "XWY", "YT" }));
		q1state2.setMaximumRowCount(38);
		q1state2.setBounds(516, 520, 51, 22);
		panel.add(q1state2);

		q1state1 = new JComboBox();
		q1state1.setModel(new DefaultComboBoxModel(new String[] { "N/A", "AB", "AK", "AL", "AR", "AZ", "BC", "CA", "CO",
				"CT", "DOW", "DUR", "FL", "GA", "HI", "HPL", "IL", "MB", "MI", "MO", "NC", "NE", "NV", "NY", "OH", "ON",
				"OR", "PA", "QC", "SC", "TX", "UT", "VA", "VT", "WA", "WI", "XWY", "YT" }));
		q1state1.setMaximumRowCount(38);
		q1state1.setBounds(245, 520, 51, 22);
		panel.add(q1state1);

		// Question 3
		rbQ3 = new JRadioButton("Question 3");
		rbQ3.setBounds(10, 568, 109, 23);
		panel.add(rbQ3);

		lbQuestion3a = new JLabel(
				"Given a US state, find the 5 restaurant franchises (restaurants with multiple locations)\r\n");
		lbQuestion3a.setBounds(149, 572, 549, 14);
		panel.add(lbQuestion3a);

		lbQuestion3b = new JLabel("that have at least an average rating of 3.5 with the furthest location spread.");
		lbQuestion3b.setBounds(149, 583, 531, 14);
		panel.add(lbQuestion3b);

		lbq3state = new JLabel("State");
		lbq3state.setBounds(149, 612, 46, 14);
		panel.add(lbq3state);

		q3state = new JComboBox();
		q3state.setModel(new DefaultComboBoxModel(new String[] { "N/A", "AB", "AK", "AL", "AR", "AZ", "BC", "CA", "CO",
				"CT", "DOW", "DUR", "FL", "GA", "HI", "HPL", "IL", "MB", "MI", "MO", "NC", "NE", "NV", "NY", "OH", "ON",
				"OR", "PA", "QC", "SC", "TX", "UT", "VA", "VT", "WA", "WI", "XWY", "YT" }));
		q3state.setMaximumRowCount(28);
		q3state.setBounds(197, 608, 51, 22);
		panel.add(q3state);

		// Question 4
		rbQ4 = new JRadioButton("Question 4");
		rbQ4.setBounds(10, 661, 109, 23);
		panel.add(rbQ4);

		lbQuestion4a = new JLabel(
				"Find the best local restaurant, no franchises. Given a city, find the non-franchise restaurant");
		lbQuestion4a.setBounds(149, 665, 600, 14);
		panel.add(lbQuestion4a);

		lbQuestion4b = new JLabel("that receives the most tips and display restaurant and tips.");
		lbQuestion4b.setBounds(149, 677, 340, 14);
		panel.add(lbQuestion4b);

		lbq4city = new JLabel("City");
		lbq4city.setBounds(149, 702, 46, 14);
		panel.add(lbq4city);

		q4city = new JTextField();
		q4city.setColumns(10);
		q4city.setBounds(197, 699, 212, 20);
		panel.add(q4city);

		// Question 5
		rbQ5 = new JRadioButton("Question 5");
		rbQ5.setBounds(10, 751, 109, 23);
		panel.add(rbQ5);

		lbQuestion5a = new JLabel(
				"Find the nearest entertainment above a certain number of stars, given the restaurant");
		lbQuestion5a.setBounds(149, 751, 500, 14);
		panel.add(lbQuestion5a);

		lbQuestion5b = new JLabel("you are currently in");
		lbQuestion5b.setBounds(149, 766, 209, 14);
		panel.add(lbQuestion5b);

		lbq5res = new JLabel("Restaurant");
		lbq5res.setBounds(149, 791, 85, 14);
		panel.add(lbq5res);

		lbq5city = new JLabel("City");
		lbq5city.setBounds(149, 822, 46, 14);
		panel.add(lbq5city);

		lbq5state = new JLabel("State");
		lbq5state.setBounds(149, 854, 46, 14);
		panel.add(lbq5state);

		lbq5rating = new JLabel("Rating");
		lbq5rating.setBounds(149, 887, 46, 14);
		panel.add(lbq5rating);

		q5res = new JTextField();
		q5res.setColumns(10);
		q5res.setBounds(245, 788, 164, 20);
		panel.add(q5res);

		q5city = new JTextField();
		q5city.setColumns(10);
		q5city.setBounds(245, 819, 164, 20);
		panel.add(q5city);

		q5state = new JComboBox();
		q5state.setModel(new DefaultComboBoxModel(new String[] { "N/A", "AB", "AK", "AL", "AR", "AZ", "BC", "CA", "CO",
				"CT", "DOW", "DUR", "FL", "GA", "HI", "HPL", "IL", "MB", "MI", "MO", "NC", "NE", "NV", "NY", "OH", "ON",
				"OR", "PA", "QC", "SC", "TX", "UT", "VA", "VT", "WA", "WI", "XWY", "YT" }));
		q5state.setMaximumRowCount(38);
		q5state.setBounds(244, 850, 51, 22);
		panel.add(q5state);

		q5ratingb = new JComboBox();
		q5ratingb.setModel(
				new DefaultComboBoxModel(new String[] { "N/A", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5" }));
		q5ratingb.setMaximumRowCount(11);
		q5ratingb.setBounds(297, 883, 51, 22);
		panel.add(q5ratingb);

		q5ratinga = new JComboBox();
		q5ratinga.setModel(new DefaultComboBoxModel(new String[] { "N/A", "=", ">", ">=", "<", "<=" }));
		q5ratinga.setMaximumRowCount(6);
		q5ratinga.setBounds(244, 883, 51, 22);
		panel.add(q5ratinga);

		// NA Button
		rbNA = new JRadioButton("N/A");
		rbNA.setBounds(10, 915, 109, 23);
		rbNA.setSelected(true);
		panel.add(rbNA);

		groupB = new ButtonGroup();
		groupB.add(rbQ1);
		groupB.add(rbQ3);
		groupB.add(rbQ4);
		groupB.add(rbQ5);
		groupB.add(rbNA);

		// ------------------------------------------------------------------------------------------------------------------------

		// set up frame

		panel.setPreferredSize(new Dimension(1000, 1200));
		scrollFrame = new JScrollPane(panel);
		panel.setAutoscrolls(true);

		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// closes database connection
				conn.close();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

		frame.setVisible(true);
		// frame.add(panel);
		frame.add(scrollFrame);
	}

	// when an action event occurs it will go through here
	@Override
	public void actionPerformed(ActionEvent e) {
		// get the action event and decide what to do given the action command "String"
		switch (e.getActionCommand()) {
			// search is the displayed phrase on queryButton
			case "Search":
				if (rbNA.isSelected())
					searchPressed();
				else if (rbQ1.isSelected())
					question1();
				else if (rbQ3.isSelected())
					question3();
				else if (rbQ4.isSelected())
					question4();
				else if (rbQ5.isSelected())
					question5();
				break;
			default:
				output.setText(e.toString());

		}
	}

	/**
	 * getButtonsTest gets arraylist of JRadioButton and returns an arraylist of
	 * string of buttons that are clicked
	 * 
	 * @param group arraylist of JRadioButton
	 * @return arraylist of string of buttons that are clicked
	 */
	private ArrayList<String> getButtonsTest(ArrayList<JRadioButton> group) {
		ArrayList<String> ret = new ArrayList<String>();

		for (JRadioButton button : group) {
			if (button.isSelected()) {
				ret.add(button.getText());
			}
		}

		return ret;
	}

	/**
	 * searchPressed is where we get the selected values and make the query for then
	 * execute and display the query
	 */
	private void searchPressed() {

		// Declare and get all the variables that were selected
		ArrayList<String> selected_attr = getButtonsTest(attributes);
		ArrayList<String> selected_tabl = getButtonsTest(tables);
		String ratingA, ratingB, state;
		ratingA = cbRating1.getSelectedItem().toString();
		ratingB = cbRating2.getSelectedItem().toString();
		state = cbState.getSelectedItem().toString();

		String display = "";
		String sqlStatement = "";
		// sqlStatement = "SELECT \"UserName\" FROM \"User\" limit 10";

		// this is just to display what is selected when user press search can be
		// deleted later
		// display += "'I want to find: '" + Arrays.toString(selected_attr.toArray()) +
		// "\n";
		// display += "'From the list of: '" + Arrays.toString(selected_tabl.toArray())
		// + "\n";
		// display += "'Where: ' Rating " + ratingA + " " + ratingB + "\n";
		// display += " State = " + state + "\n";

		if (!selected_attr.isEmpty()) {
			sqlStatement += "SELECT ";
			for (String attr : selected_attr) {
				sqlStatement += "\"";
				if (attr.equalsIgnoreCase("rating")) {
					sqlStatement += "Stars";
				} else if (attr.equalsIgnoreCase("business names")) {
					sqlStatement += "BusinessName";
				} else {
					sqlStatement += attr;
				}
				sqlStatement += "\",";
			}
			sqlStatement = sqlStatement.substring(0, sqlStatement.length() - 1);
		}
		if (!selected_tabl.isEmpty()) {
			sqlStatement += " FROM \"Business\" NATURAL JOIN \"Location\" NATURAL JOIN ";
			for (String table : selected_tabl) {
				sqlStatement += "\"";
				sqlStatement += table;
				sqlStatement += "\" NATURAL JOIN ";
			}
			sqlStatement = sqlStatement.substring(0, sqlStatement.length() - 13);
		} else {
			sqlStatement = "";
		}
		boolean ratingFound = false;
		boolean stateFound = false;
		if (!ratingA.equals("N/A") && !ratingB.equals("N/A")) {
			ratingFound = true;
		}
		if (!state.equals("N/A")) {
			stateFound = true;
		}
		if ((ratingFound || stateFound) && !sqlStatement.equals("")) {
			sqlStatement += " WHERE ";
			if (ratingFound) {
				sqlStatement += "\"Stars\" " + ratingA + ratingB;
				if (stateFound) { // add ors to this for other conditions
					sqlStatement += " AND ";
				}
			}
			if (stateFound) {
				sqlStatement += "\"State\" = " + "\'" + state + "\' ";
			}
		}
		// display += sqlStatement + "\n";

		/*
		 * the command below sends the string 'sqlStatement' and executes it. It returns
		 * the string that is formatted inside DBConnect.excute() note make sure
		 * sqlStatement does NOT need ';' at the end
		 */

		if (!sqlStatement.equals("")) {
			display = conn.execute(sqlStatement);
		}

		if (display.lastIndexOf("-") == display.length() - 2 || display.equals("")) {
			display += "*** No Results Found ***\n";

		}

		// this displays the strinsg to the output box
		output.setText(display);
	}
    
    /**
     * @param String takes in a string to print to file
     * 
     * prints to a string to a file
     */
    private void printFile(String out){
        if (out.length() > 500) {
            String prompt = "The search result is very large, would you like to output to Results.txt?";
            String title = "Output to Separate File?";
            int opt = JOptionPane.showConfirmDialog(null, prompt, title, JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                try (PrintWriter writer = new PrintWriter("Results.txt")) {
                    writer.println(out);
                } catch (Exception e) {
                    System.err.println("Could not print results to file");
                    JOptionPane.showMessageDialog(null, "Results could not be redirected to file.");
                }
                // JOptionPane.showMessageDialog(null, "Search printed in Results.txt");
            }
        }
    }
    /*
    *  Given 2 restaurants, find the shortest chain of patrons that gave a review of 3 stars (of 5) or better.
    */
	private void question1() {
        System.out.println("start");
		String res1 = q1res1.getText();
        String res2 = q1res2.getText();
        String state1 = q1state1.getSelectedItem().toString();
        String state2 = q1state2.getSelectedItem().toString();
        String city1 = q1city1.getText();
        String city2 = q1city2.getText();

		String sqlStatementR1 = "";
		String sqlStatementR2 = "";

		ArrayList<String> start_ID = new ArrayList<String>();
		ArrayList<String> dest_ID = new ArrayList<String>();

		sqlStatementR1 = "SELECT \"UserID\" FROM \"Review\" NATURAL JOIN \"Location\" NATURAL JOIN \"Restaurant\" NATURAL JOIN \"Business\" WHERE \"Review\".\"Stars\" >= 3 AND \"BusinessName\" = \'"
				+ res1 + "\' AND \"City\" = \'"+ city1 + "\' AND \"State\" = \'"+ state1 + "\'";
		sqlStatementR2 = "SELECT \"UserID\" FROM \"Review\" NATURAL JOIN \"Location\" NATURAL JOIN \"Restaurant\" NATURAL JOIN \"Business\" WHERE \"Review\".\"Stars\" >= 3 AND \"BusinessName\" = \'"
				+ res2 + "\' AND \"City\" = \'"+ city2 + "\' AND \"State\" = \'"+ state2 +"\'";
        
        // get UserIDs from restaurants
        System.err.println("First Query");
        System.err.println(sqlStatementR1);
        start_ID = conn.executeArray(sqlStatementR1, "UserID");
        System.err.println("Second Query");
        System.err.println(sqlStatementR2);
		dest_ID = conn.executeArray(sqlStatementR2, "UserID");
        Map<String,String> userMap = new HashMap<>();
        Map<String,String> busMap = new HashMap<>();
        boolean found = false;
        String foundID = "";
        for(int i = 0; i < start_ID.size();i++){
            System.err.println(start_ID.get(i));
            if(dest_ID.contains(start_ID.get(i))){
                // System.err.println("found");
                found = true;
                userMap.put(start_ID.get(i), "0");
                busMap.put(start_ID.get(i), res1);
                foundID = start_ID.get(i);
                break;
            }
                
        }
        
        HashSet<String> visBus = new HashSet<String>();
        HashSet<String> visUser = new HashSet<String>();
        
        System.err.println("Search");
        
        if(!found){
            // Breadth First Search: all ids in starting business are roots
            for(int i = 0; i < start_ID.size(); i++){
                //Store UserID[0] and BusinessName [1] in Q
                Queue<String> userq = new ArrayDeque<String>();
                String parent = start_ID.get(i);
                userq.add(parent);

                //Map parent user to child
                userMap.put(parent,"0"); 
                //map user to business
                busMap.put(parent,res1);

                System.err.println(parent);
                visBus.add(res1);
                //begin BFS
                while(!userq.isEmpty()){
                    String remUser = userq.remove();
                    System.err.println("remove from queue: " + remUser);
                    //found common userid
                    if(dest_ID.contains(remUser)){
                        System.out.println("found");
                        foundID = remUser;
                        found = true;
                        break;
                    }

                    //if user has not been visited
                    if(!visUser.contains(remUser)){
                        // get businesses user has reviewed >= 3
                        String sqlStatementBus = "";
                        System.err.println(sqlStatementBus);
                        sqlStatementBus = "SELECT \"BusinessName\" FROM \"Review\" NATURAL JOIN \"Business\" NATURAL JOIN \"Restaurant\" WHERE \"Review\".\"Stars\" >= 3 AND \"UserID\" = \'"
                                + remUser + "\';";
                        ArrayList<String> userBus = conn.executeArray(sqlStatementBus, "BusinessName");
                        
                        visUser.add(remUser); //visited user
                        
                        //loop through businesses
                        for(int j = 0; j < userBus.size() && !found; j++){
                            //if business hasn't already been visited
                            if(!visBus.contains(userBus.get(j))){
                                System.err.println("******Business: " + userBus.get(j) 
                                    + "   -Remaining: " + Integer.toString(userBus.size()-j) + "*************");
                                visBus.add(userBus.get(j));
                                //get userIds from business
                                String sqlStatementUser = "";
                                String bus1 = userBus.get(j).replace("'","''");
                                sqlStatementUser = "SELECT \"UserID\" FROM \"Review\" NATURAL JOIN \"Restaurant\" NATURAL JOIN \"Location\" NATURAL JOIN \"Business\" WHERE \"Review\".\"Stars\" >= 3 AND \"BusinessName\" = \'"
                                    + bus1+ "\';";
                                System.err.println(sqlStatementUser + "\n");
                                ArrayList<String> userIDs = conn.executeArray(sqlStatementUser, "UserID");;
                                
                                //loop through userIDs
                                for(int k = 0; k < userIDs.size() && !found; k++){
                                    if(!visUser.contains(userIDs.get(k))){//check hashset
                                        userMap.put(userIDs.get(k),remUser);
                                        busMap.put(userIDs.get(k),userBus.get(j));
                                        System.err.println(userIDs.get(k));
                                        // visUser.add(userIDs.get(k));
                                        userq.add(userIDs.get(k)); //add to queue

                                        //if user is found break out of loop
                                        if(dest_ID.contains(userIDs.get(k))){
                                            System.err.println("found");
                                            foundID = userIDs.get(k);
                                            found = true;
                                            break;
                                        }
                                    }
                                }
                                
                                if(found){
                                    break;
                                }
                            }//business not visited
                        }//loop business
                    }//user not visited
                    if(found){
                        break;
                    }
                }//queue isnt empty
            }//loop original users
        }//if !found

        //Text processing
        String sqlStatementname = "SELECT \"UserName\" FROM \"User\" WHERE \"UserID\" = \'";
        String text = "";
        ArrayList<String> path = new ArrayList<String>();
        if(found){
            //get user path
            while(foundID != "0"){
                System.err.println("Add to path");
                path.add(foundID);
                foundID = userMap.get(foundID);
            }
            //format output message
            text = "Longest Path length is " + Integer.toString(path.size()) + "\n";
            text += "------------------------------\n";
            String lastGuy = "";
            text += "Starting with\n";
            for(int i = path.size()-1; i >= 0; i--){
                String sqlGetName = sqlStatementname + path.get(i) + "\'";
                lastGuy = conn.executeArray(sqlGetName, "UserName").get(0);
                text += "User: \"" + lastGuy;
                text += "\" went to Business: \"" + busMap.get(path.get(i)) +"\"";
                if(i > 0){
                    text += " chains with";
                }
                text += "\n";

            }
            text += "Destination: " + res2 + " links with the user: " + lastGuy;
        } else {
            text = "No Chain found";
        }
        
        // printFile(text);
		output.setText(text);
    }

    /*Given a US state, find the 5 restaurant franchises (restaurants with multiple locations)
        that have at least an average rating of 3.5 with the furthest location spread.
    */
	private void question3() {
		// Get the passed state.
		String state = q3state.getSelectedItem().toString();

		// Variables
		ArrayList<String> businessnames = new ArrayList<String>();
		ArrayList<String> longitudes = new ArrayList<String>();
		ArrayList<String> latitudes = new ArrayList<String>();
		ArrayList<ArrayList<String>> dat = new ArrayList<ArrayList<String>>();

		// String sqlStatement = "";
		// String sqlStatementlong = "";
		// String sqlStatementlat = "";
		String getSQL = "";
		Hashtable<String, Double> distTable = new Hashtable<String, Double>();
		TreeMap<Double, String> ret = new TreeMap<Double, String>();
		Double min = 0.0;
		String printOutput = "";

		ArrayList<String> uniqueFranch = new ArrayList<String>();

		String query = "";
		String retVal = "";
		String tempBiz = "";

		// This is the subquery to get the table with business names and average stars.
		String queryAvg = "(select \"BusinessID\", AVG(\"Stars\") from \"Restaurant\" natural join \"Business\" natural join \"Location\" where \"State\" = \'"
				+ state + "\' group by \"BusinessName\") AS Average";

		// Query to get the Business, Long, and Lat arrays.
		getSQL += "select * from \"Restaurant\" natural join \"Business\" natural join \"Location\" where \"State\" = \'"
				+ state + "\'";

		// sqlStatement += "select \"BusinessName\" from \"Restaurant\" natural join
		// \"Business\" natural join \"Location\" where \"State\" = \'"
		// + state + "\' and \"Stars\" > 3.5";
		// sqlStatementlong += "select \"Longitude\" from \"Restaurant\" natural join
		// \"Business\" natural join \"Location\" where \"State\" = \'"
		// + state + "\' and \"Stars\" > 3.5";
		// sqlStatementlat += "select \"Latitude\" from \"Restaurant\" natural join
		// \"Business\" natural join \"Location\" where \"State\" = \'"
		// + state + "\' and \"Stars\" > 3.5";

		dat = conn.executeArrayArray(getSQL);
		businessnames = dat.get(0);
		longitudes = dat.get(1);
		latitudes = dat.get(2);

		// businessnames = conn.executeArray(sqlStatement, "BusinessName");
		// longitudes = conn.executeArray(sqlStatementlong, "Longitude");
		// latitudes = conn.executeArray(sqlStatementlat, "Latitude");

		// If the size of the businessnames array is less than two, then there can't be
		// any franchises.
		if (businessnames.size() < 2) {
			output.setText("*** No Results Found ***\n");
			return;
		}

		// Cout for testing purposes.
		for (String key : businessnames) {
			System.out.println(key);
		}

		// Loop through arrays O(n^2) and calculate the spread for each business. Store
		// in hashtable.
		for (Integer i = 0; i < businessnames.size() - 1; i++) {
			// Add businessname if not present.
			if (!distTable.containsKey(businessnames.get(i))) {
				distTable.put(businessnames.get(i), 0.0);
			}
			// Go through remaining businesses while calculating distances.
			for (Integer j = i + 1; j < businessnames.size(); j++) {
				// Check if the businesses (i and j) are franchises and then calculates the
				// distance between the two.
				if (businessnames.get(i).equals(businessnames.get(j))) {
					Double latsqr = Math
							.pow(Double.parseDouble(latitudes.get(i)) - Double.parseDouble(latitudes.get(j)), 2);
					Double longsqr = Math
							.pow(Double.parseDouble(longitudes.get(i)) - Double.parseDouble(longitudes.get(j)), 2);
					Double distance = Math.sqrt(latsqr + longsqr);
					// Only update the distance if it is greater than the past one.
					if (Double.compare(distTable.get(businessnames.get(i)), distance) < 0.0) {
						distTable.replace(businessnames.get(i), distance);
					}

				}
			}
		}

		// Removes all items where value is 0 aka non-franchises.
		distTable.values().removeAll(Collections.singleton(0.0));

		// If the hashtable is empty, that means no franchises. Return.
		if (distTable.size() == 0) {
			output.setText("*** No Results Found ***\n");
			return;
		}

		// Create a list of unique franchises.
		for (String key : distTable.keySet()) {
			if (!uniqueFranch.contains(key)) {
				uniqueFranch.add(key);
			}
		}

		// Remove all businesses with avg rating less than 3.5
		for (String biz : uniqueFranch) {
			tempBiz = biz.replace("'", "''");

			query = "select AVG(\"Stars\") from \"Business\" natural join \"Location\" where \"BusinessName\" = \'"
					+ tempBiz + "\' and \"State\" = \'" + state + "\'";
			System.out.println(query);
			retVal = conn.execSingleVal(query);
			System.out.println(retVal);
			if (Double.parseDouble(retVal) < 3.5) {
				distTable.remove(biz);
			}
		}

		// Using a TreeMap to find the top 5 restaurants with furthest spread
		for (String key : distTable.keySet()) {
			// Append value to treemap if it is greater than the current minimum.
			// System.out.println("Key: " + key + ", Value: " + distTable.get(key));
			if (Double.compare(min, distTable.get(key)) < 0.0) {
				// Adding value. Since it is a treemap, the keys are automatically sorted.
				ret.put(distTable.get(key), key);
				// If size greater than 5 pop min.
				if (ret.size() > 5) {
					ret.remove(ret.firstKey());
				}
				// Reallocate min to smallest value in treemap.
				min = ret.firstKey();
			}
		}

		// Converting treemap contents to string to be passed to output.setText()
		for (Double key : ret.keySet()) {
			System.out.println(ret.get(key) + ": " + key);
			printOutput += "Business: " + ret.get(key) + ", Spread: " + key;
			printOutput += "\n";
		}

        // Display result.
        // printFile(printOutput);
		output.setText(printOutput);
	}

    /*Find the best local restaurant, no franchises. Given a city, find the non-franchise restaurant
    that receives the most tips and display restaurant and tips.    
    */
	private void question4() {
		String city = q4city.getText();
		int maxTipCount = 0;
		String maxBID = "";
		int currentCount = 0;
		String display = "";
		String header;
		String finalQuery = "";
		ArrayList<String> tips;
		// ArrayList<String> curTips;
		ResultSet data;
		// This query returns all local restaurants in a given city
		String query = "SELECT \"BusinessID\" FROM \"Business\" NATURAL JOIN \"Location\" NATURAL JOIN \"Restaurant\" WHERE"
				+ " \"City\" LIKE \'" + city
				+ "\' AND \"BusinessName\" NOT IN(SELECT \"BusinessName\" FROM \"Business\" NATURAL JOIN"
				+ " \"Location\" NATURAL JOIN \"Restaurant\" WHERE \"City\" <> \'" + city + "\')";
		ArrayList<String> Ids = conn.executeArray(query, "BusinessID");
		// System.out.println(Ids.size());

		// Loops through the local restaurants
		for (String business : Ids) {
			String newQuery = "SELECT COUNT(\"UserID\") FROM \"Tips\" WHERE \"BusinessID\" = \'" + business + "\'";
			data = conn.exec(newQuery);
			try {
				// Assigns the current business' amount of tips
				while (data.next()) {
					currentCount = Integer.parseInt(data.getString(1));
				}
			} catch (Exception e) {
				System.err.println("There was an error in getting tip count");
				System.exit(1);
			}
			// Current tip count is greater than the max
			if (currentCount > maxTipCount) {
				maxBID = business;
				maxTipCount = currentCount;
			}
			// System.out.println(maxTipCount);
		}

		if (!maxBID.equals("")) {
			finalQuery += "SELECT \"BusinessName\", \"City\" FROM \"Business\" NATURAL JOIN \"Location\" WHERE \"BusinessID\" = \'"
					+ maxBID + "\'";
			header = conn.executeArray(finalQuery, "BusinessName").get(0);
			display += header + ", ";
			header = conn.executeArray(finalQuery, "City").get(0) + ", Tip Count = " + maxTipCount + "\n"
					+ "-------------------------------------------------------------" + "\n";
			display += header;
			finalQuery = "SELECT \"Text\" FROM \"Tips\" NATURAL JOIN \"Business\" WHERE \"BusinessID\" = \'" + maxBID
					+ "\'";
			tips = conn.executeArray(finalQuery, "Text");
			for (String tip : tips) {
				display += tip + "\n";
			}
		} else {
			display += "*** No Results Found ***\n";
		}
        // printFile(display);
		output.setText(display);

	}

    /**
     * Find the nearest entertainment above a certain number of stars, given the restaurant you are at or nearest to
     */
	private void question5() {
		String res, city, state, ratingA, ratingB, command;
		ResultSet data, resdata;
		city = "'%" + q5city.getText() + "%'";
		city = city.replaceAll(" ", "%");
		state = "'" + q5state.getSelectedItem().toString() + "'";
		ratingA = q5ratinga.getSelectedItem().toString();
		ratingB = q5ratingb.getSelectedItem().toString();

		String resarr[] = q5res.getText().split(" ");
		res = "";
		for (String word : resarr) {
			word = word.replaceAll("'", "''");
			res += "\"BusinessName\" Like '%" + word + "%' AND ";
		}
		res = res.substring(0, res.length() - 4);

		// making command to get data of surounding data
		command = "SELECT \"BusinessName\", \"Longitude\", \"Latitude\", \"Types\", \"Stars\" ";
		command += "FROM \"Business\" NATURAL JOIN \"Location\" NATURAL JOIN \"Entertainment\" ";
		command += "WHERE \"State\" = " + state + " AND \"City\" LIKE " + city + " AND \"Stars\" " + ratingA + " "
				+ ratingB + ";";

		// declare values to store data
		ArrayList<String> bnamesList = new ArrayList<String>();
		ArrayList<Double> latList = new ArrayList<Double>();
		ArrayList<Double> lonList = new ArrayList<Double>();
		ArrayList<String> typeList = new ArrayList<String>();
		ArrayList<Double> starList = new ArrayList<Double>();
		// output.setText(conn.execute(command));
		// get surounding data
		data = conn.exec(command);
		try {
			// get the metadeta
			ResultSetMetaData rsmd = data.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			// set up how string is going to be displayed
			while (data.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i == 1)
						bnamesList.add(data.getString(i));
					if (i == 2)
						lonList.add(data.getDouble(i));
					if (i == 3)
						latList.add(data.getDouble(i));
					if (i == 4)
						typeList.add(data.getString(i));
					if (i == 5)
						starList.add(data.getDouble(i));
				}
			}
		} catch (Exception e) {
			System.err.println("There was an error in getting data1");
			System.exit(1);
		}

		// making command to get data of restaurant
		command = "SELECT \"Longitude\", \"Latitude\" ";
		command += "FROM \"Business\" NATURAL JOIN \"Location\" NATURAL JOIN \"Restaurant\" ";
		command += "WHERE \"State\" = " + state + " AND \"City\" LIKE " + city + " AND " + res + ";";
		// command += "WHERE \"State\" = "+ state + " AND \"City\" LIKE "+ city + " AND
		// \"Stars\" " + ratingA + " " + ratingB + ";";
		// output.setText(conn.execute(command));
		double latitude = 0;
		double longitude = 0;
		// get surounding data
		resdata = conn.exec(command);
		try {

			ResultSetMetaData rsmd = data.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (resdata.next()) {

				longitude = resdata.getDouble(1);
				latitude = resdata.getDouble(2);

			}
		} catch (Exception e) {
			System.err.println("There was an error in getting data2");
			System.exit(1);
		}
		// System.err.println(res);
		if (bnamesList.size() > 0) {
			double min = euclid(longitude, latitude, lonList.get(0), latList.get(0));
			String bname = bnamesList.get(0);
			String types = typeList.get(0);
			Double star = starList.get(0);
			for (int i = 0; i < bnamesList.size(); i++) {
				if (min > euclid(longitude, latitude, lonList.get(i), latList.get(i))) {
					min = euclid(longitude, latitude, lonList.get(i), latList.get(i));
					bname = bnamesList.get(i);
					types = typeList.get(i);
					star = starList.get(i);
					// System.err.println(euclid(longitude, latitude, lonList.get(i),
					// latList.get(i)) + " " + bname);
					// System.err.println(longitude+"|"+latitude+"|"+ lonList.get(i)+"|"+
					// latList.get(i)+"\n");
				}

			}
			String lineOut = "Our Recommendation: \n";
			lineOut += String.format(
					"With the rating of %.2f, %s is %.2f miles away\nType of entertainment avenue is:\n%s", star, bname,
                    min, types);
            // printFile(lineOut);
			output.setText(lineOut);
		} else {
			output.setText("No Entertainment Avenue Close by");
		}

	}

    /**
     * @param double longitude of first point
     * @param double latitude of first point
     * @param double longitude of second point
     * @param double latitude of second point
     * 
     * @return distance between points in miles
     */
	public double euclid(double lon1, double lat1, double lon2, double lat2) {
		// The math module contains a function
		// named toRadians which converts from
		// degrees to radians.
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		// Haversine formula
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		// Radius of earth in kilometers. Use 3956
		// for miles
		double r = 6371;

		// calculate the result
		return (c * r);
	}

}
