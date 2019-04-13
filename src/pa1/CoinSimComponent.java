package pa1;

//Name: Weikai Zhou
//USC NetID: 7988402237
//CS 455 PA1
//Spring 2018

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import pa1.CoinTossSimulator;

public class CoinSimComponent extends JComponent {

	// VB is the vertical-buffer, and WIDTH_OF_BAR is the width of a bar
	private int numTrials;
	int VB = 22;
	int WIDTH_OF_BAR = 49;
	CoinTossSimulator res = new CoinTossSimulator();

	// constructor. Catch the input of numTrials and pass it to new CoinTossSimulator
	public CoinSimComponent(int numTrials) {
		this.numTrials = numTrials;
		res.run(numTrials);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int height = this.getHeight();
		int width = this.getWidth();
		int bottom = height - VB;
		int left = (width - WIDTH_OF_BAR * 3) / 4;
		int widthBar = WIDTH_OF_BAR;

		Font font = g2.getFont();
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D labelBounds = font.getStringBounds("label", context);
		int heightOfLabel = (int) labelBounds.getHeight();

		double scale = (double) (height - VB * 2 - heightOfLabel) / res.getNumTrials();

		// explain string of the label
		String label1 = "Two Heads: " + res.getTwoHeads() + "("
				+ (int) Math.round((double) res.getTwoHeads() / res.getNumTrials() * 100) + "%)";
		String label2 = "A Heaad and a tail: " + res.getHeadTails() + "("
				+ (int) Math.round((double) res.getHeadTails() / res.getNumTrials() * 100) + "%)";
		String label3 = "Two Tails: " + res.getTwoTails() + "("
				+ (int) Math.round((double) res.getTwoTails() / res.getNumTrials() * 100) + "%)";

		// create the three bars
		Bar red = new Bar(bottom, left, widthBar, res.getTwoHeads(), scale, Color.RED, label1);
		Bar green = new Bar(bottom, left * 2 + widthBar, widthBar, res.getHeadTails(), scale, Color.GREEN, label2);
		Bar blue = new Bar(bottom, left * 3 + widthBar * 2, widthBar, res.getTwoTails(), scale, Color.BLUE, label3);

		// draw the bar one by one
		red.draw(g2);
		green.draw(g2);
		blue.draw(g2);

	}
}
