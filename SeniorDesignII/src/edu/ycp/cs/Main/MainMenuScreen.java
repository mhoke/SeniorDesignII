package edu.ycp.cs.Main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuScreen extends JPanel{
	private static final long serialVersionUID = 1L;

	private BufferedImage background;
	//JPanel panelMainMenu;
	//private JButton playButton, highScoreButton;

	public MainMenuScreen() {
		try {
			background = ImageIO.read(new File("M&M Arcade.png"));
//			setLayout(new GridBagLayout());
//			
//			panelMainMenu = this;
//			playButton = new JButton("PLAY");
//			highScoreButton = new JButton("HIGH SCORES");
//			
//			GridBagConstraints gbc = new GridBagConstraints();
//			gbc.gridx = 0;
//			gbc.gridy = 0;
//			gbc.insets = new Insets(2,2,2,2);
//			gbc.anchor = GridBagConstraints.CENTER;
//			
//			this.add(playButton, gbc);
//			gbc.gridy++;
//			this.add(highScoreButton, gbc);
//			
//			playButton.addActionListener(new java.awt.event.ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    panelMainMenu.setVisible(false);
//                }
//            });
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			int x = (getWidth() - background.getWidth());
			int y = (getHeight() - background.getHeight());
			g.drawImage(background, x, y, this);
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.white);
	}
}