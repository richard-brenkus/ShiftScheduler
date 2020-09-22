package com.richardbrenkus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FinishedCalendar implements Serializable{
	
	private static String month, year;
	private static Map<Integer, Set<Employee>> lastFiveDays, restoredDays, returnedDays;

	public static void resetArchive() {
		returnedDays = loadLastFiveDays();
		returnedDays.clear();
		archiveAgain(returnedDays);
//		GUImessageWindow message = new GUImessageWindow("The last five days of the previous month have been deleted from the archive.");
	}

	public static void archiveLastFiveDays(GUImainWindow owner, Map<Integer, Map<Employee, Boolean>> supercalendar) {
		lastFiveDays = new HashMap<>();		
		List<Integer> setOfKeys = new ArrayList<>();
		setOfKeys = supercalendar.keySet().stream().sorted(Collections.reverseOrder()).limit(5).collect(Collectors.toList());
		int position = 0;
		for(Integer i:  setOfKeys) {
			lastFiveDays.put(position, supercalendar.get(i).keySet().stream().filter(k -> supercalendar.get(i).get(k.getEmployee()).equals(true)).map(e -> e.getEmployee()).collect(Collectors.toSet()));
			position = position - 1;
		}
		
		try {
			FileOutputStream lastFiveKeysStream = new FileOutputStream("LastFiveDays.ser");
			ObjectOutputStream outputStream = new ObjectOutputStream (lastFiveKeysStream);
			outputStream.writeObject(lastFiveDays);
			outputStream.close();
			GUImessageWindow message = new GUImessageWindow(owner, "The last five days of the current supercalendar have been archived.");
			}
			catch (Exception c){
				c.printStackTrace();
			}	
		}
	
	public static Map<Integer, Set<Employee>> loadLastFiveDays() {
		try {
			FileInputStream fileIn = new FileInputStream(new File ("LastFiveDays.ser"));
			ObjectInputStream inputStream = new ObjectInputStream(fileIn);
			restoredDays = (Map<Integer, Set<Employee>>) inputStream.readObject();
			inputStream.close();	
			}catch(Exception c){
				c.printStackTrace();
			}
		return restoredDays;
		
	}
	
	public static void archiveAgain(Map<Integer, Set<Employee>> inputDays) {
		try {
			FileOutputStream lastFiveKeysStream = new FileOutputStream("LastFiveDays.ser");
			ObjectOutputStream outputStream = new ObjectOutputStream (lastFiveKeysStream);
			outputStream.writeObject(inputDays);
			outputStream.close();
			}
			catch (Exception c){
				c.printStackTrace();
			}	
	}
}