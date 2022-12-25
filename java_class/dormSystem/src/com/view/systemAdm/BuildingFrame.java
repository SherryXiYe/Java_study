package com.view.systemAdm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.bean.Building;
import com.dao.SysAdminDao;

@SuppressWarnings("serial")
public class BuildingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField buildingName;
	private JTable buildingTable;
	private DefaultTableModel dtm=null;
	private AddBuildingFrame addBuilding=null;
	private JComboBox sexTypeComb;
	private JButton changeButton;
	private JButton deleteButton;
	private static Building selectBuilding=new Building();
	
	@SuppressWarnings("unused")
	private static final String[] sexType={"","男寝","女寝"};

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BuildingFrame() {
		setTitle("\u5BBF\u820D\u697C\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 753, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("\u5BBF\u820D\u697C\u540D\u79F0");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(48, 21, 108, 29);
		contentPane.add(lblNewLabel);
		
		buildingName = new JTextField();
		buildingName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		buildingName.setBounds(166, 20, 130, 30);
		contentPane.add(buildingName);
		buildingName.setColumns(10);
		
		JButton queryButton = new JButton("\u67E5\u8BE2");
		queryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryBuilding(e);
			}
		});
		queryButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		queryButton.setBounds(331, 80, 89, 32);
		contentPane.add(queryButton);
		
		JButton insertButton = new JButton("\u589E\u52A0");
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertBuilding(e);
			}
		});
		insertButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		insertButton.setBounds(428, 80, 89, 32);
		contentPane.add(insertButton);
		
		changeButton = new JButton("\u4FEE\u6539");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBuilding(e);
			}
		});
		changeButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		changeButton.setBounds(524, 80, 89, 32);
		contentPane.add(changeButton);
		
		deleteButton = new JButton("\u5220\u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBuilding(e);
			}
		});
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		deleteButton.setBounds(617, 80, 89, 32);
		contentPane.add(deleteButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 123, 675, 209);
		contentPane.add(scrollPane);
		
		buildingTable = new JTable();
		buildingTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectRow(e);
			}
		});
		//设置用户不可拖动
		buildingTable.getTableHeader().setReorderingAllowed(false);
		//设置表格高度
		buildingTable.setRowHeight(20);
		//设置表格居中显示
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		buildingTable.setDefaultRenderer(Object.class, r);
		
		buildingTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5BBF\u820D\u697C\u540D\u79F0", "\u5BBF\u820D\u697C\u7C7B\u578B", "\u5BBF\u7BA1\u6570\u91CF", "\u5BBF\u820D\u6570\u91CF", "\u5907\u6CE8"
			}
		));
		buildingTable.setFont(new Font("SimSun", Font.PLAIN, 12));
		scrollPane.setViewportView(buildingTable);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BBF\u820D\u697C\u7C7B\u578B");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(352, 21, 108, 29);
		contentPane.add(lblNewLabel_1);
		
		sexTypeComb = new JComboBox();
		sexTypeComb.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		sexTypeComb.setModel(new DefaultComboBoxModel(sexType));
		sexTypeComb.setBounds(470, 20, 130, 30);
		contentPane.add(sexTypeComb);
		
		this.deleteButton.setFocusable(false);
		this.changeButton.setFocusable(false);
		queryButton.setFocusable(false);
		insertButton.setFocusable(false);
		
		dtm=(DefaultTableModel)buildingTable.getModel();
		queryAllBuilding();
	}
	
	private boolean isEmpty(String s){
		if("".equals(s)||s==null)
			return true;
		return false;
	}
	//更新宿舍楼(存储过程)
	protected void updateBuilding(ActionEvent e) {
		String  newName=this.buildingName.getText();
		String sexType=(String)this.sexTypeComb.getSelectedItem();
		if(isEmpty(newName)&&isEmpty(sexType)){
			JOptionPane.showMessageDialog(this, "请输入要更新的信息");
			return;
		}
		if(isEmpty(sexType)){
			sexType=selectBuilding.getType();
		}
		if(isEmpty(newName)){
			newName=selectBuilding.getName();
		}
		boolean confirmUpdate=JOptionPane.showConfirmDialog(this, "确定要更新该栋宿舍楼吗？","正在更新……",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION;
		if(confirmUpdate){
			SysAdminDao sysAdmDao=new SysAdminDao();
			boolean success=sysAdmDao.updateBuilding(selectBuilding.getName(),newName,sexType);
			if(success){
				JOptionPane.showMessageDialog(this, "更新成功！");
				queryAllBuilding();
			}else{
				JOptionPane.showMessageDialog(this, "更新失败");
			}
		}
	}

	//删除宿舍楼
	protected void deleteBuilding(ActionEvent e) {
		boolean confirmDelete=JOptionPane.showConfirmDialog(this, "确定要删除该栋宿舍楼吗？","正在删除……",JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION;
		if(confirmDelete){
			SysAdminDao sysAdmDao=new SysAdminDao();
			boolean success=sysAdmDao.deleteBuilding(selectBuilding.getName());
			if(success){
				JOptionPane.showMessageDialog(this, "删除成功！");
				queryAllBuilding();
			}else{
				JOptionPane.showMessageDialog(this, "删除失败");
			}
		}
	}

	//鼠标点击某一行，选中改行
	protected void selectRow(MouseEvent e) {
		selectBuilding.setName(dtm.getValueAt(this.buildingTable.getSelectedRow(), 0).toString());
		selectBuilding.setType(dtm.getValueAt(this.buildingTable.getSelectedRow(), 1).toString());
		this.changeButton.setEnabled(true);
		this.deleteButton.setEnabled(true);
	}

	//查询宿舍楼
	@SuppressWarnings("unchecked")
	protected void queryBuilding(ActionEvent e) {
		String buildingName=this.buildingName.getText();
		String sexType=(String)this.sexTypeComb.getSelectedItem();
		if(("".equals(buildingName)||buildingName==null)&&("".equals(sexType)||sexType==null)){
			queryAllBuilding();
			return;
		}
		Building queryBuilding=new Building();
		queryBuilding.setName(buildingName);
		queryBuilding.setType(sexType);
		dtm.setRowCount(0);
		SysAdminDao sysAdmDao=new SysAdminDao();
		List<Building> allBuildingList=sysAdmDao.queryBuilding(queryBuilding);
		for(Building bu:allBuildingList){
			@SuppressWarnings("rawtypes")
			Vector v=new Vector();
			v.add(bu.getName());
			v.add(bu.getType());
			v.add(bu.getAdmNum());
			v.add(bu.getDormNum());
			v.add(bu.getRemarks());
			dtm.addRow(v);
		}
	}

	//增加宿舍楼
	protected void insertBuilding(ActionEvent e) {
		if(addBuilding==null){
			addBuilding=new AddBuildingFrame(this);
		}
		addBuilding.setVisible(true);
	}

	//查询全部宿舍楼
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryAllBuilding(){
		dtm.setRowCount(0);
		SysAdminDao sysAdmDao=new SysAdminDao();
		List<Building> allBuildingList=sysAdmDao.queryAllBuilding();
		for(Building bu:allBuildingList){
			Vector v=new Vector();
			v.add(bu.getName());
			v.add(bu.getType());
			v.add(bu.getAdmNum());
			v.add(bu.getDormNum());
			v.add(bu.getRemarks());
			dtm.addRow(v);
		}
		this.changeButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
	}
}
