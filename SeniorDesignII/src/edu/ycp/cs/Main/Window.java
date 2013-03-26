package edu.ycp.cs.Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import edu.ycp.cs.Tetris.Tetris;

public class Window {
	final static String SPLASHSCREEN = "SplashScreen";
	final static String MAINMENU = "MainMenu";
	final static String TETRIS = "Tetris";
	
	final static int GRID_ROW_HEIGHT = 30;
	final static int NUM_ROWS = 20;
	final static int NUM_COLS = 10;
	final static int TIMER = 250;
	
	private JPanel cards; //Used for card layout
	private JTable table;
	private JTable table2;
	private MyTableModel model;
	private MyTableModel2 model2;
	private Tetris game = new Tetris();	

	public void addComponentToWindow(final Container pane) {
		//Create the "cards"
		final JPanel card1 = new JPanel();
		final JPanel card2 = new JPanel();
		final JPanel card3 = new JPanel();

		//Button for SplashScreen
		JButton continueButton = new JButton("CONTINUE TO MAIN MENU");
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
		gbc.insets = new Insets(2, 2, 2, 2);
		card2.add(mms);
		mms.add(menuButton1, gbc);
		gbc.gridy++;
		mms.add(menuButton2, gbc);

		//Tetris setup
		final JButton startGame = new JButton("START GAME");
		card3.setLayout(new GridBagLayout());
		final GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gbc2.insets = new Insets(2, 2, 2, 2);
		card3.add(startGame, gbc2);

		//Once user presses start game...
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card3.remove(0); // Removes start game button
				
				//Handles tetris grid
				model = new MyTableModel();				
				table = new JTable(model);
				table.setDefaultRenderer(Integer.class, new MyRenderer());
				table.setRowHeight(GRID_ROW_HEIGHT);
				table.setFocusable(false);
				table.setRowSelectionAllowed(true);
				for (int i = 0; i < NUM_COLS; i++) {
					table.getColumnModel().getColumn(i)
							.setPreferredWidth(table.getRowHeight());
					table.getColumnModel().getColumn(i)
							.setMaxWidth(table.getRowHeight());
					table.getColumnModel().getColumn(i)
							.setMinWidth(table.getRowHeight());
				}
				
				//Handles next piece grid and label
				model2 = new MyTableModel2();
				table2 = new JTable(model2);
				table2.setDefaultRenderer(Integer.class, new MyRenderer());
				table2.setRowHeight(20);
				table2.setFocusable(false);
				table2.setRowSelectionAllowed(true);
				for (int i = 0; i < 5; i++) {
					table2.getColumnModel().getColumn(i)
							.setPreferredWidth(table2.getRowHeight());
					table2.getColumnModel().getColumn(i)
							.setMaxWidth(table2.getRowHeight());
					table2.getColumnModel().getColumn(i)
							.setMinWidth(table2.getRowHeight());
				}
				JLabel nextPiece = new JLabel("Next Piece");
				
				//-----------------------Put components on card 3 -----------------------------
				//Add next piece information
				Container container = new Container(); //Used for label and next piece grid
				container.setLayout(new GridBagLayout());
				GridBagConstraints gbc3 = new GridBagConstraints();
				gbc3.gridx = 0;
				gbc3.gridy = 0;
				gbc3.insets = new Insets(2, 2, 2, 2);
				container.add(nextPiece, gbc3);
				gbc3.gridy++;
				container.add(table2, gbc3);
				
				//Adds tetris grid
				card3.add(container, gbc2);				
				gbc2.gridx++;
				card3.add(table, gbc2);
				gbc2.gridx++;
				
				//Add buttons
				Container buttonContainer = new Container();
				buttonContainer.setLayout(new GridBagLayout());
				GridBagConstraints gbc4 = new GridBagConstraints();
				gbc4.gridx = 0;
				gbc4.gridy = 0;
				gbc4.insets = new Insets(2, 2, 2, 2);				
				JButton pauseButton = new JButton("Pause");
				buttonContainer.add(pauseButton, gbc4);
				gbc4.gridy++;				
				JButton restartButton = new JButton("Restart");				
				buttonContainer.add(restartButton, gbc4);
				
				card3.add(buttonContainer, gbc2);				
				card3.requestFocusInWindow(); //Needed to reset focus for keyboard interaction
				//-----------------------------------------------------------------------------

				//If pause button is pressed
				pauseButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						game.pause();
						card3.requestFocusInWindow(); //Needed to reset focus for keyboard interaction
					}
				});
				
				
				
				//Check for user interaction with keyboard
				KeyListener kl = new KeyListener() {
					public void keyTyped(KeyEvent e) {}
					public void keyReleased(KeyEvent e) {}
					@Override
					public void keyPressed(KeyEvent e) {
						if (!game.getPause()) {
							if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
								game.move_Left();
								draw_grid();
								card3.revalidate();
							} else if (e.getKeyChar() == 'd'
									|| e.getKeyChar() == 'D') {
								game.move_Right();
								draw_grid();
								card3.revalidate();
							} else if (e.getKeyChar() == 'q'
									|| e.getKeyChar() == 'Q') {
								game.rotate_left();
								draw_grid();
								card3.revalidate();
							} else if (e.getKeyChar() == 'e'
									|| e.getKeyChar() == 'E') {
								game.rotate_right();
								draw_grid();
								card3.revalidate();
							}
						}
						if (e.getKeyChar() == ' ') {
							game.pause();
							card3.requestFocusInWindow(); //Needed to reset focus for keyboard interaction
						}
					}
				};
				card3.addKeyListener(kl);
				
				draw_grid();
				card3.revalidate(); //Redraws graphics on card3

				//Actual loop for game
				final Timer timer = new Timer(TIMER, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (!game.getPause()) {
							game.move_Down();
							draw_grid();
							card3.revalidate(); //Redraws graphics on card3
						}
						if (game.isOver()) {
							System.out.println("gfhj");
							((Timer) arg0.getSource()).stop();
						}
					}
				});								
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.start();
				
				//If restart button is pressed
				restartButton.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						card3.requestFocusInWindow(); //Needed to reset focus for keyboard interaction						
						game = new Tetris();
						draw_grid();
						card3.revalidate(); //Redraws graphics on card3
						timer.setRepeats(true);
						timer.setCoalesce(true);
						timer.start();
						game.pause();
					}
				});
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

	//Sets values in the grid
	public void draw_grid() {
		for (int i = 0; i < game.getNumRows(); i++) {
			for (int j = 0; j < game.getNumCols(); j++) {
				int[][] grid = game.getGrid();
				model.setValueAt(grid[j][i], i, j);
			}
		}
	}

	//Gets a color from a specific cell
	public Color getTableCellBackground(JTable table, int row, int col) {
		TableCellRenderer renderer = table.getCellRenderer(row, col);
		Component component = table.prepareRenderer(renderer, row, col);
		return component.getBackground();
	}

	//Render each cell as a background color dependent on grid from tetris game
	@SuppressWarnings("serial")
	class MyRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			c.setBackground(getColor((Integer) value));
			return c;
		}

		private Color getColor(Integer value) {
			switch (value) {
			case 1:
				return Color.RED;
			case 2:
				return Color.GREEN;
			case 3:
				return Color.BLUE;
			case 4:
				return Color.YELLOW;
			case 5:
				return Color.CYAN;
			case 6:
				return Color.BLACK;
			case 7:
				return Color.MAGENTA;
			case 8:
				switch (game.getCur_color().getKey()) {
				case 1:
					return Color.RED;
				case 2:
					return Color.GREEN;
				case 3:
					return Color.BLUE;
				case 4:
					return Color.YELLOW;
				case 5:
					return Color.CYAN;
				case 6:
					return Color.BLACK;
				case 7:
					return Color.MAGENTA;
				}
			}
			return Color.DARK_GRAY;
		}
	}

	//Overwrite the Table Model to be what I want color-wise
	@SuppressWarnings("serial")
	class MyTableModel extends AbstractTableModel {
		private int[][] values = new int[NUM_COLS][NUM_ROWS];

		public int getColumnCount() {
			return NUM_COLS;
		}

		public int getRowCount() {
			return NUM_ROWS;
		}

		public Object getValueAt(int row, int col) {
			return values[col][row];
		}

		public void setValueAt(Object val, int row, int col) {
			values[col][19 - row] = (Integer) val;
			fireTableCellUpdated(row, col);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return Integer.class;
		}
	}
	
	@SuppressWarnings("serial")
	class MyTableModel2 extends AbstractTableModel {
		private int[][] values = new int[5][5];

		public int getColumnCount() {
			return 5;
		}

		public int getRowCount() {
			return 5;
		}

		public Object getValueAt(int row, int col) {
			return values[col][row];
		}

		public void setValueAt(Object val, int row, int col) {
			values[col][4 - row] = (Integer) val;
			fireTableCellUpdated(row, col);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return Integer.class;
		}
	}
}
