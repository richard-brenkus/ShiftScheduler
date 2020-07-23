package com.richardbrenkus;

import java.util.*;
import java.io.*;
import java.time.*;

public class ShiftPool implements Serializable{
	private static List<List<Employee>> shiftPoolDatabase;
	private static List<Employee> shiftType1, shiftType2, shiftType3, shiftType4;
	private static Set<Employee> restoredShiftType1, restoredShiftType2, restoredShiftType3, restoredShiftType4;
	private static List<Employee> employeeList;
	
	public static void shiftCollector(List<Employee> inputEmployees) {
		shiftPoolDatabase = new ArrayList<>();
		employeeList = inputEmployees;
			
		try {
			shiftPoolDatabase = deserialize();	
			}catch(Exception c){
				c.printStackTrace();
				serialize();
			}

		shiftType1 = new ArrayList<>();
		shiftType2 = new ArrayList<>();
		shiftType3 = new ArrayList<>();
		shiftType4 = new ArrayList<>();
	
		for(Employee person: employeeList) {
			if (person.getShiftDetails().contains(1)) {
				shiftType1.add(person);
			}
			if (person.getShiftDetails().contains(2)){
				shiftType2.add(person);
			}
			if (person.getShiftDetails().contains(3)){
				shiftType3.add(person);
			}
			if (person.getShiftDetails().contains(4)){
				shiftType4.add(person);
				}
			}

		shiftPoolDatabase.add(shiftType1);
		shiftPoolDatabase.add(shiftType2);
		shiftPoolDatabase.add(shiftType3);
		shiftPoolDatabase.add(shiftType4);
		
		try {
			serialize();
			}
			catch (Exception c){
				c.printStackTrace();
			}			
	}
	
	public static List<List<Employee>> displayShiftPools() {
		shiftPoolDatabase = new ArrayList<>();
		try {
			shiftPoolDatabase = deserialize();
		}catch(Exception c){
				c.printStackTrace();
			}
		
		restoredShiftType1 = new HashSet<>();
		restoredShiftType2 = new HashSet<>();
		restoredShiftType3 = new HashSet<>();
		restoredShiftType4 = new HashSet<>();
		
		for(int x = 0; x < shiftPoolDatabase.size(); x++)
			for(int y = 0; y < shiftPoolDatabase.get(x).size(); y++) {
			if (shiftPoolDatabase.get(x).get(y).getShiftDetails().contains(1)) {
				restoredShiftType1.add(shiftPoolDatabase.get(x).get(y));
				}
			}
		for(int x = 0; x < shiftPoolDatabase.size(); x++)
			for(int y = 0; y < shiftPoolDatabase.get(x).size(); y++) {
			if (shiftPoolDatabase.get(x).get(y).getShiftDetails().contains(2)) {
				restoredShiftType2.add(shiftPoolDatabase.get(x).get(y));
				}
			}
			
		for(int x = 0; x < shiftPoolDatabase.size(); x++)
			for(int y = 0; y < shiftPoolDatabase.get(x).size(); y++) {
			if (shiftPoolDatabase.get(x).get(y).getShiftDetails().contains(3)) {
				restoredShiftType3.add(shiftPoolDatabase.get(x).get(y));
				}
			}
		
		for(int x = 0; x < shiftPoolDatabase.size(); x++)
			for(int y = 0; y < shiftPoolDatabase.get(x).size(); y++) {
			if (shiftPoolDatabase.get(x).get(y).getShiftDetails().contains(4)) {
				restoredShiftType4.add(shiftPoolDatabase.get(x).get(y));
				}
			}	
		return shiftPoolDatabase;
	}
	
	public static void resetShiftPool() {
		
		shiftPoolDatabase = new ArrayList<>();

		try {
			shiftPoolDatabase = deserialize();
			}catch(Exception c){
				c.printStackTrace();
			}
	
			shiftPoolDatabase.removeAll(shiftPoolDatabase);
		
		try {
			serialize();
			}
			catch (Exception c){
				c.printStackTrace();
			}		
	}
	
