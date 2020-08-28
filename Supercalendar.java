package com.richardbrenkus;

import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.io.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.xa.XAException;

import java.util.stream.IntStream;

public class Supercalendar implements Serializable{
	private static List<Request> modifiedList = new ArrayList<>();
	private static int monthLength = YearMonth.of(RequestPool.yearInt(), RequestPool.monthInt()).lengthOfMonth();
	private static String month = RequestPool.monthString();;
	private static String year = RequestPool.yearString();
	private static List<Integer> weekends;
	private static Map<Integer, List<Employee>> supercalendar1, supercalendar2, supercalendar3, supercalendar4;
	private static Map<Integer, Map<Employee, Boolean>> supercalendar;
	private static Map<Employee, Boolean> supercalendarLine;
	private static Map<Employee, Integer> shiftCounter;
	private static Map<Employee, Integer> weekendCounter;
	private static boolean dayIsSafe;
	private static List<Employee> dailyEmployeeList;
	private static boolean preferEmployees, avoidEmployees, ignorePreviousMonth, archiveNextMonth;
	private static Map<Integer, Set<Employee>> lastFiveDays;
	public static boolean isNotPresent;
	private static GUImainWindow owner;

	public Supercalendar() {
		
	}

	public Supercalendar(boolean preferActive, boolean avoidActive, boolean previousMonth, boolean archiveNext, GUImainWindow mainWindow) {
		
//import request list, modify String dates from format dd-mm-yyyy to ArrayList<Integer>		
		modifiedList = RequestPool.requestModifier();
		owner = mainWindow;

//create supercalendars
		supercalendar = new HashMap<>();
		supercalendar1 = new HashMap<>();
		supercalendar2= new HashMap<>();
		supercalendar3= new HashMap<>();
		supercalendar4 = new HashMap<>();
		preferEmployees = preferActive;
		avoidEmployees = avoidActive;
		ignorePreviousMonth = previousMonth;
		archiveNextMonth = archiveNext;
		lastFiveDays = FinishedCalendar.loadLastFiveDays();
		
//sort the modified list in an ascending order, so that the requests with the least amount of days get checked first:		
		for(int day = 1; day <= monthLength; day++) {
			for(int x = 0; x<modifiedList.size(); x++) {
				supercalendarLine = new HashMap<>();
				modifiedList.sort((Request r1, Request r2) -> r1.getIntegerDatesSize() - r2.getIntegerDatesSize());
				for(Request r : modifiedList) {
					if(r.getIntegerDates().contains(day) && r.getEmployee().getShiftDetails().contains(1)) {
						supercalendarLine.put(r.getEmployee(), false);
					} //close if contains(day) && shift type 1
					if(r.getIntegerDates().contains(day) && r.getEmployee().getShiftDetails().contains(2)) {
						supercalendarLine.put(r.getEmployee(), false);
					} //close if contains(day) && shift type 2
					if(r.getIntegerDates().contains(day) && r.getEmployee().getShiftDetails().contains(3)) {
						supercalendarLine.put(r.getEmployee(), false);
					} //close if contains(day) && shift type 3
					if(r.getIntegerDates().contains(day) && r.getEmployee().getShiftDetails().contains(4)) {
						supercalendarLine.put(r.getEmployee(), false);
					} //close if contains(day) && shift type 4				
				}//close for Request r : modifiedList
			} //close for modifiedList
			supercalendar.put(day, supercalendarLine);
		} //close for monthLength
		
//fill out the supercalendars with blanks of List<Employee>
		for(int x = 1; x<=monthLength; x++) {
			List<Employee> blankEmpList = new ArrayList<>();
			supercalendar1.put(x, blankEmpList);
			supercalendar2.put(x, blankEmpList);
			supercalendar3.put(x, blankEmpList);
			supercalendar4.put(x, blankEmpList);

		}
									
//process the requests:			
		requestProcessor();
		
//archive the last 5 days of the processed month (if the corresponding checkbox in the GUI is selected):		
		if(archiveNextMonth) {
			FinishedCalendar.archiveLastFiveDays(owner, supercalendar);
			}	
	}	
	
