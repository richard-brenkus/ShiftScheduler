package com.richardbrenkus;

import java.io.Serializable;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIdeleteEmployee extends JDialog implements Serializable{
	private JPanel panel;
	private static List<Employee> newEmployeeDatabaseList;
	
	public GUIdeleteEmployee(Frame owner) {
		newEmployeeDatabaseList = new ArrayList<Employee>();
		for (Employee x : EmployeePool.getAllEmployeeDetails())
			newEmployeeDatabaseList.add(x);
				
		setModal(true);
		setResizable(false);
		setMinimumSize(new Dimension(450, 300));
		setTitle("Delete employee");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(owner);

		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select the employee you'd like to delete:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 35, 414, 36);
		panel_1.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(EmployeePool.getEmployeePoolAsStringArray()));
		comboBox.setBounds(89, 82, 146, 22);
		panel_1.add(comboBox);
				
		JButton btnNewButton = new JButton("Delete employee");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(89, 115, 146, 23);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e) {
				try {
				int index = comboBox.getSelectedIndex();
				newEmployeeDatabaseList.remove(newEmployeeDatabaseList.get(index));{
				GUImessageWindow message = new GUImessageWindow(GUIdeleteEmployee.this, "The selected employee has been deleted. Click OK to update the employee database or click Cancel to revert the change.");
				}
				}
				catch(Exception ex) {
					GUImessageWindow message = new GUImessageWindow(GUIdeleteEmployee.this, "No employees found");
				}
			}
		});
		
		JButton btnNewButton_22 = new JButton("Delete all employees");
		btnNewButton_22.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_22.setBounds(89, 192, 146, 23);
		panel_1.add(btnNewButton_22);
		btnNewButton_22.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e) {
				newEmployeeDatabaseList.removeAll(newEmployeeDatabaseList);
				GUImessageWindow message = new GUImessageWindow(GUIdeleteEmployee.this, "All employees have been deleted. Click OK to update the employee database or click Cancel to revert the change.");
						}
		});
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EmployeePool.updateEmployeeList(newEmployeeDatabaseList);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				setVisible(true);
			}
		}
	}
}
