package com.richardbrenkus;

import java.util.*;
import java.io.*;

public class EmployeePool implements Serializable{
	
	private static List<Employee> allEmployeeDetails = new ArrayList<>();
	private static List<Employee> updatedEmployeeDetails;	
	private static	ArrayList<String> employeePool = new ArrayList<>();
	private static String[] employeePoolAsStringArray;
	private static List<Employee> restoredEmployeeDetails;
	
    public static void addEmployeeToDatabase(Employee input) {
	   	allEmployeeDetails = deserialize();
		allEmployeeDetails.add(input);
		serialize(allEmployeeDetails);		
	}
    
    public static List<Employee> getAllEmployeeDetails(){
    	allEmployeeDetails = deserialize();
    	return allEmployeeDetails;
     }
	
	public static List<Employee> displayEmployeePool() {
		try {
		FileInputStream fileIn = new FileInputStream(new File ("EmployeePool.ser"));
		ObjectInputStream inputStream = new ObjectInputStream(fileIn);
		List<Employee> restoredEmployeeDetails = (List<Employee>) inputStream.readObject();
		return restoredEmployeeDetails;		
		}
		catch(Exception c){
			c.printStackTrace();
		}
		return restoredEmployeeDetails;
	}
	
	public static String[] getEmployeePoolAsStringArray(){
		allEmployeeDetails = deserialize();
		employeePoolAsStringArray = new String[allEmployeeDetails.size()];
		for (int x = 0; x < allEmployeeDetails.size(); x++) {
			employeePoolAsStringArray[x] = allEmployeeDetails.get(x).getEmployeeDetailsAsString();
		}
		Arrays.sort(employeePoolAsStringArray);				
		return employeePoolAsStringArray;	
	}
	
	public static void updateEmployeeList(List<Employee> inputFromAnotherWindow) {
		updatedEmployeeDetails = new ArrayList<>();
		updatedEmployeeDetails = inputFromAnotherWindow;		
		try {
			FileOutputStream allEmployeesDetailsDatabaseStream = new FileOutputStream("EmployeePool.ser");
			ObjectOutputStream outputStream = new ObjectOutputStream (allEmployeesDetailsDatabaseStream);
			outputStream.writeObject(updatedEmployeeDetails);
			outputStream.close();
			}
			catch (Exception c){
				c.printStackTrace();
			}		
		displayEmployeePool();
	}


	public static void resetEmployeePool() {
		allEmployeeDetails = deserialize();
		allEmployeeDetails.removeAll(allEmployeeDetails);
		serialize(allEmployeeDetails);	
	}

	public static void deleteEmployeeFromDatabase(Employee input) {
		allEmployeeDetails = deserialize();
		allEmployeeDetails.remove(input);
		serialize(allEmployeeDetails);
	}

	public static List<Employee> deserialize(){
		try {
			FileInputStream fileIn = new FileInputStream(new File ("EmployeePool.ser"));
			ObjectInputStream inputStream = new ObjectInputStream(fileIn);
			allEmployeeDetails = (List<Employee>) inputStream.readObject();
			inputStream.close();			
		}catch(Exception c){
				c.printStackTrace();
			}
		return allEmployeeDetails;	
	}
	
	public static void serialize(List<Employee> allEmployeeDetails) {
		try {
			FileOutputStream allEmployeesDetailsDatabaseStream = new FileOutputStream("EmployeePool.ser");
			ObjectOutputStream outputStream = new ObjectOutputStream (allEmployeesDetailsDatabaseStream);
			outputStream.writeObject(allEmployeeDetails);
			outputStream.close();
			}
			catch (Exception c){
				c.printStackTrace();
			}
	}
	
	public static ArrayList<String> getAllEmployeeNamesAsArrayList(){
		for (Employee x: deserialize())
			employeePool.add(x.getEmployeeDetailsAsString().toUpperCase());
		return employeePool;
	}
}





