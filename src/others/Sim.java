package others;

import java.awt.Graphics;

import pa1.CoinTossSimulator;

public class Sim {
	public void paintComponent(Graphics g) {
	CoinTossSimulator res = new CoinTossSimulator ();
	String label1 = "Two Heads: " + res.getTwoHeads() + "(" + res.getTwoHeads() / res.getNumTrials() * 100 + "%)";
    System.out.println(label1);
	}

}
