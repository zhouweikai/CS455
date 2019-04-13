package pa2;

import java.util.ArrayList;
import java.util.Scanner;

//Name: Weikai Zhou
//USC NetID: weikaizh
//CSCI455 PA2
//Spring 2018

/**
 * <add main program comment here> There are three conditions: userConfig,
 * singleStep and default one. In the main method, we simply call corresponding
 * private methods. There are also two helper methods: getInput, which return
 * the input of the user as String array; isValidInput, which checks if the user
 * give the right input.
 * 
 * For only userConfig method, we take the input and check it until we get valid
 * input, then we start to play the game, till the end.
 * 
 * For only singleStep method, we begin with random piles, and play step by step to
 * finish the game.
 * 
 * For both true, we take another method.
 * 
 * For defaultRun method, we play like the singleStep method, but not step by
 * step -- we simply print all the rounds once the game begins.
 */

public class BulgarianSolitaireSimulator {

	public static void main(String[] args) {

		boolean singleStep = false;
		boolean userConfig = false;
		
		Scanner in = new Scanner(System.in);

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u")) {
				userConfig = true;
			} else if (args[i].equals("-s")) {
				singleStep = true;
			}
		}

		// <add code here>

		// do method perspectively
		if (userConfig && !singleStep) {

			userConfig(in);

		} else if (singleStep && !userConfig) {

			singleStep(in);

		} else if (singleStep && userConfig) {
			
			userAndSingle(in);
			
		} else {

			defaultRun();

		}

	}

	// <add private static methods here>

	/**
	 * @param userInsputNoSpace
	 *            the result returned by getInpu() method
	 * @param piles
	 *            the ArrayList required by the constructor of SolitaireBoard class
	 * @param solitaireBoard
	 *            the object of SolitaireBoard
	 * @param time
	 *            counts the rounds of play
	 */
	
	private static void userAndSingle(Scanner in) {
		System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
		System.out
				.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");

		String[] userInputNoSpace = getInput(in);

		// if the input is not correct, force re-input
		while (!isValidInput(userInputNoSpace, SolitaireBoard.CARD_TOTAL)) {

			System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be "
					+ SolitaireBoard.CARD_TOTAL);

			userInputNoSpace = getInput(in);

		}

		ArrayList<Integer> piles = new ArrayList<Integer>();

		// copy elements, to make the ArrayList for constructor to use
		for (int i = 0; i < userInputNoSpace.length; i++) {

			piles.add(Integer.parseInt(userInputNoSpace[i]));

		}

		// construct the solitaireBoard object
		SolitaireBoard solitaireBoard = new SolitaireBoard(piles);

		System.out.print("Initial configuration: " + solitaireBoard.configString());
		int time = 1;
		while (!solitaireBoard.isDone()) {

			solitaireBoard.playRound();

			String newLine = in.nextLine();

			System.out.println("[" + time + "] " + "Current configuration: " + solitaireBoard.configString() + newLine);
			time++;

			System.out.print("<Type return to continue>");

		}
		System.out.println();
		System.out.println("Done!");
		
	}
	
	private static void userConfig(Scanner in) {
		System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
		System.out
				.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");

		String[] userInputNoSpace = getInput(in);

		// if the input is not correct, force re-input
		while (!isValidInput(userInputNoSpace, SolitaireBoard.CARD_TOTAL)) {

			System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be "
					+ SolitaireBoard.CARD_TOTAL);

			userInputNoSpace = getInput(in);

		}

		ArrayList<Integer> piles = new ArrayList<Integer>();

		// copy elements, to make the ArrayList for constructor to use
		for (int i = 0; i < userInputNoSpace.length; i++) {

			piles.add(Integer.parseInt(userInputNoSpace[i]));

		}

		// construct the solitaireBoard object
		SolitaireBoard solitaireBoard = new SolitaireBoard(piles);

		System.out.println("Initial configuration: " + solitaireBoard.configString());

		int time = 1;
		while (!solitaireBoard.isDone()) {
			solitaireBoard.playRound();
			System.out.println("[" + time + "] " + "Current configuration: " + solitaireBoard.configString());
			time++;
		}

		System.out.println("Done!");
	}

	/**
	 * @param newLine
	 *            String type variable, indicates another line
	 * @param time
	 *            count the rounds of play
	 */
	// call when singleStep is true
	private static void singleStep(Scanner in) {

		SolitaireBoard solitaireBoard = new SolitaireBoard();

		System.out.print("Initial configuration: " + solitaireBoard.configString());

		int time = 1;
		while (!solitaireBoard.isDone()) {

			solitaireBoard.playRound();

			String newLine = in.nextLine();

			System.out.println("[" + time + "] " + "Current configuration: " + solitaireBoard.configString() + newLine);
			time++;

			System.out.print("<Type return to continue>");

		}
		System.out.println();
		System.out.println("Done!");

	}

	/**
	 * @param solitaireBoard
	 *            the object of SolitaireBoard
	 * @param time
	 *            count the rounds of play
	 */
	private static void defaultRun() {

		SolitaireBoard solitaireBoard = new SolitaireBoard();

		System.out.println("Initial configuration: " + solitaireBoard.configString());

		int time = 1;
		while (!solitaireBoard.isDone()) {

			solitaireBoard.playRound();

			System.out.println("[" + time + "] " + "Current configuration: " + solitaireBoard.configString());
			time++;

		}

		System.out.println("Done!");

	}

	/**
	 * @param rawUserInput
	 *            the initial input as a String by user
	 * @param userInputNoSpace
	 *            the input without spaces as String array
	 */
	// return the String array containing user input
	private static String[] getInput(Scanner in) {

		System.out.println("Please enter a space-separated list of positive integers followed by newline:");

		String rawUserInput = in.nextLine();

		String[] userInputNoSpace = rawUserInput.split(" ");

		return userInputNoSpace;

	}

	/**
	 * 
	 * @param userInputNoSpace
	 *            As mentioned above, it is the String input without spaces
	 * @param CARD_TOTAL
	 *            the sum of all the numbers if valid; also the upper bound of every
	 *            single number
	 * @param number
	 *            perspectively refer to numbers of the user input in each loop
	 * @param total
	 *            add up all the numbers
	 * @return whether it is a valid input
	 */

	private static boolean isValidInput(String[] userInputNoSpace, int CARD_TOTAL) {
		
		if (userInputNoSpace == null || userInputNoSpace.length == 0) {
			return false;
		}

		for (int i = 0; i < userInputNoSpace.length; i++) {

			// use regular expression to check the rightness of the input
			if (!userInputNoSpace[i].matches("^[0-9]+$")) {
				return false;

			} else {
				int number = Integer.parseInt(userInputNoSpace[i]);

				if (number <= 0 || number > CARD_TOTAL) {
					return false;
				}
			}

		}

		int total = 0;
		for (int i = 0; i < userInputNoSpace.length; i++) {

			total += Integer.parseInt(userInputNoSpace[i]);

		}

		if (total != CARD_TOTAL) {
			return false;
		}

		return true;

	}

}