package pa4;

//Name: Weikai Zhou
//USC NetID: weikaizh
//CS 455 PA4
//Spring 2018

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;


/**
 * A dictionary of all anagram sets.
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 *
 * @param wordMap this is a HashMap to organize the original dict, with its key
 *                representing char-sorted String of a list of its anagrams, and
 *                its value representing all the anagrams
 */

public class AnagramDictionary {

	private Map<String, ArrayList<String>> wordMap;


	/**
	 * Create an anagram dictionary from the list of words given in the file
	 * indicated by fileName.
	 * PRE: The strings in the file are unique.
	 *
	 * @param fileName   the name of the file to read from
	 * @param dictionary the original dict given
	 * @param word       words from the original dict
	 * @param key        the sorted String as the representation of a group of anagrams,
	 *                   it may not be a valid word
	 * @param value      the ArrayList which contains all the anagrams of the key
	 * @throws FileNotFoundException if the file is not found
	 */
	public AnagramDictionary(String fileName) throws FileNotFoundException {


		File dictionary = new File(fileName);
		Scanner in = new Scanner(dictionary);
		wordMap = new HashMap<>();
		while (in.hasNext()) {
			String word = in.next();
			char[] arrayOfWord = word.toCharArray();
			Arrays.sort(arrayOfWord);
			String key = new String(arrayOfWord);
			if (wordMap.containsKey(key)) {
				wordMap.get(key).add(word);// if the word is anagram of key, put it into the value of the key
			} else {
				ArrayList<String> value = new ArrayList<>();
				value.add(word);
				wordMap.put(key, value);// if the word is not the anagram of any key, put the word as 1st value of its key
			}
		}


	}


	/**
	 * Get all anagrams of the given string. This method is case-sensitive.
	 * E.g. "CARE" and "race" would not be recognized as anagrams.
	 *
	 * @param s        string to process
	 * @param arrayOfS a sorted string to represent the rack, to help check whether we can find its anagrams
	 * @param sSorted  a string with its chars sorted from the input String s
	 * @return a list of the anagrams of s
	 */
	public ArrayList<String> getAnagramsOf(String s) {
		char[] arrayOfS = s.toCharArray();
		Arrays.sort(arrayOfS);
		String sSorted = new String(arrayOfS);
		if (wordMap.containsKey(sSorted)) {
			return wordMap.get(sSorted);
		} else {
			return new ArrayList<>();
		}
	}
}