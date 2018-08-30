import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TreeSet;

public class MyCalendar {
	private LocalDate calendar;
	private TreeSet<Event> eventList;
	private int[][] days;
	
	public MyCalendar(LocalDate calendar) {
		this.calendar = calendar;
		this.eventList = new TreeSet<Event>();
		this.days = new int[6][7];
	}
	
	public TreeSet<Event> getList() {
		return eventList;
	}
	
	public void setDate(LocalDate newCalendar) {
		this.calendar = newCalendar;
	}
	
	/**
	 * Prints the calendar with the Month and Year as a header. Also prints filled matrix that corresponds to calendar of specific month and year.
	 */
	public void printCalendar() {
		System.out.print(calendar.getMonth() + " ");
		System.out.println(calendar.getYear());
		System.out.println("Su Mo Tu We Th Fr Sa");
		printDays();
	}
	
	/**
	 * Prints out the days matrix.
	 */
	public void printDays() {
		//iterate through matrix
		for (int row = 0; row < days.length; row++) {
			for (int col = 0; col < days[0].length; col++) {
				//make sure single digit dates are aligned correctly
				if (days[row][col] < 10) {
					//Print spaces for dates that are 0
					if (days[row][col] == 0) {
						System.out.print("   ");
					}
					else {
						if (days[row][col] == calendar.getDayOfMonth()) {
							System.out.print("[" + days[row][col] + "]" + " ");
						}
						System.out.print(" " + days[row][col] + " ");
					}
				}
				//highlight current date
				else {
					if (days[row][col] == calendar.getDayOfMonth()) {
						System.out.print("[" + days[row][col] + "]" + " ");
					}
					System.out.print(days[row][col] + " ");
				}
			}
			//Print new line for end of each row
			System.out.println();
		}
	}
	
	public void addEvent(Event e) {
		eventList.add(e);
	}
	
	/**
	 * Prints out all events that are listed on the calendar's date
	 */
	public void printEventsPerDay() {
		for (Event e : eventList) {
			if (e.getDate().equals(calendar)) {
				System.out.println(e.toString());
			}
		}
	}
	
	/**
	 * Fills a matrix with appropriate days to put on calendar
	 */
	public void fillDays() {
		/*
		 * Changes variable depending on the month value.
		 * 0 for 30 day months
		 * 1 for 31 day months
		 * 2 for 28 day months
		 * 3 for 29 day months
		 */
		int firstDay = LocalDate.of(calendar.getYear(), calendar.getMonth(), 1).getDayOfWeek().getValue();
		int monthStatus = 0;
		if (calendar.getMonthValue() == 1 || calendar.getMonthValue() == 3 || calendar.getMonthValue() == 5 || 
				calendar.getMonthValue() == 7 || calendar.getMonthValue() == 8 || calendar.getMonthValue() == 10 || calendar.getMonthValue() == 12) {
			 monthStatus = 1;
		}
		if (calendar.getMonthValue() == 2) {
			 monthStatus = 2;
		}
		if (calendar.getMonthValue() == 2 && calendar.getYear() % 4 == 0) {
			monthStatus = 3;
		}
		int count = 0;
		for (int row = 0; row < days.length; row++) {
			for (int col = 0; col < days[0].length; col++ ) {
				if (row == 0 && col < firstDay - 1) {
					days[row][col] = 0;
				}
				else {
					days[row][col] = count;
					count++;
				}
				if (count > 29) {
					if (monthStatus == 0) {
						if (count > 31) {
							days[row][col] = 0;
						}
					}
					if (monthStatus == 1) {
						if (count > 32) {
							days[row][col] = 0;
						}
					}
					if (monthStatus == 2) {
						days[row][col] = 0;
					}
					if (monthStatus == 3) {
						if (count > 30) {
						days[row][col] = 0;
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		MyCalendar c = new MyCalendar(now);
		c.fillDays();
		c.printDays();
	}
}
