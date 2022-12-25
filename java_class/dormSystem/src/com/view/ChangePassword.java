package com.view;

import java.awt.Font;
import java.awt.event.WindowAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.bean.UserType;
import com.dao.SysAdminDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	
	public final UserType userType;
	public final String userID;
	private JPasswordField reNewPass;
	private JPasswordField newPass;
	private JPasswordField oldPass;

	/**
	 * Create the frame.
	 */
	public ChangePassword(UserType usertype,String userid) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.userType=usertype;
		this.userID=userid;
		
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(100, 100, 528, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u7528\u6237\uFF1A");
		lblNewLabel.setIcon(new ImageIcon("D:\\Java\\eclipse workplace\\dormSystem\\src\\images\\\u8D26\u53F7.png"));
		lblNewLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		lblNewLabel.setBounds(36, 21, 147, 29);
		contentPane.add(lblNewLabel);
		
		JLabel userInfo = new JLabel("["+userType+"] "+userID);
		userInfo.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		userInfo.setBounds(176, 21, 277, 29);
		contentPane.add(userInfo);
		
		JLabel lblNewLabel_2 = new JLabel("\u539F\u5BC6\u7801");
		lblNewLabel_2.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(102, 90, 108, 29);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\u65B0\u5BC6\u7801");
		lblNewLabel_2_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(102, 160, 108, 29);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_2_2.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(86, 230, 108, 29);
		contentPane.add(lblNewLabel_2_2);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmChange(e);
			}
		});
		btnNewButton.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		btnNewButton.setBounds(405, 288, 101, 37);
		contentPane.add(btnNewButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(e);
			}
		});
		resetButton.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		resetButton.setBounds(302, 287, 101, 37);
		contentPane.add(resetButton);
		
		reNewPass = new JPasswordField();
		reNewPass.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		reNewPass.setBounds(230, 230, 130, 32);
		contentPane.add(reNewPass);
		
		newPass = new JPasswordField();
		newPass.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		newPass.setBounds(230, 160, 130, 32);
		contentPane.add(newPass);
		
		oldPass = new JPasswordField();
		oldPass.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		oldPass.setBounds(230, 90, 130, 32);
		contentPane.add(oldPass);
		

        
	}
	
	protected void confirmChange(ActionEvent e) {
		// »∑»œ–ﬁ∏ƒ√‹¬Î
		String oldPassword=String.valueOf(this.oldPass.getPassword());
		String newPassword=String.valueOf(this.newPass.getPassword());
		String reNewPassword=String.valueOf(this.reNewPass.getPassword());
		if("".equals(oldPassword)||oldPassword==null){
			JOptionPane.showMessageDialog(this, "«Î ‰»Î‘≠√‹¬Î");
		}
		else if("".equals(newPassword)||newPassword==null){
			JOptionPane.showMessageDialog(this, "«Î ‰»Î–¬√‹¬Î");
		}
		else if("".equals(reNewPassword)||reNewPassword==null){
			JOptionPane.showMessageDialog(this, "«Î‘Ÿ¥Œ ‰»Î–¬√‹¬Î");
		}
		else{
			if("œµÕ≥π‹¿Ì‘±".equals(userType.getType())){
				SysAdminDao sysAdmDao=new SysAdminDao();
				boolean updated=sysAdmDao.changePassword(userID, oldPassword,newPassword);
				if(updated){
					JOptionPane.showMessageDialog(this, "–ﬁ∏ƒ≥…π¶");
					reseti();
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(this, "”√ªß√˚ªÚ√‹¬Î¥ÌŒÛ");
					reseti();
				}
			}
			if("Àﬁ…·π‹¿Ì‘±".equals(userType.getType())){
				return;
			}
			
			if("—ß…˙".equals(userType.getType())){
				return;
			}
		}
	}

	protected void reset(ActionEvent e) {
		// ÷ÿ÷√
		oldPass.setText("");
		newPass.setText("");
		reNewPass.setText("");
	}
	
	public void reseti() {
		// TODO Auto-generated method stub
		oldPass.setText("");
		newPass.setText("");
		reNewPass.setText("");
	}
}
