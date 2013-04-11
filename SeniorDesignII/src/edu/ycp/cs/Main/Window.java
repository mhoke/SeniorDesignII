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

import edu.ycp.cs.Centipede.Centipede;
import edu.ycp.cs.Invader.Alien;
import edu.ycp.cs.Invader.Barrier;
import edu.ycp.cs.Invader.SpaceInvaders;
import edu.ycp.cs.Tetris.Tetris;

public class Window {
	final static String SPLASHSCREEN = "Splash Screen";
	final static String MAINMENU = "Main Menu";
	final static String TETRIS = "Tetris";
	final static String CENTIPEDE = "Centipede";
	final static String SPACEINVADERS = "Space Invaders";

	final static int GRID_ROW_HEIGHT = 30;
	final static int NUM_ROWS = 20;
	final static int NUM_COLS = 10;
	final static int TIMER = 250;

	private JPanel cards; // Used for card layout
	
	private JTable tetrisTable;
	private JTable tetrisTable2;
	private JTable centTable;
	private JTable siTable;
	
	private MyTetrisTableModel tetrisModel;
	private MyTetrisTableModel2 tetrisModel2;
	private MyCentTableModel centModel;
	private MySITableModel siModel;
	
	private Tetris tetrisGame = new Tetris();
	private Centipede centGame = new Centipede();
	private SpaceInvaders siGame = new SpaceInvaders();
	
	private boolean tetrisFlag = false;
	private boolean centFlag = false;
	private boolean siFlag = false;

