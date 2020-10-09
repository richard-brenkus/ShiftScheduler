package com.richardbrenkus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Font;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class GUIoutputCalendar extends JFrame {
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public GUIoutputCalendar(Map<Integer, List<Employee>> supercalendar1, Map<Integer, List<Employee>> supercalendar2, Map<Integer, List<Employee>> supercalendar3, Map<Integer, List<Employee>>supercalendar4, String month, String year, List<Integer> wknds ) {
		setTitle("Month Calendar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();

		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Date", "Shift type 1", "Shift type 2", "Shift type 3", "Shift type 4"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panel.add(table, BorderLayout.CENTER);
		contentPane.add(scrollPane, BorderLayout.CENTER);
							
		for(int i = 1; i<=supercalendar1.size(); i++) {
			String day = String.format("%02d", i);
			table.setValueAt(day + "-" + month + "-" + year, i+1, 0);
			
			if(wknds.contains(i)) {
				table.setValueAt(day + "-" + month + "-" + year + " ***** ", i+1, 0);
				
			}
		}

		
		table.setValueAt("Date", 0, 0);
		table.setValueAt("Shift type 1", 0, 1);
		table.setValueAt("Shift type 2", 0, 2);
		table.setValueAt("Shift type 3", 0, 3);
		table.setValueAt("Shift type 4", 0, 4);
			
		for(int i = 1; i<=supercalendar1.size(); i++) 
			if(!supercalendar1.get(i).isEmpty())
			table.setValueAt("MUDr. " + supercalendar1.get(i).stream().map(e -> e.getLastName()).collect(Collectors.toList()).get(0).toString(), i+1, 1);
			else
			table.setValueAt(" ", i+1, 1);
		
		for(int i = 1; i<=supercalendar2.size(); i++) 
			if(!supercalendar2.get(i).isEmpty())
				table.setValueAt("MUDr. "+ supercalendar2.get(i).stream().map(e -> e.getLastName()).collect(Collectors.toList()).get(0).toString(), i+1, 2);
			else
				table.setValueAt(" ", i+1, 2);

		for(int i = 1; i<=supercalendar3.size(); i++) 
			if(!supercalendar3.get(i).isEmpty())
				table.setValueAt("MUDr. " + supercalendar3.get(i).stream().map(e -> e.getLastName()).collect(Collectors.toList()).get(0).toString(), i+1, 3);
			else
				table.setValueAt(" ", i+1, 3);

		for(int i = 1; i<=supercalendar4.size(); i++)
			if(!supercalendar4.get(i).isEmpty())
				table.setValueAt("MUDr. " + supercalendar4.get(i).stream().map(e -> e.getLastName()).collect(Collectors.toList()).get(0).toString(), i+1, 4);
			else
			table.setValueAt(" ", i+1, 4);
				
		setVisible(true);

	}

}
