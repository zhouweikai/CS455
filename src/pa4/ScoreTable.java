package pa4;

//Name: Weikai Zhou
//USC NetID: weikaizh
//CS 455 PA4
//Spring 2018

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This class is used to score each word, and sort them using score and string serialization
 */
public class ScoreTable {
	//private TreeSet<String> wordScored;
	private HashMap<String, Integer> wordScore;


	/**
	 * @param allAnag the input that containing candidate results
	 */
	public ScoreTable(ArrayList<String> allAnag) {
		Collections.sort(allAnag, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				char startChar;
				if (o1.charAt(0) - 'a' >= 0 && o1.charAt(0) - 'a' <= 25) {
					startChar = 'a';
				} else {
					startChar = 'A';
				}
				int score1 = getScore(o1, startChar);
				int score2 = getScore(o2, startChar);
				if (score2 - score1 != 0) {
					return score2 - score1;
				}
				return o1.compareTo(o2);
			}
		});
	}


	/**
	 * @param word      the input word to be scored
	 * @param startChar it is 'A' or 'a'
	 * @return
	 */
	public int getScore(String word, char startChar) {
		char[] array = word.toCharArray();
		int score = 0;
		for (int i = 0; i < array.length; i++) {
			int diff = array[i] - startChar;
			if (diff == 0 || diff == 4 || diff == 8 || diff == 14 || diff == 20 || diff == 11 || diff == 13 || diff == 17 || diff == 18 || diff == 19) {
				score = score + 1;
			}
			if (diff == 3 || diff == 6) {
				score = score + 2;
			}
			if (diff == 1 || diff == 2 || diff == 12 || diff == 15) {
				score = score + 3;
			}
			if (diff == 5 || diff == 7 || diff == 21 || diff == 22 || diff == 24) {
				score = score + 4;
			}
			if (diff == 10) {
				score = score + 5;
			}
			if (diff == 9 || diff == 23) {
				score = score + 8;
			}
			if (diff == 16 || diff == 25) {
				score = score + 10;
			}
		}
		return score;
	}
}
