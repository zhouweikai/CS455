package pa3;

//Name: Weikai Zhou
//USC NetID: weikaizh
//CS 455 PA3
//Spring 2018

import java.util.LinkedList;

/**
 * Maze class
 * 
 * Stores information about a maze and can find a path through the maze (if
 * there is one).
 * 
 * Assumptions about structure of the maze, as given in mazeData, startLoc, and
 * endLoc (parameters to constructor), and the path: -- no outer walls given in
 * mazeData -- search assumes there is a virtual border around the maze (i.e.,
 * the maze path can't go outside of the maze boundaries) -- start location for
 * a path is maze coordinate startLoc -- exit location is maze coordinate
 * exitLoc -- mazeData input is a 2D array of booleans, where true means there
 * is a wall at that location, and false means there isn't (see public FREE /
 * WALL constants below) -- in mazeData the first index indicates the row. e.g.,
 * mazeData[row][col] -- only travel in 4 compass directions (no diagonal paths)
 * -- can't travel through walls
 * 
 */

public class Maze {

	public static final boolean FREE = false;
	public static final boolean WALL = true;

	private boolean[][] mazeData;
	private MazeCoord startLoc;
	private MazeCoord exitLoc;
	// mark those visited coords
	private boolean[][] visited;
	// to keep the path
	private LinkedList<MazeCoord> coordOfPath;

	/**
	 * Constructs a maze.
	 * 
	 * @param mazeData
	 *            the maze to search. See general Maze comments above for what goes
	 *            in this array.
	 * @param startLoc
	 *            the location in maze to start the search (not necessarily on an
	 *            edge)
	 * @param exitLoc
	 *            the "exit" location of the maze (not necessarily on an edge) PRE:
	 *            0 <= startLoc.getRow() < mazeData.length and 0 <=
	 *            startLoc.getCol() < mazeData[0].length and 0 <= endLoc.getRow() <
	 *            mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length
	 * 
	 */
	public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
		this.mazeData = mazeData;
		this.startLoc = startLoc;
		this.exitLoc = exitLoc;
		coordOfPath = new LinkedList<>();
		visited = new boolean[numRows()][numCols()];
	}

	/**
	 * Returns the number of rows in the maze
	 * 
	 * @return number of rows
	 */
	public int numRows() {
		return mazeData.length;
	}

	/**
	 * Returns the number of columns in the maze
	 * 
	 * @return number of columns
	 */
	public int numCols() {
		return mazeData[0].length;
	}

	/**
	 * Returns true iff there is a wall at this location
	 * 
	 * @param loc
	 *            the location in maze coordinates
	 * @return whether there is a wall here PRE: 0 <= loc.getRow() < numRows() and 0
	 *         <= loc.getCol() < numCols()
	 */
	public boolean hasWallAt(MazeCoord loc) {
		int locRow = loc.getRow();
		int locCol = loc.getCol();
		if (mazeData[locRow][locCol] == WALL) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the entry location of this maze.
	 */
	public MazeCoord getEntryLoc() {
		return startLoc;
	}

	/**
	 * Returns the exit location of this maze.
	 */
	public MazeCoord getExitLoc() {
		return exitLoc;
	}

	/**
	 * Returns the path through the maze. First element is start location, and last
	 * element is exit location. If there was not path, or if this is called before
	 * a call to search, returns empty list.
	 * 
	 * @return the maze path
	 */
	public LinkedList<MazeCoord> getPath() {
		return coordOfPath;
	}

	/**
	 * Find a path from start location to the exit location (see Maze constructor
	 * parameters, startLoc and exitLoc) if there is one. Client can access the path
	 * found via getPath method.
	 * 
	 * @return whether a path was found.
	 */
	public boolean search() {

		if (hasWallAt(startLoc) || hasWallAt(exitLoc)) {
			return false;
		}
		coordOfPath = new LinkedList<>();
		visited = new boolean[numRows()][numCols()];
		return findPath(startLoc);

	}

	/**
	 * This is the helper method which will do recursion and return whether there is
	 * a valid path.
	 * 
	 * @param startCoord
	 *            the start coord when try to find a path.
	 * @return if there exist a path from the startLoc to the exitLoc
	 */
	private boolean findPath(MazeCoord startCoord) {

		// if out of the boundary, false
		if (startCoord.getRow() < 0 || startCoord.getRow() >= numRows() || startCoord.getCol() < 0
				|| startCoord.getCol() >= numCols()) {
			return false;
		}
		// if it is a wall, false
		if (mazeData[startCoord.getRow()][startCoord.getCol()] == WALL) {
			return false;
		}
		// if visited, false
		if (visited[startCoord.getRow()][startCoord.getCol()] == true) {
			return false;
		}
		// if found the exitLoc, true!
		if (startCoord.getRow() == exitLoc.getRow() && startCoord.getCol() == exitLoc.getCol()) {
			coordOfPath.add(exitLoc);
			return true;
		}

		visited[startCoord.getRow()][startCoord.getCol()] = true;

		if (findPath(new MazeCoord(startCoord.getRow(), startCoord.getCol() - 1))) {
			coordOfPath.add(startCoord);
			return true;
		}

		if (findPath(new MazeCoord(startCoord.getRow() + 1, startCoord.getCol()))) {
			coordOfPath.add(startCoord);
			return true;
		}

		if (findPath(new MazeCoord(startCoord.getRow(), startCoord.getCol() + 1))) {
			coordOfPath.add(startCoord);
			return true;
		}

		if (findPath(new MazeCoord(startCoord.getRow() - 1, startCoord.getCol()))) {
			coordOfPath.add(startCoord);
			return true;
		}

		return false;
	}

}