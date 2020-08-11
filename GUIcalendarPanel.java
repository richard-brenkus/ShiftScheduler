package com.richardbrenkus;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIcalendarPanel extends JPanel {
		//define variables
        int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        //create object of JLabel with alignment
        JLabel l = new JLabel("", JLabel.CENTER);
        //define variable
        String day = "";
        //create object of JButton
        JButton[] button = new JButton[49];
        // decide whether this is a new request so that a new GUIcalendarPanel can be created or an existing request so that the GUIcalendarPanel needs to 
        //display a set of dates that have been already filled out previously:
    	private static Set<String> editedDays;
    	private static LinkedList<Integer> returnedParsedDays;
 
    	public GUIcalendarPanel(JInternalFrame parent, Set<String> requestedDays)//create constructor 
        {
                //define string
                String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
                //create JPanel object and set layout
                JPanel p1 = new JPanel(new GridLayout(7, 7));
                //set size
                p1.setPreferredSize(new Dimension(430, 120));
                //for loop condition
                for (int x = 0; x < button.length; x++) 
                {		
                	//define variable
                        final int selection = x;
                        //create object of JButton
                        button[x] = new JButton();
                        //set focus painted false
                        button[x].setFocusPainted(false);
                        //set background colour
                        button[x].setBackground(Color.white);                      
                        //if loop condition
                        if (x > 6)
                        //add action listener                	
                        button[x].addActionListener(new ActionListener() 
                        {
                                 public void actionPerformed(ActionEvent ae) 
                                 {              	 
                                	 day = button[selection].getActionCommand();
                                       if(button[selection].getBackground().equals(Color.WHITE)) {
                                    	   button[selection].setBackground(Color.GREEN);
                                       		requestedDays.add(setPickedDate());
                                       		 }
                                       else {
                                    	   button[selection].setBackground(Color.WHITE);
                                    	   requestedDays.remove(setPickedDate());
                                            }
                                 }
                        });
                        if (x < 7)//if loop condition 
                        {
                                button[x].setText(header[x]);
                                //set fore ground colour
                                button[x].setForeground(Color.red);
                        }
                        p1.add(button[x]);//add button
                }
                //create JPanel object with grid layout
                JPanel p2 = new JPanel(new GridLayout(1, 3));
                
                //create object of button for previous month
                JButton previous = new JButton("<< Previous");
                //add action command
                previous.addActionListener(new ActionListener() 
                {
                        public void actionPerformed(ActionEvent ae) 
                        {
                            //decrement month by 1
                            month--;
                            //call method
                            displayDate();
                        }
                });
                p2.add(previous);//add button
                p2.add(l);//add label
                //create object of button for next month
                JButton next = new JButton("Next >>");
                //add action command
                next.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ae) 
                        {
                             //increment month by 1
                             month++;
                             //call method
                            displayDate();
                        }
                });
                p2.add(next);// add next button
                //set border alignment
                parent.getContentPane().add(p1, BorderLayout.CENTER);
                parent.getContentPane().add(p2, BorderLayout.SOUTH);
                //call method
                displayDate();
        }        
        
        public GUIcalendarPanel(JInternalFrame parent, Set<String> requestedDays, ArrayList<Integer> previousDates)//create constructor 
        {
        	    //define string
                String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
                //create JPanel object and set layout
                JPanel p1 = new JPanel(new GridLayout(7, 7));
                
               

                //set size
                p1.setPreferredSize(new Dimension(430, 120));
                //for loop condition
                for (int x = 0; x < button.length; x++) 
                {		
                	//define variable
                        final int selection = x;
                        //create object of JButton
                        button[x] = new JButton();
                        //set focus painted false
                        button[x].setFocusPainted(false);
                        //set background colour 
                        button[x].setBackground(Color.WHITE);
                        
//                       java.util.Calendar cal = java.util.Calendar.getInstance();			
//                    	int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
//                        if(previousDates.contains(x-7)) {
//                        	button[x].setBackground(Color.GREEN); 
//                        	System.out.println(x-7);
//
//                        }
//                        else if (previousDates.contains(x-7)) {
//                        	button[x].setBackground(Color.GREEN); 
//                        	System.out.println("dayOfWeek == 1");
//                        }
                     

                        //if loop condition
                        if (x > 6) { 
                        	
                        //add action listener                	
                        button[x].addActionListener(new ActionListener() 
                        {
                                 public void actionPerformed(ActionEvent ae) 
                                 {              	 
                                	 day = button[selection].getActionCommand();
                                       if(button[selection].getBackground().equals(Color.WHITE)) {
                                    	   button[selection].setBackground(Color.GREEN);
                                       		requestedDays.add(setPickedDate());
                                       		 }
                                       else {
                                    	   button[selection].setBackground(Color.WHITE);
                                    	   requestedDays.remove(setPickedDate());
                                            }
                                 }
                        });
                        }
                        if (x < 7)//if loop condition 
                        {
                                button[x].setText(header[x]);
                                //set fore ground colour
                                button[x].setForeground(Color.red);
                        }
                        p1.add(button[x]);//add button
                }
                //create JPanel object with grid layout
                JPanel p2 = new JPanel(new GridLayout(1, 3));
                //create object of button for previous month
                JButton previous = new JButton("<< Previous");
                //add action command
                previous.addActionListener(new ActionListener() 
                {
                        public void actionPerformed(ActionEvent ae) 
                        {
                            //decrement month by 1
                            month--;
                            //call method
                            displayDate();
                        }
                });
                p2.add(previous);//add button
                p2.add(l);//add label
                //create object of button for next month
                JButton next = new JButton("Next >>");
                //add action command
                next.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent ae) 
                        {
                             //increment month by 1
                             month++;
                             //call method
                            displayDate();
                        }
                });
                p2.add(next);// add next button
                //set border alignment
                parent.getContentPane().add(p1, BorderLayout.CENTER);
                parent.getContentPane().add(p2, BorderLayout.SOUTH);
                //call method
                displayDate();
        }
        
        public void displayDate() 
        {
        	for (int x = 7; x < button.length; x++)//for loop
        		button[x].setText("");//set text
      	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM");	
            //create object of SimpleDateFormat 
            java.util.Calendar cal = java.util.Calendar.getInstance();			
            //create object of java.util.Calendar 
        	cal.set(year, month+2, 1); //set year, month and date
         	//define variables
        	int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        	int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        	//condition
        	for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
        	//set text
        	button[x].setText("" + day);
        	l.setText(sdf.format(cal.getTime()));
        }
         
        public String setPickedDate() 
        {
        	//if condition
        	if (day.equals(""))
        		return day;
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(year, month +2, Integer.parseInt(day));
            return sdf.format(cal.getTime());
        }
        
        public static Set<String> editedDaysGetter(){
        	return editedDays;
        }
        
        public static LinkedList<Integer> editedCalendarParser(Set<String> requestedDays) {
        	
        	LinkedList<String> listToBeParsed = new LinkedList<>();
        	for(String x : requestedDays)
        		listToBeParsed.add(x);
        	
        	 returnedParsedDays = new LinkedList<>();
        	
        	for (int x = 0; x<listToBeParsed.size(); x++) {
        		returnedParsedDays.add(Integer.parseInt((listToBeParsed.get(x)).substring(0, 2)));
        	}
        	return returnedParsedDays;	
        }
}