package edu.ycp.cs.Invader;

import java.util.LinkedList;
import java.util.ListIterator;

public class SpaceInvaders 
{
	boolean move_Left;
//	Coordinates top_left;
//	Coordinates bottom_right;
	int score;
	Object grid[][];
	
	LinkedList<Alien> AlienList;
	LinkedList<Barrier> BarrierList;
	
	Character character;
	
	boolean move_Down;
	boolean is_Over;
	
	public SpaceInvaders()
	{
		move_Left = false;
//		top_left = new Coordinates(0, 19);
//		bottom_right = new Coordinates(10, 14);
		score = 0;
		grid = new Object[20][25];
		move_Down = false;
		is_Over = false;
		
		AlienList = new LinkedList<Alien>();
		BarrierList = new LinkedList<Barrier>();
		
		setBarriers();
		setAliens();
		
		character = new Character(7, 0);
		
		renderGrid();
	}
	
	public void setBarriers()
	{	
		createBarrier(1, 4);
		createBarrier(6, 4);
		createBarrier(11, 4);
		createBarrier(16, 4);
	}
	
	public void setAliens()
	{
		for(int i = 0; i < 10; i ++)
		{
			for(int j = 15; j < 25; j ++)
			{
				AlienList.add(new Alien(i, j, 1));
			}
		}
	}
	
	public void createBarrier(int x, int y)
	{
		BarrierList.add(new Barrier(x, y, 3));
		BarrierList.add(new Barrier(x, ++y, 3));
		BarrierList.add(new Barrier(++x, y, 3));
		BarrierList.add(new Barrier(++x, y, 3));
		BarrierList.add(new Barrier(x, --y, 3));
	}
	
	public void moveAliens()
	{
		if(move_Down)
		{
			for(Alien a : AlienList)
			{
				a.moveDown();
			}
			move_Down = false;
		}
		else
		{
			if(move_Left)
			{
				for(Alien a : AlienList)
				{
					if(a.moveLeft())
					{
						if(!move_Down)
						{
							move_Left = false;
							move_Down = true;
						}
					}				
				}
			}
			else
			{
				for(Alien a : AlienList)
				{
					if(a.moveRight())
					{
						if(!move_Down)
						{
							move_Left = true;
							move_Down = true;
						}
					}
				}
			}
		}
		
		killBarriers();
		
		renderGrid();
		
		for(Alien a : AlienList)
		{
			if(a.charTest(character.getLocation()))
			{
				is_Over = true;
			}
		}
	}
	
	public void killBarriers()
	{
		for(Alien a : AlienList)
		{
			for(Barrier b : BarrierList)
			{
				if(b.getLocation().isEqual(a.getLocation()))
				{
					b.killBarrier();
				}
			}
		}
	}
	
	public void renderGrid()
	{
		for(int i = 0; i < 20; i ++)
		{
			for(int j = 0; j < 25; j ++)
			{
				grid[i][j] = 0;
			}
		}
		
		ListIterator<Alien> alienIterator = AlienList.listIterator();
		while(alienIterator.hasNext())
		{
			if(alienIterator.next().getLife() == 0)
			{
				alienIterator.remove();
			}
		}
		
		ListIterator<Barrier> barrierIterator = BarrierList.listIterator();
		while(barrierIterator.hasNext())
		{
			if(barrierIterator.next().getLife() == 0)
			{
				barrierIterator.remove();
			}
		}
		
		for(Alien a : AlienList)
		{
			grid[a.getX()][a.getY()] = a;
		}
		
		for(Barrier b : BarrierList)
		{
			grid[b.getX()][b.getY()] = b;
		}
		
		grid[character.getLocation().getX()][character.getLocation().getY()] = character;
	}
	
	public void printGrid()
	{
		System.out.println("");
		for(int i = 24; i >= 0; i --)
		{
			for(int j = 0; j < 20; j ++)
			{
				if(grid[j][i].getClass().equals(Alien.class))
				{
					System.out.printf("A ");
				}
				else if(grid[j][i].getClass().equals(Barrier.class))
				{
					System.out.printf("B ");
				}
				else if(grid[j][i].getClass().equals(Character.class))
				{
					System.out.printf("C ");
				}
				else
				{
					System.out.printf("0 ");
				}
			}
			System.out.println("");
		}
	}
	
	public Object[][] getGrid()
	{
		return grid;
	}
	
	public boolean isOver()
	{
		return is_Over;
	}
}
