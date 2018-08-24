import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Event {

	private String name;
	private LocalDate date;
	private ArrayList<Event> list;
	private String start;
	private String end;
	
	public Event(String name, LocalDate date, String start, String end) {
		this.name = name;
		this.date = date;
		this.start = start;
		this.end = end;
	}
	
	public void printEvent() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/DD/YYYY");
		System.out.println(name);
		System.out.println(formatter.format(date));
	}
	
	public void printList() {
		for (Event e : list) {
			printEvent();
		}
	}
}
