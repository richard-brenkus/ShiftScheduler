package com.richardbrenkus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class GUIinnerInputWindow extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private static String[] employeeNames; 
	private static GUIcalendarPanel calendar;
	private Set<String> requestedDays;
	private JTextArea previousDatesTextField;

	public GUIinnerInputWindow(JDialog owner, Employee emp) {
		setModal(true);
		
		setTitle("Create shift request for " + emp.getEmployeeDetailsAsString());
		setBounds(100, 100, 589, 488);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		employeeNames = new String[EmployeePool.getAllEmployeeDetails().size()];
		for(int x = 0; x<EmployeePool.getAllEmployeeDetails().size(); x++)
			if(!EmployeePool.getAllEmployeeDetails().get(x).getEmployeeDetailsAsString().equals(emp.getEmployeeDetailsAsString()))
				employeeNames[x] = EmployeePool.getAllEmployeeDetails().get(x).getEmployeeDetailsAsString();
		requestedDays = new HashSet<String>();
				
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setSize(167, 89);
		scrollPane_1.setLocation(10, 253);
		JList combineWithEmployeesList= new JList();
		combineWithEmployeesList.setToolTipText("Select coworkers the employee likes to work with. If the employee profile contains a list of preferred employees, it will be overwritten by the selection you make in this window. Go to Employee maintenance--Edit employee to define the employee's preferences.");
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
		contentPanel.add(scrollPane_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(187, 253, 167, 89);
		contentPanel.add(scrollPane_1_1);
		
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
		avoidEmployeesList.setToolTipText("Select coworkers the employee does not like to work with. If the employee profile contains a list of preferred employees, it will be overwritten by the selection you make in this window. Go to Employee maintenance--Edit employee to define the employee's preferences.");
		
		JComboBox priorityBox = new JComboBox();
		priorityBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		priorityBox.setSelectedIndex(2);
		priorityBox.setToolTipText("set the priority from 1--highest to 5--lowest");
		priorityBox.setBounds(376, 320, 56, 22);
		contentPanel.add(priorityBox);
		
		JLabel lblNewLabel = new JLabel("Look for combinations with:");
		lblNewLabel.setBounds(10, 228, 167, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Avoid:");
		lblNewLabel_1.setBounds(245, 228, 48, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Priority:");
		lblNewLabel_2.setBounds(376, 295, 48, 14);
		contentPanel.add(lblNewLabel_2);
		
		JInternalFrame calendarFrame = new JInternalFrame("Calendar");
		calendarFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		calendarFrame.setBounds(21, 11, 529, 206);
		contentPanel.add(calendarFrame);
		
		if(RequestPool.requestAlreadyEntered(emp.getEmployeeDetailsAsString())) {
			RequestPool.loadRequest(emp.getEmployeeDetailsAsString());
			calendar = new GUIcalendarPanel(calendarFrame, requestedDays, RequestPool.loadRequest(emp.getEmployeeDetailsAsString()));
		}
		else 
			calendar = new GUIcalendarPanel(calendarFrame, requestedDays);	
		
		JComboBox shiftCountBox = new JComboBox();
		shiftCountBox.setToolTipText("set the total requested number of shifts for the employee");
		shiftCountBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		shiftCountBox.setSelectedIndex(1);
		shiftCountBox.setBounds(376, 251, 56, 22);
		contentPanel.add(shiftCountBox);
		
		JLabel lblNewLabel_3 = new JLabel("Shift count:");
		lblNewLabel_3.setBounds(376, 228, 80, 14);
		contentPanel.add(lblNewLabel_3);
		
		JComboBox weekendBox = new JComboBox();
		weekendBox.setToolTipText("set the number of Saturday or Sunday shifts");
		weekendBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2"}));
		weekendBox.setBounds(455, 251, 56, 22);
		contentPanel.add(weekendBox);
		
		JLabel lblNewLabel_4 = new JLabel("Weekends:");
		lblNewLabel_4.setBounds(455, 228, 84, 14);
		contentPanel.add(lblNewLabel_4);
		
		JButton btnDeleteRequest = new JButton("Delete");
		btnDeleteRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestPool.deleteRequest(emp);
				GUIinnerInputWindow.this.dispose();
				RequestPool.showRequestPool();
			}
		});
		btnDeleteRequest.setVisible(false);
		btnDeleteRequest.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteRequest.setHorizontalTextPosition(SwingConstants.LEFT);
		btnDeleteRequest.setForeground(new Color(0, 0, 0));
		btnDeleteRequest.setToolTipText("Click this button to delete the current request from the request pool");
		btnDeleteRequest.setBounds(455, 320, 84, 22);
		contentPanel.add(btnDeleteRequest);
		
		JLabel lblNewLabel_5 = new JLabel("Click OK to overwrite the previous request, Cancel to keep it, or Delete to remove it.");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setVisible(false);
		lblNewLabel_5.setBounds(10, 353, 550, 14);
		contentPanel.add(lblNewLabel_5);
			
		previousDatesTextField = new JTextArea();
		previousDatesTextField.setToolTipText("the dates entered in the employee's previous request");
		previousDatesTextField.setBounds(10, 378, 550, 20);
		previousDatesTextField.setVisible(false);
		contentPanel.add(previousDatesTextField);
		previousDatesTextField.setColumns(10);
		
		if(RequestPool.employeeHasARequest(emp.getEmployeeDetailsAsString())) {
			btnDeleteRequest.setVisible(true);
			lblNewLabel_5.setVisible(true);
			previousDatesTextField.append(RequestPool.loadRequest(emp.getEmployeeDetailsAsString()).stream().sorted().collect(Collectors.toList()).toString().substring(1, RequestPool.loadRequest(emp.getEmployeeDetailsAsString()).toString().length()-1));
			previousDatesTextField.setVisible(true);
		}
		ArrayList<String> selectedNamesPrefers = new ArrayList<>();
		ArrayList<String> selectedNamesAvoids = new ArrayList<>();
		
		Boolean prefersOrAvoids = true;
		
		calendarFrame.setVisible(true);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int priorityBoxInt = Integer.parseInt(priorityBox.getSelectedItem().toString());
						int shiftCountBoxInt = Integer.parseInt(shiftCountBox.getSelectedItem().toString());
						int weekendBoxInt = Integer.parseInt(weekendBox.getSelectedItem().toString());
						selectedNamesPrefers.addAll(combineWithEmployeesList.getSelectedValuesList());
						selectedNamesAvoids.addAll(avoidEmployeesList.getSelectedValuesList());
						if (requestedDays.isEmpty()) {
							GUImessageWindow message = new GUImessageWindow(GUIinnerInputWindow.this, "You cannot create a shift request without having selected at least one dayfrom the calendar. Shift request not created.");								
							} //close if
						
						else if(RequestPool.requestAlreadyEntered(emp.getEmployeeDetailsAsString())) {
							RequestPool.deleteRequest(emp);
							GUImessageWindow message = new GUImessageWindow(GUIinnerInputWindow.this, "The request pool already contained a request for " + emp.getEmployeeDetailsAsString() + ". The previous request has been replaced with the new one.");
							Request request = new Request(emp, priorityBoxInt, requestedDays, selectedNamesPrefers, selectedNamesAvoids, shiftCountBoxInt, weekendBoxInt);
							}
						else{if(emp.getAvoidedCoworkers().isEmpty() && emp.getPreferredCoworkers().isEmpty()) {						
								Request request = new Request(emp, priorityBoxInt, requestedDays, selectedNamesPrefers, selectedNamesAvoids, shiftCountBoxInt, weekendBoxInt);
								}
							else if (!emp.getAvoidedCoworkers().isEmpty() && emp.getPreferredCoworkers().isEmpty()){
								Request request = new Request(emp, priorityBoxInt, requestedDays, selectedNamesPrefers, emp.getAvoidedCoworkers(), shiftCountBoxInt, weekendBoxInt);	
							}
							else if (emp.getAvoidedCoworkers().isEmpty() && !emp.getPreferredCoworkers().isEmpty()){
								Request request = new Request(emp, priorityBoxInt, requestedDays, emp.getPreferredCoworkers(), selectedNamesAvoids, shiftCountBoxInt, weekendBoxInt);
								}
							else if (!emp.getAvoidedCoworkers().isEmpty() && !emp.getPreferredCoworkers().isEmpty()){
								Request request = new Request(emp, priorityBoxInt, requestedDays, emp.getPreferredCoworkers(), emp.getAvoidedCoworkers(), shiftCountBoxInt, weekendBoxInt);
								}
						}
						GUIinnerInputWindow.this.dispose();
						RequestPool.showRequestPool();
					}	// close actionPerformed
				});		//close ActionListener
			}	
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GUIinnerInputWindow.this.dispose();
					}
				});
			}
		}			
		setLocationRelativeTo(owner);
		setVisible(true);
	}
}
