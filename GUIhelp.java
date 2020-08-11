package com.richardbrenkus;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class GUIhelp extends JFrame{
	
	private JPanel contentPane;
	private JTextPane textPane;
	
	public GUIhelp() {
		
		setTitle("Help page");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		textPane = new JTextPane();
		
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
			
		textPane.setContentType("text/html");
		textPane.setText("<html>\r\n" + 
				"	<head>\r\n" + 
				"		<title>Shift Scheduler Help</title>\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<h1>How to use the Shift Scheduler</h1>\r\n" + 
				"				<h2>Purpose</h2>\r\n" + 
				"					<p> The Shift Scheduler solves a real-life scheduling problem. It produces a monthly calendar that contains the names of the employees who are best suited to work on each day in the month, based on the employees' requests.\r\n" + 
				"					</p>\r\n" + 
				"				<h2>Function desription</h2>\r\n" + 
				"					<p> The app takes the following variables into consideration: the dates as requested by the employees, the maximum amount of shifts and the maximum amount of weekends they'd like to work, their preferences as to which colleagues they'd like to work with <br> "
				+ 					"and which colleagues they'd like to avoid, and the priority of their request. In addition, there must be at least 5 days between two shifts of an employee (as per the customer's specification), and the last five days of the previous month must also be <br> "
				+ 					"considered. The amount of days between two shifts is currently hard-coded, and it cannot be altered by the user. Another limitation is that the algorithm distributes the names of the employees through up to 4 shift types. This was also specified by the <br>"
				+ 					"customer. I might make these two parameters into adjustable variables later on in order to make the Shift Scheduler more versatile. In order to give the user more control over the importance of each request, I've added a priority option, so that the <br> "
				+ 					"user  can prioritize requests on a scale from 1 to 5. A priority 1 request gets processed before a priority 2 request, and so on. The result of the calculation is a sheet with 5 columns: the dates in a month, and the shift types 1-4 with the employees' <br>"
				+ 					" names filled out.\r\n" + 
				"					</p>\r\n" + 
				"				<h2>How it works</h2>\r\n" + 
				"					<p>At first the user has to add employees under Employee maintenance. The fields Last name, First name, and Shift type are mandatory. Multiple shift types can be selected. The user can also change an employee's details later on, delete an employee (or <br> "
				+ 					"delete all employees at once), and view all employees with their phone numbers and shift types. The employee pool is serialized, and it is stored on the user's hard drive.\r\n" + 
				"					</p>\r\n" + 
				"					<p>By clicking on Create shift pool, the user creates a shift pool that is subsequently used to distribute the employees among Shift types 1-4 for the user's convenience, meaning that the user only sees employees of a particular shift when he or she is <br>"
				+ 					"about to create a request for the employee. In order to create a request, click on Manage shift schedule, and then on Shift type 1-4. When a new employee is added after a shift pool has been already created, the user should click on Reset shift pool, <br> "
				+ 					"and then Create shift pool in order to see the new employee in the Shift type 1-4 windows. These actions do not affect the requests that have already been entered, since the request pool is stored separately. It is possible to add or alter an employee <br> "
				+ 					"without having to reenter other employees' requests.\r\n" + 
				"					</p>\r\n" + 
				"					<p>Once a request for an employee has been entered and stored, the button with the employee's name on it turns green to indicate that a request has been already created for the employee. Clicking on the same button again will display the calendar, and in <br>"
				+ 					"the bottom part of the inner window the dates in the employee's current request will be shown. If the user would like to alter the dates, he or she has to make a new date selection. If the user would only like to view the dates, but not to alter them, <br>"
				+ 					"then he or she should click on Cancel to exit the Create shift request for [employee] window. If the user clicks on OK, the previous request will be overwritten. If the user clicks on Delete (this button only <br> "
				+ 					"appears in case a request has been already created for the employee, and the same goes for the textfield with the previously entered dates), the request will be deleted without a new request being created.\r\n" 
				+ 					
				"					<p>Finally, the user clicks on Calculate shifts to generate a calendar. If the checkbox archive next is selected, then the last 5 days of the newly generated calendar get stored into the archive, and they can be used during next month's calculation. If <br>"
				+ 					"ignore previous month is selected, then the archived last 5 days will be ignored during the next calculation.\r\n" + 
				"					</p>\r\n" + 
				"					<p>The user can also decide whether he'd like the algorithm to consider the employees' preferences as to whom they'd like to work with, or whom they'd like to avoid. For optimal results, the user can adjust the priority of each request by reopening any <br>"
				+ 					"of the shift types in the Manage shift schedule tab, and by altering the employee's request. The user can then change the parameters in the request, save them, and perform a new calculation until the result is a fully filled-out calendar, or until the <br> "
				+ 					"most amount of lines are filled with names. Please note that it is not always possible to fully fill out a calendar (for instance, if no employee has requested to work on a particular day, then this day will remain empty in the calendar).\r\n" + 
				"					</p>\r\n" + 
				"				<h2>Short manual</h2>\r\n" + 
				"				<ol>\r\n" + 
				"					<li>click on Employee maintenance – Add employee – Save</li>\r\n" + 
				"					<br>\r\n" + 
				"					<li>click on Create shift pool</li>\r\n" + 
				"					<br>\r\n" + 
				"					<li>click on Manage shift schedule – Shift type 1 to Shift type 4</li>\r\n" + 
				"					<br>\r\n" + 
				"					<li>click on the employee's button</li>\r\n" + 
				"					<br>\r\n" + 
				"					<li>select the requested dates, change the shift count, weekend count and priority if needed, combine with other employees if needed (this can be also set up in the Add employee window), then click on OK</li>\r\n" + 
				"					<br>\r\n" + 
				"					<li>select or deselect the checkboxes in the bottom part of the window as needed</li>\r\n" + 
				"					<br>\r\n" + 
				"					<li>click on Calculate shifts</li>\r\n" + 
				"					<br>\r\n" + 
				"				</ol>\r\n" + 
				"				<h2>GUI Toolbar description</h2>\r\n" + 
		//		"				<ul>\r\n" + 
				"					<h3>View shift pool</h3> <p>displays the current shift pool</p> <br>\r\n" + 
				"					<h3>Reset shift pool</h3> <p>erases the current shift pool</p> <br>\r\n" + 
				"					<h3>Create shift pool</h3> <p>generates a new shift pool that is then used to create the buttons with the employees' names on them in the Manage shift schedule tab when the user clicks on Shift type 1-4</p> <br>\r\n" + 
				"					<h3>Show request pool</h3> <p>displays a table with the requests that have been already entered</p> <br>\r\n" + 
				"					<h3>Reset request pool</h3> <p>erases all requests. This doesn't affect the employee pool, or the shift pool.</p> <br>\r\n" + 
				"					<h3>Show archived days</h3> <p>displays the last 5 days of the (previous) month that have been archived in order to use for next month's calculation</p> <br>\r\n" + 
				"					<h3>Reset archive</h3> <p>erases the data from the archive. Once clicked, the data stored in the archive cannot be used for next month's calculation any longer.</p> <br>\r\n" + 
				"				<br>	\r\n" + 
				"				<h3>Note</h3>\r\n" + 
				"				<p>The Shift Scheduler app is currently being used in a hospital in the Czech Republic, therefore the prefix MUDr. (medical doctor) is added in front of each name in the output calendar.\r\n" + 
//				"				</p>\r\n" + 
//				"				<p>There are two parts in the app that I didn't create myself. The first part is the GUIcalendarPanel class, the source code for which I'd downloaded from \r\n" + 
//				"					<a href=\"https://blog.eduonix.com/java-programming-2/how-to-use-date-picker-component-in-java/\" target=\"_blank\"> here </a>.  I did alter this class heavily, though, by adding the functionality of storing the selected dates (or removing them from the String<Set> when deselected) instead of just displaying the dates to the user, and I also added the functionality of displaying the previously entered dates in the calendar when a request is reopened.\r\n" + 
//				"				</p>\r\n" + 
//				"				<p>The second part I didn't create myself is the IntStream.rangeClosed stream in the weekendFinder() method in the Supercalendar class, but I also altered this one so that the method returns a List<Integer> instead of just printing out the dates. The credit for this block goes to Ortomala Lokni who has posted it <a href=\"https://stackoverflow.com/\r\n" + 
//				"				questions/3272454/in-java-get-all-weekend-dates-in-a-given-month\" target=\"_blank\"> here </a>.\r\n" + 
//				"				</p>\r\n" + 
//				"				<p>This is my first Java app, and it took me about 6 weeks to design, write, debug and test it.\r\n" + 
//				"				</p>\r\n" + 
				"	<hr>\r\n" + 
				"		<p> Copyright &copy; Richard Brenkus, 2020</p>\r\n" + 
				"	</body>\r\n" + 
				"</html>"		
				);
				
		panel.add(textPane, BorderLayout.CENTER);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		setVisible(false);
		}
}
