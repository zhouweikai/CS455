package pa4;

//Name: Weikai Zhou
//USC NetID: weikaizh
//CS 455 PA4
//Spring 2018

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the main method to do the game.
 */
public class WordFinder {
	public static void main(String[] args) {

		String fileName;

		if (args.length == 0) {
			fileName = "/Users/weikaizhou/Documents/my_java_docs/CSCI455/src/pa4/sowpods.txt";//Users/weikaizhou/Documents/my_java_docs/CSCI455/src/pa4/sowpods.txt
		} else {
			fileName = args[0];
		}

		try {

			AnagramDictionary anagDict = new AnagramDictionary(fileName);// get all the anagrams, have to sort to do the output

			System.out.println("Type . to quit.");
			Scanner in = new Scanner(System.in);
			System.out.print("Rack? ");
			String input = in.next();


			while (!input.equals(".")) {// if user not input a dot, then do the while loop
				findWord(input, anagDict);
				input = in.next();
			}
			//System.out.println();
			return;

		} catch (FileNotFoundException e) {

			System.out.println("Error: File does not exist: " + fileName);
			System.out.println("Exiting program.");

		}

	}


	/**
	 * @param input    the input of user
	 * @param anagDict the organized dict using file arg[0]
	 */
	private static void findWord(String input, AnagramDictionary anagDict) {
		ArrayList<String> allAnag = new ArrayList<>();
		Rack rack = new Rack(input);// get all the subsets of the word given by user
		ArrayList<String> allSubsets = rack.getAllSubsets();
		for (int i = 0; i < allSubsets.size(); i++) {
			ArrayList<String> temp = anagDict.getAnagramsOf(allSubsets.get(i));
			for (String aAnagram : temp) {
				allAnag.add(aAnagram);
			}
		}

		char[] inputArr = input.toCharArray();
		Arrays.sort(inputArr);
		String inputSorted = new String(inputArr);


		System.out.println("We can make " + allAnag.size() + " words from " + "\"" + inputSorted + "\"");

		if (allAnag.size() != 0) {
			System.out.println("All of the words with their scores (sorted by score):");
			printSortResult(allAnag);
		}

		System.out.print("Rack? ");

	}

	/**
	 * @param allAnag the input list containing anagrams
	 */
	private static void printSortResult(ArrayList<String> allAnag) {
		ScoreTable table = new ScoreTable(allAnag);
		for (int i = 0; i < allAnag.size(); i++) {
			String word = allAnag.get(i);
			char startChar;
			if (word.charAt(0) - 'a' >= 0 && word.charAt(0) - 'a' <= 25) {
				startChar = 'a';
			} else {
				startChar = 'A';
			}
			System.out.println(table.getScore(word, startChar) + ": " + word);
		}
	}

}