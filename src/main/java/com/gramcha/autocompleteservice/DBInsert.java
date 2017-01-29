package com.gramcha.autocompleteservice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInsert {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String csvFile = "amazon_dataset.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        long items=0;
        Connection conn =null;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            String url = "jdbc:mysql://127.0.0.1:3306/acs"; 
            conn = DriverManager.getConnection(url,"root","root"); 
            Statement st = conn.createStatement(); 
            String token = "INSERT INTO products (title) VALUES (";
            
            while ((line = br.readLine()) != null) {
            	items++;

            	if(items <= 368309)
            		continue;
                // use comma as separator
                String[] productDetails = line.split(cvsSplitBy);
                
                String str = productDetails[0].toLowerCase();
                str = str.replace("\"", "");
                StringBuilder builder = new StringBuilder();
        	    builder.append(token);
        	    builder.append("\"");
        	    builder.append(str);
        	    builder.append("\"");
        	    builder.append(")");
        	    //System.out.println(builder.toString());
        	    try{
        	    	st.executeUpdate(builder.toString());
        	    }
        	    catch(com.mysql.jdbc.MysqlDataTruncation e)
                {
                	System.out.println(e.getMessage());
                }
                
                if(items%5000==0)
                {
                	resetting connec
                	conn.close();
                	conn = DriverManager.getConnection(url,"root","root"); 
                    st = conn.createStatement();
                	System.out.println(items +" "+str);
                }
            }

        }
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
            	if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		try { 
            
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        } 
	}*/

}
