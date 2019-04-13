package pa3;

import java.io.File;

//Name: Weikai Zhou
//USC NetID: weikaizh
//CS 455 PA3
//Spring 2018

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * MazeViewer class
 * 
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 * 
 * How to call it from the command line:
 * 
 * java MazeViewer mazeFile
 * 
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row, followed by the start
 * location, and ending with the exit location. Each maze location is either a
 * wall (1) or free (0). Here is an example of contents of a file for a 3x4
 * maze, with start location as the top left, and exit location as the bottom
 * right (we count locations from 0, similar to Java arrays):
 * 
 * 3 4 0111 0000 1110 0 0 2 3
 * 
 */

public class MazeViewer {

	private static final char WALL_CHAR = '1';
	private static final char FREE_CHAR = '0';

	public static void main(String[] args) {

		String fileName = "";

		try {

			if (args.length < 1) {
				System.out.println("ERROR: missing file name command line argument");
			} else {
				fileName = args[0];

				JFrame frame = readMazeFile(fileName);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.setVisible(true);
			}

		} catch (FileNotFoundException exc) {
			System.out.println("ERROR: File not found: " + fileName);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * readMazeFile reads in maze from the file whose name is given and returns a
	 * MazeFrame created from it.
	 * 
	 * @param fileName
	 *            the name of a file to read from (file format shown in class
	 *            comments, above)
	 * @param fileScanner
	 *            the scanner to scan the file
	 * @param numOfRows
	 *            the number of rows of the maze
	 * @param numOfCols
	 *            the number of columns of the maze
	 * @param mazeData
	 *            the matrix to demonstrate the maze
	 * @param eachLine
	 *            the matrix to demonstrate each line of the maze
	 *            
	 * @returns a MazeFrame containing the data from the file.
	 * 
	 * @throws FileNotFoundException
	 *             if there's no such file (subclass of IOException)
	 * @throws IOException
	 *             (hook given in case you want to do more error-checking -- that
	 *             would also involve changing main to catch other exceptions)
	 */
	private static MazeFrame readMazeFile(String fileName) throws IOException {
		File myFile = new File(fileName);
		Scanner fileScanner = new Scanner(myFile);

		int numOfRows = Integer.parseInt(fileScanner.next());
		int numOfCols = Integer.parseInt(fileScanner.next());

		fileScanner.nextLine();
		boolean[][] mazeData = new boolean[numOfRows][numOfCols];
		String[] eachLine = new String[numOfRows];

		for (int i = 0; i < numOfRows; i++) {
			eachLine[i] = fileScanner.nextLine();
			StringBuilder oneLineOfMaze = new StringBuilder(eachLine[i]);
			for (int j = 0; j < numOfCols; j++) {
				if (oneLineOfMaze.charAt(j) == WALL_CHAR) {
					mazeData[i][j] = true;
				} else {
					mazeData[i][j] = false;
				}
			}
		}

		int startLocRow = Integer.parseInt(fileScanner.next());
		int startLocCol = Integer.parseInt(fileScanner.next());

		fileScanner.nextLine();
		int exitLocRow = Integer.parseInt(fileScanner.next());
		int exitLocCol = Integer.parseInt(fileScanner.next());

		fileScanner.close();

		return new MazeFrame(mazeData, new MazeCoord(startLocRow, startLocCol), new MazeCoord(exitLocRow, exitLocCol));

	}

}