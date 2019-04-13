package pa1;

//Name: Weikai Zhou
//USC NetID: 7988402237
//CS 455 PA1
//Spring 2018

import java.util.Scanner;

import javax.swing.JFrame;

public class CoinSimViewer {

	public static void main(String[] args) {

		System.out.println("Enter number of trials: ");

		Scanner in = new Scanner(System.in);
		int numTrials = in.nextInt();

		// error checking: if the input is less than 1, throw the warning to the user and 
		// automatically require another input
		while (numTrials < 1) {
			System.out.println("ERROR: Number entered must be greater than 0.");
			System.out.println("Enter number of trials: ");
			numTrials = in.nextInt();
		}

		JFrame frame = new JFrame();

		// set the size and title of the frame
		frame.setSize(800, 500);
		frame.setTitle("CoinSim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CoinSimComponent comp = new CoinSimComponent(numTrials);
		frame.add(comp);// add the comp to the frame so it can be exhibited

		frame.setVisible(true);

	}

}
