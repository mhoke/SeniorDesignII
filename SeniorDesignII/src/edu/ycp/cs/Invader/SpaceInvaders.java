package edu.ycp.cs.Invader;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class SpaceInvaders 
{
	int NUM_ROWS = 25;
	int NUM_COLS = 20;
	
	boolean move_Left;
//	Coordinates top_left;
//	Coordinates bottom_right;
	int score;
	Object grid[][];
	
	LinkedList<Alien> AlienList;
	LinkedList<Barrier> BarrierList;
	LinkedList<Laser> LaserList;
	
	Character character;
	
	boolean move_Down;
	boolean is_Over;
	boolean pause;
	
	public SpaceInvaders()
	{
		move_Left = false;
//		top_left = new Coordinates(0, 19);
//		bottom_right = new Coordinates(10, 14);
		score = 0;
		grid = new Object[20][25];
		move_Down = false;
		is_Over = false;
		pause = false;
		
		AlienList = new LinkedList<Alien>();
		BarrierList = new LinkedList<Barrier>();
		LaserList = new LinkedList<Laser>();
		
		setBarriers();
		setAliens();
		
		character = new Character(7, 0);
		
		renderGrid();
	}
	
	public boolean getPause() {
		return pause;
	}
	
	public void pause() {
		pause = !pause;
	}
	
	public int getNumRows() {
		return NUM_ROWS;
	}
	
	public int getNumCols() {
		return NUM_COLS;
	}
	
	public void characterMoveLeft() {
		character.moveLeft();
	}
	
	public void characterMoveRight() {
		character.moveRight();
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
		
		Random random = new Random();
		
		if(random.nextInt(10) < 4)
		{
			createAlienLaser();
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
	
	public void createAlienLaser()
	{
		LinkedList<Coordinates> list = new LinkedList<Coordinates>();
		for(int i = 0; i < 20; i ++)
		{
			int temp = colContainsAlien(i);
			if(temp != -1)
			{
				list.add(new Coordinates(i, temp));
			}
		}
		
		Random random = new Random();
		
		LaserList.add(new Laser(false, list.get(random.nextInt(list.size()))));
	}
	
	public int colContainsAlien(int col)
	{
		for(int i = 0; i < 25; i ++)
		{
			if(grid[col][i].getClass().equals(Alien.class))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	public void createUserLaser()
	{
		int x = character.getLocation().getX();
		int y = character.getLocation().getY() + 1;
		
		Laser laser = new Laser(true, new Coordinates(x, y));
		
		if(!LaserList.contains(laser))
		{
			LaserList.add(laser);
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