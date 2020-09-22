package com.richardbrenkus;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIshiftInputWindow extends JDialog {
	private JPanel contentPane;
	private JPanel innerContentPane;
	private List<Employee> inputList;
	private JButton btnNewButton;
	
	public GUIshiftInputWindow(Set<Employee> restoredList, Frame owner, String windowName) {
		setModal(true);
		setTitle(windowName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(owner);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		inputList = new ArrayList<>();
		inputList.addAll(restoredList);
		inputList.sort((e1, e2) -> e1.getEmployeeDetailsAsString().compareTo(e2.getEmployeeDetailsAsString()) );

		for(Employee emp : inputList) {
			JButton btnNewButton = new JButton(emp.getEmployeeDetailsAsString());
			panel.add(btnNewButton);
			if(RequestPool.requestAlreadyEntered(emp.getEmployeeDetailsAsString(), emp.getShiftDetails()))
				btnNewButton.setBackground(Color.GREEN);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIinnerInputWindow innerWindow = new GUIinnerInputWindow(GUIshiftInputWindow.this, emp);
					if(RequestPool.requestAlreadyEntered(emp.getEmployeeDetailsAsString(), emp.getShiftDetails()))
						btnNewButton.setBackground(Color.GREEN);
				}
			});
		}		
		setVisible(true);
	}
}
	

