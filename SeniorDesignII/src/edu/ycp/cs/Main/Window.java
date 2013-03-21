package edu.ycp.cs.Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import edu.ycp.cs.Tetris.Tetris;

public class Window {
	JPanel cards;
	final static String SPLASHSCREEN = "SplashScreen";
	final static String MAINMENU = "MainMenu";
	final static String TETRIS = "Tetris";
	final static int GRID_ROW_HEIGHT = 30;
	final static int NUM_ROWS = 20;
	final static int NUM_COLS = 10;
	JTable table = new JTable(new MyTableModel());
	Tetris game = new Tetris(); 

	public void addComponentToWindow(Container pane) {
		// Create the "cards"
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
				table.setDefaultRenderer(Object.class, new MyRenderer());
				table.setRowHeight(GRID_ROW_HEIGHT);
				table.setFocusable(false);
				table.setRowSelectionAllowed(false);
				for (int i = 0; i < game.getNumCols(); i++) {
					table.getColumnModel().getColumn(i).setPreferredWidth(table.getRowHeight());					
				}				
				card3.add(table);
				card3.remove(0); //Removes button
				card3.revalidate(); //Redraws graphics
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
	
	public Color getTableCellBackground(JTable table, int row, int col) {
        TableCellRenderer renderer = table.getCellRenderer(row, col);
        Component component = table.prepareRenderer(renderer, row, col);    
        return component.getBackground();
    }

	//Render each cell as a background color dependent on grid from tetris game
    class MyRenderer implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JTextField editor = new JTextField();
            if (value != null) {
                editor.setText(value.toString());
            }
            if (game.getCur_color().getKey() == 0) {
            	editor.setBackground(Color.WHITE);
            }
            else if (game.getCur_color().getKey() == 1) {
            	editor.setBackground(Color.RED);
            }
            else if (game.getCur_color().getKey() == 2) {
            	editor.setBackground(Color.GREEN);
            }
            else if (game.getCur_color().getKey() == 3) {
            	editor.setBackground(Color.BLUE);
            }
            else if (game.getCur_color().getKey() == 4) {
            	editor.setBackground(Color.YELLOW);
            }
            return editor;
        }
    }
    
    //Overwrite the Table Model to be what I want color wise
    @SuppressWarnings("serial")
	class MyTableModel extends AbstractTableModel {
        public int getColumnCount() {
            return NUM_COLS;
        }
        public int getRowCount() {
            return NUM_ROWS;
        }        
        public Object getValueAt(int row, int col) {
            return null;
        }
    }
}
