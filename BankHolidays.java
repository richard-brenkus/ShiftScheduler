package com.richardbrenkus;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BankHolidays {
	
	ArrayList<String> holidays = new ArrayList<>(Arrays.asList("01-01", "10-04", "01-05", "08-05", "05-07", "06-07", "28-09", "28-10", "17-11", "24-12", "25-12", "26-12"));
	int month;
	boolean monthHasHolidays; //unused value
	ArrayList<Integer> holidaysList = new ArrayList<>();
	Integer fromRequestPool = RequestPool.monthInt();

	
	public ArrayList<Integer> holidayHandler(int currentMonth) {
				
		for(String str : holidays)
			if(fromRequestPool.equals(Integer.parseInt(str.substring(3))))
			{
				monthHasHolidays = true;
				holidaysList.add(Integer.parseInt(str.substring(0,2)));
			}
			else {
				monthHasHolidays = false;
			}
				
		return holidaysList;
	}
}
