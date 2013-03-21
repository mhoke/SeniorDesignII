package edu.ycp.cs.Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import edu.ycp.cs.Tetris.Game;
import edu.ycp.cs.Tetris.Tetris;

public class Window {
	JPanel cards;
	final static String SPLASHSCREEN = "SplashScreen";
	final static String MAINMENU = "MainMenu";
	final static String TETRIS = "Tetris";

	public void addComponentToWindow(Container pane) {
		// Create the "cards".
		final JPanel card1 = new JPanel();
		final JPanel card2 = new JPanel();
		final JPanel card3 = new JPanel();

		// Button for SplashScreen
		JButton continueButton = new JButton("CONTINUE TO MAIN MENU");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, MAINMENU);
			}
		});

		// SplashScreen setup
		SplashScreen splash = new SplashScreen();
		splash.setLayout(new FlowLayout());
		card1.add(splash);
		splash.add(continueButton);

		// Buttons for MainMenu
		JButton menuButton1 = new JButton("PLAY TETRIS");
		menuButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, TETRIS);
			}
		});
		JButton menuButton2 = new JButton("HIGH SCORES");

		// MainMenu setup
		MainMenuScreen mms = new MainMenuScreen();
		mms.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);
		card2.add(mms);
		mms.add(menuButton1, gbc);
		gbc.gridy = 1;
		mms.add(menuButton2, gbc);

		// Tetris setup
		final JButton startGame = new JButton("START GAME");
		card3.setLayout(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);
		card3.add(startGame, gbc2);
		gbc.gridy = 1;
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tetris game = new Tetris(); 
				JTable table = new JTable(game.getNumRows(), game.getNumCols());
				table.setRowHeight(30);
				for (int i = 0; i < game.getNumCols(); i++) {
					table.getColumnModel().getColumn(i).setPreferredWidth(table.getRowHeight());
					
				}
				card3.add(table);
				card3.remove(0); //Removes button card3.revalidate();
				card3.revalidate(); //Redraws graphics
				
//				card3.remove(0); //remove start button
//				
//				Game game = new Game();
//				int[][] oldGrid = null;
//				int[][] newGrid = null;
//				boolean firstTime = true;
//				
//				JButton[][] grid; // tetris grid of buttons
//				card3.setLayout(new GridLayout(20, 10));
//				grid = new JButton[20][10];
//				for (int i = 0; i < 20; i++) {
//					for (int j = 0; j < 10; j++) {
//						grid[i][j] = new JButton();
//						card3.add(grid[i][j]);
//					}
//				}
//				card3.revalidate();

//				while (true) {
//					if (firstTime) {
//						newGrid = game.gamePlay(null);
//					} else {
//						newGrid = game.gamePlay(oldGrid);
//					}
//
//					oldGrid = newGrid;
//					firstTime = false;
//				}
			}
		});

		// Sets up layout
		cards = new JPanel(new CardLayout());
		cards.add(card1, SPLASHSCREEN);
		cards.add(card2, MAINMENU);
		cards.add(card3, TETRIS);

		// Creates the actual window
		pane.add(cards, BorderLayout.CENTER);
	}
}

//STUFF I MAY NEED FOR GAME CLASS
//public int[][] gamePlay(int[][] grid) {
//if (grid == null) {
//	game = new Tetris();
//	System.out.println("first time");
//}
//else {
//	game.setGrid(grid);
//}
//try {
//	Thread.sleep(1000);
//} catch (InterruptedException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//game.move_Down();
//game.print_Game();
//
//return game.getGrid();
//}
