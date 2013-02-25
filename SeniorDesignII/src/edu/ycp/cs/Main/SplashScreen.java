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
		g2d.drawString("Please wait...", getWidth() / 2, getHeight() * 3 / 4);
	}
}

/*public SplashScreen(int d) {
		duration = d; //Used for splashscreen timer

		JFrame frame = new JFrame("Splashscreen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(new ImagePane());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.white);
		int width = 800; //Size of splashscreen
		int height = 600;

		//Sets window to middle of screen
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		ImageIcon image = new ImageIcon("Logo.png"); //Splashscreen image 
		JLabel label = new JLabel(); //Countdown timer

		content.add(new JLabel(image));
		content.add(label);
		setVisible(true);

		try {
			for(int i = 3; i >= 0; i--) {
				label.setText(i + " seconds remaining");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Splashscreen sleep failure");
		}

		setVisible(false);		
	}


}*/

/*public class SplashScreen extends JWindow
{
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	private static final long serialVersionUID = 1L; //Dont know what this does either

	//Test

	private static void createAndShowGUI()  {

		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setTitle("M&M ARCADE");



		frame.setContentPane(splashscreen);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);


		/*final JFrame mainmenu = new JFrame();
        mainmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainmenu.setSize(800, 600);
        mainmenu.setResizable(false);
        mainmenu.setTitle("M&M ARCADE");
        mainmenu.setContentPane(new JLabel(new ImageIcon("M&M Arcade.png")));
        mainmenu.setLayout(new FlowLayout());
        mainmenu.setVisible(false);

		final JFrame splashscreen = new JFrame();
        splashscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        splashscreen.setSize(800, 600);
        splashscreen.setResizable(false);
        splashscreen.setTitle("M&M ARCADE");
        splashscreen.setContentPane(new JLabel(new ImageIcon("Logo.png")));
        splashscreen.setLayout(new FlowLayout());

        JButton button = new JButton("Continue To Main Menu");
        //Add action listener to button
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                //frame.setVisible(false);
                //mainmenu.setVisible(true);
            }
        });      

        //splashscreen.getContentPane().add(button);
        //splashscreen.pack();
        //splashscreen.setVisible(true);
    }
}*/