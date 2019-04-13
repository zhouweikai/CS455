package lab5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadTester {
	public static void main(String[] args) {
		
		while (true) {
			
			System.out.println("Enter a space separated list of numbers: ");
			Scanner lineScanner = new Scanner(System.in);
			String numString = lineScanner.nextLine();
			
			List<Integer> numArr = new ArrayList<> ();

			if (numString.length() < 1) {
				System.out.println(numArr);
			} else {
				String[] inNumString = numString.split(" ");
				for (int i = 0; i < inNumString.length; i++) {
					numArr.add(Integer.parseInt(inNumString[i]));
					
				}
				System.out.println(numArr);
			}
			
		
		}
		
		
	}

}
