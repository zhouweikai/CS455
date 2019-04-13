package mid1;

import java.util.Random;

public class SlotMachine {
	public static final int SHORT_PAY = -1;
	public static final int NUM_SYMBOL = 6;
	
	private Random rand;
	private int numTokens;
	private int reel1;
	private int reel2;
	private int reel3;
	
	public SlotMachine(int startTokens) {
		numTokens = startTokens;
		rand = new Random();
		reel1 = 0;
		reel2 = 0;
		reel3 = 0;
	}
	
	public int spin() {
		int payout = 0;
		numTokens++;
		reel1 = rand.nextInt(NUM_SYMBOL);
		reel2 = rand.nextInt(NUM_SYMBOL);
		reel3 = rand.nextInt(NUM_SYMBOL);
		if (reel1 == reel2 && reel2 == reel3) {
			if (reel1 == 0)
				payout = 1;
			else
				payout = 2 * reel1;
		}
		if (payout > numTokens) return SHORT_PAY;
		numTokens -= payout;
		return payout;
		
	}
	
	public void addTokens(int numTokens) {
		
		this.numTokens = numTokens;
		
	}
	
	public int getTokensLeft() {
		
		return numTokens;
		
	}
	
	public int getReel1Symbol() {
		
		return reel1;
		
	}
	
	public int getReel2Symbol() {
		
		return reel2;
		
	}
	
	public int getReel3Symbol() {
		
		return reel3;
		
	}

}
