package com.richardbrenkus;

import java.awt.BorderLayout;
import java.util.*;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GUIupdateEmployeeDetails extends GUIaddNewEmployee {
	private JPanel panel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private static String[] employeeNames; 
	
	public GUIupdateEmployeeDetails(JDialog owner, Employee employee, List<Employee> cacheList) {		 
		setResizable(false);
		setMinimumSize(new Dimension(450, 300));
		setTitle("Add new employee");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 463, 427);
		setLocationRelativeTo(owner);
			
		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 79, 97, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 20, 97, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 135, 97, 14);
		panel_1.add(lblNewLabel_2);
		
		textField = new JTextField(employee.getLastName());
		textField.setBounds(10, 45, 196, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(employee.getFirstName());
		textField_1.setBounds(10, 104, 196, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(employee.getPhoneNumber());
		textField_2.setBounds(10, 160, 196, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Shift type");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 191, 64, 20);
		panel_1.add(lblNewLabel_3);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Shift type 1");
		chckbxNewCheckBox.setBounds(10, 221, 97, 23);
		if(employee.getShiftDetails().contains(1))
			chckbxNewCheckBox.setSelected(true);
		panel_1.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Shift type 2");
		chckbxNewCheckBox_1.setBounds(109, 221, 97, 23);
		if(employee.getShiftDetails().contains(2))
			chckbxNewCheckBox.setSelected(true);
		panel_1.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Shift type 3");
		chckbxNewCheckBox_2.setBounds(208, 221, 97, 23);
		if(employee.getShiftDetails().contains(3))
			chckbxNewCheckBox.setSelected(true);
		panel_1.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Shift type 4");
		chckbxNewCheckBox_3.setBounds(307, 221, 97, 23);
		if(employee.getShiftDetails().contains(4))
			chckbxNewCheckBox.setSelected(true);		
		panel_1.add(chckbxNewCheckBox_3);
		
		
		JLabel lblNewLabel_4 = new JLabel("Note");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(235, 79, 48, 14);
		panel_1.add(lblNewLabel_4);
		
		textField_3 = new JTextField(employee.getEmailAddress());
		textField_3.setBounds(235, 104, 200, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
				
		employeeNames = new String[EmployeePool.getAllEmployeeDetails().size()];
		if(!EmployeePool.getAllEmployeeDetails().isEmpty())
			for(int x = 0; x<EmployeePool.getAllEmployeeDetails().size(); x++)
				if(!EmployeePool.getAllEmployeeDetails().get(x).getEmployeeDetailsAsString().equals(employee.getEmployeeDetailsAsString()))
					employeeNames[x] = EmployeePool.getAllEmployeeDetails().get(x).getEmployeeDetailsAsString();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setSize(196, 89);
		scrollPane_1.setLocation(10, 251);
		JList combineWithEmployeesList= new JList();
		combineWithEmployeesList.setToolTipText("likes these coworkers");
		combineWithEmployeesList.setVisibleRowCount(4);
		combineWithEmployeesList.setModel(new AbstractListModel() {
			String[] values = employeeNames;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_1.setViewportView(combineWithEmployeesList);
		panel_1.add(scrollPane_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(235, 251, 200, 89);
		panel_1.add(scrollPane_1_1);
		
		JList avoidEmployeesList = new JList();
		scrollPane_1_1.setViewportView(avoidEmployeesList);
		avoidEmployeesList.setModel(new AbstractListModel() {
			String[] values = employeeNames;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		avoidEmployeesList.setVisibleRowCount(4);
		avoidEmployeesList.setToolTipText("dislikes these coworkers");
		
		ArrayList<String> selectedNamesPrefers = new ArrayList<>();
		ArrayList<String> selectedNamesAvoids = new ArrayList<>();
			
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(242, 351, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedNamesPrefers.addAll(combineWithEmployeesList.getSelectedValuesList());
				selectedNamesAvoids.addAll(avoidEmployeesList.getSelectedValuesList());
				if(textField.getText().toString().isEmpty() || textField_1.getText().toString().isEmpty()) { 
					GUImessageWindow message = new GUImessageWindow(GUIupdateEmployeeDetails.this, "Employee details not updated. Both first and last name must be filled out!");
				}
				else
				{if(!chckbxNewCheckBox.isSelected() && !chckbxNewCheckBox_1.isSelected() && !chckbxNewCheckBox_2.isSelected() && !chckbxNewCheckBox_3.isSelected()) {
					GUImessageWindow message = new GUImessageWindow(GUIupdateEmployeeDetails.this, "You must select at least one shift type to add an employee! Employee details not updated.");
				}
					else {
					cacheList.remove(employee);
					Employee newEmployee = new Employee(textField.getText().toString().trim().toUpperCase(),  //capitalise last name
							//capitalise the 1st letter	of the last name (maybe create a separate string for this later):
						(textField_1.getText().toString().trim().substring(0, 1).toUpperCase() + textField_1.getText().toString().trim().substring(1, textField_1.getText().toString().trim().length())), 
							chckbxNewCheckBox.isSelected(), chckbxNewCheckBox_1.isSelected(), chckbxNewCheckBox_2.isSelected(), 
							chckbxNewCheckBox_3.isSelected(), textField_2.getText().toString(), textField_3.getText().toString(), textField_4.getText().toString(),
							selectedNamesPrefers, selectedNamesAvoids);					
					cacheList.add(newEmployee);
					EmployeePool.updateEmployeeList(cacheList);
					GUImessageWindow message = new GUImessageWindow(GUIupdateEmployeeDetails.this, "The employee details have been updated. Click OK to update the employee database, or click Cancel to revert the changes.");			
					dispose();
					}
				}			
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(346, 351, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("Email address");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(235, 135, 96, 14);
		
		panel_1.add(lblNewLabel_5);
		
		textField_4 = new JTextField(employee.getNote());
		textField_4.setBounds(235, 160, 200, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		
		JLabel lblNewLabel_6 = new JLabel("*");
		lblNewLabel_6.setBounds(78, 22, 13, 14);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("*");
		lblNewLabel_6_1.setBounds(78, 81, 13, 14);
		panel_1.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_3 = new JLabel("* mandatory fields");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_6_3.setBounds(10, 357, 155, 14);
		panel_1.add(lblNewLabel_6_3);
		
		JLabel lblNewLabel_6_2 = new JLabel("*");
		lblNewLabel_6_2.setBounds(73, 196, 13, 14);
		panel_1.add(lblNewLabel_6_2);
		
		setVisible(true);				
		}
}


