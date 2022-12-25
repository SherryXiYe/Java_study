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
	
	//��ѯϵͳ����Ա
	public SystemAdmin selectSysAdm(String userID,String password)
	{
		String sqlStr="select* from system_admin where systemAdminID=? and password=?";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//����Ԥ���뻷��
		PreparedStatement prepareStatement=null;
		ResultSet resultSet=null;
		SystemAdmin sysadm=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//����sql������
			prepareStatement.setString(1, userID);
			prepareStatement.setString(2, password);
			//ִ��sql��ѯ
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
	
	//�޸�ϵͳ����Ա����
	public boolean changePassword(String userid,String oldPassword, String newPassword){
		String sqlStr="update system_admin set password=? where systemAdminID=? and password=?";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//����Ԥ���뻷��
		PreparedStatement prepareStatement=null;
		//ResultSet resultSet=null;
		//SystemAdmin sysadm=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//����sql������
			prepareStatement.setString(1, newPassword);
			prepareStatement.setString(2, userid);
			prepareStatement.setString(3, oldPassword);
			//ִ��sql���
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
	
	//��������¥
	public boolean insertBuilding(String buildingName,String buildingType,String remarks)
	{
		String sqlStr="insert into dorm_building(dormName,sexType,remarks) values(?,?,?)";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//����Ԥ���뻷��
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//����sql������
			prepareStatement.setString(1, buildingName);
			prepareStatement.setString(2, buildingType);
			prepareStatement.setString(3, remarks);
			//ִ��sql��ѯ
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
	
	//��ѯ��������¥
	public ArrayList<Building> queryAllBuilding()
	{
		ArrayList<Building> result=new ArrayList<Building>();
		String sqlStr="select dormName,sexType,admNum,dormNum,remarks from dorm_building";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//����Ԥ���뻷��
		PreparedStatement prepareStatement=null;			
		ResultSet resultSet=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//ִ��sql��ѯ
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
	
	//��ѯ����¥��Ϣ
	public ArrayList<Building> queryBuilding(Building building)
	{
		ArrayList<Building> result=new ArrayList<Building>();
		String sqlStr="select dormName,sexType,admNum,dormNum,remarks from dorm_building where dormName like '%"+building.getName()+"%' and sexType like '%"+building.getType()+"%'";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//����Ԥ���뻷��
		PreparedStatement prepareStatement=null;			
		ResultSet resultSet=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//ִ��sql��ѯ
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
		
	//ɾ������¥
	public boolean deleteBuilding(String buildingName)
	{
		String sqlStr="delete from dorm_building where dormName = ?";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//����Ԥ���뻷��
		PreparedStatement prepareStatement=null;	
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//����sql������
			prepareStatement.setString(1, buildingName);
			//ִ��sql���
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
	
	//��������¥���洢���̣����ƻ��Ա�Ȼ������ʵ������������¥���Ƶ�����Ҳ�����
	public boolean updateBuilding(String oldbuildingName,String newBuildingName,String sexType)
	{
		String sqlStr="{call buildingNameU(?,?,?)}";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//���ô洢����
		CallableStatement stmt=null;
		try {
			stmt=conn.prepareCall(sqlStr);
			//���ô洢���̲���
			stmt.setString(1,oldbuildingName);
			stmt.setString(2, newBuildingName);
			stmt.setString(3, sexType);
			//ִ�д洢����
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

	
	//��ѯ�����޹�
	public ArrayList<DormAdmin> queryAllDormAdm()
	{
		ArrayList<DormAdmin> result=new ArrayList<DormAdmin>();
		String sqlStr="select dormAdminID,name,buildingName,sex from dorm_admin";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//����Ԥ���뻷��
		PreparedStatement prepareStatement=null;			
		ResultSet resultSet=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//ִ��sql��ѯ
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
	
	//��ѯ�޹���Ϣ
	public ArrayList<DormAdmin> queryDormAdm(DormAdmin dormAdm)
	{
		ArrayList<DormAdmin> result=new ArrayList<DormAdmin>();
		String sqlStr="select dormAdminID,name,buildingName,sex from dorm_admin where dormAdminID like '%"+dormAdm.getDormAdmID()+"%' and name like '%"+dormAdm.getName()+"%' and buildingName like '%"+dormAdm.getBuildingName()+"%' and sex like '%" +dormAdm.getSex()+"%'";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//����Ԥ���뻷��
		PreparedStatement prepareStatement=null;			
		ResultSet resultSet=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//ִ��sql��ѯ
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
	
	//ɾ���޹�(����)
	public boolean deleteDormAdm(String dormAdmID,String buildingName)
	{
		String sqlStr="{call dormAdmDel(?,?)}";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//���ô洢����
		CallableStatement stmt=null;
		try {
			stmt=conn.prepareCall(sqlStr);
			//���ô洢���̲���
			stmt.setString(1,dormAdmID);
			stmt.setString(2,buildingName);
			//ִ�д洢����
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

	//�����޹�(��������
	public boolean insertDormAdm(DormAdmin dormAdm)
	{
		String sqlStr="insert into dorm_admin values(?,?,?,?,?)";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//����Ԥ���뻷��
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//����sql������
			prepareStatement.setString(1, dormAdm.getDormAdmID());
			prepareStatement.setString(2, dormAdm.getName());
			prepareStatement.setString(3, dormAdm.getPassword());
			prepareStatement.setString(4, dormAdm.getBuildingName());
			prepareStatement.setString(5, dormAdm.getSex());
			//ִ��sql��ѯ
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
	
	//��ѯ��ͼ
	public ArrayList<DormAdmin> womanBuilding()
	{
		ArrayList<DormAdmin> result=new ArrayList<DormAdmin>();
		String sqlStr="select * from dormadm_woman";
		//������Ӷ���
		Connection conn=JDBC.getConnection();
		//����Ԥ���뻷��
		PreparedStatement prepareStatement=null;			
		ResultSet resultSet=null;
		try {
			prepareStatement=conn.prepareStatement(sqlStr);
			//ִ��sql��ѯ
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
