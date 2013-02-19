package edu.ycp.cs.Main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Window extends JFrame
{
	private static final long serialVersionUID = 1L; //Dont know what this does either
	
	private JButton mainMenuButton = null;
		
	public Window() 
	{
		super(); //I don't know what this does
		initialize();
	}
	
	private void initialize() 
	{
		this.setSize(800, 600);
		this.setResizable(false);
		setTitle("M&M ARCADE");
		setContentPane(new JLabel(new ImageIcon("Logo.png")));
		setLayout(new FlowLayout());
		
		mainMenuButton = new JButton("Continue to Main Menu");
		add(mainMenuButton);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	private JButton getDrawButton() 
	{
		if (mainMenuButton == null) 
		{
			mainMenuButton = new JButton();
			mainMenuButton.setBounds(new java.awt.Rectangle(264,534,102,31));
			mainMenuButton.setText("Draw!");
			mainMenuButton.addActionListener(new java.awt.event.ActionListener() 
			{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					//Make the Jpanel change to main menu view
				}
			});
		}
		return mainMenuButton;
	}
	
	public static void main(String args[])
	{
		Window window = new Window();
		window.setVisible(true);
	}
}