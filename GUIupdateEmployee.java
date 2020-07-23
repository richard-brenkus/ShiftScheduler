package com.richardbrenkus;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIupdateEmployee extends JDialog{
	private JPanel panel;
	private static List<Employee> newEmployeeDatabaseList;
	private final static List<Employee> backUpNewEmployeeDatabaseList = EmployeePool.getAllEmployeeDetails();
	
	public GUIupdateEmployee(Frame owner) {
		
		newEmployeeDatabaseList = new ArrayList<Employee>();
		for (Employee x : EmployeePool.getAllEmployeeDetails())
			newEmployeeDatabaseList.add(x);
				
		setModal(true);
		
		setResizable(false);
		setMinimumSize(new Dimension(450, 300));
		setTitle("Update employee");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(owner);

		panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select the employee you'd like to update:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 35, 414, 36);
		panel_1.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(EmployeePool.getEmployeePoolAsStringArray()));
		comboBox.setBounds(89, 82, 146, 22);
		panel_1.add(comboBox);
				
		JButton btnNewButton = new JButton("Update employee");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(89, 115, 146, 23);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e) {
				try {
				int index = comboBox.getSelectedIndex();
				GUIupdateEmployeeDetails newWindow = new GUIupdateEmployeeDetails(GUIupdateEmployee.this, newEmployeeDatabaseList.get(index), newEmployeeDatabaseList);
				}
				catch(Exception ex) {
					GUImessageWindow message = new GUImessageWindow(GUIupdateEmployee.this, "No employees found");
				}
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
						ShiftPool.resetShiftPool();												
						ShiftPool.shiftCollector(EmployeePool.getAllEmployeeDetails());			
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
						EmployeePool.updateEmployeeList(backUpNewEmployeeDatabaseList);
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


