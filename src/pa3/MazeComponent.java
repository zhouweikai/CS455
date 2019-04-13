package pa3;

import java.awt.Color;

//Name: Weikai Zhou
//USC NetID: weikaizh
//CS 455 PA3
//Spring 2018

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JComponent;

/**
 * MazeComponent class
 * 
 * A component that displays the maze and path through it if one has been found.
 */
public class MazeComponent extends JComponent {

	private static final int START_X = 10; // top left of corner of maze in frame
	private static final int START_Y = 10;
	private static final int BOX_WIDTH = 20; // width and height of one maze "location"
	private static final int BOX_HEIGHT = 20;
	private static final int INSET = 2;
	// how much smaller on each side to make entry/exit inner box

	private Maze maze;
	private Color colorOfBorder = Color.black;
	private Color colorOfWall = Color.black;
	private Color colorOfStartLoc = Color.yellow;
	private Color colorOfExitLoc = Color.green;
	private Color colorOfPath = Color.blue;
	private int widthOfBox = BOX_WIDTH;
	private int heightOfBox = BOX_HEIGHT;

	/**
	 * Constructs the component.
	 * 
	 * @param maze
	 *            the maze to display
	 */
	public MazeComponent(Maze maze) {
		this.maze = maze;
	}

	/**
	 * Draws the current state of maze including the path through it if one has been
	 * found.
	 * 
	 * @param g
	 *            the graphics context
	 * @param g2
	 *            the graphics context
	 * @param border
	 *            the border of the maze
	 * @param wall
	 *            the rectangle which refers a wall
	 * @param startLoc
	 *            the start location box
	 * @param exitLoc
	 *            the exit location box
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(colorOfBorder);
		Rectangle border = new Rectangle(START_X, START_Y, maze.numCols() * widthOfBox, maze.numRows() * heightOfBox);
		g2.draw(border);

		for (int i = 0; i < maze.numRows(); i++) {
			for (int j = 0; j < maze.numCols(); j++) {
				if (maze.hasWallAt(new MazeCoord(i, j))) {
					g2.setColor(colorOfWall);
					Rectangle wall = new Rectangle(START_X + j * widthOfBox, START_Y + i * heightOfBox, widthOfBox,
							heightOfBox);
					g2.fill(wall);
				}

			}
		}

		g2.setColor(colorOfStartLoc);
		int startLoc_X = START_X + maze.getEntryLoc().getCol() * widthOfBox + INSET;
		int startLoc_Y = START_Y + maze.getEntryLoc().getRow() * heightOfBox + INSET;
		Rectangle startLoc = new Rectangle(startLoc_X, startLoc_Y, widthOfBox - 2 * INSET, heightOfBox - 2 * INSET);
		g2.fill(startLoc);

		g2.setColor(colorOfExitLoc);
		int exitLoc_X = START_X + maze.getExitLoc().getCol() * widthOfBox + INSET;
		int exitLoc_Y = START_Y + maze.getExitLoc().getRow() * heightOfBox + INSET;
		Rectangle exitLoc = new Rectangle(exitLoc_X, exitLoc_Y, widthOfBox - 2 * INSET, heightOfBox - 2 * INSET);
		g2.fill(exitLoc);

		drawPath(g2);

	}

	/**
	 * The helper function to draw the path in the maze
	 * 
	 * @param g2
	 *            the graphics context
	 */
	private void drawPath(Graphics g2) {
		g2.setColor(colorOfPath);
		LinkedList<MazeCoord> coordOfPath = maze.getPath();
		int size = coordOfPath.size();
		if (size > 1) {
			for (int i = 0; i < size - 1; i++) {
				g2.drawLine(START_X + coordOfPath.get(i).getCol() * widthOfBox + widthOfBox / 2,
						START_Y + coordOfPath.get(i).getRow() * heightOfBox + heightOfBox / 2,
						START_X + coordOfPath.get(i + 1).getCol() * widthOfBox + widthOfBox / 2,
						START_Y + coordOfPath.get(i + 1).getRow() * heightOfBox + heightOfBox / 2);
			}
		}
	}

}
