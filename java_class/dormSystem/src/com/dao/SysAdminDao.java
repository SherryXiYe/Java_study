package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Building;
import com.bean.DormAdmin;
import com.bean.SystemAdmin;
import com.jdbc.JDBC;


public class SysAdminDao {
	
	//查询系统管理员
	public SystemAdmin selectSysAdm(String userID,String password)
	{
		String sqlStr="select* from system_admin where systemAdminID=? and password=?";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//创建预编译环境
		PreparedStatement prepareStatement=null;
		ResultSet resultSet=null;
		SystemAdmin sysadm=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//设置sql语句参数
			prepareStatement.setString(1, userID);
			prepareStatement.setString(2, password);
			//执行sql查询
			resultSet = prepareStatement.executeQuery();
			if(resultSet.next())
			{
				sysadm=new SystemAdmin(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, prepareStatement, resultSet);
		}
		return sysadm;
	}
	
	//修改系统管理员密码
	public boolean changePassword(String userid,String oldPassword, String newPassword){
		String sqlStr="update system_admin set password=? where systemAdminID=? and password=?";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//创建预编译环境
		PreparedStatement prepareStatement=null;
		//ResultSet resultSet=null;
		//SystemAdmin sysadm=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//设置sql语句参数
			prepareStatement.setString(1, newPassword);
			prepareStatement.setString(2, userid);
			prepareStatement.setString(3, oldPassword);
			//执行sql语句
			if(prepareStatement.executeUpdate()>0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, prepareStatement, null);
		}
		return false;
	}
	
	//插入宿舍楼
	public boolean insertBuilding(String buildingName,String buildingType,String remarks)
	{
		String sqlStr="insert into dorm_building(dormName,sexType,remarks) values(?,?,?)";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//创建预编译环境
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//设置sql语句参数
			prepareStatement.setString(1, buildingName);
			prepareStatement.setString(2, buildingType);
			prepareStatement.setString(3, remarks);
			//执行sql查询
			int count=prepareStatement.executeUpdate();
			if(count>0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, prepareStatement, null);
		}
		return false;
	}
	
	//查询所有宿舍楼
	public ArrayList<Building> queryAllBuilding()
	{
		ArrayList<Building> result=new ArrayList<Building>();
		String sqlStr="select dormName,sexType,admNum,dormNum,remarks from dorm_building";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//创建预编译环境
		PreparedStatement prepareStatement=null;			
		ResultSet resultSet=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//执行sql查询
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{	
				Building tempBuilding=new Building();
				tempBuilding.setName(resultSet.getString("dormName"));
				tempBuilding.setType(resultSet.getString("sexType"));
				tempBuilding.setAdmNum(resultSet.getInt("admNum"));
				tempBuilding.setDormNum(resultSet.getInt("dormNum"));
				tempBuilding.setRemarks(resultSet.getString("remarks"));
				result.add(tempBuilding);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, prepareStatement, resultSet);
		}
			return result;
	}
	
	//查询宿舍楼信息
	public ArrayList<Building> queryBuilding(Building building)
	{
		ArrayList<Building> result=new ArrayList<Building>();
		String sqlStr="select dormName,sexType,admNum,dormNum,remarks from dorm_building where dormName like '%"+building.getName()+"%' and sexType like '%"+building.getType()+"%'";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//创建预编译环境
		PreparedStatement prepareStatement=null;			
		ResultSet resultSet=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//执行sql查询
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{	
				Building tempBuilding=new Building();
				tempBuilding.setName(resultSet.getString("dormName"));
				tempBuilding.setType(resultSet.getString("sexType"));
				tempBuilding.setAdmNum(resultSet.getInt("admNum"));
				tempBuilding.setDormNum(resultSet.getInt("dormNum"));
				tempBuilding.setRemarks(resultSet.getString("remarks"));
				result.add(tempBuilding);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, prepareStatement, resultSet);
		}
			return result;
	}
		
	//删除宿舍楼
	public boolean deleteBuilding(String buildingName)
	{
		String sqlStr="delete from dorm_building where dormName = ?";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//创建预编译环境
		PreparedStatement prepareStatement=null;	
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//设置sql语句参数
			prepareStatement.setString(1, buildingName);
			//执行sql语句
			int count=prepareStatement.executeUpdate();
			if(count>0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, prepareStatement, null);
		}
		return false;
	}
	
	//更新宿舍楼（存储过程）名称或性别，然后其它实体依赖于宿舍楼名称的属性也会更新
	public boolean updateBuilding(String oldbuildingName,String newBuildingName,String sexType)
	{
		String sqlStr="{call buildingNameU(?,?,?)}";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//调用存储过程
		CallableStatement stmt=null;
		try {
			stmt=conn.prepareCall(sqlStr);
			//设置存储过程参数
			stmt.setString(1,oldbuildingName);
			stmt.setString(2, newBuildingName);
			stmt.setString(3, sexType);
			//执行存储过程
			int count=stmt.executeUpdate();
			if(count>0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, stmt, null);
		}
		return false;
	}

	
	//查询所有宿管
	public ArrayList<DormAdmin> queryAllDormAdm()
	{
		ArrayList<DormAdmin> result=new ArrayList<DormAdmin>();
		String sqlStr="select dormAdminID,name,buildingName,sex from dorm_admin";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//创建预编译环境
		PreparedStatement prepareStatement=null;			
		ResultSet resultSet=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//执行sql查询
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{	
				DormAdmin tempDormAdm=new DormAdmin();
				tempDormAdm.setDormAdmID(resultSet.getString("dormAdminID"));
				tempDormAdm.setName(resultSet.getString("name"));
				tempDormAdm.setBuildingName(resultSet.getString("buildingName"));
				tempDormAdm.setSex(resultSet.getString("sex"));
				result.add(tempDormAdm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, prepareStatement, resultSet);
		}
			return result;
	}
	
	//查询宿管信息
	public ArrayList<DormAdmin> queryDormAdm(DormAdmin dormAdm)
	{
		ArrayList<DormAdmin> result=new ArrayList<DormAdmin>();
		String sqlStr="select dormAdminID,name,buildingName,sex from dorm_admin where dormAdminID like '%"+dormAdm.getDormAdmID()+"%' and name like '%"+dormAdm.getName()+"%' and buildingName like '%"+dormAdm.getBuildingName()+"%' and sex like '%" +dormAdm.getSex()+"%'";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//创建预编译环境
		PreparedStatement prepareStatement=null;			
		ResultSet resultSet=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//执行sql查询
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{	
				DormAdmin tempDormAdm=new DormAdmin();
				tempDormAdm.setDormAdmID(resultSet.getString("dormAdminID"));
				tempDormAdm.setName(resultSet.getString("name"));
				tempDormAdm.setBuildingName(resultSet.getString("buildingName"));
				tempDormAdm.setSex(resultSet.getString("sex"));
				result.add(tempDormAdm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, prepareStatement, resultSet);
		}
			return result;
	}
	
	//删除宿管(事务)
	public boolean deleteDormAdm(String dormAdmID,String buildingName)
	{
		String sqlStr="{call dormAdmDel(?,?)}";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//调用存储过程
		CallableStatement stmt=null;
		try {
			stmt=conn.prepareCall(sqlStr);
			//设置存储过程参数
			stmt.setString(1,dormAdmID);
			stmt.setString(2,buildingName);
			//执行存储过程
			int count=stmt.executeUpdate();
			if(count>0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, stmt, null);
		}
		return false;
	}

	//插入宿管(触发器）
	public boolean insertDormAdm(DormAdmin dormAdm)
	{
		String sqlStr="insert into dorm_admin values(?,?,?,?,?)";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//创建预编译环境
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//设置sql语句参数
			prepareStatement.setString(1, dormAdm.getDormAdmID());
			prepareStatement.setString(2, dormAdm.getName());
			prepareStatement.setString(3, dormAdm.getPassword());
			prepareStatement.setString(4, dormAdm.getBuildingName());
			prepareStatement.setString(5, dormAdm.getSex());
			//执行sql查询
			int count=prepareStatement.executeUpdate();
			if(count>0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, prepareStatement, null);
		}
		return false;
	}
	
	//查询视图
	public ArrayList<DormAdmin> womanBuilding()
	{
		ArrayList<DormAdmin> result=new ArrayList<DormAdmin>();
		String sqlStr="select * from dormadm_woman";
		//获得连接对象
		Connection conn=JDBC.getConnection();
		//创建预编译环境
		PreparedStatement prepareStatement=null;			
		ResultSet resultSet=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//执行sql查询
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
			{	
				DormAdmin tempDormAdm=new DormAdmin();
				tempDormAdm.setDormAdmID(resultSet.getString("dormAdminID"));
				tempDormAdm.setName(resultSet.getString("name"));
				tempDormAdm.setBuildingName(resultSet.getString("buildingName"));
				tempDormAdm.setSex(resultSet.getString("sex"));
				result.add(tempDormAdm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBC.closeConnection(conn, prepareStatement, resultSet);
		}
			return result;
	}
	
}
