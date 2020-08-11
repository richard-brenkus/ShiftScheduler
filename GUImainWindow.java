package com.richardbrenkus;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.Dialog;

import javax.swing.JTable;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.InputStream;

import javax.swing.JInternalFrame;
import javax.swing.JPopupMenu;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GUImainWindow extends JFrame {

	private JPanel contentPane;
	private static boolean avoidActive, preferActive, previousMonth, archiveNext;;
	private static Supercalendar spcl;
	private static GUIhelp helpPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUImainWindow frame = new GUImainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public GUImainWindow () {
		
		setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(false);	
		
		setTitle("Shift Scheduler 2020");
		setBounds(100, 100, 896, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton = new JButton("Manage shift schedule");
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Employee maintenance");
		panel.add(btnNewButton_2);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		layeredPane.setLayer(panel_3, 1);
		panel_3.setVisible(false);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8);
		
		JButton btnNewButton_14 = new JButton("Shift type 1");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ShiftPool.returnShiftType1().isEmpty()) {
					GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "The shift pool is empty. Click on 'Create shift pool', or add employees with Shift type 1 and create a shift pool!");
				}
				else {
				GUIshiftInputWindow shiftType1 = new GUIshiftInputWindow(ShiftPool.returnShiftType1(), GUImainWindow.this, "Shift type 1");
				}
			}
		});
		panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_8.add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton("Shift type 2");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ShiftPool.returnShiftType2().isEmpty()) {
					GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "The shift pool is empty. Click on 'Create shift pool', or add employees with Shift type 2 and create a shift pool!");

				}
				else {
					GUIshiftInputWindow shiftType2 = new GUIshiftInputWindow(ShiftPool.returnShiftType2(), GUImainWindow.this, "Shift types 2");
				}
			}
		});
		panel_8.add(btnNewButton_15);
		
		JButton btnNewButton_66 = new JButton("Shift type 3");
		btnNewButton_66.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ShiftPool.returnShiftType3().isEmpty()) {
					GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "The shift pool is empty. Click on 'Create shift pool', or add employees with Shift type 3 and create a shift pool!");

				}
				else {
					GUIshiftInputWindow shiftType3 = new GUIshiftInputWindow(ShiftPool.returnShiftType3(), GUImainWindow.this, "Shift type 3");
				}
			}
		});
		panel_8.add(btnNewButton_66);
			
				
		JButton btnNewButton_17 = new JButton("Shift type 4");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ShiftPool.returnShiftType4().isEmpty()) {
					GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "The shift pool is empty. Click on 'Create shift pool', or add employees with Shift type 4 and create a shift pool!");
				}
				else {
					GUIshiftInputWindow shiftType4 = new GUIshiftInputWindow(ShiftPool.returnShiftType4(), GUImainWindow.this, "Shift type 4");
				}
			}
		});
		panel_8.add(btnNewButton_17);
		
		JPanel panel_5 = new JPanel();
		layeredPane.setLayer(panel_5, 3);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Employee maintenance");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_5.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel_9 = new JPanel();
		panel_5.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_6 = new JButton("Add employee");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				GUIaddNewEmployee newEmployee = new GUIaddNewEmployee(GUImainWindow.this);								
			}
		});
		panel_9.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Delete employee");
		panel_9.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Edit employee");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIupdateEmployee newUpdate = new GUIupdateEmployee(GUImainWindow.this);
			}
		});
		panel_9.add(btnNewButton_8);
		
		JButton btnNewButton_19 = new JButton("Display employee list");
		panel_9.add(btnNewButton_19);
		
		JPanel panel_7 = new JPanel();
		layeredPane.setLayer(panel_7, 5);
		layeredPane.setLayout(new CardLayout(0, 0));
		layeredPane.add(panel_3, "name_364862670609700");
		
		JLabel lblNewLabel_1 = new JLabel("Create new schedule");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_3.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		panel_3.add(layeredPane_1, BorderLayout.SOUTH);
		layeredPane_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11, BorderLayout.SOUTH);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_11.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.NORTH);
		panel_14.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JCheckBox chckbxArchiveNext = new JCheckBox("archive next");
		chckbxArchiveNext.setToolTipText("Select this checkbox to store a filled-out calendar in the archive once you're happy with the result of the calculation. The archived calendar will be taken into account during next month's calculation.");
		panel_14.add(chckbxArchiveNext);
		
		JCheckBox chckbxIgnorePreviousMonth = new JCheckBox("ignore previous month");
		chckbxIgnorePreviousMonth.setSelected(true);
		chckbxIgnorePreviousMonth.setToolTipText("Select this checkbox to ignore the last 5 days of the previous month's calendar. If selected, the previous month's shifts will be ignored during the calculation.");
		panel_14.add(chckbxIgnorePreviousMonth);
		
		JCheckBox chckbxPreferActive = new JCheckBox("prefer active");
		chckbxPreferActive.setToolTipText("Select this checkbox to activate the employee's preference checks--whether he should be paired with other employees or not.");
		panel_14.add(chckbxPreferActive);
		
		JCheckBox chckbxAvoidActive = new JCheckBox("avoid active");
		chckbxAvoidActive.setToolTipText("Select this checkbox to activate the employee's preference checks--whether he should avoid other employees or not.");
		panel_14.add(chckbxAvoidActive);
		
		
		JButton btnNewButton_26 = new JButton("Calculate shifts");
		btnNewButton_26.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_26.setPreferredSize(new Dimension(160, 60));
		btnNewButton_26.setMaximumSize(new Dimension(109, 45));
		btnNewButton_26.setMinimumSize(new Dimension(109, 45));
		btnNewButton_26.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {				
				if(chckbxPreferActive.isSelected()) {
					preferActive = true;
				}
				else if(!chckbxPreferActive.isSelected()) {
					preferActive = false;
				}
				if(chckbxAvoidActive.isSelected()) {
					avoidActive = true;
				}
				else if(!chckbxAvoidActive.isSelected()) {
					avoidActive = false;
				}
				
				if(chckbxIgnorePreviousMonth.isSelected()) {
					previousMonth = true;
				}
				else if(!chckbxIgnorePreviousMonth.isSelected()) {
					previousMonth = false;
				}
				if(chckbxArchiveNext.isSelected()) {
					archiveNext = true;
				}
				else if(!chckbxArchiveNext.isSelected()) {
					archiveNext = false;
				}				
				if(RequestPool.deserialize().isEmpty()) {
					GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "The request pool is empty. Add shift requests!");
				}
				else if (!RequestPool.deserialize().isEmpty())
					spcl = new Supercalendar(preferActive, avoidActive, previousMonth, archiveNext, GUImainWindow.this);
				
			}
		});
		panel_13.add(btnNewButton_26, BorderLayout.CENTER);
		layeredPane.add(panel_5, "name_364862735482100");
		layeredPane.add(panel_7, "name_364862788002100");
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Welcome to Shift Scheduler 2020!");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		panel_1.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("About");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("About the program");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUImessageWindow about = new GUImessageWindow(GUImainWindow.this, "	\n		Shift Scheduler 2020 \n \n		 © Richard Brenkus");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		helpPage = new GUIhelp();
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Help");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			if(helpPage.isVisible())
				helpPage.setVisible(false);
			else if(!helpPage.isVisible())
				helpPage.setVisible(true);			
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
			
		JScrollPane textAreaScroll = new JScrollPane();
		panel_2.add(textAreaScroll);
					
		JToolBar toolBar = new JToolBar();
		toolBar.setName("Toolbar");
		toolBar.setBorder(new LineBorder(Color.BLACK, 1, true));
		toolBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setAutoscrolls(true);
		contentPane.add(toolBar, BorderLayout.EAST);
		
		JButton btnNewButton_20 = new JButton("View shift pool");
		btnNewButton_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIshiftPoolDisplayer display = new GUIshiftPoolDisplayer(ShiftPool.displayShiftPools(), GUImainWindow.this);
			}
		});
		toolBar.add(btnNewButton_20);
		
		JButton btnNewButton_21 = new JButton("Reset shift pool");
		btnNewButton_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShiftPool.resetShiftPool();
				GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "The shift pool has been emptied.");

			}
		});
		toolBar.add(btnNewButton_21);
				
		JButton btnNewButton_23 = new JButton("Create shift pool");
		btnNewButton_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShiftPool.shiftCollector(EmployeePool.getAllEmployeeDetails());
				GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "A new shift pool has been created.");
			}
		});
		toolBar.add(btnNewButton_23);
		
		JButton btnNewButton_22 = new JButton("Show request pool");
		btnNewButton_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIrequestPoolDisplayer display = new GUIrequestPoolDisplayer(RequestPool.showRequestPool(), GUImainWindow.this);
			}
		});
		toolBar.add(btnNewButton_22);
		
		JButton btnNewButton_16 = new JButton("Reset request pool");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestPool.resetRequestPool();
				GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "The request pool has been emptied.");

			}
		});
		toolBar.add(btnNewButton_16);
		
		JButton btnNewButton_25 = new JButton("Show archived days");
		btnNewButton_25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FinishedCalendar.loadLastFiveDays().isEmpty()) {
					GUImessageWindow message = new GUImessageWindow(GUImainWindow.this, "The archive is currently empty. "
							+ "Create a new calendar by clicking on 'Calculate shifts' and select the 'archive next' checkbox "
							+ "in order to store the last five days of the calendar in the archive.");
				}
				else {
				Supercalendar.supercalendarShower(GUImainWindow.this);
				}
			}
		});
		toolBar.add(btnNewButton_25);
		
		JButton btnNewButton_27 = new JButton("Reset archive");
		btnNewButton_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinishedCalendar.resetArchive(GUImainWindow.this);
			}
		});
		toolBar.add(btnNewButton_27);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 2, 2);
//		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 2, 2);		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(true);
				panel_7.setVisible(false);
				panel_5.setVisible(false);
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.setVisible(true);
				panel_7.setVisible(false);
				panel_3.setVisible(false);
			}
		});
		
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIdeleteEmployee dialog = new GUIdeleteEmployee(GUImainWindow.this);
			}
		});
		
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIemployeeListDisplayer employeeDisplay = new GUIemployeeListDisplayer(EmployeePool.displayEmployeePool(), GUImainWindow.this);
			}
		});
			
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GUIexitDialog question = new GUIexitDialog(GUImainWindow.this, Dialog.ModalityType.DOCUMENT_MODAL);
			}
		});	
		
	}
}