	public static void requestProcessor() {
		
//find all weekend days:
		weekends = weekendFinder();
			
// initialize shiftCounter for each employee: <K,V> String, Integer			
		shiftCounter = new HashMap<>();
		for(Request r : modifiedList)
			shiftCounter.put(r.getEmployee(), 0);
						
// initialize shiftCounter for each employee: <K,V> String, Integer			
		weekendCounter = new HashMap<>();
		for(Request r : modifiedList)
			weekendCounter.put(r.getEmployee(), 0);		

// loop through the calendar and modified request list
		for (int x = 1; x<=monthLength; x++) {
			for(Request r : modifiedList) 				
				if(r.getIntegerDates().contains(x)) {
					if(r.getPriority() == 1) {
							/*check the following conditions:
							 * -weekends? if yes, then check for shifts on the surrounding 5 days, if no shifts, then return true, else return false
							 * -weekends < requested weekend count? 
							 * -shifts on the surrounding 5 days? if no, then return true, else return false
							 * -shifts < requested shift count?
							 * -is the employee paired with a colleague whom he or she likes to work with? (this feature can be checked or unchecked in the GUI)
							 * -does the employee avoid all colleagues he or she dislikes? (this feature can be checked or unchecked in the GUI)
							 */
						searcherWithWeekends(r, 1, supercalendar1);
						searcherWithWeekends(r, 2, supercalendar2);
						searcherWithWeekends(r, 3, supercalendar3);
						searcherWithWeekends(r, 4, supercalendar4);
						searcher(r, 1, x, supercalendar1);
						searcher(r, 2, x, supercalendar2);
						searcher(r, 3, x, supercalendar3);
						searcher(r, 4, x, supercalendar4);
					} //close if getPriority == 1
								
					else if (r.getPriority() == 2) {
						searcherWithWeekends(r, 1, supercalendar1);
						searcherWithWeekends(r, 2, supercalendar2);
						searcherWithWeekends(r, 3, supercalendar3);
						searcherWithWeekends(r, 4, supercalendar4);
						searcher(r, 1, x, supercalendar1);
						searcher(r, 2, x, supercalendar2);
						searcher(r, 3, x, supercalendar3);
						searcher(r, 4, x, supercalendar4);
					} //close if getPriority == 2
									
					else if(r.getPriority() == 3) {			
						searcherWithWeekends(r, 1, supercalendar1);
						searcherWithWeekends(r, 2, supercalendar2);
						searcherWithWeekends(r, 3, supercalendar3);
						searcherWithWeekends(r, 4, supercalendar4);
						searcher(r, 1, x, supercalendar1);
						searcher(r, 2, x, supercalendar2);
						searcher(r, 3, x, supercalendar3);
						searcher(r, 4, x, supercalendar4);
					} //close if getPriority == 3
									
					else if(r.getPriority() == 4) {					
						searcherWithWeekends(r, 1, supercalendar1);
						searcherWithWeekends(r, 2, supercalendar2);
						searcherWithWeekends(r, 3, supercalendar3);
						searcherWithWeekends(r, 4, supercalendar4);
						searcher(r, 1, x, supercalendar1);
						searcher(r, 2, x, supercalendar2);
						searcher(r, 3, x, supercalendar3);
						searcher(r, 4, x, supercalendar4);
					} //close if getPriority == 4
									
					else if(r.getPriority() == 5) {					
						searcherWithWeekends(r, 1, supercalendar1);
						searcherWithWeekends(r, 2, supercalendar2);
						searcherWithWeekends(r, 3, supercalendar3);
						searcherWithWeekends(r, 4, supercalendar4);
						searcher(r, 1, x, supercalendar1);
						searcher(r, 2, x, supercalendar2);
						searcher(r, 3, x, supercalendar3);
						searcher(r, 4, x, supercalendar4);
					} //close if getPriority == 5		
				} //close if r.getIntegerDates().contains(x)		
			} //close for monthLength
		
//create output, show the calendar with all names:
			GUIoutputCalendar outputWindow = new GUIoutputCalendar(supercalendar1, supercalendar2, supercalendar3, supercalendar4, month, year);			
	} //close requestProcessor
	
						
	public static List<Integer> weekendFinder() {			
//find the days in the given month that are weekend days:
		int currentYear    = Integer.parseInt(year);
		int currentMonth = Integer.parseInt(month);
		List<Integer> weekends = new ArrayList<>();
		List<Integer> holidayList = new ArrayList<>();

		IntStream.rangeClosed(1,YearMonth.of(currentYear, currentMonth).lengthOfMonth())
			.mapToObj(day -> LocalDate.of(currentYear, currentMonth, day))
			.filter(date -> date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)
			.forEach(date -> weekends.add(date.getDayOfMonth()));
				
		BankHolidays bankHolidays = new BankHolidays();	
		holidayList = bankHolidays.holidayHandler(currentMonth);

		for(Integer i : holidayList)
			if(!weekends.contains(i))
				weekends.add(i);
		
		weekends.sort(null);
							     
		return weekends;
	} //close weekendFinder
					
