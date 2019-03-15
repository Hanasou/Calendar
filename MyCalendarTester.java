package calendar;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;

public class MyCalendarTester {
	public static void main(String[] args) throws FileNotFoundException {
		//Create calendar and print it out. Set up scanner and formatter.
		LocalDate now = LocalDate.now();
		File eventText = new File("Events/events.txt");
		boolean overlap = false;
		MyCalendar cal = new MyCalendar(now);
		cal.fillDays();
		cal.printCalendar();
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
		Scanner sc = new Scanner(System.in);
		System.out.println("Select one of the following options: ");
		System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		while (sc.hasNextLine())
        {
			String input = sc.nextLine();
			//load events from events.txt
			//loading regular events not supported
			if (input.equals("L")) {
				Scanner events = new Scanner(eventText);
				while (events.hasNextLine()) {
					String name = events.nextLine();
					String date = events.next();
					String start = events.next();
					String end = events.next();
					String next = events.nextLine();
					TimeInterval loadInterval = new TimeInterval(LocalTime.parse(start), LocalTime.parse(end));
					Event loadEvent = new Event(name, LocalDate.parse(date), loadInterval);
					overlap = false;
					for (Event e : cal.getList()) {
						if (loadEvent.getDate().equals(e.getDate())) {
							if (loadEvent.getInterval().checkOverlap(e.getInterval())) {
								overlap = true;
								System.out.println("This event conflicts with another");
								break;
							}
						}
					}
					if (!overlap) {
						cal.getList().add(loadEvent);
						overlap = false;
						System.out.println(loadEvent.toString());
					}
				}
				events.close();
			}
			//Create event
			else if (input.equals("C")) {
				System.out.println("Please enter title, date, starting time, and ending time of an event: ");
				System.out.println("Title: ");
				String name = sc.nextLine();
				System.out.println("Date (YYYY-MM-DD): ");
				String date = sc.nextLine();
				System.out.println("Start Time (24 hour clock, HH:MM): ");
				String start = sc.nextLine();
				System.out.println("End Time (24 hour clock, HH:MM): ");
				String end = sc.nextLine();
				TimeInterval newInterval = new TimeInterval(LocalTime.parse(start), LocalTime.parse(end));
				Event newEvent = new Event(name, LocalDate.parse(date), newInterval);
				//check overlap with all events
				for (Event e : cal.getList()) {
					if (newEvent.getDate().equals(e.getDate())) {
						if (newEvent.getInterval().checkOverlap(e.getInterval())) {
							overlap = true;
						}
					}
				}
				if (!overlap) {
					cal.getList().add(newEvent);
					overlap = false;
				}
				else {
					System.out.println("This event conflicts with another");
				}
			}
		    //View by
			else if (input.equals("V")) {
			   //choose whether you want to view in day or month view.
			   System.out.println("[D]ay View or [M]onth View");
			   String view = sc.nextLine();
			   //day view
			   if (view.equals("D")) {
				   System.out.println(dayFormatter.format(now));
				   cal.printEventsPerDay();
				   System.out.println("[P]revious, [N]ext, or [G]o Back to main menu? : ");
				   //Scroll through days
				   while (sc.hasNextLine()) {
					   String option = sc.nextLine();
					   if (option.equals("P")) {
						   cal.retractByDay(1);
						   now = now.minusDays(1);
						   System.out.println(dayFormatter.format(now));
						   cal.printEventsPerDay();
						   System.out.println("[P]revious, [N]ext, or [Go] Back? : ");
					   }
					   else if (option.equals("N")) {
						   cal.advanceByDay(1);
						   now = now.plusDays(1);
						   System.out.println(dayFormatter.format(now));
						   cal.printEventsPerDay();
						   System.out.println("[P]revious, [N]ext, or [Go] Back? : ");
					   }
					   else if (option.equals("G")) {
						   break;
					   }
				   }
			   }
			   else if (view.equals("M")) {
				   cal.printCalendarMonthView();
				   cal.printEventsPerMonth();
				   System.out.println("[P]revious, [N]ext, or [G]o Back to main menu? : ");
				   //Scroll through days
				   while (sc.hasNextLine()) {
					   String option = sc.nextLine();
					   if (option.equals("P")) {
						   cal.retractByMonth(1);
						   cal.fillDays();
						   cal.printCalendarMonthView();
						   cal.printEventsPerMonth();
						   System.out.println("[P]revious, [N]ext, or [Go] Back? : ");
					   }
					   else if (option.equals("N")) {
						   cal.advanceByMonth(1);
						   cal.fillDays();
						   cal.printCalendarMonthView();
						   cal.printEventsPerMonth();
						   System.out.println("[P]revious, [N]ext, or [Go] Back? : ");
					   }
					   else if (option.equals("G")) {
						   break;
					   }
				   }
			   }
		   }
			//Prints all events
			else if (input.equals("E")) {
				   for (Event e : cal.getList()) {
					   System.out.println(e.toString());
				   }
			  }
			//Go to. Print out all strings for listed date.
			else if (input.equals("G")) {
			  System.out.println("Input Date (YYYY-MM-DD): ");
			  String date = sc.nextLine();
			  LocalDate goTo = LocalDate.parse(date);
			  System.out.println(dayFormatter.format(goTo));
			  for (Event e : cal.getList()) {
				  if (e.getDate().equals(goTo)) {
					  System.out.println(e.toString());
				  }
			  }
		  }
			else if (input.equals("D")) {
				ArrayList<Event> selected = new ArrayList<Event>();
				ArrayList<Event> removeThese = new ArrayList<Event>();
				System.out.println("[S]elected or [A]ll?");
				String deletion = sc.nextLine();
				if (deletion.equals("S")) {
					System.out.println("Input date of event you want to delete (YYYY-MM-DD): ");
					String deleteDate = sc.nextLine();
					for (Event e : cal.getList()) {
						if (LocalDate.parse(deleteDate).equals(e.getDate())) {
							selected.add(e);
							System.out.println(e.toString());
						}
					}
					System.out.println("Enter the name of the event you want to delete");
					String deleteThis = sc.nextLine();
					for (Event f : selected) {
						if (deleteThis.equals(f.getName())) {
							removeThese.add(f);
						}
					}
					if (removeThese.size() == 0) {
						System.out.println("No events with that name on this date");
					}
					else {
						cal.getList().removeAll(removeThese);
						System.out.println("Deleted");
					}
				}
				else if (deletion.equals("A")) {
					System.out.println("Clear all events on what date? ");
					String deleteDate = sc.nextLine();
					for (Event e : cal.getList()) {
						if (LocalDate.parse(deleteDate).equals(e.getDate())) {
							selected.add(e);
							System.out.println(e.toString());
						}
					}
					cal.getList().removeAll(selected);
					System.out.println("Above Events Deleted");
				}
			}
			//Quit
			else if (input.equals("Q")) {
			 System.out.println("Finished");
			 break;
		 }
        }
		sc.close();
	}
}
