package edu.ycp.cs.Tetris;

import java.util.Random;

public class Game
{
	static Tetris game;
	
	public static void main(String[] args) throws InterruptedException 
	{
		game = new Tetris();
		game.print_Game();
		
		while(true)
		{
			System.out.print("\n\n");
			Thread.sleep(1000);
			Random random = new Random();
			int val = random.nextInt(5);
			if(val == 0)
			{
				//game.move_Left();
			}
			else if(val == 1)
			{
				//game.move_Right();
			}
			//game.rotate_left();
			game.rotate_right();
			game.move_Down();
			game.print_Game();
		}
	}
}
