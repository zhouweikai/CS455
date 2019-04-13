package others;

import java.util.*;

public class HW3 {
	public static void main(String[] args) {
		Calendar thisCalendar = Calendar.getInstance();
		int thisMonth = thisCalendar.get(Calendar.MONTH) + 1;
		int thisDay = thisCalendar.get(Calendar.DATE);
		int thisYear = thisCalendar.get(Calendar.YEAR);
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your birth month [1..12]: ");
		int bMonth = in.nextInt();
		System.out.print("Enter your birth day of month: ");
		int bDay = in.nextInt();
		System.out.print("Enter your birth year [4-digit year]: ");
		int bYear = in.nextInt();
		if (thisMonth > bMonth) {
			System.out.println("Your birthday has already happened this year.");
		}
		else if (thisMonth == bMonth) {
			if (thisDay >= bDay) {
				System.out.println("Your birthday has already happened this year.");
			} else {
				System.out.println("Your birthday has not yet happened this year.");
			}
		} else {
			System.out.println("Your birthday has not yet happpened this year.");
		}
		System.out.println("You're " + (thisYear - bYear) + " years old.");
	}
}
