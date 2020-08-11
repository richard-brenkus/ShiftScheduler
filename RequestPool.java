package com.richardbrenkus;

import java.util.*;
import java.io.*;

public class RequestPool implements Serializable{
	
	private static List<Request> requestPool = new LinkedList<>();
	private static Request returnedRequest = new Request();
	private static Boolean namePresent;
	private static Set<String> requestedDays;

	public static void addToRequestPool(Request inputRequest){
		try {
			requestPool = deserialize();	
			}catch(Exception c){
				c.printStackTrace();
				serialize();
			}
		requestPool.add(inputRequest);
		serialize();		
	}
	
	public static List<Request> showRequestPool() {
		try {
			requestPool = deserialize();
			}catch(Exception c){
				c.printStackTrace();
			}
		return requestPool;
	}
	
	public static Boolean requestAlreadyEntered(String employee) {
		namePresent = false;
		try {
			requestPool = deserialize();	
			}catch(Exception c){
				c.printStackTrace();
			}	
		
		for(Request r : requestPool)
			if(r.getEmployee().getEmployeeDetailsAsString().equals(employee)) {
				namePresent = true;
			}
		return namePresent;
	}
	
	public static Boolean employeeHasARequest(String employee) {
		Boolean employeeHasARequest = false;
		
		try {
			requestPool = deserialize();	
			}catch(Exception c){
				c.printStackTrace();
			}
		
		if(requestPool.isEmpty())
			return false;
		else
			for(Request r : requestPool)
				if(r.getEmployee().getEmployeeDetailsAsString().equals(employee))
					employeeHasARequest = true;	
		
		return employeeHasARequest;
			
	}
	
	public static Request requestGetter(String employee) {
		
		try {
			requestPool = deserialize();	
			}catch(Exception c){
				c.printStackTrace();
			}		
		return returnedRequest;
			
	}
	
	public static void deleteRequest(Employee employee) {
		Request requestToDelete = new Request();
		try {
			requestPool = deserialize();	
			}catch(Exception c){
				c.printStackTrace();
			}
		
		for(Request r : requestPool) 
			if(r.getEmployee().getEmployeeDetailsAsString().contentEquals(employee.getEmployeeDetailsAsString()))
				requestToDelete = r;
		requestPool.remove(requestToDelete);
		serialize();
	}
		

	
	public static ArrayList<Integer> loadRequest(String employee) {
//must return an ArrayList of days for the calendarPanel to be rebuilt
	Set<String> returnedDates  = new HashSet<>();
	
	try {
		requestPool = deserialize();	
		}catch(Exception c){
			c.printStackTrace();
		}
		
		for(Request r : requestPool)
			if(r.getEmployee().getEmployeeDetailsAsString().equals(employee))
				returnedDates = r.getRequestedDates();
		
		return parsedDates(returnedDates);
	}
	
	public static ArrayList<Integer> parsedDates(Set<String> returnedDates) {
		ArrayList<Integer> parsedDays = new ArrayList<>();
		
		for(String x : returnedDates)
			parsedDays.add(Integer.parseInt(x.substring(0, 2)));
		
		return parsedDays;
		
	}
	
	public static void resetRequestPool() {
		requestPool = new LinkedList<>();

		try {
			requestPool = deserialize();
			}catch(Exception c){
				c.printStackTrace();
			}
	
		requestPool.removeAll(requestPool);
		
		try {
			serialize();
			}
			catch (Exception c){
				c.printStackTrace();
			}		
	}
	
	public static List<Request> deserialize(){
		
		requestPool = new LinkedList<>();
	
		try {
			FileInputStream fileIn = new FileInputStream(new File ("RequestPool.ser"));
			ObjectInputStream inputStream = new ObjectInputStream(fileIn);
			requestPool = (List<Request>) inputStream.readObject();
			inputStream.close();	
			}catch(Exception c){
				c.printStackTrace();
			}
		
		return requestPool;
	}
	
	public static void serialize() {
		
		System.out.println();
		try {
			FileOutputStream requestPoolStream = new FileOutputStream("RequestPool.ser");
			ObjectOutputStream outputStream = new ObjectOutputStream (requestPoolStream);
			outputStream.writeObject(requestPool);
			outputStream.close();
			}
			catch (Exception c){
				c.printStackTrace();
			}			
	}	
	
	public static List<Request> requestModifier() {
		List<Request> listToBeModified = new ArrayList<>();
		ArrayList<Request> modifiedList = new ArrayList<>();
		ArrayList<Integer> datesList;
				
		listToBeModified = deserialize();
		for(Request y : listToBeModified) {
			datesList = new ArrayList<>();
			for(String x : y.getRequestedDates()) {
				datesList.add(Integer.parseInt(x.toString().substring(0, 2)));
				datesList.sort(null);
			}
			Request modifiedRequest = new Request(y.getEmployee(), y.getPriority(), datesList, 
					y.getPrefers(), y.getAvoids(), y.getShiftCount(), y.getWeekendCount());
			modifiedList.add(modifiedRequest);
		}
		
		return modifiedList;
	}
	
	public static int monthInt() {
		int monthInt = Integer.parseInt(deserialize().get(0).getRequestedDates().toString().substring(4, 6));
		return monthInt;
	}
	
	public static int yearInt() {
		int yearInt = Integer.parseInt(deserialize().get(0).getRequestedDates().toString().substring(7, 11));
		return yearInt;
	}
	
	public static String monthString() {
		String monthString = deserialize().get(0).getRequestedDates().toString().substring(4, 6);
		return monthString;
	}
	
	public static String yearString() {
		String yearString = deserialize().get(0).getRequestedDates().toString().substring(7, 11);
		return yearString;
	}
	
	public static int dayInt() {
		int dayInt = Integer.parseInt(deserialize().get(0).getRequestedDates().toString().substring(1, 3));
		return dayInt;
	}
}

