package com.richardbrenkus;

import java.awt.BorderLayout;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GUIemployeeListDisplayer extends JFrame{
	
	private JPanel contentPane;
	private JTable table;
	private List<Employee> employeePool;
	GUImessageWindow message;
	
	public GUIemployeeListDisplayer (List<Employee> employeePoolInput, GUImainWindow window) {
		this.employeePool = employeePoolInput;
		if(employeePool.isEmpty())
			message = new GUImessageWindow(window, "The employee pool is empty. Click on 'Employee maintenance' to add employees.");
		else {
			setTitle("Employee pool");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			JPanel panel = new JPanel();

			JScrollPane scrollPane = new JScrollPane(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			int rows = employeePool.size();
			
			table = new JTable();
			table.setModel(new DefaultTableModel(rows+2, 3));

			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(150);

			table.setCellSelectionEnabled(true);
			table.getTableHeader().setReorderingAllowed(true);
			
			table.setValueAt("Name", 0, 0);
			table.setValueAt("Phone number", 0, 1);
			table.setValueAt("Shift types", 0, 2);


//sort the employees by their full name:
			Comparator<Employee> byName = Comparator.comparing(Employee::getEmployeeDetailsAsString);						
			employeePool.sort(byName);
			
//fill out the table to be displayed:			
			for(int x = 0; x<employeePool.size(); x++)
				table.setValueAt(employeePool.get(x).getEmployeeDetailsAsString(), x+2, 0);
			for(int x = 0; x<employeePool.size(); x++)
				if(!employeePool.get(x).getPhoneNumber().isEmpty())
					table.setValueAt(employeePool.get(x).getPhoneNumber(), x+2, 1);
				else
					table.setValueAt("not available", x+2, 1);
			for(int x = 0; x<employeePool.size(); x++)
				table.setValueAt(employeePool.get(x).getShiftDetails(), x+2, 2);
			
			panel.add(table, BorderLayout.CENTER);
			contentPane.add(scrollPane, BorderLayout.CENTER);
			setLocationRelativeTo(window);
			setVisible(true);
			}
		}
}
