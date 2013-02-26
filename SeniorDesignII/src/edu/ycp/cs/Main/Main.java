package edu.ycp.cs.Main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Score score = new Score();

		score.getScores("Tetris");

		score.setCurrent_Score(115);
		score.setCurrentName("TST");

		score.checkScore();

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				makeGUI();
			}
		});
	}

	private static void makeGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("M&M Arcade");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		Window window = new Window();
		window.addComponentToWindow(frame.getContentPane());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
