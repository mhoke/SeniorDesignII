package edu.ycp.cs.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainMenuScreen extends JPanel{
	private static final long serialVersionUID = 1L;

	private BufferedImage background;

	public MainMenuScreen() {
		try {
			background = ImageIO.read(new File("M&M Arcade.png"));
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
		g2d.drawString("Please wait...", getWidth() / 2, getHeight() * 3 / 4);
	}
}