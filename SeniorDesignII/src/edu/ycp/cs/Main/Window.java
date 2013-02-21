package edu.ycp.cs.Main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Window extends JFrame
{
	private static final long serialVersionUID = 1L; //Dont know what this does either
	
	//Test
	
	private static void createAndShowGUI()  {
		 
		final JFrame mainmenu = new JFrame();
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
                splashscreen.setVisible(false);
                mainmenu.setVisible(true);
            }
        });      
 
        splashscreen.getContentPane().add(button);
        splashscreen.pack();
        splashscreen.setVisible(true);
    }
 
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}