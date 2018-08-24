import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MyCalendarTester {
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		MyCalendar cal = new MyCalendar(now);
		Scanner sc = new Scanner(System.in);
		System.out.println("Select one of the following options: ");
		System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		while (sc.hasNextLine())
        {
			String input = sc.nextLine();
			if (input.equals("C")) {
				System.out.println("Please enter title, date, starting time, and ending time of an event: ");
				
			}
        }
	}
}