	public void addComponentToWindow(final Container pane) {
		// Create the "cards"
		final JPanel card1 = new JPanel();
		final JPanel card2 = new JPanel();
		final JPanel card3 = new JPanel();
		final JPanel card4 = new JPanel();
		final JPanel card5 = new JPanel();

		// Button for SplashScreen
		JButton continueButton = new JButton("CONTINUE TO MAIN MENU");
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, MAINMENU);
			}
		});

		// SPLASHSCREEN CARD setup
		SplashScreen splash = new SplashScreen();
		splash.setLayout(new FlowLayout());
		card1.add(splash);
		splash.add(continueButton);

		// Buttons for MainMenu
		JButton menuButton1 = new JButton("PLAY TETRIS");
		menuButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.add(card3, TETRIS);
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, TETRIS);
			}
		});
		JButton menuButton2 = new JButton("HIGH SCORES");
		
		// Button for Centipede
		JButton menuButton3 = new JButton("PLAY CENTIPEDE");
		menuButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				cards.add(card4, CENTIPEDE);
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, CENTIPEDE);				
			}
		});
		
		// Button for Space Invaders
		JButton menuButton4 = new JButton("PLAY SPACE INVADERS");
		menuButton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				cards.add(card5, SPACEINVADERS);
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, SPACEINVADERS);				
			}
		});

		// MAINMENU CARD setup
		MainMenuScreen mms = new MainMenuScreen();
		mms.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);
		card2.add(mms);
		mms.add(menuButton1, gbc);
		gbc.gridy++;
		mms.add(menuButton3, gbc);
		gbc.gridy++;
		mms.add(menuButton4, gbc);
		gbc.gridy++;
		mms.add(menuButton2, gbc);

		// TETRIS CARD setup
		final JButton startGameTetris = new JButton("START GAME");
		card3.setLayout(new GridBagLayout());
		final GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gbc2.insets = new Insets(2, 2, 2, 2);
		card3.add(startGameTetris, gbc2);

		// Once user presses start game...
		startGameTetris.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card3.remove(0); // Removes start game button
				
				// Handles tetris grid
				tetrisModel = new MyTetrisTableModel();
				tetrisTable = new JTable(tetrisModel);
				tetrisTable.setDefaultRenderer(Integer.class, new MyTetrisRenderer());
				tetrisTable.setRowHeight(GRID_ROW_HEIGHT);
				tetrisTable.setFocusable(false);
				tetrisTable.setRowSelectionAllowed(true);
				for (int i = 0; i < tetrisGame.getNumCols(); i++) {
					tetrisTable.getColumnModel().getColumn(i)
							.setPreferredWidth(tetrisTable.getRowHeight());
					tetrisTable.getColumnModel().getColumn(i)
							.setMaxWidth(tetrisTable.getRowHeight());
					tetrisTable.getColumnModel().getColumn(i)
							.setMinWidth(tetrisTable.getRowHeight());
				}

				// Handles next piece grid and label
				tetrisModel2 = new MyTetrisTableModel2();
				tetrisTable2 = new JTable(tetrisModel2);
				tetrisTable2.setDefaultRenderer(Integer.class, new MyTetrisRenderer2());
				tetrisTable2.setRowHeight(20);
				tetrisTable2.setFocusable(false);
				tetrisTable2.setRowSelectionAllowed(true);
				for (int i = 0; i < 5; i++) {
					tetrisTable2.getColumnModel().getColumn(i)
							.setPreferredWidth(tetrisTable2.getRowHeight());
					tetrisTable2.getColumnModel().getColumn(i)
							.setMaxWidth(tetrisTable2.getRowHeight());
					tetrisTable2.getColumnModel().getColumn(i)
							.setMinWidth(tetrisTable2.getRowHeight());
				}

				// -----------------------Put components on card 3-----------------------------
				// Add next piece information
				JLabel nextPiece = new JLabel("Next Piece");
				Container container = new Container(); // Used for label and next piece grid
				container.setLayout(new GridBagLayout());
				GridBagConstraints gbc3 = new GridBagConstraints();
				gbc3.gridx = 0;
				gbc3.gridy = 0;
				gbc3.insets = new Insets(2, 2, 2, 2);
				container.add(nextPiece, gbc3);
				gbc3.gridy++;
				container.add(tetrisTable2, gbc3);

				// Adds tetris grid
				card3.add(container, gbc2);
				gbc2.gridx++;
				card3.add(tetrisTable, gbc2);
				gbc2.gridx++;

				// Add buttons
				Container buttonContainer = new Container();
				buttonContainer.setLayout(new GridBagLayout());
				GridBagConstraints gbc4 = new GridBagConstraints();
				gbc4.gridx = 0;
				gbc4.gridy = 0;
				gbc4.insets = new Insets(2, 2, 2, 2);
				final JButton pauseButton = new JButton("Pause");
				buttonContainer.add(pauseButton, gbc4);
				gbc4.gridy++;
				JButton restartButton = new JButton("Restart");
				buttonContainer.add(restartButton, gbc4);
				gbc4.gridy++;
				JButton mainMenuButton = new JButton("Main Menu");
				buttonContainer.add(mainMenuButton, gbc4);

				card3.add(buttonContainer, gbc2);
				card3.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
				// -----------------------------------------------------------------------------

				// If pause button is pressed
				pauseButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (tetrisFlag) {
							pauseButton.setText("Pause");
							tetrisFlag = false;
						}
						tetrisGame.pause();
						card3.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
					}
				});

				// Check for user interaction with keyboard
				KeyListener tkl = new KeyListener() {
					public void keyTyped(KeyEvent e) {}
					public void keyReleased(KeyEvent e) {}
					@Override
					public void keyPressed(KeyEvent e) {
						if (!tetrisGame.getPause() && !tetrisGame.isOver()) {
							if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
								tetrisGame.move_Left();
								draw_tetris_grid();
								card3.revalidate();
							} else if (e.getKeyChar() == 'd'
									|| e.getKeyChar() == 'D') {
								tetrisGame.move_Right();
								draw_tetris_grid();
								card3.revalidate();
							} else if (e.getKeyChar() == 'q'
									|| e.getKeyChar() == 'Q') {
								tetrisGame.rotate_left();
								draw_tetris_grid();
								card3.revalidate();
							} else if (e.getKeyChar() == 'e'
									|| e.getKeyChar() == 'E') {
								tetrisGame.rotate_right();
								draw_tetris_grid();
								card3.revalidate();
							} else if (e.getKeyChar() == 's'
									|| e.getKeyChar() == 'S') {
								tetrisGame.drop_Piece();
								draw_tetris_grid();
								card3.revalidate();
							}
						}
						if (e.getKeyChar() == ' ') {
							if (tetrisFlag) {
								pauseButton.setText("Pause");
								tetrisFlag = false;
							}
							tetrisGame.pause();
							card3.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
						}
					}
				};
				card3.addKeyListener(tkl);

				draw_tetris_grid();
				card3.revalidate(); // Redraws graphics on card3

				// Actual loop for game
				final Timer timer = new Timer(TIMER, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (!tetrisGame.getPause()) {
							tetrisGame.move_Down();
							draw_tetris_grid();
							draw_next_piece_grid();
							card3.revalidate(); // Redraws graphics on card3
						}
						if (tetrisGame.isOver()) {
							System.out.println("GAME OVER PRESS RESTART!");
							((Timer) arg0.getSource()).stop();
							System.out.println("Final score is: " + tetrisGame.getScore());
						}
					}
				});
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.start();

				// If restart button is pressed
				restartButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						card3.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
						tetrisGame = new Tetris();
						draw_tetris_grid();
						draw_next_piece_grid();
						card3.revalidate(); // Redraws graphics on card3
						timer.setRepeats(true);
						timer.setCoalesce(true);
						timer.start();
						tetrisGame.pause();
						tetrisFlag = true;
						pauseButton.setText("Start");						
					}
				});
				
				// If main menu button is pressed in tetris game
				mainMenuButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tetrisFlag = true;
						cards.remove(card3);
						CardLayout cl = (CardLayout) (cards.getLayout());
						cl.show(cards, MAINMENU);				
					}
				});
			}
		});
		
		// CENTIPEDE CARD setup
		final JButton startGameCentipede = new JButton("START GAME");
		card4.setLayout(new GridBagLayout());
		final GridBagConstraints gbc5 = new GridBagConstraints();
		gbc5.gridx = 0;
		gbc5.gridy = 0;
		gbc5.insets = new Insets(2, 2, 2, 2);
		card4.add(startGameCentipede, gbc5);
		
		// Once user presses start game button...
		startGameCentipede.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card4.remove(0); // Removes start game button

				// Handles centipede grid
				centModel = new MyCentTableModel();
				centTable = new JTable(centModel);
				centTable.setDefaultRenderer(Integer.class, new MyCentRenderer());
				centTable.setRowHeight(GRID_ROW_HEIGHT);
				centTable.setFocusable(false);
				centTable.setRowSelectionAllowed(true);
				for (int i = 0; i < centGame.getNumCols(); i++) {
					centTable.getColumnModel().getColumn(i)
							.setPreferredWidth(centTable.getRowHeight());
					centTable.getColumnModel().getColumn(i)
							.setMaxWidth(centTable.getRowHeight());
					centTable.getColumnModel().getColumn(i)
							.setMinWidth(centTable.getRowHeight());
				}

				// Puts components on card 4
				card4.add(centTable, gbc5);
				gbc5.gridx++;
				
				Container centButtonContainer = new Container();
				centButtonContainer.setLayout(new GridBagLayout());
				GridBagConstraints gbc6 = new GridBagConstraints();
				gbc6.gridx = 0;
				gbc6.gridy = 0;
				gbc6.insets = new Insets(2, 2, 2, 2);
				final JButton centPauseButton = new JButton("Pause");
				centButtonContainer.add(centPauseButton, gbc6);
				gbc6.gridy++;
				JButton centRestartButton = new JButton("Restart");
				centButtonContainer.add(centRestartButton, gbc6);
				gbc6.gridy++;
				JButton centMainMenuButton = new JButton("Main Menu");
				centButtonContainer.add(centMainMenuButton, gbc6);
				card4.add(centButtonContainer, gbc5);
				card4.requestFocusInWindow();
				
				// If pause button is pressed
				centPauseButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (centFlag) {
							centPauseButton.setText("Pause");
							centFlag = false;
						}
						centGame.pause();
						card4.requestFocusInWindow();
					}
				});
				
				// Check for user interaction with keyboard
				KeyListener ckl = new KeyListener() {
					public void keyTyped(KeyEvent e) {}
					public void keyReleased(KeyEvent e) {}
					@Override
					public void keyPressed(KeyEvent e) {
						if (!centGame.getPause() && !centGame.isOver()) {
							if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
								centGame.Move('l');
								draw_centipede_grid();
								card4.revalidate();
							} else if (e.getKeyChar() == 'd'
									|| e.getKeyChar() == 'D') {
								centGame.Move('r');
								draw_centipede_grid();
								card4.revalidate();
							} else if (e.getKeyChar() == 'w'
									|| e.getKeyChar() == 'W') {
								centGame.Move('u');
								draw_centipede_grid();
								card4.revalidate();
							} else if (e.getKeyChar() == 's'
									|| e.getKeyChar() == 'S') {
								centGame.Move('d');
								draw_centipede_grid();
								card4.revalidate();
							}
						}
						if (e.getKeyChar() == ' ') {
							if (centFlag) {
								centPauseButton.setText("Pause");
								centFlag = false;
							}
							centGame.pause();
							card4.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
						}
					}
				};
				card4.addKeyListener(ckl);

				card4.requestFocusInWindow();
				draw_centipede_grid();
				card4.revalidate(); // Redraws graphics on card4
				
				// Actual loop for game
				final Timer timer = new Timer(TIMER, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (!centGame.getPause()) {
							centGame.Move(centGame.getCurrentDir());
							draw_centipede_grid();
							card4.revalidate();
						}
						if (centGame.isOver()) {
							System.out.println("GAME OVER PRESS RESTART!");
							((Timer) arg0.getSource()).stop();
							System.out.println("Final score is: " + centGame.getScore());
						}
					}
				});
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.start();
				
				// If restart button is pressed
				centRestartButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						card4.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
						centGame = new Centipede();
						centGame.Move('l');
						draw_centipede_grid();						
						card4.revalidate(); // Redraws graphics on card4
						timer.setRepeats(true);
						timer.setCoalesce(true);
						timer.start();
						centGame.pause();
						centFlag = true;
						centPauseButton.setText("Start");
					}
				});
				
				// If main menu button is pressed in centipede game
				centMainMenuButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cards.remove(card4);
						centFlag = true;
						CardLayout cl = (CardLayout) (cards.getLayout());
						cl.show(cards, MAINMENU);				
					}
				});
			}
		});
		
		// SPACE INVADERS CARD setup
		final JButton startGameSI = new JButton("START GAME");
		card5.setLayout(new GridBagLayout());
		final GridBagConstraints gbc7 = new GridBagConstraints();
		gbc7.gridx = 0;
		gbc7.gridy = 0;
		gbc7.insets = new Insets(2, 2, 2, 2);
		card5.add(startGameSI, gbc7);
		
		// Once user presses start game button...
		startGameSI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card5.remove(0); // Removes start game button

				// Handles space invaders grid
				siModel = new MySITableModel();
				siTable = new JTable(siModel);
				siTable.setDefaultRenderer(Object.class, new MySIRenderer());
				siTable.setRowHeight(GRID_ROW_HEIGHT - 6);
				siTable.setFocusable(false);
				siTable.setRowSelectionAllowed(true);
				for (int i = 0; i < siGame.getNumCols(); i++) {
					siTable.getColumnModel().getColumn(i)
							.setPreferredWidth(siTable.getRowHeight());
					siTable.getColumnModel().getColumn(i)
							.setMaxWidth(siTable.getRowHeight());
					siTable.getColumnModel().getColumn(i)
							.setMinWidth(siTable.getRowHeight());
				}

				// Puts components on card 5
				card5.add(siTable, gbc7);
				gbc7.gridx++;
				
				card5.requestFocusInWindow();
				draw_si_grid();
				card5.revalidate();
				
