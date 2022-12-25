package com.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		//getClassLoader（）类加载器，通过getRescourceAsStream（）将db.properties资源文件放入到输入流中
		InputStream resourceAsStream = JDBC.class.getClassLoader().getResourceAsStream("db.properties");
		//创建Properties类型的对象
		Properties p=new Properties();
		//加载流文件
		try {
			p.load(resourceAsStream);
			driver=p.getProperty("driver");
			url=p.getProperty("url");
			username=p.getProperty("username");
			password=p.getProperty("password");
			//加载mysql驱动
			Class.forName(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获得连接对象
	public static Connection getConnection()
	{
		//连接数据库。通过DriverManager类创建数据库连接对象Connection
		try {
			return DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//关闭数据库连接,释放对象
	public static void closeConnection(Connection conn,Statement statement, ResultSet resultSet)
	{
		try {
				if(resultSet!=null)
				{
					resultSet.close();
					resultSet=null;
				}
				if(statement!=null)
				{
					statement.close();
					statement=null;
				}
				if(conn!=null)
				{
					conn.close();
					conn=null;
				}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	 public static void main(String[] args) throws SQLException{
		 
	 }
}
