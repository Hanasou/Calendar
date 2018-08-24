import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MyCalendar {
	public MyCalendar(LocalDate c) {
		System.out.print(c.getMonth() + " ");
		System.out.println(c.getYear());
		System.out.println("Su Mo Tu We Th Fr Sa");
	}
	
	public void printDays(LocalDate c) {
		
	}
}
