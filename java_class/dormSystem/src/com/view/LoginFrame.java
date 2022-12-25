package com.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.bean.SystemAdmin;
import com.bean.UserType;
import com.dao.SysAdminDao;
import com.view.systemAdm.SysAdmFrame;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userID;
	private JPasswordField userPass;
	@SuppressWarnings("rawtypes")
	private JComboBox userTypeComb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("\u5B66\u751F\u5BBF\u820D\u7BA1\u7406\u7CFB\u7EDF\u767B\u5F55\u9875\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u5BBF\u820D\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 45));
		lblNewLabel.setBounds(135, -8, 397, 131);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8D26\u53F7");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Java\\eclipse workplace\\dormSystem\\src\\images\\\u8D26\u53F7.png"));
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(130, 127, 165, 40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u5BC6\u7801");
		lblNewLabel_1_1.setIcon(new ImageIcon("D:\\Java\\eclipse workplace\\dormSystem\\src\\images\\\u5BC6\u7801.png"));
		lblNewLabel_1_1.setFont(new Font("΢���ź�", Font.PLAIN, 28));
		lblNewLabel_1_1.setBounds(130, 211, 165, 40);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("\u7528\u6237\u7C7B\u578B");
		lblNewLabel_1_2.setIcon(new ImageIcon("D:\\Java\\eclipse workplace\\dormSystem\\src\\images\\\u968F\u673A\u7528\u6237.png"));
		lblNewLabel_1_2.setFont(new Font("΢���ź�", Font.PLAIN, 28));
		lblNewLabel_1_2.setBounds(96, 290, 165, 40);
		contentPane.add(lblNewLabel_1_2);
		
		userID = new JTextField();
		userID.setFont(new Font("΢���ź�", Font.PLAIN, 26));
		userID.setBounds(275, 130, 240, 40);
		contentPane.add(userID);
		userID.setColumns(10);
		
		userPass = new JPasswordField();
		userPass.setFont(new Font("΢���ź�", Font.PLAIN, 28));
		userPass.setBounds(275, 212, 240, 40);
		contentPane.add(userPass);
		
		userTypeComb = new JComboBox();
		userTypeComb.setFont(new Font("΢���ź�", Font.PLAIN, 26));
		userTypeComb.setModel(new DefaultComboBoxModel (new UserType[]{UserType.SYSTEMADMIN,UserType.DORMADMIN,UserType.STUDENT}));
		userTypeComb.setBounds(275, 292, 240, 40);
		contentPane.add(userTypeComb);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginButton(e);
			}
		});
		button.setIcon(new ImageIcon("D:\\Java\\eclipse workplace\\dormSystem\\src\\images\\\u767B\u5F55.png"));
		button.setFont(new Font("΢���ź�", Font.PLAIN, 28));
		button.setBounds(515, 375, 130, 49);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//loginButton(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon("D:\\Java\\eclipse workplace\\dormSystem\\src\\images\\227\u6CE8\u518C\u3001\u6DFB\u52A0\u597D\u53CB.png"));
		btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 28));
		btnNewButton.setBounds(375, 375, 130, 49);
		contentPane.add(btnNewButton);
		
		btnNewButton.setFocusable(false);
		button.setFocusable(false);
		
		setLocationRelativeTo(null);
	}

	protected void loginButton(ActionEvent e) {
		// TODO Auto-generated method stub
		String userID=this.userID.getText();
		String password=String.valueOf(this.userPass.getPassword());
		UserType userType=(UserType) this.userTypeComb.getSelectedItem();
		if("ϵͳ����Ա".equals(userType.getType())){
			SysAdminDao sysAdmDao=new SysAdminDao();
			SystemAdmin sysAdm=sysAdmDao.selectSysAdm(userID, password);
			if(sysAdm==null){
				JOptionPane.showMessageDialog(this, "�û������������");
				return;
			}
			SysAdmFrame sysAdmFrame=new SysAdmFrame(userType,sysAdm);
			sysAdmFrame.setVisible(true);
			this.dispose();
		}
			
		if("�������Ա".equals(userType.getType())){
			return;
		}
		
		if("ѧ��".equals(userType.getType())){
			return;
		}
		
	}
}
