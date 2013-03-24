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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import edu.ycp.cs.Tetris.Tetris;

public class Window {
	private JPanel cards;
	final static String SPLASHSCREEN = "SplashScreen";
	final static String MAINMENU = "MainMenu";
	final static String TETRIS = "Tetris";
	final static int GRID_ROW_HEIGHT = 30;
	final static int NUM_ROWS = 20;
	final static int NUM_COLS = 10;
	private JTable table;
	private Tetris game = new Tetris();
	private MyTableModel model;

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
				card3.remove(0); // Removes button
				model = new MyTableModel();
				table = new JTable(model);
				table.setDefaultRenderer(int.class, new MyRenderer());
				table.setRowHeight(GRID_ROW_HEIGHT);
				table.setFocusable(false);
				table.setRowSelectionAllowed(true);
				for (int i = 0; i < NUM_COLS; i++) {
					table.getColumnModel().getColumn(i)
							.setPreferredWidth(table.getRowHeight());
				}
				card3.add(table);

				card3.setFocusable(true);
				card3.requestFocusInWindow();

				KeyListener kl = new KeyListener() {
					public void keyTyped(KeyEvent e) {
					}

					public void keyReleased(KeyEvent e) {
					}

					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
							System.out.println("pressed a");
							game.move_Left();
							draw_grid_first_time();
							card3.revalidate();
						} else if (e.getKeyChar() == 'd'
								|| e.getKeyChar() == 'D') {
							game.move_Right();
							draw_grid_first_time();
							card3.revalidate();
						} else if (e.getKeyChar() == 'q'
								|| e.getKeyChar() == 'Q') {
							game.rotate_left();
							draw_grid_first_time();
							card3.revalidate();
						} else if (e.getKeyChar() == 'e'
								|| e.getKeyChar() == 'E') {
							game.rotate_right();
							draw_grid_first_time();
							card3.revalidate();
						} else if (e.getKeyChar() == ' ') {
							game.pause();
						}
					}
				};
				card3.addKeyListener(kl);

				draw_grid_first_time();
				card3.revalidate(); // Redraws graphics

				Timer timer = new Timer(500, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (!game.getPause()) {
							game.move_Down();
							draw_grid();
							card3.revalidate(); // Redraws graphics
						}						
					}

					public void draw_grid() {
						for (int i = 0; i < game.getNumRows(); i++) {
							for (int j = 0; j < game.getNumCols(); j++) {
								int[][] grid = game.getGrid();
								model.setValueAt(grid[j][i], i, j);
							}
						}
					}
				});
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.start();
				if (game.isOver()) {
					timer.stop();
				}
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

	public void draw_grid_first_time() {
		for (int i = 0; i < game.getNumRows(); i++) {
			for (int j = 0; j < game.getNumCols(); j++) {
				int[][] grid = game.getGrid();
				model.setValueAt(grid[j][i], i, j);
			}
		}
	}

	public Color getTableCellBackground(JTable table, int row, int col) {
		TableCellRenderer renderer = table.getCellRenderer(row, col);
		Component component = table.prepareRenderer(renderer, row, col);
		return component.getBackground();
	}

	// Render each cell as a background color dependent on grid from tetris game
	class MyRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			JTextField editor = new JTextField();
			if (value != null) {
				editor.setText(value.toString());
			}
			if ((Integer) table.getValueAt(row, column) == 0) {
				editor.setBackground(Color.DARK_GRAY);
			} else if ((Integer) table.getValueAt(row, column) == 1) {
				editor.setBackground(Color.RED);
			} else if ((Integer) table.getValueAt(row, column) == 2) {
				editor.setBackground(Color.GREEN);
			} else if ((Integer) table.getValueAt(row, column) == 3) {
				editor.setBackground(Color.BLUE);
			} else if ((Integer) table.getValueAt(row, column) == 4) {
				editor.setBackground(Color.YELLOW);
			}
			return editor;
		}
	}

	// Overwrite the Table Model to be what I want color wise
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
	}
}
