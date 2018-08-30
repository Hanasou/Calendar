import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.TreeSet;

public class Event implements Comparable<Event>{

	private String name;
	private LocalDate date;
	private TimeInterval interval;
	
	public Event(String name, LocalDate date, TimeInterval interval) {
		this.name = name;
		this.date = date;
		this.interval = interval;
	}
	
	public String toString() {
		return name + " " + date.toString() + " " + interval.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public TimeInterval getInterval() {
		return interval;
	}
	
	/**
	 * Compare events so that they get sorted properly in the event list. Compare their year, then month, then day.
	 */
	public int compareTo(Event that) {
		int yearCmp = this.date.getYear() - that.date.getYear();
		int monthCmp = this.date.getMonthValue() - that.date.getMonthValue();
		int dayCmp = this.date.getDayOfMonth() - that.date.getDayOfMonth();
		if (yearCmp != 0) {
			return yearCmp;
		}
		if (monthCmp != 0) {
			return monthCmp;
		}
		return dayCmp;
	}
}
