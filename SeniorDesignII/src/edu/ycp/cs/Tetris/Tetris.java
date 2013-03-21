package edu.ycp.cs.Tetris;

public class Tetris 
{
	int NUM_ROWS = 20;
	int NUM_COLS = 10;
	
	int grid[][];
	int cur_row;
	int cur_col;
	Pieces cur_color;
	Style cur_style;
	Pieces next_color;
	Style next_style;
	boolean over;
	
	public Tetris()
	{
		grid = new int[10][20];
		
		for(int i = 0; i < 10; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				grid[i][j] = Pieces.BLANK.getKey();
			}
		}
		
		next_color = Pieces.createColor();
		next_style = Style.createStyle();
		over = false;
		
		create_Piece();
	}
	
	public void create_Piece()
	{
		cur_color = next_color;
		cur_style = next_style;
		next_color = Pieces.createColor();
		next_style = Style.createStyle();
		
		cur_row = 19;
		cur_col = 4;
		
		if(grid[4][19] != Pieces.BLANK.getKey())
		{
			over = true;
		}
		
		grid[4][19] = Pieces.CURRENT.getKey();
		
		if(cur_style == Style.BOX)
		{
			if(grid[5][19] != Pieces.BLANK.getKey() || grid[4][18] != Pieces.BLANK.getKey() || grid[5][18] != Pieces.BLANK.getKey())
			{
				over = true;
			}
			else
			{
				grid[5][19] = Pieces.CURRENT.getKey();
				grid[4][18] = Pieces.CURRENT.getKey();
				grid[5][18] = Pieces.CURRENT.getKey();
			}
		}
		else if(cur_style == Style.L)
		{
			if(grid[5][19] != Pieces.BLANK.getKey() || grid[6][19] != Pieces.BLANK.getKey() || grid[4][18] != Pieces.BLANK.getKey())
			{
				over = true;
			}
			else
			{
				grid[5][19] = Pieces.CURRENT.getKey();
				grid[6][19] = Pieces.CURRENT.getKey();
				grid[4][18] = Pieces.CURRENT.getKey();
			}
		}
		else if(cur_style == Style.RL)
		{
			if(grid[4][18] != Pieces.BLANK.getKey() || grid[5][18] != Pieces.BLANK.getKey() || grid[6][18] != Pieces.BLANK.getKey())
			{
				over = true;
			}
			else
			{
				grid[4][18] = Pieces.CURRENT.getKey();
				grid[5][18] = Pieces.CURRENT.getKey();
				grid[6][18] = Pieces.CURRENT.getKey();
			}
		}
		else if(cur_style == Style.ROD)
		{
			if(grid[5][19] != Pieces.BLANK.getKey() || grid[6][19] != Pieces.BLANK.getKey() || grid[7][19] != Pieces.BLANK.getKey())
			{
				over = true;
			}
			else
			{
				grid[5][19] = Pieces.CURRENT.getKey();
				grid[6][19] = Pieces.CURRENT.getKey();
				grid[7][19] = Pieces.CURRENT.getKey();
			}
		}
		else if(cur_style == Style.RZ)
		{
			if(grid[3][19] != Pieces.BLANK.getKey() || grid[4][18] != Pieces.BLANK.getKey() || grid[5][18] != Pieces.BLANK.getKey())
			{
				over = true;
			}
			else
			{
				grid[3][19] = Pieces.CURRENT.getKey();
				grid[4][18] = Pieces.CURRENT.getKey();
				grid[5][18] = Pieces.CURRENT.getKey();
			}
		}
		else if(cur_style == Style.T)
		{
			if(grid[5][19] != Pieces.BLANK.getKey() || grid[4][18] != Pieces.BLANK.getKey() || grid[3][19] != Pieces.BLANK.getKey())
			{
				over = true;
			}
			else
			{
				grid[5][19] = Pieces.CURRENT.getKey();
				grid[4][18] = Pieces.CURRENT.getKey();
				grid[3][19] = Pieces.CURRENT.getKey();
			}
			grid[5][19] = Pieces.CURRENT.getKey();
			grid[4][18] = Pieces.CURRENT.getKey();
			grid[3][19] = Pieces.CURRENT.getKey();
		}
		else if(cur_style == Style.Z)
		{
			if(grid[5][19] != Pieces.BLANK.getKey() || grid[4][18] != Pieces.BLANK.getKey() || grid[3][18] != Pieces.BLANK.getKey())
			{
				over = true;
			}
			else
			{
				grid[5][19] = Pieces.CURRENT.getKey();
				grid[4][18] = Pieces.CURRENT.getKey();
				grid[3][18] = Pieces.CURRENT.getKey();
			}
		}
	}
	
	public void set_Piece()
	{
		boolean flag = true;
		
		for(int j = 0; j < 20; j ++)
		{
			flag = true;
			for(int i = 0; i < 10; i ++)
			{
				if(grid[i][j] == Pieces.CURRENT.getKey())
				{
					grid[i][j] = cur_color.getKey();
				}
				if(grid[i][j] == Pieces.BLANK.getKey())
				{
					flag = false;
				}
			}
			if(flag == true)
			{
				remove_Row(j);
			}
		}
		
		create_Piece();
	}
	
	public void remove_Row(int row)
	{
		for(int i = row; i < 19; i ++)
		{
			for(int j = 0; j < 10; j ++)
			{
				grid[j][i] = grid[j][i + 1];
			}
		}
		
		for(int i = 0; i < 10; i ++)
		{
			grid[i][19] = Pieces.BLANK.getKey();
		}
	}
	
	public void move_Right()
	{
		boolean flag = true;
		
		for(int i = 0; i < 20; i ++)
		{
			if(grid[9][i] == Pieces.CURRENT.getKey())
			{
				flag = false;
				i = 21;
			}
		}
		
		if(flag)
		{	
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey())
					{
						if(grid[i + 1][j] != Pieces.BLANK.getKey() && grid[i + 1][j] != Pieces.CURRENT.getKey() && grid[i + 1][j] != Pieces.TWO.getKey())
						{
							flag = false;
							j = 21;
							i = 11;
						}
					}
				}
			}
		}
		
		if(flag)
		{
			for(int i = 9; i >= 0; i --)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey())
					{
						if(grid[i + 1][j] == Pieces.CURRENT.getKey())
						{
							grid[i + 1][j] = Pieces.TWO.getKey();
						}
						else
						{
							grid[i + 1][j] = Pieces.CURRENT.getKey();
						}
						
						if(grid[i][j] == Pieces.CURRENT.getKey())
						{
							grid[i][j] = Pieces.BLANK.getKey();
						}
						else if(grid[i][j] == Pieces.TWO.getKey())
						{
							grid[i][j] = Pieces.CURRENT.getKey();
						}
					}
				}
			}
			
			cur_col ++;
		}
	}
	
	public void move_Left()
	{
		boolean flag = true;
		
		for(int i = 0; i < 20; i ++)
		{
			if(grid[0][i] == Pieces.CURRENT.getKey())
			{
				flag = false;
				i = 21;
			}
		}
		
		if(flag)
		{	
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey())
					{
						if(grid[i - 1][j] != Pieces.BLANK.getKey() && grid[i - 1][j] != Pieces.CURRENT.getKey() && grid[i - 1][j] != Pieces.TWO.getKey())
						{
							flag = false;
							j = 21;
							i = 11;
						}
					}
				}
			}
		}
		
		if(flag)
		{
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey())
					{
						if(grid[i - 1][j] == Pieces.CURRENT.getKey())
						{
							grid[i - 1][j] = Pieces.TWO.getKey();
						}
						else
						{
							grid[i - 1][j] = Pieces.CURRENT.getKey();
						}
						
						if(grid[i][j] == Pieces.CURRENT.getKey())
						{
							grid[i][j] = Pieces.BLANK.getKey();
						}
						else if(grid[i][j] == Pieces.TWO.getKey())
						{
							grid[i][j] = Pieces.CURRENT.getKey();
						}
					}
				}
			}
			
			cur_col --;
		}
	}
	
	public void move_Down()
	{
		boolean flag = true;
		
		for(int i = 0; i < 10; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				if(grid[i][j] == Pieces.CURRENT.getKey())
				{
					if(j == 0 || (grid[i][j - 1] != Pieces.CURRENT.getKey() && grid[i][j - 1] != Pieces.BLANK.getKey()))
					{
						flag = false;
						set_Piece();
						i = 11;
						j = 21;
					}
				}
			}
		}
		
		if(flag)
		{
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey())
					{
						if(grid[i][j - 1] == Pieces.CURRENT.getKey())
						{
							grid[i][j - 1] = Pieces.TWO.getKey();
						}
						else
						{
							grid[i][j - 1] = Pieces.CURRENT.getKey();
						}
						
						if(grid[i][j] == Pieces.CURRENT.getKey())
						{
							grid[i][j] = Pieces.BLANK.getKey();
						}
						else if(grid[i][j] == Pieces.TWO.getKey())
						{
							grid[i][j] = Pieces.CURRENT.getKey();
						}
					}
				}
			}
			
			cur_row --;
		}
	}
	
	public void rotate_right()
	{	
		boolean flag = true;
		
		for(int i = 0; i < 10; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				if(grid[i][j] == Pieces.CURRENT.getKey())
				{
					//There are 20 rows and 10 columns
					int temp_row;
					int temp_col;
					
					if(j < cur_row)
					{
						temp_col = cur_col - (cur_row - j);
					}
					else
					{
						temp_col = cur_col + (j - cur_row);
					}
					
					if(i < cur_col)
					{
						temp_row = cur_row + (cur_col - i);
					}
					else
					{
						temp_row = cur_row - (i - cur_col);
					}
					
					if(temp_col >= 10 || temp_row >= 20 ||  temp_col < 0 || temp_row < 0 || (grid[temp_col][temp_row] != Pieces.CURRENT.getKey() && grid[temp_col][temp_row] != Pieces.BLANK.getKey()))
					{
						i = 11;
						j = 21;
						flag = false;
					}
					
					//System.out.println("(" + i + ", " + j + ") rotates to (" + temp_col + ", " + temp_row + ")");
				}
			}
		}
		
		if(flag)
		{	
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey())
					{
						//There are 20 rows and 10 columns
						int temp_row;
						int temp_col;
						
						if(j < cur_row)
						{
							temp_col = cur_col - (cur_row - j);
						}
						else
						{
							temp_col = cur_col + (j - cur_row);
						}
						
						if(i < cur_col)
						{
							temp_row = cur_row + (cur_col - i);
						}
						else
						{
							temp_row = cur_row - (i - cur_col);
						}
						
						if(grid[i][j] == Pieces.CURRENT.getKey())
						{
							grid[i][j] = Pieces.BLANK.getKey();
						}
						else
						{
							grid[i][j] = Pieces.ROTATE.getKey();
						}
						
						if(grid[temp_col][temp_row] == Pieces.CURRENT.getKey())
						{
							grid[temp_col][temp_row] = Pieces.TWO.getKey();
						}
						else
						{
							grid[temp_col][temp_row] = Pieces.ROTATE.getKey();
						}
					}
				}
			}
		}
		
		for(int i = 0; i < 10; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				if(grid[i][j] == Pieces.ROTATE.getKey())
				{
					grid[i][j] = Pieces.CURRENT.getKey();
				}
			}
		}
	}
	
	public void rotate_left()
	{	
		boolean flag = true;
		
		for(int i = 0; i < 10; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				if(grid[i][j] == Pieces.CURRENT.getKey())
				{
					//There are 20 rows and 10 columns
					int temp_row;
					int temp_col;
					
					if(j < cur_row)
					{
						temp_col = cur_col + (j - cur_row);
					}
					else
					{
						temp_col = cur_col - (cur_row - j);
					}
					
					if(i < cur_col)
					{
						temp_row = cur_row - (i - cur_col);
					}
					else
					{
						temp_row = cur_row + (cur_col - i);
					}
					
					if(temp_col >= 10 || temp_row >= 20 || temp_col < 0 || temp_row < 0 || (grid[temp_col][temp_row] != Pieces.CURRENT.getKey() && grid[temp_col][temp_row] != Pieces.BLANK.getKey()))
					{
						i = 11;
						j = 21;
						flag = false;
					}
					
					//System.out.println("(" + i + ", " + j + ") rotates to (" + temp_col + ", " + temp_row + ")");
				}
			}
		}
		
		if(flag)
		{	
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey())
					{
						//There are 20 rows and 10 columns
						int temp_row;
						int temp_col;
						
						if(j < cur_row)
						{
							temp_col = cur_col - (cur_row - j);
						}
						else
						{
							temp_col = cur_col + (j - cur_row);
						}
						
						if(i < cur_col)
						{
							temp_row = cur_row + (cur_col - i);
						}
						else
						{
							temp_row = cur_row - (i - cur_col);
						}
						
						if(grid[i][j] == Pieces.CURRENT.getKey())
						{
							grid[i][j] = Pieces.BLANK.getKey();
						}
						else
						{
							grid[i][j] = Pieces.ROTATE.getKey();
						}
						
						if(grid[temp_col][temp_row] == Pieces.CURRENT.getKey())
						{
							grid[temp_col][temp_row] = Pieces.TWO.getKey();
						}
						else
						{
							grid[temp_col][temp_row] = Pieces.ROTATE.getKey();
						}
					}
				}
			}
		}
		
		for(int i = 0; i < 10; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				if(grid[i][j] == Pieces.ROTATE.getKey())
				{
					grid[i][j] = Pieces.CURRENT.getKey();
				}
			}
		}
	}
	
	public void print_Game()
	{
		System.out.println("\n");
		for(int i = 19; i >= 0; i --)
		{
			for(int j = 0; j < 10; j ++)
			{
				System.out.print(grid[j][i] + " ");
			}
			System.out.print("\n");
		}
	}

	public int[][] getGrid() 
	{
		return grid;
	}
	
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}
	
	public int getGridElem(int row, int col) {
		return grid[col][row];
	}

	public Pieces getCur_color() 
	{
		return cur_color;
	}

	public Pieces getNext_color() 
	{
		return next_color;
	}

	public Style getNext_style() 
	{
		return next_style;
	}

	public boolean isOver() 
	{
		return over;
	}
	
	public int getNumRows()
	{
		return NUM_ROWS;
	}
	
	public int getNumCols()
	{
		return NUM_COLS;
	}
}