	public static boolean dayChecker(int day, Request r, Map<Integer, List<Employee>>inputCalendar) {
//check whether the the employee has one or more shifts on the days surrounding the checked day--if yes, then return false, else return true 
//(true means that the day is safe, and the employee can work on that day):
		dayIsSafe = true;
		List<Integer> dayRangeChecker = new ArrayList<>(Arrays.asList(day, day+1, day+2, day+3, day+4, day+5, day-1, day-2, day-3, day-4, day-5));

		for(Integer probe : dayRangeChecker) {
			if(supercalendar.containsKey(probe) && supercalendar.get(probe).containsKey(r.getEmployee()) && supercalendar.get(probe).get(r.getEmployee()).equals(true)
					|| (supercalendar.containsKey(probe) && supercalendar.get(probe).keySet().stream().anyMatch(x -> x.getEmployee().getEmployeeDetailsAsString().equals(r.getEmployee().getEmployeeDetailsAsString()) && supercalendar.get(probe).get(x.getEmployee()).equals(true)))
		)		{
				dayIsSafe = false;
			} //close if
		} //close for
		return dayIsSafe;
	} //close dayChecker
					
	public static void dayFiller(int day, Request r) {
//if all conditions are fulfilled, fill the employee's into the supercalendar:
		Map<Employee, Boolean> toBeAltered = new HashMap<>(supercalendar.get(day));

		for(int x = 0; x < toBeAltered.size(); x++) {
			toBeAltered.remove(r.getEmployee());
			toBeAltered.putIfAbsent(r.getEmployee(), true);					
		}	//close for	
						
		supercalendar.put(day, toBeAltered);
		shiftCounter.put(r.getEmployee(), shiftCounter.get(r.getEmployee())+1);
		weekendCounter.put(r.getEmployee(), weekendCounter.get(r.getEmployee())+1);
	}//close dayfiller
					
	public static boolean peerFinder(int day, Request r, boolean preferEmployees) {
//check whether there are any employees in the supercalendar with whom the employee prefers to work with--if yes, then return true 
//(only check this condition if the corresponding checkbox in the GUI is selected, if it's not selected, then always return true):
		boolean matchFound = false;
			
		if(preferEmployees == true) {
			for(String s : r.getPrefers())
				if(!supercalendar.get(day).isEmpty() && supercalendar.get(day).keySet().stream().anyMatch(e -> e.getEmployeeDetailsAsString().contains(s) ))
					matchFound = true;
		} //close if preferEmployees == true
		else if(preferEmployees == false)
			matchFound = true;
		
		return matchFound;
	} //close peerFinder
					
	public static boolean peerShunner(int day, Request r, boolean avoidEmployees) {
//check whether there are any employees in the supercalendar whom the employee would like to avoid--if yes, then return false 
//(only check this condition if the corresponding checkbox in the GUI is selected, if it's not selected, then always return true):
		boolean shunned = true;
			
		if(avoidEmployees == true) {
			for(String s : r.getAvoids())
				if(!supercalendar.get(day).isEmpty() && supercalendar.get(day).keySet().stream().anyMatch(e -> e.getEmployeeDetailsAsString().contains(s) ))
					shunned = false;
		} //close if avoidEmployees == true
		else if (avoidEmployees == false)
			shunned = true;
		
		return shunned;
	} //close peerShunner

