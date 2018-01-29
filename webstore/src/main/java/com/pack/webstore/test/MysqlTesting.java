package com.pack.webstore.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class MysqlTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webstore","root","root");  
			//here sonoo is database name, root is username and password  
			Statement stmt = con.createStatement();  
			ResultSet rs=stmt.executeQuery("select id,name,description   from products");  
			while(rs.next())  
			System.out.println(rs.getString(1)+"....... "+rs.getString(2)+"....... "+rs.getString(3));  
			con.close();  
			
		}catch(Exception e){
			System.out.println(e);
			}  
			
	}  
			 
}
