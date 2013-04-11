package edu.ycp.cs.Centipede;

import java.io.IOException;

public class Game 
{
	public static void main(String[] args) throws IOException 
	{
		Centipede game = new Centipede();
		
		game.print_Game();
		
		game.Grow('l');
		game.Grow('l');
		game.Grow('l');
		game.Grow('l');
		game.Move('l');
		game.Grow('d');
		game.Grow('d');
		game.Grow('d');
		game.Grow('d');
		game.Move('d');
		game.Grow('r');
		game.Grow('r');
		game.Grow('r');
		game.Grow('r');
		game.Grow('r');
		game.Grow('r');
		game.Move('r');
		
		game.print_Game();
	}
}
