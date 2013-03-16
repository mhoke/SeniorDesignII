package edu.ycp.cs.Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ycp.cs.Tetris.Game;

public class Window {
	JPanel cards;
	final static String SPLASHSCREEN = "SplashScreen";
	final static String MAINMENU = "MainMenu";
	final static String TETRIS = "Tetris";

	public void addComponentToWindow(Container pane) {
		JPanel gameWindow = new JPanel(); // uses FlowLayout by default

		// Create the "cards".
		JPanel card1 = new JPanel();
		//JPanel card2 = new MainMenuScreen();
		JPanel card2 = new JPanel();
		JPanel card3 = new JPanel();
		
		//Button for SplashScreen
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, MAINMENU);
			}
		});
		
		//SplashScreen setup
		SplashScreen splash = new SplashScreen();
		splash.setLayout(new FlowLayout());
		card1.add(splash);
		splash.add(continueButton);
		
		//Buttons for MainMenu
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
		
		//MainMenu setup
		MainMenuScreen mms = new MainMenuScreen();
		mms.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2,2,2,2);
		card2.add(mms);
		mms.add(menuButton1, gbc);
		gbc.gridy = 1;
		mms.add(menuButton2, gbc);
		
		//Tetris setup
		JLabel label = new JLabel("TETRIS GAME WILL GO HERE");
		JButton startGame = new JButton("START GAME");
		card3.setLayout(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2,2,2,2);		
		card3.add(label, gbc2);
		gbc2.gridy = 1;
		card3.add(startGame, gbc2);
		startGame.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Game.main(null);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

		//Sets up layout
		cards = new JPanel(new CardLayout());
		cards.add(card1, SPLASHSCREEN);
		cards.add(card2, MAINMENU);
		cards.add(card3, TETRIS);

		//Creates the actual window
		pane.add(cards, BorderLayout.CENTER);
	}
}