	public static boolean previousMonthChecker(int day, Request r) {
//check the last five days of the previous month to make sure that the there are at least 5 days between the employee's first shift in the current month
//and his last shift in the previous month		
//(this condition is only active if the corresponding checkbox in the GUI is selected):
		dayIsSafe = true;
		List<Integer> dayRangeChecker = new ArrayList<>(Arrays.asList(day-5, day-4, day-3, day-2, day-1, day));

		for(Integer probe : dayRangeChecker) {
			if(ignorePreviousMonth == false) {
				if(lastFiveDays != null && !lastFiveDays.isEmpty())
					if(lastFiveDays.containsKey(probe) && lastFiveDays.get(probe).stream()
						.map(e -> e.getEmployeeDetailsAsString())
						.collect(Collectors.toList()).contains(r.getEmployee().getEmployeeDetailsAsString()))
						dayIsSafe = false;
				} //close if(ignorePreviousMonth == false)
			} //close for
		return dayIsSafe;
	} //close previousMonthChecker

	public static Map<Integer, Map<Employee, Boolean>> supercalendarGetter(){
	return supercalendar;	//not used
	}

	public static void supercalendarArchiver(GUImainWindow ownerWindow) {
//archive the last five days of the current month for next month's calculation:
		FinishedCalendar.archiveLastFiveDays(ownerWindow, supercalendar);
	} //close supercalendarArchiver

	public static void supercalendarShower(GUImainWindow ownerWindow) {
//display the archived last 5 days (of the previous month): 
		GUIarchiveDisplayer archiveDisplayer = new GUIarchiveDisplayer(ownerWindow, FinishedCalendar.loadLastFiveDays());
	} //close supercalendarShower	
	
	public static void searcherWithWeekends(Request r, int shiftType, Map<Integer, List<Employee>> supercalendarInput) {

		if(r.getEmployee().getShiftDetails().contains(shiftType) && r.getWeekendCount() > 0 && r.getIntegerDates().stream().anyMatch((i) -> weekends.contains(i))) {									
			List<Integer> weekendsOnly = r.getIntegerDates().stream().filter((i) -> weekends.contains(i)).collect(Collectors.toList());
		for(Integer i : weekendsOnly)
			if(dayChecker(i, r, supercalendarInput) && shiftCounter.get(r.getEmployee()) < r.getShiftCount()
					&& weekendCounter.get(r.getEmployee()) < r.getWeekendCount()
					&& peerFinder(i, r, preferEmployees)
					&& peerShunner(i, r, avoidEmployees)
					&& previousMonthChecker(i, r)
					) {
						dailyEmployeeList = new ArrayList<>(supercalendarInput.get(i));
						if(dailyEmployeeList.isEmpty()) {
							dailyEmployeeList.add(r.getEmployee());
							supercalendarInput.put(i,  dailyEmployeeList);
							dayFiller(i, r);
						} //close if !dailyEmployeeList.contains employee
			} //close if daychecker
		} //close if shift type is shiftType && weekend		
	} //close searcherWithWeekends
	
	public static void searcher(Request r, int shiftType, int day, Map<Integer, List<Employee>> supercalendarInput) {
		if(r.getEmployee().getShiftDetails().contains(shiftType)) {
			if(dayChecker(day, r, supercalendarInput) && shiftCounter.get(r.getEmployee()) < r.getShiftCount()
					&& previousMonthChecker(day, r)
					&& peerFinder(day, r, preferEmployees)
					&& peerShunner(day, r, avoidEmployees)
					) {
					dailyEmployeeList = new ArrayList<>(supercalendarInput.get(day));
					if(dailyEmployeeList.isEmpty()) {
						dailyEmployeeList.add(r.getEmployee());
						supercalendarInput.put(day,  dailyEmployeeList);
						dayFiller(day, r);
					} //close if !dailyEmployeeList.contains r.getEmploee
			} //close if daychecker
		} //close if shift type is shiftType && weekday	
	} //close searcher
} //close Supercalendar





