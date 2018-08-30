import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;

public class MyCalendarTester {
	public static void main(String[] args) {
		//Create calendar and print it out. Set up scanner and formatter.
		LocalDate now = LocalDate.now();
		MyCalendar cal = new MyCalendar(now);
		cal.fillDays();
		cal.printCalendar();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
		Scanner sc = new Scanner(System.in);
		System.out.println("Select one of the following options: ");
		System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		while (sc.hasNextLine())
        {
			String input = sc.nextLine();
			//Create event
			if (input.equals("C")) {
				System.out.println("Please enter title, date, starting time, and ending time of an event: ");
				System.out.println("Title: ");
				String name = sc.nextLine();
				System.out.println("Date (YYYY-MM-DD): ");
				String date = sc.nextLine();
				System.out.println("Start Time (24 hour clock): ");
				String start = sc.nextLine();
				System.out.println("End Time (24 hour clock): ");
				String end = sc.nextLine();
				TimeInterval newInterval = new TimeInterval(LocalTime.parse(start), LocalTime.parse(end));
				Event newEvent = new Event(name, LocalDate.parse(date), newInterval);
				//check overlap with all events
				boolean overlap = false;
				for (Event e : cal.getList()) {
					if (newEvent.getInterval().checkOverlap(e.getInterval())) {
						overlap = true;
					}
				}
				if (!overlap) {
					cal.getList().add(newEvent);
				}
				else {
					System.out.println("Event Overlap");
				}
			}
		    //View by
			if (input.equals("V")) {
			   //choose whether you want to view in day or month view.
			   System.out.println("[D]ay View or [M]onth View");
			   String view = sc.nextLine();
			   //day view
			   if (view.equals("D")) {
				   System.out.println(formatter.format(now));
				   cal.printEventsPerDay();
				   System.out.println("[P]revious, [N]ext, or [G]o Back to main menu? : ");
				   //Scroll through days
				   while (sc.hasNextLine()) {
					   String option = sc.nextLine();
					   if (option.equals("P")) {
						   now = now.minusDays(1);
						   cal.setDate(now);
						   System.out.println(formatter.format(now));
						   cal.printEventsPerDay();
						   System.out.println("[P]revious, [N]ext, or [Go] Back? : ");
					   }
					   if (option.equals("N")) {
						   now = now.plusDays(1);
						   cal.setDate(now);
						   System.out.println(formatter.format(now));
						   cal.printEventsPerDay();
						   System.out.println("[P]revious, [N]ext, or [Go] Back? : ");
					   }
					   if (option.equals("G")) {
						   break;
					   }
				   }
			   }
		   }
			//Go to. Print out all strings for listed date.
			if (input.equals("G")) {
			  System.out.println("Input Date (YYYY-MM-DD): ");
			  String date = sc.nextLine();
			  LocalDate goTo = LocalDate.parse(date);
			  System.out.println(formatter.format(goTo));
			  for (Event e : cal.getList()) {
				  if (e.getDate().equals(goTo)) {
					  System.out.println(e.toString());
				  }
			  }
		  }
		 //Quit
		 if (input.equals("Q")) {
			 System.out.println("Finished");
			 break;
		 }
        }
	}
}
