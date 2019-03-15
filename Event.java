package calendar;
import java.time.DayOfWeek;
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

	/**
	 * Returns an event in string format
	 * @return event into string
	 */
	public String toString() {
		return name + " " + date.toString() + " " + interval.toString();
	}
	
	/**
	 * Accessor for name of event
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Accessor for date
	 * @return date
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * mutator for date
	 * @param newDate
	 */
	public void setDate(LocalDate newDate) {
		this.date = newDate;
	}
	
	/**
	 * Accessor for time interval
	 * @return interval
	 */
	public TimeInterval getInterval() {
		return interval;
	}
	
	/**
	 * Compare events so that they get sorted properly in the event list. Compare their year, then month, then day.
	 * @param event that is being compared
	 */
	@Override
	public int compareTo(Event that) {
		int yearCmp = this.date.getYear() - that.date.getYear();
		int monthCmp = this.date.getMonthValue() - that.date.getMonthValue();
		int dayCmp = this.date.getDayOfMonth() - that.date.getDayOfMonth();
		int timeCmp = this.interval.getStart().compareTo(that.interval.getStart());
		if (yearCmp != 0) {
			return yearCmp;
		}
		if (monthCmp != 0) {
			return monthCmp;
		}
		if (dayCmp != 0) {
			return dayCmp;
		}
		return timeCmp;
	}
	
	/**
	 * Equals method to check whether two events are the same or not
	 * @param x object that is being compared to
	 */
	@Override
	public boolean equals(Object x) {
		Event that = (Event)x;
		return this.compareTo(that) == 0;
	}
}
