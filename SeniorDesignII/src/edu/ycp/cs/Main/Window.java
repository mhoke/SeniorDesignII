package edu.ycp.cs.Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Window {
	JPanel cards; // a panel that uses CardLayout
	final static String SPLASHSCREEN = "SplashScreen";
	final static String MAINMENU = "MainMenu";

	public void addComponentToWindow(Container pane) {
		// Put the JComboBox in a JPanel to get a nicer look.
		JPanel gameWindow = new JPanel(); // use FlowLayout

		// Create the "cards".
		JPanel card1 = new JPanel();
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, MAINMENU);
			}
		});
		card1.add(new SplashScreen());
		card1.add(continueButton);

		JPanel card2 = new JPanel();
		JButton menuButton1 = new JButton("PLAY!");
		JButton menuButton2 = new JButton("HIGH SCORES");
		card2.add(new MainMenuScreen());
		card2.add(menuButton1);
		card2.add(menuButton2);

		cards = new JPanel(new CardLayout());
		cards.add(card1, SPLASHSCREEN);
		cards.add(card2, MAINMENU);

		pane.add(gameWindow, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);
	}
}