	public static List<List<Employee>> deserialize(){
		shiftPoolDatabase = new ArrayList<>();
		
		try {
			FileInputStream fileIn = new FileInputStream(new File ("ShiftPool.ser"));
			ObjectInputStream inputStream = new ObjectInputStream(fileIn);
			shiftPoolDatabase = (List<List<Employee>>) inputStream.readObject();
			inputStream.close();	
			}catch(Exception c){
				c.printStackTrace();
			}
		
		return shiftPoolDatabase;
	}
	
	public static void serialize() {
		try {
			FileOutputStream shiftPoolDatabaseStream = new FileOutputStream("ShiftPool.ser");
			ObjectOutputStream outputStream = new ObjectOutputStream (shiftPoolDatabaseStream);
			outputStream.writeObject(shiftPoolDatabase);
			outputStream.close();
			}
			catch (Exception c){
				c.printStackTrace();
			}			
	}	

	public static Set<Employee> returnShiftType1(){
			shiftPoolDatabase = new ArrayList<>();
			try {
				shiftPoolDatabase = deserialize();
			}catch(Exception c){
					c.printStackTrace();
				}
			
			try {
			
			restoredShiftType1 = new HashSet<>();
						
			for(int x = 0; x < shiftPoolDatabase.size(); x++)
				for(int y = 0; y < shiftPoolDatabase.get(x).size(); y++) {
				if (shiftPoolDatabase.get(x).get(y).getShiftDetails().contains(1)) {
					restoredShiftType1.add(shiftPoolDatabase.get(x).get(y));
					}
				}
				return restoredShiftType1;
			
			}catch(Exception e){
				return restoredShiftType1;
			}		
	}
	
	public static Set<Employee> returnShiftType2(){
		shiftPoolDatabase = new ArrayList<>();
		try {
			shiftPoolDatabase = deserialize();
		}catch(Exception c){
				c.printStackTrace();
			}
		
		try {	
			restoredShiftType2 = new HashSet<>();
			for(int x = 0; x < shiftPoolDatabase.size(); x++)
				for(int y = 0; y < shiftPoolDatabase.get(x).size(); y++) {
					if (shiftPoolDatabase.get(x).get(y).getShiftDetails().contains(2)) {
						restoredShiftType2.add(shiftPoolDatabase.get(x).get(y));
					}
				}		
			return restoredShiftType2;	
		}catch(Exception e){
			return restoredShiftType2;
		}		
	}
	
	public static Set<Employee> returnShiftType3(){
		shiftPoolDatabase = new ArrayList<>();
		try {
			shiftPoolDatabase = deserialize();
		}catch(Exception c){
			c.printStackTrace();
			}
		
		try {
			restoredShiftType3 = new HashSet<>();
			for(int x = 0; x < shiftPoolDatabase.size(); x++)
			for(int y = 0; y < shiftPoolDatabase.get(x).size(); y++) {
			if (shiftPoolDatabase.get(x).get(y).getShiftDetails().contains(3)) {
				restoredShiftType3.add(shiftPoolDatabase.get(x).get(y));
				}
			}
		
			return restoredShiftType3;
		
		}catch(Exception e){
			return restoredShiftType3;
		}		
	}
	
	public static Set<Employee> returnShiftType4(){
		shiftPoolDatabase = new ArrayList<>();
		try {
			shiftPoolDatabase = deserialize();
		}catch(Exception c){
			c.printStackTrace();
			}
		
		try {
		restoredShiftType4 = new HashSet<>();
		for(int x = 0; x < shiftPoolDatabase.size(); x++)
			for(int y = 0; y < shiftPoolDatabase.get(x).size(); y++) {
			if (shiftPoolDatabase.get(x).get(y).getShiftDetails().contains(4)) {
				restoredShiftType4.add(shiftPoolDatabase.get(x).get(y));
				}
			}
			return restoredShiftType4;
		
		}catch(Exception e){
			return restoredShiftType4;
		}		
	}
}


