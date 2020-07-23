package com.richardbrenkus;

import java.util.*;
import java.io.*;

public class Request implements Serializable{
	private Employee employee;
	private int priority, shiftCount, weekends;
	private ArrayList<String> prefers, avoids;
	private Set<String> requestedDates;
	private List<Object> requestDetails;
	private ArrayList<Integer> datesAsInteger;
	private int shiftCounter;
	
	public Request() {
		
	}
	
	public Request(Employee inputEmployee, int prio, Set<String> dates, ArrayList<String> prefersThese, ArrayList<String> avoidsThese, int shiftCountBoxInt, int weekendBoxInt) {
		
		employee = inputEmployee;
		requestedDates = dates;
		prefers = prefersThese;
		avoids = avoidsThese;
		priority = prio;
		shiftCount = shiftCountBoxInt;
		weekends = weekendBoxInt;

		RequestPool.addToRequestPool(this);
	}
		
	public Request(Employee inputEmployee, int prio, ArrayList<Integer> datesList, ArrayList<String> prefersThese, ArrayList<String> avoidsThese,
		int shiftCount2, int weekendCount) {
		
		employee = inputEmployee;
		datesAsInteger = datesList;
		prefers = prefersThese;
		avoids = avoidsThese;
		priority = prio;
		shiftCount = shiftCount2;
		weekends = weekendCount;
		}

	public List<Object> getAllRequestDetails(){
		requestDetails = new ArrayList<Object>();
		requestDetails.add(employee.getLastName());
		requestDetails.add(employee.getFirstName());
		requestDetails.add(priority);
		requestDetails.add(requestedDates);
		requestDetails.add(prefers);
		requestDetails.add(avoids);
		requestDetails.add(shiftCount);
		requestDetails.add(weekends);
		
		return requestDetails;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public int getShiftCount() {
		return shiftCount;
	}
	
	public int getWeekendCount() {
		return weekends;
	}
	
	public Set<String> getRequestedDates(){
		return requestedDates;
	}
	
	public ArrayList<String> getPrefers(){
		return prefers;
	}
		
	public ArrayList<String> getAvoids(){
		return avoids;
	}
	
	public ArrayList<Integer> getIntegerDates(){
		return datesAsInteger;
	}
	
	public void increaseShiftCounterByOne() {
		shiftCounter = shiftCounter+1;
	}
	
	public int getShiftCounter() {
		return shiftCounter;
	}
	
	public Integer getIntegerDatesSize(){
		return datesAsInteger.size();
	}
}



		

	



