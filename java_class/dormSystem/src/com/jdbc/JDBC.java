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
		//getClassLoader�������������ͨ��getRescourceAsStream������db.properties��Դ�ļ����뵽��������
		InputStream resourceAsStream = JDBC.class.getClassLoader().getResourceAsStream("db.properties");
		//����Properties���͵Ķ���
		Properties p=new Properties();
		//�������ļ�
		try {
			p.load(resourceAsStream);
			driver=p.getProperty("driver");
			url=p.getProperty("url");
			username=p.getProperty("username");
			password=p.getProperty("password");
			//����mysql����
			Class.forName(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//������Ӷ���
	public static Connection getConnection()
	{
		//�������ݿ⡣ͨ��DriverManager�ഴ�����ݿ����Ӷ���Connection
		try {
			return DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//�ر����ݿ�����,�ͷŶ���
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
