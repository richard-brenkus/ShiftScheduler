package com.richardbrenkus;

import java.awt.BorderLayout;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GUIrequestPoolDisplayer extends JFrame{
	private JPanel contentPane;
	private JTable table;
	private List<Request> requestPool;
	GUImessageWindow message;
	
	public GUIrequestPoolDisplayer(List<Request> requestPoolInput, GUImainWindow window) {
		
		this.requestPool = requestPoolInput;
		if(requestPool.isEmpty())
			message = new GUImessageWindow(window, "The request pool is empty. Click on 'Manage shift schedule' and then on 'Shift type 1-4' to add shift requests, or add employees first.");
		else {
		setTitle("Request pool");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();

		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		int rows = requestPool.size();
		
		table = new JTable();
		table.setModel(new DefaultTableModel(rows+2, 7));

		table.getColumnModel().getColumn(0).setPreferredWidth(146);
		table.getColumnModel().getColumn(1).setPreferredWidth(45);
		table.getColumnModel().getColumn(2).setPreferredWidth(420);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(117);
		table.getColumnModel().getColumn(5).setPreferredWidth(104);
		table.getColumnModel().getColumn(6).setPreferredWidth(131);
		table.setCellSelectionEnabled(true);
		table.getTableHeader().setReorderingAllowed(true);
		
		table.setValueAt("Name", 0, 0);
		table.setValueAt("Priority", 0, 1);
		table.setValueAt("Dates", 0, 2);
		table.setValueAt("Prefers to work with", 0, 3);
		table.setValueAt("Would like to avoid", 0, 4);
		table.setValueAt("Amount of shifts", 0, 5);
		table.setValueAt("Amount of weekends", 0, 6);

		for(int x = 0; x<requestPool.size(); x++) {
			table.setValueAt(requestPool.get(x).getEmployee().getEmployeeDetailsAsString(), x+2, 0);
		}
		for(int x = 0; x<requestPool.size(); x++)
			table.setValueAt(requestPool.get(x).getPriority(), x+2, 1);
		for(int x = 0; x<requestPool.size(); x++)
			table.setValueAt(requestPool.get(x).getRequestedDates(), x+2, 2);
		for(int x = 0; x<requestPool.size(); x++)
			table.setValueAt(requestPool.get(x).getPrefers(), x+2, 3);
		for(int x = 0; x<requestPool.size(); x++)
			table.setValueAt(requestPool.get(x).getAvoids(), x+2, 4);
		for(int x = 0; x<requestPool.size(); x++)
			table.setValueAt(requestPool.get(x).getShiftCount(), x+2, 5);
		for(int x = 0; x<requestPool.size(); x++)
			table.setValueAt(requestPool.get(x).getWeekendCount(), x+2, 6);
		
		panel.add(table, BorderLayout.CENTER);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		setVisible(true);
		}
	}
}
