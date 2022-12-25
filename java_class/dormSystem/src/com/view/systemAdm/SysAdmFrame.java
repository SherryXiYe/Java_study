package com.view.systemAdm;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bean.SystemAdmin;
import com.bean.UserType;
import com.view.ChangePassword;
import com.view.LoginFrame;


public class SysAdmFrame extends JFrame {

	private JPanel contentPane;
	public final UserType usertype;
	public final SystemAdmin sysAdm; 
	private ChangePassword changePass=null;
	private BuildingFrame buildingManage=null;
	private DormAdmManage dormAdmManage=null;
	
	/**
	 * Create the frame.
	 */
	public SysAdmFrame(UserType usertype,SystemAdmin sysAdm) {
		setTitle("\u7CFB\u7EDF\u7BA1\u7406\u5458");
		
		this.usertype=usertype;
		this.sysAdm=sysAdm;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 689);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("\u7CFB\u7EDF\u8BBE\u7F6E");
		mnNewMenu_1.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword(e);
			}
		});
		mntmNewMenuItem.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u9000\u51FA\u767B\u5F55");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			//�˳���¼
			public void actionPerformed(ActionEvent e) {
				exit(e);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1_1 = new JMenu("\u5BBF\u820D\u697C\u7BA1\u7406");
		mnNewMenu_1_1.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		menuBar.add(mnNewMenu_1_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u5BBF\u820D\u697C\u4FE1\u606F\u7BA1\u7406");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����¥��Ϣ����
				buildingManage(e);
			}
		});
		mntmNewMenuItem_2.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		mnNewMenu_1_1.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1_2 = new JMenu("\u5BBF\u7BA1\u4FE1\u606F\u7BA1\u7406");
		mnNewMenu_1_2.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		menuBar.add(mnNewMenu_1_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u5BBF\u7BA1\u7BA1\u7406");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�޹ܹ���
				dormAdmManage(e);
			}
		});
		mntmNewMenuItem_3.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		mnNewMenu_1_2.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1_3 = new JMenu("\u5BBF\u820D\u7BA1\u7406");
		mnNewMenu_1_3.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		menuBar.add(mnNewMenu_1_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u5BBF\u820D\u4FE1\u606F\u7BA1\u7406");
		mntmNewMenuItem_4.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		mnNewMenu_1_3.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_1_4 = new JMenu("\u5B66\u751F\u7BA1\u7406");
		mnNewMenu_1_4.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		menuBar.add(mnNewMenu_1_4);
		
		JMenuItem mntmNewMenuItem_4_1 = new JMenuItem("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406");
		mntmNewMenuItem_4_1.setFont(new Font("΢���ź�", Font.PLAIN, 19));
		mnNewMenu_1_4.add(mntmNewMenuItem_4_1);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	protected void dormAdmManage(ActionEvent e) {
		//�޹���Ϣ����
		if(dormAdmManage==null){
			dormAdmManage=new DormAdmManage();
		}
		dormAdmManage.queryAllDormAdm();
		dormAdmManage.setVisible(true);
	}

	protected void buildingManage(ActionEvent e) {
		// ����¥��Ϣ����
		if(buildingManage==null){
			buildingManage=new BuildingFrame();
		}
		
		buildingManage.queryAllBuilding();
		buildingManage.setVisible(true);
	}
	
	protected void changePassword(ActionEvent e) {
		// �޸�����
		if(changePass==null)
			changePass=new ChangePassword(this.usertype,this.sysAdm.getID());
		changePass.reseti();
		changePass.setVisible(true);
	}

	protected void exit(ActionEvent e) {
		// �˳���¼
		LoginFrame login=new LoginFrame();
		login.setVisible(true);
		this.dispose();
	}
}
