package com.view.systemAdm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dao.SysAdminDao;

@SuppressWarnings("serial")
public class AddBuildingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField newBuildingName;
	@SuppressWarnings("rawtypes")
	private JComboBox buildingTypeCombo;
	private JTextArea remarksTextArea ;
	private static final String[] buildingType={"����","Ů��"};
	private BuildingFrame buildingFrame;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddBuildingFrame(BuildingFrame building) {
		
		this.buildingFrame=building;
		
		setTitle("\u6DFB\u52A0\u5BBF\u820D\u697C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("\u5BBF\u820D\u697C\u540D\u79F0");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel.setBounds(75, 59, 146, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BBF\u820D\u697C\u7C7B\u578B");
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(75, 124, 146, 29);
		contentPane.add(lblNewLabel_1);
		
		newBuildingName = new JTextField();
		newBuildingName.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		newBuildingName.setBounds(230, 56, 130, 32);
		contentPane.add(newBuildingName);
		newBuildingName.setColumns(10);
		
		buildingTypeCombo = new JComboBox();
		buildingTypeCombo.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		buildingTypeCombo.setModel(new DefaultComboBoxModel(buildingType));
		buildingTypeCombo.setBounds(230, 122, 130, 32);
		contentPane.add(buildingTypeCombo);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmAddBuilding(e);
			}
		});
		btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		btnNewButton.setBounds(402, 300, 98, 37);
		contentPane.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(432, 213, -319, 82);
		contentPane.add(textArea);
		
		remarksTextArea = new JTextArea();
		remarksTextArea.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		remarksTextArea.setLineWrap(true);
		remarksTextArea.setBounds(172, 197, 273, 82);
		contentPane.add(remarksTextArea);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u5907\u6CE8");
		lblNewLabel_1_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(75, 191, 111, 29);
		contentPane.add(lblNewLabel_1_1);
	}

	private void reset(){
		this.newBuildingName.setText("");
		this.remarksTextArea.setText("");
	}
	
	protected void confirmAddBuilding(ActionEvent e) {
		// TODO Auto-generated method stub
		String newBuildingName=this.newBuildingName.getText();
		String buildingType=(String)this.buildingTypeCombo.getSelectedItem();
		String remarks=this.remarksTextArea.getText();
		if("".equals(newBuildingName)||newBuildingName==null){
			JOptionPane.showMessageDialog(this, "������Ҫ���ӵ�����¥����");
		}else{
			SysAdminDao sysAdmDao=new SysAdminDao();
			boolean success=sysAdmDao.insertBuilding(newBuildingName,buildingType,remarks);
			if(success){
				JOptionPane.showMessageDialog(this, "����ɹ���");
				buildingFrame.queryAllBuilding();
			}else{
				JOptionPane.showMessageDialog(this, "����ʧ��");
			}
			reset();
		}
	}
}
