package edu.ycp.cs.Invader;

import java.util.Random;

public class Game 
{
	public static void main(String[] args) throws InterruptedException 
	{
			SpaceInvaders SIgame = new SpaceInvaders();
			
			while(!SIgame.isOver())
			{
				SIgame.printGrid();
				SIgame.moveAliens();
				
				Random random = new Random();
				int val = random.nextInt(10);
				
				if(val == 1)
				{
					SIgame.getCharacter().moveLeft();
				}
				else if(val == 2)
				{
					SIgame.getCharacter().moveRight();
				}
				else if(val == 3)
				{
					SIgame.createUserLaser();
				}
				
				Thread.sleep(250);
			}
			
			System.out.println("\nGame is over");
	}
}
