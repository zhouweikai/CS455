package pa1;

// Name: Weikai Zhou
// USC NetID: 7988402237
// CS 455 PA1
// Spring 2018

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;

public class CoinTossSimulator {
	private int twoHeads;
	private int twoTails;
	private int headTails;
	private int numTrialsCoin;

	/**
	 * Creates a coin toss simulator with no trials done yet.
	 */
	public CoinTossSimulator() {

	}

	/**
	 * Runs the simulation for numTrials more trials. Multiple calls to this method
	 * without a reset() between them *add* these trials to the current simulation.
	 * 
	 * @param numTrials
	 *            number of trials to for simulation; must be >= 1
	 */
	public void run(int numTrials) {

		// after receiving the numTrials, do the for loop
		for (int i = 0; i < numTrials; i++) {
			Random rand = new Random();
			int res1 = rand.nextInt(2);
			int res2 = rand.nextInt(2);
			
			// return random number with 0 inclusive and 2 exclusive, so
			// it returns either 0 or 1, 0 for head while 1 for tail
			if (res1 == 0 && res2 == 0)
				twoHeads++;
			else if (res1 == 1 && res2 == 1)
				twoTails++;
			else
				headTails++;
		}
		numTrialsCoin += numTrials;

	}

	public boolean addRes() {
		return getNumTrials() == getTwoHeads() + getTwoTails() + getHeadTails();
	}

	/**
	 * Get number of trials performed since last reset.
	 */
	public int getNumTrials() {
		return numTrialsCoin; // DUMMY CODE TO GET IT TO COMPILE
	}

	/**
	 * Get number of trials that came up two heads since last reset.
	 */
	public int getTwoHeads() {
		return twoHeads; // DUMMY CODE TO GET IT TO COMPILE
	}

	/**
	 * Get number of trials that came up two tails since last reset.
	 */
	public int getTwoTails() {
		return twoTails; // DUMMY CODE TO GET IT TO COMPILE
	}

	/**
	 * Get number of trials that came up one head and one tail since last reset.
	 */
	public int getHeadTails() {
		return headTails; // DUMMY CODE TO GET IT TO COMPILE
	}

	/**
	 * Resets the simulation, so that subsequent runs start from 0 trials done.
	 */
	// set all the result to 0
	public void reset() {
		twoHeads = 0;
		twoTails = 0;
		headTails = 0;
		numTrialsCoin = 0;
	}

}