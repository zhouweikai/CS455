package pa4;

//Name: Weikai Zhou
//USC NetID: weikaizh
//CS 455 PA4
//Spring 2018

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A Rack of Scrabble tiles
 * In this class, our constructor bring in the input by user, and find the
 * unique, mult, k, and use methods to find all subsets, we have a public
 * method to return to the outer class
 */

public class Rack {

	private String unique;
	private int[] mult;
	private int startIndex;

	/**
	 *
	 * @param input it is the input String by user
	 */
	public Rack(String input) {
		this.unique = findUnique(input);
		this.mult = findMult(input);
		this.startIndex = 0;
	}

	/**
	 * @return a list containing all subsets
	 */
	public ArrayList<String> getAllSubsets() {
		ArrayList<String> allSubsets = allSubsets(unique, mult, startIndex);
		return allSubsets;
	}


	/**
	 * The method focus on finding "unique"
	 *
	 * @param uniqueArr the ArrayList to make up "unique"
	 * @param input     this is the input word
	 * @return
	 */
	private static String findUnique(String input) {
		char[] inputArr = input.toCharArray();
		Arrays.sort(inputArr);
		ArrayList<Character> uniqueArr = new ArrayList<>();
		int slow = 0;
		int fast = 0;
		uniqueArr.add(inputArr[fast]);
		while (fast < inputArr.length) {// use the two pointer to find unique
			if (inputArr[fast] != inputArr[slow]) {
				uniqueArr.add(inputArr[fast]);
				slow = fast;
			}
			fast++;
		}
		char[] uniqueArray = new char[uniqueArr.size()];
		for (int i = 0; i < uniqueArr.size(); i++) {
			uniqueArray[i] = uniqueArr.get(i);
		}
		String unique = new String(uniqueArray);
		//System.out.println(unique);
		return unique;
	}


	/**
	 * The method focus on finding "mult"
	 *
	 * @param inputArr the ArrayList to make up "mult"
	 * @param input    this is the input word
	 * @return
	 */
	private static int[] findMult(String input) {
		ArrayList<Integer> multArr = new ArrayList<>();
		char[] inputArr = input.toCharArray();
		Arrays.sort(inputArr);
		int slow = 0;
		int fast = 0;
		while (fast < inputArr.length) {// use the two pointer to find "mult"
			if (inputArr[fast] != inputArr[slow]) {
				multArr.add(fast - slow);
				slow = fast;
			}
			fast++;
		}
		multArr.add(fast - slow);
		int[] mult = new int[multArr.size()];
		for (int i = 0; i < multArr.size(); i++) {
			mult[i] = multArr.get(i);
		}
		return mult;
	}


	/**
	 * Finds all subsets of the multiset starting at position k in unique and mult.
	 * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
	 * unique.charAt(i).
	 * PRE: mult.length must be at least as big as unique.length()
	 * 0 <= k <= unique.length()
	 *
	 * @param unique a string of unique letters
	 * @param mult   the multiplicity of each letter from unique.
	 * @param k      the smallest index of unique and mult to consider.
	 * @return all subsets of the indicated multiset
	 * @author Claire Bono
	 */
	private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
		ArrayList<String> allCombos = new ArrayList<>();

		if (k == unique.length()) {  // multiset is empty
			allCombos.add("");
			return allCombos;
		}

		// get all subsets of the multiset without the first unique char
		ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

		// prepend all possible numbers of the first char (i.e., the one at position k)
		// to the front of each string in restCombos.  Suppose that char is 'a'...

		String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
		for (int n = 0; n <= mult[k]; n++) {
			for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets
				// we found in the recursive call
				// create and add a new string with n 'a's in front of that subset
				allCombos.add(firstPart + restCombos.get(i));
			}
			firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
		}

		return allCombos;
	}


}