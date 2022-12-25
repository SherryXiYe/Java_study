package com.view.systemAdm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.bean.DormAdmin;
import com.bean.UserType;
import com.dao.SysAdminDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddDormAdm extends JFrame {

	private JPanel contentPane;
	private JTextField userID;
	private JTextField Name;
	private JPasswordField password;
	@SuppressWarnings("rawtypes")
	private JComboBox sex;
	private JTextField buildingName;
	private static final String[] sexType={"��","Ů"};
	private DormAdmManage dormAdmManage;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddDormAdm(DormAdmManage dormA) {
		
		this.dormAdmManage=dormA;
		
		setTitle("\u589E\u52A0\u5BBF\u820D\u7BA1\u7406\u5458");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 443, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("\u5BBF\u7BA1\u8D26\u53F7");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel.setBounds(60, 40, 146, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BBF\u7BA1\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(60, 100, 145, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BBF\u7BA1\u59D3\u540D");
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(60, 160, 146, 29);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u5BBF\u820D\u697C\u540D\u79F0");
		lblNewLabel_3.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(56, 280, 146, 29);
		contentPane.add(lblNewLabel_3);
		
		userID = new JTextField();
		userID.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		userID.setColumns(10);
		userID.setBounds(199, 38, 130, 30);
		contentPane.add(userID);
		
		sex = new JComboBox();
		sex.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		sex.setModel(new DefaultComboBoxModel(sexType));
		sex.setBounds(199, 220, 130, 30);
		contentPane.add(sex);
		
		JLabel lblNewLabel_2_1 = new JLabel("\u5BBF\u7BA1\u6027\u522B");
		lblNewLabel_2_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(60, 220, 117, 29);
		contentPane.add(lblNewLabel_2_1);
		
		Name = new JTextField();
		Name.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		Name.setColumns(10);
		Name.setBounds(199, 156, 130, 30);
		contentPane.add(Name);
		
		password = new JPasswordField();
		password.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		password.setBounds(199, 100, 130, 35);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertDormAdm(e);
			}
		});
		btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		btnNewButton.setBounds(307, 334, 98, 37);
		contentPane.add(btnNewButton);
		
		buildingName = new JTextField();
		buildingName.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		buildingName.setColumns(10);
		buildingName.setBounds(199, 280, 130, 30);
		contentPane.add(buildingName);
		
		setLocationRelativeTo(null);
	}

	private void reset(){
		this.userID.setText("");
		this.password.setText("");
		this.Name.setText("");
		this.buildingName.setText("");
	}
	
	private boolean is_empty(String s){
		if("".equals(s)||s==null){
			return true;
		}
		return false;
	}
	//�����޹�
	protected void insertDormAdm(ActionEvent e) {
		String userID=this.userID.getText();
		String sex=(String)this.sex.getSelectedItem();
		String name=this.Name.getText();
		String password=String.valueOf(this.password.getPassword());
		String building=this.buildingName.getText();
		if(is_empty(userID)||is_empty(sex)||is_empty(name)||is_empty(password)||is_empty(building)){
			JOptionPane.showMessageDialog(this, "������ȫ����Ϣ");
		}else{
			DormAdmin dormAdm=new DormAdmin(userID,name,password,building,sex);
			SysAdminDao sysAdmDao=new SysAdminDao();
			boolean success=sysAdmDao.insertDormAdm(dormAdm);
			if(success){
				JOptionPane.showMessageDialog(this, "����ɹ���");
				dormAdmManage.queryAllDormAdm();
			}else{
				JOptionPane.showMessageDialog(this, "����ʧ��,����������");
			}
			reset();
		}
	}
}
