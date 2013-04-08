package edu.ycp.cs.Invader;

public class Game 
{
	public static void main(String[] args) throws InterruptedException 
	{
			SpaceInvaders SIgame = new SpaceInvaders();
			
			while(!SIgame.isOver())
			{
				SIgame.printGrid();
				SIgame.moveAliens();
				
				Thread.sleep(250);
			}
			
			System.out.println("\nGame is over");
	}
}
