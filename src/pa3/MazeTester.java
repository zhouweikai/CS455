package pa3;

import java.util.LinkedList;

public class MazeTester {

	public static void main(String[] args) {
		boolean[][] mazeData = { { false, false, true }, { true, false, true }, { true, false, false } };
		MazeCoord startLoc = new MazeCoord(0, 0);
		MazeCoord exitLoc = new MazeCoord(2, 2);
		Maze maze = new Maze(mazeData, startLoc, exitLoc);

		System.out.println(maze.getPath());

		boolean test = maze.search();
		System.out.println(test);
		System.out.println(maze.getEntryLoc());
		System.out.println(maze.getExitLoc());

		// maze.search();

		LinkedList<MazeCoord> test3 = maze.getPath();
		System.out.println(test3);
	}

}
