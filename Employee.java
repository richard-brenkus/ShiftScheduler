package com.richardbrenkus;
import java.util.*;

import java.io.*;

public class Employee implements Serializable{
	
	private String firstName, lastName, phoneNumber, emailAddress, note;
	ArrayList<String> prefers, avoids;
	private List<Integer> shiftDetails = new ArrayList<>();
	
	public Employee() {
		
	}

	public Employee(String lName, String fName, boolean input1, boolean input2, boolean input3, boolean input4, String phone, String email, String inputNote, ArrayList<String> prefersCoworkers, ArrayList<String> avoidsCoworkers) {
		firstName = fName;
		lastName = lName;
		phoneNumber = phone;
		emailAddress = email;
		note = inputNote;
		prefers = prefersCoworkers;
		avoids = avoidsCoworkers;
		
		if(input1)
			shiftDetails.add(1);
		if(input2)
			shiftDetails.add(2);
		if(input3)
			shiftDetails.add(3);
		if(input4)
			shiftDetails.add(4);		
	}
	
	public void printEmployeeName() {	
		System.out.println(lastName +	", " + firstName);
	}
	
	public List<Integer> getShiftDetails() {
		return shiftDetails;
	}
	
	public String getEmployeeDetailsAsString() {
		return (lastName + ", " + firstName);
	}
	
	public String getLastName () {
		return lastName;
	}
	
	public String getFirstName () {
		return firstName;
	}
	
	public String getPhoneNumber () {
		return phoneNumber;
	}
	
	public String getEmailAddress () {
		return emailAddress;
	}
	
	public String getNote () {
		return note;
	}
				
	public ArrayList<String> getPreferredCoworkers () {
		return prefers;
		}
	
	public ArrayList<String> getAvoidedCoworkers () {
		return avoids;
		}
	
	public Employee getEmployee() {
		return Employee.this;
	}
}