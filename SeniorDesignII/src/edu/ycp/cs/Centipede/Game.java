package edu.ycp.cs.Centipede;

public class Game 
{
	public static void main(String[] args) 
	{
		Centipede game = new Centipede();
		
		game.print_Game();
		
		game.Grow('l');
		game.Grow('l');
		game.Grow('l');
		game.Grow('l');
		game.Grow('d');
		game.Grow('d');
		game.Grow('d');
		game.Grow('d');
		game.Grow('r');
		game.Grow('r');
		game.Grow('r');
		game.Grow('r');
		game.Grow('r');
		game.Grow('r');
		game.print_Game();
	}

}
