package com.richardbrenkus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class GUIarchiveDisplayer extends JDialog{
	private JPanel contentPane;
	private JTextArea textArea;
	private Map<Integer, Set<Employee>> lastFiveDays;
	GUImessageWindow message;
	
	public GUIarchiveDisplayer(GUImainWindow window, Map<Integer, Set<Employee>> lastFiveDaysInput) {
		this.lastFiveDays = lastFiveDaysInput;
		if(lastFiveDays.isEmpty())
			message = new GUImessageWindow(window, "The archive is empty. The last five days of the previous month cannot be taken into consideration during next month's calculation.");
		else {
		setTitle("Archive");
		setModal(true);		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		textArea.append("Which employees worked during the last five days of the previous month: ");
		textArea.append("\n");
		for (int x = -4; x<=0; x++)
			if(!lastFiveDays.get(x).isEmpty()) {
				int strLength = lastFiveDays.get(x).stream().map(e -> e.getEmployeeDetailsAsString()).collect(Collectors.toList()).toString().length();
				textArea.append("\n" + (x-1) + ":   " +lastFiveDays.get(x).stream().map(e -> e.getEmployeeDetailsAsString()).collect(Collectors.toList()).toString().substring(1, strLength-1));
			}
			else
				textArea.append("\n"  + (x-1) + ":   none");

					textArea.setEditable(false);
		panel.add(textArea, BorderLayout.CENTER);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		setLocationRelativeTo(window);
		setVisible(true);	
		}
	}
}
