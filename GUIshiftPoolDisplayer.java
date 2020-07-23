package com.richardbrenkus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUIshiftPoolDisplayer extends JDialog{
	private JPanel contentPane;
	private JTextArea textArea;
	private List<List<Employee>> shiftPoolDatabase;
	GUImessageWindow message;
	
	public GUIshiftPoolDisplayer(List<List<Employee>> shiftPoolInput, GUImainWindow window) {
		this.shiftPoolDatabase = shiftPoolInput;
		if(shiftPoolDatabase.isEmpty())
			message = new GUImessageWindow(window, "The shift pool is empty. Click on 'Create shift pool', or add employees first.");
		else {
		
		setTitle("Shift pool");
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		textArea = new JTextArea();
		textArea.setBackground(Color.lightGray);

		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		textArea.append("\n" + "Shift type 1: "+ "\n");
		for (String s: shiftPoolDatabase.get(0).stream().filter(e -> e.getShiftDetails().contains(1)).map(emp -> emp.getEmployeeDetailsAsString()).collect(Collectors.toList()))
			textArea.append(s + "; ");
		textArea.append("\n");
		textArea.append("\n" + "Shift type 2: " + "\n");
		for (String s: shiftPoolDatabase.get(1).stream().filter(e -> e.getShiftDetails().contains(2)).map(emp -> emp.getEmployeeDetailsAsString()).collect(Collectors.toList()))
			textArea.append(s + "; ");
		textArea.append("\n");
		textArea.append("\n" + "Shift type 3: " +  "\n");
		for (String s: shiftPoolDatabase.get(2).stream().filter(e -> e.getShiftDetails().contains(3)).map(emp -> emp.getEmployeeDetailsAsString()).collect(Collectors.toList()))
			textArea.append(s + "; ");
		textArea.append("\n");
		textArea.append("\n" + "Shift type 4: " +  "\n");
		for (String s: shiftPoolDatabase.get(3).stream().filter(e -> e.getShiftDetails().contains(4)).map(emp -> emp.getEmployeeDetailsAsString()).collect(Collectors.toList()))
			textArea.append(s + "; ");
					
		textArea.setEditable(false);
		panel.add(textArea, BorderLayout.CENTER);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		setLocationRelativeTo(window);
		setVisible(true);	
		}
	}
}