//				Container centButtonContainer = new Container();
//				centButtonContainer.setLayout(new GridBagLayout());
//				GridBagConstraints gbc6 = new GridBagConstraints();
//				gbc6.gridx = 0;
//				gbc6.gridy = 0;
//				gbc6.insets = new Insets(2, 2, 2, 2);
//				final JButton centPauseButton = new JButton("Pause");
//				centButtonContainer.add(centPauseButton, gbc6);
//				gbc6.gridy++;
//				JButton centRestartButton = new JButton("Restart");
//				centButtonContainer.add(centRestartButton, gbc6);
//				gbc6.gridy++;
//				JButton centMainMenuButton = new JButton("Main Menu");
//				centButtonContainer.add(centMainMenuButton, gbc6);
//				card4.add(centButtonContainer, gbc5);
//				card4.requestFocusInWindow();
//				
//				// If pause button is pressed
//				centPauseButton.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						if (centFlag) {
//							centPauseButton.setText("Pause");
//							centFlag = false;
//						}
//						centGame.pause();
//						card4.requestFocusInWindow();
//					}
//				});
//				
//				// Check for user interaction with keyboard
//				KeyListener ckl = new KeyListener() {
//					public void keyTyped(KeyEvent e) {}
//					public void keyReleased(KeyEvent e) {}
//					@Override
//					public void keyPressed(KeyEvent e) {
//						if (!centGame.getPause() && !centGame.isOver()) {
//							if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
//								centGame.Move('l');
//								draw_centipede_grid();
//								card4.revalidate();
//							} else if (e.getKeyChar() == 'd'
//									|| e.getKeyChar() == 'D') {
//								centGame.Move('r');
//								draw_centipede_grid();
//								card4.revalidate();
//							} else if (e.getKeyChar() == 'w'
//									|| e.getKeyChar() == 'W') {
//								centGame.Move('u');
//								draw_centipede_grid();
//								card4.revalidate();
//							} else if (e.getKeyChar() == 's'
//									|| e.getKeyChar() == 'S') {
//								centGame.Move('d');
//								draw_centipede_grid();
//								card4.revalidate();
//							}
//						}
//						if (e.getKeyChar() == ' ') {
//							if (centFlag) {
//								centPauseButton.setText("Pause");
//								centFlag = false;
//							}
//							centGame.pause();
//							card4.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
//						}
//					}
//				};
//				card4.addKeyListener(ckl);
//
//				card4.requestFocusInWindow();
//				draw_centipede_grid();
//				card4.revalidate(); // Redraws graphics on card4
//				
//				// Actual loop for game
//				final Timer timer = new Timer(TIMER, new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent arg0) {
//						if (!centGame.getPause()) {
//							centGame.Move(centGame.getCurrentDir());
//							draw_centipede_grid();
//							card4.revalidate();
//						}
//						if (centGame.isOver()) {
//							System.out.println("GAME OVER PRESS RESTART!");
//							((Timer) arg0.getSource()).stop();
//							System.out.println("Final score is: " + centGame.getScore());
//						}
//					}
//				});
//				timer.setRepeats(true);
//				timer.setCoalesce(true);
//				timer.start();
//				
//				// If restart button is pressed
//				centRestartButton.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						card4.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
//						centGame = new Centipede();
//						centGame.Move('l');
//						draw_centipede_grid();						
//						card4.revalidate(); // Redraws graphics on card4
//						timer.setRepeats(true);
//						timer.setCoalesce(true);
//						timer.start();
//						centGame.pause();
//						centFlag = true;
//						centPauseButton.setText("Start");
//					}
//				});
//				
//				// If main menu button is pressed in centipede game
//				centMainMenuButton.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						cards.remove(card4);
//						centFlag = true;
//						CardLayout cl = (CardLayout) (cards.getLayout());
//						cl.show(cards, MAINMENU);				
//					}
//				});
			}
		});
		
		// Sets up layout
		cards = new JPanel(new CardLayout());
		cards.add(card1, SPLASHSCREEN);
		cards.add(card2, MAINMENU);

		// Creates the actual window
		pane.add(cards, BorderLayout.CENTER);
	}
	
	public JPanel createNewTetrisCard(JPanel card) {
		card = new JPanel();
		return card;
	}

	// Sets values in the tetris grid
	public void draw_tetris_grid() {
		for (int i = 0; i < tetrisGame.getNumRows(); i++) {
			for (int j = 0; j < tetrisGame.getNumCols(); j++) {
				int[][] grid = tetrisGame.getGrid();
				tetrisModel.setValueAt(grid[j][i], i, j);
			}
		}
	}

	// Sets values in next piece grid
	public void draw_next_piece_grid() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				int[][] npgrid = tetrisGame.getNextPieceGrid();
				tetrisModel2.setValueAt(npgrid[j][i], i, j);
			}
		}
	}
	
	// Sets values in centipede grid
	public void draw_centipede_grid() {
		for (int i = 0; i < centGame.getNumRows(); i++) {
			for (int j = 0; j < centGame.getNumCols(); j++) {
				int[][] grid = centGame.getGrid();
				centModel.setValueAt(grid[j][i], i, j);
			}
		}
	}
	
	// Sets values in space invaders grid
	public void draw_si_grid() {
		for (int i = 0; i < siGame.getNumRows(); i++) {
			for (int j = 0; j < siGame.getNumCols(); j++) {
				Object[][] grid = siGame.getGrid();
				siModel.setValueAt(grid[j][i], i, j);
			}
		}
	}

	// Gets a color from a specific cell
	public Color getTableCellBackground(JTable table, int row, int col) {
		TableCellRenderer renderer = table.getCellRenderer(row, col);
		Component component = table.prepareRenderer(renderer, row, col);
		return component.getBackground();
	}

	// Render each cell as a background color dependent on grid for tetris game
	@SuppressWarnings("serial")
	class MyTetrisRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			c.setBackground(getColor((Integer) value));
			c.setForeground(getColor((Integer) value));
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
				switch (tetrisGame.getCur_color().getKey()) {
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

	// Overwrite the Table Model to be what I want color-wise for tetris grid
	@SuppressWarnings("serial")
	class MyTetrisTableModel extends AbstractTableModel {
		private int[][] values = new int[tetrisGame.getNumCols()][tetrisGame.getNumRows()];

		public int getColumnCount() {
			return tetrisGame.getNumCols();
		}

		public int getRowCount() {
			return tetrisGame.getNumRows();
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

	// Render each cell as a background color dependent on grid from next piece grid
	@SuppressWarnings("serial")
	class MyTetrisRenderer2 extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			c.setBackground(getColor((Integer) value));
			c.setForeground(getColor((Integer) value));
			return c;
		}

		private Color getColor(Integer value) {
			switch (value) {
			case 8:
				switch (tetrisGame.getNext_color().getKey()) {
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

	// Next piece grid model
	@SuppressWarnings("serial")
	class MyTetrisTableModel2 extends AbstractTableModel {
		private int[][] values = new int[5][6];

		public int getColumnCount() {
			return 5;
		}

		public int getRowCount() {
			return 6;
		}

		public Object getValueAt(int row, int col) {
			return values[col][row];
		}

		public void setValueAt(Object val, int row, int col) {
			values[col][5 - row] = (Integer) val;
			fireTableCellUpdated(row, col);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return Integer.class;
		}
	}
	
	// Render each cell as a background color dependent on grid for centipede game
	@SuppressWarnings("serial")
	class MyCentRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			c.setBackground(getColor((Integer) value));
			c.setForeground(getColor((Integer) value));
			return c;
		}

		private Color getColor(Integer value) {
			switch (value) {
			case 1:
				return Color.GREEN;
			case 2:
				return Color.RED;
			}
			return Color.DARK_GRAY;
		}
	}

	// Overwrite the Table Model to be what I want color-wise for centipede grid
	@SuppressWarnings("serial")
	class MyCentTableModel extends AbstractTableModel {
		private int[][] values = new int[centGame.getNumCols()][centGame.getNumRows()];

		public int getColumnCount() {
			return centGame.getNumCols();
		}

		public int getRowCount() {
			return centGame.getNumRows();
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
	
	// Render each cell as a background color dependent on grid for space invaders game
	@SuppressWarnings("serial")
	class MySIRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			c.setBackground(getColor((Object) value));
			return c;
		}

		private Color getColor(Object value) {
			// get grid object (alien, barrier, or character) and use certain color for each
			if (value.getClass().equals(Barrier.class)) {
				return Color.BLACK;
			}
			else if (value.getClass().equals(Alien.class)) {
				return Color.CYAN;
			}
			else if (value.getClass().equals(edu.ycp.cs.Invader.Character.class)) {
				return Color.GREEN;
			}
			else {
				return Color.DARK_GRAY;
			}
		}
	}

	// Overwrite the Table Model to be what I want color-wise for space invaders grid
	@SuppressWarnings("serial")
	class MySITableModel extends AbstractTableModel {
		private Object[][] values = new Object[siGame.getNumCols()][siGame.getNumRows()];

		public int getColumnCount() {
			return siGame.getNumCols();
		}

		public int getRowCount() {
			return siGame.getNumRows();
		}

		public Object getValueAt(int row, int col) {
			return values[col][row];
		}

		public void setValueAt(Object val, int row, int col) {
			values[col][24 - row] = val;
			fireTableCellUpdated(row, col);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return Object.class;
		}
	}
}
