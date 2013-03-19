package edu.ycp.cs.Centipede;

import java.util.LinkedList;
import java.util.Random;

public class Centipede 
{
	int head_row;
	int head_col;
	
	int next_row;
	int next_col;
	
	int grid[][];
	
	LinkedList<Coordinates> Centipede;
	Coordinates Food;
	
	boolean Over;
	
	public Centipede()
	{
		grid = new int[20][20];
		
		for(int i = 0; i < 20; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				grid[i][j] = 0;
			}
		}
		Centipede = new LinkedList<Coordinates>();
		
		Centipede.add(new Coordinates(9, 10));
		Centipede.add(new Coordinates(10, 10));
		
		grid[9][10] = Values.CENTIPEDE.getKey();
		grid[10][10] = Values.CENTIPEDE.getKey();
		
		head_col = 9;
		head_row = 10;
		
		next_row = 8;
		next_col = 10;
		
		Food = placeFood();
		
		Over = false;
	}
	
	public int getHead_row() 
	{
		return head_row;
	}
	public void setHead_row(int head_row) 
	{
		this.head_row = head_row;
	}
	public int getHead_col() 
	{
		return head_col;
	}
	public void setHead_col(int head_col) 
	{
		this.head_col = head_col;
	}
	public int getNext_row() 
	{
		return next_row;
	}
	public void setNext_row(int next_row) 
	{
		this.next_row = next_row;
	}
	public int getNext_col() 
	{
		return next_col;
	}
	public void setNext_col(int next_col) 
	{
		this.next_col = next_col;
	}
	
	public Coordinates placeFood()
	{
		int blanks = getNumberofBlanks();
		
		Random random = new Random();
		int num = random.nextInt(blanks);
		
		return putFoodatBlank(num);
	}
	
	private Coordinates putFoodatBlank(int num) 
	{
		int current = 0;
		Coordinates returnVal = new Coordinates(0, 0);
		
		for(int i = 0; i < 20; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				if(grid[i][j] == Values.BLANK.getKey())
				{
					if(current == num)
					{
						returnVal.setX(i);
						returnVal.setY(j);
						i = 20;
						j = 20;
					}
					else
					{
						current ++;
					}
				}
			}
		}
		
		return returnVal;
	}

	public int getNumberofBlanks()
	{
		int returnVal = 0;
		
		for(int i = 0; i < 20; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				if(grid[i][j] == 0)
				{
					returnVal ++;
				}
			}
		}
		
		return returnVal;
	}
	
	public void Move(char m)
	{		
		if(m == 'l')
		{
			next_col = head_col - 1;
			next_row = head_row;
		}
		else if(m == 'r')
		{
			next_col = head_col + 1;
			next_row = head_row;
		}
		else if(m == 'u')
		{
			next_col = head_col;
			next_row = head_row + 1;
		}
		else if(m == 'd')
		{
			next_col = head_col;
			next_row = head_row - 1;
		}
		
		if(next_col >= 20 || next_col < 0 || next_row >= 20 || next_row < 0)
		{
			Over = true;
		}
		else
		{
		
			if(grid[next_col][next_row] == Values.CENTIPEDE.getKey())
			{
				Over = true;
			}
			else
			{
				Centipede.addFirst(new Coordinates(next_col, next_row));
				
				if(grid[next_col][next_row] != Values.FOOD.getKey())
				{
					Centipede.removeLast();
				}
				else
				{
					placeFood();
				}
				
				head_row = next_row;
				head_col = next_col;
			}
			
			render_Game();
		}
	}
	
	public void render_Game()
	{
		for(int i = 0; i < 20; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				grid[i][j] = Values.BLANK.getKey();
			}
		}
		for(Coordinates c : Centipede)
		{
			grid[c.getX()][c.getY()] = Values.CENTIPEDE.getKey();
		}
		System.out.println("(" + Food.getX() + ", " + Food.getY() + ")");
		grid[Food.getX()][Food.getY()] = Values.FOOD.getKey();
	}
	
	public void print_Game()
	{
		System.out.println("\n");
		for(int i = 0; i < 20; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				System.out.print(grid[j][i] + " ");
			}
			System.out.print("\n");
		}
	}
	
	//This function is for testing only
	public void Grow(char c)
	{
		int temp_row = 0;
		int temp_col = 0;
		
		if(c == 'l')
		{
			temp_col = head_col - 1;
			temp_row = head_row;
		}
		else if(c == 'r')
		{
			temp_col = head_col + 1;
			temp_row = head_row;
		}
		else if(c == 'u')
		{
			temp_col = head_col;
			temp_row = head_row + 1;
		}
		else if(c == 'd')
		{
			temp_col = head_col;
			temp_row = head_row - 1;
		}
		grid[temp_col][temp_row] = Values.FOOD.getKey();
		Move(c);
	}
}
