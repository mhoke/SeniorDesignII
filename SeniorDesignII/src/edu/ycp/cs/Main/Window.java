package edu.ycp.cs.Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Window implements ItemListener{
	private static final long serialVersionUID = 1L;
	JPanel cards; // a panel that uses CardLayout
	final static String SPLASHSCREEN = "SplashScreen";
	final static String MAINMENU = "MainMenu";

	public void addComponentToWindow(Container pane) {
		// Put the JComboBox in a JPanel to get a nicer look.
		JPanel gameWindow = new JPanel(); // use FlowLayout
		
		// Create the "cards".
		JPanel card1 = new JPanel();
		JButton continueButton = new JButton("Continue");
		continueButton.addItemListener(this);
		card1.add(new SplashScreen());
		card1.add(continueButton);
		
		JPanel card2 = new JPanel();
		card2.add(new MainMenuScreen());
		
		cards = new JPanel(new CardLayout());
		cards.add(card1, SPLASHSCREEN);
		cards.add(card2, MAINMENU);
		
		pane.add(gameWindow, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);
	}
	
	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, (String) evt.getItem());
	}
}
