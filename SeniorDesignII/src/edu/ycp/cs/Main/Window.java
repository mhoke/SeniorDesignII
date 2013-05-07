package edu.ycp.cs.Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import edu.ycp.cs.Centipede.Centipede;
import edu.ycp.cs.Invader.Alien;
import edu.ycp.cs.Invader.Barrier;
import edu.ycp.cs.Invader.Laser;
import edu.ycp.cs.Invader.SpaceInvaders;
import edu.ycp.cs.Tetris.Tetris;

public class Window {
	final static String SPLASHSCREEN = "Splash Screen";
	final static String MAINMENU = "Main Menu";
	final static String TETRIS = "Tetris";
	final static String CENTIPEDE = "Centipede";
	final static String SPACEINVADERS = "Space Invaders";
	final static String HIGHSCORES = "High Scores";

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
	
	private boolean tetrisFlag = true;
	private boolean centFlag = true;
	private boolean siFlag = true;
	private boolean ArcadeControls;
	
	// Create the "cards"
	JPanel card1 = new JPanel();
	JPanel card2 = new JPanel();
	JPanel card3 = new JPanel();
	JPanel card4 = new JPanel();
	JPanel card5 = new JPanel();
	JPanel card6 = new JPanel();

	public void addComponentToWindow(final Container pane) {		
		tetrisGame.pause();
		centGame.pause();
		siGame.pause();

		// Button for SplashScreen
		JButton arcadeButton = new JButton("Arcade Controls");
		arcadeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArcadeControls = true;
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, MAINMENU);
			}
		});
		
		JButton pcButton = new JButton("PC Controls");
		pcButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArcadeControls = false;
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, MAINMENU);
			}
		});

		// SPLASHSCREEN CARD setup
		SplashScreen splash = new SplashScreen();
		splash.setLayout(new FlowLayout());
		splash.add(arcadeButton);
		splash.add(pcButton);
		card1.add(splash);

		// Buttons for MainMenu
		JButton menuButton1 = new JButton("PLAY TETRIS");
		menuButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.add(card3, TETRIS);
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, TETRIS);
				card3.requestFocusInWindow();
			}
		});
		
		// Buttons for high scores
		JButton menuButton2 = new JButton("HIGH SCORES");
		menuButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card6.revalidate();
				cards.add(card6, HIGHSCORES);
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, HIGHSCORES);				
			}
		});
		
		// Button for Centipede
		JButton menuButton3 = new JButton("PLAY CENTIPEDE");
		menuButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				cards.add(card4, CENTIPEDE);
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, CENTIPEDE);
				card4.requestFocusInWindow();
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
				card5.requestFocusInWindow();
			}
		});
		
		// Button for Space Invaders
		JButton menuButton5 = new JButton("CONTROLS");
		menuButton5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, SPLASHSCREEN);
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
		gbc.gridy++;
		mms.add(menuButton5, gbc);
		
		// HIGHSCORE CARD setup ---------------------------------------------------------------
		HighScoreMenuScreen bckgrnd = new HighScoreMenuScreen();
		bckgrnd.setLayout(new GridBagLayout());
		GridBagConstraints gbc12 = new GridBagConstraints();
		gbc12.gridx = 0;
		gbc12.gridy = 0;
		gbc12.insets = new Insets(2,2,2,2);
		
		//Tetris high scores
		JLabel tetHS = new JLabel("Tetris:");
		tetHS.setFont(new Font("Dialog", Font.BOLD, 20));
		tetHS.setForeground(Color.WHITE);
		Container tetContainer = new Container();
		tetContainer.setLayout(new GridBagLayout());
		GridBagConstraints gbc13 = new GridBagConstraints();
		gbc13.gridx = 0;
		gbc13.gridy = 0;
		gbc13.insets = new Insets(2, 2, 2, 2);
		tetContainer.add(tetHS, gbc13);
		gbc13.gridy++;
		
		JTextArea hsTetris = new JTextArea(20,20);
		hsTetris.setLineWrap(true);
		try {
			BufferedReader input = new BufferedReader(new FileReader("Tetris.txt"));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					hsTetris.append(line + "\n");
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		hsTetris.setEditable(false);
		JScrollPane tetScrollPane = new JScrollPane(hsTetris);
		tetScrollPane.setEnabled(false);
		tetContainer.add(tetScrollPane, gbc13);
		
		bckgrnd.add(tetContainer, gbc12);
		gbc12.gridx++;
		
		//Cent high scores
		JLabel centHS = new JLabel("Centipede:");
		centHS.setFont(new Font("Dialog", Font.BOLD, 20));
		centHS.setForeground(Color.WHITE);
		Container centContainer = new Container();
		centContainer.setLayout(new GridBagLayout());
		GridBagConstraints gbc14 = new GridBagConstraints();
		gbc14.gridx = 0;
		gbc14.gridy = 0;
		gbc14.insets = new Insets(2, 2, 2, 2);
		centContainer.add(centHS, gbc14);
		gbc14.gridy++;
		
		JTextArea hsCent = new JTextArea(20,20);
		hsCent.setLineWrap(true);
		try {
			BufferedReader input = new BufferedReader(new FileReader("Centipede.txt"));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					hsCent.append(line + "\n");
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		hsCent.setEditable(false);
		JScrollPane centScrollPane = new JScrollPane(hsCent);
		centScrollPane.setEnabled(false);
		centContainer.add(centScrollPane, gbc13);
		
		bckgrnd.add(centContainer, gbc12);
		gbc12.gridx++;
		
		//SpaceInvaders high scores
		JLabel siHS = new JLabel("Space Invaders:");
		siHS.setFont(new Font("Dialog", Font.BOLD, 20));
		siHS.setForeground(Color.WHITE);
		Container siContainer = new Container();
		siContainer.setLayout(new GridBagLayout());
		GridBagConstraints gbc15 = new GridBagConstraints();
		gbc15.gridx = 0;
		gbc15.gridy = 0;
		gbc15.insets = new Insets(2, 2, 2, 2);
		siContainer.add(siHS, gbc15);
		gbc15.gridy++;
		
		JTextArea hsSI = new JTextArea(20,20);
		hsSI.setLineWrap(true);
		try {
			BufferedReader input = new BufferedReader(new FileReader("Space Invaders.txt"));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					hsSI.append(line + "\n");
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		hsSI.setEditable(false);
		JScrollPane siScrollPane = new JScrollPane(hsSI);
		siScrollPane.setEnabled(false);
		siContainer.add(siScrollPane, gbc13);
		
		bckgrnd.add(siContainer, gbc12);
		gbc12.gridx--;
		gbc12.gridy++;
		JButton hsMainMenuButton = new JButton("Main Menu");
		bckgrnd.add(hsMainMenuButton, gbc12);
		
		// If main menu button is pressed in space invaders game
		hsMainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.remove(card6);
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, MAINMENU);				
			}
		});
		
		card6.add(bckgrnd);		
		// ------------------------------------------------------------------------------------

		// TETRIS CARD setup
		card3.setLayout(new GridBagLayout());
		final GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gbc2.insets = new Insets(2, 2, 2, 2);
				
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
		Container tetrisContainer = new Container(); // Used for label and next piece grid
		tetrisContainer.setLayout(new GridBagLayout());
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.gridx = 0;
		gbc3.gridy = 0;
		gbc3.insets = new Insets(2, 2, 2, 2);
		tetrisContainer.add(nextPiece, gbc3);
		gbc3.gridy++;
		tetrisContainer.add(tetrisTable2, gbc3);

		// Adds tetris grid
		card3.add(tetrisContainer, gbc2);
		gbc2.gridx++;
		card3.add(tetrisTable, gbc2);
		gbc2.gridx++;

		// Add buttons
		final Container tetrisButtonContainer = new Container();
		tetrisButtonContainer.setLayout(new GridBagLayout());
		final GridBagConstraints gbc4 = new GridBagConstraints();
		gbc4.gridx = 0;
		gbc4.gridy = 0;
		gbc4.insets = new Insets(2, 2, 2, 2);
		final JButton tetrisPauseButton = new JButton("Start");
		tetrisButtonContainer.add(tetrisPauseButton, gbc4);
		gbc4.gridy++;
		final JButton tetrisRestartButton = new JButton("Restart");
		tetrisButtonContainer.add(tetrisRestartButton, gbc4);
		gbc4.gridy++;
		final JButton mainMenuButton = new JButton("Main Menu");
		tetrisButtonContainer.add(mainMenuButton, gbc4);
		gbc4.gridy++;

		card3.add(tetrisButtonContainer, gbc2);
		card3.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
		// -----------------------------------------------------------------------------

		// If pause button is pressed
		tetrisPauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tetrisFlag) {
					tetrisPauseButton.setText("Pause");
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
					if(!ArcadeControls)
					{
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
					else
					{
						if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
							tetrisGame.move_Left();
							draw_tetris_grid();
							card3.revalidate();
						} else if (e.getKeyChar() == 'g'
								|| e.getKeyChar() == 'G') {
							tetrisGame.move_Right();
							draw_tetris_grid();
							card3.revalidate();
						} else if (e.getKeyChar() == 'a'
								|| e.getKeyChar() == 'A') {
							tetrisGame.rotate_left();
							draw_tetris_grid();
							card3.revalidate();
						} else if (e.getKeyChar() == 's'
								|| e.getKeyChar() == 'S') {
							tetrisGame.rotate_right();
							draw_tetris_grid();
							card3.revalidate();
						} else if (e.getKeyChar() == 'f'
								|| e.getKeyChar() == 'F') {
							tetrisGame.drop_Piece();
							draw_tetris_grid();
							card3.revalidate();
						}
					}
				}
				if(!ArcadeControls)
				{
					if (e.getKeyChar() == ' ') {
						if (tetrisFlag) {
							tetrisPauseButton.setText("Pause");
							tetrisFlag = false;
						}
						tetrisGame.pause();
						card3.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
					} else if (e.getKeyChar() == 'r'
						|| e.getKeyChar() == 'R') {
						tetrisRestartButton.doClick();
					} else if (e.getKeyChar() == 'm'
						|| e.getKeyChar() == 'M') {
						mainMenuButton.doClick();
					}
				}
				else
				{
					if(e.getKeyChar() == 'q' || e.getKeyChar() == 'Q')
					{
						if(tetrisFlag)
						{
							tetrisPauseButton.setText("Pause");
							tetrisFlag = false;
						}
						tetrisGame.pause();
						card3.requestFocusInWindow();
					}
				}				
			}
		};
		card3.addKeyListener(tkl);

		card3.requestFocusInWindow();
		draw_tetris_grid();
		draw_next_piece_grid();
		card3.revalidate(); // Redraws graphics on card3

		// Actual loop for game
		final Timer tetrisTimer = new Timer(TIMER, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!tetrisGame.getPause()) {
					tetrisGame.move_Down();
					draw_tetris_grid();
					draw_next_piece_grid();
					card3.revalidate(); // Redraws graphics on card3
				}
				if (tetrisGame.isOver()) { // Print final score
					((Timer) arg0.getSource()).stop();
					Container gameOverContainer = new Container();
					gameOverContainer.setLayout(new GridBagLayout());
					GridBagConstraints gbc9 = new GridBagConstraints();
					gbc9.gridx = 0;
					gbc9.gridy = 0;
					gbc9.insets = new Insets(2, 2, 2, 2);
					JLabel gameOver = new JLabel("GAME OVER!");
					JLabel tetrisScore = new JLabel("Final score is: " + tetrisGame.getScore());
					JLabel pressRestart = new JLabel("Press restart!");
					gameOverContainer.add(gameOver, gbc9);
					gbc9.gridy++;
					gameOverContainer.add(tetrisScore, gbc9);
					gbc9.gridy++;
					gameOverContainer.add(pressRestart, gbc9);
					tetrisButtonContainer.add(gameOverContainer, gbc4);
					card3.requestFocusInWindow();
					
					Score score = new Score();
					score.getScores("Tetris");
					score.createRandomName();
					score.setCurrent_Score(tetrisGame.getScore());
					score.checkScore();
				}
			}
		});
		tetrisTimer.setRepeats(true);
		tetrisTimer.setCoalesce(true);
		tetrisTimer.start();

		// If restart button is pressed
		tetrisRestartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card3.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
				if(tetrisGame.isOver()) { // Remove final score stuff
					tetrisButtonContainer.remove(tetrisButtonContainer.getComponentCount() - 1);
				}
				tetrisGame = new Tetris();
				draw_tetris_grid();
				draw_next_piece_grid();
				card3.revalidate(); // Redraws graphics on card3
				tetrisTimer.setRepeats(true);
				tetrisTimer.setCoalesce(true);
				tetrisTimer.start();
				tetrisGame.pause();
				tetrisFlag = true;
				tetrisPauseButton.setText("Start");
			}
		});
		
		// If main menu button is pressed in tetris game
		mainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tetrisFlag = true;
				if (!tetrisGame.getPause()) {
					tetrisGame.pause();
				}
				cards.remove(card3);
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, MAINMENU);				
			}
		});
			
		
		// CENTIPEDE CARD setup
		card4.setLayout(new GridBagLayout());
		final GridBagConstraints gbc5 = new GridBagConstraints();
		gbc5.gridx = 0;
		gbc5.gridy = 0;
		gbc5.insets = new Insets(2, 2, 2, 2);
		
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
		
		final Container centButtonContainer = new Container();
		centButtonContainer.setLayout(new GridBagLayout());
		final GridBagConstraints gbc6 = new GridBagConstraints();
		gbc6.gridx = 0;
		gbc6.gridy = 0;
		gbc6.insets = new Insets(2, 2, 2, 2);
		final JButton centPauseButton = new JButton("Start");
		centButtonContainer.add(centPauseButton, gbc6);
		gbc6.gridy++;
		final JButton centRestartButton = new JButton("Restart");
		centButtonContainer.add(centRestartButton, gbc6);
		gbc6.gridy++;
		final JButton centMainMenuButton = new JButton("Main Menu");
		centButtonContainer.add(centMainMenuButton, gbc6);
		gbc6.gridy++;
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
					if(!ArcadeControls)
					{
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
					else
					{
						if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
							centGame.Move('l');
							draw_centipede_grid();
							card4.revalidate();
						} else if (e.getKeyChar() == 'g'
								|| e.getKeyChar() == 'G') {
							centGame.Move('r');
							draw_centipede_grid();
							card4.revalidate();
						} else if (e.getKeyChar() == 'r'
								|| e.getKeyChar() == 'R') {
							centGame.Move('u');
							draw_centipede_grid();
							card4.revalidate();
						} else if (e.getKeyChar() == 'f'
								|| e.getKeyChar() == 'F') {
							centGame.Move('d');
							draw_centipede_grid();
							card4.revalidate();
						}
					}
				}
				if(!ArcadeControls)
				{
					if (e.getKeyChar() == ' ') {
						if (centFlag) {
							centPauseButton.setText("Pause");
							centFlag = false;
						}
						centGame.pause();
						card4.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
					} else if (e.getKeyChar() == 'r'
						|| e.getKeyChar() == 'R') {
						centRestartButton.doClick();
					} else if (e.getKeyChar() == 'm'
						|| e.getKeyChar() == 'M') {
						centMainMenuButton.doClick();
					}
				}
				else
				{
					if(e.getKeyChar() == 'q' || e.getKeyChar() == 'Q')
					{
						if(centFlag)
						{
							centPauseButton.setText("Pause");
							centFlag = false;
						}
						centGame.pause();
						card4.requestFocusInWindow();
					}
				}
			}
		};
		card4.addKeyListener(ckl);

		card4.requestFocusInWindow();
		centGame.Move(centGame.getCurrentDir());
		draw_centipede_grid();
		card4.revalidate(); // Redraws graphics on card4
		
		// Actual loop for game
		final Timer centTimer = new Timer(TIMER, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!centGame.getPause()) {
					centGame.Move(centGame.getCurrentDir());
					draw_centipede_grid();
					card4.revalidate();
				}
				if (centGame.isOver()) { // Print final score
					((Timer) arg0.getSource()).stop();
					Container gameOverContainer = new Container();
					gameOverContainer.setLayout(new GridBagLayout());
					GridBagConstraints gbc10 = new GridBagConstraints();
					gbc10.gridx = 0;
					gbc10.gridy = 0;
					gbc10.insets = new Insets(2, 2, 2, 2);
					JLabel gameOver = new JLabel("GAME OVER!");
					JLabel tetrisScore = new JLabel("Final score is: " + centGame.getScore());
					JLabel pressRestart = new JLabel("Press restart!");
					gameOverContainer.add(gameOver, gbc10);
					gbc10.gridy++;
					gameOverContainer.add(tetrisScore, gbc10);
					gbc10.gridy++;
					gameOverContainer.add(pressRestart, gbc10);
					centButtonContainer.add(gameOverContainer, gbc6);
					card4.requestFocusInWindow();
					
					Score score = new Score();
					score.getScores("Centipede");
					score.createRandomName();
					score.setCurrent_Score(centGame.getScore());
					score.checkScore();
				}
			}
		});
		centTimer.setRepeats(true);
		centTimer.setCoalesce(true);
		centTimer.start();
		
		// If restart button is pressed
		centRestartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card4.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
				if(centGame.isOver()) { // Remove final score stuff
					centButtonContainer.remove(centButtonContainer.getComponentCount() - 1);
				}
				centGame = new Centipede();
				centGame.Move('l');
				draw_centipede_grid();						
				card4.revalidate(); // Redraws graphics on card4
				centTimer.setRepeats(true);
				centTimer.setCoalesce(true);
				centTimer.start();
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
				if (!centGame.getPause()) {
					centGame.pause();
				}				
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, MAINMENU);				
			}
		});
		
		// SPACE INVADERS CARD setup
		card5.setLayout(new GridBagLayout());
		final GridBagConstraints gbc7 = new GridBagConstraints();
		gbc7.gridx = 0;
		gbc7.gridy = 0;
		gbc7.insets = new Insets(2, 2, 2, 2);
		
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

		// Puts components on card5
		card5.add(siTable, gbc7);
		gbc7.gridx++;
						
		final Container siButtonContainer = new Container();
		siButtonContainer.setLayout(new GridBagLayout());
		final GridBagConstraints gbc8 = new GridBagConstraints();
		gbc8.gridx = 0;
		gbc8.gridy = 0;
		gbc8.insets = new Insets(2, 2, 2, 2);
		final JButton siPauseButton = new JButton("Start");
		siButtonContainer.add(siPauseButton, gbc8);
		gbc8.gridy++;
		final JButton siRestartButton = new JButton("Restart");
		siButtonContainer.add(siRestartButton, gbc8);
		gbc8.gridy++;
		final JButton siMainMenuButton = new JButton("Main Menu");
		siButtonContainer.add(siMainMenuButton, gbc8);
		gbc8.gridy++;
		card5.add(siButtonContainer, gbc7);
		card5.requestFocusInWindow();
		
		// If pause button is pressed
		siPauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (siFlag) {
					siPauseButton.setText("Pause");
					siFlag = false;
				}
				siGame.pause();
				card5.requestFocusInWindow();
			}
		});
		
		// Check for user interaction with keyboard
		KeyListener skl = new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if (!siGame.getPause() && !siGame.isOver()) {
					if(!ArcadeControls)
					{
						if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
							siGame.characterMoveLeft();
							draw_si_grid();
							card5.revalidate();
						} else if (e.getKeyChar() == 'd'
								|| e.getKeyChar() == 'D') {
							siGame.characterMoveRight();
							draw_si_grid();
							card5.revalidate();
						} else if (e.getKeyChar() == 'w'
								|| e.getKeyChar() == 'W') {
							siGame.createUserLaser();
							draw_si_grid();
							card5.revalidate();
						}
					}
					else
					{
						if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
							siGame.characterMoveLeft();
							draw_si_grid();
							card5.revalidate();
						} else if (e.getKeyChar() == 'g'
								|| e.getKeyChar() == 'G') {
							siGame.characterMoveRight();
							draw_si_grid();
							card5.revalidate();
						} else if (e.getKeyChar() == 'r'
								|| e.getKeyChar() == 'R' || e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
							siGame.createUserLaser();
							draw_si_grid();
							card5.revalidate();
						}
					}
				}
				if(!ArcadeControls)
				{
					if (e.getKeyChar() == ' ') {
						if (siFlag) {
							siPauseButton.setText("Pause");
							siFlag = false;
						}
						siGame.pause();
						card5.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
					} else if (e.getKeyChar() == 'r'
						|| e.getKeyChar() == 'R') {
						siRestartButton.doClick();
					}  else if (e.getKeyChar() == 'm'
						|| e.getKeyChar() == 'M') {
						siMainMenuButton.doClick();
					}
				}
				else
				{
					if(e.getKeyChar() == 'q' || e.getKeyChar() == 'Q')
					{
						if(siFlag)
						{
							siPauseButton.setText("Pause");
							siFlag = false;
						}
						siGame.pause();
						card5.requestFocusInWindow();
					}
				}
			}
		};
		card5.addKeyListener(skl);

		card5.requestFocusInWindow();
		draw_si_grid();
		card5.revalidate(); // Redraws graphics on card5
		
		// Actual loop for game
		final Timer siTimer = new Timer(TIMER, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!siGame.getPause()) {
					siGame.moveAliens();
					draw_si_grid();
					card5.revalidate();
				}
				if (siGame.isOver()) {
					((Timer) arg0.getSource()).stop();
					Container gameOverContainer = new Container();
					gameOverContainer.setLayout(new GridBagLayout());
					GridBagConstraints gbc11 = new GridBagConstraints();
					gbc11.gridx = 0;
					gbc11.gridy = 0;
					gbc11.insets = new Insets(2, 2, 2, 2);
					JLabel gameOver = new JLabel("GAME OVER!");
					JLabel tetrisScore = new JLabel("Final score is: " + siGame.getScore());
					JLabel pressRestart = new JLabel("Press restart!");
					gameOverContainer.add(gameOver, gbc11);
					gbc11.gridy++;
					gameOverContainer.add(tetrisScore, gbc11);
					gbc11.gridy++;
					gameOverContainer.add(pressRestart, gbc11);
					siButtonContainer.add(gameOverContainer, gbc8);
					card5.requestFocusInWindow();
					
					Score score = new Score();
					score.getScores("Space Invaders");
					score.createRandomName();
					score.setCurrent_Score(siGame.getScore());
					score.checkScore();
				}
			}
		});
		siTimer.setRepeats(true);
		siTimer.setCoalesce(true);
		siTimer.start();
		
		// If restart button is pressed
		siRestartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card5.requestFocusInWindow(); // Needed to reset focus for keyboard interaction
				if(siGame.isOver()) { // Remove final score stuff
					siButtonContainer.remove(siButtonContainer.getComponentCount() - 1);
				}
				siGame = new SpaceInvaders();
				draw_si_grid();						
				card5.revalidate(); // Redraws graphics on card5
				siTimer.setRepeats(true);
				siTimer.setCoalesce(true);
				siTimer.start();
				siGame.pause();
				siFlag = true;
				siPauseButton.setText("Start");
			}
		});
		
		// If main menu button is pressed in space invaders game
		siMainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.remove(card5);
				siFlag = true;
				if (!siGame.getPause()) {
					siGame.pause();
				}
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, MAINMENU);				
			}
		});
		
		// Sets up layout
		cards = new JPanel(new CardLayout());
		cards.add(card1, SPLASHSCREEN);
		cards.add(card2, MAINMENU);

		// Creates the actual window
		pane.add(cards, BorderLayout.CENTER);
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
				return Color.ORANGE;
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
					return Color.ORANGE;
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
					return Color.ORANGE;
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
			c.setForeground(getColor((Object) value));
			return c;
		}

		private Color getColor(Object value) {
			// get grid object (alien, barrier, or character) and use certain color for each
			if (value.getClass().equals(Barrier.class)) {
				return Color.MAGENTA;
			}
			else if (value.getClass().equals(Alien.class)) {
				return Color.CYAN;
			}
			else if (value.getClass().equals(edu.ycp.cs.Invader.Character.class)) {
				return Color.GREEN;
			}
//			else if (value.getClass().equals(Laser.class)) {
//				return Color.WHITE;
//			}
			else if (value.getClass().equals(Laser.class)) {
				if (((Laser) value).isFriendly()) {
					return Color.WHITE;
				}
				else {
					return Color.YELLOW;
				}
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
