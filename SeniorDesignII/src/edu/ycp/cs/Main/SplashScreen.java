package edu.ycp.cs.Main;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

class SplashScreen extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage background;

	public SplashScreen() {
		try {
			background = ImageIO.read(new File("Logo.png"));
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