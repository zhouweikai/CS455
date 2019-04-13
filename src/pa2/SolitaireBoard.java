package pa2;

//Name: Weikai Zhou
//USC NetID: weikaizh
//CSCI455 PA2
//Spring 2018

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/*
class SolitaireBoard
The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
for CARD_TOTAL that result in a game that terminates.
(See comments below next to named constant declarations for more details on this.)
*/

public class SolitaireBoard {

	public static final int NUM_FINAL_PILES = 9;
	// number of piles in a final configuration
	// (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

	public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
	// bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
	// see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
	// the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

	// Note to students: you may not use an ArrayList -- see assgt description for
	// details.

	/**
	 * Representation invariant:
	 * 
	 * <put rep. invar. comment here> the card_total is the invariant and it will
	 * not change when run the code.
	 * 
	 * the NUM_FINAL_PILES is the invariant and it will not change each time the 
	 * game finishes.
	 * 
	 * the elements in final result are from 1 to NUM_FINAL_PILES, in any order
	 * 
	 */

	// <add instance variables here>
	private int[] cardPiles;
	private int sizeCardPiles;

	/**
	 * Creates a solitaire board with the configuration specified in piles. piles
	 * has the number of cards in the first pile, then the number of cards in the
	 * second pile, etc. PRE: piles contains a sequence of positive numbers that sum
	 * to SolitaireBoard.CARD_TOTAL
	 */
	public SolitaireBoard(ArrayList<Integer> piles) {

		cardPiles = new int[CARD_TOTAL];

		// keep the size of this partially filled array
		sizeCardPiles = piles.size();

		// copy elements from ArrayList to array
		for (int i = 0; i < sizeCardPiles; i++) {
			cardPiles[i] = piles.get(i);
		}

		assert isValidSolitaireBoard(); // sample assert statement (you will be adding more of these calls)
	}

	/**
	 * Creates a solitaire board with a random initial configuration.
	 */
	public SolitaireBoard() {

		Random randomNumGenerator = new Random();

		int[] randomCardNumsForEachPile = new int[CARD_TOTAL];
		int[] cardPilesBuilder = new int[CARD_TOTAL];

		int numCardsToGenerate = CARD_TOTAL;
		int iindexOfPile = 0;

		// how many can I create from rest
		while (numCardsToGenerate > 0) {
			randomCardNumsForEachPile[iindexOfPile] = randomNumGenerator.nextInt(numCardsToGenerate) + 1;
			numCardsToGenerate -= randomCardNumsForEachPile[iindexOfPile];
			iindexOfPile++;
		}

		if (iindexOfPile == 1) {
			cardPiles = new int[] { CARD_TOTAL };
		} else {
			int sumOfCards = 0;// from 0 to (i - 2), the sum
			for (int j = 0; j <= iindexOfPile - 2; j++) {
				cardPilesBuilder[j] = randomCardNumsForEachPile[j];
				sumOfCards += cardPilesBuilder[j];
			}

			cardPilesBuilder[iindexOfPile - 1] = CARD_TOTAL - sumOfCards;// so that the total sum is CARD_TOTAL
			cardPiles = cardPilesBuilder;
		}
		sizeCardPiles = iindexOfPile;
		assert isValidSolitaireBoard();

	}

	/**
	 * Plays one round of Bulgarian solitaire. Updates the configuration according
	 * to the rules of Bulgarian solitaire: Takes one card from each pile, and puts
	 * them all together in a new pile. The old piles that are left will be in the
	 * same relative order as before, and the new pile will be at the end.
	 */
	public void playRound() {
		int end = 0;// to keep the valid number in piles
		int count = sizeCardPiles;
		for (int i = 0; i < count; i++) {
			if (cardPiles[i] - 1 > 0) {
				cardPiles[end++] = cardPiles[i] - 1;
			}
		}
		cardPiles[end] = count;

		// the rest of the array should be zeros
		for (int i = end + 1; i < count; i++) {
			cardPiles[i] = 0;
		}

		sizeCardPiles = end + 1;

		assert isValidSolitaireBoard();
	}

	/**
	 * Returns true iff the current board is at the end of the game. That is, there
	 * are NUM_FINAL_PILES piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES,
	 * in any order.
	 */

	public boolean isDone() {

		if (sizeCardPiles != NUM_FINAL_PILES) {
			assert isValidSolitaireBoard();
			return false;
		}

		Set<Integer> hash = new HashSet<>();
		// put all the elements in array in the set
		for (int i = 0; i < sizeCardPiles; i++) {
			hash.add(cardPiles[i]);
		}

		// judge if the set contains all the numbers from 1 to NUM_FINAL_PILES
		for (int i = 0; i < NUM_FINAL_PILES; i++) {
			if (!hash.contains(i + 1)) {
				assert isValidSolitaireBoard();
				return false;
			}
		}
		assert isValidSolitaireBoard();
		return true;

	}

	/**
	 * Returns current board configuration as a string with the format of a
	 * space-separated list of numbers with no leading or trailing spaces. The
	 * numbers represent the number of cards in each non-empty pile.
	 */
	public String configString() {

		if (sizeCardPiles == 1) {
			Integer j = new Integer(cardPiles[0]);
			assert isValidSolitaireBoard();
			return j.toString();
		}

		String conf = "";
		// make the string and add space between numbers
		for (int i = 0; i < sizeCardPiles - 1; i++) {
			Integer j = new Integer(cardPiles[i]);
			conf += j.toString() + " ";
		}

		Integer k = new Integer(cardPiles[sizeCardPiles - 1]);
		
		assert isValidSolitaireBoard();
		return (conf + k.toString());
	}

	/**
	 * Returns true iff the solitaire board data is in a valid state (See
	 * representation invariant comment for more details.)
	 */
	private boolean isValidSolitaireBoard() {
		int total = 0;
		for (int i = 0; i < sizeCardPiles; i++) {
			total += cardPiles[i];
		}

		if (total == CARD_TOTAL)// to check the total sum
			return true;
		else
			return false;

	}

	// <add any additional private methods here>

}