import java.io.PrintWriter;
import java.sql.*;
import javax.swing.JOptionPane;

// import jdk.javadoc.internal.doclets.toolkit.taglets.ReturnTaglet;

import java.util.*;

public class DBConnect {

    // Declare Variables
    private Connection conn;
    public boolean outputRedirected;

    public DBConnect() {
        // get my account stuff
        dbSetup my = new dbSetup();

        // connect to data base
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/team910_d6_db", my.user,
                    my.pswd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * try to excute the command
     * 
     * @param line, this is the psl line to execute
     * @return string of the output just
     */
    public String execute(String sqlStatement) {
        String ret = "";
        try {
            // create statement object
            Statement stmt = conn.createStatement();
            // send statement to DBMS
            ResultSet rs = stmt.executeQuery(sqlStatement);

            // getting the result

            // get the metadeta
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            // Column names
            for (int i = 1; i <= columnsNumber; i++) {

                ret += rsmd.getColumnLabel(i);
                if (i < columnsNumber)
                    ret += ",  ";
            }
            ret += "\n----------------------------------------------------\n";
            // set up how string is going to be displayed
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        ret += ",  ";
                    }
                    String columnValue = rs.getString(i);
                    ret += columnValue + " ";
                }
                ret += "\n";
            }

            // if size is too big ask using JOptionPane if user want to create a new file to
            // display it
            // if you are writing you can just write using ret string cause it would have
            // been formatted too
            if (ret.length() > 500) {
                String prompt = "The search result is very large, would you like to output to Results.txt?";
                String title = "Output to Separate File?";
                int opt = JOptionPane.showConfirmDialog(null, prompt, title, JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) {
                    try (PrintWriter out = new PrintWriter("Results.txt")) {
                        out.println(ret);
                    } catch (Exception e) {
                        System.err.println("Could not print results to file");
                        JOptionPane.showMessageDialog(null, "Results could not be redirected to file.");
                    }
                    outputRedirected = true;
                    // JOptionPane.showMessageDialog(null, "Search printed in Results.txt");
                } else {
                    outputRedirected = false;
                }
            } else {
                outputRedirected = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in the query");
            return "Error found in the sql statement";
        }
        return ret;
    }

    /**
     * 
     * @param String takes in a sql statement
     * @param String takes in the column name you want from database
     * 
     * @return ArrayList<String> returns array of values related to the column
     */
    public ArrayList<String> executeArray(String sqlStatement, String col) {
        ArrayList<String> myArray = new ArrayList<String>();
        try {
            // create statement object
            Statement stmt = conn.createStatement();
            // send statement to DBMS
            ResultSet rs = stmt.executeQuery(sqlStatement);

            while (rs.next()) {
                myArray.add(rs.getString(col));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in the query\n"+ sqlStatement);
            System.out.println(sqlStatement);
            // return "Error found in the sql statement";
            return myArray;
        }
        return myArray;
    }

    /**
     * @param String takes in an sql statement (only used for longitude and latitude)
     * 
     * @return ArrayList<ArrayList<String>> (2d arraylist) of the dataset (for longitude and latitude only)
     */
    public ArrayList<ArrayList<String>> executeArrayArray(String sqlStatement) {
        ArrayList<String> biz = new ArrayList<String>();
        ArrayList<String> longi = new ArrayList<String>();
        ArrayList<String> lat = new ArrayList<String>();
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();

        try {
            // create statement object
            Statement stmt = conn.createStatement();
            // send statement to DBMS
            ResultSet rs = stmt.executeQuery(sqlStatement);

            while (rs.next()) {
                biz.add(rs.getString("BusinessName"));
                longi.add(rs.getString("Longitude"));
                lat.add(rs.getString("Latitude"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in the query");
            // return "Error found in the sql statement";
            return ret;
        }
        ret.add(biz);
        ret.add(longi);
        ret.add(lat);
        return ret;
    }

    /**
     * @param String takes in an sql statement
     * 
     * @return average stars of the business 
     */
    public String execSingleVal(String sqlStatement) {
        String ret = "";
        try {
            // create statement object
            Statement stmt = conn.createStatement();
            // send statement to DBMS

            ResultSet rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
                ret = rs.getString("avg");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in the query");
            // return "Error found in the sql statement";
            return ret;
        }
        return ret;
    }

    /**
     * @param takes in sql Statement
     * 
     * @returns everything that server prints out
     */
    public ResultSet exec(String sqlStatement) {
        try {
            // create statement object
            Statement stmt = conn.createStatement();
            // send statement to DBMS
            return stmt.executeQuery(sqlStatement);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in the query");
            return null;
        }
    }

    /**
     * Closes databas connection
     */
    public void close() {
        try {
            conn.close();
            JOptionPane.showMessageDialog(null, "Connection Closed.");
        } catch (Exception e) {
            System.err.println("Connection to database is not closed");
            JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
        }
    }
}
