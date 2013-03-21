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
import javax.swing.Timer;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
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
	private JTable table = new JTable(new MyTableModel());
	private Tetris game = new Tetris(); 
//	private JTable table;
//	private TableModel model;
//	private int currentRow = 0;
//  private int blockHeight = 3;
//  private int blockWidth = 3;

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
			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				card3.remove(0); //remove start button
//				model = new TableModel();
//				table = new JTable(model);
//				table.setDefaultRenderer(Integer.class, new TableRenderer());
//				table.setRowHeight(GRID_ROW_HEIGHT);
//				for (int i = 0; i < NUM_COLS; i++) {
//					table.getColumnModel().getColumn(i).setPreferredWidth(table.getRowHeight());					
//				}
//				card3.setLayout(new GridBagLayout());
//				card3.add(table);
//				//Tetris game = new Tetris();
//				
//				Timer timer = new Timer(500, new ActionListener() {					
//					@Override
//	                public void actionPerformed(ActionEvent e) {
//	                    int col = (model.getColumnCount() - blockWidth) / 2;
//	                    int row = currentRow - blockHeight;
//	                    if (row + blockHeight >= model.getRowCount()) {
//	                        ((Timer) e.getSource()).stop();
//	                    } else {
//	                        drawShape(row, col, 0);
//	                        currentRow++;
//	                        row = currentRow - blockHeight;
//	                        drawShape(row, col, 3);
//	                    }
//	                }
//					public void drawShape(int row, int col, int color) {
//
//	                    for (int index = 0; index < blockHeight; index++) {
//
//	                        if (row >= 0 && row < model.getRowCount()) {
//
//	                            switch (index) {
//	                                case 0:
//	                                case 1:
//	                                    model.setValueAt(color, row, col);
//	                                    break;
//	                                case 2:
//	                                    model.setValueAt(color, row, col);
//	                                    model.setValueAt(color, row, col + 1);
//	                                    model.setValueAt(color, row, col + 2);
//	                                    break;
//	                            }
//
//	                        }
//	                        row++;
//
//	                    }
//	                }
//	            });
//	            timer.setRepeats(true);
//	            timer.setCoalesce(true);
//	            timer.start();	            
//	            
//	            card3.revalidate();
//			}
//		});

		// Sets up layout
		cards = new JPanel(new CardLayout());
		cards.add(card1, SPLASHSCREEN);
		cards.add(card2, MAINMENU);
		cards.add(card3, TETRIS);

		// Creates the actual window
		pane.add(cards, BorderLayout.CENTER);
	}
	
//	public class TableModel extends AbstractTableModel {
//		
//        private int[][] values;
//
//        public TableModel() {
//            values = new int[NUM_ROWS][NUM_COLS];
//        }
//
//        @Override
//        public int getRowCount() {
//            return NUM_ROWS;
//        }
//
//        @Override
//        public int getColumnCount() {
//            return NUM_COLS;
//        }
//
//        @Override
//        public Class<?> getColumnClass(int columnIndex) {
//            return Integer.class;
//        }
//
//        @Override
//        public Object getValueAt(int row, int col) {
//            return values[row][col];
//        }
//
//        @Override
//        public void setValueAt(Object val, int row, int col) {
//            values[row][col] = (Integer) val;
//            fireTableCellUpdated(row, col);
//        }
//    }	
//
//	public class TableRenderer extends DefaultTableCellRenderer {
//		@Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            super.getTableCellRendererComponent(table, "", false, false, row, column);
//            setOpaque(true);
//            if (value != null) {
//                if ((Integer) value == 0) {
//                    setBackground(Color.WHITE);
//                } else if ((Integer) value == 1) {
//                    setBackground(Color.RED);
//                } else if ((Integer) value == 2) {
//                    setBackground(Color.GREEN);
//                } else if ((Integer) value == 3) {
//                    setBackground(Color.BLUE);
//                } else if ((Integer) value == 4) {
//                    setBackground(Color.YELLOW);
//                }
//            } else {
//                setBackground(Color.DARK_GRAY);
//            }
//            return this;
//        }
//    }
	
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
