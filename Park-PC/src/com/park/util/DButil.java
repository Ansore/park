package com.park.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    
    static{
    	driver="com.mysql.jdbc.Driver";
    	url="jdbc:mysql://localhost:3306/stallpc";
    	username="ansore";
    	password="ansore";
    }
    
    public static Connection open()
    {
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			}return null;
    }
    public static Connection getConnection() throws SQLException{
    	         return DriverManager.getConnection(url, username,password);
         }
    
    public static void close(Connection conn){
    	if(conn!=null){
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
